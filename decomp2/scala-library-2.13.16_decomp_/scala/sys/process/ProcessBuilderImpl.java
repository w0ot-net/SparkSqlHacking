package scala.sys.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.ProcessBuilder.Redirect;
import java.lang.invoke.SerializedLambda;
import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;
import scala.Function0;
import scala.Function1;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.LazyList;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Stream;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0$mcV$sp;
import scala.util.control.NonFatal$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011McADA\b\u0003#\u0001\n1!\u0001\u0002\u0012\u0005uAq\n\u0005\b\u0003O\u0001A\u0011AA\u0016\r!\t\u0019\u0004\u0001\u0001\u0002\u0012\u0005U\u0002B\u0003BY\u0005\t\u0005\t\u0015!\u0003\u0002@!9\u0011q\u000b\u0002\u0005\u0002\tM\u0006bBAB\u0005\u0011\u0015!\u0011\u0018\u0004\t\u0005{\u0003\u0001!!\u0005\u0003@\"Q!\u0011\u0019\u0004\u0003\u0006\u0004%\t%!,\t\u0015\t\rgA!A!\u0002\u0013\ty\u000b\u0003\u0006\u0003F\u001a\u0011\t\u0011*A\u0005\u0005\u000fDq!a\u0016\u0007\t\u0003\u0011i\rC\u0004\u0002\u0004\u001a!\tE!6\t\u000f\t-f\u0001\"\u0011\u0003.\u001aA!\u0011\u001c\u0001\u0001\u0003#\u0011Y\u000e\u0003\u0006\u0004\u00165\u0011\t\u0011)A\u0005\u0007/Aq!a\u0016\u000e\t\u0003\u0019iB\u0002\u0005\u0004$\u0001\u0001\u0011\u0011CB\u0013\u0011)\u00199\u0003\u0005B\u0001B\u0003%1\u0011\u0006\u0005\b\u0003/\u0002B\u0011AB\u0018\r!\u0019)\u0004\u0001\u0001\u0002\u0012\r]\u0002BCB\u0014'\t\u0005\t\u0015!\u0003\u0004*!Q1QJ\n\u0003\u0002\u0003\u0006I!a%\t\u000f\u0005]3\u0003\"\u0001\u0004P\u0019A11\b\u0001\u0001\u0003#\u0019i\u0004\u0003\u0006\u0003|^\u0011\t\u0011*A\u0005\u0007\u007fA1b!\u0004\u0018\u0005\u0003\u0005\u000b\u0011BAXE!9\u0011qK\f\u0005\u0002\r\u001d\u0003b\u0002BX/\u0011\u0005#Q\u0016\u0004\t\u0005?\u0004\u0001!!\u0005\u0003b\"Q!1 \u000f\u0003\u0002\u0013\u0006IA!@\t\u0017\r5AD!A!\u0002\u0013\tyK\t\u0005\b\u0003/bB\u0011AB\b\u0011\u001d\u0011y\u000b\bC!\u0005[3\u0011B!:\u0001\u0003\u0003\t\tBa:\t\u0015\t\u0005\u0017E!b\u0001\n\u0003\ni\u000b\u0003\u0006\u0003D\u0006\u0012\t\u0011)A\u0005\u0003_C!B!;\"\u0005\u0003\u0005\u000b\u0011\u0002Bv\u0011\u001d\t9&\tC\u0001\u0005cDq!a!\"\t\u0003\u00129P\u0002\u0005\u0004X\u0001\u0001\u0011\u0011CB-\u0011)\u0019Yf\nB\u0001B\u0003%1Q\f\u0005\b\u0003/:C\u0011AB2\u0011\u001d\t\u0019i\nC!\u0007SBqA!1(\t\u0003\u001ai\u0007C\u0004\u0003,\u001e\"\tE!,\u0007\u0013\u0005m\u0002!!\u0001\u0002\u001a\u0005u\u0002bBA,[\u0011\u0005\u0011\u0011\f\u0005\b\u00037jC\u0011CA/\u0011\u001d\ty&\fC\t\u0003;B\u0001\"!\u0019.A\u0003%\u00111\r\u0005\b\u0003SjC\u0011AA6\u0011\u001d\t\t(\fC\u0001\u0003gBq!a\u001e.\t\u0003\tI\bC\u0004\u0002~5\"\t!a \t\u000f\u0005\rU\u0006\"\u0001\u0002\u0006\"9\u00111Q\u0017\u0005\u0002\u00055\u0005bBAB[\u0011\u0005\u0011\u0011\u0014\u0005\b\u0003\u0007kC\u0011AAS\u0011\u001d\tY+\fC\u0001\u0003[Cq!a+.\t\u0003\t)\rC\u0004\u0002J6\"\t!!,\t\u000f\u0005%W\u0006\"\u0001\u0002L\"9\u0011qZ\u0017\u0005\u0002\u0005E\u0007bBAh[\u0011\u0005\u0011\u0011\u001d\u0005\b\u0003KlC\u0011AAi\u0011\u001d\t)/\fC\u0001\u0003ODq!a4.\t\u0003\tY\u000fC\u0004\u0002P6\"\tA!\u0001\t\u000f\u0005\u0015X\u0006\"\u0001\u0003\b!9\u0011Q]\u0017\u0005\u0002\t-\u0001b\u0002B\t[\u0011\u0005!1\u0003\u0005\b\u0005#iC\u0011\u0001B\u0018\u0011\u001d\u0011)$\fC\u0001\u0005'AqA!\u000e.\t\u0003\u0011I\u0004C\u0004\u0003\u00125\"\tAa\u0010\t\u000f\tEQ\u0006\"\u0001\u0003F!9!QG\u0017\u0005\u0002\t5\u0003b\u0002B\u001b[\u0011\u0005!1\u000b\u0005\b\u00057jC\u0011\u0001B/\u0011\u001d\u0011Y&\fC\u0001\u0005?BqAa\u0017.\t\u0003\u0011Y\u0007C\u0004\u0003p5\"\tA!\u0018\t\u000f\t=T\u0006\"\u0001\u0003r!9!QO\u0017\u0005\u0002\t]\u0004\u0002\u0003B=[\u0001&IAa\u001f\t\u0011\u0005=W\u0006)C\u0005\u0005\u0013C\u0001B!\u0005.A\u0013%!q\u0013\u0005\t\u0005Gk\u0003\u0015\"\u0003\u0003&\"9!1V\u0017\u0005\u0002\t5\u0006b\u0002BX[\u0011\u0005!Q\u0016\u0004\t\u0007g\u0002\u0001!!\u0005\u0004v!Q1Q\u0003.\u0003\u0002\u0003\u0006Iaa\u0006\t\u000f\u0005]#\f\"\u0001\u0004~!9\u00111\f.\u0005\u0012\r\re\u0001CBC\u0001\u0001\t\tba\"\t\u0015\r=eL!A!\u0002\u0013\u0019I\u0003C\u0004\u0002Xy#\ta!%\t\u000f\u0005mc\f\"\u0005\u0004\u0018\"9\u0011q\f0\u0005\u0012\re\u0005bBBN=\u0012\u00051Q\u0014\u0005\b\u00077sF\u0011ABR\u0011\u001d\u0019YJ\u0018C\u0001\u0007SCqaa'_\t\u0003\u0019yKB\u0005\u00046\u0002\t\t!!\u0005\u00048\"9\u0011qK4\u0005\u0002\re\u0006\u0002CB_O\u0002&\tba0\t\u000f\u0005\ru\r\"\u0002\u0004F\"A1\u0011Z4!\u000e#\u0019YMB\u0005\u0004`\u0002\t\t!!\u0005\u0004b\"Q11\u00197\u0003\u0002\u0003\u0006I!a\u0010\t\u0015\rMFN!A!\u0002\u0013\ty\u0004\u0003\u0006\u0004d2\u0014\t\u0011)A\u0005\u0003_Cq!a\u0016m\t\u0003\u0019)\u000fC\u0004\u0003B2$\te!\u001c\u0007\u0011\r=\b\u0001AA\t\u0007cD!ba=s\u0005\u0003\u0005\u000b\u0011BA \u0011)\u0019)P\u001dB\u0001B\u0003%\u0011q\b\u0005\u000b\u0007o\u0014(\u0011!Q\u0001\n\u0005M\u0005bBA,e\u0012\u00051\u0011 \u0005\b\u0007\u0013\u0014H\u0011\tC\u0002\r!!i\u0001\u0001\u0001\u0002\u0012\u0011=\u0001BCBzq\n\u0005\t\u0015!\u0003\u0002@!Q1Q\u001f=\u0003\u0002\u0003\u0006I!a\u0010\t\u000f\u0005]\u0003\u0010\"\u0001\u0005\u0012!91\u0011\u001a=\u0005B\u0011ea\u0001\u0003C\u0012\u0001\u0001\t\t\u0002\"\n\t\u0015\rMXP!A!\u0002\u0013\ty\u0004\u0003\u0006\u0004vv\u0014\t\u0011)A\u0005\u0003\u007fAq!a\u0016~\t\u0003!9\u0003C\u0004\u0004Jv$\t\u0005b\f\u0007\u0011\u0011e\u0002\u0001AA\t\twA1ba=\u0002\u0006\t\u0005\t\u0015!\u0003\u0002@!Y1Q_A\u0003\u0005\u0003\u0005\u000b\u0011BA \u0011!\t9&!\u0002\u0005\u0002\u0011u\u0002\u0002CBe\u0003\u000b!\t\u0005\"\u0012\u0003%A\u0013xnY3tg\n+\u0018\u000e\u001c3fe&k\u0007\u000f\u001c\u0006\u0005\u0003'\t)\"A\u0004qe>\u001cWm]:\u000b\t\u0005]\u0011\u0011D\u0001\u0004gf\u001c(BAA\u000e\u0003\u0015\u00198-\u00197b'\r\u0001\u0011q\u0004\t\u0005\u0003C\t\u0019#\u0004\u0002\u0002\u001a%!\u0011QEA\r\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\u001a\u0001\u0001\u0006\u0002\u0002.A!\u0011\u0011EA\u0018\u0013\u0011\t\t$!\u0007\u0003\tUs\u0017\u000e\u001e\u0002\u000e\t\u0006,Wn\u001c8Ck&dG-\u001a:\u0014\u0007\t\t9\u0004E\u0002\u0002:5j\u0011\u0001\u0001\u0002\u0010\u0003\n\u001cHO]1di\n+\u0018\u000e\u001c3feNIQ&a\b\u0002@\u0005\u001d\u0013\u0011\u000b\t\u0005\u0003\u0003\n\u0019%\u0004\u0002\u0002\u0012%!\u0011QIA\t\u00059\u0001&o\\2fgN\u0014U/\u001b7eKJ\u0004B!!\u000f\u0002J%!\u00111JA'\u0005\u0011\u0019\u0016N\\6\u000b\t\u0005=\u0013\u0011C\u0001\u000f!J|7-Z:t\u0005VLG\u000eZ3s!\u0011\tI$a\u0015\n\t\u0005U\u0013Q\n\u0002\u0007'>,(oY3\u0002\rqJg.\u001b;?)\t\t9$\u0001\u0005u_N{WO]2f+\t\t9$\u0001\u0004u_NKgn[\u0001\u0016I\u00164\u0017-\u001e7u'R\u0014X-Y7DCB\f7-\u001b;z!\u0011\t\t#!\u001a\n\t\u0005\u001d\u0014\u0011\u0004\u0002\u0004\u0013:$\u0018!\u0003\u0013iCNDGEY1s)\u0011\ty$!\u001c\t\u000f\u0005=$\u00071\u0001\u0002@\u0005)q\u000e\u001e5fe\u0006iA\u0005[1tQ\u0012\u0012\u0017M\u001d\u0013cCJ$B!a\u0010\u0002v!9\u0011qN\u001aA\u0002\u0005}\u0012!\u0004\u0013iCNDG%Y7qI\u0005l\u0007\u000f\u0006\u0003\u0002@\u0005m\u0004bBA8i\u0001\u0007\u0011qH\u0001\u0010I!\f7\u000f\u001b\u0013iCNDG\u0005[1tQR!\u0011qHAA\u0011\u001d\ty'\u000ea\u0001\u0003\u007f\t1A];o)\t\t9\t\u0005\u0003\u0002B\u0005%\u0015\u0002BAF\u0003#\u0011q\u0001\u0015:pG\u0016\u001c8\u000f\u0006\u0003\u0002\b\u0006=\u0005bBAIo\u0001\u0007\u00111S\u0001\rG>tg.Z2u\u0013:\u0004X\u000f\u001e\t\u0005\u0003C\t)*\u0003\u0003\u0002\u0018\u0006e!a\u0002\"p_2,\u0017M\u001c\u000b\u0005\u0003\u000f\u000bY\nC\u0004\u0002\u001eb\u0002\r!a(\u0002\u00071|w\r\u0005\u0003\u0002B\u0005\u0005\u0016\u0002BAR\u0003#\u0011Q\u0002\u0015:pG\u0016\u001c8\u000fT8hO\u0016\u0014HCBAD\u0003O\u000bI\u000bC\u0004\u0002\u001ef\u0002\r!a(\t\u000f\u0005E\u0015\b1\u0001\u0002\u0014\u0006QAEY1oO\u0012\u0012\u0017M\\4\u0016\u0005\u0005=\u0006\u0003BAY\u0003\u007fsA!a-\u0002<B!\u0011QWA\r\u001b\t\t9L\u0003\u0003\u0002:\u0006%\u0012A\u0002\u001fs_>$h(\u0003\u0003\u0002>\u0006e\u0011A\u0002)sK\u0012,g-\u0003\u0003\u0002B\u0006\r'AB*ue&twM\u0003\u0003\u0002>\u0006eA\u0003BAX\u0003\u000fDq!!(<\u0001\u0004\ty*A\b%E\u0006tw\r\n2b]\u001e$C.Z:t)\u0011\ty+!4\t\u000f\u0005uU\b1\u0001\u0002 \u0006IA.\u0019>z\u0019&tWm]\u000b\u0003\u0003'\u0004b!!6\u0002\\\u0006=f\u0002BA\u0011\u0003/LA!!7\u0002\u001a\u00059\u0001/Y2lC\u001e,\u0017\u0002BAo\u0003?\u0014\u0001\u0002T1{s2K7\u000f\u001e\u0006\u0005\u00033\fI\u0002\u0006\u0003\u0002T\u0006\r\bbBAO\u007f\u0001\u0007\u0011qT\u0001\u0010Y\u0006T\u0018\u0010T5oKN|FEY1oOR!\u00111[Au\u0011\u001d\ti*\u0011a\u0001\u0003?#B!a5\u0002n\"9\u0011q\u001e\"A\u0002\u0005E\u0018\u0001C2ba\u0006\u001c\u0017\u000e^=\u0011\t\u0005M\u0018Q`\u0007\u0003\u0003kTA!a>\u0002z\u0006!A.\u00198h\u0015\t\tY0\u0001\u0003kCZ\f\u0017\u0002BA\u0000\u0003k\u0014q!\u00138uK\u001e,'\u000f\u0006\u0004\u0002T\n\r!Q\u0001\u0005\b\u0003;\u001b\u0005\u0019AAP\u0011\u001d\tyo\u0011a\u0001\u0003c$B!a5\u0003\n!9\u0011q\u001e#A\u0002\u0005EHCBAj\u0005\u001b\u0011y\u0001C\u0004\u0002\u001e\u0016\u0003\r!a(\t\u000f\u0005=X\t1\u0001\u0002r\u0006QA.\u001b8f'R\u0014X-Y7\u0016\u0005\tU\u0001CBAk\u0005/\ty+\u0003\u0003\u0003\u001a\u0005}'AB*ue\u0016\fW\u000eK\u0006G\u0005;\u0011\u0019C!\n\u0003*\t-\u0002\u0003BA\u0011\u0005?IAA!\t\u0002\u001a\tQA-\u001a9sK\u000e\fG/\u001a3\u0002\u000f5,7o]1hK\u0006\u0012!qE\u0001\tS:$XM\u001d8bY\u0006)1/\u001b8dK\u0006\u0012!QF\u0001\u0007e9\n4G\f\u001b\u0015\t\tU!\u0011\u0007\u0005\b\u0003;;\u0005\u0019AAPQ-9%Q\u0004B\u0012\u0005K\u0011ICa\u000b\u0002!1Lg.Z*ue\u0016\fWn\u0018\u0013cC:<\u0007f\u0003%\u0003\u001e\t\r\"Q\u0005B\u0015\u0005W!BA!\u0006\u0003<!9\u0011QT%A\u0002\u0005}\u0005fC%\u0003\u001e\t\r\"Q\u0005B\u0015\u0005W!BA!\u0006\u0003B!9\u0011q\u001e&A\u0002\u0005E\bf\u0003&\u0003\u001e\t\r\"Q\u0005B\u0015\u0005W!bA!\u0006\u0003H\t%\u0003bBAO\u0017\u0002\u0007\u0011q\u0014\u0005\b\u0003_\\\u0005\u0019AAyQ-Y%Q\u0004B\u0012\u0005K\u0011ICa\u000b\u0015\t\tU!q\n\u0005\b\u0003_d\u0005\u0019AAyQ-a%Q\u0004B\u0012\u0005K\u0011ICa\u000b\u0015\r\tU!Q\u000bB,\u0011\u001d\ti*\u0014a\u0001\u0003?Cq!a<N\u0001\u0004\t\t\u0010K\u0006N\u0005;\u0011\u0019C!\n\u0003*\t-\u0012!\u0002\u0013cC:<WCAA2)\u0011\t\u0019G!\u0019\t\u000f\t\rt\n1\u0001\u0003f\u0005\u0011\u0011n\u001c\t\u0005\u0003\u0003\u00129'\u0003\u0003\u0003j\u0005E!!\u0003)s_\u000e,7o]%P)\u0011\t\u0019G!\u001c\t\u000f\u0005u\u0005\u000b1\u0001\u0002 \u0006QAEY1oO\u0012bWm]:\u0015\t\u0005\r$1\u000f\u0005\b\u0003;\u0013\u0006\u0019AAP\u0003)!\u0017-Z7p]&TX\r\u001a\u000b\u0003\u0003\u007f\tQa\u001d7veB$b!a,\u0003~\t\u0015\u0005bBAO)\u0002\u0007!q\u0010\t\u0007\u0003C\u0011\t)a(\n\t\t\r\u0015\u0011\u0004\u0002\u0007\u001fB$\u0018n\u001c8\t\u000f\t\u001dE\u000b1\u0001\u0002\u0014\u00061q/\u001b;i\u0013:$\"\"a5\u0003\f\n=%1\u0013BK\u0011\u001d\u0011i)\u0016a\u0001\u0003'\u000b\u0011b^5uQ&s\u0007/\u001e;\t\u000f\tEU\u000b1\u0001\u0002\u0014\u0006\u0001bn\u001c8[KJ|W\t_2faRLwN\u001c\u0005\b\u0003;+\u0006\u0019\u0001B@\u0011\u001d\ty/\u0016a\u0001\u0003c$\"B!\u0006\u0003\u001a\nm%Q\u0014BP\u0011\u001d\u0011iI\u0016a\u0001\u0003'CqA!%W\u0001\u0004\t\u0019\nC\u0004\u0002\u001eZ\u0003\rAa \t\u000f\u0005=h\u000b1\u0001\u0002r\"ZaK!\b\u0003$\t\u0015\"\u0011\u0006B\u0016\u0003-\u0011XO\u001c\"vM\u001a,'/\u001a3\u0015\r\u0005\r$q\u0015BU\u0011\u001d\tij\u0016a\u0001\u0003?Cq!!%X\u0001\u0004\t\u0019*A\u0005dC:\u0004\u0016\u000e]3U_V\u0011\u00111S\u0001\rQ\u0006\u001cX\t_5u-\u0006dW/Z\u0001\u000bk:$WM\u001d7zS:<G\u0003\u0002B[\u0005o\u00032!!\u000f\u0003\u0011\u001d\u0011\t\f\u0002a\u0001\u0003\u007f!B!a\"\u0003<\"9!1M\u0003A\u0002\t\u0015$!\u0002#v[6L8c\u0001\u0004\u00028\u0005AAo\\*ue&tw-A\u0005u_N#(/\u001b8hA\u0005IQ\r_5u-\u0006dW/\u001a\t\u0007\u0003C\u0011I-a\u0019\n\t\t-\u0017\u0011\u0004\u0002\ty\tLh.Y7f}Q1!q\u001aBi\u0005'\u00042!!\u000f\u0007\u0011\u001d\u0011\tM\u0003a\u0001\u0003_C\u0001B!2\u000b\t\u0003\u0007!q\u0019\u000b\u0005\u0003\u000f\u00139\u000eC\u0004\u0003d-\u0001\rA!\u001a\u0003\u0011U\u0013F*\u00138qkR\u001c2!\u0004Bo!\r\tI\u0004\b\u0002\u000f\u0013N#(/Z1n\u0005VLG\u000eZ3s'\ra\"1\u001d\t\u0004\u0003s\t#!\u0004+ie\u0016\fGMQ;jY\u0012,'oE\u0002\"\u0003o\tqA];o\u00136\u0004H\u000e\u0005\u0005\u0002\"\t5(QMA\u0017\u0013\u0011\u0011y/!\u0007\u0003\u0013\u0019+hn\u0019;j_:\fDC\u0002Br\u0005g\u0014)\u0010C\u0004\u0003B\u0016\u0002\r!a,\t\u000f\t%X\u00051\u0001\u0003lR!\u0011q\u0011B}\u0011\u001d\u0011\u0019G\na\u0001\u0005K\naa\u001d;sK\u0006l\u0007CBA\u0011\u0005\u0013\u0014y\u0010\u0005\u0003\u0004\u0002\r\u001da\u0002BA!\u0007\u0007IAa!\u0002\u0002\u0012\u0005y\u0001O]8dKN\u001c\u0018J\u001c;fe:\fG.\u0003\u0003\u0004\n\r-!aC%oaV$8\u000b\u001e:fC6TAa!\u0002\u0002\u0012\u0005)A.\u00192fYR1!Q\\B\t\u0007'A\u0001Ba? \t\u0003\u0007!Q \u0005\b\u0007\u001by\u0002\u0019AAX\u0003\r)(\u000f\u001c\t\u0005\u0007\u0003\u0019I\"\u0003\u0003\u0004\u001c\r-!aA+S\u0019R!1qDB\u0011!\r\tI$\u0004\u0005\b\u0007+y\u0001\u0019AB\f\u0005%1\u0015\u000e\\3J]B,HoE\u0002\u0011\u0005;\fAAZ5mKB!1\u0011AB\u0016\u0013\u0011\u0019ica\u0003\u0003\t\u0019KG.\u001a\u000b\u0005\u0007c\u0019\u0019\u0004E\u0002\u0002:AAqaa\n\u0013\u0001\u0004\u0019IC\u0001\u0006GS2,w*\u001e;qkR\u001c2aEB\u001d!\r\tId\u0006\u0002\u000f\u001fN#(/Z1n\u0005VLG\u000eZ3s'\r9\"1\u001d\t\u0007\u0003C\u0011Im!\u0011\u0011\t\r\u000511I\u0005\u0005\u0007\u000b\u001aYA\u0001\u0007PkR\u0004X\u000f^*ue\u0016\fW\u000e\u0006\u0004\u0004:\r%31\n\u0005\t\u0005wTB\u00111\u0001\u0004@!91Q\u0002\u000eA\u0002\u0005=\u0016AB1qa\u0016tG\r\u0006\u0004\u0004R\rM3Q\u000b\t\u0004\u0003s\u0019\u0002bBB\u0014-\u0001\u00071\u0011\u0006\u0005\b\u0007\u001b2\u0002\u0019AAJ\u0005\u0019\u0019\u0016.\u001c9mKN\u0019q%a\u000e\u0002\u0003A\u0004Ba!\u0001\u0004`%!1\u0011MB\u0006\u0005=Q\u0005K]8dKN\u001c()^5mI\u0016\u0014H\u0003BB3\u0007O\u00022!!\u000f(\u0011\u001d\u0019Y&\u000ba\u0001\u0007;\"B!a\"\u0004l!9!1\r\u0016A\u0002\t\u0015DCAB8!\u0011\t\u0019p!\u001d\n\t\u0005\u0005\u0017Q\u001f\u0002\b+Jc\u0015*\u001c9m'\u001dQ\u0016qDB<\u0003#\u0002B!!\u000f\u0004z%!11PA'\u0005))&\u000b\u0014\"vS2$WM\u001d\u000b\u0005\u0007\u007f\u001a\t\tE\u0002\u0002:iCqa!\u0006]\u0001\u0004\u00199\"\u0006\u0002\u0004 \tAa)\u001b7f\u00136\u0004HnE\u0005_\u0003?\u0019I)a\u0012\u0002RA!\u0011\u0011HBF\u0013\u0011\u0019i)!\u0014\u0003\u0017\u0019KG.\u001a\"vS2$WM]\u0001\u0005E\u0006\u001cX\r\u0006\u0003\u0004\u0014\u000eU\u0005cAA\u001d=\"91q\u00121A\u0002\r%RCAB\u0019+\t\u0019\t&A\b%Q\u0006\u001c\b\u000e\n7fgN$C.Z:t)\u0011\tyda(\t\u000f\r\u00056\r1\u0001\u0004*\u0005\ta\r\u0006\u0003\u0002@\r\u0015\u0006bBBTI\u0002\u00071qC\u0001\u0002kR!\u0011qHBV\u0011!\u0019i+\u001aCA\u0002\tu\u0018!A:\u0015\t\u0005}2\u0011\u0017\u0005\b\u0007g3\u0007\u0019AA \u0003\u0005\u0011'\u0001\u0004\"bg&\u001c')^5mI\u0016\u00148cA4\u00028Q\u001111\u0018\t\u0004\u0003s9\u0017\u0001D2iK\u000e\\gj\u001c;UQ&\u001cH\u0003BA\u0017\u0007\u0003Dqaa1j\u0001\u0004\ty$A\u0001b)\u0011\t9ia2\t\u000f\t\r$\u000e1\u0001\u0003f\u0005i1M]3bi\u0016\u0004&o\\2fgN$Ba!4\u0004^B!1qZBk\u001d\u0011\t\te!5\n\t\rM\u0017\u0011C\u0001\b!J|7-Z:t\u0013\u0011\u00199n!7\u0003\u0019\t\u000b7/[2Qe>\u001cWm]:\n\t\rm\u0017\u0011\u0003\u0002\f!J|7-Z:t\u00136\u0004H\u000eC\u0004\u0003d-\u0004\rA!\u001a\u0003#M+\u0017/^3oi&\fGNQ;jY\u0012,'oE\u0002m\u0007w\u000bab\u001c9fe\u0006$xN]*ue&tw\r\u0006\u0005\u0004h\u000e%81^Bw!\r\tI\u0004\u001c\u0005\b\u0007\u0007\u0004\b\u0019AA \u0011\u001d\u0019\u0019\f\u001da\u0001\u0003\u007fAqaa9q\u0001\u0004\tyK\u0001\u0007QSB,GMQ;jY\u0012,'oE\u0002s\u0007O\fQAZ5sgR\faa]3d_:$\u0017a\u0002;p\u000bJ\u0014xN\u001d\u000b\t\u0007w\u001cipa@\u0005\u0002A\u0019\u0011\u0011\b:\t\u000f\rMh\u000f1\u0001\u0002@!91Q\u001f<A\u0002\u0005}\u0002bBB|m\u0002\u0007\u00111\u0013\u000b\u0005\t\u000b!Y\u0001\u0005\u0003\u0004P\u0012\u001d\u0011\u0002\u0002C\u0005\u00073\u0014a\u0002U5qK\u0012\u0004&o\\2fgN,7\u000fC\u0004\u0003d]\u0004\rA!\u001a\u0003\u0015\u0005sGMQ;jY\u0012,'oE\u0002y\u0007O$b\u0001b\u0005\u0005\u0016\u0011]\u0001cAA\u001dq\"911_>A\u0002\u0005}\u0002bBB{w\u0002\u0007\u0011q\b\u000b\u0005\t7!\t\u0003\u0005\u0003\u0004P\u0012u\u0011\u0002\u0002C\u0010\u00073\u0014!\"\u00118e!J|7-Z:t\u0011\u001d\u0011\u0019\u0007 a\u0001\u0005K\u0012\u0011b\u0014:Ck&dG-\u001a:\u0014\u0007u\u001c9\u000f\u0006\u0004\u0005*\u0011-BQ\u0006\t\u0004\u0003si\b\u0002CBz\u0003\u0003\u0001\r!a\u0010\t\u0011\rU\u0018\u0011\u0001a\u0001\u0003\u007f!B\u0001\"\r\u00058A!1q\u001aC\u001a\u0013\u0011!)d!7\u0003\u0013=\u0013\bK]8dKN\u001c\b\u0002\u0003B2\u0003\u0007\u0001\rA!\u001a\u0003\u001fM+\u0017/^3oG\u0016\u0014U/\u001b7eKJ\u001cB!!\u0002\u0004hR1Aq\bC!\t\u0007\u0002B!!\u000f\u0002\u0006!A11_A\u0006\u0001\u0004\ty\u0004\u0003\u0005\u0004v\u0006-\u0001\u0019AA )\u0011!9\u0005\"\u0014\u0011\t\r=G\u0011J\u0005\u0005\t\u0017\u001aINA\bQe>\u001cWm]:TKF,XM\\2f\u0011!\u0011\u0019'!\u0004A\u0002\t\u0015d\u0002BA!\t#JA!a\u0014\u0002\u0012\u0001"
)
public interface ProcessBuilderImpl {
   static void $init$(final ProcessBuilderImpl $this) {
   }

