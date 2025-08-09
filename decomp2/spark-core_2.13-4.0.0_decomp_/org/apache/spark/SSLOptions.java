package org.apache.spark;

import java.io.File;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.network.util.ConfigProvider;
import org.apache.spark.network.util.MapConfigProvider;
import org.slf4j.Logger;
import org.sparkproject.jetty.util.ssl.SslContextFactory;
import scala.Function0;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011ua!CA\f\u00033\u0001\u0015\u0011DA\u0013\u0011)\ty\u0006\u0001BK\u0002\u0013\u0005\u0011\u0011\r\u0005\u000b\u0003s\u0002!\u0011#Q\u0001\n\u0005\r\u0004BCA>\u0001\tU\r\u0011\"\u0001\u0002~!Q\u0011Q\u0011\u0001\u0003\u0012\u0003\u0006I!a \t\u0015\u0005\u001d\u0005A!f\u0001\n\u0003\tI\t\u0003\u0006\u0002\u0014\u0002\u0011\t\u0012)A\u0005\u0003\u0017C!\"!&\u0001\u0005+\u0007I\u0011AAL\u0011)\tY\u000b\u0001B\tB\u0003%\u0011\u0011\u0014\u0005\u000b\u0003[\u0003!Q3A\u0005\u0002\u0005\u0005\u0004BCAX\u0001\tE\t\u0015!\u0003\u0002d!Q\u0011\u0011\u0017\u0001\u0003\u0016\u0004%\t!a&\t\u0015\u0005M\u0006A!E!\u0002\u0013\tI\n\u0003\u0006\u00026\u0002\u0011)\u001a!C\u0001\u0003CB!\"a.\u0001\u0005#\u0005\u000b\u0011BA2\u0011)\tI\f\u0001BK\u0002\u0013\u0005\u0011\u0011\r\u0005\u000b\u0003w\u0003!\u0011#Q\u0001\n\u0005\r\u0004BCA_\u0001\tU\r\u0011\"\u0001\u0002~!Q\u0011q\u0018\u0001\u0003\u0012\u0003\u0006I!a \t\u0015\u0005\u0005\u0007A!f\u0001\n\u0003\t9\n\u0003\u0006\u0002D\u0002\u0011\t\u0012)A\u0005\u00033C!\"!2\u0001\u0005+\u0007I\u0011AAL\u0011)\t9\r\u0001B\tB\u0003%\u0011\u0011\u0014\u0005\u000b\u0003\u0013\u0004!Q3A\u0005\u0002\u0005\u0005\u0004BCAf\u0001\tE\t\u0015!\u0003\u0002d!Q\u0011Q\u001a\u0001\u0003\u0016\u0004%\t!!\u0019\t\u0015\u0005=\u0007A!E!\u0002\u0013\t\u0019\u0007\u0003\u0006\u0002R\u0002\u0011)\u001a!C\u0001\u0003{B!\"a5\u0001\u0005#\u0005\u000b\u0011BA@\u0011)\t)\u000e\u0001BK\u0002\u0013\u0005\u0011q\u001b\u0005\u000b\u00033\u0004!\u0011#Q\u0001\n\u00055\u0005BCAn\u0001\tU\r\u0011\"\u0001\u0002~!Q\u0011Q\u001c\u0001\u0003\u0012\u0003\u0006I!a \t\u0015\u0005}\u0007A!f\u0001\n\u0003\t\t\u0007\u0003\u0006\u0002b\u0002\u0011\t\u0012)A\u0005\u0003GB!\"a9\u0001\u0005+\u0007I\u0011AAs\u0011)\ti\u000f\u0001B\tB\u0003%\u0011q\u001d\u0005\u000b\u0003_\u0004!Q3A\u0005\u0002\u0005\u0005\u0004BCAy\u0001\tE\t\u0015!\u0003\u0002d!9\u00111\u001f\u0001\u0005\u0002\u0005U\bb\u0002B\u0011\u0001\u0011\u0005!1\u0005\u0005\n\u0005\u000f\u0002!\u0019!C\u0005\u0003KD\u0001B!\u0013\u0001A\u0003%\u0011q\u001d\u0005\b\u0005\u0017\u0002A\u0011\u0001B'\u0011\u001d\u00119\u0007\u0001C!\u0005SB\u0011Ba\u001b\u0001\u0003\u0003%\tA!\u001c\t\u0013\tU\u0005!%A\u0005\u0002\t]\u0005\"\u0003BW\u0001E\u0005I\u0011\u0001BX\u0011%\u0011\u0019\fAI\u0001\n\u0003\u0011)\fC\u0005\u0003:\u0002\t\n\u0011\"\u0001\u0003<\"I!q\u0018\u0001\u0012\u0002\u0013\u0005!q\u0013\u0005\n\u0005\u0003\u0004\u0011\u0013!C\u0001\u0005wC\u0011Ba1\u0001#\u0003%\tAa&\t\u0013\t\u0015\u0007!%A\u0005\u0002\t]\u0005\"\u0003Bd\u0001E\u0005I\u0011\u0001BX\u0011%\u0011I\rAI\u0001\n\u0003\u0011Y\fC\u0005\u0003L\u0002\t\n\u0011\"\u0001\u0003<\"I!Q\u001a\u0001\u0012\u0002\u0013\u0005!q\u0013\u0005\n\u0005\u001f\u0004\u0011\u0013!C\u0001\u0005/C\u0011B!5\u0001#\u0003%\tAa,\t\u0013\tM\u0007!%A\u0005\u0002\tU\u0007\"\u0003Bm\u0001E\u0005I\u0011\u0001BX\u0011%\u0011Y\u000eAI\u0001\n\u0003\u00119\nC\u0005\u0003^\u0002\t\n\u0011\"\u0001\u0003`\"I!1\u001d\u0001\u0012\u0002\u0013\u0005!q\u0013\u0005\n\u0005K\u0004\u0011\u0011!C!\u0005OD\u0011Ba=\u0001\u0003\u0003%\t!a6\t\u0013\tU\b!!A\u0005\u0002\t]\b\"CB\u0002\u0001\u0005\u0005I\u0011IB\u0003\u0011%\u0019\u0019\u0002AA\u0001\n\u0003\u0019)\u0002C\u0005\u0004\u001a\u0001\t\t\u0011\"\u0011\u0004\u001c!I1q\u0004\u0001\u0002\u0002\u0013\u00053\u0011\u0005\u0005\n\u0007G\u0001\u0011\u0011!C!\u0007K9!b!\u000b\u0002\u001a!\u0005\u0011\u0011DB\u0016\r)\t9\"!\u0007\t\u0002\u0005e1Q\u0006\u0005\b\u0003gTE\u0011AB\u001a\u0011\u001d\u0019)D\u0013C\u0001\u0007oA\u0011ba\u0016K#\u0003%\ta!\u0017\t\u0013\ru#J1A\u0005\u0002\t\u001d\b\u0002CB0\u0015\u0002\u0006IA!;\t\u0013\r\u0005$J1A\u0005\u0002\t\u001d\b\u0002CB2\u0015\u0002\u0006IA!;\t\u0013\r\u0015$J1A\u0005\u0002\t\u001d\b\u0002CB4\u0015\u0002\u0006IA!;\t\u0013\r%$J1A\u0005\u0002\t\u001d\b\u0002CB6\u0015\u0002\u0006IA!;\t\u0013\r5$J1A\u0005\u0002\r=\u0004\u0002CB<\u0015\u0002\u0006Ia!\u001d\t\u0013\re$J1A\u0005\u0002\t\u001d\b\u0002CB>\u0015\u0002\u0006IA!;\t\u0013\ru$J1A\u0005\u0002\t\u001d\b\u0002CB@\u0015\u0002\u0006IA!;\t\u0013\r\u0005%J1A\u0005\u0002\t\u001d\b\u0002CBB\u0015\u0002\u0006IA!;\t\u0013\r\u0015%J1A\u0005\u0002\t\u001d\b\u0002CBD\u0015\u0002\u0006IA!;\t\u0013\r%%J1A\u0005\u0002\r=\u0004\u0002CBF\u0015\u0002\u0006Ia!\u001d\t\u0013\r5%*!A\u0005\u0002\u000e=\u0005\"CB\\\u0015F\u0005I\u0011\u0001BL\u0011%\u0019ILSI\u0001\n\u0003\u0011y\u000bC\u0005\u0004<*\u000b\n\u0011\"\u0001\u00036\"I1Q\u0018&\u0012\u0002\u0013\u0005!1\u0018\u0005\n\u0007\u007fS\u0015\u0013!C\u0001\u0005/C\u0011b!1K#\u0003%\tAa/\t\u0013\r\r'*%A\u0005\u0002\t]\u0005\"CBc\u0015F\u0005I\u0011\u0001BL\u0011%\u00199MSI\u0001\n\u0003\u0011y\u000bC\u0005\u0004J*\u000b\n\u0011\"\u0001\u0003<\"I11\u001a&\u0012\u0002\u0013\u0005!1\u0018\u0005\n\u0007\u001bT\u0015\u0013!C\u0001\u0005/C\u0011ba4K#\u0003%\tAa&\t\u0013\rE'*%A\u0005\u0002\t=\u0006\"CBj\u0015F\u0005I\u0011\u0001Bk\u0011%\u0019)NSI\u0001\n\u0003\u0011y\u000bC\u0005\u0004X*\u000b\n\u0011\"\u0001\u0003\u0018\"I1\u0011\u001c&\u0012\u0002\u0013\u0005!q\u001c\u0005\n\u00077T\u0015\u0013!C\u0001\u0005/C\u0011b!8K\u0003\u0003%\tia8\t\u0013\r5(*%A\u0005\u0002\t]\u0005\"CBx\u0015F\u0005I\u0011\u0001BX\u0011%\u0019\tPSI\u0001\n\u0003\u0011)\fC\u0005\u0004t*\u000b\n\u0011\"\u0001\u0003<\"I1Q\u001f&\u0012\u0002\u0013\u0005!q\u0013\u0005\n\u0007oT\u0015\u0013!C\u0001\u0005wC\u0011b!?K#\u0003%\tAa&\t\u0013\rm(*%A\u0005\u0002\t]\u0005\"CB\u007f\u0015F\u0005I\u0011\u0001BX\u0011%\u0019yPSI\u0001\n\u0003\u0011Y\fC\u0005\u0005\u0002)\u000b\n\u0011\"\u0001\u0003<\"IA1\u0001&\u0012\u0002\u0013\u0005!q\u0013\u0005\n\t\u000bQ\u0015\u0013!C\u0001\u0005/C\u0011\u0002b\u0002K#\u0003%\tAa,\t\u0013\u0011%!*%A\u0005\u0002\tU\u0007\"\u0003C\u0006\u0015F\u0005I\u0011\u0001BX\u0011%!iASI\u0001\n\u0003\u00119\nC\u0005\u0005\u0010)\u000b\n\u0011\"\u0001\u0003`\"IA\u0011\u0003&\u0012\u0002\u0013\u0005!q\u0013\u0005\n\t'Q\u0015\u0011!C\u0005\t+\u0011!bU*M\u001fB$\u0018n\u001c8t\u0015\u0011\tY\"!\b\u0002\u000bM\u0004\u0018M]6\u000b\t\u0005}\u0011\u0011E\u0001\u0007CB\f7\r[3\u000b\u0005\u0005\r\u0012aA8sONI\u0001!a\n\u00024\u0005}\u0012Q\t\t\u0005\u0003S\ty#\u0004\u0002\u0002,)\u0011\u0011QF\u0001\u0006g\u000e\fG.Y\u0005\u0005\u0003c\tYC\u0001\u0004B]f\u0014VM\u001a\t\u0005\u0003k\tY$\u0004\u0002\u00028)!\u0011\u0011HA\r\u0003!Ig\u000e^3s]\u0006d\u0017\u0002BA\u001f\u0003o\u0011q\u0001T8hO&tw\r\u0005\u0003\u0002*\u0005\u0005\u0013\u0002BA\"\u0003W\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0003\u0002H\u0005ec\u0002BA%\u0003+rA!a\u0013\u0002T5\u0011\u0011Q\n\u0006\u0005\u0003\u001f\n\t&\u0001\u0004=e>|GOP\u0002\u0001\u0013\t\ti#\u0003\u0003\u0002X\u0005-\u0012a\u00029bG.\fw-Z\u0005\u0005\u00037\niF\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0003\u0002X\u0005-\u0012!\u00038b[\u0016\u001c\b/Y2f+\t\t\u0019\u0007\u0005\u0004\u0002*\u0005\u0015\u0014\u0011N\u0005\u0005\u0003O\nYC\u0001\u0004PaRLwN\u001c\t\u0005\u0003W\n\u0019H\u0004\u0003\u0002n\u0005=\u0004\u0003BA&\u0003WIA!!\u001d\u0002,\u00051\u0001K]3eK\u001aLA!!\u001e\u0002x\t11\u000b\u001e:j]\u001eTA!!\u001d\u0002,\u0005Qa.Y7fgB\f7-\u001a\u0011\u0002\u000f\u0015t\u0017M\u00197fIV\u0011\u0011q\u0010\t\u0005\u0003S\t\t)\u0003\u0003\u0002\u0004\u0006-\"a\u0002\"p_2,\u0017M\\\u0001\tK:\f'\r\\3eA\u0005!\u0001o\u001c:u+\t\tY\t\u0005\u0004\u0002*\u0005\u0015\u0014Q\u0012\t\u0005\u0003S\ty)\u0003\u0003\u0002\u0012\u0006-\"aA%oi\u0006)\u0001o\u001c:uA\u0005A1.Z=Ti>\u0014X-\u0006\u0002\u0002\u001aB1\u0011\u0011FA3\u00037\u0003B!!(\u0002(6\u0011\u0011q\u0014\u0006\u0005\u0003C\u000b\u0019+\u0001\u0002j_*\u0011\u0011QU\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002*\u0006}%\u0001\u0002$jY\u0016\f\u0011b[3z'R|'/\u001a\u0011\u0002!-,\u0017p\u0015;pe\u0016\u0004\u0016m]:x_J$\u0017!E6fsN#xN]3QCN\u001cxo\u001c:eA\u0005Q\u0001O]5wCR,7*Z=\u0002\u0017A\u0014\u0018N^1uK.+\u0017\u0010I\u0001\fW\u0016L\b+Y:to>\u0014H-\u0001\u0007lKf\u0004\u0016m]:x_J$\u0007%\u0001\u0007lKf\u001cFo\u001c:f)f\u0004X-A\u0007lKf\u001cFo\u001c:f)f\u0004X\rI\u0001\u000f]\u0016,Gm\u00117jK:$\u0018)\u001e;i\u0003=qW-\u001a3DY&,g\u000e^!vi\"\u0004\u0013!C2feR\u001c\u0005.Y5o\u0003)\u0019WM\u001d;DQ\u0006Lg\u000eI\u0001\u000biJ,8\u000f^*u_J,\u0017a\u0003;skN$8\u000b^8sK\u0002\n!\u0003\u001e:vgR\u001cFo\u001c:f!\u0006\u001c8o^8sI\u0006\u0019BO];tiN#xN]3QCN\u001cxo\u001c:eA\u0005qAO];tiN#xN]3UsB,\u0017a\u0004;skN$8\u000b^8sKRK\b/\u001a\u0011\u00025Q\u0014Xo\u001d;Ti>\u0014XMU3m_\u0006$\u0017N\\4F]\u0006\u0014G.\u001a3\u00027Q\u0014Xo\u001d;Ti>\u0014XMU3m_\u0006$\u0017N\\4F]\u0006\u0014G.\u001a3!\u0003i!(/^:u'R|'/\u001a*fY>\fG-\u00138uKJ4\u0018\r\\'t+\t\ti)A\u000eueV\u001cHo\u0015;pe\u0016\u0014V\r\\8bI&sG/\u001a:wC2l5\u000fI\u0001\u000f_B,gnU:m\u000b:\f'\r\\3e\u0003=y\u0007/\u001a8Tg2,e.\u00192mK\u0012\u0004\u0013\u0001\u00039s_R|7m\u001c7\u0002\u0013A\u0014x\u000e^8d_2\u0004\u0013!E3oC\ndW\rZ!mO>\u0014\u0018\u000e\u001e5ngV\u0011\u0011q\u001d\t\u0007\u0003W\nI/!\u001b\n\t\u0005-\u0018q\u000f\u0002\u0004'\u0016$\u0018AE3oC\ndW\rZ!mO>\u0014\u0018\u000e\u001e5ng\u0002\n!\u0003\u001d:jm\u0006$XmS3z!\u0006\u001c8o^8sI\u0006\u0019\u0002O]5wCR,7*Z=QCN\u001cxo\u001c:eA\u00051A(\u001b8jiz\"\u0002&a>\u0002|\u0006u\u0018q B\u0001\u0005\u0007\u0011)Aa\u0002\u0003\n\t-!Q\u0002B\b\u0005#\u0011\u0019B!\u0006\u0003\u0018\te!1\u0004B\u000f\u0005?\u00012!!?\u0001\u001b\t\tI\u0002C\u0005\u0002`\u001d\u0002\n\u00111\u0001\u0002d!I\u00111P\u0014\u0011\u0002\u0003\u0007\u0011q\u0010\u0005\n\u0003\u000f;\u0003\u0013!a\u0001\u0003\u0017C\u0011\"!&(!\u0003\u0005\r!!'\t\u0013\u00055v\u0005%AA\u0002\u0005\r\u0004\"CAYOA\u0005\t\u0019AAM\u0011%\t)l\nI\u0001\u0002\u0004\t\u0019\u0007C\u0005\u0002:\u001e\u0002\n\u00111\u0001\u0002d!I\u0011QX\u0014\u0011\u0002\u0003\u0007\u0011q\u0010\u0005\n\u0003\u0003<\u0003\u0013!a\u0001\u00033C\u0011\"!2(!\u0003\u0005\r!!'\t\u0013\u0005%w\u0005%AA\u0002\u0005\r\u0004\"CAgOA\u0005\t\u0019AA2\u0011%\t\tn\nI\u0001\u0002\u0004\ty\bC\u0005\u0002V\u001e\u0002\n\u00111\u0001\u0002\u000e\"I\u00111\\\u0014\u0011\u0002\u0003\u0007\u0011q\u0010\u0005\n\u0003?<\u0003\u0013!a\u0001\u0003GB\u0011\"a9(!\u0003\u0005\r!a:\t\u0013\u0005=x\u0005%AA\u0002\u0005\r\u0014AI2sK\u0006$XMS3uif\u001c6\u000f\\\"p]R,\u0007\u0010\u001e$bGR|'/_*feZ,'\u000f\u0006\u0002\u0003&A1\u0011\u0011FA3\u0005O\u0001BA!\u000b\u0003B9!!1\u0006B\u001f\u001b\t\u0011iC\u0003\u0003\u00030\tE\u0012aA:tY*!!1\u0007B\u001b\u0003\u0011)H/\u001b7\u000b\t\t]\"\u0011H\u0001\u0006U\u0016$H/\u001f\u0006\u0005\u0005w\t\t#A\u0004fG2L\u0007o]3\n\t\t}\"QF\u0001\u0012'Nd7i\u001c8uKb$h)Y2u_JL\u0018\u0002\u0002B\"\u0005\u000b\u0012aaU3sm\u0016\u0014(\u0002\u0002B \u0005[\t1c];qa>\u0014H/\u001a3BY\u001e|'/\u001b;i[N\fAc];qa>\u0014H/\u001a3BY\u001e|'/\u001b;i[N\u0004\u0013\u0001F2sK\u0006$XmQ8oM&<\u0007K]8wS\u0012,'\u000f\u0006\u0003\u0003P\tu\u0003\u0003\u0002B)\u00053j!Aa\u0015\u000b\t\tM\"Q\u000b\u0006\u0005\u0005/\nI\"A\u0004oKR<xN]6\n\t\tm#1\u000b\u0002\u000f\u0007>tg-[4Qe>4\u0018\u000eZ3s\u0011\u001d\u0011yf\u000ba\u0001\u0005C\nAaY8oMB!\u0011\u0011 B2\u0013\u0011\u0011)'!\u0007\u0003\u0013M\u0003\u0018M]6D_:4\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005%\u0014\u0001B2paf$\u0002&a>\u0003p\tE$1\u000fB;\u0005o\u0012IHa\u001f\u0003~\t}$\u0011\u0011BB\u0005\u000b\u00139I!#\u0003\f\n5%q\u0012BI\u0005'C\u0011\"a\u0018.!\u0003\u0005\r!a\u0019\t\u0013\u0005mT\u0006%AA\u0002\u0005}\u0004\"CAD[A\u0005\t\u0019AAF\u0011%\t)*\fI\u0001\u0002\u0004\tI\nC\u0005\u0002.6\u0002\n\u00111\u0001\u0002d!I\u0011\u0011W\u0017\u0011\u0002\u0003\u0007\u0011\u0011\u0014\u0005\n\u0003kk\u0003\u0013!a\u0001\u0003GB\u0011\"!/.!\u0003\u0005\r!a\u0019\t\u0013\u0005uV\u0006%AA\u0002\u0005}\u0004\"CAa[A\u0005\t\u0019AAM\u0011%\t)-\fI\u0001\u0002\u0004\tI\nC\u0005\u0002J6\u0002\n\u00111\u0001\u0002d!I\u0011QZ\u0017\u0011\u0002\u0003\u0007\u00111\r\u0005\n\u0003#l\u0003\u0013!a\u0001\u0003\u007fB\u0011\"!6.!\u0003\u0005\r!!$\t\u0013\u0005mW\u0006%AA\u0002\u0005}\u0004\"CAp[A\u0005\t\u0019AA2\u0011%\t\u0019/\fI\u0001\u0002\u0004\t9\u000fC\u0005\u0002p6\u0002\n\u00111\u0001\u0002d\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nTC\u0001BMU\u0011\t\u0019Ga',\u0005\tu\u0005\u0003\u0002BP\u0005Sk!A!)\u000b\t\t\r&QU\u0001\nk:\u001c\u0007.Z2lK\u0012TAAa*\u0002,\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\t-&\u0011\u0015\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0005cSC!a \u0003\u001c\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aTC\u0001B\\U\u0011\tYIa'\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%iU\u0011!Q\u0018\u0016\u0005\u00033\u0013Y*\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001b\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%m\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012:\u0014AD2paf$C-\u001a4bk2$H\u0005O\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u0013:\u0003=\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE\u0002\u0014aD2paf$C-\u001a4bk2$H%M\u0019\u0002\u001f\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cI\nqbY8qs\u0012\"WMZ1vYR$\u0013gM\u0001\u0010G>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132i\u0005y1m\u001c9zI\u0011,g-Y;mi\u0012\nT'\u0006\u0002\u0003X*\"\u0011Q\u0012BN\u0003=\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE2\u0014aD2paf$C-\u001a4bk2$H%M\u001c\u0002\u001f\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%ca*\"A!9+\t\u0005\u001d(1T\u0001\u0010G>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132s\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"A!;\u0011\t\t-(\u0011_\u0007\u0003\u0005[TAAa<\u0002$\u0006!A.\u00198h\u0013\u0011\t)H!<\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!!\u0011 B\u0000!\u0011\tICa?\n\t\tu\u00181\u0006\u0002\u0004\u0003:L\b\"CB\u0001\u0007\u0006\u0005\t\u0019AAG\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u00111q\u0001\t\u0007\u0007\u0013\u0019yA!?\u000e\u0005\r-!\u0002BB\u0007\u0003W\t!bY8mY\u0016\u001cG/[8o\u0013\u0011\u0019\tba\u0003\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u007f\u001a9\u0002C\u0005\u0004\u0002\u0015\u000b\t\u00111\u0001\u0003z\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\u0011Io!\b\t\u0013\r\u0005a)!AA\u0002\u00055\u0015\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u00055\u0015AB3rk\u0006d7\u000f\u0006\u0003\u0002\u0000\r\u001d\u0002\"CB\u0001\u0011\u0006\u0005\t\u0019\u0001B}\u0003)\u00196\u000bT(qi&|gn\u001d\t\u0004\u0003sT5c\u0002&\u0002(\u0005M2q\u0006\t\u0005\u0003;\u001b\t$\u0003\u0003\u0002\\\u0005}ECAB\u0016\u0003\u0015\u0001\u0018M]:f))\t9p!\u000f\u0004<\r53\u0011\u000b\u0005\b\u0005?b\u0005\u0019\u0001B1\u0011\u001d\u0019i\u0004\u0014a\u0001\u0007\u007f\t!\u0002[1e_>\u00048i\u001c8g!\u0011\u0019\te!\u0013\u000e\u0005\r\r#\u0002\u0002B0\u0007\u000bRAaa\u0012\u0002\u001e\u00051\u0001.\u00193p_BLAaa\u0013\u0004D\ti1i\u001c8gS\u001e,(/\u0019;j_:Dqaa\u0014M\u0001\u0004\tI'\u0001\u0002og\"I11\u000b'\u0011\u0002\u0003\u00071QK\u0001\tI\u00164\u0017-\u001e7ugB1\u0011\u0011FA3\u0003o\fq\u0002]1sg\u0016$C-\u001a4bk2$H\u0005N\u000b\u0003\u00077RCa!\u0016\u0003\u001c\u0006y2\u000bU!S\u0017~\u0013\u0006kQ0T'2{6*R-`!\u0006\u001b6kV(S\t~\u001buJ\u0014$\u0002AM\u0003\u0016IU&`%B\u001bulU*M?.+\u0015l\u0018)B'N;vJ\u0015#`\u0007>se\tI\u0001('B\u000b%kS0S!\u000e{6k\u0015'`!JKe+\u0011+F?.+\u0015l\u0018)B'N;vJ\u0015#`\u0007>se)\u0001\u0015T!\u0006\u00136j\u0018*Q\u0007~\u001b6\u000bT0Q%&3\u0016\tV#`\u0017\u0016Kv\fU!T'^{%\u000bR0D\u001f:3\u0005%A\u0013T!\u0006\u00136j\u0018*Q\u0007~\u001b6\u000bT0L\u000bf{6\u000bV(S\u000b~\u0003\u0016iU*X\u001fJ#ulQ(O\r\u000613\u000bU!S\u0017~\u0013\u0006kQ0T'2{6*R-`'R{%+R0Q\u0003N\u001bvk\u0014*E?\u000e{eJ\u0012\u0011\u0002OM\u0003\u0016IU&`%B\u001bulU*M?R\u0013Vk\u0015+`'R{%+R0Q\u0003N\u001bvk\u0014*E?\u000e{eJR\u0001)'B\u000b%kS0S!\u000e{6k\u0015'`)J+6\u000bV0T)>\u0013Vi\u0018)B'N;vJ\u0015#`\u0007>se\tI\u0001\u001e'B\u000b%kS0S!\u000e{6k\u0015'`!\u0006\u001b6kV(S\t~3\u0015*\u0012'E'V\u00111\u0011\u000f\t\u0007\u0003\u000f\u001a\u0019(!\u001b\n\t\rU\u0014Q\f\u0002\u0004'\u0016\f\u0018AH*Q\u0003J[uL\u0015)D?N\u001bFj\u0018)B'N;vJ\u0015#`\r&+E\nR*!\u0003a)eJV0S!\u000e{6k\u0015'`\u0017\u0016Kv\fU!T'^{%\u000bR\u0001\u001a\u000b:3vL\u0015)D?N\u001bFjX&F3~\u0003\u0016iU*X\u001fJ#\u0005%\u0001\u0011F\u001dZ{&\u000bU\"`'Ncu\f\u0015*J-\u0006#ViX&F3~\u0003\u0016iU*X\u001fJ#\u0015!I#O-~\u0013\u0006kQ0T'2{\u0006KU%W\u0003R+ulS#Z?B\u000b5kU,P%\u0012\u0003\u0013AH#O-~\u0013\u0006kQ0T'2{6*R-`'R{%+R0Q\u0003N\u001bvk\u0014*E\u0003})eJV0S!\u000e{6k\u0015'`\u0017\u0016Kvl\u0015+P%\u0016{\u0006+Q*T/>\u0013F\tI\u0001!\u000b:3vL\u0015)D?N\u001bFj\u0018+S+N#vl\u0015+P%\u0016{\u0006+Q*T/>\u0013F)A\u0011F\u001dZ{&\u000bU\"`'Ncu\f\u0016*V'R{6\u000bV(S\u000b~\u0003\u0016iU*X\u001fJ#\u0005%A\u000eT!\u0006\u00136j\u0018*Q\u0007~\u001b6\u000bT0Q\u0003N\u001bvk\u0014*E?\u0016sekU\u0001\u001d'B\u000b%kS0S!\u000e{6k\u0015'`!\u0006\u001b6kV(S\t~+eJV*!\u0003\u0015\t\u0007\u000f\u001d7z)!\n9p!%\u0004\u0014\u000eU5qSBM\u00077\u001bija(\u0004\"\u000e\r6QUBT\u0007S\u001bYk!,\u00040\u000eE61WB[\u0011%\tyF\u0019I\u0001\u0002\u0004\t\u0019\u0007C\u0005\u0002|\t\u0004\n\u00111\u0001\u0002\u0000!I\u0011q\u00112\u0011\u0002\u0003\u0007\u00111\u0012\u0005\n\u0003+\u0013\u0007\u0013!a\u0001\u00033C\u0011\"!,c!\u0003\u0005\r!a\u0019\t\u0013\u0005E&\r%AA\u0002\u0005e\u0005\"CA[EB\u0005\t\u0019AA2\u0011%\tIL\u0019I\u0001\u0002\u0004\t\u0019\u0007C\u0005\u0002>\n\u0004\n\u00111\u0001\u0002\u0000!I\u0011\u0011\u00192\u0011\u0002\u0003\u0007\u0011\u0011\u0014\u0005\n\u0003\u000b\u0014\u0007\u0013!a\u0001\u00033C\u0011\"!3c!\u0003\u0005\r!a\u0019\t\u0013\u00055'\r%AA\u0002\u0005\r\u0004\"CAiEB\u0005\t\u0019AA@\u0011%\t)N\u0019I\u0001\u0002\u0004\ti\tC\u0005\u0002\\\n\u0004\n\u00111\u0001\u0002\u0000!I\u0011q\u001c2\u0011\u0002\u0003\u0007\u00111\r\u0005\n\u0003G\u0014\u0007\u0013!a\u0001\u0003OD\u0011\"a<c!\u0003\u0005\r!a\u0019\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIE\nq\"\u00199qYf$C-\u001a4bk2$HEM\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u0005y\u0011\r\u001d9ms\u0012\"WMZ1vYR$C'A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00136\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u00122\u0014aD1qa2LH\u0005Z3gCVdG\u000fJ\u001c\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIa\nq\"\u00199qYf$C-\u001a4bk2$H%O\u0001\u0011CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%cA\n\u0001#\u00199qYf$C-\u001a4bk2$H%M\u0019\u0002!\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIE\u0012\u0014\u0001E1qa2LH\u0005Z3gCVdG\u000fJ\u00194\u0003A\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\nD'\u0001\tbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00132k\u0005\u0001\u0012\r\u001d9ms\u0012\"WMZ1vYR$\u0013GN\u0001\u0011CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%c]\n\u0001#\u00199qYf$C-\u001a4bk2$H%\r\u001d\u0002!\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIEJ\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0007C\u001cI\u000f\u0005\u0004\u0002*\u0005\u001541\u001d\t+\u0003S\u0019)/a\u0019\u0002\u0000\u0005-\u0015\u0011TA2\u00033\u000b\u0019'a\u0019\u0002\u0000\u0005e\u0015\u0011TA2\u0003G\ny(!$\u0002\u0000\u0005\r\u0014q]A2\u0013\u0011\u00199/a\u000b\u0003\u000fQ+\b\u000f\\32s!I11\u001e<\u0002\u0002\u0003\u0007\u0011q_\u0001\u0004q\u0012\u0002\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001a\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00135\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%k\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIY\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012:\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0003(A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%O\u0001\u001dI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u00191\u0003q!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%cE\nA\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\n$'\u0001\u000f%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%M\u001a\u00029\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132i\u0005aB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE*\u0014\u0001\b\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013GN\u0001\u001dI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u00198\u0003q!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%ca\nA\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\n\u0014(\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0005\u0018A!!1\u001eC\r\u0013\u0011!YB!<\u0003\r=\u0013'.Z2u\u0001"
)
public class SSLOptions implements Logging, Product, Serializable {
   private final Option namespace;
   private final boolean enabled;
   private final Option port;
   private final Option keyStore;
   private final Option keyStorePassword;
   private final Option privateKey;
   private final Option keyPassword;
   private final Option keyStoreType;
   private final boolean needClientAuth;
   private final Option certChain;
   private final Option trustStore;
   private final Option trustStorePassword;
   private final Option trustStoreType;
   private final boolean trustStoreReloadingEnabled;
   private final int trustStoreReloadIntervalMs;
   private final boolean openSslEnabled;
   private final Option protocol;
   private final Set enabledAlgorithms;
   private final Option privateKeyPassword;
   private final Set supportedAlgorithms;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Option $lessinit$greater$default$19() {
      return SSLOptions$.MODULE$.$lessinit$greater$default$19();
   }

