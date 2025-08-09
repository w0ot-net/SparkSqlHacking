package breeze.linalg;

import breeze.util.Opt;
import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple1;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\rUv\u0001CA\u001e\u0003{A\t!a\u0012\u0007\u0011\u0005-\u0013Q\bE\u0001\u0003\u001bBq!a\u0017\u0002\t\u0003\tiFB\u0004\u0002`\u0005\t\t#!\u0019\t\u000f\u0005m3\u0001\"\u0001\u0002p\u00191\u0011qO\u0001A\u0003sB!\"!'\u0006\u0005+\u0007I\u0011AAN\u0011)\t\u0019+\u0002B\tB\u0003%\u0011Q\u0014\u0005\b\u00037*A\u0011AAS\u0011%\tY+BA\u0001\n\u0003\ti\u000bC\u0005\u00022\u0016\t\n\u0011\"\u0001\u00024\"I\u0011\u0011Z\u0003\u0002\u0002\u0013\u0005\u00131\u001a\u0005\n\u0003;,\u0011\u0011!C\u0001\u00037C\u0011\"a8\u0006\u0003\u0003%\t!!9\t\u0013\u00055X!!A\u0005B\u0005=\b\"CA\u007f\u000b\u0005\u0005I\u0011AA\u0000\u0011%\u0011I!BA\u0001\n\u0003\u0012Y\u0001C\u0005\u0003\u0010\u0015\t\t\u0011\"\u0011\u0003\u0012!I!1C\u0003\u0002\u0002\u0013\u0005#Q\u0003\u0005\n\u0005/)\u0011\u0011!C!\u000539\u0011B!\u0012\u0002\u0003\u0003E\tAa\u0012\u0007\u0013\u0005]\u0014!!A\t\u0002\t%\u0003bBA.+\u0011\u0005!\u0011\r\u0005\n\u0005')\u0012\u0011!C#\u0005+A\u0011Ba\u0019\u0016\u0003\u0003%\tI!\u001a\t\u0013\t%T#!A\u0005\u0002\n-\u0004\"\u0003B<+\u0005\u0005I\u0011\u0002B=\r\u0019\u0011i\"\u0001!\u0003 !Q\u0011\u0011T\u000e\u0003\u0016\u0004%\t!a'\t\u0015\u0005\r6D!E!\u0002\u0013\ti\n\u0003\u0006\u0003\"m\u0011)\u001a!C\u0001\u00037C!Ba\t\u001c\u0005#\u0005\u000b\u0011BAO\u0011\u001d\tYf\u0007C\u0001\u0005KA\u0011\"a+\u001c\u0003\u0003%\tA!\f\t\u0013\u0005E6$%A\u0005\u0002\u0005M\u0006\"\u0003B\u001a7E\u0005I\u0011AAZ\u0011%\tImGA\u0001\n\u0003\nY\rC\u0005\u0002^n\t\t\u0011\"\u0001\u0002\u001c\"I\u0011q\\\u000e\u0002\u0002\u0013\u0005!Q\u0007\u0005\n\u0003[\\\u0012\u0011!C!\u0003_D\u0011\"!@\u001c\u0003\u0003%\tA!\u000f\t\u0013\t%1$!A\u0005B\tu\u0002\"\u0003B\b7\u0005\u0005I\u0011\tB\t\u0011%\u0011\u0019bGA\u0001\n\u0003\u0012)\u0002C\u0005\u0003\u0018m\t\t\u0011\"\u0011\u0003B\u001dI!\u0011Q\u0001\u0002\u0002#\u0005!1\u0011\u0004\n\u0005;\t\u0011\u0011!E\u0001\u0005\u000bCq!a\u0017/\t\u0003\u0011i\tC\u0005\u0003\u00149\n\t\u0011\"\u0012\u0003\u0016!I!1\r\u0018\u0002\u0002\u0013\u0005%q\u0012\u0005\n\u0005Sr\u0013\u0011!CA\u0005+C\u0011Ba\u001e/\u0003\u0003%IA!\u001f\t\u000f\t\u0005\u0016\u0001b\u0001\u0003$\"9!\u0011V\u0001\u0005\u0004\t-\u0006b\u0002B[\u0003\u0011\r!q\u0017\u0004\b\u0005w\u000b\u0011\u0011\u0005B_\u0011\u001d\tYf\u000eC\u0001\u0005\u007f;qa!\u001f\u0002\u0011\u0003\u001byGB\u0004\u0004j\u0005A\tia\u001b\t\u000f\u0005m#\b\"\u0001\u0004n!I\u0011\u0011\u001a\u001e\u0002\u0002\u0013\u0005\u00131\u001a\u0005\n\u0003;T\u0014\u0011!C\u0001\u00037C\u0011\"a8;\u0003\u0003%\ta!\u001d\t\u0013\u00055((!A\u0005B\u0005=\b\"CA\u007fu\u0005\u0005I\u0011AB;\u0011%\u0011yAOA\u0001\n\u0003\u0012\t\u0002C\u0005\u0003\u0014i\n\t\u0011\"\u0011\u0003\u0016!I!q\u000f\u001e\u0002\u0002\u0013%!\u0011P\u0004\b\u0007w\n\u0001\u0012\u0011Bf\r\u001d\u0011)-\u0001EA\u0005\u000fDq!a\u0017F\t\u0003\u0011I\rC\u0005\u0002J\u0016\u000b\t\u0011\"\u0011\u0002L\"I\u0011Q\\#\u0002\u0002\u0013\u0005\u00111\u0014\u0005\n\u0003?,\u0015\u0011!C\u0001\u0005\u001bD\u0011\"!<F\u0003\u0003%\t%a<\t\u0013\u0005uX)!A\u0005\u0002\tE\u0007\"\u0003B\b\u000b\u0006\u0005I\u0011\tB\t\u0011%\u0011\u0019\"RA\u0001\n\u0003\u0012)\u0002C\u0005\u0003x\u0015\u000b\t\u0011\"\u0003\u0003z\u001d91QP\u0001\t\u0002\nmha\u0002B{\u0003!\u0005%q\u001f\u0005\b\u00037\u0002F\u0011\u0001B}\u0011%\tI\rUA\u0001\n\u0003\nY\rC\u0005\u0002^B\u000b\t\u0011\"\u0001\u0002\u001c\"I\u0011q\u001c)\u0002\u0002\u0013\u0005!Q \u0005\n\u0003[\u0004\u0016\u0011!C!\u0003_D\u0011\"!@Q\u0003\u0003%\ta!\u0001\t\u0013\t=\u0001+!A\u0005B\tE\u0001\"\u0003B\n!\u0006\u0005I\u0011\tB\u000b\u0011%\u00119\bUA\u0001\n\u0013\u0011IhB\u0004\u0004\u0000\u0005A\tIa7\u0007\u000f\tU\u0017\u0001#!\u0003X\"9\u00111L.\u0005\u0002\te\u0007\"CAe7\u0006\u0005I\u0011IAf\u0011%\tinWA\u0001\n\u0003\tY\nC\u0005\u0002`n\u000b\t\u0011\"\u0001\u0003^\"I\u0011Q^.\u0002\u0002\u0013\u0005\u0013q\u001e\u0005\n\u0003{\\\u0016\u0011!C\u0001\u0005CD\u0011Ba\u0004\\\u0003\u0003%\tE!\u0005\t\u0013\tM1,!A\u0005B\tU\u0001\"\u0003B<7\u0006\u0005I\u0011\u0002B=\u000f\u001d\u0019\t)\u0001EA\u0005W4qA!:\u0002\u0011\u0003\u00139\u000fC\u0004\u0002\\\u0019$\tA!;\t\u0013\u0005%g-!A\u0005B\u0005-\u0007\"CAoM\u0006\u0005I\u0011AAN\u0011%\tyNZA\u0001\n\u0003\u0011i\u000fC\u0005\u0002n\u001a\f\t\u0011\"\u0011\u0002p\"I\u0011Q 4\u0002\u0002\u0013\u0005!\u0011\u001f\u0005\n\u0005\u001f1\u0017\u0011!C!\u0005#A\u0011Ba\u0005g\u0003\u0003%\tE!\u0006\t\u0013\t]d-!A\u0005\n\tedABB\u000b\u0003\u0001\u001b9\u0002\u0003\u0006\u0003(B\u0014)\u001a!C\u0001\u00077A!b!\fq\u0005#\u0005\u000b\u0011BB\u000f\u0011\u001d\tY\u0006\u001dC\u0001\u0007_A\u0011\"a+q\u0003\u0003%\ta!\u000e\t\u0013\u0005E\u0006/%A\u0005\u0002\r\u0005\u0003\"CAea\u0006\u0005I\u0011IAf\u0011%\ti\u000e]A\u0001\n\u0003\tY\nC\u0005\u0002`B\f\t\u0011\"\u0001\u0004J!I\u0011Q\u001e9\u0002\u0002\u0013\u0005\u0013q\u001e\u0005\n\u0003{\u0004\u0018\u0011!C\u0001\u0007\u001bB\u0011B!\u0003q\u0003\u0003%\te!\u0015\t\u0013\t=\u0001/!A\u0005B\tE\u0001\"\u0003B\na\u0006\u0005I\u0011\tB\u000b\u0011%\u00119\u0002]A\u0001\n\u0003\u001a)fB\u0005\u0004\u0004\u0006\t\t\u0011#\u0001\u0004\u0006\u001aI1QC\u0001\u0002\u0002#\u00051q\u0011\u0005\t\u00037\n\t\u0001\"\u0001\u0004\n\"Q!1CA\u0001\u0003\u0003%)E!\u0006\t\u0015\t\r\u0014\u0011AA\u0001\n\u0003\u001bY\t\u0003\u0006\u0003j\u0005\u0005\u0011\u0011!CA\u0007/C!Ba\u001e\u0002\u0002\u0005\u0005I\u0011\u0002B=\u000f\u001d\u0019)+\u0001EA\u0007?2qa!\u0017\u0002\u0011\u0003\u001bY\u0006\u0003\u0005\u0002\\\u0005=A\u0011AB/\u0011)\tI-a\u0004\u0002\u0002\u0013\u0005\u00131\u001a\u0005\u000b\u0003;\fy!!A\u0005\u0002\u0005m\u0005BCAp\u0003\u001f\t\t\u0011\"\u0001\u0004b!Q\u0011Q^A\b\u0003\u0003%\t%a<\t\u0015\u0005u\u0018qBA\u0001\n\u0003\u0019)\u0007\u0003\u0006\u0003\u0010\u0005=\u0011\u0011!C!\u0005#A!Ba\u0005\u0002\u0010\u0005\u0005I\u0011\tB\u000b\u0011)\u00119(a\u0004\u0002\u0002\u0013%!\u0011P\u0004\b\u0007O\u000b\u0001\u0012QB\u0006\r\u001d\u0019)!\u0001EA\u0007\u000fA\u0001\"a\u0017\u0002&\u0011\u00051\u0011\u0002\u0005\u000b\u0003\u0013\f)#!A\u0005B\u0005-\u0007BCAo\u0003K\t\t\u0011\"\u0001\u0002\u001c\"Q\u0011q\\A\u0013\u0003\u0003%\ta!\u0004\t\u0015\u00055\u0018QEA\u0001\n\u0003\ny\u000f\u0003\u0006\u0002~\u0006\u0015\u0012\u0011!C\u0001\u0007#A!Ba\u0004\u0002&\u0005\u0005I\u0011\tB\t\u0011)\u0011\u0019\"!\n\u0002\u0002\u0013\u0005#Q\u0003\u0005\u000b\u0005o\n)#!A\u0005\n\te\u0004bBBU\u0003\u0011\r11V\u0001\b\u001fB$\u0018n\u001c8t\u0015\u0011\ty$!\u0011\u0002\r1Lg.\u00197h\u0015\t\t\u0019%\u0001\u0004ce\u0016,'0Z\u0002\u0001!\r\tI%A\u0007\u0003\u0003{\u0011qa\u00149uS>t7oE\u0002\u0002\u0003\u001f\u0002B!!\u0015\u0002X5\u0011\u00111\u000b\u0006\u0003\u0003+\nQa]2bY\u0006LA!!\u0017\u0002T\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtDCAA$\u0005Ay\u0005\u000f\u001e)bI\u0012KW.\u001a8tS>t7oE\u0002\u0004\u0003G\u0002B!!\u001a\u0002l5\u0011\u0011q\r\u0006\u0005\u0003S\n\t%\u0001\u0003vi&d\u0017\u0002BA7\u0003O\u00121a\u00149u)\t\t\t\bE\u0002\u0002t\ri\u0011!A\u0015\u0004\u0007\u0015Y\"a\u0003#j[\u0016t7/[8ogF\u001ar!BA9\u0003w\n\t\t\u0005\u0003\u0002R\u0005u\u0014\u0002BA@\u0003'\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0003\u0002\u0004\u0006Me\u0002BAC\u0003\u001fsA!a\"\u0002\u000e6\u0011\u0011\u0011\u0012\u0006\u0005\u0003\u0017\u000b)%\u0001\u0004=e>|GOP\u0005\u0003\u0003+JA!!%\u0002T\u00059\u0001/Y2lC\u001e,\u0017\u0002BAK\u0003/\u0013AbU3sS\u0006d\u0017N_1cY\u0016TA!!%\u0002T\u0005\u0011a.M\u000b\u0003\u0003;\u0003B!!\u0015\u0002 &!\u0011\u0011UA*\u0005\rIe\u000e^\u0001\u0004]F\u0002C\u0003BAT\u0003S\u00032!a\u001d\u0006\u0011\u001d\tI\n\u0003a\u0001\u0003;\u000bAaY8qsR!\u0011qUAX\u0011%\tI*\u0003I\u0001\u0002\u0004\ti*\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005U&\u0006BAO\u0003o[#!!/\u0011\t\u0005m\u0016QY\u0007\u0003\u0003{SA!a0\u0002B\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0005\u0003\u0007\f\u0019&\u0001\u0006b]:|G/\u0019;j_:LA!a2\u0002>\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\ti\r\u0005\u0003\u0002P\u0006eWBAAi\u0015\u0011\t\u0019.!6\u0002\t1\fgn\u001a\u0006\u0003\u0003/\fAA[1wC&!\u00111\\Ai\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BAr\u0003S\u0004B!!\u0015\u0002f&!\u0011q]A*\u0005\r\te.\u001f\u0005\n\u0003Wl\u0011\u0011!a\u0001\u0003;\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAAy!\u0019\t\u00190!?\u0002d6\u0011\u0011Q\u001f\u0006\u0005\u0003o\f\u0019&\u0001\u0006d_2dWm\u0019;j_:LA!a?\u0002v\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\u0011\tAa\u0002\u0011\t\u0005E#1A\u0005\u0005\u0005\u000b\t\u0019FA\u0004C_>dW-\u00198\t\u0013\u0005-x\"!AA\u0002\u0005\r\u0018A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!4\u0003\u000e!I\u00111\u001e\t\u0002\u0002\u0003\u0007\u0011QT\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011QT\u0001\ti>\u001cFO]5oOR\u0011\u0011QZ\u0001\u0007KF,\u0018\r\\:\u0015\t\t\u0005!1\u0004\u0005\n\u0003W\u001c\u0012\u0011!a\u0001\u0003G\u00141\u0002R5nK:\u001c\u0018n\u001c8teM91$!\u001d\u0002|\u0005\u0005\u0015A\u000183\u0003\rq'\u0007\t\u000b\u0007\u0005O\u0011ICa\u000b\u0011\u0007\u0005M4\u0004C\u0004\u0002\u001a\u0002\u0002\r!!(\t\u000f\t\u0005\u0002\u00051\u0001\u0002\u001eR1!q\u0005B\u0018\u0005cA\u0011\"!'\"!\u0003\u0005\r!!(\t\u0013\t\u0005\u0012\u0005%AA\u0002\u0005u\u0015AD2paf$C-\u001a4bk2$HE\r\u000b\u0005\u0003G\u00149\u0004C\u0005\u0002l\u001a\n\t\u00111\u0001\u0002\u001eR!!\u0011\u0001B\u001e\u0011%\tY\u000fKA\u0001\u0002\u0004\t\u0019\u000f\u0006\u0003\u0002N\n}\u0002\"CAvS\u0005\u0005\t\u0019AAO)\u0011\u0011\tAa\u0011\t\u0013\u0005-H&!AA\u0002\u0005\r\u0018a\u0003#j[\u0016t7/[8ogF\u00022!a\u001d\u0016'\u0015)\"1\nB,!!\u0011iEa\u0015\u0002\u001e\u0006\u001dVB\u0001B(\u0015\u0011\u0011\t&a\u0015\u0002\u000fI,h\u000e^5nK&!!Q\u000bB(\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\t\u0005\u00053\u0012y&\u0004\u0002\u0003\\)!!QLAk\u0003\tIw.\u0003\u0003\u0002\u0016\nmCC\u0001B$\u0003\u0015\t\u0007\u000f\u001d7z)\u0011\t9Ka\u001a\t\u000f\u0005e\u0005\u00041\u0001\u0002\u001e\u00069QO\\1qa2LH\u0003\u0002B7\u0005g\u0002b!!\u0015\u0003p\u0005u\u0015\u0002\u0002B9\u0003'\u0012aa\u00149uS>t\u0007\"\u0003B;3\u0005\u0005\t\u0019AAT\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005w\u0002B!a4\u0003~%!!qPAi\u0005\u0019y%M[3di\u0006YA)[7f]NLwN\\:3!\r\t\u0019HL\n\u0006]\t\u001d%q\u000b\t\u000b\u0005\u001b\u0012I)!(\u0002\u001e\n\u001d\u0012\u0002\u0002BF\u0005\u001f\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83)\t\u0011\u0019\t\u0006\u0004\u0003(\tE%1\u0013\u0005\b\u00033\u000b\u0004\u0019AAO\u0011\u001d\u0011\t#\ra\u0001\u0003;#BAa&\u0003 B1\u0011\u0011\u000bB8\u00053\u0003\u0002\"!\u0015\u0003\u001c\u0006u\u0015QT\u0005\u0005\u0005;\u000b\u0019F\u0001\u0004UkBdWM\r\u0005\n\u0005k\u0012\u0014\u0011!a\u0001\u0005O\t\u0001#\u001b8u)>$\u0015.\\3og&|gn]\u0019\u0015\t\u0005\u001d&Q\u0015\u0005\b\u0005O#\u0004\u0019AAO\u0003\u0005q\u0017a\u0004;2)>$\u0015.\\3og&|gn]\u0019\u0015\t\u0005\u001d&Q\u0016\u0005\b\u0005O+\u0004\u0019\u0001BX!\u0019\t\tF!-\u0002\u001e&!!1WA*\u0005\u0019!V\u000f\u001d7fc\u0005yAO\r+p\t&lWM\\:j_:\u001c(\u0007\u0006\u0003\u0003(\te\u0006b\u0002BTm\u0001\u0007!\u0011\u0014\u0002\u000b\u001fB$\b+\u00193N_\u0012,7cA\u001c\u0002dQ\u0011!\u0011\u0019\t\u0004\u0003g:\u0014fC\u001cF7\u001a\u0004\u0016Q\u00059\u0002\u0010i\u00121!T1y'\u001d)%\u0011YA>\u0003\u0003#\"Aa3\u0011\u0007\u0005MT\t\u0006\u0003\u0002d\n=\u0007\"CAv\u0013\u0006\u0005\t\u0019AAO)\u0011\u0011\tAa5\t\u0013\u0005-8*!AA\u0002\u0005\r(\u0001B'fC:\u001cra\u0017Ba\u0003w\n\t\t\u0006\u0002\u0003\\B\u0019\u00111O.\u0015\t\u0005\r(q\u001c\u0005\n\u0003W|\u0016\u0011!a\u0001\u0003;#BA!\u0001\u0003d\"I\u00111^1\u0002\u0002\u0003\u0007\u00111\u001d\u0002\u0007\u001b\u0016$\u0017.\u00198\u0014\u000f\u0019\u0014\t-a\u001f\u0002\u0002R\u0011!1\u001e\t\u0004\u0003g2G\u0003BAr\u0005_D\u0011\"a;k\u0003\u0003\u0005\r!!(\u0015\t\t\u0005!1\u001f\u0005\n\u0003Wd\u0017\u0011!a\u0001\u0003G\u00141!T5o'\u001d\u0001&\u0011YA>\u0003\u0003#\"Aa?\u0011\u0007\u0005M\u0004\u000b\u0006\u0003\u0002d\n}\b\"CAv)\u0006\u0005\t\u0019AAO)\u0011\u0011\taa\u0001\t\u0013\u0005-h+!AA\u0002\u0005\r(a\u0002*fM2,7\r^\n\t\u0003K\u0011\t-a\u001f\u0002\u0002R\u001111\u0002\t\u0005\u0003g\n)\u0003\u0006\u0003\u0002d\u000e=\u0001BCAv\u0003[\t\t\u00111\u0001\u0002\u001eR!!\u0011AB\n\u0011)\tY/!\r\u0002\u0002\u0003\u0007\u00111\u001d\u0002\u0006-\u0006dW/Z\u000b\u0005\u00073\u0019\tcE\u0004q\u0005\u0003\fY(!!\u0016\u0005\ru\u0001\u0003BB\u0010\u0007Ca\u0001\u0001B\u0004\u0004$A\u0014\ra!\n\u0003\u0003Q\u000bBaa\n\u0002dB!\u0011\u0011KB\u0015\u0013\u0011\u0019Y#a\u0015\u0003\u000f9{G\u000f[5oO\u0006\u0011a\u000e\t\u000b\u0005\u0007c\u0019\u0019\u0004E\u0003\u0002tA\u001ci\u0002C\u0004\u0003(N\u0004\ra!\b\u0016\t\r]2Q\b\u000b\u0005\u0007s\u0019y\u0004E\u0003\u0002tA\u001cY\u0004\u0005\u0003\u0004 \ruBaBB\u0012i\n\u00071Q\u0005\u0005\n\u0005O#\b\u0013!a\u0001\u0007w)Baa\u0011\u0004HU\u00111Q\t\u0016\u0005\u0007;\t9\fB\u0004\u0004$U\u0014\ra!\n\u0015\t\u0005\r81\n\u0005\n\u0003WD\u0018\u0011!a\u0001\u0003;#BA!\u0001\u0004P!I\u00111\u001e>\u0002\u0002\u0003\u0007\u00111\u001d\u000b\u0005\u0003\u001b\u001c\u0019\u0006C\u0005\u0002ln\f\t\u00111\u0001\u0002\u001eR!!\u0011AB,\u0011%\tYO`A\u0001\u0002\u0004\t\u0019O\u0001\u0003Xe\u0006\u00048\u0003CA\b\u0005\u0003\fY(!!\u0015\u0005\r}\u0003\u0003BA:\u0003\u001f!B!a9\u0004d!Q\u00111^A\f\u0003\u0003\u0005\r!!(\u0015\t\t\u00051q\r\u0005\u000b\u0003W\fY\"!AA\u0002\u0005\r(\u0001\u0002.fe>\u001crA\u000fBa\u0003w\n\t\t\u0006\u0002\u0004pA\u0019\u00111\u000f\u001e\u0015\t\u0005\r81\u000f\u0005\n\u0003Wt\u0014\u0011!a\u0001\u0003;#BA!\u0001\u0004x!I\u00111\u001e!\u0002\u0002\u0003\u0007\u00111]\u0001\u00055\u0016\u0014x.A\u0002NCb\f1!T5o\u0003\u0011iU-\u00198\u0002\r5+G-[1o\u0003\u00151\u0016\r\\;f!\u0011\t\u0019(!\u0001\u0014\r\u0005\u0005\u0011q\nB,)\t\u0019))\u0006\u0003\u0004\u000e\u000eME\u0003BBH\u0007+\u0003R!a\u001dq\u0007#\u0003Baa\b\u0004\u0014\u0012A11EA\u0004\u0005\u0004\u0019)\u0003\u0003\u0005\u0003(\u0006\u001d\u0001\u0019ABI+\u0011\u0019Ija(\u0015\t\rm5\u0011\u0015\t\u0007\u0003#\u0012yg!(\u0011\t\r}1q\u0014\u0003\t\u0007G\tIA1\u0001\u0004&!Q!QOA\u0005\u0003\u0003\u0005\raa)\u0011\u000b\u0005M\u0004o!(\u0002\t]\u0013\u0018\r]\u0001\b%\u00164G.Z2u\u0003=!Hk\\(qi6{G-\u001a,bYV,W\u0003BBW\u0007g#BA!1\u00040\"A!qUA\u001d\u0001\u0004\u0019\t\f\u0005\u0003\u0004 \rMF\u0001CB\u0012\u0003s\u0011\ra!\n"
)
public final class Options {
   public static OptPadMode tToOptModeValue(final Object n) {
      return Options$.MODULE$.tToOptModeValue(n);
   }

