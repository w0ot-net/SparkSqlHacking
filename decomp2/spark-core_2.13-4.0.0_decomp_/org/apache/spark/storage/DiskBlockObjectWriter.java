package org.apache.spark.storage;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.Map;
import java.util.zip.Checksum;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.io.MutableCheckedOutputStream;
import org.apache.spark.serializer.SerializationStream;
import org.apache.spark.serializer.SerializerInstance;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.shuffle.ShuffleWriteMetricsReporter;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.collection.PairsWriter;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015h!\u0002+V\u0001]k\u0006\u0002\u0003;\u0001\u0005\u000b\u0007I\u0011\u0001<\t\u0011i\u0004!\u0011!Q\u0001\n]D\u0001b\u001f\u0001\u0003\u0002\u0003\u0006I\u0001 \u0005\u000b\u0003\u000b\u0001!\u0011!Q\u0001\n\u0005\u001d\u0001BCA\u0007\u0001\t\u0005\t\u0015!\u0003\u0002\u0010!Q\u00111\u0004\u0001\u0003\u0002\u0003\u0006I!!\b\t\u0015\u0005\r\u0002A!A!\u0002\u0013\t)\u0003\u0003\u0006\u00022\u0001\u0011)\u0019!C\u0001\u0003gA!\"!\u0010\u0001\u0005\u0003\u0005\u000b\u0011BA\u001b\u0011\u001d\ty\u0004\u0001C\u0001\u0003\u00032\u0011\"a\u0015\u0001!\u0003\rI!!\u0016\t\u000f\u0005]3\u0002\"\u0001\u0002Z!A\u0011\u0011M\u0006\u0011\n\u0003\tI\u0006C\u0004\u0002d-!\t!!\u0017\t\u001d\u0005\u00154\u0002%A\u0002\u0002\u0003%I!!\u0017\u0002h!I\u0011\u0011\u000e\u0001A\u0002\u0013%\u00111\u000e\u0005\n\u0003{\u0002\u0001\u0019!C\u0005\u0003\u007fB\u0001\"!\"\u0001A\u0003&\u0011Q\u000e\u0005\n\u0003\u000f\u0003\u0001\u0019!C\u0005\u0003\u0013C\u0011\"a$\u0001\u0001\u0004%I!!%\t\u0011\u0005U\u0005\u0001)Q\u0005\u0003\u0017C\u0011\"a&\u0001\u0001\u0004%I!!'\t\u0013\u0005m\u0005\u00011A\u0005\n\u0005u\u0005bBAQ\u0001\u0001\u0006KA\u0018\u0005\n\u0003G\u0003\u0001\u0019!C\u0005\u0003KC\u0011\"!,\u0001\u0001\u0004%I!a,\t\u0011\u0005M\u0006\u0001)Q\u0005\u0003OC\u0011\"!.\u0001\u0001\u0004%I!a.\t\u0013\u0005}\u0006\u00011A\u0005\n\u0005\u0005\u0007\u0002CAc\u0001\u0001\u0006K!!/\t\u0013\u0005\u001d\u0007\u00011A\u0005\n\u0005%\u0007\"CAi\u0001\u0001\u0007I\u0011BAj\u0011!\t9\u000e\u0001Q!\n\u0005-\u0007\"CAm\u0001\u0001\u0007I\u0011BAn\u0011%\ti\u000e\u0001a\u0001\n\u0013\ty\u000e\u0003\u0005\u0002d\u0002\u0001\u000b\u0015BA\u000f\u0011%\t)\u000f\u0001a\u0001\n\u0013\tY\u000eC\u0005\u0002h\u0002\u0001\r\u0011\"\u0003\u0002j\"A\u0011Q\u001e\u0001!B\u0013\ti\u0002C\u0005\u0002p\u0002\u0001\r\u0011\"\u0003\u0002\\\"I\u0011\u0011\u001f\u0001A\u0002\u0013%\u00111\u001f\u0005\t\u0003o\u0004\u0001\u0015)\u0003\u0002\u001e!I\u0011\u0011 \u0001A\u0002\u0013%\u00111\u001c\u0005\n\u0003w\u0004\u0001\u0019!C\u0005\u0003{D\u0001B!\u0001\u0001A\u0003&\u0011Q\u0004\u0005\f\u0005\u0007\u0001\u0001\u0019!a\u0001\n\u0013\u0011)\u0001C\u0006\u0003\u0012\u0001\u0001\r\u00111A\u0005\n\tM\u0001b\u0003B\f\u0001\u0001\u0007\t\u0011)Q\u0005\u0005\u000fA1B!\u0007\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u0003\u001c!Y!1\u0006\u0001A\u0002\u0003\u0007I\u0011\u0002B\u0017\u0011-\u0011\t\u0004\u0001a\u0001\u0002\u0003\u0006KA!\b\t\u0013\tM\u0002\u00011A\u0005\n\tU\u0002\"\u0003B\u001f\u0001\u0001\u0007I\u0011\u0002B \u0011!\u0011\u0019\u0005\u0001Q!\n\t]\u0002\"\u0003B#\u0001\u0001\u0007I\u0011\u0002B\u001b\u0011%\u00119\u0005\u0001a\u0001\n\u0013\u0011I\u0005\u0003\u0005\u0003N\u0001\u0001\u000b\u0015\u0002B\u001c\u0011%\u0011y\u0005\u0001a\u0001\n\u0013\u0011\t\u0006C\u0005\u0003T\u0001\u0001\r\u0011\"\u0003\u0003V!A!\u0011\f\u0001!B\u0013\ty\u0001C\u0005\u0003\\\u0001\u0001\r\u0011\"\u0003\u00036!I!Q\f\u0001A\u0002\u0013%!q\f\u0005\t\u0005G\u0002\u0001\u0015)\u0003\u00038!A!Q\r\u0001\u0005\u0002U\u000bI\n\u0003\u0005\u0003h\u0001!\t!VAe\u0011\u001d\u0011I\u0007\u0001C\u0001\u0005WBqAa\u001c\u0001\t\u0013\tI\u0006C\u0004\u0003r\u0001!\tAa\u001d\t\u000f\tU\u0004\u0001\"\u0003\u0002Z!9\u0011\u0011\r\u0001\u0005B\u0005e\u0003b\u0002B<\u0001\u0011\u0005!\u0011\u0010\u0005\b\u0005\u0003\u0003A\u0011\u0001BB\u0011\u001d\u0011)\t\u0001C\u0001\u00033BqAa\"\u0001\t\u0003\u0012I\tC\u0004\u0003\b\u0002!\tE!'\t\u000f\t\u001d\u0005\u0001\"\u0011\u0003 \"9!\u0011\u0018\u0001\u0005\u0002\u0005e\u0003b\u0002B^\u0001\u0011%\u0011\u0011\f\u0005\b\u0005{\u0003A\u0011IA-\u000f)\u0011y,VA\u0001\u0012\u00039&\u0011\u0019\u0004\n)V\u000b\t\u0011#\u0001X\u0005\u0007Dq!a\u0010R\t\u0003\u0011Y\rC\u0005\u0003NF\u000b\n\u0011\"\u0001\u0003P\n)B)[:l\u00052|7m[(cU\u0016\u001cGo\u0016:ji\u0016\u0014(B\u0001,X\u0003\u001d\u0019Ho\u001c:bO\u0016T!\u0001W-\u0002\u000bM\u0004\u0018M]6\u000b\u0005i[\u0016AB1qC\u000eDWMC\u0001]\u0003\ry'oZ\n\u0005\u0001y3G\u000e\u0005\u0002`I6\t\u0001M\u0003\u0002bE\u0006\u0011\u0011n\u001c\u0006\u0002G\u0006!!.\u0019<b\u0013\t)\u0007M\u0001\u0007PkR\u0004X\u000f^*ue\u0016\fW\u000e\u0005\u0002hU6\t\u0001N\u0003\u0002j/\u0006A\u0011N\u001c;fe:\fG.\u0003\u0002lQ\n9Aj\\4hS:<\u0007CA7s\u001b\u0005q'BA8q\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0003c^\u000bA!\u001e;jY&\u00111O\u001c\u0002\f!\u0006L'o],sSR,'/\u0001\u0003gS2,7\u0001A\u000b\u0002oB\u0011q\f_\u0005\u0003s\u0002\u0014AAR5mK\u0006)a-\u001b7fA\u0005\t2/\u001a:jC2L'0\u001a:NC:\fw-\u001a:\u0011\u0007u\f\t!D\u0001\u007f\u0015\tyx+\u0001\u0006tKJL\u0017\r\\5{KJL1!a\u0001\u007f\u0005E\u0019VM]5bY&TXM]'b]\u0006<WM]\u0001\u0013g\u0016\u0014\u0018.\u00197ju\u0016\u0014\u0018J\\:uC:\u001cW\rE\u0002~\u0003\u0013I1!a\u0003\u007f\u0005I\u0019VM]5bY&TXM]%ogR\fgnY3\u0002\u0015\t,hMZ3s'&TX\r\u0005\u0003\u0002\u0012\u0005]QBAA\n\u0015\t\t)\"A\u0003tG\u0006d\u0017-\u0003\u0003\u0002\u001a\u0005M!aA%oi\u0006Q1/\u001f8d/JLG/Z:\u0011\t\u0005E\u0011qD\u0005\u0005\u0003C\t\u0019BA\u0004C_>dW-\u00198\u0002\u0019]\u0014\u0018\u000e^3NKR\u0014\u0018nY:\u0011\t\u0005\u001d\u0012QF\u0007\u0003\u0003SQ1!a\u000bX\u0003\u001d\u0019\b.\u001e4gY\u0016LA!a\f\u0002*\tY2\u000b[;gM2,wK]5uK6+GO]5dgJ+\u0007o\u001c:uKJ\fqA\u00197pG.LE-\u0006\u0002\u00026A!\u0011qGA\u001d\u001b\u0005)\u0016bAA\u001e+\n9!\t\\8dW&#\u0017\u0001\u00032m_\u000e\\\u0017\n\u001a\u0011\u0002\rqJg.\u001b;?)A\t\u0019%!\u0012\u0002H\u0005%\u00131JA'\u0003\u001f\n\t\u0006E\u0002\u00028\u0001AQ\u0001\u001e\u0006A\u0002]DQa\u001f\u0006A\u0002qDq!!\u0002\u000b\u0001\u0004\t9\u0001C\u0004\u0002\u000e)\u0001\r!a\u0004\t\u000f\u0005m!\u00021\u0001\u0002\u001e!9\u00111\u0005\u0006A\u0002\u0005\u0015\u0002\"CA\u0019\u0015A\u0005\t\u0019AA\u001b\u0005]i\u0015M\\;bY\u000ecwn]3PkR\u0004X\u000f^*ue\u0016\fWn\u0005\u0002\f=\u00061A%\u001b8ji\u0012\"\"!a\u0017\u0011\t\u0005E\u0011QL\u0005\u0005\u0003?\n\u0019B\u0001\u0003V]&$\u0018!B2m_N,\u0017aC7b]V\fGn\u00117pg\u0016\f1b];qKJ$3\r\\8tK&\u0019\u0011\u0011\r3\u0002\u000f\rD\u0017M\u001c8fYV\u0011\u0011Q\u000e\t\u0005\u0003_\nI(\u0004\u0002\u0002r)!\u00111OA;\u0003!\u0019\u0007.\u00198oK2\u001c(bAA<E\u0006\u0019a.[8\n\t\u0005m\u0014\u0011\u000f\u0002\f\r&dWm\u00115b]:,G.A\u0006dQ\u0006tg.\u001a7`I\u0015\fH\u0003BA.\u0003\u0003C\u0011\"a!\u0012\u0003\u0003\u0005\r!!\u001c\u0002\u0007a$\u0013'\u0001\u0005dQ\u0006tg.\u001a7!\u0003\ri7m]\u000b\u0003\u0003\u0017\u00032!!$\f\u001b\u0005\u0001\u0011aB7dg~#S-\u001d\u000b\u0005\u00037\n\u0019\nC\u0005\u0002\u0004R\t\t\u00111\u0001\u0002\f\u0006!QnY:!\u0003\t\u00117/F\u0001_\u0003\u0019\u00117o\u0018\u0013fcR!\u00111LAP\u0011!\t\u0019iFA\u0001\u0002\u0004q\u0016a\u00012tA\u0005\u0019am\\:\u0016\u0005\u0005\u001d\u0006cA0\u0002*&\u0019\u00111\u00161\u0003!\u0019KG.Z(viB,Ho\u0015;sK\u0006l\u0017a\u00024pg~#S-\u001d\u000b\u0005\u00037\n\t\fC\u0005\u0002\u0004j\t\t\u00111\u0001\u0002(\u0006!am\\:!\u0003\t!8/\u0006\u0002\u0002:B!\u0011qGA^\u0013\r\ti,\u0016\u0002\u0019)&lW\r\u0016:bG.LgnZ(viB,Ho\u0015;sK\u0006l\u0017A\u0002;t?\u0012*\u0017\u000f\u0006\u0003\u0002\\\u0005\r\u0007\"CAB;\u0005\u0005\t\u0019AA]\u0003\r!8\u000fI\u0001\u0007_\nTw*\u001e;\u0016\u0005\u0005-\u0007cA?\u0002N&\u0019\u0011q\u001a@\u0003'M+'/[1mSj\fG/[8o'R\u0014X-Y7\u0002\u0015=\u0014'nT;u?\u0012*\u0017\u000f\u0006\u0003\u0002\\\u0005U\u0007\"CABA\u0005\u0005\t\u0019AAf\u0003\u001dy'M[(vi\u0002\n1\"\u001b8ji&\fG.\u001b>fIV\u0011\u0011QD\u0001\u0010S:LG/[1mSj,Gm\u0018\u0013fcR!\u00111LAq\u0011%\t\u0019iIA\u0001\u0002\u0004\ti\"\u0001\u0007j]&$\u0018.\u00197ju\u0016$\u0007%\u0001\u0006tiJ,\u0017-\\(qK:\fab\u001d;sK\u0006lw\n]3o?\u0012*\u0017\u000f\u0006\u0003\u0002\\\u0005-\b\"CABM\u0005\u0005\t\u0019AA\u000f\u0003-\u0019HO]3b[>\u0003XM\u001c\u0011\u0002\u001b!\f7OQ3f]\u000ecwn]3e\u0003EA\u0017m\u001d\"fK:\u001cEn\\:fI~#S-\u001d\u000b\u0005\u00037\n)\u0010C\u0005\u0002\u0004&\n\t\u00111\u0001\u0002\u001e\u0005q\u0001.Y:CK\u0016t7\t\\8tK\u0012\u0004\u0013aD2iK\u000e\\7/^7F]\u0006\u0014G.\u001a3\u0002'\rDWmY6tk6,e.\u00192mK\u0012|F%Z9\u0015\t\u0005m\u0013q \u0005\n\u0003\u0007c\u0013\u0011!a\u0001\u0003;\t\u0001c\u00195fG.\u001cX/\\#oC\ndW\r\u001a\u0011\u0002)\rDWmY6tk6|U\u000f\u001e9viN#(/Z1n+\t\u00119\u0001\u0005\u0003\u0003\n\t5QB\u0001B\u0006\u0015\t\tw+\u0003\u0003\u0003\u0010\t-!AG'vi\u0006\u0014G.Z\"iK\u000e\\W\rZ(viB,Ho\u0015;sK\u0006l\u0017\u0001G2iK\u000e\\7/^7PkR\u0004X\u000f^*ue\u0016\fWn\u0018\u0013fcR!\u00111\fB\u000b\u0011%\t\u0019iLA\u0001\u0002\u0004\u00119!A\u000bdQ\u0016\u001c7n];n\u001fV$\b/\u001e;TiJ,\u0017-\u001c\u0011\u0002\u0011\rDWmY6tk6,\"A!\b\u0011\t\t}!qE\u0007\u0003\u0005CQAAa\t\u0003&\u0005\u0019!0\u001b9\u000b\u0005E\u0014\u0017\u0002\u0002B\u0015\u0005C\u0011\u0001b\u00115fG.\u001cX/\\\u0001\rG\",7m[:v[~#S-\u001d\u000b\u0005\u00037\u0012y\u0003C\u0005\u0002\u0004J\n\t\u00111\u0001\u0003\u001e\u0005I1\r[3dWN,X\u000eI\u0001\u0012G>lW.\u001b;uK\u0012\u0004vn]5uS>tWC\u0001B\u001c!\u0011\t\tB!\u000f\n\t\tm\u00121\u0003\u0002\u0005\u0019>tw-A\u000bd_6l\u0017\u000e\u001e;fIB{7/\u001b;j_:|F%Z9\u0015\t\u0005m#\u0011\t\u0005\n\u0003\u0007+\u0014\u0011!a\u0001\u0005o\t!cY8n[&$H/\u001a3Q_NLG/[8oA\u0005\u0001\"/\u001a9peR,G\rU8tSRLwN\\\u0001\u0015e\u0016\u0004xN\u001d;fIB{7/\u001b;j_:|F%Z9\u0015\t\u0005m#1\n\u0005\n\u0003\u0007C\u0014\u0011!a\u0001\u0005o\t\u0011C]3q_J$X\r\u001a)pg&$\u0018n\u001c8!\u0003EqW/\u001c*fG>\u0014Hm],sSR$XM\\\u000b\u0003\u0003\u001f\tQC\\;n%\u0016\u001cwN\u001d3t/JLG\u000f^3o?\u0012*\u0017\u000f\u0006\u0003\u0002\\\t]\u0003\"CABw\u0005\u0005\t\u0019AA\b\u0003IqW/\u001c*fG>\u0014Hm],sSR$XM\u001c\u0011\u0002'9,XNU3d_J$7oQ8n[&$H/\u001a3\u0002/9,XNU3d_J$7oQ8n[&$H/\u001a3`I\u0015\fH\u0003BA.\u0005CB\u0011\"a!?\u0003\u0003\u0005\rAa\u000e\u0002)9,XNU3d_J$7oQ8n[&$H/\u001a3!\u0003i9W\r^*fe&\fG.\u001b>fe^\u0013\u0018\r\u001d9fIN#(/Z1n\u0003Y9W\r^*fe&\fG.\u001b>bi&|gn\u0015;sK\u0006l\u0017aC:fi\u000eCWmY6tk6$B!a\u0017\u0003n!9!\u0011\u0004\"A\u0002\tu\u0011AC5oSRL\u0017\r\\5{K\u0006!q\u000e]3o)\t\t\u0019%\u0001\bdY>\u001cXMU3t_V\u00148-Z:\u0002\u0019\r|W.\\5u\u0003:$w)\u001a;\u0015\u0005\tm\u0004\u0003BA\u001c\u0005{J1Aa V\u0005-1\u0015\u000e\\3TK\u001elWM\u001c;\u00027I,g/\u001a:u!\u0006\u0014H/[1m/JLG/Z:B]\u0012\u001cEn\\:f)\u00059\u0018AD2m_N,\u0017I\u001c3EK2,G/Z\u0001\u0006oJLG/\u001a\u000b\u0007\u00037\u0012YI!&\t\u000f\t5%\n1\u0001\u0003\u0010\u0006\u00191.Z=\u0011\t\u0005E!\u0011S\u0005\u0005\u0005'\u000b\u0019BA\u0002B]fDqAa&K\u0001\u0004\u0011y)A\u0003wC2,X\r\u0006\u0003\u0002\\\tm\u0005b\u0002BO\u0017\u0002\u0007\u0011qB\u0001\u0002ERA\u00111\fBQ\u0005c\u0013)\fC\u0004\u0003$2\u0003\rA!*\u0002\u000f-4()\u001f;fgB1\u0011\u0011\u0003BT\u0005WKAA!+\u0002\u0014\t)\u0011I\u001d:bsB!\u0011\u0011\u0003BW\u0013\u0011\u0011y+a\u0005\u0003\t\tKH/\u001a\u0005\b\u0005gc\u0005\u0019AA\b\u0003\u0011ygMZ:\t\u000f\t]F\n1\u0001\u0002\u0010\u0005\u0019A.\u001a8\u0002\u001bI,7m\u001c:e/JLG\u000f^3o\u0003I)\b\u000fZ1uK\nKH/Z:Xe&$H/\u001a8\u0002\u000b\u0019dWo\u001d5\u0002+\u0011K7o\u001b\"m_\u000e\\wJ\u00196fGR<&/\u001b;feB\u0019\u0011qG)\u0014\u0007E\u0013)\r\u0005\u0003\u0002\u0012\t\u001d\u0017\u0002\u0002Be\u0003'\u0011a!\u00118z%\u00164GC\u0001Ba\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%oU\u0011!\u0011\u001b\u0016\u0005\u0003k\u0011\u0019n\u000b\u0002\u0003VB!!q\u001bBq\u001b\t\u0011IN\u0003\u0003\u0003\\\nu\u0017!C;oG\",7m[3e\u0015\u0011\u0011y.a\u0005\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003d\ne'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0002"
)
public class DiskBlockObjectWriter extends OutputStream implements Logging, PairsWriter {
   private final File file;
   private final SerializerManager serializerManager;
   private final SerializerInstance serializerInstance;
   public final int org$apache$spark$storage$DiskBlockObjectWriter$$bufferSize;
   private final boolean syncWrites;
   private final ShuffleWriteMetricsReporter writeMetrics;
   private final BlockId blockId;
   private FileChannel channel;
   private ManualCloseOutputStream mcs;
   private OutputStream bs;
   private FileOutputStream fos;
   private TimeTrackingOutputStream org$apache$spark$storage$DiskBlockObjectWriter$$ts;
   private SerializationStream objOut;
   private boolean initialized;
   private boolean streamOpen;
   private boolean hasBeenClosed;
   private boolean org$apache$spark$storage$DiskBlockObjectWriter$$checksumEnabled;
   private MutableCheckedOutputStream org$apache$spark$storage$DiskBlockObjectWriter$$checksumOutputStream;
   private Checksum checksum;
   private long committedPosition;
   private long reportedPosition;
   private int numRecordsWritten;
   private long numRecordsCommitted;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static BlockId $lessinit$greater$default$7() {
      return DiskBlockObjectWriter$.MODULE$.$lessinit$greater$default$7();
   }

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

