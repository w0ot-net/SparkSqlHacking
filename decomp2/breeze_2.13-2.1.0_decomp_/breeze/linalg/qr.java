package breeze.linalg;

import breeze.generic.UFunc;
import java.io.Serializable;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\rEs!B.]\u0011\u0003\tg!B2]\u0011\u0003!\u0007\"B9\u0002\t\u0003\u0011h\u0001B:\u0002\u0001RD!\"a\u0003\u0004\u0005+\u0007I\u0011AA\u0007\u0011)\t)c\u0001B\tB\u0003%\u0011q\u0002\u0005\u000b\u0003O\u0019!Q3A\u0005\u0002\u00055\u0001BCA\u0015\u0007\tE\t\u0015!\u0003\u0002\u0010!1\u0011o\u0001C\u0001\u0003WA\u0011\"!\u000e\u0004\u0003\u0003%\t!a\u000e\t\u0013\u0005\u00153!%A\u0005\u0002\u0005\u001d\u0003\"CA1\u0007E\u0005I\u0011AA2\u0011%\t9gAA\u0001\n\u0003\nI\u0007C\u0005\u0002|\r\t\t\u0011\"\u0001\u0002~!I\u0011QQ\u0002\u0002\u0002\u0013\u0005\u0011q\u0011\u0005\n\u0003\u001b\u001b\u0011\u0011!C!\u0003\u001fC\u0011\"!(\u0004\u0003\u0003%\t!a(\t\u0013\u0005%6!!A\u0005B\u0005-\u0006\"CAX\u0007\u0005\u0005I\u0011IAY\u0011%\t\u0019lAA\u0001\n\u0003\n)\fC\u0005\u00028\u000e\t\t\u0011\"\u0011\u0002:\u001eI\u0011QX\u0001\u0002\u0002#\u0005\u0011q\u0018\u0004\tg\u0006\t\t\u0011#\u0001\u0002B\"1\u0011O\u0006C\u0001\u0003\u001bD\u0011\"a-\u0017\u0003\u0003%)%!.\t\u0013\u0005=g#!A\u0005\u0002\u0006E\u0007\"CAp-\u0005\u0005I\u0011QAq\u0011%\tYPFA\u0001\n\u0013\ti0\u0002\u0004\u0003\u0006\u0005\u0001!qA\u0003\u0007\u0005+\t\u0001Aa\u0006\b\u000f\t\u0005\u0012\u0001c\u0001\u0003$\u00199!QE\u0001\t\u0002\t\u001d\u0002BB9 \t\u0003\u0011\t\u0004C\u0004\u0002P~!\tAa\r\t\u0013\u0005mx$!A\u0005\n\u0005uxa\u0002B\u001d\u0003!\r!1\b\u0004\b\u0005{\t\u0001\u0012\u0001B \u0011\u0019\tH\u0005\"\u0001\u0003F!9\u0011q\u001a\u0013\u0005\u0002\t\u001d\u0003\"CA~I\u0005\u0005I\u0011BA\u007f\u000f\u001d\u0011Y%\u0001E\u0001\u0005\u001b2qAa\u0014\u0002\u0011\u0003\u0011\t\u0006\u0003\u0004rS\u0011\u0005!1K\u0004\b\u0005CI\u00032\u0001B+\r\u001d\u0011)#\u000bE\u0001\u00053Ba!\u001d\u0017\u0005\u0002\tu\u0003bBAhY\u0011\u0005!q\f\u0005\n\u0003wd\u0013\u0011!C\u0005\u0003{<qA!\u000f*\u0011\u0007\u0011\u0019GB\u0004\u0003>%B\tA!\u001a\t\rE\fD\u0011\u0001B5\u0011\u001d\ty-\rC\u0001\u0005WB\u0011\"a?2\u0003\u0003%I!!@\t\u000f\t=\u0014\u0006b\u0001\u0003r\u001d9!1R\u0001\t\u0002\t5ea\u0002BH\u0003!\u0005!\u0011\u0013\u0005\u0007c^\"\tAa%\t\u000f\t=t\u0007b\u0001\u0003\u0016\u001e9!1V\u0001\t\u0002\t5fa\u0002BX\u0003!\u0005!\u0011\u0017\u0005\u0007cn\"\tAa-\b\u000f\tU6\bc\u0001\u00038\u001a9!1X\u001e\t\u0002\tu\u0006BB9?\t\u0003\u0011\t\rC\u0004\u0002Pz\"\tAa1\t\u0013\u0005mh(!A\u0005\n\u0005uxa\u0002Bdw!\r!\u0011\u001a\u0004\b\u0005\u0017\\\u0004\u0012\u0001Bg\u0011\u0019\t8\t\"\u0001\u0003R\"9\u0011qZ\"\u0005\u0002\tM\u0007\"CA~\u0007\u0006\u0005I\u0011BA\u007f\u000f\u001d\u0011Ye\u000fE\u0001\u0005/4qAa\u0014<\u0011\u0003\u0011I\u000e\u0003\u0004r\u0011\u0012\u0005!1\\\u0004\b\u0005kC\u00052\u0001Bo\r\u001d\u0011Y\f\u0013E\u0001\u0005CDa!]&\u0005\u0002\t\u0015\bbBAh\u0017\u0012\u0005!q\u001d\u0005\n\u0003w\\\u0015\u0011!C\u0005\u0003{<qAa2I\u0011\u0007\u0011YOB\u0004\u0003L\"C\tA!<\t\rE\u0004F\u0011\u0001By\u0011\u001d\ty\r\u0015C\u0001\u0005gD\u0011\"a?Q\u0003\u0003%I!!@\t\u000f\t=\u0004\nb\u0001\u0003x\u001e9!1R\u001e\t\u0002\r5aa\u0002BHw!\u00051q\u0002\u0005\u0007cZ#\ta!\u0005\t\u000f\t=d\u000bb\u0001\u0004\u0014!91\u0011F\u0001\u0005\n\r-\u0002bBB\"\u0003\u0011%1QI\u0001\u0003cJT!!\u00180\u0002\r1Lg.\u00197h\u0015\u0005y\u0016A\u00022sK\u0016TXm\u0001\u0001\u0011\u0005\t\fQ\"\u0001/\u0003\u0005E\u00148cA\u0001fWB\u0011a-[\u0007\u0002O*\t\u0001.A\u0003tG\u0006d\u0017-\u0003\u0002kO\n1\u0011I\\=SK\u001a\u0004\"\u0001\\8\u000e\u00035T!A\u001c0\u0002\u000f\u001d,g.\u001a:jG&\u0011\u0001/\u001c\u0002\u0006+\u001a+hnY\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0005\u0014!!\u0015*\u0016\u0007U\f\u0019b\u0005\u0003\u0004KZL\bC\u00014x\u0013\tAxMA\u0004Qe>$Wo\u0019;\u0011\u0007i\f)AD\u0002|\u0003\u0003q!\u0001`@\u000e\u0003uT!A 1\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0017bAA\u0002O\u00069\u0001/Y2lC\u001e,\u0017\u0002BA\u0004\u0003\u0013\u0011AbU3sS\u0006d\u0017N_1cY\u0016T1!a\u0001h\u0003\u0005\tXCAA\b!\u0011\t\t\"a\u0005\r\u0001\u00119\u0011QC\u0002C\u0002\u0005]!!A'\u0012\t\u0005e\u0011q\u0004\t\u0004M\u0006m\u0011bAA\u000fO\n9aj\u001c;iS:<\u0007c\u00014\u0002\"%\u0019\u00111E4\u0003\u0007\u0005s\u00170\u0001\u0002rA\u0005\t!/\u0001\u0002sAQ1\u0011QFA\u0019\u0003g\u0001R!a\f\u0004\u0003\u001fi\u0011!\u0001\u0005\b\u0003\u0017A\u0001\u0019AA\b\u0011\u001d\t9\u0003\u0003a\u0001\u0003\u001f\tAaY8qsV!\u0011\u0011HA )\u0019\tY$!\u0011\u0002DA)\u0011qF\u0002\u0002>A!\u0011\u0011CA \t\u001d\t)\"\u0003b\u0001\u0003/A\u0011\"a\u0003\n!\u0003\u0005\r!!\u0010\t\u0013\u0005\u001d\u0012\u0002%AA\u0002\u0005u\u0012AD2paf$C-\u001a4bk2$H%M\u000b\u0005\u0003\u0013\ny&\u0006\u0002\u0002L)\"\u0011qBA'W\t\ty\u0005\u0005\u0003\u0002R\u0005mSBAA*\u0015\u0011\t)&a\u0016\u0002\u0013Ut7\r[3dW\u0016$'bAA-O\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005u\u00131\u000b\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,GaBA\u000b\u0015\t\u0007\u0011qC\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0011\tI%!\u001a\u0005\u000f\u0005U1B1\u0001\u0002\u0018\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"!a\u001b\u0011\t\u00055\u0014qO\u0007\u0003\u0003_RA!!\u001d\u0002t\u0005!A.\u00198h\u0015\t\t)(\u0001\u0003kCZ\f\u0017\u0002BA=\u0003_\u0012aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLXCAA@!\r1\u0017\u0011Q\u0005\u0004\u0003\u0007;'aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\u0010\u0003\u0013C\u0011\"a#\u000f\u0003\u0003\u0005\r!a \u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t\t\n\u0005\u0004\u0002\u0014\u0006e\u0015qD\u0007\u0003\u0003+S1!a&h\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u00037\u000b)J\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BAQ\u0003O\u00032AZAR\u0013\r\t)k\u001a\u0002\b\u0005>|G.Z1o\u0011%\tY\tEA\u0001\u0002\u0004\ty\"\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA6\u0003[C\u0011\"a#\u0012\u0003\u0003\u0005\r!a \u0002\u0011!\f7\u000f[\"pI\u0016$\"!a \u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\u001b\u0002\r\u0015\fX/\u00197t)\u0011\t\t+a/\t\u0013\u0005-E#!AA\u0002\u0005}\u0011AA)S!\r\tyCF\n\u0005-\u0015\f\u0019\r\u0005\u0003\u0002F\u0006-WBAAd\u0015\u0011\tI-a\u001d\u0002\u0005%|\u0017\u0002BA\u0004\u0003\u000f$\"!a0\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\u0005M\u0017\u0011\u001c\u000b\u0007\u0003+\fY.!8\u0011\u000b\u0005=2!a6\u0011\t\u0005E\u0011\u0011\u001c\u0003\b\u0003+I\"\u0019AA\f\u0011\u001d\tY!\u0007a\u0001\u0003/Dq!a\n\u001a\u0001\u0004\t9.A\u0004v]\u0006\u0004\b\u000f\\=\u0016\t\u0005\r\u00181\u001f\u000b\u0005\u0003K\f)\u0010E\u0003g\u0003O\fY/C\u0002\u0002j\u001e\u0014aa\u00149uS>t\u0007c\u00024\u0002n\u0006E\u0018\u0011_\u0005\u0004\u0003_<'A\u0002+va2,'\u0007\u0005\u0003\u0002\u0012\u0005MHaBA\u000b5\t\u0007\u0011q\u0003\u0005\n\u0003oT\u0012\u0011!a\u0001\u0003s\f1\u0001\u001f\u00131!\u0015\tycAAy\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ty\u0010\u0005\u0003\u0002n\t\u0005\u0011\u0002\u0002B\u0002\u0003_\u0012aa\u00142kK\u000e$(a\u0002#f]N,\u0017K\u0015\t\u0006\u0003_\u0019!\u0011\u0002\t\u0006E\n-!qB\u0005\u0004\u0005\u001ba&a\u0003#f]N,W*\u0019;sSb\u00042A\u001aB\t\u0013\r\u0011\u0019b\u001a\u0002\u0007\t>,(\r\\3\u0003\u0011M#UM\\:f#J\u0003R!a\f\u0004\u00053\u0001RA\u0019B\u0006\u00057\u00012A\u001aB\u000f\u0013\r\u0011yb\u001a\u0002\u0006\r2|\u0017\r^\u0001\u000fS6\u0004Hn\u0018#N?\u0012{WO\u00197f!\r\tyc\b\u0002\u000fS6\u0004Hn\u0018#N?\u0012{WO\u00197f'\u0011yRM!\u000b\u0011\u0011\u0005=\"1\u0006B\u0005\u0005_I1A!\fp\u0005\u0011IU\u000e\u001d7\u0011\u0007\u0005=B\u0004\u0006\u0002\u0003$Q!!q\u0006B\u001b\u0011\u001d\u00119$\ta\u0001\u0005\u0013\t\u0011A^\u0001\u000eS6\u0004Hn\u0018#N?\u001acw.\u0019;\u0011\u0007\u0005=BEA\u0007j[Bdw\fR'`\r2|\u0017\r^\n\u0005I\u0015\u0014\t\u0005\u0005\u0005\u00020\t-\"\u0011\u0004B\"!\r\ty#\b\u000b\u0003\u0005w!BAa\u0011\u0003J!9!q\u0007\u0014A\u0002\te\u0011!\u00026vgR\u0014\u0006cAA\u0018S\t)!.^:u%N\u0019\u0011&Z6\u0015\u0005\t5\u0003c\u0001B,Y5\t\u0011f\u0005\u0003-K\nm\u0003\u0003\u0003B,\u0005W\u0011IA!\u0003\u0015\u0005\tUC\u0003\u0002B\u0005\u0005CBqAa\u000e/\u0001\u0004\u0011I\u0001E\u0002\u0003XE\u001aB!M3\u0003hAA!q\u000bB\u0016\u00053\u0011I\u0002\u0006\u0002\u0003dQ!!\u0011\u0004B7\u0011\u001d\u00119d\ra\u0001\u00053\t\u0011cY1o\u0015V\u001cH/U%g/\u0016\u001c\u0015M\\)S+\u0019\u0011\u0019H!\u001f\u0003\u0000Q!!Q\u000fBA!!\u00119Fa\u000b\u0003x\tu\u0004\u0003BA\t\u0005s\"qAa\u001f6\u0005\u0004\t9BA\u0001U!\u0011\t\tBa \u0005\u000f\u0005UQG1\u0001\u0002\u0018!9!1Q\u001bA\u0004\t\u0015\u0015AB9s\u00136\u0004H\u000e\u0005\u0005\u0003\b\n-\"q\u000fBE\u001d\t\u0011\u0007\u0001E\u0003\u00020\r\u0011i(A\u0003kkN$\u0018\u000bE\u0002\u00020]\u0012QA[;tiF\u001b2aN3l)\t\u0011i)\u0006\u0004\u0003\u0018\n}%1\u0015\u000b\u0005\u00053\u0013)\u000b\u0005\u0005\u0003\u001c\n-\"Q\u0014BQ\u001b\u00059\u0004\u0003BA\t\u0005?#qAa\u001f:\u0005\u0004\t9\u0002\u0005\u0003\u0002\u0012\t\rFaBA\u000bs\t\u0007\u0011q\u0003\u0005\b\u0005\u0007K\u00049\u0001BT!!\u00119Ia\u000b\u0003\u001e\n%\u0006#BA\u0018\u0007\t\u0005\u0016a\u0002:fIV\u001cW\r\u001a\t\u0004\u0003_Y$a\u0002:fIV\u001cW\rZ\n\u0004w\u0015\\GC\u0001BW\u0003YIW\u000e\u001d7`e\u0016$WoY3e?\u0012ku\fR8vE2,\u0007c\u0001B]}5\t1H\u0001\fj[BdwL]3ek\u000e,Gm\u0018#N?\u0012{WO\u00197f'\u0011qTMa0\u0011\u0011\te&1\u0006B\u0005\u0005_!\"Aa.\u0015\t\t=\"Q\u0019\u0005\b\u0005o\u0001\u0005\u0019\u0001B\u0005\u0003UIW\u000e\u001d7`e\u0016$WoY3e?\u0012kuL\u00127pCR\u00042A!/D\u0005UIW\u000e\u001d7`e\u0016$WoY3e?\u0012kuL\u00127pCR\u001cBaQ3\u0003PBA!\u0011\u0018B\u0016\u00053\u0011\u0019\u0005\u0006\u0002\u0003JR!!1\tBk\u0011\u001d\u00119$\u0012a\u0001\u00053\u00012A!/I'\rAUm\u001b\u000b\u0003\u0005/\u00042Aa8L\u001b\u0005A5\u0003B&f\u0005G\u0004\u0002Ba8\u0003,\t%!\u0011\u0002\u000b\u0003\u0005;$BA!\u0003\u0003j\"9!qG'A\u0002\t%\u0001c\u0001Bp!N!\u0001+\u001aBx!!\u0011yNa\u000b\u0003\u001a\teAC\u0001Bv)\u0011\u0011IB!>\t\u000f\t]\"\u000b1\u0001\u0003\u001aU1!\u0011 B\u0000\u0007\u0007!BAa?\u0004\u0006AA!q\u001cB\u0016\u0005{\u001c\t\u0001\u0005\u0003\u0002\u0012\t}Ha\u0002B>)\n\u0007\u0011q\u0003\t\u0005\u0003#\u0019\u0019\u0001B\u0004\u0002\u0016Q\u0013\r!a\u0006\t\u000f\t\rE\u000bq\u0001\u0004\bAA1\u0011\u0002B\u0016\u0005{\u001cYAD\u0002\u0003\bj\u0002R!a\f\u0004\u0007\u0003\u00012A!/W'\r1Vm\u001b\u000b\u0003\u0007\u001b)ba!\u0006\u0004\u001e\r\u0005B\u0003BB\f\u0007G\u0001\u0002b!\u0007\u0003,\rm1qD\u0007\u0002-B!\u0011\u0011CB\u000f\t\u001d\u0011Y\b\u0017b\u0001\u0003/\u0001B!!\u0005\u0004\"\u00119\u0011Q\u0003-C\u0002\u0005]\u0001b\u0002BB1\u0002\u000f1Q\u0005\t\t\u0007\u0013\u0011Yca\u0007\u0004(A)\u0011qF\u0002\u0004 \u0005!Am\\)s)\u0019\u0019ica\u000f\u0004@Q!1qFB\u0019!\u001d1\u0017Q\u001eB\u0005\u0005\u0013Aqaa\rZ\u0001\u0004\u0019)$\u0001\u0003n_\u0012,\u0007c\u00012\u00048%\u00191\u0011\b/\u0003\rE\u0013Vj\u001c3f\u0011\u001d\u0019i$\u0017a\u0001\u0005\u0013\t\u0011!\u0014\u0005\b\u0007\u0003J\u0006\u0019AAQ\u0003\u0015\u00198.\u001b9R\u0003)!w.\u0015:`\r2|\u0017\r\u001e\u000b\u0007\u0007\u000f\u001aiea\u0014\u0015\t\r%31\n\t\bM\u00065(\u0011\u0004B\r\u0011\u001d\u0019\u0019D\u0017a\u0001\u0007kAqa!\u0010[\u0001\u0004\u0011I\u0002C\u0004\u0004Bi\u0003\r!!)"
)
public final class qr {
   public static Object withSink(final Object s) {
      return qr$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return qr$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return qr$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return qr$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return qr$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return qr$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return qr$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return qr$.MODULE$.apply(v, impl);
   }

