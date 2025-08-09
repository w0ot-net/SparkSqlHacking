package org.apache.spark.mllib.fpm;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuilder;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\r%d\u0001B%K\u0001UC\u0001B\u001c\u0001\u0003\u0002\u0004%Ia\u001c\u0005\tg\u0002\u0011\t\u0019!C\u0005i\"A!\u0010\u0001B\u0001B\u0003&\u0001\u000f\u0003\u0005|\u0001\t\u0005\r\u0011\"\u0003}\u0011)\t\t\u0001\u0001BA\u0002\u0013%\u00111\u0001\u0005\n\u0003\u000f\u0001!\u0011!Q!\nuD!\"!\u0003\u0001\u0005\u0003\u0007I\u0011BA\u0006\u0011)\t\u0019\u0002\u0001BA\u0002\u0013%\u0011Q\u0003\u0005\u000b\u00033\u0001!\u0011!Q!\n\u00055\u0001bBA\u000e\u0001\u0011%\u0011Q\u0004\u0005\b\u00037\u0001A\u0011AA\u0015\u0011\u0019\ti\u0004\u0001C\u0001_\"9\u0011\u0011\t\u0001\u0005\u0002\u0005\r\u0003BBA&\u0001\u0011\u0005A\u0010C\u0004\u0002P\u0001!\t!!\u0015\t\u000f\u0005]\u0003\u0001\"\u0001\u0002\f!9\u00111\f\u0001\u0005\u0002\u0005u\u0003bBA2\u0001\u0011\u0005\u0011Q\r\u0005\b\u0003G\u0002A\u0011AAY\u000f\u001d\t\tP\u0013E\u0001\u0003g4a!\u0013&\t\u0002\u0005U\bbBA\u000e+\u0011\u0005!\u0011\u0001\u0005\t\u0005\u0007)B\u0011\u0001&\u0003\u0006!A!\u0011E\u000b\u0005\u0002)\u0013\u0019\u0003\u0003\u0005\u0003TU!\tA\u0013B+\r\u001d\u00119'\u0006\u0001K\u0005SB!Ba\u001b\u001b\u0005\u000b\u0007I\u0011\u0001B7\u0011)\u0011yG\u0007B\u0001B\u0003%!1\u0006\u0005\n\u0005cR\"Q1A\u0005\u0002qD\u0011Ba\u001d\u001b\u0005\u0003\u0005\u000b\u0011B?\t\u000f\u0005m!\u0004\"\u0003\u0003v!A!q\u0010\u000eC\u0002\u0013\u0005A\u0010C\u0004\u0003\u0002j\u0001\u000b\u0011B?\t\u000f\t\r%\u0004\"\u0001\u0003\u0006\u001eA!1R\u000b\t\u0002)\u0013iI\u0002\u0005\u0003hUA\tA\u0013BH\u0011\u001d\tY\u0002\nC\u0001\u0005#C\u0011Ba%%\u0005\u0004%IA!&\t\u0011\t-F\u0005)A\u0005\u0005/CaA!,%\t\u0013a\b\"\u0003BXI\t\u0007I\u0011\u0001BY\u0011!\u0011\u0019\f\nQ\u0001\n\t]\u0004\"\u0003B[I\u0005\u0005I\u0011\u0002B\\\r\u001d\u0011y,\u0006\u0001K\u0005\u0003D!Ba\u001b-\u0005\u000b\u0007I\u0011\u0001B7\u0011)\u0011y\u0007\fB\u0001B\u0003%!1\u0006\u0005\n\u0005\u0007d#Q1A\u0005\u0002qD\u0011B!2-\u0005\u0003\u0005\u000b\u0011B?\t\u0015\t\u001dGF!b\u0001\n\u0003\u0011i\u0007\u0003\u0006\u0003J2\u0012\t\u0011)A\u0005\u0005WAq!a\u0007-\t\u0003\u0011Y\rC\u0004\u0003V2\u0002K\u0011\u0002?\t\u000f\t]G\u0006\"\u0001\u0003Z\"9!1\u001d\u0017\u0005\u0002\t\u0015\bb\u0002BwY\u0011\u0005!q\u001e\u0005\b\u0005[dC\u0011\u0002B{\u0011\u001d\u0011i\u000f\fC\u0001\u0005sDqA!@-\t\u0003\u0011yp\u0002\u0006\u0004\u0002U\t\t\u0011#\u0001K\u0007\u00071!Ba0\u0016\u0003\u0003E\tASB\u0003\u0011\u001d\tY\u0002\u0010C\u0001\u0007\u000fA\u0011b!\u0003=#\u0003%\taa\u0003\t\u0013\r}A(%A\u0005\u0002\r\u0005\u0002\"\u0003B[y\u0005\u0005I\u0011\u0002B\\\r\u0019\u0019)#\u0006\u0001\u0004(!Q11F!\u0003\u0006\u0004%\ta!\f\t\u0015\re\u0012I!A!\u0002\u0013\u0019y\u0003\u0003\u0006\u0004>\u0005\u0013)\u0019!C\u0001\u0003\u0017A!b!\u0011B\u0005\u0003\u0005\u000b\u0011BA\u0007\u0011\u001d\tY\"\u0011C\u0001\u0007\u000bBqaa\u0015B\t\u0003\u0019)\u0006C\u0005\u00036V\t\t\u0011\"\u0003\u00038\nQ\u0001K]3gSb\u001c\u0006/\u00198\u000b\u0005-c\u0015a\u00014q[*\u0011QJT\u0001\u0006[2d\u0017N\u0019\u0006\u0003\u001fB\u000bQa\u001d9be.T!!\u0015*\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0016aA8sO\u000e\u00011\u0003\u0002\u0001W9\n\u0004\"a\u0016.\u000e\u0003aS\u0011!W\u0001\u0006g\u000e\fG.Y\u0005\u00037b\u0013a!\u00118z%\u00164\u0007CA/a\u001b\u0005q&BA0O\u0003!Ig\u000e^3s]\u0006d\u0017BA1_\u0005\u001daunZ4j]\u001e\u0004\"aY6\u000f\u0005\u0011LgBA3i\u001b\u00051'BA4U\u0003\u0019a$o\\8u}%\t\u0011,\u0003\u0002k1\u00069\u0001/Y2lC\u001e,\u0017B\u00017n\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tQ\u0007,\u0001\u0006nS:\u001cV\u000f\u001d9peR,\u0012\u0001\u001d\t\u0003/FL!A\u001d-\u0003\r\u0011{WO\u00197f\u00039i\u0017N\\*vaB|'\u000f^0%KF$\"!\u001e=\u0011\u0005]3\u0018BA<Y\u0005\u0011)f.\u001b;\t\u000fe\u0014\u0011\u0011!a\u0001a\u0006\u0019\u0001\u0010J\u0019\u0002\u00175LgnU;qa>\u0014H\u000fI\u0001\u0011[\u0006D\b+\u0019;uKJtG*\u001a8hi\",\u0012! \t\u0003/zL!a -\u0003\u0007%sG/\u0001\u000bnCb\u0004\u0016\r\u001e;fe:dUM\\4uQ~#S-\u001d\u000b\u0004k\u0006\u0015\u0001bB=\u0006\u0003\u0003\u0005\r!`\u0001\u0012[\u0006D\b+\u0019;uKJtG*\u001a8hi\"\u0004\u0013AE7bq2{7-\u00197Qe>TGIQ*ju\u0016,\"!!\u0004\u0011\u0007]\u000by!C\u0002\u0002\u0012a\u0013A\u0001T8oO\u00061R.\u0019=M_\u000e\fG\u000e\u0015:pU\u0012\u00135+\u001b>f?\u0012*\u0017\u000fF\u0002v\u0003/A\u0001\"\u001f\u0005\u0002\u0002\u0003\u0007\u0011QB\u0001\u0014[\u0006DHj\\2bYB\u0013xN\u001b#C'&TX\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0011\u0005}\u00111EA\u0013\u0003O\u00012!!\t\u0001\u001b\u0005Q\u0005\"\u00028\u000b\u0001\u0004\u0001\b\"B>\u000b\u0001\u0004i\bbBA\u0005\u0015\u0001\u0007\u0011Q\u0002\u000b\u0003\u0003?ASaCA\u0017\u0003s\u0001B!a\f\u000265\u0011\u0011\u0011\u0007\u0006\u0004\u0003gq\u0015AC1o]>$\u0018\r^5p]&!\u0011qGA\u0019\u0005\u0015\u0019\u0016N\\2fC\t\tY$A\u00032]Ur\u0003'A\u0007hKRl\u0015N\\*vaB|'\u000f\u001e\u0015\u0006\u0019\u00055\u0012\u0011H\u0001\u000eg\u0016$X*\u001b8TkB\u0004xN\u001d;\u0015\t\u0005\u0015\u0013qI\u0007\u0002\u0001!)a.\u0004a\u0001a\"*Q\"!\f\u0002:\u0005\u0019r-\u001a;NCb\u0004\u0016\r\u001e;fe:dUM\\4uQ\"*a\"!\f\u0002:\u0005\u00192/\u001a;NCb\u0004\u0016\r\u001e;fe:dUM\\4uQR!\u0011QIA*\u0011\u0015Yx\u00021\u0001~Q\u0015y\u0011QFA\u001d\u0003U9W\r^'bq2{7-\u00197Qe>TGIQ*ju\u0016DS\u0001EA\u0017\u0003s\tQc]3u\u001b\u0006DHj\\2bYB\u0013xN\u001b#C'&TX\r\u0006\u0003\u0002F\u0005}\u0003bBA\u0005#\u0001\u0007\u0011Q\u0002\u0015\u0006#\u00055\u0012\u0011H\u0001\u0004eVtW\u0003BA4\u0003k\"B!!\u001b\u0002\u0018R!\u00111NAD!\u0019\t\t#!\u001c\u0002r%\u0019\u0011q\u000e&\u0003\u001fA\u0013XMZ5y'B\fg.T8eK2\u0004B!a\u001d\u0002v1\u0001AaBA<%\t\u0007\u0011\u0011\u0010\u0002\u0005\u0013R,W.\u0005\u0003\u0002|\u0005\u0005\u0005cA,\u0002~%\u0019\u0011q\u0010-\u0003\u000f9{G\u000f[5oOB\u0019q+a!\n\u0007\u0005\u0015\u0005LA\u0002B]fD\u0011\"!#\u0013\u0003\u0003\u0005\u001d!a#\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u0004\u0002\u000e\u0006M\u0015\u0011O\u0007\u0003\u0003\u001fS1!!%Y\u0003\u001d\u0011XM\u001a7fGRLA!!&\u0002\u0010\nA1\t\\1tgR\u000bw\rC\u0004\u0002\u001aJ\u0001\r!a'\u0002\t\u0011\fG/\u0019\t\u0007\u0003;\u000b\u0019+a*\u000e\u0005\u0005}%bAAQ\u001d\u0006\u0019!\u000f\u001a3\n\t\u0005\u0015\u0016q\u0014\u0002\u0004%\u0012#\u0005#B,\u0002*\u00065\u0016bAAV1\n)\u0011I\u001d:bsB)q+!+\u0002r!*!#!\f\u0002:UA\u00111WA]\u0003K\fy\r\u0006\u0003\u00026\u0006m\u0006CBA\u0011\u0003[\n9\f\u0005\u0003\u0002t\u0005eFaBA<'\t\u0007\u0011\u0011\u0010\u0005\b\u00033\u001b\u0002\u0019AA_!\u0019\ty,!3\u0002N6\u0011\u0011\u0011\u0019\u0006\u0005\u0003\u0007\f)-\u0001\u0003kCZ\f'bAAd\u001d\u0006\u0019\u0011\r]5\n\t\u0005-\u0017\u0011\u0019\u0002\b\u0015\u00064\u0018M\u0015#E!\u0011\t\u0019(a4\u0005\u000f\u0005E7C1\u0001\u0002T\nA1+Z9vK:\u001cW-\u0005\u0003\u0002|\u0005U\u0007CBAl\u0003?\f\u0019/\u0004\u0002\u0002Z*!\u00111\\Ao\u0003\u0011a\u0017M\\4\u000b\u0005\u0005\r\u0017\u0002BAq\u00033\u0014\u0001\"\u0013;fe\u0006\u0014G.\u001a\t\u0005\u0003g\n)\u000fB\u0004\u0002hN\u0011\r!!;\u0003\u000f%#X-\\:fiF!\u00111PAv!\u0019\t9.a8\u00028\"*1#!\f\u0002:!*\u0001!!\f\u0002:\u0005Q\u0001K]3gSb\u001c\u0006/\u00198\u0011\u0007\u0005\u0005RcE\u0003\u0016-r\u000b9\u0010\u0005\u0003\u0002z\u0006}XBAA~\u0015\u0011\ti0!8\u0002\u0005%|\u0017b\u00017\u0002|R\u0011\u00111_\u0001\u0012M&tGM\u0012:fcV,g\u000e^%uK6\u001cX\u0003\u0002B\u0004\u0005\u001f!bA!\u0003\u0003\u0018\tuA\u0003\u0002B\u0006\u0005#\u0001RaVAU\u0005\u001b\u0001B!a\u001d\u0003\u0010\u00119\u0011qO\fC\u0002\u0005e\u0004\"\u0003B\n/\u0005\u0005\t9\u0001B\u000b\u0003))g/\u001b3f]\u000e,GE\r\t\u0007\u0003\u001b\u000b\u0019J!\u0004\t\u000f\u0005eu\u00031\u0001\u0003\u001aA1\u0011QTAR\u00057\u0001RaVAU\u0005\u0017AqAa\b\u0018\u0001\u0004\ti!\u0001\u0005nS:\u001cu.\u001e8u\u0003Y!x\u000eR1uC\n\f7/Z%oi\u0016\u0014h.\u00197SKB\u0014X\u0003\u0002B\u0013\u0005k!bAa\n\u00038\t}B\u0003\u0002B\u0015\u0005[\u0001b!!(\u0002$\n-\u0002\u0003B,\u0002*vD\u0011Ba\f\u0019\u0003\u0003\u0005\u001dA!\r\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007\u0005\u0004\u0002\u000e\u0006M%1\u0007\t\u0005\u0003g\u0012)\u0004B\u0004\u0002xa\u0011\r!!\u001f\t\u000f\u0005e\u0005\u00041\u0001\u0003:A1\u0011QTAR\u0005w\u0001RaVAU\u0005{\u0001RaVAU\u0005gAqA!\u0011\u0019\u0001\u0004\u0011\u0019%A\u0005ji\u0016lGk\\%oiB9!Q\tB'\u0005gih\u0002\u0002B$\u0005\u0013\u0002\"!\u001a-\n\u0007\t-\u0003,\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0005\u001f\u0012\tFA\u0002NCBT1Aa\u0013Y\u0003=9WM\u001c$sKF\u0004\u0016\r\u001e;fe:\u001cHC\u0003B,\u0005?\u0012\tGa\u0019\u0003fA1\u0011QTAR\u00053\u0002ra\u0016B.\u0005W\ti!C\u0002\u0003^a\u0013a\u0001V;qY\u0016\u0014\u0004bBAM3\u0001\u0007!\u0011\u0006\u0005\b\u0005?I\u0002\u0019AA\u0007\u0011\u0015Y\u0018\u00041\u0001~\u0011\u001d\tI!\u0007a\u0001\u0003\u001b\u0011a\u0001\u0015:fM&D8c\u0001\u000eWE\u0006)\u0011\u000e^3ngV\u0011!1F\u0001\u0007SR,Wn\u001d\u0011\u0002\r1,gn\u001a;i\u0003\u001daWM\\4uQ\u0002\"bAa\u001e\u0003|\tu\u0004c\u0001B=55\tQ\u0003C\u0004\u0003l}\u0001\rAa\u000b\t\r\tEt\u00041\u0001~\u0003\tIG-A\u0002jI\u0002\n1\u0002J2pY>tG\u0005\u001d7vgR!!q\u000fBD\u0011\u0019\u0011II\ta\u0001{\u0006!\u0011\u000e^3n\u0003\u0019\u0001&/\u001a4jqB\u0019!\u0011\u0010\u0013\u0014\t\u00112\u0016q\u001f\u000b\u0003\u0005\u001b\u000bqaY8v]R,'/\u0006\u0002\u0003\u0018B!!\u0011\u0014BT\u001b\t\u0011YJ\u0003\u0003\u0003\u001e\n}\u0015AB1u_6L7M\u0003\u0003\u0003\"\n\r\u0016AC2p]\u000e,(O]3oi*!!QUAo\u0003\u0011)H/\u001b7\n\t\t%&1\u0014\u0002\u000e\u0003R|W.[2J]R,w-\u001a:\u0002\u0011\r|WO\u001c;fe\u0002\naA\\3yi&#\u0017!B3naRLXC\u0001B<\u0003\u0019)W\u000e\u001d;zA\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!\u0011\u0018\t\u0005\u0003/\u0014Y,\u0003\u0003\u0003>\u0006e'AB(cU\u0016\u001cGOA\u0004Q_N$h-\u001b=\u0014\u000712&-A\u0003ti\u0006\u0014H/\u0001\u0004ti\u0006\u0014H\u000fI\u0001\u000ea\u0006\u0014H/[1m'R\f'\u000f^:\u0002\u001dA\f'\u000f^5bYN#\u0018M\u001d;tAQA!Q\u001aBh\u0005#\u0014\u0019\u000eE\u0002\u0003z1BqAa\u001b4\u0001\u0004\u0011Y\u0003\u0003\u0005\u0003DN\u0002\n\u00111\u0001~\u0011%\u00119m\rI\u0001\u0002\u0004\u0011Y#A\u0005gk2d7\u000b^1si\u0006qq-\u001a8Qe\u00164\u0017\u000e_%uK6\u001cXC\u0001Bn!\u0015\u0019'Q\u001cBq\u0013\r\u0011y.\u001c\u0002\t\u0013R,'/\u0019;peB1qKa\u0017~\u0003\u001b\t\u0001B\\8o\u000b6\u0004H/_\u000b\u0003\u0005O\u00042a\u0016Bu\u0013\r\u0011Y\u000f\u0017\u0002\b\u0005>|G.Z1o\u0003\u001d\u0001(o\u001c6fGR$BA!4\u0003r\"1!1_\u001cA\u0002u\fa\u0001\u001d:fM&DH\u0003\u0002Bg\u0005oDqAa=9\u0001\u0004\u0011Y\u0003\u0006\u0003\u0003N\nm\bb\u0002Bzs\u0001\u0007!qO\u0001\u000bG>l\u0007O]3tg\u0016$WC\u0001Bg\u0003\u001d\u0001vn\u001d;gSb\u00042A!\u001f='\u0011ad+a>\u0015\u0005\r\r\u0011a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$#'\u0006\u0002\u0004\u000e)\u001aQpa\u0004,\u0005\rE\u0001\u0003BB\n\u00077i!a!\u0006\u000b\t\r]1\u0011D\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\rY\u0013\u0011\u0019ib!\u0006\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HeM\u000b\u0003\u0007GQCAa\u000b\u0004\u0010\taaI]3r'\u0016\fX/\u001a8dKV!1\u0011FB\u001b'\r\teKY\u0001\tg\u0016\fX/\u001a8dKV\u00111q\u0006\t\u0006/\u0006%6\u0011\u0007\t\u0006/\u0006%61\u0007\t\u0005\u0003g\u001a)\u0004B\u0004\u0002x\u0005\u0013\r!!\u001f)\u000b\t\u000bi#!\u000f\u0002\u0013M,\u0017/^3oG\u0016\u0004\u0003&B\"\u0002.\u0005e\u0012\u0001\u00024sKFDS\u0001RA\u0017\u0003s\tQA\u001a:fc\u0002BS!RA\u0017\u0003s!baa\u0012\u0004J\r5\u0003#\u0002B=\u0003\u000eM\u0002bBB\u0016\r\u0002\u00071q\u0006\u0015\u0007\u0007\u0013\ni#!\u000f\t\u000f\rub\t1\u0001\u0002\u000e!21QJA\u0017\u0003sASARA\u0017\u0003s\tAB[1wCN+\u0017/^3oG\u0016,\"aa\u0016\u0011\r\re31LB0\u001b\t\u0011\u0019+\u0003\u0003\u0004^\t\r&\u0001\u0002'jgR\u0004ba!\u0017\u0004\\\rM\u0002&B$\u0002.\u0005e\u0002&B!\u0002.\u0005e\u0002&B\u000b\u0002.\u0005e\u0002&\u0002\u000b\u0002.\u0005e\u0002"
)
public class PrefixSpan implements Logging, Serializable {
   private double minSupport;
   private int maxPatternLength;
   private long maxLocalProjDBSize;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private double minSupport() {
      return this.minSupport;
   }

