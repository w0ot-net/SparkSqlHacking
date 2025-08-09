package breeze.collection.mutable;

import breeze.storage.ConfigurableDefault$;
import breeze.storage.Storage;
import breeze.storage.Zero;
import breeze.util.ArrayUtil$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\r%d\u0001\u0002\u001b6\u0005qB\u0001B \u0001\u0003\u0002\u0004%\ta \u0005\u000b\u0003\u001b\u0001!\u00111A\u0005\u0002\u0005=\u0001BCA\u000e\u0001\t\u0005\t\u0015)\u0003\u0002\u0002!Q\u0011Q\u0004\u0001\u0003\u0002\u0004%\t!a\b\t\u0015\u0005\r\u0002A!a\u0001\n\u0003\t)\u0003\u0003\u0006\u0002*\u0001\u0011\t\u0011)Q\u0005\u0003CAa\"a\u000b\u0001\t\u0003\u0005)Q!a\u0001\n\u0013\ti\u0003\u0003\b\u00020\u0001!\t\u0011!B\u0003\u0002\u0004%I!!\r\t\u0017\u0005U\u0002A!B\u0001B\u0003&\u0011q\u0001\u0005\u000b\u0003o\u0001!Q1A\u0005\u0002\u00055\u0002BCA\u001d\u0001\t\u0005\t\u0015!\u0003\u0002\b!Q\u00111\b\u0001\u0003\u0006\u0004%\t!!\u0010\t\u0013\u0005}\u0002A!A!\u0002\u0013A\u0005bBA!\u0001\u0011\u0005\u00111\t\u0005\b\u0003\u0003\u0002A\u0011AA*\u0011\u001d\t\t\u0005\u0001C\u0001\u0003WBq!! \u0001\t\u000b\ty\bC\u0004\u0002\u000e\u0002!\t!a$\t\u000f\u0005}\u0005\u0001\"\u0001\u0002\"\"9\u0011Q\u0015\u0001\u0005\u0002\u0005\u001d\u0006bBAY\u0001\u0011\u0005\u00111\u0017\u0005\b\u0003\u0003\u0004A\u0011AAb\u0011\u001d\tI\r\u0001C\u0001\u0003\u0017Dq!a<\u0001\t\u0003\t\t\u0010C\u0004\u0002~\u0002!\t%a@\t\u000f\tE\u0001\u0001\"\u0001\u0002.!9!1\u0003\u0001\u0005\u0006\tU\u0001b\u0002B\r\u0001\u0011\u0015!1\u0004\u0005\b\u0005?\u0001A\u0011\u0001B\u0011\u0011%\u0011)\u0003\u0001a\u0001\n\u0013\ti\u0003C\u0005\u0003(\u0001\u0001\r\u0011\"\u0003\u0003*!A!Q\u0006\u0001!B\u0013\t9\u0001C\u0004\u00030\u0001!)B!\r\t\u000f\tU\u0002\u0001\"\u0002\u00038!9!q\b\u0001\u0005\u0002\t\u0005\u0003b\u0002B#\u0001\u0011\u0005!q\t\u0005\b\u0005\u0013\u0002A\u0011\u0001B&\u0011\u001d\u0011i\u0005\u0001C\u0001\u0005\u001fBqAa\u0016\u0001\t\u0003\u0011I\u0006C\u0004\u0003`\u0001!\tAa\u0013\t\u000f\t\u0005\u0004\u0001\"\u0001\u0003d!9!q\u000e\u0001\u0005B\tE\u0004b\u0002B:\u0001\u0011\u0005#QO\u0004\b\u0005\u000b+\u0004\u0012\u0001BD\r\u0019!T\u0007#\u0001\u0003\n\"9\u0011\u0011I\u0017\u0005\u0002\t-\u0005bBA?[\u0011\u0005!Q\u0012\u0005\b\u0005\u007fkC\u0011\u0001Ba\u0011\u001d\u0011\t0\fC\u0001\u0005gDqaa\n.\t\u0003\u0019I\u0003C\u0005\u0004Z5\n\t\u0011\"\u0003\u0004\\\tY1\u000b]1sg\u0016\f%O]1z\u0015\t1t'A\u0004nkR\f'\r\\3\u000b\u0005aJ\u0014AC2pY2,7\r^5p]*\t!(\u0001\u0004ce\u0016,'0Z\u0002\u0001+\ti$jE\u0003\u0001}\u0011\u0003h\u000f\u0005\u0002@\u00056\t\u0001IC\u0001B\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0005I\u0001\u0004B]f\u0014VM\u001a\t\u0004\u000b\u001aCU\"A\u001b\n\u0005\u001d+$aD*qCJ\u001cX-\u0011:sCfd\u0015n[3\u0011\u0005%SE\u0002\u0001\u0003\n\u0017\u0002\u0001\u000b\u0011!AC\u00021\u0013\u0011AV\t\u0003\u001bB\u0003\"a\u0010(\n\u0005=\u0003%a\u0002(pi\"Lgn\u001a\t\u0003\u007fEK!A\u0015!\u0003\u0007\u0005s\u0017\u0010\u000b\u0004K)^\u000bgm\u001b\t\u0003\u007fUK!A\u0016!\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006GaK6L\u0017\b\u0003\u007feK!A\u0017!\u0002\r\u0011{WO\u00197fc\u0011!C\fY!\u000f\u0005u\u0003W\"\u00010\u000b\u0005}[\u0014A\u0002\u001fs_>$h(C\u0001Bc\u0015\u0019#mY3e\u001d\ty4-\u0003\u0002e\u0001\u0006\u0019\u0011J\u001c;2\t\u0011b\u0006-Q\u0019\u0006G\u001dD'.\u001b\b\u0003\u007f!L!!\u001b!\u0002\u000b\u0019cw.\u0019;2\t\u0011b\u0006-Q\u0019\u0006G1lwN\u001c\b\u0003\u007f5L!A\u001c!\u0002\t1{gnZ\u0019\u0005Iq\u0003\u0017\tE\u0002ri\"k\u0011A\u001d\u0006\u0003gf\nqa\u001d;pe\u0006<W-\u0003\u0002ve\n91\u000b^8sC\u001e,\u0007CA<}\u001b\u0005A(BA={\u0003\tIwNC\u0001|\u0003\u0011Q\u0017M^1\n\u0005uD(\u0001D*fe&\fG.\u001b>bE2,\u0017!B5oI\u0016DXCAA\u0001!\u0015y\u00141AA\u0004\u0013\r\t)\u0001\u0011\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0004\u007f\u0005%\u0011bAA\u0006\u0001\n\u0019\u0011J\u001c;\u0002\u0013%tG-\u001a=`I\u0015\fH\u0003BA\t\u0003/\u00012aPA\n\u0013\r\t)\u0002\u0011\u0002\u0005+:LG\u000fC\u0005\u0002\u001a\t\t\t\u00111\u0001\u0002\u0002\u0005\u0019\u0001\u0010J\u0019\u0002\r%tG-\u001a=!\u0003\u0011!\u0017\r^1\u0016\u0005\u0005\u0005\u0002\u0003B \u0002\u0004!\u000b\u0001\u0002Z1uC~#S-\u001d\u000b\u0005\u0003#\t9\u0003C\u0005\u0002\u001a\u0015\t\t\u00111\u0001\u0002\"\u0005)A-\u0019;bA\u0005Y#M]3fu\u0016$3m\u001c7mK\u000e$\u0018n\u001c8%[V$\u0018M\u00197fIM\u0003\u0018M]:f\u0003J\u0014\u0018-\u001f\u0013%kN,G-\u0006\u0002\u0002\b\u0005y#M]3fu\u0016$3m\u001c7mK\u000e$\u0018n\u001c8%[V$\u0018M\u00197fIM\u0003\u0018M]:f\u0003J\u0014\u0018-\u001f\u0013%kN,Gm\u0018\u0013fcR!\u0011\u0011CA\u001a\u0011%\tI\u0002CA\u0001\u0002\u0004\t9!\u0001\u0017ce\u0016,'0\u001a\u0013d_2dWm\u0019;j_:$S.\u001e;bE2,Ge\u00159beN,\u0017I\u001d:bs\u0012\"So]3eA\u0005!1/\u001b>f\u0003\u0015\u0019\u0018N_3!\u0003\u001d!WMZ1vYR,\u0012\u0001S\u0001\tI\u00164\u0017-\u001e7uA\u00051A(\u001b8jiz\"B\"!\u0012\u0002H\u0005%\u00131JA(\u0003#\u00022!\u0012\u0001I\u0011\u0019qh\u00021\u0001\u0002\u0002!9\u0011Q\u0004\bA\u0002\u0005\u0005\u0002bBA'\u001d\u0001\u0007\u0011qA\u0001\u0005kN,G\rC\u0004\u000289\u0001\r!a\u0002\t\r\u0005mb\u00021\u0001I)\u0019\t)&a\u001a\u0002jQ!\u0011QIA,\u0011\u001d\tIf\u0004a\u0002\u00037\nq!\\1o\u000b2,W\u000eE\u0003\u0002^\u0005\r\u0004*\u0004\u0002\u0002`)\u0019\u0011\u0011\r!\u0002\u000fI,g\r\\3di&!\u0011QMA0\u0005!\u0019E.Y:t)\u0006<\u0007bBA\u001c\u001f\u0001\u0007\u0011q\u0001\u0005\u0007\u0003wy\u0001\u0019\u0001%\u0015\t\u00055\u00141\u0010\u000b\u0007\u0003\u000b\ny'!\u001d\t\u000f\u0005e\u0003\u0003q\u0001\u0002\\!9\u00111\u000f\tA\u0004\u0005U\u0014\u0001\u0002>fe>\u0004B!]A<\u0011&\u0019\u0011\u0011\u0010:\u0003\ti+'o\u001c\u0005\b\u0003o\u0001\u0002\u0019AA\u0004\u0003\u0015\t\u0007\u000f\u001d7z)\rA\u0015\u0011\u0011\u0005\b\u0003\u0007\u000b\u0002\u0019AA\u0004\u0003\u0005I\u0007fA\t\u0002\bB\u0019q(!#\n\u0007\u0005-\u0005I\u0001\u0004j]2Lg.Z\u0001\u000fm\u0006dW/Z:Ji\u0016\u0014\u0018\r^8s+\t\t\t\nE\u0003\u0002\u0014\u0006e\u0005JD\u0002]\u0003+K1!a&A\u0003\u001d\u0001\u0018mY6bO\u0016LA!a'\u0002\u001e\nA\u0011\n^3sCR|'OC\u0002\u0002\u0018\u0002\u000bAb[3zg&#XM]1u_J,\"!a)\u0011\r\u0005M\u0015\u0011TA\u0004\u0003\r9W\r\u001e\u000b\u0005\u0003S\u000by\u000b\u0005\u0003@\u0003WC\u0015bAAW\u0001\n1q\n\u001d;j_:Dq!a!\u0015\u0001\u0004\t9!A\u0005hKR|%/\u00127tKR)\u0001*!.\u00028\"9\u00111Q\u000bA\u0002\u0005\u001d\u0001\u0002CA]+\u0011\u0005\r!a/\u0002\u000bY\fG.^3\u0011\t}\ni\fS\u0005\u0004\u0003\u007f\u0003%\u0001\u0003\u001fcs:\fW.\u001a \u0002\u001f\u001d,Go\u0014:FYN,W\u000b\u001d3bi\u0016$R\u0001SAc\u0003\u000fDq!a!\u0017\u0001\u0004\t9\u0001\u0003\u0005\u0002:Z!\t\u0019AA^\u0003\ri\u0017\r]\u000b\u0005\u0003\u001b\f)\u000e\u0006\u0003\u0002P\u0006\u0015HCBAi\u00033\fy\u000e\u0005\u0003F\u0001\u0005M\u0007cA%\u0002V\u00121\u0011q[\fC\u00021\u0013\u0011A\u0011\u0005\n\u00037<\u0012\u0011!a\u0002\u0003;\f!\"\u001a<jI\u0016t7-\u001a\u00132!\u0019\ti&a\u0019\u0002T\"I\u0011\u0011]\f\u0002\u0002\u0003\u000f\u00111]\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004#B9\u0002x\u0005M\u0007bBAt/\u0001\u0007\u0011\u0011^\u0001\u0002MB1q(a;I\u0003'L1!!<A\u0005%1UO\\2uS>t\u0017'\u0001\u0004gS2$XM\u001d\u000b\u0005\u0003\u000b\n\u0019\u0010C\u0004\u0002hb\u0001\r!!>\u0011\r}\nY\u000fSA|!\ry\u0014\u0011`\u0005\u0004\u0003w\u0004%a\u0002\"p_2,\u0017M\\\u0001\ti>\u001cFO]5oOR\u0011!\u0011\u0001\t\u0005\u0005\u0007\u0011YA\u0004\u0003\u0003\u0006\t\u001d\u0001CA/A\u0013\r\u0011I\u0001Q\u0001\u0007!J,G-\u001a4\n\t\t5!q\u0002\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\t%\u0001)\u0001\u0006bGRLg/Z*ju\u0016\fqA^1mk\u0016\fE\u000fF\u0002I\u0005/Aq!a!\u001c\u0001\u0004\t9!A\u0004j]\u0012,\u00070\u0011;\u0015\t\u0005\u001d!Q\u0004\u0005\b\u0003\u0007c\u0002\u0019AA\u0004\u0003!\u0019wN\u001c;bS:\u001cH\u0003BA|\u0005GAq!a!\u001e\u0001\u0004\t9!A\bmCN$(+\u001a;ve:,G\rU8t\u0003Ma\u0017m\u001d;SKR,(O\\3e!>\u001cx\fJ3r)\u0011\t\tBa\u000b\t\u0013\u0005eq$!AA\u0002\u0005\u001d\u0011\u0001\u00057bgR\u0014V\r^;s]\u0016$\u0007k\\:!\u0003)1\u0017N\u001c3PM\u001a\u001cX\r\u001e\u000b\u0005\u0003\u000f\u0011\u0019\u0004C\u0004\u0002\u0004\u0006\u0002\r!a\u0002\u0002\rU\u0004H-\u0019;f)\u0019\t\tB!\u000f\u0003<!9\u00111\u0011\u0012A\u0002\u0005\u001d\u0001BBA]E\u0001\u0007\u0001\nK\u0002#\u0003\u000f\u000b\u0001\"[:BGRLg/\u001a\u000b\u0005\u0003o\u0014\u0019\u0005C\u0004\u0002\u0004\u000e\u0002\r!a\u0002\u00023\u0005dGNV5tSR\f'\r\\3J]\u0012L7-Z:BGRLg/Z\u000b\u0003\u0003o\fqaY8na\u0006\u001cG\u000f\u0006\u0002\u0002\u0012\u0005\u0019Qo]3\u0015\u0011\u0005E!\u0011\u000bB*\u0005+BaA \u0014A\u0002\u0005\u0005\u0001bBA\u000fM\u0001\u0007\u0011\u0011\u0005\u0005\b\u0003\u001b2\u0003\u0019AA\u0004\u0003\u001d\u0011Xm]3sm\u0016$B!!\u0005\u0003\\!9!QL\u0014A\u0002\u0005\u001d\u0011a\u00018ou\u0006a\u0011/^5dW\u000e{W\u000e]1di\u0006Y1m\u001c8dCR,g.\u0019;f)\u0011\u0011)Ga\u001b\u0015\t\u0005\u0015#q\r\u0005\b\u0005SJ\u00039AA.\u0003\ri\u0017M\u001c\u0005\b\u0005[J\u0003\u0019AA#\u0003\u0011!\b.\u0019;\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a\u0002\u0002\r\u0015\fX/\u00197t)\u0011\t9Pa\u001e\t\r\te4\u00061\u0001Q\u0003\u0005y\u0007f\u0002\u0001\u0003~\u0005e&1\u0011\t\u0004\u007f\t}\u0014b\u0001BA\u0001\n\u00012+\u001a:jC24VM]:j_:,\u0016\n\u0012\u0010\u0002\u0003\u0005Y1\u000b]1sg\u0016\f%O]1z!\t)UfE\u0002.}Y$\"Aa\"\u0016\t\t=%q\u0013\u000b\u0005\u0005#\u0013)\f\u0006\u0004\u0003\u0014\n%&q\u0016\t\u0005\u000b\u0002\u0011)\nE\u0002J\u0005/#!B!'0A\u0003\u0005\tQ1\u0001M\u0005\u0005!\u0006&\u0003BL)\nu%\u0011\u0015BSc\u0019\u0019#m\u0019BPIF\"A\u0005\u00181Bc\u0019\u0019s\r\u001bBRSF\"A\u0005\u00181Bc\u0019\u0019\u0003,\u0017BT5F\"A\u0005\u00181B\u0011%\u0011YkLA\u0001\u0002\b\u0011i+\u0001\u0006fm&$WM\\2fIM\u0002b!!\u0018\u0002d\tU\u0005\"\u0003BY_\u0005\u0005\t9\u0001BZ\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u0006c\u0006]$Q\u0013\u0005\b\u0005o{\u0003\u0019\u0001B]\u0003\u00191\u0018\r\\;fgB)qHa/\u0003\u0016&\u0019!Q\u0018!\u0003\u0015q\u0012X\r]3bi\u0016$g(\u0001\u0003gS2dW\u0003\u0002Bb\u0005\u001b$BA!2\u0003nR!!q\u0019Bu)\u0019\u0011IM!8\u0003dB!Q\t\u0001Bf!\rI%Q\u001a\u0003\u000b\u00053\u0003\u0004\u0015!A\u0001\u0006\u0004a\u0005&\u0003Bg)\nE'Q\u001bBmc\u0019\u0019#m\u0019BjIF\"A\u0005\u00181Bc\u0019\u0019s\r\u001bBlSF\"A\u0005\u00181Bc\u0019\u0019\u0003,\u0017Bn5F\"A\u0005\u00181B\u0011%\u0011y\u000eMA\u0001\u0002\b\u0011\t/\u0001\u0006fm&$WM\\2fIU\u0002b!!\u0018\u0002d\t-\u0007\"\u0003Bsa\u0005\u0005\t9\u0001Bt\u0003))g/\u001b3f]\u000e,GE\u000e\t\u0006c\u0006]$1\u001a\u0005\t\u0003s\u0003D\u00111\u0001\u0003lB)q(!0\u0003L\"9!q\u001e\u0019A\u0002\u0005\u001d\u0011A\u00027f]\u001e$\b.\u0001\u0004de\u0016\fG/Z\u000b\u0005\u0005k\u0014y\u0010\u0006\u0003\u0003x\u000e\u0015B\u0003\u0002B}\u00077!bAa?\u0004\u0010\rU\u0001\u0003B#\u0001\u0005{\u00042!\u0013B\u0000\t)\u0011I*\rQ\u0001\u0002\u0003\u0015\r\u0001\u0014\u0015\n\u0005\u007f$61AB\u0004\u0007\u0017\tda\t2d\u0007\u000b!\u0017\u0007\u0002\u0013]A\u0006\u000bdaI4i\u0007\u0013I\u0017\u0007\u0002\u0013]A\u0006\u000bda\t-Z\u0007\u001bQ\u0016\u0007\u0002\u0013]A\u0006C\u0011b!\u00052\u0003\u0003\u0005\u001daa\u0005\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$s\u0007\u0005\u0004\u0002^\u0005\r$Q \u0005\n\u0007/\t\u0014\u0011!a\u0002\u00073\t!\"\u001a<jI\u0016t7-\u001a\u00139!\u0015\t\u0018q\u000fB\u007f\u0011\u001d\u00119,\ra\u0001\u0007;\u0001Ra\u0010B^\u0007?\u0001raPB\u0011\u0003\u000f\u0011i0C\u0002\u0004$\u0001\u0013a\u0001V;qY\u0016\u0014\u0004b\u0002Bxc\u0001\u0007\u0011qA\u0001\ti\u0006\u0014W\u000f\\1uKV!11FB\u001b)\u0011\u0019ica\u0016\u0015\t\r=2\u0011\u000b\u000b\u0007\u0007c\u0019)ea\u0013\u0011\t\u0015\u000311\u0007\t\u0004\u0013\u000eUBA\u0003BMe\u0001\u0006\t\u0011!b\u0001\u0019\"J1Q\u0007+\u0004:\ru2\u0011I\u0019\u0007G\t\u001c71\b32\t\u0011b\u0006-Q\u0019\u0007G\u001dD7qH52\t\u0011b\u0006-Q\u0019\u0007GaK61\t.2\t\u0011b\u0006-\u0011\u0005\n\u0007\u000f\u0012\u0014\u0011!a\u0002\u0007\u0013\n!\"\u001a<jI\u0016t7-\u001a\u0013:!\u0019\ti&a\u0019\u00044!I1Q\n\u001a\u0002\u0002\u0003\u000f1qJ\u0001\fKZLG-\u001a8dK\u0012\n\u0004\u0007E\u0003r\u0003o\u001a\u0019\u0004C\u0004\u0004TI\u0002\ra!\u0016\u0002\u0005\u0019t\u0007cB \u0002l\u0006\u001d11\u0007\u0005\b\u0005_\u0014\u0004\u0019AA\u0004\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0019i\u0006\u0005\u0003\u0004`\r\u0015TBAB1\u0015\r\u0019\u0019G_\u0001\u0005Y\u0006tw-\u0003\u0003\u0004h\r\u0005$AB(cU\u0016\u001cG\u000f"
)
public class SparseArray implements SparseArrayLike, Storage, Serializable {
   private static final long serialVersionUID = 1L;
   private int[] index;
   public Object data;
   private int breeze$collection$mutable$SparseArray$$used;
   private final int size;
   public final Object default;
   private int lastReturnedPos;