   public static class QR implements Product, Serializable {
      private final Object q;
      private final Object r;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object q() {
         return this.q;
      }

      public Object r() {
         return this.r;
      }

      public QR copy(final Object q, final Object r) {
         return new QR(q, r);
      }

      public Object copy$default$1() {
         return this.q();
      }

      public Object copy$default$2() {
         return this.r();
      }

      public String productPrefix() {
         return "QR";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.q();
               break;
            case 1:
               var10000 = this.r();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof QR;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "q";
               break;
            case 1:
               var10000 = "r";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label51: {
               boolean var2;
               if (x$1 instanceof QR) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  QR var4 = (QR)x$1;
                  if (BoxesRunTime.equals(this.q(), var4.q()) && BoxesRunTime.equals(this.r(), var4.r()) && var4.canEqual(this)) {
                     break label51;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public QR(final Object q, final Object r) {
         this.q = q;
         this.r = r;
         Product.$init$(this);
      }
   }

   public static class QR$ implements Serializable {
      public static final QR$ MODULE$ = new QR$();

      public final String toString() {
         return "QR";
      }

      public QR apply(final Object q, final Object r) {
         return new QR(q, r);
      }

      public Option unapply(final QR x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.q(), x$0.r())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(QR$.class);
      }
   }

   public static class impl_DM_Double$ implements UFunc.UImpl {
      public static final impl_DM_Double$ MODULE$ = new impl_DM_Double$();

      public double apply$mcDD$sp(final double v) {
         return UFunc.UImpl.apply$mcDD$sp$(this, v);
      }

      public float apply$mcDF$sp(final double v) {
         return UFunc.UImpl.apply$mcDF$sp$(this, v);
      }

      public int apply$mcDI$sp(final double v) {
         return UFunc.UImpl.apply$mcDI$sp$(this, v);
      }

      public double apply$mcFD$sp(final float v) {
         return UFunc.UImpl.apply$mcFD$sp$(this, v);
      }

      public float apply$mcFF$sp(final float v) {
         return UFunc.UImpl.apply$mcFF$sp$(this, v);
      }

      public int apply$mcFI$sp(final float v) {
         return UFunc.UImpl.apply$mcFI$sp$(this, v);
      }

      public double apply$mcID$sp(final int v) {
         return UFunc.UImpl.apply$mcID$sp$(this, v);
      }

      public float apply$mcIF$sp(final int v) {
         return UFunc.UImpl.apply$mcIF$sp$(this, v);
      }

      public int apply$mcII$sp(final int v) {
         return UFunc.UImpl.apply$mcII$sp$(this, v);
      }

      public QR apply(final DenseMatrix v) {
         Tuple2 var4 = qr$.MODULE$.breeze$linalg$qr$$doQr(v, false, CompleteQR$.MODULE$);
         if (var4 != null) {
            DenseMatrix q = (DenseMatrix)var4._1();
            DenseMatrix r = (DenseMatrix)var4._2();
            Tuple2 var2 = new Tuple2(q, r);
            DenseMatrix q = (DenseMatrix)var2._1();
            DenseMatrix r = (DenseMatrix)var2._2();
            return new QR(q, r);
         } else {
            throw new MatchError(var4);
         }
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(impl_DM_Double$.class);
      }
   }

   public static class impl_DM_Float$ implements UFunc.UImpl {
      public static final impl_DM_Float$ MODULE$ = new impl_DM_Float$();

      public double apply$mcDD$sp(final double v) {
         return UFunc.UImpl.apply$mcDD$sp$(this, v);
      }

      public float apply$mcDF$sp(final double v) {
         return UFunc.UImpl.apply$mcDF$sp$(this, v);
      }

      public int apply$mcDI$sp(final double v) {
         return UFunc.UImpl.apply$mcDI$sp$(this, v);
      }

      public double apply$mcFD$sp(final float v) {
         return UFunc.UImpl.apply$mcFD$sp$(this, v);
      }

      public float apply$mcFF$sp(final float v) {
         return UFunc.UImpl.apply$mcFF$sp$(this, v);
      }

      public int apply$mcFI$sp(final float v) {
         return UFunc.UImpl.apply$mcFI$sp$(this, v);
      }

      public double apply$mcID$sp(final int v) {
         return UFunc.UImpl.apply$mcID$sp$(this, v);
      }

      public float apply$mcIF$sp(final int v) {
         return UFunc.UImpl.apply$mcIF$sp$(this, v);
      }

      public int apply$mcII$sp(final int v) {
         return UFunc.UImpl.apply$mcII$sp$(this, v);
      }

      public QR apply(final DenseMatrix v) {
         Tuple2 var4 = qr$.MODULE$.breeze$linalg$qr$$doQr_Float(v, false, CompleteQR$.MODULE$);
         if (var4 != null) {
            DenseMatrix q = (DenseMatrix)var4._1();
            DenseMatrix r = (DenseMatrix)var4._2();
            Tuple2 var2 = new Tuple2(q, r);
            DenseMatrix q = (DenseMatrix)var2._1();
            DenseMatrix r = (DenseMatrix)var2._2();
            return new QR(q, r);
         } else {
            throw new MatchError(var4);
         }
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(impl_DM_Float$.class);
      }
   }

   public static class justR$ implements UFunc {
      public static final justR$ MODULE$ = new justR$();

      static {
         UFunc.$init$(MODULE$);
      }

      public final Object apply(final Object v, final UFunc.UImpl impl) {
         return UFunc.apply$(this, v, impl);
      }

      public final double apply$mDDc$sp(final double v, final UFunc.UImpl impl) {
         return UFunc.apply$mDDc$sp$(this, v, impl);
      }

      public final float apply$mDFc$sp(final double v, final UFunc.UImpl impl) {
         return UFunc.apply$mDFc$sp$(this, v, impl);
      }

      public final int apply$mDIc$sp(final double v, final UFunc.UImpl impl) {
         return UFunc.apply$mDIc$sp$(this, v, impl);
      }

      public final double apply$mFDc$sp(final float v, final UFunc.UImpl impl) {
         return UFunc.apply$mFDc$sp$(this, v, impl);
      }

      public final float apply$mFFc$sp(final float v, final UFunc.UImpl impl) {
         return UFunc.apply$mFFc$sp$(this, v, impl);
      }

      public final int apply$mFIc$sp(final float v, final UFunc.UImpl impl) {
         return UFunc.apply$mFIc$sp$(this, v, impl);
      }

      public final double apply$mIDc$sp(final int v, final UFunc.UImpl impl) {
         return UFunc.apply$mIDc$sp$(this, v, impl);
      }

      public final float apply$mIFc$sp(final int v, final UFunc.UImpl impl) {
         return UFunc.apply$mIFc$sp$(this, v, impl);
      }

      public final int apply$mIIc$sp(final int v, final UFunc.UImpl impl) {
         return UFunc.apply$mIIc$sp$(this, v, impl);
      }

      public final Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$(this, v1, v2, impl);
      }

      public final double apply$mDDDc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDDDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mDDFc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDDFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mDDIc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDDIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mDFDc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDFDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mDFFc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDFFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mDFIc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDFIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mDIDc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDIDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mDIFc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDIFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mDIIc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDIIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mFDDc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFDDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mFDFc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFDFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mFDIc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFDIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mFFDc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFFDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mFFFc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFFFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mFFIc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFFIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mFIDc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFIDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mFIFc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFIFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mFIIc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFIIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mIDDc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIDDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mIDFc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIDFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mIDIc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIDIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mIFDc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIFDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mIFFc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIFFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mIFIc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIFIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mIIDc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIIDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mIIFc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIIFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mIIIc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIIIc$sp$(this, v1, v2, impl);
      }

