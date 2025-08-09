package org.apache.spark.errors;

import java.io.File;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkException;
import org.apache.spark.shuffle.ShuffleManager;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.BlockManagerId;
import org.apache.spark.storage.RDDBlockId;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\rMtAB'O\u0011\u0003\u0001fK\u0002\u0004Y\u001d\"\u0005\u0001+\u0017\u0005\u0006A\u0006!\tA\u0019\u0005\u0006G\u0006!\t\u0001\u001a\u0005\u0006w\u0006!\t\u0001 \u0005\n\u0003?\t\u0011\u0013!C\u0001\u0003CAq!a\u000e\u0002\t\u0003\tI\u0004C\u0004\u0002D\u0005!\t!!\u0012\t\u000f\u0005m\u0013\u0001\"\u0001\u0002^!9\u00111M\u0001\u0005\u0002\u0005\u0015\u0004bBA4\u0003\u0011\u0005\u0011Q\r\u0005\b\u0003S\nA\u0011AA6\u0011\u001d\t\t(\u0001C\u0001\u0003gBq!a \u0002\t\u0003\t)\u0007C\u0004\u0002\u0002\u0006!\t!!\u001a\t\u000f\u0005\r\u0015\u0001\"\u0001\u0002f!9\u0011QQ\u0001\u0005\u0002\u0005\u0015\u0004bBAD\u0003\u0011\u0005\u0011Q\r\u0005\b\u0003\u0013\u000bA\u0011AA3\u0011\u001d\tY)\u0001C\u0001\u0003KBq!!$\u0002\t\u0003\t)\u0007C\u0004\u0002\u0010\u0006!\t!!\u001a\t\u000f\u0005E\u0015\u0001\"\u0001\u0002f!9\u00111S\u0001\u0005\u0002\u0005U\u0005bBAU\u0003\u0011\u0005\u00111\u0016\u0005\b\u0003c\u000bA\u0011AAZ\u0011\u001d\t)-\u0001C\u0001\u0003\u000fDq!a4\u0002\t\u0003\t)\u0007C\u0004\u0002R\u0006!\t!a5\t\u000f\u0005}\u0017\u0001\"\u0001\u0002b\"9\u0011Q]\u0001\u0005\u0002\u0005\u001d\bbBAv\u0003\u0011\u0005\u0011Q\u001e\u0005\b\u0005\u0003\tA\u0011\u0001B\u0002\u0011\u001d\u0011Y!\u0001C\u0001\u0003KBqA!\u0004\u0002\t\u0003\t)\u0007C\u0004\u0003\u0010\u0005!\tA!\u0005\t\u000f\tm\u0011\u0001\"\u0001\u0002f!9!QD\u0001\u0005\u0002\t}\u0001b\u0002B\u0015\u0003\u0011\u0005\u0011Q\r\u0005\b\u0005W\tA\u0011\u0001B\u0017\u0011\u001d\u0011\u0019$\u0001C\u0001\u0003KBqA!\u000e\u0002\t\u0003\u00119\u0004C\u0004\u0003B\u0005!\tAa\u0011\t\u000f\t%\u0013\u0001\"\u0001\u0003L!9!\u0011K\u0001\u0005\u0002\tM\u0003b\u0002B,\u0003\u0011\u0005!\u0011\f\u0005\b\u0005?\nA\u0011\u0001B1\u0011\u001d\u0011I'\u0001C\u0001\u0005WBqAa\u001c\u0002\t\u0003\u0011\t\bC\u0004\u0003v\u0005!\tAa\u001e\t\u000f\tm\u0014\u0001\"\u0001\u0003~!9!\u0011Q\u0001\u0005\u0002\t\r\u0005b\u0002BD\u0003\u0011\u0005!\u0011\u0012\u0005\b\u0005;\u000bA\u0011\u0001BP\u0011\u001d\u0011i+\u0001C\u0001\u0005_CqAa-\u0002\t\u0003\u0011)\fC\u0004\u0003:\u0006!\tAa/\t\u000f\t}\u0016\u0001\"\u0001\u0002f!9!\u0011Y\u0001\u0005\u0002\t\r\u0007b\u0002Bd\u0003\u0011\u0005\u0011Q\r\u0005\b\u0005\u0013\fA\u0011\u0001Bf\u0011\u001d\u0011\u0019.\u0001C\u0001\u0003KBqA!6\u0002\t\u0003\t)\u0007C\u0004\u0003X\u0006!\tA!7\t\u0013\tU\u0018!%A\u0005\u0002\t]\bb\u0002B~\u0003\u0011\u0005!Q \u0005\b\u0007\u0007\tA\u0011AB\u0003\u0011\u001d\u0019Y!\u0001C\u0001\u0007\u001bAqaa\u0005\u0002\t\u0003\u0019)\u0002C\u0004\u0004&\u0005!\taa\n\t\u000f\ru\u0012\u0001\"\u0001\u0004@!911I\u0001\u0005\u0002\r\u0015\u0003bBB%\u0003\u0011\u000511\n\u0005\b\u0007#\nA\u0011AB*\u0011\u001d\u0019i&\u0001C\u0005\u0007?Bqa!\u001a\u0002\t\u0003\u00199\u0007C\u0004\u0004n\u0005!\taa\u001c\u0002\u001fM\u0003\u0018M]6D_J,WI\u001d:peNT!a\u0014)\u0002\r\u0015\u0014(o\u001c:t\u0015\t\t&+A\u0003ta\u0006\u00148N\u0003\u0002T)\u00061\u0011\r]1dQ\u0016T\u0011!V\u0001\u0004_J<\u0007CA,\u0002\u001b\u0005q%aD*qCJ\\7i\u001c:f\u000bJ\u0014xN]:\u0014\u0005\u0005Q\u0006CA._\u001b\u0005a&\"A/\u0002\u000bM\u001c\u0017\r\\1\n\u0005}c&AB!osJ+g-\u0001\u0004=S:LGOP\u0002\u0001)\u00051\u0016!G;oKb\u0004Xm\u0019;fIBKHGS*feZ,'/\u0012:s_J$\"!Z9\u0011\u0005\u0019tgBA4m\u001d\tA7.D\u0001j\u0015\tQ\u0017-\u0001\u0004=e>|GOP\u0005\u0002;&\u0011Q\u000eX\u0001\ba\u0006\u001c7.Y4f\u0013\ty\u0007OA\u0005UQJ|w/\u00192mK*\u0011Q\u000e\u0018\u0005\u0006e\u000e\u0001\ra]\u0001\u0006_RDWM\u001d\t\u0003ifl\u0011!\u001e\u0006\u0003m^\fA\u0001\\1oO*\t\u00010\u0001\u0003kCZ\f\u0017B\u0001>v\u0005\u0019y%M[3di\u0006!Sm\u001c4Fq\u000e,\u0007\u000f^5p]^C\u0017\u000e\\3SK\u0006$\u0007k\u001c:u\u001dVl'-\u001a:FeJ|'\u000f\u0006\u0003f{\u0006=\u0001\"\u0002@\u0005\u0001\u0004y\u0018\u0001\u00043bK6|g.T8ek2,\u0007\u0003BA\u0001\u0003\u0013qA!a\u0001\u0002\u0006A\u0011\u0001\u000eX\u0005\u0004\u0003\u000fa\u0016A\u0002)sK\u0012,g-\u0003\u0003\u0002\f\u00055!AB*ue&twMC\u0002\u0002\bqC\u0011\"!\u0005\u0005!\u0003\u0005\r!a\u0005\u0002\u001f\u0011\fW-\\8o\u000bbLGOV1mk\u0016\u0004RaWA\u000b\u00033I1!a\u0006]\u0005\u0019y\u0005\u000f^5p]B\u00191,a\u0007\n\u0007\u0005uALA\u0002J]R\fa&Z8g\u000bb\u001cW\r\u001d;j_:<\u0006.\u001b7f%\u0016\fG\rU8si:+XNY3s\u000bJ\u0014xN\u001d\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u00111\u0005\u0016\u0005\u0003'\t)c\u000b\u0002\u0002(A!\u0011\u0011FA\u001a\u001b\t\tYC\u0003\u0003\u0002.\u0005=\u0012!C;oG\",7m[3e\u0015\r\t\t\u0004X\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u001b\u0003W\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003a)hn];qa>\u0014H/\u001a3ECR\fG+\u001f9f\u000bJ\u0014xN\u001d\u000b\u0004K\u0006m\u0002B\u0002:\u0007\u0001\u0004\ti\u0004E\u0002\\\u0003\u007fI1!!\u0011]\u0005\r\te._\u0001\u0016e\u0012$'\t\\8dW:{GOR8v]\u0012,%O]8s)\u0015)\u0017qIA,\u0011\u001d\tIe\u0002a\u0001\u0003\u0017\nqA\u00197pG.LE\r\u0005\u0003\u0002N\u0005MSBAA(\u0015\r\t\t\u0006U\u0001\bgR|'/Y4f\u0013\u0011\t)&a\u0014\u0003\u000f\tcwnY6JI\"9\u0011\u0011L\u0004A\u0002\u0005e\u0011AA5e\u0003e\u0011Gn\\2l\u0011\u00064XMQ3f]J+Wn\u001c<fI\u0016\u0013(o\u001c:\u0015\u0007\u0015\fy\u0006\u0003\u0004\u0002b!\u0001\ra`\u0001\u0007gR\u0014\u0018N\\4\u0002c!L7\u000f^8he\u0006lwJ\\#naRL(\u000b\u0012#Pe\u000e{g\u000e^1j]&tw-\u00138gS:LG/_(s\u001d\u0006tUI\u001d:peR\tQ-A\u0007f[B$\u0018P\u0015#E\u000bJ\u0014xN]\u0001\u0016a\u0006$\bNT8u'V\u0004\bo\u001c:uK\u0012,%O]8s)\r)\u0017Q\u000e\u0005\u0007\u0003_Z\u0001\u0019A@\u0002\tA\fG\u000f[\u0001\"G\",7m\u001b9pS:$(\u000b\u0012#CY>\u001c7.\u00133O_R4u.\u001e8e\u000bJ\u0014xN\u001d\u000b\u0004K\u0006U\u0004bBA<\u0019\u0001\u0007\u0011\u0011P\u0001\u000be\u0012$'\t\\8dW&#\u0007\u0003BA'\u0003wJA!! \u0002P\tQ!\u000b\u0012#CY>\u001c7.\u00133\u0002!\u0015tGm\u00144TiJ,\u0017-\\#se>\u0014\u0018AK2b]:|G/V:f\u001b\u0006\u00048+\u001b3f\u0007>l'-\u001b8j]\u001e<\u0016\u000e\u001e5BeJ\f\u0017pS3z\u000bJ\u0014xN]\u0001,Q\u0006\u001c\b\u000eU1si&$\u0018n\u001c8fe\u000e\u000bgN\\8u!\u0006\u0014H/\u001b;j_:\f%O]1z\u0017\u0016LXI\u001d:pe\u0006Q#/\u001a3vG\u0016\u0014\u0015pS3z\u0019>\u001c\u0017\r\u001c7z\u001d>$8+\u001e9q_J$\u0018I\u001d:bs.+\u0017p]#se>\u0014\u0018!\u0007:eI2\u000b7m[:Ta\u0006\u00148nQ8oi\u0016DH/\u0012:s_J\fQdY1o]>$8\t[1oO\u0016\u001cFo\u001c:bO\u0016dUM^3m\u000bJ\u0014xN]\u0001)G\u0006twJ\u001c7z5&\u0004(\u000b\u0012#t/&$\bnU1nKB\u000b'\u000f^5uS>t7+\u001b>f\u000bJ\u0014xN]\u0001\u0015K6\u0004H/_\"pY2,7\r^5p]\u0016\u0013(o\u001c:\u0002O\r|WO\u001c;CsZ\u000bG.^3BaB\u0014x\u000e\u001f(piN+\b\u000f]8si\u0006\u0013(/Y=t\u000bJ\u0014xN]\u00014G\",7m\u001b9pS:$H)\u001b:fGR|'/\u001f%bg:{GOQ3f]N+G/\u00138Ta\u0006\u00148nQ8oi\u0016DH/\u0012:s_J\f!$\u001b8wC2LGm\u00115fG.\u0004x.\u001b8u\r&dW-\u0012:s_J$2!ZAL\u0011\u001d\tyg\u0006a\u0001\u00033\u0003B!a'\u0002&6\u0011\u0011Q\u0014\u0006\u0005\u0003?\u000b\t+\u0001\u0002gg*\u0019\u00111\u0015*\u0002\r!\fGm\\8q\u0013\u0011\t9+!(\u0003\tA\u000bG\u000f[\u0001 M\u0006LG\u000eV8De\u0016\fG/Z\"iK\u000e\\\u0007o\\5oiB\u000bG\u000f[#se>\u0014HcA3\u0002.\"9\u0011q\u0016\rA\u0002\u0005e\u0015!E2iK\u000e\\\u0007o\\5oi\u0012K'\u000fU1uQ\u0006y4\r[3dWB|\u0017N\u001c;S\t\u0012C\u0015m\u001d#jM\u001a,'/\u001a8u\u001dVl'-\u001a:PMB\u000b'\u000f^5uS>t7O\u0012:p[>\u0013\u0018nZ5oC2\u0014F\tR#se>\u0014H#C3\u00026\u0006e\u0016QXAa\u0011\u001d\t9,\u0007a\u0001\u00033\tQb\u001c:jO&t\u0017\r\u001c*E\t&#\u0007bBA^3\u0001\u0007\u0011\u0011D\u0001\u0012_JLw-\u001b8bYJ#E\tT3oORD\u0007bBA`3\u0001\u0007\u0011\u0011D\u0001\t]\u0016<(\u000b\u0012#JI\"9\u00111Y\rA\u0002\u0005e\u0011\u0001\u00048foJ#E\tT3oORD\u0017aG2iK\u000e\\\u0007o\\5oi\u001a\u000b\u0017\u000e\\3e)>\u001c\u0016M^3FeJ|'\u000fF\u0003f\u0003\u0013\fi\rC\u0004\u0002Lj\u0001\r!!\u0007\u0002\tQ\f7o\u001b\u0005\b\u0003_R\u0002\u0019AAM\u0003uiWo\u001d;Ta\u0016\u001c\u0017NZ=DQ\u0016\u001c7\u000e]8j]R$\u0015N]#se>\u0014\u0018AL1tWN#\u0018M\u001c3bY>tWmU2iK\u0012,H.\u001a:U_NCW\u000f\u001e#po:,\u00050Z2vi>\u00148/\u0012:s_J$2!ZAk\u0011\u001d\t9\u000e\ba\u0001\u00033\f\u0011!\u001a\t\u0004M\u0006m\u0017bAAoa\nIQ\t_2faRLwN\\\u0001+gR|\u0007o\u0015;b]\u0012\fGn\u001c8f'\u000eDW\rZ;mKJ$%/\u001b<fe\u0016sG\r]8j]R,%O]8s)\r)\u00171\u001d\u0005\b\u0003/l\u0002\u0019AAm\u0003Mqw.\u0012=fGV$xN]%eY\u0016,%O]8s)\r)\u0017\u0011\u001e\u0005\u0007\u00033r\u0002\u0019A@\u0002#M\u0004\u0018M]6K_\n\u001c\u0015M\\2fY2,G\r\u0006\u0005\u0002p\u0006]\u00181`A\u0000!\u0011\t\t0a=\u000e\u0003AK1!!>Q\u00059\u0019\u0006/\u0019:l\u000bb\u001cW\r\u001d;j_:Dq!!? \u0001\u0004\tI\"A\u0003k_\nLE\r\u0003\u0004\u0002~~\u0001\ra`\u0001\u0007e\u0016\f7o\u001c8\t\u000f\u0005]w\u00041\u0001\u0002Z\u000613\u000f]1sW*{'mQ1oG\u0016dG.\u001a3BgB\u000b'\u000f^(g\u0015>\u0014wI]8va\u0016\u0013(o\u001c:\u0015\r\u0005=(Q\u0001B\u0004\u0011\u001d\tI\u0010\ta\u0001\u00033AaA!\u0003!\u0001\u0004y\u0018A\u00036pE\u001e\u0013x.\u001e9JI\u0006!#-\u0019:sS\u0016\u00148\u000b^1hK^KG\u000f\u001b*E\t\u000eC\u0017-\u001b8QCR$XM\u001d8FeJ|'/\u0001\u0014cCJ\u0014\u0018.\u001a:Ti\u0006<WmV5uQ\u0012Kh.Y7jG\u0006cGn\\2bi&|g.\u0012:s_J\f!G\\;n!\u0006\u0014H/\u001b;j_:\u001cxI]3bi\u0016\u0014H\u000b[1o\u001b\u0006Dh*^7D_:\u001cWO\u001d:f]R$\u0016m]6t\u000bJ\u0014xN\u001d\u000b\u0006K\nM!q\u0003\u0005\b\u0005+\u0019\u0003\u0019AA\r\u00035qW/\u001c)beRLG/[8og\"9!\u0011D\u0012A\u0002\u0005e\u0011!F7bq:+XnQ8oGV\u0014(/\u001a8u)\u0006\u001c8n]\u0001/G\u0006tgn\u001c;Sk:\u001cVOY7ji6\u000b\u0007o\u0015;bO\u0016|eNW3s_B\u000b'\u000f^5uS>t'\u000b\u0012#FeJ|'/A\u0011bG\u000e,7o\u001d(p]\u0016C\u0018n\u001d;f]R\f5mY;nk2\fGo\u001c:FeJ|'\u000fF\u0002f\u0005CAq!!\u0017&\u0001\u0004\u0011\u0019\u0003E\u0002\\\u0005KI1Aa\n]\u0005\u0011auN\\4\u0002kM,g\u000e\u001a*fgV\u0014W.\u001b;uK\u0012$\u0016m]6Ti\u0006$Xo\u001d$peNCWO\u001a4mK6\u000b\u0007o\u0015;bO\u0016\u001cxJ\u001c7z\u000bJ\u0014xN]\u0001$]>tW)\u001c9us\u00163XM\u001c;Rk\u0016,X-\u00114uKJ$\u0016.\\3pkR,%O]8s)\r)'q\u0006\u0005\b\u0005c9\u0003\u0019\u0001B\u0012\u00035!\u0018.\\3pkRl\u0015\u000e\u001c7jg\u0006\u0019C-\u001e:bi&|gnQ1mY\u0016$wJ\\+oM&t\u0017n\u001d5fIR\u000b7o[#se>\u0014\u0018AJ;oe\u0016\u001cwn\u001a8ju\u0016$7k\u00195fIVdWM]'pI\u0016\u0004&o\u001c9feRLXI\u001d:peR)QM!\u000f\u0003>!1!1H\u0015A\u0002}\fQc]2iK\u0012,H.\u001a:N_\u0012,\u0007K]8qKJ$\u0018\u0010\u0003\u0004\u0003@%\u0002\ra`\u0001\u0013g\u000eDW\rZ;mS:<Wj\u001c3f\u0007>tg-\u0001\u0006ta\u0006\u00148.\u0012:s_J$2!\u001aB#\u0011\u0019\u00119E\u000ba\u0001\u007f\u0006AQM\u001d:pe6\u001bx-A\u000bdYV\u001cH/\u001a:TG\",G-\u001e7fe\u0016\u0013(o\u001c:\u0015\u0007\u0015\u0014i\u0005\u0003\u0004\u0003P-\u0002\ra`\u0001\b[\u0016\u001c8/Y4f\u0003a1\u0017-\u001b7U_N+'/[1mSj,G+Y:l\u000bJ\u0014xN\u001d\u000b\u0004K\nU\u0003BBAlY\u0001\u0007Q-\u0001\rv]J,7m\\4oSj,GM\u00117pG.LE-\u0012:s_J$2!\u001aB.\u0011\u0019\u0011i&\fa\u0001\u007f\u0006!a.Y7f\u0003i!\u0018m]6ICNtu\u000e\u001e'pG.,GM\u00117pG.,%O]8s)\u0015)'1\rB4\u0011\u001d\u0011)G\fa\u0001\u0005G\tAcY;se\u0016tG\u000fV1tW\u0006#H/Z7qi&#\u0007bBA%]\u0001\u0007\u00111J\u0001\u0017E2|7m\u001b#pKNtu\u000e^#ySN$XI\u001d:peR\u0019QM!\u001c\t\u000f\u0005%s\u00061\u0001\u0002L\u0005a3-\u00198o_R\u001c\u0016M^3CY>\u001c7n\u00148EK\u000e|W.\\5tg&|g.\u001a3Fq\u0016\u001cW\u000f^8s\u000bJ\u0014xN\u001d\u000b\u0004K\nM\u0004bBA%a\u0001\u0007\u00111J\u0001#o\u0006LG/\u001b8h\r>\u0014(+\u001a9mS\u000e\fG/[8o)>4\u0015N\\5tQ\u0016\u0013(o\u001c:\u0015\u0007\u0015\u0014I\b\u0003\u0004\u0002XF\u0002\r!Z\u0001/k:\f'\r\\3U_J+w-[:uKJ<\u0016\u000e\u001e5FqR,'O\\1m'\",hM\u001a7f'\u0016\u0014h/\u001a:FeJ|'\u000fF\u0002f\u0005\u007fBa!a63\u0001\u0004)\u0017AI<bSRLgn\u001a$pe\u0006\u001b\u0018P\\2SKJ,w-[:ue\u0006$\u0018n\u001c8FeJ|'\u000fF\u0002f\u0005\u000bCa!a64\u0001\u0004)\u0017AM;oKb\u0004Xm\u0019;fINCWO\u001a4mK\ncwnY6XSRDWK\\:vaB|'\u000f^3e%\u0016\u001cx\u000e\u001c<fe\u0016\u0013(o\u001c:\u0015\u000b\u0015\u0014YIa'\t\u000f\t5E\u00071\u0001\u0003\u0010\u0006q1\u000f[;gM2,W*\u00198bO\u0016\u0014\b\u0003\u0002BI\u0005/k!Aa%\u000b\u0007\tU\u0005+A\u0004tQV4g\r\\3\n\t\te%1\u0013\u0002\u000f'\",hM\u001a7f\u001b\u0006t\u0017mZ3s\u0011\u001d\tI\u0005\u000ea\u0001\u0003\u0017\n1EZ1jYR{7\u000b^8sK\ncwnY6P]\ncwnY6NC:\fw-\u001a:FeJ|'\u000fF\u0003f\u0005C\u0013Y\u000bC\u0004\u0003$V\u0002\rA!*\u0002\u001d\tdwnY6NC:\fw-\u001a:JIB!\u0011Q\nBT\u0013\u0011\u0011I+a\u0014\u0003\u001d\tcwnY6NC:\fw-\u001a:JI\"9\u0011\u0011J\u001bA\u0002\u0005-\u0013\u0001\b:fC\u0012dunY6fI\ncwnY6O_R4u.\u001e8e\u000bJ\u0014xN\u001d\u000b\u0004K\nE\u0006bBA%m\u0001\u0007\u00111J\u0001\u001cM\u0006LG\u000eV8HKR\u0014En\\2l/&$\b\u000eT8dW\u0016\u0013(o\u001c:\u0015\u0007\u0015\u00149\fC\u0004\u0002J]\u0002\r!a\u0013\u0002%\tdwnY6O_R4u.\u001e8e\u000bJ\u0014xN\u001d\u000b\u0004K\nu\u0006bBA%q\u0001\u0007\u00111J\u0001\u0011S:$XM\u001d:vaR,G-\u0012:s_J\f\u0011E\u00197pG.\u001cF/\u0019;vgF+XM]=SKR,(O\\3e\u001dVdG.\u0012:s_J$2!\u001aBc\u0011\u001d\tIE\u000fa\u0001\u0003\u0017\nq&\u001e8fqB,7\r^3e\u00052|7m['b]\u0006<WM]'bgR,'/\u00128ea>Lg\u000e\u001e*fgVdG/\u0012:s_J\f!DZ1jYR{7I]3bi\u0016$\u0015N]3di>\u0014\u00180\u0012:s_J$R!\u001aBg\u0005\u001fDa!a\u001c=\u0001\u0004y\bb\u0002Biy\u0001\u0007\u0011\u0011D\u0001\f[\u0006D\u0018\t\u001e;f[B$8/A\rv]N,\b\u000f]8si\u0016$w\n]3sCRLwN\\#se>\u0014\u0018A\u00058p'V\u001c\u0007.\u00127f[\u0016tG/\u0012:s_J\f\u0001CZ3uG\"4\u0015-\u001b7fI\u0016\u0013(o\u001c:\u0015\u001f\u0015\u0014YNa8\u0003d\n\u001d(1\u001eBx\u0005cDqA!8@\u0001\u0004\u0011)+A\u0005c[\u0006#GM]3tg\"9!\u0011] A\u0002\u0005e\u0011!C:ik\u001a4G.Z%e\u0011\u001d\u0011)o\u0010a\u0001\u0005G\tQ!\\1q\u0013\u0012DqA!;@\u0001\u0004\tI\"\u0001\u0005nCBLe\u000eZ3y\u0011\u001d\u0011io\u0010a\u0001\u00033\t\u0001B]3ek\u000e,\u0017\n\u001a\u0005\u0007\u0005\u001fz\u0004\u0019A@\t\u0011\tMx\b%AA\u0002\u0015\fQaY1vg\u0016\f!DZ3uG\"4\u0015-\u001b7fI\u0016\u0013(o\u001c:%I\u00164\u0017-\u001e7uI]*\"A!?+\u0007\u0015\f)#A\u000fgC&dGk\\$fi:{gn\u00155vM\u001adWM\u00117pG.,%O]8s)\u0015)'q`B\u0001\u0011\u001d\tI%\u0011a\u0001\u0003\u0017Ba!a6B\u0001\u0004)\u0017\u0001I4sCBD\u0017\u000e^3TS:\\\u0017J\u001c<bY&$\u0007K]8u_\u000e|G.\u0012:s_J$2!ZB\u0004\u0011\u0019\u0019IA\u0011a\u0001\u007f\u0006y\u0011N\u001c<bY&$\u0007K]8u_\u000e|G.\u0001\u0011he\u0006\u0004\b.\u001b;f'&t7\u000e\u0015:pa\u0016\u0014H/_'jgNLgnZ#se>\u0014HcA3\u0004\u0010!11\u0011C\"A\u0002}\fq\"\\5tg&tw\r\u0015:pa\u0016\u0014H/_\u0001\u0011_V$xJZ'f[>\u0014\u00180\u0012:s_J$baa\u0006\u0004\u001e\r\u0005\u0002c\u0001;\u0004\u001a%\u001911D;\u0003!=+Ho\u00144NK6|'/_#se>\u0014\bbBB\u0010\t\u0002\u0007!1E\u0001\u000fe\u0016\fX/Z:uK\u0012\u0014\u0015\u0010^3t\u0011\u001d\u0019\u0019\u0003\u0012a\u0001\u0005G\tQB]3dK&4X\r\u001a\"zi\u0016\u001c\u0018!\u00074bS2,GMU3oC6,G+Z7q\r&dW-\u0012:s_J$R!ZB\u0015\u0007sAqaa\u000bF\u0001\u0004\u0019i#A\u0004te\u000e4\u0015\u000e\\3\u0011\t\r=2QG\u0007\u0003\u0007cQ1aa\rx\u0003\tIw.\u0003\u0003\u00048\rE\"\u0001\u0002$jY\u0016Dqaa\u000fF\u0001\u0004\u0019i#A\u0004egR4\u0015\u000e\\3\u0002-\u0005$G\rT8dC2$\u0015N]3di>\u0014\u00180\u0012:s_J$2!ZB!\u0011\u001d\tyG\u0012a\u0001\u00033\u000b\u0011#\u00193e\t&\u0014Xm\u0019;pef,%O]8s)\r)7q\t\u0005\b\u0003_:\u0005\u0019AAM\u0003Y\u0019w\u000eZ3d\u001d>$\u0018I^1jY\u0006\u0014G.Z#se>\u0014HcA3\u0004N!11q\n%A\u0002}\f\u0011bY8eK\u000et\u0015-\\3\u00023Q|w.T1os\u0006\u0013(/Y=FY\u0016lWM\u001c;t\u000bJ\u0014xN\u001d\u000b\u0006K\u000eU3\u0011\f\u0005\b\u0007/J\u0005\u0019\u0001B\u0012\u0003-qW/\\#mK6,g\u000e^:\t\u000f\rm\u0013\n1\u0001\u0002\u001a\u0005)R.\u0019=S_VtG-\u001a3BeJ\f\u0017\u0010T3oORD\u0017AD9v_R,')\u001f#fM\u0006,H\u000e\u001e\u000b\u0004\u007f\u000e\u0005\u0004BBB2\u0015\u0002\u0007q0\u0001\u0003fY\u0016l\u0017A\u0002;p\u0007>tg\rF\u0002\u0000\u0007SBaaa\u001bL\u0001\u0004y\u0018\u0001B2p]\u001a\f\u0011\u0002^8D_:4g+\u00197\u0015\u0007}\u001c\t\b\u0003\u0004\u0004l1\u0003\ra "
)
public final class SparkCoreErrors {
   public static String toConfVal(final String conf) {
      return SparkCoreErrors$.MODULE$.toConfVal(conf);
   }