   public static SparseArray tabulate(final int length, final Function1 fn, final ClassTag evidence$9, final Zero evidence$10) {
      return SparseArray$.MODULE$.tabulate(length, fn, evidence$9, evidence$10);
   }

   public static SparseArray create(final int length, final Seq values, final ClassTag evidence$7, final Zero evidence$8) {
      return SparseArray$.MODULE$.create(length, values, evidence$7, evidence$8);
   }

   public static SparseArray fill(final int length, final Function0 value, final ClassTag evidence$5, final Zero evidence$6) {
      return SparseArray$.MODULE$.fill(length, value, evidence$5, evidence$6);
   }

   public int iterableSize() {
      return Storage.iterableSize$(this);
   }

   public int length() {
      return SparseArrayLike.length$(this);
   }

   public void foreach(final Function1 f) {
      SparseArrayLike.foreach$(this, f);
   }

   public Iterator iterator() {
      return SparseArrayLike.iterator$(this);
   }

   public Object toArray(final ClassTag evidence$1) {
      return SparseArrayLike.toArray$(this, evidence$1);
   }

   public List toList() {
      return SparseArrayLike.toList$(this);
   }

   public List toIndexedSeq() {
      return SparseArrayLike.toIndexedSeq$(this);
   }

   public Map toMap() {
      return SparseArrayLike.toMap$(this);
   }

