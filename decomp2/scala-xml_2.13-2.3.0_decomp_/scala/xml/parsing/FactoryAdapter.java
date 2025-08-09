package scala.xml.parsing;

import java.io.File;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.Reader;
import java.lang.invoke.SerializedLambda;
import java.net.URL;
import javax.xml.parsers.SAXParser;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.DefaultHandler2;
import org.xml.sax.ext.Locator2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Option.;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction1;
import scala.xml.Attribute$;
import scala.xml.Document;
import scala.xml.MetaData;
import scala.xml.NamespaceBinding;
import scala.xml.Node;
import scala.xml.Null$;
import scala.xml.PCData;
import scala.xml.Text;
import scala.xml.Text$;
import scala.xml.TopScope$;
import scala.xml.Utility$;
import scala.xml.dtd.DTD;
import scala.xml.factory.XMLLoader;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u0015f!B,Y\u0003\u0003y\u0006\"B;\u0001\t\u00031\bbB=\u0001\u0005\u0004%\tA\u001f\u0005\u0007\u007f\u0002\u0001\u000b\u0011B>\t\u0013\u0005\u0005\u0001\u00011A\u0005\n\u0005\r\u0001\"CA\r\u0001\u0001\u0007I\u0011BA\u000e\u0011!\t9\u0003\u0001Q!\n\u0005\u0015\u0001\"CA\u0015\u0001\u0001\u0007I\u0011BA\u0016\u0011%\t)\u0004\u0001a\u0001\n\u0013\t9\u0004\u0003\u0005\u0002<\u0001\u0001\u000b\u0015BA\u0017\u0011\u0019\ti\u0004\u0001C\u0005u\"I\u0011q\b\u0001A\u0002\u0013%\u0011\u0011\t\u0005\n\u0003\u0017\u0002\u0001\u0019!C\u0005\u0003\u001bB\u0001\"!\u0015\u0001A\u0003&\u00111\t\u0005\n\u0003'\u0002\u0001\u0019!C\u0005\u0003+B\u0011\"a\u001c\u0001\u0001\u0004%I!!\u001d\t\u0011\u0005U\u0004\u0001)Q\u0005\u0003/B\u0011\"a\u001e\u0001\u0001\u0004%I!!\u0016\t\u0013\u0005e\u0004\u00011A\u0005\n\u0005m\u0004\u0002CA@\u0001\u0001\u0006K!a\u0016\t\u0013\u0005\u0005\u0005\u00011A\u0005\n\u0005\r\u0005\"CAL\u0001\u0001\u0007I\u0011BAM\u0011!\ti\n\u0001Q!\n\u0005\u0015\u0005\"CAP\u0001\u0001\u0007I\u0011AAQ\u0011%\t)\u000b\u0001a\u0001\n\u0003\t9\u000b\u0003\u0005\u0002,\u0002\u0001\u000b\u0015BAR\u0011-\ti\u000b\u0001a\u0001\u0002\u0004%\t!a,\t\u0017\u0005E\u0006\u00011AA\u0002\u0013\u0005\u00111\u0017\u0005\u000b\u0003o\u0003\u0001\u0019!A!B\u0013\t\b\"CA]\u0001\u0001\u0007I\u0011AAQ\u0011%\tY\f\u0001a\u0001\n\u0003\ti\f\u0003\u0005\u0002B\u0002\u0001\u000b\u0015BAR\u0011%\t\u0019\r\u0001b\u0001\n\u0003\t)\r\u0003\u0005\u0002N\u0002\u0001\u000b\u0011BAd\u0011!\ty\r\u0001a\u0001\n\u0013Q\b\"CAi\u0001\u0001\u0007I\u0011BAj\u0011\u001d\t9\u000e\u0001Q!\nmD\u0011\"!7\u0001\u0001\u0004%\t!a7\t\u0013\u0005\u0015\b\u00011A\u0005\u0002\u0005\u001d\b\u0002CAv\u0001\u0001\u0006K!!8\t\u0013\u00055\b\u00011A\u0005\u0002\u0005\u0005\u0006\"CAx\u0001\u0001\u0007I\u0011AAy\u0011!\t)\u0010\u0001Q!\n\u0005\r\u0006\"CA|\u0001\u0001\u0007I\u0011AA}\u0011%\ti\u0010\u0001a\u0001\n\u0003\ty\u0010\u0003\u0005\u0003\u0004\u0001\u0001\u000b\u0015BA~\u0011%\u0011)\u0001\u0001a\u0001\n\u0003\u00119\u0001C\u0005\u0003\u0012\u0001\u0001\r\u0011\"\u0001\u0003\u0014!A!q\u0003\u0001!B\u0013\u0011I\u0001C\u0006\u0003\u001a\u0001\u0001\r\u00111A\u0005\u0002\tm\u0001b\u0003B\u000f\u0001\u0001\u0007\t\u0019!C\u0001\u0005?A1Ba\t\u0001\u0001\u0004\u0005\t\u0015)\u0003\u0002Z!A!Q\u0005\u0001A\u0002\u0013\u0005!\u0010C\u0005\u0003(\u0001\u0001\r\u0011\"\u0001\u0003*!9!Q\u0006\u0001!B\u0013Y\bb\u0002B\u0018\u0001\u0011\u0005!\u0011\u0007\u0005\b\u0005g\u0001A\u0011\u0001B\u001b\u0011\u001d\u0011\u0019\u0005\u0001D\u0001\u0005\u000bBqAa\u0013\u0001\r\u0003\u0011i\u0005C\u0004\u0003d\u00011\tA!\u001a\t\u000f\tE\u0004A\"\u0001\u0003t!9!Q\u0010\u0001\u0007\u0002\t}\u0004b\u0002BN\u0001\u0019\u0005!Q\u0014\u0005\b\u0005W\u0003A\u0011\tBW\u0011\u001d\u0011Y\f\u0001C!\u0005cAqA!0\u0001\t\u0003\u0012\t\u0004C\u0004\u0003@\u0002!\tE!1\t\u000f\t-\u0007\u0001\"\u0011\u0003N\"9!\u0011\u001b\u0001\u0005B\tM\u0007b\u0002Bu\u0001\u0011\u0005#1\u001e\u0005\b\u0005S\u0003A\u0011\tBz\u0011\u001d\u0019\u0019\u0002\u0001C!\u0007+Aqa!\b\u0001\t\u0003\u001ay\u0002C\u0004\u0004&\u0001!\tea\n\t\u000f\r5\u0002\u0001\"\u0011\u00040!911\b\u0001\u0005B\tE\u0002bBB\u001f\u0001\u0011\u00053q\b\u0005\b\u0007\u0007\u0002A\u0011IB#\u0011\u001d\u0019I\u0005\u0001C!\u0005cAqaa\u0013\u0001\t\u0003\u0012\t\u0004C\u0004\u0004N\u0001!\tea\u0014\t\u000f\re\u0003\u0001\"\u0011\u0004\\!911\r\u0001\u0005B\r\u0015\u0004bBB9\u0001\u0011\u000531\u000f\u0005\b\u0007w\u0002A\u0011IB?\u0011\u001d\u0019\u0019\n\u0001C!\u0007+Cqaa'\u0001\t\u0003\u001aiJ\u0001\bGC\u000e$xN]=BI\u0006\u0004H/\u001a:\u000b\u0005eS\u0016a\u00029beNLgn\u001a\u0006\u00037r\u000b1\u0001_7m\u0015\u0005i\u0016!B:dC2\f7\u0001A\n\u0004\u0001\u0001\\\u0007CA1j\u001b\u0005\u0011'BA2e\u0003\r)\u0007\u0010\u001e\u0006\u0003K\u001a\f1a]1y\u0015\tYvMC\u0001i\u0003\ry'oZ\u0005\u0003U\n\u0014q\u0002R3gCVdG\u000fS1oI2,'O\r\t\u0004Y>\fX\"A7\u000b\u00059T\u0016a\u00024bGR|'/_\u0005\u0003a6\u0014\u0011\u0002W'M\u0019>\fG-\u001a:\u0011\u0005I\u001cX\"\u0001.\n\u0005QT&\u0001\u0002(pI\u0016\fa\u0001P5oSRtD#A<\u0011\u0005a\u0004Q\"\u0001-\u0002'9|'/\\1mSj,w\u000b[5uKN\u0004\u0018mY3\u0016\u0003m\u0004\"\u0001`?\u000e\u0003qK!A /\u0003\u000f\t{w\u000e\\3b]\u0006!bn\u001c:nC2L'0Z,iSR,7\u000f]1dK\u0002\n\u0011\u0002_7m%\u0016\fG-\u001a:\u0016\u0005\u0005\u0015\u0001#\u0002?\u0002\b\u0005-\u0011bAA\u00059\n1q\n\u001d;j_:\u0004B!!\u0004\u0002\u00149\u0019!/a\u0004\n\u0007\u0005E!,A\u0004qC\u000e\\\u0017mZ3\n\t\u0005U\u0011q\u0003\u0002\n16c%+Z1eKJT1!!\u0005[\u00035AX\u000e\u001c*fC\u0012,'o\u0018\u0013fcR!\u0011QDA\u0012!\ra\u0018qD\u0005\u0004\u0003Ca&\u0001B+oSRD\u0011\"!\n\u0006\u0003\u0003\u0005\r!!\u0002\u0002\u0007a$\u0013'\u0001\u0006y[2\u0014V-\u00193fe\u0002\n!\u0002\u001a;e\u0005VLG\u000eZ3s+\t\ti\u0003E\u0003}\u0003\u000f\ty\u0003E\u0002y\u0003cI1!a\rY\u0005)!E\u000f\u001a\"vS2$WM]\u0001\u000fIR$')^5mI\u0016\u0014x\fJ3r)\u0011\ti\"!\u000f\t\u0013\u0005\u0015\u0002\"!AA\u0002\u00055\u0012a\u00033uI\n+\u0018\u000e\u001c3fe\u0002\nQ!\u001b8Ei\u0012\f\u0001\u0002Z8dk6,g\u000e^\u000b\u0003\u0003\u0007\u0002R\u0001`A\u0004\u0003\u000b\u00022A]A$\u0013\r\tIE\u0017\u0002\t\t>\u001cW/\\3oi\u0006aAm\\2v[\u0016tGo\u0018\u0013fcR!\u0011QDA(\u0011%\t)\u0003DA\u0001\u0002\u0004\t\u0019%A\u0005e_\u000e,X.\u001a8uA\u00059!-Y:f+JKUCAA,!\u0015a\u0018qAA-!\u0011\tY&!\u001b\u000f\t\u0005u\u0013Q\r\t\u0004\u0003?bVBAA1\u0015\r\t\u0019GX\u0001\u0007yI|w\u000e\u001e \n\u0007\u0005\u001dD,\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003W\niG\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003Ob\u0016a\u00032bg\u0016,&+S0%KF$B!!\b\u0002t!I\u0011QE\b\u0002\u0002\u0003\u0007\u0011qK\u0001\tE\u0006\u001cX-\u0016*JA\u0005Y\u00010\u001c7F]\u000e|G-\u001b8h\u0003=AX\u000e\\#oG>$\u0017N\\4`I\u0015\fH\u0003BA\u000f\u0003{B\u0011\"!\n\u0013\u0003\u0003\u0005\r!a\u0016\u0002\u0019alG.\u00128d_\u0012Lgn\u001a\u0011\u0002\u001dA\u0014XMZ5y\u001b\u0006\u0004\b/\u001b8hgV\u0011\u0011Q\u0011\t\u0007\u0003\u000f\u000bY)!%\u000f\u0007q\fI)C\u0002\u0002\u0012qKA!!$\u0002\u0010\n!A*[:u\u0015\r\t\t\u0002\u0018\t\by\u0006M\u0015\u0011LA-\u0013\r\t)\n\u0018\u0002\u0007)V\u0004H.\u001a\u001a\u0002%A\u0014XMZ5y\u001b\u0006\u0004\b/\u001b8hg~#S-\u001d\u000b\u0005\u0003;\tY\nC\u0005\u0002&U\t\t\u00111\u0001\u0002\u0006\u0006y\u0001O]3gSbl\u0015\r\u001d9j]\u001e\u001c\b%\u0001\u0004qe>dwnZ\u000b\u0003\u0003G\u0003R!a\"\u0002\fF\f!\u0002\u001d:pY><w\fJ3r)\u0011\ti\"!+\t\u0013\u0005\u0015\u0002$!AA\u0002\u0005\r\u0016a\u00029s_2|w\rI\u0001\te>|G/\u00127f[V\t\u0011/\u0001\u0007s_>$X\t\\3n?\u0012*\u0017\u000f\u0006\u0003\u0002\u001e\u0005U\u0006\u0002CA\u00137\u0005\u0005\t\u0019A9\u0002\u0013I|w\u000e^#mK6\u0004\u0013\u0001C3qS2|w-^3\u0002\u0019\u0015\u0004\u0018\u000e\\8hk\u0016|F%Z9\u0015\t\u0005u\u0011q\u0018\u0005\n\u0003Kq\u0012\u0011!a\u0001\u0003G\u000b\u0011\"\u001a9jY><W/\u001a\u0011\u0002\r\t,hMZ3s+\t\t9\r\u0005\u0003\u0002\b\u0006%\u0017\u0002BAf\u0003\u001f\u0013Qb\u0015;sS:<')^5mI\u0016\u0014\u0018a\u00022vM\u001a,'\u000fI\u0001\bS:\u001cE)\u0011+B\u0003-Ign\u0011#B)\u0006{F%Z9\u0015\t\u0005u\u0011Q\u001b\u0005\t\u0003K\u0019\u0013\u0011!a\u0001w\u0006A\u0011N\\\"E\u0003R\u000b\u0005%A\u0006biR\u0014\u0018NY*uC\u000e\\WCAAo!\u0019\t9)a#\u0002`B\u0019!/!9\n\u0007\u0005\r(L\u0001\u0005NKR\fG)\u0019;b\u0003=\tG\u000f\u001e:jEN#\u0018mY6`I\u0015\fH\u0003BA\u000f\u0003SD\u0011\"!\n'\u0003\u0003\u0005\r!!8\u0002\u0019\u0005$HO]5c'R\f7m\u001b\u0011\u0002\r!\u001cF/Y2l\u0003)A7\u000b^1dW~#S-\u001d\u000b\u0005\u0003;\t\u0019\u0010C\u0005\u0002&%\n\t\u00111\u0001\u0002$\u00069\u0001n\u0015;bG.\u0004\u0013\u0001\u0003;bON#\u0018mY6\u0016\u0005\u0005m\bCBAD\u0003\u0017\u000bI&\u0001\u0007uC\u001e\u001cF/Y2l?\u0012*\u0017\u000f\u0006\u0003\u0002\u001e\t\u0005\u0001\"CA\u0013Y\u0005\u0005\t\u0019AA~\u0003%!\u0018mZ*uC\u000e\\\u0007%\u0001\u0006tG>\u0004Xm\u0015;bG.,\"A!\u0003\u0011\r\u0005\u001d\u00151\u0012B\u0006!\r\u0011(QB\u0005\u0004\u0005\u001fQ&\u0001\u0005(b[\u0016\u001c\b/Y2f\u0005&tG-\u001b8h\u00039\u00198m\u001c9f'R\f7m[0%KF$B!!\b\u0003\u0016!I\u0011QE\u0018\u0002\u0002\u0003\u0007!\u0011B\u0001\fg\u000e|\u0007/Z*uC\u000e\\\u0007%\u0001\u0004dkJ$\u0016mZ\u000b\u0003\u00033\n!bY;s)\u0006<w\fJ3r)\u0011\tiB!\t\t\u0013\u0005\u0015\"'!AA\u0002\u0005e\u0013aB2veR\u000bw\rI\u0001\bG\u0006\u0004H/\u001e:f\u0003-\u0019\u0017\r\u001d;ve\u0016|F%Z9\u0015\t\u0005u!1\u0006\u0005\t\u0003K)\u0014\u0011!a\u0001w\u0006A1-\u00199ukJ,\u0007%A\u0006dCB$XO]3UKb$HCAA\u000f\u00031aw.\u00193E_\u000e,X.\u001a8u)\u0019\t)Ea\u000e\u0003B!9!\u0011\b\u001dA\u0002\tm\u0012aC5oaV$8k\\;sG\u0016\u0004B!!\u0004\u0003>%!!qHA\f\u0005-Ie\u000e];u'>,(oY3\t\u000f\u0005\u0005\u0001\b1\u0001\u0002\f\u0005\u0001bn\u001c3f\u0007>tG/Y5ogR+\u0007\u0010\u001e\u000b\u0004w\n\u001d\u0003b\u0002B%s\u0001\u0007\u0011\u0011L\u0001\nY>\u001c\u0017\r\u001c(b[\u0016\f!b\u0019:fCR,gj\u001c3f)-\t(q\nB*\u0005/\u0012YFa\u0018\t\u000f\tE#\b1\u0001\u0002Z\u0005\u0019\u0001O]3\t\u000f\tU#\b1\u0001\u0002Z\u0005AQ\r\\3n\u001d\u0006lW\rC\u0004\u0003Zi\u0002\r!a8\u0002\u000f\u0005$HO]5cg\"9!Q\f\u001eA\u0002\t-\u0011!B:d_B,\u0007b\u0002B1u\u0001\u0007\u00111U\u0001\u0007G\"LE/\u001a:\u0002\u0015\r\u0014X-\u0019;f)\u0016DH\u000f\u0006\u0003\u0003h\t5\u0004c\u0001:\u0003j%\u0019!1\u000e.\u0003\tQ+\u0007\u0010\u001e\u0005\b\u0005_Z\u0004\u0019AA-\u0003\u0011!X\r\u001f;\u0002\u0019\r\u0014X-\u0019;f!\u000e#\u0015\r^1\u0015\t\tU$1\u0010\t\u0004e\n]\u0014b\u0001B=5\n1\u0001k\u0011#bi\u0006DqAa\u001c=\u0001\u0004\tI&A\bde\u0016\fG/\u001a)s_\u000eLen\u001d;s)\u0019\u0011\tIa%\u0003\u0018B1!1\u0011BE\u0005\u001bk!A!\"\u000b\u0007\t\u001dE,\u0001\u0006d_2dWm\u0019;j_:LAAa#\u0003\u0006\n\u00191+Z9\u0011\u0007I\u0014y)C\u0002\u0003\u0012j\u0013\u0011\u0002\u0015:pG&s7\u000f\u001e:\t\u000f\tUU\b1\u0001\u0002Z\u00051A/\u0019:hKRDqA!'>\u0001\u0004\tI&\u0001\u0003eCR\f\u0017!D2sK\u0006$XmQ8n[\u0016tG\u000f\u0006\u0003\u0003 \n\u001d\u0006C\u0002BB\u0005\u0013\u0013\t\u000bE\u0002s\u0005GK1A!*[\u0005\u001d\u0019u.\\7f]RDqA!+?\u0001\u0004\tI&\u0001\u0006dQ\u0006\u0014\u0018m\u0019;feN\f!c]3u\t>\u001cW/\\3oi2{7-\u0019;peR!\u0011Q\u0004BX\u0011\u001d\u0011\tl\u0010a\u0001\u0005g\u000bq\u0001\\8dCR|'\u000f\u0005\u0003\u00036\n]V\"\u00013\n\u0007\teFMA\u0004M_\u000e\fGo\u001c:\u0002\u001bM$\u0018M\u001d;E_\u000e,X.\u001a8u\u0003-)g\u000e\u001a#pGVlWM\u001c;\u0002%M$\u0018M\u001d;Qe\u00164\u0017\u000e_'baBLgn\u001a\u000b\u0007\u0003;\u0011\u0019Ma2\t\u000f\t\u0015'\t1\u0001\u0002Z\u00051\u0001O]3gSbDqA!3C\u0001\u0004\tI&A\u0002ve&\f\u0001#\u001a8e!J,g-\u001b=NCB\u0004\u0018N\\4\u0015\t\u0005u!q\u001a\u0005\b\u0005\u000b\u001c\u0005\u0019AA-\u00031\u0019H/\u0019:u\u000b2,W.\u001a8u))\tiB!6\u0003X\nm'q\u001c\u0005\b\u0005\u0013$\u0005\u0019AA-\u0011\u001d\u0011I\u000e\u0012a\u0001\u00033\n!b\u00187pG\u0006dg*Y7f\u0011\u001d\u0011i\u000e\u0012a\u0001\u00033\nQ!\u001d8b[\u0016DqA!9E\u0001\u0004\u0011\u0019/\u0001\u0006biR\u0014\u0018NY;uKN\u0004BA!.\u0003f&\u0019!q\u001d3\u0003\u0015\u0005#HO]5ckR,7/\u0001\u0006f]\u0012,E.Z7f]R$\u0002\"!\b\u0003n\n=(\u0011\u001f\u0005\b\u0005\u0013,\u0005\u0019AA-\u0011\u001d\u0011I.\u0012a\u0001\u00033BqA!8F\u0001\u0004\tI\u0006\u0006\u0005\u0002\u001e\tU8QAB\b\u0011\u001d\u00119P\u0012a\u0001\u0005s\f!a\u00195\u0011\u000bq\u0014YPa@\n\u0007\tuHLA\u0003BeJ\f\u0017\u0010E\u0002}\u0007\u0003I1aa\u0001]\u0005\u0011\u0019\u0005.\u0019:\t\u000f\r\u001da\t1\u0001\u0004\n\u00051qN\u001a4tKR\u00042\u0001`B\u0006\u0013\r\u0019i\u0001\u0018\u0002\u0004\u0013:$\bbBB\t\r\u0002\u00071\u0011B\u0001\u0007Y\u0016tw\r\u001e5\u0002'%<gn\u001c:bE2,w\u000b[5uKN\u0004\u0018mY3\u0015\u0011\u0005u1qCB\r\u00077AqAa>H\u0001\u0004\u0011I\u0010C\u0004\u0004\b\u001d\u0003\ra!\u0003\t\u000f\rEq\t1\u0001\u0004\n\u0005)\u0002O]8dKN\u001c\u0018N\\4J]N$(/^2uS>tGCBA\u000f\u0007C\u0019\u0019\u0003C\u0004\u0003\u0016\"\u0003\r!!\u0017\t\u000f\te\u0005\n1\u0001\u0002Z\u0005i1o[5qa\u0016$WI\u001c;jif$B!!\b\u0004*!911F%A\u0002\u0005e\u0013\u0001\u00028b[\u0016\f\u0001b\u001d;beR$E\u000b\u0012\u000b\t\u0003;\u0019\tda\r\u00048!911\u0006&A\u0002\u0005e\u0003bBB\u001b\u0015\u0002\u0007\u0011\u0011L\u0001\taV\u0014G.[2JI\"91\u0011\b&A\u0002\u0005e\u0013\u0001C:zgR,W.\u00133\u0002\r\u0015tG\r\u0012+E\u0003-\u0019H/\u0019:u\u000b:$\u0018\u000e^=\u0015\t\u0005u1\u0011\t\u0005\b\u0007Wa\u0005\u0019AA-\u0003%)g\u000eZ#oi&$\u0018\u0010\u0006\u0003\u0002\u001e\r\u001d\u0003bBB\u0016\u001b\u0002\u0007\u0011\u0011L\u0001\u000bgR\f'\u000f^\"E\u0003R\u000b\u0015\u0001C3oI\u000e#\u0015\tV!\u0002\u000f\r|W.\\3oiRA\u0011QDB)\u0007'\u001a9\u0006C\u0004\u0003xB\u0003\rA!?\t\u000f\rU\u0003\u000b1\u0001\u0004\n\u0005)1\u000f^1si\"91\u0011\u0003)A\u0002\r%\u0011\u0001\u00048pi\u0006$\u0018n\u001c8EK\u000edG\u0003CA\u000f\u0007;\u001ayf!\u0019\t\u000f\r-\u0012\u000b1\u0001\u0002Z!91QG)A\u0002\u0005e\u0003bBB\u001d#\u0002\u0007\u0011\u0011L\u0001\u0013k:\u0004\u0018M]:fI\u0016sG/\u001b;z\t\u0016\u001cG\u000e\u0006\u0006\u0002\u001e\r\u001d4\u0011NB6\u0007[Bqaa\u000bS\u0001\u0004\tI\u0006C\u0004\u00046I\u0003\r!!\u0017\t\u000f\re\"\u000b1\u0001\u0002Z!91q\u000e*A\u0002\u0005e\u0013\u0001\u00048pi\u0006$\u0018n\u001c8OC6,\u0017aC3mK6,g\u000e\u001e#fG2$b!!\b\u0004v\r]\u0004bBB\u0016'\u0002\u0007\u0011\u0011\f\u0005\b\u0007s\u001a\u0006\u0019AA-\u0003\u0015iw\u000eZ3m\u00035\tG\u000f\u001e:jEV$X\rR3dYRa\u0011QDB@\u0007\u0007\u001b9ia#\u0004\u0010\"91\u0011\u0011+A\u0002\u0005e\u0013!B3OC6,\u0007bBBC)\u0002\u0007\u0011\u0011L\u0001\u0006C:\u000bW.\u001a\u0005\b\u0007\u0013#\u0006\u0019AA-\u0003\u0011!\u0018\u0010]3\t\u000f\r5E\u000b1\u0001\u0002Z\u0005!Qn\u001c3f\u0011\u001d\u0019\t\n\u0016a\u0001\u00033\nQA^1mk\u0016\f!#\u001b8uKJt\u0017\r\\#oi&$\u0018\u0010R3dYR1\u0011QDBL\u00073Cqaa\u000bV\u0001\u0004\tI\u0006C\u0004\u0004\u0012V\u0003\r!!\u0017\u0002%\u0015DH/\u001a:oC2,e\u000e^5us\u0012+7\r\u001c\u000b\t\u0003;\u0019yj!)\u0004$\"911\u0006,A\u0002\u0005e\u0003bBB\u001b-\u0002\u0007\u0011\u0011\f\u0005\b\u0007s1\u0006\u0019AA-\u0001"
)
public abstract class FactoryAdapter extends DefaultHandler2 implements XMLLoader {
   private final boolean normalizeWhitespace;
   private Option xmlReader;
   private Option dtdBuilder;
   private Option document;
   private Option baseURI;
   private Option xmlEncoding;
   private List prefixMappings;
   private List prolog;
   private Node rootElem;
   private List epilogue;
   private final StringBuilder buffer;
   private boolean inCDATA;
   private List attribStack;
   private List hStack;
   private List tagStack;
   private List scopeStack;
   private String curTag;
   private boolean capture;
   private ThreadLocal scala$xml$factory$XMLLoader$$parserInstance;
   private volatile boolean bitmap$0;

