package scala.collection.mutable;

import java.util.Arrays;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterator;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.MapFactory;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StrictOptimizedMapOps;
import scala.collection.generic.DefaultSerializable;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.RichInt$;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3$;

@ScalaSignature(
   bytes = "\u0006\u0005\u00115b\u0001B/_\u0001\u0015Dq!!\r\u0001\t\u0003\t\u0019\u0004C\u0004\u00026\u0001!\t%a\u000e\u0006\u000f\u0005}\u0002\u0001\u00011\u0002B!A!Q\b\u0001\u0005\u0002\u0001\u0014y\u0004C\u0005\u0003F\u0001\u0001\r\u0011\"\u0005\u0003@!I!q\t\u0001A\u0002\u0013E!\u0011\n\u0005\t\u0005\u001b\u0002\u0001\u0015)\u0003\u0003B!I!q\n\u0001A\u0002\u0013E!q\b\u0005\n\u0005#\u0002\u0001\u0019!C\t\u0005'B\u0001Ba\u0016\u0001A\u0003&!\u0011\t\u0005\u000f\u00053\u0002A\u0011!A\u0003\u0002\u0003\u0005\u000b\u0015\u0002B.\u0011!\u0011\t\u0007\u0001Q!\n\u0005-\u0006\u0002\u0003B2\u0001\u0001\u0006K!a+\t\u000f\t\u0015\u0004\u0001\"\u0011\u0003h!9!\u0011\u000e\u0001\u0005B\t-\u0004b\u0002B:\u0001\u0011\u0005#q\r\u0005\b\u0005k\u0002A\u0011\tB6\u0011\u001d\u00119\b\u0001C!\u0003SCqA!\u001f\u0001\t\u0003\nI\u000bC\u0004\u0003|\u0001!\tE! \t\u000f\t\u0015\u0005\u0001\"\u0001\u0003\b\"9!Q\u0012\u0001\u0005B\t=\u0005b\u0002BJ\u0001\u0011\u0005#Q\u0013\u0005\b\u00053\u0003A\u0011\tBN\u0011\u001d\u0011\t\u000b\u0001C!\u0005GCqA!+\u0001\t\u0003\u0012Y\u000bC\u0004\u00030\u0002!\tE!-\t\u000f\t%\u0007\u0001\"\u0011\u0003L\"A!Q\u001b\u0001!\n\u0013\u00119\u000e\u0003\u0005\u0003V\u0002\u0001K\u0011\u0002Bo\u00119\u0011\u0019\u000f\u0001C\u0001\u0002\u000b\u0005\t\u0011)C\u0005\u0005KD\u0001Ba=\u0001\t\u0003\u0001'Q\u001f\u0005\u000f\u0005{\u0004A\u0011!A\u0003\u0002\u0003\u0005K\u0011\u0002B\u0000\u00119\u00199\u0001\u0001C\u0001\u0002\u000b\u0005\t\u0011)C\u0005\u0007\u0013A\u0001\"a>\u0001A\u0013%1q\u0002\u0005\b\u0007+\u0001A\u0011AB\f\u0011\u001d\u0019i\u0002\u0001C\u0001\u0007?1\u0001ba\t\u0001A\u0007%1Q\u0005\u0005\b\u0003c1C\u0011AB\u001b\u0011!\u0019ID\nQ!\n\t\u0005\u0003bBB\u001eM\u0019\u00051Q\b\u0005\b\u0007\u00072C\u0011\u0001B?\u0011\u001d\tiO\nC\u0001\u0007\u000bBqaa\u0012\u0001\t\u0003\u0019IE\u0002\u0004\u0004R\u0001A11\u000b\u0005\b\u0003ciC\u0011AB/\u0011\u001d\u0019\t'\fC!\u0007GBqa!\u001d\u0001\t\u0003\u001a\u0019\bC\u0004\u0004x\u0001!\te!\u001f\t\u0011\ru\u0004\u0001\"\u0001a\u0007\u007fBqaa!\u0001\t\u0003\u001a)\tC\u0004\u0004\u0016\u0002!\tea&\t\u000f\rm\u0005\u0001\"\u0011\u0004\u001e\"91Q\u0016\u0001\u0005B\r=\u0006bBB`\u0001\u0011\u00053\u0011\u0019\u0005\t\u0007\u0007\u0004\u0001\u0015\"\u0003\u0004F\"A11\u001a\u0001!\n\u0013\u0019i\r\u0003\u0005\u0004R\u0002\u0001K\u0011BBj\u0011!\u0019Y\u000e\u0001Q\u0005\n\ru\u0007\u0002CBr\u0001\u0001&Ia!:\t\u0011\r\r\b\u0001)C\u0005\u0007kD\u0001\u0002b\u0001\u0001A\u0013%AQ\u0001\u0005\b\t\u0017\u0001A\u0011\tC\u0007\u0011!!y\u0001\u0001Q\u0005R\u0011EqaBA#=\"\u0005\u0011q\t\u0004\u0007;zC\t!!\u0013\t\u000f\u0005E\"\t\"\u0001\u0002R!9\u00111\u000b\"\u0005\u0002\u0005U\u0003bBA2\u0005\u0012\u0005\u0011Q\r\u0005\b\u0003\u007f\u0012E\u0011AAA\r\u001d\t9J\u0011\u0002_\u00033C!\"!(H\u0005\u000b\u0007I\u0011AAP\u0011)\t)k\u0012B\u0001B\u0003%\u0011\u0011\u0015\u0005\u000b\u0003O;%Q1A\u0005\u0002\u0005%\u0006BCAY\u000f\n\u0005\t\u0015!\u0003\u0002,\"Q\u00111W$\u0003\u0002\u0004%\t!!.\t\u0015\u0005mvI!a\u0001\n\u0003\ti\f\u0003\u0006\u0002J\u001e\u0013\t\u0011)Q\u0005\u0003oCq!!\rH\t\u0003\tY\rC\u0005\u0002X\u001e\u0003\r\u0011\"\u0001\u0002Z\"I\u00111\\$A\u0002\u0013\u0005\u0011Q\u001c\u0005\t\u0003C<\u0005\u0015)\u0003\u0002N\"I\u00111]$A\u0002\u0013\u0005\u0011\u0011\u001c\u0005\n\u0003K<\u0005\u0019!C\u0001\u0003OD\u0001\"a;HA\u0003&\u0011Q\u001a\u0005\n\u0003[<\u0005\u0019!C\u0001\u00033D\u0011\"a<H\u0001\u0004%\t!!=\t\u0011\u0005Ux\t)Q\u0005\u0003\u001bDq!a>H\t\u000b\tI\u0010\u0003\u0005\u0003\u0012\t#)\u0001\u0019B\n\u0011!\u0011YB\u0011C\u0003A\u0006%\u0006\"\u0003B\u000f\u0005\u0006\u0005I\u0011\u0002B\u0010\u00055a\u0015N\\6fI\"\u000b7\u000f['ba*\u0011q\fY\u0001\b[V$\u0018M\u00197f\u0015\t\t'-\u0001\u0006d_2dWm\u0019;j_:T\u0011aY\u0001\u0006g\u000e\fG.Y\u0002\u0001+\r1W\u000e_\n\r\u0001\u001dTX0!\u0002\u0002\u001a\u0005}\u0011Q\u0005\t\u0005Q&\\w/D\u0001_\u0013\tQgLA\u0006BEN$(/Y2u\u001b\u0006\u0004\bC\u00017n\u0019\u0001!QA\u001c\u0001C\u0002=\u0014\u0011aS\t\u0003aR\u0004\"!\u001d:\u000e\u0003\tL!a\u001d2\u0003\u000f9{G\u000f[5oOB\u0011\u0011/^\u0005\u0003m\n\u00141!\u00118z!\ta\u0007\u0010B\u0003z\u0001\t\u0007qNA\u0001W!\u0011A7p[<\n\u0005qt&AB*fc6\u000b\u0007\u000f\u0005\u0005i}.<\u0018\u0011AA\u0002\u0013\tyhL\u0001\u0004NCB|\u0005o\u001d\t\u0003Q\u0002\u0001B\u0001\u001b\u0001loBQ\u0011qAA\u0005\u0003\u001b\t\u0019\"a\u0001\u000e\u0003\u0001L1!a\u0003a\u0005i\u0019FO]5di>\u0003H/[7ju\u0016$\u0017\n^3sC\ndWm\u00149t!\u0015\t\u0018qB6x\u0013\r\t\tB\u0019\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0007!\f)\"C\u0002\u0002\u0018y\u0013\u0001\"\u0013;fe\u0006\u0014G.\u001a\t\u000b\u0003\u000f\tYb[<\u0002\u0002\u0005\r\u0011bAA\u000fA\n)2\u000b\u001e:jGR|\u0005\u000f^5nSj,G-T1q\u001fB\u001c\bCCA\u0004\u0003CYw/!\u0001\u0002\u0014%\u0019\u00111\u00051\u0003%5\u000b\u0007OR1di>\u0014\u0018\u0010R3gCVdGo\u001d\t\u0005\u0003O\ti#\u0004\u0002\u0002*)\u0019\u00111\u00061\u0002\u000f\u001d,g.\u001a:jG&!\u0011qFA\u0015\u0005M!UMZ1vYR\u001cVM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\u0011\u00111A\u0001\u000b[\u0006\u0004h)Y2u_JLXCAA\u001d!\u0019\t9!a\u000f\u0002\u0002%\u0019\u0011Q\b1\u0003\u00155\u000b\u0007OR1di>\u0014\u0018PA\u0003F]R\u0014\u0018\u0010E\u0003\u0002D\u001d[wO\u0004\u0002i\u0003\u0006iA*\u001b8lK\u0012D\u0015m\u001d5NCB\u0004\"\u0001\u001b\"\u0014\u000b\t\u000bY%!\u000f\u0011\u0007E\fi%C\u0002\u0002P\t\u0014a!\u00118z%\u00164GCAA$\u0003\u0015)W\u000e\u001d;z+\u0019\t9&!\u0018\u0002bU\u0011\u0011\u0011\f\t\u0007Q\u0002\tY&a\u0018\u0011\u00071\fi\u0006B\u0003o\t\n\u0007q\u000eE\u0002m\u0003C\"Q!\u001f#C\u0002=\fAA\u001a:p[V1\u0011qMA7\u0003c\"B!!\u001b\u0002tA1\u0001\u000eAA6\u0003_\u00022\u0001\\A7\t\u0015qWI1\u0001p!\ra\u0017\u0011\u000f\u0003\u0006s\u0016\u0013\ra\u001c\u0005\b\u0003k*\u0005\u0019AA<\u0003\tIG\u000f\u0005\u0004\u0002\b\u0005e\u0014QP\u0005\u0004\u0003w\u0002'\u0001D%uKJ\f'\r\\3P]\u000e,\u0007cB9\u0002\u0010\u0005-\u0014qN\u0001\u000b]\u0016<()^5mI\u0016\u0014XCBAB\u0003\u001f\u000b\u0019*\u0006\u0002\u0002\u0006B9\u0001.a\"\u0002\f\u0006U\u0015bAAE=\nyqI]8xC\ndWMQ;jY\u0012,'\u000fE\u0004r\u0003\u001f\ti)!%\u0011\u00071\fy\tB\u0003o\r\n\u0007q\u000eE\u0002m\u0003'#Q!\u001f$C\u0002=\u0004b\u0001\u001b\u0001\u0002\u000e\u0006E%a\u0003'j].,G-\u00128uef,b!a'\u0002$\u0006e6cA$\u0002L\u0005\u00191.Z=\u0016\u0005\u0005\u0005\u0006c\u00017\u0002$\u0012)an\u0012b\u0001_\u0006!1.Z=!\u0003\u0011A\u0017m\u001d5\u0016\u0005\u0005-\u0006cA9\u0002.&\u0019\u0011q\u00162\u0003\u0007%sG/A\u0003iCND\u0007%A\u0003wC2,X-\u0006\u0002\u00028B\u0019A.!/\u0005\u000be<%\u0019A8\u0002\u0013Y\fG.^3`I\u0015\fH\u0003BA`\u0003\u000b\u00042!]Aa\u0013\r\t\u0019M\u0019\u0002\u0005+:LG\u000fC\u0005\u0002H6\u000b\t\u00111\u0001\u00028\u0006\u0019\u0001\u0010J\u0019\u0002\rY\fG.^3!)!\ti-!5\u0002T\u0006U\u0007cBAh\u000f\u0006\u0005\u0016qW\u0007\u0002\u0005\"9\u0011QT(A\u0002\u0005\u0005\u0006bBAT\u001f\u0002\u0007\u00111\u0016\u0005\b\u0003g{\u0005\u0019AA\\\u0003\u001d)\u0017M\u001d7jKJ,\"!!4\u0002\u0017\u0015\f'\u000f\\5fe~#S-\u001d\u000b\u0005\u0003\u007f\u000by\u000eC\u0005\u0002HF\u000b\t\u00111\u0001\u0002N\u0006AQ-\u0019:mS\u0016\u0014\b%A\u0003mCR,'/A\u0005mCR,'o\u0018\u0013fcR!\u0011qXAu\u0011%\t9\rVA\u0001\u0002\u0004\ti-\u0001\u0004mCR,'\u000fI\u0001\u0005]\u0016DH/\u0001\u0005oKb$x\fJ3r)\u0011\ty,a=\t\u0013\u0005\u001dw+!AA\u0002\u00055\u0017!\u00028fqR\u0004\u0013!\u00034j]\u0012,e\u000e\u001e:z)\u0019\ti-a?\u0002\u0000\"9\u0011Q`-A\u0002\u0005\u0005\u0016!A6\t\u000f\t\u0005\u0011\f1\u0001\u0002,\u0006\t\u0001\u000eK\u0002Z\u0005\u000b\u0001BAa\u0002\u0003\u000e5\u0011!\u0011\u0002\u0006\u0004\u0005\u0017\u0011\u0017AC1o]>$\u0018\r^5p]&!!q\u0002B\u0005\u0005\u001d!\u0018-\u001b7sK\u000e\f\u0011\u0003Z3gCVdG\u000fT8bI\u001a\u000b7\r^8s+\t\u0011)\u0002E\u0002r\u0005/I1A!\u0007c\u0005\u0019!u.\u001e2mK\u0006\u0011B-\u001a4bk2$\u0018N\\5uS\u0006d7+\u001b>f\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011\t\u0003\u0005\u0003\u0003$\t5RB\u0001B\u0013\u0015\u0011\u00119C!\u000b\u0002\t1\fgn\u001a\u0006\u0003\u0005W\tAA[1wC&!!q\u0006B\u0013\u0005\u0019y%M[3di\":!Ia\r\u00024\ne\u0002cA9\u00036%\u0019!q\u00072\u0003!M+'/[1m-\u0016\u00148/[8o+&#e$A\u0002)\u000f\u0005\u0013\u0019$a-\u0003:\u0005YqLZ5sgR,e\u000e\u001e:z+\t\u0011\t\u0005E\u0002\u0003D\ri\u0011\u0001A\u0001\u000bM&\u00148\u000f^#oiJL\u0018A\u00044jeN$XI\u001c;ss~#S-\u001d\u000b\u0005\u0003\u007f\u0013Y\u0005C\u0005\u0002H\u001a\t\t\u00111\u0001\u0003B\u0005Ya-\u001b:ti\u0016sGO]=!\u0003%a\u0017m\u001d;F]R\u0014\u00180A\u0007mCN$XI\u001c;ss~#S-\u001d\u000b\u0005\u0003\u007f\u0013)\u0006C\u0005\u0002H&\t\t\u00111\u0001\u0003B\u0005QA.Y:u\u000b:$(/\u001f\u0011\u0002[M\u001c\u0017\r\\1%G>dG.Z2uS>tG%\\;uC\ndW\r\n'j].,G\rS1tQ6\u000b\u0007\u000f\n\u0013uC\ndW\rE\u0003r\u0005;\u0012\t%C\u0002\u0003`\t\u0014Q!\u0011:sCf\f\u0011\u0002\u001e5sKNDw\u000e\u001c3\u0002\u0017\r|g\u000e^3oiNK'0Z\u0001\u0005Y\u0006\u001cH/\u0006\u0002\u0002\u000e\u0005QA.Y:u\u001fB$\u0018n\u001c8\u0016\u0005\t5\u0004#B9\u0003p\u00055\u0011b\u0001B9E\n1q\n\u001d;j_:\fA\u0001[3bI\u0006Q\u0001.Z1e\u001fB$\u0018n\u001c8\u0002\tML'0Z\u0001\nW:|wO\\*ju\u0016\fq![:F[B$\u00180\u0006\u0002\u0003\u0000A\u0019\u0011O!!\n\u0007\t\r%MA\u0004C_>dW-\u00198\u0002\u0007\u001d,G\u000f\u0006\u0003\u0003\n\n-\u0005\u0003B9\u0003p]Da!!(\u0016\u0001\u0004Y\u0017\u0001C:ju\u0016D\u0015N\u001c;\u0015\t\u0005}&\u0011\u0013\u0005\b\u0005o2\u0002\u0019AAV\u0003!\u0019wN\u001c;bS:\u001cH\u0003\u0002B@\u0005/Ca!!(\u0018\u0001\u0004Y\u0017a\u00019viR1!\u0011\u0012BO\u0005?Ca!!(\u0019\u0001\u0004Y\u0007BBAZ1\u0001\u0007q/\u0001\u0004va\u0012\fG/\u001a\u000b\u0007\u0003\u007f\u0013)Ka*\t\r\u0005u\u0015\u00041\u0001l\u0011\u0019\t\u0019,\u0007a\u0001o\u00061!/Z7pm\u0016$BA!#\u0003.\"1\u0011Q\u0014\u000eA\u0002-\f\u0011bZ3u\u001fJ,En]3\u0016\t\tM&q\u0017\u000b\u0007\u0005k\u0013iLa0\u0011\u00071\u00149\fB\u0004\u0003:n\u0011\rAa/\u0003\u0005Y\u000b\u0014CA<u\u0011\u0019\tij\u0007a\u0001W\"A!\u0011Y\u000e\u0005\u0002\u0004\u0011\u0019-A\u0004eK\u001a\fW\u000f\u001c;\u0011\u000bE\u0014)M!.\n\u0007\t\u001d'M\u0001\u0005=Eft\u0017-\\3?\u0003=9W\r^(s\u000b2\u001cX-\u00169eCR,G#B<\u0003N\n=\u0007BBAO9\u0001\u00071\u000e\u0003\u0005\u0003Rr!\t\u0019\u0001Bj\u00031!WMZ1vYR4\u0016\r\\;f!\u0011\t(QY<\u0002\u0019I,Wn\u001c<f\u000b:$(/\u001f\u0019\u0015\t\t\u0005#\u0011\u001c\u0005\u0007\u00057l\u0002\u0019A6\u0002\t\u0015dW-\u001c\u000b\u0007\u0005\u0003\u0012yN!9\t\r\tmg\u00041\u0001l\u0011\u001d\t9K\ba\u0001\u0003W\u000b1g]2bY\u0006$3m\u001c7mK\u000e$\u0018n\u001c8%[V$\u0018M\u00197fI1Kgn[3e\u0011\u0006\u001c\b.T1qI\u0011JW\u000e\u001d:pm\u0016D\u0015m\u001d5\u0015\t\u0005-&q\u001d\u0005\b\u0005S|\u0002\u0019AAV\u00031y'/[4j]\u0006d\u0007*Y:iQ\ry\"Q\u001e\t\u0004c\n=\u0018b\u0001ByE\n1\u0011N\u001c7j]\u0016\fQ\"\u001e8j[B\u0014xN^3ICNDG\u0003BAV\u0005oDqA!?!\u0001\u0004\tY+\u0001\u0007j[B\u0014xN^3e\u0011\u0006\u001c\b\u000eK\u0002!\u0005[\f1g]2bY\u0006$3m\u001c7mK\u000e$\u0018n\u001c8%[V$\u0018M\u00197fI1Kgn[3e\u0011\u0006\u001c\b.T1qI\u0011\u001aw.\u001c9vi\u0016D\u0015m\u001d5\u0015\t\u0005-6\u0011\u0001\u0005\u0007\u0007\u0007\t\u0003\u0019A6\u0002\u0003=D3!\tBw\u00035\u001a8-\u00197bI\r|G\u000e\\3di&|g\u000eJ7vi\u0006\u0014G.\u001a\u0013MS:\\W\r\u001a%bg\"l\u0015\r\u001d\u0013%S:$W\r\u001f\u000b\u0005\u0003W\u001bY\u0001C\u0004\u0002(\n\u0002\r!a+)\u0007\t\u0012i\u000f\u0006\u0003\u0003B\rE\u0001BBAOG\u0001\u00071\u000eK\u0002$\u0005[\fa!\u00193e\u001f:,G\u0003\u0002B\"\u00073Aqaa\u0007%\u0001\u0004\ti!\u0001\u0002lm\u0006Y1/\u001e2ue\u0006\u001cGo\u00148f)\u0011\u0011\u0019e!\t\t\r\u0005uU\u00051\u0001l\u0005Ua\u0015N\\6fI\"\u000b7\u000f['ba&#XM]1u_J,Baa\n\u00042M\u0019ae!\u000b\u0011\r\u0005\u001d11FB\u0018\u0013\r\u0019i\u0003\u0019\u0002\u0011\u0003\n\u001cHO]1di&#XM]1u_J\u00042\u0001\\B\u0019\t\u0019\u0019\u0019D\nb\u0001_\n\tA\u000b\u0006\u0002\u00048A)!1\t\u0014\u00040\u0005\u00191-\u001e:\u0002\u000f\u0015DHO]1diR!1qFB \u0011\u001d\u0019\t%\u000ba\u0001\u0005\u0003\n!A\u001c3\u0002\u000f!\f7OT3yiR\u00111qF\u0001\tSR,'/\u0019;peV\u001111\n\t\u0007\u0003\u000f\u0019i%!\u0004\n\u0007\r=\u0003M\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u00051a\u0015N\\6fI.+\u0017pU3u'\ri3Q\u000b\t\u0005\u0005\u0007\u001a9&\u0003\u0003\u0004Z\rm#AB&fsN+G/\u0003\u0002\u0000AR\u00111q\f\t\u0004\u0005\u0007j\u0013aD5uKJ\f'\r\\3GC\u000e$xN]=\u0016\u0005\r\u0015\u0004CBA\u0004\u0007O\u001aY'C\u0002\u0004j\u0001\u0014q\"\u0013;fe\u0006\u0014G.\u001a$bGR|'/\u001f\t\u0005\u0003\u000f\u0019i'C\u0002\u0004p\u0001\u00141aU3u\u0003\u0019YW-_*fiV\u00111Q\u000f\t\u0006\u0003\u000f\u0019ig[\u0001\rW\u0016L8/\u0013;fe\u0006$xN]\u000b\u0003\u0007w\u0002R!a\u0002\u0004N-\fQ\"\u001a8uefLE/\u001a:bi>\u0014XCABA!\u0019\t9a!\u0014\u0003B\u0005QQ\u000f\u001d3bi\u0016<\u0016\u000e\u001e5\u0015\t\r\u001d51\u0013\u000b\u0005\u0005\u0013\u001bI\tC\u0004\u0004\fN\u0002\ra!$\u0002#I,W.\u00199qS:<g)\u001e8di&|g\u000eE\u0004r\u0007\u001f\u0013II!#\n\u0007\rE%MA\u0005Gk:\u001cG/[8oc!1\u0011QT\u001aA\u0002-\faB^1mk\u0016\u001c\u0018\n^3sCR|'/\u0006\u0002\u0004\u001aB)\u0011qAB'o\u00069am\u001c:fC\u000eDW\u0003BBP\u0007S#B!a0\u0004\"\"911U\u001bA\u0002\r\u0015\u0016!\u00014\u0011\u000fE\u001cy)!\u0004\u0004(B\u0019An!+\u0005\r\r-VG1\u0001p\u0005\u0005)\u0016\u0001\u00044pe\u0016\f7\r[#oiJLX\u0003BBY\u0007{#B!a0\u00044\"911\u0015\u001cA\u0002\rU\u0006cB9\u00048.<81X\u0005\u0004\u0007s\u0013'!\u0003$v]\u000e$\u0018n\u001c83!\ra7Q\u0018\u0003\u0007\u0007W3$\u0019A8\u0002\u000b\rdW-\u0019:\u0015\u0005\u0005}\u0016\u0001\u0004;bE2,7+\u001b>f\r>\u0014H\u0003BAV\u0007\u000fDqa!39\u0001\u0004\tY+\u0001\u0005dCB\f7-\u001b;z\u00031qWm\u001e+ie\u0016\u001c\bn\u001c7e)\u0011\tYka4\t\u000f\t]\u0014\b1\u0001\u0002,\u0006q1M]3bi\u0016tUm^#oiJLH\u0003\u0003B!\u0007+\u001c9n!7\t\r\u0005u%\b1\u0001l\u0011\u001d\t9K\u000fa\u0001\u0003WCa!a-;\u0001\u00049\u0018a\u00033fY\u0016$X-\u00128uef$B!a0\u0004`\"91\u0011]\u001eA\u0002\t\u0005\u0013!A3\u0002\tA,H\u000f\r\u000b\t\u0007O\u001cioa<\u0004rB!\u0011o!;x\u0013\r\u0019YO\u0019\u0002\u0005'>lW\r\u0003\u0004\u0002\u001er\u0002\ra\u001b\u0005\u0007\u0003gc\u0004\u0019A<\t\u000f\rMH\b1\u0001\u0003\u0000\u00051q-\u001a;PY\u0012$Bba:\u0004x\u000ee81`B\u007f\u0007\u007fDa!!(>\u0001\u0004Y\u0007BBAZ{\u0001\u0007q\u000fC\u0004\u0004tv\u0002\rAa \t\u000f\u0005\u001dV\b1\u0001\u0002,\"9A\u0011A\u001fA\u0002\u0005-\u0016aA5eq\u0006IqM]8x)\u0006\u0014G.\u001a\u000b\u0005\u0003\u007f#9\u0001C\u0004\u0005\ny\u0002\r!a+\u0002\r9,w\u000f\\3o\u0003!A\u0017m\u001d5D_\u0012,GCAAV\u00031\u0019HO]5oOB\u0013XMZ5y+\t!\u0019\u0002\u0005\u0003\u0003$\u0011U\u0011\u0002\u0002C\f\u0005K\u0011aa\u0015;sS:<\u0007f\u0003\u0001\u0005\u001c\u0011\u0005B1\u0005C\u0014\tS\u00012!\u001dC\u000f\u0013\r!yB\u0019\u0002\u0016I\u0016\u0004(/Z2bi\u0016$\u0017J\u001c5fe&$\u0018M\\2f\u0003\u001diWm]:bO\u0016\f#\u0001\"\n\u0002O2Kgn[3e\u0011\u0006\u001c\b.T1qA]LG\u000e\u001c\u0011cK\u0002j\u0017\rZ3!M&t\u0017\r\\\u001e!kN,\u0007EL<ji\"$UMZ1vYR\u0004cm\u001c:!i\",\u0007eY8n[>t\u0007%^:fA\r\f7/\u001a\u0011pM\u0002\u001aw.\u001c9vi&tw\rI1!I\u00164\u0017-\u001e7uAY\fG.^3\u0002\u000bMLgnY3\"\u0005\u0011-\u0012a\u0002\u001a/cMr\u0013'\r"
)
public class LinkedHashMap extends AbstractMap implements SeqMap, StrictOptimizedMapOps, DefaultSerializable {
   private LinkedEntry firstEntry = null;
   private LinkedEntry lastEntry = null;
   public LinkedEntry[] scala$collection$mutable$LinkedHashMap$$table;
   private int threshold;
   private int contentSize;

