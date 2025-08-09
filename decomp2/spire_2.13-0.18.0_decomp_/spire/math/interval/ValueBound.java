package spire.math.interval;

import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.MultiplicativeGroup;
import algebra.ring.MultiplicativeSemigroup;
import cats.kernel.Order;
import scala.Option;
import scala.reflect.ScalaSignature;
import spire.math.Interval;

@ScalaSignature(
   bytes = "\u0006\u0005\r]eaB\u0011#!\u0003\r\t#\u000b\u0005\u0006\u0001\u0002!\t!\u0011\u0005\u0006\u000b\u00021\tA\u0012\u0005\u0006\u000f\u00021\t\u0001\u0013\u0005\u0006\u0019\u0002!\t%\u0014\u0005\u0006C\u0002!\tE\u0019\u0005\u0006O\u0002!\t\u0001\u001b\u0005\u0006a\u0002!\t!\u001d\u0005\u0006k\u0002!\tA\u001e\u0005\u0006{\u0002!\tA`\u0004\b\u0003\u001f\u0011\u0003\u0012AA\t\r\u0019\t#\u0005#\u0001\u0002\u0014!9\u0011QC\u0006\u0005\u0002\u0005]\u0001bBA\r\u0017\u0011\u0005\u00111\u0004\u0005\b\u0003_YA\u0011AA\u0019\u0011\u001d\tyf\u0003C\u0001\u0003CBq!a \f\t\u0003\t\t\tC\u0004\u0002\u001e.!\t!a(\t\u000f\u0005m6\u0002\"\u0001\u0002>\"9\u0011\u0011\\\u0006\u0005\u0002\u0005m\u0007bBA|\u0017\u0011\u0005\u0011\u0011 \u0005\b\u0005+YA\u0011\u0001B\f\u0011\u001d\u00119d\u0003C\u0001\u0005sAqAa\u0016\f\t\u0003\u0011I\u0006C\u0004\u0003x-!\tA!\u001f\t\u000f\t]5\u0002\"\u0001\u0003\u001a\"9!qW\u0006\u0005\u0002\te\u0006b\u0002Bl\u0017\u0011\u0005!\u0011\u001c\u0005\b\u0005o\\A\u0011\u0001B}\u0011\u001d\u00199b\u0003C\u0001\u00073Aqaa\u000e\f\t\u0003\u0019I\u0004C\u0004\u0004X-!\ta!\u0017\t\u000f\r]4\u0002\"\u0001\u0004z\tQa+\u00197vK\n{WO\u001c3\u000b\u0005\r\"\u0013\u0001C5oi\u0016\u0014h/\u00197\u000b\u0005\u00152\u0013\u0001B7bi\"T\u0011aJ\u0001\u0006gBL'/Z\u0002\u0001+\tQsgE\u0002\u0001WE\u0002\"\u0001L\u0018\u000e\u00035R\u0011AL\u0001\u0006g\u000e\fG.Y\u0005\u0003a5\u0012a!\u00118z%\u00164\u0007c\u0001\u001a4k5\t!%\u0003\u00025E\t)!i\\;oIB\u0011ag\u000e\u0007\u0001\t\u0015A\u0004A1\u0001:\u0005\u0005\t\u0015C\u0001\u001e>!\ta3(\u0003\u0002=[\t9aj\u001c;iS:<\u0007C\u0001\u0017?\u0013\tyTFA\u0002B]f\fa\u0001J5oSR$C#\u0001\"\u0011\u00051\u001a\u0015B\u0001#.\u0005\u0011)f.\u001b;\u0002\u0003\u0005,\u0012!N\u0001\tSN\u001cEn\\:fIV\t\u0011\n\u0005\u0002-\u0015&\u00111*\f\u0002\b\u0005>|G.Z1o\u00031)h.\u0019:z?\u0012j\u0017N\\;t)\tqu\nE\u00023\u0001UBQ\u0001\u0015\u0003A\u0004E\u000b!!\u001a<\u0011\u0007IsVG\u0004\u0002T7:\u0011A+\u0017\b\u0003+bk\u0011A\u0016\u0006\u0003/\"\na\u0001\u0010:p_Rt\u0014\"A\u0014\n\u0005i3\u0013aB1mO\u0016\u0014'/Y\u0005\u00039v\u000bq\u0001]1dW\u0006<WM\u0003\u0002[M%\u0011q\f\u0019\u0002\u000e\u0003\u0012$\u0017\u000e^5wK\u001e\u0013x.\u001e9\u000b\u0005qk\u0016A\u0003:fG&\u0004(o\\2bYR\u0011aj\u0019\u0005\u0006!\u0016\u0001\u001d\u0001\u001a\t\u0004%\u0016,\u0014B\u00014a\u0005MiU\u000f\u001c;ja2L7-\u0019;jm\u0016<%o\\;q\u0003-!\u0003\u000f\\;tIQLG\u000eZ3\u0015\u0005%tGC\u0001(k\u0011\u0015\u0001f\u0001q\u0001l!\r\u0011F.N\u0005\u0003[\u0002\u0014\u0011#\u00113eSRLg/Z*f[&<'o\\;q\u0011\u0015yg\u00011\u0001O\u0003\r\u0011\bn]\u0001\rI5Lg.^:%i&dG-\u001a\u000b\u0003eR$\"AT:\t\u000bA;\u00019A)\t\u000b=<\u0001\u0019\u0001(\u0002\u0019\u0011\"\u0018.\\3tIQLG\u000eZ3\u0015\u0005]dHC\u0001(y\u0011\u0015\u0001\u0006\u0002q\u0001z!\r\u0011&0N\u0005\u0003w\u0002\u0014q#T;mi&\u0004H.[2bi&4XmU3nS\u001e\u0014x.\u001e9\t\u000b=D\u0001\u0019\u0001(\u0002\u0015\u0011\"\u0017N\u001e\u0013uS2$W\rF\u0002\u0000\u0003\u0007!2ATA\u0001\u0011\u0015\u0001\u0016\u0002q\u0001e\u0011\u0015y\u0017\u00021\u0001OS\u0015\u0001\u0011qAA\u0006\u0013\r\tIA\t\u0002\u0007\u00072|7/\u001a3\n\u0007\u00055!E\u0001\u0003Pa\u0016t\u0017A\u0003,bYV,'i\\;oIB\u0011!gC\n\u0003\u0017-\na\u0001P5oSRtDCAA\t\u0003\u001d)h.\u00199qYf,B!!\b\u0002(Q!\u0011qDA\u0015!\u0015a\u0013\u0011EA\u0013\u0013\r\t\u0019#\f\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0007Y\n9\u0003B\u00039\u001b\t\u0007\u0011\bC\u0004\u0002,5\u0001\r!!\f\u0002\u0003\t\u0004BAM\u001a\u0002&\u00051QO\\5p]J*B!a\r\u0002BQ1\u0011QGA'\u0003'\"B!a\u000e\u0002DA1\u0011\u0011HA\u001e\u0003\u007fi\u0011\u0001J\u0005\u0004\u0003{!#\u0001C%oi\u0016\u0014h/\u00197\u0011\u0007Y\n\t\u0005B\u00039\u001d\t\u0007\u0011\bC\u0005\u0002F9\t\t\u0011q\u0001\u0002H\u0005QQM^5eK:\u001cW\rJ\u001b\u0011\u000bI\u000bI%a\u0010\n\u0007\u0005-\u0003MA\u0003Pe\u0012,'\u000fC\u0004\u0002P9\u0001\r!!\u0015\u0002\u0005Y\f\u0004\u0003\u0002\u001a\u0001\u0003\u007fAq!!\u0016\u000f\u0001\u0004\t\t&\u0001\u0002we!\u001aa\"!\u0017\u0011\u00071\nY&C\u0002\u0002^5\u0012a!\u001b8mS:,\u0017AF;oS>t7gX\u0019baB\u0014x\u000e\u001f\u001a`e1,7o]\u001a\u0016\t\u0005\r\u00141\u000e\u000b\t\u0003K\n\u0019(a\u001e\u0002zQ!\u0011qMA7!\u0019\tI$a\u000f\u0002jA\u0019a'a\u001b\u0005\u000baz!\u0019A\u001d\t\u0013\u0005=t\"!AA\u0004\u0005E\u0014AC3wS\u0012,gnY3%mA)!+!\u0013\u0002j!9\u0011qJ\bA\u0002\u0005U\u0004\u0003\u0002\u001a\u0001\u0003SBq!!\u0016\u0010\u0001\u0004\t)\bC\u0004\u0002|=\u0001\r!!\u001e\u0002\u0005Y\u001c\u0004fA\b\u0002Z\u00051RO\\5p]Nz\u0016\u0007\\3tgJz&'\u00199qe>D8'\u0006\u0003\u0002\u0004\u0006-E\u0003CAC\u0003'\u000b9*!'\u0015\t\u0005\u001d\u0015Q\u0012\t\u0007\u0003s\tY$!#\u0011\u0007Y\nY\tB\u00039!\t\u0007\u0011\bC\u0005\u0002\u0010B\t\t\u0011q\u0001\u0002\u0012\u0006QQM^5eK:\u001cW\rJ\u001c\u0011\u000bI\u000bI%!#\t\u000f\u0005=\u0003\u00031\u0001\u0002\u0016B!!\u0007AAE\u0011\u001d\t)\u0006\u0005a\u0001\u0003+Cq!a\u001f\u0011\u0001\u0004\t)\nK\u0002\u0011\u00033\n\u0001$\u001e8j_:\u001ct,M1qaJ|\u0007PM03CB\u0004(o\u001c=4+\u0011\t\t+!+\u0015\u0011\u0005\r\u0016\u0011WA[\u0003o#B!!*\u0002,B1\u0011\u0011HA\u001e\u0003O\u00032ANAU\t\u0015A\u0014C1\u0001:\u0011%\ti+EA\u0001\u0002\b\ty+\u0001\u0006fm&$WM\\2fIa\u0002RAUA%\u0003OCq!a\u0014\u0012\u0001\u0004\t\u0019\f\u0005\u00033\u0001\u0005\u001d\u0006bBA+#\u0001\u0007\u00111\u0017\u0005\b\u0003w\n\u0002\u0019AAZQ\r\t\u0012\u0011L\u0001\u0010k:LwN\\\u001a`c\u0005\u0004\bO]8yeU!\u0011qXAd)!\t\t-a4\u0002T\u0006UG\u0003BAb\u0003\u0013\u0004b!!\u000f\u0002<\u0005\u0015\u0007c\u0001\u001c\u0002H\u0012)\u0001H\u0005b\u0001s!I\u00111\u001a\n\u0002\u0002\u0003\u000f\u0011QZ\u0001\u000bKZLG-\u001a8dK\u0012J\u0004#\u0002*\u0002J\u0005\u0015\u0007bBA(%\u0001\u0007\u0011\u0011\u001b\t\u0005e\u0001\t)\rC\u0004\u0002VI\u0001\r!!5\t\u000f\u0005m$\u00031\u0001\u0002R\"\u001a!#!\u0017\u0002\u001bUt\u0017n\u001c84?FbWm]:3+\u0011\ti.!:\u0015\u0011\u0005}\u0017Q^Ay\u0003g$B!!9\u0002hB1\u0011\u0011HA\u001e\u0003G\u00042ANAs\t\u0015A4C1\u0001:\u0011%\tIoEA\u0001\u0002\b\tY/A\u0006fm&$WM\\2fIE\u0002\u0004#\u0002*\u0002J\u0005\r\bbBA('\u0001\u0007\u0011q\u001e\t\u0005e\u0001\t\u0019\u000fC\u0004\u0002VM\u0001\r!a<\t\u000f\u0005m4\u00031\u0001\u0002p\"\u001a1#!\u0017\u0002\rUt\u0017n\u001c84+\u0011\tYPa\u0001\u0015\u0011\u0005u(1\u0002B\b\u0005#!B!a@\u0003\u0006A1\u0011\u0011HA\u001e\u0005\u0003\u00012A\u000eB\u0002\t\u0015ADC1\u0001:\u0011%\u00119\u0001FA\u0001\u0002\b\u0011I!A\u0006fm&$WM\\2fIE\n\u0004#\u0002*\u0002J\t\u0005\u0001bBA()\u0001\u0007!Q\u0002\t\u0005e\u0001\u0011\t\u0001C\u0004\u0002VQ\u0001\rA!\u0004\t\u000f\u0005mD\u00031\u0001\u0003\u000e!\u001aA#!\u0017\u0002?Ut\u0017n\u001c85?F\n\u0007\u000f\u001d:pqJz&'\u00199qe>D8gX\u001amKN\u001cH'\u0006\u0003\u0003\u001a\t\u0005BC\u0003B\u000e\u0005S\u0011iCa\f\u00032Q!!Q\u0004B\u0012!\u0019\tI$a\u000f\u0003 A\u0019aG!\t\u0005\u000ba*\"\u0019A\u001d\t\u0013\t\u0015R#!AA\u0004\t\u001d\u0012aC3wS\u0012,gnY3%cI\u0002RAUA%\u0005?Aq!a\u0014\u0016\u0001\u0004\u0011Y\u0003\u0005\u00033\u0001\t}\u0001bBA++\u0001\u0007!1\u0006\u0005\b\u0003w*\u0002\u0019\u0001B\u0016\u0011\u001d\u0011\u0019$\u0006a\u0001\u0005W\t!A\u001e\u001b)\u0007U\tI&A\u0010v]&|g\u000eN02CB\u0004(o\u001c=3?JbWm]:4?N\n\u0007\u000f\u001d:pqR*BAa\u000f\u0003DQQ!Q\bB&\u0005\u001f\u0012\tFa\u0015\u0015\t\t}\"Q\t\t\u0007\u0003s\tYD!\u0011\u0011\u0007Y\u0012\u0019\u0005B\u00039-\t\u0007\u0011\bC\u0005\u0003HY\t\t\u0011q\u0001\u0003J\u0005YQM^5eK:\u001cW\rJ\u00194!\u0015\u0011\u0016\u0011\nB!\u0011\u001d\tyE\u0006a\u0001\u0005\u001b\u0002BA\r\u0001\u0003B!9\u0011Q\u000b\fA\u0002\t5\u0003bBA>-\u0001\u0007!Q\n\u0005\b\u0005g1\u0002\u0019\u0001B'Q\r1\u0012\u0011L\u0001 k:LwN\u001c\u001b`c1,7o\u001d\u001a`e\u0005\u0004\bO]8yg}\u001b\u0014\r\u001d9s_b$T\u0003\u0002B.\u0005G\"\"B!\u0018\u0003l\t=$\u0011\u000fB:)\u0011\u0011yF!\u001a\u0011\r\u0005e\u00121\bB1!\r1$1\r\u0003\u0006q]\u0011\r!\u000f\u0005\n\u0005O:\u0012\u0011!a\u0002\u0005S\n1\"\u001a<jI\u0016t7-\u001a\u00132iA)!+!\u0013\u0003b!9\u0011qJ\fA\u0002\t5\u0004\u0003\u0002\u001a\u0001\u0005CBq!!\u0016\u0018\u0001\u0004\u0011i\u0007C\u0004\u0002|]\u0001\rA!\u001c\t\u000f\tMr\u00031\u0001\u0003n!\u001aq#!\u0017\u0002;Ut\u0017n\u001c85?FbWm]:4?JbWm]:4?N\n\u0007\u000f\u001d:pqR*BAa\u001f\u0003\u0004RQ!Q\u0010BF\u0005\u001f\u0013\tJa%\u0015\t\t}$Q\u0011\t\u0007\u0003s\tYD!!\u0011\u0007Y\u0012\u0019\tB\u000391\t\u0007\u0011\bC\u0005\u0003\bb\t\t\u0011q\u0001\u0003\n\u0006YQM^5eK:\u001cW\rJ\u00196!\u0015\u0011\u0016\u0011\nBA\u0011\u001d\ty\u0005\u0007a\u0001\u0005\u001b\u0003BA\r\u0001\u0003\u0002\"9\u0011Q\u000b\rA\u0002\t5\u0005bBA>1\u0001\u0007!Q\u0012\u0005\b\u0005gA\u0002\u0019\u0001BGQ\rA\u0012\u0011L\u0001\u0017k:LwN\u001c\u001b`c1,7o\u001d\u001a`e\u0005\u0004\bO]8ygU!!1\u0014BR))\u0011iJa+\u00030\nE&1\u0017\u000b\u0005\u0005?\u0013)\u000b\u0005\u0004\u0002:\u0005m\"\u0011\u0015\t\u0004m\t\rF!\u0002\u001d\u001a\u0005\u0004I\u0004\"\u0003BT3\u0005\u0005\t9\u0001BU\u0003-)g/\u001b3f]\u000e,G%\r\u001c\u0011\u000bI\u000bIE!)\t\u000f\u0005=\u0013\u00041\u0001\u0003.B!!\u0007\u0001BQ\u0011\u001d\t)&\u0007a\u0001\u0005[Cq!a\u001f\u001a\u0001\u0004\u0011i\u000bC\u0004\u00034e\u0001\rA!,)\u0007e\tI&\u0001\fv]&|g\u000eN02CB\u0004(o\u001c=3?JbWm]:4+\u0011\u0011YLa1\u0015\u0015\tu&1\u001aBh\u0005#\u0014\u0019\u000e\u0006\u0003\u0003@\n\u0015\u0007CBA\u001d\u0003w\u0011\t\rE\u00027\u0005\u0007$Q\u0001\u000f\u000eC\u0002eB\u0011Ba2\u001b\u0003\u0003\u0005\u001dA!3\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013g\u000e\t\u0006%\u0006%#\u0011\u0019\u0005\b\u0003\u001fR\u0002\u0019\u0001Bg!\u0011\u0011\u0004A!1\t\u000f\u0005U#\u00041\u0001\u0003N\"9\u00111\u0010\u000eA\u0002\t5\u0007b\u0002B\u001a5\u0001\u0007!Q\u001a\u0015\u00045\u0005e\u0013\u0001G;oS>tGgX\u0019baB\u0014x\u000e\u001f\u001a`e\u0005\u0004\bO]8ygU!!1\u001cBr))\u0011iNa;\u0003p\nE(1\u001f\u000b\u0005\u0005?\u0014)\u000f\u0005\u0004\u0002:\u0005m\"\u0011\u001d\t\u0004m\t\rH!\u0002\u001d\u001c\u0005\u0004I\u0004\"\u0003Bt7\u0005\u0005\t9\u0001Bu\u0003-)g/\u001b3f]\u000e,G%\r\u001d\u0011\u000bI\u000bIE!9\t\u000f\u0005=3\u00041\u0001\u0003nB!!\u0007\u0001Bq\u0011\u001d\t)f\u0007a\u0001\u0005[Dq!a\u001f\u001c\u0001\u0004\u0011i\u000fC\u0004\u00034m\u0001\rA!<)\u0007m\tI&A\bv]&|g\u000eN02CB\u0004(o\u001c=3+\u0011\u0011Ypa\u0001\u0015\u0015\tu81BB\b\u0007#\u0019\u0019\u0002\u0006\u0003\u0003\u0000\u000e\u0015\u0001CBA\u001d\u0003w\u0019\t\u0001E\u00027\u0007\u0007!Q\u0001\u000f\u000fC\u0002eB\u0011ba\u0002\u001d\u0003\u0003\u0005\u001da!\u0003\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013'\u000f\t\u0006%\u0006%3\u0011\u0001\u0005\b\u0003\u001fb\u0002\u0019AB\u0007!\u0011\u0011\u0004a!\u0001\t\u000f\u0005UC\u00041\u0001\u0004\u000e!9\u00111\u0010\u000fA\u0002\r5\u0001b\u0002B\u001a9\u0001\u00071Q\u0002\u0015\u00049\u0005e\u0013\u0001F;oS>tGgX\u0019mKN\u001c(gX\u0019mKN\u001c8'\u0006\u0003\u0004\u001c\r\rBCCB\u000f\u0007W\u0019yc!\r\u00044Q!1qDB\u0013!\u0019\tI$a\u000f\u0004\"A\u0019aga\t\u0005\u000baj\"\u0019A\u001d\t\u0013\r\u001dR$!AA\u0004\r%\u0012aC3wS\u0012,gnY3%eA\u0002RAUA%\u0007CAq!a\u0014\u001e\u0001\u0004\u0019i\u0003\u0005\u00033\u0001\r\u0005\u0002bBA+;\u0001\u00071Q\u0006\u0005\b\u0003wj\u0002\u0019AB\u0017\u0011\u001d\u0011\u0019$\ba\u0001\u0007[A3!HA-\u0003Q)h.[8oi}\u000bD.Z:tg}\u0013D.Z:tgU!11HB\"))\u0019ida\u0013\u0004P\rE31\u000b\u000b\u0005\u0007\u007f\u0019)\u0005\u0005\u0004\u0002:\u0005m2\u0011\t\t\u0004m\r\rC!\u0002\u001d\u001f\u0005\u0004I\u0004\"CB$=\u0005\u0005\t9AB%\u0003-)g/\u001b3f]\u000e,GEM\u0019\u0011\u000bI\u000bIe!\u0011\t\u000f\u0005=c\u00041\u0001\u0004NA!!\u0007AB!\u0011\u001d\t)F\ba\u0001\u0007\u001bBq!a\u001f\u001f\u0001\u0004\u0019i\u0005C\u0004\u00034y\u0001\ra!\u0014)\u0007y\tI&A\u0007v]&|g\u000eN02Y\u0016\u001c8OM\u000b\u0005\u00077\u001a\u0019\u0007\u0006\u0006\u0004^\r-4qNB9\u0007g\"Baa\u0018\u0004fA1\u0011\u0011HA\u001e\u0007C\u00022ANB2\t\u0015AtD1\u0001:\u0011%\u00199gHA\u0001\u0002\b\u0019I'A\u0006fm&$WM\\2fII\u0012\u0004#\u0002*\u0002J\r\u0005\u0004bBA(?\u0001\u00071Q\u000e\t\u0005e\u0001\u0019\t\u0007C\u0004\u0002V}\u0001\ra!\u001c\t\u000f\u0005mt\u00041\u0001\u0004n!9!1G\u0010A\u0002\r5\u0004fA\u0010\u0002Z\u00051QO\\5p]R*Baa\u001f\u0004\u0004RQ1QPBF\u0007\u001f\u001b\tja%\u0015\t\r}4Q\u0011\t\u0007\u0003s\tYd!!\u0011\u0007Y\u001a\u0019\tB\u00039A\t\u0007\u0011\bC\u0005\u0004\b\u0002\n\t\u0011q\u0001\u0004\n\u0006YQM^5eK:\u001cW\r\n\u001a4!\u0015\u0011\u0016\u0011JBA\u0011\u001d\ty\u0005\ta\u0001\u0007\u001b\u0003BA\r\u0001\u0004\u0002\"9\u0011Q\u000b\u0011A\u0002\r5\u0005bBA>A\u0001\u00071Q\u0012\u0005\b\u0005g\u0001\u0003\u0019ABGQ\r\u0001\u0013\u0011\f"
)
public interface ValueBound extends Bound {
   static Interval union4(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$23) {
      return ValueBound$.MODULE$.union4(v1, v2, v3, v4, evidence$23);
   }

