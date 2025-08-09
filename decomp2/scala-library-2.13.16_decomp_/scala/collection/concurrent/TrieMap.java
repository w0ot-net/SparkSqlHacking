package scala.collection.concurrent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.package$;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.MapFactory;
import scala.collection.MapOps;
import scala.collection.MapView;
import scala.collection.Set;
import scala.collection.generic.DefaultSerializable;
import scala.collection.mutable.AbstractMap;
import scala.collection.mutable.GrowableBuilder;
import scala.math.Equiv;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;
import scala.util.Try$;
import scala.util.control.NonFatal$;
import scala.util.hashing.Hashing;
import scala.util.hashing.Hashing$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011Uf\u0001B+W\u0005uC!\"!\u0006\u0001\u0005\u0003\u0005\u000b\u0011BA\f\u0011)\ti\u0002\u0001B\u0001B\u0003%\u0011q\u0004\u0005\u000b\u0003k\u0001!\u0011!Q\u0001\n\u0005]\u0002BCA#\u0001\t\u0005\t\u0015!\u0003\u0002H!9\u0011Q\u000b\u0001\u0005\n\u0005]\u0003\u0002CA1\u0001\u0001\u0006K!a\u000e\t\u0011\u0005\r\u0004\u0001)Q\u0005\u0003\u000fB\u0001\"!\u001a\u0001A\u0003&\u0011q\u0004\u0005\b\u0003{\u0001A\u0011AA8\u0011\u001d\t\t\b\u0001C\u0001\u0003gB\u0011\"!\u001e\u0001\u0001\u0004%I!a\u001e\t\u0013\u0005e\u0004\u00011A\u0005\n\u0005m\u0004\u0002CAD\u0001\u0001\u0006K!a\u0006\t\u000f\u0005U\u0003\u0001\"\u0001\u0002\u0012\"9\u0011Q\u000b\u0001\u0005\u0002\u0005]\u0005bBAM\u0001\u0011\u0005\u00131\u0014\u0005\b\u0003G\u0003A\u0011BAS\u0011\u001d\t9\f\u0001C\u0005\u0003sCq!!2\u0001\t\u0013\t9\r\u0003\u0005\u0002X\u0002!\t\u0001WAm\u0011)\t)\u000fAI\u0001\n\u0003A\u0016q\u001d\u0005\t\u0003{\u0004A\u0011\u0001,\u0002\u0000\"Q!1\u0001\u0001\u0012\u0002\u0013\u0005a+a:\t\u000f\t\u0015\u0001\u0001\"\u0003\u0003\b!9!Q\u0003\u0001\u0005\n\t]\u0001b\u0002B\u0014\u0001\u0011%!\u0011\u0006\u0005\b\u0005\u007f\u0001A\u0011\u0002B!\u0011\u001d\u0011I\u0006\u0001C\u0005\u00057BqAa\u0019\u0001\t\u0013\u0011)\u0007C\u0004\u0003t\u0001!\tA!\u001e\t\u000f\t5\u0005\u0001\"\u0001\u0003\u0010\"9!\u0011\u0013\u0001\u0005\u0002\t=\u0005b\u0002BJ\u0001\u0011\u0005\u0011q\u0013\u0005\b\u0005/\u0003A\u0011\u0001BM\u0011\u001d\u0011\t\u000b\u0001C!\u0005GCqAa*\u0001\t\u0003\u0011I\u000bC\u0004\u0003.\u0002!\tAa,\t\u000f\t\u001d\u0007\u0001\"\u0011\u0003J\"9!Q\u001a\u0001\u0005\u0002\t=\u0007b\u0002Bj\u0001\u0011\u0005#Q\u001b\u0005\b\u0005?\u0004A\u0011\tBq\u0011\u001d\u00119\u000f\u0001C\u0001\u0005SDqAa>\u0001\t\u0003\u0012I\u0010C\u0004\u0003~\u0002!\tAa@\t\u000f\r\r\u0001\u0001\"\u0001\u0004\u0006!911\u0002\u0001\u0005B\r5\u0001b\u0002B|\u0001\u0011\u000511\u0006\u0005\t\u0007c\u0001A\u0011\t-\u00044!91\u0011\b\u0001\u0005\u0002\rm\u0002\u0002CB$\u0001\u0011\u0005\u0003l!\u0013\t\u000f\re\u0002\u0001\"\u0001\u0004V!911\f\u0001\u0005\u0002\ru\u0003bBB3\u0001\u0011\u00053q\r\u0005\b\u0007[\u0002A\u0011IB8\u0011\u001d\u00199\b\u0001C!\u0007sBqa!!\u0001\t\u0003\u001a\u0019\tC\u0004\u0004\u0016\u0002!\tea&\t\u000f\r=\u0006\u0001\"\u0011\u00042\"911\u0017\u0001\u0005B\rE\u0006bBB[\u0001\u0011\u0005#q\u0012\u0005\t\u0007o\u0003\u0001\u0015\"\u0015\u0004:\"91Q\u0019\u0001\u0005B\r\u001dwaBBk-\"\u00051q\u001b\u0004\u0007+ZC\ta!7\t\u000f\u0005U\u0003\t\"\u0001\u0004\\\"91Q\u001c!\u0005\u0002\r}\u0007bBBw\u0001\u0012\u00051q\u001e\u0005\b\t\u0013\u0001E\u0011\u0001C\u0006\u0011%!\t\u0003\u0011b\u0001\n\u0003!\u0019\u0003\u0003\u0005\u00056\u0001\u0003\u000b\u0011\u0002C\u0013\r\u0019!y\u0005\u0011\u0001\u0005R!9\u0011QK$\u0005\u0002\u0011m\u0003b\u0002C1\u000f\u0012\u0005A1M\u0004\t\tO\u0002\u0005\u0012\u0001,\u0005j\u0019AA1\u000e!\t\u0002Y#i\u0007C\u0004\u0002V-#\t\u0001b\u001c\t\u0013\u0011E4J1A\u0005\u0006\u0011M\u0004\u0002\u0003C=\u0017\u0002\u0006i\u0001\"\u001e\t\u0013\u0011m4J1A\u0005\u0006\u0011u\u0004\u0002\u0003CB\u0017\u0002\u0006i\u0001b \t\u0013\u0011\u00155J1A\u0005\u0006\u0011\u001d\u0005\u0002\u0003CG\u0017\u0002\u0006i\u0001\"#\t\u000f\u0011=5\n\"\u0001\u0005\u0012\"IAQ\u0015!\u0002\u0002\u0013%Aq\u0015\u0002\b)JLW-T1q\u0015\t9\u0006,\u0001\u0006d_:\u001cWO\u001d:f]RT!!\u0017.\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\\\u0003\u0015\u00198-\u00197b\u0007\u0001)2AX4s'\u001d\u0001q\f\u001e=~\u0003\u0013\u0001B\u0001Y2fc6\t\u0011M\u0003\u0002c1\u00069Q.\u001e;bE2,\u0017B\u00013b\u0005-\t%m\u001d;sC\u000e$X*\u00199\u0011\u0005\u0019<G\u0002\u0001\u0003\u0006Q\u0002\u0011\r!\u001b\u0002\u0002\u0017F\u0011!N\u001c\t\u0003W2l\u0011AW\u0005\u0003[j\u0013qAT8uQ&tw\r\u0005\u0002l_&\u0011\u0001O\u0017\u0002\u0004\u0003:L\bC\u00014s\t\u0015\u0019\bA1\u0001j\u0005\u00051\u0006\u0003B;wKFl\u0011AV\u0005\u0003oZ\u00131!T1q!\u0019\u0001\u00170Z9|y&\u0011!0\u0019\u0002\u0007\u001b\u0006\u0004x\n]:\u0011\u0005U\u0004\u0001\u0003B;\u0001KF\u0004rA`@fcn\f\u0019!D\u0001Y\u0013\r\t\t\u0001\u0017\u0002\u0013\u001b\u0006\u0004h)Y2u_JLH)\u001a4bk2$8\u000fE\u0002a\u0003\u000bI1!a\u0002b\u0005!IE/\u001a:bE2,\u0007\u0003BA\u0006\u0003#i!!!\u0004\u000b\u0007\u0005=\u0001,A\u0004hK:,'/[2\n\t\u0005M\u0011Q\u0002\u0002\u0014\t\u00164\u0017-\u001e7u'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0002eB\u00191.!\u0007\n\u0007\u0005m!L\u0001\u0004B]f\u0014VMZ\u0001\u0006eR,\b\u000f\u001a\t\b\u0003C\t\t\u0004`A\f\u001b\t\t\u0019C\u0003\u0003\u0002&\u0005\u001d\u0012AB1u_6L7MC\u0002X\u0003SQA!a\u000b\u0002.\u0005!Q\u000f^5m\u0015\t\ty#\u0001\u0003kCZ\f\u0017\u0002BA\u001a\u0003G\u00111$\u0011;p[&\u001c'+\u001a4fe\u0016t7-\u001a$jK2$W\u000b\u001d3bi\u0016\u0014\u0018!\u00025bg\"4\u0007#BA\u001d\u0003\u0003*WBAA\u001e\u0015\u0011\ti$a\u0010\u0002\u000f!\f7\u000f[5oO*\u0019\u00111\u0006.\n\t\u0005\r\u00131\b\u0002\b\u0011\u0006\u001c\b.\u001b8h\u0003\t)g\rE\u0003\u0002J\u0005=SMD\u0002l\u0003\u0017J1!!\u0014[\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u0015\u0002T\t)Q)];jm*\u0019\u0011Q\n.\u0002\rqJg.\u001b;?)%a\u0018\u0011LA.\u0003;\ny\u0006C\u0004\u0002\u0016\u0015\u0001\r!a\u0006\t\u000f\u0005uQ\u00011\u0001\u0002 !9\u0011QG\u0003A\u0002\u0005]\u0002bBA#\u000b\u0001\u0007\u0011qI\u0001\u000bQ\u0006\u001c\b.\u001b8h_\nT\u0017aC3rk\u0006d\u0017\u000e^=pE*\f1B]8piV\u0004H-\u0019;fe\"\u001a\u0001\"!\u001b\u0011\u0007-\fY'C\u0002\u0002ni\u0013\u0011\u0002\u001e:b]NLWM\u001c;\u0016\u0005\u0005]\u0012\u0001C3rk\u0006d\u0017\u000e^=\u0016\u0005\u0005\u001d\u0013\u0001\u0002:p_R,\"!a\u0006\u0002\u0011I|w\u000e^0%KF$B!! \u0002\u0004B\u00191.a \n\u0007\u0005\u0005%L\u0001\u0003V]&$\b\"CAC\u0019\u0005\u0005\t\u0019AA\f\u0003\rAH%M\u0001\u0006e>|G\u000f\t\u0015\u0004\u001b\u0005-\u0005cA6\u0002\u000e&\u0019\u0011q\u0012.\u0003\u0011Y|G.\u0019;jY\u0016$R\u0001`AJ\u0003+Cq!!\u000e\u000f\u0001\u0004\t9\u0004C\u0004\u0002F9\u0001\r!a\u0012\u0015\u0003q\f!\"\\1q\r\u0006\u001cGo\u001c:z+\t\ti\n\u0005\u0003\u007f\u0003?[\u0018bAAQ1\nQQ*\u00199GC\u000e$xN]=\u0002\u0017]\u0014\u0018\u000e^3PE*,7\r\u001e\u000b\u0005\u0003{\n9\u000bC\u0004\u0002*F\u0001\r!a+\u0002\u0007=,H\u000f\u0005\u0003\u0002.\u0006MVBAAX\u0015\u0011\t\t,!\f\u0002\u0005%|\u0017\u0002BA[\u0003_\u0013!c\u00142kK\u000e$x*\u001e;qkR\u001cFO]3b[\u0006Q!/Z1e\u001f\nTWm\u0019;\u0015\t\u0005u\u00141\u0018\u0005\b\u0003{\u0013\u0002\u0019AA`\u0003\tIg\u000e\u0005\u0003\u0002.\u0006\u0005\u0017\u0002BAb\u0003_\u0013\u0011c\u00142kK\u000e$\u0018J\u001c9viN#(/Z1n\u0003!\u0019\u0015iU0S\u001f>#FCBAe\u0003\u001f\f\u0019\u000eE\u0002l\u0003\u0017L1!!4[\u0005\u001d\u0011un\u001c7fC:Dq!!5\u0014\u0001\u0004\t9\"\u0001\u0002pm\"9\u0011Q[\nA\u0002\u0005]\u0011A\u00018w\u0003!\u0011X-\u00193S_>$H\u0003BAn\u0003C\u0004R!^AoKFL1!a8W\u0005\u0015Iej\u001c3f\u0011%\t\u0019\u000f\u0006I\u0001\u0002\u0004\tI-A\u0003bE>\u0014H/\u0001\nsK\u0006$'k\\8uI\u0011,g-Y;mi\u0012\nTCAAuU\u0011\tI-a;,\u0005\u00055\b\u0003BAx\u0003sl!!!=\u000b\t\u0005M\u0018Q_\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a>[\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003w\f\tPA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fqB\u0015#D'N{&+R!E?J{u\n\u0016\u000b\u0005\u00037\u0014\t\u0001C\u0005\u0002dZ\u0001\n\u00111\u0001\u0002J\u0006I\"\u000bR\"T'~\u0013V)\u0011#`%>{E\u000b\n3fM\u0006,H\u000e\u001e\u00132\u00039\u0011FiQ*T?\u000e{W\u000e\u001d7fi\u0016$B!a7\u0003\n!9\u00111\u001d\rA\u0002\u0005%\u0007f\u0001\r\u0003\u000eA!!q\u0002B\t\u001b\t\t)0\u0003\u0003\u0003\u0014\u0005U(a\u0002;bS2\u0014XmY\u0001\u000b%\u0012\u001b5kU0S\u001f>#F\u0003CAe\u00053\u0011YB!\n\t\u000f\u0005E\u0017\u00041\u0001\u0002\\\"9!QD\rA\u0002\t}\u0011\u0001D3ya\u0016\u001cG/\u001a3nC&t\u0007#B;\u0003\"\u0015\f\u0018b\u0001B\u0012-\nAQ*Y5o\u001d>$W\rC\u0004\u0002Vf\u0001\r!a7\u0002\u0011%t7/\u001a:uQ\u000e$\u0002\"! \u0003,\t=\"\u0011\b\u0005\u0007\u0005[Q\u0002\u0019A3\u0002\u0003-DqA!\r\u001b\u0001\u0004\u0011\u0019$\u0001\u0002iGB\u00191N!\u000e\n\u0007\t]\"LA\u0002J]RDaAa\u000f\u001b\u0001\u0004\t\u0018!\u0001<)\u0007i\u0011i!\u0001\u0006j]N,'\u000f^5gQ\u000e$BBa\u0011\u0003J\t-#Q\nB(\u0005'\u0002Ba\u001bB#c&\u0019!q\t.\u0003\r=\u0003H/[8o\u0011\u0019\u0011ic\u0007a\u0001K\"9!\u0011G\u000eA\u0002\tM\u0002B\u0002B\u001e7\u0001\u0007\u0011\u000fC\u0004\u0003Rm\u0001\r!a\u0006\u0002\t\r|g\u000e\u001a\u0005\b\u0005+Z\u0002\u0019AAe\u0003)1W\u000f\u001c7FcV\fGn\u001d\u0015\u00047\t5\u0011\u0001\u00037p_.,\b\u000f[2\u0015\r\u0005]!Q\fB0\u0011\u0019\u0011i\u0003\ba\u0001K\"9!\u0011\u0007\u000fA\u0002\tM\u0002f\u0001\u000f\u0003\u000e\u0005A!/Z7pm\u0016D7\r\u0006\u0006\u0003D\t\u001d$\u0011\u000eB6\u0005_BaA!\f\u001e\u0001\u0004)\u0007B\u0002B\u001e;\u0001\u0007\u0011\u000fC\u0004\u0003nu\u0001\rAa\r\u0002\u001bI,Wn\u001c<bYB{G.[2z\u0011\u001d\u0011\t$\ba\u0001\u0005gA3!\bB\u0007\u0003\u0019\u0019HO]5oOV\u0011!q\u000f\t\u0005\u0005s\u00129I\u0004\u0003\u0003|\t\r\u0005c\u0001B?56\u0011!q\u0010\u0006\u0004\u0005\u0003c\u0016A\u0002\u001fs_>$h(C\u0002\u0003\u0006j\u000ba\u0001\u0015:fI\u00164\u0017\u0002\u0002BE\u0005\u0017\u0013aa\u0015;sS:<'b\u0001BC5\u0006Q\u0011n\u001d*fC\u0012|e\u000e\\=\u0016\u0005\u0005%\u0017a\u00038p]J+\u0017\rZ(oYf\f\u0001b\u001d8baNDw\u000e\u001e\u0015\u0004C\t5\u0011\u0001\u0005:fC\u0012|e\u000e\\=T]\u0006\u00048\u000f[8u)\t\u0011Y\nE\u0003\u007f\u0005;+\u0017/\u0003\u0002x1\"\u001a!E!\u0004\u0002\u000b\rdW-\u0019:\u0015\u0005\u0005u\u0004fA\u0012\u0003\u000e\u0005Y1m\\7qkR,\u0007*Y:i)\u0011\u0011\u0019Da+\t\r\t5B\u00051\u0001f\u0003\u0019awn\\6vaR\u0019\u0011O!-\t\r\t5R\u00051\u0001fQ-)#Q\u0017B^\u0005{\u0013\tMa1\u0011\u0007-\u00149,C\u0002\u0003:j\u0013!\u0002Z3qe\u0016\u001c\u0017\r^3e\u0003\u001diWm]:bO\u0016\f#Aa0\u0002?U\u001bX\rI4fi>\u0013X\t\\:fQ-d\u0003E\\;mY&\u0002\u0013N\\:uK\u0006$g&A\u0003tS:\u001cW-\t\u0002\u0003F\u00061!GL\u00194]A\nQ!\u00199qYf$2!\u001dBf\u0011\u0019\u0011iC\na\u0001K\u0006\u0019q-\u001a;\u0015\t\t\r#\u0011\u001b\u0005\u0007\u0005[9\u0003\u0019A3\u0002\u0007A,H\u000f\u0006\u0004\u0003D\t]'1\u001c\u0005\u0007\u00053D\u0003\u0019A3\u0002\u0007-,\u0017\u0010\u0003\u0004\u0003^\"\u0002\r!]\u0001\u0006m\u0006dW/Z\u0001\u0007kB$\u0017\r^3\u0015\r\u0005u$1\u001dBs\u0011\u0019\u0011i#\u000ba\u0001K\"1!1H\u0015A\u0002E\fa!\u00193e\u001f:,G\u0003\u0002Bv\u0005[l\u0011\u0001\u0001\u0005\b\u0005_T\u0003\u0019\u0001By\u0003\tYg\u000fE\u0003l\u0005g,\u0017/C\u0002\u0003vj\u0013a\u0001V;qY\u0016\u0014\u0014A\u0002:f[>4X\r\u0006\u0003\u0003D\tm\bB\u0002B\u0017W\u0001\u0007Q-A\u0006tk\n$(/Y2u\u001f:,G\u0003\u0002Bv\u0007\u0003AaA!\f-\u0001\u0004)\u0017a\u00039vi&3\u0017IY:f]R$bAa\u0011\u0004\b\r%\u0001B\u0002B\u0017[\u0001\u0007Q\r\u0003\u0004\u0003<5\u0002\r!]\u0001\u0010O\u0016$xJ]#mg\u0016,\u0006\u000fZ1uKR)\u0011oa\u0004\u0004\u0012!1!Q\u0006\u0018A\u0002\u0015D\u0001ba\u0005/\t\u0003\u00071QC\u0001\rI\u00164\u0017-\u001e7u-\u0006dW/\u001a\t\u0005W\u000e]\u0011/C\u0002\u0004\u001ai\u0013\u0001\u0002\u00102z]\u0006lWM\u0010\u0015\t\u0007#\u0019iba\t\u0004(A\u00191na\b\n\u0007\r\u0005\"L\u0001\beKB\u0014XmY1uK\u0012t\u0015-\\3\"\u0005\r\u0015\u0012AA8qC\t\u0019I#A\u00043]E\u001ad&M\u001a\u0015\r\u0005%7QFB\u0018\u0011\u0019\u0011ic\fa\u0001K\"1!1H\u0018A\u0002E\f1B]3n_Z,'+\u001a4FcR1\u0011\u0011ZB\u001b\u0007oAaA!\f1\u0001\u0004)\u0007B\u0002B\u001ea\u0001\u0007\u0011/A\u0004sKBd\u0017mY3\u0015\u0011\u0005%7QHB \u0007\u0007BaA!\f2\u0001\u0004)\u0007BBB!c\u0001\u0007\u0011/\u0001\u0005pY\u00124\u0018\r\\;f\u0011\u0019\u0019)%\ra\u0001c\u0006Aa.Z<wC2,X-\u0001\u0007sKBd\u0017mY3SK\u001a,\u0015\u000f\u0006\u0005\u0002J\u000e-3QJB)\u0011\u0019\u0011iC\ra\u0001K\"11q\n\u001aA\u0002E\f\u0001b\u001c7e-\u0006dW/\u001a\u0005\u0007\u0007'\u0012\u0004\u0019A9\u0002\u00119,wOV1mk\u0016$bAa\u0011\u0004X\re\u0003B\u0002B\u0017g\u0001\u0007Q\r\u0003\u0004\u0003<M\u0002\r!]\u0001\tSR,'/\u0019;peV\u00111q\f\t\u0006}\u000e\u0005$\u0011_\u0005\u0004\u0007GB&\u0001C%uKJ\fGo\u001c:\u0002\rY\fG.^3t+\t\u0019I\u0007\u0005\u0003\u007f\u0007W\n\u0018bAA\u00041\u000611.Z=TKR,\"a!\u001d\u0011\ty\u001c\u0019(Z\u0005\u0004\u0007kB&aA*fi\u0006!a/[3x+\t\u0019Y\bE\u0003\u007f\u0007{*\u0017/C\u0002\u0004\u0000a\u0013q!T1q-&,w/\u0001\u0006gS2$XM]&fsN$Baa\u001f\u0004\u0006\"91q\u0011\u001dA\u0002\r%\u0015!\u00019\u0011\r-\u001cY)ZAe\u0013\r\u0019iI\u0017\u0002\n\rVt7\r^5p]FB3\u0002\u000fB[\u0005w\u001b\tJ!1\u0003D\u0006\u001211S\u0001}+N,\u0007E\f<jK^tc-\u001b7uKJ\\U-_:)M&r\u0003%\u0011\u0011gkR,(/\u001a\u0011wKJ\u001c\u0018n\u001c8!o&dG\u000eI5oG2,H-\u001a\u0011bAM$(/[2uAY,'o]5p]\u0002zg\r\t;iSN\u0004S.\u001a;i_\u0012\u0004\u0003FZ8sA9|w\u000f\f\u0011/m&,wO\f4jYR,'oS3zg\"\u0002\u0018F\f;p\u001b\u0006\u0004\u0018FL\u0001\n[\u0006\u0004h+\u00197vKN,Ba!'\u0004 R!11TBR!\u0019q8QP3\u0004\u001eB\u0019ama(\u0005\r\r\u0005\u0016H1\u0001j\u0005\u00059\u0006bBBSs\u0001\u00071qU\u0001\u0002MB11na#r\u0007;C3\"\u000fB[\u0005w\u001bYK!1\u0003D\u0006\u00121QV\u0001{+N,\u0007E\f<jK^tS.\u00199WC2,Xm\u001d\u0015gS9\u0002\u0013\t\t4viV\u0014X\r\t<feNLwN\u001c\u0011xS2d\u0007%\u001b8dYV$W\rI1!gR\u0014\u0018n\u0019;!m\u0016\u00148/[8oA=4\u0007\u0005\u001e5jg\u0002jW\r\u001e5pI\u0002Bcm\u001c:!]><H\u0006\t\u0018wS\u0016<h&\\1q-\u0006dW/Z:)M&rCo\\'ba&r\u0013\u0001B:ju\u0016,\"Aa\r\u0002\u0013-twn\u001e8TSj,\u0017aB5t\u000b6\u0004H/_\u0001\nG2\f7o\u001d(b[\u0016,\"aa/\u0011\t\ru61Y\u0007\u0003\u0007\u007fSAa!1\u0002.\u0005!A.\u00198h\u0013\u0011\u0011Iia0\u0002\u00151\f7\u000f^(qi&|g.\u0006\u0002\u0004JB)1N!\u0012\u0003r\":\u0001a!4\u0003^\u000eM\u0007cA6\u0004P&\u00191\u0011\u001b.\u0003!M+'/[1m-\u0016\u00148/[8o+&#e\u0004C\\*H\u0003D\u0019/\u0016K\u0002\u000fQ\u0013\u0018.Z'baB\u0011Q\u000fQ\n\u0006\u0001\u0006]\u0011Q\u0014\u000b\u0003\u0007/\fQ!Z7qif,ba!9\u0004h\u000e-XCABr!\u0019)\ba!:\u0004jB\u0019ama:\u0005\u000b!\u0014%\u0019A5\u0011\u0007\u0019\u001cY\u000fB\u0003t\u0005\n\u0007\u0011.\u0001\u0003ge>lWCBBy\u0007o\u001cY\u0010\u0006\u0003\u0004t\u000eu\bCB;\u0001\u0007k\u001cI\u0010E\u0002g\u0007o$Q\u0001[\"C\u0002%\u00042AZB~\t\u0015\u00198I1\u0001j\u0011\u001d\u0019yp\u0011a\u0001\t\u0003\t!!\u001b;\u0011\u000by$\u0019\u0001b\u0002\n\u0007\u0011\u0015\u0001L\u0001\u0007Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cW\rE\u0004l\u0005g\u001c)p!?\u0002\u00159,wOQ;jY\u0012,'/\u0006\u0004\u0005\u000e\u0011eAQD\u000b\u0003\t\u001f\u0001r\u0001\u0019C\t\t+!y\"C\u0002\u0005\u0014\u0005\u0014qb\u0012:po\u0006\u0014G.\u001a\"vS2$WM\u001d\t\bW\nMHq\u0003C\u000e!\r1G\u0011\u0004\u0003\u0006Q\u0012\u0013\r!\u001b\t\u0004M\u0012uA!B:E\u0005\u0004I\u0007CB;\u0001\t/!Y\"\u0001\u0007j]>$W-\u001e9eCR,'/\u0006\u0002\u0005&AA\u0011\u0011EA\u0019\tO!y\u0004\r\u0004\u0005*\u0011EB1\b\t\bk\u0012-Bq\u0006C\u001d\u0013\r!iC\u0016\u0002\n\u0013:{G-\u001a\"bg\u0016\u00042A\u001aC\u0019\t)!\u0019DRA\u0001\u0002\u0003\u0015\t!\u001b\u0002\u0004?\u0012\"\u0014!D5o_\u0012,W\u000f\u001d3bi\u0016\u0014\b\u0005K\u0002G\u0003S\u00022A\u001aC\u001e\t)!iDRA\u0001\u0002\u0003\u0015\t!\u001b\u0002\u0004?\u0012*\u0004G\u0002C!\t\u000b\"Y\u0005E\u0004v\u0005C!\u0019\u0005\"\u0013\u0011\u0007\u0019$)\u0005\u0002\u0006\u0005H\u0019\u000b\t\u0011!A\u0003\u0002%\u00141a\u0018\u00137!\r1G1\n\u0003\u000b\t\u001b2\u0015\u0011!A\u0001\u0006\u0003I'aA0%o\tqQ*\u00198hY\u0016$\u0007*Y:iS:<W\u0003\u0002C*\t3\u001aRaRA\f\t+\u0002b!!\u000f\u0002B\u0011]\u0003c\u00014\u0005Z\u0011)\u0001n\u0012b\u0001SR\u0011AQ\f\t\u0006\t?:EqK\u0007\u0002\u0001\u0006!\u0001.Y:i)\u0011\u0011\u0019\u0004\"\u001a\t\u000f\t5\u0012\n1\u0001\u0005X\u0005i!+Z7pm\u0006d\u0007k\u001c7jGf\u00042\u0001b\u0018L\u00055\u0011V-\\8wC2\u0004v\u000e\\5dsN\u00191*a\u0006\u0015\u0005\u0011%\u0014AB!mo\u0006L8/\u0006\u0002\u0005v=\u0011AqO\u000f\u0002\u0001\u00059\u0011\t\\<bsN\u0004\u0013A\u0003$vY2,\u0015/^1mgV\u0011AqP\b\u0003\t\u0003k\u0012!A\u0001\f\rVdG.R9vC2\u001c\b%A\u0006SK\u001a,'/\u001a8dK\u0016\u000bXC\u0001CE\u001f\t!Y)H\u0001\u0003\u00031\u0011VMZ3sK:\u001cW-R9!\u00031\u0019\bn\\;mIJ+Wn\u001c<f+\u0011!\u0019\n\"(\u0015\t\u0011UE1\u0015\u000b\u0007\u0003\u0013$9\nb(\t\u000f\u0011e5\u000b1\u0001\u0005\u001c\u0006\t\u0011\rE\u0002g\t;#Qa]*C\u0002%Dq\u0001\")T\u0001\u0004!Y*A\u0001c\u0011\u001d\u0011ig\u0015a\u0001\u0005g\tAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"\u0001\"+\u0011\t\ruF1V\u0005\u0005\t[\u001byL\u0001\u0004PE*,7\r\u001e\u0015\b\u0001\u000e5'Q\u001cCY=\u0005\u0019\u0001fB \u0004N\nuG\u0011\u0017"
)
public final class TrieMap extends AbstractMap implements Map, DefaultSerializable {
   private static final long serialVersionUID = -5212455458703321708L;
   private Hashing hashingobj;
   private Equiv equalityobj;
   private transient AtomicReferenceFieldUpdater rootupdater;
   private volatile Object root;