   private void minSupport_$eq(final double x$1) {
      this.minSupport = x$1;
   }

   private int maxPatternLength() {
      return this.maxPatternLength;
   }

   private void maxPatternLength_$eq(final int x$1) {
      this.maxPatternLength = x$1;
   }

   private long maxLocalProjDBSize() {
      return this.maxLocalProjDBSize;
   }

   private void maxLocalProjDBSize_$eq(final long x$1) {
      this.maxLocalProjDBSize = x$1;
   }

   public double getMinSupport() {
      return this.minSupport();
   }

   public PrefixSpan setMinSupport(final double minSupport) {
      .MODULE$.require(minSupport >= (double)0 && minSupport <= (double)1, () -> "The minimum support value must be in [0, 1], but got " + minSupport + ".");
      this.minSupport_$eq(minSupport);
      return this;
   }

   public int getMaxPatternLength() {
      return this.maxPatternLength();
   }

   public PrefixSpan setMaxPatternLength(final int maxPatternLength) {
      .MODULE$.require(maxPatternLength >= 1, () -> "The maximum pattern length value must be greater than 0, but got " + maxPatternLength + ".");
      this.maxPatternLength_$eq(maxPatternLength);
      return this;
   }

   public long getMaxLocalProjDBSize() {
      return this.maxLocalProjDBSize();
   }

