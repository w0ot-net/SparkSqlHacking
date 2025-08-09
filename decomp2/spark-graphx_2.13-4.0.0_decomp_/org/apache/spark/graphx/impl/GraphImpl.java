package org.apache.spark.graphx.impl;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.HashPartitioner;
import org.apache.spark.graphx.EdgeDirection;
import org.apache.spark.graphx.EdgeDirection$;
import org.apache.spark.graphx.EdgeRDD;
import org.apache.spark.graphx.EdgeTriplet;
import org.apache.spark.graphx.Graph;
import org.apache.spark.graphx.PartitionStrategy;
import org.apache.spark.graphx.TripletFields;
import org.apache.spark.graphx.VertexRDD;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u00115e\u0001\u0002\u00180\u0001iB\u0001\"\u0018\u0001\u0003\u0006\u0004%\tA\u0018\u0005\tE\u0002\u0011\t\u0011)A\u0005?\"Aq\r\u0001BC\u0002\u0013\u0005\u0001\u000e\u0003\u0005n\u0001\t\u0005\t\u0015!\u0003j\u0011!y\u0007AaA!\u0002\u0017\u0001\b\u0002\u0003<\u0001\u0005\u0007\u0005\u000b1B<\t\u000ba\u0004A\u0011C=\t\ra\u0004A\u0011CA\u0001\u0011%\ti\u0001\u0001b\u0001\n\u0003\ny\u0001\u0003\u0005\u0002\u0018\u0001\u0001\u000b\u0011BA\t\u0011)\tY\u0002\u0001EC\u0002\u0013\u0005\u0013Q\u0004\u0005\b\u0003g\u0001A\u0011IA\u001b\u0011\u001d\t9\u0005\u0001C!\u0003\u0013Bq!a\u0013\u0001\t\u0003\ni\u0005C\u0004\u0002V\u0001!\t%a\u0016\t\u000f\u0005}\u0003\u0001\"\u0011\u0002b!9\u0011\u0011\u0010\u0001\u0005B\u0005m\u0004\"CAA\u0001E\u0005I\u0011AAB\u0011\u001d\tI\n\u0001C!\u00037C\u0011\"a(\u0001#\u0003%\t!a!\t\u000f\u0005\u0005\u0006\u0001\"\u0011\u0002$\"9\u0011\u0011\u0015\u0001\u0005B\u0005=\u0006bBA_\u0001\u0011\u0005\u0013q\u0018\u0005\b\u0003\u0003\u0004A\u0011IAb\u0011%\u00119\u0001AI\u0001\n\u0003\u0011I\u0001C\u0004\u0003\u001c\u0001!\tE!\b\t\u000f\t%\u0003\u0001\"\u0011\u0003L!9!q\u000e\u0001\u0005B\tE\u0004\"\u0003BB\u0001E\u0005I\u0011\u0001BC\u0011%\u0011I\tAI\u0001\n\u0003\u0011Y\tC\u0004\u0003\u0010\u0002!\tE!%\t\u000f\tE\u0006\u0001\"\u0011\u00034\"9!1\u0018\u0001\u0005B\tu\u0006bBB\u0004\u0001\u0011\u00053\u0011\u0002\u0005\n\u0007\u007f\u0001\u0011\u0013!C\u0001\u0007\u0003:qaa\u00170\u0011\u0003\u0019iF\u0002\u0004/_!\u00051q\f\u0005\u0007q\u0016\"\ta!\u001e\t\u000f\r]T\u0005\"\u0001\u0004z!91qU\u0013\u0005\u0002\r%\u0006bBB<K\u0011\u00051\u0011\u001c\u0005\b\u0007o*C\u0011\u0001C\u0004\u0011\u001d!y#\nC\u0001\tcAq\u0001\"\u0016&\t\u0013!9\u0006C\u0005\u0005~\u0015\n\t\u0011\"\u0003\u0005\u0000\tIqI]1qQ&k\u0007\u000f\u001c\u0006\u0003aE\nA![7qY*\u0011!gM\u0001\u0007OJ\f\u0007\u000f\u001b=\u000b\u0005Q*\u0014!B:qCJ\\'B\u0001\u001c8\u0003\u0019\t\u0007/Y2iK*\t\u0001(A\u0002pe\u001e\u001c\u0001!F\u0002<\u0005>\u001b2\u0001\u0001\u001fR!\u0011id\b\u0011(\u000e\u0003EJ!aP\u0019\u0003\u000b\u001d\u0013\u0018\r\u001d5\u0011\u0005\u0005\u0013E\u0002\u0001\u0003\u0006\u0007\u0002\u0011\r\u0001\u0012\u0002\u0003-\u0012\u000b\"!R&\u0011\u0005\u0019KU\"A$\u000b\u0003!\u000bQa]2bY\u0006L!AS$\u0003\u000f9{G\u000f[5oOB\u0011a\tT\u0005\u0003\u001b\u001e\u00131!\u00118z!\t\tu\nB\u0003Q\u0001\t\u0007AI\u0001\u0002F\tB\u0011!K\u0017\b\u0003'bs!\u0001V,\u000e\u0003US!AV\u001d\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0015BA-H\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0017/\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005e;\u0015\u0001\u0003<feRL7-Z:\u0016\u0003}\u00032!\u00101A\u0013\t\t\u0017GA\u0005WKJ$X\r\u001f*E\t\u0006Ia/\u001a:uS\u000e,7\u000f\t\u0015\u0003\u0005\u0011\u0004\"AR3\n\u0005\u0019<%!\u0003;sC:\u001c\u0018.\u001a8u\u0003Q\u0011X\r\u001d7jG\u0006$X\r\u001a,feR,\u0007PV5foV\t\u0011\u000e\u0005\u0003kW\u0002sU\"A\u0018\n\u00051|#\u0001\u0006*fa2L7-\u0019;fIZ+'\u000f^3y-&,w/A\u000bsKBd\u0017nY1uK\u00124VM\u001d;fqZKWm\u001e\u0011)\u0005\u0011!\u0017AC3wS\u0012,gnY3%cA\u0019\u0011\u000f\u001e!\u000e\u0003IT!a]$\u0002\u000fI,g\r\\3di&\u0011QO\u001d\u0002\t\u00072\f7o\u001d+bO\u0006QQM^5eK:\u001cW\r\n\u001a\u0011\u0007E$h*\u0001\u0004=S:LGO\u0010\u000b\u0004uz|HcA>}{B!!\u000e\u0001!O\u0011\u0015yw\u0001q\u0001q\u0011\u00151x\u0001q\u0001x\u0011\u0015iv\u00011\u0001`\u0011\u00159w\u00011\u0001j)\t\t\u0019\u0001F\u0003|\u0003\u000b\tI\u0001\u0003\u0005\u0002\b!\t\t\u0011q\u0001q\u0003))g/\u001b3f]\u000e,Ge\r\u0005\t\u0003\u0017A\u0011\u0011!a\u0002o\u0006QQM^5eK:\u001cW\r\n\u001b\u0002\u000b\u0015$w-Z:\u0016\u0005\u0005E\u0001#\u00026\u0002\u00149\u0003\u0015bAA\u000b_\tYQ\tZ4f%\u0012#\u0015*\u001c9m\u0003\u0019)GmZ3tA!\u0012!\u0002Z\u0001\tiJL\u0007\u000f\\3ugV\u0011\u0011q\u0004\t\u0007\u0003C\t9#a\u000b\u000e\u0005\u0005\r\"bAA\u0013g\u0005\u0019!\u000f\u001a3\n\t\u0005%\u00121\u0005\u0002\u0004%\u0012#\u0005#B\u001f\u0002.\u0001s\u0015bAA\u0018c\tYQ\tZ4f)JL\u0007\u000f\\3uQ\tYA-A\u0004qKJ\u001c\u0018n\u001d;\u0015\u0007q\n9\u0004C\u0005\u0002:1\u0001\n\u00111\u0001\u0002<\u0005Aa.Z<MKZ,G\u000e\u0005\u0003\u0002>\u0005\rSBAA \u0015\r\t\teM\u0001\bgR|'/Y4f\u0013\u0011\t)%a\u0010\u0003\u0019M#xN]1hK2+g/\u001a7\u0002\u000b\r\f7\r[3\u0015\u0003q\n!b\u00195fG.\u0004x.\u001b8u)\t\ty\u0005E\u0002G\u0003#J1!a\u0015H\u0005\u0011)f.\u001b;\u0002\u001d%\u001c8\t[3dWB|\u0017N\u001c;fIV\u0011\u0011\u0011\f\t\u0004\r\u0006m\u0013bAA/\u000f\n9!i\\8mK\u0006t\u0017AE4fi\u000eCWmY6q_&tGOR5mKN,\"!a\u0019\u0011\u000bI\u000b)'!\u001b\n\u0007\u0005\u001dDLA\u0002TKF\u0004B!a\u001b\u0002t9!\u0011QNA8!\t!v)C\u0002\u0002r\u001d\u000ba\u0001\u0015:fI\u00164\u0017\u0002BA;\u0003o\u0012aa\u0015;sS:<'bAA9\u000f\u0006IQO\u001c9feNL7\u000f\u001e\u000b\u0004y\u0005u\u0004\"CA@#A\u0005\t\u0019AA-\u0003!\u0011Gn\\2lS:<\u0017aE;oa\u0016\u00148/[:uI\u0011,g-Y;mi\u0012\nTCAACU\u0011\tI&a\",\u0005\u0005%\u0005\u0003BAF\u0003+k!!!$\u000b\t\u0005=\u0015\u0011S\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a%H\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003/\u000biIA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f\u0011#\u001e8qKJ\u001c\u0018n\u001d;WKJ$\u0018nY3t)\ra\u0014Q\u0014\u0005\n\u0003\u007f\u001a\u0002\u0013!a\u0001\u00033\n1$\u001e8qKJ\u001c\u0018n\u001d;WKJ$\u0018nY3tI\u0011,g-Y;mi\u0012\n\u0014a\u00039beRLG/[8o\u0005f$2\u0001PAS\u0011\u001d\t9+\u0006a\u0001\u0003S\u000b\u0011\u0003]1si&$\u0018n\u001c8TiJ\fG/Z4z!\ri\u00141V\u0005\u0004\u0003[\u000b$!\u0005)beRLG/[8o'R\u0014\u0018\r^3hsR)A(!-\u00024\"9\u0011q\u0015\fA\u0002\u0005%\u0006bBA[-\u0001\u0007\u0011qW\u0001\u000e]Vl\u0007+\u0019:uSRLwN\\:\u0011\u0007\u0019\u000bI,C\u0002\u0002<\u001e\u00131!\u00138u\u0003\u001d\u0011XM^3sg\u0016,\u0012\u0001P\u0001\f[\u0006\u0004h+\u001a:uS\u000e,7/\u0006\u0003\u0002F\u00065G\u0003BAd\u0003C$b!!3\u0002R\u0006]\u0007#B\u001f?\u0003\u0017t\u0005cA!\u0002N\u00121\u0011q\u001a\rC\u0002\u0011\u00131A\u0016#3\u0011%\t\u0019\u000eGA\u0001\u0002\b\t).\u0001\u0006fm&$WM\\2fIU\u0002B!\u001d;\u0002L\"I\u0011\u0011\u001c\r\u0011\u0002\u0003\u000f\u00111\\\u0001\u0003KF\u0004bARAo\u0001\u0006-\u0017bAAp\u000f\naA%Z9%G>dwN\u001c\u0013fc\"9\u00111\u001d\rA\u0002\u0005\u0015\u0018!\u00014\u0011\u0011\u0019\u000b9/a;A\u0003\u0017L1!!;H\u0005%1UO\\2uS>t'\u0007\u0005\u0003\u0002n\n\u0005a\u0002BAx\u0003\u007ftA!!=\u0002~:!\u00111_A~\u001d\u0011\t)0!?\u000f\u0007Q\u000b90C\u00019\u0013\t1t'\u0003\u00025k%\u0011!gM\u0005\u00033FJAAa\u0001\u0003\u0006\tAa+\u001a:uKbLEM\u0003\u0002Zc\u0005)R.\u00199WKJ$\u0018nY3tI\u0011,g-Y;mi\u0012\u001aT\u0003\u0002B\u0006\u00053!BA!\u0004\u0003\u0014)\"!qBAD\u001f\t\u0011\tB\t\u0001\t\u000f\u0005\r\u0018\u00041\u0001\u0003\u0016AAa)a:\u0002l\u0002\u00139\u0002E\u0002B\u00053!a!a4\u001a\u0005\u0004!\u0015\u0001C7ba\u0016#w-Z:\u0016\t\t}!q\u0005\u000b\u0005\u0005C\u0011\t\u0004\u0006\u0003\u0003$\t-\u0002#B\u001f?\u0001\n\u0015\u0002cA!\u0003(\u00111!\u0011\u0006\u000eC\u0002\u0011\u00131!\u0012#3\u0011%\u0011iCGA\u0001\u0002\b\u0011y#\u0001\u0006fm&$WM\\2fIY\u0002B!\u001d;\u0003&!9\u00111\u001d\u000eA\u0002\tM\u0002#\u0003$\u0002h\nU\"1\bB$!\u0011\tiOa\u000e\n\t\te\"Q\u0001\u0002\f!\u0006\u0014H/\u001b;j_:LE\tE\u0003S\u0005{\u0011\t%C\u0002\u0003@q\u0013\u0001\"\u0013;fe\u0006$xN\u001d\t\u0005{\t\rc*C\u0002\u0003FE\u0012A!\u00123hKB)!K!\u0010\u0003&\u0005YQ.\u00199Ue&\u0004H.\u001a;t+\u0011\u0011iE!\u0016\u0015\r\t=#Q\fB3)\u0011\u0011\tFa\u0016\u0011\u000bur\u0004Ia\u0015\u0011\u0007\u0005\u0013)\u0006\u0002\u0004\u0003*m\u0011\r\u0001\u0012\u0005\n\u00053Z\u0012\u0011!a\u0002\u00057\n!\"\u001a<jI\u0016t7-\u001a\u00138!\u0011\tHOa\u0015\t\u000f\u0005\r8\u00041\u0001\u0003`AIa)a:\u00036\t\u0005$1\r\t\u0006%\nu\u00121\u0006\t\u0006%\nu\"1\u000b\u0005\b\u0005OZ\u0002\u0019\u0001B5\u00035!(/\u001b9mKR4\u0015.\u001a7egB\u0019QHa\u001b\n\u0007\t5\u0014GA\u0007Ue&\u0004H.\u001a;GS\u0016dGm]\u0001\tgV\u0014wM]1qQR)AHa\u001d\u0003~!I!Q\u000f\u000f\u0011\u0002\u0003\u0007!qO\u0001\u0006KB\u0014X\r\u001a\t\b\r\ne\u00141FA-\u0013\r\u0011Yh\u0012\u0002\n\rVt7\r^5p]FB\u0011Ba \u001d!\u0003\u0005\rA!!\u0002\u000bY\u0004(/\u001a3\u0011\u0011\u0019\u000b9/a;A\u00033\n!c];cOJ\f\u0007\u000f\u001b\u0013eK\u001a\fW\u000f\u001c;%cU\u0011!q\u0011\u0016\u0005\u0005o\n9)\u0001\ntk\n<'/\u00199iI\u0011,g-Y;mi\u0012\u0012TC\u0001BGU\u0011\u0011\t)a\"\u0002\t5\f7o[\u000b\u0007\u0005'\u0013yJ!+\u0015\t\tU%1\u0016\u000b\u0006y\t]%\u0011\u0015\u0005\n\u00053{\u0012\u0011!a\u0002\u00057\u000b!\"\u001a<jI\u0016t7-\u001a\u00139!\u0011\tHO!(\u0011\u0007\u0005\u0013y\n\u0002\u0004\u0002P~\u0011\r\u0001\u0012\u0005\n\u0005G{\u0012\u0011!a\u0002\u0005K\u000b!\"\u001a<jI\u0016t7-\u001a\u0013:!\u0011\tHOa*\u0011\u0007\u0005\u0013I\u000b\u0002\u0004\u0003*}\u0011\r\u0001\u0012\u0005\b\u0005[{\u0002\u0019\u0001BX\u0003\u0015yG\u000f[3s!\u0019idH!(\u0003(\u0006QqM]8va\u0016#w-Z:\u0015\u0007q\u0012)\fC\u0004\u00038\u0002\u0002\rA!/\u0002\u000b5,'oZ3\u0011\r\u0019\u000b9O\u0014(O\u0003y\twm\u001a:fO\u0006$X-T3tg\u0006<Wm],ji\"\f5\r^5wKN+G/\u0006\u0003\u0003@\n\u001dGC\u0003Ba\u0005#\u0014iNa9\u0003fR!!1\u0019Bf!\u0011i\u0004M!2\u0011\u0007\u0005\u00139\r\u0002\u0004\u0003J\u0006\u0012\r\u0001\u0012\u0002\u0002\u0003\"I!QZ\u0011\u0002\u0002\u0003\u000f!qZ\u0001\fKZLG-\u001a8dK\u0012\n\u0004\u0007\u0005\u0003ri\n\u0015\u0007b\u0002BjC\u0001\u0007!Q[\u0001\bg\u0016tG-T:h!\u001d1%\u0011\u0010Bl\u0003\u001f\u0002r!\u0010Bm\u0001:\u0013)-C\u0002\u0003\\F\u00121\"\u00123hK\u000e{g\u000e^3yi\"9!q\\\u0011A\u0002\t\u0005\u0018\u0001C7fe\u001e,Wj]4\u0011\u0013\u0019\u000b9O!2\u0003F\n\u0015\u0007b\u0002B4C\u0001\u0007!\u0011\u000e\u0005\b\u0005O\f\u0003\u0019\u0001Bu\u00031\t7\r^5wKN+Go\u00149u!\u00151%1\u001eBx\u0013\r\u0011io\u0012\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000f\u0019\u0013\tP!>\u0004\u0002%\u0019!1_$\u0003\rQ+\b\u000f\\33a\u0011\u00119Pa?\u0011\tu\u0002'\u0011 \t\u0004\u0003\nmHa\u0003B\u007f\u0005\u007f\f\t\u0011!A\u0003\u0002\u0011\u00131a\u0018\u00132\u0011\u001d\u00119/\ta\u0001\u0005S\u00042!PB\u0002\u0013\r\u0019)!\r\u0002\u000e\u000b\u0012<W\rR5sK\u000e$\u0018n\u001c8\u0002#=,H/\u001a:K_&tg+\u001a:uS\u000e,7/\u0006\u0004\u0004\f\r}1Q\u0003\u000b\u0005\u0007\u001b\u0019I\u0004\u0006\u0003\u0004\u0010\r5B\u0003CB\t\u0007/\u0019\u0019c!\u000b\u0011\u000bur41\u0003(\u0011\u0007\u0005\u001b)\u0002\u0002\u0004\u0002P\n\u0012\r\u0001\u0012\u0005\n\u00073\u0011\u0013\u0011!a\u0002\u00077\t1\"\u001a<jI\u0016t7-\u001a\u00132cA!\u0011\u000f^B\u000f!\r\t5q\u0004\u0003\u0007\u0007C\u0011#\u0019\u0001#\u0003\u0003UC\u0011b!\n#\u0003\u0003\u0005\u001daa\n\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013G\r\t\u0005cR\u001c\u0019\u0002C\u0005\u0002Z\n\u0002\n\u0011q\u0001\u0004,A1a)!8A\u0007'Aqaa\f#\u0001\u0004\u0019\t$A\u0004va\u0012\fG/\u001a$\u0011\u0015\u0019\u001b\u0019$a;A\u0007o\u0019\u0019\"C\u0002\u00046\u001d\u0013\u0011BR;oGRLwN\\\u001a\u0011\u000b\u0019\u0013Yo!\b\t\u000f\t5&\u00051\u0001\u0004<A1\u0011\u0011EA\u0014\u0007{\u0001rA\u0012By\u0003W\u001ci\"A\u000epkR,'OS8j]Z+'\u000f^5dKN$C-\u001a4bk2$H%N\u000b\u0007\u0007\u0007\u001ayea\u0015\u0015\t\r\u00153Q\u000b\u000b\u0005\u0005\u001b\u00199\u0005C\u0004\u00040\r\u0002\ra!\u0013\u0011\u0015\u0019\u001b\u0019$a;A\u0007\u0017\u001a\t\u0006E\u0003G\u0005W\u001ci\u0005E\u0002B\u0007\u001f\"aa!\t$\u0005\u0004!\u0005cA!\u0004T\u00111\u0011qZ\u0012C\u0002\u0011CqA!,$\u0001\u0004\u00199\u0006\u0005\u0004\u0002\"\u0005\u001d2\u0011\f\t\b\r\nE\u00181^B'\u0003%9%/\u00199i\u00136\u0004H\u000e\u0005\u0002kKM)Qe!\u0019\u0004hA\u0019aia\u0019\n\u0007\r\u0015tI\u0001\u0004B]f\u0014VM\u001a\t\u0005\u0007S\u001a\u0019(\u0004\u0002\u0004l)!1QNB8\u0003\tIwN\u0003\u0002\u0004r\u0005!!.\u0019<b\u0013\rY61\u000e\u000b\u0003\u0007;\nQ!\u00199qYf,baa\u001f\u0004\u0004\u000e\u001dECCB?\u0007+\u001bYja(\u0004$R11qPBE\u0007\u001f\u0003bA\u001b\u0001\u0004\u0002\u000e\u0015\u0005cA!\u0004\u0004\u0012)1i\nb\u0001\tB\u0019\u0011ia\"\u0005\u000bA;#\u0019\u0001#\t\u0013\r-u%!AA\u0004\r5\u0015aC3wS\u0012,gnY3%cM\u0002B!\u001d;\u0004\u0002\"I1\u0011S\u0014\u0002\u0002\u0003\u000f11S\u0001\fKZLG-\u001a8dK\u0012\nD\u0007\u0005\u0003ri\u000e\u0015\u0005bBA\u0007O\u0001\u00071q\u0013\t\u0007\u0003C\t9c!'\u0011\u000bu\u0012\u0019e!\"\t\u000f\ruu\u00051\u0001\u0004\u0002\u0006\tB-\u001a4bk2$h+\u001a:uKb\fE\u000f\u001e:\t\u000f\r\u0005v\u00051\u0001\u0002<\u0005\u0001R\rZ4f'R|'/Y4f\u0019\u00164X\r\u001c\u0005\b\u0007K;\u0003\u0019AA\u001e\u0003I1XM\u001d;fqN#xN]1hK2+g/\u001a7\u0002%\u0019\u0014x.\\#eO\u0016\u0004\u0016M\u001d;ji&|gn]\u000b\u0007\u0007W\u001b\u0019la.\u0015\u0015\r56QYBj\u0007+\u001c9\u000e\u0006\u0004\u00040\u000ee6q\u0018\t\u0007U\u0002\u0019\tl!.\u0011\u0007\u0005\u001b\u0019\fB\u0003DQ\t\u0007A\tE\u0002B\u0007o#Q\u0001\u0015\u0015C\u0002\u0011C\u0011ba/)\u0003\u0003\u0005\u001da!0\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013'\u000e\t\u0005cR\u001c\t\fC\u0005\u0004B\"\n\t\u0011q\u0001\u0004D\u0006YQM^5eK:\u001cW\rJ\u00197!\u0011\tHo!.\t\u000f\r\u001d\u0007\u00061\u0001\u0004J\u0006qQ\rZ4f!\u0006\u0014H/\u001b;j_:\u001c\bCBA\u0011\u0003O\u0019Y\rE\u0004G\u0005c\u0014)d!4\u0011\u000f)\u001cym!.\u00042&\u00191\u0011[\u0018\u0003\u001b\u0015#w-\u001a)beRLG/[8o\u0011\u001d\u0019i\n\u000ba\u0001\u0007cCqa!))\u0001\u0004\tY\u0004C\u0004\u0004&\"\u0002\r!a\u000f\u0016\r\rm71]Bt)1\u0019in!>\u0004|\u0012\u0005A1\u0001C\u0003)\u0019\u0019yn!;\u0004pB1!\u000eABq\u0007K\u00042!QBr\t\u0015\u0019\u0015F1\u0001E!\r\t5q\u001d\u0003\u0006!&\u0012\r\u0001\u0012\u0005\n\u0007WL\u0013\u0011!a\u0002\u0007[\f1\"\u001a<jI\u0016t7-\u001a\u00132oA!\u0011\u000f^Bq\u0011%\u0019\t0KA\u0001\u0002\b\u0019\u00190A\u0006fm&$WM\\2fIEB\u0004\u0003B9u\u0007KDa!X\u0015A\u0002\r]\bCBA\u0011\u0003O\u0019I\u0010E\u0004G\u0005c\fYo!9\t\u000f\u00055\u0011\u00061\u0001\u0004~B1\u0011\u0011EA\u0014\u0007\u007f\u0004R!\u0010B\"\u0007KDqa!(*\u0001\u0004\u0019\t\u000fC\u0004\u0004\"&\u0002\r!a\u000f\t\u000f\r\u0015\u0016\u00061\u0001\u0002<U1A\u0011\u0002C\t\t+!b\u0001b\u0003\u0005$\u0011\u001dBC\u0002C\u0007\t/!i\u0002\u0005\u0004k\u0001\u0011=A1\u0003\t\u0004\u0003\u0012EA!B\"+\u0005\u0004!\u0005cA!\u0005\u0016\u0011)\u0001K\u000bb\u0001\t\"IA\u0011\u0004\u0016\u0002\u0002\u0003\u000fA1D\u0001\fKZLG-\u001a8dK\u0012\n\u0014\b\u0005\u0003ri\u0012=\u0001\"\u0003C\u0010U\u0005\u0005\t9\u0001C\u0011\u0003-)g/\u001b3f]\u000e,GE\r\u0019\u0011\tE$H1\u0003\u0005\u0007;*\u0002\r\u0001\"\n\u0011\tu\u0002Gq\u0002\u0005\b\u0003\u001bQ\u0003\u0019\u0001C\u0015!\u0015iD1\u0006C\n\u0013\r!i#\r\u0002\b\u000b\u0012<WM\u0015#E\u0003A1'o\\7Fq&\u001cH/\u001b8h%\u0012#5/\u0006\u0004\u00054\u0011mBq\b\u000b\u0007\tk!i\u0005\"\u0015\u0015\r\u0011]B\u0011\tC$!\u0019Q\u0007\u0001\"\u000f\u0005>A\u0019\u0011\tb\u000f\u0005\u000b\r[#\u0019\u0001#\u0011\u0007\u0005#y\u0004B\u0003QW\t\u0007A\tC\u0005\u0005D-\n\t\u0011q\u0001\u0005F\u0005YQM^5eK:\u001cW\r\n\u001a2!\u0011\tH\u000f\"\u000f\t\u0013\u0011%3&!AA\u0004\u0011-\u0013aC3wS\u0012,gnY3%eI\u0002B!\u001d;\u0005>!1Ql\u000ba\u0001\t\u001f\u0002B!\u00101\u0005:!9\u0011QB\u0016A\u0002\u0011M\u0003#B\u001f\u0005,\u0011u\u0012a\u00034s_6,EmZ3S\t\u0012+b\u0001\"\u0017\u0005b\u0011\u0015DC\u0003C.\tg\"9\b\"\u001f\u0005|Q1AQ\fC4\t[\u0002bA\u001b\u0001\u0005`\u0011\r\u0004cA!\u0005b\u0011)1\t\fb\u0001\tB\u0019\u0011\t\"\u001a\u0005\u000bAc#\u0019\u0001#\t\u0013\u0011%D&!AA\u0004\u0011-\u0014aC3wS\u0012,gnY3%eM\u0002B!\u001d;\u0005`!IAq\u000e\u0017\u0002\u0002\u0003\u000fA\u0011O\u0001\fKZLG-\u001a8dK\u0012\u0012D\u0007\u0005\u0003ri\u0012\r\u0004bBA\u0007Y\u0001\u0007AQ\u000f\t\bU\u0006MA1\rC0\u0011\u001d\u0019i\n\fa\u0001\t?Bqa!)-\u0001\u0004\tY\u0004C\u0004\u0004&2\u0002\r!a\u000f\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0011\u0005\u0005\u0003\u0002CB\t\u0013k!\u0001\"\"\u000b\t\u0011\u001d5qN\u0001\u0005Y\u0006tw-\u0003\u0003\u0005\f\u0012\u0015%AB(cU\u0016\u001cG\u000f"
)
public class GraphImpl extends Graph {
   private transient RDD triplets;
   private final transient VertexRDD vertices;
   private final transient ReplicatedVertexView replicatedVertexView;
   private final ClassTag evidence$1;
   private final ClassTag evidence$2;
   private final transient EdgeRDDImpl edges;
   private transient volatile boolean bitmap$trans$0;

