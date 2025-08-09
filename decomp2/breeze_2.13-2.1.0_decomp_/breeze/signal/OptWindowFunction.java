package breeze.signal;

import breeze.linalg.DenseVector;
import breeze.util.Opt;
import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.AbstractFunction2;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\r=b\u0001CA\u0003\u0003\u000f\t\t!!\u0005\t\u000f\u0005}\u0001\u0001\"\u0001\u0002\"\u001dA\u0011qEA\u0004\u0011\u0003\tIC\u0002\u0005\u0002\u0006\u0005\u001d\u0001\u0012AA\u0016\u0011\u001d\tyb\u0001C\u0001\u0003s1a!a\u000f\u0004\u0001\u0006u\u0002BCA/\u000b\tU\r\u0011\"\u0001\u0002`!Q\u0011qM\u0003\u0003\u0012\u0003\u0006I!!\u0019\t\u0015\u0005%TA!f\u0001\n\u0003\ty\u0006\u0003\u0006\u0002l\u0015\u0011\t\u0012)A\u0005\u0003CBq!a\b\u0006\t\u0003\ti\u0007C\u0004\u0002x\u0015!\t%!\u001f\t\u0013\u0005-U!!A\u0005\u0002\u00055\u0005\"CAJ\u000bE\u0005I\u0011AAK\u0011%\tY+BI\u0001\n\u0003\t)\nC\u0005\u0002.\u0016\t\t\u0011\"\u0011\u00020\"I\u0011\u0011W\u0003\u0002\u0002\u0013\u0005\u00111\u0017\u0005\n\u0003w+\u0011\u0011!C\u0001\u0003{C\u0011\"!3\u0006\u0003\u0003%\t%a3\t\u0013\u0005eW!!A\u0005\u0002\u0005m\u0007\"CAs\u000b\u0005\u0005I\u0011IAt\u0011%\tY/BA\u0001\n\u0003\ni\u000fC\u0005\u0002p\u0016\t\t\u0011\"\u0011\u0002r\u001eI\u0011Q_\u0002\u0002\u0002#\u0005\u0011q\u001f\u0004\n\u0003w\u0019\u0011\u0011!E\u0001\u0003sDq!a\b\u0019\t\u0003\u0011\t\u0002C\u0005\u0002xa\t\t\u0011\"\u0012\u0002z!I!1\u0003\r\u0002\u0002\u0013\u0005%Q\u0003\u0005\n\u00057A\u0012\u0013!C\u0001\u0003+C\u0011B!\b\u0019#\u0003%\t!!&\t\u0013\t}\u0001$!A\u0005\u0002\n\u0005\u0002\"\u0003B\u001a1E\u0005I\u0011AAK\u0011%\u0011)\u0004GI\u0001\n\u0003\t)\nC\u0005\u00038a\t\t\u0011\"\u0003\u0003:\u00191!\u0011I\u0002A\u0005\u0007B!\"!\u0018#\u0005+\u0007I\u0011AA0\u0011)\t9G\tB\tB\u0003%\u0011\u0011\r\u0005\u000b\u0003S\u0012#Q3A\u0005\u0002\u0005}\u0003BCA6E\tE\t\u0015!\u0003\u0002b!9\u0011q\u0004\u0012\u0005\u0002\t\u0015\u0003bBA<E\u0011\u0005\u0013\u0011\u0010\u0005\n\u0003\u0017\u0013\u0013\u0011!C\u0001\u0005\u001bB\u0011\"a%##\u0003%\t!!&\t\u0013\u0005-&%%A\u0005\u0002\u0005U\u0005\"CAWE\u0005\u0005I\u0011IAX\u0011%\t\tLIA\u0001\n\u0003\t\u0019\fC\u0005\u0002<\n\n\t\u0011\"\u0001\u0003T!I\u0011\u0011\u001a\u0012\u0002\u0002\u0013\u0005\u00131\u001a\u0005\n\u00033\u0014\u0013\u0011!C\u0001\u0005/B\u0011\"!:#\u0003\u0003%\tEa\u0017\t\u0013\u0005-(%!A\u0005B\u00055\b\"CAxE\u0005\u0005I\u0011\tB0\u000f%\u0011\u0019gAA\u0001\u0012\u0003\u0011)GB\u0005\u0003B\r\t\t\u0011#\u0001\u0003h!9\u0011qD\u001b\u0005\u0002\t-\u0004\"CA<k\u0005\u0005IQIA=\u0011%\u0011\u0019\"NA\u0001\n\u0003\u0013i\u0007C\u0005\u0003\u001cU\n\n\u0011\"\u0001\u0002\u0016\"I!QD\u001b\u0012\u0002\u0013\u0005\u0011Q\u0013\u0005\n\u0005?)\u0014\u0011!CA\u0005gB\u0011Ba\r6#\u0003%\t!!&\t\u0013\tUR'%A\u0005\u0002\u0005U\u0005\"\u0003B\u001ck\u0005\u0005I\u0011\u0002B\u001d\r\u0019\u00119h\u0001!\u0003z!Q!1P \u0003\u0016\u0004%\t!a\u0018\t\u0015\tutH!E!\u0002\u0013\t\t\u0007\u0003\u0006\u0003\u0000}\u0012)\u001a!C\u0001\u0003?B!B!!@\u0005#\u0005\u000b\u0011BA1\u0011)\u0011\u0019i\u0010BK\u0002\u0013\u0005\u0011q\f\u0005\u000b\u0005\u000b{$\u0011#Q\u0001\n\u0005\u0005\u0004bBA\u0010\u007f\u0011\u0005!q\u0011\u0005\b\u0003ozD\u0011IA=\u0011%\tYiPA\u0001\n\u0003\u0011\t\nC\u0005\u0002\u0014~\n\n\u0011\"\u0001\u0002\u0016\"I\u00111V \u0012\u0002\u0013\u0005\u0011Q\u0013\u0005\n\u00053{\u0014\u0013!C\u0001\u0003+C\u0011\"!,@\u0003\u0003%\t%a,\t\u0013\u0005Ev(!A\u0005\u0002\u0005M\u0006\"CA^\u007f\u0005\u0005I\u0011\u0001BN\u0011%\tImPA\u0001\n\u0003\nY\rC\u0005\u0002Z~\n\t\u0011\"\u0001\u0003 \"I\u0011Q] \u0002\u0002\u0013\u0005#1\u0015\u0005\n\u0003W|\u0014\u0011!C!\u0003[D\u0011\"a<@\u0003\u0003%\tEa*\b\u0013\t-6!!A\t\u0002\t5f!\u0003B<\u0007\u0005\u0005\t\u0012\u0001BX\u0011\u001d\ty\"\u0016C\u0001\u0005oC\u0011\"a\u001eV\u0003\u0003%)%!\u001f\t\u0013\tMQ+!A\u0005\u0002\ne\u0006\"\u0003B\u000e+F\u0005I\u0011AAK\u0011%\u0011i\"VI\u0001\n\u0003\t)\nC\u0005\u0003BV\u000b\n\u0011\"\u0001\u0002\u0016\"I!qD+\u0002\u0002\u0013\u0005%1\u0019\u0005\n\u0005g)\u0016\u0013!C\u0001\u0003+C\u0011B!\u000eV#\u0003%\t!!&\t\u0013\t=W+%A\u0005\u0002\u0005U\u0005\"\u0003B\u001c+\u0006\u0005I\u0011\u0002B\u001d\r\u0019\u0011\tn\u0001!\u0003T\"Q!Q[1\u0003\u0016\u0004%\tAa6\t\u0015\t\u0015\u0018M!E!\u0002\u0013\u0011I\u000eC\u0004\u0002 \u0005$\tAa:\t\u000f\u0005]\u0014\r\"\u0011\u0002z!I\u00111R1\u0002\u0002\u0013\u0005!Q\u001e\u0005\n\u0003'\u000b\u0017\u0013!C\u0001\u0005cD\u0011\"!,b\u0003\u0003%\t%a,\t\u0013\u0005E\u0016-!A\u0005\u0002\u0005M\u0006\"CA^C\u0006\u0005I\u0011\u0001B{\u0011%\tI-YA\u0001\n\u0003\nY\rC\u0005\u0002Z\u0006\f\t\u0011\"\u0001\u0003z\"I\u0011Q]1\u0002\u0002\u0013\u0005#Q \u0005\n\u0003W\f\u0017\u0011!C!\u0003[D\u0011\"a<b\u0003\u0003%\te!\u0001\b\u0013\r\u00151!!A\t\u0002\r\u001da!\u0003Bi\u0007\u0005\u0005\t\u0012AB\u0005\u0011\u001d\ty\"\u001dC\u0001\u0007#A\u0011\"a\u001er\u0003\u0003%)%!\u001f\t\u0013\tM\u0011/!A\u0005\u0002\u000eM\u0001\"\u0003B\u0010c\u0006\u0005I\u0011QB\f\u0011%\u00119$]A\u0001\n\u0013\u0011IdB\u0004\u0004\u001e\rA\tia\b\u0007\u000f\r\u00052\u0001#!\u0004$!9\u0011q\u0004=\u0005\u0002\r\u0015\u0002bBA<q\u0012\u0005\u0013\u0011\u0010\u0005\n\u0003[C\u0018\u0011!C!\u0003_C\u0011\"!-y\u0003\u0003%\t!a-\t\u0013\u0005m\u00060!A\u0005\u0002\r\u001d\u0002\"CAeq\u0006\u0005I\u0011IAf\u0011%\tI\u000e_A\u0001\n\u0003\u0019Y\u0003C\u0005\u0002lb\f\t\u0011\"\u0011\u0002n\"I!q\u0007=\u0002\u0002\u0013%!\u0011\b\u0002\u0012\u001fB$x+\u001b8e_^4UO\\2uS>t'\u0002BA\u0005\u0003\u0017\taa]5h]\u0006d'BAA\u0007\u0003\u0019\u0011'/Z3{K\u000e\u00011c\u0001\u0001\u0002\u0014A!\u0011QCA\u000e\u001b\t\t9B\u0003\u0003\u0002\u001a\u0005-\u0011\u0001B;uS2LA!!\b\u0002\u0018\t\u0019q\n\u001d;\u0002\rqJg.\u001b;?)\t\t\u0019\u0003E\u0002\u0002&\u0001i!!a\u0002\u0002#=\u0003HoV5oI><h)\u001e8di&|g\u000eE\u0002\u0002&\r\u00192aAA\u0017!\u0011\ty#!\u000e\u000e\u0005\u0005E\"BAA\u001a\u0003\u0015\u00198-\u00197b\u0013\u0011\t9$!\r\u0003\r\u0005s\u0017PU3g)\t\tICA\u0004IC6l\u0017N\\4\u0014\u000f\u0015\t\u0019#a\u0010\u0002FA!\u0011qFA!\u0013\u0011\t\u0019%!\r\u0003\u000fA\u0013x\u000eZ;diB!\u0011qIA,\u001d\u0011\tI%a\u0015\u000f\t\u0005-\u0013\u0011K\u0007\u0003\u0003\u001bRA!a\u0014\u0002\u0010\u00051AH]8pizJ!!a\r\n\t\u0005U\u0013\u0011G\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\tI&a\u0017\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\t\u0005U\u0013\u0011G\u0001\u0006C2\u0004\b.Y\u000b\u0003\u0003C\u0002B!a\f\u0002d%!\u0011QMA\u0019\u0005\u0019!u.\u001e2mK\u00061\u0011\r\u001c9iC\u0002\nAAY3uC\u0006)!-\u001a;bAQ1\u0011qNA:\u0003k\u00022!!\u001d\u0006\u001b\u0005\u0019\u0001\"CA/\u0015A\u0005\t\u0019AA1\u0011%\tIG\u0003I\u0001\u0002\u0004\t\t'\u0001\u0005u_N#(/\u001b8h)\t\tY\b\u0005\u0003\u0002~\u0005\u001dUBAA@\u0015\u0011\t\t)a!\u0002\t1\fgn\u001a\u0006\u0003\u0003\u000b\u000bAA[1wC&!\u0011\u0011RA@\u0005\u0019\u0019FO]5oO\u0006!1m\u001c9z)\u0019\ty'a$\u0002\u0012\"I\u0011Q\f\u0007\u0011\u0002\u0003\u0007\u0011\u0011\r\u0005\n\u0003Sb\u0001\u0013!a\u0001\u0003C\nabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002\u0018*\"\u0011\u0011MAMW\t\tY\n\u0005\u0003\u0002\u001e\u0006\u001dVBAAP\u0015\u0011\t\t+a)\u0002\u0013Ut7\r[3dW\u0016$'\u0002BAS\u0003c\t!\"\u00198o_R\fG/[8o\u0013\u0011\tI+a(\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\tY(\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u00026B!\u0011qFA\\\u0013\u0011\tI,!\r\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005}\u0016Q\u0019\t\u0005\u0003_\t\t-\u0003\u0003\u0002D\u0006E\"aA!os\"I\u0011qY\t\u0002\u0002\u0003\u0007\u0011QW\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u00055\u0007CBAh\u0003+\fy,\u0004\u0002\u0002R*!\u00111[A\u0019\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003/\f\tN\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BAo\u0003G\u0004B!a\f\u0002`&!\u0011\u0011]A\u0019\u0005\u001d\u0011un\u001c7fC:D\u0011\"a2\u0014\u0003\u0003\u0005\r!a0\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003w\nI\u000fC\u0005\u0002HR\t\t\u00111\u0001\u00026\u0006A\u0001.Y:i\u0007>$W\r\u0006\u0002\u00026\u00061Q-];bYN$B!!8\u0002t\"I\u0011q\u0019\f\u0002\u0002\u0003\u0007\u0011qX\u0001\b\u0011\u0006lW.\u001b8h!\r\t\t\bG\n\u00061\u0005m(q\u0001\t\u000b\u0003{\u0014\u0019!!\u0019\u0002b\u0005=TBAA\u0000\u0015\u0011\u0011\t!!\r\u0002\u000fI,h\u000e^5nK&!!QAA\u0000\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005\u0005\u0013\u0011y!\u0004\u0002\u0003\f)!!QBAB\u0003\tIw.\u0003\u0003\u0002Z\t-ACAA|\u0003\u0015\t\u0007\u000f\u001d7z)\u0019\tyGa\u0006\u0003\u001a!I\u0011QL\u000e\u0011\u0002\u0003\u0007\u0011\u0011\r\u0005\n\u0003SZ\u0002\u0013!a\u0001\u0003C\nq\"\u00199qYf$C-\u001a4bk2$H%M\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u00059QO\\1qa2LH\u0003\u0002B\u0012\u0005_\u0001b!a\f\u0003&\t%\u0012\u0002\u0002B\u0014\u0003c\u0011aa\u00149uS>t\u0007\u0003CA\u0018\u0005W\t\t'!\u0019\n\t\t5\u0012\u0011\u0007\u0002\u0007)V\u0004H.\u001a\u001a\t\u0013\tEb$!AA\u0002\u0005=\u0014a\u0001=%a\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u0012\u0014\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B\u001e!\u0011\tiH!\u0010\n\t\t}\u0012q\u0010\u0002\u0007\u001f\nTWm\u0019;\u0003\u000f!\u000bgN\\5oON9!%a\t\u0002@\u0005\u0015CC\u0002B$\u0005\u0013\u0012Y\u0005E\u0002\u0002r\tB\u0011\"!\u0018(!\u0003\u0005\r!!\u0019\t\u0013\u0005%t\u0005%AA\u0002\u0005\u0005DC\u0002B$\u0005\u001f\u0012\t\u0006C\u0005\u0002^%\u0002\n\u00111\u0001\u0002b!I\u0011\u0011N\u0015\u0011\u0002\u0003\u0007\u0011\u0011\r\u000b\u0005\u0003\u007f\u0013)\u0006C\u0005\u0002H:\n\t\u00111\u0001\u00026R!\u0011Q\u001cB-\u0011%\t9\rMA\u0001\u0002\u0004\ty\f\u0006\u0003\u0002|\tu\u0003\"CAdc\u0005\u0005\t\u0019AA[)\u0011\tiN!\u0019\t\u0013\u0005\u001d7'!AA\u0002\u0005}\u0016a\u0002%b]:Lgn\u001a\t\u0004\u0003c*4#B\u001b\u0003j\t\u001d\u0001CCA\u007f\u0005\u0007\t\t'!\u0019\u0003HQ\u0011!Q\r\u000b\u0007\u0005\u000f\u0012yG!\u001d\t\u0013\u0005u\u0003\b%AA\u0002\u0005\u0005\u0004\"CA5qA\u0005\t\u0019AA1)\u0011\u0011\u0019C!\u001e\t\u0013\tE2(!AA\u0002\t\u001d#\u0001\u0003\"mC\u000e\\W.\u00198\u0014\u000f}\n\u0019#a\u0010\u0002F\u0005\u0011\u0011\rM\u0001\u0004CB\u0002\u0013AA12\u0003\r\t\u0017\u0007I\u0001\u0003CJ\n1!\u0019\u001a!)!\u0011IIa#\u0003\u000e\n=\u0005cAA9\u007f!I!1\u0010$\u0011\u0002\u0003\u0007\u0011\u0011\r\u0005\n\u0005\u007f2\u0005\u0013!a\u0001\u0003CB\u0011Ba!G!\u0003\u0005\r!!\u0019\u0015\u0011\t%%1\u0013BK\u0005/C\u0011Ba\u001fI!\u0003\u0005\r!!\u0019\t\u0013\t}\u0004\n%AA\u0002\u0005\u0005\u0004\"\u0003BB\u0011B\u0005\t\u0019AA1\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM\"B!a0\u0003\u001e\"I\u0011q\u0019(\u0002\u0002\u0003\u0007\u0011Q\u0017\u000b\u0005\u0003;\u0014\t\u000bC\u0005\u0002HB\u000b\t\u00111\u0001\u0002@R!\u00111\u0010BS\u0011%\t9-UA\u0001\u0002\u0004\t)\f\u0006\u0003\u0002^\n%\u0006\"CAd'\u0006\u0005\t\u0019AA`\u0003!\u0011E.Y2l[\u0006t\u0007cAA9+N)QK!-\u0003\bAa\u0011Q BZ\u0003C\n\t'!\u0019\u0003\n&!!QWA\u0000\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\r\u000b\u0003\u0005[#\u0002B!#\u0003<\nu&q\u0018\u0005\n\u0005wB\u0006\u0013!a\u0001\u0003CB\u0011Ba Y!\u0003\u0005\r!!\u0019\t\u0013\t\r\u0005\f%AA\u0002\u0005\u0005\u0014aD1qa2LH\u0005Z3gCVdG\u000fJ\u001a\u0015\t\t\u0015'Q\u001a\t\u0007\u0003_\u0011)Ca2\u0011\u0015\u0005=\"\u0011ZA1\u0003C\n\t'\u0003\u0003\u0003L\u0006E\"A\u0002+va2,7\u0007C\u0005\u00032q\u000b\t\u00111\u0001\u0003\n\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIM\u0012A!V:feN9\u0011-a\t\u0002@\u0005\u0015\u0013A\u00013w+\t\u0011I\u000e\u0005\u0004\u0003\\\n\u0005\u0018\u0011M\u0007\u0003\u0005;TAAa8\u0002\f\u00051A.\u001b8bY\u001eLAAa9\u0003^\nYA)\u001a8tKZ+7\r^8s\u0003\r!g\u000f\t\u000b\u0005\u0005S\u0014Y\u000fE\u0002\u0002r\u0005DqA!6e\u0001\u0004\u0011I\u000e\u0006\u0003\u0003j\n=\b\"\u0003BkMB\u0005\t\u0019\u0001Bm+\t\u0011\u0019P\u000b\u0003\u0003Z\u0006eE\u0003BA`\u0005oD\u0011\"a2k\u0003\u0003\u0005\r!!.\u0015\t\u0005u'1 \u0005\n\u0003\u000fd\u0017\u0011!a\u0001\u0003\u007f#B!a\u001f\u0003\u0000\"I\u0011qY7\u0002\u0002\u0003\u0007\u0011Q\u0017\u000b\u0005\u0003;\u001c\u0019\u0001C\u0005\u0002H>\f\t\u00111\u0001\u0002@\u0006!Qk]3s!\r\t\t(]\n\u0006c\u000e-!q\u0001\t\t\u0003{\u001ciA!7\u0003j&!1qBA\u0000\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\u000b\u0003\u0007\u000f!BA!;\u0004\u0016!9!Q\u001b;A\u0002\teG\u0003BB\r\u00077\u0001b!a\f\u0003&\te\u0007\"\u0003B\u0019k\u0006\u0005\t\u0019\u0001Bu\u0003\u0011quN\\3\u0011\u0007\u0005E\u0004P\u0001\u0003O_:,7c\u0002=\u0002$\u0005}\u0012Q\t\u000b\u0003\u0007?!B!a0\u0004*!I\u0011qY?\u0002\u0002\u0003\u0007\u0011Q\u0017\u000b\u0005\u0003;\u001ci\u0003C\u0005\u0002H~\f\t\u00111\u0001\u0002@\u0002"
)
public abstract class OptWindowFunction extends Opt {
   public static class Hamming extends OptWindowFunction implements Product, Serializable {
      private final double alpha;
      private final double beta;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double alpha() {
         return this.alpha;
      }

