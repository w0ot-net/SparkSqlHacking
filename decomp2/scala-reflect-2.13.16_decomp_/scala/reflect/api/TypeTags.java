package scala.reflect.api;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Equals;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.reflect.ScalaSignature;
import scala.reflect.runtime.JavaMirrors;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011eeAC={!\u0003\r\t!a\u0001\u0002`!9\u0011Q\u0002\u0001\u0005\u0002\u0005=a!CA\f\u0001A\u0005\u0019\u0011AA\r\u0011\u001d\tiA\u0001C\u0001\u0003\u001fA\u0011\"!\r\u0003\u0005\u00045\t!a\r\t\u000f\u0005\u0005#A\"\u0001\u0002D!9\u00111\u0011\u0002\u0007\u0002\u0005\u0015\u0005bBAI\u0005\u0011\u0005\u00131\u0013\u0005\b\u0003?\u0013A\u0011IAQ\u0011\u001d\t)K\u0001C!\u0003OCq!a,\u0003\t\u0003\n\tlB\u0004\u0002X\u0002A\t!!7\u0007\u000f\u0005]\u0001\u0001#\u0001\u0002\\\"9\u0011q\u001d\u0007\u0005\u0002\u0005%\b\"CAv\u0019\t\u0007I\u0011AAw\u0011!\t9\u0010\u0004Q\u0001\n\u0005=\b\"CA}\u0019\t\u0007I\u0011AA~\u0011!\u0011)\u0001\u0004Q\u0001\n\u0005u\b\"\u0003B\u0004\u0019\t\u0007I\u0011\u0001B\u0005\u0011!\u0011\u0019\u0002\u0004Q\u0001\n\t-\u0001\"\u0003B\u000b\u0019\t\u0007I\u0011\u0001B\f\u0011!\u0011Y\u0002\u0004Q\u0001\n\te\u0001\"\u0003B\u000f\u0019\t\u0007I\u0011\u0001B\u0010\u0011!\u0011I\u0003\u0004Q\u0001\n\t\u0005\u0002\"\u0003B\u0016\u0019\t\u0007I\u0011\u0001B\u0017\u0011!\u00119\u0004\u0004Q\u0001\n\t=\u0002\"\u0003B\u001d\u0019\t\u0007I\u0011\u0001B\u001e\u0011!\u0011)\u0005\u0004Q\u0001\n\tu\u0002\"\u0003B$\u0019\t\u0007I\u0011\u0001B%\u0011!\u0011i\u0005\u0004Q\u0001\n\t-\u0003\"\u0003B(\u0019\t\u0007I\u0011\u0001B)\u0011!\u0011)\u0006\u0004Q\u0001\n\tM\u0003\"\u0003B,\u0019\t\u0007I\u0011\u0001B-\u0011!\u0011i\u0006\u0004Q\u0001\n\tm\u0003\"\u0003B0\u0019\t\u0007I\u0011\u0001B1\u0011!\u0011Y\u0007\u0004Q\u0001\n\t\r\u0004\"\u0003B7\u0019\t\u0007I\u0011\u0001B8\u0011!\u0011\u0019\b\u0004Q\u0001\n\tE\u0004\"\u0003B;\u0019\t\u0007I\u0011\u0001B<\u0011!\u0011\t\t\u0004Q\u0001\n\te\u0004\"\u0003BB\u0019\t\u0007I\u0011\u0001BC\u0011!\u0011I\t\u0004Q\u0001\n\t\u001d\u0005\"\u0003BF\u0019\t\u0007I\u0011\u0001BG\u0011!\u00119\n\u0004Q\u0001\n\t=\u0005b\u0002BM\u0019\u0011\u0005!1\u0014\u0005\b\u0005kcA\u0011\u0001B\\\r\u0019\u0011Y\r\u0001\u0003\u0003N\"Q\u0011\u0011\u0007\u0018\u0003\u0006\u0004%\t!a\r\t\u0015\t]gF!A!\u0002\u0013\t)\u0004\u0003\u0006\u0003Z:\u0012)\u0019!C\u0001\u00057D!B!8/\u0005\u0003\u0005\u000b\u0011\u0002BX\u0011\u001d\t9O\fC\u0001\u0005?D!\"a!/\u0011\u000b\u0007I\u0011AAC\u0011\u001d\t\tE\fC\u0001\u0005ODqAa?/\t\u0013\u0011iPB\u0005\u0004\u0010\u0001\u0001\n1!\u0001\u0004\u0012!9\u0011QB\u001c\u0005\u0002\u0005=\u0001bBA!o\u0019\u000531\u0004\u0005\b\u0003#;D\u0011IB\u0018\u0011\u001d\tyj\u000eC!\u0007gAq!!*8\t\u0003\n9\u000bC\u0004\u00020^\"\t%!-\b\u000f\ru\u0002\u0001#\u0001\u0004@\u001991q\u0002\u0001\t\u0002\r\u0005\u0003bBAt\u007f\u0011\u000511\t\u0005\n\u0003W|$\u0019!C\u0001\u0007\u000bB\u0001\"a>@A\u0003%1q\t\u0005\n\u0003s|$\u0019!C\u0001\u0007\u0013B\u0001B!\u0002@A\u0003%11\n\u0005\n\u0005\u000fy$\u0019!C\u0001\u0007\u001bB\u0001Ba\u0005@A\u0003%1q\n\u0005\n\u0005+y$\u0019!C\u0001\u0007#B\u0001Ba\u0007@A\u0003%11\u000b\u0005\n\u0005;y$\u0019!C\u0001\u0007+B\u0001B!\u000b@A\u0003%1q\u000b\u0005\n\u0005Wy$\u0019!C\u0001\u00073B\u0001Ba\u000e@A\u0003%11\f\u0005\n\u0005sy$\u0019!C\u0001\u0007;B\u0001B!\u0012@A\u0003%1q\f\u0005\n\u0005\u000fz$\u0019!C\u0001\u0007CB\u0001B!\u0014@A\u0003%11\r\u0005\n\u0005\u001fz$\u0019!C\u0001\u0007KB\u0001B!\u0016@A\u0003%1q\r\u0005\n\u0005/z$\u0019!C\u0001\u0007SB\u0001B!\u0018@A\u0003%11\u000e\u0005\n\u0005?z$\u0019!C\u0001\u0007[B\u0001Ba\u001b@A\u0003%1q\u000e\u0005\n\u0005[z$\u0019!C\u0001\u0007cB\u0001Ba\u001d@A\u0003%11\u000f\u0005\n\u0005kz$\u0019!C\u0001\u0007kB\u0001B!!@A\u0003%1q\u000f\u0005\n\u0005\u0007{$\u0019!C\u0001\u0007sB\u0001B!#@A\u0003%11\u0010\u0005\n\u0005\u0017{$\u0019!C\u0001\u0007{B\u0001Ba&@A\u0003%1q\u0010\u0005\b\u00053{D\u0011ABA\u0011\u001d\u0011)l\u0010C\u0001\u0007\u001fC\u0011ba'@\u0005\u0004%Ia!(\t\u0011\r}u\b)A\u0005\u0003+C\u0001b!)\u0001\t\u0003a81\u0015\u0004\u0007\u0007c\u0003Aaa-\t\u0017\u0005EBM!A!\u0002\u0013\t)d\f\u0005\f\u00053$'\u0011!Q\u0001\n\t=\u0016\u0007C\u0004\u0002h\u0012$\taa0\t\u000f\u0005\u0005C\r\"\u0011\u0004H\"9!1 3\u0005\n\tuhABBo\u0001\u0011\u0019y\u000e\u0003\u0006\u0004d*\u0014\t\u0011)A\u0005\u0007KDq!a:k\t\u0003\u0019\t\u0010C\u0004\u0003\u001a*$\taa>\u0007\r\u0011%\u0002\u0001\u0002C\u0016\u0011)!)D\u001cB\u0001B\u0003%\u0011q\u0011\u0005\u000b\u0007Gt'\u0011!Q\u0001\n\u0011]\u0002bBAt]\u0012\u0005A1\b\u0005\u000b\u0003\u0007s\u0007R1A\u0005B\u0005\u0015\u0005b\u0002B~]\u0012%!Q \u0005\b\t\u000b\u0002A\u0011\u0001C$\u0011\u001d!)\u0006\u0001C\u0001\t/Bq\u0001b\u0019\u0001\t\u0003!)\u0007C\u0004\u0005r\u0001!\t\u0001b\u001d\t\u000f\u0011}\u0004A\"\u0001\u0005\u0002\nAA+\u001f9f)\u0006<7O\u0003\u0002|y\u0006\u0019\u0011\r]5\u000b\u0005ut\u0018a\u0002:fM2,7\r\u001e\u0006\u0002\u007f\u0006)1oY1mC\u000e\u00011c\u0001\u0001\u0002\u0006A!\u0011qAA\u0005\u001b\u0005q\u0018bAA\u0006}\n1\u0011I\\=SK\u001a\fa\u0001J5oSR$CCAA\t!\u0011\t9!a\u0005\n\u0007\u0005UaP\u0001\u0003V]&$(aC,fC.$\u0016\u0010]3UC\u001e,B!a\u0007\u0002pM9!!!\u0002\u0002\u001e\u0005\r\u0002\u0003BA\u0004\u0003?I1!!\t\u007f\u0005\u0019)\u0015/^1mgB!\u0011QEA\u0016\u001d\u0011\t9!a\n\n\u0007\u0005%b0A\u0004qC\u000e\\\u0017mZ3\n\t\u00055\u0012q\u0006\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0004\u0003Sq\u0018AB7jeJ|'/\u0006\u0002\u00026A!\u0011qGA\u001d\u001b\u0005\u0001\u0011\u0002BA\u001e\u0003{\u0011a!T5se>\u0014\u0018bAA u\n9Q*\u001b:s_J\u001c\u0018AA5o+\u0011\t)%!\u0014\u0015\t\u0005\u001d\u00131\u0010\t\u0006\u0003\u0013\u0012\u0011Q\u000e\t\u0005\u0003\u0017\ni\u0005\u0004\u0001\u0005\u000f\u0005=SA1\u0001\u0002R\t\tQ+\u0005\u0003\u0002T\u0005e\u0003\u0003BA\u0004\u0003+J1!a\u0016\u007f\u0005\u001dqu\u000e\u001e5j]\u001e\u0014b!a\u0017\u0002`\u0005\u001ddABA/\u0005\u0001\tIF\u0001\u0007=e\u00164\u0017N\\3nK:$h\b\u0005\u0003\u0002b\u0005\rT\"\u0001>\n\u0007\u0005\u0015$P\u0001\u0005V]&4XM]:f!\u0011\t9!!\u001b\n\u0007\u0005-dPA\u0005TS:<G.\u001a;p]B!\u00111JA8\t\u001d\t\tH\u0001b\u0001\u0003g\u0012\u0011\u0001V\t\u0005\u0003'\n)\b\u0005\u0003\u0002\b\u0005]\u0014bAA=}\n\u0019\u0011I\\=\t\u000f\u0005uT\u00011\u0001\u0002\u0000\u0005Yq\u000e\u001e5fe6K'O]8s!\u0019\t\t'!!\u0002J%\u0019\u00111\b>\u0002\u0007Q\u0004X-\u0006\u0002\u0002\bB!\u0011qGAE\u0013\u0011\tY)!$\u0003\tQK\b/Z\u0005\u0004\u0003\u001fS(!\u0002+za\u0016\u001c\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005U\u00151\u0014\t\u0005\u0003\u000f\t9*C\u0002\u0002\u001az\u0014qAQ8pY\u0016\fg\u000eC\u0004\u0002\u001e\u001e\u0001\r!!\u001e\u0002\u0003a\fa!Z9vC2\u001cH\u0003BAK\u0003GCq!!(\t\u0001\u0004\t)(\u0001\u0005iCND7i\u001c3f)\t\tI\u000b\u0005\u0003\u0002\b\u0005-\u0016bAAW}\n\u0019\u0011J\u001c;\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a-\u0011\t\u0005U\u0016qX\u0007\u0003\u0003oSA!!/\u0002<\u0006!A.\u00198h\u0015\t\ti,\u0001\u0003kCZ\f\u0017\u0002BAa\u0003o\u0013aa\u0015;sS:<\u0007f\u0002\u0002\u0002F\u0006E\u00171\u001b\t\u0005\u0003\u000f\fi-\u0004\u0002\u0002J*\u0019\u00111\u001a@\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002P\u0006%'\u0001E5na2L7-\u001b;O_R4u.\u001e8e\u0003\ri7oZ\u0011\u0003\u0003+\f\u0011ET8!/\u0016\f7\u000eV=qKR\u000bw\rI1wC&d\u0017M\u00197fA\u0019|'\u000f\t\u0013|)v\f1bV3bWRK\b/\u001a+bOB\u0019\u0011q\u0007\u0007\u0014\u000b1\t)!!8\u0011\t\u0005}\u0017Q]\u0007\u0003\u0003CTA!a9\u0002<\u0006\u0011\u0011n\\\u0005\u0005\u0003[\t\t/\u0001\u0004=S:LGO\u0010\u000b\u0003\u00033\fAAQ=uKV\u0011\u0011q\u001e\t\u0006\u0003o\u0011\u0011\u0011\u001f\t\u0005\u0003\u000f\t\u00190C\u0002\u0002vz\u0014AAQ=uK\u0006)!)\u001f;fA\u0005)1\u000b[8siV\u0011\u0011Q \t\u0006\u0003o\u0011\u0011q \t\u0005\u0003\u000f\u0011\t!C\u0002\u0003\u0004y\u0014Qa\u00155peR\faa\u00155peR\u0004\u0013\u0001B\"iCJ,\"Aa\u0003\u0011\u000b\u0005]\"A!\u0004\u0011\t\u0005\u001d!qB\u0005\u0004\u0005#q(\u0001B\"iCJ\fQa\u00115be\u0002\n1!\u00138u+\t\u0011I\u0002E\u0003\u00028\t\tI+\u0001\u0003J]R\u0004\u0013\u0001\u0002'p]\u001e,\"A!\t\u0011\u000b\u0005]\"Aa\t\u0011\t\u0005\u001d!QE\u0005\u0004\u0005Oq(\u0001\u0002'p]\u001e\fQ\u0001T8oO\u0002\nQA\u00127pCR,\"Aa\f\u0011\u000b\u0005]\"A!\r\u0011\t\u0005\u001d!1G\u0005\u0004\u0005kq(!\u0002$m_\u0006$\u0018A\u0002$m_\u0006$\b%\u0001\u0004E_V\u0014G.Z\u000b\u0003\u0005{\u0001R!a\u000e\u0003\u0005\u007f\u0001B!a\u0002\u0003B%\u0019!1\t@\u0003\r\u0011{WO\u00197f\u0003\u001d!u.\u001e2mK\u0002\nqAQ8pY\u0016\fg.\u0006\u0002\u0003LA)\u0011q\u0007\u0002\u0002\u0016\u0006A!i\\8mK\u0006t\u0007%\u0001\u0003V]&$XC\u0001B*!\u0015\t9DAA\t\u0003\u0015)f.\u001b;!\u0003\r\te._\u000b\u0003\u00057\u0002R!a\u000e\u0003\u0003k\nA!\u00118zA\u00051\u0011I\\=WC2,\"Aa\u0019\u0011\u000b\u0005]\"A!\u001a\u0011\t\u0005\u001d!qM\u0005\u0004\u0005Sr(AB!osZ\u000bG.A\u0004B]f4\u0016\r\u001c\u0011\u0002\r\u0005s\u0017PU3g+\t\u0011\t\bE\u0003\u00028\t\t)!A\u0004B]f\u0014VM\u001a\u0011\u0002\r=\u0013'.Z2u+\t\u0011I\bE\u0003\u00028\t\u0011Y\b\u0005\u0003\u00026\nu\u0014\u0002\u0002B@\u0003o\u0013aa\u00142kK\u000e$\u0018aB(cU\u0016\u001cG\u000fI\u0001\b\u001d>$\b.\u001b8h+\t\u00119\tE\u0003\u00028\t\t\u0019&\u0001\u0005O_RD\u0017N\\4!\u0003\u0011qU\u000f\u001c7\u0016\u0005\t=\u0005#BA\u001c\u0005\tE\u0005\u0003BA\u0004\u0005'K1A!&\u007f\u0005\u0011qU\u000f\u001c7\u0002\u000b9+H\u000e\u001c\u0011\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\tu%1\u0015\u000b\u0007\u0005?\u0013)Ka+\u0011\u000b\u0005]\"A!)\u0011\t\u0005-#1\u0015\u0003\b\u0003cb#\u0019AA:\u0011\u001d\u00119\u000b\fa\u0001\u0005S\u000bq!\\5se>\u0014\u0018\u0007\u0005\u0004\u0002b\u0005\u0005\u0015q\u0007\u0005\b\u0005[c\u0003\u0019\u0001BX\u0003\u0015!\b/Z22!\u0011\t\tG!-\n\u0007\tM&PA\u0006UsB,7I]3bi>\u0014\u0018aB;oCB\u0004H._\u000b\u0005\u0005s\u0013I\r\u0006\u0003\u0003<\n\u0005\u0007CBA\u0004\u0005{\u000b9)C\u0002\u0003@z\u0014aa\u00149uS>t\u0007b\u0002Bb[\u0001\u0007!QY\u0001\u0005iR\fw\rE\u0003\u00028\t\u00119\r\u0005\u0003\u0002L\t%GaBA9[\t\u0007\u00111\u000f\u0002\u0010/\u0016\f7\u000eV=qKR\u000bw-S7qYV!!q\u001aBk'\u0015q\u0013Q\u0001Bi!\u0015\t9D\u0001Bj!\u0011\tYE!6\u0005\u000f\u0005EdF1\u0001\u0002t\u00059Q.\u001b:s_J\u0004\u0013\u0001\u0002;qK\u000e,\"Aa,\u0002\u000bQ\u0004Xm\u0019\u0011\u0015\r\t\u0005(1\u001dBs!\u0015\t9D\fBj\u0011\u001d\t\td\ra\u0001\u0003kAqA!74\u0001\u0004\u0011y+\u0006\u0003\u0003j\n=H\u0003\u0002Bv\u0005o\u0004RA!<\u0003\u0005'\u0004B!a\u0013\u0003p\u00129\u0011qJ\u001bC\u0002\tE\u0018\u0003BA*\u0005g\u0014bA!>\u0002`\u0005\u001ddABA/]\u0001\u0011\u0019\u0010C\u0004\u0002~U\u0002\rA!?\u0011\r\u0005\u0005\u0014\u0011\u0011Bw\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t)\u0001K\u00037\u0007\u0003\u0019i\u0001\u0005\u0004\u0002\b\r\r1qA\u0005\u0004\u0007\u000bq(A\u0002;ie><8\u000f\u0005\u0003\u0002`\u000e%\u0011\u0002BB\u0006\u0003C\u0014Qc\u00142kK\u000e$8\u000b\u001e:fC6,\u0005pY3qi&|gn\t\u0002\u0004\b\t9A+\u001f9f)\u0006<W\u0003BB\n\u00073\u0019\u0012bNA\u0003\u0007+\ti\"a\t\u0011\u000b\u0005]\"aa\u0006\u0011\t\u0005-3\u0011\u0004\u0003\b\u0003c:$\u0019AA:+\u0011\u0019iba\t\u0015\t\r}11\u0006\t\u0006\u0007C94q\u0003\t\u0005\u0003\u0017\u001a\u0019\u0003B\u0004\u0002Pe\u0012\ra!\n\u0012\t\u0005M3q\u0005\n\u0007\u0007S\ty&a\u001a\u0007\r\u0005us\u0007AB\u0014\u0011\u001d\ti(\u000fa\u0001\u0007[\u0001b!!\u0019\u0002\u0002\u000e\u0005B\u0003BAK\u0007cAq!!(;\u0001\u0004\t)\b\u0006\u0003\u0002\u0016\u000eU\u0002bBAOw\u0001\u0007\u0011Q\u000f\u0015\bo\u0005\u0015\u0017\u0011[B\u001dC\t\u0019Y$A\u000fO_\u0002\"\u0016\u0010]3UC\u001e\u0004\u0013M^1jY\u0006\u0014G.\u001a\u0011g_J\u0004Ce\u001f+~\u0003\u001d!\u0016\u0010]3UC\u001e\u00042!a\u000e@'\u0015y\u0014QAAo)\t\u0019y$\u0006\u0002\u0004HA)\u0011qG\u001c\u0002rV\u001111\n\t\u0006\u0003o9\u0014q`\u000b\u0003\u0007\u001f\u0002R!a\u000e8\u0005\u001b)\"aa\u0015\u0011\u000b\u0005]r'!+\u0016\u0005\r]\u0003#BA\u001co\t\rRCAB.!\u0015\t9d\u000eB\u0019+\t\u0019y\u0006E\u0003\u00028]\u0012y$\u0006\u0002\u0004dA)\u0011qG\u001c\u0002\u0016V\u00111q\r\t\u0006\u0003o9\u0014\u0011C\u000b\u0003\u0007W\u0002R!a\u000e8\u0003k*\"aa\u001c\u0011\u000b\u0005]rG!\u001a\u0016\u0005\rM\u0004#BA\u001co\u0005\u0015QCAB<!\u0015\t9d\u000eB>+\t\u0019Y\bE\u0003\u00028]\n\u0019&\u0006\u0002\u0004\u0000A)\u0011qG\u001c\u0003\u0012V!11QBE)\u0019\u0019)ia#\u0004\u000eB)\u0011qG\u001c\u0004\bB!\u00111JBE\t\u001d\t\th\u0018b\u0001\u0003gBqAa*`\u0001\u0004\u0011I\u000bC\u0004\u0003.~\u0003\rAa,\u0016\t\rE5\u0011\u0014\u000b\u0005\u0005w\u001b\u0019\nC\u0004\u0003D\u0002\u0004\ra!&\u0011\u000b\u0005]rga&\u0011\t\u0005-3\u0011\u0014\u0003\b\u0003c\u0002'\u0019AA:\u0003e\u0019\u0017m\u00195f\u001b\u0006$XM]5bY&TX\r\u001a+za\u0016$\u0016mZ:\u0016\u0005\u0005U\u0015AG2bG\",W*\u0019;fe&\fG.\u001b>fIRK\b/\u001a+bON\u0004\u0013a\u0003+za\u0016$\u0016mZ%na2,Ba!*\u0004,R11qUBW\u0007_\u0003R!a\u000e8\u0007S\u0003B!a\u0013\u0004,\u00129\u0011\u0011O2C\u0002\u0005M\u0004bBA\u0019G\u0002\u0007\u0011Q\u0007\u0005\b\u00053\u001c\u0007\u0019\u0001BX\u0005-!\u0016\u0010]3UC\u001eLU\u000e\u001d7\u0016\t\rU61X\n\u0006I\u000e]6Q\u0018\t\u0006\u0003oq3\u0011\u0018\t\u0005\u0003\u0017\u001aY\fB\u0004\u0002r\u0011\u0014\r!a\u001d\u0011\u000b\u0005]rg!/\u0015\r\r\u000571YBc!\u0015\t9\u0004ZB]\u0011\u001d\t\td\u001aa\u0001\u0003kAqA!7h\u0001\u0004\u0011y+\u0006\u0003\u0004J\u000e=G\u0003BBf\u0007/\u0004Ra!48\u0007s\u0003B!a\u0013\u0004P\u00129\u0011q\n5C\u0002\rE\u0017\u0003BA*\u0007'\u0014ba!6\u0002`\u0005\u001ddABA/I\u0002\u0019\u0019\u000eC\u0004\u0002~!\u0004\ra!7\u0011\r\u0005\u0005\u0014\u0011QBgQ\u0015I7\u0011AB\u0007\u0005E\u0001&/\u001a3fMRK\b/Z\"sK\u0006$xN]\u000b\u0005\u0007C\u001cyoE\u0002k\u0005_\u000baaY8qs&s\u0007\u0003CA\u0004\u0007O\fyfa;\n\u0007\r%hPA\u0005Gk:\u001cG/[8ocA)\u0011qL\u001c\u0004nB!\u00111JBx\t\u001d\t\tH\u001bb\u0001\u0003g\"Baa=\u0004vB)\u0011q\u00076\u0004n\"911\u001d7A\u0002\r\u0015X\u0003BB}\u0007\u007f$Baa?\u0005\bA!1Q`AE!\u0011\tYea@\u0005\u000f\u0005=SN1\u0001\u0005\u0002E!\u00111\u000bC\u0002%\u0019!)!a\u0018\u0002h\u00191\u0011Q\f6\u0001\t\u0007Aq\u0001\"\u0003n\u0001\u0004!Y!A\u0001n!\u0019\t\t'!!\u0004~\"Z!\u000eb\u0004\u0005\u0016\u0011]A1\u0004C\u000f!\u0011\t9\u0001\"\u0005\n\u0007\u0011MaP\u0001\u0006eKB\u0014XmY1uK\u0012\fq!\\3tg\u0006<W-\t\u0002\u0005\u001a\u0005\u0011F\u000b[5tA\rd\u0017m]:!_:d\u0017\u0010I3ySN$8\u000f\t;pAMLG.\u001a8dK\u0002j\u0015*T!!G>l\u0007\u000f\\1j]&tw\rI1c_V$\b%\u0019\u0011cS:\f'/\u001f\u0011j]\u000e|W\u000e]1uS\nLG.\u001b;z]\u0005)1/\u001b8dK\u0006\u0012AqD\u0001\bM>\u0014XM^3sQ\rQG1\u0005\t\u0005\u0003\u000f$)#\u0003\u0003\u0005(\u0005%'AB;okN,GMA\u0007Qe\u0016$WM\u001a+za\u0016$\u0016mZ\u000b\u0005\t[!\u0019dE\u0002o\t_\u0001R!a\u000ee\tc\u0001B!a\u0013\u00054\u00119\u0011\u0011\u000f8C\u0002\u0005M\u0014\u0001B0ua\u0016\u0004\u0002\"a\u0002\u0004h\u0006}C\u0011\b\t\u0006\u0003?:D\u0011\u0007\u000b\u0007\t{!y\u0004\"\u0011\u0011\u000b\u0005]b\u000e\"\r\t\u000f\u0011U\u0012\u000f1\u0001\u0002\b\"911]9A\u0002\u0011]\u0002&B:\u0004\u0002\r5\u0011aC<fC.$\u0016\u0010]3UC\u001e,B\u0001\"\u0013\u0005PQ!A1\nC)!\u0015\t9D\u0001C'!\u0011\tY\u0005b\u0014\u0005\u000f\u0005EDO1\u0001\u0002t!9A1\u000b;A\u0004\u0011-\u0013!B1ui\u0006<\u0017a\u0002;za\u0016$\u0016mZ\u000b\u0005\t3\"y\u0006\u0006\u0003\u0005\\\u0011\u0005\u0004#BA\u001co\u0011u\u0003\u0003BA&\t?\"q!!\u001dv\u0005\u0004\t\u0019\bC\u0004\u0003DV\u0004\u001d\u0001b\u0017\u0002\u0015],\u0017m\u001b+za\u0016|e-\u0006\u0003\u0005h\u0011=D\u0003BAD\tSBq\u0001b\u0015w\u0001\b!Y\u0007E\u0003\u00028\t!i\u0007\u0005\u0003\u0002L\u0011=DaBA9m\n\u0007\u00111O\u0001\u0007if\u0004Xm\u00144\u0016\t\u0011UDQ\u0010\u000b\u0005\u0003\u000f#9\bC\u0004\u0003D^\u0004\u001d\u0001\"\u001f\u0011\u000b\u0005]r\u0007b\u001f\u0011\t\u0005-CQ\u0010\u0003\b\u0003c:(\u0019AA:\u0003!\u0019\u00180\u001c2pY>3W\u0003\u0002CB\t/#B\u0001\"\"\u0005\u0010B!\u0011q\u0007CD\u0013\u0011!I\tb#\u0003\u0015QK\b/Z*z[\n|G.C\u0002\u0005\u000ej\u0014qaU=nE>d7\u000fC\u0005\u0005\u0012b\f\t\u0011q\u0001\u0005\u0014\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\u000b\u0005]\"\u0001\"&\u0011\t\u0005-Cq\u0013\u0003\b\u0003cB(\u0019AA:\u0001"
)
public interface TypeTags {
   WeakTypeTag$ WeakTypeTag();

