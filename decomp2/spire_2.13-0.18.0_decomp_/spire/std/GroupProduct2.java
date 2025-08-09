package spire.std;

import cats.kernel.Group;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)4\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005\u0001B\u0003\u0005\u00067\u0002!\t\u0001\u0018\u0005\u0006A\u00021\u0019!\u0019\u0005\u0006G\u00021\u0019\u0001\u001a\u0005\u0006M\u0002!\ta\u001a\u0002\u000e\u000fJ|W\u000f\u001d)s_\u0012,8\r\u001e\u001a\u000b\u0005\u001dA\u0011aA:uI*\t\u0011\"A\u0003ta&\u0014X-F\u0002\fQ1\u001bB\u0001\u0001\u0007\u0013/B\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\u00042a\u0005\u0011$\u001d\t!RD\u0004\u0002\u001679\u0011aCG\u0007\u0002/)\u0011\u0001$G\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t\u0011\"\u0003\u0002\u001d\u0011\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u0010 \u0003\u001d\u0001\u0018mY6bO\u0016T!\u0001\b\u0005\n\u0005\u0005\u0012#!B$s_V\u0004(B\u0001\u0010 !\u0011iAEJ&\n\u0005\u0015r!A\u0002+va2,'\u0007\u0005\u0002(Q1\u0001A!C\u0015\u0001A\u0003\u0005\tQ1\u0001+\u0005\u0005\t\u0015CA\u0016/!\tiA&\u0003\u0002.\u001d\t9aj\u001c;iS:<\u0007CA\u00070\u0013\t\u0001dBA\u0002B]fDc\u0001\u000b\u001a6y\u00053\u0005CA\u00074\u0013\t!dBA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u00127oeBdBA\u00078\u0013\tAd\"A\u0002J]R\fD\u0001\n\u001e<\u001f9\u0011acO\u0005\u0002\u001fE*1%\u0010 A\u007f9\u0011QBP\u0005\u0003\u007f9\tA\u0001T8oOF\"AEO\u001e\u0010c\u0015\u0019#iQ#E\u001d\ti1)\u0003\u0002E\u001d\u0005)a\t\\8biF\"AEO\u001e\u0010c\u0015\u0019s\t\u0013&J\u001d\ti\u0001*\u0003\u0002J\u001d\u00051Ai\\;cY\u0016\fD\u0001\n\u001e<\u001fA\u0011q\u0005\u0014\u0003\n\u001b\u0002\u0001\u000b\u0011!AC\u0002)\u0012\u0011A\u0011\u0015\u0007\u0019Jz\u0015kU+2\u000b\r2t\u0007\u0015\u001d2\t\u0011R4hD\u0019\u0006Gur$kP\u0019\u0005IiZt\"M\u0003$\u0005\u000e#F)\r\u0003%umz\u0011'B\u0012H\u0011ZK\u0015\u0007\u0002\u0013;w=\u0001B\u0001W-'\u00176\ta!\u0003\u0002[\r\tqQj\u001c8pS\u0012\u0004&o\u001c3vGR\u0014\u0014A\u0002\u0013j]&$H\u0005F\u0001^!\tia,\u0003\u0002`\u001d\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0002EB\u00191\u0003\t\u0014\u0002\u0015M$(/^2ukJ,''F\u0001f!\r\u0019\u0002eS\u0001\bS:4XM]:f)\t\u0019\u0003\u000eC\u0003j\t\u0001\u00071%\u0001\u0002ya\u0001"
)
public interface GroupProduct2 extends Group, MonoidProduct2 {
   Group structure1();

   Group structure2();

   // $FF: synthetic method
   static Tuple2 inverse$(final GroupProduct2 $this, final Tuple2 x0) {
      return $this.inverse(x0);
   }

   default Tuple2 inverse(final Tuple2 x0) {
      return new Tuple2(this.structure1().inverse(x0._1()), this.structure2().inverse(x0._2()));
   }

   // $FF: synthetic method
   static Group structure1$mcD$sp$(final GroupProduct2 $this) {
      return $this.structure1$mcD$sp();
   }

   default Group structure1$mcD$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Group structure1$mcF$sp$(final GroupProduct2 $this) {
      return $this.structure1$mcF$sp();
   }

   default Group structure1$mcF$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Group structure1$mcI$sp$(final GroupProduct2 $this) {
      return $this.structure1$mcI$sp();
   }

   default Group structure1$mcI$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Group structure1$mcJ$sp$(final GroupProduct2 $this) {
      return $this.structure1$mcJ$sp();
   }

   default Group structure1$mcJ$sp() {
      return this.structure1();
   }

