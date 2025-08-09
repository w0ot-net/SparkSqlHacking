package org.apache.spark.sql;

import org.apache.spark.sql.types.StructType;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.api.TypeTags;

@ScalaSignature(
   bytes = "\u0006\u0005\r=w!\u0002\u0017.\u0011\u00031d!\u0002\u001d.\u0011\u0003I\u0004\"\u0002!\u0002\t\u0003\t\u0005\"\u0002\"\u0002\t\u0003\u0019\u0005\"B(\u0002\t\u0003\u0001\u0006\"B+\u0002\t\u00031\u0006\"B.\u0002\t\u0003a\u0006\"B1\u0002\t\u0003\u0011\u0007\"B4\u0002\t\u0003A\u0007\"B7\u0002\t\u0003q\u0007\"B:\u0002\t\u0003!\b\"\u0002@\u0002\t\u0003y\bbBA\u0002\u0003\u0011\u0005\u0011Q\u0001\u0005\b\u0003\u000f\tA\u0011AA\u0005\u0011\u001d\tI\"\u0001C\u0001\u00037Aq!!\u000b\u0002\t\u0003\tY\u0003C\u0004\u0002<\u0005!\t!!\u0010\t\u000f\u0005\u001d\u0013\u0001\"\u0001\u0002J!9\u00111K\u0001\u0005\u0002\u0005U\u0003bBA0\u0003\u0011\u0005\u0011\u0011\r\u0005\b\u0003_\nA\u0011AA9\u0011\u001d\tY(\u0001C\u0001\u0003{Bq!a\"\u0002\t\u0003\tI\tC\u0004\u0002@\u0006!\t!!1\t\u000f\u0005m\u0017\u0001\"\u0001\u0002^\"9\u00111\\\u0001\u0005\u0002\u0005]\bb\u0002B\u0004\u0003\u0011\u0005!\u0011\u0002\u0005\b\u0005\u000f\tA\u0011\u0001B\r\u0011\u001d\u00119#\u0001C\u0005\u0005SAqAa\u0010\u0002\t\u0013\u0011\t\u0005\u0003\u0005\u0003n\u0005!\t!\fB8\u0011\u001d\u0011Y)\u0001C\u0001\u0005\u001bCqAa#\u0002\t\u0003\u0011i\nC\u0004\u0003\f\u0006!\tA!0\t\u000f\t-\u0015\u0001\"\u0001\u0003f\"9!1R\u0001\u0005\u0002\rU\u0001bBB'\u0003\u0011\u00051q\n\u0005\b\u0007#\u000bA\u0011ABJ\u0011\u001d\u00199*\u0001C\u0001\u00073Cqa!)\u0002\t\u0003\u0019\u0019\u000bC\u0004\u0004,\u0006!\ta!,\t\u000f\rU\u0016\u0001\"\u0001\u00048\"911X\u0001\u0005\u0002\ru\u0006bBBc\u0003\u0011\u00051qY\u0001\t\u000b:\u001cw\u000eZ3sg*\u0011afL\u0001\u0004gFd'B\u0001\u00192\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00114'\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002i\u0005\u0019qN]4\u0004\u0001A\u0011q'A\u0007\u0002[\tAQI\\2pI\u0016\u00148o\u0005\u0002\u0002uA\u00111HP\u0007\u0002y)\tQ(A\u0003tG\u0006d\u0017-\u0003\u0002@y\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#\u0001\u001c\u0002\u000f\t{u\nT#B\u001dV\tA\tE\u00028\u000b\u001eK!AR\u0017\u0003\u000f\u0015s7m\u001c3feB\u0011\u0001*T\u0007\u0002\u0013*\u0011!jS\u0001\u0005Y\u0006twMC\u0001M\u0003\u0011Q\u0017M^1\n\u00059K%a\u0002\"p_2,\u0017M\\\u0001\u0005\u0005f#V)F\u0001R!\r9TI\u0015\t\u0003\u0011NK!\u0001V%\u0003\t\tKH/Z\u0001\u0006'\"{%\u000bV\u000b\u0002/B\u0019q'\u0012-\u0011\u0005!K\u0016B\u0001.J\u0005\u0015\u0019\u0006n\u001c:u\u0003\rIe\nV\u000b\u0002;B\u0019q'\u00120\u0011\u0005!{\u0016B\u00011J\u0005\u001dIe\u000e^3hKJ\fA\u0001T(O\u000fV\t1\rE\u00028\u000b\u0012\u0004\"\u0001S3\n\u0005\u0019L%\u0001\u0002'p]\u001e\fQA\u0012'P\u0003R+\u0012!\u001b\t\u0004o\u0015S\u0007C\u0001%l\u0013\ta\u0017JA\u0003GY>\fG/\u0001\u0004E\u001fV\u0013E*R\u000b\u0002_B\u0019q'\u00129\u0011\u0005!\u000b\u0018B\u0001:J\u0005\u0019!u.\u001e2mK\u0006!1\tS!S)\t)\u0018\u0010E\u00028\u000bZ\u0004\"\u0001S<\n\u0005aL%AB*ue&tw\rC\u0003{\u0015\u0001\u000710\u0001\u0004mK:<G\u000f\u001b\t\u0003wqL!! \u001f\u0003\u0007%sG/A\u0004W\u0003J\u001b\u0005*\u0011*\u0015\u0007U\f\t\u0001C\u0003{\u0017\u0001\u000710\u0001\u0004T)JKejR\u000b\u0002k\u00069A)R\"J\u001b\u0006cUCAA\u0006!\u00119T)!\u0004\u0011\t\u0005=\u0011QC\u0007\u0003\u0003#Q1!a\u0005L\u0003\u0011i\u0017\r\u001e5\n\t\u0005]\u0011\u0011\u0003\u0002\u000b\u0005&<G)Z2j[\u0006d\u0017\u0001\u0002#B)\u0016+\"!!\b\u0011\t]*\u0015q\u0004\t\u0005\u0003C\t)#\u0004\u0002\u0002$)\u0011afS\u0005\u0005\u0003O\t\u0019C\u0001\u0003ECR,\u0017!\u0003'P\u0007\u0006cE)\u0011+F+\t\ti\u0003\u0005\u00038\u000b\u0006=\u0002\u0003BA\u0019\u0003oi!!a\r\u000b\u0007\u0005U2*\u0001\u0003uS6,\u0017\u0002BA\u001d\u0003g\u0011\u0011\u0002T8dC2$\u0015\r^3\u0002\u001b1{5)\u0011'E\u0003R+E+S'F+\t\ty\u0004\u0005\u00038\u000b\u0006\u0005\u0003\u0003BA\u0019\u0003\u0007JA!!\u0012\u00024\tiAj\\2bY\u0012\u000bG/\u001a+j[\u0016\f\u0011\u0002V%N\u000bN#\u0016)\u0014)\u0016\u0005\u0005-\u0003\u0003B\u001cF\u0003\u001b\u0002B!!\t\u0002P%!\u0011\u0011KA\u0012\u0005%!\u0016.\\3ti\u0006l\u0007/A\u0004J\u001dN#\u0016I\u0014+\u0016\u0005\u0005]\u0003\u0003B\u001cF\u00033\u0002B!!\r\u0002\\%!\u0011QLA\u001a\u0005\u001dIen\u001d;b]R\faAQ%O\u0003JKVCAA2!\u00119T)!\u001a\u0011\u000bm\n9'a\u001b\n\u0007\u0005%DHA\u0003BeJ\f\u0017\u0010E\u0002<\u0003[J!\u0001\u0016\u001f\u0002\u0011\u0011+&+\u0011+J\u001f:+\"!a\u001d\u0011\t]*\u0015Q\u000f\t\u0005\u0003c\t9(\u0003\u0003\u0002z\u0005M\"\u0001\u0003#ve\u0006$\u0018n\u001c8\u0002\rA+%+S(E+\t\ty\b\u0005\u00038\u000b\u0006\u0005\u0005\u0003BA\u0019\u0003\u0007KA!!\"\u00024\t1\u0001+\u001a:j_\u0012\fAAY3b]V!\u00111RAJ)\u0011\ti)!*\u0011\t]*\u0015q\u0012\t\u0005\u0003#\u000b\u0019\n\u0004\u0001\u0005\u000f\u0005UeC1\u0001\u0002\u0018\n\tA+\u0005\u0003\u0002\u001a\u0006}\u0005cA\u001e\u0002\u001c&\u0019\u0011Q\u0014\u001f\u0003\u000f9{G\u000f[5oOB\u00191(!)\n\u0007\u0005\rFHA\u0002B]fDq!a*\u0017\u0001\u0004\tI+A\u0005cK\u0006t7\t\\1tgB1\u00111VA]\u0003\u001fsA!!,\u00026B\u0019\u0011q\u0016\u001f\u000e\u0005\u0005E&bAAZk\u00051AH]8pizJ1!a.=\u0003\u0019\u0001&/\u001a3fM&!\u00111XA_\u0005\u0015\u0019E.Y:t\u0015\r\t9\fP\u0001\u0004e><H\u0003BAb\u0003\u0017\u0004BaN#\u0002FB\u0019q'a2\n\u0007\u0005%WFA\u0002S_^Dq!!4\u0018\u0001\u0004\ty-\u0001\u0004tG\",W.\u0019\t\u0005\u0003#\f9.\u0004\u0002\u0002T*\u0019\u0011Q[\u0017\u0002\u000bQL\b/Z:\n\t\u0005e\u00171\u001b\u0002\u000b'R\u0014Xo\u0019;UsB,\u0017\u0001B6ss>,B!a8\u0002fR!\u0011\u0011]At!\u00119T)a9\u0011\t\u0005E\u0015Q\u001d\u0003\b\u0003+C\"\u0019AAL\u0011%\tI\u000fGA\u0001\u0002\b\tY/\u0001\u0006fm&$WM\\2fIE\u0002b!!<\u0002t\u0006\rXBAAx\u0015\r\t\t\u0010P\u0001\be\u00164G.Z2u\u0013\u0011\t)0a<\u0003\u0011\rc\u0017m]:UC\u001e,B!!?\u0002\u0000R!\u00111 B\u0001!\u00119T)!@\u0011\t\u0005E\u0015q \u0003\b\u0003+K\"\u0019AAL\u0011\u001d\u0011\u0019!\u0007a\u0001\u0005\u000b\tQa\u00197buj\u0004b!a+\u0002:\u0006u\u0018!\u00056bm\u0006\u001cVM]5bY&T\u0018\r^5p]V!!1\u0002B\t)\u0011\u0011iAa\u0005\u0011\t]*%q\u0002\t\u0005\u0003#\u0013\t\u0002B\u0004\u0002\u0016j\u0011\r!a&\t\u0013\tU!$!AA\u0004\t]\u0011AC3wS\u0012,gnY3%eA1\u0011Q^Az\u0005\u001f)BAa\u0007\u0003\"Q!!Q\u0004B\u0012!\u00119TIa\b\u0011\t\u0005E%\u0011\u0005\u0003\b\u0003+[\"\u0019AAL\u0011\u001d\u0011\u0019a\u0007a\u0001\u0005K\u0001b!a+\u0002:\n}\u0011a\u0005<bY&$\u0017\r^3Qk\nd\u0017nY\"mCN\u001cX\u0003\u0002B\u0016\u0005{!\"A!\f\u0015\t\t=\"Q\u0007\t\u0004w\tE\u0012b\u0001B\u001ay\t!QK\\5u\u0011%\u00119\u0004HA\u0001\u0002\b\u0011I$\u0001\u0006fm&$WM\\2fIM\u0002b!!<\u0002t\nm\u0002\u0003BAI\u0005{!q!!&\u001d\u0005\u0004\t9*A\thK:,'/[2TKJL\u0017\r\\5{KJ,BAa\u0011\u0003LQ!!Q\tB*)\u0011\u00119E!\u0014\u0011\t]*%\u0011\n\t\u0005\u0003#\u0013Y\u0005B\u0004\u0002\u0016v\u0011\r!a&\t\u0013\t=S$!AA\u0004\tE\u0013AC3wS\u0012,gnY3%iA1\u0011Q^Az\u0005\u0013BqA!\u0016\u001e\u0001\u0004\u00119&\u0001\u0005qe>4\u0018\u000eZ3s!\u0015Y$\u0011\fB/\u0013\r\u0011Y\u0006\u0010\u0002\n\rVt7\r^5p]B\u0002\u0002Ba\u0018\u0003j\u0005}\u0015QM\u0007\u0003\u0005CRAAa\u0019\u0003f\u0005AQM\\2pI\u0016\u00148OC\u0002\u0003h5\n\u0001bY1uC2L8\u000f^\u0005\u0005\u0005W\u0012\tGA\u0003D_\u0012,7-\u0001\u0007ukBdW-\u00128d_\u0012,'/\u0006\u0003\u0003r\t]D\u0003\u0002B:\u0005s\u0002BaN#\u0003vA!\u0011\u0011\u0013B<\t\u001d\t)J\bb\u0001\u0003/CqAa\u0019\u001f\u0001\u0004\u0011Y\bE\u0003<\u0005{\u0012\t)C\u0002\u0003\u0000q\u0012!\u0002\u0010:fa\u0016\fG/\u001a3?a\u0011\u0011\u0019Ia\"\u0011\t]*%Q\u0011\t\u0005\u0003#\u00139\t\u0002\u0007\u0003\n\ne\u0014\u0011!A\u0001\u0006\u0003\t9JA\u0002`IE\nQ\u0001^;qY\u0016,BAa$\u0003\u0016R!!\u0011\u0013BM!\u00119TIa%\u0011\t\u0005E%Q\u0013\u0003\b\u0005/{\"\u0019AAL\u0005\t!\u0016\u0007C\u0004\u0003\u001c~\u0001\rA!%\u0002\u0005\u0015\fTC\u0002BP\u0005W\u0013y\u000b\u0006\u0004\u0003\"\nM&q\u0017\t\u0005o\u0015\u0013\u0019\u000bE\u0004<\u0005K\u0013IK!,\n\u0007\t\u001dFH\u0001\u0004UkBdWM\r\t\u0005\u0003#\u0013Y\u000bB\u0004\u0003\u0018\u0002\u0012\r!a&\u0011\t\u0005E%q\u0016\u0003\b\u0005c\u0003#\u0019AAL\u0005\t!&\u0007C\u0004\u0003\u001c\u0002\u0002\rA!.\u0011\t]*%\u0011\u0016\u0005\b\u0005s\u0003\u0003\u0019\u0001B^\u0003\t)'\u0007\u0005\u00038\u000b\n5V\u0003\u0003B`\u0005\u0017\u0014yMa5\u0015\u0011\t\u0005'q\u001bBn\u0005?\u0004BaN#\u0003DBI1H!2\u0003J\n5'\u0011[\u0005\u0004\u0005\u000fd$A\u0002+va2,7\u0007\u0005\u0003\u0002\u0012\n-Ga\u0002BLC\t\u0007\u0011q\u0013\t\u0005\u0003#\u0013y\rB\u0004\u00032\u0006\u0012\r!a&\u0011\t\u0005E%1\u001b\u0003\b\u0005+\f#\u0019AAL\u0005\t!6\u0007C\u0004\u0003\u001c\u0006\u0002\rA!7\u0011\t]*%\u0011\u001a\u0005\b\u0005s\u000b\u0003\u0019\u0001Bo!\u00119TI!4\t\u000f\t\u0005\u0018\u00051\u0001\u0003d\u0006\u0011Qm\r\t\u0005o\u0015\u0013\t.\u0006\u0006\u0003h\nM(q\u001fB~\u0005\u007f$\"B!;\u0004\u0004\r\u001d11BB\b!\u00119TIa;\u0011\u0017m\u0012iO!=\u0003v\ne(Q`\u0005\u0004\u0005_d$A\u0002+va2,G\u0007\u0005\u0003\u0002\u0012\nMHa\u0002BLE\t\u0007\u0011q\u0013\t\u0005\u0003#\u00139\u0010B\u0004\u00032\n\u0012\r!a&\u0011\t\u0005E%1 \u0003\b\u0005+\u0014#\u0019AAL!\u0011\t\tJa@\u0005\u000f\r\u0005!E1\u0001\u0002\u0018\n\u0011A\u000b\u000e\u0005\b\u00057\u0013\u0003\u0019AB\u0003!\u00119TI!=\t\u000f\te&\u00051\u0001\u0004\nA!q'\u0012B{\u0011\u001d\u0011\tO\ta\u0001\u0007\u001b\u0001BaN#\u0003z\"91\u0011\u0003\u0012A\u0002\rM\u0011AA35!\u00119TI!@\u0016\u0019\r]11EB\u0014\u0007W\u0019yca\r\u0015\u0019\re1qGB\u001e\u0007\u007f\u0019\u0019ea\u0012\u0011\t]*51\u0004\t\u000ew\ru1\u0011EB\u0013\u0007S\u0019ic!\r\n\u0007\r}AH\u0001\u0004UkBdW-\u000e\t\u0005\u0003#\u001b\u0019\u0003B\u0004\u0003\u0018\u000e\u0012\r!a&\u0011\t\u0005E5q\u0005\u0003\b\u0005c\u001b#\u0019AAL!\u0011\t\tja\u000b\u0005\u000f\tU7E1\u0001\u0002\u0018B!\u0011\u0011SB\u0018\t\u001d\u0019\ta\tb\u0001\u0003/\u0003B!!%\u00044\u001191QG\u0012C\u0002\u0005]%A\u0001+6\u0011\u001d\u0011Yj\ta\u0001\u0007s\u0001BaN#\u0004\"!9!\u0011X\u0012A\u0002\ru\u0002\u0003B\u001cF\u0007KAqA!9$\u0001\u0004\u0019\t\u0005\u0005\u00038\u000b\u000e%\u0002bBB\tG\u0001\u00071Q\t\t\u0005o\u0015\u001bi\u0003C\u0004\u0004J\r\u0002\raa\u0013\u0002\u0005\u0015,\u0004\u0003B\u001cF\u0007c\tq\u0001\u001d:pIV\u001cG/\u0006\u0003\u0004R\r]C\u0003BB*\u0007C\u0002BaN#\u0004VA!\u0011\u0011SB,\t\u001d\t)\n\nb\u0001\u00073\nB!!'\u0004\\A\u00191h!\u0018\n\u0007\r}CHA\u0004Qe>$Wo\u0019;\t\u0013\r\rD%!AA\u0004\r\u0015\u0014AC3wS\u0012,gnY3%kA11qMBC\u0007+rAa!\u001b\u0004\u00009!11NB=\u001d\u0011\u0019ig!\u001e\u000f\t\r=41\u000f\b\u0005\u0003_\u001b\t(C\u0001>\u0013\r\t\t\u0010P\u0005\u0005\u0007o\ny/A\u0004sk:$\u0018.\\3\n\t\rm4QP\u0001\ba\u0006\u001c7.Y4f\u0015\u0011\u00199(a<\n\t\r\u000551Q\u0001\tk:Lg/\u001a:tK*!11PB?\u0013\u0011\u00199i!#\u0003\u000fQK\b/\u001a+bO&!11RBG\u0005!!\u0016\u0010]3UC\u001e\u001c(\u0002BBH\u0003_\f1!\u00199j\u0003!\u00198-\u00197b\u0013:$XCABK!\r9Ti_\u0001\ng\u000e\fG.\u0019'p]\u001e,\"aa'\u0011\t]*5Q\u0014\t\u0004w\r}\u0015B\u00014=\u0003-\u00198-\u00197b\t>,(\r\\3\u0016\u0005\r\u0015\u0006\u0003B\u001cF\u0007O\u00032aOBU\u0013\t\u0011H(\u0001\u0006tG\u0006d\u0017M\u00127pCR,\"aa,\u0011\t]*5\u0011\u0017\t\u0004w\rM\u0016B\u00017=\u0003%\u00198-\u00197b\u0005f$X-\u0006\u0002\u0004:B!q'RA6\u0003)\u00198-\u00197b'\"|'\u000f^\u000b\u0003\u0007\u007f\u0003BaN#\u0004BB\u00191ha1\n\u0005ic\u0014\u0001D:dC2\f'i\\8mK\u0006tWCABe!\u00119Tia3\u0011\u0007m\u001ai-\u0003\u0002Oy\u0001"
)
public final class Encoders {
   public static Encoder scalaBoolean() {
      return Encoders$.MODULE$.scalaBoolean();
   }