   public SAXParser parser() {
      return XMLLoader.parser$(this);
   }

   public XMLReader reader() {
      return XMLLoader.reader$(this);
   }

   public Node loadXML(final InputSource inputSource, final SAXParser parser) {
      return XMLLoader.loadXML$(this, inputSource, parser);
   }

   public Seq loadXMLNodes(final InputSource inputSource, final SAXParser parser) {
      return XMLLoader.loadXMLNodes$(this, inputSource, parser);
   }

   public FactoryAdapter adapter() {
      return XMLLoader.adapter$(this);
   }

   public Document loadDocument(final InputSource inputSource) {
      return XMLLoader.loadDocument$(this, (InputSource)inputSource);
   }

   public Document loadFileDocument(final String fileName) {
      return XMLLoader.loadFileDocument$(this, (String)fileName);
   }

   public Document loadFileDocument(final File file) {
      return XMLLoader.loadFileDocument$(this, (File)file);
   }

   public Document loadDocument(final URL url) {
      return XMLLoader.loadDocument$(this, (URL)url);
   }

   public Document loadDocument(final String sysId) {
      return XMLLoader.loadDocument$(this, (String)sysId);
   }

   public Document loadFileDocument(final FileDescriptor fileDescriptor) {
      return XMLLoader.loadFileDocument$(this, (FileDescriptor)fileDescriptor);
   }