   public static String toConf(final String conf) {
      return SparkCoreErrors$.MODULE$.toConf(conf);
   }

   public static Throwable tooManyArrayElementsError(final long numElements, final int maxRoundedArrayLength) {
      return SparkCoreErrors$.MODULE$.tooManyArrayElementsError(numElements, maxRoundedArrayLength);
   }

   public static Throwable codecNotAvailableError(final String codecName) {
      return SparkCoreErrors$.MODULE$.codecNotAvailableError(codecName);
   }

   public static Throwable addDirectoryError(final Path path) {
      return SparkCoreErrors$.MODULE$.addDirectoryError(path);
   }

   public static Throwable addLocalDirectoryError(final Path path) {
      return SparkCoreErrors$.MODULE$.addLocalDirectoryError(path);
   }

   public static Throwable failedRenameTempFileError(final File srcFile, final File dstFile) {
      return SparkCoreErrors$.MODULE$.failedRenameTempFileError(srcFile, dstFile);
   }

   public static OutOfMemoryError outOfMemoryError(final long requestedBytes, final long receivedBytes) {
      return SparkCoreErrors$.MODULE$.outOfMemoryError(requestedBytes, receivedBytes);
   }

   public static Throwable graphiteSinkPropertyMissingError(final String missingProperty) {
      return SparkCoreErrors$.MODULE$.graphiteSinkPropertyMissingError(missingProperty);
   }

