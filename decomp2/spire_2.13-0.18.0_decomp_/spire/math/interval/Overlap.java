package spire.math.interval;

import cats.kernel.Eq;
import cats.kernel.Order;
import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import spire.math.Interval;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u0005g!\u00026l\u0003C\u0011\bbBA\n\u0001\u0011\u0005\u0011Q\u0003\u0005\b\u0003c\u0001A\u0011AA\u001a\u0011\u001d\tY\u0004\u0001C\u0001\u0003gAq!!\u0010\u0001\t\u0003\t\u0019dB\u0004\u0004@.D\t!!\u0016\u0007\r)\\\u0007\u0012AA\"\u0011\u001d\t\u0019B\u0002C\u0001\u0003'2a!!\u0011\u0007\u0001\u000eM\u0004BCA@\u0011\tU\r\u0011\"\u0001\u0004~!Q\u0011Q\u0019\u0005\u0003\u0012\u0003\u0006Iaa \t\u0015\u0005-\u0005B!f\u0001\n\u0003\u0019i\b\u0003\u0006\u0002H\"\u0011\t\u0012)A\u0005\u0007\u007fB\u0001\"a\u0005\t\t\u0003y7\u0011\u0011\u0005\b\u0007\u0013CA\u0011ABF\u0011%\t\t\u000eCA\u0001\n\u0003\u0019\u0019\nC\u0005\u0002d\"\t\n\u0011\"\u0001\u0004$\"I\u0011q \u0005\u0012\u0002\u0013\u000511\u0016\u0005\n\u0005\u000bA\u0011\u0011!C!\u0005\u000fA\u0011B!\u0003\t\u0003\u0003%\tAa\u0003\t\u0013\tM\u0001\"!A\u0005\u0002\r=\u0006\"\u0003B\u000e\u0011\u0005\u0005I\u0011\tB\u000f\u0011%\u0011Y\u0003CA\u0001\n\u0003\u0019\u0019\fC\u0005\u00032!\t\t\u0011\"\u0011\u00048\"I!q\u0007\u0005\u0002\u0002\u0013\u0005#\u0011\b\u0005\n\u0003CB\u0011\u0011!C!\u0003GB\u0011Ba\u000f\t\u0003\u0003%\tea/\b\u0013\u0005]c!!A\t\u0002\u0005ec!CA!\r\u0005\u0005\t\u0012AA/\u0011\u001d\t\u0019\u0002\bC\u0001\u0003?B\u0011\"!\u0019\u001d\u0003\u0003%)%a\u0019\t\u0013\u0005ED$!A\u0005\u0002\u0006M\u0004\"CAG9\u0005\u0005I\u0011QAH\u0011%\tY\u000bHA\u0001\n\u0013\tiK\u0002\u0004\u00026\u001a\u0001\u0015q\u0017\u0005\u000b\u0003\u007f\u0012#Q3A\u0005\u0002\u0005\u0005\u0007BCAcE\tE\t\u0015!\u0003\u0002D\"Q\u00111\u0012\u0012\u0003\u0016\u0004%\t!!1\t\u0015\u0005\u001d'E!E!\u0002\u0013\t\u0019\r\u0003\u0005\u0002\u0014\t\"\ta\\Ae\u0011%\t\tNIA\u0001\n\u0003\t\u0019\u000eC\u0005\u0002d\n\n\n\u0011\"\u0001\u0002f\"I\u0011q \u0012\u0012\u0002\u0013\u0005!\u0011\u0001\u0005\n\u0005\u000b\u0011\u0013\u0011!C!\u0005\u000fA\u0011B!\u0003#\u0003\u0003%\tAa\u0003\t\u0013\tM!%!A\u0005\u0002\tU\u0001\"\u0003B\u000eE\u0005\u0005I\u0011\tB\u000f\u0011%\u0011YCIA\u0001\n\u0003\u0011i\u0003C\u0005\u00032\t\n\t\u0011\"\u0011\u00034!I!q\u0007\u0012\u0002\u0002\u0013\u0005#\u0011\b\u0005\n\u0003C\u0012\u0013\u0011!C!\u0003GB\u0011Ba\u000f#\u0003\u0003%\tE!\u0010\b\u0013\t\u0005c!!A\t\u0002\t\rc!CA[\r\u0005\u0005\t\u0012\u0001B#\u0011\u001d\t\u0019\"\u000eC\u0001\u0005\u000fB\u0011\"!\u00196\u0003\u0003%)%a\u0019\t\u0013\u0005ET'!A\u0005\u0002\n%\u0003\"CAGk\u0005\u0005I\u0011\u0011B-\u0011%\tY+NA\u0001\n\u0013\tiK\u0002\u0004\u0003l\u0019\u0001%Q\u000e\u0005\u000b\u0005oZ$Q3A\u0005\u0002\te\u0004B\u0003B?w\tE\t\u0015!\u0003\u0003|!Q!qP\u001e\u0003\u0016\u0004%\tA!\u001f\t\u0015\t\u00055H!E!\u0002\u0013\u0011Y\b\u0003\u0005\u0002\u0014m\"\ta\u001cBB\u0011%\t\tnOA\u0001\n\u0003\u0011Y\tC\u0005\u0002dn\n\n\u0011\"\u0001\u0003\u001c\"I\u0011q`\u001e\u0012\u0002\u0013\u0005!1\u0015\u0005\n\u0005\u000bY\u0014\u0011!C!\u0005\u000fA\u0011B!\u0003<\u0003\u0003%\tAa\u0003\t\u0013\tM1(!A\u0005\u0002\t\u001d\u0006\"\u0003B\u000ew\u0005\u0005I\u0011\tB\u000f\u0011%\u0011YcOA\u0001\n\u0003\u0011Y\u000bC\u0005\u00032m\n\t\u0011\"\u0011\u00030\"I!qG\u001e\u0002\u0002\u0013\u0005#\u0011\b\u0005\n\u0003CZ\u0014\u0011!C!\u0003GB\u0011Ba\u000f<\u0003\u0003%\tEa-\b\u0013\t]f!!A\t\u0002\tef!\u0003B6\r\u0005\u0005\t\u0012\u0001B^\u0011\u001d\t\u0019B\u0014C\u0001\u0005{C\u0011\"!\u0019O\u0003\u0003%)%a\u0019\t\u0013\u0005Ed*!A\u0005\u0002\n}\u0006\"CAG\u001d\u0006\u0005I\u0011\u0011Bh\u0011%\tYKTA\u0001\n\u0013\tiK\u0002\u0004\u0003b\u001a\u0001%1\u001d\u0005\t\u0003'!F\u0011A8\u0003n\"I\u0011\u0011\u001b+\u0002\u0002\u0013\u0005!\u0011\u001f\u0005\n\u0005\u000b!\u0016\u0011!C!\u0005\u000fA\u0011B!\u0003U\u0003\u0003%\tAa\u0003\t\u0013\tMA+!A\u0005\u0002\tm\b\"\u0003B\u000e)\u0006\u0005I\u0011\tB\u000f\u0011%\u0011Y\u0003VA\u0001\n\u0003\u0011y\u0010C\u0005\u00032Q\u000b\t\u0011\"\u0011\u0004\u0004!I!q\u0007+\u0002\u0002\u0013\u0005#\u0011\b\u0005\n\u0003C\"\u0016\u0011!C!\u0003GB\u0011Ba\u000fU\u0003\u0003%\tea\u0002\b\u0013\r-a!!A\t\u0002\r5a!\u0003Bq\r\u0005\u0005\t\u0012AB\b\u0011\u001d\t\u0019\"\u0019C\u0001\u0007#A\u0011\"!\u0019b\u0003\u0003%)%a\u0019\t\u0013\u0005E\u0014-!A\u0005\u0002\u000eM\u0001\"CAGC\u0006\u0005I\u0011QB\u000f\u0011%\tY+YA\u0001\n\u0013\ti\u000bC\u0004\u0002r\u0019!\ta!\u000b\t\u000f\rmc\u0001b\u0001\u0004^!I\u00111\u0016\u0004\u0002\u0002\u0013%\u0011Q\u0016\u0002\b\u001fZ,'\u000f\\1q\u0015\taW.\u0001\u0005j]R,'O^1m\u0015\tqw.\u0001\u0003nCRD'\"\u00019\u0002\u000bM\u0004\u0018N]3\u0004\u0001U\u00191/a\b\u0014\t\u0001!(0 \t\u0003kbl\u0011A\u001e\u0006\u0002o\u0006)1oY1mC&\u0011\u0011P\u001e\u0002\u0007\u0003:L(+\u001a4\u0011\u0005U\\\u0018B\u0001?w\u0005\u001d\u0001&o\u001c3vGR\u00042A`A\u0007\u001d\ry\u0018\u0011\u0002\b\u0005\u0003\u0003\t9!\u0004\u0002\u0002\u0004)\u0019\u0011QA9\u0002\rq\u0012xn\u001c;?\u0013\u00059\u0018bAA\u0006m\u00069\u0001/Y2lC\u001e,\u0017\u0002BA\b\u0003#\u0011AbU3sS\u0006d\u0017N_1cY\u0016T1!a\u0003w\u0003\u0019a\u0014N\\5u}Q\u0011\u0011q\u0003\t\u0006\u00033\u0001\u00111D\u0007\u0002WB!\u0011QDA\u0010\u0019\u0001!q!!\t\u0001\u0005\u0004\t\u0019CA\u0001B#\u0011\t)#a\u000b\u0011\u0007U\f9#C\u0002\u0002*Y\u0014qAT8uQ&tw\rE\u0002v\u0003[I1!a\fw\u0005\r\te._\u0001\u000bSN$\u0015n\u001d6pS:$XCAA\u001b!\r)\u0018qG\u0005\u0004\u0003s1(a\u0002\"p_2,\u0017M\\\u0001\tSN\u001cVOY:fi\u00069\u0011n]#rk\u0006d\u0017&\u0002\u0001\t)\nZ$\u0001\u0003#jg*|\u0017N\u001c;\u0014\t\u0019!\u0018Q\t\t\u0005\u0003\u000f\n\t&\u0004\u0002\u0002J)!\u00111JA'\u0003\tIwN\u0003\u0002\u0002P\u0005!!.\u0019<b\u0013\u0011\ty!!\u0013\u0015\u0005\u0005U\u0003cAA\r\r\u0005AA)[:k_&tG\u000fE\u0002\u0002\\qi\u0011AB\n\u00059Q\f)\u0005\u0006\u0002\u0002Z\u0005AAo\\*ue&tw\r\u0006\u0002\u0002fA!\u0011qMA7\u001b\t\tIG\u0003\u0003\u0002l\u00055\u0013\u0001\u00027b]\u001eLA!a\u001c\u0002j\t11\u000b\u001e:j]\u001e\fQ!\u00199qYf,B!!\u001e\u0002|Q1\u0011qOA?\u0003\u0013\u0003R!a\u0017\t\u0003s\u0002B!!\b\u0002|\u00119\u0011\u0011E\u0010C\u0002\u0005\r\u0002bBA@?\u0001\u0007\u0011\u0011Q\u0001\u0006Y><XM\u001d\t\u0007\u0003\u0007\u000b))!\u001f\u000e\u00035L1!a\"n\u0005!Ie\u000e^3sm\u0006d\u0007bBAF?\u0001\u0007\u0011\u0011Q\u0001\u0006kB\u0004XM]\u0001\bk:\f\u0007\u000f\u001d7z+\u0011\t\t*a)\u0015\t\u0005M\u0015Q\u0015\t\u0006k\u0006U\u0015\u0011T\u0005\u0004\u0003/3(AB(qi&|g\u000eE\u0004v\u00037\u000by*a(\n\u0007\u0005ueO\u0001\u0004UkBdWM\r\t\u0007\u0003\u0007\u000b))!)\u0011\t\u0005u\u00111\u0015\u0003\b\u0003C\u0001#\u0019AA\u0012\u0011%\t9\u000bIA\u0001\u0002\u0004\tI+A\u0002yIA\u0002R!a\u0017\t\u0003C\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a,\u0011\t\u0005\u001d\u0014\u0011W\u0005\u0005\u0003g\u000bIG\u0001\u0004PE*,7\r\u001e\u0002\u000f!\u0006\u0014H/[1m\u001fZ,'\u000f\\1q+\u0011\tI,a0\u0014\u000b\t\nYL_?\u0011\u000b\u0005e\u0001!!0\u0011\t\u0005u\u0011q\u0018\u0003\b\u0003C\u0011#\u0019AA\u0012+\t\t\u0019\r\u0005\u0004\u0002\u0004\u0006\u0015\u0015QX\u0001\u0007Y><XM\u001d\u0011\u0002\rU\u0004\b/\u001a:!)\u0019\tY-!4\u0002PB)\u00111\f\u0012\u0002>\"9\u0011qP\u0014A\u0002\u0005\r\u0007bBAFO\u0001\u0007\u00111Y\u0001\u0005G>\u0004\u00180\u0006\u0003\u0002V\u0006mGCBAl\u0003;\f\t\u000fE\u0003\u0002\\\t\nI\u000e\u0005\u0003\u0002\u001e\u0005mGaBA\u0011Q\t\u0007\u00111\u0005\u0005\n\u0003\u007fB\u0003\u0013!a\u0001\u0003?\u0004b!a!\u0002\u0006\u0006e\u0007\"CAFQA\u0005\t\u0019AAp\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*B!a:\u0002~V\u0011\u0011\u0011\u001e\u0016\u0005\u0003\u0007\fYo\u000b\u0002\u0002nB!\u0011q^A}\u001b\t\t\tP\u0003\u0003\u0002t\u0006U\u0018!C;oG\",7m[3e\u0015\r\t9P^\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA~\u0003c\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t\u001d\t\t#\u000bb\u0001\u0003G\tabY8qs\u0012\"WMZ1vYR$#'\u0006\u0003\u0002h\n\rAaBA\u0011U\t\u0007\u00111E\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005\u0015\u0014\u0001\u00049s_\u0012,8\r^!sSRLXC\u0001B\u0007!\r)(qB\u0005\u0004\u0005#1(aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\u0016\u0005/A\u0011B!\u0007.\u0003\u0003\u0005\rA!\u0004\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\u0011y\u0002\u0005\u0004\u0003\"\t\u001d\u00121F\u0007\u0003\u0005GQ1A!\nw\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0005S\u0011\u0019C\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u001b\u0005_A\u0011B!\u00070\u0003\u0003\u0005\r!a\u000b\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003K\u0012)\u0004C\u0005\u0003\u001aA\n\t\u00111\u0001\u0003\u000e\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0003\u000e\u00051Q-];bYN$B!!\u000e\u0003@!I!\u0011D\u001a\u0002\u0002\u0003\u0007\u00111F\u0001\u000f!\u0006\u0014H/[1m\u001fZ,'\u000f\\1q!\r\tY&N\n\u0005kQ\f)\u0005\u0006\u0002\u0003DU!!1\nB))\u0019\u0011iEa\u0015\u0003XA)\u00111\f\u0012\u0003PA!\u0011Q\u0004B)\t\u001d\t\t\u0003\u000fb\u0001\u0003GAq!a 9\u0001\u0004\u0011)\u0006\u0005\u0004\u0002\u0004\u0006\u0015%q\n\u0005\b\u0003\u0017C\u0004\u0019\u0001B++\u0011\u0011YF!\u001a\u0015\t\tu#q\r\t\u0006k\u0006U%q\f\t\bk\u0006m%\u0011\rB1!\u0019\t\u0019)!\"\u0003dA!\u0011Q\u0004B3\t\u001d\t\t#\u000fb\u0001\u0003GA\u0011\"a*:\u0003\u0003\u0005\rA!\u001b\u0011\u000b\u0005m#Ea\u0019\u0003\rM+(m]3u+\u0011\u0011yG!\u001e\u0014\u000bm\u0012\tH_?\u0011\u000b\u0005e\u0001Aa\u001d\u0011\t\u0005u!Q\u000f\u0003\b\u0003CY$\u0019AA\u0012\u0003\u0015IgN\\3s+\t\u0011Y\b\u0005\u0004\u0002\u0004\u0006\u0015%1O\u0001\u0007S:tWM\u001d\u0011\u0002\u000b=,H/\u001a:\u0002\r=,H/\u001a:!)\u0019\u0011)Ia\"\u0003\nB)\u00111L\u001e\u0003t!9!q\u000f!A\u0002\tm\u0004b\u0002B@\u0001\u0002\u0007!1P\u000b\u0005\u0005\u001b\u0013\u0019\n\u0006\u0004\u0003\u0010\nU%\u0011\u0014\t\u0006\u00037Z$\u0011\u0013\t\u0005\u0003;\u0011\u0019\nB\u0004\u0002\"\u0005\u0013\r!a\t\t\u0013\t]\u0014\t%AA\u0002\t]\u0005CBAB\u0003\u000b\u0013\t\nC\u0005\u0003\u0000\u0005\u0003\n\u00111\u0001\u0003\u0018V!!Q\u0014BQ+\t\u0011yJ\u000b\u0003\u0003|\u0005-HaBA\u0011\u0005\n\u0007\u00111E\u000b\u0005\u0005;\u0013)\u000bB\u0004\u0002\"\r\u0013\r!a\t\u0015\t\u0005-\"\u0011\u0016\u0005\n\u000531\u0015\u0011!a\u0001\u0005\u001b!B!!\u000e\u0003.\"I!\u0011\u0004%\u0002\u0002\u0003\u0007\u00111\u0006\u000b\u0005\u0003K\u0012\t\fC\u0005\u0003\u001a%\u000b\t\u00111\u0001\u0003\u000eQ!\u0011Q\u0007B[\u0011%\u0011I\u0002TA\u0001\u0002\u0004\tY#\u0001\u0004Tk\n\u001cX\r\u001e\t\u0004\u00037r5\u0003\u0002(u\u0003\u000b\"\"A!/\u0016\t\t\u0005'q\u0019\u000b\u0007\u0005\u0007\u0014IM!4\u0011\u000b\u0005m3H!2\u0011\t\u0005u!q\u0019\u0003\b\u0003C\t&\u0019AA\u0012\u0011\u001d\u00119(\u0015a\u0001\u0005\u0017\u0004b!a!\u0002\u0006\n\u0015\u0007b\u0002B@#\u0002\u0007!1Z\u000b\u0005\u0005#\u0014Y\u000e\u0006\u0003\u0003T\nu\u0007#B;\u0002\u0016\nU\u0007cB;\u0002\u001c\n]'q\u001b\t\u0007\u0003\u0007\u000b)I!7\u0011\t\u0005u!1\u001c\u0003\b\u0003C\u0011&\u0019AA\u0012\u0011%\t9KUA\u0001\u0002\u0004\u0011y\u000eE\u0003\u0002\\m\u0012INA\u0003FcV\fG.\u0006\u0003\u0003f\n-8#\u0002+\u0003hjl\b#BA\r\u0001\t%\b\u0003BA\u000f\u0005W$q!!\tU\u0005\u0004\t\u0019\u0003\u0006\u0002\u0003pB)\u00111\f+\u0003jV!!1\u001fB})\t\u0011)\u0010E\u0003\u0002\\Q\u00139\u0010\u0005\u0003\u0002\u001e\teHaBA\u0011-\n\u0007\u00111\u0005\u000b\u0005\u0003W\u0011i\u0010C\u0005\u0003\u001ae\u000b\t\u00111\u0001\u0003\u000eQ!\u0011QGB\u0001\u0011%\u0011IbWA\u0001\u0002\u0004\tY\u0003\u0006\u0003\u0002f\r\u0015\u0001\"\u0003B\r9\u0006\u0005\t\u0019\u0001B\u0007)\u0011\t)d!\u0003\t\u0013\teq,!AA\u0002\u0005-\u0012!B#rk\u0006d\u0007cAA.CN!\u0011\r^A#)\t\u0019i!\u0006\u0003\u0004\u0016\rmACAB\f!\u0015\tY\u0006VB\r!\u0011\tiba\u0007\u0005\u000f\u0005\u0005BM1\u0001\u0002$U!1qDB\u0014)\u0011\t)d!\t\t\u0013\u0005\u001dV-!AA\u0002\r\r\u0002#BA.)\u000e\u0015\u0002\u0003BA\u000f\u0007O!q!!\tf\u0005\u0004\t\u0019#\u0006\u0003\u0004,\rMBCBB\u0017\u0007#\u001a9\u0006\u0006\u0003\u00040\rU\u0002#BA\r\u0001\rE\u0002\u0003BA\u000f\u0007g!q!!\th\u0005\u0004\t\u0019\u0003C\u0005\u00048\u001d\f\t\u0011q\u0001\u0004:\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\r\rm21JB\u0019\u001d\u0011\u0019ida\u0012\u000f\t\r}21\t\b\u0005\u0003\u0003\u0019\t%C\u0001q\u0013\r\u0019)e\\\u0001\bC2<WM\u0019:b\u0013\u0011\tYa!\u0013\u000b\u0007\r\u0015s.\u0003\u0003\u0004N\r=#!B(sI\u0016\u0014(\u0002BA\u0006\u0007\u0013Bqaa\u0015h\u0001\u0004\u0019)&A\u0002mQN\u0004b!a!\u0002\u0006\u000eE\u0002bBB-O\u0002\u00071QK\u0001\u0004e\"\u001c\u0018!C3r\u001fZ,'\u000f\\1q+\u0011\u0019yfa\u001b\u0015\t\r\u00054Q\u000e\t\u0007\u0007w\u0019\u0019ga\u001a\n\t\r\u00154q\n\u0002\u0003\u000bF\u0004R!!\u0007\u0001\u0007S\u0002B!!\b\u0004l\u00119\u0011\u0011\u00055C\u0002\u0005\r\u0002\"CB8Q\u0006\u0005\t9AB9\u0003))g/\u001b3f]\u000e,GE\r\t\u0007\u0007w\u0019\u0019g!\u001b\u0016\t\rU41P\n\u0006\u0011\r]$0 \t\u0006\u00033\u00011\u0011\u0010\t\u0005\u0003;\u0019Y\bB\u0004\u0002\"!\u0011\r!a\t\u0016\u0005\r}\u0004CBAB\u0003\u000b\u001bI\b\u0006\u0004\u0004\u0004\u000e\u00155q\u0011\t\u0006\u00037B1\u0011\u0010\u0005\b\u0003\u007fj\u0001\u0019AB@\u0011\u001d\tY)\u0004a\u0001\u0007\u007f\nAA[8j]R!1qPBG\u0011\u001d\u0019yI\u0004a\u0002\u0007#\u000b\u0011a\u001c\t\u0007\u0007w\u0019Ye!\u001f\u0016\t\rU51\u0014\u000b\u0007\u0007/\u001bij!)\u0011\u000b\u0005m\u0003b!'\u0011\t\u0005u11\u0014\u0003\b\u0003Cy!\u0019AA\u0012\u0011%\tyh\u0004I\u0001\u0002\u0004\u0019y\n\u0005\u0004\u0002\u0004\u0006\u00155\u0011\u0014\u0005\n\u0003\u0017{\u0001\u0013!a\u0001\u0007?+Ba!*\u0004*V\u00111q\u0015\u0016\u0005\u0007\u007f\nY\u000fB\u0004\u0002\"A\u0011\r!a\t\u0016\t\r\u00156Q\u0016\u0003\b\u0003C\t\"\u0019AA\u0012)\u0011\tYc!-\t\u0013\teA#!AA\u0002\t5A\u0003BA\u001b\u0007kC\u0011B!\u0007\u0017\u0003\u0003\u0005\r!a\u000b\u0015\t\u0005\u00154\u0011\u0018\u0005\n\u000539\u0012\u0011!a\u0001\u0005\u001b!B!!\u000e\u0004>\"I!\u0011\u0004\u000e\u0002\u0002\u0003\u0007\u00111F\u0001\b\u001fZ,'\u000f\\1q\u0001"
)
public abstract class Overlap implements Product, Serializable {
   public static Eq eqOverlap(final Eq evidence$2) {
      return Overlap$.MODULE$.eqOverlap(evidence$2);
   }