   TypeTag$ TypeTag();

   // $FF: synthetic method
   static TypeTag TypeTagImpl$(final TypeTags $this, final Mirror mirror, final TypeCreator tpec) {
      return $this.TypeTagImpl(mirror, tpec);
   }

   default TypeTag TypeTagImpl(final Mirror mirror, final TypeCreator tpec) {
      return (Universe)this.new TypeTagImpl(mirror, tpec);
   }

   // $FF: synthetic method
   static WeakTypeTag weakTypeTag$(final TypeTags $this, final WeakTypeTag attag) {
      return $this.weakTypeTag(attag);
   }

   default WeakTypeTag weakTypeTag(final WeakTypeTag attag) {
      return attag;
   }

   // $FF: synthetic method
   static TypeTag typeTag$(final TypeTags $this, final TypeTag ttag) {
      return $this.typeTag(ttag);
   }

   default TypeTag typeTag(final TypeTag ttag) {
      return ttag;
   }

   // $FF: synthetic method
   static Types.TypeApi weakTypeOf$(final TypeTags $this, final WeakTypeTag attag) {
      return $this.weakTypeOf(attag);
   }

   default Types.TypeApi weakTypeOf(final WeakTypeTag attag) {
      return attag.tpe();
   }

   // $FF: synthetic method
   static Types.TypeApi typeOf$(final TypeTags $this, final TypeTag ttag) {
      return $this.typeOf(ttag);
   }

