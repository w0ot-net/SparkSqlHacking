package scala.collection.mutable;

import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterator;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.View;
import scala.collection.generic.DefaultSerializable;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt$;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3$;

@ScalaSignature(
   bytes = "\u0006\u0005\r\rb\u0001B&M\u0001MCQa\u001e\u0001\u0005\u0002aDQ!\u001f\u0001\u0005Bi,AA \u0001\u0001\u007f\"I\u00111\u001c\u0001A\u0002\u0013E\u0011Q\u001c\u0005\n\u0003G\u0004\u0001\u0019!C\t\u0003KD\u0001\"!;\u0001A\u0003&\u0011q\u001c\u0005\n\u0003W\u0004\u0001\u0019!C\t\u0003;D\u0011\"!<\u0001\u0001\u0004%\t\"a<\t\u0011\u0005M\b\u0001)Q\u0005\u0003?Da\"!>\u0001\t\u0003\u0005)\u0011!A!B\u0013\t9\u0010\u0003\u0005\u0002~\u0002\u0001\u000b\u0015BA-\u0011!\ty\u0010\u0001Q!\n\u0005e\u0003b\u0002B\u0001\u0001\u0011\u0005#1\u0001\u0005\b\u0005\u000b\u0001A\u0011\tB\u0004\u0011\u001d\u0011y\u0001\u0001C!\u0005\u0007AqA!\u0005\u0001\t\u0003\u00129\u0001C\u0004\u0003\u0014\u0001!\t%a\u0016\t\u000f\tU\u0001\u0001\"\u0011\u0002X!9!q\u0003\u0001\u0005B\te\u0001b\u0002B\u0011\u0001\u0011\u0005!1\u0005\u0005\b\u0005S\u0001A\u0011\tB\u0016\u0011\u001d\u0011y\u0003\u0001C!\u0005cAqA!\u000e\u0001\t\u0003\u00119\u0004C\u0004\u0003<\u0001!\tA!\u0010\t\u000f\t\u0005\u0003\u0001\"\u0011\u0003D\u0019A!q\t\u0001!\u0004\u0013\u0011I\u0005\u0003\u0004x5\u0011\u0005!\u0011\f\u0005\t\u0005;R\u0002\u0015)\u0003\u0002`\"9!q\f\u000e\u0007\u0002\t\u0005\u0004b\u0002B45\u0011\u0005!\u0011\u0004\u0005\b\u0003\u0013SB\u0011\u0001B5\u0011\u001d\u0011Y\u0007\u0001C\u0001\u0005[B\u0001B!\u001e\u0001\t\u0003q%q\u000f\u0005\b\u0005w\u0002A\u0011\tB?\u0011\u001d\u0011\t\n\u0001C!\u0005'C\u0001B!&\u0001A\u0013%!q\u0013\u0005\t\u0005;\u0003\u0001\u0015\"\u0003\u0003 \"q!1\u0015\u0001\u0005\u0002\u0003\u0015\t\u0011!Q\u0005\n\t\u0015\u0006\u0002\u0003BZ\u0001\u0011\u0005aJ!.\t\u001d\tu\u0006\u0001\"A\u0001\u0006\u0003\u0005\t\u0015\"\u0003\u0003@\"q!q\u0019\u0001\u0005\u0002\u0003\u0015\t\u0011!Q\u0005\n\t%\u0007\u0002CAJ\u0001\u0001&IAa4\t\u0011\tU\u0007\u0001)C\u0005\u0005/D\u0001B!8\u0001A\u0013%!q\u001c\u0005\t\u0005K\u0004\u0001\u0015\"\u0003\u0003h\"A!\u0011\u001f\u0001!\n\u0013\u0011\u0019\u0010\u0003\u0005\u0003z\u0002\u0001K\u0011\u0002B~\u0011\u001d\u0019\t\u0001\u0001C!\u0007\u0007A\u0001b!\u0002\u0001A\u0013E3qA\u0004\b\u0003\u0007a\u0005\u0012AA\u0003\r\u0019YE\n#\u0001\u0002\b!1qo\rC\u0001\u0003\u001fAq!!\u00054\t\u0003\n\u0019\u0002C\u0004\u0002\u001eM\"\t!a\b\t\u000f\u0005U2\u0007\"\u0001\u00028\u00191ap\r\u0002M\u0003\u000fB!\"a\u00139\u0005\u000b\u0007I\u0011AA'\u0011)\t\u0019\u0006\u000fB\u0001B\u0003%\u0011q\n\u0005\u000b\u0003+B$Q1A\u0005\u0002\u0005]\u0003BCA0q\t\u0005\t\u0015!\u0003\u0002Z!1q\u000f\u000fC\u0001\u0003CB\u0011\"a\u001b9\u0001\u0004%\t!!\u001c\t\u0013\u0005=\u0004\b1A\u0005\u0002\u0005E\u0004\u0002CA?q\u0001\u0006K!a\u0019\t\u0013\u0005}\u0004\b1A\u0005\u0002\u00055\u0004\"CAAq\u0001\u0007I\u0011AAB\u0011!\t9\t\u000fQ!\n\u0005\r\u0004\"CAEq\u0001\u0007I\u0011AA7\u0011%\tY\t\u000fa\u0001\n\u0003\ti\t\u0003\u0005\u0002\u0012b\u0002\u000b\u0015BA2\u0011\u001d\t\u0019\n\u000fC\u0003\u0003+C\u0001\"!,4\t\u000bq\u0015q\u0016\u0005\t\u0003o\u001bDQ\u0001(\u0002X!I\u0011\u0011X\u001a\u0002\u0002\u0013%\u00111\u0018\u0002\u000e\u0019&t7.\u001a3ICND7+\u001a;\u000b\u00055s\u0015aB7vi\u0006\u0014G.\u001a\u0006\u0003\u001fB\u000b!bY8mY\u0016\u001cG/[8o\u0015\u0005\t\u0016!B:dC2\f7\u0001A\u000b\u0003)n\u001bb\u0001A+fU:\f\bc\u0001,X36\tA*\u0003\u0002Y\u0019\nY\u0011IY:ue\u0006\u001cGoU3u!\tQ6\f\u0004\u0001\u0005\u000bq\u0003!\u0019A/\u0003\u0003\u0005\u000b\"A\u00182\u0011\u0005}\u0003W\"\u0001)\n\u0005\u0005\u0004&a\u0002(pi\"Lgn\u001a\t\u0003?\u000eL!\u0001\u001a)\u0003\u0007\u0005s\u0017\u0010E\u0003WMfC\u0017.\u0003\u0002h\u0019\n11+\u001a;PaN\u0004\"A\u0016\u0001\u0011\u0007Y\u0003\u0011\fE\u0003lYfC\u0017.D\u0001O\u0013\tigJ\u0001\u000eTiJL7\r^(qi&l\u0017N_3e\u0013R,'/\u00192mK>\u00038\u000f\u0005\u0003l_fC\u0017B\u00019O\u0005]IE/\u001a:bE2,g)Y2u_JLH)\u001a4bk2$8\u000f\u0005\u0002sk6\t1O\u0003\u0002u\u001d\u00069q-\u001a8fe&\u001c\u0017B\u0001<t\u0005M!UMZ1vYR\u001cVM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\t\u0011.A\bji\u0016\u0014\u0018M\u00197f\r\u0006\u001cGo\u001c:z+\u0005Y\bcA6}Q&\u0011QP\u0014\u0002\u0010\u0013R,'/\u00192mK\u001a\u000b7\r^8ss\n)QI\u001c;ssB!\u0011\u0011\u0001\u001dZ\u001d\t1&'A\u0007MS:\\W\r\u001a%bg\"\u001cV\r\u001e\t\u0003-N\u001aBaMA\u0005wB\u0019q,a\u0003\n\u0007\u00055\u0001K\u0001\u0004B]f\u0014VM\u001a\u000b\u0003\u0003\u000b\tQ!Z7qif,B!!\u0006\u0002\u001cU\u0011\u0011q\u0003\t\u0005-\u0002\tI\u0002E\u0002[\u00037!Q\u0001X\u001bC\u0002u\u000bAA\u001a:p[V!\u0011\u0011EA\u0014)\u0011\t\u0019#a\u000b\u0011\tY\u0003\u0011Q\u0005\t\u00045\u0006\u001dBABA\u0015m\t\u0007QLA\u0001F\u0011\u001d\tiC\u000ea\u0001\u0003_\t!!\u001b;\u0011\u000b-\f\t$!\n\n\u0007\u0005MbJ\u0001\u0007Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cW-\u0001\u0006oK^\u0014U/\u001b7eKJ,B!!\u000f\u0002DU\u0011\u00111\b\t\b-\u0006u\u0012\u0011IA#\u0013\r\ty\u0004\u0014\u0002\u0010\u000fJ|w/\u00192mK\n+\u0018\u000e\u001c3feB\u0019!,a\u0011\u0005\u000bq;$\u0019A/\u0011\tY\u0003\u0011\u0011I\u000b\u0005\u0003\u0013\n\tfE\u00029\u0003\u0013\t1a[3z+\t\ty\u0005E\u0002[\u0003#\"Q\u0001\u0018\u001dC\u0002u\u000bAa[3zA\u0005!\u0001.Y:i+\t\tI\u0006E\u0002`\u00037J1!!\u0018Q\u0005\rIe\u000e^\u0001\u0006Q\u0006\u001c\b\u000e\t\u000b\u0007\u0003G\n9'!\u001b\u0011\u000b\u0005\u0015\u0004(a\u0014\u000e\u0003MBq!a\u0013>\u0001\u0004\ty\u0005C\u0004\u0002Vu\u0002\r!!\u0017\u0002\u000f\u0015\f'\u000f\\5feV\u0011\u00111M\u0001\fK\u0006\u0014H.[3s?\u0012*\u0017\u000f\u0006\u0003\u0002t\u0005e\u0004cA0\u0002v%\u0019\u0011q\u000f)\u0003\tUs\u0017\u000e\u001e\u0005\n\u0003wz\u0014\u0011!a\u0001\u0003G\n1\u0001\u001f\u00132\u0003!)\u0017M\u001d7jKJ\u0004\u0013!\u00027bi\u0016\u0014\u0018!\u00037bi\u0016\u0014x\fJ3r)\u0011\t\u0019(!\"\t\u0013\u0005m$)!AA\u0002\u0005\r\u0014A\u00027bi\u0016\u0014\b%\u0001\u0003oKb$\u0018\u0001\u00038fqR|F%Z9\u0015\t\u0005M\u0014q\u0012\u0005\n\u0003w*\u0015\u0011!a\u0001\u0003G\nQA\\3yi\u0002\n\u0011BZ5oI\u0016sGO]=\u0015\r\u0005\r\u0014qSAN\u0011\u001d\tIj\u0012a\u0001\u0003\u001f\n\u0011a\u001b\u0005\b\u0003;;\u0005\u0019AA-\u0003\u0005A\u0007fA$\u0002\"B!\u00111UAU\u001b\t\t)KC\u0002\u0002(B\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\tY+!*\u0003\u000fQ\f\u0017\u000e\u001c:fG\u0006\tB-\u001a4bk2$Hj\\1e\r\u0006\u001cGo\u001c:\u0016\u0005\u0005E\u0006cA0\u00024&\u0019\u0011Q\u0017)\u0003\r\u0011{WO\u00197f\u0003I!WMZ1vYRLg.\u001b;jC2\u001c\u0016N_3\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005u\u0006\u0003BA`\u0003\u0013l!!!1\u000b\t\u0005\r\u0017QY\u0001\u0005Y\u0006twM\u0003\u0002\u0002H\u0006!!.\u0019<b\u0013\u0011\tY-!1\u0003\r=\u0013'.Z2uQ\u001d\u0019\u0014qZAk\u0003/\u00042aXAi\u0013\r\t\u0019\u000e\u0015\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0012a\u0001\u0015\be\u0005=\u0017Q[Al\u0003)1\u0017N]:u\u000b:$(/_\u000b\u0003\u0003?\u00042!!9\u0004\u001b\u0005\u0001\u0011A\u00044jeN$XI\u001c;ss~#S-\u001d\u000b\u0005\u0003g\n9\u000fC\u0005\u0002|\u0015\t\t\u00111\u0001\u0002`\u0006Ya-\u001b:ti\u0016sGO]=!\u0003%a\u0017m\u001d;F]R\u0014\u00180A\u0007mCN$XI\u001c;ss~#S-\u001d\u000b\u0005\u0003g\n\t\u0010C\u0005\u0002|!\t\t\u00111\u0001\u0002`\u0006QA.Y:u\u000b:$(/\u001f\u0011\u0002[M\u001c\u0017\r\\1%G>dG.Z2uS>tG%\\;uC\ndW\r\n'j].,G\rS1tQN+G\u000f\n\u0013uC\ndW\rE\u0003`\u0003s\fy.C\u0002\u0002|B\u0013Q!\u0011:sCf\f\u0011\u0002\u001e5sKNDw\u000e\u001c3\u0002\u0017\r|g\u000e^3oiNK'0Z\u0001\u0005Y\u0006\u001cH/F\u0001Z\u0003)a\u0017m\u001d;PaRLwN\\\u000b\u0003\u0005\u0013\u0001Ba\u0018B\u00063&\u0019!Q\u0002)\u0003\r=\u0003H/[8o\u0003\u0011AW-\u00193\u0002\u0015!,\u0017\rZ(qi&|g.\u0001\u0003tSj,\u0017!C6o_^t7+\u001b>f\u0003\u001dI7/R7qif,\"Aa\u0007\u0011\u0007}\u0013i\"C\u0002\u0003 A\u0013qAQ8pY\u0016\fg.\u0001\u0005d_:$\u0018-\u001b8t)\u0011\u0011YB!\n\t\r\t\u001dB\u00031\u0001Z\u0003\u0011)G.Z7\u0002\u0011ML'0\u001a%j]R$B!a\u001d\u0003.!9!1C\u000bA\u0002\u0005e\u0013aA1eIR!!1\u0004B\u001a\u0011\u0019\u00119C\u0006a\u00013\u00061\u0011\r\u001a3P]\u0016$B!!9\u0003:!1!qE\fA\u0002e\u000b1b];ciJ\f7\r^(oKR!\u0011\u0011\u001dB \u0011\u0019\u00119\u0003\u0007a\u00013\u00061!/Z7pm\u0016$BAa\u0007\u0003F!1!qE\rA\u0002e\u0013Q\u0003T5oW\u0016$\u0007*Y:i'\u0016$\u0018\n^3sCR|'/\u0006\u0003\u0003L\tU3c\u0001\u000e\u0003NA)1Na\u0014\u0003T%\u0019!\u0011\u000b(\u0003!\u0005\u00137\u000f\u001e:bGRLE/\u001a:bi>\u0014\bc\u0001.\u0003V\u00111!q\u000b\u000eC\u0002u\u0013\u0011\u0001\u0016\u000b\u0003\u00057\u0002R!!9\u001b\u0005'\n1aY;s\u0003\u001d)\u0007\u0010\u001e:bGR$BAa\u0015\u0003d!9!QM\u000fA\u0002\u0005}\u0017A\u00018e\u0003\u001dA\u0017m\u001d(fqR$\"Aa\u0015\u0002\u0011%$XM]1u_J,\"Aa\u001c\u0011\t-\u0014\t(W\u0005\u0004\u0005gr%\u0001C%uKJ\fGo\u001c:\u0002\u001b\u0015tGO]=Ji\u0016\u0014\u0018\r^8s+\t\u0011I\bE\u0003l\u0005c\ny.A\u0004g_J,\u0017m\u00195\u0016\t\t}$Q\u0012\u000b\u0005\u0003g\u0012\t\tC\u0004\u0003\u0004\n\u0002\rA!\"\u0002\u0003\u0019\u0004ba\u0018BD3\n-\u0015b\u0001BE!\nIa)\u001e8di&|g.\r\t\u00045\n5EA\u0002BHE\t\u0007QLA\u0001V\u0003\u0015\u0019G.Z1s)\t\t\u0019(\u0001\u0007uC\ndWmU5{K\u001a{'\u000f\u0006\u0003\u0002Z\te\u0005b\u0002BNI\u0001\u0007\u0011\u0011L\u0001\tG\u0006\u0004\u0018mY5us\u0006aa.Z<UQJ,7\u000f[8mIR!\u0011\u0011\fBQ\u0011\u001d\u0011\u0019\"\na\u0001\u00033\n1g]2bY\u0006$3m\u001c7mK\u000e$\u0018n\u001c8%[V$\u0018M\u00197fI1Kgn[3e\u0011\u0006\u001c\bnU3uI\u0011JW\u000e\u001d:pm\u0016D\u0015m\u001d5\u0015\t\u0005e#q\u0015\u0005\b\u0005S3\u0003\u0019AA-\u00031y'/[4j]\u0006d\u0007*Y:iQ\r1#Q\u0016\t\u0004?\n=\u0016b\u0001BY!\n1\u0011N\u001c7j]\u0016\fQ\"\u001e8j[B\u0014xN^3ICNDG\u0003BA-\u0005oCqA!/(\u0001\u0004\tI&\u0001\u0007j[B\u0014xN^3e\u0011\u0006\u001c\b\u000eK\u0002(\u0005[\u000b1g]2bY\u0006$3m\u001c7mK\u000e$\u0018n\u001c8%[V$\u0018M\u00197fI1Kgn[3e\u0011\u0006\u001c\bnU3uI\u0011\u001aw.\u001c9vi\u0016D\u0015m\u001d5\u0015\t\u0005e#\u0011\u0019\u0005\u0007\u0005\u0007D\u0003\u0019A-\u0002\u0003=D3\u0001\u000bBW\u00035\u001a8-\u00197bI\r|G\u000e\\3di&|g\u000eJ7vi\u0006\u0014G.\u001a\u0013MS:\\W\r\u001a%bg\"\u001cV\r\u001e\u0013%S:$W\r\u001f\u000b\u0005\u00033\u0012Y\rC\u0004\u0002V%\u0002\r!!\u0017)\u0007%\u0012i\u000b\u0006\u0003\u0002`\nE\u0007BBA&U\u0001\u0007\u0011\fK\u0002+\u0005[\u000bab\u0019:fCR,g*Z<F]R\u0014\u0018\u0010\u0006\u0004\u0002`\ne'1\u001c\u0005\u0007\u0003\u0017Z\u0003\u0019A-\t\u000f\u0005U3\u00061\u0001\u0002Z\u0005YA-\u001a7fi\u0016,e\u000e\u001e:z)\u0011\t\u0019H!9\t\u000f\t\rH\u00061\u0001\u0002`\u0006\tQ-\u0001\u0003qkR\u0004D\u0003\u0003B\u000e\u0005S\u0014YO!<\t\r\t\u001dR\u00061\u0001Z\u0011\u001d\t)&\fa\u0001\u00033BqAa<.\u0001\u0004\tI&A\u0002jIb\fqA]3n_Z,\u0007\u0007\u0006\u0004\u0003\u001c\tU(q\u001f\u0005\u0007\u0005Oq\u0003\u0019A-\t\u000f\u0005Uc\u00061\u0001\u0002Z\u0005IqM]8x)\u0006\u0014G.\u001a\u000b\u0005\u0003g\u0012i\u0010C\u0004\u0003\u0000>\u0002\r!!\u0017\u0002\r9,w\u000f\\3o\u0003!A\u0017m\u001d5D_\u0012,GCAA-\u00031\u0019HO]5oOB\u0013XMZ5y+\t\u0019I\u0001\u0005\u0003\u0002@\u000e-\u0011\u0002BB\u0007\u0003\u0003\u0014aa\u0015;sS:<\u0007f\u0003\u0001\u0004\u0012\r]1\u0011DB\u000f\u0007?\u00012aXB\n\u0013\r\u0019)\u0002\u0015\u0002\u0016I\u0016\u0004(/Z2bi\u0016$\u0017J\u001c5fe&$\u0018M\\2f\u0003\u001diWm]:bO\u0016\f#aa\u0007\u0002A1Kgn[3e\u0011\u0006\u001c\bnU3uA]LG\u000e\u001c\u0011cK\u0002j\u0017\rZ3!M&t\u0017\r\\\u0001\u0006g&t7-Z\u0011\u0003\u0007C\tqA\r\u00182g9\n\u0014\u0007"
)
public class LinkedHashSet extends AbstractSet implements StrictOptimizedIterableOps, DefaultSerializable {
   private Entry firstEntry = null;
   private Entry lastEntry = null;
   public Entry[] scala$collection$mutable$LinkedHashSet$$table;
   private int threshold;
   private int contentSize;

