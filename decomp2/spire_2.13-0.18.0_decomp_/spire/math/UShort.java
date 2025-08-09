package spire.math;

import algebra.ring.CommutativeRig;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u001dt!\u0002-Z\u0011\u0003qf!\u00021Z\u0011\u0003\t\u0007\"B6\u0002\t\u0003a\u0007\"B7\u0002\t\u000bq\u0007BB7\u0002\t\u000b\u0011I\u0002\u0003\u0004n\u0003\u0011\u0015!q\u0004\u0005\b\u0005K\tAQAAW\u0011\u001d\u0011I#\u0001C\u0003\u0003[CqA!\f\u0002\t\u000b\u0011y\u0003C\u0004\u00036\u0005!)Aa\u000e\t\u000f\tm\u0012\u0001\"\u0002\u0003>!9!\u0011I\u0001\u0005\u0006\t\r\u0003b\u0002B$\u0003\u0011\u0015!\u0011\n\u0005\b\u0005\u001b\nAQ\u0001B(\u0011\u001d\u0011\u0019&\u0001C\u0003\u0005+BqA!\u0017\u0002\t\u000b\u0011Y\u0006C\u0004\u0003`\u0005!)A!\u0019\t\u000f\t\u0015\u0014\u0001\"\u0002\u0003h!9!1N\u0001\u0005\u0006\t5\u0004b\u0002B9\u0003\u0011\u0015!1\u000f\u0005\b\u0005o\nAQ\u0001B=\u0011\u001d\u0011i(\u0001C\u0003\u0005\u007fBqAa!\u0002\t\u000b\u0011)\tC\u0004\u0003\u000e\u0006!)Aa$\t\u000f\t]\u0015\u0001\"\u0002\u0003\u001a\"9!\u0011U\u0001\u0005\u0006\t\r\u0006b\u0002BV\u0003\u0011\u0015!Q\u0016\u0005\b\u0005k\u000bAQ\u0001B\\\u0011\u001d\u0011y,\u0001C\u0003\u0005\u0003DqA!3\u0002\t\u000b\u0011Y\rC\u0004\u0003T\u0006!)A!6\t\u000f\te\u0017\u0001\"\u0002\u0003\\\"9!1]\u0001\u0005\u0006\t\u0015\bb\u0002Bw\u0003\u0011\u0015!q\u001e\u0005\b\u0005o\fAQ\u0001B}\u0011\u001d\u0019\t!\u0001C\u0003\u0007\u0007Aqaa\u0003\u0002\t\u000b\u0019i\u0001C\u0004\u0004\u0012\u0005!)aa\u0005\t\u000f\rm\u0011\u0001\"\u0002\u0004\u001e!91QE\u0001\u0005\u0006\r\u001d\u0002bBB\u0018\u0003\u0011\u00151\u0011\u0007\u0005\b\u0007s\tAQAB\u001e\u0011\u001d\u0019\u0019%\u0001C\u0003\u0007\u000bBqa!\u0014\u0002\t\u000b\u0019y\u0005C\u0005\u0004X\u0005\t\t\u0011\"\u0002\u0004Z!I1QL\u0001\u0002\u0002\u0013\u00151q\f\u0004\u0005Af\u0013\u0001\u000f\u0003\u0005u]\t\u0015\r\u0011\"\u0001v\u0011!IhF!A!\u0002\u00131\b\"B6/\t\u0003Q\b\"\u0002?/\t\u0003i\bBBA\u0002]\u0011\u0005Q\u000fC\u0004\u0002\u00069\"\t!a\u0002\t\u000f\u0005=a\u0006\"\u0001\u0002\u0012!9\u0011\u0011\u0004\u0018\u0005\u0002\u0005m\u0001bBA\u0012]\u0011\u0005\u0011Q\u0005\u0005\b\u0003[qC\u0011AA\u0018\u0011\u001d\t9D\fC\u0001\u0003sAq!a\u0015/\t\u0003\t)\u0006C\u0004\u0002^9\"\t!!\u0016\t\u000f\u0005}c\u0006\"\u0001\u0002V!9\u0011\u0011\r\u0018\u0005\u0002\u0005U\u0003bBA2]\u0011\u0005\u0011Q\u000b\u0005\b\u0003KrC\u0011IA4\u0011\u001d\tIH\fC\u0001\u0003wBq!!!/\t\u0003\t\u0019\tC\u0004\u0002\b:\"\t!!#\t\u000f\u00055e\u0006\"\u0001\u0002\u0010\"9\u00111\u0013\u0018\u0005\u0002\u0005U\u0005bBAM]\u0011\u0005\u00111\u0014\u0005\b\u0003?sC\u0011AAQ\u0011\u001d\t)K\fC\u0001\u0003OCq!a+/\t\u0003\ti\u000bC\u0004\u00020:\"\t!!-\t\u000f\u0005Uf\u0006\"\u0001\u00028\"9\u00111\u0018\u0018\u0005\u0002\u0005u\u0006bBAa]\u0011\u0005\u00111\u0019\u0005\b\u0003\u000ftC\u0011AAe\u0011\u001d\tiM\fC\u0001\u0003[Cq!a4/\t\u0003\t\t\u000eC\u0004\u0002X:\"\t!!7\t\u000f\u0005ug\u0006\"\u0001\u0002`\"9\u00111\u001d\u0018\u0005\u0002\u0005\u0015\bbBAu]\u0011\u0005\u00111\u001e\u0005\b\u0003_tC\u0011AAy\u0011\u001d\t)P\fC\u0001\u0003oD\u0011\"a?/\u0003\u0003%\t%!@\t\u0013\u0005}h&!A\u0005B\t\u0005\u0011AB+TQ>\u0014HO\u0003\u0002[7\u0006!Q.\u0019;i\u0015\u0005a\u0016!B:qSJ,7\u0001\u0001\t\u0003?\u0006i\u0011!\u0017\u0002\u0007+NCwN\u001d;\u0014\u0007\u0005\u0011\u0007\u000e\u0005\u0002dM6\tAMC\u0001f\u0003\u0015\u00198-\u00197b\u0013\t9GM\u0001\u0004B]f\u0014VM\u001a\t\u0003?&L!A[-\u0003\u001fU\u001b\u0006n\u001c:u\u0013:\u001cH/\u00198dKN\fa\u0001P5oSRtD#\u00010\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007=\u0014i\u0001\u0005\u0002`]M\u0011a&\u001d\t\u0003GJL!a\u001d3\u0003\r\u0005s\u0017PV1m\u0003\u0019\u0019\u0018n\u001a8fIV\ta\u000f\u0005\u0002do&\u0011\u0001\u0010\u001a\u0002\u0005\u0007\"\f'/A\u0004tS\u001etW\r\u001a\u0011\u0015\u0005=\\\b\"\u0002;2\u0001\u00041\u0018A\u0002;p\u0005f$X-F\u0001\u007f!\t\u0019w0C\u0002\u0002\u0002\u0011\u0014AAQ=uK\u00061Ao\\\"iCJ\fq\u0001^8TQ>\u0014H/\u0006\u0002\u0002\nA\u00191-a\u0003\n\u0007\u00055AMA\u0003TQ>\u0014H/A\u0003u_&sG/\u0006\u0002\u0002\u0014A\u00191-!\u0006\n\u0007\u0005]AMA\u0002J]R\fa\u0001^8M_:<WCAA\u000f!\r\u0019\u0017qD\u0005\u0004\u0003C!'\u0001\u0002'p]\u001e\fq\u0001^8GY>\fG/\u0006\u0002\u0002(A\u00191-!\u000b\n\u0007\u0005-BMA\u0003GY>\fG/\u0001\u0005u_\u0012{WO\u00197f+\t\t\t\u0004E\u0002d\u0003gI1!!\u000ee\u0005\u0019!u.\u001e2mK\u0006AAo\u001c\"jO&sG/\u0006\u0002\u0002<A!\u0011QHA'\u001d\u0011\ty$!\u0013\u000f\t\u0005\u0005\u0013qI\u0007\u0003\u0003\u0007R1!!\u0012^\u0003\u0019a$o\\8u}%\tQ-C\u0002\u0002L\u0011\fq\u0001]1dW\u0006<W-\u0003\u0003\u0002P\u0005E#A\u0002\"jO&sGOC\u0002\u0002L\u0011\f1\"[:WC2LGMQ=uKV\u0011\u0011q\u000b\t\u0004G\u0006e\u0013bAA.I\n9!i\\8mK\u0006t\u0017\u0001D5t-\u0006d\u0017\u000eZ*i_J$\u0018aC5t-\u0006d\u0017\u000eZ\"iCJ\f!\"[:WC2LG-\u00138u\u0003-I7OV1mS\u0012duN\\4\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\u001b\u0011\t\u0005-\u00141\u000f\b\u0005\u0003[\ny\u0007E\u0002\u0002B\u0011L1!!\u001de\u0003\u0019\u0001&/\u001a3fM&!\u0011QOA<\u0005\u0019\u0019FO]5oO*\u0019\u0011\u0011\u000f3\u0002\r\u0011*\u0017\u000fJ3r)\u0011\t9&! \t\r\u0005}\u0004\t1\u0001p\u0003\u0011!\b.\u0019;\u0002\u0011\u0011\u0012\u0017M\\4%KF$B!a\u0016\u0002\u0006\"1\u0011qP!A\u0002=\f\u0011\u0002J3rI\u0015\fH%Z9\u0015\t\u0005]\u00131\u0012\u0005\u0007\u0003\u007f\u0012\u0005\u0019A8\u0002\u0017\u0011*\u0017\u000f\n2b]\u001e$S-\u001d\u000b\u0005\u0003/\n\t\n\u0003\u0004\u0002\u0000\r\u0003\ra\\\u0001\tI1,7o\u001d\u0013fcR!\u0011qKAL\u0011\u0019\ty\b\u0012a\u0001_\u0006)A\u0005\\3tgR!\u0011qKAO\u0011\u0019\ty(\u0012a\u0001_\u0006YAe\u001a:fCR,'\u000fJ3r)\u0011\t9&a)\t\r\u0005}d\t1\u0001p\u0003!!sM]3bi\u0016\u0014H\u0003BA,\u0003SCa!a H\u0001\u0004y\u0017\u0001D;oCJLx\fJ7j]V\u001cX#A8\u0002\u000b\u0011\u0002H.^:\u0015\u0007=\f\u0019\f\u0003\u0004\u0002\u0000%\u0003\ra\\\u0001\u0007I5Lg.^:\u0015\u0007=\fI\f\u0003\u0004\u0002\u0000)\u0003\ra\\\u0001\u0007IQLW.Z:\u0015\u0007=\fy\f\u0003\u0004\u0002\u0000-\u0003\ra\\\u0001\u0005I\u0011Lg\u000fF\u0002p\u0003\u000bDa!a M\u0001\u0004y\u0017\u0001\u0003\u0013qKJ\u001cWM\u001c;\u0015\u0007=\fY\r\u0003\u0004\u0002\u00005\u0003\ra\\\u0001\rk:\f'/_0%i&dG-Z\u0001\u000bI1,7o\u001d\u0013mKN\u001cHcA8\u0002T\"9\u0011Q[(A\u0002\u0005M\u0011!B:iS\u001a$\u0018\u0001\u0005\u0013he\u0016\fG/\u001a:%OJ,\u0017\r^3s)\ry\u00171\u001c\u0005\b\u0003+\u0004\u0006\u0019AA\n\u0003a!sM]3bi\u0016\u0014He\u001a:fCR,'\u000fJ4sK\u0006$XM\u001d\u000b\u0004_\u0006\u0005\bbBAk#\u0002\u0007\u00111C\u0001\u0005I\u0005l\u0007\u000fF\u0002p\u0003ODa!a S\u0001\u0004y\u0017\u0001\u0002\u0013cCJ$2a\\Aw\u0011\u0019\tyh\u0015a\u0001_\u0006\u0019A%\u001e9\u0015\u0007=\f\u0019\u0010\u0003\u0004\u0002\u0000Q\u0003\ra\\\u0001\rIQLW.Z:%i&lWm\u001d\u000b\u0004_\u0006e\bBBA@+\u0002\u0007q.\u0001\u0005iCND7i\u001c3f)\t\t\u0019\"\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003/\u0012\u0019\u0001C\u0005\u0003\u0006]\u000b\t\u00111\u0001\u0003\b\u0005\u0019\u0001\u0010J\u0019\u0011\u0007\r\u0014I!C\u0002\u0003\f\u0011\u00141!\u00118z\u0011\u0019\u0011ya\u0001a\u0001m\u0006\ta\u000eK\u0002\u0004\u0005'\u00012a\u0019B\u000b\u0013\r\u00119\u0002\u001a\u0002\u0007S:d\u0017N\\3\u0015\u0007=\u0014Y\u0002C\u0004\u0003\u0010\u0011\u0001\r!!\u0003)\u0007\u0011\u0011\u0019\u0002F\u0002p\u0005CAqAa\u0004\u0006\u0001\u0004\t\u0019\u0002K\u0002\u0006\u0005'\t\u0001\"T5o-\u0006dW/\u001a\u0015\u0004\r\tM\u0011\u0001C'bqZ\u000bG.^3)\u0007\u001d\u0011\u0019\"\u0001\tu_\nKH/\u001a\u0013fqR,gn]5p]R\u0019aP!\r\t\r\tM\u0002\u00021\u0001p\u0003\u0015!C\u000f[5t\u0003A!xn\u00115be\u0012*\u0007\u0010^3og&|g\u000eF\u0002w\u0005sAaAa\r\n\u0001\u0004y\u0017!\u0005;p'\"|'\u000f\u001e\u0013fqR,gn]5p]R!\u0011\u0011\u0002B \u0011\u0019\u0011\u0019D\u0003a\u0001_\u0006yAo\\%oi\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0002\u0014\t\u0015\u0003B\u0002B\u001a\u0017\u0001\u0007q.\u0001\tu_2{gn\u001a\u0013fqR,gn]5p]R!\u0011Q\u0004B&\u0011\u0019\u0011\u0019\u0004\u0004a\u0001_\u0006\tBo\u001c$m_\u0006$H%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005\u001d\"\u0011\u000b\u0005\u0007\u0005gi\u0001\u0019A8\u0002%Q|Gi\\;cY\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0003c\u00119\u0006\u0003\u0004\u000349\u0001\ra\\\u0001\u0013i>\u0014\u0015nZ%oi\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0002<\tu\u0003B\u0002B\u001a\u001f\u0001\u0007q.A\u000bjgZ\u000bG.\u001b3CsR,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005]#1\r\u0005\u0007\u0005g\u0001\u0002\u0019A8\u0002-%\u001ch+\u00197jINCwN\u001d;%Kb$XM\\:j_:$B!a\u0016\u0003j!1!1G\tA\u0002=\fQ#[:WC2LGm\u00115be\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0002X\t=\u0004B\u0002B\u001a%\u0001\u0007q.\u0001\u000bjgZ\u000bG.\u001b3J]R$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0003/\u0012)\b\u0003\u0004\u00034M\u0001\ra\\\u0001\u0016SN4\u0016\r\\5e\u0019>tw\rJ3yi\u0016t7/[8o)\u0011\t9Fa\u001f\t\r\tMB\u00031\u0001p\u0003I!xn\u0015;sS:<G%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005\u001d$\u0011\u0011\u0005\u0007\u0005g)\u0002\u0019A8\u0002!\u0011*\u0017\u000fJ3rI\u0015DH/\u001a8tS>tG\u0003\u0002BD\u0005\u0017#B!a\u0016\u0003\n\"1\u0011q\u0010\fA\u0002=DaAa\r\u0017\u0001\u0004y\u0017A\u0005\u0013cC:<G%Z9%Kb$XM\\:j_:$BA!%\u0003\u0016R!\u0011q\u000bBJ\u0011\u0019\tyh\u0006a\u0001_\"1!1G\fA\u0002=\f1\u0003J3rI\u0015\fH%Z9%Kb$XM\\:j_:$BAa'\u0003 R!\u0011q\u000bBO\u0011\u0019\ty\b\u0007a\u0001_\"1!1\u0007\rA\u0002=\fQ\u0003J3rI\t\fgn\u001a\u0013fc\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0003&\n%F\u0003BA,\u0005OCa!a \u001a\u0001\u0004y\u0007B\u0002B\u001a3\u0001\u0007q.\u0001\n%Y\u0016\u001c8\u000fJ3rI\u0015DH/\u001a8tS>tG\u0003\u0002BX\u0005g#B!a\u0016\u00032\"1\u0011q\u0010\u000eA\u0002=DaAa\r\u001b\u0001\u0004y\u0017a\u0004\u0013mKN\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\te&Q\u0018\u000b\u0005\u0003/\u0012Y\f\u0003\u0004\u0002\u0000m\u0001\ra\u001c\u0005\u0007\u0005gY\u0002\u0019A8\u0002+\u0011:'/Z1uKJ$S-\u001d\u0013fqR,gn]5p]R!!1\u0019Bd)\u0011\t9F!2\t\r\u0005}D\u00041\u0001p\u0011\u0019\u0011\u0019\u0004\ba\u0001_\u0006\u0011Be\u001a:fCR,'\u000fJ3yi\u0016t7/[8o)\u0011\u0011iM!5\u0015\t\u0005]#q\u001a\u0005\u0007\u0003\u007fj\u0002\u0019A8\t\r\tMR\u00041\u0001p\u0003Y)h.\u0019:z?\u0012j\u0017N\\;tI\u0015DH/\u001a8tS>tGcA8\u0003X\"1!1\u0007\u0010A\u0002=\fq\u0002\n9mkN$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0005;\u0014\t\u000fF\u0002p\u0005?Da!a  \u0001\u0004y\u0007B\u0002B\u001a?\u0001\u0007q.\u0001\t%[&tWo\u001d\u0013fqR,gn]5p]R!!q\u001dBv)\ry'\u0011\u001e\u0005\u0007\u0003\u007f\u0002\u0003\u0019A8\t\r\tM\u0002\u00051\u0001p\u0003A!C/[7fg\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0003r\nUHcA8\u0003t\"1\u0011qP\u0011A\u0002=DaAa\r\"\u0001\u0004y\u0017A\u0004\u0013eSZ$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0005w\u0014y\u0010F\u0002p\u0005{Da!a #\u0001\u0004y\u0007B\u0002B\u001aE\u0001\u0007q.\u0001\n%a\u0016\u00148-\u001a8uI\u0015DH/\u001a8tS>tG\u0003BB\u0003\u0007\u0013!2a\\B\u0004\u0011\u0019\tyh\ta\u0001_\"1!1G\u0012A\u0002=\fa#\u001e8bef|F\u0005^5mI\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0004_\u000e=\u0001B\u0002B\u001aI\u0001\u0007q.\u0001\u000b%Y\u0016\u001c8\u000f\n7fgN$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0007+\u0019I\u0002F\u0002p\u0007/Aq!!6&\u0001\u0004\t\u0019\u0002\u0003\u0004\u00034\u0015\u0002\ra\\\u0001\u001bI\u001d\u0014X-\u0019;fe\u0012:'/Z1uKJ$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0007?\u0019\u0019\u0003F\u0002p\u0007CAq!!6'\u0001\u0004\t\u0019\u0002\u0003\u0004\u00034\u0019\u0002\ra\\\u0001#I\u001d\u0014X-\u0019;fe\u0012:'/Z1uKJ$sM]3bi\u0016\u0014H%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\r%2Q\u0006\u000b\u0004_\u000e-\u0002bBAkO\u0001\u0007\u00111\u0003\u0005\u0007\u0005g9\u0003\u0019A8\u0002\u001d\u0011\nW\u000e\u001d\u0013fqR,gn]5p]R!11GB\u001c)\ry7Q\u0007\u0005\u0007\u0003\u007fB\u0003\u0019A8\t\r\tM\u0002\u00061\u0001p\u00039!#-\u0019:%Kb$XM\\:j_:$Ba!\u0010\u0004BQ\u0019qna\u0010\t\r\u0005}\u0014\u00061\u0001p\u0011\u0019\u0011\u0019$\u000ba\u0001_\u0006iA%\u001e9%Kb$XM\\:j_:$Baa\u0012\u0004LQ\u0019qn!\u0013\t\r\u0005}$\u00061\u0001p\u0011\u0019\u0011\u0019D\u000ba\u0001_\u00061B\u0005^5nKN$C/[7fg\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0004R\rUCcA8\u0004T!1\u0011qP\u0016A\u0002=DaAa\r,\u0001\u0004y\u0017A\u00055bg\"\u001cu\u000eZ3%Kb$XM\\:j_:$B!!@\u0004\\!1!1\u0007\u0017A\u0002=\f\u0001#Z9vC2\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\r\u00054Q\r\u000b\u0005\u0003/\u001a\u0019\u0007C\u0005\u0003\u00065\n\t\u00111\u0001\u0003\b!1!1G\u0017A\u0002=\u0004"
)
public final class UShort {
   private final char signed;