   public PrefixSpan setMaxLocalProjDBSize(final long maxLocalProjDBSize) {
      .MODULE$.require(maxLocalProjDBSize >= 0L, () -> "The maximum local projected database size must be nonnegative, but got " + maxLocalProjDBSize);
      this.maxLocalProjDBSize_$eq(maxLocalProjDBSize);
      return this;
   }

   public PrefixSpanModel run(final RDD data, final ClassTag evidence$1) {
      label28: {
         StorageLevel var10000 = data.getStorageLevel();
         StorageLevel var3 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
         if (var10000 == null) {
            if (var3 != null) {
               break label28;
            }
         } else if (!var10000.equals(var3)) {
            break label28;
         }

         this.logWarning((Function0)(() -> "Input data is not cached."));
      }

      RDD dataInternalRepr;
      RDD freqSequences;
      label22: {
         label21: {
            long totalCount = data.count();
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"number of sequences: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_SEQUENCES..MODULE$, BoxesRunTime.boxToLong(totalCount))})))));
            long minCount = (long)scala.math.package..MODULE$.ceil(this.minSupport() * (double)totalCount);
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"minimum count for a frequent pattern: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MIN_NUM_FREQUENT_PATTERN..MODULE$, BoxesRunTime.boxToLong(minCount))})))));
            Object freqItems = PrefixSpan$.MODULE$.findFrequentItems(data, minCount, evidence$1);
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"number of frequent items: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_FREQUENT_ITEMS..MODULE$, BoxesRunTime.boxToInteger(scala.runtime.ScalaRunTime..MODULE$.array_length(freqItems)))})))));
            scala.collection.immutable.Map itemToInt = org.apache.spark.util.collection.Utils..MODULE$.toMapWithIndex(.MODULE$.genericWrapArray(freqItems));
            dataInternalRepr = PrefixSpan$.MODULE$.toDatabaseInternalRepr(data, itemToInt, evidence$1).persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
            RDD results = PrefixSpan$.MODULE$.genFreqPatterns(dataInternalRepr, minCount, this.maxPatternLength(), this.maxLocalProjDBSize());
            freqSequences = results.map((x0$1) -> {
               if (x0$1 != null) {
                  int[] seq = (int[])x0$1._1();
                  long count = x0$1._2$mcJ$sp();
                  if (seq != null && true) {
                     return new FreqSequence(toPublicRepr$1(seq, evidence$1, freqItems), count);
                  }
               }

               throw new MatchError(x0$1);
            }, scala.reflect.ClassTag..MODULE$.apply(FreqSequence.class));
            StorageLevel var14 = data.getStorageLevel();
            StorageLevel var13 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
            if (var14 == null) {
               if (var13 != null) {
                  break label21;
               }
            } else if (!var14.equals(var13)) {
               break label21;
            }

            BoxedUnit var15 = BoxedUnit.UNIT;
            break label22;
         }

         freqSequences.persist(data.getStorageLevel());
         BoxesRunTime.boxToLong(freqSequences.count());
      }

      dataInternalRepr.unpersist(dataInternalRepr.unpersist$default$1());
      return new PrefixSpanModel(freqSequences);
   }