      public double beta() {
         return this.beta;
      }

      public String toString() {
         return (new StringBuilder(19)).append("Hamming window (").append(this.alpha()).append(", ").append(this.beta()).append(")").toString();
      }

      public Hamming copy(final double alpha, final double beta) {
         return new Hamming(alpha, beta);
      }

      public double copy$default$1() {
         return this.alpha();
      }

      public double copy$default$2() {
         return this.beta();
      }

      public String productPrefix() {
         return "Hamming";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToDouble(this.alpha());
               break;
            case 1:
               var10000 = BoxesRunTime.boxToDouble(this.beta());
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
         return x$1 instanceof Hamming;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "alpha";
               break;
            case 1:
               var10000 = "beta";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.doubleHash(this.alpha()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.beta()));
         return Statics.finalizeHash(var1, 2);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label51: {
               boolean var2;
               if (x$1 instanceof Hamming) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  Hamming var4 = (Hamming)x$1;
                  if (this.alpha() == var4.alpha() && this.beta() == var4.beta() && var4.canEqual(this)) {
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

      public Hamming(final double alpha, final double beta) {
         this.alpha = alpha;
         this.beta = beta;
         Product.$init$(this);
      }
   }

   public static class Hamming$ extends AbstractFunction2 implements Serializable {
      public static final Hamming$ MODULE$ = new Hamming$();

      public double $lessinit$greater$default$1() {
         return 0.54;
      }

      public double $lessinit$greater$default$2() {
         return 0.46;
      }

      public final String toString() {
         return "Hamming";
      }

      public Hamming apply(final double alpha, final double beta) {
         return new Hamming(alpha, beta);
      }

      public double apply$default$1() {
         return 0.54;
      }

      public double apply$default$2() {
         return 0.46;
      }

      public Option unapply(final Hamming x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2.mcDD.sp(x$0.alpha(), x$0.beta())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Hamming$.class);
      }
   }

