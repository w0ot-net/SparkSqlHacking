package breeze.optimize.linear;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.package$;
import breeze.linalg.operators.HasOps$;
import breeze.optimize.proximal.QuadraticMinimizer$;
import breeze.storage.Zero$;
import breeze.util.LazyLogger;
import breeze.util.SerializableLogging;
import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple10;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.AbstractFunction10;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t%h\u0001B$I\u0001=C\u0001\u0002\u0018\u0001\u0003\u0006\u0004%\t!\u0018\u0005\tC\u0002\u0011\t\u0011)A\u0005=\")!\r\u0001C\u0001G\u0016!q\r\u0001\u0001i\u000b\u0011\t\b\u0001\u0001:\u0007\tU\u0004\u0001I\u001e\u0005\u000b\u0003\u001b1!Q3A\u0005\u0002\u0005=\u0001BCA\u000b\r\tE\t\u0015!\u0003\u0002\u0012!Q\u0011q\u0003\u0004\u0003\u0016\u0004%\t!a\u0004\t\u0015\u0005eaA!E!\u0002\u0013\t\t\u0002\u0003\u0006\u0002\u001c\u0019\u0011)\u001a!C\u0001\u0003\u001fA!\"!\b\u0007\u0005#\u0005\u000b\u0011BA\t\u0011)\tyB\u0002BK\u0002\u0013\u0005\u0011q\u0002\u0005\u000b\u0003C1!\u0011#Q\u0001\n\u0005E\u0001BCA\u0012\r\tU\r\u0011\"\u0001\u0002\u0010!Q\u0011Q\u0005\u0004\u0003\u0012\u0003\u0006I!!\u0005\t\u0015\u0005\u001dbA!f\u0001\n\u0003\ty\u0001\u0003\u0006\u0002*\u0019\u0011\t\u0012)A\u0005\u0003#A!\"a\u000b\u0007\u0005+\u0007I\u0011AA\u0017\u0011%\tyC\u0002B\tB\u0003%a\u000eC\u0005\u00022\u0019\u0011)\u001a!C\u0001;\"I\u00111\u0007\u0004\u0003\u0012\u0003\u0006IA\u0018\u0005\n\u0003k1!Q3A\u0005\u0002uC\u0011\"a\u000e\u0007\u0005#\u0005\u000b\u0011\u00020\t\u0015\u0005ebA!f\u0001\n\u0003\tY\u0004\u0003\u0006\u0002D\u0019\u0011\t\u0012)A\u0005\u0003{AqA\u0019\u0004\u0005\u0002\u0001\t)\u0005C\u0005\u0002^\u0019\t\t\u0011\"\u0001\u0002`!I\u0011Q\u000f\u0004\u0012\u0002\u0013\u0005\u0011q\u000f\u0005\n\u0003\u001b3\u0011\u0013!C\u0001\u0003oB\u0011\"a$\u0007#\u0003%\t!a\u001e\t\u0013\u0005Ee!%A\u0005\u0002\u0005]\u0004\"CAJ\rE\u0005I\u0011AA<\u0011%\t)JBI\u0001\n\u0003\t9\bC\u0005\u0002\u0018\u001a\t\n\u0011\"\u0001\u0002\u001a\"I\u0011Q\u0014\u0004\u0012\u0002\u0013\u0005\u0011q\u0014\u0005\n\u0003G3\u0011\u0013!C\u0001\u0003?C\u0011\"!*\u0007#\u0003%\t!a*\t\u0013\u0005-f!!A\u0005B\u00055\u0006\u0002CA`\r\u0005\u0005I\u0011A/\t\u0013\u0005\u0005g!!A\u0005\u0002\u0005\r\u0007\"CAh\r\u0005\u0005I\u0011IAi\u0011%\tyNBA\u0001\n\u0003\t\t\u000fC\u0005\u0002f\u001a\t\t\u0011\"\u0011\u0002h\"I\u00111\u001e\u0004\u0002\u0002\u0013\u0005\u0013Q\u001e\u0005\n\u0003_4\u0011\u0011!C!\u0003cD\u0011\"a=\u0007\u0003\u0003%\t%!>\b\u0013\u0005e\b!!A\t\u0002\u0005mh\u0001C;\u0001\u0003\u0003E\t!!@\t\r\t\fD\u0011\u0001B\u000b\u0011%\ty/MA\u0001\n\u000b\n\t\u0010C\u0005\u0003\u0018E\n\t\u0011\"!\u0003\u001a!I!qF\u0019\u0002\u0002\u0013\u0005%\u0011\u0007\u0005\b\u0005\u0007\u0002A\u0011\u0002B#\u0011\u001d\u0011\u0019\u0006\u0001C\u0005\u0005+BqAa\u0019\u0001\t\u0003\u0011)\u0007C\u0004\u0003l\u0001!\tA!\u001c\t\u000f\te\u0004\u0001\"\u0001\u0003|!I!\u0011\u0012\u0001\u0012\u0002\u0013\u0005\u0011q\u0015\u0005\b\u0005s\u0002A\u0011\u0001BF\u0011\u001d\u0011\t\n\u0001C\u0001\u0005'CqA!%\u0001\t\u0003\u0011IjB\u0004\u0003$\"C\tA!*\u0007\r\u001dC\u0005\u0012\u0001BT\u0011\u0019\u0011\u0007\t\"\u0001\u0003*\"9!1\u0016!\u0005\u0002\t5\u0006b\u0002B\f\u0001\u0012\u0005!Q\u0017\u0005\b\u0005w\u0003E\u0011\u0001B_\u0011%\u0011i\u000eQI\u0001\n\u0003\ty\nC\u0005\u0003`\u0002\u000b\t\u0011\"\u0003\u0003b\n!aJ\u0014'T\u0015\tI%*\u0001\u0004mS:,\u0017M\u001d\u0006\u0003\u00172\u000b\u0001b\u001c9uS6L'0\u001a\u0006\u0002\u001b\u00061!M]3fu\u0016\u001c\u0001aE\u0002\u0001!Z\u0003\"!\u0015+\u000e\u0003IS\u0011aU\u0001\u0006g\u000e\fG.Y\u0005\u0003+J\u0013a!\u00118z%\u00164\u0007CA,[\u001b\u0005A&BA-M\u0003\u0011)H/\u001b7\n\u0005mC&aE*fe&\fG.\u001b>bE2,Gj\\4hS:<\u0017\u0001C7bq&#XM]:\u0016\u0003y\u0003\"!U0\n\u0005\u0001\u0014&aA%oi\u0006IQ.\u0019=Ji\u0016\u00148\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u00114\u0007CA3\u0001\u001b\u0005A\u0005b\u0002/\u0004!\u0003\u0005\rA\u0018\u0002\u0004\u0005\u0012k\u0005cA5m]6\t!N\u0003\u0002l\u0019\u00061A.\u001b8bY\u001eL!!\u001c6\u0003\u0017\u0011+gn]3NCR\u0014\u0018\u000e\u001f\t\u0003#>L!\u0001\u001d*\u0003\r\u0011{WO\u00197f\u0005\r\u0011EI\u0016\t\u0004SNt\u0017B\u0001;k\u0005-!UM\\:f-\u0016\u001cGo\u001c:\u0003\u000bM#\u0018\r^3\u0014\t\u0019\u0001vO\u001f\t\u0003#bL!!\u001f*\u0003\u000fA\u0013x\u000eZ;diB\u001910a\u0002\u000f\u0007q\f\u0019AD\u0002~\u0003\u0003i\u0011A \u0006\u0003\u007f:\u000ba\u0001\u0010:p_Rt\u0014\"A*\n\u0007\u0005\u0015!+A\u0004qC\u000e\\\u0017mZ3\n\t\u0005%\u00111\u0002\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0004\u0003\u000b\u0011\u0016!\u0001=\u0016\u0005\u0005E\u0001cAA\n\u000b5\t\u0001!\u0001\u0002yA\u0005!qM]1e\u0003\u00159'/\u00193!\u0003\r!\u0017N]\u0001\u0005I&\u0014\b%A\u0004mCN$H)\u001b:\u0002\u00111\f7\u000f\u001e#je\u0002\n1A]3t\u0003\u0011\u0011Xm\u001d\u0011\u0002\u0007Ql\u0007/\u0001\u0003u[B\u0004\u0013\u0001\u00037bgRtuN]7\u0016\u00039\f\u0011\u0002\\1ti:{'/\u001c\u0011\u0002\u00111\f7\u000f^,bY2\f\u0011\u0002\\1ti^\u000bG\u000e\u001c\u0011\u0002\t%$XM]\u0001\u0006SR,'\u000fI\u0001\nG>tg/\u001a:hK\u0012,\"!!\u0010\u0011\u0007E\u000by$C\u0002\u0002BI\u0013qAQ8pY\u0016\fg.\u0001\u0006d_:4XM]4fI\u0002\"b#a\u0012\u0002J\u0005-\u0013QJA(\u0003#\n\u0019&!\u0016\u0002X\u0005e\u00131\f\t\u0004\u0003'1\u0001bBA\u00077\u0001\u0007\u0011\u0011\u0003\u0005\b\u0003/Y\u0002\u0019AA\t\u0011\u001d\tYb\u0007a\u0001\u0003#Aq!a\b\u001c\u0001\u0004\t\t\u0002C\u0004\u0002$m\u0001\r!!\u0005\t\u000f\u0005\u001d2\u00041\u0001\u0002\u0012!1\u00111F\u000eA\u00029Da!!\r\u001c\u0001\u0004q\u0006BBA\u001b7\u0001\u0007a\fC\u0004\u0002:m\u0001\r!!\u0010\u0002\t\r|\u0007/\u001f\u000b\u0017\u0003\u000f\n\t'a\u0019\u0002f\u0005\u001d\u0014\u0011NA6\u0003[\ny'!\u001d\u0002t!I\u0011Q\u0002\u000f\u0011\u0002\u0003\u0007\u0011\u0011\u0003\u0005\n\u0003/a\u0002\u0013!a\u0001\u0003#A\u0011\"a\u0007\u001d!\u0003\u0005\r!!\u0005\t\u0013\u0005}A\u0004%AA\u0002\u0005E\u0001\"CA\u00129A\u0005\t\u0019AA\t\u0011%\t9\u0003\bI\u0001\u0002\u0004\t\t\u0002\u0003\u0005\u0002,q\u0001\n\u00111\u0001o\u0011!\t\t\u0004\bI\u0001\u0002\u0004q\u0006\u0002CA\u001b9A\u0005\t\u0019\u00010\t\u0013\u0005eB\u0004%AA\u0002\u0005u\u0012AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003sRC!!\u0005\u0002|-\u0012\u0011Q\u0010\t\u0005\u0003\u007f\nI)\u0004\u0002\u0002\u0002*!\u00111QAC\u0003%)hn\u00195fG.,GMC\u0002\u0002\bJ\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\tY)!!\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\"\u0014AD2paf$C-\u001a4bk2$H%N\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00137\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uI]*\"!a'+\u00079\fY(\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001d\u0016\u0005\u0005\u0005&f\u00010\u0002|\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012J\u0014aD2paf$C-\u001a4bk2$H%\r\u0019\u0016\u0005\u0005%&\u0006BA\u001f\u0003w\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAAX!\u0011\t\t,a/\u000e\u0005\u0005M&\u0002BA[\u0003o\u000bA\u0001\\1oO*\u0011\u0011\u0011X\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002>\u0006M&AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005\u0015\u00171\u001a\t\u0004#\u0006\u001d\u0017bAAe%\n\u0019\u0011I\\=\t\u0011\u00055\u0017&!AA\u0002y\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAAj!\u0019\t).a7\u0002F6\u0011\u0011q\u001b\u0006\u0004\u00033\u0014\u0016AC2pY2,7\r^5p]&!\u0011Q\\Al\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005u\u00121\u001d\u0005\n\u0003\u001b\\\u0013\u0011!a\u0001\u0003\u000b\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011qVAu\u0011!\ti\rLA\u0001\u0002\u0004q\u0016\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003y\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003_\u000ba!Z9vC2\u001cH\u0003BA\u001f\u0003oD\u0011\"!40\u0003\u0003\u0005\r!!2\u0002\u000bM#\u0018\r^3\u0011\u0007\u0005M\u0011gE\u00032\u0003\u007f\u0014Y\u0001E\f\u0003\u0002\t\u001d\u0011\u0011CA\t\u0003#\t\t\"!\u0005\u0002\u00129tf,!\u0010\u0002H5\u0011!1\u0001\u0006\u0004\u0005\u000b\u0011\u0016a\u0002:v]RLW.Z\u0005\u0005\u0005\u0013\u0011\u0019A\u0001\nBEN$(/Y2u\rVt7\r^5p]F\u0002\u0004\u0003\u0002B\u0007\u0005'i!Aa\u0004\u000b\t\tE\u0011qW\u0001\u0003S>LA!!\u0003\u0003\u0010Q\u0011\u00111`\u0001\u0006CB\u0004H.\u001f\u000b\u0017\u0003\u000f\u0012YB!\b\u0003 \t\u0005\"1\u0005B\u0013\u0005O\u0011ICa\u000b\u0003.!9\u0011Q\u0002\u001bA\u0002\u0005E\u0001bBA\fi\u0001\u0007\u0011\u0011\u0003\u0005\b\u00037!\u0004\u0019AA\t\u0011\u001d\ty\u0002\u000ea\u0001\u0003#Aq!a\t5\u0001\u0004\t\t\u0002C\u0004\u0002(Q\u0002\r!!\u0005\t\r\u0005-B\u00071\u0001o\u0011\u0019\t\t\u0004\u000ea\u0001=\"1\u0011Q\u0007\u001bA\u0002yCq!!\u000f5\u0001\u0004\ti$A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\tM\"q\b\t\u0006#\nU\"\u0011H\u0005\u0004\u0005o\u0011&AB(qi&|g\u000e\u0005\u000bR\u0005w\t\t\"!\u0005\u0002\u0012\u0005E\u0011\u0011CA\t]zs\u0016QH\u0005\u0004\u0005{\u0011&a\u0002+va2,\u0017\u0007\r\u0005\n\u0005\u0003*\u0014\u0011!a\u0001\u0003\u000f\n1\u0001\u001f\u00131\u0003\u001d\u0019H/\u001a9mK:$\u0012B\u001cB$\u0005\u001b\u0012yE!\u0015\t\u000f\t%c\u00071\u0001\u0003L\u0005\u0019\u0011\r^1\u0011\u0007\u0005MA\u0001C\u0004\u0002\u001cY\u0002\r!!\u0005\t\u000f\u0005\rb\u00071\u0001\u0002\u0012!9\u0011q\u0005\u001cA\u0002\u0005E\u0011\u0001B:u_B$\u0002\"!\u0010\u0003X\tm#q\f\u0005\u0007\u00053:\u0004\u0019\u00018\u0002\tM$X\r\u001d\u0005\u0007\u0005;:\u0004\u0019\u00018\u0002\t9$\u0017N\u001d\u0005\u0007\u0005C:\u0004\u0019\u00018\u0002\u00059D\u0018AC5oSRL\u0017\r\\5{KR!\u0011q\tB4\u0011\u0019\u0011I\u0007\u000fa\u0001=\u0006\ta.A\u0003sKN,G\u000f\u0006\u0005\u0002H\t=$\u0011\u000fB;\u0011\u0019\u0011I%\u000fa\u0001Q\"1!1O\u001dA\u0002I\f1!\u0019;c\u0011\u001d\u00119(\u000fa\u0001\u0003\u000f\nQa\u001d;bi\u0016\fa#\\5oS6L'0Z!oIJ+G/\u001e:o'R\fG/\u001a\u000b\u000b\u0003\u000f\u0012iHa \u0003\u0002\n\u0015\u0005B\u0002B%u\u0001\u0007\u0001\u000e\u0003\u0004\u0003ti\u0002\rA\u001d\u0005\b\u0005\u0007S\u0004\u0019AA$\u00031Ig.\u001b;jC2\u001cF/\u0019;f\u0011%\u00119I\u000fI\u0001\u0002\u0004\ti$\u0001\u0006sKN,Go\u0015;bi\u0016\f\u0001%\\5oS6L'0Z!oIJ+G/\u001e:o'R\fG/\u001a\u0013eK\u001a\fW\u000f\u001c;%iQ1\u0011q\tBG\u0005\u001fCaA!\u0013=\u0001\u0004A\u0007B\u0002B:y\u0001\u0007!/\u0001\u0005nS:LW.\u001b>f)\u0015\u0011(Q\u0013BL\u0011\u0019\u0011I%\u0010a\u0001Q\"1!1O\u001fA\u0002I$rA\u001dBN\u0005;\u0013y\n\u0003\u0004\u0003Jy\u0002\r\u0001\u001b\u0005\u0007\u0005gr\u0004\u0019\u0001:\t\u000f\t\u0005f\b1\u0001\u0002H\u0005!\u0011N\\5u\u0003\u0011qe\nT*\u0011\u0005\u0015\u00045\u0003\u0002!Q\u0005\u0017!\"A!*\u0002+\r|W\u000e];uK>\u0013'.Z2uSZ,g+\u00197vKR9aNa,\u00032\nM\u0006B\u0002B%\u0005\u0002\u0007\u0001\u000e\u0003\u0004\u0003t\t\u0003\rA\u001d\u0005\u0007\u0003\u001b\u0011\u0005\u0019\u0001:\u0015\u0007\u0011\u00149\f\u0003\u0004\u0003:\u000e\u0003\rAX\u0001\u0006SR,'o]\u0001\u0005[\u0006Lg\u000e\u0006\u0003\u0003@\n\u0015\u0007cA)\u0003B&\u0019!1\u0019*\u0003\tUs\u0017\u000e\u001e\u0005\b\u0005\u000f$\u0005\u0019\u0001Be\u0003\u0011\t'oZ:\u0011\u000bE\u0013YMa4\n\u0007\t5'KA\u0003BeJ\f\u0017\u0010\u0005\u0003\u0003R\neg\u0002\u0002Bj\u0005+\u0004\"! *\n\u0007\t]'+\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003{\u0013YNC\u0002\u0003XJ\u000b1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\n\u0014\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001Br!\u0011\t\tL!:\n\t\t\u001d\u00181\u0017\u0002\u0007\u001f\nTWm\u0019;"
)
public class NNLS implements SerializableLogging {
   private volatile State$ State$module;
   private final int maxIters;
   private transient volatile LazyLogger breeze$util$SerializableLogging$$_the_logger;

