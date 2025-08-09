package breeze.collection.immutable;

import breeze.util.Iterators$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BuildFrom;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableFactory;
import scala.collection.IterableFactoryDefaults;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.LazyZip2;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.View;
import scala.collection.WithFilter;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\r=d\u0001B A\u0001\u001dC\u0001\u0002\u001e\u0001\u0003\u0002\u0003\u0006Y!\u001e\u0005\u0006q\u0002!\t!\u001f\u0005\by\u0002\u0011\r\u0011\"\u0005~\u0011\u001d\u00119\u000b\u0001Q\u0001\nyD\u0011B!+\u0001\u0005\u0004%\t%a\u000b\t\u0011\t-\u0006\u0001)A\u0005\u0003[AqA!,\u0001\t\u0003\u0011y\u000bC\u0004\u00034\u0002!IA!.\t\u000f\tm\u0006\u0001\"\u0001\u0003>\"9!1\u0019\u0001\u0005\n\t\u0015\u0007b\u0002Bj\u0001\u0011\u0005!Q\u001b\u0005\u000b\u0005/\u0004\u0001R1A\u0005\u0002\te\u0007b\u0002Bo\u0001\u0011%!q\u001c\u0005\b\u0005G\u0004A\u0011\u0001Bs\u0011\u001d\u00119\u000f\u0001C\u0001\u0005SDqA!<\u0001\t\u0013\u0011y\u000fC\u0004\u0003t\u0002!\tA!>\t\u000f\rE\u0001\u0001\"\u0001\u0004\u0014!91Q\u0006\u0001\u0005\u0002\r=\u0002bBB%\u0001\u0011\u000511\n\u0005\b\u0005w\u0003A\u0011AB*\u0011\u001d\u00199\u0006\u0001C!\u00073Bqaa\u0018\u0001\t\u0003\u001a\t\u0007C\u0004\u0003:\u0001!\tE!:\b\u000f\u0005\u001d\u0001\t#\u0001\u0002\n\u00191q\b\u0011E\u0001\u0003\u0017Aa\u0001\u001f\u000e\u0005\u0002\u0005maABA\u000f5!\u000by\u0002\u0003\u0006\u0002*q\u0011)\u001a!C\u0001\u0003WA!\"a\r\u001d\u0005#\u0005\u000b\u0011BA\u0017\u0011)\t)\u0004\bBK\u0002\u0013\u0005\u0011q\u0007\u0005\u000b\u0003{a\"\u0011#Q\u0001\n\u0005e\u0002BCA 9\tU\r\u0011\"\u0001\u0002B!Q\u0011\u0011\n\u000f\u0003\u0012\u0003\u0006I!a\u0011\t\u0013Qd\"\u0011!Q\u0001\f\u0005-\u0003B\u0002=\u001d\t\u0003\ti\u0005C\u0004\u0002Zq!\t!a\u0017\t\u0013\u0005\u0005D$!A\u0005\u0002\u0005\r\u0004\"CA>9E\u0005I\u0011AA?\u0011%\t9\nHI\u0001\n\u0003\tI\nC\u0005\u0002\"r\t\n\u0011\"\u0001\u0002$\"I\u00111\u0016\u000f\u0002\u0002\u0013\u0005\u0013Q\u0016\u0005\n\u0003wc\u0012\u0011!C\u0001\u0003WA\u0011\"!0\u001d\u0003\u0003%\t!a0\t\u0013\u0005\u0015G$!A\u0005B\u0005\u001d\u0007\"CAh9\u0005\u0005I\u0011AAi\u0011%\tY\u000eHA\u0001\n\u0003\ni\u000eC\u0005\u0002br\t\t\u0011\"\u0011\u0002d\"I\u0011Q\u001d\u000f\u0002\u0002\u0013\u0005\u0013q\u001d\u0005\n\u0003Sd\u0012\u0011!C!\u0003W<\u0011\"a<\u001b\u0003\u0003E\t\"!=\u0007\u0013\u0005u!$!A\t\u0012\u0005M\bB\u0002=5\t\u0003\t)\u0010C\u0005\u0002fR\n\t\u0011\"\u0012\u0002h\"I\u0011q\u001f\u001b\u0002\u0002\u0013\u0005\u0015\u0011 \u0005\n\u0005#!\u0014\u0011!CA\u0005'A\u0011Ba\f5\u0003\u0003%IA!\r\t\u000f\te\"\u0004\"\u0001\u0003<!9!1\n\u000e\u0005\n\t5\u0003bBA|5\u0011\u0005!1\u000e\u0005\b\u0005\u000fSB1\u0001BE\u0011%\u0011yCGA\u0001\n\u0013\u0011\tD\u0001\u0007CS:|W.[1m\u0011\u0016\f\u0007O\u0003\u0002B\u0005\u0006I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003\u0007\u0012\u000b!bY8mY\u0016\u001cG/[8o\u0015\u0005)\u0015A\u00022sK\u0016TXm\u0001\u0001\u0016\u0005!36C\u0002\u0001J\u001f~+\u0007\u000e\u0005\u0002K\u001b6\t1JC\u0001M\u0003\u0015\u00198-\u00197b\u0013\tq5J\u0001\u0004B]f\u0014VM\u001a\t\u0004!J#V\"A)\u000b\u0005\r[\u0015BA*R\u0005!IE/\u001a:bE2,\u0007CA+W\u0019\u0001!Qa\u0016\u0001C\u0002a\u0013\u0011\u0001V\t\u00033r\u0003\"A\u0013.\n\u0005m[%a\u0002(pi\"Lgn\u001a\t\u0003\u0015vK!AX&\u0003\u0007\u0005s\u0017\u0010E\u0003QAR\u00137-\u0003\u0002b#\nY\u0011\n^3sC\ndWm\u00149t!\t\u0001&\u000bE\u0002e\u0001Qk\u0011\u0001\u0011\t\u0006!\u001a$&mY\u0005\u0003OF\u0013!d\u0015;sS\u000e$x\n\u001d;j[&TX\rZ%uKJ\f'\r\\3PaN\u0004\"![9\u000f\u0005)|gBA6o\u001b\u0005a'BA7G\u0003\u0019a$o\\8u}%\tA*\u0003\u0002q\u0017\u00069\u0001/Y2lC\u001e,\u0017B\u0001:t\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u00018*A\u0002pe\u0012\u00042!\u001b<U\u0013\t98O\u0001\u0005Pe\u0012,'/\u001b8h\u0003\u0019a\u0014N\\5u}Q\t!\u0010\u0006\u0002dw\")AO\u0001a\u0002k\u0006)AO]3fgV\ta\u0010\u0005\u0003j\u007f\u0006\r\u0011bAA\u0001g\n!A*[:u!\u0011\t)\u0001\b+\u000f\u0005\u0011L\u0012\u0001\u0004\"j]>l\u0017.\u00197IK\u0006\u0004\bC\u00013\u001b'\u0011Q\u0012*!\u0004\u0011\t\u0005=\u0011\u0011D\u0007\u0003\u0003#QA!a\u0005\u0002\u0016\u0005\u0011\u0011n\u001c\u0006\u0003\u0003/\tAA[1wC&\u0019!/!\u0005\u0015\u0005\u0005%!\u0001\u0002(pI\u0016,B!!\t\u0002<M)A$SA\u0012QB\u0019!*!\n\n\u0007\u0005\u001d2JA\u0004Qe>$Wo\u0019;\u0002\tI\fgn[\u000b\u0003\u0003[\u00012ASA\u0018\u0013\r\t\td\u0013\u0002\u0004\u0013:$\u0018!\u0002:b].\u0004\u0013!\u0001=\u0016\u0005\u0005e\u0002cA+\u0002<\u0011)q\u000b\bb\u00011\u0006\u0011\u0001\u0010I\u0001\tG\"LG\u000e\u001a:f]V\u0011\u00111\t\t\u0005S~\f)\u0005E\u0003\u0002Hq\tI$D\u0001\u001b\u0003%\u0019\u0007.\u001b7ee\u0016t\u0007\u0005\u0005\u0003jm\u0006eB\u0003CA(\u0003'\n)&a\u0016\u0015\t\u0005\u0015\u0013\u0011\u000b\u0005\u0007i\u0012\u0002\u001d!a\u0013\t\u000f\u0005%B\u00051\u0001\u0002.!9\u0011Q\u0007\u0013A\u0002\u0005e\u0002bBA I\u0001\u0007\u00111I\u0001\u0005Y&t7\u000e\u0006\u0003\u0002F\u0005u\u0003bBA0K\u0001\u0007\u0011QI\u0001\u0002]\u0006!1m\u001c9z+\u0011\t)'!\u001c\u0015\u0011\u0005\u001d\u00141OA;\u0003o\"B!!\u001b\u0002pA)\u0011q\t\u000f\u0002lA\u0019Q+!\u001c\u0005\u000b]3#\u0019\u0001-\t\rQ4\u00039AA9!\u0011Ig/a\u001b\t\u0013\u0005%b\u0005%AA\u0002\u00055\u0002\"CA\u001bMA\u0005\t\u0019AA6\u0011%\tyD\nI\u0001\u0002\u0004\tI\b\u0005\u0003j\u007f\u0006%\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0005\u0003\u007f\n)*\u0006\u0002\u0002\u0002*\"\u0011QFABW\t\t)\t\u0005\u0003\u0002\b\u0006EUBAAE\u0015\u0011\tY)!$\u0002\u0013Ut7\r[3dW\u0016$'bAAH\u0017\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005M\u0015\u0011\u0012\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,G!B,(\u0005\u0004A\u0016AD2paf$C-\u001a4bk2$HEM\u000b\u0005\u00037\u000by*\u0006\u0002\u0002\u001e*\"\u0011\u0011HAB\t\u00159\u0006F1\u0001Y\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*B!!*\u0002*V\u0011\u0011q\u0015\u0016\u0005\u0003\u0007\n\u0019\tB\u0003XS\t\u0007\u0001,A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003_\u0003B!!-\u000286\u0011\u00111\u0017\u0006\u0005\u0003k\u000b)\"\u0001\u0003mC:<\u0017\u0002BA]\u0003g\u0013aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u00049\u0006\u0005\u0007\"CAbY\u0005\u0005\t\u0019AA\u0017\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011\u0011\u001a\t\u0005!\u0006-G,C\u0002\u0002NF\u0013\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u00111[Am!\rQ\u0015Q[\u0005\u0004\u0003/\\%a\u0002\"p_2,\u0017M\u001c\u0005\t\u0003\u0007t\u0013\u0011!a\u00019\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\ty+a8\t\u0013\u0005\rw&!AA\u0002\u00055\u0012\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u00055\u0012\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005=\u0016AB3rk\u0006d7\u000f\u0006\u0003\u0002T\u00065\b\u0002CAbe\u0005\u0005\t\u0019\u0001/\u0002\t9{G-\u001a\t\u0004\u0003\u000f\"4\u0003\u0002\u001bJ\u0003\u001b!\"!!=\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\u0005m(1\u0001\u000b\t\u0003{\u0014IAa\u0003\u0003\u000eQ!\u0011q B\u0003!\u0015\t9\u0005\bB\u0001!\r)&1\u0001\u0003\u0006/^\u0012\r\u0001\u0017\u0005\u0007i^\u0002\u001dAa\u0002\u0011\t%4(\u0011\u0001\u0005\b\u0003S9\u0004\u0019AA\u0017\u0011\u001d\t)d\u000ea\u0001\u0005\u0003Aq!a\u00108\u0001\u0004\u0011y\u0001\u0005\u0003j\u007f\u0006}\u0018aB;oCB\u0004H._\u000b\u0005\u0005+\u0011)\u0003\u0006\u0003\u0003\u0018\t-\u0002#\u0002&\u0003\u001a\tu\u0011b\u0001B\u000e\u0017\n1q\n\u001d;j_:\u0004\u0012B\u0013B\u0010\u0003[\u0011\u0019Ca\n\n\u0007\t\u00052J\u0001\u0004UkBdWm\r\t\u0004+\n\u0015B!B,9\u0005\u0004A\u0006\u0003B5\u0000\u0005S\u0001R!a\u0012\u001d\u0005GA\u0011B!\f9\u0003\u0003\u0005\rA!\u000b\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u00034A!\u0011\u0011\u0017B\u001b\u0013\u0011\u00119$a-\u0003\r=\u0013'.Z2u\u0003\u0015)W\u000e\u001d;z+\u0011\u0011iDa\u0011\u0015\t\t}\"Q\t\t\u0005I\u0002\u0011\t\u0005E\u0002V\u0005\u0007\"Qa\u0016\u001eC\u0002aC\u0011Ba\u0012;\u0003\u0003\u0005\u001dA!\u0013\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u0003jm\n\u0005\u0013AB7l\u0011\u0016\f\u0007/\u0006\u0003\u0003P\t]CC\u0002B)\u0005?\u00129\u0007\u0006\u0003\u0003T\te\u0003\u0003\u00023\u0001\u0005+\u00022!\u0016B,\t\u001596H1\u0001Y\u0011%\u0011YfOA\u0001\u0002\b\u0011i&\u0001\u0006fm&$WM\\2fII\u0002B!\u001b<\u0003V!9!\u0011M\u001eA\u0002\t\r\u0014A\u00018t!\u0011IwP!\u001a\u0011\u000b\u0005\u001dCD!\u0016\t\u000f\t%4\b1\u0001\u0002.\u0005\u00111O_\u000b\u0005\u0005[\u0012)\b\u0006\u0003\u0003p\tuD\u0003\u0002B9\u0005o\u0002B\u0001\u001a\u0001\u0003tA\u0019QK!\u001e\u0005\u000b]c$\u0019\u0001-\t\u0013\teD(!AA\u0004\tm\u0014AC3wS\u0012,gnY3%gA!\u0011N\u001eB:\u0011\u001d\u0011y\b\u0010a\u0001\u0005\u0003\u000b\u0011\u0001\u001e\t\u0006\u0015\n\r%1O\u0005\u0004\u0005\u000b[%A\u0003\u001fsKB,\u0017\r^3e}\u0005a1-\u00198Ck&dGM\u0012:p[V1!1\u0012BL\u00057#BA!$\u0003\"BI\u0001Ka$\u0003\u0014\ne%qT\u0005\u0004\u0005#\u000b&!\u0003\"vS2$gI]8n!\u0011!\u0007A!&\u0011\u0007U\u00139\nB\u0003X{\t\u0007\u0001\fE\u0002V\u00057#aA!(>\u0005\u0004A&!\u0001\"\u0011\t\u0011\u0004!\u0011\u0014\u0005\n\u0005Gk\u0014\u0011!a\u0002\u0005K\u000b!\"\u001a<jI\u0016t7-\u001a\u00135!\u0011IgO!'\u0002\rQ\u0014X-Z:!\u0003\u0011\u0019\u0018N_3\u0002\u000bML'0\u001a\u0011\u0002\u000b\u0011\u0002H.^:\u0015\u0007\r\u0014\t\f\u0003\u0004\u00026\u001d\u0001\r\u0001V\u0001\u000bS:\u001cXM\u001d;Ue\u0016,G#\u0002@\u00038\ne\u0006bBA0\u0011\u0001\u0007\u00111\u0001\u0005\u0007\u0005\u007fB\u0001\u0019\u0001@\u0002\u0015\u0011\u0002H.^:%a2,8\u000fF\u0002d\u0005\u007fCaA!1\n\u0001\u0004\u0019\u0017!B8uQ\u0016\u0014\u0018!B7fe\u001e,Gc\u0002@\u0003H\n-'q\u001a\u0005\u0007\u0005\u0013T\u0001\u0019\u0001@\u0002\u00051\f\u0004B\u0002Bg\u0015\u0001\u0007a0\u0001\u0002me!1!\u0011\u001b\u0006A\u0002y\f1!Y2d\u0003\ri\u0017N\\\u000b\u0002)\u00061Q.\u001b8PaR,\"Aa7\u0011\t)\u0013I\u0002V\u0001\bM&tG-T5o)\r!&\u0011\u001d\u0005\u0006y6\u0001\rA`\u0001\u0007I\u0016dW*\u001b8\u0016\u0003\r\f\u0001\"\u001b;fe\u0006$xN]\u000b\u0003\u0005W\u0004B\u0001UAf)\u0006aAO]3f\u0013R,'/\u0019;peR!!1\u001eBy\u0011\u001d\ty\u0006\u0005a\u0001\u0003\u0007\t1!\\1q+\u0011\u00119Pa@\u0015\t\te8q\u0001\u000b\u0005\u0005w\u001c\t\u0001\u0005\u0003e\u0001\tu\bcA+\u0003\u0000\u00121!QT\tC\u0002aCqaa\u0001\u0012\u0001\b\u0019)!\u0001\u0002fmB!\u0011N\u001eB\u007f\u0011\u001d\u0019I!\u0005a\u0001\u0007\u0017\t\u0011A\u001a\t\u0007\u0015\u000e5AK!@\n\u0007\r=1JA\u0005Gk:\u001cG/[8oc\u00059a\r\\1u\u001b\u0006\u0004X\u0003BB\u000b\u0007;!Baa\u0006\u0004$Q!1\u0011DB\u0010!\u0011!\u0007aa\u0007\u0011\u0007U\u001bi\u0002\u0002\u0004\u0003\u001eJ\u0011\r\u0001\u0017\u0005\b\u0007\u0007\u0011\u00029AB\u0011!\u0011Igoa\u0007\t\u000f\r%!\u00031\u0001\u0004&A1!j!\u0004U\u0007O\u0001R\u0001UB\u0015\u00077I1aa\u000bR\u00051IE/\u001a:bE2,wJ\\2f\u0003\u001d\u0019w\u000e\u001c7fGR,Ba!\r\u0004:Q!11GB )\u0011\u0019)da\u000f\u0011\t\u0011\u00041q\u0007\t\u0004+\u000eeBA\u0002BO'\t\u0007\u0001\fC\u0004\u0004\u0004M\u0001\u001da!\u0010\u0011\t%48q\u0007\u0005\b\u0007\u0003\u001a\u0002\u0019AB\"\u0003\t\u0001h\r\u0005\u0004K\u0007\u000b\"6qG\u0005\u0004\u0007\u000fZ%a\u0004)beRL\u0017\r\u001c$v]\u000e$\u0018n\u001c8\u0002\r\r|gnY1u)\r\u00197Q\n\u0005\b\u0007\u001f\"\u0002\u0019AB)\u0003\u0011!\b.\u0019;\u0011\tA\u001bI\u0003\u0016\u000b\u0004G\u000eU\u0003bBB(+\u0001\u00071\u0011K\u0001\rMJ|Wn\u00159fG&4\u0017n\u0019\u000b\u0004G\u000em\u0003bBB/-\u0001\u00071\u0011K\u0001\u0005G>dG.\u0001\noK^\u001c\u0006/Z2jM&\u001c')^5mI\u0016\u0014XCAB2!\u0019\u0019)ga\u001bUG6\u00111q\r\u0006\u0004\u0007S\n\u0016aB7vi\u0006\u0014G.Z\u0005\u0005\u0007[\u001a9GA\u0004Ck&dG-\u001a:"
)
public class BinomialHeap implements Iterable, StrictOptimizedIterableOps, Serializable {
   private Option minOpt;
   private final Ordering ord;
   private final List trees;
   private final int size;
   private volatile boolean bitmap$0;

