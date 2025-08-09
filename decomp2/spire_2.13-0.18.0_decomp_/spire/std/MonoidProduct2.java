package spire.std;

import cats.kernel.Monoid;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!4\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005\u0001B\u0003\u0005\u00067\u0002!\t\u0001\u0018\u0005\u0006A\u00021\u0019!\u0019\u0005\u0006G\u00021\u0019\u0001\u001a\u0005\u0006M\u0002!\ta\u001a\u0002\u000f\u001b>tw.\u001b3Qe>$Wo\u0019;3\u0015\t9\u0001\"A\u0002ti\u0012T\u0011!C\u0001\u0006gBL'/Z\u000b\u0004\u0017!b5\u0003\u0002\u0001\r%]\u0003\"!\u0004\t\u000e\u00039Q\u0011aD\u0001\u0006g\u000e\fG.Y\u0005\u0003#9\u0011a!\u00118z%\u00164\u0007cA\n!G9\u0011A#\b\b\u0003+mq!A\u0006\u000e\u000e\u0003]Q!\u0001G\r\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!C\u0005\u00039!\tq!\u00197hK\n\u0014\u0018-\u0003\u0002\u001f?\u00059\u0001/Y2lC\u001e,'B\u0001\u000f\t\u0013\t\t#E\u0001\u0004N_:|\u0017\u000e\u001a\u0006\u0003=}\u0001B!\u0004\u0013'\u0017&\u0011QE\u0004\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0005\u001dBC\u0002\u0001\u0003\nS\u0001\u0001\u000b\u0011!AC\u0002)\u0012\u0011!Q\t\u0003W9\u0002\"!\u0004\u0017\n\u00055r!a\u0002(pi\"Lgn\u001a\t\u0003\u001b=J!\u0001\r\b\u0003\u0007\u0005s\u0017\u0010\u000b\u0004)eUb\u0014I\u0012\t\u0003\u001bMJ!\u0001\u000e\b\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006GY:\u0014\b\u000f\b\u0003\u001b]J!\u0001\u000f\b\u0002\u0007%sG/\r\u0003%umzaB\u0001\f<\u0013\u0005y\u0011'B\u0012>}\u0001{dBA\u0007?\u0013\tyd\"\u0001\u0003M_:<\u0017\u0007\u0002\u0013;w=\tTa\t\"D\u000b\u0012s!!D\"\n\u0005\u0011s\u0011!\u0002$m_\u0006$\u0018\u0007\u0002\u0013;w=\tTaI$I\u0015&s!!\u0004%\n\u0005%s\u0011A\u0002#pk\ndW-\r\u0003%umz\u0001CA\u0014M\t%i\u0005\u0001)A\u0001\u0002\u000b\u0007!FA\u0001CQ\u0019a%gT)T+F*1EN\u001cQqE\"AEO\u001e\u0010c\u0015\u0019SH\u0010*@c\u0011!#hO\b2\u000b\r\u00125\t\u0016#2\t\u0011R4hD\u0019\u0006G\u001dCe+S\u0019\u0005IiZt\u0002\u0005\u0003Y3\u001aZU\"\u0001\u0004\n\u0005i3!!E*f[&<'o\\;q!J|G-^2ue\u00051A%\u001b8ji\u0012\"\u0012!\u0018\t\u0003\u001byK!a\u0018\b\u0003\tUs\u0017\u000e^\u0001\u000bgR\u0014Xo\u0019;ve\u0016\fT#\u00012\u0011\u0007M\u0001c%\u0001\u0006tiJ,8\r^;sKJ*\u0012!\u001a\t\u0004'\u0001Z\u0015!B3naRLX#A\u0012"
)
public interface MonoidProduct2 extends Monoid, SemigroupProduct2 {
   Monoid structure1();

   Monoid structure2();

   // $FF: synthetic method
   static Tuple2 empty$(final MonoidProduct2 $this) {
      return $this.empty();
   }

   default Tuple2 empty() {
      return new Tuple2(this.structure1().empty(), this.structure2().empty());
   }

   // $FF: synthetic method
   static Monoid structure1$mcD$sp$(final MonoidProduct2 $this) {
      return $this.structure1$mcD$sp();
   }