   public static boolean equals$extension(final char $this, final Object x$1) {
      return UShort$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final char $this) {
      return UShort$.MODULE$.hashCode$extension($this);
   }

   public static char $times$times$extension(final char $this, final char that) {
      return UShort$.MODULE$.$times$times$extension($this, that);
   }

   public static char $up$extension(final char $this, final char that) {
      return UShort$.MODULE$.$up$extension($this, that);
   }

   public static char $bar$extension(final char $this, final char that) {
      return UShort$.MODULE$.$bar$extension($this, that);
   }

   public static char $amp$extension(final char $this, final char that) {
      return UShort$.MODULE$.$amp$extension($this, that);
   }

   public static char $greater$greater$greater$extension(final char $this, final int shift) {
      return UShort$.MODULE$.$greater$greater$greater$extension($this, shift);
   }

   public static char $greater$greater$extension(final char $this, final int shift) {
      return UShort$.MODULE$.$greater$greater$extension($this, shift);
   }

   public static char $less$less$extension(final char $this, final int shift) {
      return UShort$.MODULE$.$less$less$extension($this, shift);
   }

   public static char unary_$tilde$extension(final char $this) {
      return UShort$.MODULE$.unary_$tilde$extension($this);
   }

   public static char $percent$extension(final char $this, final char that) {
      return UShort$.MODULE$.$percent$extension($this, that);
   }