   public Document loadDocument(final InputStream inputStream) {
      return XMLLoader.loadDocument$(this, (InputStream)inputStream);
   }

   public Document loadDocument(final Reader reader) {
      return XMLLoader.loadDocument$(this, (Reader)reader);
   }

   public Document loadStringDocument(final String string) {
      return XMLLoader.loadStringDocument$(this, string);
   }

   public Node load(final InputSource inputSource) {
      return XMLLoader.load$(this, (InputSource)inputSource);
   }

   public Node loadFile(final String fileName) {
      return XMLLoader.loadFile$(this, (String)fileName);
   }

   public Node loadFile(final File file) {
      return XMLLoader.loadFile$(this, (File)file);
   }

   public Node load(final URL url) {
      return XMLLoader.load$(this, (URL)url);
   }

   public Node load(final String sysId) {
      return XMLLoader.load$(this, (String)sysId);
   }

   public Node loadFile(final FileDescriptor fileDescriptor) {
      return XMLLoader.loadFile$(this, (FileDescriptor)fileDescriptor);
   }

   public Node load(final InputStream inputStream) {
      return XMLLoader.load$(this, (InputStream)inputStream);
   }

   public Node load(final Reader reader) {
      return XMLLoader.load$(this, (Reader)reader);
   }

   public Node loadString(final String string) {
      return XMLLoader.loadString$(this, string);
   }