   public static Dimensions2 t2ToDimensions2(final Tuple2 n) {
      return Options$.MODULE$.t2ToDimensions2(n);
   }

   public static Dimensions1 t1ToDimensions1(final Tuple1 n) {
      return Options$.MODULE$.t1ToDimensions1(n);
   }

   public static Dimensions1 intToDimensions1(final int n) {
      return Options$.MODULE$.intToDimensions1(n);
   }

   public abstract static class OptPadDimensions extends Opt {
   }

   public static class Dimensions1 extends OptPadDimensions implements Product, Serializable {
      private final int n1;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int n1() {
         return this.n1;
      }

      public Dimensions1 copy(final int n1) {
         return new Dimensions1(n1);
      }

      public int copy$default$1() {
         return this.n1();
      }

      public String productPrefix() {
         return "Dimensions1";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToInteger(this.n1());
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Dimensions1;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "n1";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.n1());
         return Statics.finalizeHash(var1, 1);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label49: {
               boolean var2;
               if (x$1 instanceof Dimensions1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  Dimensions1 var4 = (Dimensions1)x$1;
                  if (this.n1() == var4.n1() && var4.canEqual(this)) {
                     break label49;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public Dimensions1(final int n1) {
         this.n1 = n1;
         Product.$init$(this);
      }
   }

   public static class Dimensions1$ extends AbstractFunction1 implements Serializable {
      public static final Dimensions1$ MODULE$ = new Dimensions1$();

      public final String toString() {
         return "Dimensions1";
      }

      public Dimensions1 apply(final int n1) {
         return new Dimensions1(n1);
      }

      public Option unapply(final Dimensions1 x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.n1())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Dimensions1$.class);
      }
   }