   public static char $div$extension(final char $this, final char that) {
      return UShort$.MODULE$.$div$extension($this, that);
   }

   public static char $times$extension(final char $this, final char that) {
      return UShort$.MODULE$.$times$extension($this, that);
   }

   public static char $minus$extension(final char $this, final char that) {
      return UShort$.MODULE$.$minus$extension($this, that);
   }

   public static char $plus$extension(final char $this, final char that) {
      return UShort$.MODULE$.$plus$extension($this, that);
   }

   public static char unary_$minus$extension(final char $this) {
      return UShort$.MODULE$.unary_$minus$extension($this);
   }

   public static boolean $greater$extension(final char $this, final char that) {
      return UShort$.MODULE$.$greater$extension($this, that);
   }

   public static boolean $greater$eq$extension(final char $this, final char that) {
      return UShort$.MODULE$.$greater$eq$extension($this, that);
   }

   public static boolean $less$extension(final char $this, final char that) {
      return UShort$.MODULE$.$less$extension($this, that);
   }

   public static boolean $less$eq$extension(final char $this, final char that) {
      return UShort$.MODULE$.$less$eq$extension($this, that);
   }

   public static boolean $eq$bang$eq$extension(final char $this, final char that) {
      return UShort$.MODULE$.$eq$bang$eq$extension($this, that);
   }

