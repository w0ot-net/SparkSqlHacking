package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.Counter;
import breeze.linalg.Counter$;
import breeze.linalg.norm$;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanTransformValues;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanZipMapKeyValues;
import breeze.linalg.support.CanZipMapValues;
import breeze.math.Field;
import breeze.math.Ring;
import breeze.math.Semiring;
import breeze.storage.Zero;
import breeze.storage.Zero$;
import breeze.util.ScalaVersion$;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011UhaB\u0016-!\u0003\r\ta\r\u0005\u0006u\u0001!\ta\u000f\u0005\u0006\u007f\u0001!\u0019\u0001\u0011\u0005\u0006U\u0002!\ta\u001b\u0005\b\u0003C\u0001A1AA\u0012\u0011\u001d\t)\u0005\u0001C\u0002\u0003\u000fBq!a\u001a\u0001\t\u0007\tI\u0007C\u0004\u0002\n\u0002!\u0019!a#\t\u000f\u0005\u0005\u0006\u0001b\u0001\u0002$\"9\u0011q\u0018\u0001\u0005\u0004\u0005\u0005\u0007bBAq\u0001\u0011\r\u00111\u001d\u0005\b\u0003\u007f\u0004A1\u0001B\u0001\u0011\u001d\u00119\u0002\u0001C\u0002\u00053AqA!\u000e\u0001\t\u0007\u00119\u0004C\u0004\u0003^\u0001!\u0019Aa\u0018\t\u000f\tU\u0004\u0001b\u0001\u0003x!9!1\u0013\u0001\u0005\u0004\tU\u0005b\u0002B\\\u0001\u0011\r!\u0011\u0018\u0005\b\u0005'\u0004A1\u0001Bk\u0011\u001d\u0011y\u000f\u0001C\u0002\u0005cDqa!\u0005\u0001\t\u0007\u0019\u0019\u0002C\u0004\u0004,\u0001!\u0019a!\f\t\u000f\r\u0015\u0003\u0001b\u0001\u0004H!91Q\f\u0001\u0005\u0004\r}\u0003bBB?\u0001\u0011\r1q\u0010\u0005\b\u0007\u001f\u0003A1ABI\u0011\u001d\u0019\t\f\u0001C\u0002\u0007gCqa!5\u0001\t\u0007\u0019\u0019N\u0002\u0004\u0004v\u0002\u00011q\u001f\u0005\u000b\t'a\"1!Q\u0001\f\u0011U\u0001B\u0003C\f9\t\r\t\u0015a\u0003\u0005\u001a!9A1\u0004\u000f\u0005\u0002\u0011u\u0001b\u0002C\u00159\u0011\u0005A1\u0006\u0005\b\t\u007f\u0001A1\u0001C!\r\u0019!\t\u0007\u0001\u0001\u0005d!QAQ\u0010\u0012\u0003\u0004\u0003\u0006Y\u0001b \t\u0015\u0011\u0005%EaA!\u0002\u0017!\u0019\tC\u0004\u0005\u001c\t\"\t\u0001\"\"\t\u000f\u0011%\"\u0005\"\u0001\u0005\u0010\"9AQ\u0014\u0012\u0005B\u0011}\u0005b\u0002CT\u0001\u0011\rA\u0011\u0016\u0005\b\t\u000f\u0004A1\u0001Ce\u0011\u001d!y\u000e\u0001C\u0002\tC\u0014!bQ8v]R,'o\u00149t\u0015\tic&A\u0005pa\u0016\u0014\u0018\r^8sg*\u0011q\u0006M\u0001\u0007Y&t\u0017\r\\4\u000b\u0003E\naA\u0019:fKj,7\u0001A\n\u0003\u0001Q\u0002\"!\u000e\u001d\u000e\u0003YR\u0011aN\u0001\u0006g\u000e\fG.Y\u0005\u0003sY\u0012a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001=!\t)T(\u0003\u0002?m\t!QK\\5u\u0003\u001d\u0019\u0017M\\\"paf,2!\u0011(Y)\r\u0011%L\u0019\t\u0004\u0007\u001aCU\"\u0001#\u000b\u0005\u0015s\u0013aB:vaB|'\u000f^\u0005\u0003\u000f\u0012\u0013qaQ1o\u0007>\u0004\u0018\u0010\u0005\u0003J\u00152;V\"\u0001\u0018\n\u0005-s#aB\"pk:$XM\u001d\t\u0003\u001b:c\u0001\u0001B\u0003P\u0005\t\u0007\u0001K\u0001\u0002LcE\u0011\u0011\u000b\u0016\t\u0003kIK!a\u0015\u001c\u0003\u000f9{G\u000f[5oOB\u0011Q'V\u0005\u0003-Z\u00121!\u00118z!\ti\u0005\fB\u0003Z\u0005\t\u0007\u0001KA\u0001W\u0011\u001dY&!!AA\u0004q\u000b!\"\u001a<jI\u0016t7-\u001a\u00132!\ri\u0006mV\u0007\u0002=*\u0011q\fM\u0001\bgR|'/Y4f\u0013\t\tgL\u0001\u0003[KJ|\u0007bB2\u0003\u0003\u0003\u0005\u001d\u0001Z\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004cA3i/6\taM\u0003\u0002ha\u0005!Q.\u0019;i\u0013\tIgM\u0001\u0005TK6L'/\u001b8h\u0003i\u0011\u0017N\\1ss>\u0003hI]8n\u0005&t\u0017M]=Va\u0012\fG/Z(q+!a\u00171AA\u0005\u0003\u001bAH#B7\u0002\u0012\u0005]\u0001c\u00028uo~\fYa \b\u0003_Jl\u0011\u0001\u001d\u0006\u0003cB\nqaZ3oKJL7-\u0003\u0002ta\u0006)QKR;oG&\u0011QO\u001e\u0002\u0007+&k\u0007\u000f\u001c\u001a\u000b\u0005M\u0004\bCA'y\t\u0015I8A1\u0001{\u0005\ty\u0005/\u0005\u0002RwB\u0011A0`\u0007\u0002Y%\u0011a\u0010\f\u0002\u0007\u001fB$\u0016\u0010]3\u0011\r%S\u0015\u0011AA\u0004!\ri\u00151\u0001\u0003\u0007\u0003\u000b\u0019!\u0019\u0001)\u0003\u0003-\u00032!TA\u0005\t\u0015I6A1\u0001Q!\ri\u0015Q\u0002\u0003\u0007\u0003\u001f\u0019!\u0019\u0001)\u0003\u000b=#\b.\u001a:\t\u000f\u0005M1\u0001q\u0001\u0002\u0016\u0005!1m\u001c9z!\r\u0019ei \u0005\b\u00033\u0019\u00019AA\u000e\u0003\ty\u0007\u000fE\u0004o\u0003;9x0a\u0003\n\u0007\u0005}aO\u0001\u0007J]Bc\u0017mY3J[Bd''A\u0005bI\u0012Le\u000e^8W-V1\u0011QEA\u001d\u0003{!B!a\n\u0002@AA\u0011\u0011FA\u0018\u0003k\t)DD\u0002}\u0003WI1!!\f-\u0003\u0015y\u0005/\u00113e\u0013\u0011\ty\"!\r\n\u0007\u0005M\u0002OA\u0003V\rVt7\r\u0005\u0004J\u0015\u0006]\u00121\b\t\u0004\u001b\u0006eB!B(\u0005\u0005\u0004\u0001\u0006cA'\u0002>\u0011)\u0011\f\u0002b\u0001!\"I\u0011\u0011\t\u0003\u0002\u0002\u0003\u000f\u00111I\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004\u0003B3i\u0003w\tqaY1o\u0003b\u0004\u00180\u0006\u0004\u0002J\u0005m\u0013q\f\u000b\u0005\u0003\u0017\n\t\u0007\u0005\u0006\u0002N\u0005M\u0013qKA/\u0003/r1!SA(\u0013\r\t\tFL\u0001\tg\u000e\fG.Z!eI&!\u0011QKA\u0019\u00051Ie\u000e\u00157bG\u0016LU\u000e\u001d74!\u0019I%*!\u0017\u0002^A\u0019Q*a\u0017\u0005\u000b=+!\u0019\u0001)\u0011\u00075\u000by\u0006B\u0003Z\u000b\t\u0007\u0001\u000bC\u0005\u0002d\u0015\t\t\u0011q\u0001\u0002f\u0005QQM^5eK:\u001cW\r\n\u001b\u0011\t\u0015D\u0017QL\u0001\u0006C\u0012$gKV\u000b\u0007\u0003W\n9(a\u001f\u0015\r\u00055\u0014QPAB!)\tI#a\u001c\u0002t\u0005M\u00141O\u0005\u0005\u0003c\n\tDA\u0003J[Bd'\u0007\u0005\u0004J\u0015\u0006U\u0014\u0011\u0010\t\u0004\u001b\u0006]D!B(\u0007\u0005\u0004\u0001\u0006cA'\u0002|\u0011)\u0011L\u0002b\u0001!\"I\u0011q\u0010\u0004\u0002\u0002\u0003\u000f\u0011\u0011Q\u0001\u000bKZLG-\u001a8dK\u0012*\u0004\u0003B3i\u0003sB\u0011\"!\"\u0007\u0003\u0003\u0005\u001d!a\"\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$c\u0007\u0005\u0003^A\u0006e\u0014!C1eI&sGo\u001c,T+\u0019\ti)!&\u0002\u001aR!\u0011qRAN!!\tI#a\f\u0002\u0012\u0006]\u0005CB%K\u0003'\u000b9\nE\u0002N\u0003+#QaT\u0004C\u0002A\u00032!TAM\t\u0015IvA1\u0001Q\u0011%\tijBA\u0001\u0002\b\ty*\u0001\u0006fm&$WM\\2fI]\u0002B!\u001a5\u0002\u0018\u0006)\u0011\r\u001a3W'V1\u0011QUAW\u0003c#b!a*\u00024\u0006e\u0006CCA\u0015\u0003_\nI+a,\u0002*B1\u0011JSAV\u0003_\u00032!TAW\t\u0015y\u0005B1\u0001Q!\ri\u0015\u0011\u0017\u0003\u00063\"\u0011\r\u0001\u0015\u0005\n\u0003kC\u0011\u0011!a\u0002\u0003o\u000b!\"\u001a<jI\u0016t7-\u001a\u00139!\u0011)\u0007.a,\t\u0013\u0005m\u0006\"!AA\u0004\u0005u\u0016AC3wS\u0012,gnY3%sA!Q\fYAX\u0003%\u0019XOY%oi>4f+\u0006\u0004\u0002D\u0006E\u0017Q\u001b\u000b\u0005\u0003\u000b\f9\u000e\u0005\u0005\u0002H\u0006=\u0012QZAg\u001d\ra\u0018\u0011Z\u0005\u0004\u0003\u0017d\u0013!B(q'V\u0014\u0007CB%K\u0003\u001f\f\u0019\u000eE\u0002N\u0003#$QaT\u0005C\u0002A\u00032!TAk\t\u0015I\u0016B1\u0001Q\u0011%\tI.CA\u0001\u0002\b\tY.A\u0006fm&$WM\\2fIE\u0002\u0004#B3\u0002^\u0006M\u0017bAApM\n!!+\u001b8h\u0003\u0015\u0019XO\u0019,W+\u0019\t)/!<\u0002rR1\u0011q]Az\u0003s\u0004\"\"a2\u0002p\u0005%\u0018\u0011^Au!\u0019I%*a;\u0002pB\u0019Q*!<\u0005\u000b=S!\u0019\u0001)\u0011\u00075\u000b\t\u0010B\u0003Z\u0015\t\u0007\u0001\u000bC\u0005\u0002v*\t\t\u0011q\u0001\u0002x\u0006YQM^5eK:\u001cW\rJ\u00192!\u0015)\u0017Q\\Ax\u0011%\tYPCA\u0001\u0002\b\ti0A\u0006fm&$WM\\2fIE\u0012\u0004\u0003B/a\u0003_\f\u0011b];c\u0013:$xNV*\u0016\r\t\r!1\u0002B\b)\u0011\u0011)A!\u0005\u0011\u0011\u0005\u001d\u0017q\u0006B\u0004\u0005\u001b\u0001b!\u0013&\u0003\n\t5\u0001cA'\u0003\f\u0011)qj\u0003b\u0001!B\u0019QJa\u0004\u0005\u000be[!\u0019\u0001)\t\u0013\tM1\"!AA\u0004\tU\u0011aC3wS\u0012,gnY3%cM\u0002R!ZAo\u0005\u001b\tQa];c-N+bAa\u0007\u0003$\t\u001dBC\u0002B\u000f\u0005S\u0011y\u0003\u0005\u0006\u0002H\u0006=$q\u0004B\u0013\u0005?\u0001b!\u0013&\u0003\"\t\u0015\u0002cA'\u0003$\u0011)q\n\u0004b\u0001!B\u0019QJa\n\u0005\u000bec!\u0019\u0001)\t\u0013\t-B\"!AA\u0004\t5\u0012aC3wS\u0012,gnY3%cQ\u0002R!ZAo\u0005KA\u0011B!\r\r\u0003\u0003\u0005\u001dAa\r\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013'\u000e\t\u0005;\u0002\u0014)#\u0001\u0007dC:lU\u000f\\%oi>4f+\u0006\u0005\u0003:\t5#q\tB*)\u0011\u0011YDa\u0016\u0011\u0011\tu\u0012q\u0006B\"\u0005+r1\u0001 B \u0013\r\u0011\t\u0005L\u0001\f\u001fBlU\u000f\\*dC2\f'\u000f\u0005\u0004J\u0015\n\u0015#\u0011\u000b\t\u0004\u001b\n\u001dCAB(\u000e\u0005\u0004\u0011I%E\u0002R\u0005\u0017\u00022!\u0014B'\t\u0019\u0011y%\u0004b\u0001!\n\u00111J\r\t\u0004\u001b\nMC!B-\u000e\u0005\u0004\u0001\u0006CB%K\u0005\u0017\u0012\t\u0006C\u0005\u0003Z5\t\t\u0011q\u0001\u0003\\\u0005YQM^5eK:\u001cW\rJ\u00197!\u0011)\u0007N!\u0015\u0002\u0011\r\fg.T;m-Z+bA!\u0019\u0003j\t5D\u0003\u0002B2\u0005_\u0002\"B!\u0010\u0002p\t\u0015$Q\rB3!\u0019I%Ja\u001a\u0003lA\u0019QJ!\u001b\u0005\u000b=s!\u0019\u0001)\u0011\u00075\u0013i\u0007B\u0003Z\u001d\t\u0007\u0001\u000bC\u0004\u0003r9\u0001\u001dAa\u001d\u0002\u0011M,W.\u001b:j]\u001e\u0004B!\u001a5\u0003l\u0005a1-\u00198Nk2Le\u000e^8W'VA!\u0011\u0010BD\u0005\u0003\u0013Y\t\u0006\u0003\u0003|\t5\u0005\u0003\u0003B\u001f\u0003_\u0011iH!#\u0011\r%S%q\u0010BE!\ri%\u0011\u0011\u0003\u0007\u001f>\u0011\rAa!\u0012\u0007E\u0013)\tE\u0002N\u0005\u000f#aAa\u0014\u0010\u0005\u0004\u0001\u0006cA'\u0003\f\u0012)\u0011l\u0004b\u0001!\"I!qR\b\u0002\u0002\u0003\u000f!\u0011S\u0001\fKZLG-\u001a8dK\u0012\nt\u0007\u0005\u0003fQ\n%\u0015AD2b]6+H.\u00138u_Z\u001bv,T\u000b\t\u0005/\u0013YK!*\u00030R!!\u0011\u0014BY!!\u0011Y*a\f\u0003\"\n5fb\u0001?\u0003\u001e&\u0019!q\u0014\u0017\u0002\u0017=\u0003X*\u001e7NCR\u0014\u0018\u000e\u001f\t\u0007\u0013*\u0013\u0019K!,\u0011\u00075\u0013)\u000b\u0002\u0004P!\t\u0007!qU\t\u0004#\n%\u0006cA'\u0003,\u00121!q\n\tC\u0002A\u00032!\u0014BX\t\u0015I\u0006C1\u0001Q\u0011%\u0011\u0019\fEA\u0001\u0002\b\u0011),A\u0006fm&$WM\\2fIEB\u0004\u0003B3i\u0005[\u000b\u0001bY1o\u001bVdgkU\u000b\t\u0005w\u0013IMa1\u0003NR!!Q\u0018Bh!)\u0011i$a\u001c\u0003@\n-'q\u0018\t\u0007\u0013*\u0013\tMa3\u0011\u00075\u0013\u0019\r\u0002\u0004P#\t\u0007!QY\t\u0004#\n\u001d\u0007cA'\u0003J\u00121!qJ\tC\u0002A\u00032!\u0014Bg\t\u0015I\u0016C1\u0001Q\u0011\u001d\u0011\t(\u0005a\u0002\u0005#\u0004B!\u001a5\u0003L\u0006Q1-\u00198Nk246kX'\u0016\u0011\t]'Q\u001dBp\u0005S$BA!7\u0003lBQ!1TA8\u00057\u00149Oa7\u0011\r%S%Q\u001cBt!\ri%q\u001c\u0003\u0007\u001fJ\u0011\rA!9\u0012\u0007E\u0013\u0019\u000fE\u0002N\u0005K$aAa\u0014\u0013\u0005\u0004\u0001\u0006cA'\u0003j\u0012)\u0011L\u0005b\u0001!\"9!\u0011\u000f\nA\u0004\t5\b\u0003B3i\u0005O\fAbY1o\t&4\u0018J\u001c;p-Z+bAa=\u0004\u0002\r\u0015A\u0003\u0002B{\u0007\u000f\u0001\u0002Ba>\u00020\tu(Q \b\u0004y\ne\u0018b\u0001B~Y\u0005)q\n\u001d#jmB1\u0011J\u0013B\u0000\u0007\u0007\u00012!TB\u0001\t\u0015y5C1\u0001Q!\ri5Q\u0001\u0003\u00063N\u0011\r\u0001\u0015\u0005\n\u0007\u0013\u0019\u0012\u0011!a\u0002\u0007\u0017\t1\"\u001a<jI\u0016t7-\u001a\u00132sA)Qm!\u0004\u0004\u0004%\u00191q\u00024\u0003\u000b\u0019KW\r\u001c3\u0002\u0011\r\fg\u000eR5w-Z+ba!\u0006\u0004\u001e\r\u0005BCBB\f\u0007G\u00199\u0003\u0005\u0006\u0003x\u0006=4\u0011DB\r\u00073\u0001b!\u0013&\u0004\u001c\r}\u0001cA'\u0004\u001e\u0011)q\n\u0006b\u0001!B\u0019Qj!\t\u0005\u000be#\"\u0019\u0001)\t\u000f\u0005MA\u0003q\u0001\u0004&A!1IRB\r\u0011\u001d\u0011\t\b\u0006a\u0002\u0007S\u0001R!ZB\u0007\u0007?\t\u0001bY1o\t&4hkU\u000b\u0007\u0007_\u00199da\u000f\u0015\r\rE2QHB!!)\u001190a\u001c\u00044\re21\u0007\t\u0007\u0013*\u001b)d!\u000f\u0011\u00075\u001b9\u0004B\u0003P+\t\u0007\u0001\u000bE\u0002N\u0007w!Q!W\u000bC\u0002ACq!a\u0005\u0016\u0001\b\u0019y\u0004\u0005\u0003D\r\u000eM\u0002b\u0002B9+\u0001\u000f11\t\t\u0006K\u000e51\u0011H\u0001\rG\u0006tG)\u001b<J]R|gkU\u000b\u0007\u0007\u0013\u001a\tf!\u0016\u0015\t\r-3q\u000b\t\t\u0005o\fyc!\u0014\u0004TA1\u0011JSB(\u0007'\u00022!TB)\t\u0015yeC1\u0001Q!\ri5Q\u000b\u0003\u00063Z\u0011\r\u0001\u0015\u0005\n\u000732\u0012\u0011!a\u0002\u00077\n1\"\u001a<jI\u0016t7-\u001a\u00133aA)Qm!\u0004\u0004T\u00051\u0012.\u001c9m?>\u00038+\u001a;`\u0013:\u0004F.Y2f?\u000e{6)\u0006\u0005\u0004b\r=4\u0011PB:+\t\u0019\u0019\u0007\u0005\u0005\u0004f\u0005=21NB;\u001d\ra8qM\u0005\u0004\u0007Sb\u0013!B(q'\u0016$\bCB%K\u0007[\u001a\t\bE\u0002N\u0007_\"QaT\fC\u0002A\u00032!TB:\t\u0015IvC1\u0001Q!\u0019I%ja\u001e\u0004rA\u0019Qj!\u001f\u0005\u000f\t=sC1\u0001\u0004|E\u0019\u0011k!\u001c\u0002\u0019\r\fgnU3u\u0013:$xNV*\u0016\r\r\u00055\u0011RBG+\t\u0019\u0019\t\u0005\u0005\u0004f\u0005=2QQBF!\u0019I%ja\"\u0004\fB\u0019Qj!#\u0005\u000b=C\"\u0019\u0001)\u0011\u00075\u001bi\tB\u0003Z1\t\u0007\u0001+A\u0005dC:tUmZ1uKV111SBS\u0007S#Ba!&\u0004,BA1qSBO\u0007C\u001b\tKD\u0002}\u00073K1aa'-\u0003\u0015y\u0005OT3h\u0013\u0011\u0019y*!\r\u0003\t%k\u0007\u000f\u001c\t\u0007\u0013*\u001b\u0019ka*\u0011\u00075\u001b)\u000bB\u0003P3\t\u0007\u0001\u000bE\u0002N\u0007S#Q!W\rC\u0002ACqa!,\u001a\u0001\b\u0019y+\u0001\u0003sS:<\u0007#B3\u0002^\u000e\u001d\u0016aC2b]6+H.\u00138oKJ,ba!.\u0004D\u000e\u001dGCBB\\\u0007\u0013\u001ci\r\u0005\u0006\u0004:\u0006=4qXB`\u0007\u000bt1\u0001`B^\u0013\r\u0019i\fL\u0001\u000b\u001fBlU\u000f\\%o]\u0016\u0014\bCB%K\u0007\u0003\u001c)\rE\u0002N\u0007\u0007$Qa\u0014\u000eC\u0002A\u00032!TBd\t\u0015I&D1\u0001Q\u0011\u001d\t\u0019B\u0007a\u0002\u0007\u0017\u0004Ba\u0011$\u0004@\"9!\u0011\u000f\u000eA\u0004\r=\u0007\u0003B3i\u0007\u000b\fqaY1o\u001d>\u0014X.\u0006\u0004\u0004V\u000e\r8q\u001d\u000b\u0005\u0007/\u001cy\u000f\u0005\u0006\u0004Z\u0006=4q\\Bu\u0007St1!SBn\u0013\r\u0019iNL\u0001\u0005]>\u0014X\u000e\u0005\u0004J\u0015\u000e\u00058Q\u001d\t\u0004\u001b\u000e\rHABA\u00037\t\u0007\u0001\u000bE\u0002N\u0007O$Q!W\u000eC\u0002A\u00032!NBv\u0013\r\u0019iO\u000e\u0002\u0007\t>,(\r\\3\t\u000f\rE8\u0004q\u0001\u0004t\u0006Aan\u001c:n\u00136\u0004H\u000e\u0005\u0005\u0004Z\u000eu5Q]Bu\u0005Y\u0019\u0015M\u001c.ja6\u000b\u0007OV1mk\u0016\u001c8i\\;oi\u0016\u0014X\u0003CB}\t\u000b!I\u0001\"\u0004\u0014\tq!41 \t\f\u0007\u000euH\u0011\u0001C\u0004\t\u0017!\t\"C\u0002\u0004\u0000\u0012\u0013qbQ1o5&\u0004X*\u00199WC2,Xm\u001d\t\u0007\u0013*#\u0019\u0001b\u0002\u0011\u00075#)\u0001\u0002\u0004\u0002\u0006q\u0011\r\u0001\u0015\t\u0004\u001b\u0012%A!B-\u001d\u0005\u0004\u0001\u0006cA'\u0005\u000e\u00111Aq\u0002\u000fC\u0002A\u0013!A\u0015,\u0011\r%SE1\u0001C\u0006\u0003-)g/\u001b3f]\u000e,GEM\u0019\u0011\tu\u0003G1B\u0001\fKZLG-\u001a8dK\u0012\u0012$\u0007\u0005\u0003fQ\u0012-\u0011A\u0002\u001fj]&$h\b\u0006\u0002\u0005 Q1A\u0011\u0005C\u0013\tO\u0001\u0012\u0002b\t\u001d\t\u0007!9\u0001b\u0003\u000e\u0003\u0001Aq\u0001b\u0005 \u0001\b!)\u0002C\u0004\u0005\u0018}\u0001\u001d\u0001\"\u0007\u0002\u00075\f\u0007\u000f\u0006\u0005\u0005\u0012\u00115B\u0011\u0007C\u001b\u0011\u001d!y\u0003\ta\u0001\t\u0003\tAA\u001a:p[\"9A1\u0007\u0011A\u0002\u0011\u0005\u0011!\u00024s_6\u0014\u0004b\u0002C\u001cA\u0001\u0007A\u0011H\u0001\u0003M:\u0004\u0012\"\u000eC\u001e\t\u000f!9\u0001b\u0003\n\u0007\u0011ubGA\u0005Gk:\u001cG/[8oe\u00051!0\u001b9NCB,\u0002\u0002b\u0011\u0005J\u00115C\u0011\u000b\u000b\u0007\t\u000b\")\u0006b\u0017\u0011\u0013\u0011\rB\u0004b\u0012\u0005L\u0011=\u0003cA'\u0005J\u00111\u0011QA\u0011C\u0002A\u00032!\u0014C'\t\u0015I\u0016E1\u0001Q!\riE\u0011\u000b\u0003\u0007\t'\n#\u0019\u0001)\u0003\u0003IC\u0011\u0002b\u0016\"\u0003\u0003\u0005\u001d\u0001\"\u0017\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#g\r\t\u0005;\u0002$y\u0005C\u0005\u0005^\u0005\n\t\u0011q\u0001\u0005`\u0005YQM^5eK:\u001cW\r\n\u001a5!\u0011)\u0007\u000eb\u0014\u00033\r\u000bgNW5q\u001b\u0006\u00048*Z=WC2,Xm]\"pk:$XM]\u000b\t\tK\"\t\b\"\u001e\u0005zM!!\u0005\u000eC4!5\u0019E\u0011\u000eC7\t_\"\u0019\bb\u001e\u0005|%\u0019A1\u000e#\u0003%\r\u000bgNW5q\u001b\u0006\u00048*Z=WC2,Xm\u001d\t\u0007\u0013*#y\u0007b\u001d\u0011\u00075#\t\b\u0002\u0004\u0002\u0006\t\u0012\r\u0001\u0015\t\u0004\u001b\u0012UD!B-#\u0005\u0004\u0001\u0006cA'\u0005z\u00111Aq\u0002\u0012C\u0002A\u0003b!\u0013&\u0005p\u0011]\u0014aC3wS\u0012,gnY3%eU\u0002B!\u00181\u0005x\u0005YQM^5eK:\u001cW\r\n\u001a7!\u0011)\u0007\u000eb\u001e\u0015\u0005\u0011\u001dEC\u0002CE\t\u0017#i\tE\u0005\u0005$\t\"y\u0007b\u001d\u0005x!9AQP\u0013A\u0004\u0011}\u0004b\u0002CAK\u0001\u000fA1\u0011\u000b\t\tw\"\t\nb%\u0005\u0016\"9Aq\u0006\u0014A\u0002\u00115\u0004b\u0002C\u001aM\u0001\u0007AQ\u000e\u0005\b\to1\u0003\u0019\u0001CL!-)D\u0011\u0014C8\tg\"\u0019\bb\u001e\n\u0007\u0011meGA\u0005Gk:\u001cG/[8og\u0005IQ.\u00199BGRLg/\u001a\u000b\t\tw\"\t\u000bb)\u0005&\"9AqF\u0014A\u0002\u00115\u0004b\u0002C\u001aO\u0001\u0007AQ\u000e\u0005\b\to9\u0003\u0019\u0001CL\u0003=Q\u0018\u000e]'ba.+\u0017PV1mk\u0016\u001cX\u0003\u0003CV\tc#)\f\"/\u0015\r\u00115F1\u0018Ca!%!\u0019C\tCX\tg#9\fE\u0002N\tc#a!!\u0002)\u0005\u0004\u0001\u0006cA'\u00056\u0012)\u0011\f\u000bb\u0001!B\u0019Q\n\"/\u0005\r\u0011M\u0003F1\u0001Q\u0011%!i\fKA\u0001\u0002\b!y,A\u0006fm&$WM\\2fII:\u0004\u0003B/a\toC\u0011\u0002b1)\u0003\u0003\u0005\u001d\u0001\"2\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#\u0007\u000f\t\u0005K\"$9,\u0001\ndC:$&/\u00198tM>\u0014XNV1mk\u0016\u001cXC\u0002Cf\t/$i.\u0006\u0002\u0005NB91\tb4\u0005T\u0012m\u0017b\u0001Ci\t\n\u00112)\u00198Ue\u0006t7OZ8s[Z\u000bG.^3t!\u0019I%\n\"6\u0005\\B\u0019Q\nb6\u0005\r\u0011e\u0017F1\u0001Q\u0005\u0005a\u0005cA'\u0005^\u0012)\u0011,\u000bb\u0001!\u0006\t2-\u00198Ue\u00064XM]:f-\u0006dW/Z:\u0016\r\u0011\rHq\u001eCz+\t!)\u000fE\u0004D\tO$Y\u000f\"=\n\u0007\u0011%HIA\tDC:$&/\u0019<feN,g+\u00197vKN\u0004b!\u0013&\u0005n\u0012E\bcA'\u0005p\u00121A\u0011\u001c\u0016C\u0002A\u00032!\u0014Cz\t\u0015I&F1\u0001Q\u0001"
)
public interface CounterOps {
   // $FF: synthetic method
   static CanCopy canCopy$(final CounterOps $this, final Zero evidence$1, final Semiring evidence$2) {
      return $this.canCopy(evidence$1, evidence$2);
   }