   public static AtomicReferenceFieldUpdater inodeupdater() {
      return TrieMap$.MODULE$.inodeupdater();
   }

   public static GrowableBuilder newBuilder() {
      return TrieMap$.MODULE$.newBuilder();
   }

   public static TrieMap from(final IterableOnce it) {
      return TrieMap$.MODULE$.from(it);
   }

   public Object writeReplace() {
      return DefaultSerializable.writeReplace$(this);
   }

   public Option updateWith(final Object key, final Function1 remappingFunction) {
      return Map.updateWith$(this, key, remappingFunction);
   }

   public Map filterInPlaceImpl(final Function2 p) {
      return Map.filterInPlaceImpl$(this, p);
   }

   public Map mapValuesInPlaceImpl(final Function2 f) {
      return Map.mapValuesInPlaceImpl$(this, f);
   }

   public Hashing hashing() {
      return this.hashingobj;
   }

   public Equiv equality() {
      return this.equalityobj;
   }

   private Object root() {
      return this.root;
   }

   private void root_$eq(final Object x$1) {
      this.root = x$1;
   }

   public MapFactory mapFactory() {
      return TrieMap$.MODULE$;
   }

   private void writeObject(final ObjectOutputStream out) {
      out.writeObject(this.hashingobj);
      out.writeObject(this.equalityobj);
      Iterator it = this.iterator();

      while(it.hasNext()) {
         Tuple2 var3 = (Tuple2)it.next();
         if (var3 == null) {
            throw new MatchError((Object)null);
         }

         Object k = var3._1();
         Object v = var3._2();
         out.writeObject(k);
         out.writeObject(v);
      }

      out.writeObject(TrieMapSerializationEnd$.MODULE$);
   }

