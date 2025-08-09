package cats.kernel;

import cats.kernel.compat.scalaVersionSpecific;
import cats.kernel.compat.scalaVersionSpecific$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.Option;
import scala.collection.IterableOnce;
import scala.concurrent.ExecutionContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\r%daB\u0014)!\u0003\r\t!\f\u0005\u0006\u0003\u0002!\tA\u0011\u0005\u0006\r\u00021\ta\u0012\u0005\u0006Y\u0002!\t!\u001c\u0005\u0007k\u0002\u0001K\u0011\u0003<\t\u000be\u0004A\u0011\u0001>\t\u000f\u0005\u001d\u0001\u0001\"\u0001\u0002\n!9\u0011q\u0002\u0001\u0005\u0002\u0005EqaBA\fQ!\u0005\u0011\u0011\u0004\u0004\u0007O!B\t!a\u0007\t\u000f\u0005-\u0013\u0002\"\u0001\u0002N!9\u0011qJ\u0005\u0005\u0006\u0005E\u0003bBA4\u0013\u0011\u0005\u0011\u0011\u000e\u0005\b\u0003\u007fJA\u0011AAA\u0011\u001d\ti)\u0003C\u0001\u0003\u001fCq!a'\n\t\u0007\ti\nC\u0004\u00026&!\u0019!a.\t\u000f\u0005\u001d\u0017\u0002b\u0001\u0002J\"9\u00111[\u0005\u0005\u0004\u0005U\u0007bBAp\u0013\u0011\r\u0011\u0011\u001d\u0005\b\u0003KLA1AAt\u0011\u001d\t\t0\u0003C\u0002\u0003gDq!!@\n\t\u0007\ty\u0010C\u0004\u0003\n%!\u0019Aa\u0003\t\u000f\t}\u0011\u0002b\u0001\u0003\"!9!1F\u0005\u0005\u0004\t5\u0002b\u0002B\u001c\u0013\u0011\r!\u0011\b\u0005\b\u0005\u0007JA1\u0001B#\u0011\u001d\u0011i&\u0003C\u0002\u0005?BqAa\u001c\n\t\u0007\u0011\t\bC\u0004\u0003\u0002&!\u0019Aa!\t\u000f\tM\u0015\u0002b\u0001\u0003\u0016\"9!1V\u0005\u0005\u0004\t5\u0006b\u0002Be\u0013\u0011\r!1\u001a\u0005\b\u00057LA1\u0001Bo\u0011\u001d\u001190\u0003C\u0002\u0005sDqaa\b\n\t\u0007\u0019\t\u0003C\u0004\u0004<%!\u0019a!\u0010\t\u0013\re\u0013\"!A\u0005\n\rm#!C*f[&<'o\\;q\u0015\tI#&\u0001\u0004lKJtW\r\u001c\u0006\u0002W\u0005!1-\u0019;t\u0007\u0001)\"A\f&\u0014\u0007\u0001yS\u0007\u0005\u00021g5\t\u0011GC\u00013\u0003\u0015\u00198-\u00197b\u0013\t!\u0014GA\u0002B]f\u0004\"A\u000e \u000f\u0005]bdB\u0001\u001d<\u001b\u0005I$B\u0001\u001e-\u0003\u0019a$o\\8u}%\t!'\u0003\u0002>c\u00059\u0001/Y2lC\u001e,\u0017BA A\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\ti\u0014'\u0001\u0004%S:LG\u000f\n\u000b\u0002\u0007B\u0011\u0001\u0007R\u0005\u0003\u000bF\u0012A!\u00168ji\u000691m\\7cS:,Gc\u0001%iUB\u0011\u0011J\u0013\u0007\u0001\t%Y\u0005\u0001)A\u0001\u0002\u000b\u0007AJA\u0001B#\tiu\u0006\u0005\u00021\u001d&\u0011q*\r\u0002\b\u001d>$\b.\u001b8hQ\u0019Q\u0015\u000bV-_GB\u0011\u0001GU\u0005\u0003'F\u00121b\u001d9fG&\fG.\u001b>fIF*1%\u0016,Y/:\u0011\u0001GV\u0005\u0003/F\n1!\u00138uc\u0011!sg\u000f\u001a2\u000b\rR6,\u0018/\u000f\u0005AZ\u0016B\u0001/2\u0003\u0011auN\\42\t\u0011:4HM\u0019\u0006G}\u0003'-\u0019\b\u0003a\u0001L!!Y\u0019\u0002\u000b\u0019cw.\u0019;2\t\u0011:4HM\u0019\u0006G\u0011,wM\u001a\b\u0003a\u0015L!AZ\u0019\u0002\r\u0011{WO\u00197fc\u0011!sg\u000f\u001a\t\u000b%\u0014\u0001\u0019\u0001%\u0002\u0003aDQa\u001b\u0002A\u0002!\u000b\u0011!_\u0001\tG>l'-\u001b8f\u001dR\u0019\u0001J\u001c9\t\u000b=\u001c\u0001\u0019\u0001%\u0002\u0003\u0005DQ!]\u0002A\u0002I\f\u0011A\u001c\t\u0003aML!\u0001^\u0019\u0003\u0007%sG/\u0001\tsKB,\u0017\r^3e\u0007>l'-\u001b8f\u001dR\u0019\u0001j\u001e=\t\u000b=$\u0001\u0019\u0001%\t\u000bE$\u0001\u0019\u0001:\u0002!\r|WNY5oK\u0006cGn\u00149uS>tGCA>\u007f!\r\u0001D\u0010S\u0005\u0003{F\u0012aa\u00149uS>t\u0007BB@\u0006\u0001\u0004\t\t!\u0001\u0002bgB!a'a\u0001I\u0013\r\t)\u0001\u0011\u0002\r\u0013R,'/\u00192mK>s7-Z\u0001\be\u00164XM]:f+\t\tY\u0001\u0005\u0003\u0002\u000e\u0001AU\"\u0001\u0015\u0002\u0017%tG/\u001a:dC2\fG/\u001a\u000b\u0005\u0003\u0017\t\u0019\u0002\u0003\u0004\u0002\u0016\u001d\u0001\r\u0001S\u0001\u0007[&$G\r\\3\u0002\u0013M+W.[4s_V\u0004\bcAA\u0007\u0013MY\u0011\"!\b\u0002&\u0005-\u0012qGA\u001f!\u0019\ti!a\b\u0002$%\u0019\u0011\u0011\u0005\u0015\u0003%M+W.[4s_V\u0004h)\u001e8di&|gn\u001d\t\u0004\u0003\u001b\u0001\u0001\u0003BA\u0007\u0003OI1!!\u000b)\u0005\r\u001a6-\u00197b-\u0016\u00148/[8o'B,7-\u001b4jG6{gn\\5e\u0013:\u001cH/\u00198dKN\u0004B!!\f\u000245\u0011\u0011q\u0006\u0006\u0004\u0003cA\u0013!C5ogR\fgnY3t\u0013\u0011\t)$a\f\u0003=Q+\b\u000f\\3D_6lW\u000f^1uSZ,wI]8va&s7\u000f^1oG\u0016\u001c\b\u0003BA\u0007\u0003sI1!a\u000f)\u000599%o\\;q\u0013:\u001cH/\u00198dKN\u0004B!a\u0010\u0002J5\u0011\u0011\u0011\t\u0006\u0005\u0003\u0007\n)%\u0001\u0002j_*\u0011\u0011qI\u0001\u0005U\u00064\u0018-C\u0002@\u0003\u0003\na\u0001P5oSRtDCAA\r\u0003\u0015\t\u0007\u000f\u001d7z+\u0011\t\u0019&!\u0017\u0015\t\u0005U\u00131\f\t\u0006\u0003\u001b\u0001\u0011q\u000b\t\u0004\u0013\u0006eC!B&\f\u0005\u0004a\u0005bBA/\u0017\u0001\u000f\u0011QK\u0001\u0003KZD3aCA1!\r\u0001\u00141M\u0005\u0004\u0003K\n$AB5oY&tW-\u0001\u0005j]N$\u0018M\\2f+\u0011\tY'!\u001d\u0015\t\u00055\u00141\u000f\t\u0006\u0003\u001b\u0001\u0011q\u000e\t\u0004\u0013\u0006ED!B&\r\u0005\u0004a\u0005bBA;\u0019\u0001\u0007\u0011qO\u0001\u0004G6\u0014\u0007#\u0003\u0019\u0002z\u0005=\u0014qNA8\u0013\r\tY(\r\u0002\n\rVt7\r^5p]JB3\u0001DA1\u0003\u00151\u0017N]:u+\u0011\t\u0019)!#\u0016\u0005\u0005\u0015\u0005#BA\u0007\u0001\u0005\u001d\u0005cA%\u0002\n\u0012)1*\u0004b\u0001\u0019\"\u001aQ\"!\u0019\u0002\t1\f7\u000f^\u000b\u0005\u0003#\u000b9*\u0006\u0002\u0002\u0014B)\u0011Q\u0002\u0001\u0002\u0016B\u0019\u0011*a&\u0005\u000b-s!\u0019\u0001')\u00079\t\t'A\u0013dCR\u001c8*\u001a:oK2\u0014u.\u001e8eK\u0012\u001cV-\\5mCR$\u0018nY3G_J\u0014\u0015\u000e^*fiV\u0011\u0011q\u0014\t\u0007\u0003\u001b\t\t+!*\n\u0007\u0005\r\u0006F\u0001\nC_VtG-\u001a3TK6LG.\u0019;uS\u000e,\u0007\u0003BAT\u0003ck!!!+\u000b\t\u0005-\u0016QV\u0001\nS6lW\u000f^1cY\u0016T1!a,2\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003g\u000bIK\u0001\u0004CSR\u001cV\r^\u0001\u001bG\u0006$8oS3s]\u0016d\u0017J\\:uC:\u001cWm\u001d$peVs\u0017\u000e^\u000b\u0003\u0003s\u0013b!a/\u0002@\u0006\u0005gABA_\u0013\u0001\tIL\u0001\u0007=e\u00164\u0017N\\3nK:$h\bE\u0003\u0002\u000e\u0005\u00056\tE\u0003\u0002\u000e\u0005\r7)C\u0002\u0002F\"\u0012\u0001cQ8n[V$\u0018\r^5wK\u001e\u0013x.\u001e9\u0002C\r\fGo]&fe:,GnQ8n[V$\u0018\r^5wK\u001e\u0013x.\u001e9G_J\u0014\u0015\u0010^3\u0016\u0005\u0005-\u0007CBA\u0007\u0003\u0007\fi\rE\u00021\u0003\u001fL1!!52\u0005\u0011\u0011\u0015\u0010^3\u0002E\r\fGo]&fe:,GnQ8n[V$\u0018\r^5wK\u001e\u0013x.\u001e9G_J\u001c\u0006n\u001c:u+\t\t9\u000e\u0005\u0004\u0002\u000e\u0005\r\u0017\u0011\u001c\t\u0004a\u0005m\u0017bAAoc\t)1\u000b[8si\u0006\u00013-\u0019;t\u0017\u0016\u0014h.\u001a7D_6lW\u000f^1uSZ,wI]8va\u001a{'/\u00138u+\t\t\u0019\u000fE\u0003\u0002\u000e\u0005\r'/A\u0011dCR\u001c8*\u001a:oK2\u001cu.\\7vi\u0006$\u0018N^3He>,\bOR8s\u0019>tw-\u0006\u0002\u0002jB1\u0011QBAb\u0003W\u00042\u0001MAw\u0013\r\ty/\r\u0002\u0005\u0019>tw-A\u0012dCR\u001c8*\u001a:oK2\u001cu.\\7vi\u0006$\u0018N^3He>,\bOR8s\u0005&<\u0017J\u001c;\u0016\u0005\u0005U\bCBA\u0007\u0003\u0007\f9\u0010E\u00027\u0003sL1!a?A\u0005\u0019\u0011\u0015nZ%oi\u000693-\u0019;t\u0017\u0016\u0014h.\u001a7D_6lW\u000f^1uSZ,wI]8va\u001a{'OQ5h\t\u0016\u001c\u0017.\\1m+\t\u0011\t\u0001\u0005\u0004\u0002\u000e\u0005\r'1\u0001\t\u0004m\t\u0015\u0011b\u0001B\u0004\u0001\nQ!)[4EK\u000eLW.\u00197\u0002K\r\fGo]&fe:,GnQ8n[V$\u0018\r^5wK\u001e\u0013x.\u001e9G_J$UO]1uS>tWC\u0001B\u0007!\u0019\ti!a1\u0003\u0010A!!\u0011\u0003B\u000e\u001b\t\u0011\u0019B\u0003\u0003\u0003\u0016\t]\u0011\u0001\u00033ve\u0006$\u0018n\u001c8\u000b\u0007\te\u0011'\u0001\u0006d_:\u001cWO\u001d:f]RLAA!\b\u0003\u0014\tAA)\u001e:bi&|g.A\u0016dCR\u001c8*\u001a:oK2\u001cu.\\7vi\u0006$\u0018N^3He>,\bOR8s\r&t\u0017\u000e^3EkJ\fG/[8o+\t\u0011\u0019\u0003\u0005\u0004\u0002\u000e\u0005\r'Q\u0005\t\u0005\u0005#\u00119#\u0003\u0003\u0003*\tM!A\u0004$j]&$X\rR;sCRLwN\\\u0001$G\u0006$8oS3s]\u0016d7i\\7nkR\fG/\u001b<f\u000fJ|W\u000f\u001d$pe\u0012{WO\u00197f+\t\u0011y\u0003\u0005\u0004\u0002\u000e\u0005\r'\u0011\u0007\t\u0004a\tM\u0012b\u0001B\u001bc\t1Ai\\;cY\u0016\f!eY1ug.+'O\\3m\u0007>lW.\u001e;bi&4Xm\u0012:pkB4uN\u001d$m_\u0006$XC\u0001B\u001e!\u0019\ti!a1\u0003>A\u0019\u0001Ga\u0010\n\u0007\t\u0005\u0013GA\u0003GY>\fG/A\rdCR\u001c8*\u001a:oK2luN\\8jI\u001a{'o\u0015;sS:<WC\u0001B$!\u0019\tiA!\u0013\u0003N%\u0019!1\n\u0015\u0003\r5{gn\\5e!\u0011\u0011yEa\u0016\u000f\t\tE#1\u000b\t\u0003qEJ1A!\u00162\u0003\u0019\u0001&/\u001a3fM&!!\u0011\fB.\u0005\u0019\u0019FO]5oO*\u0019!QK\u0019\u0002/\r\fGo]&fe:,G.T8o_&$gi\u001c:MSN$X\u0003\u0002B1\u0005[*\"Aa\u0019\u0011\r\u00055!\u0011\nB3!\u00151$q\rB6\u0013\r\u0011I\u0007\u0011\u0002\u0005\u0019&\u001cH\u000fE\u0002J\u0005[\"Qa\u0013\u000fC\u00021\u000b\u0011dY1ug.+'O\\3m\u001b>tw.\u001b3G_J4Vm\u0019;peV!!1\u000fB@+\t\u0011)\b\u0005\u0004\u0002\u000e\t%#q\u000f\t\u0006m\te$QP\u0005\u0004\u0005w\u0002%A\u0002,fGR|'\u000fE\u0002J\u0005\u007f\"QaS\u000fC\u00021\u000b\u0001dY1ug.+'O\\3m\u001b>tw.\u001b3G_J\fV/Z;f+\u0011\u0011)I!%\u0016\u0005\t\u001d\u0005CBA\u0007\u0005\u0013\u0012I\t\u0005\u0004\u0002(\n-%qR\u0005\u0005\u0005\u001b\u000bIKA\u0003Rk\u0016,X\rE\u0002J\u0005##Qa\u0013\u0010C\u00021\u000baeY1ug.+'O\\3m\u0007>lW.\u001e;bi&4Xm\u0012:pkB4uN\u001d$v]\u000e$\u0018n\u001c81+\u0011\u00119Ja)\u0015\t\te%Q\u0015\t\u0007\u0003\u001b\t\u0019Ma'\u0011\u000bA\u0012iJ!)\n\u0007\t}\u0015GA\u0005Gk:\u001cG/[8oaA\u0019\u0011Ja)\u0005\u000b-{\"\u0019\u0001'\t\u0013\t\u001dv$!AA\u0004\t%\u0016AC3wS\u0012,gnY3%cA1\u0011QBAb\u0005C\u000baeY1ug.+'O\\3m\u0007>lW.\u001e;bi&4Xm\u0012:pkB4uN\u001d$v]\u000e$\u0018n\u001c82+\u0019\u0011yKa/\u0003@R!!\u0011\u0017Bb!\u0019\ti!a1\u00034B9\u0001G!.\u0003:\nu\u0016b\u0001B\\c\tIa)\u001e8di&|g.\r\t\u0004\u0013\nmF!B&!\u0005\u0004a\u0005cA%\u0003@\u00121!\u0011\u0019\u0011C\u00021\u0013\u0011A\u0011\u0005\n\u0005\u000b\u0004\u0013\u0011!a\u0002\u0005\u000f\f!\"\u001a<jI\u0016t7-\u001a\u00133!\u0019\ti!a1\u0003>\u0006\u00113-\u0019;t\u0017\u0016\u0014h.\u001a7C_VtG-\u001a3TK6LG.\u0019;uS\u000e,gi\u001c:TKR,BA!4\u0003ZV\u0011!q\u001a\t\u0007\u0003\u001b\t\tK!5\u0011\r\t=#1\u001bBl\u0013\u0011\u0011)Na\u0017\u0003\u0007M+G\u000fE\u0002J\u00053$QaS\u0011C\u00021\u000b\u0001fY1ug.+'O\\3m\u0005>,h\u000eZ3e'\u0016l\u0017\u000e\\1ui&\u001cWMR8s'>\u0014H/\u001a3TKR,BAa8\u0003lR!!\u0011\u001dBw!\u0019\ti!!)\u0003dB1\u0011q\u0015Bs\u0005SLAAa:\u0002*\nI1k\u001c:uK\u0012\u001cV\r\u001e\t\u0004\u0013\n-H!B&#\u0005\u0004a\u0005\"\u0003BxE\u0005\u0005\t9\u0001By\u0003))g/\u001b3f]\u000e,Ge\r\t\u0007\u0003\u001b\u0011\u0019P!;\n\u0007\tU\bFA\u0003Pe\u0012,'/A\u0011dCR\u001c8*\u001a:oK2\u001cu.\\7vi\u0006$\u0018N^3N_:|\u0017\u000e\u001a$pe6\u000b\u0007/\u0006\u0004\u0003|\u000e-1\u0011\u0003\u000b\u0005\u0005{\u001c)\u0002\u0005\u0004\u0002\u000e\t}81A\u0005\u0004\u0007\u0003A#!E\"p[6,H/\u0019;jm\u0016luN\\8jIBA!qJB\u0003\u0007\u0013\u0019y!\u0003\u0003\u0004\b\tm#aA'baB\u0019\u0011ja\u0003\u0005\r\r51E1\u0001M\u0005\u0005Y\u0005cA%\u0004\u0012\u0011111C\u0012C\u00021\u0013\u0011A\u0016\u0005\n\u0007/\u0019\u0013\u0011!a\u0002\u00073\t!\"\u001a<jI\u0016t7-\u001a\u00135!\u0019\tiaa\u0007\u0004\u0010%\u00191Q\u0004\u0015\u0003)\r{W.\\;uCRLg/Z*f[&<'o\\;q\u0003)\u001a\u0017\r^:LKJtW\r\\\"p[6,H/\u0019;jm\u0016\u001cV-\\5he>,\bOR8s'>\u0014H/\u001a3NCB,baa\t\u00040\rMB\u0003BB\u0013\u0007k\u0001b!!\u0004\u0004\u001c\r\u001d\u0002\u0003CAT\u0007S\u0019ic!\r\n\t\r-\u0012\u0011\u0016\u0002\n'>\u0014H/\u001a3NCB\u00042!SB\u0018\t\u0019\u0019i\u0001\nb\u0001\u0019B\u0019\u0011ja\r\u0005\r\rMAE1\u0001M\u0011%\u00199\u0004JA\u0001\u0002\b\u0019I$\u0001\u0006fm&$WM\\2fIU\u0002b!!\u0004\u0004\u001c\rE\u0012aJ2biN\\UM\u001d8fY\u000e{W.\\;uCRLg/Z'p]>LGMR8s'>\u0014H/\u001a3NCB,baa\u0010\u0004H\r-CCBB!\u0007\u001b\u001a\u0019\u0006\u0005\u0004\u0002\u000e\t}81\t\t\t\u0003O\u001bIc!\u0012\u0004JA\u0019\u0011ja\u0012\u0005\r\r5QE1\u0001M!\rI51\n\u0003\u0007\u0007')#\u0019\u0001'\t\u0013\r=S%!AA\u0004\rE\u0013AC3wS\u0012,gnY3%mA1\u0011Q\u0002Bz\u0007\u000bB\u0011b!\u0016&\u0003\u0003\u0005\u001daa\u0016\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$s\u0007\u0005\u0004\u0002\u000e\rm1\u0011J\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0007;\u0002Baa\u0018\u0004f5\u00111\u0011\r\u0006\u0005\u0007G\n)%\u0001\u0003mC:<\u0017\u0002BB4\u0007C\u0012aa\u00142kK\u000e$\b"
)
public interface Semigroup extends Serializable {
   static CommutativeMonoid catsKernelCommutativeMonoidForSortedMap(final Order evidence$6, final CommutativeSemigroup evidence$7) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForSortedMap(evidence$6, evidence$7);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForSortedMap(final CommutativeSemigroup evidence$5) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForSortedMap(evidence$5);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForMap(final CommutativeSemigroup evidence$4) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForMap(evidence$4);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForSortedSet(final Order evidence$3) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForSortedSet(evidence$3);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForSet() {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForSet();
   }