   public static boolean $eq$eq$eq$extension(final char $this, final char that) {
      return UShort$.MODULE$.$eq$eq$eq$extension($this, that);
   }

   public static boolean $bang$eq$extension(final char $this, final char that) {
      return UShort$.MODULE$.$bang$eq$extension($this, that);
   }

   public static boolean $eq$eq$extension(final char $this, final char that) {
      return UShort$.MODULE$.$eq$eq$extension($this, that);
   }

   public static String toString$extension(final char $this) {
      return UShort$.MODULE$.toString$extension($this);
   }

   public static boolean isValidLong$extension(final char $this) {
      return UShort$.MODULE$.isValidLong$extension($this);
   }

   public static boolean isValidInt$extension(final char $this) {
      return UShort$.MODULE$.isValidInt$extension($this);
   }

   public static boolean isValidChar$extension(final char $this) {
      return UShort$.MODULE$.isValidChar$extension($this);
   }

   public static boolean isValidShort$extension(final char $this) {
      return UShort$.MODULE$.isValidShort$extension($this);
   }

   public static boolean isValidByte$extension(final char $this) {
      return UShort$.MODULE$.isValidByte$extension($this);
   }

   public static BigInt toBigInt$extension(final char $this) {
      return UShort$.MODULE$.toBigInt$extension($this);
   }