   private void readObject(final ObjectInputStream in) {
      this.root_$eq(INode$.MODULE$.newRootNode(this.equality()));
      this.rootupdater = AtomicReferenceFieldUpdater.newUpdater(TrieMap.class, Object.class, "root");
      this.hashingobj = (Hashing)in.readObject();
      this.equalityobj = (Equiv)in.readObject();
      Object obj = in.readObject();

      while(true) {
         TrieMapSerializationEnd$ var3 = TrieMapSerializationEnd$.MODULE$;
         if (obj != null) {
            if (obj.equals(var3)) {
               return;
            }
         }

         obj = in.readObject();
         TrieMapSerializationEnd$ var4 = TrieMapSerializationEnd$.MODULE$;
         if (obj != null) {
            if (obj.equals(var4)) {
               continue;
            }
         }

         Object v = in.readObject();
         this.update(obj, v);
      }
   }

   private boolean CAS_ROOT(final Object ov, final Object nv) {
      return this.rootupdater.compareAndSet(this, ov, nv);
   }

   public INode readRoot(final boolean abort) {
      return this.RDCSS_READ_ROOT(abort);
   }

   public boolean readRoot$default$1() {
      return false;
   }

   public INode RDCSS_READ_ROOT(final boolean abort) {
      Object r = this.root();
      if (r instanceof INode) {
         return (INode)r;
      } else if (r instanceof RDCSS_Descriptor) {
         return this.RDCSS_Complete(abort);
      } else {
         throw new MatchError(r);
      }
   }