   default Types.TypeApi typeOf(final TypeTag ttag) {
      return ttag.tpe();
   }

   Symbols.TypeSymbolApi symbolOf(final WeakTypeTag evidence$1);

   static void $init$(final TypeTags $this) {
   }

   public interface WeakTypeTag extends Equals, Serializable {
      Mirror mirror();

      WeakTypeTag in(final Mirror otherMirror);

      Types.TypeApi tpe();

      default boolean canEqual(final Object x) {
         return x instanceof WeakTypeTag;
      }

      default boolean equals(final Object x) {
         if (x instanceof WeakTypeTag) {
            Mirror var10000 = this.mirror();
            Mirror var2 = ((WeakTypeTag)x).mirror();
            if (var10000 == null) {
               if (var2 != null) {
                  return false;
               }
            } else if (!var10000.equals(var2)) {
               return false;
            }

            Types.TypeApi var4 = this.tpe();
            Types.TypeApi var3 = ((WeakTypeTag)x).tpe();
            if (var4 == null) {
               if (var3 == null) {
                  return true;
               }
            } else if (var4.equals(var3)) {
               return true;
            }
         }

         return false;
      }

      default int hashCode() {
         return this.mirror().hashCode() * 31 + this.tpe().hashCode();
      }

      default String toString() {
         return (new StringBuilder(13)).append("WeakTypeTag[").append(this.tpe()).append("]").toString();
      }