   public static double toDouble$extension(final char $this) {
      return UShort$.MODULE$.toDouble$extension($this);
   }

   public static float toFloat$extension(final char $this) {
      return UShort$.MODULE$.toFloat$extension($this);
   }

   public static long toLong$extension(final char $this) {
      return UShort$.MODULE$.toLong$extension($this);
   }

   public static int toInt$extension(final char $this) {
      return UShort$.MODULE$.toInt$extension($this);
   }

   public static short toShort$extension(final char $this) {
      return UShort$.MODULE$.toShort$extension($this);
   }

   public static char toChar$extension(final char $this) {
      return UShort$.MODULE$.toChar$extension($this);
   }

   public static byte toByte$extension(final char $this) {
      return UShort$.MODULE$.toByte$extension($this);
   }

   public static char MaxValue() {
      return UShort$.MODULE$.MaxValue();
   }

   public static char MinValue() {
      return UShort$.MODULE$.MinValue();
   }

   public static char apply(final int n) {
      return UShort$.MODULE$.apply(n);
   }

   public static char apply(final short n) {
      return UShort$.MODULE$.apply(n);
   }

   public static char apply(final char n) {
      return UShort$.MODULE$.apply(n);
   }

   public static NumberTag UShortTag() {
      return UShort$.MODULE$.UShortTag();
   }