   public class DaemonBuilder extends AbstractBuilder {
      private final ProcessBuilder underlying;

      public final Process run(final ProcessIO io) {
         return this.underlying.run(io.daemonized());
      }

      // $FF: synthetic method
      public ProcessBuilder$ scala$sys$process$ProcessBuilderImpl$DaemonBuilder$$$outer() {
         return this.$outer;
      }

      public DaemonBuilder(final ProcessBuilder underlying) {
         this.underlying = underlying;
      }
   }

   public class Dummy extends AbstractBuilder {
      private final String toString;
      private final Function0 exitValue;

      public String toString() {
         return this.toString;
      }

      public Process run(final ProcessIO io) {
         return Process$.MODULE$.new DummyProcess(this.exitValue);
      }

      public boolean canPipeTo() {
         return true;
      }

      // $FF: synthetic method
      public ProcessBuilder$ scala$sys$process$ProcessBuilderImpl$Dummy$$$outer() {
         return this.$outer;
      }

      public Dummy(final String toString, final Function0 exitValue) {
         this.toString = toString;
         this.exitValue = exitValue;
      }
   }

   public class URLInput extends IStreamBuilder {
      // $FF: synthetic method
      public ProcessBuilder$ scala$sys$process$ProcessBuilderImpl$URLInput$$$outer() {
         return this.$outer;
      }

