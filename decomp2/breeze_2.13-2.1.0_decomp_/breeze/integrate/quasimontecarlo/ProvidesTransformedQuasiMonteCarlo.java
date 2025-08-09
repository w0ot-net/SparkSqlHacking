package breeze.integrate.quasimontecarlo;

import breeze.stats.distributions.Exponential;
import breeze.stats.distributions.HasInverseCdf;
import breeze.stats.distributions.RandBasis$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u0015fACA\u0007\u0003\u001f\u0001\n1!\u0001\u0002\u001e!9\u00111\u0006\u0001\u0005\u0002\u00055\u0002bBA\u001b\u0001\u0011\u0005\u0011q\u0007\u0004\n\u00037\u0002\u0001\u0013aI\u0011\u0003;B\u0011\"a\u0018\u0004\u0005\u00045\t!!\u0019\t\u000f\u0005%4A\"\u0001\u0002l\u0019I\u00111\u0013\u0001\u0011\u0002G\u0005\u0011Q\u0013\u0005\b\u0003/3a\u0011AAM\r%\ty\u0007\u0001I\u0001$\u0003\t\t\bC\u0004\u0002t!1\t!!\u001e\t\u000f\u0005-\u0005B\"\u0001\u0002\u000e\u001a1\u0011\u0011\u0016\u0001A\u0003WC!\"!4\f\u0005+\u0007I\u0011AAh\u0011)\t\to\u0003B\tB\u0003%\u0011\u0011\u001b\u0005\b\u0003G\\A\u0011AAs\u0011%\tyf\u0003b\u0001\n\u0003\t\t\u0007\u0003\u0005\u0002l.\u0001\u000b\u0011BA2\u0011\u001d\t9j\u0003C\u0001\u0003[Dq!!\u001b\f\t\u0003\t\u0019\u0010C\u0005\u0002v.\t\t\u0011\"\u0011\u0002x\"I!\u0011B\u0006\u0002\u0002\u0013\u0005\u0011\u0011\r\u0005\n\u0005\u0017Y\u0011\u0011!C\u0001\u0005\u001bA\u0011B!\u0007\f\u0003\u0003%\tEa\u0007\t\u0013\t%2\"!A\u0005\u0002\t-\u0002\"\u0003B\u0018\u0017\u0005\u0005I\u0011\tB\u0019\u0011%\u0011)dCA\u0001\n\u0003\u00129\u0004C\u0005\u0003:-\t\t\u0011\"\u0011\u0003<!I!QH\u0006\u0002\u0002\u0013\u0005#qH\u0004\n\u0005\u0007\u0002\u0011\u0011!E\u0001\u0005\u000b2\u0011\"!+\u0001\u0003\u0003E\tAa\u0012\t\u000f\u0005\rX\u0004\"\u0001\u0003`!I!\u0011H\u000f\u0002\u0002\u0013\u0015#1\b\u0005\n\u0005Cj\u0012\u0011!CA\u0005GB\u0011Ba\u001a\u001e\u0003\u0003%\tI!\u001b\u0007\u0013\tU\u0004\u0001%A\u0012\u0002\t]ta\u0002B>\u0001!\u0005!Q\u0010\u0004\b\u0005k\u0002\u0001\u0012\u0001B@\u0011\u001d\t\u0019\u000f\nC\u0001\u0005\u0003CqA!\u0019%\t\u0003\u0011\u0019I\u0002\u0004\u0003\u000e\u0002\u0001%q\u0012\u0005\u000b\u0005\u000f;#Q3A\u0005\u0002\tM\u0005B\u0003BKO\tE\t\u0015!\u0003\u0002>!Q!qS\u0014\u0003\u0016\u0004%\tAa%\t\u0015\teuE!E!\u0002\u0013\ti\u0004C\u0004\u0002d\u001e\"\tAa'\t\u0013\u0005}sE1A\u0005\u0002\u0005\u0005\u0004\u0002CAvO\u0001\u0006I!a\u0019\t\u0013\t\rvE1A\u0005\n\tM\u0005\u0002\u0003BSO\u0001\u0006I!!\u0010\t\u0013\t\u001dvE1A\u0005\n\tM\u0005\u0002\u0003BUO\u0001\u0006I!!\u0010\t\u0013\t-vE1A\u0005\n\tM\u0005\u0002\u0003BWO\u0001\u0006I!!\u0010\t\u0013\u0005}t\u00051A\u0005\n\tM\u0005\"\u0003BXO\u0001\u0007I\u0011\u0002BY\u0011!\u0011)l\nQ!\n\u0005u\u0002bBA:O\u0011\u0005!q\u0017\u0005\b\u0003\u0017;C\u0011\u0001B`\u0011\u001d\tIg\nC\u0001\u0005\u000bD\u0011\"!>(\u0003\u0003%\t%a>\t\u0013\t%q%!A\u0005\u0002\u0005\u0005\u0004\"\u0003B\u0006O\u0005\u0005I\u0011\u0001Bd\u0011%\u0011IbJA\u0001\n\u0003\u0012Y\u0002C\u0005\u0003*\u001d\n\t\u0011\"\u0001\u0003L\"I!qF\u0014\u0002\u0002\u0013\u0005#q\u001a\u0005\n\u0005k9\u0013\u0011!C!\u0005oA\u0011B!\u000f(\u0003\u0003%\tEa\u000f\t\u0013\tur%!A\u0005B\tMw!\u0003Bl\u0001\u0005\u0005\t\u0012\u0001Bm\r%\u0011i\tAA\u0001\u0012\u0003\u0011Y\u000eC\u0004\u0002d\u0016#\tAa9\t\u0013\teR)!A\u0005F\tm\u0002\"\u0003B1\u000b\u0006\u0005I\u0011\u0011Bs\u0011%\u00119'RA\u0001\n\u0003\u0013YO\u0002\u0004\u0003x\u0002\u0001%\u0011 \u0005\u000b\u0005\u000fS%Q3A\u0005\u0002\tM\u0005B\u0003BK\u0015\nE\t\u0015!\u0003\u0002>!Q!q\u0013&\u0003\u0016\u0004%\tAa%\t\u0015\te%J!E!\u0002\u0013\ti\u0004C\u0004\u0002d*#\tAa?\t\u0013\u0005}#J1A\u0005\u0002\u0005\u0005\u0004\u0002CAv\u0015\u0002\u0006I!a\u0019\t\u0013\r\r!J1A\u0005\n\tM\u0005\u0002CB\u0003\u0015\u0002\u0006I!!\u0010\t\u0013\t\r&J1A\u0005\n\tM\u0005\u0002\u0003BS\u0015\u0002\u0006I!!\u0010\t\u0013\r\u001d!J1A\u0005\n\tM\u0005\u0002CB\u0005\u0015\u0002\u0006I!!\u0010\t\u0013\u0005}$\n1A\u0005\n\tM\u0005\"\u0003BX\u0015\u0002\u0007I\u0011BB\u0006\u0011!\u0011)L\u0013Q!\n\u0005u\u0002bBA:\u0015\u0012\u00051q\u0002\u0005\b\u0003\u0017SE\u0011AB\u000b\u0011\u001d\tIG\u0013C\u0001\u00077A\u0011\"!>K\u0003\u0003%\t%a>\t\u0013\t%!*!A\u0005\u0002\u0005\u0005\u0004\"\u0003B\u0006\u0015\u0006\u0005I\u0011AB\u000f\u0011%\u0011IBSA\u0001\n\u0003\u0012Y\u0002C\u0005\u0003*)\u000b\t\u0011\"\u0001\u0004\"!I!q\u0006&\u0002\u0002\u0013\u00053Q\u0005\u0005\n\u0005kQ\u0015\u0011!C!\u0005oA\u0011B!\u000fK\u0003\u0003%\tEa\u000f\t\u0013\tu\"*!A\u0005B\r%r!CB\u0017\u0001\u0005\u0005\t\u0012AB\u0018\r%\u00119\u0010AA\u0001\u0012\u0003\u0019\t\u0004C\u0004\u0002d\"$\ta!\u000e\t\u0013\te\u0002.!A\u0005F\tm\u0002\"\u0003B1Q\u0006\u0005I\u0011QB\u001c\u0011%\u00119\u0007[A\u0001\n\u0003\u001biD\u0002\u0004\u0004B\u0001\u000111\t\u0005\u000b\u0007\u001bj'Q1A\u0005\u0002\r=\u0003BCB,[\n\u0005\t\u0015!\u0003\u0004R!9\u00111]7\u0005\u0002\re\u0003bBAr[\u0012\u00051q\f\u0005\n\u0003\u001fj'\u0019!C\u0001\u0007GB\u0001ba\u001anA\u0003%1Q\r\u0005\n\u0007Sj'\u0019!C\u0001\u0003CB\u0001ba\u001bnA\u0003%\u00111\r\u0005\n\u0007[j'\u0019!C\u0001\u0003CB\u0001ba\u001cnA\u0003%\u00111\r\u0005\n\u0007cj'\u0019!C\u0005\u0007gB\u0001ba\u001fnA\u0003%1Q\u000f\u0005\n\u0007{j'\u0019!C\u0005\u0007\u007fB\u0001b!!nA\u0003%\u0011\u0011\u0011\u0005\n\u0007\u0007k\u0007\u0019!C\u0005\u0007\u000bC\u0011ba\"n\u0001\u0004%Ia!#\t\u0011\r5U\u000e)Q\u0005\u0003\u000fBqaa$n\t\u0003\u0019)\tC\u0005\u0004\u00126\u0004\r\u0011\"\u0003\u0004\u0014\"I1qS7A\u0002\u0013%1\u0011\u0014\u0005\t\u0007;k\u0007\u0015)\u0003\u0004\u0016\"91qT7\u0005\u0002\r\u0015\u0005bBBQ[\u0012\u000511\u0013\u0005\b\u0007GkG\u0011AB@\u0005\t\u0002&o\u001c<jI\u0016\u001cHK]1og\u001a|'/\\3e#V\f7/['p]R,7)\u0019:m_*!\u0011\u0011CA\n\u0003=\tX/Y:j[>tG/Z2be2|'\u0002BA\u000b\u0003/\t\u0011\"\u001b8uK\u001e\u0014\u0018\r^3\u000b\u0005\u0005e\u0011A\u00022sK\u0016TXm\u0001\u0001\u0014\u0007\u0001\ty\u0002\u0005\u0003\u0002\"\u0005\u001dRBAA\u0012\u0015\t\t)#A\u0003tG\u0006d\u0017-\u0003\u0003\u0002*\u0005\r\"AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0003\u0003_\u0001B!!\t\u00022%!\u00111GA\u0012\u0005\u0011)f.\u001b;\u00021E,\u0018m]5N_:$XmQ1sY>Le\u000e^3he\u0006$X\r\u0006\u0003\u0002:\u0005}E\u0003BA\u001e\u0003\u001b\"B!!\u0010\u0002DA!\u0011\u0011EA \u0013\u0011\t\t%a\t\u0003\r\u0011{WO\u00197f\u0011\u001d\t)E\u0001a\u0001\u0003\u000f\n!B\\;n'\u0006l\u0007\u000f\\3t!\u0011\t\t#!\u0013\n\t\u0005-\u00131\u0005\u0002\u0005\u0019>tw\rC\u0004\u0002P\t\u0001\r!!\u0015\u0002\u0013Y\f'/[1cY\u0016\u001c\bCBA\u0011\u0003'\n9&\u0003\u0003\u0002V\u0005\r\"A\u0003\u001fsKB,\u0017\r^3e}A\u0019\u0011\u0011L\u0002\u000e\u0003\u0001\u0011q#U;bg&\u0014\u0016M\u001c3p[Z\u000b'/[1cY\u0016\u001c\u0006/Z2\u0014\u0007\r\ty\"A\u0005ok6Le\u000e];ugV\u0011\u00111\r\t\u0005\u0003C\t)'\u0003\u0003\u0002h\u0005\r\"aA%oi\u0006!1m\u001c9z+\t\t9&K\u0002\u0004\u0011\u0019\u0011\u0001EU3kK\u000e$\u0018n\u001c8Rk\u0006\u001c\u0018NU1oI>lg+\u0019:jC\ndWm\u00159fGN)\u0001\"a\b\u0002X\u00051\u0011mY2faR$b!a\u001e\u0002~\u0005\u001d\u0005\u0003BA\u0011\u0003sJA!a\u001f\u0002$\t9!i\\8mK\u0006t\u0007bBA@\u0013\u0001\u0007\u0011\u0011Q\u0001\u0002qB1\u0011\u0011EAB\u0003{IA!!\"\u0002$\t)\u0011I\u001d:bs\"9\u0011\u0011R\u0005A\u0002\u0005\r\u0014\u0001\u00039pg&$\u0018n\u001c8\u0002\u000f\r|W\u000e];uKR1\u0011QHAH\u0003#Cq!a \u000b\u0001\u0004\t\t\tC\u0004\u0002\n*\u0001\r!a\u0019\u0003GQ\u0013\u0018M\\:g_Jl\u0017N\\4Rk\u0006\u001c\u0018NU1oI>lg+\u0019:jC\ndWm\u00159fGN)a!a\b\u0002X\u0005IAO]1og\u001a|'/\u001c\u000b\u0007\u0003{\tY*!(\t\u000f\u0005}t\u00011\u0001\u0002\u0002\"9\u0011\u0011R\u0004A\u0002\u0005\r\u0004bBAQ\u0005\u0001\u0007\u00111U\u0001\u0002MBA\u0011\u0011EAS\u0003\u0003\u000bi$\u0003\u0003\u0002(\u0006\r\"!\u0003$v]\u000e$\u0018n\u001c82\u0005y!\u0015n\u001d;sS\n,H/[8o%\u0006tGm\\7WCJL\u0017M\u00197f'B,7mE\u0005\f\u0003?\ti+a,\u00026B\u0019\u0011\u0011\f\u0004\u0011\t\u0005\u0005\u0012\u0011W\u0005\u0005\u0003g\u000b\u0019CA\u0004Qe>$Wo\u0019;\u0011\t\u0005]\u0016q\u0019\b\u0005\u0003s\u000b\u0019M\u0004\u0003\u0002<\u0006\u0005WBAA_\u0015\u0011\ty,a\u0007\u0002\rq\u0012xn\u001c;?\u0013\t\t)#\u0003\u0003\u0002F\u0006\r\u0012a\u00029bG.\fw-Z\u0005\u0005\u0003\u0013\fYM\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0003\u0002F\u0006\r\u0012\u0001D5dI\u001a\u0004&o\u001c<jI\u0016\u0014XCAAi!\u0011\t\u0019.!8\u000e\u0005\u0005U'\u0002BAl\u00033\fQ\u0002Z5tiJL'-\u001e;j_:\u001c(\u0002BAn\u0003/\tQa\u001d;biNLA!a8\u0002V\ni\u0001*Y:J]Z,'o]3DI\u001a\fQ\"[2eMB\u0013xN^5eKJ\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0003\u0002h\u0006%\bcAA-\u0017!9\u0011Q\u001a\bA\u0002\u0005E\u0017A\u00038v[&s\u0007/\u001e;tAQ1\u0011QHAx\u0003cDq!a \u0012\u0001\u0004\t\t\tC\u0004\u0002\nF\u0001\r!a\u0019\u0016\u0005\u0005\u001d\u0018!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002zB!\u00111 B\u0003\u001b\t\tiP\u0003\u0003\u0002\u0000\n\u0005\u0011\u0001\u00027b]\u001eT!Aa\u0001\u0002\t)\fg/Y\u0005\u0005\u0005\u000f\tiP\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\u0011yA!\u0006\u0011\t\u0005\u0005\"\u0011C\u0005\u0005\u0005'\t\u0019CA\u0002B]fD\u0011Ba\u0006\u0016\u0003\u0003\u0005\r!a\u0019\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\u0011i\u0002\u0005\u0004\u0003 \t\u0015\"qB\u0007\u0003\u0005CQAAa\t\u0002$\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\t\u001d\"\u0011\u0005\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002x\t5\u0002\"\u0003B\f/\u0005\u0005\t\u0019\u0001B\b\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005e(1\u0007\u0005\n\u0005/A\u0012\u0011!a\u0001\u0003G\n\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003G\n\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003s\fa!Z9vC2\u001cH\u0003BA<\u0005\u0003B\u0011Ba\u0006\u001c\u0003\u0003\u0005\rAa\u0004\u0002=\u0011K7\u000f\u001e:jEV$\u0018n\u001c8SC:$w.\u001c,be&\f'\r\\3Ta\u0016\u001c\u0007cAA-;M)QD!\u0013\u0003VAA!1\nB)\u0003#\f9/\u0004\u0002\u0003N)!!qJA\u0012\u0003\u001d\u0011XO\u001c;j[\u0016LAAa\u0015\u0003N\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\t\t]#QL\u0007\u0003\u00053RAAa\u0017\u0003\u0002\u0005\u0011\u0011n\\\u0005\u0005\u0003\u0013\u0014I\u0006\u0006\u0002\u0003F\u0005)\u0011\r\u001d9msR!\u0011q\u001dB3\u0011\u001d\ti\r\ta\u0001\u0003#\fq!\u001e8baBd\u0017\u0010\u0006\u0003\u0003l\tE\u0004CBA\u0011\u0005[\n\t.\u0003\u0003\u0003p\u0005\r\"AB(qi&|g\u000eC\u0005\u0003t\u0005\n\t\u00111\u0001\u0002h\u0006\u0019\u0001\u0010\n\u0019\u0003QI+'.Z2uS>t7+Y7qY\u0016$w)Y7nCF+\u0018m]5SC:$w.\u001c,be&\f'\r\\3\u0014\u000b\t\nyB!\u001f\u0011\u0007\u0005e\u0003\"\u0001\u0015SK*,7\r^5p]N\u000bW\u000e\u001d7fI\u001e\u000bW.\\1Rk\u0006\u001c\u0018NU1oI>lg+\u0019:jC\ndW\rE\u0002\u0002Z\u0011\u001a2\u0001JA\u0010)\t\u0011i\b\u0006\u0004\u0002X\t\u0015%\u0011\u0012\u0005\b\u0005\u000f3\u0003\u0019AA\u001f\u0003\u0015\tG\u000e\u001d5b\u0011\u001d\u0011YI\na\u0001\u0003{\tAAY3uC\n)s)Y7nCF+\u0018m]5SC:$w.\u001c,be&\f'\r\\3Ta\u0016\u001c\u0017\t\u001c9iC2+\u0017/M\n\nO\u0005}!\u0011SAX\u0003k\u00032!!\u0017#+\t\ti$\u0001\u0004bYBD\u0017\rI\u0001\u0006i\",G/Y\u0001\u0007i\",G/\u0019\u0011\u0015\r\tu%q\u0014BQ!\r\tIf\n\u0005\b\u0005\u000fc\u0003\u0019AA\u001f\u0011\u001d\u00119\n\fa\u0001\u0003{\t\u0011AY\u0001\u0003E\u0002\nab\u001c8f?>4XM]0bYBD\u0017-A\bp]\u0016|vN^3s?\u0006d\u0007\u000f[1!\u0003Y!xo\\0u_~\u000bG\u000e\u001d5b?6Lg.^:`_:,\u0017a\u0006;x_~#xnX1ma\"\fw,\\5okN|vN\\3!\u0003\u0015Ax\fJ3r)\u0011\tyCa-\t\u0013\t]a'!AA\u0002\u0005u\u0012A\u0001=!)\u0019\t9H!/\u0003>\"9!1\u0018\u001dA\u0002\u0005\u0005\u0015a\u0001:wg\"9\u0011\u0011\u0012\u001dA\u0002\u0005\rDCBA\u001f\u0005\u0003\u0014\u0019\rC\u0004\u0003<f\u0002\r!!!\t\u000f\u0005%\u0015\b1\u0001\u0002dU\u0011!Q\u0014\u000b\u0005\u0005\u001f\u0011I\rC\u0005\u0003\u0018u\n\t\u00111\u0001\u0002dQ!\u0011q\u000fBg\u0011%\u00119bPA\u0001\u0002\u0004\u0011y\u0001\u0006\u0003\u0002z\nE\u0007\"\u0003B\f\u0001\u0006\u0005\t\u0019AA2)\u0011\t9H!6\t\u0013\t]1)!AA\u0002\t=\u0011!J$b[6\f\u0017+^1tSJ\u000bg\u000eZ8n-\u0006\u0014\u0018.\u00192mKN\u0003XmY!ma\"\fG*Z92!\r\tI&R\n\u0006\u000b\nu'Q\u000b\t\u000b\u0005\u0017\u0012y.!\u0010\u0002>\tu\u0015\u0002\u0002Bq\u0005\u001b\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83)\t\u0011I\u000e\u0006\u0004\u0003\u001e\n\u001d(\u0011\u001e\u0005\b\u0005\u000fC\u0005\u0019AA\u001f\u0011\u001d\u00119\n\u0013a\u0001\u0003{!BA!<\u0003vB1\u0011\u0011\u0005B7\u0005_\u0004\u0002\"!\t\u0003r\u0006u\u0012QH\u0005\u0005\u0005g\f\u0019C\u0001\u0004UkBdWM\r\u0005\n\u0005gJ\u0015\u0011!a\u0001\u0005;\u0013QeR1n[\u0006\fV/Y:j%\u0006tGm\\7WCJL\u0017M\u00197f'B,7-\u00117qQ\u0006<U-]\u0019\u0014\u0013)\u000byB!%\u00020\u0006UFC\u0002B\u007f\u0005\u007f\u001c\t\u0001E\u0002\u0002Z)CqAa\"P\u0001\u0004\ti\u0004C\u0004\u0003\u0018>\u0003\r!!\u0010\u0002\u0003\u0005\f!!\u0019\u0011\u0002\u0003\r\f!a\u0019\u0011\u0015\t\u0005=2Q\u0002\u0005\n\u0005/I\u0016\u0011!a\u0001\u0003{!b!a\u001e\u0004\u0012\rM\u0001b\u0002B^7\u0002\u0007\u0011\u0011\u0011\u0005\b\u0003\u0013[\u0006\u0019AA2)\u0019\tida\u0006\u0004\u001a!9!1\u0018/A\u0002\u0005\u0005\u0005bBAE9\u0002\u0007\u00111M\u000b\u0003\u0005{$BAa\u0004\u0004 !I!q\u00031\u0002\u0002\u0003\u0007\u00111\r\u000b\u0005\u0003o\u001a\u0019\u0003C\u0005\u0003\u0018\t\f\t\u00111\u0001\u0003\u0010Q!\u0011\u0011`B\u0014\u0011%\u00119bYA\u0001\u0002\u0004\t\u0019\u0007\u0006\u0003\u0002x\r-\u0002\"\u0003B\fM\u0006\u0005\t\u0019\u0001B\b\u0003\u0015:\u0015-\\7b#V\f7/\u001b*b]\u0012|WNV1sS\u0006\u0014G.Z*qK\u000e\fE\u000e\u001d5b\u000f\u0016\f\u0018\u0007E\u0002\u0002Z!\u001cR\u0001[B\u001a\u0005+\u0002\"Ba\u0013\u0003`\u0006u\u0012Q\bB\u007f)\t\u0019y\u0003\u0006\u0004\u0003~\u000ee21\b\u0005\b\u0005\u000f[\u0007\u0019AA\u001f\u0011\u001d\u00119j\u001ba\u0001\u0003{!BA!<\u0004@!I!1\u000f7\u0002\u0002\u0003\u0007!Q \u0002$)J\fgn\u001d4pe6,G-U;bg&luN\u001c;f\u0007\u0006\u0014Hn\\$f]\u0016\u0014\u0018\r^8s'\u0015i\u0017qDB#!\u0011\u00199e!\u0013\u000e\u0005\u0005=\u0011\u0002BB&\u0003\u001f\u0011\u0001$U;bg&luN\u001c;f\u0007\u0006\u0014Hn\\$f]\u0016\u0014\u0018\r^8s\u0003-IgNV1sS\u0006\u0014G.Z:\u0016\u0005\rE\u0003CBA\\\u0007'\n9&\u0003\u0003\u0004V\u0005-'\u0001\u0002'jgR\fA\"\u001b8WCJL\u0017M\u00197fg\u0002\"Baa\u0017\u0004^A\u0019\u0011\u0011L7\t\u000f\r5\u0003\u000f1\u0001\u0004RQ!11LB1\u0011\u001d\u0019i%\u001da\u0001\u0003#*\"a!\u001a\u0011\r\u0005\u0005\u00121QA,\u0003)1\u0018M]5bE2,7\u000fI\u0001\nI&lWM\\:j_:\f!\u0002Z5nK:\u001c\u0018n\u001c8!\u00039Ig\u000e];u\t&lWM\\:j_:\fq\"\u001b8qkR$\u0015.\\3og&|g\u000eI\u0001\u000eE\u0006\u001cXmR3oKJ\fGo\u001c:\u0016\u0005\rU\u0004\u0003BB$\u0007oJAa!\u001f\u0002\u0010\tQ\")Y:f+:Lgm\u001c:n\u0011\u0006dGo\u001c8HK:,'/\u0019;pe\u0006q!-Y:f\u000f\u0016tWM]1u_J\u0004\u0013\u0001D2veJ,g\u000e\u001e,bYV,WCAAA\u00035\u0019WO\u001d:f]R4\u0016\r\\;fA\u0005qq-\u001a8fe\u0006$X\rZ\"pk:$XCAA$\u0003I9WM\\3sCR,GmQ8v]R|F%Z9\u0015\t\u0005=21\u0012\u0005\n\u0005/i\u0018\u0011!a\u0001\u0003\u000f\nqbZ3oKJ\fG/\u001a3D_VtG\u000fI\u0001\r]Vlw)\u001a8fe\u0006$X\rZ\u0001\u000ee\u0016TWm\u0019;fI\u000e{WO\u001c;\u0016\u0005\rU\u0005CBA\u0011\u0003\u0007\u000b9%A\tsK*,7\r^3e\u0007>,h\u000e^0%KF$B!a\f\u0004\u001c\"Q!qCA\u0002\u0003\u0003\u0005\ra!&\u0002\u001dI,'.Z2uK\u0012\u001cu.\u001e8uA\u0005ia.^7SK*,7\r^5p]N\fqC\\;n%\u0016TWm\u0019;j_:\u001c()\u001f,be&\f'\r\\3\u0002\u001b\u001d,GOT3yiVs7/\u00194f\u0001"
)
public interface ProvidesTransformedQuasiMonteCarlo {
   DistributionRandomVariableSpec$ DistributionRandomVariableSpec();