   public static Throwable graphiteSinkInvalidProtocolError(final String invalidProtocol) {
      return SparkCoreErrors$.MODULE$.graphiteSinkInvalidProtocolError(invalidProtocol);
   }

   public static Throwable failToGetNonShuffleBlockError(final BlockId blockId, final Throwable e) {
      return SparkCoreErrors$.MODULE$.failToGetNonShuffleBlockError(blockId, e);
   }

   public static Throwable fetchFailedError$default$7() {
      return SparkCoreErrors$.MODULE$.fetchFailedError$default$7();
   }

   public static Throwable fetchFailedError(final BlockManagerId bmAddress, final int shuffleId, final long mapId, final int mapIndex, final int reduceId, final String message, final Throwable cause) {
      return SparkCoreErrors$.MODULE$.fetchFailedError(bmAddress, shuffleId, mapId, mapIndex, reduceId, message, cause);
   }

   public static Throwable noSuchElementError() {
      return SparkCoreErrors$.MODULE$.noSuchElementError();
   }

   public static Throwable unsupportedOperationError() {
      return SparkCoreErrors$.MODULE$.unsupportedOperationError();
   }

   public static Throwable failToCreateDirectoryError(final String path, final int maxAttempts) {
      return SparkCoreErrors$.MODULE$.failToCreateDirectoryError(path, maxAttempts);
   }