      public URLInput(final URL url) {
         super(new Serializable(url) {
            private static final long serialVersionUID = 0L;
            private final URL url$1;

            public final InputStream apply() {
               return this.url$1.openStream();
            }

            public {
               this.url$1 = url$1;
            }
         }, url.toString());
      }
   }

   public class FileInput extends IStreamBuilder {
      // $FF: synthetic method
      public ProcessBuilder$ scala$sys$process$ProcessBuilderImpl$FileInput$$$outer() {
         return this.$outer;
      }

      public FileInput(final File file) {
         super(new Serializable(file) {
            private static final long serialVersionUID = 0L;
            private final File file$1;

            public final FileInputStream apply() {
               return new FileInputStream(this.file$1);
            }

            public {
               this.file$1 = file$1;
            }
         }, file.getAbsolutePath());
      }
   }

   public class FileOutput extends OStreamBuilder {
      // $FF: synthetic method
      public ProcessBuilder$ scala$sys$process$ProcessBuilderImpl$FileOutput$$$outer() {
         return this.$outer;
      }

      public FileOutput(final File file, final boolean append) {
         super(new Serializable(file, append) {
            private static final long serialVersionUID = 0L;
            private final File file$2;
            private final boolean append$1;

            public final FileOutputStream apply() {
               return new FileOutputStream(this.file$2, this.append$1);
            }

            public {
               this.file$2 = file$2;
               this.append$1 = append$1;
            }
         }, file.getAbsolutePath());
      }
   }

