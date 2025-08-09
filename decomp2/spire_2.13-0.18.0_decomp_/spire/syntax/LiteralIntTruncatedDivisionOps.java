package spire.syntax;

import algebra.ring.TruncatedDivision;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import spire.math.ConvertableTo;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uh\u0001B\f\u0019\u0005uA\u0001\u0002\n\u0001\u0003\u0006\u0004%\t!\n\u0005\tS\u0001\u0011\t\u0011)A\u0005M!)!\u0006\u0001C\u0001W!)q\u0006\u0001C\u0001a!)!\f\u0001C\u00017\")Q\r\u0001C\u0001M\")1\u000f\u0001C\u0001i\")a\u0010\u0001C\u0001\u007f\"9\u00111\u0003\u0001\u0005\u0002\u0005U\u0001\"CA\u0016\u0001\u0005\u0005I\u0011IA\u0017\u0011%\ty\u0003AA\u0001\n\u0003\n\tdB\u0005\u0002>a\t\t\u0011#\u0001\u0002@\u0019Aq\u0003GA\u0001\u0012\u0003\t\t\u0005\u0003\u0004+\u001b\u0011\u0005\u0011\u0011\n\u0005\b\u0003\u0017jAQAA'\u0011\u001d\t9'\u0004C\u0003\u0003SBq!!!\u000e\t\u000b\t\u0019\tC\u0004\u0002\u001e6!)!a(\t\u000f\u0005]V\u0002\"\u0002\u0002:\"9\u0011\u0011[\u0007\u0005\u0006\u0005M\u0007\"CAw\u001b\u0005\u0005IQAAx\u0011%\t\u00190DA\u0001\n\u000b\t)P\u0001\u0010MSR,'/\u00197J]R$&/\u001e8dCR,G\rR5wSNLwN\\(qg*\u0011\u0011DG\u0001\u0007gftG/\u0019=\u000b\u0003m\tQa\u001d9je\u0016\u001c\u0001a\u0005\u0002\u0001=A\u0011qDI\u0007\u0002A)\t\u0011%A\u0003tG\u0006d\u0017-\u0003\u0002$A\t1\u0011I\\=WC2\f1\u0001\u001c5t+\u00051\u0003CA\u0010(\u0013\tA\u0003EA\u0002J]R\fA\u0001\u001c5tA\u00051A(\u001b8jiz\"\"\u0001\f\u0018\u0011\u00055\u0002Q\"\u0001\r\t\u000b\u0011\u001a\u0001\u0019\u0001\u0014\u0002\u000bQ\fXo\u001c;\u0016\u0005E*DC\u0001\u001aY)\r\u0019d\b\u0015\t\u0003iUb\u0001\u0001B\u00037\t\t\u0007qGA\u0001B#\tA4\b\u0005\u0002 s%\u0011!\b\t\u0002\b\u001d>$\b.\u001b8h!\tyB(\u0003\u0002>A\t\u0019\u0011I\\=\t\u000b}\"\u00019\u0001!\u0002\u0005\u00154\bcA!Ng9\u0011!I\u0013\b\u0003\u0007\"s!\u0001R$\u000e\u0003\u0015S!A\u0012\u000f\u0002\rq\u0012xn\u001c;?\u0013\u0005Y\u0012BA%\u001b\u0003\u001d\tGnZ3ce\u0006L!a\u0013'\u0002\u000fA\f7m[1hK*\u0011\u0011JG\u0005\u0003\u001d>\u0013\u0011\u0003\u0016:v]\u000e\fG/\u001a3ESZL7/[8o\u0015\tYE\nC\u0003R\t\u0001\u000f!+A\u0001d!\r\u0019fkM\u0007\u0002)*\u0011QKG\u0001\u0005[\u0006$\b.\u0003\u0002X)\ni1i\u001c8wKJ$\u0018M\u00197f)>DQ!\u0017\u0003A\u0002M\n1A\u001d5t\u0003\u0011!Xn\u001c3\u0016\u0005q{FCA/e)\rq\u0006M\u0019\t\u0003i}#QAN\u0003C\u0002]BQaP\u0003A\u0004\u0005\u00042!Q'_\u0011\u0015\tV\u0001q\u0001d!\r\u0019fK\u0018\u0005\u00063\u0016\u0001\rAX\u0001\tiF,x\u000e^7pIV\u0011q-\u001c\u000b\u0003QJ$2!\u001b8q!\u0011y\"\u000e\u001c7\n\u0005-\u0004#A\u0002+va2,'\u0007\u0005\u00025[\u0012)aG\u0002b\u0001o!)qH\u0002a\u0002_B\u0019\u0011)\u00147\t\u000bE3\u00019A9\u0011\u0007M3F\u000eC\u0003Z\r\u0001\u0007A.A\u0003gcV|G/\u0006\u0002vqR\u0011a/ \u000b\u0004of\\\bC\u0001\u001by\t\u00151tA1\u00018\u0011\u0015yt\u0001q\u0001{!\r\tUj\u001e\u0005\u0006#\u001e\u0001\u001d\u0001 \t\u0004'Z;\b\"B-\b\u0001\u00049\u0018\u0001\u00024n_\u0012,B!!\u0001\u0002\bQ!\u00111AA\t)\u0019\t)!!\u0003\u0002\u000eA\u0019A'a\u0002\u0005\u000bYB!\u0019A\u001c\t\r}B\u00019AA\u0006!\u0011\tU*!\u0002\t\rEC\u00019AA\b!\u0011\u0019f+!\u0002\t\reC\u0001\u0019AA\u0003\u0003!1\u0017/^8u[>$W\u0003BA\f\u0003?!B!!\u0007\u0002*Q1\u00111DA\u0011\u0003K\u0001ba\b6\u0002\u001e\u0005u\u0001c\u0001\u001b\u0002 \u0011)a'\u0003b\u0001o!1q(\u0003a\u0002\u0003G\u0001B!Q'\u0002\u001e!1\u0011+\u0003a\u0002\u0003O\u0001Ba\u0015,\u0002\u001e!1\u0011,\u0003a\u0001\u0003;\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002M\u00051Q-];bYN$B!a\r\u0002:A\u0019q$!\u000e\n\u0007\u0005]\u0002EA\u0004C_>dW-\u00198\t\u0011\u0005m2\"!AA\u0002m\n1\u0001\u001f\u00132\u0003ya\u0015\u000e^3sC2Le\u000e\u001e+sk:\u001c\u0017\r^3e\t&4\u0018n]5p]>\u00038\u000f\u0005\u0002.\u001bM\u0019Q\"a\u0011\u0011\u0007}\t)%C\u0002\u0002H\u0001\u0012a!\u00118z%\u00164GCAA \u0003=!\u0018/^8uI\u0015DH/\u001a8tS>tW\u0003BA(\u0003/\"B!!\u0015\u0002dQ!\u00111KA1)\u0019\t)&!\u0017\u0002^A\u0019A'a\u0016\u0005\u000bYz!\u0019A\u001c\t\r}z\u00019AA.!\u0011\tU*!\u0016\t\rE{\u00019AA0!\u0011\u0019f+!\u0016\t\re{\u0001\u0019AA+\u0011\u0019\t)g\u0004a\u0001Y\u0005)A\u0005\u001e5jg\u0006qA/\\8eI\u0015DH/\u001a8tS>tW\u0003BA6\u0003g\"B!!\u001c\u0002\u0000Q!\u0011qNA?)\u0019\t\t(!\u001e\u0002zA\u0019A'a\u001d\u0005\u000bY\u0002\"\u0019A\u001c\t\r}\u0002\u00029AA<!\u0011\tU*!\u001d\t\rE\u0003\u00029AA>!\u0011\u0019f+!\u001d\t\re\u0003\u0002\u0019AA9\u0011\u0019\t)\u0007\u0005a\u0001Y\u0005\u0011B/];pi6|G\rJ3yi\u0016t7/[8o+\u0011\t))a$\u0015\t\u0005\u001d\u00151\u0014\u000b\u0005\u0003\u0013\u000bI\n\u0006\u0004\u0002\f\u0006E\u0015Q\u0013\t\u0007?)\fi)!$\u0011\u0007Q\ny\tB\u00037#\t\u0007q\u0007\u0003\u0004@#\u0001\u000f\u00111\u0013\t\u0005\u00036\u000bi\t\u0003\u0004R#\u0001\u000f\u0011q\u0013\t\u0005'Z\u000bi\t\u0003\u0004Z#\u0001\u0007\u0011Q\u0012\u0005\u0007\u0003K\n\u0002\u0019\u0001\u0017\u0002\u001f\u0019\fXo\u001c;%Kb$XM\\:j_:,B!!)\u0002*R!\u00111UA[)\u0011\t)+a-\u0015\r\u0005\u001d\u00161VAX!\r!\u0014\u0011\u0016\u0003\u0006mI\u0011\ra\u000e\u0005\u0007\u007fI\u0001\u001d!!,\u0011\t\u0005k\u0015q\u0015\u0005\u0007#J\u0001\u001d!!-\u0011\tM3\u0016q\u0015\u0005\u00073J\u0001\r!a*\t\r\u0005\u0015$\u00031\u0001-\u000391Wn\u001c3%Kb$XM\\:j_:,B!a/\u0002DR!\u0011QXAh)\u0011\ty,!4\u0015\r\u0005\u0005\u0017QYAe!\r!\u00141\u0019\u0003\u0006mM\u0011\ra\u000e\u0005\u0007\u007fM\u0001\u001d!a2\u0011\t\u0005k\u0015\u0011\u0019\u0005\u0007#N\u0001\u001d!a3\u0011\tM3\u0016\u0011\u0019\u0005\u00073N\u0001\r!!1\t\r\u0005\u00154\u00031\u0001-\u0003I1\u0017/^8u[>$G%\u001a=uK:\u001c\u0018n\u001c8\u0016\t\u0005U\u0017q\u001c\u000b\u0005\u0003/\fY\u000f\u0006\u0003\u0002Z\u0006%HCBAn\u0003C\f)\u000f\u0005\u0004 U\u0006u\u0017Q\u001c\t\u0004i\u0005}G!\u0002\u001c\u0015\u0005\u00049\u0004BB \u0015\u0001\b\t\u0019\u000f\u0005\u0003B\u001b\u0006u\u0007BB)\u0015\u0001\b\t9\u000f\u0005\u0003T-\u0006u\u0007BB-\u0015\u0001\u0004\ti\u000e\u0003\u0004\u0002fQ\u0001\r\u0001L\u0001\u0013Q\u0006\u001c\bnQ8eK\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0002.\u0005E\bBBA3+\u0001\u0007A&\u0001\tfcV\fGn\u001d\u0013fqR,gn]5p]R!\u0011q_A~)\u0011\t\u0019$!?\t\u0011\u0005mb#!AA\u0002mBa!!\u001a\u0017\u0001\u0004a\u0003"
)
public final class LiteralIntTruncatedDivisionOps {
   private final int lhs;