   public static class Hanning extends OptWindowFunction implements Product, Serializable {
      private final double alpha;
      private final double beta;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double alpha() {
         return this.alpha;
      }

      public double beta() {
         return this.beta;
      }

      public String toString() {
         return (new StringBuilder(18)).append("Hanning window (").append(this.alpha()).append(",").append(this.beta()).append(")").toString();
      }

      public Hanning copy(final double alpha, final double beta) {
         return new Hanning(alpha, beta);
      }

      public double copy$default$1() {
         return this.alpha();
      }

      public double copy$default$2() {
         return this.beta();
      }

      public String productPrefix() {
         return "Hanning";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToDouble(this.alpha());
               break;
            case 1:
               var10000 = BoxesRunTime.boxToDouble(this.beta());
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
         return x$1 instanceof Hanning;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "alpha";
               break;
            case 1:
               var10000 = "beta";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.doubleHash(this.alpha()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.beta()));
         return Statics.finalizeHash(var1, 2);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label51: {
               boolean var2;
               if (x$1 instanceof Hanning) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  Hanning var4 = (Hanning)x$1;
                  if (this.alpha() == var4.alpha() && this.beta() == var4.beta() && var4.canEqual(this)) {
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

      public Hanning(final double alpha, final double beta) {
         this.alpha = alpha;
         this.beta = beta;
         Product.$init$(this);
      }
   }

   public static class Hanning$ extends AbstractFunction2 implements Serializable {
      public static final Hanning$ MODULE$ = new Hanning$();

      public double $lessinit$greater$default$1() {
         return (double)0.5F;
      }

      public double $lessinit$greater$default$2() {
         return (double)0.5F;
      }

      public final String toString() {
         return "Hanning";
      }

      public Hanning apply(final double alpha, final double beta) {
         return new Hanning(alpha, beta);
      }

      public double apply$default$1() {
         return (double)0.5F;
      }

      public double apply$default$2() {
         return (double)0.5F;
      }

      public Option unapply(final Hanning x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2.mcDD.sp(x$0.alpha(), x$0.beta())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Hanning$.class);
      }
   }