   // $FF: synthetic method
   static Group structure2$mcD$sp$(final GroupProduct2 $this) {
      return $this.structure2$mcD$sp();
   }

   default Group structure2$mcD$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Group structure2$mcF$sp$(final GroupProduct2 $this) {
      return $this.structure2$mcF$sp();
   }

   default Group structure2$mcF$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Group structure2$mcI$sp$(final GroupProduct2 $this) {
      return $this.structure2$mcI$sp();
   }

   default Group structure2$mcI$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Group structure2$mcJ$sp$(final GroupProduct2 $this) {
      return $this.structure2$mcJ$sp();
   }

   default Group structure2$mcJ$sp() {
      return this.structure2();
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcDD$sp$(final GroupProduct2 $this, final Tuple2 x0) {
      return $this.inverse$mcDD$sp(x0);
   }

   default Tuple2 inverse$mcDD$sp(final Tuple2 x0) {
      return this.inverse(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcDF$sp$(final GroupProduct2 $this, final Tuple2 x0) {
      return $this.inverse$mcDF$sp(x0);
   }

   default Tuple2 inverse$mcDF$sp(final Tuple2 x0) {
      return this.inverse(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcDI$sp$(final GroupProduct2 $this, final Tuple2 x0) {
      return $this.inverse$mcDI$sp(x0);
   }

   default Tuple2 inverse$mcDI$sp(final Tuple2 x0) {
      return this.inverse(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcDJ$sp$(final GroupProduct2 $this, final Tuple2 x0) {
      return $this.inverse$mcDJ$sp(x0);
   }

   default Tuple2 inverse$mcDJ$sp(final Tuple2 x0) {
      return this.inverse(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcFD$sp$(final GroupProduct2 $this, final Tuple2 x0) {
      return $this.inverse$mcFD$sp(x0);
   }

   default Tuple2 inverse$mcFD$sp(final Tuple2 x0) {
      return this.inverse(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcFF$sp$(final GroupProduct2 $this, final Tuple2 x0) {
      return $this.inverse$mcFF$sp(x0);
   }

   default Tuple2 inverse$mcFF$sp(final Tuple2 x0) {
      return this.inverse(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcFI$sp$(final GroupProduct2 $this, final Tuple2 x0) {
      return $this.inverse$mcFI$sp(x0);
   }

   default Tuple2 inverse$mcFI$sp(final Tuple2 x0) {
      return this.inverse(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcFJ$sp$(final GroupProduct2 $this, final Tuple2 x0) {
      return $this.inverse$mcFJ$sp(x0);
   }

   default Tuple2 inverse$mcFJ$sp(final Tuple2 x0) {
      return this.inverse(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcID$sp$(final GroupProduct2 $this, final Tuple2 x0) {
      return $this.inverse$mcID$sp(x0);
   }

   default Tuple2 inverse$mcID$sp(final Tuple2 x0) {
      return this.inverse(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcIF$sp$(final GroupProduct2 $this, final Tuple2 x0) {
      return $this.inverse$mcIF$sp(x0);
   }

   default Tuple2 inverse$mcIF$sp(final Tuple2 x0) {
      return this.inverse(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcII$sp$(final GroupProduct2 $this, final Tuple2 x0) {
      return $this.inverse$mcII$sp(x0);
   }

   default Tuple2 inverse$mcII$sp(final Tuple2 x0) {
      return this.inverse(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcIJ$sp$(final GroupProduct2 $this, final Tuple2 x0) {
      return $this.inverse$mcIJ$sp(x0);
   }

   default Tuple2 inverse$mcIJ$sp(final Tuple2 x0) {
      return this.inverse(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcJD$sp$(final GroupProduct2 $this, final Tuple2 x0) {
      return $this.inverse$mcJD$sp(x0);
   }

   default Tuple2 inverse$mcJD$sp(final Tuple2 x0) {
      return this.inverse(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcJF$sp$(final GroupProduct2 $this, final Tuple2 x0) {
      return $this.inverse$mcJF$sp(x0);
   }

   default Tuple2 inverse$mcJF$sp(final Tuple2 x0) {
      return this.inverse(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcJI$sp$(final GroupProduct2 $this, final Tuple2 x0) {
      return $this.inverse$mcJI$sp(x0);
   }

   default Tuple2 inverse$mcJI$sp(final Tuple2 x0) {
      return this.inverse(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcJJ$sp$(final GroupProduct2 $this, final Tuple2 x0) {
      return $this.inverse$mcJJ$sp(x0);
   }

   default Tuple2 inverse$mcJJ$sp(final Tuple2 x0) {
      return this.inverse(x0);
   }

   static void $init$(final GroupProduct2 $this) {
   }
}
