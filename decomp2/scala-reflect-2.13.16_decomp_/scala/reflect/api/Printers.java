package scala.reflect.api;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.settings.MutableSettings;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\rec!\u0003.\\!\u0003\r\tAYB)\u0011\u00159\u0007\u0001\"\u0001i\r\u001da\u0007\u0001%A\u0002\u00125DQa\u001a\u0002\u0005\u0002!DQA\u001c\u0002\u0007\u0002=Dq\u0001\u001f\u0002A\u0002\u0013E\u0011\u0010C\u0004~\u0005\u0001\u0007I\u0011\u0003@\t\u0011\u0005\r!\u00011A\u0005\u0012eD\u0011\"!\u0002\u0003\u0001\u0004%\t\"a\u0002\t\u0011\u0005-!\u00011A\u0005\u0012eD\u0011\"!\u0004\u0003\u0001\u0004%\t\"a\u0004\t\u0011\u0005M!\u00011A\u0005\u0012eD\u0011\"!\u0006\u0003\u0001\u0004%\t\"a\u0006\t\u0011\u0005m!\u00011A\u0005\u0012eD\u0011\"!\b\u0003\u0001\u0004%\t\"a\b\t\u0011\u0005\r\"\u00011A\u0005\u0012eD\u0011\"!\n\u0003\u0001\u0004%\t\"a\n\t\u000f\u0005-\"\u0001\"\u0001\u0002.!9\u0011\u0011\u0007\u0002\u0005\u0002\u00055\u0002bBA\u001a\u0005\u0011\u0005\u0011Q\u0006\u0005\b\u0003k\u0011A\u0011AA\u0017\u0011\u001d\t9D\u0001C\u0001\u0003[Aq!!\u000f\u0003\t\u0003\ti\u0003C\u0004\u0002<\t!\t!!\f\t\u000f\u0005u\"\u0001\"\u0001\u0002.!9\u0011q\b\u0002\u0005\u0002\u00055\u0002bBA!\u0005\u0011\u0005\u0011Q\u0006\u0005\b\u0003\u0007\u0012A\u0011AA\u0017\u0011\u001d\t)E\u0001C\u0001\u0003[1a!a\u0012\u0001\u0001\u0006%\u0003BCA5;\tU\r\u0011\"\u0001\u0002l!Q\u00111O\u000f\u0003\u0012\u0003\u0006I!!\u001c\t\u000f\u0005UT\u0004\"\u0001\u0002x!I\u0011qP\u000f\u0002\u0002\u0013\u0005\u0011\u0011\u0011\u0005\n\u0003\u000bk\u0012\u0013!C\u0001\u0003\u000fC\u0011\"!(\u001e\u0003\u0003%\t%a(\t\u0013\u0005EV$!A\u0005\u0002\u0005M\u0006\"CA^;\u0005\u0005I\u0011AA_\u0011%\t\t-HA\u0001\n\u0003\n\u0019\rC\u0005\u0002Rv\t\t\u0011\"\u0001\u0002T\"I\u0011q[\u000f\u0002\u0002\u0013\u0005\u0013\u0011\u001c\u0005\n\u0003;l\u0012\u0011!C!\u0003?D\u0011\"!9\u001e\u0003\u0003%\t%a9\t\u0013\u0005\u0015X$!A\u0005B\u0005\u001dxaBAv\u0001!\u0005\u0011Q\u001e\u0004\b\u0003\u000f\u0002\u0001\u0012AAx\u0011\u001d\t)(\fC\u0001\u0003wDq!!@.\t\u0007\ty\u0010C\u0004\u0003\u00045\"\u0019A!\u0002\t\u000f\t%Q\u0006b\u0001\u0003\f!I!qE\u0017\u0002\u0002\u0013\u0005%\u0011\u0006\u0005\n\u0005[i\u0013\u0011!CA\u0005_AqAa\u000e\u0001\t#\u0011I\u0004C\u0005\u0003l\u0001\t\n\u0011\"\u0005\u0003n!I!\u0011\u000f\u0001\u0012\u0002\u0013E!Q\u000e\u0005\n\u0005g\u0002\u0011\u0013!C\t\u0005[B\u0011B!\u001e\u0001#\u0003%\tB!\u001c\t\u0013\t]\u0004!%A\u0005\u0012\t5\u0004\"\u0003B=\u0001E\u0005I\u0011\u0003B7\u0011\u001d\u0011Y\b\u0001C)\u0005{BqA!$\u0001\t\u0003\u0011y\tC\u0005\u0003\"\u0002\t\n\u0011\"\u0001\u0003n!I!1\u0015\u0001\u0012\u0002\u0013\u0005!Q\u000e\u0005\n\u0005K\u0003\u0011\u0013!C\u0001\u0005[B\u0011Ba*\u0001#\u0003%\tA!\u001c\t\u0013\t%\u0006!%A\u0005\u0002\t5\u0004\"\u0003BV\u0001E\u0005I\u0011\u0001B7\u0011\u001d\u0011i\u000b\u0001D\t\u0005_CqA!.\u0001\t\u0003\u00119\fC\u0005\u0003H\u0002\t\n\u0011\"\u0001\u0003n!I!\u0011\u001a\u0001\u0012\u0002\u0013\u0005!Q\u000e\u0005\n\u0005\u0017\u0004\u0011\u0013!C\u0001\u0005[B\u0011B!4\u0001#\u0003%\tA!\u001c\t\u0013\t=\u0007!%A\u0005\u0002\tE\u0007b\u0002Bk\u0001\u0019E!q\u001b\u0005\b\u0005?\u0004A\u0011\u0001Bq\u0011%\u0011\t\u0010AI\u0001\n\u0003\u0011i\u0007C\u0005\u0003t\u0002\t\n\u0011\"\u0001\u0003n!I!Q\u001f\u0001\u0012\u0002\u0013\u0005!Q\u000e\u0005\n\u0005o\u0004\u0011\u0013!C\u0001\u0005[B\u0011B!?\u0001#\u0003%\tA!\u001c\t\u0013\tm\b!%A\u0005\u0002\t5\u0004b\u0002B\u007f\u0001\u0019E!q \u0005\b\u0005\u001b\u0003a\u0011AB\u0002\u0011\u001d\u0011y\u000e\u0001C\u0001\u0007'AqA!$\u0001\r\u0003\u00199\u0002C\u0004\u0003\u000e\u00021\taa\n\t\u000f\t}\u0007\u0001\"\u0001\u00048!9!q\u001c\u0001\u0005\u0002\rm\u0002bBB \u0001\u0019\u00051\u0011\t\u0002\t!JLg\u000e^3sg*\u0011A,X\u0001\u0004CBL'B\u00010`\u0003\u001d\u0011XM\u001a7fGRT\u0011\u0001Y\u0001\u0006g\u000e\fG.Y\u0002\u0001'\t\u00011\r\u0005\u0002eK6\tq,\u0003\u0002g?\n1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#A5\u0011\u0005\u0011T\u0017BA6`\u0005\u0011)f.\u001b;\u0003\u0017Q\u0013X-\u001a)sS:$XM]\n\u0003\u0005\r\fQ\u0001\u001d:j]R$\"!\u001b9\t\u000bE$\u0001\u0019\u0001:\u0002\t\u0005\u0014xm\u001d\t\u0004IN,\u0018B\u0001;`\u0005)a$/\u001a9fCR,GM\u0010\t\u0003IZL!a^0\u0003\u0007\u0005s\u00170\u0001\u0006qe&tG\u000fV=qKN,\u0012A\u001f\t\u0003InL!\u0001`0\u0003\u000f\t{w\u000e\\3b]\u0006q\u0001O]5oiRK\b/Z:`I\u0015\fHCA5\u0000\u0011!\t\tABA\u0001\u0002\u0004Q\u0018a\u0001=%c\u0005A\u0001O]5oi&#7/\u0001\u0007qe&tG/\u00133t?\u0012*\u0017\u000fF\u0002j\u0003\u0013A\u0001\"!\u0001\t\u0003\u0003\u0005\rA_\u0001\faJLg\u000e^(x]\u0016\u00148/A\bqe&tGoT<oKJ\u001cx\fJ3r)\rI\u0017\u0011\u0003\u0005\t\u0003\u0003Q\u0011\u0011!a\u0001u\u0006Q\u0001O]5oi.Kg\u000eZ:\u0002\u001dA\u0014\u0018N\u001c;LS:$7o\u0018\u0013fcR\u0019\u0011.!\u0007\t\u0011\u0005\u0005A\"!AA\u0002i\fA\u0002\u001d:j]Rl\u0015N\u001d:peN\f\u0001\u0003\u001d:j]Rl\u0015N\u001d:peN|F%Z9\u0015\u0007%\f\t\u0003\u0003\u0005\u0002\u00029\t\t\u00111\u0001{\u00039\u0001(/\u001b8u!>\u001c\u0018\u000e^5p]N\f!\u0003\u001d:j]R\u0004vn]5uS>t7o\u0018\u0013fcR\u0019\u0011.!\u000b\t\u0011\u0005\u0005\u0001#!AA\u0002i\f\u0011b^5uQRK\b/Z:\u0016\u0005\u0005=R\"\u0001\u0002\u0002\u0019]LG\u000f[8viRK\b/Z:\u0002\u000f]LG\u000f[%eg\u0006Qq/\u001b;i_V$\u0018\nZ:\u0002\u0015]LG\u000f[(x]\u0016\u00148/A\u0007xSRDw.\u001e;Po:,'o]\u0001\no&$\bnS5oIN\fAb^5uQ>,HoS5oIN\f1b^5uQ6K'O]8sg\u0006qq/\u001b;i_V$X*\u001b:s_J\u001c\u0018!D<ji\"\u0004vn]5uS>t7/\u0001\txSRDw.\u001e;Q_NLG/[8og\nY!i\\8mK\u0006tg\t\\1h'\u0019i2-a\u0013\u0002RA\u0019A-!\u0014\n\u0007\u0005=sLA\u0004Qe>$Wo\u0019;\u0011\t\u0005M\u00131\r\b\u0005\u0003+\nyF\u0004\u0003\u0002X\u0005uSBAA-\u0015\r\tY&Y\u0001\u0007yI|w\u000e\u001e \n\u0003\u0001L1!!\u0019`\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u001a\u0002h\ta1+\u001a:jC2L'0\u00192mK*\u0019\u0011\u0011M0\u0002\u000bY\fG.^3\u0016\u0005\u00055\u0004\u0003\u00023\u0002piL1!!\u001d`\u0005\u0019y\u0005\u000f^5p]\u00061a/\u00197vK\u0002\na\u0001P5oSRtD\u0003BA=\u0003{\u00022!a\u001f\u001e\u001b\u0005\u0001\u0001bBA5A\u0001\u0007\u0011QN\u0001\u0005G>\u0004\u0018\u0010\u0006\u0003\u0002z\u0005\r\u0005\"CA5CA\u0005\t\u0019AA7\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!!#+\t\u00055\u00141R\u0016\u0003\u0003\u001b\u0003B!a$\u0002\u001a6\u0011\u0011\u0011\u0013\u0006\u0005\u0003'\u000b)*A\u0005v]\u000eDWmY6fI*\u0019\u0011qS0\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\u001c\u0006E%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"!!)\u0011\t\u0005\r\u0016QV\u0007\u0003\u0003KSA!a*\u0002*\u0006!A.\u00198h\u0015\t\tY+\u0001\u0003kCZ\f\u0017\u0002BAX\u0003K\u0013aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLXCAA[!\r!\u0017qW\u0005\u0004\u0003s{&aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HcA;\u0002@\"I\u0011\u0011A\u0013\u0002\u0002\u0003\u0007\u0011QW\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011Q\u0019\t\u0006\u0003\u000f\fi-^\u0007\u0003\u0003\u0013T1!a3`\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u001f\fIM\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGc\u0001>\u0002V\"A\u0011\u0011A\u0014\u0002\u0002\u0003\u0007Q/\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BAQ\u00037D\u0011\"!\u0001)\u0003\u0003\u0005\r!!.\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!.\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!)\u0002\r\u0015\fX/\u00197t)\rQ\u0018\u0011\u001e\u0005\t\u0003\u0003Y\u0013\u0011!a\u0001k\u0006Y!i\\8mK\u0006tg\t\\1h!\r\tY(L\n\u0005[\r\f\t\u0010\u0005\u0003\u0002t\u0006eXBAA{\u0015\u0011\t90!+\u0002\u0005%|\u0017\u0002BA3\u0003k$\"!!<\u0002)\t|w\u000e\\3b]R{'i\\8mK\u0006tg\t\\1h)\u0011\tIH!\u0001\t\r\u0005%t\u00061\u0001{\u0003My\u0007\u000f^5p]R{'i\\8mK\u0006tg\t\\1h)\u0011\tIHa\u0002\t\u000f\u0005%\u0004\u00071\u0001\u0002n\u0005!2/\u001a;uS:<Gk\u001c\"p_2,\u0017M\u001c$mC\u001e$B!!\u001f\u0003\u000e!9!qB\u0019A\u0002\tE\u0011aB:fiRLgn\u001a\t\u0005\u0005'\u0011\u0019\u0003\u0005\u0003\u0003\u0016\t}QB\u0001B\f\u0015\u0011\u0011IBa\u0007\u0002\u0011M,G\u000f^5oONT1A!\b^\u0003!Ig\u000e^3s]\u0006d\u0017\u0002\u0002B\u0011\u0005/\u0011q\"T;uC\ndWmU3ui&twm]\u0005\u0005\u0005K\u0011yB\u0001\bC_>dW-\u00198TKR$\u0018N\\4\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\t\u0005e$1\u0006\u0005\b\u0003S\u0012\u0004\u0019AA7\u0003\u001d)h.\u00199qYf$BA!\r\u00034A)A-a\u001c\u0002n!I!QG\u001a\u0002\u0002\u0003\u0007\u0011\u0011P\u0001\u0004q\u0012\u0002\u0014A\u0002:f]\u0012,'\u000f\u0006\n\u0003<\t%#Q\nB0\u0005C\u0012\u0019G!\u001a\u0003h\t%\u0004\u0003\u0002B\u001f\u0005\u000brAAa\u0010\u0003BA\u0019\u0011qK0\n\u0007\t\rs,\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003_\u00139EC\u0002\u0003D}CaAa\u00135\u0001\u0004)\u0018\u0001B<iCRDqAa\u00145\u0001\u0004\u0011\t&A\u0005nWB\u0013\u0018N\u001c;feB9AMa\u0015\u0003X\tu\u0013b\u0001B+?\nIa)\u001e8di&|g.\r\t\u0005\u0003g\u0014I&\u0003\u0003\u0003\\\u0005U(a\u0003)sS:$xK]5uKJ\u00042!a\u001f\u0003\u0011!AH\u0007%AA\u0002\u0005e\u0004\"CA\u0002iA\u0005\t\u0019AA=\u0011%\tY\u0001\u000eI\u0001\u0002\u0004\tI\bC\u0005\u0002\u0014Q\u0002\n\u00111\u0001\u0002z!I\u00111\u0004\u001b\u0011\u0002\u0003\u0007\u0011\u0011\u0010\u0005\n\u0003G!\u0004\u0013!a\u0001\u0003s\n\u0001C]3oI\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\t=$\u0006BA=\u0003\u0017\u000b\u0001C]3oI\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001b\u0002!I,g\u000eZ3sI\u0011,g-Y;mi\u0012*\u0014\u0001\u0005:f]\u0012,'\u000f\n3fM\u0006,H\u000e\u001e\u00137\u0003A\u0011XM\u001c3fe\u0012\"WMZ1vYR$s'\u0001\tsK:$WM\u001d\u0013eK\u001a\fW\u000f\u001c;%q\u0005aAO]3f)>\u001cFO]5oOR!!1\bB@\u0011\u001d\u0011\ti\u000fa\u0001\u0005\u0007\u000bA\u0001\u001e:fKB!\u00111\u0010BC\u0013\u0011\u00119I!#\u0003\tQ\u0013X-Z\u0005\u0004\u0005\u0017[&!\u0002+sK\u0016\u001c\u0018\u0001B:i_^$\u0002Ca\u000f\u0003\u0012\nU%q\u0013BM\u00057\u0013iJa(\t\r\tME\b1\u0001v\u0003\r\tg.\u001f\u0005\tqr\u0002\n\u00111\u0001\u0002z!I\u00111\u0001\u001f\u0011\u0002\u0003\u0007\u0011\u0011\u0010\u0005\n\u0003\u0017a\u0004\u0013!a\u0001\u0003sB\u0011\"a\u0005=!\u0003\u0005\r!!\u001f\t\u0013\u0005mA\b%AA\u0002\u0005e\u0004\"CA\u0012yA\u0005\t\u0019AA=\u00039\u0019\bn\\<%I\u00164\u0017-\u001e7uII\nab\u001d5po\u0012\"WMZ1vYR$3'\u0001\btQ><H\u0005Z3gCVdG\u000f\n\u001b\u0002\u001dMDwn\u001e\u0013eK\u001a\fW\u000f\u001c;%k\u0005q1\u000f[8xI\u0011,g-Y;mi\u00122\u0014AD:i_^$C-\u001a4bk2$HeN\u0001\u000f]\u0016<HK]3f!JLg\u000e^3s)\u0011\u0011iF!-\t\u000f\tM6\t1\u0001\u0003X\u0005\u0019q.\u001e;\u0002\u0011MDwn^\"pI\u0016$bBa\u000f\u0003:\nm&Q\u0018B`\u0005\u0003\u0014\u0019\rC\u0004\u0003\u0002\u0012\u0003\rAa!\t\u0011a$\u0005\u0013!a\u0001\u0003sB\u0011\"a\u0001E!\u0003\u0005\r!!\u001f\t\u0013\u0005-A\t%AA\u0002\u0005e\u0004\"CA\u0012\tB\u0005\t\u0019AA=\u0011!\u0011)\r\u0012I\u0001\u0002\u0004Q\u0018\u0001\u00049sS:$(k\\8u!.<\u0017AE:i_^\u001cu\u000eZ3%I\u00164\u0017-\u001e7uII\n!c\u001d5po\u000e{G-\u001a\u0013eK\u001a\fW\u000f\u001c;%g\u0005\u00112\u000f[8x\u0007>$W\r\n3fM\u0006,H\u000e\u001e\u00135\u0003I\u0019\bn\\<D_\u0012,G\u0005Z3gCVdG\u000fJ\u001b\u0002%MDwn^\"pI\u0016$C-\u001a4bk2$HEN\u000b\u0003\u0005'T3A_AF\u00039qWm^\"pI\u0016\u0004&/\u001b8uKJ$\u0002B!\u0018\u0003Z\nm'Q\u001c\u0005\b\u0005gS\u0005\u0019\u0001B,\u0011\u001d\u0011\tI\u0013a\u0001\u0005\u0007CaA!2K\u0001\u0004Q\u0018aB:i_^\u0014\u0016m\u001e\u000b\u0011\u0005w\u0011\u0019O!:\u0003h\n%(1\u001eBw\u0005_DaAa%L\u0001\u0004)\b\u0002\u0003=L!\u0003\u0005\r!!\u001f\t\u0013\u0005\r1\n%AA\u0002\u0005e\u0004\"CA\u0006\u0017B\u0005\t\u0019AA=\u0011%\t\u0019b\u0013I\u0001\u0002\u0004\tI\bC\u0005\u0002\u001c-\u0003\n\u00111\u0001\u0002z!I\u00111E&\u0011\u0002\u0003\u0007\u0011\u0011P\u0001\u0012g\"|wOU1xI\u0011,g-Y;mi\u0012\u0012\u0014!E:i_^\u0014\u0016m\u001e\u0013eK\u001a\fW\u000f\u001c;%g\u0005\t2\u000f[8x%\u0006<H\u0005Z3gCVdG\u000f\n\u001b\u0002#MDwn\u001e*bo\u0012\"WMZ1vYR$S'A\ttQ><(+Y<%I\u00164\u0017-\u001e7uIY\n\u0011c\u001d5poJ\u000bw\u000f\n3fM\u0006,H\u000e\u001e\u00138\u0003EqWm\u001e*boR\u0013X-\u001a)sS:$XM\u001d\u000b\u0005\u0005;\u001a\t\u0001C\u0004\u00034J\u0003\rAa\u0016\u0015\t\tm2Q\u0001\u0005\b\u0007\u000f\u0019\u0006\u0019AB\u0005\u0003\u0011q\u0017-\\3\u0011\t\u0005m41B\u0005\u0005\u0007\u001b\u0019yA\u0001\u0003OC6,\u0017bAB\t7\n)a*Y7fgR!!1HB\u000b\u0011\u001d\u00199\u0001\u0016a\u0001\u0007\u0013!BAa\u000f\u0004\u001a!911D+A\u0002\ru\u0011!\u00024mC\u001e\u001c\b\u0003BA>\u0007?IAa!\t\u0004$\t9a\t\\1h'\u0016$\u0018bAB\u00137\nAa\t\\1h'\u0016$8\u000f\u0006\u0003\u0003<\r%\u0002bBB\u0016-\u0002\u00071QF\u0001\ta>\u001c\u0018\u000e^5p]B!\u00111PB\u0018\u0013\u0011\u0019\tda\r\u0003\u0011A{7/\u001b;j_:L1a!\u000e\\\u0005%\u0001vn]5uS>t7\u000f\u0006\u0003\u0003<\re\u0002bBB\u000e/\u0002\u00071Q\u0004\u000b\u0005\u0005w\u0019i\u0004C\u0004\u0004,a\u0003\ra!\f\u0002\u0011MDwn\u001e#fG2$BAa\u000f\u0004D!91QI-A\u0002\r\u001d\u0013aA:z[B!\u00111PB%\u0013\u0011\u0019Ye!\u0014\u0003\rMKXNY8m\u0013\r\u0019ye\u0017\u0002\b'fl'm\u001c7t!\u0011\u0019\u0019f!\u0016\u000e\u0003mK1aa\u0016\\\u0005!)f.\u001b<feN,\u0007"
)
public interface Printers {
   BooleanFlag$ BooleanFlag();

   // $FF: synthetic method
   static String render$(final Printers $this, final Object what, final Function1 mkPrinter, final BooleanFlag printTypes, final BooleanFlag printIds, final BooleanFlag printOwners, final BooleanFlag printKinds, final BooleanFlag printMirrors, final BooleanFlag printPositions) {
      return $this.render(what, mkPrinter, printTypes, printIds, printOwners, printKinds, printMirrors, printPositions);
   }

   default String render(final Object what, final Function1 mkPrinter, final BooleanFlag printTypes, final BooleanFlag printIds, final BooleanFlag printOwners, final BooleanFlag printKinds, final BooleanFlag printMirrors, final BooleanFlag printPositions) {
      StringWriter buffer = new StringWriter();
      PrintWriter writer = new PrintWriter(buffer);
      TreePrinter printer = (TreePrinter)mkPrinter.apply(writer);
      Option var10000 = printTypes.value();
      if (var10000 == null) {
         throw null;
      } else {
         Option foreach_this = var10000;
         if (!foreach_this.isEmpty()) {
            Object var18 = foreach_this.get();
            $anonfun$render$1(printer, BoxesRunTime.unboxToBoolean(var18));
         }

         Object var24 = null;
         var10000 = printIds.value();
         if (var10000 == null) {
            throw null;
         } else {
            Option foreach_this = var10000;
            if (!foreach_this.isEmpty()) {
               Object var19 = foreach_this.get();
               $anonfun$render$2(printer, BoxesRunTime.unboxToBoolean(var19));
            }

            Object var25 = null;
            var10000 = printOwners.value();
            if (var10000 == null) {
               throw null;
            } else {
               Option foreach_this = var10000;
               if (!foreach_this.isEmpty()) {
                  Object var20 = foreach_this.get();
                  $anonfun$render$3(printer, BoxesRunTime.unboxToBoolean(var20));
               }

               Object var26 = null;
               var10000 = printKinds.value();
               if (var10000 == null) {
                  throw null;
               } else {
                  Option foreach_this = var10000;
                  if (!foreach_this.isEmpty()) {
                     Object var21 = foreach_this.get();
                     $anonfun$render$4(printer, BoxesRunTime.unboxToBoolean(var21));
                  }

                  Object var27 = null;
                  var10000 = printMirrors.value();
                  if (var10000 == null) {
                     throw null;
                  } else {
                     Option foreach_this = var10000;
                     if (!foreach_this.isEmpty()) {
                        Object var22 = foreach_this.get();
                        $anonfun$render$5(printer, BoxesRunTime.unboxToBoolean(var22));
                     }

                     Object var28 = null;
                     var10000 = printPositions.value();
                     if (var10000 == null) {
                        throw null;
                     } else {
                        Option foreach_this = var10000;
                        if (!foreach_this.isEmpty()) {
                           Object var23 = foreach_this.get();
                           $anonfun$render$6(printer, BoxesRunTime.unboxToBoolean(var23));
                        }

                        Object var29 = null;
                        printer.print(.MODULE$.genericWrapArray(new Object[]{what}));
                        writer.flush();
                        return buffer.toString();
                     }
                  }
               }
            }
         }
      }
   }

   // $FF: synthetic method
   static BooleanFlag render$default$3$(final Printers $this) {
      return $this.render$default$3();
   }

   default BooleanFlag render$default$3() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   // $FF: synthetic method
   static BooleanFlag render$default$4$(final Printers $this) {
      return $this.render$default$4();
   }

   default BooleanFlag render$default$4() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   // $FF: synthetic method
   static BooleanFlag render$default$5$(final Printers $this) {
      return $this.render$default$5();
   }

   default BooleanFlag render$default$5() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   // $FF: synthetic method
   static BooleanFlag render$default$6$(final Printers $this) {
      return $this.render$default$6();
   }

   default BooleanFlag render$default$6() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   // $FF: synthetic method
   static BooleanFlag render$default$7$(final Printers $this) {
      return $this.render$default$7();
   }

   default BooleanFlag render$default$7() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   // $FF: synthetic method
   static BooleanFlag render$default$8$(final Printers $this) {
      return $this.render$default$8();
   }

   default BooleanFlag render$default$8() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   // $FF: synthetic method
   static String treeToString$(final Printers $this, final Trees.TreeApi tree) {
      return $this.treeToString(tree);
   }

   default String treeToString(final Trees.TreeApi tree) {
      return this.show(tree, this.show$default$2(), this.show$default$3(), this.show$default$4(), this.show$default$5(), this.show$default$6(), this.show$default$7());
   }

   // $FF: synthetic method
   static String show$(final Printers $this, final Object any, final BooleanFlag printTypes, final BooleanFlag printIds, final BooleanFlag printOwners, final BooleanFlag printKinds, final BooleanFlag printMirrors, final BooleanFlag printPositions) {
      return $this.show(any, printTypes, printIds, printOwners, printKinds, printMirrors, printPositions);
   }

   default String show(final Object any, final BooleanFlag printTypes, final BooleanFlag printIds, final BooleanFlag printOwners, final BooleanFlag printKinds, final BooleanFlag printMirrors, final BooleanFlag printPositions) {
      return this.render(any, (x$7) -> this.newTreePrinter(x$7), printTypes, printIds, printOwners, printKinds, printMirrors, printPositions);
   }

   TreePrinter newTreePrinter(final PrintWriter out);

   // $FF: synthetic method
   static String showCode$(final Printers $this, final Trees.TreeApi tree, final BooleanFlag printTypes, final BooleanFlag printIds, final BooleanFlag printOwners, final BooleanFlag printPositions, final boolean printRootPkg) {
      return $this.showCode(tree, printTypes, printIds, printOwners, printPositions, printRootPkg);
   }

   default String showCode(final Trees.TreeApi tree, final BooleanFlag printTypes, final BooleanFlag printIds, final BooleanFlag printOwners, final BooleanFlag printPositions, final boolean printRootPkg) {
      return this.render(tree, (x$8) -> this.newCodePrinter(x$8, tree, printRootPkg), printTypes, printIds, printOwners, this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$), this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$), printPositions);
   }

   TreePrinter newCodePrinter(final PrintWriter out, final Trees.TreeApi tree, final boolean printRootPkg);

   // $FF: synthetic method
   static String showRaw$(final Printers $this, final Object any, final BooleanFlag printTypes, final BooleanFlag printIds, final BooleanFlag printOwners, final BooleanFlag printKinds, final BooleanFlag printMirrors, final BooleanFlag printPositions) {
      return $this.showRaw(any, printTypes, printIds, printOwners, printKinds, printMirrors, printPositions);
   }

   default String showRaw(final Object any, final BooleanFlag printTypes, final BooleanFlag printIds, final BooleanFlag printOwners, final BooleanFlag printKinds, final BooleanFlag printMirrors, final BooleanFlag printPositions) {
      return this.render(any, (out) -> this.newRawTreePrinter(out), printTypes, printIds, printOwners, printKinds, printMirrors, printPositions);
   }

   TreePrinter newRawTreePrinter(final PrintWriter out);

   String show(final Names.NameApi name);

   // $FF: synthetic method
   static String showRaw$(final Printers $this, final Names.NameApi name) {
      return $this.showRaw(name);
   }

   default String showRaw(final Names.NameApi name) {
      return name.toString();
   }

   String show(final Object flags);

   String show(final Position position);

   // $FF: synthetic method
   static BooleanFlag show$default$2$(final Printers $this) {
      return $this.show$default$2();
   }

   default BooleanFlag show$default$2() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   // $FF: synthetic method
   static BooleanFlag show$default$3$(final Printers $this) {
      return $this.show$default$3();
   }

   default BooleanFlag show$default$3() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   // $FF: synthetic method
   static BooleanFlag show$default$4$(final Printers $this) {
      return $this.show$default$4();
   }

   default BooleanFlag show$default$4() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   // $FF: synthetic method
   static BooleanFlag show$default$5$(final Printers $this) {
      return $this.show$default$5();
   }

   default BooleanFlag show$default$5() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   // $FF: synthetic method
   static BooleanFlag show$default$6$(final Printers $this) {
      return $this.show$default$6();
   }

   default BooleanFlag show$default$6() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   // $FF: synthetic method
   static BooleanFlag show$default$7$(final Printers $this) {
      return $this.show$default$7();
   }

   default BooleanFlag show$default$7() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   // $FF: synthetic method
   static BooleanFlag showCode$default$2$(final Printers $this) {
      return $this.showCode$default$2();
   }

   default BooleanFlag showCode$default$2() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   // $FF: synthetic method
   static BooleanFlag showCode$default$3$(final Printers $this) {
      return $this.showCode$default$3();
   }

   default BooleanFlag showCode$default$3() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   // $FF: synthetic method
   static BooleanFlag showCode$default$4$(final Printers $this) {
      return $this.showCode$default$4();
   }

   default BooleanFlag showCode$default$4() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   // $FF: synthetic method
   static BooleanFlag showCode$default$5$(final Printers $this) {
      return $this.showCode$default$5();
   }

   default BooleanFlag showCode$default$5() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   // $FF: synthetic method
   static boolean showCode$default$6$(final Printers $this) {
      return $this.showCode$default$6();
   }

   default boolean showCode$default$6() {
      return false;
   }

   // $FF: synthetic method
   static String showRaw$(final Printers $this, final Object flags) {
      return $this.showRaw(flags);
   }

   default String showRaw(final Object flags) {
      return flags.toString();
   }

   // $FF: synthetic method
   static String showRaw$(final Printers $this, final Position position) {
      return $this.showRaw(position);
   }

   default String showRaw(final Position position) {
      return position.toString();
   }

   // $FF: synthetic method
   static BooleanFlag showRaw$default$2$(final Printers $this) {
      return $this.showRaw$default$2();
   }

   default BooleanFlag showRaw$default$2() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   // $FF: synthetic method
   static BooleanFlag showRaw$default$3$(final Printers $this) {
      return $this.showRaw$default$3();
   }

   default BooleanFlag showRaw$default$3() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   // $FF: synthetic method
   static BooleanFlag showRaw$default$4$(final Printers $this) {
      return $this.showRaw$default$4();
   }

   default BooleanFlag showRaw$default$4() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   // $FF: synthetic method
   static BooleanFlag showRaw$default$5$(final Printers $this) {
      return $this.showRaw$default$5();
   }

   default BooleanFlag showRaw$default$5() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   // $FF: synthetic method
   static BooleanFlag showRaw$default$6$(final Printers $this) {
      return $this.showRaw$default$6();
   }

   default BooleanFlag showRaw$default$6() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   // $FF: synthetic method
   static BooleanFlag showRaw$default$7$(final Printers $this) {
      return $this.showRaw$default$7();
   }

   default BooleanFlag showRaw$default$7() {
      return this.BooleanFlag().optionToBooleanFlag(scala.None..MODULE$);
   }

   String showDecl(final Symbols.SymbolApi sym);

   // $FF: synthetic method
   static TreePrinter $anonfun$render$1(final TreePrinter printer$1, final boolean x$1) {
      return x$1 ? printer$1.withTypes() : printer$1.withoutTypes();
   }

   // $FF: synthetic method
   static TreePrinter $anonfun$render$2(final TreePrinter printer$1, final boolean x$2) {
      return x$2 ? printer$1.withIds() : printer$1.withoutIds();
   }

   // $FF: synthetic method
   static TreePrinter $anonfun$render$3(final TreePrinter printer$1, final boolean x$3) {
      return x$3 ? printer$1.withOwners() : printer$1.withoutOwners();
   }

   // $FF: synthetic method
   static TreePrinter $anonfun$render$4(final TreePrinter printer$1, final boolean x$4) {
      return x$4 ? printer$1.withKinds() : printer$1.withoutKinds();
   }

   // $FF: synthetic method
   static TreePrinter $anonfun$render$5(final TreePrinter printer$1, final boolean x$5) {
      return x$5 ? printer$1.withMirrors() : printer$1.withoutMirrors();
   }

   // $FF: synthetic method
   static TreePrinter $anonfun$render$6(final TreePrinter printer$1, final boolean x$6) {
      return x$6 ? printer$1.withPositions() : printer$1.withoutPositions();
   }

   static void $init$(final Printers $this) {
   }

   // $FF: synthetic method
   static TreePrinter $anonfun$render$1$adapted(final TreePrinter printer$1, final Object x$1) {
      return $anonfun$render$1(printer$1, BoxesRunTime.unboxToBoolean(x$1));
   }

   // $FF: synthetic method
   static TreePrinter $anonfun$render$2$adapted(final TreePrinter printer$1, final Object x$2) {
      return $anonfun$render$2(printer$1, BoxesRunTime.unboxToBoolean(x$2));
   }

   // $FF: synthetic method
   static TreePrinter $anonfun$render$3$adapted(final TreePrinter printer$1, final Object x$3) {
      return $anonfun$render$3(printer$1, BoxesRunTime.unboxToBoolean(x$3));
   }

   // $FF: synthetic method
   static TreePrinter $anonfun$render$4$adapted(final TreePrinter printer$1, final Object x$4) {
      return $anonfun$render$4(printer$1, BoxesRunTime.unboxToBoolean(x$4));
   }

   // $FF: synthetic method
   static TreePrinter $anonfun$render$5$adapted(final TreePrinter printer$1, final Object x$5) {
      return $anonfun$render$5(printer$1, BoxesRunTime.unboxToBoolean(x$5));
   }

   // $FF: synthetic method
   static TreePrinter $anonfun$render$6$adapted(final TreePrinter printer$1, final Object x$6) {
      return $anonfun$render$6(printer$1, BoxesRunTime.unboxToBoolean(x$6));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public interface TreePrinter {
      void print(final Seq args);

      boolean printTypes();

      void printTypes_$eq(final boolean x$1);

      boolean printIds();

      void printIds_$eq(final boolean x$1);

      boolean printOwners();

      void printOwners_$eq(final boolean x$1);

      boolean printKinds();

      void printKinds_$eq(final boolean x$1);

      boolean printMirrors();

      void printMirrors_$eq(final boolean x$1);

      boolean printPositions();

      void printPositions_$eq(final boolean x$1);

      // $FF: synthetic method
      static TreePrinter withTypes$(final TreePrinter $this) {
         return $this.withTypes();
      }

      default TreePrinter withTypes() {
         this.printTypes_$eq(true);
         return this;
      }

      // $FF: synthetic method
      static TreePrinter withoutTypes$(final TreePrinter $this) {
         return $this.withoutTypes();
      }

      default TreePrinter withoutTypes() {
         this.printTypes_$eq(false);
         return this;
      }

      // $FF: synthetic method
      static TreePrinter withIds$(final TreePrinter $this) {
         return $this.withIds();
      }

      default TreePrinter withIds() {
         this.printIds_$eq(true);
         return this;
      }

      // $FF: synthetic method
      static TreePrinter withoutIds$(final TreePrinter $this) {
         return $this.withoutIds();
      }

      default TreePrinter withoutIds() {
         this.printIds_$eq(false);
         return this;
      }

      // $FF: synthetic method
      static TreePrinter withOwners$(final TreePrinter $this) {
         return $this.withOwners();
      }

      default TreePrinter withOwners() {
         this.printOwners_$eq(true);
         return this;
      }

      // $FF: synthetic method
      static TreePrinter withoutOwners$(final TreePrinter $this) {
         return $this.withoutOwners();
      }

      default TreePrinter withoutOwners() {
         this.printOwners_$eq(false);
         return this;
      }

      // $FF: synthetic method
      static TreePrinter withKinds$(final TreePrinter $this) {
         return $this.withKinds();
      }

      default TreePrinter withKinds() {
         this.printKinds_$eq(true);
         return this;
      }

      // $FF: synthetic method
      static TreePrinter withoutKinds$(final TreePrinter $this) {
         return $this.withoutKinds();
      }

      default TreePrinter withoutKinds() {
         this.printKinds_$eq(false);
         return this;
      }

      // $FF: synthetic method
      static TreePrinter withMirrors$(final TreePrinter $this) {
         return $this.withMirrors();
      }

      default TreePrinter withMirrors() {
         this.printMirrors_$eq(true);
         return this;
      }

      // $FF: synthetic method
      static TreePrinter withoutMirrors$(final TreePrinter $this) {
         return $this.withoutMirrors();
      }

      default TreePrinter withoutMirrors() {
         this.printMirrors_$eq(false);
         return this;
      }

      // $FF: synthetic method
      static TreePrinter withPositions$(final TreePrinter $this) {
         return $this.withPositions();
      }

      default TreePrinter withPositions() {
         this.printPositions_$eq(true);
         return this;
      }

      // $FF: synthetic method
      static TreePrinter withoutPositions$(final TreePrinter $this) {
         return $this.withoutPositions();
      }

      default TreePrinter withoutPositions() {
         this.printPositions_$eq(false);
         return this;
      }

      // $FF: synthetic method
      Printers scala$reflect$api$Printers$TreePrinter$$$outer();

      static void $init$(final TreePrinter $this) {
         $this.printTypes_$eq(false);
         $this.printIds_$eq(false);
         $this.printOwners_$eq(false);
         $this.printKinds_$eq(false);
         $this.printMirrors_$eq(false);
         $this.printPositions_$eq(false);
      }
   }

   public class BooleanFlag implements Product, Serializable {
      private final Option value;
      // $FF: synthetic field
      public final Universe $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Option value() {
         return this.value;
      }

      public BooleanFlag copy(final Option value) {
         return this.scala$reflect$api$Printers$BooleanFlag$$$outer().new BooleanFlag(value);
      }

      public Option copy$default$1() {
         return this.value();
      }

      public String productPrefix() {
         return "BooleanFlag";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.value();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof BooleanFlag;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "value";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return scala.util.hashing.MurmurHash3..MODULE$.productHash(this, -889275714, false);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         if (this != x$1) {
            if (x$1 instanceof BooleanFlag && ((BooleanFlag)x$1).scala$reflect$api$Printers$BooleanFlag$$$outer() == this.scala$reflect$api$Printers$BooleanFlag$$$outer()) {
               BooleanFlag var2 = (BooleanFlag)x$1;
               Option var10000 = this.value();
               Option var3 = var2.value();
               if (var10000 == null) {
                  if (var3 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var3)) {
                  return false;
               }

               if (var2.canEqual(this)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      // $FF: synthetic method
      public Universe scala$reflect$api$Printers$BooleanFlag$$$outer() {
         return this.$outer;
      }

      public BooleanFlag(final Option value) {
         this.value = value;
         if (Printers.this == null) {
            throw null;
         } else {
            this.$outer = Printers.this;
            super();
         }
      }
   }

   public class BooleanFlag$ implements Serializable {
      // $FF: synthetic field
      private final Universe $outer;

      public BooleanFlag booleanToBooleanFlag(final boolean value) {
         return this.$outer.new BooleanFlag(new Some(value));
      }

      public BooleanFlag optionToBooleanFlag(final Option value) {
         return this.$outer.new BooleanFlag(value);
      }

      public BooleanFlag settingToBooleanFlag(final MutableSettings.SettingValue setting) {
         return this.$outer.new BooleanFlag(new Some(setting.value()));
      }

      public BooleanFlag apply(final Option value) {
         return this.$outer.new BooleanFlag(value);
      }

      public Option unapply(final BooleanFlag x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.value()));
      }

      public BooleanFlag$() {
         if (Printers.this == null) {
            throw null;
         } else {
            this.$outer = Printers.this;
            super();
         }
      }
   }
}
