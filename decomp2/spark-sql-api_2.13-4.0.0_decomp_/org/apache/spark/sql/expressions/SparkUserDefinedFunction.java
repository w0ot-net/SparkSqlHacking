package org.apache.spark.sql.expressions;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.types.DataType;
import scala.Option;
import scala.Product;
import scala.Option.;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\rUc!\u0002\u001d:\u0001v\u001a\u0005\u0002C.\u0001\u0005+\u0007I\u0011\u0001/\t\u0011\u0001\u0004!\u0011#Q\u0001\nuC\u0001\"\u0019\u0001\u0003\u0016\u0004%\tA\u0019\u0005\tS\u0002\u0011\t\u0012)A\u0005G\"A!\u000e\u0001BK\u0002\u0013\u00051\u000e\u0003\u0005|\u0001\tE\t\u0015!\u0003m\u0011)\t9\u0001\u0001BK\u0002\u0013\u0005\u0011\u0011\u0002\u0005\u000b\u0003/\u0001!\u0011#Q\u0001\n\u0005-\u0001BCA\r\u0001\tU\r\u0011\"\u0001\u0002\u001c!Q\u0011q\u0006\u0001\u0003\u0012\u0003\u0006I!!\b\t\u0015\u0005E\u0002A!f\u0001\n\u0003\t\u0019\u0004\u0003\u0006\u0002<\u0001\u0011\t\u0012)A\u0005\u0003kA!\"!\u0010\u0001\u0005+\u0007I\u0011AA\u001a\u0011)\ty\u0004\u0001B\tB\u0003%\u0011Q\u0007\u0005\b\u0003\u0003\u0002A\u0011AA\"\u0011\u001d\tY\u0007\u0001C!\u0003[Bq!a\u001d\u0001\t\u0003\n)\bC\u0004\u0002x\u0001!\t%!\u001e\t\u000f\u0005E\u0004\u0001\"\u0011\u0002z!I\u00111\u0010\u0001\u0002\u0002\u0013\u0005\u0011Q\u0010\u0005\n\u0003\u001b\u0003\u0011\u0013!C\u0001\u0003\u001fC\u0011\"!*\u0001#\u0003%\t!a*\t\u0013\u0005-\u0006!%A\u0005\u0002\u00055\u0006\"CAY\u0001E\u0005I\u0011AAZ\u0011%\t9\fAI\u0001\n\u0003\tI\fC\u0005\u0002>\u0002\t\n\u0011\"\u0001\u0002@\"I\u00111\u0019\u0001\u0012\u0002\u0013\u0005\u0011q\u0018\u0005\n\u0003\u000b\u0004\u0011\u0011!C!\u0003\u000fD\u0011\"a6\u0001\u0003\u0003%\t!!7\t\u0013\u0005\u0005\b!!A\u0005\u0002\u0005\r\b\"CAu\u0001\u0005\u0005I\u0011IAv\u0011%\tI\u0010AA\u0001\n\u0003\tY\u0010C\u0005\u0002\u0000\u0002\t\t\u0011\"\u0011\u0003\u0002!I!Q\u0001\u0001\u0002\u0002\u0013\u0005#q\u0001\u0005\n\u0005\u0013\u0001\u0011\u0011!C!\u0005\u0017A\u0011B!\u0004\u0001\u0003\u0003%\tEa\u0004\b\u000f\tM\u0011\b#\u0001\u0003\u0016\u00191\u0001(\u000fE\u0001\u0005/Aq!!\u0011'\t\u0003\u0011\u0019\u0003\u0003\u0005\u0003&\u0019\"\ta\u000fB\u0014\u0011!\u0011)C\nC\u0001w\t]\u0004\u0002\u0003B\u0013M\u0011\u00051Ha)\t\u0013\t\u0015b%!A\u0005\u0002\n=\u0006\"\u0003BkME\u0005I\u0011\u0001Bl\u0011%\u00119OJI\u0001\n\u0003\u0011I\u000fC\u0005\u0003x\u001a\n\n\u0011\"\u0001\u0002:\"I!\u0011 \u0014\u0012\u0002\u0013\u0005\u0011q\u0018\u0005\n\u0005w4\u0013\u0013!C\u0001\u0003\u007fC\u0011B!@'\u0003\u0003%\tIa@\t\u0013\r\rb%%A\u0005\u0002\r\u0015\u0002\"CB\u001bME\u0005I\u0011AB\u001c\u0011%\u0019)EJI\u0001\n\u0003\tI\fC\u0005\u0004H\u0019\n\n\u0011\"\u0001\u0002@\"I1\u0011\n\u0014\u0012\u0002\u0013\u0005\u0011q\u0018\u0005\n\u0007\u00172\u0013\u0011!C\u0005\u0007\u001b\u0012\u0001d\u00159be.,6/\u001a:EK\u001aLg.\u001a3Gk:\u001cG/[8o\u0015\tQ4(A\u0006fqB\u0014Xm]:j_:\u001c(B\u0001\u001f>\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003}}\nQa\u001d9be.T!\u0001Q!\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0015aA8sON!\u0001\u0001\u0012%O!\t)e)D\u0001:\u0013\t9\u0015HA\nVg\u0016\u0014H)\u001a4j]\u0016$g)\u001e8di&|g\u000e\u0005\u0002J\u00196\t!JC\u0001L\u0003\u0015\u00198-\u00197b\u0013\ti%JA\u0004Qe>$Wo\u0019;\u0011\u0005=CfB\u0001)W\u001d\t\tV+D\u0001S\u0015\t\u0019F+\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005Y\u0015BA,K\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0017.\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005]S\u0015!\u00014\u0016\u0003u\u0003\"!\u00130\n\u0005}S%AB!osJ+g-\u0001\u0002gA\u0005AA-\u0019;b)f\u0004X-F\u0001d!\t!w-D\u0001f\u0015\t17(A\u0003usB,7/\u0003\u0002iK\nAA)\u0019;b)f\u0004X-A\u0005eCR\fG+\u001f9fA\u0005i\u0011N\u001c9vi\u0016s7m\u001c3feN,\u0012\u0001\u001c\t\u0004\u001f6|\u0017B\u00018[\u0005\r\u0019V-\u001d\t\u0004\u0013B\u0014\u0018BA9K\u0005\u0019y\u0005\u000f^5p]B\u00121/\u001f\t\u0004iV<X\"A\u001e\n\u0005Y\\$aB#oG>$WM\u001d\t\u0003qfd\u0001\u0001B\u0005{\r\u0005\u0005\t\u0011!B\u0001y\n\u0019q\fJ\u0019\u0002\u001d%t\u0007/\u001e;F]\u000e|G-\u001a:tAE\u0019Q0!\u0001\u0011\u0005%s\u0018BA@K\u0005\u001dqu\u000e\u001e5j]\u001e\u00042!SA\u0002\u0013\r\t)A\u0013\u0002\u0004\u0003:L\u0018!D8viB,H/\u00128d_\u0012,'/\u0006\u0002\u0002\fA!\u0011\n]A\u0007a\u0011\ty!a\u0005\u0011\tQ,\u0018\u0011\u0003\t\u0004q\u0006MAACA\u000b\u0011\u0005\u0005\t\u0011!B\u0001y\n\u0019q\f\n\u001a\u0002\u001d=,H\u000f];u\u000b:\u001cw\u000eZ3sA\u0005Iq-\u001b<f]:\u000bW.Z\u000b\u0003\u0003;\u0001B!\u00139\u0002 A!\u0011\u0011EA\u0015\u001d\u0011\t\u0019#!\n\u0011\u0005ES\u0015bAA\u0014\u0015\u00061\u0001K]3eK\u001aLA!a\u000b\u0002.\t11\u000b\u001e:j]\u001eT1!a\nK\u0003)9\u0017N^3o\u001d\u0006lW\rI\u0001\t]VdG.\u00192mKV\u0011\u0011Q\u0007\t\u0004\u0013\u0006]\u0012bAA\u001d\u0015\n9!i\\8mK\u0006t\u0017!\u00038vY2\f'\r\\3!\u00035!W\r^3s[&t\u0017n\u001d;jG\u0006qA-\u001a;fe6Lg.[:uS\u000e\u0004\u0013A\u0002\u001fj]&$h\b\u0006\t\u0002F\u0005\u001d\u0013\u0011JA&\u00033\n)'a\u001a\u0002jA\u0011Q\t\u0001\u0005\u00067>\u0001\r!\u0018\u0005\u0006C>\u0001\ra\u0019\u0005\tU>\u0001\n\u00111\u0001\u0002NA!q*\\A(!\u0011I\u0005/!\u00151\t\u0005M\u0013q\u000b\t\u0005iV\f)\u0006E\u0002y\u0003/\"!B_A&\u0003\u0003\u0005\tQ!\u0001}\u0011%\t9a\u0004I\u0001\u0002\u0004\tY\u0006\u0005\u0003Ja\u0006u\u0003\u0007BA0\u0003G\u0002B\u0001^;\u0002bA\u0019\u00010a\u0019\u0005\u0017\u0005U\u0011\u0011LA\u0001\u0002\u0003\u0015\t\u0001 \u0005\n\u00033y\u0001\u0013!a\u0001\u0003;A\u0011\"!\r\u0010!\u0003\u0005\r!!\u000e\t\u0013\u0005ur\u0002%AA\u0002\u0005U\u0012\u0001C<ji\"t\u0015-\\3\u0015\t\u0005\u0015\u0013q\u000e\u0005\b\u0003c\u0002\u0002\u0019AA\u0010\u0003\u0011q\u0017-\\3\u0002\u001b\u0005\u001chj\u001c8Ok2d\u0017M\u00197f)\t\t)%\u0001\nbg:{g\u000eZ3uKJl\u0017N\\5ti&\u001cWCAA\u0010\u0003\u0011\u0019w\u000e]=\u0015!\u0005\u0015\u0013qPAA\u0003\u0007\u000b))a\"\u0002\n\u0006-\u0005bB.\u0015!\u0003\u0005\r!\u0018\u0005\bCR\u0001\n\u00111\u0001d\u0011!QG\u0003%AA\u0002\u00055\u0003\"CA\u0004)A\u0005\t\u0019AA.\u0011%\tI\u0002\u0006I\u0001\u0002\u0004\ti\u0002C\u0005\u00022Q\u0001\n\u00111\u0001\u00026!I\u0011Q\b\u000b\u0011\u0002\u0003\u0007\u0011QG\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\t\tJK\u0002^\u0003'[#!!&\u0011\t\u0005]\u0015\u0011U\u0007\u0003\u00033SA!a'\u0002\u001e\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003?S\u0015AC1o]>$\u0018\r^5p]&!\u00111UAM\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\tIKK\u0002d\u0003'\u000babY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u00020*\u001aA.a%\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%iU\u0011\u0011Q\u0017\u0016\u0005\u0003\u0017\t\u0019*\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001b\u0016\u0005\u0005m&\u0006BA\u000f\u0003'\u000babY8qs\u0012\"WMZ1vYR$c'\u0006\u0002\u0002B*\"\u0011QGAJ\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uI]\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAAe!\u0011\tY-!6\u000e\u0005\u00055'\u0002BAh\u0003#\fA\u0001\\1oO*\u0011\u00111[\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002,\u00055\u0017\u0001\u00049s_\u0012,8\r^!sSRLXCAAn!\rI\u0015Q\\\u0005\u0004\u0003?T%aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\u0001\u0003KD\u0011\"a:\u001f\u0003\u0003\u0005\r!a7\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\ti\u000f\u0005\u0004\u0002p\u0006U\u0018\u0011A\u0007\u0003\u0003cT1!a=K\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003o\f\tP\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u001b\u0003{D\u0011\"a:!\u0003\u0003\u0005\r!!\u0001\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003\u0013\u0014\u0019\u0001C\u0005\u0002h\u0006\n\t\u00111\u0001\u0002\\\u0006A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\\\u0006AAo\\*ue&tw\r\u0006\u0002\u0002J\u00061Q-];bYN$B!!\u000e\u0003\u0012!I\u0011q\u001d\u0013\u0002\u0002\u0003\u0007\u0011\u0011A\u0001\u0019'B\f'o[+tKJ$UMZ5oK\u00124UO\\2uS>t\u0007CA#''\u00111SL!\u0007\u0011\t\tm!\u0011E\u0007\u0003\u0005;QAAa\b\u0002R\u0006\u0011\u0011n\\\u0005\u00043\nuAC\u0001B\u000b\u0003\u0015\t\u0007\u000f\u001d7z)!\t)E!\u000b\u0003.\t\r\u0004B\u0002B\u0016Q\u0001\u0007Q,\u0001\u0005gk:\u001cG/[8o\u0011\u001d\u0011y\u0003\u000ba\u0001\u0005c\tQB]3ukJtG+\u001f9f)\u0006<\u0007\u0007\u0002B\u001a\u0005?\u0002bA!\u000e\u0003R\tuc\u0002\u0002B\u001c\u0005\u0017rAA!\u000f\u0003H9!!1\bB!\u001d\r\u0001&QH\u0005\u0004\u0005\u007fQ\u0015a\u0002:fM2,7\r^\u0005\u0005\u0005\u0007\u0012)%A\u0004sk:$\u0018.\\3\u000b\u0007\t}\"*C\u0002X\u0005\u0013RAAa\u0011\u0003F%!!Q\nB(\u0003!)h.\u001b<feN,'bA,\u0003J%!!1\u000bB+\u0005\u001d!\u0016\u0010]3UC\u001eLAAa\u0016\u0003Z\tAA+\u001f9f)\u0006<7O\u0003\u0003\u0003\\\t\u0015\u0013aA1qSB\u0019\u0001Pa\u0018\u0005\u0017\t\u0005$QFA\u0001\u0002\u0003\u0015\t\u0001 \u0002\u0004?\u0012\u001a\u0004b\u0002B3Q\u0001\u0007!qM\u0001\u000eS:\u0004X\u000f\u001e+za\u0016$\u0016mZ:\u0011\u000b%\u0013IG!\u001c\n\u0007\t-$J\u0001\u0006=e\u0016\u0004X-\u0019;fIz\u0002DAa\u001c\u0003tA1!Q\u0007B)\u0005c\u00022\u0001\u001fB:\t-\u0011)Ha\u0019\u0002\u0002\u0003\u0005)\u0011\u0001?\u0003\u0007}#C\u0007\u0006\u0005\u0002F\te$1\u0010BL\u0011\u0019\u0011Y#\u000ba\u0001;\"1!.\u000ba\u0001\u0005{\u0002BaT7\u0003\u0000A\"!\u0011\u0011BJ!\u0019\u0011\u0019I!$\u0003\u00126\u0011!Q\u0011\u0006\u0005\u0005\u000f\u0013I)\u0001\u0005f]\u000e|G-\u001a:t\u0015\r\u0011YiO\u0001\tG\u0006$\u0018\r\\=ti&!!q\u0012BC\u0005=\tuM\\8ti&\u001cWI\\2pI\u0016\u0014\bc\u0001=\u0003\u0014\u0012Y!Q\u0013B>\u0003\u0003\u0005\tQ!\u0001}\u0005\ryF%\u000e\u0005\b\u0003\u000fI\u0003\u0019\u0001BMa\u0011\u0011YJa(\u0011\r\t\r%Q\u0012BO!\rA(q\u0014\u0003\f\u0005C\u00139*!A\u0001\u0002\u000b\u0005APA\u0002`IY\"\u0002\"!\u0012\u0003&\n\u001d&1\u0016\u0005\u0007\u0005WQ\u0003\u0019A/\t\r\t%&\u00061\u0001d\u0003)\u0011X\r^;s]RK\b/\u001a\u0005\b\u0005[S\u0003\u0019AAn\u0003-\u0019\u0017M\u001d3j]\u0006d\u0017\u000e^=\u0015!\u0005\u0015#\u0011\u0017BZ\u0005k\u0013\u0019Ma4\u0003R\nM\u0007\"B.,\u0001\u0004i\u0006\"B1,\u0001\u0004\u0019\u0007\u0002\u00036,!\u0003\u0005\rAa.\u0011\t=k'\u0011\u0018\t\u0005\u0013B\u0014Y\f\r\u0003\u0003>\n\u0005\u0007\u0003\u0002;v\u0005\u007f\u00032\u0001\u001fBa\t)Q(QWA\u0001\u0002\u0003\u0015\t\u0001 \u0005\n\u0003\u000fY\u0003\u0013!a\u0001\u0005\u000b\u0004B!\u00139\u0003HB\"!\u0011\u001aBg!\u0011!XOa3\u0011\u0007a\u0014i\rB\u0006\u0002\u0016\t\r\u0017\u0011!A\u0001\u0006\u0003a\b\"CA\rWA\u0005\t\u0019AA\u000f\u0011%\t\td\u000bI\u0001\u0002\u0004\t)\u0004C\u0005\u0002>-\u0002\n\u00111\u0001\u00026\u0005y\u0011\r\u001d9ms\u0012\"WMZ1vYR$3'\u0006\u0002\u0003Z*\"!1\\AJ!\u0011yUN!8\u0011\t%\u0003(q\u001c\u0019\u0005\u0005C\u0014)\u000f\u0005\u0003uk\n\r\bc\u0001=\u0003f\u0012I!\u0010LA\u0001\u0002\u0003\u0015\t\u0001`\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%iU\u0011!1\u001e\u0016\u0005\u0005[\f\u0019\n\u0005\u0003Ja\n=\b\u0007\u0002By\u0005k\u0004B\u0001^;\u0003tB\u0019\u0001P!>\u0005\u0015\u0005UQ&!A\u0001\u0002\u000b\u0005A0A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00136\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u00122\u0014aD1qa2LH\u0005Z3gCVdG\u000fJ\u001c\u0002\u000fUt\u0017\r\u001d9msR!1\u0011AB\u0010!\u0011I\u0005oa\u0001\u0011\u001f%\u001b)!X2\u0004\n\rU\u0011QDA\u001b\u0003kI1aa\u0002K\u0005\u0019!V\u000f\u001d7foA!q*\\B\u0006!\u0011I\u0005o!\u00041\t\r=11\u0003\t\u0005iV\u001c\t\u0002E\u0002y\u0007'!\u0011B_\u0019\u0002\u0002\u0003\u0005)\u0011\u0001?\u0011\t%\u00038q\u0003\u0019\u0005\u00073\u0019i\u0002\u0005\u0003uk\u000em\u0001c\u0001=\u0004\u001e\u0011Q\u0011QC\u0019\u0002\u0002\u0003\u0005)\u0011\u0001?\t\u0013\r\u0005\u0012'!AA\u0002\u0005\u0015\u0013a\u0001=%a\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIM*\"aa\n+\t\r%\u00121\u0013\t\u0005\u001f6\u001cY\u0003\u0005\u0003Ja\u000e5\u0002\u0007BB\u0018\u0007g\u0001B\u0001^;\u00042A\u0019\u0001pa\r\u0005\u0013i\u0014\u0014\u0011!A\u0001\u0006\u0003a\u0018a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$C'\u0006\u0002\u0004:)\"11HAJ!\u0011I\u0005o!\u00101\t\r}21\t\t\u0005iV\u001c\t\u0005E\u0002y\u0007\u0007\"!\"!\u00064\u0003\u0003\u0005\tQ!\u0001}\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%k\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIY\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012:\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAB(!\u0011\tYm!\u0015\n\t\rM\u0013Q\u001a\u0002\u0007\u001f\nTWm\u0019;"
)
public class SparkUserDefinedFunction extends UserDefinedFunction implements Product, Serializable {
   private final Object f;
   private final DataType dataType;
   private final Seq inputEncoders;
   private final Option outputEncoder;
   private final Option givenName;
   private final boolean nullable;
   private final boolean deterministic;