   public boolean RDCSS_READ_ROOT$default$1() {
      return false;
   }

   private INode RDCSS_Complete(final boolean abort) {
      while(true) {
         Object v = this.root();
         if (v instanceof INode) {
            return (INode)v;
         }

         if (v instanceof RDCSS_Descriptor) {
            RDCSS_Descriptor var3 = (RDCSS_Descriptor)v;
            if (var3 != null) {
               INode ov = var3.old();
               MainNode exp = var3.expectedmain();
               INode nv = var3.nv();
               if (abort) {
                  if (this.CAS_ROOT(var3, ov)) {
                     return ov;
                  }

                  abort = abort;
                  continue;
               }

               if (ov == null) {
                  throw null;
               }

               if (ov.GCAS_READ(this) == exp) {
                  if (this.CAS_ROOT(var3, nv)) {
                     var3.committed_$eq(true);
                     return nv;
                  }

                  abort = abort;
                  continue;
               }

               if (this.CAS_ROOT(var3, ov)) {
                  return ov;
               }

               abort = abort;
               continue;
            }

            throw new MatchError((Object)null);
         }

         throw new MatchError(v);
      }
   }

   private boolean RDCSS_ROOT(final INode ov, final MainNode expectedmain, final INode nv) {
      RDCSS_Descriptor desc = new RDCSS_Descriptor(ov, expectedmain, nv);
      if (this.CAS_ROOT(ov, desc)) {
         this.RDCSS_Complete(false);
         return desc.committed();
      } else {
         return false;
      }
   }