   public class OStreamBuilder extends ThreadBuilder {
      public boolean hasExitValue() {
         return false;
      }

      // $FF: synthetic method
      public ProcessBuilder$ scala$sys$process$ProcessBuilderImpl$OStreamBuilder$$$outer() {
         return this.$outer;
      }

      public OStreamBuilder(final Function0 stream, final String label) {
         super(label, new Serializable(stream) {
            private static final long serialVersionUID = 0L;
            private final Function0 stream$1;

            public final void apply(final ProcessIO x$1) {
               x$1.writeInput().apply(BasicIO.Uncloseable$.MODULE$.protect((OutputStream)this.stream$1.apply()));
            }

            public {
               this.stream$1 = stream$1;
            }
         });
      }
   }

   public class IStreamBuilder extends ThreadBuilder {
      public boolean hasExitValue() {
         return false;
      }

      // $FF: synthetic method
      public ProcessBuilder$ scala$sys$process$ProcessBuilderImpl$IStreamBuilder$$$outer() {
         return this.$outer;
      }

      public IStreamBuilder(final Function0 stream, final String label) {
         super(label, new Serializable(stream) {
            private static final long serialVersionUID = 0L;
            private final Function0 stream$2;

            public final void apply(final ProcessIO x$2) {
               x$2.processOutput().apply(BasicIO.Uncloseable$.MODULE$.protect((InputStream)this.stream$2.apply()));
            }

            public {
               this.stream$2 = stream$2;
            }
         });
      }
   }