   public static GrowableBuilder newBuilder() {
      return LinkedHashSet$.MODULE$.newBuilder();
   }

   public static LinkedHashSet from(final IterableOnce it) {
      return LinkedHashSet$.MODULE$.from(it);
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      LinkedHashSet$ tabulate_this = LinkedHashSet$.MODULE$;
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$7$adapted;
      IterableOnce from_source = new View.Tabulate(n1, tabulate_f);
      return tabulate_this.from(from_source);
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      LinkedHashSet$ tabulate_this = LinkedHashSet$.MODULE$;
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$5$adapted;
      IterableOnce from_source = new View.Tabulate(n1, tabulate_f);
      return tabulate_this.from(from_source);
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      LinkedHashSet$ tabulate_this = LinkedHashSet$.MODULE$;
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$3$adapted;
      IterableOnce from_source = new View.Tabulate(n1, tabulate_f);
      return tabulate_this.from(from_source);
   }

   public static Object tabulate(final int n1, final int n2, final Function2 f) {
      LinkedHashSet$ tabulate_this = LinkedHashSet$.MODULE$;
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$1$adapted;
      IterableOnce from_source = new View.Tabulate(n1, tabulate_f);
      return tabulate_this.from(from_source);
   }

   public static Object tabulate(final int n, final Function1 f) {
      LinkedHashSet$ tabulate_this = LinkedHashSet$.MODULE$;
      IterableOnce from_source = new View.Tabulate(n, f);
      return tabulate_this.from(from_source);
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      LinkedHashSet$ fill_this = LinkedHashSet$.MODULE$;
      Function0 fill_elem = IterableFactory::$anonfun$fill$4;
      IterableOnce from_source = new View.Fill(n1, fill_elem);
      return fill_this.from(from_source);
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      LinkedHashSet$ fill_this = LinkedHashSet$.MODULE$;
      Function0 fill_elem = IterableFactory::$anonfun$fill$3;
      IterableOnce from_source = new View.Fill(n1, fill_elem);
      return fill_this.from(from_source);
   }