   public static BuildFrom canBuildFrom(final Ordering evidence$4) {
      return BinomialHeap$.MODULE$.canBuildFrom(evidence$4);
   }

   public static BinomialHeap apply(final Seq t, final Ordering evidence$3) {
      return BinomialHeap$.MODULE$.apply(t, evidence$3);
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

   /** @deprecated */
   public final Iterable toIterable() {
      return Iterable.toIterable$(this);
   }

   public final Iterable coll() {
      return Iterable.coll$(this);
   }

   public IterableFactory iterableFactory() {
      return Iterable.iterableFactory$(this);
   }

   /** @deprecated */
   public Iterable seq() {
      return Iterable.seq$(this);
   }

   public String className() {
      return Iterable.className$(this);
   }

   public final String collectionClassName() {
      return Iterable.collectionClassName$(this);
   }

   public String stringPrefix() {
      return Iterable.stringPrefix$(this);
   }

   public String toString() {
      return Iterable.toString$(this);
   }

   public LazyZip2 lazyZip(final Iterable that) {
      return Iterable.lazyZip$(this, that);
   }

   /** @deprecated */
   public final Iterable toTraversable() {
      return IterableOps.toTraversable$(this);
   }

   public boolean isTraversableAgain() {
      return IterableOps.isTraversableAgain$(this);
   }

   /** @deprecated */
   public final Object repr() {
      return IterableOps.repr$(this);
   }

   /** @deprecated */
   public IterableFactory companion() {
      return IterableOps.companion$(this);
   }

   public Object head() {
      return IterableOps.head$(this);
   }

   public Option headOption() {
      return IterableOps.headOption$(this);
   }

   public Object last() {
      return IterableOps.last$(this);
   }

   public Option lastOption() {
      return IterableOps.lastOption$(this);
   }

   public View view() {
      return IterableOps.view$(this);
   }

   public int sizeCompare(final int otherSize) {
      return IterableOps.sizeCompare$(this, otherSize);
   }

   public final IterableOps sizeIs() {
      return IterableOps.sizeIs$(this);
   }

   public int sizeCompare(final Iterable that) {
      return IterableOps.sizeCompare$(this, that);
   }

   /** @deprecated */
   public View view(final int from, final int until) {
      return IterableOps.view$(this, from, until);
   }

   public Object transpose(final Function1 asIterable) {
      return IterableOps.transpose$(this, asIterable);
   }

   public WithFilter withFilter(final Function1 p) {
      return IterableOps.withFilter$(this, p);
   }

   public Tuple2 splitAt(final int n) {
      return IterableOps.splitAt$(this, n);
   }

   public Object take(final int n) {
      return IterableOps.take$(this, n);
   }

   public Object takeWhile(final Function1 p) {
      return IterableOps.takeWhile$(this, p);
   }

   public Object drop(final int n) {
      return IterableOps.drop$(this, n);
   }

   public Object dropWhile(final Function1 p) {
      return IterableOps.dropWhile$(this, p);
   }

   public Iterator grouped(final int size) {
      return IterableOps.grouped$(this, size);
   }

   public Iterator sliding(final int size) {
      return IterableOps.sliding$(this, size);
   }

   public Iterator sliding(final int size, final int step) {
      return IterableOps.sliding$(this, size, step);
   }

   public Object tail() {
      return IterableOps.tail$(this);
   }

   public Object init() {
      return IterableOps.init$(this);
   }

   public Object slice(final int from, final int until) {
      return IterableOps.slice$(this, from, until);
   }

   public Map groupBy(final Function1 f) {
      return IterableOps.groupBy$(this, f);
   }

   public Map groupMap(final Function1 key, final Function1 f) {
      return IterableOps.groupMap$(this, key, f);
   }

   public Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
      return IterableOps.groupMapReduce$(this, key, f, reduce);
   }