   public static int $lessinit$greater$default$1() {
      return NNLS$.MODULE$.$lessinit$greater$default$1();
   }

   public static void main(final String[] args) {
      NNLS$.MODULE$.main(args);
   }

   public static NNLS apply(final int iters) {
      return NNLS$.MODULE$.apply(iters);
   }

   public static double computeObjectiveValue(final DenseMatrix ata, final DenseVector atb, final DenseVector x) {
      return NNLS$.MODULE$.computeObjectiveValue(ata, atb, x);
   }

   public LazyLogger logger() {
      return SerializableLogging.logger$(this);
   }

   public State$ State() {
      if (this.State$module == null) {
         this.State$lzycompute$1();
      }

      return this.State$module;
   }

   public LazyLogger breeze$util$SerializableLogging$$_the_logger() {
      return this.breeze$util$SerializableLogging$$_the_logger;
   }

   public void breeze$util$SerializableLogging$$_the_logger_$eq(final LazyLogger x$1) {
      this.breeze$util$SerializableLogging$$_the_logger = x$1;
   }

   public int maxIters() {
      return this.maxIters;
   }

   private double steplen(final DenseMatrix ata, final DenseVector dir, final DenseVector res, final DenseVector tmp) {
      double top = BoxesRunTime.unboxToDouble(dir.dot(res, HasOps$.MODULE$.canDotD()));
      QuadraticMinimizer$.MODULE$.gemv((double)1.0F, ata, dir, (double)0.0F, tmp);
      return top / (BoxesRunTime.unboxToDouble(tmp.dot(dir, HasOps$.MODULE$.canDotD())) + 1.0E-20);
   }