   public static class Blackman extends OptWindowFunction implements Product, Serializable {
      private final double a0;
      private final double a1;
      private final double a2;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double a0() {
         return this.a0;
      }

      public double a1() {
         return this.a1;
      }

      public double a2() {
         return this.a2;
      }

      public String toString() {
         return (new StringBuilder(18)).append("Blackman window (").append(this.a0()).append(this.a1()).append(this.a2()).append(")").toString();
      }

      public Blackman copy(final double a0, final double a1, final double a2) {
         return new Blackman(a0, a1, a2);
      }

      public double copy$default$1() {
         return this.a0();
      }

      public double copy$default$2() {
         return this.a1();
      }

      public double copy$default$3() {
         return this.a2();
      }

      public String productPrefix() {
         return "Blackman";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToDouble(this.a0());
               break;
            case 1:
               var10000 = BoxesRunTime.boxToDouble(this.a1());
               break;
            case 2:
               var10000 = BoxesRunTime.boxToDouble(this.a2());
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
         return x$1 instanceof Blackman;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "a0";
               break;
            case 1:
               var10000 = "a1";
               break;
            case 2:
               var10000 = "a2";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.doubleHash(this.a0()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.a1()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.a2()));
         return Statics.finalizeHash(var1, 3);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label53: {
               boolean var2;
               if (x$1 instanceof Blackman) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  Blackman var4 = (Blackman)x$1;
                  if (this.a0() == var4.a0() && this.a1() == var4.a1() && this.a2() == var4.a2() && var4.canEqual(this)) {
                     break label53;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public Blackman(final double a0, final double a1, final double a2) {
         this.a0 = a0;
         this.a1 = a1;
         this.a2 = a2;
         Product.$init$(this);
      }
   }

   public static class Blackman$ extends AbstractFunction3 implements Serializable {
      public static final Blackman$ MODULE$ = new Blackman$();

      public double $lessinit$greater$default$1() {
         return 0.42;
      }

      public double $lessinit$greater$default$2() {
         return (double)0.5F;
      }

      public double $lessinit$greater$default$3() {
         return 0.08;
      }

      public final String toString() {
         return "Blackman";
      }

      public Blackman apply(final double a0, final double a1, final double a2) {
         return new Blackman(a0, a1, a2);
      }

      public double apply$default$1() {
         return 0.42;
      }

      public double apply$default$2() {
         return (double)0.5F;
      }

      public double apply$default$3() {
         return 0.08;
      }

      public Option unapply(final Blackman x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToDouble(x$0.a0()), BoxesRunTime.boxToDouble(x$0.a1()), BoxesRunTime.boxToDouble(x$0.a2()))));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Blackman$.class);
      }
   }