   private void inserthc(final Object k, final int hc, final Object v) {
      while(true) {
         INode r = this.RDCSS_READ_ROOT(false);
         if (r.rec_insert(k, v, hc, 0, (INode)null, r.gen, this)) {
            return;
         }

         v = v;
         hc = hc;
         k = k;
      }
   }

   private Option insertifhc(final Object k, final int hc, final Object v, final Object cond, final boolean fullEquals) {
      while(true) {
         INode r = this.RDCSS_READ_ROOT(false);
         Option ret = r.rec_insertif(k, v, hc, cond, fullEquals, 0, (INode)null, r.gen, this);
         if (ret != null) {
            return ret;
         }

         fullEquals = fullEquals;
         cond = cond;
         v = v;
         hc = hc;
         k = k;
      }
   }

   private Object lookuphc(final Object k, final int hc) {
      while(true) {
         INode r = this.RDCSS_READ_ROOT(false);
         Object res = r.rec_lookup(k, hc, 0, (INode)null, r.gen, this);
         if (res != INodeBase.RESTART) {
            return res;
         }

         hc = hc;
         k = k;
      }
   }

   private Option removehc(final Object k, final Object v, final int removalPolicy, final int hc) {
      while(true) {
         INode r = this.RDCSS_READ_ROOT(false);
         Option res = r.rec_remove(k, v, removalPolicy, hc, 0, (INode)null, r.gen, this);
         if (res != null) {
            return res;
         }

         hc = hc;
         removalPolicy = removalPolicy;
         v = v;
         k = k;
      }
   }