   public Object scan(final Object z, final Function2 op) {
      return IterableOps.scan$(this, z, op);
   }

   public Object scanRight(final Object z, final Function2 op) {
      return IterableOps.scanRight$(this, z, op);
   }

   public Object concat(final IterableOnce suffix) {
      return IterableOps.concat$(this, suffix);
   }

   public final Object $plus$plus(final IterableOnce suffix) {
      return IterableOps.$plus$plus$(this, suffix);
   }

   public Object zipAll(final Iterable that, final Object thisElem, final Object thatElem) {
      return IterableOps.zipAll$(this, that, thisElem, thatElem);
   }

   public Iterator tails() {
      return IterableOps.tails$(this);
   }

   public Iterator inits() {
      return IterableOps.inits$(this);
   }

   /** @deprecated */
   public Object $plus$plus$colon(final IterableOnce that) {
      return IterableOps.$plus$plus$colon$(this, that);
   }

   /** @deprecated */
   public boolean hasDefiniteSize() {
      return IterableOnceOps.hasDefiniteSize$(this);
   }

   public void foreach(final Function1 f) {
      IterableOnceOps.foreach$(this, f);
   }

   public boolean forall(final Function1 p) {
      return IterableOnceOps.forall$(this, p);
   }

   public boolean exists(final Function1 p) {
      return IterableOnceOps.exists$(this, p);
   }

