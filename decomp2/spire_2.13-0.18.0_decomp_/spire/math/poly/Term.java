package spire.math.poly;

import algebra.ring.Field;
import algebra.ring.Rig;
import algebra.ring.Ring;
import algebra.ring.Rng;
import algebra.ring.Semiring;
import cats.kernel.Eq;
import cats.kernel.Order;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.LinearSeqOps;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\r]b\u0001B\u001a5\u0001nB\u0001B\u0015\u0001\u0003\u0016\u0004%\ta\u0015\u0005\t[\u0002\u0011\t\u0012)A\u0005)\"Aa\u000e\u0001BK\u0002\u0013\u0005q\u000e\u0003\u0005t\u0001\tE\t\u0015!\u0003q\u0011\u0015!\b\u0001\"\u0001v\u0011\u0015Q\b\u0001\"\u0001|\u0011\u001d\t)\u0002\u0001C\u0001\u0003/Aq!a\n\u0001\t\u0003\tI\u0003C\u0004\u00022\u0001!\t!a\r\t\u000f\u0005m\u0002\u0001\"\u0001\u0002>!9\u0011q\t\u0001\u0005\u0002\u0005%\u0003bBA)\u0001\u0011\u0005\u00111\u000b\u0005\b\u0003G\u0002A\u0011AA3\u0011\u001d\t)\b\u0001C\u0001\u0003oBq!!!\u0001\t\u0003\t\u0019\tC\u0004\u0002\b\u0002!\t%!#\t\u0013\u0005m\u0005!!A\u0005\u0002\u0005u\u0005\"CA[\u0001E\u0005I\u0011AA\\\u0011%\tY\u000eAI\u0001\n\u0003\ti\u000eC\u0005\u0002p\u0002\t\t\u0011\"\u0011\u0002r\"A!\u0011\u0001\u0001\u0002\u0002\u0013\u0005q\u000eC\u0005\u0003\u0004\u0001\t\t\u0011\"\u0001\u0003\u0006!I!1\u0002\u0001\u0002\u0002\u0013\u0005#Q\u0002\u0005\n\u00057\u0001\u0011\u0011!C\u0001\u0005;A\u0011B!\t\u0001\u0003\u0003%\tEa\t\t\u0013\t\u001d\u0002!!A\u0005B\t%\u0002\"\u0003B\u0016\u0001\u0005\u0005I\u0011\tB\u0017\u000f\u001d\u0011\t\u0004\u000eE\u0001\u0005g1aa\r\u001b\t\u0002\tU\u0002B\u0002;\u001e\t\u0003\u0011\t\u0005C\u0004\u0003Du!\u0019A!\u0012\t\u000f\tUS\u0004\"\u0001\u0003X!9!\u0011O\u000f\u0005\u0002\tM\u0004b\u0002BF;\u0011\u0005!Q\u0012\u0005\n\u0005Sk\"\u0019!C\u0005\u0005WC\u0001B!0\u001eA\u0003%!Q\u0016\u0005\n\u0005\u007fk\"\u0019!C\u0005\u0005WC\u0001B!1\u001eA\u0003%!Q\u0016\u0005\n\u0005\u0007l\"\u0019!C\u0005\u0005\u000bD\u0001B!6\u001eA\u0003%!q\u0019\u0005\n\u0005/l\"\u0019!C\u0005\u0005WC\u0001B!7\u001eA\u0003%!Q\u0016\u0005\t\u00057lB\u0011\u0001\u001d\u0003^\"I!1]\u000fC\u0002\u0013%!Q\u001d\u0005\t\u0005[l\u0002\u0015!\u0003\u0003h\"I!1\\\u000fC\u0002\u0013%!Q\u001d\u0005\t\u0005_l\u0002\u0015!\u0003\u0003h\"I!\u0011_\u000f\u0002\u0002\u0013\u0005%1\u001f\u0005\n\u0007\u0017i\u0012\u0011!CA\u0007\u001bA\u0011b!\f\u001e\u0003\u0003%Iaa\f\u0003\tQ+'/\u001c\u0006\u0003kY\nA\u0001]8ms*\u0011q\u0007O\u0001\u0005[\u0006$\bNC\u0001:\u0003\u0015\u0019\b/\u001b:f\u0007\u0001)\"\u0001\u0010,\u0014\t\u0001i4I\u0012\t\u0003}\u0005k\u0011a\u0010\u0006\u0002\u0001\u0006)1oY1mC&\u0011!i\u0010\u0002\u0007\u0003:L(+\u001a4\u0011\u0005y\"\u0015BA#@\u0005\u001d\u0001&o\u001c3vGR\u0004\"aR(\u000f\u0005!keBA%M\u001b\u0005Q%BA&;\u0003\u0019a$o\\8u}%\t\u0001)\u0003\u0002O\u007f\u00059\u0001/Y2lC\u001e,\u0017B\u0001)R\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tqu(A\u0003d_\u00164g-F\u0001U!\t)f\u000b\u0004\u0001\u0005\u0013]\u0003\u0001\u0015!A\u0001\u0006\u0004A&!A\"\u0012\u0005ec\u0006C\u0001 [\u0013\tYvHA\u0004O_RD\u0017N\\4\u0011\u0005yj\u0016B\u00010@\u0005\r\te.\u001f\u0015\u0005-\u0002\u001c\u0007\u000e\u0005\u0002?C&\u0011!m\u0010\u0002\fgB,7-[1mSj,G-M\u0003$I\u0016<gM\u0004\u0002?K&\u0011amP\u0001\u0006\r2|\u0017\r^\u0019\u0005I!c\u0005)M\u0003$S*d7N\u0004\u0002?U&\u00111nP\u0001\u0007\t>,(\r\\32\t\u0011BE\nQ\u0001\u0007G>,gM\u001a\u0011\u0002\u0007\u0015D\b/F\u0001q!\tq\u0014/\u0003\u0002s\u007f\t\u0019\u0011J\u001c;\u0002\t\u0015D\b\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007YD\u0018\u0010E\u0002x\u0001Qk\u0011\u0001\u000e\u0005\u0006%\u0016\u0001\r\u0001\u0016\u0005\u0006]\u0016\u0001\r\u0001]\u0001\rk:\f'/_0%[&tWo\u001d\u000b\u0003mrDQ! \u0004A\u0004y\f\u0011A\u001d\t\u0005\u007f\u0006=AK\u0004\u0003\u0002\u0002\u0005-a\u0002BA\u0002\u0003\u000fq1!SA\u0003\u0013\u0005I\u0014bAA\u0005q\u00059\u0011\r\\4fEJ\f\u0017b\u0001(\u0002\u000e)\u0019\u0011\u0011\u0002\u001d\n\t\u0005E\u00111\u0003\u0002\u0004%:<'b\u0001(\u0002\u000e\u0005)A\u0005\u001d7vgR!\u0011\u0011DA\u0012)\r1\u00181\u0004\u0005\u0007{\u001e\u0001\u001d!!\b\u0011\t}\fy\u0002V\u0005\u0005\u0003C\t\u0019B\u0001\u0005TK6L'/\u001b8h\u0011\u0019\t)c\u0002a\u0001m\u0006\u0019!\u000f[:\u0002\r\u0011\"\u0018.\\3t)\u0011\tY#a\f\u0015\u0007Y\fi\u0003\u0003\u0004~\u0011\u0001\u000f\u0011Q\u0004\u0005\u0007\u0003KA\u0001\u0019\u0001<\u0002\u000fQ|G+\u001e9mKV\u0011\u0011Q\u0007\t\u0006}\u0005]\u0002\u000fV\u0005\u0004\u0003sy$A\u0002+va2,''\u0001\u0003fm\u0006dG\u0003BA \u0003\u0007\"2\u0001VA!\u0011\u0019i(\u0002q\u0001\u0002\u001e!1\u0011Q\t\u0006A\u0002Q\u000b\u0011\u0001_\u0001\fSNLe\u000eZ3y5\u0016\u0014x.\u0006\u0002\u0002LA\u0019a(!\u0014\n\u0007\u0005=sHA\u0004C_>dW-\u00198\u0002\r%\u001c(,\u001a:p)\u0019\tY%!\u0016\u0002Z!9\u0011q\u000b\u0007A\u0004\u0005u\u0011\u0001\u0002:j]\u001eDq!a\u0017\r\u0001\b\ti&\u0001\u0002fcB!q0a\u0018U\u0013\u0011\t\t'a\u0005\u0003\u0005\u0015\u000b\u0018\u0001\u00033jm&$WMQ=\u0015\t\u0005\u001d\u00141\u000f\u000b\u0004m\u0006%\u0004bBA6\u001b\u0001\u000f\u0011QN\u0001\u0002MB!q0a\u001cU\u0013\u0011\t\t(a\u0005\u0003\u000b\u0019KW\r\u001c3\t\r\u0005\u0015S\u00021\u0001U\u0003\r!WM\u001d\u000b\u0004m\u0006e\u0004BB?\u000f\u0001\b\tY\b\u0005\u0003\u0000\u0003{\"\u0016\u0002BA@\u0003'\u0011AAU5oO\u0006\u0019\u0011N\u001c;\u0015\u0007Y\f)\tC\u0004\u0002l=\u0001\u001d!!\u001c\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a#\u0011\t\u00055\u0015Q\u0013\b\u0005\u0003\u001f\u000b\t\n\u0005\u0002J\u007f%\u0019\u00111S \u0002\rA\u0013X\rZ3g\u0013\u0011\t9*!'\u0003\rM#(/\u001b8h\u0015\r\t\u0019jP\u0001\u0005G>\u0004\u00180\u0006\u0003\u0002 \u0006\u0015FCBAQ\u0003c\u000b\u0019\f\u0005\u0003x\u0001\u0005\r\u0006cA+\u0002&\u0012Iq+\u0005Q\u0001\u0002\u0003\u0015\r\u0001\u0017\u0015\b\u0003K\u0003\u0017\u0011VAWc\u0019\u0019C-ZAVMF\"A\u0005\u0013'Ac\u0019\u0019\u0013N[AXWF\"A\u0005\u0013'A\u0011!\u0011\u0016\u0003%AA\u0002\u0005\r\u0006b\u00028\u0012!\u0003\u0005\r\u0001]\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0011\tI,a4\u0016\u0005\u0005m&f\u0001+\u0002>.\u0012\u0011q\u0018\t\u0005\u0003\u0003\fY-\u0004\u0002\u0002D*!\u0011QYAd\u0003%)hn\u00195fG.,GMC\u0002\u0002J~\n!\"\u00198o_R\fG/[8o\u0013\u0011\ti-a1\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u0005X%\u0001\u0006\t\u0011!b\u00011\":\u0011q\u001a1\u0002T\u0006]\u0017GB\u0012eK\u0006Ug-\r\u0003%\u00112\u0003\u0015GB\u0012jU\u0006e7.\r\u0003%\u00112\u0003\u0015AD2paf$C-\u001a4bk2$HEM\u000b\u0005\u0003?\f\u0019/\u0006\u0002\u0002b*\u001a\u0001/!0\u0005\u0013]\u001b\u0002\u0015!A\u0001\u0006\u0004A\u0006fBArA\u0006\u001d\u00181^\u0019\u0007G\u0011,\u0017\u0011\u001e42\t\u0011BE\nQ\u0019\u0007G%T\u0017Q^62\t\u0011BE\nQ\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005M\b\u0003BA{\u0003\u007fl!!a>\u000b\t\u0005e\u00181`\u0001\u0005Y\u0006twM\u0003\u0002\u0002~\u0006!!.\u0019<b\u0013\u0011\t9*a>\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019ALa\u0002\t\u0011\t%a#!AA\u0002A\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XC\u0001B\b!\u0015\u0011\tBa\u0006]\u001b\t\u0011\u0019BC\u0002\u0003\u0016}\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\u0011IBa\u0005\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u0017\u0012y\u0002\u0003\u0005\u0003\na\t\t\u00111\u0001]\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005M(Q\u0005\u0005\t\u0005\u0013I\u0012\u0011!a\u0001a\u0006A\u0001.Y:i\u0007>$W\rF\u0001q\u0003\u0019)\u0017/^1mgR!\u00111\nB\u0018\u0011!\u0011IaGA\u0001\u0002\u0004a\u0016\u0001\u0002+fe6\u0004\"a^\u000f\u0014\tui$q\u0007\t\u0005\u0005s\u0011y$\u0004\u0002\u0003<)!!QHA~\u0003\tIw.C\u0002Q\u0005w!\"Aa\r\u0002\u0011=\u0014H-\u001a:j]\u001e,BAa\u0012\u0003TU\u0011!\u0011\n\t\u0006\u007f\n-#qJ\u0005\u0005\u0005\u001b\n\u0019BA\u0003Pe\u0012,'\u000f\u0005\u0003x\u0001\tE\u0003cA+\u0003T\u0011)qk\bb\u00011\u0006IaM]8n)V\u0004H.Z\u000b\u0005\u00053\u0012y\u0006\u0006\u0003\u0003\\\t-\u0004\u0003B<\u0001\u0005;\u00022!\u0016B0\t%9\u0006\u0005)A\u0001\u0002\u000b\u0007\u0001\fK\u0004\u0003`\u0001\u0014\u0019Ga\u001a2\r\r\"WM!\u001agc\u0011!\u0003\n\u0014!2\r\rJ'N!\u001blc\u0011!\u0003\n\u0014!\t\u000f\t5\u0004\u00051\u0001\u0003p\u0005\u0019A\u000f\u001d7\u0011\ry\n9\u0004\u001dB/\u0003\u0011QXM]8\u0016\t\tU$1\u0010\u000b\u0005\u0005o\u00129\t\u0005\u0003x\u0001\te\u0004cA+\u0003|\u0011Iq+\tQ\u0001\u0002\u0003\u0015\r\u0001\u0017\u0015\b\u0005w\u0002'q\u0010BBc\u0019\u0019C-\u001aBAMF\"A\u0005\u0013'Ac\u0019\u0019\u0013N\u001bBCWF\"A\u0005\u0013'A\u0011\u0019i\u0018\u0005q\u0001\u0003\nB)q0a\b\u0003z\u0005\u0019qN\\3\u0016\t\t=%Q\u0013\u000b\u0005\u0005#\u0013\t\u000b\u0005\u0003x\u0001\tM\u0005cA+\u0003\u0016\u0012IqK\tQ\u0001\u0002\u0003\u0015\r\u0001\u0017\u0015\b\u0005+\u0003'\u0011\u0014BOc\u0019\u0019C-\u001aBNMF\"A\u0005\u0013'Ac\u0019\u0019\u0013N\u001bBPWF\"A\u0005\u0013'A\u0011\u0019i(\u0005q\u0001\u0003$B)qP!*\u0003\u0014&!!qUA\n\u0005\r\u0011\u0016nZ\u0001\u0007\u0013NTVM]8\u0016\u0005\t5\u0006\u0003\u0002BX\u0005sk!A!-\u000b\t\tM&QW\u0001\t[\u0006$8\r[5oO*\u0019!qW \u0002\tU$\u0018\u000e\\\u0005\u0005\u0005w\u0013\tLA\u0003SK\u001e,\u00070A\u0004Jgj+'o\u001c\u0011\u0002\u0015%\u001bh*Z4bi&4X-A\u0006Jg:+w-\u0019;jm\u0016\u0004\u0013A\u00053jO&$Hk\\*va\u0016\u00148o\u0019:jaR,\"Aa2\u0011\u000by\u0012IM!4\n\u0007\t-wHA\u0003BeJ\f\u0017\u0010E\u0004?\u0003o\u0011yMa4\u0011\u0007y\u0012\t.C\u0002\u0003T~\u0012Aa\u00115be\u0006\u0019B-[4jiR{7+\u001e9feN\u001c'/\u001b9uA\u0005\u00012/\u001e9feN\u001c'/\u001b9u%\u0016<W\r_\u0001\u0012gV\u0004XM]:de&\u0004HOU3hKb\u0004\u0013!\u0005:f[>4XmU;qKJ\u001c8M]5qiR!\u00111\u0012Bp\u0011\u001d\u0011\to\u000ba\u0001\u0003\u0017\u000bA\u0001^3yi\u0006Y1/\u001e9feN\u001c'/\u001b9u+\t\u00119\u000fE\u0004?\u0005S\u0014yMa4\n\u0007\t-xHA\u0005Gk:\u001cG/[8oc\u0005a1/\u001e9feN\u001c'/\u001b9uA\u0005\u0011\"/Z7pm\u0016\u001cV\u000f]3sg\u000e\u0014\u0018\u000e\u001d;!\u0003\u0015\t\u0007\u000f\u001d7z+\u0011\u0011)Pa?\u0015\r\t]8qAB\u0005!\u00119\bA!?\u0011\u0007U\u0013Y\u0010B\u0005Xa\u0001\u0006\t\u0011!b\u00011\":!1 1\u0003\u0000\u000e\r\u0011GB\u0012eK\u000e\u0005a-\r\u0003%\u00112\u0003\u0015GB\u0012jU\u000e\u00151.\r\u0003%\u00112\u0003\u0005B\u0002*1\u0001\u0004\u0011I\u0010C\u0003oa\u0001\u0007\u0001/A\u0004v]\u0006\u0004\b\u000f\\=\u0016\t\r=11\u0004\u000b\u0005\u0007#\u00199\u0003E\u0003?\u0007'\u00199\"C\u0002\u0004\u0016}\u0012aa\u00149uS>t\u0007C\u0002 \u00028\re\u0001\u000fE\u0002V\u00077!\u0011bV\u0019!\u0002\u0003\u0005)\u0019\u0001-)\u000f\rm\u0001ma\b\u0004$E21\u0005Z3\u0004\"\u0019\fD\u0001\n%M\u0001F21%\u001b6\u0004&-\fD\u0001\n%M\u0001\"I1\u0011F\u0019\u0002\u0002\u0003\u000711F\u0001\u0004q\u0012\u0002\u0004\u0003B<\u0001\u00073\tAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"a!\r\u0011\t\u0005U81G\u0005\u0005\u0007k\t9P\u0001\u0004PE*,7\r\u001e"
)
public class Term implements Product, Serializable {
   public final Object coeff;
   private final int exp;