   public static class Dimensions2 extends OptPadDimensions implements Product, Serializable {
      private final int n1;
      private final int n2;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int n1() {
         return this.n1;
      }

      public int n2() {
         return this.n2;
      }

      public Dimensions2 copy(final int n1, final int n2) {
         return new Dimensions2(n1, n2);
      }

      public int copy$default$1() {
         return this.n1();
      }

      public int copy$default$2() {
         return this.n2();
      }

      public String productPrefix() {
         return "Dimensions2";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToInteger(this.n1());
               break;
            case 1:
               var10000 = BoxesRunTime.boxToInteger(this.n2());
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Dimensions2;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "n1";
               break;
            case 1:
               var10000 = "n2";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.n1());
         var1 = Statics.mix(var1, this.n2());
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label51: {
               boolean var2;
               if (x$1 instanceof Dimensions2) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  Dimensions2 var4 = (Dimensions2)x$1;
                  if (this.n1() == var4.n1() && this.n2() == var4.n2() && var4.canEqual(this)) {
                     break label51;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public Dimensions2(final int n1, final int n2) {
         this.n1 = n1;
         this.n2 = n2;
         Product.$init$(this);
      }
   }

   public static class Dimensions2$ extends AbstractFunction2 implements Serializable {
      public static final Dimensions2$ MODULE$ = new Dimensions2$();

      public final String toString() {
         return "Dimensions2";
      }

      public Dimensions2 apply(final int n1, final int n2) {
         return new Dimensions2(n1, n2);
      }

      public Option unapply(final Dimensions2 x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2.mcII.sp(x$0.n1(), x$0.n2())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Dimensions2$.class);
      }
   }

