package scala.reflect.internal.tpe;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Console.;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.mutable.HashSet;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.AnnotationCheckers;
import scala.reflect.internal.Constants;
import scala.reflect.internal.Definitions;
import scala.reflect.internal.Depth$;
import scala.reflect.internal.Names;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.TypesStats;
import scala.reflect.internal.Variance$;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.util.Collections;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.StatisticsStatics;
import scala.reflect.internal.util.StripMarginInterpolator;
import scala.reflect.internal.util.TriState$;
import scala.reflect.internal.util.package;
import scala.reflect.internal.util.package$;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\rmb!\u0003!B!\u0003\r\tASB\u001b\u0011\u0015y\u0005\u0001\"\u0001Q\u0011\u001d!\u0006A1A\u0005\u000eUCq\u0001\u0017\u0001CB\u0013%\u0011\f\u0003\u0004\u0002\u0002\u0002!\t!\u0017\u0004\u0005I\u0002\u0011U\r\u0003\u0005v\u000b\tU\r\u0011\"\u0001w\u0011!aXA!E!\u0002\u00139\b\u0002C?\u0006\u0005+\u0007I\u0011\u0001<\t\u0011y,!\u0011#Q\u0001\n]Daa`\u0003\u0005\u0002\u0005\u0005\u0001bBA\u0004\u000b\u0011\u0005\u0013\u0011\u0002\u0005\n\u00037)\u0011\u0011!C\u0001\u0003;A\u0011\"a\t\u0006#\u0003%\t!!\n\t\u0013\u0005mR!%A\u0005\u0002\u0005\u0015\u0002\"CA\u001f\u000b\u0005\u0005I\u0011IA \u0011%\t\t%BA\u0001\n\u0003\t\u0019\u0005C\u0005\u0002L\u0015\t\t\u0011\"\u0001\u0002N!I\u0011\u0011L\u0003\u0002\u0002\u0013\u0005\u00131\f\u0005\n\u0003K*\u0011\u0011!C\u0001\u0003OB\u0011\"!\u001d\u0006\u0003\u0003%\t%a\u001d\t\u0013\u0005]T!!A\u0005B\u0005e\u0004\"CA>\u000b\u0005\u0005I\u0011IA?\u000f%\t\u0019\tAA\u0001\u0012\u0003\t)I\u0002\u0005e\u0001\u0005\u0005\t\u0012AAD\u0011\u0019y\b\u0004\"\u0001\u0002 \"I\u0011q\u0001\r\u0002\u0002\u0013\u0015\u0013\u0011\u0002\u0005\n\u0003CC\u0012\u0011!CA\u0003GC\u0011\"!+\u0019\u0003\u0003%\t)a+\t\u0013\u0005u\u0006\u00011Q\u0005\n\u0005\r\u0003\"CA`\u0001\u0001\u0007K\u0011BAa\u0011\u001d\t)\r\u0001C\u0001\u0003\u0007Bq!a2\u0001\t\u0003\tI\rC\u0004\u0002P\u0002!I!!5\t\u000f\u0005m\u0007\u0001\"\u0003\u0002^\"9\u0011Q\u001f\u0001\u0005\n\u0005]\bb\u0002B\u0001\u0001\u0011%!1\u0001\u0005\b\u0005\u001b\u0001A\u0011\u0001B\b\u0011\u001d\u0011)\u0002\u0001C\u0001\u0005/AqA!\b\u0001\t\u0013\u0011y\u0002C\u0004\u0003\u001e\u0001!IAa\f\t\u000f\tU\u0002\u0001\"\u0001\u00038!9!Q\b\u0001\u0005\n\t}\u0002b\u0002B#\u0001\u0011%!q\t\u0005\b\u0005\u001b\u0002A\u0011\u0002B(\u0011\u001d\u0011)\u0006\u0001C\u0005\u0005/BqA!\u0018\u0001\t\u0013\u0011y\u0006C\u0004\u0003l\u0001!IA!\u001c\t\u000f\tu\u0004\u0001\"\u0003\u0003\u0000!9!\u0011\u0014\u0001\u0005\n\tm\u0005b\u0002BQ\u0001\u0011%!1\u0015\u0005\b\u0005[\u0003A\u0011\u0001BX\u0011\u001d\u0011)\f\u0001C\u0001\u0005oC\u0011B!3\u0001#\u0003%\tAa3\t\u000f\t=\u0007\u0001\"\u0003\u0003R\"9!1\u001d\u0001\u0005\n\t\u0015\bb\u0002Bw\u0001\u0011%!q\u001e\u0005\b\u0005w\u0004A\u0011\u0002B\u007f\u0011\u001d\u0019\u0019\u0001\u0001C\u0005\u0007\u000bAqaa\u0003\u0001\t\u0003\u0019i\u0001C\u0004\u0004\u0016\u0001!Iaa\u0006\t\u000f\r}\u0001\u0001\"\u0001\u0004\"!91q\u0005\u0001\u0005\u0002\r%\u0002bBB\u0018\u0001\u0011%1\u0011\u0007\u0002\u000e)f\u0004XmQ8na\u0006\u0014XM]:\u000b\u0005\t\u001b\u0015a\u0001;qK*\u0011A)R\u0001\tS:$XM\u001d8bY*\u0011aiR\u0001\be\u00164G.Z2u\u0015\u0005A\u0015!B:dC2\f7\u0001A\n\u0003\u0001-\u0003\"\u0001T'\u000e\u0003\u001dK!AT$\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\t\u0011\u000b\u0005\u0002M%&\u00111k\u0012\u0002\u0005+:LG/A\u000eM_\u001e\u0004VM\u001c3j]\u001e\u001cVO\u0019+za\u0016\u001cH\u000b\u001b:fg\"|G\u000eZ\u000b\u0002->\tq+H\u00013\u0003Ay\u0006/\u001a8eS:<7+\u001e2UsB,7/F\u0001[!\rY\u0006MY\u0007\u00029*\u0011QLX\u0001\b[V$\u0018M\u00197f\u0015\tyv)\u0001\u0006d_2dWm\u0019;j_:L!!\u0019/\u0003\u000f!\u000b7\u000f[*fiB\u00111-B\u0007\u0002\u0001\tY1+\u001e2UsB,\u0007+Y5s'\u0011)1JZ5\u0011\u00051;\u0017B\u00015H\u0005\u001d\u0001&o\u001c3vGR\u0004\"A\u001b:\u000f\u0005-\u0004hB\u00017p\u001b\u0005i'B\u00018J\u0003\u0019a$o\\8u}%\t\u0001*\u0003\u0002r\u000f\u00069\u0001/Y2lC\u001e,\u0017BA:u\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\tx)A\u0002uaF*\u0012a\u001e\t\u0003GbL!!\u001f>\u0003\tQK\b/Z\u0005\u0003w\u000e\u0013Q\u0001V=qKN\fA\u0001\u001e92A\u0005\u0019A\u000f\u001d\u001a\u0002\tQ\u0004(\u0007I\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000b\t\f\u0019!!\u0002\t\u000bUT\u0001\u0019A<\t\u000buT\u0001\u0019A<\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\u0003\u0011\t\u00055\u0011qC\u0007\u0003\u0003\u001fQA!!\u0005\u0002\u0014\u0005!A.\u00198h\u0015\t\t)\"\u0001\u0003kCZ\f\u0017\u0002BA\r\u0003\u001f\u0011aa\u0015;sS:<\u0017\u0001B2paf$RAYA\u0010\u0003CAq!\u001e\u0007\u0011\u0002\u0003\u0007q\u000fC\u0004~\u0019A\u0005\t\u0019A<\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011q\u0005\u0016\u0004o\u0006%2FAA\u0016!\u0011\ti#a\u000e\u000e\u0005\u0005=\"\u0002BA\u0019\u0003g\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005Ur)\u0001\u0006b]:|G/\u0019;j_:LA!!\u000f\u00020\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"!a\u0003\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005\u0015\u0003c\u0001'\u0002H%\u0019\u0011\u0011J$\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005=\u0013Q\u000b\t\u0004\u0019\u0006E\u0013bAA*\u000f\n\u0019\u0011I\\=\t\u0013\u0005]\u0013#!AA\u0002\u0005\u0015\u0013a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002^A1\u0011qLA1\u0003\u001fj\u0011AX\u0005\u0004\u0003Gr&\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\u001b\u0002pA\u0019A*a\u001b\n\u0007\u00055tIA\u0004C_>dW-\u00198\t\u0013\u0005]3#!AA\u0002\u0005=\u0013A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!a\u0003\u0002v!I\u0011q\u000b\u000b\u0002\u0002\u0003\u0007\u0011QI\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011QI\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005%\u0014q\u0010\u0005\n\u0003/2\u0012\u0011!a\u0001\u0003\u001f\nq\u0002]3oI&twmU;c)f\u0004Xm]\u0001\f'V\u0014G+\u001f9f!\u0006L'\u000f\u0005\u0002d1M)\u0001$!#\u0002\u0016B9\u00111RAIo^\u0014WBAAG\u0015\r\tyiR\u0001\beVtG/[7f\u0013\u0011\t\u0019*!$\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t'\u0007\u0005\u0003\u0002\u0018\u0006uUBAAM\u0015\u0011\tY*a\u0005\u0002\u0005%|\u0017bA:\u0002\u001aR\u0011\u0011QQ\u0001\u0006CB\u0004H.\u001f\u000b\u0006E\u0006\u0015\u0016q\u0015\u0005\u0006kn\u0001\ra\u001e\u0005\u0006{n\u0001\ra^\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ti+!/\u0011\u000b1\u000by+a-\n\u0007\u0005EvI\u0001\u0004PaRLwN\u001c\t\u0006\u0019\u0006Uvo^\u0005\u0004\u0003o;%A\u0002+va2,'\u0007\u0003\u0005\u0002<r\t\t\u00111\u0001c\u0003\rAH\u0005M\u0001\u0017?N,(m]1nKRL\b/\u001a*fGV\u00148/[8og\u0006Qrl];cg\u0006lW\r^=qKJ+7-\u001e:tS>t7o\u0018\u0013fcR\u0019\u0011+a1\t\u0013\u0005]c$!AA\u0002\u0005\u0015\u0013!F:vEN\fW.\u001a;za\u0016\u0014VmY;sg&|gn]\u0001\u001agV\u00147/Y7fif\u0004XMU3dkJ\u001c\u0018n\u001c8t?\u0012*\u0017\u000fF\u0002R\u0003\u0017Dq!!4!\u0001\u0004\t)%A\u0003wC2,X-A\u0006jgVs\u0017NZ5bE2,GCBA5\u0003'\f9\u000e\u0003\u0004\u0002V\u0006\u0002\ra^\u0001\u0005aJ,\u0017\u0007\u0003\u0004\u0002Z\u0006\u0002\ra^\u0001\u0005aJ,''A\fjgN\u000bW.Z*qK\u000eL\u0017\r\\5{K\u0012\u001c6n\u001c7f[RQ\u0011\u0011NAp\u0003[\f\t0a=\t\u000f\u0005\u0005(\u00051\u0001\u0002d\u0006!1/_72!\r\u0019\u0017Q]\u0005\u0005\u0003O\fIO\u0001\u0004Ts6\u0014w\u000e\\\u0005\u0004\u0003W\u001c%aB*z[\n|Gn\u001d\u0005\b\u0003_\u0014\u0003\u0019AAr\u0003\u0011\u0019\u00180\u001c\u001a\t\r\u0005U'\u00051\u0001x\u0011\u0019\tIN\ta\u0001o\u0006A\u0011n]*vEB\u0013X\r\u0006\u0005\u0002j\u0005e\u00181`A\u007f\u0011\u0019\t)n\ta\u0001o\"1\u0011\u0011\\\u0012A\u0002]Dq!a@$\u0001\u0004\t\u0019/A\u0002ts6\fA#Z9vC2\u001c\u00160\\:B]\u0012\u0004&/\u001a4jq\u0016\u001cHCCA5\u0005\u000b\u00119A!\u0003\u0003\f!9\u0011\u0011\u001d\u0013A\u0002\u0005\r\bBBAkI\u0001\u0007q\u000fC\u0004\u0002p\u0012\u0002\r!a9\t\r\u0005eG\u00051\u0001x\u0003=I7\u000fR5gM\u0016\u0014XM\u001c;UsB,GCBA5\u0005#\u0011\u0019\u0002C\u0003vK\u0001\u0007q\u000fC\u0003~K\u0001\u0007q/\u0001\u000ejg\u0012KgMZ3sK:$H+\u001f9f\u0007>t7\u000f\u001e:vGR|'\u000f\u0006\u0004\u0002j\te!1\u0004\u0005\u0006k\u001a\u0002\ra\u001e\u0005\u0006{\u001a\u0002\ra^\u0001\u0016SN\u001c\u0016-\\3UsB,7i\u001c8tiJ,8\r^8s)\u0019\tIG!\t\u0003,!9!1E\u0014A\u0002\t\u0015\u0012a\u0001;scA\u00191Ma\n\n\u0007\t%\"PA\u0004UsB,'+\u001a4\t\u000f\t5r\u00051\u0001\u0003&\u0005\u0019AO\u001d\u001a\u0015\r\u0005%$\u0011\u0007B\u001a\u0011\u0015)\b\u00061\u0001x\u0011\u0015i\b\u00061\u0001x\u0003)I7oU1nKRK\b/\u001a\u000b\u0007\u0003S\u0012IDa\u000f\t\u000bUL\u0003\u0019A<\t\u000buL\u0003\u0019A<\u0002%M\fW.Z!o]>$\u0018\r^3e)f\u0004Xm\u001d\u000b\u0007\u0003S\u0012\tEa\u0011\t\u000bUT\u0003\u0019A<\t\u000buT\u0003\u0019A<\u0002\u0017%\u001c8+Y7f)f\u0004X-\r\u000b\u0007\u0003S\u0012IEa\u0013\t\u000bU\\\u0003\u0019A<\t\u000bu\\\u0003\u0019A<\u0002\u001b%\u001c8+Y7f\u0011.#\u0016\u0010]3t)\u0019\tIG!\u0015\u0003T!)Q\u000f\fa\u0001o\")Q\u0010\fa\u0001o\u0006i\u0011n]*b[\u0016$\u0016\u0010]3SK\u001a$b!!\u001b\u0003Z\tm\u0003b\u0002B\u0012[\u0001\u0007!Q\u0005\u0005\b\u0005[i\u0003\u0019\u0001B\u0013\u0003MI7oU1nKNKgn\u001a7fi>tG+\u001f9f)\u0019\tIG!\u0019\u0003j!1QO\fa\u0001\u0005G\u00022a\u0019B3\u0013\r\u00119G\u001f\u0002\u000e'&tw\r\\3u_:$\u0016\u0010]3\t\rut\u0003\u0019\u0001B2\u0003AI7oU1nK6+G\u000f[8e)f\u0004X\r\u0006\u0004\u0002j\t=$\u0011\u0010\u0005\b\u0005cz\u0003\u0019\u0001B:\u0003\riG/\r\t\u0004G\nU\u0014b\u0001B<u\nQQ*\u001a;i_\u0012$\u0016\u0010]3\t\u000f\tmt\u00061\u0001\u0003t\u0005\u0019Q\u000e\u001e\u001a\u00021\u0015\fX/\u00197UsB,\u0007+\u0019:b[N\fe\u000e\u001a*fgVdG\u000f\u0006\u0006\u0002j\t\u0005%Q\u0012BI\u0005+CqAa!1\u0001\u0004\u0011))\u0001\u0005ua\u0006\u0014\u0018-\\:2!\u0019\u00119I!#\u0002d:\u0011A\n]\u0005\u0004\u0005\u0017#(\u0001\u0002'jgRDaAa$1\u0001\u00049\u0018\u0001\u0002:fgFBqAa%1\u0001\u0004\u0011))\u0001\u0005ua\u0006\u0014\u0018-\\:3\u0011\u0019\u00119\n\ra\u0001o\u0006!!/Z:3\u0003\u001djW\r\u001e5pI\"Kw\r[3s\u001fJ$WM\u001d+za\u0016\u0004\u0016M]1ngN\u000bW.\u001a,be&\fgnY3\u0015\r\u0005%$Q\u0014BP\u0011\u001d\t\t/\ra\u0001\u0003GDq!a<2\u0001\u0004\t\u0019/\u0001\u0014nKRDw\u000e\u001a%jO\",'o\u0014:eKJ$\u0016\u0010]3QCJ\fWn]*vEZ\u000b'/[1oG\u0016$b!!\u001b\u0003&\n%\u0006b\u0002BTe\u0001\u0007\u00111]\u0001\u0004Y><\bb\u0002BVe\u0001\u0007\u00111]\u0001\u0005Q&<\u0007.A\u0006jgN\u000bW.\u001a+za\u0016\u0014DCBA5\u0005c\u0013\u0019\fC\u0003vg\u0001\u0007q\u000fC\u0003~g\u0001\u0007q/A\u0005jgN+(\rV=qKRA\u0011\u0011\u000eB]\u0005w\u0013i\fC\u0003vi\u0001\u0007q\u000fC\u0003~i\u0001\u0007q\u000fC\u0005\u0003@R\u0002\n\u00111\u0001\u0003B\u0006)A-\u001a9uQB!!1\u0019Bc\u001b\u0005\u0019\u0015b\u0001Bd\u0007\n)A)\u001a9uQ\u0006\u0019\u0012n]*vERK\b/\u001a\u0013eK\u001a\fW\u000f\u001c;%gU\u0011!Q\u001a\u0016\u0005\u0005\u0003\fI#\u0001\u000busB,'+\u001a7bi&|g\u000e\u0015:f\u0007\",7m\u001b\u000b\u0007\u0005'\u0014yN!9\u0011\t\tU'1\\\u0007\u0003\u0005/T1A!7D\u0003\u0011)H/\u001b7\n\t\tu'q\u001b\u0002\t)JL7\u000b^1uK\")QO\u000ea\u0001o\")QP\u000ea\u0001o\u0006Q\u0011n]*vERK\b/Z\u0019\u0015\u0011\u0005%$q\u001dBu\u0005WDQ!^\u001cA\u0002]DQ!`\u001cA\u0002]DqAa08\u0001\u0004\u0011\t-A\u0007jgB{G._*vERK\b/\u001a\u000b\u0007\u0003S\u0012\tP!?\t\rUD\u0004\u0019\u0001Bz!\r\u0019'Q_\u0005\u0004\u0005oT(\u0001\u0003)pYf$\u0016\u0010]3\t\ruD\u0004\u0019\u0001Bz\u0003UI7\u000f\u00165jg\u0006sGmU;qKJ\u001cVO\u0019;za\u0016$b!!\u001b\u0003\u0000\u000e\u0005\u0001\"B;:\u0001\u00049\b\"B?:\u0001\u00049\u0018!G5t\u001d>\f%oZ*uCRL7m\u00117bgN$\u0016\u0010]3SK\u001a$B!!\u001b\u0004\b!11\u0011\u0002\u001eA\u0002]\f!\u0001\u001e9\u0002\u0017%\u001c\bjS*vERK\b/\u001a\u000b\t\u0003S\u001aya!\u0005\u0004\u0014!)Qo\u000fa\u0001o\")Qp\u000fa\u0001o\"9!qX\u001eA\u0002\t\u0005\u0017AC5t'V\u0014G+\u001f9feQA\u0011\u0011NB\r\u00077\u0019i\u0002C\u0003vy\u0001\u0007q\u000fC\u0003~y\u0001\u0007q\u000fC\u0004\u0003@r\u0002\rA!1\u0002\u001b%\u001cx+Z1l'V\u0014G+\u001f9f)\u0019\tIga\t\u0004&!)Q/\u0010a\u0001o\")Q0\u0010a\u0001o\u0006\u0001\u0012n\u001d(v[\u0016\u0014\u0018nY*vERK\b/\u001a\u000b\u0007\u0003S\u001aYc!\f\t\u000bUt\u0004\u0019A<\t\u000but\u0004\u0019A<\u0002%A\u0014\u0018.\\5uSZ,')Y:f\u00072\f7o\u001d\u000b\u0005\u0003G\u001c\u0019\u0004\u0003\u0004\u0004\n}\u0002\ra\u001e\t\u0005\u0005\u0007\u001c9$C\u0002\u0004:\r\u00131bU=nE>dG+\u00192mK\u0002"
)
public interface TypeComparers {
   SubTypePair$ SubTypePair();

   void scala$reflect$internal$tpe$TypeComparers$_setter_$scala$reflect$internal$tpe$TypeComparers$$_pendingSubTypes_$eq(final HashSet x$1);

   private int LogPendingSubTypesThreshold() {
      return 50;
   }

   HashSet scala$reflect$internal$tpe$TypeComparers$$_pendingSubTypes();

   // $FF: synthetic method
   static HashSet pendingSubTypes$(final TypeComparers $this) {
      return $this.pendingSubTypes();
   }

   default HashSet pendingSubTypes() {
      return this.scala$reflect$internal$tpe$TypeComparers$$_pendingSubTypes();
   }

   int scala$reflect$internal$tpe$TypeComparers$$_subsametypeRecursions();

   void scala$reflect$internal$tpe$TypeComparers$$_subsametypeRecursions_$eq(final int x$1);

   // $FF: synthetic method
   static int subsametypeRecursions$(final TypeComparers $this) {
      return $this.subsametypeRecursions();
   }

   default int subsametypeRecursions() {
      return this.scala$reflect$internal$tpe$TypeComparers$$_subsametypeRecursions();
   }

   // $FF: synthetic method
   static void subsametypeRecursions_$eq$(final TypeComparers $this, final int value) {
      $this.subsametypeRecursions_$eq(value);
   }

   default void subsametypeRecursions_$eq(final int value) {
      this.scala$reflect$internal$tpe$TypeComparers$$_subsametypeRecursions_$eq(value);
   }

   private boolean isUnifiable(final Types.Type pre1, final Types.Type pre2) {
      return (((Types)this).isEligibleForPrefixUnification(pre1) || ((Types)this).isEligibleForPrefixUnification(pre2)) && pre1.$eq$colon$eq(pre2);
   }

   private boolean isSameSpecializedSkolem(final Symbols.Symbol sym1, final Symbols.Symbol sym2, final Types.Type pre1, final Types.Type pre2) {
      if (sym1.isExistentialSkolem() && sym2.isExistentialSkolem()) {
         Names.Name var10000 = sym1.name();
         Names.Name var5 = sym2.name();
         if (var10000 == null) {
            if (var5 != null) {
               return false;
            }
         } else if (!var10000.equals(var5)) {
            return false;
         }

         if (((SymbolTable)this).phase().specialized() && sym1.info().$eq$colon$eq(sym2.info()) && pre1.$eq$colon$eq(pre2)) {
            return true;
         }
      }

      return false;
   }

   private boolean isSubPre(final Types.Type pre1, final Types.Type pre2, final Symbols.Symbol sym) {
      if (pre1 != pre2 && pre1 != ((Types)this).NoPrefix() && pre2 != ((Types)this).NoPrefix() && pre1.$less$colon$less(pre2)) {
         MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var9 = MutableSettings$.MODULE$;
         MutableSettings SettingsOps_settings = ((SymbolTable)this).settings();
         MutableSettings var10 = SettingsOps_settings;
         SettingsOps_settings = null;
         MutableSettings isDebug$extension_$this = var10;
         boolean var11 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDebug$extension_$this.debug().value());
         isDebug$extension_$this = null;
         if (var11) {
            Object println_x = (new StringBuilder(20)).append("new isSubPre ").append(sym).append(": ").append(pre1).append(" <:< ").append(pre2).toString();
            .MODULE$.println(println_x);
         }

         return true;
      } else {
         return false;
      }
   }

   private boolean equalSymsAndPrefixes(final Symbols.Symbol sym1, final Types.Type pre1, final Symbols.Symbol sym2, final Types.Type pre2) {
      if (sym1 == sym2) {
         return sym1.hasPackageFlag() || sym1.owner().hasPackageFlag() || ((SymbolTable)this).phase().erasedTypes() || pre1.$eq$colon$eq(pre2);
      } else {
         Names.Name var10000 = sym1.name();
         Names.Name var5 = sym2.name();
         if (var10000 == null) {
            if (var5 != null) {
               return false;
            }
         } else if (!var10000.equals(var5)) {
            return false;
         }

         if (this.isUnifiable(pre1, pre2)) {
            return true;
         } else {
            return false;
         }
      }
   }

   // $FF: synthetic method
   static boolean isDifferentType$(final TypeComparers $this, final Types.Type tp1, final Types.Type tp2) {
      return $this.isDifferentType(tp1, tp2);
   }

   default boolean isDifferentType(final Types.Type tp1, final Types.Type tp2) {
      boolean var10000;
      try {
         this.subsametypeRecursions_$eq(this.subsametypeRecursions() + 1);
         var10000 = BoxesRunTime.unboxToBoolean(((TypeConstraints)this).undoLog().undo((JFunction0.mcZ.sp)() -> !this.isSameType1(tp1, tp2)));
      } finally {
         this.subsametypeRecursions_$eq(this.subsametypeRecursions() - 1);
      }

      return var10000;
   }

   // $FF: synthetic method
   static boolean isDifferentTypeConstructor$(final TypeComparers $this, final Types.Type tp1, final Types.Type tp2) {
      return $this.isDifferentTypeConstructor(tp1, tp2);
   }

   default boolean isDifferentTypeConstructor(final Types.Type tp1, final Types.Type tp2) {
      return !this.isSameTypeConstructor(tp1, tp2);
   }

   private boolean isSameTypeConstructor(final Types.TypeRef tr1, final Types.TypeRef tr2) {
      return tr1.sym() == tr2.sym() && !this.isDifferentType(tr1.pre(), tr2.pre());
   }

   private boolean isSameTypeConstructor(final Types.Type tp1, final Types.Type tp2) {
      return tp1 instanceof Types.TypeRef && tp2 instanceof Types.TypeRef && this.isSameTypeConstructor((Types.TypeRef)tp1, (Types.TypeRef)tp2);
   }

   // $FF: synthetic method
   static boolean isSameType$(final TypeComparers $this, final Types.Type tp1, final Types.Type tp2) {
      return $this.isSameType(tp1, tp2);
   }

   default boolean isSameType(final Types.Type tp1, final Types.Type tp2) {
      boolean var35;
      try {
         MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var25 = MutableSettings$.MODULE$;
         MutableSettings SettingsOps_settings = ((SymbolTable)this).settings();
         MutableSettings var26 = SettingsOps_settings;
         SettingsOps_settings = null;
         MutableSettings areStatisticsEnabled$extension_$this = var26;
         boolean var27 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
         areStatisticsEnabled$extension_$this = null;
         if (var27) {
            Statistics var28 = ((SymbolTable)this).statistics();
            Statistics.Counter incCounter_c = ((TypesStats)((SymbolTable)this).statistics()).sametypeCount();
            if (var28 == null) {
               throw null;
            }

            Statistics incCounter_this = var28;
            MutableSettings.SettingsOps$ var29 = MutableSettings.SettingsOps$.MODULE$;
            MutableSettings$ var30 = MutableSettings$.MODULE$;
            MutableSettings incCounter_enabled_SettingsOps_settings = incCounter_this.scala$reflect$internal$util$Statistics$$settings;
            MutableSettings var31 = incCounter_enabled_SettingsOps_settings;
            incCounter_enabled_SettingsOps_settings = null;
            MutableSettings incCounter_enabled_areStatisticsEnabled$extension_$this = var31;
            boolean var32 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(incCounter_enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
            incCounter_enabled_areStatisticsEnabled$extension_$this = null;
            if (var32 && incCounter_c != null) {
               incCounter_c.value_$eq(incCounter_c.value() + 1);
            }

            Object var20 = null;
            Object var21 = null;
         }

         this.subsametypeRecursions_$eq(this.subsametypeRecursions() + 1);
         List before = ((TypeConstraints)this).undoLog().log();
         boolean result = false;

         try {
            result = this.isSameType1(tp1, tp2);
         } finally {
            var27 = result;
            if (!result) {
               TypeConstraints.UndoLog var34 = ((TypeConstraints)this).undoLog();
               var34.undoTo(before);
            }

         }

         var35 = result;
      } finally {
         this.subsametypeRecursions_$eq(this.subsametypeRecursions() - 1);
      }

      return var35;
   }

   private boolean sameAnnotatedTypes(final Types.Type tp1, final Types.Type tp2) {
      return ((AnnotationCheckers)this).annotationsConform(tp1, tp2) && ((AnnotationCheckers)this).annotationsConform(tp2, tp1) && tp1.withoutAnnotations().$eq$colon$eq(tp2.withoutAnnotations());
   }

   private boolean isSameType1(final Types.Type tp1, final Types.Type tp2) {
      int var3 = this.typeRelationPreCheck(tp1, tp2);
      if (TriState$.MODULE$.isKnown$extension(var3)) {
         return TriState$.MODULE$.booleanValue$extension(var3);
      } else {
         return !(tp1 instanceof Types.AnnotatedType) && !(tp2 instanceof Types.AnnotatedType) ? this.isSameType2(tp1, tp2) : this.sameAnnotatedTypes(tp1, tp2);
      }
   }

   private boolean isSameHKTypes(final Types.Type tp1, final Types.Type tp2) {
      return tp1.isHigherKinded() && tp2.isHigherKinded() && tp1.normalize().$eq$colon$eq(tp2.normalize());
   }

   private boolean isSameTypeRef(final Types.TypeRef tr1, final Types.TypeRef tr2) {
      if ((tr1 != ((Definitions)this).definitions().ObjectTpeJava() || tr2.sym() != ((Definitions)this).definitions().AnyClass()) && (tr2 != ((Definitions)this).definitions().ObjectTpeJava() || tr1.sym() != ((Definitions)this).definitions().AnyClass())) {
         return this.equalSymsAndPrefixes(tr1.sym(), tr1.pre(), tr2.sym(), tr2.pre()) && (this.isSameHKTypes(tr1, tr2) || ((Types)this).isSameTypes(tr1.args(), tr2.args()));
      } else {
         return true;
      }
   }

   private boolean isSameSingletonType(final Types.SingletonType tp1, final Types.SingletonType tp2) {
      Types.Type origin1 = this.chaseDealiasedUnderlying$1(tp1);
      Types.Type origin2 = this.chaseDealiasedUnderlying$1(tp2);
      return (origin1 != tp1 || origin2 != tp2) && origin1.$eq$colon$eq(origin2);
   }

   private boolean isSameMethodType(final Types.MethodType mt1, final Types.MethodType mt2) {
      return ((Types)this).isSameSymbolTypes(mt1.params(), mt2.params()) && mt1.resultType().$eq$colon$eq(mt2.resultType().substSym(mt2.params(), mt1.params())) && mt1.isImplicit() == mt2.isImplicit();
   }

   private boolean equalTypeParamsAndResult(final List tparams1, final Types.Type res1, final List tparams2, final Types.Type res2) {
      if (((Collections)this).sameLength(tparams1, tparams2)) {
         TypeMaps.SubstSymMap substMap = ((TypeMaps)this).SubstSymMap().apply(tparams2, tparams1);
         if (tparams1.corresponds(tparams2, (p1, p2) -> BoxesRunTime.boxToBoolean($anonfun$equalTypeParamsAndResult$1(this, substMap, p1, p2))) && res1.$eq$colon$eq(substMap.apply(res2))) {
            return true;
         }
      }

      return false;
   }

   private boolean methodHigherOrderTypeParamsSameVariance(final Symbols.Symbol sym1, final Symbols.Symbol sym2) {
      return ignoreVariance$1(sym1) || ignoreVariance$1(sym2) || sym1.variance() == sym2.variance();
   }

   private boolean methodHigherOrderTypeParamsSubVariance(final Symbols.Symbol low, final Symbols.Symbol high) {
      return this.methodHigherOrderTypeParamsSameVariance(low, high) || Variance$.MODULE$.isInvariant$extension(low.variance());
   }

   // $FF: synthetic method
   static boolean isSameType2$(final TypeComparers $this, final Types.Type tp1, final Types.Type tp2) {
      return $this.isSameType2(tp1, tp2);
   }

   default boolean isSameType2(final Types.Type tp1, final Types.Type tp2) {
      return this.sameTypeAndSameCaseClass$1(tp1, tp2) || this.sameSingletonType$1(tp1, tp2) || mutateNonTypeConstructs$1(tp1, tp2, tp1) || mutateNonTypeConstructs$1(tp2, tp1, tp1) || this.retry$1(tp1, tp2);
   }

   // $FF: synthetic method
   static boolean isSubType$(final TypeComparers $this, final Types.Type tp1, final Types.Type tp2, final int depth) {
      return $this.isSubType(tp1, tp2, depth);
   }

   default boolean isSubType(final Types.Type tp1, final Types.Type tp2, final int depth) {
      boolean var26;
      try {
         this.subsametypeRecursions_$eq(this.subsametypeRecursions() + 1);
         List before = ((TypeConstraints)this).undoLog().log();
         boolean result;
         var26 = result = false;

         try {
            if (this.subsametypeRecursions() >= 50) {
               SubTypePair p = (SymbolTable)this.new SubTypePair(tp1, tp2);
               HashSet var22 = this.pendingSubTypes();
               if (var22 == null) {
                  throw null;
               }

               boolean var23 = var22.contains(p);
               if (var23) {
                  var26 = false;
               } else {
                  try {
                     HashSet var25 = this.pendingSubTypes();
                     if (var25 == null) {
                        throw null;
                     }

                     var25.add(p);
                     var26 = this.isSubType1(tp1, tp2, depth);
                  } finally {
                     HashSet var10001 = this.pendingSubTypes();
                     if (var10001 == null) {
                        throw null;
                     }

                     var10001.remove(p);
                  }
               }
            } else {
               var26 = this.isSubType1(tp1, tp2, depth);
            }

            result = var26;
         } finally {
            if (!result) {
               ((TypeConstraints)this).undoLog().undoTo(before);
            }

         }

         var26 = result;
      } finally {
         this.subsametypeRecursions_$eq(this.subsametypeRecursions() - 1);
      }

      return var26;
   }

   // $FF: synthetic method
   static int isSubType$default$3$(final TypeComparers $this) {
      return $this.isSubType$default$3();
   }

   default int isSubType$default$3() {
      return Depth$.MODULE$.AnyDepth();
   }

   private int typeRelationPreCheck(final Types.Type tp1, final Types.Type tp2) {
      if (this.isTrue$1(tp1, tp2)) {
         return TriState$.MODULE$.True();
      } else {
         return this.isFalse$1(tp1, tp2) ? TriState$.MODULE$.False() : TriState$.MODULE$.Unknown();
      }
   }

   private boolean isSubType1(final Types.Type tp1, final Types.Type tp2, final int depth) {
      int var4 = this.typeRelationPreCheck(tp1, tp2);
      if (TriState$.MODULE$.isKnown$extension(var4)) {
         return TriState$.MODULE$.booleanValue$extension(var4);
      } else if (!(tp1 instanceof Types.AnnotatedType) && !(tp2 instanceof Types.AnnotatedType)) {
         return this.isSubType2(tp1, tp2, depth);
      } else {
         return ((AnnotationCheckers)this).annotationsConform(tp1, tp2) && tp1.withoutAnnotations().$less$colon$less(tp2.withoutAnnotations());
      }
   }

   private boolean isPolySubType(final Types.PolyType tp1, final Types.PolyType tp2) {
      if (tp1 != null) {
         List tparams1 = tp1.typeParams();
         Types.Type res1 = tp1.resultType();
         if (tp2 != null) {
            List tparams2 = tp2.typeParams();
            Types.Type res2 = tp2.resultType();
            if (((Collections)this).sameLength(tparams1, tparams2) && tparams2.corresponds(tparams1, (low, high) -> BoxesRunTime.boxToBoolean($anonfun$isPolySubType$1(this, low, high)))) {
               boolean isMethod = ((Symbols.Symbol)tparams1.head()).owner().isMethod();
               List substitutes = isMethod ? tparams1 : ((Symbols)this).cloneSymbols(tparams1);
               Function1 sub1 = (Function1)(isMethod ? (tp) -> tp : ((TypeMaps)this).SubstSymMap().apply(tparams1, substitutes));
               Function1 sub2 = ((TypeMaps)this).SubstSymMap().apply(tparams2, substitutes);
               if (tparams1.corresponds(tparams2, (p1, p2) -> BoxesRunTime.boxToBoolean($anonfun$isPolySubType$3(sub2, sub1, p1, p2))) && ((Types.Type)sub1.apply(res1)).$less$colon$less((Types.Type)sub2.apply(res2))) {
                  return true;
               }
            }

            return false;
         } else {
            throw new MatchError((Object)null);
         }
      } else {
         throw new MatchError((Object)null);
      }
   }

   private boolean isThisAndSuperSubtype(final Types.Type tp1, final Types.Type tp2) {
      if (tp1 instanceof Types.SingleType) {
         Types.SingleType var3 = (Types.SingleType)tp1;
         Types.Type var4 = var3.pre();
         Symbols.Symbol v1 = var3.sym();
         if (var4 instanceof Types.ThisType) {
            Symbols.Symbol lpre = ((Types.ThisType)var4).sym();
            if (tp2 instanceof Types.SingleType) {
               Types.SingleType var7 = (Types.SingleType)tp2;
               Types.Type var8 = var7.pre();
               Symbols.Symbol v2 = var7.sym();
               if (var8 instanceof Types.SuperType) {
                  Types.Type var10 = ((Types.SuperType)var8).thistpe();
                  if (var10 instanceof Types.ThisType) {
                     Symbols.Symbol rpre = ((Types.ThisType)var10).sym();
                     if (lpre == rpre && v1.overrideChain().contains(v2)) {
                        return true;
                     }

                     return false;
                  }
               }
            }
         }
      }

      return false;
   }

   private boolean isNoArgStaticClassTypeRef(final Types.Type tp) {
      if (tp instanceof Types.TypeRef) {
         Types.TypeRef var2 = (Types.TypeRef)tp;
         Symbols.Symbol sym = var2.sym();
         List var4 = var2.args();
         if (scala.collection.immutable.Nil..MODULE$.equals(var4) && sym.isClass() && sym.isStatic()) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   static boolean isHKSubType$(final TypeComparers $this, final Types.Type tp1, final Types.Type tp2, final int depth) {
      return $this.isHKSubType(tp1, tp2, depth);
   }

   default boolean isHKSubType(final Types.Type tp1, final Types.Type tp2, final int depth) {
      if (tp1.typeSymbol() != ((Definitions)this).definitions().NothingClass() && tp2.typeSymbol() != ((Definitions)this).definitions().AnyClass()) {
         if (this.isNoArgStaticClassTypeRef(tp1) && this.isNoArgStaticClassTypeRef(tp2)) {
            if (tp1.typeSymbolDirect().isNonBottomSubClass(tp2.typeSymbolDirect())) {
               return true;
            }
         } else if (this.isSub$1(tp1.withoutAnnotations(), tp2.withoutAnnotations(), depth, tp1, tp2) && ((AnnotationCheckers)this).annotationsConform(tp1, tp2)) {
            return true;
         }

         return false;
      } else {
         return true;
      }
   }

   private boolean isSubType2(final Types.Type tp1, final Types.Type tp2, final int depth) {
      if (tp1 instanceof Types.SingletonType && tp2 instanceof Types.SingletonType) {
         return tp1.$eq$colon$eq(tp2) || this.isThisAndSuperSubtype(tp1, tp2) || this.retry$2(tp1.underlying(), tp2, tp1, tp2, depth);
      } else {
         return !tp1.isHigherKinded() && !tp2.isHigherKinded() ? this.firstTry$1(tp2, tp1, depth) : this.isHKSubType(tp1, tp2, depth);
      }
   }

   // $FF: synthetic method
   static boolean isWeakSubType$(final TypeComparers $this, final Types.Type tp1, final Types.Type tp2) {
      return $this.isWeakSubType(tp1, tp2);
   }

   default boolean isWeakSubType(final Types.Type tp1, final Types.Type tp2) {
      Types.Type var3 = tp1.dealiasWiden();
      if (var3 instanceof Types.TypeRef) {
         Symbols.Symbol sym1 = ((Types.TypeRef)var3).sym();
         Definitions.definitions$ var10000 = ((Definitions)this).definitions();
         if (var10000 == null) {
            throw null;
         }

         if (Definitions.ValueClassDefinitions.isNumericValueClass$(var10000, sym1)) {
            Types.Type var5 = tp2.deconst().dealias();
            if (var5 instanceof Types.TypeRef) {
               Symbols.Symbol sym2 = ((Types.TypeRef)var5).sym();
               var10000 = ((Definitions)this).definitions();
               if (var10000 == null) {
                  throw null;
               }

               if (Definitions.ValueClassDefinitions.isNumericValueClass$(var10000, sym2)) {
                  var10000 = ((Definitions)this).definitions();
                  if (var10000 == null) {
                     throw null;
                  }

                  return Definitions.ValueClassDefinitions.isNumericSubClass$(var10000, sym1, sym2);
               }
            }

            if (var5 instanceof Types.TypeVar) {
               return ((Types.TypeVar)var5).registerBound(tp1, true, true);
            }

            return this.isSubType(tp1, tp2, this.isSubType$default$3());
         }
      }

      if (var3 instanceof Types.TypeVar) {
         Types.TypeVar var7 = (Types.TypeVar)var3;
         Types.Type var8 = tp2.deconst().dealias();
         if (var8 instanceof Types.TypeRef) {
            Symbols.Symbol sym2 = ((Types.TypeRef)var8).sym();
            Definitions.definitions$ var10 = ((Definitions)this).definitions();
            if (var10 == null) {
               throw null;
            }

            if (Definitions.ValueClassDefinitions.isNumericValueClass$(var10, sym2)) {
               return var7.registerBound(tp2, false, true);
            }
         }

         return this.isSubType(tp1, tp2, this.isSubType$default$3());
      } else {
         return this.isSubType(tp1, tp2, this.isSubType$default$3());
      }
   }

   // $FF: synthetic method
   static boolean isNumericSubType$(final TypeComparers $this, final Types.Type tp1, final Types.Type tp2) {
      return $this.isNumericSubType(tp1, tp2);
   }

   default boolean isNumericSubType(final Types.Type tp1, final Types.Type tp2) {
      Definitions.definitions$ var10000 = ((Definitions)this).definitions();
      Symbols.Symbol var10001 = this.primitiveBaseClass(tp1.dealiasWiden());
      Symbols.Symbol isNumericSubClass_sup = this.primitiveBaseClass(tp2.dealias());
      Symbols.Symbol isNumericSubClass_sub = var10001;
      if (var10000 == null) {
         throw null;
      } else {
         return Definitions.ValueClassDefinitions.isNumericSubClass$(var10000, isNumericSubClass_sub, isNumericSubClass_sup);
      }
   }

   private Symbols.Symbol primitiveBaseClass(final Types.Type tp) {
      return this.loop$1(tp.baseClasses());
   }

   private Types.Type chaseDealiasedUnderlying$1(final Types.Type tp) {
      while(true) {
         Types.Type var2 = tp.underlying().dealias();
         if (var2 instanceof Types.SingletonType) {
            Types.SingletonType var3 = (Types.SingletonType)var2;
            if (tp != var3) {
               SymbolTable var10000 = (SymbolTable)this;
               tp = var3;
               this = var10000;
               continue;
            }
         }

         return tp;
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$equalTypeParamsAndResult$1(final TypeComparers $this, final TypeMaps.SubstSymMap substMap$1, final Symbols.Symbol p1, final Symbols.Symbol p2) {
      return $this.methodHigherOrderTypeParamsSameVariance(p1, p2) && p1.info().$eq$colon$eq(substMap$1.apply(p2.info()));
   }

   private static boolean ignoreVariance$1(final Symbols.Symbol sym) {
      return !sym.isHigherOrderTypeParameter() || !sym.logicallyEnclosingMember().isMethod();
   }

   private boolean retry$1(final Types.Type tp1$2, final Types.Type tp2$2) {
      if (!this.isNoArgStaticClassTypeRef(tp1$2) || !this.isNoArgStaticClassTypeRef(tp2$2)) {
         Types.Type lhs = ((Types)this).normalizePlus(tp1$2);
         Types.Type rhs = ((Types)this).normalizePlus(tp2$2);
         if ((lhs != tp1$2 || rhs != tp2$2) && this.isSameType(lhs, rhs)) {
            return true;
         }
      }

      return false;
   }

   private static boolean mutateNonTypeConstructs$1(final Types.Type lhs, final Types.Type rhs, final Types.Type tp1$2) {
      if (lhs instanceof Types.ProtoType) {
         return ((Types.ProtoType)lhs).registerTypeEquality(rhs);
      } else if (lhs instanceof Types.TypeVar) {
         return ((Types.TypeVar)lhs).registerTypeEquality(rhs, lhs == tp1$2);
      } else {
         if (lhs instanceof Types.TypeRef) {
            Types.TypeRef var3 = (Types.TypeRef)lhs;
            Types.Type tv = var3.pre();
            Symbols.Symbol sym = var3.sym();
            if (tv instanceof Types.TypeVar) {
               return ((Types.TypeVar)tv).registerTypeSelection(sym, rhs);
            }
         }

         return false;
      }
   }

   private boolean sameSingletonType$1(final Types.Type tp1$2, final Types.Type tp2$2) {
      if (tp1$2 instanceof Types.SingletonType) {
         Types.SingletonType var3 = (Types.SingletonType)tp1$2;
         if (tp2$2 instanceof Types.SingletonType) {
            Types.SingletonType var4 = (Types.SingletonType)tp2$2;
            return this.isSameSingletonType(var3, var4);
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean sameTypeAndSameCaseClass$1(final Types.Type tp1$2, final Types.Type tp2$2) {
      if (tp1$2 instanceof Types.TypeRef) {
         Types.TypeRef var3 = (Types.TypeRef)tp1$2;
         if (tp2$2 instanceof Types.TypeRef) {
            Types.TypeRef var4 = (Types.TypeRef)tp2$2;
            return this.isSameTypeRef(var3, var4);
         } else {
            return false;
         }
      } else if (tp1$2 instanceof Types.MethodType) {
         Types.MethodType var5 = (Types.MethodType)tp1$2;
         if (tp2$2 instanceof Types.MethodType) {
            Types.MethodType var6 = (Types.MethodType)tp2$2;
            return this.isSameMethodType(var5, var6);
         } else {
            return false;
         }
      } else if (tp1$2 instanceof Types.RefinedType) {
         Types.RefinedType var7 = (Types.RefinedType)tp1$2;
         List ps1 = var7.parents();
         Scopes.Scope decls1 = var7.decls();
         if (tp2$2 instanceof Types.RefinedType) {
            Types.RefinedType var10 = (Types.RefinedType)tp2$2;
            List ps2 = var10.parents();
            Scopes.Scope decls2 = var10.decls();
            return ((Types)this).isSameTypes(ps1, ps2) && decls1.isSameScope(decls2);
         } else {
            return false;
         }
      } else if (tp1$2 instanceof Types.SingleType) {
         Types.SingleType var13 = (Types.SingleType)tp1$2;
         Types.Type pre1 = var13.pre();
         Symbols.Symbol sym1 = var13.sym();
         if (tp2$2 instanceof Types.SingleType) {
            Types.SingleType var16 = (Types.SingleType)tp2$2;
            Types.Type pre2 = var16.pre();
            Symbols.Symbol sym2 = var16.sym();
            return this.equalSymsAndPrefixes(sym1, pre1, sym2, pre2);
         } else {
            return false;
         }
      } else if (tp1$2 instanceof Types.PolyType) {
         Types.PolyType var19 = (Types.PolyType)tp1$2;
         List ps1 = var19.typeParams();
         Types.Type res1 = var19.resultType();
         if (tp2$2 instanceof Types.PolyType) {
            Types.PolyType var22 = (Types.PolyType)tp2$2;
            List ps2 = var22.typeParams();
            Types.Type res2 = var22.resultType();
            return this.equalTypeParamsAndResult(ps1, res1, ps2, res2);
         } else {
            return false;
         }
      } else if (tp1$2 instanceof Types.ExistentialType) {
         Types.ExistentialType var25 = (Types.ExistentialType)tp1$2;
         List qs1 = var25.quantified();
         Types.Type res1 = var25.underlying();
         if (tp2$2 instanceof Types.ExistentialType) {
            Types.ExistentialType var28 = (Types.ExistentialType)tp2$2;
            List qs2 = var28.quantified();
            Types.Type res2 = var28.underlying();
            return this.equalTypeParamsAndResult(qs1, res1, qs2, res2);
         } else {
            return false;
         }
      } else if (tp1$2 instanceof Types.ThisType) {
         Symbols.Symbol sym1 = ((Types.ThisType)tp1$2).sym();
         if (tp2$2 instanceof Types.ThisType) {
            Symbols.Symbol sym2 = ((Types.ThisType)tp2$2).sym();
            return sym1 == sym2;
         } else {
            return false;
         }
      } else if (tp1$2 instanceof Types.FoldableConstantType) {
         Constants.Constant c1 = ((Types.FoldableConstantType)tp1$2).value();
         if (!(tp2$2 instanceof Types.FoldableConstantType)) {
            return false;
         } else {
            Constants.Constant c2 = ((Types.FoldableConstantType)tp2$2).value();
            if (c1 == null) {
               if (c2 == null) {
                  return true;
               }
            } else if (c1.equals(c2)) {
               return true;
            }

            return false;
         }
      } else if (tp1$2 instanceof Types.LiteralType) {
         Constants.Constant c1 = ((Types.LiteralType)tp1$2).value();
         if (!(tp2$2 instanceof Types.LiteralType)) {
            return false;
         } else {
            Constants.Constant c2 = ((Types.LiteralType)tp2$2).value();
            if (c1 == null) {
               if (c2 == null) {
                  return true;
               }
            } else if (c1.equals(c2)) {
               return true;
            }

            return false;
         }
      } else if (tp1$2 instanceof Types.NullaryMethodType) {
         Types.Type res1 = ((Types.NullaryMethodType)tp1$2).resultType();
         if (tp2$2 instanceof Types.NullaryMethodType) {
            Types.Type res2 = ((Types.NullaryMethodType)tp2$2).resultType();
            return res1.$eq$colon$eq(res2);
         } else {
            return false;
         }
      } else if (tp1$2 instanceof Types.TypeBounds) {
         Types.TypeBounds var39 = (Types.TypeBounds)tp1$2;
         Types.Type lo1 = var39.lo();
         Types.Type hi1 = var39.hi();
         if (tp2$2 instanceof Types.TypeBounds) {
            Types.TypeBounds var42 = (Types.TypeBounds)tp2$2;
            Types.Type lo2 = var42.lo();
            Types.Type hi2 = var42.hi();
            return lo1.$eq$colon$eq(lo2) && hi1.$eq$colon$eq(hi2);
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean isTrue$1(final Types.Type tp1$3, final Types.Type tp2$3) {
      return tp1$3 == tp2$3 || ((Types)this).isErrorOrWildcard(tp1$3) || ((Types)this).isErrorOrWildcard(tp2$3) || tp1$3 == ((Types)this).NoPrefix() && tp2$3.typeSymbol().isPackageClass() || tp2$3 == ((Types)this).NoPrefix() && tp1$3.typeSymbol().isPackageClass();
   }

   private boolean isFalse$1(final Types.Type tp1$3, final Types.Type tp2$3) {
      return tp1$3 == ((Types)this).NoType() || tp2$3 == ((Types)this).NoType() || tp1$3 == ((Types)this).NoPrefix() || tp2$3 == ((Types)this).NoPrefix();
   }

   // $FF: synthetic method
   static boolean $anonfun$isPolySubType$1(final TypeComparers $this, final Symbols.Symbol low, final Symbols.Symbol high) {
      return $this.methodHigherOrderTypeParamsSubVariance(low, high);
   }

   private static boolean cmp$1(final Symbols.Symbol p1, final Symbols.Symbol p2, final Function1 sub2$1, final Function1 sub1$1) {
      return ((Types.Type)sub2$1.apply(p2.info())).$less$colon$less((Types.Type)sub1$1.apply(p1.info()));
   }

   // $FF: synthetic method
   static boolean $anonfun$isPolySubType$3(final Function1 sub2$1, final Function1 sub1$1, final Symbols.Symbol p1, final Symbols.Symbol p2) {
      return cmp$1(p1, p2, sub2$1, sub1$1);
   }

   // $FF: synthetic method
   static boolean $anonfun$isHKSubType$1(final TypeComparers $this, final Symbols.Symbol low, final Symbols.Symbol high) {
      return $this.methodHigherOrderTypeParamsSubVariance(low, high);
   }

   private boolean hkSubVariance$1(final List tparams1, final List tparams2) {
      return tparams1.corresponds(tparams2, (low, high) -> BoxesRunTime.boxToBoolean($anonfun$isHKSubType$1(this, low, high)));
   }

   private boolean isSubHKTypeVar$1(final Types.Type tp1, final Types.Type tp2) {
      if (tp1 instanceof Types.TypeVar) {
         Types.TypeVar var3 = (Types.TypeVar)tp1;
         if (tp2 instanceof Types.TypeVar) {
            Types.TypeVar var4 = (Types.TypeVar)tp2;
            ((SymbolTable)this).devWarning(() -> {
               package$ var10000 = package$.MODULE$;
               StringContext StringContextStripMarginOps_stringContext = new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new String[]{"Unexpected code path: testing two type variables for subtype relation: ", " <:< ", ""}));
               package.StringContextStripMarginOps var4x = new package.StringContextStripMarginOps(StringContextStripMarginOps_stringContext);
               Object var3x = null;
               return StripMarginInterpolator.sm$(var4x, scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{var3, var4}));
            });
            if (var3 == var4) {
               return true;
            }

            return false;
         }
      }

      if (tp2 instanceof Types.TypeVar) {
         Types.TypeVar var5 = (Types.TypeVar)tp2;
         Types.Type ntp1 = tp1.normalize();
         boolean kindsMatch = ntp1.typeSymbol() == ((Definitions)this).definitions().AnyClass() || this.hkSubVariance$1(var5.params(), ntp1.typeParams());
         if (kindsMatch) {
            var5.addLoBound(ntp1, var5.addLoBound$default$2());
         }

         return kindsMatch;
      } else if (!(tp1 instanceof Types.TypeVar)) {
         return false;
      } else {
         Types.TypeVar var8 = (Types.TypeVar)tp1;
         Types.Type ntp2 = tp2.normalize();
         boolean kindsMatch = ntp2.typeSymbol() == ((Definitions)this).definitions().NothingClass() || this.hkSubVariance$1(ntp2.typeParams(), var8.params());
         if (kindsMatch) {
            var8.addHiBound(ntp2, var8.addHiBound$default$2());
         }

         return kindsMatch;
      }
   }

   private boolean isSub$1(final Types.Type tp1, final Types.Type tp2, final int depth$1, final Types.Type tp1$4, final Types.Type tp2$4) {
      return this.isSubHKTypeVar$1(tp1, tp2) || this.isSub2$1(tp1.normalize(), tp2.normalize(), depth$1, tp1$4, tp2$4);
   }

   // $FF: synthetic method
   static boolean $anonfun$isHKSubType$3(final Symbols.Symbol x$3) {
      return x$3.tpe_$times().isWildcard();
   }

   // $FF: synthetic method
   static boolean $anonfun$isHKSubType$4(final TypeComparers $this, final Types.Type ntp1$1, final int depth$1, final Types.Type x$4) {
      return $this.isSubType(ntp1$1, x$4, depth$1);
   }

   private static String tp_s$1(final Types.Type tp) {
      return scala.collection.StringOps..MODULE$.format$extension("%-20s %s", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{tp, package$.MODULE$.shortClassOfInstance(tp)}));
   }

   private boolean isSub2$1(final Types.Type ntp1, final Types.Type ntp2, final int depth$1, final Types.Type tp1$4, final Types.Type tp2$4) {
      if (ntp1 instanceof Types.PolyType) {
         Types.PolyType var6 = (Types.PolyType)ntp1;
         if (ntp2 instanceof Types.PolyType) {
            Types.PolyType var7 = (Types.PolyType)ntp2;
            return this.isPolySubType(var6, var7);
         }
      }

      if (((Types)this).WildcardType().equals(ntp1) ? true : ((Types)this).WildcardType().equals(ntp2)) {
         return true;
      } else {
         boolean var17;
         label180: {
            label186: {
               if (ntp1 instanceof Types.TypeRef) {
                  Symbols.Symbol var8 = ((Types.TypeRef)ntp1).sym();
                  Symbols.ClassSymbol var10000 = ((Definitions)this).definitions().AnyClass();
                  if (var10000 == null) {
                     if (var8 == null) {
                        break label186;
                     }
                  } else if (var10000.equals(var8)) {
                     break label186;
                  }
               }

               if (ntp2 instanceof Types.TypeRef) {
                  Symbols.Symbol var9 = ((Types.TypeRef)ntp2).sym();
                  if (((Definitions)this).definitions().NothingClass().equals(var9)) {
                     var17 = true;
                     break label180;
                  }
               }

               var17 = false;
               break label180;
            }

            var17 = true;
         }

         if (var17) {
            return false;
         } else {
            if (ntp1 instanceof Types.PolyType && ntp2 instanceof Types.MethodType) {
               List ps = ((Types.MethodType)ntp2).params();
               if (ps == null) {
                  throw null;
               }

               List exists_these = ps;

               while(true) {
                  if (exists_these.isEmpty()) {
                     var17 = false;
                     break;
                  }

                  if ($anonfun$isHKSubType$3((Symbols.Symbol)exists_these.head())) {
                     var17 = true;
                     break;
                  }

                  exists_these = (List)exists_these.tail();
               }

               Object var16 = null;
               if (var17) {
                  return false;
               }
            }

            if (ntp2 instanceof Types.ExistentialType) {
               return ((Types.ExistentialType)ntp2).withTypeVars((x$4) -> BoxesRunTime.boxToBoolean($anonfun$isHKSubType$4(this, ntp1, depth$1, x$4)), depth$1);
            } else if (ntp1 instanceof Types.ExistentialType) {
               Types.ExistentialType var11 = (Types.ExistentialType)ntp1;

               try {
                  ((Types)this).skolemizationLevel_$eq(((Types)this).skolemizationLevel() + 1);
                  var17 = this.isSubType(var11.skolemizeExistential(), ntp2, depth$1);
               } finally {
                  ((Types)this).skolemizationLevel_$eq(((Types)this).skolemizationLevel() - 1);
               }

               return var17;
            } else {
               ((SymbolTable)this).devWarning(() -> (new StringBuilder(79)).append("HK subtype check on ").append(tp1$4).append(" and ").append(tp2$4).append(", but both don't normalize to polytypes:\n  tp1=").append(tp_s$1(ntp1)).append("\n  tp2=").append(tp_s$1(ntp2)).toString());
               return false;
            }
         }
      }
   }

   private boolean retry$2(final Types.Type lhs, final Types.Type rhs, final Types.Type tp1$5, final Types.Type tp2$5, final int depth$2) {
      return (lhs != tp1$5 || rhs != tp2$5) && this.isSubType(lhs, rhs, depth$2);
   }

   private boolean firstTry$1(final Types.Type tp2$5, final Types.Type tp1$5, final int depth$2) {
      if (tp2$5 instanceof Types.TypeRef) {
         Types.TypeRef var4 = (Types.TypeRef)tp2$5;
         if (!(tp1$5 instanceof Types.TypeRef)) {
            return this.secondTry$1(tp1$5, tp2$5, depth$2);
         } else {
            Types.TypeRef var5;
            Symbols.Symbol sym2;
            label93: {
               var5 = (Types.TypeRef)tp1$5;
               Symbols.Symbol sym1 = var5.sym();
               sym2 = (Symbols.Symbol)(!((SymbolTable)this).phase().erasedTypes() && var4 == ((Definitions)this).definitions().ObjectTpeJava() ? ((Definitions)this).definitions().AnyClass() : var4.sym());
               Types.Type pre1 = var5.pre();
               Types.Type pre2 = var4.pre();
               if (sym1 == sym2) {
                  if (!((SymbolTable)this).phase().erasedTypes() && !sym1.rawowner().hasPackageFlag() && !this.isSubType(pre1, pre2, depth$2)) {
                     break label93;
                  }
               } else {
                  Names.Name var10000 = sym1.name();
                  Names.Name var10 = sym2.name();
                  if (var10000 == null) {
                     if (var10 != null) {
                        break label93;
                     }
                  } else if (!var10000.equals(var10)) {
                     break label93;
                  }

                  if (sym1.isModuleClass() || sym2.isModuleClass() || !this.isUnifiable(pre1, pre2) && !this.isSameSpecializedSkolem(sym1, sym2, pre1, pre2) && (!sym2.isAbstractType() || !this.isSubPre(pre1, pre2, sym2))) {
                     break label93;
                  }
               }

               if (((Types)this).isSubArgs(var5.args(), var4.args(), sym1.typeParams(), depth$2)) {
                  return true;
               }
            }

            if (sym2.isClass()) {
               Types.Type base = var5.baseType(sym2);
               if (base != var5 && base != ((Types)this).NoType() && this.isSubType(base, var4, depth$2)) {
                  return true;
               }
            }

            if (!this.thirdTryRef$1(var5, var4, depth$2, tp1$5, tp2$5)) {
               return false;
            } else {
               return true;
            }
         }
      } else if (tp2$5 instanceof Types.AnnotatedType) {
         return this.isSubType(tp1$5.withoutAnnotations(), tp2$5.withoutAnnotations(), depth$2) && ((AnnotationCheckers)this).annotationsConform(tp1$5, tp2$5);
      } else if (tp2$5 instanceof Types.ProtoType) {
         return ((Types.ProtoType)tp2$5).isMatchedBy(tp1$5, depth$2);
      } else if (tp2$5 instanceof Types.TypeVar) {
         Types.TypeVar var12 = (Types.TypeVar)tp2$5;
         return (tp1$5 instanceof Types.AnnotatedType ? true : tp1$5 instanceof Types.ProtoType) ? this.secondTry$1(tp1$5, tp2$5, depth$2) : var12.registerBound(tp1$5, true, var12.registerBound$default$3());
      } else {
         return this.secondTry$1(tp1$5, tp2$5, depth$2);
      }
   }

   private boolean secondTry$1(final Types.Type tp1$5, final Types.Type tp2$5, final int depth$2) {
      if (tp1$5 instanceof Types.ProtoType) {
         return ((Types.ProtoType)tp1$5).canMatch(tp2$5, depth$2);
      } else if (tp1$5 instanceof Types.AnnotatedType) {
         return this.isSubType(tp1$5.withoutAnnotations(), tp2$5.withoutAnnotations(), depth$2) && ((AnnotationCheckers)this).annotationsConform(tp1$5, tp2$5);
      } else if (tp1$5 instanceof Types.TypeVar) {
         Types.TypeVar var4 = (Types.TypeVar)tp1$5;
         return var4.registerBound(tp2$5, false, var4.registerBound$default$3());
      } else if (tp1$5 instanceof Types.ExistentialType) {
         boolean var10000;
         try {
            ((Types)this).skolemizationLevel_$eq(((Types)this).skolemizationLevel() + 1);
            var10000 = this.isSubType(tp1$5.skolemizeExistential(), tp2$5, depth$2);
         } finally {
            ((Types)this).skolemizationLevel_$eq(((Types)this).skolemizationLevel() - 1);
         }

         return var10000;
      } else {
         return this.thirdTry$1(tp2$5, tp1$5, depth$2);
      }
   }

   private boolean retry$3(final Types.Type lhs, final Types.Type rhs, final int depth$2) {
      return this.isSubType(lhs, rhs, depth$2);
   }

   private boolean abstractTypeOnRight$1(final Types.Type lo, final Types.TypeRef tp2$6, final Types.Type tp1$6, final int depth$2) {
      return this.isDifferentTypeConstructor(tp2$6, lo) && this.isSubType(tp1$6, lo, depth$2);
   }

   private boolean classOnRight$1(final Types.TypeRef tp2$6, final Types.Type tp1$6, final Symbols.Symbol sym2$1, final int depth$2, final Types.Type tp1$5, final Types.Type tp2$5) {
      if (((Types)this).isRawType(tp2$6)) {
         Types.Type retry$3_rhs = ((TypeMaps)this).rawToExistential().apply(tp2$6);
         return this.isSubType(tp1$6, retry$3_rhs, depth$2);
      } else if (sym2$1.isRefinementClass()) {
         Types.Type retry$3_rhs = sym2$1.info();
         return this.isSubType(tp1$6, retry$3_rhs, depth$2);
      } else {
         return this.fourthTry$1(tp1$5, tp2$5, depth$2);
      }
   }

   private boolean thirdTryRef$1(final Types.Type tp1, final Types.TypeRef tp2, final int depth$2, final Types.Type tp1$5, final Types.Type tp2$5) {
      Symbols.Symbol sym2 = tp2.sym();
      boolean var7 = false;
      Symbols.ClassSymbol var10000 = ((Definitions)this).definitions().SingletonClass();
      if (var10000 == null) {
         if (sym2 == null) {
            return tp1.isStable() || this.fourthTry$1(tp1$5, tp2$5, depth$2);
         }
      } else if (var10000.equals(sym2)) {
         return tp1.isStable() || this.fourthTry$1(tp1$5, tp2$5, depth$2);
      }

      if (sym2 instanceof Symbols.ClassSymbol) {
         return this.classOnRight$1(tp2, tp1, sym2, depth$2, tp1$5, tp2$5);
      } else {
         if (sym2 instanceof Symbols.TypeSymbol) {
            var7 = true;
            var10000 = (Symbols.TypeSymbol)sym2;
            if (sym2.isDeferred()) {
               if (!this.abstractTypeOnRight$1(tp2.lowerBound(), tp2, tp1, depth$2) && !this.fourthTry$1(tp1$5, tp2$5, depth$2)) {
                  return false;
               }

               return true;
            }
         }

         if (var7) {
            Types.Type var11 = ((Types)this).normalizePlus(tp1);
            Types.Type retry$3_rhs = ((Types)this).normalizePlus(tp2);
            Types.Type retry$3_lhs = var11;
            return this.isSubType(retry$3_lhs, retry$3_rhs, depth$2);
         } else {
            return this.fourthTry$1(tp1$5, tp2$5, depth$2);
         }
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$isSubType2$1(final TypeComparers $this, final Types.Type tp1$5, final int depth$2, final Types.Type x$5) {
      return $this.isSubType(tp1$5, x$5, depth$2);
   }

   // $FF: synthetic method
   static boolean $anonfun$isSubType2$2(final TypeComparers $this, final Types.Type tp1$5, final int depth$2, final Symbols.Symbol x$6) {
      return ((Types)$this).specializesSym(tp1$5, x$6, depth$2);
   }

   // $FF: synthetic method
   static boolean $anonfun$isSubType2$3(final TypeComparers $this, final Types.Type tp1$5, final int depth$2, final Types.Type x$7) {
      return $this.isSubType(tp1$5, x$7, depth$2);
   }

   private boolean thirdTry$1(final Types.Type tp2$5, final Types.Type tp1$5, final int depth$2) {
      if (tp2$5 instanceof Types.TypeRef) {
         Types.TypeRef var4 = (Types.TypeRef)tp2$5;
         return this.thirdTryRef$1(tp1$5, var4, depth$2, tp1$5, tp2$5);
      } else if (!(tp2$5 instanceof Types.RefinedType)) {
         if (tp2$5 instanceof Types.ExistentialType) {
            return ((Types.ExistentialType)tp2$5).withTypeVars((x$7) -> BoxesRunTime.boxToBoolean($anonfun$isSubType2$3(this, tp1$5, depth$2, x$7)), depth$2) || this.fourthTry$1(tp1$5, tp2$5, depth$2);
         } else if (tp2$5 instanceof Types.MethodType) {
            Types.MethodType var6 = (Types.MethodType)tp2$5;
            if (tp1$5 instanceof Types.MethodType) {
               Types.MethodType var7 = (Types.MethodType)tp1$5;
               List params1 = var7.params();
               Types.Type res1 = var7.resultType();
               List params2 = var6.params();
               Types.Type res2 = var6.resultType();
               return ((Collections)this).sameLength(params1, params2) && var7.isImplicit() == var6.isImplicit() && ((Types)this).matchingParams(params1, params2) && this.isSubType(res1.substSym(params1, params2), res2, depth$2);
            } else {
               return false;
            }
         } else if (tp2$5 instanceof Types.NullaryMethodType) {
            Types.NullaryMethodType var12 = (Types.NullaryMethodType)tp2$5;
            if (tp1$5 instanceof Types.NullaryMethodType) {
               Types.NullaryMethodType var13 = (Types.NullaryMethodType)tp1$5;
               return this.isSubType(var13.resultType(), var12.resultType(), depth$2);
            } else {
               return false;
            }
         } else if (tp2$5 instanceof Types.TypeBounds) {
            Types.TypeBounds var14 = (Types.TypeBounds)tp2$5;
            Types.Type lo2 = var14.lo();
            Types.Type hi2 = var14.hi();
            if (tp1$5 instanceof Types.TypeBounds) {
               Types.TypeBounds var17 = (Types.TypeBounds)tp1$5;
               Types.Type lo1 = var17.lo();
               Types.Type hi1 = var17.hi();
               return this.isSubType(lo2, lo1, depth$2) && this.isSubType(hi1, hi2, depth$2);
            } else {
               return false;
            }
         } else {
            return this.fourthTry$1(tp1$5, tp2$5, depth$2);
         }
      } else {
         Types.RefinedType var5 = (Types.RefinedType)tp2$5;
         List var10000 = var5.parents();
         if (var10000 == null) {
            throw null;
         } else {
            List forall_these = var10000;

            while(true) {
               if (forall_these.isEmpty()) {
                  var23 = true;
                  break;
               }

               Types.Type var21 = (Types.Type)forall_these.head();
               if (!this.isSubType(tp1$5, var21, depth$2)) {
                  var23 = false;
                  break;
               }

               forall_these = (List)forall_these.tail();
            }

            Object var22 = null;
            return var23 && var5.decls().forall((x$6) -> BoxesRunTime.boxToBoolean($anonfun$isSubType2$2(this, tp1$5, depth$2, x$6)));
         }
      }
   }

   private boolean retry$4(final Types.Type lhs, final Types.Type rhs, final Types.Type tp1$5, final Types.Type tp2$5, final int depth$2) {
      return (tp1$5 != lhs || tp2$5 != rhs) && this.isSubType(lhs, rhs, depth$2);
   }

   private boolean abstractTypeOnLeft$1(final Types.Type hi, final Types.Type tp1$5, final Types.Type tp2$5, final int depth$2) {
      return this.isDifferentTypeConstructor(tp1$5, hi) && this.retry$4(hi, tp2$5, tp1$5, tp2$5, depth$2);
   }

   private boolean nullOnLeft$1(final Types.Type tp2$5, final Symbols.Symbol sym1$1, final Types.Type tp1$5, final int depth$2) {
      if (tp2$5 instanceof Types.TypeRef) {
         Symbols.Symbol sym2 = ((Types.TypeRef)tp2$5).sym();
         return sym1$1.isBottomSubClass(sym2);
      } else {
         return ((Types)this).isSingleType(tp2$5) && tp2$5.widen().$less$colon$less(((Definitions)this).definitions().AnyRefTpe()) && this.retry$4(tp1$5, tp2$5.widen(), tp1$5, tp2$5, depth$2);
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$isSubType2$4(final TypeComparers $this, final Types.Type tp2$5, final Types.Type tp1$5, final int depth$2, final Types.Type x$8) {
      return $this.retry$4(x$8, tp2$5, tp1$5, tp2$5, depth$2);
   }

   private boolean fourthTry$1(final Types.Type tp1$5, final Types.Type tp2$5, final int depth$2) {
      if (tp1$5 instanceof Types.TypeRef) {
         Symbols.Symbol sym1 = ((Types.TypeRef)tp1$5).sym();
         boolean var5 = false;
         boolean var6 = false;
         if (((Definitions)this).definitions().NothingClass().equals(sym1)) {
            return true;
         } else if (((Definitions)this).definitions().NullClass().equals(sym1)) {
            return this.nullOnLeft$1(tp2$5, sym1, tp1$5, depth$2);
         } else {
            if (sym1 instanceof Symbols.ClassSymbol) {
               var5 = true;
               Symbols.ClassSymbol var10000 = (Symbols.ClassSymbol)sym1;
               if (((Types)this).isRawType(tp1$5)) {
                  return this.retry$4(((Types)this).normalizePlus(tp1$5), ((Types)this).normalizePlus(tp2$5), tp1$5, tp2$5, depth$2);
               }
            }

            if (var5 && sym1.isModuleClass()) {
               return this.retry$4(((Types)this).normalizePlus(tp1$5), ((Types)this).normalizePlus(tp2$5), tp1$5, tp2$5, depth$2);
            } else if (var5 && sym1.isRefinementClass()) {
               return this.retry$4(sym1.info(), tp2$5, tp1$5, tp2$5, depth$2);
            } else {
               if (sym1 instanceof Symbols.TypeSymbol) {
                  var6 = true;
                  Symbols.TypeSymbol var10 = (Symbols.TypeSymbol)sym1;
                  if (sym1.isDeferred()) {
                     return this.abstractTypeOnLeft$1(tp1$5.upperBound(), tp1$5, tp2$5, depth$2);
                  }
               }

               return var6 ? this.retry$4(((Types)this).normalizePlus(tp1$5), ((Types)this).normalizePlus(tp2$5), tp1$5, tp2$5, depth$2) : false;
            }
         }
      } else if (tp1$5 instanceof Types.RefinedType) {
         List parents = ((Types.RefinedType)tp1$5).parents();
         if (parents == null) {
            throw null;
         } else {
            for(List exists_these = parents; !exists_these.isEmpty(); exists_these = (List)exists_these.tail()) {
               Types.Type var9 = (Types.Type)exists_these.head();
               if ($anonfun$isSubType2$4(this, tp2$5, tp1$5, depth$2, var9)) {
                  return true;
               }
            }

            return false;
         }
      } else {
         return tp1$5 instanceof Types.SingletonType ? this.retry$4(tp1$5.underlying(), tp2$5, tp1$5, tp2$5, depth$2) : false;
      }
   }

   private Symbols.Symbol loop$1(final List bases) {
      while(!scala.collection.immutable.Nil..MODULE$.equals(bases)) {
         if (!(bases instanceof scala.collection.immutable..colon.colon)) {
            throw new MatchError(bases);
         }

         scala.collection.immutable..colon.colon var2 = (scala.collection.immutable..colon.colon)bases;
         Symbols.Symbol x = (Symbols.Symbol)var2.head();
         List xs = var2.next$access$1();
         if (((Definitions)this).definitions().isPrimitiveValueClass(x)) {
            return x;
         }

         SymbolTable var10000 = (SymbolTable)this;
         bases = xs;
         this = var10000;
      }

      return ((Symbols)this).NoSymbol();
   }

   static void $init$(final TypeComparers $this) {
      $this.scala$reflect$internal$tpe$TypeComparers$_setter_$scala$reflect$internal$tpe$TypeComparers$$_pendingSubTypes_$eq(new HashSet());
      $this.scala$reflect$internal$tpe$TypeComparers$$_subsametypeRecursions_$eq(0);
   }

   // $FF: synthetic method
   static Object $anonfun$isHKSubType$3$adapted(final Symbols.Symbol x$3) {
      return BoxesRunTime.boxToBoolean($anonfun$isHKSubType$3(x$3));
   }

   // $FF: synthetic method
   static Object $anonfun$isSubType2$1$adapted(final TypeComparers $this, final Types.Type tp1$5, final int depth$2, final Types.Type x$5) {
      return BoxesRunTime.boxToBoolean($anonfun$isSubType2$1($this, tp1$5, depth$2, x$5));
   }

   // $FF: synthetic method
   static Object $anonfun$isSubType2$4$adapted(final TypeComparers $this, final Types.Type tp2$5, final Types.Type tp1$5, final int depth$2, final Types.Type x$8) {
      return BoxesRunTime.boxToBoolean($anonfun$isSubType2$4($this, tp2$5, tp1$5, depth$2, x$8));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public final class SubTypePair implements Product, Serializable {
      private final Types.Type tp1;
      private final Types.Type tp2;
      // $FF: synthetic field
      private final SymbolTable $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Types.Type tp1() {
         return this.tp1;
      }

      public Types.Type tp2() {
         return this.tp2;
      }

      public String toString() {
         return (new StringBuilder(6)).append(this.tp1().toString()).append(" <:<? ").append(this.tp2()).toString();
      }

      public SubTypePair copy(final Types.Type tp1, final Types.Type tp2) {
         return this.$outer.new SubTypePair(tp1, tp2);
      }

      public Types.Type copy$default$1() {
         return this.tp1();
      }

      public Types.Type copy$default$2() {
         return this.tp2();
      }

      public String productPrefix() {
         return "SubTypePair";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.tp1();
            case 1:
               return this.tp2();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof SubTypePair;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "tp1";
            case 1:
               return "tp2";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return scala.util.hashing.MurmurHash3..MODULE$.productHash(this, -889275714, false);
      }

      public boolean equals(final Object x$1) {
         if (this != x$1) {
            if (x$1 instanceof SubTypePair) {
               SubTypePair var2 = (SubTypePair)x$1;
               Types.Type var10000 = this.tp1();
               Types.Type var3 = var2.tp1();
               if (var10000 == null) {
                  if (var3 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var3)) {
                  return false;
               }

               var10000 = this.tp2();
               Types.Type var4 = var2.tp2();
               if (var10000 == null) {
                  if (var4 == null) {
                     return true;
                  }
               } else if (var10000.equals(var4)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      public SubTypePair(final Types.Type tp1, final Types.Type tp2) {
         this.tp1 = tp1;
         this.tp2 = tp2;
         if (TypeComparers.this == null) {
            throw null;
         } else {
            this.$outer = TypeComparers.this;
            super();
         }
      }
   }

   public class SubTypePair$ extends AbstractFunction2 implements Serializable {
      // $FF: synthetic field
      private final SymbolTable $outer;

      public final String toString() {
         return "SubTypePair";
      }

      public SubTypePair apply(final Types.Type tp1, final Types.Type tp2) {
         return this.$outer.new SubTypePair(tp1, tp2);
      }

      public Option unapply(final SubTypePair x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.tp1(), x$0.tp2())));
      }

      public SubTypePair$() {
         if (TypeComparers.this == null) {
            throw null;
         } else {
            this.$outer = TypeComparers.this;
            super();
         }
      }
   }
}