   public String string() {
      return this.RDCSS_READ_ROOT(false).string(0);
   }

   public boolean isReadOnly() {
      return this.rootupdater == null;
   }

   public boolean nonReadOnly() {
      return this.rootupdater != null;
   }

   public TrieMap snapshot() {
      INode r;
      MainNode expmain;
      do {
         r = this.RDCSS_READ_ROOT(false);
         if (r == null) {
            throw null;
         }

         expmain = r.GCAS_READ(this);
      } while(!this.RDCSS_ROOT(r, expmain, r.copyToGen(new Gen(), this)));

      return new TrieMap(r.copyToGen(new Gen(), this), this.rootupdater, this.hashing(), this.equality());
   }

   public scala.collection.Map readOnlySnapshot() {
      INode r;
      MainNode expmain;
      do {
         r = this.RDCSS_READ_ROOT(false);
         if (r == null) {
            throw null;
         }

         expmain = r.GCAS_READ(this);
      } while(!this.RDCSS_ROOT(r, expmain, r.copyToGen(new Gen(), this)));

      return new TrieMap(r, (AtomicReferenceFieldUpdater)null, this.hashing(), this.equality());
   }

   public void clear() {
      INode r;
      do {
         r = this.RDCSS_READ_ROOT(false);
         if (r == null) {
            throw null;
         }
      } while(!this.RDCSS_ROOT(r, r.GCAS_READ(this), INode$.MODULE$.newRootNode(this.equality())));

   }