   static CommutativeGroup catsKernelCommutativeGroupForFunction1(final CommutativeGroup evidence$2) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForFunction1(evidence$2);
   }

   static CommutativeGroup catsKernelCommutativeGroupForFunction0(final CommutativeGroup evidence$1) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForFunction0(evidence$1);
   }

   static Monoid catsKernelMonoidForQueue() {
      return Semigroup$.MODULE$.catsKernelMonoidForQueue();
   }

   static Monoid catsKernelMonoidForVector() {
      return Semigroup$.MODULE$.catsKernelMonoidForVector();
   }

   static Monoid catsKernelMonoidForList() {
      return Semigroup$.MODULE$.catsKernelMonoidForList();
   }

   static Monoid catsKernelMonoidForString() {
      return Semigroup$.MODULE$.catsKernelMonoidForString();
   }

   static CommutativeGroup catsKernelCommutativeGroupForFloat() {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForFloat();
   }

   static CommutativeGroup catsKernelCommutativeGroupForDouble() {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForDouble();
   }

   static CommutativeGroup catsKernelCommutativeGroupForFiniteDuration() {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForFiniteDuration();
   }

   static CommutativeGroup catsKernelCommutativeGroupForDuration() {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForDuration();
   }

   static CommutativeGroup catsKernelCommutativeGroupForBigDecimal() {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForBigDecimal();
   }

   static CommutativeGroup catsKernelCommutativeGroupForBigInt() {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForBigInt();
   }

   static CommutativeGroup catsKernelCommutativeGroupForLong() {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForLong();
   }

   static CommutativeGroup catsKernelCommutativeGroupForInt() {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForInt();
   }

   static CommutativeGroup catsKernelCommutativeGroupForShort() {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForShort();
   }

   static CommutativeGroup catsKernelCommutativeGroupForByte() {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForByte();
   }

   static BoundedSemilattice catsKernelInstancesForUnit() {
      return Semigroup$.MODULE$.catsKernelInstancesForUnit();
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForBitSet() {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForBitSet();
   }

   static Semigroup last() {
      return Semigroup$.MODULE$.last();
   }

   static Semigroup first() {
      return Semigroup$.MODULE$.first();
   }

   static Semigroup instance(final Function2 cmb) {
      return Semigroup$.MODULE$.instance(cmb);
   }

   static Semigroup apply(final Semigroup ev) {
      return Semigroup$.MODULE$.apply(ev);
   }

   static Group catsKernelGroupForFunction1(final Group evidence$9) {
      return Semigroup$.MODULE$.catsKernelGroupForFunction1(evidence$9);
   }

   static Group catsKernelGroupForFunction0(final Group evidence$8) {
      return Semigroup$.MODULE$.catsKernelGroupForFunction0(evidence$8);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForFunction1(final BoundedSemilattice evidence$11) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForFunction1(evidence$11);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForFunction0(final BoundedSemilattice evidence$10) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForFunction0(evidence$10);
   }

   static Semilattice catsKernelSemilatticeForFunction1(final Semilattice evidence$13) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForFunction1(evidence$13);
   }

   static Semilattice catsKernelSemilatticeForFunction0(final Semilattice evidence$12) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForFunction0(evidence$12);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForOption(final CommutativeSemigroup evidence$16) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForOption(evidence$16);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForFunction1(final CommutativeMonoid evidence$15) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForFunction1(evidence$15);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForFunction0(final CommutativeMonoid evidence$14) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForFunction0(evidence$14);
   }

   static Monoid catsKernelMonoidForSeq() {
      return Semigroup$.MODULE$.catsKernelMonoidForSeq();
   }

   static Monoid catsKernelMonoidForOption(final Semigroup evidence$25) {
      return Semigroup$.MODULE$.catsKernelMonoidForOption(evidence$25);
   }

   static Monoid catsKernelMonoidForFuture(final Monoid A, final ExecutionContext ec) {
      return Semigroup$.MODULE$.catsKernelMonoidForFuture(A, ec);
   }

   static Monoid catsKernelMonoidForTry(final Monoid evidence$24) {
      return Semigroup$.MODULE$.catsKernelMonoidForTry(evidence$24);
   }

   static Monoid catsKernelMonoidForEither(final Monoid evidence$23) {
      return Semigroup$.MODULE$.catsKernelMonoidForEither(evidence$23);
   }

   static Monoid catsKernelMonoidForSortedMap(final Order evidence$21, final Semigroup evidence$22) {
      return Semigroup$.MODULE$.catsKernelMonoidForSortedMap(evidence$21, evidence$22);
   }

   static Semigroup catsKernelSemigroupForSortedMap(final Semigroup evidence$20) {
      return Semigroup$.MODULE$.catsKernelSemigroupForSortedMap(evidence$20);
   }

   static Monoid catsKernelMonoidForMap(final Semigroup evidence$19) {
      return Semigroup$.MODULE$.catsKernelMonoidForMap(evidence$19);
   }

   static Monoid catsKernelMonoidForFunction1(final Monoid evidence$18) {
      return Semigroup$.MODULE$.catsKernelMonoidForFunction1(evidence$18);
   }

   static Monoid catsKernelMonoidForFunction0(final Monoid evidence$17) {
      return Semigroup$.MODULE$.catsKernelMonoidForFunction0(evidence$17);
   }

   static Band catsKernelBandForFunction1(final Band evidence$27) {
      return Semigroup$.MODULE$.catsKernelBandForFunction1(evidence$27);
   }

   static Band catsKernelBandForFunction0(final Band evidence$26) {
      return Semigroup$.MODULE$.catsKernelBandForFunction0(evidence$26);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForFunction1(final CommutativeSemigroup evidence$29) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForFunction1(evidence$29);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForFunction0(final CommutativeSemigroup evidence$28) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForFunction0(evidence$28);
   }

   static Semigroup catsKernelSemigroupForFuture(final Semigroup A, final ExecutionContext ec) {
      return Semigroup$.MODULE$.catsKernelSemigroupForFuture(A, ec);
   }

   static Semigroup catsKernelSemigroupForTry(final Semigroup evidence$33) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTry(evidence$33);
   }

   static Semigroup catsKernelSemigroupForEither(final Semigroup evidence$32) {
      return Semigroup$.MODULE$.catsKernelSemigroupForEither(evidence$32);
   }

   static Semigroup catsKernelSemigroupForFunction1(final Semigroup evidence$31) {
      return Semigroup$.MODULE$.catsKernelSemigroupForFunction1(evidence$31);
   }

   static Semigroup catsKernelSemigroupForFunction0(final Semigroup evidence$30) {
      return Semigroup$.MODULE$.catsKernelSemigroupForFunction0(evidence$30);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple22(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16, final CommutativeGroup A17, final CommutativeGroup A18, final CommutativeGroup A19, final CommutativeGroup A20, final CommutativeGroup A21) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple21(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16, final CommutativeGroup A17, final CommutativeGroup A18, final CommutativeGroup A19, final CommutativeGroup A20) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple20(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16, final CommutativeGroup A17, final CommutativeGroup A18, final CommutativeGroup A19) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple19(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16, final CommutativeGroup A17, final CommutativeGroup A18) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple18(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16, final CommutativeGroup A17) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple17(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple16(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple15(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple14(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple13(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple12(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple11(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple10(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple9(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple8(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple7(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple6(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple6(A0, A1, A2, A3, A4, A5);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple5(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple5(A0, A1, A2, A3, A4);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple4(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple4(A0, A1, A2, A3);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple3(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple3(A0, A1, A2);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple2(final CommutativeGroup A0, final CommutativeGroup A1) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple2(A0, A1);
   }

   static CommutativeGroup catsKernelCommutativeGroupForTuple1(final CommutativeGroup A0) {
      return Semigroup$.MODULE$.catsKernelCommutativeGroupForTuple1(A0);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple22(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16, final BoundedSemilattice A17, final BoundedSemilattice A18, final BoundedSemilattice A19, final BoundedSemilattice A20, final BoundedSemilattice A21) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple21(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16, final BoundedSemilattice A17, final BoundedSemilattice A18, final BoundedSemilattice A19, final BoundedSemilattice A20) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple20(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16, final BoundedSemilattice A17, final BoundedSemilattice A18, final BoundedSemilattice A19) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple19(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16, final BoundedSemilattice A17, final BoundedSemilattice A18) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple18(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16, final BoundedSemilattice A17) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple17(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple16(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple15(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple14(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple13(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple12(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple11(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple10(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple9(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple8(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple7(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple6(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple6(A0, A1, A2, A3, A4, A5);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple5(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple5(A0, A1, A2, A3, A4);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple4(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple4(A0, A1, A2, A3);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple3(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple3(A0, A1, A2);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple2(final BoundedSemilattice A0, final BoundedSemilattice A1) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple2(A0, A1);
   }

   static BoundedSemilattice catsKernelBoundedSemilatticeForTuple1(final BoundedSemilattice A0) {
      return Semigroup$.MODULE$.catsKernelBoundedSemilatticeForTuple1(A0);
   }

   static Group catsKernelGroupForTuple22(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18, final Group A19, final Group A20, final Group A21) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   static Group catsKernelGroupForTuple21(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18, final Group A19, final Group A20) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   static Group catsKernelGroupForTuple20(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18, final Group A19) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   static Group catsKernelGroupForTuple19(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   static Group catsKernelGroupForTuple18(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   static Group catsKernelGroupForTuple17(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   static Group catsKernelGroupForTuple16(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   static Group catsKernelGroupForTuple15(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   static Group catsKernelGroupForTuple14(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   static Group catsKernelGroupForTuple13(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   static Group catsKernelGroupForTuple12(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   static Group catsKernelGroupForTuple11(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   static Group catsKernelGroupForTuple10(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   static Group catsKernelGroupForTuple9(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   static Group catsKernelGroupForTuple8(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   static Group catsKernelGroupForTuple7(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   static Group catsKernelGroupForTuple6(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple6(A0, A1, A2, A3, A4, A5);
   }

   static Group catsKernelGroupForTuple5(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple5(A0, A1, A2, A3, A4);
   }

   static Group catsKernelGroupForTuple4(final Group A0, final Group A1, final Group A2, final Group A3) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple4(A0, A1, A2, A3);
   }

   static Group catsKernelGroupForTuple3(final Group A0, final Group A1, final Group A2) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple3(A0, A1, A2);
   }

   static Group catsKernelGroupForTuple2(final Group A0, final Group A1) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple2(A0, A1);
   }

   static Group catsKernelGroupForTuple1(final Group A0) {
      return Semigroup$.MODULE$.catsKernelGroupForTuple1(A0);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple22(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16, final CommutativeMonoid A17, final CommutativeMonoid A18, final CommutativeMonoid A19, final CommutativeMonoid A20, final CommutativeMonoid A21) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple21(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16, final CommutativeMonoid A17, final CommutativeMonoid A18, final CommutativeMonoid A19, final CommutativeMonoid A20) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple20(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16, final CommutativeMonoid A17, final CommutativeMonoid A18, final CommutativeMonoid A19) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple19(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16, final CommutativeMonoid A17, final CommutativeMonoid A18) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple18(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16, final CommutativeMonoid A17) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple17(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple16(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple15(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple14(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple13(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple12(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple11(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple10(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple9(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple8(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple7(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple6(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple6(A0, A1, A2, A3, A4, A5);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple5(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple5(A0, A1, A2, A3, A4);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple4(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple4(A0, A1, A2, A3);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple3(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple3(A0, A1, A2);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple2(final CommutativeMonoid A0, final CommutativeMonoid A1) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple2(A0, A1);
   }

   static CommutativeMonoid catsKernelCommutativeMonoidForTuple1(final CommutativeMonoid A0) {
      return Semigroup$.MODULE$.catsKernelCommutativeMonoidForTuple1(A0);
   }

   static Semilattice catsKernelSemilatticeForTuple22(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18, final Semilattice A19, final Semilattice A20, final Semilattice A21) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   static Semilattice catsKernelSemilatticeForTuple21(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18, final Semilattice A19, final Semilattice A20) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   static Semilattice catsKernelSemilatticeForTuple20(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18, final Semilattice A19) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   static Semilattice catsKernelSemilatticeForTuple19(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   static Semilattice catsKernelSemilatticeForTuple18(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   static Semilattice catsKernelSemilatticeForTuple17(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   static Semilattice catsKernelSemilatticeForTuple16(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   static Semilattice catsKernelSemilatticeForTuple15(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   static Semilattice catsKernelSemilatticeForTuple14(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   static Semilattice catsKernelSemilatticeForTuple13(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   static Semilattice catsKernelSemilatticeForTuple12(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   static Semilattice catsKernelSemilatticeForTuple11(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   static Semilattice catsKernelSemilatticeForTuple10(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   static Semilattice catsKernelSemilatticeForTuple9(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   static Semilattice catsKernelSemilatticeForTuple8(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   static Semilattice catsKernelSemilatticeForTuple7(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   static Semilattice catsKernelSemilatticeForTuple6(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple6(A0, A1, A2, A3, A4, A5);
   }

   static Semilattice catsKernelSemilatticeForTuple5(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple5(A0, A1, A2, A3, A4);
   }

   static Semilattice catsKernelSemilatticeForTuple4(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple4(A0, A1, A2, A3);
   }

   static Semilattice catsKernelSemilatticeForTuple3(final Semilattice A0, final Semilattice A1, final Semilattice A2) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple3(A0, A1, A2);
   }

   static Semilattice catsKernelSemilatticeForTuple2(final Semilattice A0, final Semilattice A1) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple2(A0, A1);
   }

   static Semilattice catsKernelSemilatticeForTuple1(final Semilattice A0) {
      return Semigroup$.MODULE$.catsKernelSemilatticeForTuple1(A0);
   }

   static Monoid catsKernelMonoidForTuple22(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16, final Monoid A17, final Monoid A18, final Monoid A19, final Monoid A20, final Monoid A21) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   static Monoid catsKernelMonoidForTuple21(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16, final Monoid A17, final Monoid A18, final Monoid A19, final Monoid A20) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   static Monoid catsKernelMonoidForTuple20(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16, final Monoid A17, final Monoid A18, final Monoid A19) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   static Monoid catsKernelMonoidForTuple19(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16, final Monoid A17, final Monoid A18) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   static Monoid catsKernelMonoidForTuple18(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16, final Monoid A17) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   static Monoid catsKernelMonoidForTuple17(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   static Monoid catsKernelMonoidForTuple16(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   static Monoid catsKernelMonoidForTuple15(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   static Monoid catsKernelMonoidForTuple14(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   static Monoid catsKernelMonoidForTuple13(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   static Monoid catsKernelMonoidForTuple12(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   static Monoid catsKernelMonoidForTuple11(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   static Monoid catsKernelMonoidForTuple10(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   static Monoid catsKernelMonoidForTuple9(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   static Monoid catsKernelMonoidForTuple8(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   static Monoid catsKernelMonoidForTuple7(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   static Monoid catsKernelMonoidForTuple6(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple6(A0, A1, A2, A3, A4, A5);
   }

   static Monoid catsKernelMonoidForTuple5(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple5(A0, A1, A2, A3, A4);
   }

   static Monoid catsKernelMonoidForTuple4(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple4(A0, A1, A2, A3);
   }

   static Monoid catsKernelMonoidForTuple3(final Monoid A0, final Monoid A1, final Monoid A2) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple3(A0, A1, A2);
   }

   static Monoid catsKernelMonoidForTuple2(final Monoid A0, final Monoid A1) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple2(A0, A1);
   }

   static Monoid catsKernelMonoidForTuple1(final Monoid A0) {
      return Semigroup$.MODULE$.catsKernelMonoidForTuple1(A0);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple22(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16, final CommutativeSemigroup A17, final CommutativeSemigroup A18, final CommutativeSemigroup A19, final CommutativeSemigroup A20, final CommutativeSemigroup A21) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple21(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16, final CommutativeSemigroup A17, final CommutativeSemigroup A18, final CommutativeSemigroup A19, final CommutativeSemigroup A20) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple20(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16, final CommutativeSemigroup A17, final CommutativeSemigroup A18, final CommutativeSemigroup A19) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple19(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16, final CommutativeSemigroup A17, final CommutativeSemigroup A18) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple18(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16, final CommutativeSemigroup A17) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple17(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple16(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple15(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple14(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple13(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple12(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple11(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple10(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple9(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple8(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple7(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple6(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple6(A0, A1, A2, A3, A4, A5);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple5(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple5(A0, A1, A2, A3, A4);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple4(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple4(A0, A1, A2, A3);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple3(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple3(A0, A1, A2);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple2(final CommutativeSemigroup A0, final CommutativeSemigroup A1) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple2(A0, A1);
   }

   static CommutativeSemigroup catsKernelCommutativeSemigroupForTuple1(final CommutativeSemigroup A0) {
      return Semigroup$.MODULE$.catsKernelCommutativeSemigroupForTuple1(A0);
   }

   static Band catsKernelBandForTuple22(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16, final Band A17, final Band A18, final Band A19, final Band A20, final Band A21) {
      return Semigroup$.MODULE$.catsKernelBandForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   static Band catsKernelBandForTuple21(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16, final Band A17, final Band A18, final Band A19, final Band A20) {
      return Semigroup$.MODULE$.catsKernelBandForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   static Band catsKernelBandForTuple20(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16, final Band A17, final Band A18, final Band A19) {
      return Semigroup$.MODULE$.catsKernelBandForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   static Band catsKernelBandForTuple19(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16, final Band A17, final Band A18) {
      return Semigroup$.MODULE$.catsKernelBandForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   static Band catsKernelBandForTuple18(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16, final Band A17) {
      return Semigroup$.MODULE$.catsKernelBandForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   static Band catsKernelBandForTuple17(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16) {
      return Semigroup$.MODULE$.catsKernelBandForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   static Band catsKernelBandForTuple16(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15) {
      return Semigroup$.MODULE$.catsKernelBandForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   static Band catsKernelBandForTuple15(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14) {
      return Semigroup$.MODULE$.catsKernelBandForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   static Band catsKernelBandForTuple14(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13) {
      return Semigroup$.MODULE$.catsKernelBandForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   static Band catsKernelBandForTuple13(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12) {
      return Semigroup$.MODULE$.catsKernelBandForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   static Band catsKernelBandForTuple12(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11) {
      return Semigroup$.MODULE$.catsKernelBandForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   static Band catsKernelBandForTuple11(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10) {
      return Semigroup$.MODULE$.catsKernelBandForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   static Band catsKernelBandForTuple10(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9) {
      return Semigroup$.MODULE$.catsKernelBandForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   static Band catsKernelBandForTuple9(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8) {
      return Semigroup$.MODULE$.catsKernelBandForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   static Band catsKernelBandForTuple8(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7) {
      return Semigroup$.MODULE$.catsKernelBandForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   static Band catsKernelBandForTuple7(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6) {
      return Semigroup$.MODULE$.catsKernelBandForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   static Band catsKernelBandForTuple6(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5) {
      return Semigroup$.MODULE$.catsKernelBandForTuple6(A0, A1, A2, A3, A4, A5);
   }

   static Band catsKernelBandForTuple5(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4) {
      return Semigroup$.MODULE$.catsKernelBandForTuple5(A0, A1, A2, A3, A4);
   }

   static Band catsKernelBandForTuple4(final Band A0, final Band A1, final Band A2, final Band A3) {
      return Semigroup$.MODULE$.catsKernelBandForTuple4(A0, A1, A2, A3);
   }

   static Band catsKernelBandForTuple3(final Band A0, final Band A1, final Band A2) {
      return Semigroup$.MODULE$.catsKernelBandForTuple3(A0, A1, A2);
   }

   static Band catsKernelBandForTuple2(final Band A0, final Band A1) {
      return Semigroup$.MODULE$.catsKernelBandForTuple2(A0, A1);
   }

   static Band catsKernelBandForTuple1(final Band A0) {
      return Semigroup$.MODULE$.catsKernelBandForTuple1(A0);
   }

   static Semigroup catsKernelSemigroupForTuple22(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16, final Semigroup A17, final Semigroup A18, final Semigroup A19, final Semigroup A20, final Semigroup A21) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   static Semigroup catsKernelSemigroupForTuple21(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16, final Semigroup A17, final Semigroup A18, final Semigroup A19, final Semigroup A20) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   static Semigroup catsKernelSemigroupForTuple20(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16, final Semigroup A17, final Semigroup A18, final Semigroup A19) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   static Semigroup catsKernelSemigroupForTuple19(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16, final Semigroup A17, final Semigroup A18) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   static Semigroup catsKernelSemigroupForTuple18(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16, final Semigroup A17) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   static Semigroup catsKernelSemigroupForTuple17(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   static Semigroup catsKernelSemigroupForTuple16(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   static Semigroup catsKernelSemigroupForTuple15(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   static Semigroup catsKernelSemigroupForTuple14(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   static Semigroup catsKernelSemigroupForTuple13(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   static Semigroup catsKernelSemigroupForTuple12(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   static Semigroup catsKernelSemigroupForTuple11(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   static Semigroup catsKernelSemigroupForTuple10(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   static Semigroup catsKernelSemigroupForTuple9(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   static Semigroup catsKernelSemigroupForTuple8(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   static Semigroup catsKernelSemigroupForTuple7(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   static Semigroup catsKernelSemigroupForTuple6(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple6(A0, A1, A2, A3, A4, A5);
   }

   static Semigroup catsKernelSemigroupForTuple5(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple5(A0, A1, A2, A3, A4);
   }

   static Semigroup catsKernelSemigroupForTuple4(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple4(A0, A1, A2, A3);
   }

   static Semigroup catsKernelSemigroupForTuple3(final Semigroup A0, final Semigroup A1, final Semigroup A2) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple3(A0, A1, A2);
   }

   static Semigroup catsKernelSemigroupForTuple2(final Semigroup A0, final Semigroup A1) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple2(A0, A1);
   }

   static Semigroup catsKernelSemigroupForTuple1(final Semigroup A0) {
      return Semigroup$.MODULE$.catsKernelSemigroupForTuple1(A0);
   }

   static Monoid catsKernelMonoidForArraySeq() {
      return Semigroup$.MODULE$.catsKernelMonoidForArraySeq();
   }

   static Monoid catsKernelMonoidForLazyList() {
      return Semigroup$.MODULE$.catsKernelMonoidForLazyList();
   }

   /** @deprecated */
   static Monoid catsKernelMonoidForStream() {
      return Semigroup$.MODULE$.catsKernelMonoidForStream();
   }

   static boolean isIdempotent(final Semigroup ev) {
      return Semigroup$.MODULE$.isIdempotent(ev);
   }

   static boolean isCommutative(final Semigroup ev) {
      return Semigroup$.MODULE$.isCommutative(ev);
   }

   static Object maybeCombine(final Object x, final Option oy, final Semigroup ev) {
      return Semigroup$.MODULE$.maybeCombine(x, oy, ev);
   }

   static Object maybeCombine(final Option ox, final Object y, final Semigroup ev) {
      return Semigroup$.MODULE$.maybeCombine(ox, y, ev);
   }

   Object combine(final Object x, final Object y);

   default Object combineN(final Object a, final int n) {
      if (n <= 0) {
         throw new IllegalArgumentException("Repeated combining for semigroups must have n > 0");
      } else {
         return this.repeatedCombineN(a, n);
      }
   }

   default Object repeatedCombineN(final Object a, final int n) {
      return n == 1 ? a : this.loop$1(a, n - 1, a);
   }

   default Option combineAllOption(final IterableOnce as) {
      return scalaVersionSpecific.iterableOnceExtension$.MODULE$.reduceOption$extension(scalaVersionSpecific$.MODULE$.iterableOnceExtension(as), (x, y) -> this.combine(x, y));
   }

   default Semigroup reverse() {
      return new Semigroup() {
         // $FF: synthetic field
         private final Semigroup $outer;

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.super.combine$mcD$sp(x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.super.combine$mcF$sp(x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.super.combine$mcI$sp(x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.super.combine$mcJ$sp(x, y);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.super.combineN$mcD$sp(a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.super.combineN$mcF$sp(a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.super.combineN$mcI$sp(a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.super.combineN$mcJ$sp(a, n);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.super.repeatedCombineN(a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.super.repeatedCombineN$mcD$sp(a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.super.repeatedCombineN$mcF$sp(a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.super.repeatedCombineN$mcI$sp(a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.super.repeatedCombineN$mcJ$sp(a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.super.combineAllOption(as);
         }

         public Semigroup reverse$mcD$sp() {
            return Semigroup.super.reverse$mcD$sp();
         }

         public Semigroup reverse$mcF$sp() {
            return Semigroup.super.reverse$mcF$sp();
         }

         public Semigroup reverse$mcI$sp() {
            return Semigroup.super.reverse$mcI$sp();
         }

         public Semigroup reverse$mcJ$sp() {
            return Semigroup.super.reverse$mcJ$sp();
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.super.intercalate(middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.super.intercalate$mcD$sp(middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.super.intercalate$mcF$sp(middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.super.intercalate$mcI$sp(middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.super.intercalate$mcJ$sp(middle);
         }

         public Object combine(final Object a, final Object b) {
            return this.$outer.combine(b, a);
         }

         public Object combineN(final Object a, final int n) {
            return this.$outer.combineN(a, n);
         }

         public Semigroup reverse() {
            return this.$outer;
         }

         public {
            if (Semigroup.this == null) {
               throw null;
            } else {
               this.$outer = Semigroup.this;
               Semigroup.$init$(this);
            }
         }
      };
   }

   default Semigroup intercalate(final Object middle) {
      return new Semigroup(middle) {
         // $FF: synthetic field
         private final Semigroup $outer;
         private final Object middle$1;

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.super.combine$mcD$sp(x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.super.combine$mcF$sp(x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.super.combine$mcI$sp(x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.super.combine$mcJ$sp(x, y);
         }

         public Object combineN(final Object a, final int n) {
            return Semigroup.super.combineN(a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.super.combineN$mcD$sp(a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.super.combineN$mcF$sp(a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.super.combineN$mcI$sp(a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.super.combineN$mcJ$sp(a, n);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.super.repeatedCombineN(a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.super.repeatedCombineN$mcD$sp(a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.super.repeatedCombineN$mcF$sp(a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.super.repeatedCombineN$mcI$sp(a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.super.repeatedCombineN$mcJ$sp(a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.super.combineAllOption(as);
         }

         public Semigroup reverse() {
            return Semigroup.super.reverse();
         }

         public Semigroup reverse$mcD$sp() {
            return Semigroup.super.reverse$mcD$sp();
         }

         public Semigroup reverse$mcF$sp() {
            return Semigroup.super.reverse$mcF$sp();
         }

         public Semigroup reverse$mcI$sp() {
            return Semigroup.super.reverse$mcI$sp();
         }

         public Semigroup reverse$mcJ$sp() {
            return Semigroup.super.reverse$mcJ$sp();
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.super.intercalate(middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.super.intercalate$mcD$sp(middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.super.intercalate$mcF$sp(middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.super.intercalate$mcI$sp(middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.super.intercalate$mcJ$sp(middle);
         }

         public Object combine(final Object a, final Object b) {
            return this.$outer.combine(a, this.$outer.combine(this.middle$1, b));
         }

         public {
            if (Semigroup.this == null) {
               throw null;
            } else {
               this.$outer = Semigroup.this;
               this.middle$1 = middle$1;
               Semigroup.$init$(this);
            }
         }
      };
   }

   default double combine$mcD$sp(final double x, final double y) {
      return BoxesRunTime.unboxToDouble(this.combine(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y)));
   }

   default float combine$mcF$sp(final float x, final float y) {
      return BoxesRunTime.unboxToFloat(this.combine(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y)));
   }

   default int combine$mcI$sp(final int x, final int y) {
      return BoxesRunTime.unboxToInt(this.combine(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y)));
   }

   default long combine$mcJ$sp(final long x, final long y) {
      return BoxesRunTime.unboxToLong(this.combine(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y)));
   }

   default double combineN$mcD$sp(final double a, final int n) {
      return BoxesRunTime.unboxToDouble(this.combineN(BoxesRunTime.boxToDouble(a), n));
   }

   default float combineN$mcF$sp(final float a, final int n) {
      return BoxesRunTime.unboxToFloat(this.combineN(BoxesRunTime.boxToFloat(a), n));
   }

   default int combineN$mcI$sp(final int a, final int n) {
      return BoxesRunTime.unboxToInt(this.combineN(BoxesRunTime.boxToInteger(a), n));
   }

   default long combineN$mcJ$sp(final long a, final int n) {
      return BoxesRunTime.unboxToLong(this.combineN(BoxesRunTime.boxToLong(a), n));
   }

   default double repeatedCombineN$mcD$sp(final double a, final int n) {
      return BoxesRunTime.unboxToDouble(this.repeatedCombineN(BoxesRunTime.boxToDouble(a), n));
   }

   default float repeatedCombineN$mcF$sp(final float a, final int n) {
      return BoxesRunTime.unboxToFloat(this.repeatedCombineN(BoxesRunTime.boxToFloat(a), n));
   }

   default int repeatedCombineN$mcI$sp(final int a, final int n) {
      return BoxesRunTime.unboxToInt(this.repeatedCombineN(BoxesRunTime.boxToInteger(a), n));
   }

   default long repeatedCombineN$mcJ$sp(final long a, final int n) {
      return BoxesRunTime.unboxToLong(this.repeatedCombineN(BoxesRunTime.boxToLong(a), n));
   }

   default Semigroup reverse$mcD$sp() {
      return this.reverse();
   }

   default Semigroup reverse$mcF$sp() {
      return this.reverse();
   }

   default Semigroup reverse$mcI$sp() {
      return this.reverse();
   }

   default Semigroup reverse$mcJ$sp() {
      return this.reverse();
   }

   default Semigroup intercalate$mcD$sp(final double middle) {
      return this.intercalate(BoxesRunTime.boxToDouble(middle));
   }

   default Semigroup intercalate$mcF$sp(final float middle) {
      return this.intercalate(BoxesRunTime.boxToFloat(middle));
   }

   default Semigroup intercalate$mcI$sp(final int middle) {
      return this.intercalate(BoxesRunTime.boxToInteger(middle));
   }

   default Semigroup intercalate$mcJ$sp(final long middle) {
      return this.intercalate(BoxesRunTime.boxToLong(middle));
   }

   private Object loop$1(final Object b, final int k, final Object extra) {
      while(k != 1) {
         Object x = (k & 1) == 1 ? this.combine(b, extra) : extra;
         Object var10000 = this.combine(b, b);
         int var10001 = k >>> 1;
         extra = x;
         k = var10001;
         b = var10000;
      }

      return this.combine(b, extra);
   }

   static void $init$(final Semigroup $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