   public static BitString UShortBitString() {
      return UShort$.MODULE$.UShortBitString();
   }

   public static CommutativeRig UShortAlgebra() {
      return UShort$.MODULE$.UShortAlgebra();
   }

   public char signed() {
      return this.signed;
   }

   public byte toByte() {
      return UShort$.MODULE$.toByte$extension(this.signed());
   }

   public char toChar() {
      return UShort$.MODULE$.toChar$extension(this.signed());
   }

   public short toShort() {
      return UShort$.MODULE$.toShort$extension(this.signed());
   }

   public int toInt() {
      return UShort$.MODULE$.toInt$extension(this.signed());
   }

   public long toLong() {
      return UShort$.MODULE$.toLong$extension(this.signed());
   }

   public float toFloat() {
      return UShort$.MODULE$.toFloat$extension(this.signed());
   }

   public double toDouble() {
      return UShort$.MODULE$.toDouble$extension(this.signed());
   }

   public BigInt toBigInt() {
      return UShort$.MODULE$.toBigInt$extension(this.signed());
   }

   public boolean isValidByte() {
      return UShort$.MODULE$.isValidByte$extension(this.signed());
   }

   public boolean isValidShort() {
      return UShort$.MODULE$.isValidShort$extension(this.signed());
   }

   public boolean isValidChar() {
      return UShort$.MODULE$.isValidChar$extension(this.signed());
   }