   RejectionSampledGammaQuasiRandomVariable$ RejectionSampledGammaQuasiRandomVariable();

   GammaQuasiRandomVariableSpecAlphaLeq1$ GammaQuasiRandomVariableSpecAlphaLeq1();

   GammaQuasiRandomVariableSpecAlphaGeq1$ GammaQuasiRandomVariableSpecAlphaGeq1();

   // $FF: synthetic method
   static double quasiMonteCarloIntegrate$(final ProvidesTransformedQuasiMonteCarlo $this, final Function1 f, final Seq variables, final long numSamples) {
      return $this.quasiMonteCarloIntegrate(f, variables, numSamples);
   }

   default double quasiMonteCarloIntegrate(final Function1 f, final Seq variables, final long numSamples) {
      TransformedQuasiMonteCarloGenerator generator = new TransformedQuasiMonteCarloGenerator(variables);
      double fSum = (double)0.0F;

      for(long i = 0L; i < numSamples; ++i) {
         fSum += BoxesRunTime.unboxToDouble(f.apply(generator.getNextUnsafe()));
      }

      return fSum / (double)numSamples;
   }

   static void $init$(final ProvidesTransformedQuasiMonteCarlo $this) {
   }

   public class DistributionRandomVariableSpec implements TransformingQuasiRandomVariableSpec, Product, Serializable {
      private final HasInverseCdf icdfProvider;
      private final int numInputs;
      // $FF: synthetic field
      public final ProvidesTransformedQuasiMonteCarlo $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public HasInverseCdf icdfProvider() {
         return this.icdfProvider;
      }

