package org.apache.spark.sql.catalyst.util;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.TimeZone;
import scala.Enumeration;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011eq\u0001CA\u0004\u0003\u0013A\t!a\t\u0007\u0011\u0005\u001d\u0012\u0011\u0002E\u0001\u0003SAq!a\u000e\u0002\t\u0003\tID\u0002\u0004\u0002<\u0005\u0001\u0015Q\b\u0005\u000b\u0003;\u001a!Q3A\u0005\u0002\u0005}\u0003BCA<\u0007\tE\t\u0015!\u0003\u0002b!Q\u0011\u0011P\u0002\u0003\u0016\u0004%\t!a\u001f\t\u0015\u0005M5A!E!\u0002\u0013\ti\bC\u0004\u00028\r!\t!!&\t\u000f\u0005}5\u0001\"\u0001\u0002\"\"I\u00111U\u0002\u0002\u0002\u0013\u0005\u0011Q\u0015\u0005\n\u0003W\u001b\u0011\u0013!C\u0001\u0003[C\u0011\"a1\u0004#\u0003%\t!!2\t\u0013\u0005%7!!A\u0005B\u0005-\u0007\"CAn\u0007\u0005\u0005I\u0011AAo\u0011%\t)oAA\u0001\n\u0003\t9\u000fC\u0005\u0002t\u000e\t\t\u0011\"\u0011\u0002v\"I!1A\u0002\u0002\u0002\u0013\u0005!Q\u0001\u0005\n\u0005\u001f\u0019\u0011\u0011!C!\u0005#A\u0011B!\u0006\u0004\u0003\u0003%\tEa\u0006\t\u0013\te1!!A\u0005B\tm\u0001\"\u0003B\u000f\u0007\u0005\u0005I\u0011\tB\u0010\u000f%\u0011\u0019#AA\u0001\u0012\u0003\u0011)CB\u0005\u0002<\u0005\t\t\u0011#\u0001\u0003(!9\u0011qG\f\u0005\u0002\t}\u0002\"\u0003B\r/\u0005\u0005IQ\tB\u000e\u0011%\u0011\teFA\u0001\n\u0003\u0013\u0019\u0005C\u0005\u0003J]\t\n\u0011\"\u0001\u0002F\"I!1J\f\u0002\u0002\u0013\u0005%Q\n\u0005\n\u00057:\u0012\u0013!C\u0001\u0003\u000bD\u0011B!\u0018\u0018\u0003\u0003%IAa\u0018\t\u000f\t\u001d\u0014\u0001\"\u0003\u0003j!I!QP\u0001C\u0002\u0013%!q\u0010\u0005\t\u0005\u0003\u000b\u0001\u0015!\u0003\u0003p!I!1Q\u0001C\u0002\u0013%!q\u0010\u0005\t\u0005\u000b\u000b\u0001\u0015!\u0003\u0003p!I!qQ\u0001C\u0002\u0013\u0015\u0011Q\u001c\u0005\t\u0005\u0013\u000b\u0001\u0015!\u0004\u0002`\"I!1R\u0001C\u0002\u00135\u0011Q\u001c\u0005\t\u0005\u001b\u000b\u0001\u0015!\u0004\u0002`\"I!qR\u0001\u0005\u0002\u0005E!\u0011\u0013\u0005\b\u0005+\u000bA\u0011\u0001BL\u0011%\u0011Y*\u0001b\u0001\n\u0013\u0011y\b\u0003\u0005\u0003\u001e\u0006\u0001\u000b\u0011\u0002B8\u0011%\u0011y*\u0001b\u0001\n\u0013\u0011y\b\u0003\u0005\u0003\"\u0006\u0001\u000b\u0011\u0002B8\u0011%\u0011\u0019+\u0001b\u0001\n\u000b\ti\u000e\u0003\u0005\u0003&\u0006\u0001\u000bQBAp\u0011%\u00119+\u0001b\u0001\n\u001b\ti\u000e\u0003\u0005\u0003*\u0006\u0001\u000bQBAp\u0011%\u0011Y+\u0001b\u0001\n\u001b\u0011i\u000b\u0003\u0005\u0003<\u0006\u0001\u000bQ\u0002BX\u0011%\u0011i,\u0001b\u0001\n\u001b\u0011i\u000b\u0003\u0005\u0003@\u0006\u0001\u000bQ\u0002BX\u0011%\u0011\t-\u0001C\u0001\u0003#\u0011\u0019\rC\u0004\u0003H\u0006!\tA!3\u0007\r\t5\u0017\u0001\u0012Bh\u0011)\u0011\t\u000e\u000fBK\u0002\u0013\u0005\u0011\u0011\u0015\u0005\u000b\u0005'D$\u0011#Q\u0001\n\u0005\r\u0005B\u0003B7q\tU\r\u0011\"\u0001\u0003V\"Q!q\u001c\u001d\u0003\u0012\u0003\u0006IAa6\t\u0015\t]\u0004H!f\u0001\n\u0003\u0011)\u000e\u0003\u0006\u0003bb\u0012\t\u0012)A\u0005\u0005/Dq!a\u000e9\t\u0003\u0011\u0019\u000fC\u0005\u0002$b\n\t\u0011\"\u0001\u0003n\"I\u00111\u0016\u001d\u0012\u0002\u0013\u0005!Q\u001f\u0005\n\u0003\u0007D\u0014\u0013!C\u0001\u0005sD\u0011B!@9#\u0003%\tA!?\t\u0013\u0005%\u0007(!A\u0005B\u0005-\u0007\"CAnq\u0005\u0005I\u0011AAo\u0011%\t)\u000fOA\u0001\n\u0003\u0011y\u0010C\u0005\u0002tb\n\t\u0011\"\u0011\u0002v\"I!1\u0001\u001d\u0002\u0002\u0013\u000511\u0001\u0005\n\u0005\u001fA\u0014\u0011!C!\u0007\u000fA\u0011B!\u00069\u0003\u0003%\tEa\u0006\t\u0013\te\u0001(!A\u0005B\tm\u0001\"\u0003B\u000fq\u0005\u0005I\u0011IB\u0006\u000f%\u0019y!AA\u0001\u0012\u0013\u0019\tBB\u0005\u0003N\u0006\t\t\u0011#\u0003\u0004\u0014!9\u0011q\u0007(\u0005\u0002\rm\u0001\"\u0003B\r\u001d\u0006\u0005IQ\tB\u000e\u0011%\u0011\tETA\u0001\n\u0003\u001bi\u0002C\u0005\u0003L9\u000b\t\u0011\"!\u0004&!I!Q\f(\u0002\u0002\u0013%!q\f\u0004\t\u0007c\t\u0001)!\u0005\u00044!Q!Q\u000e+\u0003\u0016\u0004%\tA!6\t\u0015\t}GK!E!\u0002\u0013\u00119\u000e\u0003\u0006\u0003xQ\u0013)\u001a!C\u0001\u0005+D!B!9U\u0005#\u0005\u000b\u0011\u0002Bl\u0011\u001d\t9\u0004\u0016C\u0001\u0007kA\u0011\"a)U\u0003\u0003%\ta!\u0010\t\u0013\u0005-F+%A\u0005\u0002\te\b\"CAb)F\u0005I\u0011\u0001B}\u0011%\tI\rVA\u0001\n\u0003\nY\rC\u0005\u0002\\R\u000b\t\u0011\"\u0001\u0002^\"I\u0011Q\u001d+\u0002\u0002\u0013\u000511\t\u0005\n\u0003g$\u0016\u0011!C!\u0003kD\u0011Ba\u0001U\u0003\u0003%\taa\u0012\t\u0013\t=A+!A\u0005B\r-\u0003\"\u0003B\u000b)\u0006\u0005I\u0011\tB\f\u0011%\u0011I\u0002VA\u0001\n\u0003\u0012Y\u0002C\u0005\u0003\u001eQ\u000b\t\u0011\"\u0011\u0004P\u001dY11K\u0001\u0002\u0002#\u0005\u0011\u0011CB+\r-\u0019\t$AA\u0001\u0012\u0003\t\tba\u0016\t\u000f\u0005]r\r\"\u0001\u0004\\!I!\u0011D4\u0002\u0002\u0013\u0015#1\u0004\u0005\n\u0005\u0003:\u0017\u0011!CA\u0007;B\u0011Ba\u0013h\u0003\u0003%\tia\u0019\t\u0013\tus-!A\u0005\n\t}\u0003bBB6\u0003\u0011%1Q\u000e\u0005\u000b\u0007o\n\u0001R1A\u0005\n\re\u0004\"CBU\u0003\u0011\u0005\u0011\u0011CBV\u0011%\u0019i,\u0001b\u0001\n\u0013\u0019y\f\u0003\u0005\u0004B\u0006\u0001\u000b\u0011BBW\u0011\u001d\u0019\u0019-\u0001C\u0005\u0007\u000bD\u0011ba3\u0002\u0005\u0004%)a!4\t\u0011\r=\u0017\u0001)A\u0007\u00053D\u0011b!5\u0002\u0005\u0004%iaa5\t\u0011\rm\u0017\u0001)A\u0007\u0007+D\u0011b!8\u0002\u0005\u0004%iaa5\t\u0011\r}\u0017\u0001)A\u0007\u0007+D\u0011b!9\u0002\t\u0003\t\tba9\t\u000f\r\u0005\u0018\u0001\"\u0001\u0004t\"91\u0011]\u0001\u0005\u0002\rm\b\"CB\u0000\u0003\u0011\u0005\u0011\u0011\u0003C\u0001\u0011%!9!\u0001b\u0001\n\u0013\u0019y\f\u0003\u0005\u0005\n\u0005\u0001\u000b\u0011BBW\u0011%!Y!\u0001b\u0001\n\u000b\u0019i\r\u0003\u0005\u0005\u000e\u0005\u0001\u000bQ\u0002Bm\u0011\u001d\u0019y0\u0001C\u0001\t\u001fAqaa@\u0002\t\u0003!)\"\u0001\bSK\n\f7/\u001a#bi\u0016$\u0016.\\3\u000b\t\u0005-\u0011QB\u0001\u0005kRLGN\u0003\u0003\u0002\u0010\u0005E\u0011\u0001C2bi\u0006d\u0017p\u001d;\u000b\t\u0005M\u0011QC\u0001\u0004gFd'\u0002BA\f\u00033\tQa\u001d9be.TA!a\u0007\u0002\u001e\u00051\u0011\r]1dQ\u0016T!!a\b\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0007\u0005\u0015\u0012!\u0004\u0002\u0002\n\tq!+\u001a2bg\u0016$\u0015\r^3US6,7cA\u0001\u0002,A!\u0011QFA\u001a\u001b\t\tyC\u0003\u0002\u00022\u0005)1oY1mC&!\u0011QGA\u0018\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\"!a\t\u0003\u0015I+'-Y:f'B,7mE\u0004\u0004\u0003W\ty$!\u0012\u0011\t\u00055\u0012\u0011I\u0005\u0005\u0003\u0007\nyCA\u0004Qe>$Wo\u0019;\u0011\t\u0005\u001d\u0013q\u000b\b\u0005\u0003\u0013\n\u0019F\u0004\u0003\u0002L\u0005ESBAA'\u0015\u0011\ty%!\t\u0002\rq\u0012xn\u001c;?\u0013\t\t\t$\u0003\u0003\u0002V\u0005=\u0012a\u00029bG.\fw-Z\u0005\u0005\u00033\nYF\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0003\u0002V\u0005=\u0012\u0001B7pI\u0016,\"!!\u0019\u0011\t\u0005\r\u0014q\u000e\b\u0005\u0003K\nY'\u0004\u0002\u0002h)!\u0011\u0011NA\t\u0003!Ig\u000e^3s]\u0006d\u0017\u0002BA7\u0003O\nA\u0003T3hC\u000eL()\u001a5bm&|'\u000fU8mS\u000eL\u0018\u0002BA9\u0003g\u0012QAV1mk\u0016LA!!\u001e\u00020\tYQI\\;nKJ\fG/[8o\u0003\u0015iw\u000eZ3!\u00039y'/[4j]RKW.\u001a.p]\u0016,\"!! \u0011\r\u00055\u0012qPAB\u0013\u0011\t\t)a\f\u0003\r=\u0003H/[8o!\u0011\t))!$\u000f\t\u0005\u001d\u0015\u0011\u0012\t\u0005\u0003\u0017\ny#\u0003\u0003\u0002\f\u0006=\u0012A\u0002)sK\u0012,g-\u0003\u0003\u0002\u0010\u0006E%AB*ue&twM\u0003\u0003\u0002\f\u0006=\u0012aD8sS\u001eLg\u000eV5nKj{g.\u001a\u0011\u0015\r\u0005]\u00151TAO!\r\tIjA\u0007\u0002\u0003!9\u0011Q\f\u0005A\u0002\u0005\u0005\u0004\"CA=\u0011A\u0005\t\u0019AA?\u0003!!\u0018.\\3[_:,WCAAB\u0003\u0011\u0019w\u000e]=\u0015\r\u0005]\u0015qUAU\u0011%\tiF\u0003I\u0001\u0002\u0004\t\t\u0007C\u0005\u0002z)\u0001\n\u00111\u0001\u0002~\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAAXU\u0011\t\t'!-,\u0005\u0005M\u0006\u0003BA[\u0003\u007fk!!a.\u000b\t\u0005e\u00161X\u0001\nk:\u001c\u0007.Z2lK\u0012TA!!0\u00020\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005\u0005\u0017q\u0017\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0003\u000fTC!! \u00022\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"!!4\u0011\t\u0005=\u0017\u0011\\\u0007\u0003\u0003#TA!a5\u0002V\u0006!A.\u00198h\u0015\t\t9.\u0001\u0003kCZ\f\u0017\u0002BAH\u0003#\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!a8\u0011\t\u00055\u0012\u0011]\u0005\u0005\u0003G\fyCA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002j\u0006=\b\u0003BA\u0017\u0003WLA!!<\u00020\t\u0019\u0011I\\=\t\u0013\u0005Ex\"!AA\u0002\u0005}\u0017a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002xB1\u0011\u0011`A\u0000\u0003Sl!!a?\u000b\t\u0005u\u0018qF\u0001\u000bG>dG.Z2uS>t\u0017\u0002\u0002B\u0001\u0003w\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!!q\u0001B\u0007!\u0011\tiC!\u0003\n\t\t-\u0011q\u0006\u0002\b\u0005>|G.Z1o\u0011%\t\t0EA\u0001\u0002\u0004\tI/\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BAg\u0005'A\u0011\"!=\u0013\u0003\u0003\u0005\r!a8\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a8\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!4\u0002\r\u0015\fX/\u00197t)\u0011\u00119A!\t\t\u0013\u0005EX#!AA\u0002\u0005%\u0018A\u0003*fE\u0006\u001cXm\u00159fGB\u0019\u0011\u0011T\f\u0014\u000b]\u0011IC!\u000e\u0011\u0015\t-\"\u0011GA1\u0003{\n9*\u0004\u0002\u0003.)!!qFA\u0018\u0003\u001d\u0011XO\u001c;j[\u0016LAAa\r\u0003.\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\t]\"QH\u0007\u0003\u0005sQAAa\u000f\u0002V\u0006\u0011\u0011n\\\u0005\u0005\u00033\u0012I\u0004\u0006\u0002\u0003&\u0005)\u0011\r\u001d9msR1\u0011q\u0013B#\u0005\u000fBq!!\u0018\u001b\u0001\u0004\t\t\u0007C\u0005\u0002zi\u0001\n\u00111\u0001\u0002~\u0005y\u0011\r\u001d9ms\u0012\"WMZ1vYR$#'A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\t=#q\u000b\t\u0007\u0003[\tyH!\u0015\u0011\u0011\u00055\"1KA1\u0003{JAA!\u0016\u00020\t1A+\u001e9mKJB\u0011B!\u0017\u001d\u0003\u0003\u0005\r!a&\u0002\u0007a$\u0003'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005C\u0002B!a4\u0003d%!!QMAi\u0005\u0019y%M[3di\u0006Q!/\u001a2bg\u0016$\u0015-_:\u0015\u0011\u0005}'1\u000eB;\u0005sBqA!\u001c \u0001\u0004\u0011y'\u0001\u0005to&$8\r[3t!\u0019\tiC!\u001d\u0002`&!!1OA\u0018\u0005\u0015\t%O]1z\u0011\u001d\u00119h\ba\u0001\u0005_\nQ\u0001Z5gMNDqAa\u001f \u0001\u0004\ty.\u0001\u0003eCf\u001c\u0018a\u00046vY&\fgn\u0012:fO\u0012KgMZ:\u0016\u0005\t=\u0014\u0001\u00056vY&\fgn\u0012:fO\u0012KgMZ:!\u0003]QW\u000f\\5b]\u001e\u0013Xm\u001a#jM\u001a\u001cv/\u001b;dQ\u0012\u000b\u00170\u0001\rkk2L\u0017M\\$sK\u001e$\u0015N\u001a4To&$8\r\u001b#bs\u0002\n1\u0003\\1tiN;\u0018\u000e^2i\u0015Vd\u0017.\u00198ECf\fA\u0003\\1tiN;\u0018\u000e^2i\u0015Vd\u0017.\u00198ECf\u0004\u0013a\u00066vY&\fgnQ8n[>tWI]1Ti\u0006\u0014H\u000fR1z\u0003aQW\u000f\\5b]\u000e{W.\\8o\u000bJ\f7\u000b^1si\u0012\u000b\u0017\u0010I\u0001!Y>\u001c\u0017\r\u001c*fE\u0006\u001cXMS;mS\u0006tGk\\$sK\u001e|'/[1o\t\u0006L8\u000f\u0006\u0003\u0002`\nM\u0005b\u0002B>Q\u0001\u0007\u0011q\\\u0001\u001ce\u0016\u0014\u0017m]3Kk2L\u0017M\u001c+p\u000fJ,wm\u001c:jC:$\u0015-_:\u0015\t\u0005}'\u0011\u0014\u0005\b\u0005wJ\u0003\u0019AAp\u0003=9'/Z4Kk2L\u0017M\u001c#jM\u001a\u001c\u0018\u0001E4sK\u001eTU\u000f\\5b]\u0012KgMZ:!\u0003]9'/Z4Kk2L\u0017M\u001c#jM\u001a\u001cv/\u001b;dQ\u0012\u000b\u00170\u0001\rhe\u0016<'*\u001e7jC:$\u0015N\u001a4To&$8\r\u001b#bs\u0002\na\u0003\\1tiN;\u0018\u000e^2i\u000fJ,wm\u001c:jC:$\u0015-_\u0001\u0018Y\u0006\u001cHoU<ji\u000eDwI]3h_JL\u0017M\u001c#bs\u0002\n!d\u001a:fO>\u0014\u0018.\u00198D_6lwN\\#sCN#\u0018M\u001d;ECf\f1d\u001a:fO>\u0014\u0018.\u00198D_6lwN\\#sCN#\u0018M\u001d;ECf\u0004\u0013AE4sK\u001e|'/[1o'R\f'\u000f\u001e#bi\u0016,\"Aa,\u0011\t\tE&qW\u0007\u0003\u0005gSAA!.\u0002V\u0006!A/[7f\u0013\u0011\u0011ILa-\u0003\u00131{7-\u00197ECR,\u0017aE4sK\u001e|'/[1o'R\f'\u000f\u001e#bi\u0016\u0004\u0013!\u00046vY&\fg.\u00128e\t\u0006$X-\u0001\bkk2L\u0017M\\#oI\u0012\u000bG/\u001a\u0011\u0002A1|7-\u00197SK\n\f7/Z$sK\u001e|'/[1o)>TU\u000f\\5b]\u0012\u000b\u0017p\u001d\u000b\u0005\u0003?\u0014)\rC\u0004\u0003|Y\u0002\r!a8\u00027I,'-Y:f\u000fJ,wm\u001c:jC:$vNS;mS\u0006tG)Y=t)\u0011\tyNa3\t\u000f\tmt\u00071\u0001\u0002`\n\u0001\"j]8o%\u0016\u0014\u0017m]3SK\u000e|'\u000fZ\n\bq\u0005-\u0012qHA#\u0003\t!(0A\u0002uu\u0002*\"Aa6\u0011\r\u00055\"\u0011\u000fBm!\u0011\tiCa7\n\t\tu\u0017q\u0006\u0002\u0005\u0019>tw-A\u0005to&$8\r[3tA\u00051A-\u001b4gg\u0002\"\u0002B!:\u0003h\n%(1\u001e\t\u0004\u00033C\u0004b\u0002Bi\u007f\u0001\u0007\u00111\u0011\u0005\b\u0005[z\u0004\u0019\u0001Bl\u0011\u001d\u00119h\u0010a\u0001\u0005/$\u0002B!:\u0003p\nE(1\u001f\u0005\n\u0005#\u0004\u0005\u0013!a\u0001\u0003\u0007C\u0011B!\u001cA!\u0003\u0005\rAa6\t\u0013\t]\u0004\t%AA\u0002\t]WC\u0001B|U\u0011\t\u0019)!-\u0016\u0005\tm(\u0006\u0002Bl\u0003c\u000babY8qs\u0012\"WMZ1vYR$3\u0007\u0006\u0003\u0002j\u000e\u0005\u0001\"CAy\r\u0006\u0005\t\u0019AAp)\u0011\u00119a!\u0002\t\u0013\u0005E\b*!AA\u0002\u0005%H\u0003BAg\u0007\u0013A\u0011\"!=J\u0003\u0003\u0005\r!a8\u0015\t\t\u001d1Q\u0002\u0005\n\u0003cd\u0015\u0011!a\u0001\u0003S\f\u0001CS:p]J+'-Y:f%\u0016\u001cwN\u001d3\u0011\u0007\u0005eejE\u0003O\u0007+\u0011)\u0004\u0005\u0007\u0003,\r]\u00111\u0011Bl\u0005/\u0014)/\u0003\u0003\u0004\u001a\t5\"!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ogQ\u00111\u0011\u0003\u000b\t\u0005K\u001cyb!\t\u0004$!9!\u0011[)A\u0002\u0005\r\u0005b\u0002B7#\u0002\u0007!q\u001b\u0005\b\u0005o\n\u0006\u0019\u0001Bl)\u0011\u00199ca\f\u0011\r\u00055\u0012qPB\u0015!)\tica\u000b\u0002\u0004\n]'q[\u0005\u0005\u0007[\tyC\u0001\u0004UkBdWm\r\u0005\n\u00053\u0012\u0016\u0011!a\u0001\u0005K\u0014!BU3cCN,\u0017J\u001c4p'\u001d!\u00161FA \u0003\u000b\"baa\u000e\u0004:\rm\u0002cAAM)\"9!QN-A\u0002\t]\u0007b\u0002B<3\u0002\u0007!q\u001b\u000b\u0007\u0007o\u0019yd!\u0011\t\u0013\t5$\f%AA\u0002\t]\u0007\"\u0003B<5B\u0005\t\u0019\u0001Bl)\u0011\tIo!\u0012\t\u0013\u0005Ex,!AA\u0002\u0005}G\u0003\u0002B\u0004\u0007\u0013B\u0011\"!=b\u0003\u0003\u0005\r!!;\u0015\t\u000557Q\n\u0005\n\u0003c\u0014\u0017\u0011!a\u0001\u0003?$BAa\u0002\u0004R!I\u0011\u0011_3\u0002\u0002\u0003\u0007\u0011\u0011^\u0001\u000b%\u0016\u0014\u0017m]3J]\u001a|\u0007cAAMON)qm!\u0017\u00036AQ!1\u0006B\u0019\u0005/\u00149na\u000e\u0015\u0005\rUCCBB\u001c\u0007?\u001a\t\u0007C\u0004\u0003n)\u0004\rAa6\t\u000f\t]$\u000e1\u0001\u0003XR!1QMB5!\u0019\ti#a \u0004hAA\u0011Q\u0006B*\u0005/\u00149\u000eC\u0005\u0003Z-\f\t\u00111\u0001\u00048\u0005a!/\u001a2bg\u0016l\u0015n\u0019:pgR1!\u0011\\B8\u0007gBqa!\u001dn\u0001\u0004\u00199$\u0001\u0006sK\n\f7/Z%oM>Dqa!\u001en\u0001\u0004\u0011I.\u0001\u0004nS\u000e\u0014xn]\u0001\u0007[\u0006\u0004\b/\u001a:\u0016\u0005\rm$CBB?\u0007\u0007\u001bYJB\u0004\u0004\u0000\r\u0005\u0005aa\u001f\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \t\r\r]d\u000eAB>!\u0011\u0019)ia&\u000e\u0005\r\u001d%\u0002BBE\u0007\u0017\u000b\u0001\u0002Z1uC\nLg\u000e\u001a\u0006\u0005\u0007\u001b\u001by)A\u0004kC\u000e\\7o\u001c8\u000b\t\rE51S\u0001\nM\u0006\u001cH/\u001a:y[2T!a!&\u0002\u0007\r|W.\u0003\u0003\u0004\u001a\u000e\u001d%\u0001D(cU\u0016\u001cG/T1qa\u0016\u0014\b\u0003BBO\u0007Kk!aa(\u000b\t\u0005E2\u0011\u0015\u0006\u0005\u0007G\u001bY)\u0001\u0004n_\u0012,H.Z\u0005\u0005\u0007O\u001byJ\u0001\nDY\u0006\u001c8\u000fV1h\u000bb$XM\\:j_:\u001c\u0018!\u00057pC\u0012\u0014VMY1tKJ+7m\u001c:egR!1QVB]!!\u0019yk!.\u0002\u0004\u000e]RBABY\u0015\u0011\u0019\u0019,a?\u0002\u000f5,H/\u00192mK&!1qWBY\u0005\u001dA\u0015m\u001d5NCBDqaa/p\u0001\u0004\t\u0019)\u0001\u0005gS2,g*Y7f\u0003M9'/Z4Kk2L\u0017M\u001c*fE\u0006\u001cX-T1q+\t\u0019i+\u0001\u000bhe\u0016<'*\u001e7jC:\u0014VMY1tK6\u000b\u0007\u000fI\u0001\u0010O\u0016$H*Y:u'^LGo\u00195UgR!!\u0011\\Bd\u0011\u001d\u0019IM\u001da\u0001\u0007[\u000b\u0011B]3cCN,W*\u00199\u0002+1\f7\u000f^*xSR\u001c\u0007n\u0012:fO>\u0014\u0018.\u00198UgV\u0011!\u0011\\\u0001\u0017Y\u0006\u001cHoU<ji\u000eDwI]3h_JL\u0017M\u001c+tA\u0005\u0001rM]3h_JL\u0017M\\*uCJ$Hk]\u000b\u0003\u0007+\u0004BA!-\u0004X&!1\u0011\u001cBZ\u00055aunY1m\t\u0006$X\rV5nK\u0006\trM]3h_JL\u0017M\\*uCJ$Hk\u001d\u0011\u0002\u0017),H.[1o\u000b:$Gk]\u0001\rUVd\u0017.\u00198F]\u0012$6\u000fI\u0001\u001ee\u0016\u0014\u0017m]3He\u0016<wN]5b]R{'*\u001e7jC:l\u0015n\u0019:pgR1!\u0011\\Bs\u0007cDqA!5z\u0001\u0004\u00199\u000f\u0005\u0003\u0004j\u000e5XBABv\u0015\u0011\tY!!6\n\t\r=81\u001e\u0002\t)&lWMW8oK\"91QO=A\u0002\teGC\u0002Bm\u0007k\u001cI\u0010C\u0004\u0004xj\u0004\r!a!\u0002\u0015QLW.\u001a.p]\u0016LE\rC\u0004\u0004vi\u0004\rA!7\u0015\t\te7Q \u0005\b\u0007kZ\b\u0019\u0001Bm\u0003u\u0011XMY1tK*+H.[1o)><%/Z4pe&\fg.T5de>\u001cHC\u0002Bm\t\u0007!)\u0001C\u0004\u0003Rr\u0004\raa:\t\u000f\rUD\u00101\u0001\u0003Z\u0006\u0019\".\u001e7jC:<%/Z4SK\n\f7/Z'ba\u0006!\".\u001e7jC:<%/Z4SK\n\f7/Z'ba\u0002\n!\u0003\\1tiN;\u0018\u000e^2i\u0015Vd\u0017.\u00198Ug\u0006\u0019B.Y:u'^LGo\u00195Kk2L\u0017M\u001c+tAQ1!\u0011\u001cC\t\t'A\u0001ba>\u0002\u0004\u0001\u0007\u00111\u0011\u0005\t\u0007k\n\u0019\u00011\u0001\u0003ZR!!\u0011\u001cC\f\u0011!\u0019)(!\u0002A\u0002\te\u0007"
)
public final class RebaseDateTime {
   public static long rebaseJulianToGregorianMicros(final long micros) {
      return RebaseDateTime$.MODULE$.rebaseJulianToGregorianMicros(micros);
   }