   default Monoid structure1$mcD$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Monoid structure1$mcF$sp$(final MonoidProduct2 $this) {
      return $this.structure1$mcF$sp();
   }

   default Monoid structure1$mcF$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Monoid structure1$mcI$sp$(final MonoidProduct2 $this) {
      return $this.structure1$mcI$sp();
   }

   default Monoid structure1$mcI$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Monoid structure1$mcJ$sp$(final MonoidProduct2 $this) {
      return $this.structure1$mcJ$sp();
   }

   default Monoid structure1$mcJ$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Monoid structure2$mcD$sp$(final MonoidProduct2 $this) {
      return $this.structure2$mcD$sp();
   }

   default Monoid structure2$mcD$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Monoid structure2$mcF$sp$(final MonoidProduct2 $this) {
      return $this.structure2$mcF$sp();
   }

   default Monoid structure2$mcF$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Monoid structure2$mcI$sp$(final MonoidProduct2 $this) {
      return $this.structure2$mcI$sp();
   }

   default Monoid structure2$mcI$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Monoid structure2$mcJ$sp$(final MonoidProduct2 $this) {
      return $this.structure2$mcJ$sp();
   }

   default Monoid structure2$mcJ$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcDD$sp$(final MonoidProduct2 $this) {
      return $this.empty$mcDD$sp();
   }

   default Tuple2 empty$mcDD$sp() {
      return this.empty();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcDF$sp$(final MonoidProduct2 $this) {
      return $this.empty$mcDF$sp();
   }

   default Tuple2 empty$mcDF$sp() {
      return this.empty();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcDI$sp$(final MonoidProduct2 $this) {
      return $this.empty$mcDI$sp();
   }

   default Tuple2 empty$mcDI$sp() {
      return this.empty();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcDJ$sp$(final MonoidProduct2 $this) {
      return $this.empty$mcDJ$sp();
   }

   default Tuple2 empty$mcDJ$sp() {
      return this.empty();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcFD$sp$(final MonoidProduct2 $this) {
      return $this.empty$mcFD$sp();
   }

   default Tuple2 empty$mcFD$sp() {
      return this.empty();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcFF$sp$(final MonoidProduct2 $this) {
      return $this.empty$mcFF$sp();
   }

   default Tuple2 empty$mcFF$sp() {
      return this.empty();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcFI$sp$(final MonoidProduct2 $this) {
      return $this.empty$mcFI$sp();
   }

   default Tuple2 empty$mcFI$sp() {
      return this.empty();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcFJ$sp$(final MonoidProduct2 $this) {
      return $this.empty$mcFJ$sp();
   }

   default Tuple2 empty$mcFJ$sp() {
      return this.empty();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcID$sp$(final MonoidProduct2 $this) {
      return $this.empty$mcID$sp();
   }

   default Tuple2 empty$mcID$sp() {
      return this.empty();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcIF$sp$(final MonoidProduct2 $this) {
      return $this.empty$mcIF$sp();
   }

   default Tuple2 empty$mcIF$sp() {
      return this.empty();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcII$sp$(final MonoidProduct2 $this) {
      return $this.empty$mcII$sp();
   }

   default Tuple2 empty$mcII$sp() {
      return this.empty();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcIJ$sp$(final MonoidProduct2 $this) {
      return $this.empty$mcIJ$sp();
   }

   default Tuple2 empty$mcIJ$sp() {
      return this.empty();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcJD$sp$(final MonoidProduct2 $this) {
      return $this.empty$mcJD$sp();
   }

   default Tuple2 empty$mcJD$sp() {
      return this.empty();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcJF$sp$(final MonoidProduct2 $this) {
      return $this.empty$mcJF$sp();
   }

   default Tuple2 empty$mcJF$sp() {
      return this.empty();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcJI$sp$(final MonoidProduct2 $this) {
      return $this.empty$mcJI$sp();
   }

   default Tuple2 empty$mcJI$sp() {
      return this.empty();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcJJ$sp$(final MonoidProduct2 $this) {
      return $this.empty$mcJJ$sp();
   }

   default Tuple2 empty$mcJJ$sp() {
      return this.empty();
   }

   static void $init$(final MonoidProduct2 $this) {
   }
}