   default CanCopy canCopy(final Zero evidence$1, final Semiring evidence$2) {
      return new CanCopy(evidence$1, evidence$2) {
         private final Zero evidence$1$1;
         private final Semiring evidence$2$1;

         public Counter apply(final Counter t) {
            return Counter$.MODULE$.apply((IterableOnce)t.iterator(), this.evidence$1$1, this.evidence$2$1);
         }

         public {
            this.evidence$1$1 = evidence$1$1;
            this.evidence$2$1 = evidence$2$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 binaryOpFromBinaryUpdateOp$(final CounterOps $this, final CanCopy copy, final UFunc.InPlaceImpl2 op) {
      return $this.binaryOpFromBinaryUpdateOp(copy, op);
   }

   default UFunc.UImpl2 binaryOpFromBinaryUpdateOp(final CanCopy copy, final UFunc.InPlaceImpl2 op) {
      return new UFunc.UImpl2(copy, op) {
         private final CanCopy copy$1;
         private final UFunc.InPlaceImpl2 op$1;

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

         public Counter apply(final Counter a, final Object b) {
            Counter c = (Counter)this.copy$1.apply(a);
            this.op$1.apply(c, b);
            return c;
         }

         public {
            this.copy$1 = copy$1;
            this.op$1 = op$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 addIntoVV$(final CounterOps $this, final Semiring evidence$3) {
      return $this.addIntoVV(evidence$3);
   }

   default UFunc.InPlaceImpl2 addIntoVV(final Semiring evidence$3) {
      return new UFunc.InPlaceImpl2(evidence$3) {
         private final Semiring field;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Semiring field() {
            return this.field;
         }

         public void apply(final Counter a, final Counter b) {
            Iterator it = ScalaVersion$.MODULE$.is213() && b == a ? b.activeIterator().toSet().iterator() : b.activeIterator();
            it.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$1(check$ifrefutable$1))).foreach((x$1) -> {
               $anonfun$apply$2(this, a, x$1);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$1(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$2(final Object $this, final Counter a$1, final Tuple2 x$1) {
            if (x$1 != null) {
               Object k = x$1._1();
               Object v = x$1._2();
               a$1.update(k, $this.field().$plus(a$1.apply(k), v));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$1);
            }
         }

         public {
            this.field = (Semiring).MODULE$.implicitly(evidence$3$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl3 canAxpy$(final CounterOps $this, final Semiring evidence$4) {
      return $this.canAxpy(evidence$4);
   }

   default UFunc.InPlaceImpl3 canAxpy(final Semiring evidence$4) {
      return new UFunc.InPlaceImpl3(evidence$4) {
         private final Semiring field;

         private Semiring field() {
            return this.field;
         }

         public void apply(final Counter a, final Object s, final Counter b) {
            Iterator it = ScalaVersion$.MODULE$.is213() && b == a ? b.activeIterator().toSet().iterator() : b.activeIterator();
            it.withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$apply$3(check$ifrefutable$2))).foreach((x$2) -> {
               $anonfun$apply$4(this, a, s, x$2);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$3(final Tuple2 check$ifrefutable$2) {
            boolean var1;
            if (check$ifrefutable$2 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$4(final Object $this, final Counter a$2, final Object s$1, final Tuple2 x$2) {
            if (x$2 != null) {
               Object k = x$2._1();
               Object v = x$2._2();
               a$2.update(k, $this.field().$plus(a$2.apply(k), $this.field().$times(s$1, v)));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$2);
            }
         }

         public {
            this.field = (Semiring).MODULE$.implicitly(evidence$4$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 addVV$(final CounterOps $this, final Semiring evidence$5, final Zero evidence$6) {
      return $this.addVV(evidence$5, evidence$6);
   }

   default UFunc.UImpl2 addVV(final Semiring evidence$5, final Zero evidence$6) {
      return this.binaryOpFromBinaryUpdateOp(this.canCopy(evidence$6, evidence$5), this.addIntoVV(evidence$5));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 addIntoVS$(final CounterOps $this, final Semiring evidence$7) {
      return $this.addIntoVS(evidence$7);
   }

   default UFunc.InPlaceImpl2 addIntoVS(final Semiring evidence$7) {
      return new UFunc.InPlaceImpl2(evidence$7) {
         private final Semiring field;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Semiring field() {
            return this.field;
         }

         public void apply(final Counter a, final Object b) {
            Iterator it = ScalaVersion$.MODULE$.is213() ? a.activeIterator().toSet().iterator() : a.activeIterator();
            it.withFilter((check$ifrefutable$3) -> BoxesRunTime.boxToBoolean($anonfun$apply$5(check$ifrefutable$3))).foreach((x$3) -> {
               $anonfun$apply$6(this, a, b, x$3);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$5(final Tuple2 check$ifrefutable$3) {
            boolean var1;
            if (check$ifrefutable$3 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$6(final Object $this, final Counter a$3, final Object b$1, final Tuple2 x$3) {
            if (x$3 != null) {
               Object k = x$3._1();
               Object v = x$3._2();
               a$3.update(k, $this.field().$plus(v, b$1));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$3);
            }
         }

         public {
            this.field = (Semiring).MODULE$.implicitly(evidence$7$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 addVS$(final CounterOps $this, final Semiring evidence$8, final Zero evidence$9) {
      return $this.addVS(evidence$8, evidence$9);
   }

   default UFunc.UImpl2 addVS(final Semiring evidence$8, final Zero evidence$9) {
      return this.binaryOpFromBinaryUpdateOp(this.canCopy(evidence$9, evidence$8), this.addIntoVS(evidence$8));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 subIntoVV$(final CounterOps $this, final Ring evidence$10) {
      return $this.subIntoVV(evidence$10);
   }

   default UFunc.InPlaceImpl2 subIntoVV(final Ring evidence$10) {
      return new UFunc.InPlaceImpl2(evidence$10) {
         private final Ring field;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Ring field() {
            return this.field;
         }

         public void apply(final Counter a, final Counter b) {
            Iterator it = ScalaVersion$.MODULE$.is213() && b == a ? b.activeIterator().toSet().iterator() : b.activeIterator();
            it.withFilter((check$ifrefutable$4) -> BoxesRunTime.boxToBoolean($anonfun$apply$7(check$ifrefutable$4))).foreach((x$4) -> {
               $anonfun$apply$8(this, a, x$4);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$7(final Tuple2 check$ifrefutable$4) {
            boolean var1;
            if (check$ifrefutable$4 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$8(final Object $this, final Counter a$4, final Tuple2 x$4) {
            if (x$4 != null) {
               Object k = x$4._1();
               Object v = x$4._2();
               a$4.update(k, $this.field().$minus(a$4.apply(k), v));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$4);
            }
         }

         public {
            this.field = (Ring).MODULE$.implicitly(evidence$10$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 subVV$(final CounterOps $this, final Ring evidence$11, final Zero evidence$12) {
      return $this.subVV(evidence$11, evidence$12);
   }

   default UFunc.UImpl2 subVV(final Ring evidence$11, final Zero evidence$12) {
      return this.binaryOpFromBinaryUpdateOp(this.canCopy(evidence$12, evidence$11), this.subIntoVV(evidence$11));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 subIntoVS$(final CounterOps $this, final Ring evidence$13) {
      return $this.subIntoVS(evidence$13);
   }

   default UFunc.InPlaceImpl2 subIntoVS(final Ring evidence$13) {
      return new UFunc.InPlaceImpl2(evidence$13) {
         private final Ring field;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Ring field() {
            return this.field;
         }

         public void apply(final Counter a, final Object b) {
            a.activeIterator().withFilter((check$ifrefutable$5) -> BoxesRunTime.boxToBoolean($anonfun$apply$9(check$ifrefutable$5))).foreach((x$5) -> {
               $anonfun$apply$10(this, a, b, x$5);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$9(final Tuple2 check$ifrefutable$5) {
            boolean var1;
            if (check$ifrefutable$5 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$10(final Object $this, final Counter a$5, final Object b$2, final Tuple2 x$5) {
            if (x$5 != null) {
               Object k = x$5._1();
               Object v = x$5._2();
               a$5.update(k, $this.field().$minus(v, b$2));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$5);
            }
         }

         public {
            this.field = (Ring).MODULE$.implicitly(evidence$13$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 subVS$(final CounterOps $this, final Ring evidence$14, final Zero evidence$15) {
      return $this.subVS(evidence$14, evidence$15);
   }

   default UFunc.UImpl2 subVS(final Ring evidence$14, final Zero evidence$15) {
      return this.binaryOpFromBinaryUpdateOp(this.canCopy(evidence$15, evidence$14), this.subIntoVS(evidence$14));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canMulIntoVV$(final CounterOps $this, final Semiring evidence$16) {
      return $this.canMulIntoVV(evidence$16);
   }

   default UFunc.InPlaceImpl2 canMulIntoVV(final Semiring evidence$16) {
      return new UFunc.InPlaceImpl2(evidence$16) {
         private final Semiring field;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Semiring field() {
            return this.field;
         }

         public void apply(final Counter a, final Counter b) {
            Iterator it = ScalaVersion$.MODULE$.is213() ? a.activeIterator().toSet().iterator() : a.activeIterator();
            it.withFilter((check$ifrefutable$6) -> BoxesRunTime.boxToBoolean($anonfun$apply$11(check$ifrefutable$6))).foreach((x$6) -> {
               $anonfun$apply$12(this, a, b, x$6);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$11(final Tuple2 check$ifrefutable$6) {
            boolean var1;
            if (check$ifrefutable$6 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$12(final Object $this, final Counter a$6, final Counter b$3, final Tuple2 x$6) {
            if (x$6 != null) {
               Object k = x$6._1();
               Object v = x$6._2();
               a$6.update(k, $this.field().$times(v, b$3.apply(k)));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$6);
            }
         }

         public {
            this.field = (Semiring).MODULE$.implicitly(evidence$16$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canMulVV$(final CounterOps $this, final Semiring semiring) {
      return $this.canMulVV(semiring);
   }

   default UFunc.UImpl2 canMulVV(final Semiring semiring) {
      return new UFunc.UImpl2(semiring) {
         private final Semiring semiring$1;

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

         public Counter apply(final Counter a, final Counter b) {
            Counter r = Counter$.MODULE$.apply(Zero$.MODULE$.zeroFromSemiring(this.semiring$1));
            a.activeIterator().withFilter((check$ifrefutable$7) -> BoxesRunTime.boxToBoolean($anonfun$apply$13(check$ifrefutable$7))).foreach((x$7) -> {
               $anonfun$apply$14(this, b, r, x$7);
               return BoxedUnit.UNIT;
            });
            return r;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$13(final Tuple2 check$ifrefutable$7) {
            boolean var1;
            if (check$ifrefutable$7 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$14(final Object $this, final Counter b$4, final Counter r$1, final Tuple2 x$7) {
            if (x$7 != null) {
               Object k = x$7._1();
               Object v = x$7._2();
               Object vr = $this.semiring$1.$times(v, b$4.apply(k));
               if (!BoxesRunTime.equals(vr, $this.semiring$1.zero())) {
                  r$1.update(k, vr);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var9 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$7);
            }
         }

         public {
            this.semiring$1 = semiring$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canMulIntoVS$(final CounterOps $this, final Semiring evidence$17) {
      return $this.canMulIntoVS(evidence$17);
   }

   default UFunc.InPlaceImpl2 canMulIntoVS(final Semiring evidence$17) {
      return new UFunc.InPlaceImpl2(evidence$17) {
         private final Semiring field;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Semiring field() {
            return this.field;
         }

         public void apply(final Counter a, final Object b) {
            Iterator it = ScalaVersion$.MODULE$.is213() ? a.activeIterator().toSet().iterator() : a.activeIterator();
            it.withFilter((check$ifrefutable$8) -> BoxesRunTime.boxToBoolean($anonfun$apply$15(check$ifrefutable$8))).foreach((x$8) -> {
               $anonfun$apply$16(this, a, b, x$8);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$15(final Tuple2 check$ifrefutable$8) {
            boolean var1;
            if (check$ifrefutable$8 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$16(final Object $this, final Counter a$7, final Object b$5, final Tuple2 x$8) {
            if (x$8 != null) {
               Object k = x$8._1();
               Object v = x$8._2();
               a$7.update(k, $this.field().$times(v, b$5));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$8);
            }
         }

         public {
            this.field = (Semiring).MODULE$.implicitly(evidence$17$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canMulIntoVS_M$(final CounterOps $this, final Semiring evidence$18) {
      return $this.canMulIntoVS_M(evidence$18);
   }

   default UFunc.InPlaceImpl2 canMulIntoVS_M(final Semiring evidence$18) {
      return new UFunc.InPlaceImpl2(evidence$18) {
         private final Semiring field;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Semiring field() {
            return this.field;
         }

         public void apply(final Counter a, final Object b) {
            Iterator it = ScalaVersion$.MODULE$.is213() ? a.activeIterator().toSet().iterator() : a.activeIterator();
            it.withFilter((check$ifrefutable$9) -> BoxesRunTime.boxToBoolean($anonfun$apply$17(check$ifrefutable$9))).foreach((x$9) -> {
               $anonfun$apply$18(this, a, b, x$9);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$17(final Tuple2 check$ifrefutable$9) {
            boolean var1;
            if (check$ifrefutable$9 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$18(final Object $this, final Counter a$8, final Object b$6, final Tuple2 x$9) {
            if (x$9 != null) {
               Object k = x$9._1();
               Object v = x$9._2();
               a$8.update(k, $this.field().$times(v, b$6));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$9);
            }
         }

         public {
            this.field = (Semiring).MODULE$.implicitly(evidence$18$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canMulVS$(final CounterOps $this, final Semiring semiring) {
      return $this.canMulVS(semiring);
   }

   default UFunc.UImpl2 canMulVS(final Semiring semiring) {
      return new UFunc.UImpl2(semiring) {
         private final Semiring semiring$2;

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

         public Counter apply(final Counter a, final Object b) {
            Counter r = Counter$.MODULE$.apply(Zero$.MODULE$.zeroFromSemiring(this.semiring$2));
            a.activeIterator().withFilter((check$ifrefutable$10) -> BoxesRunTime.boxToBoolean($anonfun$apply$19(check$ifrefutable$10))).foreach((x$10) -> {
               $anonfun$apply$20(this, b, r, x$10);
               return BoxedUnit.UNIT;
            });
            return r;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$19(final Tuple2 check$ifrefutable$10) {
            boolean var1;
            if (check$ifrefutable$10 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$20(final Object $this, final Object b$7, final Counter r$2, final Tuple2 x$10) {
            if (x$10 != null) {
               Object k = x$10._1();
               Object v = x$10._2();
               Object vr = $this.semiring$2.$times(v, b$7);
               r$2.update(k, vr);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$10);
            }
         }

         public {
            this.semiring$2 = semiring$2;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canMulVS_M$(final CounterOps $this, final Semiring semiring) {
      return $this.canMulVS_M(semiring);
   }

   default UFunc.UImpl2 canMulVS_M(final Semiring semiring) {
      return new UFunc.UImpl2(semiring) {
         private final Semiring semiring$3;

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

         public Counter apply(final Counter a, final Object b) {
            Counter r = Counter$.MODULE$.apply(Zero$.MODULE$.zeroFromSemiring(this.semiring$3));
            a.activeIterator().withFilter((check$ifrefutable$11) -> BoxesRunTime.boxToBoolean($anonfun$apply$21(check$ifrefutable$11))).foreach((x$11) -> {
               $anonfun$apply$22(this, b, r, x$11);
               return BoxedUnit.UNIT;
            });
            return r;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$21(final Tuple2 check$ifrefutable$11) {
            boolean var1;
            if (check$ifrefutable$11 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$22(final Object $this, final Object b$8, final Counter r$3, final Tuple2 x$11) {
            if (x$11 != null) {
               Object k = x$11._1();
               Object v = x$11._2();
               Object vr = $this.semiring$3.$times(v, b$8);
               r$3.update(k, vr);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$11);
            }
         }

         public {
            this.semiring$3 = semiring$3;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canDivIntoVV$(final CounterOps $this, final Field evidence$19) {
      return $this.canDivIntoVV(evidence$19);
   }

   default UFunc.InPlaceImpl2 canDivIntoVV(final Field evidence$19) {
      return new UFunc.InPlaceImpl2(evidence$19) {
         private final Field field;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Field field() {
            return this.field;
         }

         public void apply(final Counter a, final Counter b) {
            Iterator it = ScalaVersion$.MODULE$.is213() ? a.activeIterator().toSet().iterator() : a.activeIterator();
            it.withFilter((check$ifrefutable$12) -> BoxesRunTime.boxToBoolean($anonfun$apply$23(check$ifrefutable$12))).foreach((x$12) -> {
               $anonfun$apply$24(this, a, b, x$12);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$23(final Tuple2 check$ifrefutable$12) {
            boolean var1;
            if (check$ifrefutable$12 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$24(final Object $this, final Counter a$9, final Counter b$9, final Tuple2 x$12) {
            if (x$12 != null) {
               Object k = x$12._1();
               Object v = x$12._2();
               a$9.update(k, $this.field().$div(v, b$9.apply(k)));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$12);
            }
         }

         public {
            this.field = (Field).MODULE$.implicitly(evidence$19$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canDivVV$(final CounterOps $this, final CanCopy copy, final Field semiring) {
      return $this.canDivVV(copy, semiring);
   }

   default UFunc.UImpl2 canDivVV(final CanCopy copy, final Field semiring) {
      return new UFunc.UImpl2(semiring) {
         private final Field semiring$4;

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

         public Counter apply(final Counter a, final Counter b) {
            Counter r = Counter$.MODULE$.apply(Zero$.MODULE$.zeroFromSemiring(this.semiring$4));
            a.activeIterator().withFilter((check$ifrefutable$13) -> BoxesRunTime.boxToBoolean($anonfun$apply$25(check$ifrefutable$13))).foreach((x$13) -> {
               $anonfun$apply$26(this, b, r, x$13);
               return BoxedUnit.UNIT;
            });
            return r;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$25(final Tuple2 check$ifrefutable$13) {
            boolean var1;
            if (check$ifrefutable$13 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$26(final Object $this, final Counter b$10, final Counter r$4, final Tuple2 x$13) {
            if (x$13 != null) {
               Object k = x$13._1();
               Object v = x$13._2();
               Object vr = $this.semiring$4.$div(v, b$10.apply(k));
               r$4.update(k, vr);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$13);
            }
         }

         public {
            this.semiring$4 = semiring$4;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canDivVS$(final CounterOps $this, final CanCopy copy, final Field semiring) {
      return $this.canDivVS(copy, semiring);
   }

   default UFunc.UImpl2 canDivVS(final CanCopy copy, final Field semiring) {
      return new UFunc.UImpl2(semiring) {
         private final Field semiring$5;

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

         public Counter apply(final Counter a, final Object b) {
            Counter r = Counter$.MODULE$.apply(Zero$.MODULE$.zeroFromSemiring(this.semiring$5));
            a.activeIterator().withFilter((check$ifrefutable$14) -> BoxesRunTime.boxToBoolean($anonfun$apply$27(check$ifrefutable$14))).foreach((x$14) -> {
               $anonfun$apply$28(this, b, r, x$14);
               return BoxedUnit.UNIT;
            });
            return r;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$27(final Tuple2 check$ifrefutable$14) {
            boolean var1;
            if (check$ifrefutable$14 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$28(final Object $this, final Object b$11, final Counter r$5, final Tuple2 x$14) {
            if (x$14 != null) {
               Object k = x$14._1();
               Object v = x$14._2();
               Object vr = $this.semiring$5.$div(v, b$11);
               r$5.update(k, vr);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$14);
            }
         }

         public {
            this.semiring$5 = semiring$5;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canDivIntoVS$(final CounterOps $this, final Field evidence$20) {
      return $this.canDivIntoVS(evidence$20);
   }

   default UFunc.InPlaceImpl2 canDivIntoVS(final Field evidence$20) {
      return new UFunc.InPlaceImpl2(evidence$20) {
         private final Field field;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Field field() {
            return this.field;
         }

         public void apply(final Counter a, final Object b) {
            Iterator it = ScalaVersion$.MODULE$.is213() ? a.activeIterator().toSet().iterator() : a.activeIterator();
            it.withFilter((check$ifrefutable$15) -> BoxesRunTime.boxToBoolean($anonfun$apply$29(check$ifrefutable$15))).foreach((x$15) -> {
               $anonfun$apply$30(this, a, b, x$15);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$29(final Tuple2 check$ifrefutable$15) {
            boolean var1;
            if (check$ifrefutable$15 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$30(final Object $this, final Counter a$10, final Object b$12, final Tuple2 x$15) {
            if (x$15 != null) {
               Object k = x$15._1();
               Object v = x$15._2();
               a$10.update(k, $this.field().$div(v, b$12));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$15);
            }
         }

         public {
            this.field = (Field).MODULE$.implicitly(evidence$20$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_C_C$(final CounterOps $this) {
      return $this.impl_OpSet_InPlace_C_C();
   }

   default UFunc.InPlaceImpl2 impl_OpSet_InPlace_C_C() {
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

         public void apply(final Counter a, final Counter b) {
            a.data().clear();
            b.activeIterator().withFilter((check$ifrefutable$16) -> BoxesRunTime.boxToBoolean($anonfun$apply$31(check$ifrefutable$16))).foreach((x$16) -> {
               $anonfun$apply$32(a, x$16);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$31(final Tuple2 check$ifrefutable$16) {
            boolean var1;
            if (check$ifrefutable$16 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$32(final Counter a$11, final Tuple2 x$16) {
            if (x$16 != null) {
               Object k = x$16._1();
               Object v = x$16._2();
               a$11.update(k, v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$16);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canSetIntoVS$(final CounterOps $this) {
      return $this.canSetIntoVS();
   }

   default UFunc.InPlaceImpl2 canSetIntoVS() {
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

         public void apply(final Counter a, final Object b) {
            Iterator it = ScalaVersion$.MODULE$.is213() ? a.keysIterator().toSet().iterator() : a.keysIterator();
            it.foreach((k) -> {
               $anonfun$apply$33(a, b, k);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$33(final Counter a$12, final Object b$13, final Object k) {
            a$12.update(k, b$13);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl canNegate$(final CounterOps $this, final Ring ring) {
      return $this.canNegate(ring);
   }

   default UFunc.UImpl canNegate(final Ring ring) {
      return new UFunc.UImpl(ring) {
         private final Ring ring$1;

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

         public Counter apply(final Counter a) {
            Counter result = Counter$.MODULE$.apply(Zero$.MODULE$.zeroFromSemiring(this.ring$1));
            a.activeIterator().withFilter((check$ifrefutable$17) -> BoxesRunTime.boxToBoolean($anonfun$apply$34(check$ifrefutable$17))).foreach((x$17) -> {
               $anonfun$apply$35(this, result, x$17);
               return BoxedUnit.UNIT;
            });
            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$34(final Tuple2 check$ifrefutable$17) {
            boolean var1;
            if (check$ifrefutable$17 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$35(final Object $this, final Counter result$1, final Tuple2 x$17) {
            if (x$17 != null) {
               Object k = x$17._1();
               Object v = x$17._2();
               Object vr = $this.ring$1.negate(v);
               result$1.update(k, vr);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$17);
            }
         }

         public {
            this.ring$1 = ring$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canMulInner$(final CounterOps $this, final CanCopy copy, final Semiring semiring) {
      return $this.canMulInner(copy, semiring);
   }

   default UFunc.UImpl2 canMulInner(final CanCopy copy, final Semiring semiring) {
      return new UFunc.UImpl2(semiring) {
         private final Object zero;
         private final Semiring semiring$6;

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

         private Object zero() {
            return this.zero;
         }

         public Object apply(final Counter a, final Counter b) {
            while(a.activeSize() > b.activeSize()) {
               Counter var10000 = b;
               b = a;
               a = var10000;
            }

            ObjectRef result = ObjectRef.create(this.zero());
            a.activeIterator().withFilter((check$ifrefutable$18) -> BoxesRunTime.boxToBoolean($anonfun$apply$36(check$ifrefutable$18))).foreach((x$18) -> {
               $anonfun$apply$37(this, b, result, x$18);
               return BoxedUnit.UNIT;
            });
            return result.elem;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$36(final Tuple2 check$ifrefutable$18) {
            boolean var1;
            if (check$ifrefutable$18 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$37(final Object $this, final Counter b$14, final ObjectRef result$2, final Tuple2 x$18) {
            if (x$18 != null) {
               Object k = x$18._1();
               Object v = x$18._2();
               Object vr = $this.semiring$6.$times(v, b$14.apply(k));
               result$2.elem = $this.semiring$6.$plus(result$2.elem, vr);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$18);
            }
         }

         public {
            this.semiring$6 = semiring$6;
            this.zero = semiring$6.zero();
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canNorm$(final CounterOps $this, final UFunc.UImpl normImpl) {
      return $this.canNorm(normImpl);
   }

   default UFunc.UImpl2 canNorm(final UFunc.UImpl normImpl) {
      return norm$.MODULE$.fromTraverseValues(this.canTraverseValues(), normImpl);
   }

   // $FF: synthetic method
   static CanZipMapValuesCounter zipMap$(final CounterOps $this, final Zero evidence$23, final Semiring evidence$24) {
      return $this.zipMap(evidence$23, evidence$24);
   }

   default CanZipMapValuesCounter zipMap(final Zero evidence$23, final Semiring evidence$24) {
      return new CanZipMapValuesCounter(evidence$23, evidence$24);
   }

   // $FF: synthetic method
   static CanZipMapKeyValuesCounter zipMapKeyValues$(final CounterOps $this, final Zero evidence$27, final Semiring evidence$28) {
      return $this.zipMapKeyValues(evidence$27, evidence$28);
   }

   default CanZipMapKeyValuesCounter zipMapKeyValues(final Zero evidence$27, final Semiring evidence$28) {
      return new CanZipMapKeyValuesCounter(evidence$27, evidence$28);
   }

   // $FF: synthetic method
   static CanTransformValues canTransformValues$(final CounterOps $this) {
      return $this.canTransformValues();
   }

   default CanTransformValues canTransformValues() {
      return new CanTransformValues() {
         public void transform$mcD$sp(final Object from, final Function1 fn) {
            CanTransformValues.transform$mcD$sp$(this, from, fn);
         }

         public void transform$mcF$sp(final Object from, final Function1 fn) {
            CanTransformValues.transform$mcF$sp$(this, from, fn);
         }

         public void transform$mcI$sp(final Object from, final Function1 fn) {
            CanTransformValues.transform$mcI$sp$(this, from, fn);
         }

         public void transformActive$mcD$sp(final Object from, final Function1 fn) {
            CanTransformValues.transformActive$mcD$sp$(this, from, fn);
         }

         public void transformActive$mcF$sp(final Object from, final Function1 fn) {
            CanTransformValues.transformActive$mcF$sp$(this, from, fn);
         }

         public void transformActive$mcI$sp(final Object from, final Function1 fn) {
            CanTransformValues.transformActive$mcI$sp$(this, from, fn);
         }

         public void transform(final Counter from, final Function1 fn) {
            from.activeIterator().withFilter((check$ifrefutable$19) -> BoxesRunTime.boxToBoolean($anonfun$transform$1(check$ifrefutable$19))).foreach((x$19) -> {
               $anonfun$transform$2(from, fn, x$19);
               return BoxedUnit.UNIT;
            });
         }

         public void transformActive(final Counter from, final Function1 fn) {
            this.transform(from, fn);
         }

         // $FF: synthetic method
         public static final boolean $anonfun$transform$1(final Tuple2 check$ifrefutable$19) {
            boolean var1;
            if (check$ifrefutable$19 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$transform$2(final Counter from$3, final Function1 fn$3, final Tuple2 x$19) {
            if (x$19 != null) {
               Object k = x$19._1();
               Object v = x$19._2();
               from$3.update(k, fn$3.apply(v));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$19);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanTraverseValues canTraverseValues$(final CounterOps $this) {
      return $this.canTraverseValues();
   }

   default CanTraverseValues canTraverseValues() {
      return new CanTraverseValues() {
         public Object foldLeft(final Object from, final Object b, final Function2 fn) {
            return CanTraverseValues.foldLeft$(this, from, b, fn);
         }

         public CanTraverseValues.ValuesVisitor traverse(final Counter from, final CanTraverseValues.ValuesVisitor fn) {
            from.activeValuesIterator().foreach((a) -> {
               $anonfun$traverse$1(fn, a);
               return BoxedUnit.UNIT;
            });
            return fn;
         }

         public boolean isTraversableAgain(final Counter from) {
            return true;
         }

         // $FF: synthetic method
         public static final void $anonfun$traverse$1(final CanTraverseValues.ValuesVisitor fn$4, final Object a) {
            fn$4.visit(a);
         }

         public {
            CanTraverseValues.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final CounterOps $this) {
   }

   public class CanZipMapValuesCounter implements CanZipMapValues {
      private final Zero evidence$21;
      // $FF: synthetic field
      public final CounterOps $outer;

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

      public Counter map(final Counter from, final Counter from2, final Function2 fn) {
         Counter result = Counter$.MODULE$.apply(this.evidence$21);
         from.keySet().$plus$plus(from2.keySet()).foreach((k) -> {
            $anonfun$map$1(result, fn, from, from2, k);
            return BoxedUnit.UNIT;
         });
         return result;
      }

      // $FF: synthetic method
      public CounterOps breeze$linalg$operators$CounterOps$CanZipMapValuesCounter$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$map$1(final Counter result$3, final Function2 fn$1, final Counter from$1, final Counter from2$1, final Object k) {
         result$3.update(k, fn$1.apply(from$1.apply(k), from2$1.apply(k)));
      }

      public CanZipMapValuesCounter(final Zero evidence$21, final Semiring evidence$22) {
         this.evidence$21 = evidence$21;
         if (CounterOps.this == null) {
            throw null;
         } else {
            this.$outer = CounterOps.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class CanZipMapKeyValuesCounter implements CanZipMapKeyValues {
      private final Zero evidence$25;
      // $FF: synthetic field
      public final CounterOps $outer;

      public Counter map(final Counter from, final Counter from2, final Function3 fn) {
         Counter result = Counter$.MODULE$.apply(this.evidence$25);
         from.keySet().$plus$plus(from2.keySet()).foreach((k) -> {
            $anonfun$map$2(result, fn, from, from2, k);
            return BoxedUnit.UNIT;
         });
         return result;
      }

      public Counter mapActive(final Counter from, final Counter from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      // $FF: synthetic method
      public CounterOps breeze$linalg$operators$CounterOps$CanZipMapKeyValuesCounter$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$map$2(final Counter result$4, final Function3 fn$2, final Counter from$2, final Counter from2$2, final Object k) {
         result$4.update(k, fn$2.apply(k, from$2.apply(k), from2$2.apply(k)));
      }

      public CanZipMapKeyValuesCounter(final Zero evidence$25, final Semiring evidence$26) {
         this.evidence$25 = evidence$25;
         if (CounterOps.this == null) {
            throw null;
         } else {
            this.$outer = CounterOps.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