   public static GraphImpl fromExistingRDDs(final VertexRDD vertices, final EdgeRDD edges, final ClassTag evidence$21, final ClassTag evidence$22) {
      return GraphImpl$.MODULE$.fromExistingRDDs(vertices, edges, evidence$21, evidence$22);
   }

   public static GraphImpl apply(final VertexRDD vertices, final EdgeRDD edges, final ClassTag evidence$19, final ClassTag evidence$20) {
      return GraphImpl$.MODULE$.apply(vertices, edges, evidence$19, evidence$20);
   }

   public static GraphImpl apply(final RDD vertices, final RDD edges, final Object defaultVertexAttr, final StorageLevel edgeStorageLevel, final StorageLevel vertexStorageLevel, final ClassTag evidence$17, final ClassTag evidence$18) {
      return GraphImpl$.MODULE$.apply(vertices, edges, defaultVertexAttr, edgeStorageLevel, vertexStorageLevel, evidence$17, evidence$18);
   }

   public static GraphImpl fromEdgePartitions(final RDD edgePartitions, final Object defaultVertexAttr, final StorageLevel edgeStorageLevel, final StorageLevel vertexStorageLevel, final ClassTag evidence$15, final ClassTag evidence$16) {
      return GraphImpl$.MODULE$.fromEdgePartitions(edgePartitions, defaultVertexAttr, edgeStorageLevel, vertexStorageLevel, evidence$15, evidence$16);
   }

