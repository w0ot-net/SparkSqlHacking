package spire.syntax;

import algebra.ring.TruncatedDivision;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import spire.math.ConvertableTo;

@ScalaSignature(
   bytes = "\u0006\u0005\t\ra\u0001B\f\u0019\u0005uA\u0001\u0002\n\u0001\u0003\u0006\u0004%\t!\n\u0005\tS\u0001\u0011\t\u0011)A\u0005M!)!\u0006\u0001C\u0001W!)q\u0006\u0001C\u0001a!)!\f\u0001C\u00017\")Q\r\u0001C\u0001M\")1\u000f\u0001C\u0001i\")a\u0010\u0001C\u0001\u007f\"9\u00111\u0003\u0001\u0005\u0002\u0005U\u0001\"CA\u0016\u0001\u0005\u0005I\u0011IA\u0017\u0011%\t)\u0004AA\u0001\n\u0003\n9dB\u0005\u0002Da\t\t\u0011#\u0001\u0002F\u0019Aq\u0003GA\u0001\u0012\u0003\t9\u0005\u0003\u0004+\u001b\u0011\u0005\u0011q\n\u0005\b\u0003#jAQAA*\u0011\u001d\ti'\u0004C\u0003\u0003_Bq!a\"\u000e\t\u000b\tI\tC\u0004\u0002$6!)!!*\t\u000f\u0005uV\u0002\"\u0002\u0002@\"9\u0011q[\u0007\u0005\u0006\u0005e\u0007\"CAz\u001b\u0005\u0005IQAA{\u0011%\tI0DA\u0001\n\u000b\tYPA\u0010MSR,'/\u00197M_:<GK];oG\u0006$X\r\u001a#jm&\u001c\u0018n\u001c8PaNT!!\u0007\u000e\u0002\rMLh\u000e^1y\u0015\u0005Y\u0012!B:qSJ,7\u0001A\n\u0003\u0001y\u0001\"a\b\u0012\u000e\u0003\u0001R\u0011!I\u0001\u0006g\u000e\fG.Y\u0005\u0003G\u0001\u0012a!\u00118z-\u0006d\u0017a\u00017igV\ta\u0005\u0005\u0002 O%\u0011\u0001\u0006\t\u0002\u0005\u0019>tw-\u0001\u0003mQN\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002-]A\u0011Q\u0006A\u0007\u00021!)Ae\u0001a\u0001M\u0005)A/];piV\u0011\u0011'\u000e\u000b\u0003ea#2a\r Q!\t!T\u0007\u0004\u0001\u0005\u000bY\"!\u0019A\u001c\u0003\u0003\u0005\u000b\"\u0001O\u001e\u0011\u0005}I\u0014B\u0001\u001e!\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"a\b\u001f\n\u0005u\u0002#aA!os\")q\b\u0002a\u0002\u0001\u0006\u0011QM\u001e\t\u0004\u00036\u001bdB\u0001\"K\u001d\t\u0019\u0005J\u0004\u0002E\u000f6\tQI\u0003\u0002G9\u00051AH]8pizJ\u0011aG\u0005\u0003\u0013j\tq!\u00197hK\n\u0014\u0018-\u0003\u0002L\u0019\u00069\u0001/Y2lC\u001e,'BA%\u001b\u0013\tquJA\tUeVt7-\u0019;fI\u0012Kg/[:j_:T!a\u0013'\t\u000bE#\u00019\u0001*\u0002\u0003\r\u00042a\u0015,4\u001b\u0005!&BA+\u001b\u0003\u0011i\u0017\r\u001e5\n\u0005]#&!D\"p]Z,'\u000f^1cY\u0016$v\u000eC\u0003Z\t\u0001\u00071'A\u0002sQN\fA\u0001^7pIV\u0011Al\u0018\u000b\u0003;\u0012$2A\u00181c!\t!t\fB\u00037\u000b\t\u0007q\u0007C\u0003@\u000b\u0001\u000f\u0011\rE\u0002B\u001bzCQ!U\u0003A\u0004\r\u00042a\u0015,_\u0011\u0015IV\u00011\u0001_\u0003!!\u0018/^8u[>$WCA4n)\tA'\u000fF\u0002j]B\u0004Ba\b6mY&\u00111\u000e\t\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0005QjG!\u0002\u001c\u0007\u0005\u00049\u0004\"B \u0007\u0001\by\u0007cA!NY\")\u0011K\u0002a\u0002cB\u00191K\u00167\t\u000be3\u0001\u0019\u00017\u0002\u000b\u0019\fXo\u001c;\u0016\u0005UDHC\u0001<~)\r9\u0018p\u001f\t\u0003ia$QAN\u0004C\u0002]BQaP\u0004A\u0004i\u00042!Q'x\u0011\u0015\tv\u0001q\u0001}!\r\u0019fk\u001e\u0005\u00063\u001e\u0001\ra^\u0001\u0005M6|G-\u0006\u0003\u0002\u0002\u0005\u001dA\u0003BA\u0002\u0003#!b!!\u0002\u0002\n\u00055\u0001c\u0001\u001b\u0002\b\u0011)a\u0007\u0003b\u0001o!1q\b\u0003a\u0002\u0003\u0017\u0001B!Q'\u0002\u0006!1\u0011\u000b\u0003a\u0002\u0003\u001f\u0001Ba\u0015,\u0002\u0006!1\u0011\f\u0003a\u0001\u0003\u000b\t\u0001BZ9v_Rlw\u000eZ\u000b\u0005\u0003/\ty\u0002\u0006\u0003\u0002\u001a\u0005%BCBA\u000e\u0003C\t)\u0003\u0005\u0004 U\u0006u\u0011Q\u0004\t\u0004i\u0005}A!\u0002\u001c\n\u0005\u00049\u0004BB \n\u0001\b\t\u0019\u0003\u0005\u0003B\u001b\u0006u\u0001BB)\n\u0001\b\t9\u0003\u0005\u0003T-\u0006u\u0001BB-\n\u0001\u0004\ti\"\u0001\u0005iCND7i\u001c3f)\t\ty\u0003E\u0002 \u0003cI1!a\r!\u0005\rIe\u000e^\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005e\u0012q\b\t\u0004?\u0005m\u0012bAA\u001fA\t9!i\\8mK\u0006t\u0007\u0002CA!\u0017\u0005\u0005\t\u0019A\u001e\u0002\u0007a$\u0013'A\u0010MSR,'/\u00197M_:<GK];oG\u0006$X\r\u001a#jm&\u001c\u0018n\u001c8PaN\u0004\"!L\u0007\u0014\u00075\tI\u0005E\u0002 \u0003\u0017J1!!\u0014!\u0005\u0019\te.\u001f*fMR\u0011\u0011QI\u0001\u0010iF,x\u000e\u001e\u0013fqR,gn]5p]V!\u0011QKA/)\u0011\t9&!\u001b\u0015\t\u0005e\u0013q\r\u000b\u0007\u00037\ny&a\u0019\u0011\u0007Q\ni\u0006B\u00037\u001f\t\u0007q\u0007\u0003\u0004@\u001f\u0001\u000f\u0011\u0011\r\t\u0005\u00036\u000bY\u0006\u0003\u0004R\u001f\u0001\u000f\u0011Q\r\t\u0005'Z\u000bY\u0006\u0003\u0004Z\u001f\u0001\u0007\u00111\f\u0005\u0007\u0003Wz\u0001\u0019\u0001\u0017\u0002\u000b\u0011\"\b.[:\u0002\u001dQlw\u000e\u001a\u0013fqR,gn]5p]V!\u0011\u0011OA=)\u0011\t\u0019(!\"\u0015\t\u0005U\u00141\u0011\u000b\u0007\u0003o\nY(a \u0011\u0007Q\nI\bB\u00037!\t\u0007q\u0007\u0003\u0004@!\u0001\u000f\u0011Q\u0010\t\u0005\u00036\u000b9\b\u0003\u0004R!\u0001\u000f\u0011\u0011\u0011\t\u0005'Z\u000b9\b\u0003\u0004Z!\u0001\u0007\u0011q\u000f\u0005\u0007\u0003W\u0002\u0002\u0019\u0001\u0017\u0002%Q\fXo\u001c;n_\u0012$S\r\u001f;f]NLwN\\\u000b\u0005\u0003\u0017\u000b)\n\u0006\u0003\u0002\u000e\u0006\u0005F\u0003BAH\u0003?#b!!%\u0002\u0018\u0006m\u0005CB\u0010k\u0003'\u000b\u0019\nE\u00025\u0003+#QAN\tC\u0002]BaaP\tA\u0004\u0005e\u0005\u0003B!N\u0003'Ca!U\tA\u0004\u0005u\u0005\u0003B*W\u0003'Ca!W\tA\u0002\u0005M\u0005BBA6#\u0001\u0007A&A\bgcV|G\u000fJ3yi\u0016t7/[8o+\u0011\t9+a,\u0015\t\u0005%\u00161\u0018\u000b\u0005\u0003W\u000bI\f\u0006\u0004\u0002.\u0006E\u0016Q\u0017\t\u0004i\u0005=F!\u0002\u001c\u0013\u0005\u00049\u0004BB \u0013\u0001\b\t\u0019\f\u0005\u0003B\u001b\u00065\u0006BB)\u0013\u0001\b\t9\f\u0005\u0003T-\u00065\u0006BB-\u0013\u0001\u0004\ti\u000b\u0003\u0004\u0002lI\u0001\r\u0001L\u0001\u000fM6|G\rJ3yi\u0016t7/[8o+\u0011\t\t-!3\u0015\t\u0005\r\u0017Q\u001b\u000b\u0005\u0003\u000b\f\u0019\u000e\u0006\u0004\u0002H\u0006-\u0017q\u001a\t\u0004i\u0005%G!\u0002\u001c\u0014\u0005\u00049\u0004BB \u0014\u0001\b\ti\r\u0005\u0003B\u001b\u0006\u001d\u0007BB)\u0014\u0001\b\t\t\u000e\u0005\u0003T-\u0006\u001d\u0007BB-\u0014\u0001\u0004\t9\r\u0003\u0004\u0002lM\u0001\r\u0001L\u0001\u0013MF,x\u000e^7pI\u0012*\u0007\u0010^3og&|g.\u0006\u0003\u0002\\\u0006\u0015H\u0003BAo\u0003c$B!a8\u0002pR1\u0011\u0011]At\u0003W\u0004ba\b6\u0002d\u0006\r\bc\u0001\u001b\u0002f\u0012)a\u0007\u0006b\u0001o!1q\b\u0006a\u0002\u0003S\u0004B!Q'\u0002d\"1\u0011\u000b\u0006a\u0002\u0003[\u0004Ba\u0015,\u0002d\"1\u0011\f\u0006a\u0001\u0003GDa!a\u001b\u0015\u0001\u0004a\u0013A\u00055bg\"\u001cu\u000eZ3%Kb$XM\\:j_:$B!!\f\u0002x\"1\u00111N\u000bA\u00021\n\u0001#Z9vC2\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005u(\u0011\u0001\u000b\u0005\u0003s\ty\u0010\u0003\u0005\u0002BY\t\t\u00111\u0001<\u0011\u0019\tYG\u0006a\u0001Y\u0001"
)
public final class LiteralLongTruncatedDivisionOps {
   private final long lhs;