   public int computeHash(final Object k) {
      return this.hashingobj.hash(k);
   }

   /** @deprecated */
   public Object lookup(final Object k) {
      int hc = this.computeHash(k);
      Object lookupRes = this.lookuphc(k, hc);
      return BoxesRunTime.equals(lookupRes, INodeBase.NO_SUCH_ELEMENT_SENTINEL) ? null : lookupRes;
   }

   public Object apply(final Object k) {
      int hc = this.computeHash(k);
      Object res = this.lookuphc(k, hc);
      if (res == INodeBase.NO_SUCH_ELEMENT_SENTINEL) {
         throw new NoSuchElementException();
      } else {
         return res;
      }
   }

   public Option get(final Object k) {
      int hc = this.computeHash(k);
      Object res = this.lookuphc(k, hc);
      return (Option)(res == INodeBase.NO_SUCH_ELEMENT_SENTINEL ? None$.MODULE$ : new Some(res));
   }

   public Option put(final Object key, final Object value) {
      int hc = this.computeHash(key);
      return this.insertifhc(key, hc, value, INode$.MODULE$.KEY_PRESENT_OR_ABSENT(), false);
   }

   public void update(final Object k, final Object v) {
      int hc = this.computeHash(k);
      this.inserthc(k, hc, v);
   }

   public TrieMap addOne(final Tuple2 kv) {
      this.update(kv._1(), kv._2());
      return this;
   }

   public Option remove(final Object k) {
      int hc = this.computeHash(k);
      return this.removehc(k, (Object)null, 0, hc);
   }

   public TrieMap subtractOne(final Object k) {
      this.remove(k);
      return this;
   }

   public Option putIfAbsent(final Object k, final Object v) {
      int hc = this.computeHash(k);
      return this.insertifhc(k, hc, v, INode$.MODULE$.KEY_ABSENT(), false);
   }

   public Object getOrElseUpdate(final Object k, final Function0 defaultValue) {
      int hc = this.computeHash(k);
      Object var4 = this.lookuphc(k, hc);
      if (BoxesRunTime.equals(INodeBase.NO_SUCH_ELEMENT_SENTINEL, var4)) {
         Object v = defaultValue.apply();
         Option var6 = this.insertifhc(k, hc, v, INode$.MODULE$.KEY_ABSENT(), false);
         if (var6 instanceof Some) {
            return ((Some)var6).value();
         } else if (None$.MODULE$.equals(var6)) {
            return v;
         } else {
            throw new MatchError(var6);
         }
      } else {
         return var4;
      }
   }