   public static GrowableBuilder newBuilder() {
      return LinkedHashMap$.MODULE$.newBuilder();
   }

   public static LinkedHashMap from(final IterableOnce it) {
      return LinkedHashMap$.MODULE$.from(it);
   }

   public Object writeReplace() {
      return DefaultSerializable.writeReplace$(this);
   }

   public IterableOps map(final Function1 f) {
      return StrictOptimizedMapOps.map$(this, f);
   }

   public IterableOps flatMap(final Function1 f) {
      return StrictOptimizedMapOps.flatMap$(this, f);
   }

   public IterableOps concat(final IterableOnce suffix) {
      return StrictOptimizedMapOps.concat$(this, suffix);
   }

   public IterableOps collect(final PartialFunction pf) {
      return StrictOptimizedMapOps.collect$(this, pf);
   }

   /** @deprecated */
   public IterableOps $plus(final Tuple2 elem1, final Tuple2 elem2, final scala.collection.immutable.Seq elems) {
      return StrictOptimizedMapOps.$plus$(this, elem1, elem2, elems);
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

   public MapFactory mapFactory() {
      return LinkedHashMap$.MODULE$;
   }

   public LinkedEntry _firstEntry() {
      return this.firstEntry();
   }

   public LinkedEntry firstEntry() {
      return this.firstEntry;
   }

   public void firstEntry_$eq(final LinkedEntry x$1) {
      this.firstEntry = x$1;
   }

   public LinkedEntry lastEntry() {
      return this.lastEntry;
   }

   public void lastEntry_$eq(final LinkedEntry x$1) {
      this.lastEntry = x$1;
   }

   public Tuple2 last() {
      if (this.size() > 0) {
         return new Tuple2(this.lastEntry().key(), this.lastEntry().value());
      } else {
         throw new NoSuchElementException("Cannot call .last on empty LinkedHashMap");
      }
   }

   public Option lastOption() {
      return (Option)(this.size() > 0 ? new Some(new Tuple2(this.lastEntry().key(), this.lastEntry().value())) : None$.MODULE$);
   }

   public Tuple2 head() {
      if (this.size() > 0) {
         return new Tuple2(this.firstEntry().key(), this.firstEntry().value());
      } else {
         throw new NoSuchElementException("Cannot call .head on empty LinkedHashMap");
      }
   }

   public Option headOption() {
      return (Option)(this.size() > 0 ? new Some(new Tuple2(this.firstEntry().key(), this.firstEntry().value())) : None$.MODULE$);
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

   public Option get(final Object key) {
      int findEntry_scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash = Statics.anyHash(key);
      int findEntry_hash = findEntry_scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash ^ findEntry_scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash >>> 16;
      LinkedEntry var4 = this.scala$collection$mutable$LinkedHashMap$$table[findEntry_hash & this.scala$collection$mutable$LinkedHashMap$$table.length - 1];
      LinkedEntry var10000 = var4 == null ? null : var4.findEntry(key, findEntry_hash);
      Object var6 = null;
      LinkedEntry e = var10000;
      return (Option)(e == null ? None$.MODULE$ : new Some(e.value()));
   }

   public void sizeHint(final int size) {
      double var10001 = (double)(size + 1);
      LinkedHashMap$ var10002 = LinkedHashMap$.MODULE$;
      int target = this.tableSizeFor((int)(var10001 / (double)0.75F));
      if (target > this.scala$collection$mutable$LinkedHashMap$$table.length) {
         this.growTable(target);
      }
   }

   public boolean contains(final Object key) {
      if (this.getClass() == LinkedHashMap.class) {
         int findEntry_scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash = Statics.anyHash(key);
         int findEntry_hash = findEntry_scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash ^ findEntry_scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash >>> 16;
         LinkedEntry var3 = this.scala$collection$mutable$LinkedHashMap$$table[findEntry_hash & this.scala$collection$mutable$LinkedHashMap$$table.length - 1];
         LinkedEntry var10000 = var3 == null ? null : var3.findEntry(key, findEntry_hash);
         Object var5 = null;
         return var10000 != null;
      } else {
         return scala.collection.MapOps.contains$(this, key);
      }
   }

   public Option put(final Object key, final Object value) {
      Some var3 = this.put0(key, value, true);
      return (Option)(var3 == null ? None$.MODULE$ : var3);
   }

   public void update(final Object key, final Object value) {
      this.put0(key, value, false);
   }

   public Option remove(final Object key) {
      LinkedEntry var2 = this.removeEntry0(key);
      return (Option)(var2 == null ? None$.MODULE$ : new Some(var2.value()));
   }

   public Object getOrElse(final Object key, final Function0 default) {
      Class var10000 = this.getClass();
      Class var3 = LinkedHashMap.class;
      if (var10000 != null) {
         if (var10000.equals(var3)) {
            int findEntry_scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash = Statics.anyHash(key);
            int findEntry_hash = findEntry_scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash ^ findEntry_scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash >>> 16;
            LinkedEntry var6 = this.scala$collection$mutable$LinkedHashMap$$table[findEntry_hash & this.scala$collection$mutable$LinkedHashMap$$table.length - 1];
            LinkedEntry var10 = var6 == null ? null : var6.findEntry(key, findEntry_hash);
            Object var9 = null;
            LinkedEntry nd = var10;
            if (nd == null) {
               return default.apply();
            }

            return nd.value();
         }
      }

      Option var8 = this.get(key);
      if (var8 instanceof Some) {
         return ((Some)var8).value();
      } else if (None$.MODULE$.equals(var8)) {
         return default.apply();
      } else {
         throw new MatchError(var8);
      }
   }

   public Object getOrElseUpdate(final Object key, final Function0 defaultValue) {
      Class var10000 = this.getClass();
      Class var3 = LinkedHashMap.class;
      if (var10000 != null) {
         if (var10000.equals(var3)) {
            int scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash = Statics.anyHash(key);
            int hash = scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash ^ scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash >>> 16;
            int idx = hash & this.scala$collection$mutable$LinkedHashMap$$table.length - 1;
            LinkedEntry var7 = this.scala$collection$mutable$LinkedHashMap$$table[idx];
            LinkedEntry nd = var7 == null ? null : var7.findEntry(key, hash);
            if (nd != null) {
               return nd.value();
            }

            LinkedEntry[] table0 = this.scala$collection$mutable$LinkedHashMap$$table;
            Object var9 = defaultValue.apply();
            if (this.contentSize + 1 >= this.threshold) {
               this.growTable(this.scala$collection$mutable$LinkedHashMap$$table.length * 2);
            }

            int newIdx = table0 == this.scala$collection$mutable$LinkedHashMap$$table ? idx : hash & this.scala$collection$mutable$LinkedHashMap$$table.length - 1;
            this.put0(key, var9, false, hash, newIdx);
            return var9;
         }
      }

      Option var12 = this.get(key);
      if (var12 instanceof Some) {
         return ((Some)var12).value();
      } else if (None$.MODULE$.equals(var12)) {
         Object getOrElseUpdate_d = defaultValue.apply();
         this.update(key, getOrElseUpdate_d);
         return getOrElseUpdate_d;
      } else {
         throw new MatchError(var12);
      }
   }

   private LinkedEntry removeEntry0(final Object elem) {
      int scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash = Statics.anyHash(elem);
      return this.removeEntry0(elem, scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash ^ scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash >>> 16);
   }

   private LinkedEntry removeEntry0(final Object elem, final int hash) {
      int idx = hash & this.scala$collection$mutable$LinkedHashMap$$table.length - 1;
      LinkedEntry var4 = this.scala$collection$mutable$LinkedHashMap$$table[idx];
      if (var4 == null) {
         return null;
      } else if (var4.hash() == hash && BoxesRunTime.equals(var4.key(), elem)) {
         this.scala$collection$mutable$LinkedHashMap$$table[idx] = var4.next();
         this.deleteEntry(var4);
         --this.contentSize;
         return var4;
      } else {
         LinkedEntry prev = var4;

         for(LinkedEntry next = var4.next(); next != null && next.hash() <= hash; next = next.next()) {
            if (next.hash() == hash && BoxesRunTime.equals(next.key(), elem)) {
               prev.next_$eq(next.next());
               this.deleteEntry(next);
               --this.contentSize;
               return next;
            }

            prev = next;
         }

         return null;
      }
   }

   public int scala$collection$mutable$LinkedHashMap$$improveHash(final int originalHash) {
      return originalHash ^ originalHash >>> 16;
   }

   public int unimproveHash(final int improvedHash) {
      return improvedHash ^ improvedHash >>> 16;
   }

   public int scala$collection$mutable$LinkedHashMap$$computeHash(final Object o) {
      int scala$collection$mutable$LinkedHashMap$$improveHash_originalHash = Statics.anyHash(o);
      return scala$collection$mutable$LinkedHashMap$$improveHash_originalHash ^ scala$collection$mutable$LinkedHashMap$$improveHash_originalHash >>> 16;
   }

   public int scala$collection$mutable$LinkedHashMap$$index(final int hash) {
      return hash & this.scala$collection$mutable$LinkedHashMap$$table.length - 1;
   }

   private LinkedEntry findEntry(final Object key) {
      int scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash = Statics.anyHash(key);
      int hash = scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash ^ scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash >>> 16;
      LinkedEntry var3 = this.scala$collection$mutable$LinkedHashMap$$table[hash & this.scala$collection$mutable$LinkedHashMap$$table.length - 1];
      return var3 == null ? null : var3.findEntry(key, hash);
   }

   public LinkedHashMap addOne(final Tuple2 kv) {
      this.put(kv._1(), kv._2());
      return this;
   }

   public LinkedHashMap subtractOne(final Object key) {
      this.remove(key);
      return this;
   }

   public Iterator iterator() {
      if (this.size() == 0) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new LinkedHashMapIterator() {
            public Tuple2 extract(final LinkedEntry nd) {
               return new Tuple2(nd.key(), nd.value());
            }
         };
      }
   }

   public scala.collection.Set keySet() {
      return new LinkedKeySet();
   }

   public Iterator keysIterator() {
      if (this.size() == 0) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new LinkedHashMapIterator() {
            public Object extract(final LinkedEntry nd) {
               return nd.key();
            }
         };
      }
   }