   public File file() {
      return this.file;
   }

   public BlockId blockId() {
      return this.blockId;
   }

   private FileChannel channel() {
      return this.channel;
   }

   private void channel_$eq(final FileChannel x$1) {
      this.channel = x$1;
   }

   private ManualCloseOutputStream mcs() {
      return this.mcs;
   }

   private void mcs_$eq(final ManualCloseOutputStream x$1) {
      this.mcs = x$1;
   }

   private OutputStream bs() {
      return this.bs;
   }

   private void bs_$eq(final OutputStream x$1) {
      this.bs = x$1;
   }

   private FileOutputStream fos() {
      return this.fos;
   }

   private void fos_$eq(final FileOutputStream x$1) {
      this.fos = x$1;
   }

   public TimeTrackingOutputStream org$apache$spark$storage$DiskBlockObjectWriter$$ts() {
      return this.org$apache$spark$storage$DiskBlockObjectWriter$$ts;
   }

   private void ts_$eq(final TimeTrackingOutputStream x$1) {
      this.org$apache$spark$storage$DiskBlockObjectWriter$$ts = x$1;
   }

   private SerializationStream objOut() {
      return this.objOut;
   }

   private void objOut_$eq(final SerializationStream x$1) {
      this.objOut = x$1;
   }

   private boolean initialized() {
      return this.initialized;
   }