   public static long rebaseJulianToGregorianMicros(final String timeZoneId, final long micros) {
      return RebaseDateTime$.MODULE$.rebaseJulianToGregorianMicros(timeZoneId, micros);
   }

   public static long lastSwitchJulianTs() {
      return RebaseDateTime$.MODULE$.lastSwitchJulianTs();
   }

   public static long rebaseGregorianToJulianMicros(final long micros) {
      return RebaseDateTime$.MODULE$.rebaseGregorianToJulianMicros(micros);
   }

   public static long rebaseGregorianToJulianMicros(final String timeZoneId, final long micros) {
      return RebaseDateTime$.MODULE$.rebaseGregorianToJulianMicros(timeZoneId, micros);
   }

   public static long lastSwitchGregorianTs() {
      return RebaseDateTime$.MODULE$.lastSwitchGregorianTs();
   }

   public static int rebaseGregorianToJulianDays(final int days) {
      return RebaseDateTime$.MODULE$.rebaseGregorianToJulianDays(days);
   }

   public static int lastSwitchGregorianDay() {
      return RebaseDateTime$.MODULE$.lastSwitchGregorianDay();
   }

   public static int rebaseJulianToGregorianDays(final int days) {
      return RebaseDateTime$.MODULE$.rebaseJulianToGregorianDays(days);
   }

