package spire.algebra;

import algebra.ring.CommutativeRing;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005MaaB\u0005\u000b!\u0003\r\ta\u0004\u0005\u0006\r\u0002!\ta\u0012\u0005\u0006\u0017\u00021\u0019\u0001\u0014\u0005\u0006)\u0002!\t%V\u0004\u00065*A\ta\u0017\u0004\u0006\u0013)A\t\u0001\u0018\u0005\u0006Q\u0016!\t!\u001b\u0005\u0006U\u0016!)a\u001b\u0005\n\u0003\u0007)\u0011\u0011!C\u0005\u0003\u000b\u0011qaQ'pIVdWM\u0003\u0002\f\u0019\u00059\u0011\r\\4fEJ\f'\"A\u0007\u0002\u000bM\u0004\u0018N]3\u0004\u0001U\u0019\u0001#\b\u0013\u0014\t\u0001\trc\u0011\t\u0003%Ui\u0011a\u0005\u0006\u0002)\u0005)1oY1mC&\u0011ac\u0005\u0002\u0004\u0003:L\b\u0003\u0002\r\u001a7\rj\u0011AC\u0005\u00035)\u0011!\u0002T3gi6{G-\u001e7f!\taR\u0004\u0004\u0001\u0005\u000by\u0001!\u0019A\u0010\u0003\u0003Y\u000b\"\u0001I\t\u0011\u0005I\t\u0013B\u0001\u0012\u0014\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\b\u0013\u0005\u0013\u0015\u0002\u0001\u0015!A\u0001\u0006\u0004y\"!\u0001*)\r\u0011:#\u0006N\u001d?!\t\u0011\u0002&\u0003\u0002*'\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u00193\u0006\f\u0018.\u001d\t\u0011B&\u0003\u0002.'\u0005\u0019\u0011J\u001c;2\t\u0011z3\u0007\u0006\b\u0003aMj\u0011!\r\u0006\u0003e9\ta\u0001\u0010:p_Rt\u0014\"\u0001\u000b2\u000b\r*d\u0007O\u001c\u000f\u0005I1\u0014BA\u001c\u0014\u0003\u0011auN\\42\t\u0011z3\u0007F\u0019\u0006GiZT\b\u0010\b\u0003%mJ!\u0001P\n\u0002\u000b\u0019cw.\u0019;2\t\u0011z3\u0007F\u0019\u0006G}\u0002%)\u0011\b\u0003%\u0001K!!Q\n\u0002\r\u0011{WO\u00197fc\u0011!sf\r\u000b\u0011\ta!5dI\u0005\u0003\u000b*\u00111BU5hQRlu\u000eZ;mK\u00061A%\u001b8ji\u0012\"\u0012\u0001\u0013\t\u0003%%K!AS\n\u0003\tUs\u0017\u000e^\u0001\u0007g\u000e\fG.\u0019:\u0016\u00035\u00032AT)$\u001d\tAr*\u0003\u0002Q\u0015\u00059\u0001/Y2lC\u001e,\u0017B\u0001*T\u0005\u0015\u0019%+\u001b8h\u0015\t\u0001&\"\u0001\u0004uS6,7O\u001d\u000b\u00047YC\u0006\"B,\u0004\u0001\u0004Y\u0012!\u0001<\t\u000be\u001b\u0001\u0019A\u0012\u0002\u0003I\fqaQ'pIVdW\r\u0005\u0002\u0019\u000bM\u0019Q!\u00181\u0011\u0005Iq\u0016BA0\u0014\u0005\u0019\te.\u001f*fMB\u0011\u0011MZ\u0007\u0002E*\u00111\rZ\u0001\u0003S>T\u0011!Z\u0001\u0005U\u00064\u0018-\u0003\u0002hE\na1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012aW\u0001\u0006CB\u0004H._\u000b\u0004Y>\fHCA7|!\u0011A\u0002A\u001c9\u0011\u0005qyG!\u0002\u0010\b\u0005\u0004y\u0002C\u0001\u000fr\t%)s\u0001)A\u0001\u0002\u000b\u0007q\u0004\u000b\u0004rOM,x/_\u0019\u0006G-bC/L\u0019\u0005I=\u001aD#M\u0003$kY2x'\r\u0003%_M\"\u0012'B\u0012;wad\u0014\u0007\u0002\u00130gQ\tTaI Au\u0006\u000bD\u0001J\u00184)!)Ap\u0002a\u0002[\u0006\ta\u000b\u000b\u0002\b}B\u0011!c`\u0005\u0004\u0003\u0003\u0019\"AB5oY&tW-\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\bA!\u0011\u0011BA\b\u001b\t\tYAC\u0002\u0002\u000e\u0011\fA\u0001\\1oO&!\u0011\u0011CA\u0006\u0005\u0019y%M[3di\u0002"
)
public interface CModule extends LeftModule, RightModule {
   static CModule apply(final CModule V) {
      return CModule$.MODULE$.apply(V);
   }

   CommutativeRing scalar();

   // $FF: synthetic method
   static Object timesr$(final CModule $this, final Object v, final Object r) {
      return $this.timesr(v, r);
   }

   default Object timesr(final Object v, final Object r) {
      return this.timesl(r, v);
   }

   // $FF: synthetic method
   static CommutativeRing scalar$mcD$sp$(final CModule $this) {
      return $this.scalar$mcD$sp();
   }

   default CommutativeRing scalar$mcD$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static CommutativeRing scalar$mcF$sp$(final CModule $this) {
      return $this.scalar$mcF$sp();
   }

   default CommutativeRing scalar$mcF$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static CommutativeRing scalar$mcI$sp$(final CModule $this) {
      return $this.scalar$mcI$sp();
   }

   default CommutativeRing scalar$mcI$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static CommutativeRing scalar$mcJ$sp$(final CModule $this) {
      return $this.scalar$mcJ$sp();
   }

   default CommutativeRing scalar$mcJ$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Object timesr$mcD$sp$(final CModule $this, final Object v, final double r) {
      return $this.timesr$mcD$sp(v, r);
   }

   default Object timesr$mcD$sp(final Object v, final double r) {
      return this.timesr(v, BoxesRunTime.boxToDouble(r));
   }

   // $FF: synthetic method
   static Object timesr$mcF$sp$(final CModule $this, final Object v, final float r) {
      return $this.timesr$mcF$sp(v, r);
   }

   default Object timesr$mcF$sp(final Object v, final float r) {
      return this.timesr(v, BoxesRunTime.boxToFloat(r));
   }

   // $FF: synthetic method
   static Object timesr$mcI$sp$(final CModule $this, final Object v, final int r) {
      return $this.timesr$mcI$sp(v, r);
   }

   default Object timesr$mcI$sp(final Object v, final int r) {
      return this.timesr(v, BoxesRunTime.boxToInteger(r));
   }

   // $FF: synthetic method
   static Object timesr$mcJ$sp$(final CModule $this, final Object v, final long r) {
      return $this.timesr$mcJ$sp(v, r);
   }

   default Object timesr$mcJ$sp(final Object v, final long r) {
      return this.timesr(v, BoxesRunTime.boxToLong(r));
   }

   static void $init$(final CModule $this) {
   }
}
