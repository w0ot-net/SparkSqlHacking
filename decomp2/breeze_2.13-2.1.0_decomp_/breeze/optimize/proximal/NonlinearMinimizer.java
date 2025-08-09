package breeze.optimize.proximal;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.max$;
import breeze.linalg.norm$;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.math.MutableInnerProductModule;
import breeze.optimize.DiffFunction;
import breeze.optimize.FirstOrderMinimizer;
import breeze.optimize.LBFGS;
import breeze.optimize.StochasticDiffFunction;
import breeze.util.Implicits$;
import breeze.util.Isomorphism;
import breeze.util.LazyLogger;
import breeze.util.SerializableLogging;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Enumeration;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple10;
import scala.Tuple2;
import scala.Tuple4;
import scala.collection.Iterator;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.AbstractFunction10;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011-gaBA\f\u00033\u0001\u0011q\u0005\u0005\u000b\u00037\u0001!\u0011!Q\u0001\n\u0005\u0005\u0003BCA%\u0001\t\u0005\t\u0015!\u0003\u0002L!Q\u0011\u0011\u000b\u0001\u0003\u0002\u0003\u0006I!a\u0013\t\u0015\u0005M\u0003A!A!\u0002\u0013\tY\u0005\u0003\u0006\u0002V\u0001\u0011\t\u0011)A\u0005\u0003/B!\"!\u0018\u0001\u0005\u0003\u0005\u000b\u0011BA,\u0011)\ty\u0006\u0001B\u0001B\u0003%\u0011q\u000b\u0005\u000b\u0003C\u0002!\u0011!Q\u0001\n\u0005]\u0003bBA2\u0001\u0011\u0005\u0011Q\r\u0005\n\u0003s\u0002!\u0019!C\u0001\u0003wB\u0001b!<\u0001A\u0003%\u0011Q\u0010\u0004\u0007\u0007_\u0004\u0001i!=\t\u0015\rMHB!f\u0001\n\u0003\u0019)\u0010\u0003\u0006\u0004\u00002\u0011\t\u0012)A\u0005\u0007oD!\"a=\r\u0005+\u0007I\u0011\u0001C\u0001\u0011)\t9\u0010\u0004B\tB\u0003%\u0011Q\u0011\u0005\u000b\u0003sd!Q3A\u0005\u0002\u0011\u0005\u0001BCA~\u0019\tE\t\u0015!\u0003\u0002\u0006\"QA1\u0001\u0007\u0003\u0016\u0004%\t\u0001\"\u0001\t\u0015\u0011\u0015AB!E!\u0002\u0013\t)\t\u0003\u0006\u0005\b1\u0011)\u001a!C\u0001\t\u0003A!\u0002\"\u0003\r\u0005#\u0005\u000b\u0011BAC\u0011)!Y\u0001\u0004BK\u0002\u0013\u0005A\u0011\u0001\u0005\u000b\t\u001ba!\u0011#Q\u0001\n\u0005\u0015\u0005B\u0003C\b\u0019\tU\r\u0011\"\u0001\u0005\u0002!QA\u0011\u0003\u0007\u0003\u0012\u0003\u0006I!!\"\t\u0015\u0011MAB!f\u0001\n\u0003\u0011\u0019\n\u0003\u0006\u0005\u00161\u0011\t\u0012)A\u0005\u0003\u0017B!\u0002b\u0006\r\u0005+\u0007I\u0011\u0001BJ\u0011)!I\u0002\u0004B\tB\u0003%\u00111\n\u0005\u000b\t7a!Q3A\u0005\u0002\u0011u\u0001B\u0003C\u0010\u0019\tE\t\u0015!\u0003\u00032\"A\u00111\r\u0007\u0005\u0002\u0001!\t\u0003C\u0005\u000301\t\t\u0011\"\u0001\u0005:!I!1\n\u0007\u0012\u0002\u0013\u0005Aq\n\u0005\n\u0005Ob\u0011\u0013!C\u0001\t'B\u0011B!\u001d\r#\u0003%\t\u0001b\u0015\t\u0013\t]D\"%A\u0005\u0002\u0011M\u0003\"\u0003C,\u0019E\u0005I\u0011\u0001C*\u0011%!I\u0006DI\u0001\n\u0003!\u0019\u0006C\u0005\u0005\\1\t\n\u0011\"\u0001\u0005T!IAQ\f\u0007\u0012\u0002\u0013\u000511\u0011\u0005\n\t?b\u0011\u0013!C\u0001\u0007\u0007C\u0011\u0002\"\u0019\r#\u0003%\ta!$\t\u0013\t\u0005E\"!A\u0005B\t\r\u0005\"\u0003BI\u0019\u0005\u0005I\u0011\u0001BJ\u0011%\u0011)\nDA\u0001\n\u0003!\u0019\u0007C\u0005\u0003\u001e2\t\t\u0011\"\u0011\u0003 \"I!Q\u0016\u0007\u0002\u0002\u0013\u0005Aq\r\u0005\n\u0005sc\u0011\u0011!C!\tWB\u0011Ba0\r\u0003\u0003%\tE!1\t\u0013\tEG\"!A\u0005B\tM\u0007\"\u0003Bb\u0019\u0005\u0005I\u0011\tC8\u000f%!\u0019\bAA\u0001\u0012\u0003!)HB\u0005\u0004p\u0002\t\t\u0011#\u0001\u0005x!9\u00111M\u001c\u0005\u0002\u0011}\u0004\"\u0003Bio\u0005\u0005IQ\tBj\u0011%\u0011)nNA\u0001\n\u0003#\t\tC\u0005\u0003r^\n\t\u0011\"!\u0005\u0018\"9A1\u0015\u0001\u0005\n\u0011\u0015\u0006b\u0002CX\u0001\u0011\u0005A\u0011\u0017\u0005\b\tw\u0003A\u0011\u0001C_\u0011\u001d!\u0019\r\u0001C\u0001\t\u000b<\u0001\"!#\u0002\u001a!\u0005\u00111\u0012\u0004\t\u0003/\tI\u0002#\u0001\u0002\u000e\"9\u00111M!\u0005\u0002\u0005}UABAQ\u0003\u0002\t\u0019K\u0002\u0004\u00020\u0006\u0003\u0015\u0011\u0017\u0005\u000b\u0003[$%Q3A\u0005\u0002\u0005=\bBCAy\t\nE\t\u0015!\u0003\u00026\"Q\u00111\u001f#\u0003\u0016\u0004%\t!!>\t\u0015\u0005]HI!E!\u0002\u0013\tY\f\u0003\u0006\u0002z\u0012\u0013)\u001a!C\u0001\u0003kD!\"a?E\u0005#\u0005\u000b\u0011BA^\u0011)\t)\u0006\u0012BK\u0002\u0013\u0005\u0011Q \u0005\u000b\u0003\u007f$%\u0011#Q\u0001\n\u0005]\u0003B\u0003B\u0001\t\n\u0005\t\u0015a\u0003\u0003\u0004!9\u00111\r#\u0005\u0002\t=\u0001b\u0002B\u0011\t\u0012\u0005#1\u0005\u0005\n\u0005_!\u0015\u0011!C\u0001\u0005cA\u0011Ba\u0013E#\u0003%\tA!\u0014\t\u0013\t\u001dD)%A\u0005\u0002\t%\u0004\"\u0003B9\tF\u0005I\u0011\u0001B:\u0011%\u00119\bRI\u0001\n\u0003\u0011I\bC\u0005\u0003\u0002\u0012\u000b\t\u0011\"\u0011\u0003\u0004\"I!\u0011\u0013#\u0002\u0002\u0013\u0005!1\u0013\u0005\n\u0005+#\u0015\u0011!C\u0001\u0005/C\u0011B!(E\u0003\u0003%\tEa(\t\u0013\t5F)!A\u0005\u0002\t=\u0006\"\u0003B]\t\u0006\u0005I\u0011\tB^\u0011%\u0011y\fRA\u0001\n\u0003\u0012\t\rC\u0005\u0003D\u0012\u000b\t\u0011\"\u0011\u0003F\u001eI!\u0011Z!\u0002\u0002#\u0005!1\u001a\u0004\n\u0003_\u000b\u0015\u0011!E\u0001\u0005\u001bDq!a\u0019_\t\u0003\u0011y\rC\u0005\u0003Rz\u000b\t\u0011\"\u0012\u0003T\"I!Q\u001b0\u0002\u0002\u0013\u0005%q\u001b\u0005\n\u0005ct\u0016\u0011!CA\u0005gD\u0011ba\u0004_\u0003\u0003%Ia!\u0005\u0007\r\re\u0011\tQB\u000e\u0011)\tY\u0002\u001aBK\u0002\u0013\u00051Q\u0004\u0005\u000b\u0007?!'\u0011#Q\u0001\n\u0005\u0005\u0003bBA2I\u0012\u00051\u0011\u0005\u0005\b\u0007O!G\u0011AB\u0015\u0011%\u0011y\u0003ZA\u0001\n\u0003\u0019y\u0003C\u0005\u0003L\u0011\f\n\u0011\"\u0001\u00044!I!\u0011\u00113\u0002\u0002\u0013\u0005#1\u0011\u0005\n\u0005##\u0017\u0011!C\u0001\u0005'C\u0011B!&e\u0003\u0003%\taa\u000e\t\u0013\tuE-!A\u0005B\t}\u0005\"\u0003BWI\u0006\u0005I\u0011AB\u001e\u0011%\u0011I\fZA\u0001\n\u0003\u001ay\u0004C\u0005\u0003@\u0012\f\t\u0011\"\u0011\u0003B\"I!\u0011\u001b3\u0002\u0002\u0013\u0005#1\u001b\u0005\n\u0005\u0007$\u0017\u0011!C!\u0007\u0007:\u0011ba\u0012B\u0003\u0003E\ta!\u0013\u0007\u0013\re\u0011)!A\t\u0002\r-\u0003bBA2k\u0012\u00051\u0011\f\u0005\n\u0005#,\u0018\u0011!C#\u0005'D\u0011B!6v\u0003\u0003%\tia\u0017\t\u0013\tEX/!A\u0005\u0002\u000e}\u0003\"CB\bk\u0006\u0005I\u0011BB\t\u0011\u001d\u00199#\u0011C\u0001\u0007KB\u0011b!!B#\u0003%\taa!\t\u0013\r\u001d\u0015)%A\u0005\u0002\r\r\u0005\"CBE\u0003F\u0005I\u0011\u0001B>\u0011%\u0019Y)QI\u0001\n\u0003\u0019i\tC\u0004\u0003V\u0006#\ta!%\t\u0013\rm\u0016)%A\u0005\u0002\r5\u0005bBB_\u0003\u0012\u00051q\u0018\u0005\n\u0007?\f\u0015\u0013!C\u0001\u0007\u0007C\u0011b!9B#\u0003%\taa!\t\u0013\r\r\u0018)%A\u0005\u0002\r\r\u0005\"CBs\u0003F\u0005I\u0011\u0001B>\u0011%\u00199/QI\u0001\n\u0003\u0011Y\bC\u0005\u0004j\u0006\u000b\n\u0011\"\u0001\u0003|!I11^!\u0012\u0002\u0013\u0005!1\u0010\u0005\n\u0007\u001f\t\u0015\u0011!C\u0005\u0007#\u0011!CT8oY&tW-\u0019:NS:LW.\u001b>fe*!\u00111DA\u000f\u0003!\u0001(o\u001c=j[\u0006d'\u0002BA\u0010\u0003C\t\u0001b\u001c9uS6L'0\u001a\u0006\u0003\u0003G\taA\u0019:fKj,7\u0001A\n\u0006\u0001\u0005%\u0012Q\u0007\t\u0005\u0003W\t\t$\u0004\u0002\u0002.)\u0011\u0011qF\u0001\u0006g\u000e\fG.Y\u0005\u0005\u0003g\tiC\u0001\u0004B]f\u0014VM\u001a\t\u0005\u0003o\ti$\u0004\u0002\u0002:)!\u00111HA\u0011\u0003\u0011)H/\u001b7\n\t\u0005}\u0012\u0011\b\u0002\u0014'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a'pO\u001eLgn\u001a\t\u0005\u0003\u0007\n)%\u0004\u0002\u0002\u001a%!\u0011qIA\r\u0005!\u0001&o\u001c=j[\u0006d\u0017\u0001C7bq&#XM]:\u0011\t\u0005-\u0012QJ\u0005\u0005\u0003\u001f\niCA\u0002J]R\f!\"\u001b8oKJLE/\u001a:t\u0003)\u0011gmZ:NK6|'/_\u0001\u0004e\"|\u0007\u0003BA\u0016\u00033JA!a\u0017\u0002.\t1Ai\\;cY\u0016\fQ!\u00197qQ\u0006\fa!\u00192ti>d\u0017A\u0002:fYR|G.\u0001\u0004=S:LGO\u0010\u000b\u0013\u0003O\nI'a\u001b\u0002n\u0005=\u0014\u0011OA:\u0003k\n9\bE\u0002\u0002D\u0001Aq!a\u0007\n\u0001\u0004\t\t\u0005C\u0005\u0002J%\u0001\n\u00111\u0001\u0002L!I\u0011\u0011K\u0005\u0011\u0002\u0003\u0007\u00111\n\u0005\n\u0003'J\u0001\u0013!a\u0001\u0003\u0017B\u0011\"!\u0016\n!\u0003\u0005\r!a\u0016\t\u0013\u0005u\u0013\u0002%AA\u0002\u0005]\u0003\"CA0\u0013A\u0005\t\u0019AA,\u0011%\t\t'\u0003I\u0001\u0002\u0004\t9&A\u0003mE\u001a<7/\u0006\u0002\u0002~A1\u0011qPAA\u0003\u000bk!!!\b\n\t\u0005\r\u0015Q\u0004\u0002\u0006\u0019\n3ui\u0015\t\u0004\u0003\u000f\u001bebAA\"\u0001\u0006\u0011bj\u001c8mS:,\u0017M]'j]&l\u0017N_3s!\r\t\u0019%Q\n\u0006\u0003\u0006%\u0012q\u0012\t\u0005\u0003#\u000bY*\u0004\u0002\u0002\u0014*!\u0011QSAL\u0003\tIwN\u0003\u0002\u0002\u001a\u0006!!.\u0019<b\u0013\u0011\ti*a%\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005-%a\u0001\"E-B1\u0011QUAV\u0003/j!!a*\u000b\t\u0005%\u0016\u0011E\u0001\u0007Y&t\u0017\r\\4\n\t\u00055\u0016q\u0015\u0002\f\t\u0016t7/\u001a,fGR|'O\u0001\bQe>D\u0018.\\1m!JLW.\u00197\u0016\t\u0005M\u0016qX\n\n\t\u0006%\u0012QWAi\u0003/\u0004b!a \u00028\u0006m\u0016\u0002BA]\u0003;\u0011A\u0002R5gM\u001a+hn\u0019;j_:\u0004B!!0\u0002@2\u0001AaBAa\t\n\u0007\u00111\u0019\u0002\u0002)F!\u0011QYAf!\u0011\tY#a2\n\t\u0005%\u0017Q\u0006\u0002\b\u001d>$\b.\u001b8h!\u0011\tY#!4\n\t\u0005=\u0017Q\u0006\u0002\u0004\u0003:L\b\u0003BA\u0016\u0003'LA!!6\u0002.\t9\u0001K]8ek\u000e$\b\u0003BAm\u0003StA!a7\u0002f:!\u0011Q\\Ar\u001b\t\tyN\u0003\u0003\u0002b\u0006\u0015\u0012A\u0002\u001fs_>$h(\u0003\u0002\u00020%!\u0011q]A\u0017\u0003\u001d\u0001\u0018mY6bO\u0016LA!!(\u0002l*!\u0011q]A\u0017\u0003\u0019\u0001(/[7bYV\u0011\u0011QW\u0001\baJLW.\u00197!\u0003\u0005)XCAA^\u0003\t)\b%A\u0001{\u0003\tQ\b%\u0006\u0002\u0002X\u0005!!\u000f[8!\u0003\u0015\u0019\b/Y2f!!\u0011)Aa\u0003\u0002<\u0006]SB\u0001B\u0004\u0015\u0011\u0011I!!\t\u0002\t5\fG\u000f[\u0005\u0005\u0005\u001b\u00119AA\rNkR\f'\r\\3J]:,'\u000f\u0015:pIV\u001cG/T8ek2,GC\u0003B\t\u00053\u0011YB!\b\u0003 Q!!1\u0003B\f!\u0015\u0011)\u0002RA^\u001b\u0005\t\u0005b\u0002B\u0001\u001d\u0002\u000f!1\u0001\u0005\b\u0003[t\u0005\u0019AA[\u0011\u001d\t\u0019P\u0014a\u0001\u0003wCq!!?O\u0001\u0004\tY\fC\u0004\u0002V9\u0003\r!a\u0016\u0002\u0013\r\fGnY;mCR,G\u0003\u0002B\u0013\u0005W\u0001\u0002\"a\u000b\u0003(\u0005]\u00131X\u0005\u0005\u0005S\tiC\u0001\u0004UkBdWM\r\u0005\b\u0005[y\u0005\u0019AA^\u0003\u0005A\u0018\u0001B2paf,BAa\r\u0003<QQ!Q\u0007B!\u0005\u000b\u00129E!\u0013\u0015\t\t]\"Q\b\t\u0006\u0005+!%\u0011\b\t\u0005\u0003{\u0013Y\u0004B\u0004\u0002BB\u0013\r!a1\t\u000f\t\u0005\u0001\u000bq\u0001\u0003@AA!Q\u0001B\u0006\u0005s\t9\u0006C\u0005\u0002nB\u0003\n\u00111\u0001\u0003DA1\u0011qPA\\\u0005sA\u0011\"a=Q!\u0003\u0005\rA!\u000f\t\u0013\u0005e\b\u000b%AA\u0002\te\u0002\"CA+!B\u0005\t\u0019AA,\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*BAa\u0014\u0003fU\u0011!\u0011\u000b\u0016\u0005\u0003k\u0013\u0019f\u000b\u0002\u0003VA!!q\u000bB1\u001b\t\u0011IF\u0003\u0003\u0003\\\tu\u0013!C;oG\",7m[3e\u0015\u0011\u0011y&!\f\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003d\te#!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u00129\u0011\u0011Y)C\u0002\u0005\r\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0005\u0005W\u0012y'\u0006\u0002\u0003n)\"\u00111\u0018B*\t\u001d\t\tM\u0015b\u0001\u0003\u0007\fabY8qs\u0012\"WMZ1vYR$3'\u0006\u0003\u0003l\tUDaBAa'\n\u0007\u00111Y\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135+\u0011\u0011YHa \u0016\u0005\tu$\u0006BA,\u0005'\"q!!1U\u0005\u0004\t\u0019-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0005\u000b\u0003BAa\"\u0003\u000e6\u0011!\u0011\u0012\u0006\u0005\u0005\u0017\u000b9*\u0001\u0003mC:<\u0017\u0002\u0002BH\u0005\u0013\u0013aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLXCAA&\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a3\u0003\u001a\"I!1T,\u0002\u0002\u0003\u0007\u00111J\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\t\u0005\u0006C\u0002BR\u0005S\u000bY-\u0004\u0002\u0003&*!!qUA\u0017\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0005W\u0013)K\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003\u0002BY\u0005o\u0003B!a\u000b\u00034&!!QWA\u0017\u0005\u001d\u0011un\u001c7fC:D\u0011Ba'Z\u0003\u0003\u0005\r!a3\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0005\u000b\u0013i\fC\u0005\u0003\u001cj\u000b\t\u00111\u0001\u0002L\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002L\u00051Q-];bYN$BA!-\u0003H\"I!1\u0014/\u0002\u0002\u0003\u0007\u00111Z\u0001\u000f!J|\u00070[7bYB\u0013\u0018.\\1m!\r\u0011)BX\n\u0006=\u0006%\u0012q\u0012\u000b\u0003\u0005\u0017\f\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0005\u000b\u000bQ!\u00199qYf,BA!7\u0003bRQ!1\u001cBt\u0005W\u0014iOa<\u0015\t\tu'1\u001d\t\u0006\u0005+!%q\u001c\t\u0005\u0003{\u0013\t\u000fB\u0004\u0002B\u0006\u0014\r!a1\t\u000f\t\u0005\u0011\rq\u0001\u0003fBA!Q\u0001B\u0006\u0005?\f9\u0006C\u0004\u0002n\u0006\u0004\rA!;\u0011\r\u0005}\u0014q\u0017Bp\u0011\u001d\t\u00190\u0019a\u0001\u0005?Dq!!?b\u0001\u0004\u0011y\u000eC\u0004\u0002V\u0005\u0004\r!a\u0016\u0002\u000fUt\u0017\r\u001d9msV!!Q_B\u0004)\u0011\u00119p!\u0003\u0011\r\u0005-\"\u0011 B\u007f\u0013\u0011\u0011Y0!\f\u0003\r=\u0003H/[8o!1\tYCa@\u0004\u0004\r\u00151QAA,\u0013\u0011\u0019\t!!\f\u0003\rQ+\b\u000f\\35!\u0019\ty(a.\u0004\u0006A!\u0011QXB\u0004\t\u001d\t\tM\u0019b\u0001\u0003\u0007D\u0011ba\u0003c\u0003\u0003\u0005\ra!\u0004\u0002\u0007a$\u0003\u0007E\u0003\u0003\u0016\u0011\u001b)!\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0004\u0014A!!qQB\u000b\u0013\u0011\u00199B!#\u0003\r=\u0013'.Z2u\u0005)\u0001&o\u001c6fGRLwN\\\n\bI\u0006%\u0012\u0011[Al+\t\t\t%A\u0005qe>D\u0018.\\1mAQ!11EB\u0013!\r\u0011)\u0002\u001a\u0005\b\u000379\u0007\u0019AA!\u0003\u001d\u0001(o\u001c6fGR$Baa\u000b\u0004.A\u0019!QC\"\t\u000f\t5\u0002\u000e1\u0001\u0004,Q!11EB\u0019\u0011%\tY\"\u001bI\u0001\u0002\u0004\t\t%\u0006\u0002\u00046)\"\u0011\u0011\tB*)\u0011\tYm!\u000f\t\u0013\tmU.!AA\u0002\u0005-C\u0003\u0002BY\u0007{A\u0011Ba'p\u0003\u0003\u0005\r!a3\u0015\t\t\u00155\u0011\t\u0005\n\u00057\u0003\u0018\u0011!a\u0001\u0003\u0017\"BA!-\u0004F!I!1T:\u0002\u0002\u0003\u0007\u00111Z\u0001\u000b!J|'.Z2uS>t\u0007c\u0001B\u000bkN)Qo!\u0014\u0002\u0010BA1qJB+\u0003\u0003\u001a\u0019#\u0004\u0002\u0004R)!11KA\u0017\u0003\u001d\u0011XO\u001c;j[\u0016LAaa\u0016\u0004R\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0015\u0005\r%C\u0003BB\u0012\u0007;Bq!a\u0007y\u0001\u0004\t\t\u0005\u0006\u0003\u0004b\r\r\u0004CBA\u0016\u0005s\f\t\u0005C\u0005\u0004\fe\f\t\u00111\u0001\u0004$Qa1qMB8\u0007c\u001a)h!\u001f\u0004~AA\u0011qPB5\u0007W\u0019i'\u0003\u0003\u0004l\u0005u!a\u0005$jeN$xJ\u001d3fe6Kg.[7ju\u0016\u0014\bCBA@\u0003o\u001bY\u0003C\u0004\u0002\u001cm\u0004\r!!\u0011\t\u0013\rM4\u0010%AA\u0002\u0005-\u0013aB7bq&#XM\u001d\u0005\n\u0007oZ\b\u0013!a\u0001\u0003\u0017\n\u0011!\u001c\u0005\n\u0007wZ\b\u0013!a\u0001\u0003/\n\u0011\u0002^8mKJ\fgnY3\t\u0013\r}4\u0010%AA\u0002\tE\u0016AB;tKB\u000bf*A\tqe>TWm\u0019;%I\u00164\u0017-\u001e7uII*\"a!\"+\t\u0005-#1K\u0001\u0012aJ|'.Z2uI\u0011,g-Y;mi\u0012\u001a\u0014!\u00059s_*,7\r\u001e\u0013eK\u001a\fW\u000f\u001c;%i\u0005\t\u0002O]8kK\u000e$H\u0005Z3gCVdG\u000fJ\u001b\u0016\u0005\r=%\u0006\u0002BY\u0005'\"\"ba\u001a\u0004\u0014\u000e]5QWB]\u0011!\u0019)*!\u0001A\u0002\u0005-\u0013\u0001\u00028eS6D\u0001b!'\u0002\u0002\u0001\u000711T\u0001\u000bG>t7\u000f\u001e:bS:$\b\u0003BBO\u0007_sAaa(\u0004,:!1\u0011UBU\u001d\u0011\u0019\u0019ka*\u000f\t\u0005u7QU\u0005\u0003\u0003GIA!a\b\u0002\"%!\u00111DA\u000f\u0013\u0011\u0019i+!\u0007\u0002\u0015\r{gn\u001d;sC&tG/\u0003\u0003\u00042\u000eM&AC\"p]N$(/Y5oi*!1QVA\r\u0011!\u00199,!\u0001A\u0002\u0005]\u0013A\u00027b[\n$\u0017\r\u0003\u0006\u0004\u0000\u0005\u0005\u0001\u0013!a\u0001\u0005c\u000bq\"\u00199qYf$C-\u001a4bk2$H\u0005N\u0001\u0005[\u0006Lg\u000e\u0006\u0003\u0004B\u000e\u001d\u0007\u0003BA\u0016\u0007\u0007LAa!2\u0002.\t!QK\\5u\u0011!\u0019I-!\u0002A\u0002\r-\u0017\u0001B1sON\u0004b!a\u000b\u0004N\u000eE\u0017\u0002BBh\u0003[\u0011Q!\u0011:sCf\u0004Baa5\u0004\\:!1Q[Bl!\u0011\ti.!\f\n\t\re\u0017QF\u0001\u0007!J,G-\u001a4\n\t\t=5Q\u001c\u0006\u0005\u00073\fi#A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001a\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00135\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%k\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIY\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012:\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0003(\u0001\u0004mE\u001a<7\u000f\t\u0002\u0006'R\fG/Z\n\b\u0019\u0005%\u0012\u0011[Al\u0003%\u0011gmZ:Ti\u0006$X-\u0006\u0002\u0004xB!1\u0011`B\u007f\u001d\r\u0019YPC\u0007\u0002\u0001%!1q^B5\u0003)\u0011gmZ:Ti\u0006$X\rI\u000b\u0003\u0003\u000b\u000bA\u0001\u001f%bi\u0006)\u0001\u0010S1uA\u0005!!p\u00147e\u0003\u0015Qx\n\u001c3!\u0003!\u0011Xm]5ek\u0006d\u0017!\u0003:fg&$W/\u00197!\u0003\u0005\u0019\u0018AA:!\u0003%\tG-\\7Ji\u0016\u00148/\u0001\u0006bI6l\u0017\n^3sg\u0002\nA!\u001b;fe\u0006)\u0011\u000e^3sA\u0005I1m\u001c8wKJ<W\rZ\u000b\u0003\u0005c\u000b!bY8om\u0016\u0014x-\u001a3!)Y!\u0019\u0003\"\n\u0005(\u0011%B1\u0006C\u0017\t_!\t\u0004b\r\u00056\u0011]\u0002cAB~\u0019!911_\u0011A\u0002\r]\bbBAzC\u0001\u0007\u0011Q\u0011\u0005\b\u0003s\f\u0003\u0019AAC\u0011\u001d!\u0019!\ta\u0001\u0003\u000bCq\u0001b\u0002\"\u0001\u0004\t)\tC\u0004\u0005\f\u0005\u0002\r!!\"\t\u000f\u0011=\u0011\u00051\u0001\u0002\u0006\"9A1C\u0011A\u0002\u0005-\u0003b\u0002C\fC\u0001\u0007\u00111\n\u0005\b\t7\t\u0003\u0019\u0001BY)Y!\u0019\u0003b\u000f\u0005>\u0011}B\u0011\tC\"\t\u000b\"9\u0005\"\u0013\u0005L\u00115\u0003\"CBzEA\u0005\t\u0019AB|\u0011%\t\u0019P\tI\u0001\u0002\u0004\t)\tC\u0005\u0002z\n\u0002\n\u00111\u0001\u0002\u0006\"IA1\u0001\u0012\u0011\u0002\u0003\u0007\u0011Q\u0011\u0005\n\t\u000f\u0011\u0003\u0013!a\u0001\u0003\u000bC\u0011\u0002b\u0003#!\u0003\u0005\r!!\"\t\u0013\u0011=!\u0005%AA\u0002\u0005\u0015\u0005\"\u0003C\nEA\u0005\t\u0019AA&\u0011%!9B\tI\u0001\u0002\u0004\tY\u0005C\u0005\u0005\u001c\t\u0002\n\u00111\u0001\u00032V\u0011A\u0011\u000b\u0016\u0005\u0007o\u0014\u0019&\u0006\u0002\u0005V)\"\u0011Q\u0011B*\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIU\nabY8qs\u0012\"WMZ1vYR$c'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001c\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%q\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012J\u0014aD2paf$C-\u001a4bk2$H%\r\u0019\u0015\t\u0005-GQ\r\u0005\n\u00057{\u0013\u0011!a\u0001\u0003\u0017\"BA!-\u0005j!I!1T\u0019\u0002\u0002\u0003\u0007\u00111\u001a\u000b\u0005\u0005\u000b#i\u0007C\u0005\u0003\u001cJ\n\t\u00111\u0001\u0002LQ!!\u0011\u0017C9\u0011%\u0011Y*NA\u0001\u0002\u0004\tY-A\u0003Ti\u0006$X\rE\u0002\u0004|^\u001aRa\u000eC=\u0003\u001f\u0003\"da\u0014\u0005|\r]\u0018QQAC\u0003\u000b\u000b))!\"\u0002\u0006\u0006-\u00131\nBY\tGIA\u0001\" \u0004R\t\u0011\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u00191)\t!)\b\u0006\f\u0005$\u0011\rEQ\u0011CD\t\u0013#Y\t\"$\u0005\u0010\u0012EE1\u0013CK\u0011\u001d\u0019\u0019P\u000fa\u0001\u0007oDq!a=;\u0001\u0004\t)\tC\u0004\u0002zj\u0002\r!!\"\t\u000f\u0011\r!\b1\u0001\u0002\u0006\"9Aq\u0001\u001eA\u0002\u0005\u0015\u0005b\u0002C\u0006u\u0001\u0007\u0011Q\u0011\u0005\b\t\u001fQ\u0004\u0019AAC\u0011\u001d!\u0019B\u000fa\u0001\u0003\u0017Bq\u0001b\u0006;\u0001\u0004\tY\u0005C\u0004\u0005\u001ci\u0002\rA!-\u0015\t\u0011eE\u0011\u0015\t\u0007\u0003W\u0011I\u0010b'\u00111\u0005-BQTB|\u0003\u000b\u000b))!\"\u0002\u0006\u0006\u0015\u0015QQA&\u0003\u0017\u0012\t,\u0003\u0003\u0005 \u00065\"a\u0002+va2,\u0017\u0007\r\u0005\n\u0007\u0017Y\u0014\u0011!a\u0001\tG\tA\"\u001b8ji&\fGn\u0015;bi\u0016$b\u0001b\t\u0005(\u0012-\u0006bBAwy\u0001\u0007A\u0011\u0016\t\u0007\u0003\u007f\n9,!\"\t\u000f\u00115F\b1\u0001\u0002\u0006\u0006!\u0011N\\5u\u0003)IG/\u001a:bi&|gn\u001d\u000b\u0007\tg#9\f\"/\u0011\r\u0005eGQ\u0017C\u0012\u0013\u0011\u0011Y+a;\t\u000f\u00055X\b1\u0001\u0005*\"9AQV\u001fA\u0002\u0005\u0015\u0015\u0001C7j]&l\u0017N_3\u0015\r\u0005\u0015Eq\u0018Ca\u0011\u001d\tiO\u0010a\u0001\tSCq\u0001\",?\u0001\u0004\t))\u0001\fnS:LW.\u001b>f\u0003:$'+\u001a;ve:\u001cF/\u0019;f)\u0019!\u0019\u0003b2\u0005J\"9\u0011Q^ A\u0002\u0011%\u0006b\u0002CW\u007f\u0001\u0007\u0011Q\u0011"
)
public class NonlinearMinimizer implements SerializableLogging {
   private volatile State$ State$module;
   private final Proximal proximal;
   private final int maxIters;
   private final double rho;
   private final double alpha;
   private final double abstol;
   private final double reltol;
   private final LBFGS lbfgs;
   private transient volatile LazyLogger breeze$util$SerializableLogging$$_the_logger;