      public int numInputs() {
         return this.numInputs;
      }

      public double transform(final double[] x, final int position) {
         return this.icdfProvider().inverseCdf(x[position]);
      }

      public DistributionRandomVariableSpec copy() {
         return this.breeze$integrate$quasimontecarlo$ProvidesTransformedQuasiMonteCarlo$DistributionRandomVariableSpec$$$outer().new DistributionRandomVariableSpec(this.icdfProvider());
      }

      public String productPrefix() {
         return "DistributionRandomVariableSpec";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.icdfProvider();
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
         return x$1 instanceof DistributionRandomVariableSpec;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "icdfProvider";
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
         boolean var7;
         if (this != x$1) {
            label58: {
               boolean var2;
               if (x$1 instanceof DistributionRandomVariableSpec && ((DistributionRandomVariableSpec)x$1).breeze$integrate$quasimontecarlo$ProvidesTransformedQuasiMonteCarlo$DistributionRandomVariableSpec$$$outer() == this.breeze$integrate$quasimontecarlo$ProvidesTransformedQuasiMonteCarlo$DistributionRandomVariableSpec$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label35: {
                     label34: {
                        DistributionRandomVariableSpec var4 = (DistributionRandomVariableSpec)x$1;
                        HasInverseCdf var10000 = this.icdfProvider();
                        HasInverseCdf var5 = var4.icdfProvider();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label34;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label34;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label35;
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label58;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      // $FF: synthetic method
      public ProvidesTransformedQuasiMonteCarlo breeze$integrate$quasimontecarlo$ProvidesTransformedQuasiMonteCarlo$DistributionRandomVariableSpec$$$outer() {
         return this.$outer;
      }

      public DistributionRandomVariableSpec(final HasInverseCdf icdfProvider) {
         this.icdfProvider = icdfProvider;
         if (ProvidesTransformedQuasiMonteCarlo.this == null) {
            throw null;
         } else {
            this.$outer = ProvidesTransformedQuasiMonteCarlo.this;
            super();
            Product.$init$(this);
            this.numInputs = 1;
         }
      }
   }

   public class DistributionRandomVariableSpec$ extends AbstractFunction1 implements Serializable {
      // $FF: synthetic field
      private final ProvidesTransformedQuasiMonteCarlo $outer;

      public final String toString() {
         return "DistributionRandomVariableSpec";
      }

      public DistributionRandomVariableSpec apply(final HasInverseCdf icdfProvider) {
         return this.$outer.new DistributionRandomVariableSpec(icdfProvider);
      }

      public Option unapply(final DistributionRandomVariableSpec x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.icdfProvider()));
      }

      public DistributionRandomVariableSpec$() {
         if (ProvidesTransformedQuasiMonteCarlo.this == null) {
            throw null;
         } else {
            this.$outer = ProvidesTransformedQuasiMonteCarlo.this;
            super();
         }
      }
   }

   public class RejectionSampledGammaQuasiRandomVariable$ {
      // $FF: synthetic field
      private final ProvidesTransformedQuasiMonteCarlo $outer;

      public QuasiRandomVariableSpec apply(final double alpha, final double beta) {
         scala.Predef..MODULE$.require(alpha > (double)0);
         scala.Predef..MODULE$.require(beta > (double)0);
         return (QuasiRandomVariableSpec)(alpha == (double)1.0F ? this.$outer.new DistributionRandomVariableSpec(new Exponential((double)1 / beta, RandBasis$.MODULE$.mt0())) : (alpha > (double)1 ? this.$outer.new GammaQuasiRandomVariableSpecAlphaGeq1(alpha, beta) : this.$outer.new GammaQuasiRandomVariableSpecAlphaLeq1(alpha, beta)));
      }

      public RejectionSampledGammaQuasiRandomVariable$() {
         if (ProvidesTransformedQuasiMonteCarlo.this == null) {
            throw null;
         } else {
            this.$outer = ProvidesTransformedQuasiMonteCarlo.this;
            super();
         }
      }
   }

   public class GammaQuasiRandomVariableSpecAlphaLeq1 implements RejectionSampledGammaQuasiRandomVariable, Product, Serializable {
      private final double alpha;
      private final double theta;
      private final int numInputs;
      private final double b;
      private final double one_over_alpha;
      private final double two_to_alpha_minus_one;
      private double x;
      // $FF: synthetic field
      public final ProvidesTransformedQuasiMonteCarlo $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double alpha() {
         return this.alpha;
      }

      public double theta() {
         return this.theta;
      }

      public int numInputs() {
         return this.numInputs;
      }

      private double b() {
         return this.b;
      }

      private double one_over_alpha() {
         return this.one_over_alpha;
      }

      private double two_to_alpha_minus_one() {
         return this.two_to_alpha_minus_one;
      }

      private double x() {
         return this.x;
      }

      private void x_$eq(final double x$1) {
         this.x = x$1;
      }

      public boolean accept(final double[] rvs, final int position) {
         double u = rvs[position];
         double v = rvs[position + 1];
         this.x_$eq((double)-2 * scala.math.package..MODULE$.log((double)1 - scala.math.package..MODULE$.pow(u, this.one_over_alpha())));
         double exp_minus_x_over_two = scala.math.package..MODULE$.exp((double)-0.5F * this.x());
         return v <= scala.math.package..MODULE$.pow(this.x(), this.alpha() - (double)1) * exp_minus_x_over_two / (this.two_to_alpha_minus_one() * scala.math.package..MODULE$.pow((double)1 - exp_minus_x_over_two, this.alpha() - (double)1));
      }

      public double compute(final double[] rvs, final int position) {
         return this.theta() * this.x();
      }

      public GammaQuasiRandomVariableSpecAlphaLeq1 copy() {
         return this.breeze$integrate$quasimontecarlo$ProvidesTransformedQuasiMonteCarlo$GammaQuasiRandomVariableSpecAlphaLeq1$$$outer().new GammaQuasiRandomVariableSpecAlphaLeq1(this.alpha(), this.theta());
      }

      public String productPrefix() {
         return "GammaQuasiRandomVariableSpecAlphaLeq1";
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
               var10000 = BoxesRunTime.boxToDouble(this.theta());
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
         return x$1 instanceof GammaQuasiRandomVariableSpecAlphaLeq1;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "alpha";
               break;
            case 1:
               var10000 = "theta";
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
         var1 = Statics.mix(var1, Statics.doubleHash(this.theta()));
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label56: {
               boolean var2;
               if (x$1 instanceof GammaQuasiRandomVariableSpecAlphaLeq1 && ((GammaQuasiRandomVariableSpecAlphaLeq1)x$1).breeze$integrate$quasimontecarlo$ProvidesTransformedQuasiMonteCarlo$GammaQuasiRandomVariableSpecAlphaLeq1$$$outer() == this.breeze$integrate$quasimontecarlo$ProvidesTransformedQuasiMonteCarlo$GammaQuasiRandomVariableSpecAlphaLeq1$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  GammaQuasiRandomVariableSpecAlphaLeq1 var4 = (GammaQuasiRandomVariableSpecAlphaLeq1)x$1;
                  if (this.alpha() == var4.alpha() && this.theta() == var4.theta() && var4.canEqual(this)) {
                     break label56;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      // $FF: synthetic method
      public ProvidesTransformedQuasiMonteCarlo breeze$integrate$quasimontecarlo$ProvidesTransformedQuasiMonteCarlo$GammaQuasiRandomVariableSpecAlphaLeq1$$$outer() {
         return this.$outer;
      }

      public GammaQuasiRandomVariableSpecAlphaLeq1(final double alpha, final double theta) {
         this.alpha = alpha;
         this.theta = theta;
         if (ProvidesTransformedQuasiMonteCarlo.this == null) {
            throw null;
         } else {
            this.$outer = ProvidesTransformedQuasiMonteCarlo.this;
            super();
            Product.$init$(this);
            scala.Predef..MODULE$.require(alpha < (double)1);
            this.numInputs = 2;
            this.b = (alpha + Math.E) / Math.E;
            this.one_over_alpha = (double)1.0F / alpha;
            this.two_to_alpha_minus_one = scala.math.package..MODULE$.pow((double)2.0F, alpha - (double)1);
            this.x = (double)0.0F;
         }
      }
   }

   public class GammaQuasiRandomVariableSpecAlphaLeq1$ extends AbstractFunction2 implements Serializable {
      // $FF: synthetic field
      private final ProvidesTransformedQuasiMonteCarlo $outer;

      public final String toString() {
         return "GammaQuasiRandomVariableSpecAlphaLeq1";
      }

      public GammaQuasiRandomVariableSpecAlphaLeq1 apply(final double alpha, final double theta) {
         return this.$outer.new GammaQuasiRandomVariableSpecAlphaLeq1(alpha, theta);
      }

      public Option unapply(final GammaQuasiRandomVariableSpecAlphaLeq1 x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2.mcDD.sp(x$0.alpha(), x$0.theta())));
      }

      public GammaQuasiRandomVariableSpecAlphaLeq1$() {
         if (ProvidesTransformedQuasiMonteCarlo.this == null) {
            throw null;
         } else {
            this.$outer = ProvidesTransformedQuasiMonteCarlo.this;
            super();
         }
      }
   }

   public class GammaQuasiRandomVariableSpecAlphaGeq1 implements RejectionSampledGammaQuasiRandomVariable, Product, Serializable {
      private final double alpha;
      private final double theta;
      private final int numInputs;
      private final double a;
      private final double b;
      private final double c;
      private double x;
      // $FF: synthetic field
      public final ProvidesTransformedQuasiMonteCarlo $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double alpha() {
         return this.alpha;
      }

      public double theta() {
         return this.theta;
      }

      public int numInputs() {
         return this.numInputs;
      }

      private double a() {
         return this.a;
      }

      private double b() {
         return this.b;
      }

      private double c() {
         return this.c;
      }

      private double x() {
         return this.x;
      }

      private void x_$eq(final double x$1) {
         this.x = x$1;
      }

      public boolean accept(final double[] rvs, final int position) {
         double u = rvs[position];
         double v = rvs[position + 1];
         double y = this.a() * scala.math.package..MODULE$.log(u / ((double)1 - u));
         this.x_$eq(this.alpha() * scala.math.package..MODULE$.exp(y));
         double z = u * u * v;
         double r = this.b() + this.c() * y - this.x();
         return r + 2.5040774 - (double)4.5F * z >= (double)0 || r >= scala.math.package..MODULE$.log(z);
      }

      public double compute(final double[] rvs, final int position) {
         return this.theta() * this.x();
      }

      public GammaQuasiRandomVariableSpecAlphaGeq1 copy() {
         return this.breeze$integrate$quasimontecarlo$ProvidesTransformedQuasiMonteCarlo$GammaQuasiRandomVariableSpecAlphaGeq1$$$outer().new GammaQuasiRandomVariableSpecAlphaGeq1(this.alpha(), this.theta());
      }

      public String productPrefix() {
         return "GammaQuasiRandomVariableSpecAlphaGeq1";
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
               var10000 = BoxesRunTime.boxToDouble(this.theta());
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
         return x$1 instanceof GammaQuasiRandomVariableSpecAlphaGeq1;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "alpha";
               break;
            case 1:
               var10000 = "theta";
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
         var1 = Statics.mix(var1, Statics.doubleHash(this.theta()));
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label56: {
               boolean var2;
               if (x$1 instanceof GammaQuasiRandomVariableSpecAlphaGeq1 && ((GammaQuasiRandomVariableSpecAlphaGeq1)x$1).breeze$integrate$quasimontecarlo$ProvidesTransformedQuasiMonteCarlo$GammaQuasiRandomVariableSpecAlphaGeq1$$$outer() == this.breeze$integrate$quasimontecarlo$ProvidesTransformedQuasiMonteCarlo$GammaQuasiRandomVariableSpecAlphaGeq1$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  GammaQuasiRandomVariableSpecAlphaGeq1 var4 = (GammaQuasiRandomVariableSpecAlphaGeq1)x$1;
                  if (this.alpha() == var4.alpha() && this.theta() == var4.theta() && var4.canEqual(this)) {
                     break label56;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      // $FF: synthetic method
      public ProvidesTransformedQuasiMonteCarlo breeze$integrate$quasimontecarlo$ProvidesTransformedQuasiMonteCarlo$GammaQuasiRandomVariableSpecAlphaGeq1$$$outer() {
         return this.$outer;
      }

      public GammaQuasiRandomVariableSpecAlphaGeq1(final double alpha, final double theta) {
         this.alpha = alpha;
         this.theta = theta;
         if (ProvidesTransformedQuasiMonteCarlo.this == null) {
            throw null;
         } else {
            this.$outer = ProvidesTransformedQuasiMonteCarlo.this;
            super();
            Product.$init$(this);
            scala.Predef..MODULE$.require(alpha > (double)1);
            this.numInputs = 2;
            this.a = (double)1.0F / scala.math.package..MODULE$.sqrt((double)2 * alpha - (double)1.0F);
            this.b = alpha - scala.math.package..MODULE$.log((double)4.0F);
            this.c = alpha + (double)1.0F / this.a();
            this.x = (double)0.0F;
         }
      }
   }

   public class GammaQuasiRandomVariableSpecAlphaGeq1$ extends AbstractFunction2 implements Serializable {
      // $FF: synthetic field
      private final ProvidesTransformedQuasiMonteCarlo $outer;

      public final String toString() {
         return "GammaQuasiRandomVariableSpecAlphaGeq1";
      }

      public GammaQuasiRandomVariableSpecAlphaGeq1 apply(final double alpha, final double theta) {
         return this.$outer.new GammaQuasiRandomVariableSpecAlphaGeq1(alpha, theta);
      }

      public Option unapply(final GammaQuasiRandomVariableSpecAlphaGeq1 x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2.mcDD.sp(x$0.alpha(), x$0.theta())));
      }

      public GammaQuasiRandomVariableSpecAlphaGeq1$() {
         if (ProvidesTransformedQuasiMonteCarlo.this == null) {
            throw null;
         } else {
            this.$outer = ProvidesTransformedQuasiMonteCarlo.this;
            super();
         }
      }
   }

   public class TransformedQuasiMonteCarloGenerator implements QuasiMonteCarloGenerator {
      private final List inVariables;
      private final QuasiRandomVariableSpec[] variables;
      private final int dimension;
      private final int inputDimension;
      private final BaseUniformHaltonGenerator baseGenerator;
      private final double[] currentValue;
      private long generatedCount;
      private long[] rejectedCount;
      // $FF: synthetic field
      public final ProvidesTransformedQuasiMonteCarlo $outer;

      public double[] getNext() {
         return QuasiMonteCarloGenerator.getNext$(this);
      }

      public void getNextInto(final double[] to) {
         QuasiMonteCarloGenerator.getNextInto$(this, to);
      }

      public List inVariables() {
         return this.inVariables;
      }

      public QuasiRandomVariableSpec[] variables() {
         return this.variables;
      }

      public int dimension() {
         return this.dimension;
      }

      public int inputDimension() {
         return this.inputDimension;
      }

      private BaseUniformHaltonGenerator baseGenerator() {
         return this.baseGenerator;
      }

      private double[] currentValue() {
         return this.currentValue;
      }

      private long generatedCount() {
         return this.generatedCount;
      }

      private void generatedCount_$eq(final long x$1) {
         this.generatedCount = x$1;
      }

      public long numGenerated() {
         return this.generatedCount();
      }

      private long[] rejectedCount() {
         return this.rejectedCount;
      }

      private void rejectedCount_$eq(final long[] x$1) {
         this.rejectedCount = x$1;
      }

      public long numRejections() {
         return BoxesRunTime.unboxToLong(scala.Predef..MODULE$.wrapLongArray(this.rejectedCount()).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
      }

      public long[] numRejectionsByVariable() {
         return (long[])this.rejectedCount().clone();
      }

      public double[] getNextUnsafe() {
         boolean accepted = false;

         while(!accepted) {
            accepted = true;
            double[] next = this.baseGenerator().getNextUnsafe();
            int inputPosition = 0;

            for(int i = 0; i < this.dimension() && accepted; ++i) {
               QuasiRandomVariableSpec var6 = this.variables()[i];
               if (var6 instanceof TransformingQuasiRandomVariableSpec) {
                  TransformingQuasiRandomVariableSpec var7 = (TransformingQuasiRandomVariableSpec)var6;
                  this.currentValue()[i] = var7.transform(next, inputPosition);
                  BoxedUnit var1 = BoxedUnit.UNIT;
               } else {
                  if (!(var6 instanceof RejectionQuasiRandomVariableSpec)) {
                     throw new MatchError(var6);
                  }

                  RejectionQuasiRandomVariableSpec var8 = (RejectionQuasiRandomVariableSpec)var6;
                  if (var8.accept(next, inputPosition)) {
                     this.currentValue()[i] = var8.compute(next, inputPosition);
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  } else {
                     ++this.rejectedCount()[i];
                     accepted = false;
                     BoxedUnit var9 = BoxedUnit.UNIT;
                  }
               }

               inputPosition += this.variables()[i].numInputs();
            }
         }

         this.generatedCount_$eq(this.generatedCount() + 1L);
         return this.currentValue();
      }

      // $FF: synthetic method
      public ProvidesTransformedQuasiMonteCarlo breeze$integrate$quasimontecarlo$ProvidesTransformedQuasiMonteCarlo$TransformedQuasiMonteCarloGenerator$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final int $anonfun$inputDimension$1(final QuasiRandomVariableSpec x) {
         return x.numInputs();
      }

      public TransformedQuasiMonteCarloGenerator(final List inVariables) {
         this.inVariables = inVariables;
         if (ProvidesTransformedQuasiMonteCarlo.this == null) {
            throw null;
         } else {
            this.$outer = ProvidesTransformedQuasiMonteCarlo.this;
            super();
            QuasiMonteCarloGenerator.$init$(this);
            this.variables = (QuasiRandomVariableSpec[])inVariables.map((x) -> x.copy()).toArray(scala.reflect.ClassTag..MODULE$.apply(QuasiRandomVariableSpec.class));
            this.dimension = scala.collection.ArrayOps..MODULE$.size$extension(scala.Predef..MODULE$.refArrayOps(this.variables()));
            this.inputDimension = BoxesRunTime.unboxToInt(scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.variables()), (x) -> BoxesRunTime.boxToInteger($anonfun$inputDimension$1(x)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
            this.baseGenerator = new BaseUniformHaltonGenerator(this.inputDimension());
            this.currentValue = new double[this.dimension()];
            this.generatedCount = 0L;
            this.rejectedCount = new long[this.dimension()];
         }
      }

      public TransformedQuasiMonteCarloGenerator(final Seq inVariables) {
         this((List)inVariables.toList());
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public interface QuasiRandomVariableSpec {
      int numInputs();

      QuasiRandomVariableSpec copy();
   }

   public interface RejectionQuasiRandomVariableSpec extends QuasiRandomVariableSpec {
      boolean accept(final double[] x, final int position);

      double compute(final double[] x, final int position);
   }

   public interface RejectionSampledGammaQuasiRandomVariable extends RejectionQuasiRandomVariableSpec {
   }

   public interface TransformingQuasiRandomVariableSpec extends QuasiRandomVariableSpec {
      double transform(final double[] x, final int position);
   }
}