   public int count(final Function1 p) {
      return IterableOnceOps.count$(this, p);
   }

   public Option find(final Function1 p) {
      return IterableOnceOps.find$(this, p);
   }

   public Object foldLeft(final Object z, final Function2 op) {
      return IterableOnceOps.foldLeft$(this, z, op);
   }

   public Object foldRight(final Object z, final Function2 op) {
      return IterableOnceOps.foldRight$(this, z, op);
   }

   /** @deprecated */
   public final Object $div$colon(final Object z, final Function2 op) {
      return IterableOnceOps.$div$colon$(this, z, op);
   }

   /** @deprecated */
   public final Object $colon$bslash(final Object z, final Function2 op) {
      return IterableOnceOps.$colon$bslash$(this, z, op);
   }

   public Object fold(final Object z, final Function2 op) {
      return IterableOnceOps.fold$(this, z, op);
   }

   public Object reduce(final Function2 op) {
      return IterableOnceOps.reduce$(this, op);
   }

   public Option reduceOption(final Function2 op) {
      return IterableOnceOps.reduceOption$(this, op);
   }

   public Object reduceLeft(final Function2 op) {
      return IterableOnceOps.reduceLeft$(this, op);
   }

   public Object reduceRight(final Function2 op) {
      return IterableOnceOps.reduceRight$(this, op);
   }