   static Interval union4_1less2(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$22) {
      return ValueBound$.MODULE$.union4_1less2(v1, v2, v3, v4, evidence$22);
   }

   static Interval union4_1less3_2less3(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$21) {
      return ValueBound$.MODULE$.union4_1less3_2less3(v1, v2, v3, v4, evidence$21);
   }

   static Interval union4_1less2_1less3(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$20) {
      return ValueBound$.MODULE$.union4_1less2_1less3(v1, v2, v3, v4, evidence$20);
   }

   static Interval union4_1approx2(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$19) {
      return ValueBound$.MODULE$.union4_1approx2(v1, v2, v3, v4, evidence$19);
   }

   static Interval union4_1approx2_2approx3(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$18) {
      return ValueBound$.MODULE$.union4_1approx2_2approx3(v1, v2, v3, v4, evidence$18);
   }

   static Interval union4_1approx2_2less3(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$17) {
      return ValueBound$.MODULE$.union4_1approx2_2less3(v1, v2, v3, v4, evidence$17);
   }

   static Interval union4_1less2_2approx3(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$16) {
      return ValueBound$.MODULE$.union4_1less2_2approx3(v1, v2, v3, v4, evidence$16);
   }

   static Interval union4_1less3_2less3_3approx4(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$15) {
      return ValueBound$.MODULE$.union4_1less3_2less3_3approx4(v1, v2, v3, v4, evidence$15);
   }