   public static Option unapply(final Term x$0) {
      return Term$.MODULE$.unapply(x$0);
   }

   public static Term apply(final Object coeff, final int exp) {
      return Term$.MODULE$.apply(coeff, exp);
   }

   public static Term one(final Rig r) {
      return Term$.MODULE$.one(r);
   }

   public static Term zero(final Semiring r) {
      return Term$.MODULE$.zero(r);
   }

   public static Term fromTuple(final Tuple2 tpl) {
      return Term$.MODULE$.fromTuple(tpl);
   }

   public static Order ordering() {
      return Term$.MODULE$.ordering();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Object coeff() {
      return this.coeff;
   }

   public int exp() {
      return this.exp;
   }

   public Term unary_$minus(final Rng r) {
      return new Term(r.negate(this.coeff()), this.exp());
   }

   public Term $plus(final Term rhs, final Semiring r) {
      if (this.exp() != rhs.exp()) {
         throw new IllegalArgumentException((new StringBuilder(31)).append("can't add terms of degree ").append(this.exp()).append(" and ").append(rhs.exp()).toString());
      } else {
         return new Term(r.plus(this.coeff(), rhs.coeff()), this.exp());
      }
   }

   public Term $times(final Term rhs, final Semiring r) {
      return new Term(r.times(this.coeff(), rhs.coeff()), this.exp() + rhs.exp());
   }

   public Tuple2 toTuple() {
      return new Tuple2(BoxesRunTime.boxToInteger(this.exp()), this.coeff());
   }

   public Object eval(final Object x, final Semiring r) {
      return this.exp() != 0 ? r.times(this.coeff(), r.pow(x, this.exp())) : this.coeff();
   }

   public boolean isIndexZero() {
      return this.exp() == 0;
   }

   public boolean isZero(final Semiring ring, final Eq eq) {
      return eq.eqv(this.coeff(), ring.zero());
   }

   public Term divideBy(final Object x, final Field f) {
      return new Term(f.div(this.coeff(), x), this.exp());
   }

   public Term der(final Ring r) {
      return new Term(r.times(this.coeff(), r.fromInt(this.exp())), this.exp() - 1);
   }

   public Term int(final Field f) {
      return new Term(f.div(this.coeff(), f.fromInt(this.exp() + 1)), this.exp() + 1);
   }

   public String toString() {
      return (String)this.simpleCoeff$1().orElse(() -> this.stringCoeff$1()).getOrElse(() -> (new StringBuilder(3)).append(" + ").append(this.coeff()).append(this.expString$1()).toString());
   }

   public Term copy(final Object coeff, final int exp) {
      return new Term(coeff, exp);
   }

   public Object copy$default$1() {
      return this.coeff();
   }

   public int copy$default$2() {
      return this.exp();
   }

   public String productPrefix() {
      return "Term";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.coeff();
            break;
         case 1:
            var10000 = BoxesRunTime.boxToInteger(this.exp());
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
      return x$1 instanceof Term;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "coeff";
            break;
         case 1:
            var10000 = "exp";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.coeff()));
      var1 = Statics.mix(var1, this.exp());
      return Statics.finalizeHash(var1, 2);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label51: {
            boolean var2;
            if (x$1 instanceof Term) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Term var4 = (Term)x$1;
               if (this.exp() == var4.exp() && BoxesRunTime.equals(this.coeff(), var4.coeff()) && var4.canEqual(this)) {
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

   public double coeff$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.coeff());
   }