   public Option reduceLeftOption(final Function2 op) {
      return IterableOnceOps.reduceLeftOption$(this, op);
   }

   public Option reduceRightOption(final Function2 op) {
      return IterableOnceOps.reduceRightOption$(this, op);
   }

   public boolean isEmpty() {
      return IterableOnceOps.isEmpty$(this);
   }

   public boolean nonEmpty() {
      return IterableOnceOps.nonEmpty$(this);
   }

   /** @deprecated */
   public final void copyToBuffer(final Buffer dest) {
      IterableOnceOps.copyToBuffer$(this, dest);
   }

   public int copyToArray(final Object xs) {
      return IterableOnceOps.copyToArray$(this, xs);
   }

   public int copyToArray(final Object xs, final int start) {
      return IterableOnceOps.copyToArray$(this, xs, start);
   }

   public int copyToArray(final Object xs, final int start, final int len) {
      return IterableOnceOps.copyToArray$(this, xs, start, len);
   }

   public Object sum(final Numeric num) {
      return IterableOnceOps.sum$(this, num);
   }

   public Object product(final Numeric num) {
      return IterableOnceOps.product$(this, num);
   }

   public Object min(final Ordering ord) {
      return IterableOnceOps.min$(this, ord);
   }

   public Option minOption(final Ordering ord) {
      return IterableOnceOps.minOption$(this, ord);
   }