   public boolean isValidInt() {
      return UShort$.MODULE$.isValidInt$extension(this.signed());
   }

   public boolean isValidLong() {
      return UShort$.MODULE$.isValidLong$extension(this.signed());
   }

   public String toString() {
      return UShort$.MODULE$.toString$extension(this.signed());
   }

   public boolean $eq$eq(final char that) {
      return UShort$.MODULE$.$eq$eq$extension(this.signed(), that);
   }

   public boolean $bang$eq(final char that) {
      return UShort$.MODULE$.$bang$eq$extension(this.signed(), that);
   }

   public boolean $eq$eq$eq(final char that) {
      return UShort$.MODULE$.$eq$eq$eq$extension(this.signed(), that);
   }

   public boolean $eq$bang$eq(final char that) {
      return UShort$.MODULE$.$eq$bang$eq$extension(this.signed(), that);
   }

   public boolean $less$eq(final char that) {
      return UShort$.MODULE$.$less$eq$extension(this.signed(), that);
   }

   public boolean $less(final char that) {
      return UShort$.MODULE$.$less$extension(this.signed(), that);
   }

   public boolean $greater$eq(final char that) {
      return UShort$.MODULE$.$greater$eq$extension(this.signed(), that);
   }

   public boolean $greater(final char that) {
      return UShort$.MODULE$.$greater$extension(this.signed(), that);
   }