   public static boolean equals$extension(final long $this, final Object x$1) {
      return LiteralLongTruncatedDivisionOps$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final long $this) {
      return LiteralLongTruncatedDivisionOps$.MODULE$.hashCode$extension($this);
   }

   public static Tuple2 fquotmod$extension(final long $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralLongTruncatedDivisionOps$.MODULE$.fquotmod$extension($this, rhs, ev, c);
   }

   public static Object fmod$extension(final long $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralLongTruncatedDivisionOps$.MODULE$.fmod$extension($this, rhs, ev, c);
   }

   public static Object fquot$extension(final long $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralLongTruncatedDivisionOps$.MODULE$.fquot$extension($this, rhs, ev, c);
   }

   public static Tuple2 tquotmod$extension(final long $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralLongTruncatedDivisionOps$.MODULE$.tquotmod$extension($this, rhs, ev, c);
   }

   public static Object tmod$extension(final long $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralLongTruncatedDivisionOps$.MODULE$.tmod$extension($this, rhs, ev, c);
   }

   public static Object tquot$extension(final long $this, final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralLongTruncatedDivisionOps$.MODULE$.tquot$extension($this, rhs, ev, c);
   }

   public long lhs() {
      return this.lhs;
   }

   public Object tquot(final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralLongTruncatedDivisionOps$.MODULE$.tquot$extension(this.lhs(), rhs, ev, c);
   }