   static Interval union4_1less2_2approx3_3approx4(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$14) {
      return ValueBound$.MODULE$.union4_1less2_2approx3_3approx4(v1, v2, v3, v4, evidence$14);
   }

   static Interval union4_1approx2_2less3_3approx4(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$13) {
      return ValueBound$.MODULE$.union4_1approx2_2less3_3approx4(v1, v2, v3, v4, evidence$13);
   }

   static Interval union4_1approx2_2approx3_3less4(final ValueBound v1, final ValueBound v2, final ValueBound v3, final ValueBound v4, final Order evidence$12) {
      return ValueBound$.MODULE$.union4_1approx2_2approx3_3less4(v1, v2, v3, v4, evidence$12);
   }

   static Interval union3(final ValueBound v1, final ValueBound v2, final ValueBound v3, final Order evidence$11) {
      return ValueBound$.MODULE$.union3(v1, v2, v3, evidence$11);
   }

   static Interval union3_1less2(final ValueBound v1, final ValueBound v2, final ValueBound v3, final Order evidence$10) {
      return ValueBound$.MODULE$.union3_1less2(v1, v2, v3, evidence$10);
   }

   static Interval union3_1approx2(final ValueBound v1, final ValueBound v2, final ValueBound v3, final Order evidence$9) {
      return ValueBound$.MODULE$.union3_1approx2(v1, v2, v3, evidence$9);
   }