   public static Set $lessinit$greater$default$18() {
      return SSLOptions$.MODULE$.$lessinit$greater$default$18();
   }

   public static Option $lessinit$greater$default$17() {
      return SSLOptions$.MODULE$.$lessinit$greater$default$17();
   }

   public static boolean $lessinit$greater$default$16() {
      return SSLOptions$.MODULE$.$lessinit$greater$default$16();
   }

   public static int $lessinit$greater$default$15() {
      return SSLOptions$.MODULE$.$lessinit$greater$default$15();
   }

   public static boolean $lessinit$greater$default$14() {
      return SSLOptions$.MODULE$.$lessinit$greater$default$14();
   }

   public static Option $lessinit$greater$default$13() {
      return SSLOptions$.MODULE$.$lessinit$greater$default$13();
   }

   public static Option $lessinit$greater$default$12() {
      return SSLOptions$.MODULE$.$lessinit$greater$default$12();
   }

   public static Option $lessinit$greater$default$11() {
      return SSLOptions$.MODULE$.$lessinit$greater$default$11();
   }

   public static Option $lessinit$greater$default$10() {
      return SSLOptions$.MODULE$.$lessinit$greater$default$10();
   }

   public static boolean $lessinit$greater$default$9() {
      return SSLOptions$.MODULE$.$lessinit$greater$default$9();
   }