      // $FF: synthetic method
      TypeTags scala$reflect$api$TypeTags$WeakTypeTag$$$outer();

      static void $init$(final WeakTypeTag $this) {
      }
   }

   public class WeakTypeTag$ implements Serializable {
      private final WeakTypeTag Byte;
      private final WeakTypeTag Short;
      private final WeakTypeTag Char;
      private final WeakTypeTag Int;
      private final WeakTypeTag Long;
      private final WeakTypeTag Float;
      private final WeakTypeTag Double;
      private final WeakTypeTag Boolean;
      private final WeakTypeTag Unit;
      private final WeakTypeTag Any;
      private final WeakTypeTag AnyVal;
      private final WeakTypeTag AnyRef;
      private final WeakTypeTag Object;
      private final WeakTypeTag Nothing;
      private final WeakTypeTag Null;
      // $FF: synthetic field
      private final Universe $outer;

      public WeakTypeTag Byte() {
         return this.Byte;
      }

      public WeakTypeTag Short() {
         return this.Short;
      }

      public WeakTypeTag Char() {
         return this.Char;
      }

      public WeakTypeTag Int() {
         return this.Int;
      }

      public WeakTypeTag Long() {
         return this.Long;
      }

      public WeakTypeTag Float() {
         return this.Float;
      }