   public static Encoder scalaShort() {
      return Encoders$.MODULE$.scalaShort();
   }

   public static Encoder scalaByte() {
      return Encoders$.MODULE$.scalaByte();
   }

   public static Encoder scalaFloat() {
      return Encoders$.MODULE$.scalaFloat();
   }

   public static Encoder scalaDouble() {
      return Encoders$.MODULE$.scalaDouble();
   }

   public static Encoder scalaLong() {
      return Encoders$.MODULE$.scalaLong();
   }

   public static Encoder scalaInt() {
      return Encoders$.MODULE$.scalaInt();
   }

   public static Encoder product(final TypeTags.TypeTag evidence$5) {
      return Encoders$.MODULE$.product(evidence$5);
   }

   public static Encoder tuple(final Encoder e1, final Encoder e2, final Encoder e3, final Encoder e4, final Encoder e5) {
      return Encoders$.MODULE$.tuple(e1, e2, e3, e4, e5);
   }

   public static Encoder tuple(final Encoder e1, final Encoder e2, final Encoder e3, final Encoder e4) {
      return Encoders$.MODULE$.tuple(e1, e2, e3, e4);
   }

   public static Encoder tuple(final Encoder e1, final Encoder e2, final Encoder e3) {
      return Encoders$.MODULE$.tuple(e1, e2, e3);
   }