   public static Throwable unexpectedBlockManagerMasterEndpointResultError() {
      return SparkCoreErrors$.MODULE$.unexpectedBlockManagerMasterEndpointResultError();
   }

   public static Throwable blockStatusQueryReturnedNullError(final BlockId blockId) {
      return SparkCoreErrors$.MODULE$.blockStatusQueryReturnedNullError(blockId);
   }

   public static Throwable interruptedError() {
      return SparkCoreErrors$.MODULE$.interruptedError();
   }

   public static Throwable blockNotFoundError(final BlockId blockId) {
      return SparkCoreErrors$.MODULE$.blockNotFoundError(blockId);
   }

   public static Throwable failToGetBlockWithLockError(final BlockId blockId) {
      return SparkCoreErrors$.MODULE$.failToGetBlockWithLockError(blockId);
   }

   public static Throwable readLockedBlockNotFoundError(final BlockId blockId) {
      return SparkCoreErrors$.MODULE$.readLockedBlockNotFoundError(blockId);
   }

   public static Throwable failToStoreBlockOnBlockManagerError(final BlockManagerId blockManagerId, final BlockId blockId) {
      return SparkCoreErrors$.MODULE$.failToStoreBlockOnBlockManagerError(blockManagerId, blockId);
   }

   public static Throwable unexpectedShuffleBlockWithUnsupportedResolverError(final ShuffleManager shuffleManager, final BlockId blockId) {
      return SparkCoreErrors$.MODULE$.unexpectedShuffleBlockWithUnsupportedResolverError(shuffleManager, blockId);
   }