   public static GraphImpl apply(final RDD edges, final Object defaultVertexAttr, final StorageLevel edgeStorageLevel, final StorageLevel vertexStorageLevel, final ClassTag evidence$13, final ClassTag evidence$14) {
      return GraphImpl$.MODULE$.apply(edges, defaultVertexAttr, edgeStorageLevel, vertexStorageLevel, evidence$13, evidence$14);
   }

   public VertexRDD vertices() {
      return this.vertices;
   }

   public ReplicatedVertexView replicatedVertexView() {
      return this.replicatedVertexView;
   }

   public EdgeRDDImpl edges() {
      return this.edges;
   }

   private RDD triplets$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            this.replicatedVertexView().upgrade(this.vertices(), true, true);
            RDD qual$1 = this.replicatedVertexView().edges().partitionsRDD();
            Function1 x$1 = (x$1x) -> x$1x.flatMap((x0$1) -> {
                  if (x0$1 != null) {
                     EdgePartition part = (EdgePartition)x0$1._2();
                     return part.tripletIterator(part.tripletIterator$default$1(), part.tripletIterator$default$2());
                  } else {
                     throw new MatchError(x0$1);
                  }
               });
            boolean x$2 = qual$1.mapPartitions$default$2();
            this.triplets = qual$1.mapPartitions(x$1, x$2, .MODULE$.apply(EdgeTriplet.class));
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var6) {
         throw var6;
      }

      return this.triplets;
   }

   public RDD triplets() {
      return !this.bitmap$trans$0 ? this.triplets$lzycompute() : this.triplets;
   }

   public Graph persist(final StorageLevel newLevel) {
      this.vertices().persist(newLevel);
      this.replicatedVertexView().edges().persist(newLevel);
      return this;
   }

   public Graph cache() {
      this.vertices().cache();
      this.replicatedVertexView().edges().cache();
      return this;
   }

   public void checkpoint() {
      this.vertices().checkpoint();
      this.replicatedVertexView().edges().checkpoint();
   }

   public boolean isCheckpointed() {
      return this.vertices().isCheckpointed() && this.replicatedVertexView().edges().isCheckpointed();
   }

   public Seq getCheckpointFiles() {
      return (Seq)(new scala.collection.immutable..colon.colon(this.vertices().getCheckpointFile(), new scala.collection.immutable..colon.colon(this.replicatedVertexView().edges().getCheckpointFile(), scala.collection.immutable.Nil..MODULE$))).flatMap((x0$1) -> {
         if (x0$1 instanceof Some var3) {
            String path = (String)var3.value();
            return new scala.collection.immutable..colon.colon(path, scala.collection.immutable.Nil..MODULE$);
         } else if (scala.None..MODULE$.equals(x0$1)) {
            return scala.package..MODULE$.Seq().empty();
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public Graph unpersist(final boolean blocking) {
      this.unpersistVertices(blocking);
      this.replicatedVertexView().edges().unpersist(blocking);
      return this;
   }

   public boolean unpersist$default$1() {
      return false;
   }

   public Graph unpersistVertices(final boolean blocking) {
      this.vertices().unpersist(blocking);
      return this;
   }

   public boolean unpersistVertices$default$1() {
      return false;
   }

   public Graph partitionBy(final PartitionStrategy partitionStrategy) {
      return this.partitionBy(partitionStrategy, this.edges().partitions().length);
   }

   public Graph partitionBy(final PartitionStrategy partitionStrategy, final int numPartitions) {
      ClassTag edTag = scala.reflect.package..MODULE$.classTag(this.evidence$2);
      ClassTag vdTag = scala.reflect.package..MODULE$.classTag(this.evidence$1);
      EdgeRDDImpl newEdges = this.edges().withPartitionsRDD(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(this.edges().map((e) -> {
         int part = partitionStrategy.getPartition(e.srcId(), e.dstId(), numPartitions);
         return new Tuple2(BoxesRunTime.boxToInteger(part), new Tuple3(BoxesRunTime.boxToLong(e.srcId()), BoxesRunTime.boxToLong(e.dstId()), e.attr()));
      }, .MODULE$.apply(Tuple2.class)), .MODULE$.apply(Integer.TYPE), .MODULE$.apply(Tuple3.class), scala.math.Ordering.Int..MODULE$).partitionBy(new HashPartitioner(numPartitions)).mapPartitionsWithIndex((pid, iter) -> $anonfun$partitionBy$2(edTag, vdTag, BoxesRunTime.unboxToInt(pid), iter), true, .MODULE$.apply(Tuple2.class)), this.evidence$2, this.evidence$1).cache();
      return GraphImpl$.MODULE$.fromExistingRDDs(this.vertices().withEdges(newEdges), newEdges, this.evidence$1, this.evidence$2);
   }

   public Graph reverse() {
      return new GraphImpl(this.vertices().reverseRoutingTables(), this.replicatedVertexView().reverse(), this.evidence$1, this.evidence$2);
   }

   public Graph mapVertices(final Function2 f, final ClassTag evidence$5, final scala..eq.colon.eq eq) {
      if (eq != null) {
         this.vertices().cache();
         VertexRDD newVerts = (VertexRDD)this.vertices().mapVertexPartitions((x$2) -> (ShippableVertexPartition)ShippableVertexPartition$.MODULE$.shippablePartitionToOps(x$2, this.evidence$1).map(f, evidence$5), evidence$5).cache();
         VertexRDD changedVerts = this.vertices().diff(newVerts);
         ReplicatedVertexView newReplicatedVertexView = this.replicatedVertexView().updateVertices(changedVerts);
         return new GraphImpl(newVerts, newReplicatedVertexView, evidence$5, this.evidence$2);
      } else {
         return GraphImpl$.MODULE$.apply(this.vertices().mapVertexPartitions((x$3) -> (ShippableVertexPartition)ShippableVertexPartition$.MODULE$.shippablePartitionToOps(x$3, this.evidence$1).map(f, evidence$5), evidence$5), this.replicatedVertexView().edges(), evidence$5, this.evidence$2);
      }
   }

   public Null mapVertices$default$3(final Function2 f) {
      return null;
   }

   public Graph mapEdges(final Function2 f, final ClassTag evidence$6) {
      EdgeRDDImpl newEdges = this.replicatedVertexView().edges().mapEdgePartitions((pid, part) -> $anonfun$mapEdges$1(f, evidence$6, BoxesRunTime.unboxToInt(pid), part), evidence$6, this.evidence$1);
      return new GraphImpl(this.vertices(), this.replicatedVertexView().withEdges(newEdges, this.evidence$1, evidence$6), this.evidence$1, evidence$6);
   }

   public Graph mapTriplets(final Function2 f, final TripletFields tripletFields, final ClassTag evidence$7) {
      this.vertices().cache();
      this.replicatedVertexView().upgrade(this.vertices(), tripletFields.useSrc, tripletFields.useDst);
      EdgeRDDImpl newEdges = this.replicatedVertexView().edges().mapEdgePartitions((pid, part) -> $anonfun$mapTriplets$1(f, tripletFields, evidence$7, BoxesRunTime.unboxToInt(pid), part), evidence$7, this.evidence$1);
      return new GraphImpl(this.vertices(), this.replicatedVertexView().withEdges(newEdges, this.evidence$1, evidence$7), this.evidence$1, evidence$7);
   }

   public Graph subgraph(final Function1 epred, final Function2 vpred) {
      this.vertices().cache();
      VertexRDD newVerts = this.vertices().mapVertexPartitions((x$4) -> (ShippableVertexPartition)ShippableVertexPartition$.MODULE$.shippablePartitionToOps(x$4, this.evidence$1).filter(vpred), this.evidence$1);
      this.replicatedVertexView().upgrade(this.vertices(), true, true);
      EdgeRDDImpl newEdges = this.replicatedVertexView().edges().filter(epred, vpred);
      return new GraphImpl(newVerts, this.replicatedVertexView().withEdges(newEdges, this.evidence$1, this.evidence$2), this.evidence$1, this.evidence$2);
   }

   public Function1 subgraph$default$1() {
      return (x) -> BoxesRunTime.boxToBoolean($anonfun$subgraph$default$1$1(x));
   }

   public Function2 subgraph$default$2() {
      return (a, b) -> BoxesRunTime.boxToBoolean($anonfun$subgraph$default$2$1(BoxesRunTime.unboxToLong(a), b));
   }

   public Graph mask(final Graph other, final ClassTag evidence$8, final ClassTag evidence$9) {
      VertexRDD newVerts = this.vertices().innerJoin(other.vertices(), (vid, v, w) -> $anonfun$mask$1(BoxesRunTime.unboxToLong(vid), v, w), evidence$8, this.evidence$1);
      EdgeRDDImpl newEdges = this.replicatedVertexView().edges().innerJoin(other.edges(), (src, dst, v, w) -> $anonfun$mask$2(BoxesRunTime.unboxToLong(src), BoxesRunTime.unboxToLong(dst), v, w), evidence$9, this.evidence$2);
      return new GraphImpl(newVerts, this.replicatedVertexView().withEdges(newEdges, this.evidence$1, this.evidence$2), this.evidence$1, this.evidence$2);
   }

   public Graph groupEdges(final Function2 merge) {
      EdgeRDDImpl newEdges = this.replicatedVertexView().edges().mapEdgePartitions((pid, part) -> $anonfun$groupEdges$1(merge, BoxesRunTime.unboxToInt(pid), part), this.evidence$2, this.evidence$1);
      return new GraphImpl(this.vertices(), this.replicatedVertexView().withEdges(newEdges, this.evidence$1, this.evidence$2), this.evidence$1, this.evidence$2);
   }

   public VertexRDD aggregateMessagesWithActiveSet(final Function1 sendMsg, final Function2 mergeMsg, final TripletFields tripletFields, final Option activeSetOpt, final ClassTag evidence$10) {
      ReplicatedVertexView var10000;
      label21: {
         this.vertices().cache();
         this.replicatedVertexView().upgrade(this.vertices(), tripletFields.useSrc, tripletFields.useDst);
         if (activeSetOpt instanceof Some var9) {
            Tuple2 var10 = (Tuple2)var9.value();
            if (var10 != null) {
               VertexRDD activeSet = (VertexRDD)var10._1();
               var10000 = this.replicatedVertexView().withActiveSet(activeSet);
               break label21;
            }
         }

         if (!scala.None..MODULE$.equals(activeSetOpt)) {
            throw new MatchError(activeSetOpt);
         }

         var10000 = this.replicatedVertexView();
      }

      ReplicatedVertexView view = var10000;
      Option activeDirectionOpt = activeSetOpt.map((x$5) -> (EdgeDirection)x$5._2());
      RDD qual$1 = view.edges().partitionsRDD();
      Function1 x$1 = (x$6) -> x$6.flatMap((x0$1) -> {
            if (x0$1 == null) {
               throw new MatchError(x0$1);
            } else {
               EdgePartition edgePartition = (EdgePartition)x0$1._2();
               float activeFraction = (float)BoxesRunTime.unboxToInt(edgePartition.numActives().getOrElse((JFunction0.mcI.sp)() -> 0)) / (float)edgePartition.indexSize();
               boolean var11 = false;
               Some var12 = null;
               if (activeDirectionOpt instanceof Some) {
                  var11 = true;
                  var12 = (Some)activeDirectionOpt;
                  EdgeDirection var14 = (EdgeDirection)var12.value();
                  EdgeDirection var10000 = EdgeDirection$.MODULE$.Both();
                  if (var10000 == null) {
                     if (var14 == null) {
                        return (double)activeFraction < 0.8 ? edgePartition.aggregateMessagesIndexScan(sendMsg, mergeMsg, tripletFields, EdgeActiveness.Both, evidence$10) : edgePartition.aggregateMessagesEdgeScan(sendMsg, mergeMsg, tripletFields, EdgeActiveness.Both, evidence$10);
                     }
                  } else if (var10000.equals(var14)) {
                     return (double)activeFraction < 0.8 ? edgePartition.aggregateMessagesIndexScan(sendMsg, mergeMsg, tripletFields, EdgeActiveness.Both, evidence$10) : edgePartition.aggregateMessagesEdgeScan(sendMsg, mergeMsg, tripletFields, EdgeActiveness.Both, evidence$10);
                  }
               }

               if (var11) {
                  EdgeDirection var16 = (EdgeDirection)var12.value();
                  EdgeDirection var22 = EdgeDirection$.MODULE$.Either();
                  if (var22 == null) {
                     if (var16 == null) {
                        return edgePartition.aggregateMessagesEdgeScan(sendMsg, mergeMsg, tripletFields, EdgeActiveness.Either, evidence$10);
                     }
                  } else if (var22.equals(var16)) {
                     return edgePartition.aggregateMessagesEdgeScan(sendMsg, mergeMsg, tripletFields, EdgeActiveness.Either, evidence$10);
                  }
               }

               label101: {
                  if (var11) {
                     EdgeDirection var18 = (EdgeDirection)var12.value();
                     EdgeDirection var23 = EdgeDirection$.MODULE$.Out();
                     if (var23 == null) {
                        if (var18 == null) {
                           break label101;
                        }
                     } else if (var23.equals(var18)) {
                        break label101;
                     }
                  }

                  if (var11) {
                     EdgeDirection var20 = (EdgeDirection)var12.value();
                     EdgeDirection var24 = EdgeDirection$.MODULE$.In();
                     if (var24 == null) {
                        if (var20 == null) {
                           return edgePartition.aggregateMessagesEdgeScan(sendMsg, mergeMsg, tripletFields, EdgeActiveness.DstOnly, evidence$10);
                        }
                     } else if (var24.equals(var20)) {
                        return edgePartition.aggregateMessagesEdgeScan(sendMsg, mergeMsg, tripletFields, EdgeActiveness.DstOnly, evidence$10);
                     }
                  }

                  return edgePartition.aggregateMessagesEdgeScan(sendMsg, mergeMsg, tripletFields, EdgeActiveness.Neither, evidence$10);
               }

               if ((double)activeFraction < 0.8) {
                  return edgePartition.aggregateMessagesIndexScan(sendMsg, mergeMsg, tripletFields, EdgeActiveness.SrcOnly, evidence$10);
               } else {
                  return edgePartition.aggregateMessagesEdgeScan(sendMsg, mergeMsg, tripletFields, EdgeActiveness.SrcOnly, evidence$10);
               }
            }
         });
      boolean x$2 = qual$1.mapPartitions$default$2();
      RDD preAgg = qual$1.mapPartitions(x$1, x$2, .MODULE$.apply(Tuple2.class)).setName("GraphImpl.aggregateMessages - preAgg");
      return this.vertices().aggregateUsingIndex(preAgg, mergeMsg, evidence$10);
   }

   public Graph outerJoinVertices(final RDD other, final Function3 updateF, final ClassTag evidence$11, final ClassTag evidence$12, final scala..eq.colon.eq eq) {
      if (eq != null) {
         this.vertices().cache();
         VertexRDD newVerts = (VertexRDD)this.vertices().leftJoin(other, updateF, evidence$11, evidence$12).cache();
         VertexRDD changedVerts = this.vertices().diff(newVerts);
         ReplicatedVertexView newReplicatedVertexView = this.replicatedVertexView().updateVertices(changedVerts);
         return new GraphImpl(newVerts, newReplicatedVertexView, evidence$12, this.evidence$2);
      } else {
         VertexRDD newVerts = this.vertices().leftJoin(other, updateF, evidence$11, evidence$12);
         return GraphImpl$.MODULE$.apply(newVerts, this.replicatedVertexView().edges(), evidence$12, this.evidence$2);
      }
   }

   public Null outerJoinVertices$default$5(final RDD other, final Function3 updateF) {
      return null;
   }

   // $FF: synthetic method
   public static final void $anonfun$partitionBy$3(final EdgePartitionBuilder builder$1, final Tuple2 message) {
      Tuple3 data = (Tuple3)message._2();
      builder$1.add(BoxesRunTime.unboxToLong(data._1()), BoxesRunTime.unboxToLong(data._2()), data._3());
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$partitionBy$2(final ClassTag edTag$1, final ClassTag vdTag$1, final int pid, final Iterator iter) {
      EdgePartitionBuilder builder = new EdgePartitionBuilder(EdgePartitionBuilder$.MODULE$.$lessinit$greater$default$1(), edTag$1, vdTag$1);
      iter.foreach((message) -> {
         $anonfun$partitionBy$3(builder, message);
         return BoxedUnit.UNIT;
      });
      EdgePartition edgePartition = builder.toEdgePartition();
      return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToInteger(pid), edgePartition)})));
   }

   // $FF: synthetic method
   public static final EdgePartition $anonfun$mapEdges$1(final Function2 f$2, final ClassTag evidence$6$1, final int pid, final EdgePartition part) {
      return part.map((Iterator)f$2.apply(BoxesRunTime.boxToInteger(pid), part.iterator()), evidence$6$1);
   }

   // $FF: synthetic method
   public static final EdgePartition $anonfun$mapTriplets$1(final Function2 f$3, final TripletFields tripletFields$1, final ClassTag evidence$7$1, final int pid, final EdgePartition part) {
      return part.map((Iterator)f$3.apply(BoxesRunTime.boxToInteger(pid), part.tripletIterator(tripletFields$1.useSrc, tripletFields$1.useDst)), evidence$7$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$subgraph$default$1$1(final EdgeTriplet x) {
      return true;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$subgraph$default$2$1(final long a, final Object b) {
      return true;
   }

   // $FF: synthetic method
   public static final Object $anonfun$mask$1(final long vid, final Object v, final Object w) {
      return v;
   }

   // $FF: synthetic method
   public static final Object $anonfun$mask$2(final long src, final long dst, final Object v, final Object w) {
      return v;
   }

   // $FF: synthetic method
   public static final EdgePartition $anonfun$groupEdges$1(final Function2 merge$1, final int pid, final EdgePartition part) {
      return part.groupEdges(merge$1);
   }

   public GraphImpl(final VertexRDD vertices, final ReplicatedVertexView replicatedVertexView, final ClassTag evidence$1, final ClassTag evidence$2) {
      super(evidence$1, evidence$2);
      this.vertices = vertices;
      this.replicatedVertexView = replicatedVertexView;
      this.evidence$1 = evidence$1;
      this.evidence$2 = evidence$2;
      this.edges = replicatedVertexView.edges();
   }

   public GraphImpl(final ClassTag evidence$3, final ClassTag evidence$4) {
      this((VertexRDD)null, (ReplicatedVertexView)null, evidence$3, evidence$4);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