   public static Overlap apply(final Interval lhs, final Interval rhs, final Order evidence$1) {
      return Overlap$.MODULE$.apply(lhs, rhs, evidence$1);
   }

   public Iterator productIterator() {
      return Product.productIterator$(this);
   }

   public String productPrefix() {
      return Product.productPrefix$(this);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean isDisjoint() {
      return this instanceof Disjoint;
   }

   public boolean isSubset() {
      boolean var2;
      if (this instanceof Subset) {
         var2 = true;
      } else if (this instanceof Equal) {
         var2 = true;
      } else {
         var2 = false;
      }

      boolean var1;
      if (var2) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isEqual() {
      return this instanceof Equal;
   }

   public Overlap() {
      Product.$init$(this);
   }

   public static class Disjoint extends Overlap {
      private final Interval lower;
      private final Interval upper;

      public Interval lower() {
         return this.lower;
      }

      public Interval upper() {
         return this.upper;
      }

      public Interval join(final Order o) {
         return ((Interval)this.lower().unary_$tilde(o).last()).intersect((Interval)this.upper().unary_$tilde(o).head(), o);
      }

      public Disjoint copy(final Interval lower, final Interval upper) {
         return new Disjoint(lower, upper);
      }

      public Interval copy$default$1() {
         return this.lower();
      }

      public Interval copy$default$2() {
         return this.upper();
      }

      public String productPrefix() {
         return "Disjoint";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.lower();
               break;
            case 1:
               var10000 = this.upper();
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
         return x$1 instanceof Disjoint;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "lower";
               break;
            case 1:
               var10000 = "upper";
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
         boolean var9;
         if (this != x$1) {
            label63: {
               boolean var2;
               if (x$1 instanceof Disjoint) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label45: {
                     label54: {
                        Disjoint var4 = (Disjoint)x$1;
                        Interval var10000 = this.lower();
                        Interval var5 = var4.lower();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label54;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label54;
                        }

                        var10000 = this.upper();
                        Interval var6 = var4.upper();
                        if (var10000 == null) {
                           if (var6 != null) {
                              break label54;
                           }
                        } else if (!var10000.equals(var6)) {
                           break label54;
                        }

                        if (var4.canEqual(this)) {
                           var9 = true;
                           break label45;
                        }
                     }

                     var9 = false;
                  }

                  if (var9) {
                     break label63;
                  }
               }

               var9 = false;
               return var9;
            }
         }

         var9 = true;
         return var9;
      }

      public Disjoint(final Interval lower, final Interval upper) {
         this.lower = lower;
         this.upper = upper;
      }
   }