   private void initialized_$eq(final boolean x$1) {
      this.initialized = x$1;
   }

   private boolean streamOpen() {
      return this.streamOpen;
   }

   private void streamOpen_$eq(final boolean x$1) {
      this.streamOpen = x$1;
   }

   private boolean hasBeenClosed() {
      return this.hasBeenClosed;
   }

   private void hasBeenClosed_$eq(final boolean x$1) {
      this.hasBeenClosed = x$1;
   }

   public boolean org$apache$spark$storage$DiskBlockObjectWriter$$checksumEnabled() {
      return this.org$apache$spark$storage$DiskBlockObjectWriter$$checksumEnabled;
   }

   private void checksumEnabled_$eq(final boolean x$1) {
      this.org$apache$spark$storage$DiskBlockObjectWriter$$checksumEnabled = x$1;
   }

   public MutableCheckedOutputStream org$apache$spark$storage$DiskBlockObjectWriter$$checksumOutputStream() {
      return this.org$apache$spark$storage$DiskBlockObjectWriter$$checksumOutputStream;
   }

   private void checksumOutputStream_$eq(final MutableCheckedOutputStream x$1) {
      this.org$apache$spark$storage$DiskBlockObjectWriter$$checksumOutputStream = x$1;
   }

   private Checksum checksum() {
      return this.checksum;
   }