   public static Encoder tuple(final Encoder e1, final Encoder e2) {
      return Encoders$.MODULE$.tuple(e1, e2);
   }

   public static Encoder tuple(final Encoder e1) {
      return Encoders$.MODULE$.tuple(e1);
   }

   public static Encoder javaSerialization(final Class clazz) {
      return Encoders$.MODULE$.javaSerialization(clazz);
   }

   public static Encoder javaSerialization(final ClassTag evidence$2) {
      return Encoders$.MODULE$.javaSerialization(evidence$2);
   }

   public static Encoder kryo(final Class clazz) {
      return Encoders$.MODULE$.kryo(clazz);
   }

   public static Encoder kryo(final ClassTag evidence$1) {
      return Encoders$.MODULE$.kryo(evidence$1);
   }

   public static Encoder row(final StructType schema) {
      return Encoders$.MODULE$.row(schema);
   }

   public static Encoder bean(final Class beanClass) {
      return Encoders$.MODULE$.bean(beanClass);
   }

   public static Encoder PERIOD() {
      return Encoders$.MODULE$.PERIOD();
   }

   public static Encoder DURATION() {
      return Encoders$.MODULE$.DURATION();
   }

   public static Encoder BINARY() {
      return Encoders$.MODULE$.BINARY();
   }