   public static class Disjoint$ implements Serializable {
      public static final Disjoint$ MODULE$ = new Disjoint$();

      public final String toString() {
         return "Disjoint";
      }

      public Disjoint apply(final Interval lower, final Interval upper) {
         return new Disjoint(lower, upper);
      }

      public Option unapply(final Disjoint x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.lower(), x$0.upper())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Disjoint$.class);
      }
   }

   public static class PartialOverlap extends Overlap {
      private final Interval lower;
      private final Interval upper;

      public Interval lower() {
         return this.lower;
      }

      public Interval upper() {
         return this.upper;
      }

      public PartialOverlap copy(final Interval lower, final Interval upper) {
         return new PartialOverlap(lower, upper);
      }

      public Interval copy$default$1() {
         return this.lower();
      }

      public Interval copy$default$2() {
         return this.upper();
      }

      public String productPrefix() {
         return "PartialOverlap";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.lower();
               break;
            case 1:
               var10000 = this.upper();
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
         return x$1 instanceof PartialOverlap;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "lower";
               break;
            case 1:
               var10000 = "upper";
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
         boolean var9;
         if (this != x$1) {
            label63: {
               boolean var2;
               if (x$1 instanceof PartialOverlap) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label45: {
                     label54: {
                        PartialOverlap var4 = (PartialOverlap)x$1;
                        Interval var10000 = this.lower();
                        Interval var5 = var4.lower();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label54;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label54;
                        }

                        var10000 = this.upper();
                        Interval var6 = var4.upper();
                        if (var10000 == null) {
                           if (var6 != null) {
                              break label54;
                           }
                        } else if (!var10000.equals(var6)) {
                           break label54;
                        }

                        if (var4.canEqual(this)) {
                           var9 = true;
                           break label45;
                        }
                     }

                     var9 = false;
                  }

                  if (var9) {
                     break label63;
                  }
               }

               var9 = false;
               return var9;
            }
         }

         var9 = true;
         return var9;
      }

      public PartialOverlap(final Interval lower, final Interval upper) {
         this.lower = lower;
         this.upper = upper;
      }
   }

   public static class PartialOverlap$ implements Serializable {
      public static final PartialOverlap$ MODULE$ = new PartialOverlap$();

      public final String toString() {
         return "PartialOverlap";
      }

      public PartialOverlap apply(final Interval lower, final Interval upper) {
         return new PartialOverlap(lower, upper);
      }

      public Option unapply(final PartialOverlap x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.lower(), x$0.upper())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(PartialOverlap$.class);
      }
   }

   public static class Subset extends Overlap {
      private final Interval inner;
      private final Interval outer;

      public Interval inner() {
         return this.inner;
      }

      public Interval outer() {
         return this.outer;
      }

      public Subset copy(final Interval inner, final Interval outer) {
         return new Subset(inner, outer);
      }

      public Interval copy$default$1() {
         return this.inner();
      }

      public Interval copy$default$2() {
         return this.outer();
      }

      public String productPrefix() {
         return "Subset";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.inner();
               break;
            case 1:
               var10000 = this.outer();
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
         return x$1 instanceof Subset;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "inner";
               break;
            case 1:
               var10000 = "outer";
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
         boolean var9;
         if (this != x$1) {
            label63: {
               boolean var2;
               if (x$1 instanceof Subset) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label45: {
                     label54: {
                        Subset var4 = (Subset)x$1;
                        Interval var10000 = this.inner();
                        Interval var5 = var4.inner();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label54;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label54;
                        }

                        var10000 = this.outer();
                        Interval var6 = var4.outer();
                        if (var10000 == null) {
                           if (var6 != null) {
                              break label54;
                           }
                        } else if (!var10000.equals(var6)) {
                           break label54;
                        }

                        if (var4.canEqual(this)) {
                           var9 = true;
                           break label45;
                        }
                     }

                     var9 = false;
                  }

                  if (var9) {
                     break label63;
                  }
               }

               var9 = false;
               return var9;
            }
         }

         var9 = true;
         return var9;
      }

      public Subset(final Interval inner, final Interval outer) {
         this.inner = inner;
         this.outer = outer;
      }
   }

   public static class Subset$ implements Serializable {
      public static final Subset$ MODULE$ = new Subset$();

      public final String toString() {
         return "Subset";
      }

      public Subset apply(final Interval inner, final Interval outer) {
         return new Subset(inner, outer);
      }

      public Option unapply(final Subset x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.inner(), x$0.outer())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Subset$.class);
      }
   }

   public static class Equal extends Overlap {
      public Equal copy() {
         return new Equal();
      }

      public String productPrefix() {
         return "Equal";
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
         return x$1 instanceof Equal;
      }

      public String productElementName(final int x$1) {
         String var2 = (String)Statics.ioobe(x$1);
         return var2;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var2;
         if (x$1 instanceof Equal) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2 && ((Equal)x$1).canEqual(this);
      }
   }

   public static class Equal$ implements Serializable {
      public static final Equal$ MODULE$ = new Equal$();

      public final String toString() {
         return "Equal";
      }

      public Equal apply() {
         return new Equal();
      }

      public boolean unapply(final Equal x$0) {
         return x$0 != null;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Equal$.class);
      }
   }
}