   public static boolean equals$extension(final int $this, final Object x$1) {
      return LiteralIntTruncatedDivisionOps$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final int $this) {
      return LiteralIntTruncatedDivisionOps$.MODULE$.hashCode$extension($this);
   }

   public static Tuple2 fquotmod$extension(final int $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralIntTruncatedDivisionOps$.MODULE$.fquotmod$extension($this, rhs, ev, c);
   }

   public static Object fmod$extension(final int $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralIntTruncatedDivisionOps$.MODULE$.fmod$extension($this, rhs, ev, c);
   }

   public static Object fquot$extension(final int $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralIntTruncatedDivisionOps$.MODULE$.fquot$extension($this, rhs, ev, c);
   }

   public static Tuple2 tquotmod$extension(final int $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralIntTruncatedDivisionOps$.MODULE$.tquotmod$extension($this, rhs, ev, c);
   }

   public static Object tmod$extension(final int $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralIntTruncatedDivisionOps$.MODULE$.tmod$extension($this, rhs, ev, c);
   }

   public static Object tquot$extension(final int $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralIntTruncatedDivisionOps$.MODULE$.tquot$extension($this, rhs, ev, c);
   }

   public int lhs() {
      return this.lhs;
   }

   public Object tquot(final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralIntTruncatedDivisionOps$.MODULE$.tquot$extension(this.lhs(), rhs, ev, c);
   }

   public Object tmod(final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralIntTruncatedDivisionOps$.MODULE$.tmod$extension(this.lhs(), rhs, ev, c);
   }

   public Tuple2 tquotmod(final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralIntTruncatedDivisionOps$.MODULE$.tquotmod$extension(this.lhs(), rhs, ev, c);
   }

   public Object fquot(final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralIntTruncatedDivisionOps$.MODULE$.fquot$extension(this.lhs(), rhs, ev, c);
   }

   public Object fmod(final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralIntTruncatedDivisionOps$.MODULE$.fmod$extension(this.lhs(), rhs, ev, c);
   }

   public Tuple2 fquotmod(final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralIntTruncatedDivisionOps$.MODULE$.fquotmod$extension(this.lhs(), rhs, ev, c);
   }

   public int hashCode() {
      return LiteralIntTruncatedDivisionOps$.MODULE$.hashCode$extension(this.lhs());
   }

   public boolean equals(final Object x$1) {
      return LiteralIntTruncatedDivisionOps$.MODULE$.equals$extension(this.lhs(), x$1);
   }

   public LiteralIntTruncatedDivisionOps(final int lhs) {
      this.lhs = lhs;
   }
}