   public static Encoder INSTANT() {
      return Encoders$.MODULE$.INSTANT();
   }

   public static Encoder TIMESTAMP() {
      return Encoders$.MODULE$.TIMESTAMP();
   }

   public static Encoder LOCALDATETIME() {
      return Encoders$.MODULE$.LOCALDATETIME();
   }

   public static Encoder LOCALDATE() {
      return Encoders$.MODULE$.LOCALDATE();
   }

   public static Encoder DATE() {
      return Encoders$.MODULE$.DATE();
   }

   public static Encoder DECIMAL() {
      return Encoders$.MODULE$.DECIMAL();
   }

   public static Encoder STRING() {
      return Encoders$.MODULE$.STRING();
   }

   public static Encoder VARCHAR(final int length) {
      return Encoders$.MODULE$.VARCHAR(length);
   }

   public static Encoder CHAR(final int length) {
      return Encoders$.MODULE$.CHAR(length);
   }

   public static Encoder DOUBLE() {
      return Encoders$.MODULE$.DOUBLE();
   }

   public static Encoder FLOAT() {
      return Encoders$.MODULE$.FLOAT();
   }

   public static Encoder LONG() {
      return Encoders$.MODULE$.LONG();
   }

   public static Encoder INT() {
      return Encoders$.MODULE$.INT();
   }

   public static Encoder SHORT() {
      return Encoders$.MODULE$.SHORT();
   }

   public static Encoder BYTE() {
      return Encoders$.MODULE$.BYTE();
   }

   public static Encoder BOOLEAN() {
      return Encoders$.MODULE$.BOOLEAN();
   }
}