   public static double $lessinit$greater$default$8() {
      return NonlinearMinimizer$.MODULE$.$lessinit$greater$default$8();
   }

   public static double $lessinit$greater$default$7() {
      return NonlinearMinimizer$.MODULE$.$lessinit$greater$default$7();
   }

   public static double $lessinit$greater$default$6() {
      return NonlinearMinimizer$.MODULE$.$lessinit$greater$default$6();
   }

   public static double $lessinit$greater$default$5() {
      return NonlinearMinimizer$.MODULE$.$lessinit$greater$default$5();
   }

   public static int $lessinit$greater$default$4() {
      return NonlinearMinimizer$.MODULE$.$lessinit$greater$default$4();
   }

   public static int $lessinit$greater$default$3() {
      return NonlinearMinimizer$.MODULE$.$lessinit$greater$default$3();
   }

   public static int $lessinit$greater$default$2() {
      return NonlinearMinimizer$.MODULE$.$lessinit$greater$default$2();
   }

   public static void main(final String[] args) {
      NonlinearMinimizer$.MODULE$.main(args);
   }

   public static boolean apply$default$4() {
      return NonlinearMinimizer$.MODULE$.apply$default$4();
   }

   public static FirstOrderMinimizer apply(final int ndim, final Enumeration.Value constraint, final double lambda, final boolean usePQN) {
      return NonlinearMinimizer$.MODULE$.apply(ndim, constraint, lambda, usePQN);
   }