   public Seq loadNodes(final InputSource inputSource) {
      return XMLLoader.loadNodes$(this, (InputSource)inputSource);
   }

   public Seq loadFileNodes(final String fileName) {
      return XMLLoader.loadFileNodes$(this, (String)fileName);
   }

   public Seq loadFileNodes(final File file) {
      return XMLLoader.loadFileNodes$(this, (File)file);
   }

   public Seq loadNodes(final URL url) {
      return XMLLoader.loadNodes$(this, (URL)url);
   }

   public Seq loadNodes(final String sysId) {
      return XMLLoader.loadNodes$(this, (String)sysId);
   }

   public Seq loadFileNodes(final FileDescriptor fileDescriptor) {
      return XMLLoader.loadFileNodes$(this, (FileDescriptor)fileDescriptor);
   }

   public Seq loadNodes(final InputStream inputStream) {
      return XMLLoader.loadNodes$(this, (InputStream)inputStream);
   }

   public Seq loadNodes(final Reader reader) {
      return XMLLoader.loadNodes$(this, (Reader)reader);
   }

   public Seq loadStringNodes(final String string) {
      return XMLLoader.loadStringNodes$(this, string);
   }

   private ThreadLocal scala$xml$factory$XMLLoader$$parserInstance$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.scala$xml$factory$XMLLoader$$parserInstance = XMLLoader.scala$xml$factory$XMLLoader$$parserInstance$(this);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.scala$xml$factory$XMLLoader$$parserInstance;
   }

   public ThreadLocal scala$xml$factory$XMLLoader$$parserInstance() {
      return !this.bitmap$0 ? this.scala$xml$factory$XMLLoader$$parserInstance$lzycompute() : this.scala$xml$factory$XMLLoader$$parserInstance;
   }

   public boolean normalizeWhitespace() {
      return this.normalizeWhitespace;
   }

   private Option xmlReader() {
      return this.xmlReader;
   }

   private void xmlReader_$eq(final Option x$1) {
      this.xmlReader = x$1;
   }

   private Option dtdBuilder() {
      return this.dtdBuilder;
   }

   private void dtdBuilder_$eq(final Option x$1) {
      this.dtdBuilder = x$1;
   }

   private boolean inDtd() {
      return this.dtdBuilder().isDefined() && !((DtdBuilder)this.dtdBuilder().get()).isDone();
   }

   private Option document() {
      return this.document;
   }

   private void document_$eq(final Option x$1) {
      this.document = x$1;
   }

   private Option baseURI() {
      return this.baseURI;
   }

   private void baseURI_$eq(final Option x$1) {
      this.baseURI = x$1;
   }

   private Option xmlEncoding() {
      return this.xmlEncoding;
   }

   private void xmlEncoding_$eq(final Option x$1) {
      this.xmlEncoding = x$1;
   }

   private List prefixMappings() {
      return this.prefixMappings;
   }

   private void prefixMappings_$eq(final List x$1) {
      this.prefixMappings = x$1;
   }

   public List prolog() {
      return this.prolog;
   }

   public void prolog_$eq(final List x$1) {
      this.prolog = x$1;
   }

   public Node rootElem() {
      return this.rootElem;
   }

   public void rootElem_$eq(final Node x$1) {
      this.rootElem = x$1;
   }

   public List epilogue() {
      return this.epilogue;
   }

   public void epilogue_$eq(final List x$1) {
      this.epilogue = x$1;
   }

   public StringBuilder buffer() {
      return this.buffer;
   }

   private boolean inCDATA() {
      return this.inCDATA;
   }

   private void inCDATA_$eq(final boolean x$1) {
      this.inCDATA = x$1;
   }

   public List attribStack() {
      return this.attribStack;
   }

   public void attribStack_$eq(final List x$1) {
      this.attribStack = x$1;
   }

   public List hStack() {
      return this.hStack;
   }

   public void hStack_$eq(final List x$1) {
      this.hStack = x$1;
   }

   public List tagStack() {
      return this.tagStack;
   }

   public void tagStack_$eq(final List x$1) {
      this.tagStack = x$1;
   }

   public List scopeStack() {
      return this.scopeStack;
   }

   public void scopeStack_$eq(final List x$1) {
      this.scopeStack = x$1;
   }

   public String curTag() {
      return this.curTag;
   }

   public void curTag_$eq(final String x$1) {
      this.curTag = x$1;
   }

   public boolean capture() {
      return this.capture;
   }

   public void capture_$eq(final boolean x$1) {
      this.capture = x$1;
   }

   public void captureText() {
      if (this.capture() && this.buffer().nonEmpty()) {
         String text = this.buffer().toString();
         Node newNode = (Node)(this.inCDATA() ? this.createPCData(text) : this.createText(text));
         this.hStack_$eq(this.hStack().$colon$colon(newNode));
      }

      this.buffer().clear();
      this.inCDATA_$eq(false);
   }

   public Document loadDocument(final InputSource inputSource, final XMLReader xmlReader) {
      if (inputSource == null) {
         throw new IllegalArgumentException("InputSource cannot be null");
      } else {
         xmlReader.setContentHandler(this);
         xmlReader.setDTDHandler(this);
         if (xmlReader.getEntityResolver() == null) {
            xmlReader.setEntityResolver(this);
         }

         if (xmlReader.getErrorHandler() == null) {
            xmlReader.setErrorHandler(this);
         }

         try {
            xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler", this);
         } catch (SAXNotRecognizedException var5) {
         } catch (SAXNotSupportedException var6) {
         }

         try {
            xmlReader.setProperty("http://xml.org/sax/properties/declaration-handler", this);
         } catch (SAXNotRecognizedException var3) {
         } catch (SAXNotSupportedException var4) {
         }

         this.xmlReader_$eq(new Some(xmlReader));
         xmlReader.parse(inputSource);
         return (Document)this.document().get();
      }
   }

   public abstract boolean nodeContainsText(final String localName);

   public abstract Node createNode(final String pre, final String elemName, final MetaData attribs, final NamespaceBinding scope, final List chIter);

   public abstract Text createText(final String text);

   public abstract PCData createPCData(final String text);

   public abstract scala.collection.Seq createProcInstr(final String target, final String data);

   public abstract scala.collection.Seq createComment(final String characters);

   public void setDocumentLocator(final Locator locator) {
      this.baseURI_$eq(.MODULE$.apply(locator.getSystemId()));
      if (locator instanceof Locator2) {
         Locator2 var4 = (Locator2)locator;
         this.xmlEncoding_$eq(.MODULE$.apply(var4.getEncoding()));
         BoxedUnit var5 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   public void startDocument() {
      this.scopeStack_$eq(this.scopeStack().$colon$colon(TopScope$.MODULE$));
   }

   public void endDocument() {
      this.epilogue_$eq(((List)this.hStack().init()).reverse());
      Document document = new Document();
      this.document_$eq(new Some(document));
      document.children_$eq((scala.collection.Seq)((IterableOps)this.prolog().$plus$plus(this.rootElem())).$plus$plus(this.epilogue()));
      document.docElem_$eq(this.rootElem());
      document.dtd_$eq((DTD)this.dtdBuilder().map((x$3) -> x$3.dtd()).orNull(scala..less.colon.less..MODULE$.refl()));
      document.baseURI_$eq((String)this.baseURI().orNull(scala..less.colon.less..MODULE$.refl()));
      document.encoding_$eq(this.xmlEncoding());
      document.version_$eq(this.liftedTree1$1());
      document.standAlone_$eq(this.liftedTree2$1());
      this.dtdBuilder_$eq(scala.None..MODULE$);
      this.xmlReader_$eq(scala.None..MODULE$);
      this.baseURI_$eq(scala.None..MODULE$);
      this.xmlEncoding_$eq(scala.None..MODULE$);
      Node var2 = (Node)this.hStack().last();
      this.hStack_$eq(scala.collection.immutable.Nil..MODULE$.$colon$colon(var2));
      this.scopeStack_$eq((List)this.scopeStack().tail());
      this.rootElem_$eq((Node)null);
      this.prolog_$eq(scala.package..MODULE$.List().empty());
      this.epilogue_$eq(scala.package..MODULE$.List().empty());
      this.buffer().clear();
      this.inCDATA_$eq(false);
      this.capture_$eq(false);
      this.curTag_$eq((String)null);
      this.attribStack_$eq(scala.package..MODULE$.List().empty());
      this.tagStack_$eq(scala.package..MODULE$.List().empty());
   }

   public void startPrefixMapping(final String prefix, final String uri) {
      this.prefixMappings_$eq(this.prefixMappings().$colon$colon(new Tuple2(prefix, uri)));
   }

   public void endPrefixMapping(final String prefix) {
   }

   public void startElement(final String uri, final String _localName, final String qname, final Attributes attributes) {
      this.captureText();
      if (this.tagStack().isEmpty()) {
         this.prolog_$eq(this.hStack().reverse());
         this.hStack_$eq(scala.package..MODULE$.List().empty());
      }

      this.tagStack_$eq(this.tagStack().$colon$colon(this.curTag()));
      this.curTag_$eq(qname);
      String localName = (String)Utility$.MODULE$.splitName(qname)._2();
      this.capture_$eq(this.nodeContainsText(localName));
      this.hStack_$eq(this.hStack().$colon$colon((Object)null));
      ObjectRef m = ObjectRef.create(Null$.MODULE$);
      ObjectRef scpe = ObjectRef.create(this.scopeStack().isEmpty() ? TopScope$.MODULE$ : (NamespaceBinding)this.scopeStack().head());
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), attributes.getLength()).reverse().foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
         String qname = attributes.getQName(i);
         String value = attributes.getValue(i);
         Tuple2 var8 = Utility$.MODULE$.splitName(qname);
         if (var8 != null) {
            Option pre = (Option)var8._1();
            String key = (String)var8._2();
            if (pre != null && key != null) {
               Option pre;
               String key;
               label39: {
                  Tuple2 var7 = new Tuple2(pre, key);
                  pre = (Option)var7._1();
                  key = (String)var7._2();
                  if (!pre.contains("xmlns")) {
                     if (!pre.isEmpty()) {
                        break label39;
                     }

                     String var15 = "xmlns";
                     if (qname == null) {
                        if (var15 != null) {
                           break label39;
                        }
                     } else if (!qname.equals(var15)) {
                        break label39;
                     }
                  }

                  String arg = pre.isEmpty() ? null : key;
                  scpe.elem = new NamespaceBinding(arg, nullIfEmpty$1(value), (NamespaceBinding)scpe.elem);
                  return;
               }

               m.elem = (MetaData)Attribute$.MODULE$.apply((Option)pre, key, (scala.collection.Seq)Text$.MODULE$.apply(value), (MetaData)m.elem);
               return;
            }
         }

         throw new MatchError(var8);
      });
      this.prefixMappings().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$startElement$2(check$ifrefutable$1))).foreach((x$5) -> {
         $anonfun$startElement$3(scpe, x$5);
         return BoxedUnit.UNIT;
      });
      this.prefixMappings_$eq(scala.package..MODULE$.List().empty());
      this.scopeStack_$eq(this.scopeStack().$colon$colon((NamespaceBinding)scpe.elem));
      this.attribStack_$eq(this.attribStack().$colon$colon((MetaData)m.elem));
   }

   public void endElement(final String uri, final String _localName, final String qname) {
      MetaData metaData;
      List v;
      List var10001;
      label35: {
         this.captureText();
         metaData = (MetaData)this.attribStack().head();
         this.attribStack_$eq((List)this.attribStack().tail());
         v = this.hStack().takeWhile((x$6) -> BoxesRunTime.boxToBoolean($anonfun$endElement$1(x$6))).reverse();
         List var8 = (List)this.hStack().dropWhile((x$7) -> BoxesRunTime.boxToBoolean($anonfun$endElement$2(x$7)));
         if (var8 instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var9 = (scala.collection.immutable..colon.colon)var8;
            Node var10 = (Node)var9.head();
            List hs = var9.next$access$1();
            if (var10 == null) {
               var10001 = hs;
               break label35;
            }
         }

         var10001 = var8;
      }

      this.hStack_$eq(var10001);
      Tuple2 var13 = Utility$.MODULE$.splitName(qname);
      if (var13 != null) {
         Option pre = (Option)var13._1();
         String localName = (String)var13._2();
         if (pre != null && localName != null) {
            Tuple2 var12 = new Tuple2(pre, localName);
            Option pre = (Option)var12._1();
            String localName = (String)var12._2();
            NamespaceBinding scp = (NamespaceBinding)this.scopeStack().head();
            this.scopeStack_$eq((List)this.scopeStack().tail());
            this.rootElem_$eq(this.createNode((String)pre.orNull(scala..less.colon.less..MODULE$.refl()), localName, metaData, scp, v));
            this.hStack_$eq(this.hStack().$colon$colon(this.rootElem()));
            this.curTag_$eq((String)this.tagStack().head());
            this.tagStack_$eq((List)this.tagStack().tail());
            this.capture_$eq(this.curTag() != null && this.nodeContainsText(this.curTag()));
            return;
         }
      }

      throw new MatchError(var13);
   }

   public void characters(final char[] ch, final int offset, final int length) {
      if (this.capture()) {
         if (!this.normalizeWhitespace()) {
            this.buffer().appendAll(ch, offset, length);
         } else {
            Iterator it = scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.charArrayOps((char[])scala.collection.ArrayOps..MODULE$.slice$extension(scala.Predef..MODULE$.charArrayOps(ch), offset, offset + length)));

            while(it.hasNext()) {
               char c = BoxesRunTime.unboxToChar(it.next());
               boolean isSpace = scala.runtime.RichChar..MODULE$.isWhitespace$extension(scala.Predef..MODULE$.charWrapper(c));
               this.buffer().append(isSpace ? ' ' : c);
               if (isSpace) {
                  it = it.dropWhile((x$9) -> BoxesRunTime.boxToBoolean($anonfun$characters$1(BoxesRunTime.unboxToChar(x$9))));
               }
            }

         }
      }
   }

   public void ignorableWhitespace(final char[] ch, final int offset, final int length) {
   }

   public void processingInstruction(final String target, final String data) {
      if (this.inDtd()) {
         this.dtdBuilder().foreach((x$10) -> {
            $anonfun$processingInstruction$1(target, data, x$10);
            return BoxedUnit.UNIT;
         });
      } else {
         this.captureText();
         this.hStack_$eq(this.hStack().reverse_$colon$colon$colon(this.createProcInstr(target, data).toList()));
      }
   }

   public void skippedEntity(final String name) {
   }

   public void startDTD(final String name, final String publicId, final String systemId) {
      this.dtdBuilder_$eq(new Some(DtdBuilder$.MODULE$.apply(name, publicId, systemId)));
   }

   public void endDTD() {
      this.dtdBuilder().foreach((x$11) -> {
         $anonfun$endDTD$1(x$11);
         return BoxedUnit.UNIT;
      });
   }

   public void startEntity(final String name) {
      this.dtdBuilder().foreach((x$12) -> {
         $anonfun$startEntity$1(name, x$12);
         return BoxedUnit.UNIT;
      });
   }

   public void endEntity(final String name) {
      this.dtdBuilder().foreach((x$13) -> {
         $anonfun$endEntity$1(name, x$13);
         return BoxedUnit.UNIT;
      });
   }

   public void startCDATA() {
      this.captureText();
      this.inCDATA_$eq(true);
   }

   public void endCDATA() {
      this.captureText();
   }

   public void comment(final char[] ch, final int start, final int length) {
      String commentText = String.valueOf((char[])scala.collection.ArrayOps..MODULE$.slice$extension(scala.Predef..MODULE$.charArrayOps(ch), start, start + length));
      if (this.inDtd()) {
         this.dtdBuilder().foreach((x$14) -> {
            $anonfun$comment$1(commentText, x$14);
            return BoxedUnit.UNIT;
         });
      } else {
         this.captureText();
         this.hStack_$eq(this.hStack().reverse_$colon$colon$colon(this.createComment(commentText).toList()));
      }
   }

   public void notationDecl(final String name, final String publicId, final String systemId) {
      this.dtdBuilder().foreach((x$15) -> {
         $anonfun$notationDecl$1(name, publicId, systemId, x$15);
         return BoxedUnit.UNIT;
      });
   }

   public void unparsedEntityDecl(final String name, final String publicId, final String systemId, final String notationName) {
      this.dtdBuilder().foreach((x$16) -> {
         $anonfun$unparsedEntityDecl$1(name, publicId, systemId, notationName, x$16);
         return BoxedUnit.UNIT;
      });
   }

   public void elementDecl(final String name, final String model) {
      this.dtdBuilder().foreach((x$17) -> {
         $anonfun$elementDecl$1(name, model, x$17);
         return BoxedUnit.UNIT;
      });
   }

   public void attributeDecl(final String eName, final String aName, final String type, final String mode, final String value) {
      this.dtdBuilder().foreach((x$18) -> {
         $anonfun$attributeDecl$1(eName, aName, type, mode, value, x$18);
         return BoxedUnit.UNIT;
      });
   }

   public void internalEntityDecl(final String name, final String value) {
      this.dtdBuilder().foreach((x$19) -> {
         $anonfun$internalEntityDecl$1(name, value, x$19);
         return BoxedUnit.UNIT;
      });
   }

   public void externalEntityDecl(final String name, final String publicId, final String systemId) {
      this.dtdBuilder().foreach((x$20) -> {
         $anonfun$externalEntityDecl$1(name, publicId, systemId, x$20);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   private final Option liftedTree1$1() {
      Object var10000;
      try {
         var10000 = .MODULE$.apply((String)((XMLReader)this.xmlReader().get()).getProperty("http://xml.org/sax/properties/document-xml-version"));
      } catch (SAXNotRecognizedException var1) {
         var10000 = scala.None..MODULE$;
      } catch (SAXNotSupportedException var2) {
         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   // $FF: synthetic method
   private final Option liftedTree2$1() {
      Object var10000;
      try {
         var10000 = new Some(BoxesRunTime.boxToBoolean(((XMLReader)this.xmlReader().get()).getFeature("http://xml.org/sax/features/is-standalone")));
      } catch (SAXNotRecognizedException var1) {
         var10000 = scala.None..MODULE$;
      } catch (SAXNotSupportedException var2) {
         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   private static final String nullIfEmpty$1(final String s) {
      String var1 = "";
      if (s == null) {
         if (var1 == null) {
            return null;
         }
      } else if (s.equals(var1)) {
         return null;
      }

      return s;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$startElement$2(final Tuple2 check$ifrefutable$1) {
      if (check$ifrefutable$1 != null) {
         String prefix = (String)check$ifrefutable$1._1();
         String uri = (String)check$ifrefutable$1._2();
         if (prefix != null && uri != null) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final void $anonfun$startElement$3(final ObjectRef scpe$1, final Tuple2 x$5) {
      if (x$5 != null) {
         String prefix = (String)x$5._1();
         String uri = (String)x$5._2();
         if (prefix != null && uri != null) {
            scpe$1.elem = new NamespaceBinding(prefix.isEmpty() ? null : prefix, uri, (NamespaceBinding)scpe$1.elem);
            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }
      }

      throw new MatchError(x$5);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$endElement$1(final Node x$6) {
      return x$6 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$endElement$2(final Node x$7) {
      return x$7 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$characters$1(final char x$9) {
      return scala.runtime.RichChar..MODULE$.isWhitespace$extension(scala.Predef..MODULE$.charWrapper(x$9));
   }

   // $FF: synthetic method
   public static final void $anonfun$processingInstruction$1(final String target$1, final String data$1, final DtdBuilder x$10) {
      x$10.processingInstruction(target$1, data$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$endDTD$1(final DtdBuilder x$11) {
      x$11.endDTD();
   }

   // $FF: synthetic method
   public static final void $anonfun$startEntity$1(final String name$1, final DtdBuilder x$12) {
      x$12.startEntity(name$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$endEntity$1(final String name$2, final DtdBuilder x$13) {
      x$13.endEntity(name$2);
   }

   // $FF: synthetic method
   public static final void $anonfun$comment$1(final String commentText$1, final DtdBuilder x$14) {
      x$14.comment(commentText$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$notationDecl$1(final String name$3, final String publicId$1, final String systemId$1, final DtdBuilder x$15) {
      x$15.notationDecl(name$3, publicId$1, systemId$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$unparsedEntityDecl$1(final String name$4, final String publicId$2, final String systemId$2, final String notationName$1, final DtdBuilder x$16) {
      x$16.unparsedEntityDecl(name$4, publicId$2, systemId$2, notationName$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$elementDecl$1(final String name$5, final String model$1, final DtdBuilder x$17) {
      x$17.elementDecl(name$5, model$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$attributeDecl$1(final String eName$1, final String aName$1, final String type$1, final String mode$1, final String value$1, final DtdBuilder x$18) {
      x$18.attributeDecl(eName$1, aName$1, type$1, mode$1, value$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$internalEntityDecl$1(final String name$6, final String value$2, final DtdBuilder x$19) {
      x$19.internalEntityDecl(name$6, value$2);
   }

   // $FF: synthetic method
   public static final void $anonfun$externalEntityDecl$1(final String name$7, final String publicId$3, final String systemId$3, final DtdBuilder x$20) {
      x$20.externalEntityDecl(name$7, publicId$3, systemId$3);
   }

   public FactoryAdapter() {
      XMLLoader.$init$(this);
      this.normalizeWhitespace = false;
      this.xmlReader = scala.None..MODULE$;
      this.dtdBuilder = scala.None..MODULE$;
      this.document = scala.None..MODULE$;
      this.baseURI = scala.None..MODULE$;
      this.xmlEncoding = scala.None..MODULE$;
      this.prefixMappings = scala.package..MODULE$.List().empty();
      this.prolog = scala.package..MODULE$.List().empty();
      this.epilogue = scala.package..MODULE$.List().empty();
      this.buffer = new StringBuilder();
      this.inCDATA = false;
      this.attribStack = scala.package..MODULE$.List().empty();
      this.hStack = scala.package..MODULE$.List().empty();
      this.tagStack = scala.package..MODULE$.List().empty();
      this.scopeStack = scala.package..MODULE$.List().empty();
      this.capture = false;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