   public abstract class ThreadBuilder extends AbstractBuilder {
      private final String toString;
      private final Function1 runImpl;

      public String toString() {
         return this.toString;
      }

      public Process run(final ProcessIO io) {
         LinkedBlockingQueue success = new LinkedBlockingQueue(1);
         ProcessImpl.Spawn$ var10000 = Process$.MODULE$.Spawn();
         boolean var10002 = io.daemonizeThreads();
         JFunction0$mcV$sp apply_f = () -> this.go$1(io, success);
         boolean apply_daemon = var10002;
         String apply_prefix = "ThreadProcess";
         if (var10000 == null) {
            throw null;
         } else {
            Thread apply_thread = new Thread(apply_f) {
               private final Function0 f$1;

               public void run() {
                  this.f$1.apply$mcV$sp();
               }

               public {
                  this.f$1 = f$1;
               }
            };
            apply_thread.setName((new StringBuilder(7)).append(apply_prefix).append("-spawn-").append(apply_thread.getName()).toString());
            apply_thread.setDaemon(apply_daemon);
            apply_thread.start();
            Thread var11 = apply_thread;
            Object var8 = null;
            apply_f = null;
            Object var10 = null;
            Thread t = var11;
            return Process$.MODULE$.new ThreadProcess(t, success);
         }
      }

      // $FF: synthetic method
      public ProcessBuilder$ scala$sys$process$ProcessBuilderImpl$ThreadBuilder$$$outer() {
         return this.$outer;
      }

      private final void go$1(final ProcessIO io$1, final LinkedBlockingQueue success$1) {
         boolean ok = false;

         try {
            this.runImpl.apply(io$1);
            ok = true;
         } finally {
            success$1.put(ok);
         }

      }