   public static boolean project$default$5() {
      return NonlinearMinimizer$.MODULE$.project$default$5();
   }

   public static double project$default$4() {
      return NonlinearMinimizer$.MODULE$.project$default$4();
   }

   public static int project$default$3() {
      return NonlinearMinimizer$.MODULE$.project$default$3();
   }

   public static int project$default$2() {
      return NonlinearMinimizer$.MODULE$.project$default$2();
   }

   public static FirstOrderMinimizer project(final Proximal proximal, final int maxIter, final int m, final double tolerance, final boolean usePQN) {
      return NonlinearMinimizer$.MODULE$.project(proximal, maxIter, m, tolerance, usePQN);
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

   public LBFGS lbfgs() {
      return this.lbfgs;
   }

   private State initialState(final DiffFunction primal, final DenseVector init) {
      DenseVector z = init.copy$mcD$sp();
      DenseVector u = init.copy$mcD$sp();
      DenseVector xHat = init.copy$mcD$sp();
      DenseVector zOld = init.copy$mcD$sp();
      DenseVector residual = init.copy$mcD$sp();
      DenseVector s = init.copy$mcD$sp();
      FirstOrderMinimizer.State resultState = this.lbfgs().minimizeAndReturnState(primal, xHat);
      int admmIters = this.maxIters < 0 ? max$.MODULE$.apply$mIIIc$sp(400, 20 * z.length(), max$.MODULE$.maxImpl2_Int()) : this.maxIters;
      return this.State().apply(resultState, u, z, xHat, zOld, residual, s, admmIters, 0, false);
   }

   public Iterator iterations(final DiffFunction primal, final DenseVector init) {
      return Implicits$.MODULE$.scEnrichIterator(.MODULE$.Iterator().iterate(this.initialState(primal, init), (state) -> {
         double scale = scala.math.package..MODULE$.sqrt((double)init.size()) * this.abstol;
         ProximalPrimal proxPrimal = new ProximalPrimal(primal, state.u(), state.z(), this.rho, DenseVector$.MODULE$.space_Double());
         FirstOrderMinimizer.State resultState = this.lbfgs().minimizeAndReturnState(proxPrimal, state.bfgsState().x());
         state.zOld().$colon$eq(state.z(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
         state.zOld().$times$eq(BoxesRunTime.boxToDouble((double)1 - this.alpha), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpMulScalar());
         state.xHat().$colon$eq(resultState.x(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
         state.xHat().$times$eq(BoxesRunTime.boxToDouble(this.alpha), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpMulScalar());
         state.xHat().$plus$eq(state.zOld(), HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
         state.zOld().$colon$eq(state.z(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
         state.z().$colon$eq(state.xHat(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
         state.z().$plus$eq(state.u(), HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
         this.proximal.prox(state.z(), this.rho);
         state.xHat().$minus$eq(state.z(), HasOps$.MODULE$.impl_OpSub_InPlace_DV_DV_Double());
         state.u().$plus$eq(state.xHat(), HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
         state.residual().$colon$eq(resultState.x(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
         state.residual().$minus$eq(state.z(), HasOps$.MODULE$.impl_OpSub_InPlace_DV_DV_Double());
         double residualNorm = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(state.residual(), norm$.MODULE$.normDoubleToNormalNorm(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double()))));
         state.s().$colon$eq(state.z(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
         state.s().$minus$eq(state.zOld(), HasOps$.MODULE$.impl_OpSub_InPlace_DV_DV_Double());
         state.s().$times$eq(BoxesRunTime.boxToDouble(-this.rho), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpMulScalar());
         double sNorm = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(state.s(), norm$.MODULE$.normDoubleToNormalNorm(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double()))));
         state.residual().$colon$eq(state.z(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
         state.residual().$times$eq(BoxesRunTime.boxToDouble((double)-1.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpMulScalar());
         state.s().$colon$eq(state.u(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
         state.s().$times$eq(BoxesRunTime.boxToDouble(this.rho), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpMulScalar());
         double epsPrimal = scale + this.reltol * max$.MODULE$.apply$mDDDc$sp(BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(resultState.x(), norm$.MODULE$.normDoubleToNormalNorm(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double())))), BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(state.residual(), norm$.MODULE$.normDoubleToNormalNorm(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double())))), max$.MODULE$.maxImpl2_Double());
         double epsDual = scale + this.reltol * BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(state.s(), norm$.MODULE$.normDoubleToNormalNorm(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double()))));
         return (!(residualNorm < epsPrimal) || !(sNorm < epsDual)) && state.iter() <= state.admmIters() ? this.State().apply(resultState, state.u(), state.z(), state.xHat(), state.zOld(), state.residual(), state.s(), state.admmIters(), state.iter() + 1, false) : this.State().apply(resultState, state.u(), state.z(), state.xHat(), state.zOld(), state.residual(), state.s(), state.admmIters(), state.iter() + 1, true);
      })).takeUpToWhere((x$1) -> BoxesRunTime.boxToBoolean($anonfun$iterations$2(x$1)));
   }

   public DenseVector minimize(final DiffFunction primal, final DenseVector init) {
      return this.minimizeAndReturnState(primal, init).z();
   }

   public State minimizeAndReturnState(final DiffFunction primal, final DenseVector init) {
      return (State)Implicits$.MODULE$.scEnrichIterator(this.iterations(primal, init)).last();
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

   // $FF: synthetic method
   public static final boolean $anonfun$iterations$2(final State x$1) {
      return x$1.converged();
   }

   public NonlinearMinimizer(final Proximal proximal, final int maxIters, final int innerIters, final int bfgsMemory, final double rho, final double alpha, final double abstol, final double reltol) {
      this.proximal = proximal;
      this.maxIters = maxIters;
      this.rho = rho;
      this.alpha = alpha;
      this.abstol = abstol;
      this.reltol = reltol;
      SerializableLogging.$init$(this);
      this.lbfgs = new LBFGS(innerIters, bfgsMemory, abstol, DenseVector$.MODULE$.space_Double());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class State implements Product, Serializable {
      private final FirstOrderMinimizer.State bfgsState;
      private final DenseVector u;
      private final DenseVector z;
      private final DenseVector xHat;
      private final DenseVector zOld;
      private final DenseVector residual;
      private final DenseVector s;
      private final int admmIters;
      private final int iter;
      private final boolean converged;
      // $FF: synthetic field
      public final NonlinearMinimizer $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public FirstOrderMinimizer.State bfgsState() {
         return this.bfgsState;
      }

      public DenseVector u() {
         return this.u;
      }

      public DenseVector z() {
         return this.z;
      }

      public DenseVector xHat() {
         return this.xHat;
      }

      public DenseVector zOld() {
         return this.zOld;
      }

      public DenseVector residual() {
         return this.residual;
      }

      public DenseVector s() {
         return this.s;
      }

      public int admmIters() {
         return this.admmIters;
      }

      public int iter() {
         return this.iter;
      }

      public boolean converged() {
         return this.converged;
      }

      public State copy(final FirstOrderMinimizer.State bfgsState, final DenseVector u, final DenseVector z, final DenseVector xHat, final DenseVector zOld, final DenseVector residual, final DenseVector s, final int admmIters, final int iter, final boolean converged) {
         return this.breeze$optimize$proximal$NonlinearMinimizer$State$$$outer().new State(bfgsState, u, z, xHat, zOld, residual, s, admmIters, iter, converged);
      }

      public FirstOrderMinimizer.State copy$default$1() {
         return this.bfgsState();
      }

      public boolean copy$default$10() {
         return this.converged();
      }

      public DenseVector copy$default$2() {
         return this.u();
      }

      public DenseVector copy$default$3() {
         return this.z();
      }

      public DenseVector copy$default$4() {
         return this.xHat();
      }

      public DenseVector copy$default$5() {
         return this.zOld();
      }

      public DenseVector copy$default$6() {
         return this.residual();
      }

      public DenseVector copy$default$7() {
         return this.s();
      }

      public int copy$default$8() {
         return this.admmIters();
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
               var10000 = this.bfgsState();
               break;
            case 1:
               var10000 = this.u();
               break;
            case 2:
               var10000 = this.z();
               break;
            case 3:
               var10000 = this.xHat();
               break;
            case 4:
               var10000 = this.zOld();
               break;
            case 5:
               var10000 = this.residual();
               break;
            case 6:
               var10000 = this.s();
               break;
            case 7:
               var10000 = BoxesRunTime.boxToInteger(this.admmIters());
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
               var10000 = "bfgsState";
               break;
            case 1:
               var10000 = "u";
               break;
            case 2:
               var10000 = "z";
               break;
            case 3:
               var10000 = "xHat";
               break;
            case 4:
               var10000 = "zOld";
               break;
            case 5:
               var10000 = "residual";
               break;
            case 6:
               var10000 = "s";
               break;
            case 7:
               var10000 = "admmIters";
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
         var1 = Statics.mix(var1, Statics.anyHash(this.bfgsState()));
         var1 = Statics.mix(var1, Statics.anyHash(this.u()));
         var1 = Statics.mix(var1, Statics.anyHash(this.z()));
         var1 = Statics.mix(var1, Statics.anyHash(this.xHat()));
         var1 = Statics.mix(var1, Statics.anyHash(this.zOld()));
         var1 = Statics.mix(var1, Statics.anyHash(this.residual()));
         var1 = Statics.mix(var1, Statics.anyHash(this.s()));
         var1 = Statics.mix(var1, this.admmIters());
         var1 = Statics.mix(var1, this.iter());
         var1 = Statics.mix(var1, this.converged() ? 1231 : 1237);
         return Statics.finalizeHash(var1, 10);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var19;
         if (this != x$1) {
            label119: {
               boolean var2;
               if (x$1 instanceof State && ((State)x$1).breeze$optimize$proximal$NonlinearMinimizer$State$$$outer() == this.breeze$optimize$proximal$NonlinearMinimizer$State$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label95: {
                     State var4 = (State)x$1;
                     if (this.admmIters() == var4.admmIters() && this.iter() == var4.iter() && this.converged() == var4.converged()) {
                        label109: {
                           FirstOrderMinimizer.State var10000 = this.bfgsState();
                           FirstOrderMinimizer.State var5 = var4.bfgsState();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label109;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label109;
                           }

                           DenseVector var12 = this.u();
                           DenseVector var6 = var4.u();
                           if (var12 == null) {
                              if (var6 != null) {
                                 break label109;
                              }
                           } else if (!var12.equals(var6)) {
                              break label109;
                           }

                           var12 = this.z();
                           DenseVector var7 = var4.z();
                           if (var12 == null) {
                              if (var7 != null) {
                                 break label109;
                              }
                           } else if (!var12.equals(var7)) {
                              break label109;
                           }

                           var12 = this.xHat();
                           DenseVector var8 = var4.xHat();
                           if (var12 == null) {
                              if (var8 != null) {
                                 break label109;
                              }
                           } else if (!var12.equals(var8)) {
                              break label109;
                           }

                           var12 = this.zOld();
                           DenseVector var9 = var4.zOld();
                           if (var12 == null) {
                              if (var9 != null) {
                                 break label109;
                              }
                           } else if (!var12.equals(var9)) {
                              break label109;
                           }

                           var12 = this.residual();
                           DenseVector var10 = var4.residual();
                           if (var12 == null) {
                              if (var10 != null) {
                                 break label109;
                              }
                           } else if (!var12.equals(var10)) {
                              break label109;
                           }

                           var12 = this.s();
                           DenseVector var11 = var4.s();
                           if (var12 == null) {
                              if (var11 != null) {
                                 break label109;
                              }
                           } else if (!var12.equals(var11)) {
                              break label109;
                           }

                           if (var4.canEqual(this)) {
                              var19 = true;
                              break label95;
                           }
                        }
                     }

                     var19 = false;
                  }

                  if (var19) {
                     break label119;
                  }
               }

               var19 = false;
               return var19;
            }
         }

         var19 = true;
         return var19;
      }

      // $FF: synthetic method
      public NonlinearMinimizer breeze$optimize$proximal$NonlinearMinimizer$State$$$outer() {
         return this.$outer;
      }

      public State(final FirstOrderMinimizer.State bfgsState, final DenseVector u, final DenseVector z, final DenseVector xHat, final DenseVector zOld, final DenseVector residual, final DenseVector s, final int admmIters, final int iter, final boolean converged) {
         this.bfgsState = bfgsState;
         this.u = u;
         this.z = z;
         this.xHat = xHat;
         this.zOld = zOld;
         this.residual = residual;
         this.s = s;
         this.admmIters = admmIters;
         this.iter = iter;
         this.converged = converged;
         if (NonlinearMinimizer.this == null) {
            throw null;
         } else {
            this.$outer = NonlinearMinimizer.this;
            super();
            Product.$init$(this);
         }
      }
   }

   public class State$ extends AbstractFunction10 implements Serializable {
      // $FF: synthetic field
      private final NonlinearMinimizer $outer;

      public final String toString() {
         return "State";
      }

      public State apply(final FirstOrderMinimizer.State bfgsState, final DenseVector u, final DenseVector z, final DenseVector xHat, final DenseVector zOld, final DenseVector residual, final DenseVector s, final int admmIters, final int iter, final boolean converged) {
         return this.$outer.new State(bfgsState, u, z, xHat, zOld, residual, s, admmIters, iter, converged);
      }

      public Option unapply(final State x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple10(x$0.bfgsState(), x$0.u(), x$0.z(), x$0.xHat(), x$0.zOld(), x$0.residual(), x$0.s(), BoxesRunTime.boxToInteger(x$0.admmIters()), BoxesRunTime.boxToInteger(x$0.iter()), BoxesRunTime.boxToBoolean(x$0.converged()))));
      }

      public State$() {
         if (NonlinearMinimizer.this == null) {
            throw null;
         } else {
            this.$outer = NonlinearMinimizer.this;
            super();
         }
      }
   }

   public static class ProximalPrimal implements DiffFunction, Product, Serializable {
      private final DiffFunction primal;
      private final Object u;
      private final Object z;
      private final double rho;
      private final MutableInnerProductModule space;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public DiffFunction repr() {
         return DiffFunction.repr$(this);
      }

      public DiffFunction cached(final CanCopy copy) {
         return DiffFunction.cached$(this, copy);
      }

      public DiffFunction throughLens(final Isomorphism l) {
         return DiffFunction.throughLens$(this, l);
      }

      public Object gradientAt(final Object x) {
         return StochasticDiffFunction.gradientAt$(this, x);
      }

      public double valueAt(final Object x) {
         return StochasticDiffFunction.valueAt$(this, x);
      }

      public final double apply(final Object x) {
         return StochasticDiffFunction.apply$(this, x);
      }

      public final Object $plus(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$plus$(this, b, op);
      }

      public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$eq$(this, b, op);
      }

      public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$plus$eq$(this, b, op);
      }

      public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$times$eq$(this, b, op);
      }

      public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$plus$eq$(this, b, op);
      }

      public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$times$eq$(this, b, op);
      }

      public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$minus$eq$(this, b, op);
      }

      public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$percent$eq$(this, b, op);
      }

      public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$percent$eq$(this, b, op);
      }

      public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$minus$eq$(this, b, op);
      }

      public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$div$eq$(this, b, op);
      }

      public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$up$eq$(this, b, op);
      }