   public static int lastSwitchJulianDay() {
      return RebaseDateTime$.MODULE$.lastSwitchJulianDay();
   }

   public static class RebaseSpec implements Product, Serializable {
      private final Enumeration.Value mode;
      private final Option originTimeZone;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Enumeration.Value mode() {
         return this.mode;
      }

      public Option originTimeZone() {
         return this.originTimeZone;
      }

      public String timeZone() {
         return (String)this.originTimeZone().getOrElse(() -> TimeZone.getDefault().getID());
      }

      public RebaseSpec copy(final Enumeration.Value mode, final Option originTimeZone) {
         return new RebaseSpec(mode, originTimeZone);
      }

      public Enumeration.Value copy$default$1() {
         return this.mode();
      }

      public Option copy$default$2() {
         return this.originTimeZone();
      }

      public String productPrefix() {
         return "RebaseSpec";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.mode();
            }
            case 1 -> {
               return this.originTimeZone();
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof RebaseSpec;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "mode";
            }
            case 1 -> {
               return "originTimeZone";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var8;
         if (this != x$1) {
            label55: {
               if (x$1 instanceof RebaseSpec) {
                  label48: {
                     RebaseSpec var4 = (RebaseSpec)x$1;
                     Enumeration.Value var10000 = this.mode();
                     Enumeration.Value var5 = var4.mode();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label48;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label48;
                     }

                     Option var7 = this.originTimeZone();
                     Option var6 = var4.originTimeZone();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label48;
                        }
                     } else if (!var7.equals(var6)) {
                        break label48;
                     }

                     if (var4.canEqual(this)) {
                        break label55;
                     }
                  }
               }