   public Object tmod(final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralLongTruncatedDivisionOps$.MODULE$.tmod$extension(this.lhs(), rhs, ev, c);
   }

   public Tuple2 tquotmod(final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralLongTruncatedDivisionOps$.MODULE$.tquotmod$extension(this.lhs(), rhs, ev, c);
   }

   public Object fquot(final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralLongTruncatedDivisionOps$.MODULE$.fquot$extension(this.lhs(), rhs, ev, c);
   }

   public Object fmod(final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralLongTruncatedDivisionOps$.MODULE$.fmod$extension(this.lhs(), rhs, ev, c);
   }

   public Tuple2 fquotmod(final Object rhs, final TruncatedDivision ev, final ConvertableTo c) {
      return LiteralLongTruncatedDivisionOps$.MODULE$.fquotmod$extension(this.lhs(), rhs, ev, c);
   }

   public int hashCode() {
      return LiteralLongTruncatedDivisionOps$.MODULE$.hashCode$extension(this.lhs());
   }

   public boolean equals(final Object x$1) {
      return LiteralLongTruncatedDivisionOps$.MODULE$.equals$extension(this.lhs(), x$1);
   }

   public LiteralLongTruncatedDivisionOps(final long lhs) {
      this.lhs = lhs;
   }
}