      public WeakTypeTag Double() {
         return this.Double;
      }

      public WeakTypeTag Boolean() {
         return this.Boolean;
      }

      public WeakTypeTag Unit() {
         return this.Unit;
      }

      public WeakTypeTag Any() {
         return this.Any;
      }

      public WeakTypeTag AnyVal() {
         return this.AnyVal;
      }

      public WeakTypeTag AnyRef() {
         return this.AnyRef;
      }

      public WeakTypeTag Object() {
         return this.Object;
      }

      public WeakTypeTag Nothing() {
         return this.Nothing;
      }

      public WeakTypeTag Null() {
         return this.Null;
      }

      public WeakTypeTag apply(final Mirror mirror1, final TypeCreator tpec1) {
         return this.$outer.new WeakTypeTagImpl(mirror1, tpec1);
      }

      public Option unapply(final WeakTypeTag ttag) {
         return new Some(ttag.tpe());
      }

      public WeakTypeTag$() {
         if (TypeTags.this == null) {
            throw null;
         } else {
            this.$outer = TypeTags.this;
            super();
            this.Byte = TypeTags.this.TypeTag().Byte();
            this.Short = TypeTags.this.TypeTag().Short();
            this.Char = TypeTags.this.TypeTag().Char();
            this.Int = TypeTags.this.TypeTag().Int();
            this.Long = TypeTags.this.TypeTag().Long();
            this.Float = TypeTags.this.TypeTag().Float();
            this.Double = TypeTags.this.TypeTag().Double();
            this.Boolean = TypeTags.this.TypeTag().Boolean();
            this.Unit = TypeTags.this.TypeTag().Unit();
            this.Any = TypeTags.this.TypeTag().Any();
            this.AnyVal = TypeTags.this.TypeTag().AnyVal();
            this.AnyRef = TypeTags.this.TypeTag().AnyRef();
            this.Object = TypeTags.this.TypeTag().Object();
            this.Nothing = TypeTags.this.TypeTag().Nothing();
            this.Null = TypeTags.this.TypeTag().Null();
         }
      }
   }