               var8 = false;
               return var8;
            }
         }

         var8 = true;
         return var8;
      }

      public RebaseSpec(final Enumeration.Value mode, final Option originTimeZone) {
         this.mode = mode;
         this.originTimeZone = originTimeZone;
         Product.$init$(this);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class RebaseSpec$ extends AbstractFunction2 implements Serializable {
      public static final RebaseSpec$ MODULE$ = new RebaseSpec$();

      public Option $lessinit$greater$default$2() {
         return scala.None..MODULE$;
      }

      public final String toString() {
         return "RebaseSpec";
      }

      public RebaseSpec apply(final Enumeration.Value mode, final Option originTimeZone) {
         return new RebaseSpec(mode, originTimeZone);
      }

      public Option apply$default$2() {
         return scala.None..MODULE$;
      }

      public Option unapply(final RebaseSpec x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.mode(), x$0.originTimeZone())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(RebaseSpec$.class);
      }
   }

   private static class JsonRebaseRecord implements Product, Serializable {
      private final String tz;
      private final long[] switches;
      private final long[] diffs;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String tz() {
         return this.tz;
      }

      public long[] switches() {
         return this.switches;
      }

      public long[] diffs() {
         return this.diffs;
      }

      public JsonRebaseRecord copy(final String tz, final long[] switches, final long[] diffs) {
         return new JsonRebaseRecord(tz, switches, diffs);
      }

      public String copy$default$1() {
         return this.tz();
      }

      public long[] copy$default$2() {
         return this.switches();
      }

      public long[] copy$default$3() {
         return this.diffs();
      }

      public String productPrefix() {
         return "JsonRebaseRecord";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.tz();
            }
            case 1 -> {
               return this.switches();
            }
            case 2 -> {
               return this.diffs();
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof JsonRebaseRecord;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "tz";
            }
            case 1 -> {
               return "switches";
            }
            case 2 -> {
               return "diffs";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label51: {
               if (x$1 instanceof JsonRebaseRecord) {
                  label44: {
                     JsonRebaseRecord var4 = (JsonRebaseRecord)x$1;
                     String var10000 = this.tz();
                     String var5 = var4.tz();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label44;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label44;
                     }

                     if (this.switches() == var4.switches() && this.diffs() == var4.diffs() && var4.canEqual(this)) {
                        break label51;
                     }
                  }
               }

               var6 = false;
               return var6;
            }
         }

         var6 = true;
         return var6;
      }

      public JsonRebaseRecord(final String tz, final long[] switches, final long[] diffs) {
         this.tz = tz;
         this.switches = switches;
         this.diffs = diffs;
         Product.$init$(this);
      }
   }

   private static class JsonRebaseRecord$ extends AbstractFunction3 implements Serializable {
      public static final JsonRebaseRecord$ MODULE$ = new JsonRebaseRecord$();

      public final String toString() {
         return "JsonRebaseRecord";
      }

      public JsonRebaseRecord apply(final String tz, final long[] switches, final long[] diffs) {
         return new JsonRebaseRecord(tz, switches, diffs);
      }

      public Option unapply(final JsonRebaseRecord x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.tz(), x$0.switches(), x$0.diffs())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(JsonRebaseRecord$.class);
      }

      public JsonRebaseRecord$() {
      }
   }

   public static class RebaseInfo implements Product, Serializable {
      private final long[] switches;
      private final long[] diffs;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public long[] switches() {
         return this.switches;
      }

      public long[] diffs() {
         return this.diffs;
      }

      public RebaseInfo copy(final long[] switches, final long[] diffs) {
         return new RebaseInfo(switches, diffs);
      }

      public long[] copy$default$1() {
         return this.switches();
      }

      public long[] copy$default$2() {
         return this.diffs();
      }

      public String productPrefix() {
         return "RebaseInfo";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.switches();
            }
            case 1 -> {
               return this.diffs();
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof RebaseInfo;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "switches";
            }
            case 1 -> {
               return "diffs";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
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
            label38: {
               if (x$1 instanceof RebaseInfo) {
                  RebaseInfo var4 = (RebaseInfo)x$1;
                  if (this.switches() == var4.switches() && this.diffs() == var4.diffs() && var4.canEqual(this)) {
                     break label38;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public RebaseInfo(final long[] switches, final long[] diffs) {
         this.switches = switches;
         this.diffs = diffs;
         Product.$init$(this);
      }
   }

   public static class RebaseInfo$ extends AbstractFunction2 implements Serializable {
      public static final RebaseInfo$ MODULE$ = new RebaseInfo$();

      public final String toString() {
         return "RebaseInfo";
      }

      public RebaseInfo apply(final long[] switches, final long[] diffs) {
         return new RebaseInfo(switches, diffs);
      }

      public Option unapply(final RebaseInfo x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.switches(), x$0.diffs())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(RebaseInfo$.class);
      }
   }
}