      public ThreadBuilder(final String toString, final Function1 runImpl) {
         this.toString = toString;
         this.runImpl = runImpl;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class Simple extends AbstractBuilder {
      private final java.lang.ProcessBuilder p;

      public Process run(final ProcessIO io) {
         boolean inherit = io.writeInput() == BasicIO$.MODULE$.connectToStdIn();
         if (inherit) {
            this.p.redirectInput(Redirect.INHERIT);
         }

         java.lang.Process process = this.p.start();
         Thread var10000;
         if (!inherit && io.writeInput() != BasicIO$.MODULE$.connectNoOp()) {
            ProcessImpl.Spawn$ var28 = Process$.MODULE$.Spawn();
            JFunction0$mcV$sp apply_f = () -> io.writeInput().apply(process.getOutputStream());
            boolean apply_daemon = true;
            String apply_prefix = "Simple-input";
            if (var28 == null) {
               throw null;
            }

            Thread apply_thread = new Thread(apply_f) {
               private final Function0 f$1;

               public void run() {
                  this.f$1.apply$mcV$sp();
               }

               public {
                  this.f$1 = f$1;
               }
            };
            apply_thread.setName((new StringBuilder(7)).append(apply_prefix).append("-spawn-").append(apply_thread.getName()).toString());
            apply_thread.setDaemon(apply_daemon);
            apply_thread.start();
            var10000 = apply_thread;
            Object var19 = null;
            apply_f = null;
            Object var21 = null;
         } else {
            var10000 = null;
         }

         Thread inThread = var10000;
         ProcessImpl.Spawn$ var29 = Process$.MODULE$.Spawn();
         boolean var10002 = io.daemonizeThreads();
         JFunction0$mcV$sp apply_fx = () -> io.processOutput().apply(process.getInputStream());
         boolean apply_daemonx = var10002;
         String apply_prefixx = "Simple-output";
         if (var29 == null) {
            throw null;
         } else {
            Thread apply_thread = new Thread(apply_fx) {
               private final Function0 f$1;

               public void run() {
                  this.f$1.apply$mcV$sp();
               }

               public {
                  this.f$1 = f$1;
               }
            };
            apply_thread.setName((new StringBuilder(7)).append(apply_prefixx).append("-spawn-").append(apply_thread.getName()).toString());
            apply_thread.setDaemon(apply_daemonx);
            apply_thread.start();
            Thread var30 = apply_thread;
            Object var22 = null;
            apply_fx = null;
            Object var24 = null;
            Thread outThread = var30;
            Object var31;
            if (this.p.redirectErrorStream()) {
               var31 = Nil$.MODULE$;
            } else {
               var31 = new $colon$colon;
               ProcessImpl.Spawn$ var32 = Process$.MODULE$.Spawn();
               boolean var10004 = io.daemonizeThreads();
               JFunction0$mcV$sp apply_fxx = () -> io.processError().apply(process.getErrorStream());
               boolean apply_daemonxx = var10004;
               String apply_prefixxx = "Simple-error";
               if (var32 == null) {
                  throw null;
               }

               Thread apply_thread = new Thread(apply_fxx) {
                  private final Function0 f$1;

                  public void run() {
                     this.f$1.apply$mcV$sp();
                  }

                  public {
                     this.f$1 = f$1;
                  }
               };
               apply_thread.setName((new StringBuilder(7)).append(apply_prefixxx).append("-spawn-").append(apply_thread.getName()).toString());
               apply_thread.setDaemon(apply_daemonxx);
               apply_thread.start();
               Thread var33 = apply_thread;
               Object var25 = null;
               apply_fxx = null;
               Object var27 = null;
               var31.<init>(var33, Nil$.MODULE$);
            }

            List errorThread = (List)var31;
            return Process$.MODULE$.new SimpleProcess(process, inThread, errorThread.$colon$colon(outThread));
         }
      }

      public String toString() {
         return this.p.command().toString();
      }

      public boolean canPipeTo() {
         return true;
      }

      // $FF: synthetic method
      public ProcessBuilder$ scala$sys$process$ProcessBuilderImpl$Simple$$$outer() {
         return this.$outer;
      }

      public Simple(final java.lang.ProcessBuilder p) {
         this.p = p;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public abstract class AbstractBuilder implements ProcessBuilder {
      private final int defaultStreamCapacity;
      // $FF: synthetic field
      public final ProcessBuilder$ $outer;

      public ProcessBuilder $hash$less(final File f) {
         return ProcessBuilder.Sink.$hash$less$(this, (File)f);
      }

      public ProcessBuilder $hash$less(final URL f) {
         return ProcessBuilder.Sink.$hash$less$(this, (URL)f);
      }

      public ProcessBuilder $hash$less(final Function0 in) {
         return ProcessBuilder.Sink.$hash$less$(this, (Function0)in);
      }

      public ProcessBuilder $hash$less(final ProcessBuilder b) {
         return ProcessBuilder.Sink.$hash$less$(this, (ProcessBuilder)b);
      }

      public ProcessBuilder $hash$greater(final File f) {
         return ProcessBuilder.Source.$hash$greater$(this, (File)f);
      }

      public ProcessBuilder $hash$greater$greater(final File f) {
         return ProcessBuilder.Source.$hash$greater$greater$(this, f);
      }

      public ProcessBuilder $hash$greater(final Function0 out) {
         return ProcessBuilder.Source.$hash$greater$(this, (Function0)out);
      }

      public ProcessBuilder $hash$greater(final ProcessBuilder b) {
         return ProcessBuilder.Source.$hash$greater$(this, (ProcessBuilder)b);
      }

      public ProcessBuilder cat() {
         return ProcessBuilder.Source.cat$(this);
      }

      public AbstractBuilder toSource() {
         return this;
      }

      public AbstractBuilder toSink() {
         return this;
      }

      public ProcessBuilder $hash$bar(final ProcessBuilder other) {
         if (!other.canPipeTo()) {
            throw new IllegalArgumentException((new StringBuilder(20)).append("requirement failed: ").append("Piping to multiple processes is not supported.").toString());
         } else {
            return this.scala$sys$process$ProcessBuilderImpl$AbstractBuilder$$$outer().new PipedBuilder(this, other, false);
         }
      }

      public ProcessBuilder $hash$bar$bar(final ProcessBuilder other) {
         return this.scala$sys$process$ProcessBuilderImpl$AbstractBuilder$$$outer().new OrBuilder(this, other);
      }

      public ProcessBuilder $hash$amp$amp(final ProcessBuilder other) {
         return this.scala$sys$process$ProcessBuilderImpl$AbstractBuilder$$$outer().new AndBuilder(this, other);
      }

      public ProcessBuilder $hash$hash$hash(final ProcessBuilder other) {
         return this.scala$sys$process$ProcessBuilderImpl$AbstractBuilder$$$outer().new SequenceBuilder(this, other);
      }

      public Process run() {
         return this.run(false);
      }

      public Process run(final boolean connectInput) {
         return this.run(BasicIO$.MODULE$.standard(connectInput));
      }

      public Process run(final ProcessLogger log) {
         return this.run(log, false);
      }

      public Process run(final ProcessLogger log, final boolean connectInput) {
         return this.run(BasicIO$.MODULE$.apply(connectInput, log));
      }

      public String $bang$bang() {
         return this.slurp(None$.MODULE$, false);
      }

      public String $bang$bang(final ProcessLogger log) {
         return this.slurp(new Some(log), false);
      }

      public String $bang$bang$less() {
         return this.slurp(None$.MODULE$, true);
      }

      public String $bang$bang$less(final ProcessLogger log) {
         return this.slurp(new Some(log), true);
      }

      public LazyList lazyLines() {
         return this.lazyLines(false, true, None$.MODULE$, this.defaultStreamCapacity);
      }

      public LazyList lazyLines(final ProcessLogger log) {
         return this.lazyLines(false, true, new Some(log), this.defaultStreamCapacity);
      }

      public LazyList lazyLines_$bang() {
         return this.lazyLines(false, false, None$.MODULE$, this.defaultStreamCapacity);
      }

      public LazyList lazyLines_$bang(final ProcessLogger log) {
         return this.lazyLines(false, false, new Some(log), this.defaultStreamCapacity);
      }

      public LazyList lazyLines(final Integer capacity) {
         return this.lazyLines(false, true, None$.MODULE$, capacity);
      }

      public LazyList lazyLines(final ProcessLogger log, final Integer capacity) {
         return this.lazyLines(false, true, new Some(log), capacity);
      }

      public LazyList lazyLines_$bang(final Integer capacity) {
         return this.lazyLines(false, false, None$.MODULE$, capacity);
      }

      public LazyList lazyLines_$bang(final ProcessLogger log, final Integer capacity) {
         return this.lazyLines(false, false, new Some(log), capacity);
      }

      /** @deprecated */
      public Stream lineStream() {
         return this.lineStream(false, true, None$.MODULE$, this.defaultStreamCapacity);
      }

      /** @deprecated */
      public Stream lineStream(final ProcessLogger log) {
         return this.lineStream(false, true, new Some(log), this.defaultStreamCapacity);
      }

      /** @deprecated */
      public Stream lineStream_$bang() {
         return this.lineStream(false, false, None$.MODULE$, this.defaultStreamCapacity);
      }

      /** @deprecated */
      public Stream lineStream_$bang(final ProcessLogger log) {
         return this.lineStream(false, false, new Some(log), this.defaultStreamCapacity);
      }

      /** @deprecated */
      public Stream lineStream(final Integer capacity) {
         return this.lineStream(false, true, None$.MODULE$, capacity);
      }

      /** @deprecated */
      public Stream lineStream(final ProcessLogger log, final Integer capacity) {
         return this.lineStream(false, true, new Some(log), capacity);
      }

      /** @deprecated */
      public Stream lineStream_$bang(final Integer capacity) {
         return this.lineStream(false, false, None$.MODULE$, capacity);
      }

      /** @deprecated */
      public Stream lineStream_$bang(final ProcessLogger log, final Integer capacity) {
         return this.lineStream(false, false, new Some(log), capacity);
      }

      public int $bang() {
         return this.run(false).exitValue();
      }

      public int $bang(final ProcessIO io) {
         return this.run(io).exitValue();
      }

      public int $bang(final ProcessLogger log) {
         return this.runBuffered(log, false);
      }

      public int $bang$less() {
         return this.run(true).exitValue();
      }

      public int $bang$less(final ProcessLogger log) {
         return this.runBuffered(log, true);
      }

      public ProcessBuilder daemonized() {
         return this.scala$sys$process$ProcessBuilderImpl$AbstractBuilder$$$outer().new DaemonBuilder(this);
      }

      private String slurp(final Option log, final boolean withIn) {
         StringBuffer buffer = new StringBuffer();
         int code = this.$bang(BasicIO$.MODULE$.apply(withIn, (Appendable)buffer, log));
         if (code == 0) {
            return buffer.toString();
         } else {
            scala.sys.package$ var10000 = scala.sys.package$.MODULE$;
            String error_message = (new StringBuilder(20)).append("Nonzero exit value: ").append(code).toString();
            throw new RuntimeException(error_message);
         }
      }

      private LazyList lazyLines(final boolean withInput, final boolean nonZeroException, final Option log, final Integer capacity) {
         BasicIO.LazilyListed lazilyListed = BasicIO.LazilyListed$.MODULE$.apply(nonZeroException, capacity);
         Process process = this.run(BasicIO$.MODULE$.apply(withInput, lazilyListed.process(), log));
         Function1 done = lazilyListed.done();
         ProcessImpl.Spawn$ var10000 = Process$.MODULE$.Spawn();
         if (Process$.MODULE$.Spawn() == null) {
            throw null;
         } else {
            JFunction0$mcV$sp apply_f = () -> done.apply$mcVI$sp(liftedTree1$1(process));
            boolean apply_daemon = false;
            String apply_prefix = "LazyLines";
            if (var10000 == null) {
               throw null;
            } else {
               Thread apply_thread = new Thread(apply_f) {
                  private final Function0 f$1;

                  public void run() {
                     this.f$1.apply$mcV$sp();
                  }

                  public {
                     this.f$1 = f$1;
                  }
               };
               apply_thread.setName((new StringBuilder(7)).append(apply_prefix).append("-spawn-").append(apply_thread.getName()).toString());
               apply_thread.setDaemon(apply_daemon);
               apply_thread.start();
               Object var12 = null;
               apply_f = null;
               Object var14 = null;
               return lazilyListed.lazyList();
            }
         }
      }

      /** @deprecated */
      private Stream lineStream(final boolean withInput, final boolean nonZeroException, final Option log, final Integer capacity) {
         BasicIO.Streamed streamed = BasicIO.Streamed$.MODULE$.apply(nonZeroException, capacity);
         Process process = this.run(BasicIO$.MODULE$.apply(withInput, streamed.process(), log));
         ProcessImpl.Spawn$ var10000 = Process$.MODULE$.Spawn();
         if (Process$.MODULE$.Spawn() == null) {
            throw null;
         } else {
            JFunction0$mcV$sp apply_f = () -> streamed.done().apply$mcVI$sp(process.exitValue());
            boolean apply_daemon = false;
            String apply_prefix = "LineStream";
            if (var10000 == null) {
               throw null;
            } else {
               Thread apply_thread = new Thread(apply_f) {
                  private final Function0 f$1;

                  public void run() {
                     this.f$1.apply$mcV$sp();
                  }

                  public {
                     this.f$1 = f$1;
                  }
               };
               apply_thread.setName((new StringBuilder(7)).append(apply_prefix).append("-spawn-").append(apply_thread.getName()).toString());
               apply_thread.setDaemon(apply_daemon);
               apply_thread.start();
               Object var11 = null;
               apply_f = null;
               Object var13 = null;
               return (Stream)streamed.stream().apply();
            }
         }
      }

      private int runBuffered(final ProcessLogger log, final boolean connectInput) {
         return BoxesRunTime.unboxToInt(log.buffer(() -> this.run(log, connectInput).exitValue()));
      }

      public boolean canPipeTo() {
         return false;
      }

      public boolean hasExitValue() {
         return true;
      }

      // $FF: synthetic method
      public ProcessBuilder$ scala$sys$process$ProcessBuilderImpl$AbstractBuilder$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final String $anonfun$$hash$bar$1() {
         return "Piping to multiple processes is not supported.";
      }

      // $FF: synthetic method
      private static final int liftedTree1$1(final Process process$2) {
         try {
            return process$2.exitValue();
         } catch (Throwable var2) {
            if (var2 != null && !NonFatal$.MODULE$.unapply(var2).isEmpty()) {
               return -2;
            } else {
               throw var2;
            }
         }
      }

      public AbstractBuilder() {
         if (ProcessBuilderImpl.this == null) {
            throw null;
         } else {
            this.$outer = ProcessBuilderImpl.this;
            super();
            this.defaultStreamCapacity = 4096;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class URLImpl implements ProcessBuilder.URLBuilder {
      private final URL url;
      // $FF: synthetic field
      public final ProcessBuilder$ $outer;

      public ProcessBuilder $hash$greater(final File f) {
         return ProcessBuilder.Source.$hash$greater$(this, (File)f);
      }

      public ProcessBuilder $hash$greater$greater(final File f) {
         return ProcessBuilder.Source.$hash$greater$greater$(this, f);
      }

      public ProcessBuilder $hash$greater(final Function0 out) {
         return ProcessBuilder.Source.$hash$greater$(this, (Function0)out);
      }

      public ProcessBuilder $hash$greater(final ProcessBuilder b) {
         return ProcessBuilder.Source.$hash$greater$(this, (ProcessBuilder)b);
      }

      public ProcessBuilder cat() {
         return ProcessBuilder.Source.cat$(this);
      }

      public URLInput toSource() {
         return this.scala$sys$process$ProcessBuilderImpl$URLImpl$$$outer().new URLInput(this.url);
      }

      // $FF: synthetic method
      public ProcessBuilder$ scala$sys$process$ProcessBuilderImpl$URLImpl$$$outer() {
         return this.$outer;
      }

      public URLImpl(final URL url) {
         this.url = url;
         if (ProcessBuilderImpl.this == null) {
            throw null;
         } else {
            this.$outer = ProcessBuilderImpl.this;
            super();
         }
      }
   }

   public class FileImpl implements ProcessBuilder.FileBuilder {
      private final File base;
      // $FF: synthetic field
      public final ProcessBuilder$ $outer;

      public ProcessBuilder $hash$greater(final File f) {
         return ProcessBuilder.Source.$hash$greater$(this, (File)f);
      }

      public ProcessBuilder $hash$greater$greater(final File f) {
         return ProcessBuilder.Source.$hash$greater$greater$(this, f);
      }

      public ProcessBuilder $hash$greater(final Function0 out) {
         return ProcessBuilder.Source.$hash$greater$(this, (Function0)out);
      }

      public ProcessBuilder $hash$greater(final ProcessBuilder b) {
         return ProcessBuilder.Source.$hash$greater$(this, (ProcessBuilder)b);
      }

      public ProcessBuilder cat() {
         return ProcessBuilder.Source.cat$(this);
      }

      public ProcessBuilder $hash$less(final File f) {
         return ProcessBuilder.Sink.$hash$less$(this, (File)f);
      }

      public ProcessBuilder $hash$less(final URL f) {
         return ProcessBuilder.Sink.$hash$less$(this, (URL)f);
      }

      public ProcessBuilder $hash$less(final Function0 in) {
         return ProcessBuilder.Sink.$hash$less$(this, (Function0)in);
      }

      public ProcessBuilder $hash$less(final ProcessBuilder b) {
         return ProcessBuilder.Sink.$hash$less$(this, (ProcessBuilder)b);
      }

      public FileInput toSource() {
         return this.scala$sys$process$ProcessBuilderImpl$FileImpl$$$outer().new FileInput(this.base);
      }

      public FileOutput toSink() {
         return this.scala$sys$process$ProcessBuilderImpl$FileImpl$$$outer().new FileOutput(this.base, false);
      }

      public ProcessBuilder $hash$less$less(final File f) {
         return this.$hash$less$less((ProcessBuilder)(this.scala$sys$process$ProcessBuilderImpl$FileImpl$$$outer().new FileInput(f)));
      }

      public ProcessBuilder $hash$less$less(final URL u) {
         return this.$hash$less$less((ProcessBuilder)(this.scala$sys$process$ProcessBuilderImpl$FileImpl$$$outer().new URLInput(u)));
      }

      public ProcessBuilder $hash$less$less(final Function0 s) {
         return this.$hash$less$less((ProcessBuilder)(this.scala$sys$process$ProcessBuilderImpl$FileImpl$$$outer().new IStreamBuilder(s, "<input stream>")));
      }

      public ProcessBuilder $hash$less$less(final ProcessBuilder b) {
         return this.scala$sys$process$ProcessBuilderImpl$FileImpl$$$outer().new PipedBuilder(b, this.scala$sys$process$ProcessBuilderImpl$FileImpl$$$outer().new FileOutput(this.base, true), false);
      }

      // $FF: synthetic method
      public ProcessBuilder$ scala$sys$process$ProcessBuilderImpl$FileImpl$$$outer() {
         return this.$outer;
      }

      public FileImpl(final File base) {
         this.base = base;
         if (ProcessBuilderImpl.this == null) {
            throw null;
         } else {
            this.$outer = ProcessBuilderImpl.this;
            super();
         }
      }
   }

   public abstract class BasicBuilder extends AbstractBuilder {
      public void checkNotThis(final ProcessBuilder a) {
         boolean var10000;
         label17: {
            if (a != null) {
               if (a.equals(this)) {
                  var10000 = false;
                  break label17;
               }
            }

            var10000 = true;
         }

         if (!var10000) {
            throw new IllegalArgumentException((new StringBuilder(20)).append("requirement failed: ").append($anonfun$checkNotThis$1(a)).toString());
         }
      }

      public final Process run(final ProcessIO io) {
         ProcessImpl.BasicProcess p = this.createProcess(io);
         p.start();
         return p;
      }

      public abstract ProcessImpl.BasicProcess createProcess(final ProcessIO io);

      // $FF: synthetic method
      public ProcessBuilder$ scala$sys$process$ProcessBuilderImpl$BasicBuilder$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final String $anonfun$checkNotThis$1(final ProcessBuilder a$1) {
         return (new StringBuilder(42)).append("Compound process '").append(a$1).append("' cannot contain itself.").toString();
      }
   }

   public abstract class SequentialBuilder extends BasicBuilder {
      private final ProcessBuilder a;
      private final ProcessBuilder b;
      private final String operatorString;

      public String toString() {
         return (new StringBuilder(8)).append(" ( ").append(this.a).append(" ").append(this.operatorString).append(" ").append(this.b).append(" ) ").toString();
      }

      // $FF: synthetic method
      public ProcessBuilder$ scala$sys$process$ProcessBuilderImpl$SequentialBuilder$$$outer() {
         return this.$outer;
      }

      public SequentialBuilder(final ProcessBuilder a, final ProcessBuilder b, final String operatorString) {
         this.a = a;
         this.b = b;
         this.operatorString = operatorString;
         this.checkNotThis(a);
         this.checkNotThis(b);
      }
   }

   public class PipedBuilder extends SequentialBuilder {
      private final ProcessBuilder first;
      private final ProcessBuilder second;
      private final boolean toError;

      public ProcessImpl.PipedProcesses createProcess(final ProcessIO io) {
         return Process$.MODULE$.new PipedProcesses(this.first, this.second, io, this.toError);
      }

      // $FF: synthetic method
      public ProcessBuilder$ scala$sys$process$ProcessBuilderImpl$PipedBuilder$$$outer() {
         return this.$outer;
      }

      public PipedBuilder(final ProcessBuilder first, final ProcessBuilder second, final boolean toError) {
         super(first, second, toError ? "#|!" : "#|");
         this.first = first;
         this.second = second;
         this.toError = toError;
      }
   }

   public class AndBuilder extends SequentialBuilder {
      private final ProcessBuilder first;
      private final ProcessBuilder second;

      public ProcessImpl.AndProcess createProcess(final ProcessIO io) {
         return Process$.MODULE$.new AndProcess(this.first, this.second, io);
      }

      // $FF: synthetic method
      public ProcessBuilder$ scala$sys$process$ProcessBuilderImpl$AndBuilder$$$outer() {
         return this.$outer;
      }

      public AndBuilder(final ProcessBuilder first, final ProcessBuilder second) {
         super(first, second, "#&&");
         this.first = first;
         this.second = second;
      }
   }

   public class OrBuilder extends SequentialBuilder {
      private final ProcessBuilder first;
      private final ProcessBuilder second;

      public ProcessImpl.OrProcess createProcess(final ProcessIO io) {
         return Process$.MODULE$.new OrProcess(this.first, this.second, io);
      }

      // $FF: synthetic method
      public ProcessBuilder$ scala$sys$process$ProcessBuilderImpl$OrBuilder$$$outer() {
         return this.$outer;
      }

      public OrBuilder(final ProcessBuilder first, final ProcessBuilder second) {
         super(first, second, "#||");
         this.first = first;
         this.second = second;
      }
   }

   public class SequenceBuilder extends SequentialBuilder {
      private final ProcessBuilder first;
      private final ProcessBuilder second;

      public ProcessImpl.ProcessSequence createProcess(final ProcessIO io) {
         return Process$.MODULE$.new ProcessSequence(this.first, this.second, io);
      }

      // $FF: synthetic method
      public ProcessBuilder$ scala$sys$process$ProcessBuilderImpl$SequenceBuilder$$$outer() {
         return this.$outer;
      }

      public SequenceBuilder(final ProcessBuilder first, final ProcessBuilder second) {
         super(first, second, "###");
         this.first = first;
         this.second = second;
      }
   }
}