   private class WeakTypeTagImpl implements WeakTypeTag {
      private Types.TypeApi tpe;
      private final Mirror mirror;
      private final TypeCreator tpec;
      private volatile boolean bitmap$0;
      // $FF: synthetic field
      public final Universe $outer;

      public boolean canEqual(final Object x) {
         return TypeTags.WeakTypeTag.super.canEqual(x);
      }

      public boolean equals(final Object x) {
         return TypeTags.WeakTypeTag.super.equals(x);
      }

      public int hashCode() {
         return TypeTags.WeakTypeTag.super.hashCode();
      }

      public String toString() {
         return TypeTags.WeakTypeTag.super.toString();
      }

      public Mirror mirror() {
         return this.mirror;
      }

      public TypeCreator tpec() {
         return this.tpec;
      }

      private Types.TypeApi tpe$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               this.tpe = this.tpec().apply(this.mirror());
               this.bitmap$0 = true;
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.tpe;
      }

      public Types.TypeApi tpe() {
         return !this.bitmap$0 ? this.tpe$lzycompute() : this.tpe;
      }

      public WeakTypeTag in(final Mirror otherMirror) {
         return otherMirror.universe().WeakTypeTag().apply(otherMirror, this.tpec());
      }

      private Object writeReplace() throws ObjectStreamException {
         return new SerializedTypeTag(this.tpec(), false);
      }