   public static Throwable waitingForAsyncReregistrationError(final Throwable e) {
      return SparkCoreErrors$.MODULE$.waitingForAsyncReregistrationError(e);
   }

   public static Throwable unableToRegisterWithExternalShuffleServerError(final Throwable e) {
      return SparkCoreErrors$.MODULE$.unableToRegisterWithExternalShuffleServerError(e);
   }

   public static Throwable waitingForReplicationToFinishError(final Throwable e) {
      return SparkCoreErrors$.MODULE$.waitingForReplicationToFinishError(e);
   }

   public static Throwable cannotSaveBlockOnDecommissionedExecutorError(final BlockId blockId) {
      return SparkCoreErrors$.MODULE$.cannotSaveBlockOnDecommissionedExecutorError(blockId);
   }

   public static Throwable blockDoesNotExistError(final BlockId blockId) {
      return SparkCoreErrors$.MODULE$.blockDoesNotExistError(blockId);
   }

   public static Throwable taskHasNotLockedBlockError(final long currentTaskAttemptId, final BlockId blockId) {
      return SparkCoreErrors$.MODULE$.taskHasNotLockedBlockError(currentTaskAttemptId, blockId);
   }

   public static Throwable unrecognizedBlockIdError(final String name) {
      return SparkCoreErrors$.MODULE$.unrecognizedBlockIdError(name);
   }