   public float coeff$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.coeff());
   }

   public Term unary_$minus$mcD$sp(final Rng r) {
      return this.unary_$minus(r);
   }

   public Term unary_$minus$mcF$sp(final Rng r) {
      return this.unary_$minus(r);
   }

   public Term $plus$mcD$sp(final Term rhs, final Semiring r) {
      return this.$plus(rhs, r);
   }

   public Term $plus$mcF$sp(final Term rhs, final Semiring r) {
      return this.$plus(rhs, r);
   }

   public Term $times$mcD$sp(final Term rhs, final Semiring r) {
      return this.$times(rhs, r);
   }

   public Term $times$mcF$sp(final Term rhs, final Semiring r) {
      return this.$times(rhs, r);
   }

   public Tuple2 toTuple$mcD$sp() {
      return this.toTuple();
   }

   public Tuple2 toTuple$mcF$sp() {
      return this.toTuple();
   }

   public double eval$mcD$sp(final double x, final Semiring r) {
      return BoxesRunTime.unboxToDouble(this.eval(BoxesRunTime.boxToDouble(x), r));
   }

   public float eval$mcF$sp(final float x, final Semiring r) {
      return BoxesRunTime.unboxToFloat(this.eval(BoxesRunTime.boxToFloat(x), r));
   }

   public boolean isZero$mcD$sp(final Semiring ring, final Eq eq) {
      return this.isZero(ring, eq);
   }

   public boolean isZero$mcF$sp(final Semiring ring, final Eq eq) {
      return this.isZero(ring, eq);
   }

   public Term divideBy$mcD$sp(final double x, final Field f) {
      return this.divideBy(BoxesRunTime.boxToDouble(x), f);
   }

   public Term divideBy$mcF$sp(final float x, final Field f) {
      return this.divideBy(BoxesRunTime.boxToFloat(x), f);
   }

   public Term der$mcD$sp(final Ring r) {
      return this.der(r);
   }

   public Term der$mcF$sp(final Ring r) {
      return this.der(r);
   }

   public Term int$mcD$sp(final Field f) {
      return this.int(f);
   }

   public Term int$mcF$sp(final Field f) {
      return this.int(f);
   }

   public Term copy$mDc$sp(final double coeff, final int exp) {
      return new Term$mcD$sp(coeff, exp);
   }

   public Term copy$mFc$sp(final float coeff, final int exp) {
      return new Term$mcF$sp(coeff, exp);
   }

   public double copy$default$1$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.copy$default$1());
   }

   public float copy$default$1$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.copy$default$1());
   }

   public boolean specInstance$() {
      return false;
   }

   private final String expString$1() {
      int var1 = this.exp();
      String var10000;
      switch (var1) {
         case 0:
            var10000 = "";
            break;
         case 1:
            var10000 = "x";
            break;
         default:
            var10000 = (new StringBuilder(1)).append("x").append(scala.collection.StringOps..MODULE$.map$extension(scala.Predef..MODULE$.augmentString(Integer.toString(this.exp())), Term$.MODULE$.spire$math$poly$Term$$superscript())).toString();
      }

      return var10000;
   }

   private final Option simpleCoeff$1() {
      boolean var2 = false;
      Object var3 = null;
      Object var4 = this.coeff();
      Object var1;
      if (BoxesRunTime.equals(BoxesRunTime.boxToInteger(0), var4)) {
         var1 = new Some("");
      } else {
         if (BoxesRunTime.equals(BoxesRunTime.boxToInteger(1), var4)) {
            var2 = true;
            if (this.exp() == 0) {
               var1 = new Some((new StringBuilder(3)).append(" + ").append(this.coeff()).toString());
               return (Option)var1;
            }
         }

         if (var2) {
            var1 = new Some((new StringBuilder(3)).append(" + ").append(this.expString$1()).toString());
         } else if (BoxesRunTime.equals(BoxesRunTime.boxToInteger(-1), var4) && this.exp() != 0) {
            var1 = new Some((new StringBuilder(3)).append(" - ").append(this.expString$1()).toString());
         } else {
            var1 = scala.None..MODULE$;
         }
      }

      return (Option)var1;
   }

   private final Option stringCoeff$1() {
      String var2 = this.coeff().toString();
      Object var1;
      if (var2 != null) {
         Option var3 = Term$.MODULE$.spire$math$poly$Term$$IsZero().unapplySeq(var2);
         if (!var3.isEmpty() && var3.get() != null && ((List)var3.get()).lengthCompare(0) == 0) {
            var1 = new Some("");
            return (Option)var1;
         }
      }

      if (var2 != null) {
         Option var4 = Term$.MODULE$.spire$math$poly$Term$$IsNegative().unapplySeq(var2);
         if (!var4.isEmpty() && var4.get() != null && ((List)var4.get()).lengthCompare(1) == 0) {
            String posPart = (String)((LinearSeqOps)var4.get()).apply(0);
            if (this.exp() == 0) {
               var1 = new Some((new StringBuilder(3)).append(" - ").append(posPart).toString());
               return (Option)var1;
            }
         }
      }

      if (var2 != null) {
         Option var6 = Term$.MODULE$.spire$math$poly$Term$$IsNegative().unapplySeq(var2);
         if (!var6.isEmpty() && var6.get() != null && ((List)var6.get()).lengthCompare(1) == 0) {
            String posPart = (String)((LinearSeqOps)var6.get()).apply(0);
            var1 = new Some((new StringBuilder(3)).append(" - ").append(posPart).append(this.expString$1()).toString());
            return (Option)var1;
         }
      }

      var1 = scala.None..MODULE$;
      return (Option)var1;
   }

   public Term(final Object coeff, final int exp) {
      this.coeff = coeff;
      this.exp = exp;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