   private void checksum_$eq(final Checksum x$1) {
      this.checksum = x$1;
   }

   private long committedPosition() {
      return this.committedPosition;
   }

   private void committedPosition_$eq(final long x$1) {
      this.committedPosition = x$1;
   }

   private long reportedPosition() {
      return this.reportedPosition;
   }

   private void reportedPosition_$eq(final long x$1) {
      this.reportedPosition = x$1;
   }

   private int numRecordsWritten() {
      return this.numRecordsWritten;
   }

   private void numRecordsWritten_$eq(final int x$1) {
      this.numRecordsWritten = x$1;
   }

   private long numRecordsCommitted() {
      return this.numRecordsCommitted;
   }

   private void numRecordsCommitted_$eq(final long x$1) {
      this.numRecordsCommitted = x$1;
   }

   public OutputStream getSerializerWrappedStream() {
      return this.bs();
   }

   public SerializationStream getSerializationStream() {
      return this.objOut();
   }

   public void setChecksum(final Checksum checksum) {
      if (this.org$apache$spark$storage$DiskBlockObjectWriter$$checksumOutputStream() == null) {
         this.checksumEnabled_$eq(true);
         this.checksum_$eq(checksum);
      } else {
         this.org$apache$spark$storage$DiskBlockObjectWriter$$checksumOutputStream().setChecksum(checksum);
      }
   }