   public static Throwable failToSerializeTaskError(final Throwable e) {
      return SparkCoreErrors$.MODULE$.failToSerializeTaskError(e);
   }

   public static Throwable clusterSchedulerError(final String message) {
      return SparkCoreErrors$.MODULE$.clusterSchedulerError(message);
   }

   public static Throwable sparkError(final String errorMsg) {
      return SparkCoreErrors$.MODULE$.sparkError(errorMsg);
   }

   public static Throwable unrecognizedSchedulerModePropertyError(final String schedulerModeProperty, final String schedulingModeConf) {
      return SparkCoreErrors$.MODULE$.unrecognizedSchedulerModePropertyError(schedulerModeProperty, schedulingModeConf);
   }

   public static Throwable durationCalledOnUnfinishedTaskError() {
      return SparkCoreErrors$.MODULE$.durationCalledOnUnfinishedTaskError();
   }

   public static Throwable nonEmptyEventQueueAfterTimeoutError(final long timeoutMillis) {
      return SparkCoreErrors$.MODULE$.nonEmptyEventQueueAfterTimeoutError(timeoutMillis);
   }

   public static Throwable sendResubmittedTaskStatusForShuffleMapStagesOnlyError() {
      return SparkCoreErrors$.MODULE$.sendResubmittedTaskStatusForShuffleMapStagesOnlyError();
   }