   public Iterator entryIterator() {
      if (this.size() == 0) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new LinkedHashMapIterator() {
            public LinkedEntry extract(final LinkedEntry nd) {
               return nd;
            }
         };
      }
   }

   public Option updateWith(final Object key, final Function1 remappingFunction) {
      Class var10000 = this.getClass();
      Class var3 = LinkedHashMap.class;
      if (var10000 != null) {
         if (var10000.equals(var3)) {
            int scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash = Statics.anyHash(key);
            int hash = scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash ^ scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash >>> 16;
            int indexedHash = hash & this.scala$collection$mutable$LinkedHashMap$$table.length - 1;
            LinkedEntry var23 = null;
            LinkedEntry var24 = null;
            LinkedEntry var6 = this.scala$collection$mutable$LinkedHashMap$$table[indexedHash];
            if (var6 != null) {
               LinkedEntry findEntry$1_nd = var6;
               LinkedEntry findEntry$1_prev = null;

               while(true) {
                  if (hash == findEntry$1_nd.hash() && BoxesRunTime.equals(key, findEntry$1_nd.key())) {
                     var24 = findEntry$1_prev;
                     var23 = findEntry$1_nd;
                     break;
                  }

                  if (findEntry$1_nd.next() == null || findEntry$1_nd.hash() > hash) {
                     break;
                  }

                  LinkedEntry var27 = findEntry$1_nd;
                  findEntry$1_nd = findEntry$1_nd.next();
                  findEntry$1_prev = var27;
               }

               findEntry$1_prev = null;
               Object var26 = null;
            }

            LinkedEntry var8 = var23;
            Option previousValue = (Option)(var8 == null ? None$.MODULE$ : new Some(var8.value()));
            Option nextValue = (Option)remappingFunction.apply(previousValue);
            Tuple2 var10 = new Tuple2(previousValue, nextValue);
            Option var11 = previousValue;
            if (!None$.MODULE$.equals(var11) || !None$.MODULE$.equals(nextValue)) {
               if ((Option)previousValue instanceof Some && None$.MODULE$.equals(nextValue)) {
                  if ((LinkedEntry)var24 != null) {
                     ((LinkedEntry)var24).next_$eq(((LinkedEntry)var23).next());
                  } else {
                     this.scala$collection$mutable$LinkedHashMap$$table[indexedHash] = ((LinkedEntry)var23).next();
                  }

                  this.deleteEntry(var23);
                  --this.contentSize;
               } else {
                  Option var12 = previousValue;
                  if (None$.MODULE$.equals(var12) && nextValue instanceof Some) {
                     Object value = ((Some)nextValue).value();
                     int var28;
                     if (this.contentSize + 1 >= this.threshold) {
                        this.growTable(this.scala$collection$mutable$LinkedHashMap$$table.length * 2);
                        var28 = hash & this.scala$collection$mutable$LinkedHashMap$$table.length - 1;
                     } else {
                        var28 = indexedHash;
                     }

                     int newIndexedHash = var28;
                     this.put0(key, value, false, hash, newIndexedHash);
                  } else {
                     if (!((Option)previousValue instanceof Some) || !(nextValue instanceof Some)) {
                        throw new MatchError(var10);
                     }

                     Object newValue = ((Some)nextValue).value();
                     ((LinkedEntry)var23).value_$eq(newValue);
                  }
               }
            }

            return nextValue;
         }
      }

      Option updateWith_previousValue = this.get(key);
      Option updateWith_nextValue = (Option)remappingFunction.apply(updateWith_previousValue);
      Tuple2 var21 = new Tuple2(updateWith_previousValue, updateWith_nextValue);
      if (!None$.MODULE$.equals(updateWith_previousValue) || !None$.MODULE$.equals(updateWith_nextValue)) {
         if (updateWith_previousValue instanceof Some && None$.MODULE$.equals(updateWith_nextValue)) {
            this.remove(key);
         } else {
            if (!(updateWith_nextValue instanceof Some)) {
               throw new MatchError(var21);
            }

            Object updateWith_v = ((Some)updateWith_nextValue).value();
            this.update(key, updateWith_v);
         }
      }

      return updateWith_nextValue;
   }

   public Iterator valuesIterator() {
      if (this.size() == 0) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new LinkedHashMapIterator() {
            public Object extract(final LinkedEntry nd) {
               return nd.value();
            }
         };
      }
   }

   public void foreach(final Function1 f) {
      for(LinkedEntry cur = this.firstEntry(); cur != null; cur = cur.later()) {
         f.apply(new Tuple2(cur.key(), cur.value()));
      }

   }

   public void foreachEntry(final Function2 f) {
      for(LinkedEntry cur = this.firstEntry(); cur != null; cur = cur.later()) {
         f.apply(cur.key(), cur.value());
      }

   }

   public void clear() {
      Arrays.fill(this.scala$collection$mutable$LinkedHashMap$$table, (Object)null);
      this.contentSize = 0;
      this.firstEntry_$eq((LinkedEntry)null);
      this.lastEntry_$eq((LinkedEntry)null);
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
      LinkedHashMap$ var10001 = LinkedHashMap$.MODULE$;
      return (int)(var10000 * (double)0.75F);
   }

   private LinkedEntry createNewEntry(final Object key, final int hash, final Object value) {
      LinkedEntry e = new LinkedEntry(key, hash, value);
      if (this.firstEntry() == null) {
         this.firstEntry_$eq(e);
      } else {
         this.lastEntry().later_$eq(e);
         e.earlier_$eq(this.lastEntry());
      }

      this.lastEntry_$eq(e);
      return e;
   }

   private void deleteEntry(final LinkedEntry e) {
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

      e.earlier_$eq((LinkedEntry)null);
      e.later_$eq((LinkedEntry)null);
      e.next_$eq((LinkedEntry)null);
   }

   private Some put0(final Object key, final Object value, final boolean getOld) {
      if (this.contentSize + 1 >= this.threshold) {
         this.growTable(this.scala$collection$mutable$LinkedHashMap$$table.length * 2);
      }

      int scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash = Statics.anyHash(key);
      int hash = scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash ^ scala$collection$mutable$LinkedHashMap$$computeHash_scala$collection$mutable$LinkedHashMap$$improveHash_originalHash >>> 16;
      int idx = hash & this.scala$collection$mutable$LinkedHashMap$$table.length - 1;
      return this.put0(key, value, getOld, hash, idx);
   }

   private Some put0(final Object key, final Object value, final boolean getOld, final int hash, final int idx) {
      LinkedEntry var6 = this.scala$collection$mutable$LinkedHashMap$$table[idx];
      if (var6 == null) {
         this.scala$collection$mutable$LinkedHashMap$$table[idx] = this.createNewEntry(key, hash, value);
      } else {
         LinkedEntry prev = null;

         for(LinkedEntry n = var6; n != null && n.hash() <= hash; n = n.next()) {
            if (n.hash() == hash && BoxesRunTime.equals(key, n.key())) {
               Object old = n.value();
               n.value_$eq(value);
               if (getOld) {
                  return new Some(old);
               }

               return null;
            }

            prev = n;
         }

         LinkedEntry nnode = this.createNewEntry(key, hash, value);
         if (prev == null) {
            nnode.next_$eq(var6);
            this.scala$collection$mutable$LinkedHashMap$$table[idx] = nnode;
         } else {
            nnode.next_$eq(prev.next());
            prev.next_$eq(nnode);
         }
      }

      ++this.contentSize;
      return null;
   }

   private void growTable(final int newlen) {
      if (newlen < 0) {
         throw new RuntimeException((new java.lang.StringBuilder(36)).append("new hash table size ").append(newlen).append(" exceeds maximum").toString());
      } else {
         int oldlen = this.scala$collection$mutable$LinkedHashMap$$table.length;
         double var10001 = (double)newlen;
         LinkedHashMap$ var10002 = LinkedHashMap$.MODULE$;
         this.threshold = (int)(var10001 * (double)0.75F);
         if (this.size() == 0) {
            this.scala$collection$mutable$LinkedHashMap$$table = new LinkedEntry[newlen];
         } else {
            this.scala$collection$mutable$LinkedHashMap$$table = (LinkedEntry[])Arrays.copyOf(this.scala$collection$mutable$LinkedHashMap$$table, newlen);
            LinkedEntry preLow = new LinkedEntry((Object)null, 0, (Object)null);

            for(LinkedEntry preHigh = new LinkedEntry((Object)null, 0, (Object)null); oldlen < newlen; oldlen *= 2) {
               for(int i = 0; i < oldlen; ++i) {
                  LinkedEntry old = this.scala$collection$mutable$LinkedHashMap$$table[i];
                  if (old != null) {
                     preLow.next_$eq((LinkedEntry)null);
                     preHigh.next_$eq((LinkedEntry)null);
                     LinkedEntry lastLow = preLow;
                     LinkedEntry lastHigh = preHigh;

                     LinkedEntry next;
                     for(LinkedEntry n = old; n != null; n = next) {
                        next = n.next();
                        if ((n.hash() & oldlen) == 0) {
                           lastLow.next_$eq(n);
                           lastLow = n;
                        } else {
                           lastHigh.next_$eq(n);
                           lastHigh = n;
                        }
                     }

                     lastLow.next_$eq((LinkedEntry)null);
                     if (old != preLow.next()) {
                        this.scala$collection$mutable$LinkedHashMap$$table[i] = preLow.next();
                     }

                     if (preHigh.next() != null) {
                        this.scala$collection$mutable$LinkedHashMap$$table[i + oldlen] = preHigh.next();
                        lastHigh.next_$eq((LinkedEntry)null);
                     }
                  }
               }
            }

         }
      }
   }

   public int hashCode() {
      if (this.isEmpty()) {
         return MurmurHash3$.MODULE$.emptyMapHash();
      } else {
         LinkedHashMapIterator tupleHashIterator = new LinkedHashMapIterator() {
            private int hash;
            // $FF: synthetic field
            private final LinkedHashMap $outer;

            public int hash() {
               return this.hash;
            }

            public void hash_$eq(final int x$1) {
               this.hash = x$1;
            }

            public int hashCode() {
               return this.hash();
            }

            public Object extract(final LinkedEntry nd) {
               this.hash_$eq(MurmurHash3$.MODULE$.tuple2Hash(this.$outer.unimproveHash(nd.hash()), Statics.anyHash(nd.value())));
               return this;
            }

            public {
               if (LinkedHashMap.this == null) {
                  throw null;
               } else {
                  this.$outer = LinkedHashMap.this;
                  this.hash = 0;
               }
            }
         };
         return MurmurHash3$.MODULE$.unorderedHash(tupleHashIterator, MurmurHash3$.MODULE$.mapSeed());
      }
   }

   public String stringPrefix() {
      return "LinkedHashMap";
   }

   private final void findEntry$1(final LinkedEntry prev, final LinkedEntry nd, final Object k, final int h, final ObjectRef previousEntry$1, final ObjectRef foundEntry$1) {
      while(h != nd.hash() || !BoxesRunTime.equals(k, nd.key())) {
         if (nd.next() == null || nd.hash() > h) {
            return;
         }

         LinkedEntry var10000 = nd;
         LinkedEntry var10001 = nd.next();
         h = h;
         k = k;
         nd = var10001;
         prev = var10000;
      }

      previousEntry$1.elem = prev;
      foundEntry$1.elem = nd;
   }

   public LinkedHashMap() {
      LinkedHashMap$ var10002 = LinkedHashMap$.MODULE$;
      this.scala$collection$mutable$LinkedHashMap$$table = new LinkedEntry[this.tableSizeFor(16)];
      double var10001 = (double)this.scala$collection$mutable$LinkedHashMap$$table.length;
      var10002 = LinkedHashMap$.MODULE$;
      this.threshold = (int)(var10001 * (double)0.75F);
      this.contentSize = 0;
   }

   private abstract class LinkedHashMapIterator extends AbstractIterator {
      private LinkedEntry cur;
      // $FF: synthetic field
      public final LinkedHashMap $outer;

      public abstract Object extract(final LinkedEntry nd);

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
      public LinkedHashMap scala$collection$mutable$LinkedHashMap$LinkedHashMapIterator$$$outer() {
         return this.$outer;
      }

      public LinkedHashMapIterator() {
         if (LinkedHashMap.this == null) {
            throw null;
         } else {
            this.$outer = LinkedHashMap.this;
            super();
            this.cur = LinkedHashMap.this.firstEntry();
         }
      }
   }

   public class LinkedKeySet extends scala.collection.MapOps.KeySet {
      public IterableFactory iterableFactory() {
         return LinkedHashSet$.MODULE$;
      }

      // $FF: synthetic method
      public LinkedHashMap scala$collection$mutable$LinkedHashMap$LinkedKeySet$$$outer() {
         return (LinkedHashMap)this.$outer;
      }
   }

   public static final class LinkedEntry {
      private final Object key;
      private final int hash;
      private Object value;
      private LinkedEntry earlier;
      private LinkedEntry later;
      private LinkedEntry next;

      public Object key() {
         return this.key;
      }

      public int hash() {
         return this.hash;
      }

      public Object value() {
         return this.value;
      }

      public void value_$eq(final Object x$1) {
         this.value = x$1;
      }

      public LinkedEntry earlier() {
         return this.earlier;
      }

      public void earlier_$eq(final LinkedEntry x$1) {
         this.earlier = x$1;
      }

      public LinkedEntry later() {
         return this.later;
      }

      public void later_$eq(final LinkedEntry x$1) {
         this.later = x$1;
      }

      public LinkedEntry next() {
         return this.next;
      }

      public void next_$eq(final LinkedEntry x$1) {
         this.next = x$1;
      }

      public final LinkedEntry findEntry(final Object k, final int h) {
         while(h != this.hash() || !BoxesRunTime.equals(k, this.key())) {
            if (this.next() == null || this.hash() > h) {
               return null;
            }

            LinkedEntry var10000 = this.next();
            h = h;
            k = k;
            this = var10000;
         }

         return this;
      }

      public LinkedEntry(final Object key, final int hash, final Object value) {
         this.key = key;
         this.hash = hash;
         this.value = value;
         super();
         this.earlier = null;
         this.later = null;
         this.next = null;
      }
   }
}