   public static class User extends OptWindowFunction implements Product, Serializable {
      private final DenseVector dv;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public DenseVector dv() {
         return this.dv;
      }

      public String toString() {
         return "user-specified window";
      }

      public User copy(final DenseVector dv) {
         return new User(dv);
      }

      public DenseVector copy$default$1() {
         return this.dv();
      }

      public String productPrefix() {
         return "User";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.dv();
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
         return x$1 instanceof User;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "dv";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label53: {
               boolean var2;
               if (x$1 instanceof User) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label36: {
                     label35: {
                        User var4 = (User)x$1;
                        DenseVector var10000 = this.dv();
                        DenseVector var5 = var4.dv();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label35;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label35;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label36;
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label53;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      public User(final DenseVector dv) {
         this.dv = dv;
         Product.$init$(this);
      }
   }

   public static class User$ extends AbstractFunction1 implements Serializable {
      public static final User$ MODULE$ = new User$();

      public final String toString() {
         return "User";
      }

      public User apply(final DenseVector dv) {
         return new User(dv);
      }

      public Option unapply(final User x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.dv()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(User$.class);
      }
   }

   public static class None$ extends OptWindowFunction implements Product, Serializable {
      public static final None$ MODULE$ = new None$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String toString() {
         return "no window";
      }

      public String productPrefix() {
         return "None";
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
         return x$1 instanceof None$;
      }

      public int hashCode() {
         return 2433880;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(None$.class);
      }
   }
}