   public static Throwable accessNonExistentAccumulatorError(final long id) {
      return SparkCoreErrors$.MODULE$.accessNonExistentAccumulatorError(id);
   }

   public static Throwable cannotRunSubmitMapStageOnZeroPartitionRDDError() {
      return SparkCoreErrors$.MODULE$.cannotRunSubmitMapStageOnZeroPartitionRDDError();
   }

   public static Throwable numPartitionsGreaterThanMaxNumConcurrentTasksError(final int numPartitions, final int maxNumConcurrentTasks) {
      return SparkCoreErrors$.MODULE$.numPartitionsGreaterThanMaxNumConcurrentTasksError(numPartitions, maxNumConcurrentTasks);
   }

   public static Throwable barrierStageWithDynamicAllocationError() {
      return SparkCoreErrors$.MODULE$.barrierStageWithDynamicAllocationError();
   }

   public static Throwable barrierStageWithRDDChainPatternError() {
      return SparkCoreErrors$.MODULE$.barrierStageWithRDDChainPatternError();
   }

   public static SparkException sparkJobCancelledAsPartOfJobGroupError(final int jobId, final String jobGroupId) {
      return SparkCoreErrors$.MODULE$.sparkJobCancelledAsPartOfJobGroupError(jobId, jobGroupId);
   }