   public PrefixSpanModel run(final JavaRDD data) {
      ClassTag tag = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      return this.run(data.rdd().map((x$1) -> ((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala(x$1).asScala().map((x$2) -> scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala(x$2).asScala().toArray(tag))).toArray(scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(tag.runtimeClass()))), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(scala.runtime.ScalaRunTime..MODULE$.arrayClass(tag.runtimeClass())))), tag);
   }

   private static final Object[] toPublicRepr$1(final int[] pattern, final ClassTag evidence$1$1, final Object freqItems$1) {
      ArrayBuilder sequenceBuilder = scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(evidence$1$1.runtimeClass())));
      ArrayBuilder itemsetBuilder = scala.collection.mutable.ArrayBuilder..MODULE$.make(evidence$1$1);

      for(int x : pattern) {
         if (x == 0) {
            sequenceBuilder.$plus$eq(itemsetBuilder.result());
            itemsetBuilder.clear();
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            itemsetBuilder.$plus$eq(scala.runtime.ScalaRunTime..MODULE$.array_apply(freqItems$1, x - 1));
         }
      }

      return sequenceBuilder.result();
   }

   private PrefixSpan(final double minSupport, final int maxPatternLength, final long maxLocalProjDBSize) {
      this.minSupport = minSupport;
      this.maxPatternLength = maxPatternLength;
      this.maxLocalProjDBSize = maxLocalProjDBSize;
      super();
      Logging.$init$(this);
   }

   public PrefixSpan() {
      this(0.1, 10, 32000000L);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class Prefix implements Serializable {
      private final int[] items;
      private final int length;
      private final int id;

      public int[] items() {
         return this.items;
      }

      public int length() {
         return this.length;
      }

      public int id() {
         return this.id;
      }

      public Prefix $colon$plus(final int item) {
         .MODULE$.require(item != 0);
         return item < 0 ? new Prefix((int[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(.MODULE$.intArrayOps(this.items()), BoxesRunTime.boxToInteger(-item), scala.reflect.ClassTag..MODULE$.Int()), this.length() + 1) : new Prefix((int[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(.MODULE$.intArrayOps(this.items()), new int[]{0, item}, scala.reflect.ClassTag..MODULE$.Int()), this.length() + 1);
      }

      public Prefix(final int[] items, final int length) {
         this.items = items;
         this.length = length;
         this.id = PrefixSpan.Prefix$.MODULE$.org$apache$spark$mllib$fpm$PrefixSpan$Prefix$$nextId();
      }
   }

   public static class Prefix$ implements Serializable {
      public static final Prefix$ MODULE$ = new Prefix$();
      private static final AtomicInteger counter = new AtomicInteger(-1);
      private static final Prefix empty;

      static {
         empty = new Prefix((int[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), 0);
      }

      private AtomicInteger counter() {
         return counter;
      }

      public int org$apache$spark$mllib$fpm$PrefixSpan$Prefix$$nextId() {
         return this.counter().incrementAndGet();
      }

      public Prefix empty() {
         return empty;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Prefix$.class);
      }
   }

   public static class Postfix$ implements Serializable {
      public static final Postfix$ MODULE$ = new Postfix$();

      public int $lessinit$greater$default$2() {
         return 0;
      }

      public int[] $lessinit$greater$default$3() {
         return (int[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int());
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Postfix$.class);
      }
   }

   public static class Postfix implements Serializable {
      private final int[] items;
      private final int start;
      private final int[] partialStarts;

      public int[] items() {
         return this.items;
      }

      public int start() {
         return this.start;
      }

      public int[] partialStarts() {
         return this.partialStarts;
      }

      private int fullStart() {
         int i;
         for(i = this.start(); this.items()[i] != 0; ++i) {
         }

         return i;
      }

      public Iterator genPrefixItems() {
         int n1 = this.items().length - 1;
         scala.collection.mutable.Map prefixes = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty();
         scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.intArrayOps(this.partialStarts()), (JFunction1.mcVI.sp)(start) -> {
            int i = start;

            for(int x = -this.items()[start]; x != 0; x = -this.items()[i]) {
               if (!prefixes.contains(BoxesRunTime.boxToInteger(x))) {
                  prefixes.update(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToLong((long)(n1 - i)));
               }

               ++i;
            }

         });

         for(int i = this.fullStart(); i < n1; ++i) {
            int x = this.items()[i];
            if (x != 0 && !prefixes.contains(BoxesRunTime.boxToInteger(x))) {
               prefixes.update(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToLong((long)(n1 - i)));
            }
         }

         return prefixes.iterator();
      }

      public boolean nonEmpty() {
         return this.items().length > this.start() + 1;
      }

      public Postfix project(final int prefix) {
         .MODULE$.require(prefix != 0);
         int n1 = this.items().length - 1;
         BooleanRef matched = BooleanRef.create(false);
         IntRef newStart = IntRef.create(n1);
         ArrayBuilder newPartialStarts = scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Int());
         if (prefix < 0) {
            int target = -prefix;
            scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.intArrayOps(this.partialStarts()), (start) -> $anonfun$project$1(this, target, matched, newStart, newPartialStarts, BoxesRunTime.unboxToInt(start)));
         } else {
            int target = prefix;

            for(int i = this.fullStart(); i < n1; ++i) {
               int x = this.items()[i];
               if (x == target) {
                  if (!matched.elem) {
                     newStart.elem = i;
                     matched.elem = true;
                  }

                  if (this.items()[i + 1] != 0) {
                     newPartialStarts.$plus$eq(BoxesRunTime.boxToInteger(i + 1));
                  } else {
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }
               } else {
                  BoxedUnit var10 = BoxedUnit.UNIT;
               }
            }
         }

         return new Postfix(this.items(), newStart.elem, (int[])newPartialStarts.result());
      }

      private Postfix project(final int[] prefix) {
         boolean partial = true;
         Postfix cur = this;
         int i = 0;

         for(int np = prefix.length; i < np && cur.nonEmpty(); ++i) {
            int x = prefix[i];
            if (x == 0) {
               partial = false;
            } else if (partial) {
               cur = cur.project(-x);
            } else {
               cur = cur.project(x);
               partial = true;
            }
         }

         return cur;
      }

      public Postfix project(final Prefix prefix) {
         return this.project(prefix.items());
      }

      public Postfix compressed() {
         return this.start() > 0 ? new Postfix((int[])scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.intArrayOps(this.items()), this.start(), this.items().length), 0, (int[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.intArrayOps(this.partialStarts()), (JFunction1.mcII.sp)(x$9) -> x$9 - this.start(), scala.reflect.ClassTag..MODULE$.Int())) : this;
      }

      // $FF: synthetic method
      public static final Object $anonfun$project$1(final Postfix $this, final int target$1, final BooleanRef matched$1, final IntRef newStart$1, final ArrayBuilder newPartialStarts$1, final int start) {
         int i = start;

         int x;
         for(x = $this.items()[start]; x != target$1 && x != 0; x = $this.items()[i]) {
            ++i;
         }

         if (x == target$1) {
            ++i;
            if (!matched$1.elem) {
               newStart$1.elem = i;
               matched$1.elem = true;
            }

            return $this.items()[i] != 0 ? newPartialStarts$1.$plus$eq(BoxesRunTime.boxToInteger(i)) : BoxedUnit.UNIT;
         } else {
            return BoxedUnit.UNIT;
         }
      }

      public Postfix(final int[] items, final int start, final int[] partialStarts) {
         this.items = items;
         this.start = start;
         this.partialStarts = partialStarts;
         .MODULE$.require(BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.last$extension(.MODULE$.intArrayOps(items))) == 0, () -> {
            Object var10000 = scala.collection.ArrayOps..MODULE$.last$extension(.MODULE$.intArrayOps(this.items()));
            return "The last item in a postfix must be zero, but got " + var10000 + ".";
         });
         if (scala.collection.ArrayOps..MODULE$.nonEmpty$extension(.MODULE$.intArrayOps(partialStarts))) {
            .MODULE$.require(BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.head$extension(.MODULE$.intArrayOps(partialStarts))) >= start, () -> {
               Object var10000 = scala.collection.ArrayOps..MODULE$.head$extension(.MODULE$.intArrayOps(this.partialStarts()));
               return "The first partial start cannot be smaller than the start index,but got partialStarts.head = " + var10000 + " < start = " + this.start() + ".";
            });
         }

      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class FreqSequence implements Serializable {
      private final Object[] sequence;
      private final long freq;

      public Object[] sequence() {
         return this.sequence;
      }

      public long freq() {
         return this.freq;
      }

      public List javaSequence() {
         return scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(.MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(this.sequence()), (x$10) -> scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(.MODULE$.genericWrapArray(x$10).toList()).asJava(), scala.reflect.ClassTag..MODULE$.apply(List.class))).toList()).asJava();
      }

      public FreqSequence(final Object[] sequence, final long freq) {
         this.sequence = sequence;
         this.freq = freq;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