   public static boolean $lessinit$greater$default$7() {
      return SparkUserDefinedFunction$.MODULE$.$lessinit$greater$default$7();
   }

   public static boolean $lessinit$greater$default$6() {
      return SparkUserDefinedFunction$.MODULE$.$lessinit$greater$default$6();
   }

   public static Option $lessinit$greater$default$5() {
      return SparkUserDefinedFunction$.MODULE$.$lessinit$greater$default$5();
   }

   public static Option $lessinit$greater$default$4() {
      return SparkUserDefinedFunction$.MODULE$.$lessinit$greater$default$4();
   }

   public static Seq $lessinit$greater$default$3() {
      return SparkUserDefinedFunction$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option unapply(final SparkUserDefinedFunction x$0) {
      return SparkUserDefinedFunction$.MODULE$.unapply(x$0);
   }

   public static boolean apply$default$7() {
      return SparkUserDefinedFunction$.MODULE$.apply$default$7();
   }

   public static boolean apply$default$6() {
      return SparkUserDefinedFunction$.MODULE$.apply$default$6();
   }

   public static Option apply$default$5() {
      return SparkUserDefinedFunction$.MODULE$.apply$default$5();
   }

   public static Option apply$default$4() {
      return SparkUserDefinedFunction$.MODULE$.apply$default$4();
   }

   public static Seq apply$default$3() {
      return SparkUserDefinedFunction$.MODULE$.apply$default$3();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Object f() {
      return this.f;
   }

   public DataType dataType() {
      return this.dataType;
   }

   public Seq inputEncoders() {
      return this.inputEncoders;
   }

   public Option outputEncoder() {
      return this.outputEncoder;
   }

   public Option givenName() {
      return this.givenName;
   }

   public boolean nullable() {
      return this.nullable;
   }

   public boolean deterministic() {
      return this.deterministic;
   }

   public SparkUserDefinedFunction withName(final String name) {
      Option x$1 = .MODULE$.apply(name);
      Object x$2 = this.copy$default$1();
      DataType x$3 = this.copy$default$2();
      Seq x$4 = this.copy$default$3();
      Option x$5 = this.copy$default$4();
      boolean x$6 = this.copy$default$6();
      boolean x$7 = this.copy$default$7();
      return this.copy(x$2, x$3, x$4, x$5, x$1, x$6, x$7);
   }

   public SparkUserDefinedFunction asNonNullable() {
      if (!this.nullable()) {
         return this;
      } else {
         boolean x$1 = false;
         Object x$2 = this.copy$default$1();
         DataType x$3 = this.copy$default$2();
         Seq x$4 = this.copy$default$3();
         Option x$5 = this.copy$default$4();
         Option x$6 = this.copy$default$5();
         boolean x$7 = this.copy$default$7();
         return this.copy(x$2, x$3, x$4, x$5, x$6, false, x$7);
      }
   }

   public SparkUserDefinedFunction asNondeterministic() {
      if (!this.deterministic()) {
         return this;
      } else {
         boolean x$1 = false;
         Object x$2 = this.copy$default$1();
         DataType x$3 = this.copy$default$2();
         Seq x$4 = this.copy$default$3();
         Option x$5 = this.copy$default$4();
         Option x$6 = this.copy$default$5();
         boolean x$7 = this.copy$default$6();
         return this.copy(x$2, x$3, x$4, x$5, x$6, x$7, false);
      }
   }

   public String name() {
      return (String)this.givenName().getOrElse(() -> "UDF");
   }

   public SparkUserDefinedFunction copy(final Object f, final DataType dataType, final Seq inputEncoders, final Option outputEncoder, final Option givenName, final boolean nullable, final boolean deterministic) {
      return new SparkUserDefinedFunction(f, dataType, inputEncoders, outputEncoder, givenName, nullable, deterministic);
   }

   public Object copy$default$1() {
      return this.f();
   }

   public DataType copy$default$2() {
      return this.dataType();
   }

   public Seq copy$default$3() {
      return this.inputEncoders();
   }

   public Option copy$default$4() {
      return this.outputEncoder();
   }

   public Option copy$default$5() {
      return this.givenName();
   }

   public boolean copy$default$6() {
      return this.nullable();
   }

   public boolean copy$default$7() {
      return this.deterministic();
   }

   public String productPrefix() {
      return "SparkUserDefinedFunction";
   }

   public int productArity() {
      return 7;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.f();
         }
         case 1 -> {
            return this.dataType();
         }
         case 2 -> {
            return this.inputEncoders();
         }
         case 3 -> {
            return this.outputEncoder();
         }
         case 4 -> {
            return this.givenName();
         }
         case 5 -> {
            return BoxesRunTime.boxToBoolean(this.nullable());
         }
         case 6 -> {
            return BoxesRunTime.boxToBoolean(this.deterministic());
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof SparkUserDefinedFunction;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "f";
         }
         case 1 -> {
            return "dataType";
         }
         case 2 -> {
            return "inputEncoders";
         }
         case 3 -> {
            return "outputEncoder";
         }
         case 4 -> {
            return "givenName";
         }
         case 5 -> {
            return "nullable";
         }
         case 6 -> {
            return "deterministic";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.f()));
      var1 = Statics.mix(var1, Statics.anyHash(this.dataType()));
      var1 = Statics.mix(var1, Statics.anyHash(this.inputEncoders()));
      var1 = Statics.mix(var1, Statics.anyHash(this.outputEncoder()));
      var1 = Statics.mix(var1, Statics.anyHash(this.givenName()));
      var1 = Statics.mix(var1, this.nullable() ? 1231 : 1237);
      var1 = Statics.mix(var1, this.deterministic() ? 1231 : 1237);
      return Statics.finalizeHash(var1, 7);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var12;
      if (this != x$1) {
         label83: {
            if (x$1 instanceof SparkUserDefinedFunction) {
               SparkUserDefinedFunction var4 = (SparkUserDefinedFunction)x$1;
               if (this.nullable() == var4.nullable() && this.deterministic() == var4.deterministic() && BoxesRunTime.equals(this.f(), var4.f())) {
                  label76: {
                     DataType var10000 = this.dataType();
                     DataType var5 = var4.dataType();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label76;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label76;
                     }

                     Seq var9 = this.inputEncoders();
                     Seq var6 = var4.inputEncoders();
                     if (var9 == null) {
                        if (var6 != null) {
                           break label76;
                        }
                     } else if (!var9.equals(var6)) {
                        break label76;
                     }

                     Option var10 = this.outputEncoder();
                     Option var7 = var4.outputEncoder();
                     if (var10 == null) {
                        if (var7 != null) {
                           break label76;
                        }
                     } else if (!var10.equals(var7)) {
                        break label76;
                     }

                     var10 = this.givenName();
                     Option var8 = var4.givenName();
                     if (var10 == null) {
                        if (var8 != null) {
                           break label76;
                        }
                     } else if (!var10.equals(var8)) {
                        break label76;
                     }

                     if (var4.canEqual(this)) {
                        break label83;
                     }
                  }
               }
            }

            var12 = false;
            return var12;
         }
      }

      var12 = true;
      return var12;
   }

   public SparkUserDefinedFunction(final Object f, final DataType dataType, final Seq inputEncoders, final Option outputEncoder, final Option givenName, final boolean nullable, final boolean deterministic) {
      this.f = f;
      this.dataType = dataType;
      this.inputEncoders = inputEncoders;
      this.outputEncoder = outputEncoder;
      this.givenName = givenName;
      this.nullable = nullable;
      this.deterministic = deterministic;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