   private void initialize() {
      this.fos_$eq(new FileOutputStream(this.file(), true));
      this.channel_$eq(this.fos().getChannel());
      this.ts_$eq(new TimeTrackingOutputStream(this.writeMetrics, this.fos()));
      if (this.org$apache$spark$storage$DiskBlockObjectWriter$$checksumEnabled()) {
         .MODULE$.assert(this.checksum() != null, () -> "Checksum is not set");
         this.checksumOutputStream_$eq(new MutableCheckedOutputStream(this.org$apache$spark$storage$DiskBlockObjectWriter$$ts()));
         this.org$apache$spark$storage$DiskBlockObjectWriter$$checksumOutputStream().setChecksum(this.checksum());
      }

      class ManualCloseBufferedOutputStream$1 extends BufferedOutputStream implements ManualCloseOutputStream {
         // $FF: synthetic field
         private final DiskBlockObjectWriter $outer;

         // $FF: synthetic method
         public void org$apache$spark$storage$DiskBlockObjectWriter$ManualCloseOutputStream$$super$close() {
            super.close();
         }

         public void close() {
            DiskBlockObjectWriter.ManualCloseOutputStream.super.close();
         }

         public void manualClose() {
            DiskBlockObjectWriter.ManualCloseOutputStream.super.manualClose();
         }

         // $FF: synthetic method
         public DiskBlockObjectWriter org$apache$spark$storage$DiskBlockObjectWriter$ManualCloseOutputStream$$$outer() {
            return this.$outer;
         }

         public ManualCloseBufferedOutputStream$1() {
            if (DiskBlockObjectWriter.this == null) {
               throw null;
            } else {
               this.$outer = DiskBlockObjectWriter.this;
               super((OutputStream)(DiskBlockObjectWriter.this.org$apache$spark$storage$DiskBlockObjectWriter$$checksumEnabled() ? DiskBlockObjectWriter.this.org$apache$spark$storage$DiskBlockObjectWriter$$checksumOutputStream() : DiskBlockObjectWriter.this.org$apache$spark$storage$DiskBlockObjectWriter$$ts()), DiskBlockObjectWriter.this.org$apache$spark$storage$DiskBlockObjectWriter$$bufferSize);
               DiskBlockObjectWriter.ManualCloseOutputStream.$init$(this);
            }
         }
      }

      this.mcs_$eq(new ManualCloseBufferedOutputStream$1());
   }