   public char unary_$minus() {
      return UShort$.MODULE$.unary_$minus$extension(this.signed());
   }

   public char $plus(final char that) {
      return UShort$.MODULE$.$plus$extension(this.signed(), that);
   }

   public char $minus(final char that) {
      return UShort$.MODULE$.$minus$extension(this.signed(), that);
   }

   public char $times(final char that) {
      return UShort$.MODULE$.$times$extension(this.signed(), that);
   }

   public char $div(final char that) {
      return UShort$.MODULE$.$div$extension(this.signed(), that);
   }

   public char $percent(final char that) {
      return UShort$.MODULE$.$percent$extension(this.signed(), that);
   }

   public char unary_$tilde() {
      return UShort$.MODULE$.unary_$tilde$extension(this.signed());
   }

   public char $less$less(final int shift) {
      return UShort$.MODULE$.$less$less$extension(this.signed(), shift);
   }

   public char $greater$greater(final int shift) {
      return UShort$.MODULE$.$greater$greater$extension(this.signed(), shift);
   }

   public char $greater$greater$greater(final int shift) {
      return UShort$.MODULE$.$greater$greater$greater$extension(this.signed(), shift);
   }

   public char $amp(final char that) {
      return UShort$.MODULE$.$amp$extension(this.signed(), that);
   }

   public char $bar(final char that) {
      return UShort$.MODULE$.$bar$extension(this.signed(), that);
   }

   public char $up(final char that) {
      return UShort$.MODULE$.$up$extension(this.signed(), that);
   }

   public char $times$times(final char that) {
      return UShort$.MODULE$.$times$times$extension(this.signed(), that);
   }

   public int hashCode() {
      return UShort$.MODULE$.hashCode$extension(this.signed());
   }

   public boolean equals(final Object x$1) {
      return UShort$.MODULE$.equals$extension(this.signed(), x$1);
   }

   public UShort(final char signed) {
      this.signed = signed;
   }
}