   public static Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      LinkedHashSet$ fill_this = LinkedHashSet$.MODULE$;
      Function0 fill_elem = IterableFactory::$anonfun$fill$2;
      IterableOnce from_source = new View.Fill(n1, fill_elem);
      return fill_this.from(from_source);
   }

   public static Object fill(final int n1, final int n2, final Function0 elem) {
      LinkedHashSet$ fill_this = LinkedHashSet$.MODULE$;
      Function0 fill_elem = IterableFactory::$anonfun$fill$1;
      IterableOnce from_source = new View.Fill(n1, fill_elem);
      return fill_this.from(from_source);
   }

   public static Object fill(final int n, final Function0 elem) {
      LinkedHashSet$ fill_this = LinkedHashSet$.MODULE$;
      IterableOnce from_source = new View.Fill(n, elem);
      return fill_this.from(from_source);
   }

   public static Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(LinkedHashSet$.MODULE$, start, end, step, evidence$4);
   }

   public static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(LinkedHashSet$.MODULE$, start, end, evidence$3);
   }

   public static Object unfold(final Object init, final Function1 f) {
      LinkedHashSet$ unfold_this = LinkedHashSet$.MODULE$;
      IterableOnce from_source = new View.Unfold(init, f);
      return unfold_this.from(from_source);
   }

   public static Object iterate(final Object start, final int len, final Function1 f) {
      LinkedHashSet$ iterate_this = LinkedHashSet$.MODULE$;
      IterableOnce from_source = new View.Iterate(start, len, f);
      return iterate_this.from(from_source);
   }

   public Object writeReplace() {
      return DefaultSerializable.writeReplace$(this);
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

   public IterableFactory iterableFactory() {
      return LinkedHashSet$.MODULE$;
   }

   public Entry firstEntry() {
      return this.firstEntry;
   }

   public void firstEntry_$eq(final Entry x$1) {
      this.firstEntry = x$1;
   }

   public Entry lastEntry() {
      return this.lastEntry;
   }

   public void lastEntry_$eq(final Entry x$1) {
      this.lastEntry = x$1;
   }

   public Object last() {
      if (this.size() > 0) {
         return this.lastEntry().key();
      } else {
         throw new NoSuchElementException("Cannot call .last on empty LinkedHashSet");
      }
   }

   public Option lastOption() {
      return (Option)(this.size() > 0 ? new Some(this.lastEntry().key()) : None$.MODULE$);
   }

   public Object head() {
      if (this.size() > 0) {
         return this.firstEntry().key();
      } else {
         throw new NoSuchElementException("Cannot call .head on empty LinkedHashSet");
      }
   }

   public Option headOption() {
      return (Option)(this.size() > 0 ? new Some(this.firstEntry().key()) : None$.MODULE$);
   }

   public int size() {
      return this.contentSize;
   }

   public int knownSize() {
      return this.size();
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public boolean contains(final Object elem) {
      int findEntry_scala$collection$mutable$LinkedHashSet$$computeHash_scala$collection$mutable$LinkedHashSet$$improveHash_originalHash = Statics.anyHash(elem);
      int findEntry_hash = findEntry_scala$collection$mutable$LinkedHashSet$$computeHash_scala$collection$mutable$LinkedHashSet$$improveHash_originalHash ^ findEntry_scala$collection$mutable$LinkedHashSet$$computeHash_scala$collection$mutable$LinkedHashSet$$improveHash_originalHash >>> 16;
      Entry var3 = this.scala$collection$mutable$LinkedHashSet$$table[findEntry_hash & this.scala$collection$mutable$LinkedHashSet$$table.length - 1];
      Entry var10000 = var3 == null ? null : var3.findEntry(elem, findEntry_hash);
      Object var5 = null;
      return var10000 != null;
   }

   public void sizeHint(final int size) {
      double var10001 = (double)(size + 1);
      LinkedHashSet$ var10002 = LinkedHashSet$.MODULE$;
      int target = this.tableSizeFor((int)(var10001 / (double)0.75F));
      if (target > this.scala$collection$mutable$LinkedHashSet$$table.length) {
         this.growTable(target);
      }
   }

   public boolean add(final Object elem) {
      if (this.contentSize + 1 >= this.threshold) {
         this.growTable(this.scala$collection$mutable$LinkedHashSet$$table.length * 2);
      }

      int scala$collection$mutable$LinkedHashSet$$computeHash_scala$collection$mutable$LinkedHashSet$$improveHash_originalHash = Statics.anyHash(elem);
      int hash = scala$collection$mutable$LinkedHashSet$$computeHash_scala$collection$mutable$LinkedHashSet$$improveHash_originalHash ^ scala$collection$mutable$LinkedHashSet$$computeHash_scala$collection$mutable$LinkedHashSet$$improveHash_originalHash >>> 16;
      return this.put0(elem, hash, hash & this.scala$collection$mutable$LinkedHashSet$$table.length - 1);
   }

   public LinkedHashSet addOne(final Object elem) {
      this.add(elem);
      return this;
   }

   public LinkedHashSet subtractOne(final Object elem) {
      this.remove(elem);
      return this;
   }

   public boolean remove(final Object elem) {
      int scala$collection$mutable$LinkedHashSet$$computeHash_scala$collection$mutable$LinkedHashSet$$improveHash_originalHash = Statics.anyHash(elem);
      return this.remove0(elem, scala$collection$mutable$LinkedHashSet$$computeHash_scala$collection$mutable$LinkedHashSet$$improveHash_originalHash ^ scala$collection$mutable$LinkedHashSet$$computeHash_scala$collection$mutable$LinkedHashSet$$improveHash_originalHash >>> 16);
   }

   public Iterator iterator() {
      return new LinkedHashSetIterator() {
         public Object extract(final Entry nd) {
            return nd.key();
         }
      };
   }

   public Iterator entryIterator() {
      return new LinkedHashSetIterator() {
         public Entry extract(final Entry nd) {
            return nd;
         }
      };
   }

   public void foreach(final Function1 f) {
      for(Entry cur = this.firstEntry(); cur != null; cur = cur.later()) {
         f.apply(cur.key());
      }

   }

   public void clear() {
      Arrays.fill(this.scala$collection$mutable$LinkedHashSet$$table, (Object)null);
      this.contentSize = 0;
      this.firstEntry_$eq((Entry)null);
      this.lastEntry_$eq((Entry)null);
   }

   private int tableSizeFor(final int capacity) {
      RichInt$ var10000 = RichInt$.MODULE$;
      var10000 = RichInt$.MODULE$;
      int var2 = capacity - 1;
      int max$extension_that = 4;
      scala.math.package$ var7 = scala.math.package$.MODULE$;
      int var3 = Integer.highestOneBit(Math.max(var2, max$extension_that)) * 2;
      int min$extension_that = 1073741824;
      var7 = scala.math.package$.MODULE$;
      return Math.min(var3, min$extension_that);
   }

   private int newThreshold(final int size) {
      double var10000 = (double)size;
      LinkedHashSet$ var10001 = LinkedHashSet$.MODULE$;
      return (int)(var10000 * (double)0.75F);
   }

   public int scala$collection$mutable$LinkedHashSet$$improveHash(final int originalHash) {
      return originalHash ^ originalHash >>> 16;
   }

   public int unimproveHash(final int improvedHash) {
      return improvedHash ^ improvedHash >>> 16;
   }

   public int scala$collection$mutable$LinkedHashSet$$computeHash(final Object o) {
      int scala$collection$mutable$LinkedHashSet$$improveHash_originalHash = Statics.anyHash(o);
      return scala$collection$mutable$LinkedHashSet$$improveHash_originalHash ^ scala$collection$mutable$LinkedHashSet$$improveHash_originalHash >>> 16;
   }

   public int scala$collection$mutable$LinkedHashSet$$index(final int hash) {
      return hash & this.scala$collection$mutable$LinkedHashSet$$table.length - 1;
   }

   private Entry findEntry(final Object key) {
      int scala$collection$mutable$LinkedHashSet$$computeHash_scala$collection$mutable$LinkedHashSet$$improveHash_originalHash = Statics.anyHash(key);
      int hash = scala$collection$mutable$LinkedHashSet$$computeHash_scala$collection$mutable$LinkedHashSet$$improveHash_originalHash ^ scala$collection$mutable$LinkedHashSet$$computeHash_scala$collection$mutable$LinkedHashSet$$improveHash_originalHash >>> 16;
      Entry var3 = this.scala$collection$mutable$LinkedHashSet$$table[hash & this.scala$collection$mutable$LinkedHashSet$$table.length - 1];
      return var3 == null ? null : var3.findEntry(key, hash);
   }

   private Entry createNewEntry(final Object key, final int hash) {
      Entry e = new Entry(key, hash);
      if (this.firstEntry() == null) {
         this.firstEntry_$eq(e);
      } else {
         this.lastEntry().later_$eq(e);
         e.earlier_$eq(this.lastEntry());
      }

      this.lastEntry_$eq(e);
      return e;
   }

   private void deleteEntry(final Entry e) {
      if (e.earlier() == null) {
         this.firstEntry_$eq(e.later());
      } else {
         e.earlier().later_$eq(e.later());
      }

      if (e.later() == null) {
         this.lastEntry_$eq(e.earlier());
      } else {
         e.later().earlier_$eq(e.earlier());
      }

      e.earlier_$eq((Entry)null);
      e.later_$eq((Entry)null);
      e.next_$eq((Entry)null);
   }

   private boolean put0(final Object elem, final int hash, final int idx) {
      Entry var4 = this.scala$collection$mutable$LinkedHashSet$$table[idx];
      if (var4 == null) {
         this.scala$collection$mutable$LinkedHashSet$$table[idx] = this.createNewEntry(elem, hash);
      } else {
         Entry prev = null;

         for(Entry n = var4; n != null && n.hash() <= hash; n = n.next()) {
            if (n.hash() == hash && BoxesRunTime.equals(elem, n.key())) {
               return false;
            }

            prev = n;
         }

         Entry nnode = this.createNewEntry(elem, hash);
         if (prev == null) {
            nnode.next_$eq(var4);
            this.scala$collection$mutable$LinkedHashSet$$table[idx] = nnode;
         } else {
            nnode.next_$eq(prev.next());
            prev.next_$eq(nnode);
         }
      }

      ++this.contentSize;
      return true;
   }

   private boolean remove0(final Object elem, final int hash) {
      int idx = hash & this.scala$collection$mutable$LinkedHashSet$$table.length - 1;
      Entry var4 = this.scala$collection$mutable$LinkedHashSet$$table[idx];
      if (var4 == null) {
         return false;
      } else if (var4.hash() == hash && BoxesRunTime.equals(var4.key(), elem)) {
         this.scala$collection$mutable$LinkedHashSet$$table[idx] = var4.next();
         this.deleteEntry(var4);
         --this.contentSize;
         return true;
      } else {
         Entry prev = var4;

         for(Entry next = var4.next(); next != null && next.hash() <= hash; next = next.next()) {
            if (next.hash() == hash && BoxesRunTime.equals(next.key(), elem)) {
               prev.next_$eq(next.next());
               this.deleteEntry(next);
               --this.contentSize;
               return true;
            }

            prev = next;
         }

         return false;
      }
   }

   private void growTable(final int newlen) {
      if (newlen < 0) {
         throw new RuntimeException((new java.lang.StringBuilder(36)).append("new hash table size ").append(newlen).append(" exceeds maximum").toString());
      } else {
         int oldlen = this.scala$collection$mutable$LinkedHashSet$$table.length;
         double var10001 = (double)newlen;
         LinkedHashSet$ var10002 = LinkedHashSet$.MODULE$;
         this.threshold = (int)(var10001 * (double)0.75F);
         if (this.size() == 0) {
            this.scala$collection$mutable$LinkedHashSet$$table = new Entry[newlen];
         } else {
            this.scala$collection$mutable$LinkedHashSet$$table = (Entry[])Arrays.copyOf(this.scala$collection$mutable$LinkedHashSet$$table, newlen);
            Entry preLow = new Entry((Object)null, 0);

            for(Entry preHigh = new Entry((Object)null, 0); oldlen < newlen; oldlen *= 2) {
               for(int i = 0; i < oldlen; ++i) {
                  Entry old = this.scala$collection$mutable$LinkedHashSet$$table[i];
                  if (old != null) {
                     preLow.next_$eq((Entry)null);
                     preHigh.next_$eq((Entry)null);
                     Entry lastLow = preLow;
                     Entry lastHigh = preHigh;

                     Entry next;
                     for(Entry n = old; n != null; n = next) {
                        next = n.next();
                        if ((n.hash() & oldlen) == 0) {
                           lastLow.next_$eq(n);
                           lastLow = n;
                        } else {
                           lastHigh.next_$eq(n);
                           lastHigh = n;
                        }
                     }

                     lastLow.next_$eq((Entry)null);
                     if (old != preLow.next()) {
                        this.scala$collection$mutable$LinkedHashSet$$table[i] = preLow.next();
                     }

                     if (preHigh.next() != null) {
                        this.scala$collection$mutable$LinkedHashSet$$table[i + oldlen] = preHigh.next();
                        lastHigh.next_$eq((Entry)null);
                     }
                  }
               }
            }

         }
      }
   }

   public int hashCode() {
      Iterator setHashIterator = (Iterator)(this.isEmpty() ? this.iterator() : new LinkedHashSetIterator() {
         private int hash;
         // $FF: synthetic field
         private final LinkedHashSet $outer;

         public int hash() {
            return this.hash;
         }

         public void hash_$eq(final int x$1) {
            this.hash = x$1;
         }

         public int hashCode() {
            return this.hash();
         }

         public Object extract(final Entry nd) {
            this.hash_$eq(this.$outer.unimproveHash(nd.hash()));
            return this;
         }

         public {
            if (LinkedHashSet.this == null) {
               throw null;
            } else {
               this.$outer = LinkedHashSet.this;
               this.hash = 0;
            }
         }
      });
      return MurmurHash3$.MODULE$.unorderedHash(setHashIterator, MurmurHash3$.MODULE$.setSeed());
   }

   public String stringPrefix() {
      return "LinkedHashSet";
   }

   public LinkedHashSet() {
      LinkedHashSet$ var10002 = LinkedHashSet$.MODULE$;
      this.scala$collection$mutable$LinkedHashSet$$table = new Entry[this.tableSizeFor(16)];
      double var10001 = (double)this.scala$collection$mutable$LinkedHashSet$$table.length;
      var10002 = LinkedHashSet$.MODULE$;
      this.threshold = (int)(var10001 * (double)0.75F);
      this.contentSize = 0;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private abstract class LinkedHashSetIterator extends AbstractIterator {
      private Entry cur;
      // $FF: synthetic field
      public final LinkedHashSet $outer;

      public abstract Object extract(final Entry nd);

      public boolean hasNext() {
         return this.cur != null;
      }

      public Object next() {
         if (this.hasNext()) {
            Object r = this.extract(this.cur);
            this.cur = this.cur.later();
            return r;
         } else {
            Iterator$ var10000 = Iterator$.MODULE$;
            return Iterator$.scala$collection$Iterator$$_empty.next();
         }
      }

      // $FF: synthetic method
      public LinkedHashSet scala$collection$mutable$LinkedHashSet$LinkedHashSetIterator$$$outer() {
         return this.$outer;
      }

      public LinkedHashSetIterator() {
         if (LinkedHashSet.this == null) {
            throw null;
         } else {
            this.$outer = LinkedHashSet.this;
            super();
            this.cur = LinkedHashSet.this.firstEntry();
         }
      }
   }

   public static final class Entry {
      private final Object key;
      private final int hash;
      private Entry earlier;
      private Entry later;
      private Entry next;

      public Object key() {
         return this.key;
      }

      public int hash() {
         return this.hash;
      }

      public Entry earlier() {
         return this.earlier;
      }

      public void earlier_$eq(final Entry x$1) {
         this.earlier = x$1;
      }

      public Entry later() {
         return this.later;
      }

      public void later_$eq(final Entry x$1) {
         this.later = x$1;
      }

      public Entry next() {
         return this.next;
      }

      public void next_$eq(final Entry x$1) {
         this.next = x$1;
      }

      public final Entry findEntry(final Object k, final int h) {
         while(h != this.hash() || !BoxesRunTime.equals(k, this.key())) {
            if (this.next() == null || this.hash() > h) {
               return null;
            }

            Entry var10000 = this.next();
            h = h;
            k = k;
            this = var10000;
         }

         return this;
      }

      public Entry(final Object key, final int hash) {
         this.key = key;
         this.hash = hash;
         this.earlier = null;
         this.later = null;
         this.next = null;
      }
   }
}