   public DiskBlockObjectWriter open() {
      if (this.hasBeenClosed()) {
         throw org.apache.spark.SparkException..MODULE$.internalError("Writer already closed. Cannot be reopened.", "STORAGE");
      } else {
         if (!this.initialized()) {
            this.initialize();
            this.initialized_$eq(true);
         }

         this.bs_$eq(this.serializerManager.wrapStream(this.blockId(), (OutputStream)this.mcs()));
         this.objOut_$eq(this.serializerInstance.serializeStream(this.bs()));
         this.streamOpen_$eq(true);
         return this;
      }
   }

   private void closeResources() {
      try {
         if (this.streamOpen()) {
            Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> {
               if (this.objOut() != null) {
                  this.objOut().close();
               }

               this.bs_$eq((OutputStream)null);
            }, (JFunction0.mcV.sp)() -> {
               this.objOut_$eq((SerializationStream)null);
               if (this.bs() != null) {
                  this.bs().close();
               }

               this.bs_$eq((OutputStream)null);
            });
         }
      } catch (IOException var5) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception occurred while closing the output stream"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var5.getMessage())}))))));
      } finally {
         if (this.initialized()) {
            Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> this.mcs().manualClose(), (JFunction0.mcV.sp)() -> {
               this.channel_$eq((FileChannel)null);
               this.mcs_$eq((ManualCloseOutputStream)null);
               this.bs_$eq((OutputStream)null);
               this.fos_$eq((FileOutputStream)null);
               this.ts_$eq((TimeTrackingOutputStream)null);
               this.objOut_$eq((SerializationStream)null);
               this.initialized_$eq(false);
               this.streamOpen_$eq(false);
               this.hasBeenClosed_$eq(true);
            });
         }

      }

   }

   public void close() {
      if (this.initialized()) {
         Utils$.MODULE$.tryWithSafeFinally(() -> this.commitAndGet(), (JFunction0.mcV.sp)() -> this.closeResources());
      }
   }

   public FileSegment commitAndGet() {
      if (this.streamOpen()) {
         this.objOut().flush();
         this.bs().flush();
         this.objOut().close();
         this.streamOpen_$eq(false);
         if (this.syncWrites) {
            long start = System.nanoTime();
            this.fos().getFD().sync();
            this.writeMetrics.incWriteTime(System.nanoTime() - start);
         }

         long pos = this.channel().position();
         FileSegment fileSegment = new FileSegment(this.file(), this.committedPosition(), pos - this.committedPosition());
         this.committedPosition_$eq(pos);
         this.writeMetrics.incBytesWritten(this.committedPosition() - this.reportedPosition());
         this.reportedPosition_$eq(this.committedPosition());
         this.numRecordsCommitted_$eq(this.numRecordsCommitted() + (long)this.numRecordsWritten());
         this.numRecordsWritten_$eq(0);
         return fileSegment;
      } else {
         return new FileSegment(this.file(), this.committedPosition(), 0L);
      }
   }

   public File revertPartialWritesAndClose() {
      Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> {
         if (this.initialized()) {
            this.writeMetrics.decBytesWritten(this.reportedPosition() - this.committedPosition());
            this.writeMetrics.decRecordsWritten((long)this.numRecordsWritten());
            this.streamOpen_$eq(false);
            this.closeResources();
         }
      }, (JFunction0.mcV.sp)() -> {
         FileOutputStream truncateStream = null;

         try {
            truncateStream = new FileOutputStream(this.file(), true);
            truncateStream.getChannel().truncate(this.committedPosition());
         } catch (ClosedByInterruptException var8) {
            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception occurred while reverting partial writes to file "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.file()), new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var8.getMessage())}))))));
         } catch (Exception var9) {
            this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Uncaught exception while reverting partial writes to file ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.file())})))), var9);
         } finally {
            if (truncateStream != null) {
               truncateStream.close();
               FileOutputStream var11 = null;
            }

         }

      });
      return this.file();
   }

   public void closeAndDelete() {
      Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> {
         if (this.initialized()) {
            this.writeMetrics.decBytesWritten(this.reportedPosition());
            this.writeMetrics.decRecordsWritten(this.numRecordsCommitted() + (long)this.numRecordsWritten());
            this.closeResources();
         }
      }, (JFunction0.mcV.sp)() -> {
         if (!Files.deleteIfExists(this.file().toPath())) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error deleting ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, this.file())})))));
         }
      });
   }

   public void write(final Object key, final Object value) {
      if (!this.streamOpen()) {
         this.open();
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this.objOut().writeKey(key, scala.reflect.ClassTag..MODULE$.Any());
      this.objOut().writeValue(value, scala.reflect.ClassTag..MODULE$.Any());
      this.recordWritten();
   }

   public void write(final int b) {
      throw SparkCoreErrors$.MODULE$.unsupportedOperationError();
   }

   public void write(final byte[] kvBytes, final int offs, final int len) {
      if (!this.streamOpen()) {
         this.open();
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this.bs().write(kvBytes, offs, len);
   }

   public void recordWritten() {
      this.numRecordsWritten_$eq(this.numRecordsWritten() + 1);
      this.writeMetrics.incRecordsWritten(1L);
      if (this.numRecordsWritten() % 16384 == 0) {
         this.updateBytesWritten();
      }
   }

   private void updateBytesWritten() {
      long pos = this.channel().position();
      this.writeMetrics.incBytesWritten(pos - this.reportedPosition());
      this.reportedPosition_$eq(pos);
   }

   public void flush() {
      this.objOut().flush();
      this.bs().flush();
   }

   public DiskBlockObjectWriter(final File file, final SerializerManager serializerManager, final SerializerInstance serializerInstance, final int bufferSize, final boolean syncWrites, final ShuffleWriteMetricsReporter writeMetrics, final BlockId blockId) {
      this.file = file;
      this.serializerManager = serializerManager;
      this.serializerInstance = serializerInstance;
      this.org$apache$spark$storage$DiskBlockObjectWriter$$bufferSize = bufferSize;
      this.syncWrites = syncWrites;
      this.writeMetrics = writeMetrics;
      this.blockId = blockId;
      Logging.$init$(this);
      this.channel = null;
      this.mcs = null;
      this.bs = null;
      this.fos = null;
      this.org$apache$spark$storage$DiskBlockObjectWriter$$ts = null;
      this.objOut = null;
      this.initialized = false;
      this.streamOpen = false;
      this.hasBeenClosed = false;
      this.org$apache$spark$storage$DiskBlockObjectWriter$$checksumEnabled = false;
      this.committedPosition = file.length();
      this.reportedPosition = this.committedPosition();
      this.numRecordsWritten = 0;
      this.numRecordsCommitted = 0L;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private interface ManualCloseOutputStream {
      // $FF: synthetic method
      void org$apache$spark$storage$DiskBlockObjectWriter$ManualCloseOutputStream$$super$close();

      default void close() {
         ((OutputStream)this).flush();
      }

      default void manualClose() {
         try {
            this.org$apache$spark$storage$DiskBlockObjectWriter$ManualCloseOutputStream$$super$close();
         } catch (IOException var2) {
            this.org$apache$spark$storage$DiskBlockObjectWriter$ManualCloseOutputStream$$$outer().logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$storage$DiskBlockObjectWriter$ManualCloseOutputStream$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception occurred while manually close the output stream to file "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.org$apache$spark$storage$DiskBlockObjectWriter$ManualCloseOutputStream$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.org$apache$spark$storage$DiskBlockObjectWriter$ManualCloseOutputStream$$$outer().file()), new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var2.getMessage())}))))));
         }

      }

      // $FF: synthetic method
      DiskBlockObjectWriter org$apache$spark$storage$DiskBlockObjectWriter$ManualCloseOutputStream$$$outer();

      static void $init$(final ManualCloseOutputStream $this) {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