   private boolean stop(final double step, final double ndir, final double nx) {
      return Double.isNaN(step) || step < 1.0E-7 || step > 1.0E40 || ndir < 1.0E-12 * nx || ndir < 1.0E-32;
   }

   public State initialize(final int n) {
      DenseVector grad = DenseVector$.MODULE$.zeros$mDc$sp(n, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      DenseVector x = DenseVector$.MODULE$.zeros$mDc$sp(n, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      DenseVector dir = DenseVector$.MODULE$.zeros$mDc$sp(n, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      DenseVector lastDir = DenseVector$.MODULE$.zeros$mDc$sp(n, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      DenseVector res = DenseVector$.MODULE$.zeros$mDc$sp(n, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      DenseVector tmp = DenseVector$.MODULE$.zeros$mDc$sp(n, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      double lastNorm = (double)0.0F;
      int lastWall = 0;
      return this.State().apply(x, grad, dir, lastDir, res, tmp, lastNorm, lastWall, 0, false);
   }

   public State reset(final DenseMatrix ata, final DenseVector atb, final State state) {
      int left$macro$1 = ata.cols();
      int right$macro$2 = ata.rows();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(49)).append("requirement failed: ").append("NNLS:iterations gram matrix must be symmetric").append(": ").append("ata.cols == ata.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         int left$macro$3 = ata.rows();
         int right$macro$4 = state.x().length();
         if (left$macro$3 != right$macro$4) {
            throw new IllegalArgumentException((new StringBuilder(55)).append("requirement failed: ").append("NNLS:iterations gram and linear dimension mismatch").append(": ").append("ata.rows == state.x.length (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
         } else {
            state.x().$colon$eq(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet());
            state.grad().$colon$eq(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet());
            state.dir().$colon$eq(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet());
            state.lastDir().$colon$eq(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet());
            state.res().$colon$eq(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet());
            state.tmp().$colon$eq(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet());
            return this.State().apply(state.x(), state.grad(), state.dir(), state.lastDir(), state.res(), state.tmp(), (double)0.0F, 0, 0, false);
         }
      }
   }

   public State minimizeAndReturnState(final DenseMatrix ata, final DenseVector atb, final State initialState, final boolean resetState) {
      State startState = resetState ? this.reset(ata, atb, initialState) : initialState;
      int n = atb.length();
      int iterMax = this.maxIters() < 0 ? Math.max(400, 20 * n) : this.maxIters();
      double nextNorm = startState.lastNorm();
      int nextWall = startState.lastWall();

      int nextIter;
      for(nextIter = 0; nextIter <= iterMax; ++nextIter) {
         QuadraticMinimizer$.MODULE$.gemv((double)1.0F, ata, startState.x(), (double)0.0F, startState.res());
         package$.MODULE$.axpy(BoxesRunTime.boxToDouble((double)-1.0F), atb, startState.res(), HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_T_DV_Double());
         startState.grad().$colon$eq(startState.res(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
         int index$macro$2 = 0;

         for(int limit$macro$4 = n; index$macro$2 < limit$macro$4; ++index$macro$2) {
            if (startState.grad().apply$mcD$sp(index$macro$2) > (double)0.0F && startState.x().apply$mcD$sp(index$macro$2) == (double)0.0F) {
               startState.grad().update$mcD$sp(index$macro$2, (double)0.0F);
            }
         }

         double ngrad = BoxesRunTime.unboxToDouble(startState.grad().dot(startState.grad(), HasOps$.MODULE$.canDotD()));
         startState.dir().$colon$eq(startState.grad(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
         double step = this.steplen(ata, startState.grad(), startState.res(), startState.tmp());
         double ndir = (double)0.0F;
         double nx = BoxesRunTime.unboxToDouble(startState.x().dot(startState.x(), HasOps$.MODULE$.canDotD()));
         if (nextIter > nextWall + 1) {
            double alpha = ngrad / nextNorm;
            package$.MODULE$.axpy(BoxesRunTime.boxToDouble(alpha), startState.lastDir(), startState.dir(), HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_T_DV_Double());
            double dstep = this.steplen(ata, startState.dir(), startState.res(), startState.tmp());
            ndir = BoxesRunTime.unboxToDouble(startState.dir().dot(startState.dir(), HasOps$.MODULE$.canDotD()));
            if (this.stop(dstep, ndir, nx)) {
               startState.dir().$colon$eq(startState.grad(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
               ndir = BoxesRunTime.unboxToDouble(startState.dir().dot(startState.dir(), HasOps$.MODULE$.canDotD()));
            } else {
               step = dstep;
            }
         } else {
            ndir = BoxesRunTime.unboxToDouble(startState.dir().dot(startState.dir(), HasOps$.MODULE$.canDotD()));
         }

         if (this.stop(step, ndir, nx)) {
            return this.State().apply(startState.x(), startState.grad(), startState.dir(), startState.lastDir(), startState.res(), startState.tmp(), nextNorm, nextWall, nextIter, true);
         }

         int index$macro$7 = 0;

         for(int limit$macro$9 = n; index$macro$7 < limit$macro$9; ++index$macro$7) {
            if (step * startState.dir().apply$mcD$sp(index$macro$7) > startState.x().apply$mcD$sp(index$macro$7)) {
               step = startState.x().apply$mcD$sp(index$macro$7) / startState.dir().apply$mcD$sp(index$macro$7);
            }
         }

         int index$macro$12 = 0;

         for(int limit$macro$14 = n; index$macro$12 < limit$macro$14; ++index$macro$12) {
            if (step * startState.dir().apply$mcD$sp(index$macro$12) > startState.x().apply$mcD$sp(index$macro$12) * 0.99999999999999) {
               startState.x().update$mcD$sp(index$macro$12, (double)0.0F);
               nextWall = nextIter;
            } else {
               startState.x().update$mcD$sp(index$macro$12, startState.x().apply$mcD$sp(index$macro$12) - step * startState.dir().apply$mcD$sp(index$macro$12));
            }
         }

         startState.lastDir().$colon$eq(startState.dir(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
         nextNorm = ngrad;
      }

      return this.State().apply(startState.x(), startState.grad(), startState.dir(), startState.lastDir(), startState.res(), startState.tmp(), nextNorm, nextWall, nextIter, false);
   }

   public State minimizeAndReturnState(final DenseMatrix ata, final DenseVector atb) {
      State initialState = this.initialize(atb.length());
      return this.minimizeAndReturnState(ata, atb, initialState, this.minimizeAndReturnState$default$4());
   }

   public DenseVector minimize(final DenseMatrix ata, final DenseVector atb) {
      return this.minimizeAndReturnState(ata, atb).x();
   }

   public DenseVector minimize(final DenseMatrix ata, final DenseVector atb, final State init) {
      return this.minimizeAndReturnState(ata, atb, init, this.minimizeAndReturnState$default$4()).x();
   }

   public boolean minimizeAndReturnState$default$4() {
      return true;
   }

   private final void State$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.State$module == null) {
            this.State$module = new State$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public NNLS(final int maxIters) {
      this.maxIters = maxIters;
      SerializableLogging.$init$(this);
   }

   public class State implements Product, Serializable {
      private final DenseVector x;
      private final DenseVector grad;
      private final DenseVector dir;
      private final DenseVector lastDir;
      private final DenseVector res;
      private final DenseVector tmp;
      private final double lastNorm;
      private final int lastWall;
      private final int iter;
      private final boolean converged;
      // $FF: synthetic field
      public final NNLS $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public DenseVector x() {
         return this.x;
      }

      public DenseVector grad() {
         return this.grad;
      }

      public DenseVector dir() {
         return this.dir;
      }

      public DenseVector lastDir() {
         return this.lastDir;
      }

      public DenseVector res() {
         return this.res;
      }

      public DenseVector tmp() {
         return this.tmp;
      }

      public double lastNorm() {
         return this.lastNorm;
      }

      public int lastWall() {
         return this.lastWall;
      }

      public int iter() {
         return this.iter;
      }

      public boolean converged() {
         return this.converged;
      }

      public State copy(final DenseVector x, final DenseVector grad, final DenseVector dir, final DenseVector lastDir, final DenseVector res, final DenseVector tmp, final double lastNorm, final int lastWall, final int iter, final boolean converged) {
         return this.breeze$optimize$linear$NNLS$State$$$outer().new State(x, grad, dir, lastDir, res, tmp, lastNorm, lastWall, iter, converged);
      }

      public DenseVector copy$default$1() {
         return this.x();
      }

      public boolean copy$default$10() {
         return this.converged();
      }

      public DenseVector copy$default$2() {
         return this.grad();
      }

      public DenseVector copy$default$3() {
         return this.dir();
      }

      public DenseVector copy$default$4() {
         return this.lastDir();
      }

      public DenseVector copy$default$5() {
         return this.res();
      }

      public DenseVector copy$default$6() {
         return this.tmp();
      }

      public double copy$default$7() {
         return this.lastNorm();
      }

      public int copy$default$8() {
         return this.lastWall();
      }

      public int copy$default$9() {
         return this.iter();
      }

      public String productPrefix() {
         return "State";
      }

      public int productArity() {
         return 10;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.x();
               break;
            case 1:
               var10000 = this.grad();
               break;
            case 2:
               var10000 = this.dir();
               break;
            case 3:
               var10000 = this.lastDir();
               break;
            case 4:
               var10000 = this.res();
               break;
            case 5:
               var10000 = this.tmp();
               break;
            case 6:
               var10000 = BoxesRunTime.boxToDouble(this.lastNorm());
               break;
            case 7:
               var10000 = BoxesRunTime.boxToInteger(this.lastWall());
               break;
            case 8:
               var10000 = BoxesRunTime.boxToInteger(this.iter());
               break;
            case 9:
               var10000 = BoxesRunTime.boxToBoolean(this.converged());
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
         return x$1 instanceof State;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "x";
               break;
            case 1:
               var10000 = "grad";
               break;
            case 2:
               var10000 = "dir";
               break;
            case 3:
               var10000 = "lastDir";
               break;
            case 4:
               var10000 = "res";
               break;
            case 5:
               var10000 = "tmp";
               break;
            case 6:
               var10000 = "lastNorm";
               break;
            case 7:
               var10000 = "lastWall";
               break;
            case 8:
               var10000 = "iter";
               break;
            case 9:
               var10000 = "converged";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.anyHash(this.x()));
         var1 = Statics.mix(var1, Statics.anyHash(this.grad()));
         var1 = Statics.mix(var1, Statics.anyHash(this.dir()));
         var1 = Statics.mix(var1, Statics.anyHash(this.lastDir()));
         var1 = Statics.mix(var1, Statics.anyHash(this.res()));
         var1 = Statics.mix(var1, Statics.anyHash(this.tmp()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.lastNorm()));
         var1 = Statics.mix(var1, this.lastWall());
         var1 = Statics.mix(var1, this.iter());
         var1 = Statics.mix(var1, this.converged() ? 1231 : 1237);
         return Statics.finalizeHash(var1, 10);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var17;
         if (this != x$1) {
            label112: {
               boolean var2;
               if (x$1 instanceof State && ((State)x$1).breeze$optimize$linear$NNLS$State$$$outer() == this.breeze$optimize$linear$NNLS$State$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label88: {
                     State var4 = (State)x$1;
                     if (this.lastNorm() == var4.lastNorm() && this.lastWall() == var4.lastWall() && this.iter() == var4.iter() && this.converged() == var4.converged()) {
                        label102: {
                           DenseVector var10000 = this.x();
                           DenseVector var5 = var4.x();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label102;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label102;
                           }

                           var10000 = this.grad();
                           DenseVector var6 = var4.grad();
                           if (var10000 == null) {
                              if (var6 != null) {
                                 break label102;
                              }
                           } else if (!var10000.equals(var6)) {
                              break label102;
                           }

                           var10000 = this.dir();
                           DenseVector var7 = var4.dir();
                           if (var10000 == null) {
                              if (var7 != null) {
                                 break label102;
                              }
                           } else if (!var10000.equals(var7)) {
                              break label102;
                           }

                           var10000 = this.lastDir();
                           DenseVector var8 = var4.lastDir();
                           if (var10000 == null) {
                              if (var8 != null) {
                                 break label102;
                              }
                           } else if (!var10000.equals(var8)) {
                              break label102;
                           }

                           var10000 = this.res();
                           DenseVector var9 = var4.res();
                           if (var10000 == null) {
                              if (var9 != null) {
                                 break label102;
                              }
                           } else if (!var10000.equals(var9)) {
                              break label102;
                           }

                           var10000 = this.tmp();
                           DenseVector var10 = var4.tmp();
                           if (var10000 == null) {
                              if (var10 != null) {
                                 break label102;
                              }
                           } else if (!var10000.equals(var10)) {
                              break label102;
                           }

                           if (var4.canEqual(this)) {
                              var17 = true;
                              break label88;
                           }
                        }
                     }

                     var17 = false;
                  }

                  if (var17) {
                     break label112;
                  }
               }

               var17 = false;
               return var17;
            }
         }

         var17 = true;
         return var17;
      }

      // $FF: synthetic method
      public NNLS breeze$optimize$linear$NNLS$State$$$outer() {
         return this.$outer;
      }

      public State(final DenseVector x, final DenseVector grad, final DenseVector dir, final DenseVector lastDir, final DenseVector res, final DenseVector tmp, final double lastNorm, final int lastWall, final int iter, final boolean converged) {
         this.x = x;
         this.grad = grad;
         this.dir = dir;
         this.lastDir = lastDir;
         this.res = res;
         this.tmp = tmp;
         this.lastNorm = lastNorm;
         this.lastWall = lastWall;
         this.iter = iter;
         this.converged = converged;
         if (NNLS.this == null) {
            throw null;
         } else {
            this.$outer = NNLS.this;
            super();
            Product.$init$(this);
         }
      }
   }

   public class State$ extends AbstractFunction10 implements Serializable {
      // $FF: synthetic field
      private final NNLS $outer;

      public final String toString() {
         return "State";
      }

      public State apply(final DenseVector x, final DenseVector grad, final DenseVector dir, final DenseVector lastDir, final DenseVector res, final DenseVector tmp, final double lastNorm, final int lastWall, final int iter, final boolean converged) {
         return this.$outer.new State(x, grad, dir, lastDir, res, tmp, lastNorm, lastWall, iter, converged);
      }

      public Option unapply(final State x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple10(x$0.x(), x$0.grad(), x$0.dir(), x$0.lastDir(), x$0.res(), x$0.tmp(), BoxesRunTime.boxToDouble(x$0.lastNorm()), BoxesRunTime.boxToInteger(x$0.lastWall()), BoxesRunTime.boxToInteger(x$0.iter()), BoxesRunTime.boxToBoolean(x$0.converged()))));
      }

      public State$() {
         if (NNLS.this == null) {
            throw null;
         } else {
            this.$outer = NNLS.this;
            super();
         }
      }
   }
}