   static Interval union3_1approx2_2approx3(final ValueBound v1, final ValueBound v2, final ValueBound v3, final Order evidence$8) {
      return ValueBound$.MODULE$.union3_1approx2_2approx3(v1, v2, v3, evidence$8);
   }

   static Interval union3_1less2_2approx3(final ValueBound v1, final ValueBound v2, final ValueBound v3, final Order evidence$7) {
      return ValueBound$.MODULE$.union3_1less2_2approx3(v1, v2, v3, evidence$7);
   }

   static Interval union3_1approx2_2less3(final ValueBound v1, final ValueBound v2, final ValueBound v3, final Order evidence$6) {
      return ValueBound$.MODULE$.union3_1approx2_2less3(v1, v2, v3, evidence$6);
   }

   static Interval union2(final ValueBound v1, final ValueBound v2, final Order evidence$5) {
      return ValueBound$.MODULE$.union2(v1, v2, evidence$5);
   }

   static Option unapply(final Bound b) {
      return ValueBound$.MODULE$.unapply(b);
   }

   Object a();

   boolean isClosed();

   // $FF: synthetic method
   static ValueBound unary_$minus$(final ValueBound $this, final AdditiveGroup ev) {
      return $this.unary_$minus(ev);
   }

   default ValueBound unary_$minus(final AdditiveGroup ev) {
      return (ValueBound)(this.isClosed() ? new Closed(ev.negate(this.a())) : new Open(ev.negate(this.a())));
   }