      public final Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$(this, v1, v2, v3, impl);
      }

      public final double apply$mDDDc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDDDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mDDFc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDDFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mDDIc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDDIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mDFDc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDFDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mDFFc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDFFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mDFIc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDFIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mDIDc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDIDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mDIFc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDIFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mDIIc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDIIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mFDDc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFDDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mFDFc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFDFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mFDIc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFDIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mFFDc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFFDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mFFFc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFFFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mFFIc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFFIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mFIDc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFIDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mFIFc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFIFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mFIIc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFIIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mIDDc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIDDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mIDFc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIDFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mIDIc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIDIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mIFDc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIFDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mIFFc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIFFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mIFIc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIFIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mIIDc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIIDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mIIFc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIIFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mIIIc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIIIc$sp$(this, v1, v2, v3, impl);
      }

      public final Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
         return UFunc.apply$(this, v1, v2, v3, v4, impl);
      }

      public final Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
         return UFunc.inPlace$(this, v, impl);
      }

      public final Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
         return UFunc.inPlace$(this, v, v2, impl);
      }

      public final Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
         return UFunc.inPlace$(this, v, v2, v3, impl);
      }

      public final Object withSink(final Object s) {
         return UFunc.withSink$(this, s);
      }

      public UFunc.UImpl canJustQIfWeCanQR(final UFunc.UImpl qrImpl) {
         return new UFunc.UImpl(qrImpl) {
            private final UFunc.UImpl qrImpl$1;

            public double apply$mcDD$sp(final double v) {
               return UFunc.UImpl.apply$mcDD$sp$(this, v);
            }

            public float apply$mcDF$sp(final double v) {
               return UFunc.UImpl.apply$mcDF$sp$(this, v);
            }

            public int apply$mcDI$sp(final double v) {
               return UFunc.UImpl.apply$mcDI$sp$(this, v);
            }

            public double apply$mcFD$sp(final float v) {
               return UFunc.UImpl.apply$mcFD$sp$(this, v);
            }

            public float apply$mcFF$sp(final float v) {
               return UFunc.UImpl.apply$mcFF$sp$(this, v);
            }

            public int apply$mcFI$sp(final float v) {
               return UFunc.UImpl.apply$mcFI$sp$(this, v);
            }

            public double apply$mcID$sp(final int v) {
               return UFunc.UImpl.apply$mcID$sp$(this, v);
            }

            public float apply$mcIF$sp(final int v) {
               return UFunc.UImpl.apply$mcIF$sp$(this, v);
            }

            public int apply$mcII$sp(final int v) {
               return UFunc.UImpl.apply$mcII$sp$(this, v);
            }

            public Object apply(final Object v) {
               return ((QR)this.qrImpl$1.apply(v)).r();
            }

            public {
               this.qrImpl$1 = qrImpl$1;
            }
         };
      }
   }

   public static class justQ$ implements UFunc {
      public static final justQ$ MODULE$ = new justQ$();

      static {
         UFunc.$init$(MODULE$);
      }

      public final Object apply(final Object v, final UFunc.UImpl impl) {
         return UFunc.apply$(this, v, impl);
      }

      public final double apply$mDDc$sp(final double v, final UFunc.UImpl impl) {
         return UFunc.apply$mDDc$sp$(this, v, impl);
      }

      public final float apply$mDFc$sp(final double v, final UFunc.UImpl impl) {
         return UFunc.apply$mDFc$sp$(this, v, impl);
      }

      public final int apply$mDIc$sp(final double v, final UFunc.UImpl impl) {
         return UFunc.apply$mDIc$sp$(this, v, impl);
      }

      public final double apply$mFDc$sp(final float v, final UFunc.UImpl impl) {
         return UFunc.apply$mFDc$sp$(this, v, impl);
      }

      public final float apply$mFFc$sp(final float v, final UFunc.UImpl impl) {
         return UFunc.apply$mFFc$sp$(this, v, impl);
      }

      public final int apply$mFIc$sp(final float v, final UFunc.UImpl impl) {
         return UFunc.apply$mFIc$sp$(this, v, impl);
      }

      public final double apply$mIDc$sp(final int v, final UFunc.UImpl impl) {
         return UFunc.apply$mIDc$sp$(this, v, impl);
      }

      public final float apply$mIFc$sp(final int v, final UFunc.UImpl impl) {
         return UFunc.apply$mIFc$sp$(this, v, impl);
      }

      public final int apply$mIIc$sp(final int v, final UFunc.UImpl impl) {
         return UFunc.apply$mIIc$sp$(this, v, impl);
      }

      public final Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$(this, v1, v2, impl);
      }

      public final double apply$mDDDc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDDDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mDDFc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDDFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mDDIc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDDIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mDFDc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDFDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mDFFc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDFFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mDFIc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDFIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mDIDc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDIDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mDIFc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDIFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mDIIc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDIIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mFDDc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFDDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mFDFc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFDFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mFDIc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFDIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mFFDc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFFDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mFFFc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFFFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mFFIc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFFIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mFIDc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFIDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mFIFc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFIFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mFIIc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFIIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mIDDc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIDDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mIDFc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIDFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mIDIc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIDIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mIFDc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIFDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mIFFc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIFFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mIFIc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIFIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mIIDc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIIDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mIIFc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIIFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mIIIc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIIIc$sp$(this, v1, v2, impl);
      }

      public final Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$(this, v1, v2, v3, impl);
      }

      public final double apply$mDDDc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDDDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mDDFc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDDFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mDDIc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDDIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mDFDc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDFDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mDFFc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDFFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mDFIc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDFIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mDIDc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDIDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mDIFc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDIFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mDIIc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDIIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mFDDc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFDDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mFDFc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFDFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mFDIc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFDIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mFFDc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFFDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mFFFc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFFFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mFFIc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFFIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mFIDc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFIDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mFIFc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFIFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mFIIc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFIIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mIDDc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIDDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mIDFc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIDFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mIDIc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIDIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mIFDc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIFDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mIFFc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIFFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mIFIc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIFIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mIIDc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIIDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mIIFc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIIFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mIIIc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIIIc$sp$(this, v1, v2, v3, impl);
      }

      public final Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
         return UFunc.apply$(this, v1, v2, v3, v4, impl);
      }

      public final Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
         return UFunc.inPlace$(this, v, impl);
      }

      public final Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
         return UFunc.inPlace$(this, v, v2, impl);
      }

      public final Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
         return UFunc.inPlace$(this, v, v2, v3, impl);
      }

      public final Object withSink(final Object s) {
         return UFunc.withSink$(this, s);
      }

      public UFunc.UImpl canJustQIfWeCanQR(final UFunc.UImpl qrImpl) {
         return new UFunc.UImpl(qrImpl) {
            private final UFunc.UImpl qrImpl$2;

            public double apply$mcDD$sp(final double v) {
               return UFunc.UImpl.apply$mcDD$sp$(this, v);
            }

            public float apply$mcDF$sp(final double v) {
               return UFunc.UImpl.apply$mcDF$sp$(this, v);
            }

            public int apply$mcDI$sp(final double v) {
               return UFunc.UImpl.apply$mcDI$sp$(this, v);
            }

            public double apply$mcFD$sp(final float v) {
               return UFunc.UImpl.apply$mcFD$sp$(this, v);
            }

            public float apply$mcFF$sp(final float v) {
               return UFunc.UImpl.apply$mcFF$sp$(this, v);
            }

            public int apply$mcFI$sp(final float v) {
               return UFunc.UImpl.apply$mcFI$sp$(this, v);
            }

            public double apply$mcID$sp(final int v) {
               return UFunc.UImpl.apply$mcID$sp$(this, v);
            }

            public float apply$mcIF$sp(final int v) {
               return UFunc.UImpl.apply$mcIF$sp$(this, v);
            }

            public int apply$mcII$sp(final int v) {
               return UFunc.UImpl.apply$mcII$sp$(this, v);
            }

            public Object apply(final Object v) {
               return ((QR)this.qrImpl$2.apply(v)).q();
            }

            public {
               this.qrImpl$2 = qrImpl$2;
            }
         };
      }
   }

   public static class reduced$ implements UFunc {
      public static final reduced$ MODULE$ = new reduced$();

      static {
         UFunc.$init$(MODULE$);
      }

      public final Object apply(final Object v, final UFunc.UImpl impl) {
         return UFunc.apply$(this, v, impl);
      }

      public final double apply$mDDc$sp(final double v, final UFunc.UImpl impl) {
         return UFunc.apply$mDDc$sp$(this, v, impl);
      }

      public final float apply$mDFc$sp(final double v, final UFunc.UImpl impl) {
         return UFunc.apply$mDFc$sp$(this, v, impl);
      }

      public final int apply$mDIc$sp(final double v, final UFunc.UImpl impl) {
         return UFunc.apply$mDIc$sp$(this, v, impl);
      }

      public final double apply$mFDc$sp(final float v, final UFunc.UImpl impl) {
         return UFunc.apply$mFDc$sp$(this, v, impl);
      }

      public final float apply$mFFc$sp(final float v, final UFunc.UImpl impl) {
         return UFunc.apply$mFFc$sp$(this, v, impl);
      }

      public final int apply$mFIc$sp(final float v, final UFunc.UImpl impl) {
         return UFunc.apply$mFIc$sp$(this, v, impl);
      }

      public final double apply$mIDc$sp(final int v, final UFunc.UImpl impl) {
         return UFunc.apply$mIDc$sp$(this, v, impl);
      }

      public final float apply$mIFc$sp(final int v, final UFunc.UImpl impl) {
         return UFunc.apply$mIFc$sp$(this, v, impl);
      }

      public final int apply$mIIc$sp(final int v, final UFunc.UImpl impl) {
         return UFunc.apply$mIIc$sp$(this, v, impl);
      }

      public final Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$(this, v1, v2, impl);
      }

      public final double apply$mDDDc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDDDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mDDFc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDDFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mDDIc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDDIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mDFDc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDFDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mDFFc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDFFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mDFIc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDFIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mDIDc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDIDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mDIFc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDIFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mDIIc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDIIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mFDDc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFDDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mFDFc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFDFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mFDIc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFDIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mFFDc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFFDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mFFFc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFFFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mFFIc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFFIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mFIDc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFIDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mFIFc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFIFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mFIIc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFIIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mIDDc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIDDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mIDFc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIDFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mIDIc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIDIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mIFDc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIFDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mIFFc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIFFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mIFIc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIFIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mIIDc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIIDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mIIFc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIIFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mIIIc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIIIc$sp$(this, v1, v2, impl);
      }

      public final Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$(this, v1, v2, v3, impl);
      }

      public final double apply$mDDDc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDDDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mDDFc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDDFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mDDIc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDDIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mDFDc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDFDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mDFFc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDFFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mDFIc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDFIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mDIDc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDIDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mDIFc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDIFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mDIIc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDIIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mFDDc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFDDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mFDFc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFDFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mFDIc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFDIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mFFDc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFFDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mFFFc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFFFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mFFIc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFFIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mFIDc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFIDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mFIFc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFIFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mFIIc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFIIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mIDDc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIDDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mIDFc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIDFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mIDIc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIDIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mIFDc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIFDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mIFFc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIFFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mIFIc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIFIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mIIDc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIIDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mIIFc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIIFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mIIIc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIIIc$sp$(this, v1, v2, v3, impl);
      }

      public final Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
         return UFunc.apply$(this, v1, v2, v3, v4, impl);
      }

      public final Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
         return UFunc.inPlace$(this, v, impl);
      }

      public final Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
         return UFunc.inPlace$(this, v, v2, impl);
      }

      public final Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
         return UFunc.inPlace$(this, v, v2, v3, impl);
      }

      public final Object withSink(final Object s) {
         return UFunc.withSink$(this, s);
      }
   }
}