   public boolean remove(final Object k, final Object v) {
      int hc = this.computeHash(k);
      Option var10000 = this.removehc(k, v, 1, hc);
      if (var10000 == null) {
         throw null;
      } else {
         return var10000.isDefined();
      }
   }

   public boolean removeRefEq(final Object k, final Object v) {
      int hc = this.computeHash(k);
      Option var10000 = this.removehc(k, v, 2, hc);
      if (var10000 == null) {
         throw null;
      } else {
         return var10000.isDefined();
      }
   }

   public boolean replace(final Object k, final Object oldvalue, final Object newvalue) {
      int hc = this.computeHash(k);
      Option var10000 = this.insertifhc(k, hc, newvalue, oldvalue, true);
      if (var10000 == null) {
         throw null;
      } else {
         return var10000.isDefined();
      }
   }

   public boolean replaceRefEq(final Object k, final Object oldValue, final Object newValue) {
      int hc = this.computeHash(k);
      Option var10000 = this.insertifhc(k, hc, newValue, oldValue, false);
      if (var10000 == null) {
         throw null;
      } else {
         return var10000.isDefined();
      }
   }

   public Option replace(final Object k, final Object v) {
      int hc = this.computeHash(k);
      return this.insertifhc(k, hc, v, INode$.MODULE$.KEY_PRESENT(), false);
   }

   public Iterator iterator() {
      if (this.nonReadOnly()) {
         return this.readOnlySnapshot().iterator();
      } else {
         TrieMapIterator$ var10004 = TrieMapIterator$.MODULE$;
         return new TrieMapIterator(0, this, true);
      }
   }

   public Iterable values() {
      return (Iterable)(this.nonReadOnly() ? this.readOnlySnapshot().values() : new DefaultSerializable() {
         // $FF: synthetic field
         private final MapOps $outer;

         public Object writeReplace() {
            return DefaultSerializable.writeReplace$(this);
         }

         public int knownSize() {
            return this.$outer.knownSize();
         }

         public Iterator iterator() {
            return this.$outer.valuesIterator();
         }

         public {
            if (TrieMap.this == null) {
               throw null;
            } else {
               this.$outer = TrieMap.this;
            }
         }
      });
   }

   public Set keySet() {
      return (Set)(this.nonReadOnly() ? this.readOnlySnapshot().keySet() : new MapOps.KeySet());
   }

   public MapView view() {
      return (MapView)(this.nonReadOnly() ? this.readOnlySnapshot().view() : new MapView.Id(this));
   }

   /** @deprecated */
   public MapView filterKeys(final Function1 p) {
      return this.view().filterKeys(p);
   }

   /** @deprecated */
   public MapView mapValues(final Function1 f) {
      return this.view().mapValues(f);
   }

   public int size() {
      return this.nonReadOnly() ? this.readOnlySnapshot().size() : this.RDCSS_READ_ROOT(false).cachedSize(this);
   }

   public int knownSize() {
      return this.nonReadOnly() ? -1 : this.RDCSS_READ_ROOT(false).knownSize(this);
   }

   public boolean isEmpty() {
      IterableOps.SizeCompareOps$ var10000 = IterableOps.SizeCompareOps$.MODULE$;
      return ((scala.collection.Map)(this.nonReadOnly() ? this.readOnlySnapshot() : this)).sizeIs().sizeCompare(0) == 0;
   }

   public String className() {
      return "TrieMap";
   }

   public Option lastOption() {
      if (this.isEmpty()) {
         return None$.MODULE$;
      } else {
         Try$ var10000 = Try$.MODULE$;

         try {
            Object apply_r1 = (Tuple2)IterableOps.last$(this);
            var10000 = new Success(apply_r1);
         } catch (Throwable var3) {
            if (var3 == null || !NonFatal$.MODULE$.apply(var3)) {
               throw var3;
            }

            var10000 = new Failure(var3);
         }

         Object var4 = null;
         Object var2 = null;
         return ((Try)var10000).toOption();
      }
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$lastOption$1(final TrieMap $this) {
      return (Tuple2)$this.last();
   }

   private TrieMap(final Object r, final AtomicReferenceFieldUpdater rtupd, final Hashing hashf, final Equiv ef) {
      this.hashingobj = (Hashing)(hashf instanceof Hashing.Default ? new MangledHashing() : hashf);
      this.equalityobj = ef;
      this.rootupdater = rtupd;
      this.root = r;
   }

   public TrieMap(final Hashing hashf, final Equiv ef) {
      this(INode$.MODULE$.newRootNode(ef), AtomicReferenceFieldUpdater.newUpdater(TrieMap.class, Object.class, "root"), hashf, ef);
   }

   public TrieMap() {
      Hashing$ var10001 = Hashing$.MODULE$;
      this(new Hashing.Default(), package$.MODULE$.Equiv().universal());
   }

   public static class MangledHashing implements Hashing {
      public int hash(final Object k) {
         scala.util.hashing.package$ var10000 = scala.util.hashing.package$.MODULE$;
         return Integer.reverseBytes(Statics.anyHash(k) * -1640532531) * -1640532531;
      }
   }

   public static class RemovalPolicy$ {
      public static final RemovalPolicy$ MODULE$ = new RemovalPolicy$();

      public final int Always() {
         return 0;
      }

      public final int FullEquals() {
         return 1;
      }

      public final int ReferenceEq() {
         return 2;
      }

      public boolean shouldRemove(final int removalPolicy, final Object a, final Object b) {
         switch (removalPolicy) {
            case 0:
               return true;
            case 1:
               if (BoxesRunTime.equals(a, b)) {
                  return true;
               }

               return false;
            case 2:
               if (a == b) {
                  return true;
               }

               return false;
            default:
               throw new MatchError(removalPolicy);
         }
      }
   }
}