   public Object max(final Ordering ord) {
      return IterableOnceOps.max$(this, ord);
   }

   public Option maxOption(final Ordering ord) {
      return IterableOnceOps.maxOption$(this, ord);
   }

   public Object maxBy(final Function1 f, final Ordering cmp) {
      return IterableOnceOps.maxBy$(this, f, cmp);
   }

   public Option maxByOption(final Function1 f, final Ordering cmp) {
      return IterableOnceOps.maxByOption$(this, f, cmp);
   }

   public Object minBy(final Function1 f, final Ordering cmp) {
      return IterableOnceOps.minBy$(this, f, cmp);
   }

   public Option minByOption(final Function1 f, final Ordering cmp) {
      return IterableOnceOps.minByOption$(this, f, cmp);
   }

   public Option collectFirst(final PartialFunction pf) {
      return IterableOnceOps.collectFirst$(this, pf);
   }

   /** @deprecated */
   public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
      return IterableOnceOps.aggregate$(this, z, seqop, combop);
   }

   public boolean corresponds(final IterableOnce that, final Function2 p) {
      return IterableOnceOps.corresponds$(this, that, p);
   }

   public final String mkString(final String start, final String sep, final String end) {
      return IterableOnceOps.mkString$(this, start, sep, end);
   }

   public final String mkString(final String sep) {
      return IterableOnceOps.mkString$(this, sep);
   }

   public final String mkString() {
      return IterableOnceOps.mkString$(this);
   }

   public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
      return IterableOnceOps.addString$(this, b, start, sep, end);
   }

   public final StringBuilder addString(final StringBuilder b, final String sep) {
      return IterableOnceOps.addString$(this, b, sep);
   }

   public final StringBuilder addString(final StringBuilder b) {
      return IterableOnceOps.addString$(this, b);
   }

   public Object to(final Factory factory) {
      return IterableOnceOps.to$(this, factory);
   }

   /** @deprecated */
   public final Iterator toIterator() {
      return IterableOnceOps.toIterator$(this);
   }

   public List toList() {
      return IterableOnceOps.toList$(this);
   }

   public Vector toVector() {
      return IterableOnceOps.toVector$(this);
   }

   public Map toMap(final .less.colon.less ev) {
      return IterableOnceOps.toMap$(this, ev);
   }

   public Set toSet() {
      return IterableOnceOps.toSet$(this);
   }

   public Seq toSeq() {
      return IterableOnceOps.toSeq$(this);
   }

   public IndexedSeq toIndexedSeq() {
      return IterableOnceOps.toIndexedSeq$(this);
   }

   /** @deprecated */
   public final Stream toStream() {
      return IterableOnceOps.toStream$(this);
   }

   public final Buffer toBuffer() {
      return IterableOnceOps.toBuffer$(this);
   }

   public Object toArray(final ClassTag evidence$2) {
      return IterableOnceOps.toArray$(this, evidence$2);
   }

   public Iterable reversed() {
      return IterableOnceOps.reversed$(this);
   }

   public Stepper stepper(final StepperShape shape) {
      return IterableOnce.stepper$(this, shape);
   }

   public int knownSize() {
      return IterableOnce.knownSize$(this);
   }

   public List trees() {
      return this.trees;
   }

   public int size() {
      return this.size;
   }

   public BinomialHeap $plus(final Object x) {
      return BinomialHeap$.MODULE$.breeze$collection$immutable$BinomialHeap$$mkHeap(this.insertTree(new Node(0, x, scala.package..MODULE$.Nil(), this.ord), this.trees()), this.size() + 1, this.ord);
   }

   private List insertTree(final Node n, final List t) {
      while(true) {
         List var10000;
         if (t.isEmpty()) {
            var10000 = (List)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Node[]{n}));
         } else {
            if (n.rank() >= ((Node)t.head()).rank()) {
               Node var5 = n.link((Node)t.head());
               t = (List)t.tail();
               n = var5;
               continue;
            }

            var10000 = t.$colon$colon(n);
         }

         return var10000;
      }
   }

   public BinomialHeap $plus$plus(final BinomialHeap other) {
      return BinomialHeap$.MODULE$.breeze$collection$immutable$BinomialHeap$$mkHeap(this.merge(this.trees(), other.trees(), scala.package..MODULE$.Nil()), this.size() + other.size(), this.ord);
   }

   private List merge(final List l1, final List l2, final List acc) {
      List var5;
      List l2;
      while(true) {
         Tuple2 var6 = new Tuple2(l1, l2);
         if (var6 != null) {
            List var7 = (List)var6._1();
            l2 = (List)var6._2();
            Nil var10000 = scala.package..MODULE$.Nil();
            if (var10000 == null) {
               if (var7 == null) {
                  break;
               }
            } else if (var10000.equals(var7)) {
               break;
            }
         }

         List l1;
         label78: {
            if (var6 != null) {
               l1 = (List)var6._1();
               List var11 = (List)var6._2();
               Nil var23 = scala.package..MODULE$.Nil();
               if (var23 == null) {
                  if (var11 == null) {
                     break label78;
                  }
               } else if (var23.equals(var11)) {
                  break label78;
               }
            }

            if (var6 == null) {
               throw new MatchError(var6);
            }

            List var13 = (List)var6._1();
            List var14 = (List)var6._2();
            if (!(var13 instanceof scala.collection.immutable..colon.colon)) {
               throw new MatchError(var6);
            }

            scala.collection.immutable..colon.colon var15 = (scala.collection.immutable..colon.colon)var13;
            Node n1 = (Node)var15.head();
            List r1 = var15.next$access$1();
            if (!(var14 instanceof scala.collection.immutable..colon.colon)) {
               throw new MatchError(var6);
            }

            scala.collection.immutable..colon.colon var18 = (scala.collection.immutable..colon.colon)var14;
            Node n2 = (Node)var18.head();
            List r2 = var18.next$access$1();
            if (n1.rank() < n2.rank()) {
               acc = acc.$colon$colon(n1);
               l2 = l2;
               l1 = r1;
               continue;
            }

            if (n2.rank() < n1.rank()) {
               acc = acc.$colon$colon(n2);
               l2 = r2;
               l1 = l1;
               continue;
            }

            var5 = this.insertTree(n1.link(n2), this.merge(r1, r2, acc));
            return var5;
         }

         var5 = (List)acc.reverse().$plus$plus(l1);
         return var5;
      }

      var5 = (List)acc.reverse().$plus$plus(l2);
      return var5;
   }

   public Object min() {
      return this.minOpt().get();
   }

   private Option minOpt$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.minOpt = (Option)(this.trees().isEmpty() ? scala.None..MODULE$ : new Some(this.findMin(this.trees())));
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.minOpt;
   }

   public Option minOpt() {
      return !this.bitmap$0 ? this.minOpt$lzycompute() : this.minOpt;
   }

   private Object findMin(final List trees) {
      Object var2;
      Node t;
      label40: {
         boolean var3 = false;
         scala.collection.immutable..colon.colon var4 = null;
         if (trees instanceof scala.collection.immutable..colon.colon) {
            var3 = true;
            var4 = (scala.collection.immutable..colon.colon)trees;
            t = (Node)var4.head();
            List var7 = var4.next$access$1();
            Nil var10000 = scala.package..MODULE$.Nil();
            if (var10000 == null) {
               if (var7 == null) {
                  break label40;
               }
            } else if (var10000.equals(var7)) {
               break label40;
            }
         }

         if (!var3) {
            throw new IllegalArgumentException("Shouldn't get Nil!");
         }

         Node t = (Node)var4.head();
         List ts = var4.next$access$1();
         Object x = t.x();
         Object y = this.findMin(ts);
         var2 = this.ord.mkOrderingOps(x).$less(y) ? x : y;
         return var2;
      }

      var2 = t.x();
      return var2;
   }

   public BinomialHeap delMin() {
      BinomialHeap var10000;
      if (this.trees().isEmpty()) {
         var10000 = this;
      } else {
         Tuple2 var3 = this.getMin$1(this.trees());
         if (var3 == null) {
            throw new MatchError(var3);
         }

         Node var4 = (Node)var3._1();
         List t2 = (List)var3._2();
         if (var4 == null) {
            throw new MatchError(var3);
         }

         Object x = var4.x();
         List t1 = var4.children();
         Tuple3 var1 = new Tuple3(x, t1, t2);
         Object var8 = var1._1();
         List t1 = (List)var1._2();
         List t2 = (List)var1._3();
         this.merge(t1.reverse(), t2, scala.package..MODULE$.Nil());
         var10000 = BinomialHeap$.MODULE$.breeze$collection$immutable$BinomialHeap$$mkHeap(this.merge(t1.reverse(), t2, scala.package..MODULE$.Nil()), this.size() - 1, this.ord);
      }

      return var10000;
   }

   public Iterator iterator() {
      return Iterators$.MODULE$.merge(this.trees().map((n) -> this.treeIterator(n)), (x, y) -> BoxesRunTime.boxToInteger($anonfun$iterator$2(this, x, y)));
   }

   private Iterator treeIterator(final Node n) {
      Iterators$ var10000 = Iterators$.MODULE$;
      Iterator var2 = scala.collection.Iterator..MODULE$.single(n.x());
      return var10000.merge(n.children().map((nx) -> this.treeIterator(nx)).$colon$colon(var2), (x, y) -> BoxesRunTime.boxToInteger($anonfun$treeIterator$2(this, x, y)));
   }

   public BinomialHeap map(final Function1 f, final Ordering ev) {
      return (BinomialHeap)this.strictOptimizedMap(BinomialHeap$.MODULE$.canBuildFrom(ev).newBuilder(this), f);
   }

   public BinomialHeap flatMap(final Function1 f, final Ordering ev) {
      return (BinomialHeap)this.strictOptimizedFlatMap(BinomialHeap$.MODULE$.canBuildFrom(ev).newBuilder(this), f);
   }

   public BinomialHeap collect(final PartialFunction pf, final Ordering ev) {
      return (BinomialHeap)this.strictOptimizedCollect(BinomialHeap$.MODULE$.canBuildFrom(ev).newBuilder(this), pf);
   }

   public BinomialHeap concat(final IterableOnce that) {
      return (BinomialHeap)that.iterator().foldLeft(this, (x$3, x$4) -> x$3.$plus(x$4));
   }

   public BinomialHeap $plus$plus(final IterableOnce that) {
      return this.concat(that);
   }

   public BinomialHeap fromSpecific(final IterableOnce coll) {
      return BinomialHeap$.MODULE$.empty(this.ord).$plus$plus(coll);
   }

   public Builder newSpecificBuilder() {
      return BinomialHeap$.MODULE$.canBuildFrom(this.ord).newBuilder(this);
   }

   public BinomialHeap empty() {
      return BinomialHeap$.MODULE$.empty(this.ord);
   }

   private final Tuple2 getMin$1(final List t) {
      Tuple2 var2;
      Node n;
      label46: {
         boolean var4 = false;
         scala.collection.immutable..colon.colon var5 = null;
         if (t instanceof scala.collection.immutable..colon.colon) {
            var4 = true;
            var5 = (scala.collection.immutable..colon.colon)t;
            n = (Node)var5.head();
            List var8 = var5.next$access$1();
            Nil var10000 = scala.package..MODULE$.Nil();
            if (var10000 == null) {
               if (var8 == null) {
                  break label46;
               }
            } else if (var10000.equals(var8)) {
               break label46;
            }
         }

         if (!var4) {
            throw new IllegalArgumentException("Shouldn't get Nil!");
         }

         Node n = (Node)var5.head();
         List ts = var5.next$access$1();
         Tuple2 var13 = this.getMin$1(ts);
         if (var13 == null) {
            throw new MatchError(var13);
         }

         Node n2 = (Node)var13._1();
         List ts2 = (List)var13._2();
         Tuple2 var3 = new Tuple2(n2, ts2);
         Node n2 = (Node)var3._1();
         List ts2 = (List)var3._2();
         var2 = this.ord.mkOrderingOps(n.x()).$less$eq(n2.x()) ? new Tuple2(n, ts) : new Tuple2(n2, ts2.$colon$colon(n));
         return var2;
      }

      var2 = new Tuple2(n, scala.package..MODULE$.Nil());
      return var2;
   }

   // $FF: synthetic method
   public static final int $anonfun$iterator$2(final BinomialHeap $this, final Object x, final Object y) {
      return $this.ord.compare(x, y);
   }

   // $FF: synthetic method
   public static final int $anonfun$treeIterator$2(final BinomialHeap $this, final Object x, final Object y) {
      return $this.ord.compare(x, y);
   }

   public BinomialHeap(final Ordering ord) {
      this.ord = ord;
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      IterableOps.$init$(this);
      IterableFactoryDefaults.$init$(this);
      Iterable.$init$(this);
      StrictOptimizedIterableOps.$init$(this);
      this.trees = scala.package..MODULE$.Nil();
      this.size = 0;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class Node implements Product, Serializable {
      private final int rank;
      private final Object x;
      private final List children;
      private final Ordering ord;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int rank() {
         return this.rank;
      }

      public Object x() {
         return this.x;
      }

      public List children() {
         return this.children;
      }

      public Node link(final Node n) {
         return this.ord.mkOrderingOps(this.x()).$less$eq(n.x()) ? new Node(this.rank() + 1, this.x(), this.children().$colon$colon(n), this.ord) : new Node(this.rank() + 1, n.x(), n.children().$colon$colon(this), this.ord);
      }

      public Node copy(final int rank, final Object x, final List children, final Ordering ord) {
         return new Node(rank, x, children, ord);
      }

      public int copy$default$1() {
         return this.rank();
      }

      public Object copy$default$2() {
         return this.x();
      }

      public List copy$default$3() {
         return this.children();
      }

      public String productPrefix() {
         return "Node";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToInteger(this.rank());
               break;
            case 1:
               var10000 = this.x();
               break;
            case 2:
               var10000 = this.children();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Node;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "rank";
               break;
            case 1:
               var10000 = "x";
               break;
            case 2:
               var10000 = "children";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.rank());
         var1 = Statics.mix(var1, Statics.anyHash(this.x()));
         var1 = Statics.mix(var1, Statics.anyHash(this.children()));
         return Statics.finalizeHash(var1, 3);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label57: {
               boolean var2;
               if (x$1 instanceof Node) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label40: {
                     Node var4 = (Node)x$1;
                     if (this.rank() == var4.rank() && BoxesRunTime.equals(this.x(), var4.x())) {
                        label37: {
                           List var10000 = this.children();
                           List var5 = var4.children();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label37;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label37;
                           }

                           if (var4.canEqual(this)) {
                              var7 = true;
                              break label40;
                           }
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label57;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      public Node(final int rank, final Object x, final List children, final Ordering ord) {
         this.rank = rank;
         this.x = x;
         this.children = children;
         this.ord = ord;
         Product.$init$(this);
      }
   }

   public static class Node$ implements Serializable {
      public static final Node$ MODULE$ = new Node$();

      public final String toString() {
         return "Node";
      }

      public Node apply(final int rank, final Object x, final List children, final Ordering ord) {
         return new Node(rank, x, children, ord);
      }

      public Option unapply(final Node x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.rank()), x$0.x(), x$0.children())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Node$.class);
      }
   }
}