      // $FF: synthetic method
      public Universe scala$reflect$api$TypeTags$WeakTypeTagImpl$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public TypeTags scala$reflect$api$TypeTags$WeakTypeTag$$$outer() {
         return this.scala$reflect$api$TypeTags$WeakTypeTagImpl$$$outer();
      }

      public WeakTypeTagImpl(final Mirror mirror, final TypeCreator tpec) {
         this.mirror = mirror;
         this.tpec = tpec;
         if (TypeTags.this == null) {
            throw null;
         } else {
            this.$outer = TypeTags.this;
            super();
         }
      }
   }

   public interface TypeTag extends WeakTypeTag {
      TypeTag in(final Mirror otherMirror);

      default boolean canEqual(final Object x) {
         return x instanceof TypeTag;
      }

      default boolean equals(final Object x) {
         if (x instanceof TypeTag) {
            Mirror var10000 = this.mirror();
            Mirror var2 = ((TypeTag)x).mirror();
            if (var10000 == null) {
               if (var2 != null) {
                  return false;
               }
            } else if (!var10000.equals(var2)) {
               return false;
            }

            Types.TypeApi var4 = this.tpe();
            Types.TypeApi var3 = ((TypeTag)x).tpe();
            if (var4 == null) {
               if (var3 == null) {
                  return true;
               }
            } else if (var4.equals(var3)) {
               return true;
            }
         }

         return false;
      }

      default int hashCode() {
         return this.mirror().hashCode() * 31 + this.tpe().hashCode();
      }

      default String toString() {
         return (new StringBuilder(9)).append("TypeTag[").append(this.tpe()).append("]").toString();
      }

      // $FF: synthetic method
      TypeTags scala$reflect$api$TypeTags$TypeTag$$$outer();

      static void $init$(final TypeTag $this) {
      }
   }

   public class TypeTag$ implements Serializable {
      private final TypeTag Byte;
      private final TypeTag Short;
      private final TypeTag Char;
      private final TypeTag Int;
      private final TypeTag Long;
      private final TypeTag Float;
      private final TypeTag Double;
      private final TypeTag Boolean;
      private final TypeTag Unit;
      private final TypeTag Any;
      private final TypeTag AnyVal;
      private final TypeTag AnyRef;
      private final TypeTag Object;
      private final TypeTag Nothing;
      private final TypeTag Null;
      private final boolean cacheMaterializedTypeTags;
      // $FF: synthetic field
      private final Universe $outer;

      public TypeTag Byte() {
         return this.Byte;
      }

      public TypeTag Short() {
         return this.Short;
      }

      public TypeTag Char() {
         return this.Char;
      }

      public TypeTag Int() {
         return this.Int;
      }

      public TypeTag Long() {
         return this.Long;
      }

      public TypeTag Float() {
         return this.Float;
      }

      public TypeTag Double() {
         return this.Double;
      }

      public TypeTag Boolean() {
         return this.Boolean;
      }

      public TypeTag Unit() {
         return this.Unit;
      }

      public TypeTag Any() {
         return this.Any;
      }

      public TypeTag AnyVal() {
         return this.AnyVal;
      }

      public TypeTag AnyRef() {
         return this.AnyRef;
      }

      public TypeTag Object() {
         return this.Object;
      }

      public TypeTag Nothing() {
         return this.Nothing;
      }

      public TypeTag Null() {
         return this.Null;
      }

      public TypeTag apply(final Mirror mirror1, final TypeCreator tpec1) {
         if (mirror1 instanceof JavaMirrors.JavaMirror) {
            JavaMirrors.JavaMirror var3 = (JavaMirrors.JavaMirror)mirror1;
            if (this.cacheMaterializedTypeTags() && tpec1.getClass().getName().contains("$typecreator") && tpec1.getClass().getDeclaredFields().length == 0) {
               return var3.typeTag(tpec1);
            }
         }

         return this.$outer.new TypeTagImpl(mirror1, tpec1);
      }

      public Option unapply(final TypeTag ttag) {
         return new Some(ttag.tpe());
      }

      private boolean cacheMaterializedTypeTags() {
         return this.cacheMaterializedTypeTags;
      }

      public TypeTag$() {
         if (TypeTags.this == null) {
            throw null;
         } else {
            this.$outer = TypeTags.this;
            super();
            this.Byte = TypeTags.this.new PredefTypeTag(TypeTags.this.definitions().ByteTpe(), (x$1) -> x$1.TypeTag().Byte());
            this.Short = TypeTags.this.new PredefTypeTag(TypeTags.this.definitions().ShortTpe(), (x$2) -> x$2.TypeTag().Short());
            this.Char = TypeTags.this.new PredefTypeTag(TypeTags.this.definitions().CharTpe(), (x$3) -> x$3.TypeTag().Char());
            this.Int = TypeTags.this.new PredefTypeTag(TypeTags.this.definitions().IntTpe(), (x$4) -> x$4.TypeTag().Int());
            this.Long = TypeTags.this.new PredefTypeTag(TypeTags.this.definitions().LongTpe(), (x$5) -> x$5.TypeTag().Long());
            this.Float = TypeTags.this.new PredefTypeTag(TypeTags.this.definitions().FloatTpe(), (x$6) -> x$6.TypeTag().Float());
            this.Double = TypeTags.this.new PredefTypeTag(TypeTags.this.definitions().DoubleTpe(), (x$7) -> x$7.TypeTag().Double());
            this.Boolean = TypeTags.this.new PredefTypeTag(TypeTags.this.definitions().BooleanTpe(), (x$8) -> x$8.TypeTag().Boolean());
            this.Unit = TypeTags.this.new PredefTypeTag(TypeTags.this.definitions().UnitTpe(), (x$9) -> x$9.TypeTag().Unit());
            this.Any = TypeTags.this.new PredefTypeTag(TypeTags.this.definitions().AnyTpe(), (x$10) -> x$10.TypeTag().Any());
            this.AnyVal = TypeTags.this.new PredefTypeTag(TypeTags.this.definitions().AnyValTpe(), (x$11) -> x$11.TypeTag().AnyVal());
            this.AnyRef = TypeTags.this.new PredefTypeTag(TypeTags.this.definitions().AnyRefTpe(), (x$12) -> x$12.TypeTag().AnyRef());
            this.Object = TypeTags.this.new PredefTypeTag(TypeTags.this.definitions().ObjectTpe(), (x$13) -> x$13.TypeTag().Object());
            this.Nothing = TypeTags.this.new PredefTypeTag(TypeTags.this.definitions().NothingTpe(), (x$14) -> x$14.TypeTag().Nothing());
            this.Null = TypeTags.this.new PredefTypeTag(TypeTags.this.definitions().NullTpe(), (x$15) -> x$15.TypeTag().Null());
            this.cacheMaterializedTypeTags = !Boolean.getBoolean("scala.reflect.runtime.disable.typetag.cache");
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private class TypeTagImpl extends WeakTypeTagImpl implements TypeTag {
      public boolean canEqual(final Object x) {
         return TypeTags.TypeTag.super.canEqual(x);
      }

      public boolean equals(final Object x) {
         return TypeTags.TypeTag.super.equals(x);
      }

      public int hashCode() {
         return TypeTags.TypeTag.super.hashCode();
      }

      public String toString() {
         return TypeTags.TypeTag.super.toString();
      }

      public TypeTag in(final Mirror otherMirror) {
         return otherMirror.universe().TypeTag().apply(otherMirror, super.tpec());
      }

      private Object writeReplace() throws ObjectStreamException {
         return new SerializedTypeTag(super.tpec(), true);
      }

      // $FF: synthetic method
      public Universe scala$reflect$api$TypeTags$TypeTagImpl$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public TypeTags scala$reflect$api$TypeTags$TypeTag$$$outer() {
         return this.scala$reflect$api$TypeTags$TypeTagImpl$$$outer();
      }

      public TypeTagImpl(final Mirror mirror, final TypeCreator tpec) {
         super(mirror, tpec);
      }
   }

   /** @deprecated */
   private class PredefTypeCreator extends TypeCreator {
      private final Function1 copyIn;
      // $FF: synthetic field
      public final Universe $outer;

      public Types.TypeApi apply(final Mirror m) {
         return ((TypeTag)this.copyIn.apply(m.universe())).tpe();
      }

      // $FF: synthetic method
      public Universe scala$reflect$api$TypeTags$PredefTypeCreator$$$outer() {
         return this.$outer;
      }

      public PredefTypeCreator(final Function1 copyIn) {
         this.copyIn = copyIn;
         if (TypeTags.this == null) {
            throw null;
         } else {
            this.$outer = TypeTags.this;
            super();
         }
      }
   }

   private class PredefTypeTag extends TypeTagImpl {
      private Types.TypeApi tpe;
      private Types.TypeApi _tpe;
      private volatile boolean bitmap$0;

      private Types.TypeApi tpe$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               this.tpe = this._tpe;
               this.bitmap$0 = true;
            }
         } catch (Throwable var2) {
            throw var2;
         }

         this._tpe = null;
         return this.tpe;
      }

      public Types.TypeApi tpe() {
         return !this.bitmap$0 ? this.tpe$lzycompute() : this.tpe;
      }

      private Object writeReplace() throws ObjectStreamException {
         return new SerializedTypeTag(this.tpec(), true);
      }

      // $FF: synthetic method
      public Universe scala$reflect$api$TypeTags$PredefTypeTag$$$outer() {
         return this.$outer;
      }

      public PredefTypeTag(final Types.TypeApi _tpe, final Function1 copyIn) {
         this._tpe = _tpe;
         super(TypeTags.this.rootMirror(), new scala.reflect.api.PredefTypeCreator(copyIn));
      }
   }
}