      public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$div$eq$(this, b, op);
      }

      public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$less$colon$less$(this, b, op);
      }

      public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$less$colon$eq$(this, b, op);
      }

      public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$greater$colon$greater$(this, b, op);
      }

      public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$greater$colon$eq$(this, b, op);
      }

      public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$amp$eq$(this, b, op);
      }

      public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$bar$eq$(this, b, op);
      }

      public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$up$up$eq$(this, b, op);
      }

      public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$amp$eq$(this, b, op);
      }

      public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$bar$eq$(this, b, op);
      }

      public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$up$up$eq$(this, b, op);
      }

      public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
      }

      public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$times$colon$times$(this, b, op);
      }

      public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
      }

      public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
      }

      public final Object unary_$minus(final UFunc.UImpl op) {
         return ImmutableNumericOps.unary_$minus$(this, op);
      }

      public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
      }

      public final Object $minus(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$minus$(this, b, op);
      }

      public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
      }

      public final Object $percent(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$percent$(this, b, op);
      }

      public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$div$colon$div$(this, b, op);
      }

      public final Object $div(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$div$(this, b, op);
      }

      public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$up$colon$up$(this, b, op);
      }

      public final Object dot(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.dot$(this, b, op);
      }

      public final Object unary_$bang(final UFunc.UImpl op) {
         return ImmutableNumericOps.unary_$bang$(this, op);
      }

      public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
      }

      public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
      }

      public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
      }

      public final Object $amp(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$amp$(this, b, op);
      }

      public final Object $bar(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$bar$(this, b, op);
      }

      public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$up$up$(this, b, op);
      }

      public final Object $times(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$times$(this, b, op);
      }

      public final Object t(final CanTranspose op) {
         return ImmutableNumericOps.t$(this, op);
      }

      public Object $bslash(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$bslash$(this, b, op);
      }

      public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
         return ImmutableNumericOps.t$(this, a, b, op, canSlice);
      }

      public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
         return ImmutableNumericOps.t$(this, a, op, canSlice);
      }

      public boolean apply$mcZD$sp(final double v1) {
         return Function1.apply$mcZD$sp$(this, v1);
      }

      public double apply$mcDD$sp(final double v1) {
         return Function1.apply$mcDD$sp$(this, v1);
      }

      public float apply$mcFD$sp(final double v1) {
         return Function1.apply$mcFD$sp$(this, v1);
      }

      public int apply$mcID$sp(final double v1) {
         return Function1.apply$mcID$sp$(this, v1);
      }

      public long apply$mcJD$sp(final double v1) {
         return Function1.apply$mcJD$sp$(this, v1);
      }

      public void apply$mcVD$sp(final double v1) {
         Function1.apply$mcVD$sp$(this, v1);
      }

      public boolean apply$mcZF$sp(final float v1) {
         return Function1.apply$mcZF$sp$(this, v1);
      }

      public double apply$mcDF$sp(final float v1) {
         return Function1.apply$mcDF$sp$(this, v1);
      }

      public float apply$mcFF$sp(final float v1) {
         return Function1.apply$mcFF$sp$(this, v1);
      }

      public int apply$mcIF$sp(final float v1) {
         return Function1.apply$mcIF$sp$(this, v1);
      }

      public long apply$mcJF$sp(final float v1) {
         return Function1.apply$mcJF$sp$(this, v1);
      }

      public void apply$mcVF$sp(final float v1) {
         Function1.apply$mcVF$sp$(this, v1);
      }

      public boolean apply$mcZI$sp(final int v1) {
         return Function1.apply$mcZI$sp$(this, v1);
      }

      public double apply$mcDI$sp(final int v1) {
         return Function1.apply$mcDI$sp$(this, v1);
      }

      public float apply$mcFI$sp(final int v1) {
         return Function1.apply$mcFI$sp$(this, v1);
      }

      public int apply$mcII$sp(final int v1) {
         return Function1.apply$mcII$sp$(this, v1);
      }

      public long apply$mcJI$sp(final int v1) {
         return Function1.apply$mcJI$sp$(this, v1);
      }

      public void apply$mcVI$sp(final int v1) {
         Function1.apply$mcVI$sp$(this, v1);
      }

      public boolean apply$mcZJ$sp(final long v1) {
         return Function1.apply$mcZJ$sp$(this, v1);
      }

      public double apply$mcDJ$sp(final long v1) {
         return Function1.apply$mcDJ$sp$(this, v1);
      }

      public float apply$mcFJ$sp(final long v1) {
         return Function1.apply$mcFJ$sp$(this, v1);
      }

      public int apply$mcIJ$sp(final long v1) {
         return Function1.apply$mcIJ$sp$(this, v1);
      }

      public long apply$mcJJ$sp(final long v1) {
         return Function1.apply$mcJJ$sp$(this, v1);
      }

      public void apply$mcVJ$sp(final long v1) {
         Function1.apply$mcVJ$sp$(this, v1);
      }

      public Function1 compose(final Function1 g) {
         return Function1.compose$(this, g);
      }

      public Function1 andThen(final Function1 g) {
         return Function1.andThen$(this, g);
      }

      public String toString() {
         return Function1.toString$(this);
      }

      public DiffFunction primal() {
         return this.primal;
      }

      public Object u() {
         return this.u;
      }

      public Object z() {
         return this.z;
      }

      public double rho() {
         return this.rho;
      }

      public Tuple2 calculate(final Object x) {
         Tuple2 var4 = this.primal().calculate(x);
         if (var4 != null) {
            double f = var4._1$mcD$sp();
            Object g = var4._2();
            Tuple2 var2 = new Tuple2(BoxesRunTime.boxToDouble(f), g);
            double f = var2._1$mcD$sp();
            Object g = var2._2();
            Object scale = ((NumericOps)this.space.hasOps().apply(((ImmutableNumericOps)this.space.hasOps().apply(x)).$minus(this.z(), this.space.subVV()))).$plus(this.u(), this.space.addVV());
            double proxObj = f + (double)0.5F * this.rho() * scala.math.package..MODULE$.pow(BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(scale, this.space.normImpl())), (double)2.0F);
            Object proxGrad = ((NumericOps)this.space.hasOps().apply(g)).$plus(((ImmutableNumericOps)this.space.hasOps().apply(scale)).$times$colon$times(BoxesRunTime.boxToDouble(this.rho()), this.space.mulVS()), this.space.addVV());
            return new Tuple2(BoxesRunTime.boxToDouble(proxObj), proxGrad);
         } else {
            throw new MatchError(var4);
         }
      }

      public ProximalPrimal copy(final DiffFunction primal, final Object u, final Object z, final double rho, final MutableInnerProductModule space) {
         return new ProximalPrimal(primal, u, z, rho, space);
      }

      public DiffFunction copy$default$1() {
         return this.primal();
      }

      public Object copy$default$2() {
         return this.u();
      }

      public Object copy$default$3() {
         return this.z();
      }

      public double copy$default$4() {
         return this.rho();
      }

      public String productPrefix() {
         return "ProximalPrimal";
      }

      public int productArity() {
         return 4;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.primal();
               break;
            case 1:
               var10000 = this.u();
               break;
            case 2:
               var10000 = this.z();
               break;
            case 3:
               var10000 = BoxesRunTime.boxToDouble(this.rho());
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
         return x$1 instanceof ProximalPrimal;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "primal";
               break;
            case 1:
               var10000 = "u";
               break;
            case 2:
               var10000 = "z";
               break;
            case 3:
               var10000 = "rho";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.anyHash(this.primal()));
         var1 = Statics.mix(var1, Statics.anyHash(this.u()));
         var1 = Statics.mix(var1, Statics.anyHash(this.z()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.rho()));
         return Statics.finalizeHash(var1, 4);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label59: {
               boolean var2;
               if (x$1 instanceof ProximalPrimal) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label42: {
                     ProximalPrimal var4 = (ProximalPrimal)x$1;
                     if (this.rho() == var4.rho()) {
                        label40: {
                           DiffFunction var10000 = this.primal();
                           DiffFunction var5 = var4.primal();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label40;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label40;
                           }

                           if (BoxesRunTime.equals(this.u(), var4.u()) && BoxesRunTime.equals(this.z(), var4.z()) && var4.canEqual(this)) {
                              var7 = true;
                              break label42;
                           }
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label59;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      public ProximalPrimal(final DiffFunction primal, final Object u, final Object z, final double rho, final MutableInnerProductModule space) {
         this.primal = primal;
         this.u = u;
         this.z = z;
         this.rho = rho;
         this.space = space;
         Function1.$init$(this);
         ImmutableNumericOps.$init$(this);
         NumericOps.$init$(this);
         StochasticDiffFunction.$init$(this);
         DiffFunction.$init$(this);
         Product.$init$(this);
      }
   }

   public static class ProximalPrimal$ implements Serializable {
      public static final ProximalPrimal$ MODULE$ = new ProximalPrimal$();

      public final String toString() {
         return "ProximalPrimal";
      }

      public ProximalPrimal apply(final DiffFunction primal, final Object u, final Object z, final double rho, final MutableInnerProductModule space) {
         return new ProximalPrimal(primal, u, z, rho, space);
      }

      public Option unapply(final ProximalPrimal x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple4(x$0.primal(), x$0.u(), x$0.z(), BoxesRunTime.boxToDouble(x$0.rho()))));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ProximalPrimal$.class);
      }
   }

   public static class Projection implements Product, Serializable {
      private final Proximal proximal;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Proximal proximal() {
         return this.proximal;
      }

      public DenseVector project(final DenseVector x) {
         this.proximal().prox(x, this.proximal().prox$default$2());
         return x;
      }

      public Projection copy(final Proximal proximal) {
         return new Projection(proximal);
      }

      public Proximal copy$default$1() {
         return this.proximal();
      }

      public String productPrefix() {
         return "Projection";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.proximal();
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
         return x$1 instanceof Projection;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "proximal";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label53: {
               boolean var2;
               if (x$1 instanceof Projection) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label36: {
                     label35: {
                        Projection var4 = (Projection)x$1;
                        Proximal var10000 = this.proximal();
                        Proximal var5 = var4.proximal();
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

      public Projection(final Proximal proximal) {
         this.proximal = proximal;
         Product.$init$(this);
      }
   }

   public static class Projection$ extends AbstractFunction1 implements Serializable {
      public static final Projection$ MODULE$ = new Projection$();

      public final String toString() {
         return "Projection";
      }

      public Projection apply(final Proximal proximal) {
         return new Projection(proximal);
      }

      public Option unapply(final Projection x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.proximal()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Projection$.class);
      }
   }
}