   // $FF: synthetic method
   static ValueBound reciprocal$(final ValueBound $this, final MultiplicativeGroup ev) {
      return $this.reciprocal(ev);
   }

   default ValueBound reciprocal(final MultiplicativeGroup ev) {
      return (ValueBound)(this.isClosed() ? new Closed(ev.reciprocal(this.a())) : new Open(ev.reciprocal(this.a())));
   }

   // $FF: synthetic method
   static ValueBound $plus$tilde$(final ValueBound $this, final ValueBound rhs, final AdditiveSemigroup ev) {
      return $this.$plus$tilde(rhs, ev);
   }

   default ValueBound $plus$tilde(final ValueBound rhs, final AdditiveSemigroup ev) {
      Object m = ev.plus(this.a(), rhs.a());
      return (ValueBound)(this.isClosed() && rhs.isClosed() ? new Closed(m) : new Open(m));
   }

   // $FF: synthetic method
   static ValueBound $minus$tilde$(final ValueBound $this, final ValueBound rhs, final AdditiveGroup ev) {
      return $this.$minus$tilde(rhs, ev);
   }

   default ValueBound $minus$tilde(final ValueBound rhs, final AdditiveGroup ev) {
      Object m = ev.minus(this.a(), rhs.a());
      return (ValueBound)(this.isClosed() && rhs.isClosed() ? new Closed(m) : new Open(m));
   }

   // $FF: synthetic method
   static ValueBound $times$tilde$(final ValueBound $this, final ValueBound rhs, final MultiplicativeSemigroup ev) {
      return $this.$times$tilde(rhs, ev);
   }

   default ValueBound $times$tilde(final ValueBound rhs, final MultiplicativeSemigroup ev) {
      Object m = ev.times(this.a(), rhs.a());
      return (ValueBound)(this.isClosed() && rhs.isClosed() ? new Closed(m) : new Open(m));
   }

   // $FF: synthetic method
   static ValueBound $div$tilde$(final ValueBound $this, final ValueBound rhs, final MultiplicativeGroup ev) {
      return $this.$div$tilde(rhs, ev);
   }

   default ValueBound $div$tilde(final ValueBound rhs, final MultiplicativeGroup ev) {
      Object m = ev.div(this.a(), rhs.a());
      return (ValueBound)(this.isClosed() && rhs.isClosed() ? new Closed(m) : new Open(m));
   }

   static void $init$(final ValueBound $this) {
   }
}