   public static SparkException sparkJobCancelled(final int jobId, final String reason, final Exception e) {
      return SparkCoreErrors$.MODULE$.sparkJobCancelled(jobId, reason, e);
   }

   public static Throwable noExecutorIdleError(final String id) {
      return SparkCoreErrors$.MODULE$.noExecutorIdleError(id);
   }

   public static Throwable stopStandaloneSchedulerDriverEndpointError(final Exception e) {
      return SparkCoreErrors$.MODULE$.stopStandaloneSchedulerDriverEndpointError(e);
   }

   public static Throwable askStandaloneSchedulerToShutDownExecutorsError(final Exception e) {
      return SparkCoreErrors$.MODULE$.askStandaloneSchedulerToShutDownExecutorsError(e);
   }

   public static Throwable mustSpecifyCheckpointDirError() {
      return SparkCoreErrors$.MODULE$.mustSpecifyCheckpointDirError();
   }

   public static Throwable checkpointFailedToSaveError(final int task, final Path path) {
      return SparkCoreErrors$.MODULE$.checkpointFailedToSaveError(task, path);
   }

   public static Throwable checkpointRDDHasDifferentNumberOfPartitionsFromOriginalRDDError(final int originalRDDId, final int originalRDDLength, final int newRDDId, final int newRDDLength) {
      return SparkCoreErrors$.MODULE$.checkpointRDDHasDifferentNumberOfPartitionsFromOriginalRDDError(originalRDDId, originalRDDLength, newRDDId, newRDDLength);
   }

   public static Throwable failToCreateCheckpointPathError(final Path checkpointDirPath) {
      return SparkCoreErrors$.MODULE$.failToCreateCheckpointPathError(checkpointDirPath);
   }

   public static Throwable invalidCheckpointFileError(final Path path) {
      return SparkCoreErrors$.MODULE$.invalidCheckpointFileError(path);
   }

   public static Throwable checkpointDirectoryHasNotBeenSetInSparkContextError() {
      return SparkCoreErrors$.MODULE$.checkpointDirectoryHasNotBeenSetInSparkContextError();
   }

   public static Throwable countByValueApproxNotSupportArraysError() {
      return SparkCoreErrors$.MODULE$.countByValueApproxNotSupportArraysError();
   }

   public static Throwable emptyCollectionError() {
      return SparkCoreErrors$.MODULE$.emptyCollectionError();
   }

   public static Throwable canOnlyZipRDDsWithSamePartitionSizeError() {
      return SparkCoreErrors$.MODULE$.canOnlyZipRDDsWithSamePartitionSizeError();
   }

   public static Throwable cannotChangeStorageLevelError() {
      return SparkCoreErrors$.MODULE$.cannotChangeStorageLevelError();
   }

   public static Throwable rddLacksSparkContextError() {
      return SparkCoreErrors$.MODULE$.rddLacksSparkContextError();
   }

   public static Throwable reduceByKeyLocallyNotSupportArrayKeysError() {
      return SparkCoreErrors$.MODULE$.reduceByKeyLocallyNotSupportArrayKeysError();
   }

   public static Throwable hashPartitionerCannotPartitionArrayKeyError() {
      return SparkCoreErrors$.MODULE$.hashPartitionerCannotPartitionArrayKeyError();
   }

   public static Throwable cannotUseMapSideCombiningWithArrayKeyError() {
      return SparkCoreErrors$.MODULE$.cannotUseMapSideCombiningWithArrayKeyError();
   }

   public static Throwable endOfStreamError() {
      return SparkCoreErrors$.MODULE$.endOfStreamError();
   }

   public static Throwable checkpointRDDBlockIdNotFoundError(final RDDBlockId rddBlockId) {
      return SparkCoreErrors$.MODULE$.checkpointRDDBlockIdNotFoundError(rddBlockId);
   }

   public static Throwable pathNotSupportedError(final String path) {
      return SparkCoreErrors$.MODULE$.pathNotSupportedError(path);
   }

   public static Throwable emptyRDDError() {
      return SparkCoreErrors$.MODULE$.emptyRDDError();
   }

   public static Throwable histogramOnEmptyRDDOrContainingInfinityOrNaNError() {
      return SparkCoreErrors$.MODULE$.histogramOnEmptyRDDOrContainingInfinityOrNaNError();
   }

   public static Throwable blockHaveBeenRemovedError(final String string) {
      return SparkCoreErrors$.MODULE$.blockHaveBeenRemovedError(string);
   }

   public static Throwable rddBlockNotFoundError(final BlockId blockId, final int id) {
      return SparkCoreErrors$.MODULE$.rddBlockNotFoundError(blockId, id);
   }

   public static Throwable unsupportedDataTypeError(final Object other) {
      return SparkCoreErrors$.MODULE$.unsupportedDataTypeError(other);
   }

   public static Option eofExceptionWhileReadPortNumberError$default$2() {
      return SparkCoreErrors$.MODULE$.eofExceptionWhileReadPortNumberError$default$2();
   }

   public static Throwable eofExceptionWhileReadPortNumberError(final String daemonModule, final Option daemonExitValue) {
      return SparkCoreErrors$.MODULE$.eofExceptionWhileReadPortNumberError(daemonModule, daemonExitValue);
   }

   public static Throwable unexpectedPy4JServerError(final Object other) {
      return SparkCoreErrors$.MODULE$.unexpectedPy4JServerError(other);
   }
}