   public int[] index() {
      return this.index;
   }

   public void index_$eq(final int[] x$1) {
      this.index = x$1;
   }

   public Object data() {
      return this.data;
   }

   public void data_$eq(final Object x$1) {
      this.data = x$1;
   }

   public int breeze$collection$mutable$SparseArray$$used() {
      return this.breeze$collection$mutable$SparseArray$$used;
   }

   public void breeze$collection$mutable$SparseArray$$used_$eq(final int x$1) {
      this.breeze$collection$mutable$SparseArray$$used = x$1;
   }

   public int size() {
      return this.size;
   }

   public Object default() {
      return this.default;
   }

   public Object apply(final int i) {
      int offset = this.findOffset(i);
      return offset >= 0 ? .MODULE$.array_apply(this.data(), offset) : this.default();
   }

   public Iterator valuesIterator() {
      return scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.genericArrayOps(this.data())).take(this.breeze$collection$mutable$SparseArray$$used());
   }

   public Iterator keysIterator() {
      return scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.intArrayOps(this.index())).take(this.breeze$collection$mutable$SparseArray$$used());
   }

   public Option get(final int i) {
      int offset = this.findOffset(i);
      return (Option)(offset >= 0 ? new Some(.MODULE$.array_apply(this.data(), offset)) : scala.None..MODULE$);
   }

   public Object getOrElse(final int i, final Function0 value) {
      int offset = this.findOffset(i);
      return offset >= 0 ? .MODULE$.array_apply(this.data(), offset) : value.apply();
   }

   public Object getOrElseUpdate(final int i, final Function0 value) {
      int offset = this.findOffset(i);
      Object var10000;
      if (offset >= 0) {
         var10000 = .MODULE$.array_apply(this.data(), offset);
      } else {
         Object v = value.apply();
         this.update(i, v);
         var10000 = v;
      }

      return var10000;
   }

   public SparseArray map(final Function1 f, final ClassTag evidence$1, final Zero evidence$2) {
      Object newZero = ((Zero)scala.Predef..MODULE$.implicitly(evidence$2)).zero();
      SparseArray var10000;
      if (this.breeze$collection$mutable$SparseArray$$used() <= this.length() && BoxesRunTime.equals(f.apply(this.default()), newZero)) {
         int[] newIndex = new int[this.breeze$collection$mutable$SparseArray$$used()];
         Object newData = evidence$1.newArray(this.breeze$collection$mutable$SparseArray$$used());
         int i = 0;

         int o;
         for(o = 0; i < this.breeze$collection$mutable$SparseArray$$used(); ++i) {
            newIndex[o] = this.index()[i];
            Object newValue = f.apply(.MODULE$.array_apply(this.data(), i));
            if (!BoxesRunTime.equals(newValue, newZero)) {
               .MODULE$.array_update(newData, o, newValue);
               ++o;
            }
         }

         var10000 = new SparseArray(newIndex, newData, o, this.length(), newZero);
      } else {
         Object newDefault = f.apply(this.default());
         int[] newIndex = new int[this.length()];
         Object newData = evidence$1.newArray(this.length());
         int i = 0;

         int o;
         for(o = 0; i < this.breeze$collection$mutable$SparseArray$$used(); ++i) {
            while(o < this.index()[i]) {
               newIndex[o] = o;
               .MODULE$.array_update(newData, o, newDefault);
               ++o;
            }

            newIndex[o] = o;
            .MODULE$.array_update(newData, o, f.apply(.MODULE$.array_apply(this.data(), i)));
            ++o;
         }

         while(o < this.length()) {
            newIndex[o] = o;
            .MODULE$.array_update(newData, o, newDefault);
            ++o;
         }

         SparseArray rv = new SparseArray(newIndex, newData, o, this.length(), newDefault);
         rv.compact();
         var10000 = rv;
      }

      return var10000;
   }

   public SparseArray filter(final Function1 f) {
      int[] newIndex = new int[this.breeze$collection$mutable$SparseArray$$used()];
      Object newData = ArrayUtil$.MODULE$.copyOf(this.data(), this.breeze$collection$mutable$SparseArray$$used());
      int i = 0;

      int o;
      for(o = 0; i < this.breeze$collection$mutable$SparseArray$$used(); ++i) {
         if (BoxesRunTime.unboxToBoolean(f.apply(.MODULE$.array_apply(this.data(), i)))) {
            newIndex[o] = this.index()[i] - (i - o);
            .MODULE$.array_update(newData, o, .MODULE$.array_apply(this.data(), i));
            ++o;
         }
      }

      SparseArray var10000;
      if (BoxesRunTime.unboxToBoolean(f.apply(this.default()))) {
         int newLength = this.length() - (i - o);
         var10000 = new SparseArray(newIndex, newData, o, newLength, this.default());
      } else {
         var10000 = new SparseArray(scala.Array..MODULE$.range(0, o), scala.collection.ArrayOps..MODULE$.take$extension(scala.Predef..MODULE$.genericArrayOps(newData), o), o, o, this.default());
      }

      return var10000;
   }

   public String toString() {
      return this.iterator().mkString("SparseArray(", ", ", ")");
   }

   public int activeSize() {
      return this.breeze$collection$mutable$SparseArray$$used();
   }

   public Object valueAt(final int i) {
      return .MODULE$.array_apply(this.data(), i);
   }

   public final int indexAt(final int i) {
      return this.index()[i];
   }

   public boolean contains(final int i) {
      return this.findOffset(i) >= 0;
   }

   private int lastReturnedPos() {
      return this.lastReturnedPos;
   }

   private void lastReturnedPos_$eq(final int x$1) {
      this.lastReturnedPos = x$1;
   }

   public final int findOffset(final int i) {
      if (i >= 0 && i < this.size()) {
         int var10000;
         if (this.breeze$collection$mutable$SparseArray$$used() == 0) {
            var10000 = -1;
         } else {
            int[] index = this.index();
            if (i > index[this.breeze$collection$mutable$SparseArray$$used() - 1]) {
               var10000 = ~this.breeze$collection$mutable$SparseArray$$used();
            } else {
               int begin = 0;
               int end = this.breeze$collection$mutable$SparseArray$$used() - 1;
               if (end > i) {
                  end = i;
               }

               boolean found = false;
               int mid = end + begin >> 1;
               int l = this.lastReturnedPos();
               if (l >= 0 && l < end) {
                  mid = l;
               }

               int mi = index[mid];
               if (mi == i) {
                  found = true;
               } else if (mi > i) {
                  end = mid - 1;
               } else {
                  begin = mid + 1;
               }

               if (!found && mid < end) {
                  int mi = index[mid + 1];
                  if (mi == i) {
                     ++mid;
                     found = true;
                  } else if (mi > i) {
                     end = mid;
                  } else {
                     begin = mid + 2;
                  }
               }

               if (!found) {
                  mid = end + begin >> 1;
               }

               while(!found && begin <= end) {
                  if (index[mid] < i) {
                     begin = mid + 1;
                     mid = end + begin >> 1;
                  } else if (index[mid] > i) {
                     end = mid - 1;
                     mid = end + begin >> 1;
                  } else {
                     found = true;
                  }
               }

               int result = !found && mid >= 0 ? (i <= index[mid] ? ~mid : ~(mid + 1)) : mid;
               this.lastReturnedPos_$eq(result);
               var10000 = result;
            }
         }

         return var10000;
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(25)).append("Index ").append(i).append(" out of bounds [0,").append(this.breeze$collection$mutable$SparseArray$$used()).append(")").toString());
      }
   }

   public void update(final int i, final Object value) {
      int offset = this.findOffset(i);
      if (offset >= 0) {
         .MODULE$.array_update(this.data(), offset, value);
      } else if (!BoxesRunTime.equals(value, this.default())) {
         int insertPos = ~offset;
         this.breeze$collection$mutable$SparseArray$$used_$eq(this.breeze$collection$mutable$SparseArray$$used() + 1);
         if (this.breeze$collection$mutable$SparseArray$$used() > .MODULE$.array_length(this.data())) {
            int newLength = .MODULE$.array_length(this.data()) == 0 ? 4 : (.MODULE$.array_length(this.data()) < 1024 ? .MODULE$.array_length(this.data()) * 2 : (.MODULE$.array_length(this.data()) < 2048 ? .MODULE$.array_length(this.data()) + 1024 : (.MODULE$.array_length(this.data()) < 4096 ? .MODULE$.array_length(this.data()) + 2048 : (.MODULE$.array_length(this.data()) < 8192 ? .MODULE$.array_length(this.data()) + 4096 : (.MODULE$.array_length(this.data()) < 16384 ? .MODULE$.array_length(this.data()) + 8192 : .MODULE$.array_length(this.data()) + 16384)))));
            int[] newIndex = Arrays.copyOf(this.index(), newLength);
            Object newData = ArrayUtil$.MODULE$.copyOf(this.data(), newLength);
            System.arraycopy(this.index(), insertPos, newIndex, insertPos + 1, this.breeze$collection$mutable$SparseArray$$used() - insertPos - 1);
            System.arraycopy(this.data(), insertPos, newData, insertPos + 1, this.breeze$collection$mutable$SparseArray$$used() - insertPos - 1);
            this.index_$eq(newIndex);
            this.data_$eq(newData);
         } else if (this.breeze$collection$mutable$SparseArray$$used() - insertPos > 1) {
            System.arraycopy(this.index(), insertPos, this.index(), insertPos + 1, this.breeze$collection$mutable$SparseArray$$used() - insertPos - 1);
            System.arraycopy(this.data(), insertPos, this.data(), insertPos + 1, this.breeze$collection$mutable$SparseArray$$used() - insertPos - 1);
         }

         this.index()[insertPos] = i;
         .MODULE$.array_update(this.data(), insertPos, value);
      }

   }

   public boolean isActive(final int i) {
      return true;
   }

   public boolean allVisitableIndicesActive() {
      return true;
   }

   public void compact() {
      int _nz = 0;

      for(int i = 0; i < this.breeze$collection$mutable$SparseArray$$used(); ++i) {
         if (!BoxesRunTime.equals(.MODULE$.array_apply(this.data(), i), this.default())) {
            ++_nz;
         }
      }

      Object newData = scala.reflect.ClassTag..MODULE$.apply(this.data().getClass().getComponentType()).newArray(_nz);
      int[] newIndex = new int[_nz];
      int i = 0;

      for(int o = 0; i < this.breeze$collection$mutable$SparseArray$$used(); ++i) {
         if (!BoxesRunTime.equals(.MODULE$.array_apply(this.data(), i), this.default())) {
            .MODULE$.array_update(newData, o, .MODULE$.array_apply(this.data(), i));
            newIndex[o] = this.index()[i];
            ++o;
         }
      }

      this.data_$eq(newData);
      this.index_$eq(newIndex);
      this.breeze$collection$mutable$SparseArray$$used_$eq(_nz);
   }

   public void use(final int[] index, final Object data, final int used) {
      this.index_$eq(index);
      this.data_$eq(data);
      this.breeze$collection$mutable$SparseArray$$used_$eq(used);
   }

   public void reserve(final int nnz) {
      if (nnz >= this.breeze$collection$mutable$SparseArray$$used() && nnz != this.index().length) {
         this.index_$eq(Arrays.copyOf(this.index(), nnz));
         this.data_$eq(ArrayUtil$.MODULE$.copyOf(this.data(), nnz));
      }

   }

   public void quickCompact() {
      this.reserve(this.breeze$collection$mutable$SparseArray$$used());
   }

   public SparseArray concatenate(final SparseArray that, final ClassTag man) {
      Object left$macro$1 = this.default();
      Object right$macro$2 = that.default();
      if (!BoxesRunTime.equals(left$macro$1, right$macro$2)) {
         throw new IllegalArgumentException((new StringBuilder(87)).append("requirement failed: default values should be equal: ").append("this.default == that.default (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         return new SparseArray((int[])scala.collection.ArrayOps..MODULE$.toArray$extension(scala.Predef..MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.slice$extension(scala.Predef..MODULE$.intArrayOps(this.index()), 0, this.breeze$collection$mutable$SparseArray$$used())), scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.slice$extension(scala.Predef..MODULE$.intArrayOps(that.index()), 0, that.breeze$collection$mutable$SparseArray$$used())), (JFunction1.mcII.sp)(x$1) -> x$1 + this.size(), scala.reflect.ClassTag..MODULE$.Int()), scala.reflect.ClassTag..MODULE$.Int())), scala.reflect.ClassTag..MODULE$.Int()), scala.collection.ArrayOps..MODULE$.toArray$extension(scala.Predef..MODULE$.genericArrayOps(scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.genericArrayOps(scala.collection.ArrayOps..MODULE$.slice$extension(scala.Predef..MODULE$.genericArrayOps(this.data()), 0, this.breeze$collection$mutable$SparseArray$$used())), scala.collection.ArrayOps..MODULE$.slice$extension(scala.Predef..MODULE$.genericArrayOps(that.data()), 0, that.breeze$collection$mutable$SparseArray$$used()), man)), man), this.breeze$collection$mutable$SparseArray$$used() + that.breeze$collection$mutable$SparseArray$$used(), this.size() + that.size(), this.default());
      }
   }

   public int hashCode() {
      return ArrayUtil$.MODULE$.zeroSkippingHashCode(this.data(), 0, 1, this.breeze$collection$mutable$SparseArray$$used());
   }

   public boolean equals(final Object o) {
      boolean var2;
      if (o instanceof SparseArray) {
         SparseArray var4 = (SparseArray)o;
         boolean var10000;
         if (var4.length() != this.length()) {
            var10000 = false;
         } else if (!BoxesRunTime.equals(var4.default(), this.default())) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = this.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               if (!BoxesRunTime.equals(var4.apply(index$macro$2), this.apply(index$macro$2))) {
                  return false;
               }
            }

            var10000 = true;
         } else if (var4.breeze$collection$mutable$SparseArray$$used() < this.breeze$collection$mutable$SparseArray$$used()) {
            label68: {
               label67: {
                  if (var4 == null) {
                     if (this == null) {
                        break label67;
                     }
                  } else if (var4.equals(this)) {
                     break label67;
                  }

                  var10000 = false;
                  break label68;
               }

               var10000 = true;
            }
         } else {
            int off = 0;
            int zoff = 0;
            int size = this.breeze$collection$mutable$SparseArray$$used();
            int zsize = var4.breeze$collection$mutable$SparseArray$$used();

            while(off < size && zoff < zsize) {
               if (this.indexAt(off) < var4.indexAt(zoff)) {
                  if (!BoxesRunTime.equals(this.valueAt(off), this.default())) {
                     return false;
                  }

                  ++off;
               } else if (var4.indexAt(zoff) < this.indexAt(off)) {
                  if (!BoxesRunTime.equals(var4.valueAt(zoff), this.default())) {
                     return false;
                  }

                  ++zoff;
               } else {
                  if (!BoxesRunTime.equals(var4.valueAt(zoff), this.valueAt(off))) {
                     return false;
                  }

                  ++off;
                  ++zoff;
               }
            }

            var10000 = true;
         }

         var2 = var10000;
      } else {
         var2 = false;
      }

      return var2;
   }

   public double[] data$mcD$sp() {
      return (double[])this.data();
   }

   public float[] data$mcF$sp() {
      return (float[])this.data();
   }

   public int[] data$mcI$sp() {
      return (int[])this.data();
   }

   public long[] data$mcJ$sp() {
      return (long[])this.data();
   }

   public void data$mcD$sp_$eq(final double[] x$1) {
      this.data_$eq(x$1);
   }

   public void data$mcF$sp_$eq(final float[] x$1) {
      this.data_$eq(x$1);
   }

   public void data$mcI$sp_$eq(final int[] x$1) {
      this.data_$eq(x$1);
   }

   public void data$mcJ$sp_$eq(final long[] x$1) {
      this.data_$eq(x$1);
   }

   public double default$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.default());
   }

   public float default$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.default());
   }

   public int default$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.default());
   }

   public long default$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.default());
   }

   public double apply$mcD$sp(final int i) {
      return BoxesRunTime.unboxToDouble(this.apply(i));
   }

   public float apply$mcF$sp(final int i) {
      return BoxesRunTime.unboxToFloat(this.apply(i));
   }

   public int apply$mcI$sp(final int i) {
      return BoxesRunTime.unboxToInt(this.apply(i));
   }

   public long apply$mcJ$sp(final int i) {
      return BoxesRunTime.unboxToLong(this.apply(i));
   }

   public double getOrElse$mcD$sp(final int i, final Function0 value) {
      return BoxesRunTime.unboxToDouble(this.getOrElse(i, value));
   }

   public float getOrElse$mcF$sp(final int i, final Function0 value) {
      return BoxesRunTime.unboxToFloat(this.getOrElse(i, value));
   }

   public int getOrElse$mcI$sp(final int i, final Function0 value) {
      return BoxesRunTime.unboxToInt(this.getOrElse(i, value));
   }

   public long getOrElse$mcJ$sp(final int i, final Function0 value) {
      return BoxesRunTime.unboxToLong(this.getOrElse(i, value));
   }

   public double getOrElseUpdate$mcD$sp(final int i, final Function0 value) {
      return BoxesRunTime.unboxToDouble(this.getOrElseUpdate(i, value));
   }

   public float getOrElseUpdate$mcF$sp(final int i, final Function0 value) {
      return BoxesRunTime.unboxToFloat(this.getOrElseUpdate(i, value));
   }

   public int getOrElseUpdate$mcI$sp(final int i, final Function0 value) {
      return BoxesRunTime.unboxToInt(this.getOrElseUpdate(i, value));
   }

   public long getOrElseUpdate$mcJ$sp(final int i, final Function0 value) {
      return BoxesRunTime.unboxToLong(this.getOrElseUpdate(i, value));
   }

   public SparseArray map$mcD$sp(final Function1 f, final ClassTag evidence$1, final Zero evidence$2) {
      return this.map(f, evidence$1, evidence$2);
   }

   public SparseArray map$mcF$sp(final Function1 f, final ClassTag evidence$1, final Zero evidence$2) {
      return this.map(f, evidence$1, evidence$2);
   }

   public SparseArray map$mcI$sp(final Function1 f, final ClassTag evidence$1, final Zero evidence$2) {
      return this.map(f, evidence$1, evidence$2);
   }

   public SparseArray map$mcJ$sp(final Function1 f, final ClassTag evidence$1, final Zero evidence$2) {
      return this.map(f, evidence$1, evidence$2);
   }

   public SparseArray filter$mcD$sp(final Function1 f) {
      return this.filter(f);
   }

   public SparseArray filter$mcF$sp(final Function1 f) {
      return this.filter(f);
   }

   public SparseArray filter$mcI$sp(final Function1 f) {
      return this.filter(f);
   }

   public SparseArray filter$mcJ$sp(final Function1 f) {
      return this.filter(f);
   }

   public double valueAt$mcD$sp(final int i) {
      return BoxesRunTime.unboxToDouble(this.valueAt(i));
   }

   public float valueAt$mcF$sp(final int i) {
      return BoxesRunTime.unboxToFloat(this.valueAt(i));
   }

   public int valueAt$mcI$sp(final int i) {
      return BoxesRunTime.unboxToInt(this.valueAt(i));
   }

   public long valueAt$mcJ$sp(final int i) {
      return BoxesRunTime.unboxToLong(this.valueAt(i));
   }

   public void update$mcD$sp(final int i, final double value) {
      this.update(i, BoxesRunTime.boxToDouble(value));
   }

   public void update$mcF$sp(final int i, final float value) {
      this.update(i, BoxesRunTime.boxToFloat(value));
   }

   public void update$mcI$sp(final int i, final int value) {
      this.update(i, BoxesRunTime.boxToInteger(value));
   }

   public void update$mcJ$sp(final int i, final long value) {
      this.update(i, BoxesRunTime.boxToLong(value));
   }

   public void use$mcD$sp(final int[] index, final double[] data, final int used) {
      this.use(index, data, used);
   }

   public void use$mcF$sp(final int[] index, final float[] data, final int used) {
      this.use(index, data, used);
   }

   public void use$mcI$sp(final int[] index, final int[] data, final int used) {
      this.use(index, data, used);
   }

   public void use$mcJ$sp(final int[] index, final long[] data, final int used) {
      this.use(index, data, used);
   }

   public SparseArray concatenate$mcD$sp(final SparseArray that, final ClassTag man) {
      return this.concatenate(that, man);
   }

   public SparseArray concatenate$mcF$sp(final SparseArray that, final ClassTag man) {
      return this.concatenate(that, man);
   }

   public SparseArray concatenate$mcI$sp(final SparseArray that, final ClassTag man) {
      return this.concatenate(that, man);
   }

   public SparseArray concatenate$mcJ$sp(final SparseArray that, final ClassTag man) {
      return this.concatenate(that, man);
   }

   public boolean specInstance$() {
      return false;
   }

   public SparseArray(final int[] index, final Object data, final int used, final int size, final Object default) {
      this.index = index;
      this.data = data;
      this.breeze$collection$mutable$SparseArray$$used = used;
      this.size = size;
      this.default = default;
      super();
      SparseArrayLike.$init$(this);
      Storage.$init$(this);
      this.lastReturnedPos = -1;
   }

   public SparseArray(final int size, final Object default, final ClassTag manElem) {
      this((int[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), scala.Array..MODULE$.empty(manElem), 0, size, default);
   }

   public SparseArray(final int size, final ClassTag manElem, final Zero zero) {
      this(size, ConfigurableDefault$.MODULE$.default().value(zero), manElem);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
