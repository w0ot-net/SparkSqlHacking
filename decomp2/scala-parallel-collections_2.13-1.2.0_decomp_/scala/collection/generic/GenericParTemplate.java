package scala.collection.generic;

import scala.collection.parallel.Combiner;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}3qa\u0002\u0005\u0011\u0002\u0007\u0005q\u0002C\u0003A\u0001\u0011\u0005\u0011\tC\u0003F\u0001\u0019\u0005a\t\u0003\u0004K\u0001\u0001&\tf\u0013\u0005\u0007\u001f\u0002\u0001K\u0011K&\t\u000bA\u0003A\u0011I)\t\u000ba\u0003A\u0011A-\u0003%\u001d+g.\u001a:jGB\u000b'\u000fV3na2\fG/\u001a\u0006\u0003\u0013)\tqaZ3oKJL7M\u0003\u0002\f\u0019\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u00035\tQa]2bY\u0006\u001c\u0001!F\u0002\u00117\u0015\u001aB\u0001A\t\u0016eA\u0011!cE\u0007\u0002\u0019%\u0011A\u0003\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\tY9\u0012\u0004J\u0007\u0002\u0011%\u0011\u0001\u0004\u0003\u0002\u001b\u000f\u0016tWM]5d)J\fg/\u001a:tC\ndW\rV3na2\fG/\u001a\t\u00035ma\u0001\u0001\u0002\u0004\u001d\u0001\u0011\u0015\r!\b\u0002\u0002\u0003F\u0011a$\t\t\u0003%}I!\u0001\t\u0007\u0003\u000f9{G\u000f[5oOB\u0011!CI\u0005\u0003G1\u00111!\u00118z!\tQR\u0005\u0002\u0004'\u0001\u0011\u0015\ra\n\u0002\u0003\u0007\u000e+\"\u0001\u000b\u0019\u0012\u0005yI\u0003c\u0001\u0016._5\t1F\u0003\u0002-\u0015\u0005A\u0001/\u0019:bY2,G.\u0003\u0002/W\tY\u0001+\u0019:Ji\u0016\u0014\u0018M\u00197f!\tQ\u0002\u0007B\u00032K\t\u0007QDA\u0001Y!\u001112'G\u001b\n\u0005QB!A\u0004%bg:+woQ8nE&tWM\u001d\u0016\u0003m]\u00022AG\u0013\u001aW\u0005A\u0004CA\u001d?\u001b\u0005Q$BA\u001e=\u0003%)hn\u00195fG.,GM\u0003\u0002>\u0019\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005}R$!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u00061A%\u001b8ji\u0012\"\u0012A\u0011\t\u0003%\rK!\u0001\u0012\u0007\u0003\tUs\u0017\u000e^\u0001\nG>l\u0007/\u00198j_:,\u0012a\u0012\t\u0004-!#\u0013BA%\t\u0005M9UM\\3sS\u000e\u0004\u0016M]\"p[B\fg.[8o\u0003)qWm\u001e\"vS2$WM]\u000b\u0002\u0019B!!&T\r7\u0013\tq5F\u0001\u0005D_6\u0014\u0017N\\3s\u0003-qWm^\"p[\nLg.\u001a:\u0002\u001d\u001d,g.\u001a:jG\n+\u0018\u000e\u001c3feV\u0011!+V\u000b\u0002'B!!&\u0014+X!\tQR\u000bB\u0003W\u000b\t\u0007QDA\u0001C!\rQR\u0005V\u0001\u0010O\u0016tWM]5d\u0007>l'-\u001b8feV\u0011!,X\u000b\u00027B!!&\u0014/_!\tQR\fB\u0003W\r\t\u0007Q\u0004E\u0002\u001bKq\u0003"
)
public interface GenericParTemplate extends GenericTraversableTemplate, HasNewCombiner {
   GenericParCompanion companion();

   // $FF: synthetic method
   static Combiner newBuilder$(final GenericParTemplate $this) {
      return $this.newBuilder();
   }

   default Combiner newBuilder() {
      return this.newCombiner();
   }

   // $FF: synthetic method
   static Combiner newCombiner$(final GenericParTemplate $this) {
      return $this.newCombiner();
   }

   default Combiner newCombiner() {
      return this.companion().newCombiner();
   }

   // $FF: synthetic method
   static Combiner genericBuilder$(final GenericParTemplate $this) {
      return $this.genericBuilder();
   }

   default Combiner genericBuilder() {
      return this.genericCombiner();
   }

   // $FF: synthetic method
   static Combiner genericCombiner$(final GenericParTemplate $this) {
      return $this.genericCombiner();
   }

   default Combiner genericCombiner() {
      Combiner cb = this.companion().newCombiner();
      return cb;
   }

   static void $init$(final GenericParTemplate $this) {
   }
}