   public static Option $lessinit$greater$default$8() {
      return SSLOptions$.MODULE$.$lessinit$greater$default$8();
   }

   public static Option $lessinit$greater$default$7() {
      return SSLOptions$.MODULE$.$lessinit$greater$default$7();
   }

   public static Option $lessinit$greater$default$6() {
      return SSLOptions$.MODULE$.$lessinit$greater$default$6();
   }

   public static Option $lessinit$greater$default$5() {
      return SSLOptions$.MODULE$.$lessinit$greater$default$5();
   }

   public static Option $lessinit$greater$default$4() {
      return SSLOptions$.MODULE$.$lessinit$greater$default$4();
   }

   public static Option $lessinit$greater$default$3() {
      return SSLOptions$.MODULE$.$lessinit$greater$default$3();
   }

   public static boolean $lessinit$greater$default$2() {
      return SSLOptions$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option $lessinit$greater$default$1() {
      return SSLOptions$.MODULE$.$lessinit$greater$default$1();
   }

   public static Option unapply(final SSLOptions x$0) {
      return SSLOptions$.MODULE$.unapply(x$0);
   }

   public static Option apply$default$19() {
      return SSLOptions$.MODULE$.apply$default$19();
   }

   public static Set apply$default$18() {
      return SSLOptions$.MODULE$.apply$default$18();
   }

   public static Option apply$default$17() {
      return SSLOptions$.MODULE$.apply$default$17();
   }

   public static boolean apply$default$16() {
      return SSLOptions$.MODULE$.apply$default$16();
   }

   public static int apply$default$15() {
      return SSLOptions$.MODULE$.apply$default$15();
   }

   public static boolean apply$default$14() {
      return SSLOptions$.MODULE$.apply$default$14();
   }

   public static Option apply$default$13() {
      return SSLOptions$.MODULE$.apply$default$13();
   }

   public static Option apply$default$12() {
      return SSLOptions$.MODULE$.apply$default$12();
   }

   public static Option apply$default$11() {
      return SSLOptions$.MODULE$.apply$default$11();
   }

   public static Option apply$default$10() {
      return SSLOptions$.MODULE$.apply$default$10();
   }

   public static boolean apply$default$9() {
      return SSLOptions$.MODULE$.apply$default$9();
   }

   public static Option apply$default$8() {
      return SSLOptions$.MODULE$.apply$default$8();
   }

   public static Option apply$default$7() {
      return SSLOptions$.MODULE$.apply$default$7();
   }

   public static Option apply$default$6() {
      return SSLOptions$.MODULE$.apply$default$6();
   }

   public static Option apply$default$5() {
      return SSLOptions$.MODULE$.apply$default$5();
   }

   public static Option apply$default$4() {
      return SSLOptions$.MODULE$.apply$default$4();
   }

   public static Option apply$default$3() {
      return SSLOptions$.MODULE$.apply$default$3();
   }

   public static boolean apply$default$2() {
      return SSLOptions$.MODULE$.apply$default$2();
   }

   public static Option apply$default$1() {
      return SSLOptions$.MODULE$.apply$default$1();
   }

   public static SSLOptions apply(final Option namespace, final boolean enabled, final Option port, final Option keyStore, final Option keyStorePassword, final Option privateKey, final Option keyPassword, final Option keyStoreType, final boolean needClientAuth, final Option certChain, final Option trustStore, final Option trustStorePassword, final Option trustStoreType, final boolean trustStoreReloadingEnabled, final int trustStoreReloadIntervalMs, final boolean openSslEnabled, final Option protocol, final Set enabledAlgorithms, final Option privateKeyPassword) {
      return SSLOptions$.MODULE$.apply(namespace, enabled, port, keyStore, keyStorePassword, privateKey, keyPassword, keyStoreType, needClientAuth, certChain, trustStore, trustStorePassword, trustStoreType, trustStoreReloadingEnabled, trustStoreReloadIntervalMs, openSslEnabled, protocol, enabledAlgorithms, privateKeyPassword);
   }

   public static Seq SPARK_RPC_SSL_PASSWORD_ENVS() {
      return SSLOptions$.MODULE$.SPARK_RPC_SSL_PASSWORD_ENVS();
   }

   public static String ENV_RPC_SSL_TRUST_STORE_PASSWORD() {
      return SSLOptions$.MODULE$.ENV_RPC_SSL_TRUST_STORE_PASSWORD();
   }

   public static String ENV_RPC_SSL_KEY_STORE_PASSWORD() {
      return SSLOptions$.MODULE$.ENV_RPC_SSL_KEY_STORE_PASSWORD();
   }

   public static String ENV_RPC_SSL_PRIVATE_KEY_PASSWORD() {
      return SSLOptions$.MODULE$.ENV_RPC_SSL_PRIVATE_KEY_PASSWORD();
   }

   public static String ENV_RPC_SSL_KEY_PASSWORD() {
      return SSLOptions$.MODULE$.ENV_RPC_SSL_KEY_PASSWORD();
   }

   public static Seq SPARK_RPC_SSL_PASSWORD_FIELDS() {
      return SSLOptions$.MODULE$.SPARK_RPC_SSL_PASSWORD_FIELDS();
   }

   public static String SPARK_RPC_SSL_TRUST_STORE_PASSWORD_CONF() {
      return SSLOptions$.MODULE$.SPARK_RPC_SSL_TRUST_STORE_PASSWORD_CONF();
   }

   public static String SPARK_RPC_SSL_KEY_STORE_PASSWORD_CONF() {
      return SSLOptions$.MODULE$.SPARK_RPC_SSL_KEY_STORE_PASSWORD_CONF();
   }

   public static String SPARK_RPC_SSL_PRIVATE_KEY_PASSWORD_CONF() {
      return SSLOptions$.MODULE$.SPARK_RPC_SSL_PRIVATE_KEY_PASSWORD_CONF();
   }

   public static String SPARK_RPC_SSL_KEY_PASSWORD_CONF() {
      return SSLOptions$.MODULE$.SPARK_RPC_SSL_KEY_PASSWORD_CONF();
   }

   public static Option parse$default$4() {
      return SSLOptions$.MODULE$.parse$default$4();
   }

   public static SSLOptions parse(final SparkConf conf, final Configuration hadoopConf, final String ns, final Option defaults) {
      return SSLOptions$.MODULE$.parse(conf, hadoopConf, ns, defaults);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
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

   public Option namespace() {
      return this.namespace;
   }

   public boolean enabled() {
      return this.enabled;
   }

   public Option port() {
      return this.port;
   }

   public Option keyStore() {
      return this.keyStore;
   }

   public Option keyStorePassword() {
      return this.keyStorePassword;
   }

   public Option privateKey() {
      return this.privateKey;
   }

   public Option keyPassword() {
      return this.keyPassword;
   }

   public Option keyStoreType() {
      return this.keyStoreType;
   }

   public boolean needClientAuth() {
      return this.needClientAuth;
   }

   public Option certChain() {
      return this.certChain;
   }

   public Option trustStore() {
      return this.trustStore;
   }

   public Option trustStorePassword() {
      return this.trustStorePassword;
   }

   public Option trustStoreType() {
      return this.trustStoreType;
   }

   public boolean trustStoreReloadingEnabled() {
      return this.trustStoreReloadingEnabled;
   }

   public int trustStoreReloadIntervalMs() {
      return this.trustStoreReloadIntervalMs;
   }

   public boolean openSslEnabled() {
      return this.openSslEnabled;
   }

   public Option protocol() {
      return this.protocol;
   }

   public Set enabledAlgorithms() {
      return this.enabledAlgorithms;
   }

   public Option privateKeyPassword() {
      return this.privateKeyPassword;
   }

   public Option createJettySslContextFactoryServer() {
      if (this.enabled()) {
         SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
         this.keyStore().foreach((file) -> {
            $anonfun$createJettySslContextFactoryServer$1(sslContextFactory, file);
            return BoxedUnit.UNIT;
         });
         this.keyStorePassword().foreach((x$1) -> {
            $anonfun$createJettySslContextFactoryServer$2(sslContextFactory, x$1);
            return BoxedUnit.UNIT;
         });
         this.keyPassword().foreach((x$1) -> {
            $anonfun$createJettySslContextFactoryServer$3(sslContextFactory, x$1);
            return BoxedUnit.UNIT;
         });
         this.keyStoreType().foreach((x$1) -> {
            $anonfun$createJettySslContextFactoryServer$4(sslContextFactory, x$1);
            return BoxedUnit.UNIT;
         });
         if (this.needClientAuth()) {
            this.trustStore().foreach((file) -> {
               $anonfun$createJettySslContextFactoryServer$5(sslContextFactory, file);
               return BoxedUnit.UNIT;
            });
            this.trustStorePassword().foreach((x$1) -> {
               $anonfun$createJettySslContextFactoryServer$6(sslContextFactory, x$1);
               return BoxedUnit.UNIT;
            });
            this.trustStoreType().foreach((x$1) -> {
               $anonfun$createJettySslContextFactoryServer$7(sslContextFactory, x$1);
               return BoxedUnit.UNIT;
            });
            sslContextFactory.setNeedClientAuth(this.needClientAuth());
         }

         this.protocol().foreach((x$1) -> {
            $anonfun$createJettySslContextFactoryServer$8(sslContextFactory, x$1);
            return BoxedUnit.UNIT;
         });
         if (this.supportedAlgorithms().nonEmpty()) {
            sslContextFactory.setIncludeCipherSuites((String[])this.supportedAlgorithms().toSeq().toArray(.MODULE$.apply(String.class)));
         }

         return new Some(sslContextFactory);
      } else {
         return scala.None..MODULE$;
      }
   }

   private Set supportedAlgorithms() {
      return this.supportedAlgorithms;
   }

   public ConfigProvider createConfigProvider(final SparkConf conf) {
      String nsp = (String)this.namespace().getOrElse(() -> "spark.ssl");
      Map confMap = new HashMap();
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])conf.getAll()), (tuple) -> (String)confMap.put(tuple._1(), tuple._2()));
      confMap.put(nsp + ".enabled", Boolean.toString(this.enabled()));
      confMap.put(nsp + ".trustStoreReloadingEnabled", Boolean.toString(this.trustStoreReloadingEnabled()));
      confMap.put(nsp + ".openSslEnabled", Boolean.toString(this.openSslEnabled()));
      confMap.put(nsp + ".trustStoreReloadIntervalMs", Integer.toString(this.trustStoreReloadIntervalMs()));
      this.keyStore().map((x$1) -> x$1.getAbsolutePath()).foreach((x$2) -> (String)confMap.put(nsp + ".keyStore", x$2));
      this.keyStorePassword().foreach((x$3) -> (String)confMap.put(nsp + ".keyStorePassword", x$3));
      this.privateKey().map((x$4) -> x$4.getAbsolutePath()).foreach((x$5) -> (String)confMap.put(nsp + ".privateKey", x$5));
      this.keyPassword().foreach((x$6) -> (String)confMap.put(nsp + ".keyPassword", x$6));
      this.certChain().map((x$7) -> x$7.getAbsolutePath()).foreach((x$8) -> (String)confMap.put(nsp + ".certChain", x$8));
      this.trustStore().map((x$9) -> x$9.getAbsolutePath()).foreach((x$10) -> (String)confMap.put(nsp + ".trustStore", x$10));
      this.trustStorePassword().foreach((x$11) -> (String)confMap.put(nsp + ".trustStorePassword", x$11));
      this.protocol().foreach((x$12) -> (String)confMap.put(nsp + ".protocol", x$12));
      confMap.put(nsp + ".enabledAlgorithms", this.enabledAlgorithms().mkString(","));
      this.privateKeyPassword().foreach((x$13) -> (String)confMap.put(nsp + ".privateKeyPassword", x$13));
      return new MapConfigProvider(confMap);
   }

   public String toString() {
      boolean var10000 = this.enabled();
      return "SSLOptions{enabled=" + var10000 + ", port=" + this.port() + ", keyStore=" + this.keyStore() + ", keyStorePassword=" + this.keyStorePassword().map((x$14) -> "xxx") + ", privateKey=" + this.privateKey() + ", keyPassword=" + this.keyPassword().map((x$15) -> "xxx") + ", privateKeyPassword=" + this.privateKeyPassword().map((x$16) -> "xxx") + ", keyStoreType=" + this.keyStoreType() + ", needClientAuth=" + this.needClientAuth() + ", certChain=" + this.certChain() + ", trustStore=" + this.trustStore() + ", trustStorePassword=" + this.trustStorePassword().map((x$17) -> "xxx") + ", trustStoreReloadIntervalMs=" + this.trustStoreReloadIntervalMs() + ", trustStoreReloadingEnabled=" + this.trustStoreReloadingEnabled() + ", openSSLEnabled=" + this.openSslEnabled() + ", protocol=" + this.protocol() + ", enabledAlgorithms=" + this.enabledAlgorithms() + "}";
   }

   public SSLOptions copy(final Option namespace, final boolean enabled, final Option port, final Option keyStore, final Option keyStorePassword, final Option privateKey, final Option keyPassword, final Option keyStoreType, final boolean needClientAuth, final Option certChain, final Option trustStore, final Option trustStorePassword, final Option trustStoreType, final boolean trustStoreReloadingEnabled, final int trustStoreReloadIntervalMs, final boolean openSslEnabled, final Option protocol, final Set enabledAlgorithms, final Option privateKeyPassword) {
      return new SSLOptions(namespace, enabled, port, keyStore, keyStorePassword, privateKey, keyPassword, keyStoreType, needClientAuth, certChain, trustStore, trustStorePassword, trustStoreType, trustStoreReloadingEnabled, trustStoreReloadIntervalMs, openSslEnabled, protocol, enabledAlgorithms, privateKeyPassword);
   }

   public Option copy$default$1() {
      return this.namespace();
   }

   public Option copy$default$10() {
      return this.certChain();
   }

   public Option copy$default$11() {
      return this.trustStore();
   }

   public Option copy$default$12() {
      return this.trustStorePassword();
   }

   public Option copy$default$13() {
      return this.trustStoreType();
   }

   public boolean copy$default$14() {
      return this.trustStoreReloadingEnabled();
   }

   public int copy$default$15() {
      return this.trustStoreReloadIntervalMs();
   }

   public boolean copy$default$16() {
      return this.openSslEnabled();
   }

   public Option copy$default$17() {
      return this.protocol();
   }

   public Set copy$default$18() {
      return this.enabledAlgorithms();
   }

   public Option copy$default$19() {
      return this.privateKeyPassword();
   }

   public boolean copy$default$2() {
      return this.enabled();
   }

   public Option copy$default$3() {
      return this.port();
   }

   public Option copy$default$4() {
      return this.keyStore();
   }

   public Option copy$default$5() {
      return this.keyStorePassword();
   }

   public Option copy$default$6() {
      return this.privateKey();
   }

   public Option copy$default$7() {
      return this.keyPassword();
   }

   public Option copy$default$8() {
      return this.keyStoreType();
   }

   public boolean copy$default$9() {
      return this.needClientAuth();
   }

   public String productPrefix() {
      return "SSLOptions";
   }

   public int productArity() {
      return 19;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.namespace();
         }
         case 1 -> {
            return BoxesRunTime.boxToBoolean(this.enabled());
         }
         case 2 -> {
            return this.port();
         }
         case 3 -> {
            return this.keyStore();
         }
         case 4 -> {
            return this.keyStorePassword();
         }
         case 5 -> {
            return this.privateKey();
         }
         case 6 -> {
            return this.keyPassword();
         }
         case 7 -> {
            return this.keyStoreType();
         }
         case 8 -> {
            return BoxesRunTime.boxToBoolean(this.needClientAuth());
         }
         case 9 -> {
            return this.certChain();
         }
         case 10 -> {
            return this.trustStore();
         }
         case 11 -> {
            return this.trustStorePassword();
         }
         case 12 -> {
            return this.trustStoreType();
         }
         case 13 -> {
            return BoxesRunTime.boxToBoolean(this.trustStoreReloadingEnabled());
         }
         case 14 -> {
            return BoxesRunTime.boxToInteger(this.trustStoreReloadIntervalMs());
         }
         case 15 -> {
            return BoxesRunTime.boxToBoolean(this.openSslEnabled());
         }
         case 16 -> {
            return this.protocol();
         }
         case 17 -> {
            return this.enabledAlgorithms();
         }
         case 18 -> {
            return this.privateKeyPassword();
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
      return x$1 instanceof SSLOptions;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "namespace";
         }
         case 1 -> {
            return "enabled";
         }
         case 2 -> {
            return "port";
         }
         case 3 -> {
            return "keyStore";
         }
         case 4 -> {
            return "keyStorePassword";
         }
         case 5 -> {
            return "privateKey";
         }
         case 6 -> {
            return "keyPassword";
         }
         case 7 -> {
            return "keyStoreType";
         }
         case 8 -> {
            return "needClientAuth";
         }
         case 9 -> {
            return "certChain";
         }
         case 10 -> {
            return "trustStore";
         }
         case 11 -> {
            return "trustStorePassword";
         }
         case 12 -> {
            return "trustStoreType";
         }
         case 13 -> {
            return "trustStoreReloadingEnabled";
         }
         case 14 -> {
            return "trustStoreReloadIntervalMs";
         }
         case 15 -> {
            return "openSslEnabled";
         }
         case 16 -> {
            return "protocol";
         }
         case 17 -> {
            return "enabledAlgorithms";
         }
         case 18 -> {
            return "privateKeyPassword";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.namespace()));
      var1 = Statics.mix(var1, this.enabled() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.anyHash(this.port()));
      var1 = Statics.mix(var1, Statics.anyHash(this.keyStore()));
      var1 = Statics.mix(var1, Statics.anyHash(this.keyStorePassword()));
      var1 = Statics.mix(var1, Statics.anyHash(this.privateKey()));
      var1 = Statics.mix(var1, Statics.anyHash(this.keyPassword()));
      var1 = Statics.mix(var1, Statics.anyHash(this.keyStoreType()));
      var1 = Statics.mix(var1, this.needClientAuth() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.anyHash(this.certChain()));
      var1 = Statics.mix(var1, Statics.anyHash(this.trustStore()));
      var1 = Statics.mix(var1, Statics.anyHash(this.trustStorePassword()));
      var1 = Statics.mix(var1, Statics.anyHash(this.trustStoreType()));
      var1 = Statics.mix(var1, this.trustStoreReloadingEnabled() ? 1231 : 1237);
      var1 = Statics.mix(var1, this.trustStoreReloadIntervalMs());
      var1 = Statics.mix(var1, this.openSslEnabled() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.anyHash(this.protocol()));
      var1 = Statics.mix(var1, Statics.anyHash(this.enabledAlgorithms()));
      var1 = Statics.mix(var1, Statics.anyHash(this.privateKeyPassword()));
      return Statics.finalizeHash(var1, 19);
   }

   public boolean equals(final Object x$1) {
      boolean var32;
      if (this != x$1) {
         label171: {
            if (x$1 instanceof SSLOptions) {
               SSLOptions var4 = (SSLOptions)x$1;
               if (this.enabled() == var4.enabled() && this.needClientAuth() == var4.needClientAuth() && this.trustStoreReloadingEnabled() == var4.trustStoreReloadingEnabled() && this.trustStoreReloadIntervalMs() == var4.trustStoreReloadIntervalMs() && this.openSslEnabled() == var4.openSslEnabled()) {
                  label164: {
                     Option var10000 = this.namespace();
                     Option var5 = var4.namespace();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label164;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label164;
                     }

                     var10000 = this.port();
                     Option var6 = var4.port();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label164;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label164;
                     }

                     var10000 = this.keyStore();
                     Option var7 = var4.keyStore();
                     if (var10000 == null) {
                        if (var7 != null) {
                           break label164;
                        }
                     } else if (!var10000.equals(var7)) {
                        break label164;
                     }

                     var10000 = this.keyStorePassword();
                     Option var8 = var4.keyStorePassword();
                     if (var10000 == null) {
                        if (var8 != null) {
                           break label164;
                        }
                     } else if (!var10000.equals(var8)) {
                        break label164;
                     }

                     var10000 = this.privateKey();
                     Option var9 = var4.privateKey();
                     if (var10000 == null) {
                        if (var9 != null) {
                           break label164;
                        }
                     } else if (!var10000.equals(var9)) {
                        break label164;
                     }

                     var10000 = this.keyPassword();
                     Option var10 = var4.keyPassword();
                     if (var10000 == null) {
                        if (var10 != null) {
                           break label164;
                        }
                     } else if (!var10000.equals(var10)) {
                        break label164;
                     }

                     var10000 = this.keyStoreType();
                     Option var11 = var4.keyStoreType();
                     if (var10000 == null) {
                        if (var11 != null) {
                           break label164;
                        }
                     } else if (!var10000.equals(var11)) {
                        break label164;
                     }

                     var10000 = this.certChain();
                     Option var12 = var4.certChain();
                     if (var10000 == null) {
                        if (var12 != null) {
                           break label164;
                        }
                     } else if (!var10000.equals(var12)) {
                        break label164;
                     }

                     var10000 = this.trustStore();
                     Option var13 = var4.trustStore();
                     if (var10000 == null) {
                        if (var13 != null) {
                           break label164;
                        }
                     } else if (!var10000.equals(var13)) {
                        break label164;
                     }

                     var10000 = this.trustStorePassword();
                     Option var14 = var4.trustStorePassword();
                     if (var10000 == null) {
                        if (var14 != null) {
                           break label164;
                        }
                     } else if (!var10000.equals(var14)) {
                        break label164;
                     }

                     var10000 = this.trustStoreType();
                     Option var15 = var4.trustStoreType();
                     if (var10000 == null) {
                        if (var15 != null) {
                           break label164;
                        }
                     } else if (!var10000.equals(var15)) {
                        break label164;
                     }

                     var10000 = this.protocol();
                     Option var16 = var4.protocol();
                     if (var10000 == null) {
                        if (var16 != null) {
                           break label164;
                        }
                     } else if (!var10000.equals(var16)) {
                        break label164;
                     }

                     Set var30 = this.enabledAlgorithms();
                     Set var17 = var4.enabledAlgorithms();
                     if (var30 == null) {
                        if (var17 != null) {
                           break label164;
                        }
                     } else if (!var30.equals(var17)) {
                        break label164;
                     }

                     Option var31 = this.privateKeyPassword();
                     Option var18 = var4.privateKeyPassword();
                     if (var31 == null) {
                        if (var18 != null) {
                           break label164;
                        }
                     } else if (!var31.equals(var18)) {
                        break label164;
                     }

                     if (var4.canEqual(this)) {
                        break label171;
                     }
                  }
               }
            }

            var32 = false;
            return var32;
         }
      }

      var32 = true;
      return var32;
   }

   // $FF: synthetic method
   public static final void $anonfun$createJettySslContextFactoryServer$1(final SslContextFactory.Server sslContextFactory$1, final File file) {
      sslContextFactory$1.setKeyStorePath(file.getAbsolutePath());
   }

   // $FF: synthetic method
   public static final void $anonfun$createJettySslContextFactoryServer$2(final SslContextFactory.Server sslContextFactory$1, final String x$1) {
      sslContextFactory$1.setKeyStorePassword(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$createJettySslContextFactoryServer$3(final SslContextFactory.Server sslContextFactory$1, final String x$1) {
      sslContextFactory$1.setKeyManagerPassword(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$createJettySslContextFactoryServer$4(final SslContextFactory.Server sslContextFactory$1, final String x$1) {
      sslContextFactory$1.setKeyStoreType(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$createJettySslContextFactoryServer$5(final SslContextFactory.Server sslContextFactory$1, final File file) {
      sslContextFactory$1.setTrustStorePath(file.getAbsolutePath());
   }

   // $FF: synthetic method
   public static final void $anonfun$createJettySslContextFactoryServer$6(final SslContextFactory.Server sslContextFactory$1, final String x$1) {
      sslContextFactory$1.setTrustStorePassword(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$createJettySslContextFactoryServer$7(final SslContextFactory.Server sslContextFactory$1, final String x$1) {
      sslContextFactory$1.setTrustStoreType(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$createJettySslContextFactoryServer$8(final SslContextFactory.Server sslContextFactory$1, final String x$1) {
      sslContextFactory$1.setProtocol(x$1);
   }

   // $FF: synthetic method
   private final void liftedTree1$1(final ObjectRef context$1) {
      try {
         context$1.elem = SSLContext.getInstance((String)this.protocol().get());
         ((SSLContext)context$1.elem).init((KeyManager[])null, (TrustManager[])null, (SecureRandom)null);
      } catch (NoSuchAlgorithmException var3) {
         this.logDebug((Function0)(() -> "No support for requested SSL protocol " + this.protocol().get()));
         context$1.elem = SSLContext.getDefault();
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$supportedAlgorithms$3(final SSLOptions $this, final String cipher) {
      $this.logDebug((Function0)(() -> "Discarding unsupported cipher " + cipher));
   }

   public SSLOptions(final Option namespace, final boolean enabled, final Option port, final Option keyStore, final Option keyStorePassword, final Option privateKey, final Option keyPassword, final Option keyStoreType, final boolean needClientAuth, final Option certChain, final Option trustStore, final Option trustStorePassword, final Option trustStoreType, final boolean trustStoreReloadingEnabled, final int trustStoreReloadIntervalMs, final boolean openSslEnabled, final Option protocol, final Set enabledAlgorithms, final Option privateKeyPassword) {
      this.namespace = namespace;
      this.enabled = enabled;
      this.port = port;
      this.keyStore = keyStore;
      this.keyStorePassword = keyStorePassword;
      this.privateKey = privateKey;
      this.keyPassword = keyPassword;
      this.keyStoreType = keyStoreType;
      this.needClientAuth = needClientAuth;
      this.certChain = certChain;
      this.trustStore = trustStore;
      this.trustStorePassword = trustStorePassword;
      this.trustStoreType = trustStoreType;
      this.trustStoreReloadingEnabled = trustStoreReloadingEnabled;
      this.trustStoreReloadIntervalMs = trustStoreReloadIntervalMs;
      this.openSslEnabled = openSslEnabled;
      this.protocol = protocol;
      this.enabledAlgorithms = enabledAlgorithms;
      this.privateKeyPassword = privateKeyPassword;
      Logging.$init$(this);
      Product.$init$(this);
      Set var10001;
      if (enabledAlgorithms.isEmpty()) {
         var10001 = scala.Predef..MODULE$.Set().empty();
      } else {
         ObjectRef context = ObjectRef.create((Object)null);
         if (protocol.isEmpty()) {
            this.logDebug((Function0)(() -> "No SSL protocol specified"));
            context.elem = SSLContext.getDefault();
         } else {
            this.liftedTree1$1(context);
         }

         Set providerAlgorithms = scala.Predef..MODULE$.wrapRefArray((Object[])((SSLContext)context.elem).getServerSocketFactory().getSupportedCipherSuites()).toSet();
         enabledAlgorithms.$amp$tilde(providerAlgorithms).foreach((cipher) -> {
            $anonfun$supportedAlgorithms$3(this, cipher);
            return BoxedUnit.UNIT;
         });
         Set supported = (Set)enabledAlgorithms.$amp(providerAlgorithms);
         scala.Predef..MODULE$.require(supported.nonEmpty() || scala.sys.package..MODULE$.env().contains("SPARK_TESTING"), () -> {
            Set var10000 = this.enabledAlgorithms();
            return "SSLContext does not support any of the enabled algorithms: " + var10000.mkString(",");
         });
         var10001 = supported;
      }

      this.supportedAlgorithms = var10001;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