   public abstract static class OptPadMode extends Opt {
   }

   public static class Zero$ extends OptPadMode implements Product, Serializable {
      public static final Zero$ MODULE$ = new Zero$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Zero";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Zero$;
      }

      public int hashCode() {
         return 2781896;
      }

      public String toString() {
         return "Zero";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Zero$.class);
      }
   }

   public static class Max$ extends OptPadMode implements Product, Serializable {
      public static final Max$ MODULE$ = new Max$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Max";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Max$;
      }

      public int hashCode() {
         return 77124;
      }

      public String toString() {
         return "Max";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Max$.class);
      }
   }

   public static class Min$ extends OptPadMode implements Product, Serializable {
      public static final Min$ MODULE$ = new Min$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Min";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Min$;
      }

      public int hashCode() {
         return 77362;
      }

      public String toString() {
         return "Min";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Min$.class);
      }
   }

   public static class Mean$ extends OptPadMode implements Product, Serializable {
      public static final Mean$ MODULE$ = new Mean$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Mean";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Mean$;
      }

      public int hashCode() {
         return 2394085;
      }

      public String toString() {
         return "Mean";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Mean$.class);
      }
   }

   public static class Median$ extends OptPadMode implements Product, Serializable {
      public static final Median$ MODULE$ = new Median$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Median";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Median$;
      }

      public int hashCode() {
         return -1994163926;
      }

      public String toString() {
         return "Median";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Median$.class);
      }
   }

   public static class Value extends OptPadMode implements Product, Serializable {
      private final Object n;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object n() {
         return this.n;
      }

      public Value copy(final Object n) {
         return new Value(n);
      }

      public Object copy$default$1() {
         return this.n();
      }

      public String productPrefix() {
         return "Value";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.n();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Value;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "n";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label49: {
               boolean var2;
               if (x$1 instanceof Value) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  Value var4 = (Value)x$1;
                  if (BoxesRunTime.equals(this.n(), var4.n()) && var4.canEqual(this)) {
                     break label49;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public Value(final Object n) {
         this.n = n;
         Product.$init$(this);
      }
   }

   public static class Value$ implements Serializable {
      public static final Value$ MODULE$ = new Value$();

      public final String toString() {
         return "Value";
      }

      public Value apply(final Object n) {
         return new Value(n);
      }

      public Option unapply(final Value x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.n()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Value$.class);
      }
   }

   public static class Wrap$ extends OptPadMode implements Product, Serializable {
      public static final Wrap$ MODULE$ = new Wrap$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Wrap";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Wrap$;
      }

      public int hashCode() {
         return 2704490;
      }

      public String toString() {
         return "Wrap";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Wrap$.class);
      }
   }

   public static class Reflect$ extends OptPadMode implements Product, Serializable {
      public static final Reflect$ MODULE$ = new Reflect$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Reflect";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Reflect$;
      }

      public int hashCode() {
         return -1545048419;
      }

      public String toString() {
         return "Reflect";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Reflect$.class);
      }
   }
}
