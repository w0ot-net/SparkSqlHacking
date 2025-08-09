package cats.kernel;

import cats.kernel.instances.function.package$;
import scala.concurrent.ExecutionContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015f\u0001\u0003\u0007\u000e!\u0003\r\t!D\t\t\u000bq\u0001A\u0011\u0001\u0010\t\u000b\t\u0002A1A\u0012\t\u000be\u0002A1\u0001\u001e\t\u000b!\u0003A1A%\t\u000b\t\u0004A1A2\t\u000bU\u0004A1\u0001<\t\u000f\u00055\u0001\u0001b\u0001\u0002\u0010!9\u0011Q\u0007\u0001\u0005\u0004\u0005]\u0002bBA*\u0001\u0011\r\u0011Q\u000b\u0005\b\u0003w\u0002A1AA?\u0011\u001d\t\u0019\n\u0001C\u0002\u0003+\u0013q\"T8o_&$\u0017J\\:uC:\u001cWm\u001d\u0006\u0003\u001d=\taa[3s]\u0016d'\"\u0001\t\u0002\t\r\fGo]\n\u0004\u0001IA\u0002CA\n\u0017\u001b\u0005!\"\"A\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005]!\"AB!osJ+g\r\u0005\u0002\u001a55\tQ\"\u0003\u0002\u001c\u001b\ti!)\u00198e\u0013:\u001cH/\u00198dKN\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002?A\u00111\u0003I\u0005\u0003CQ\u0011A!\u00168ji\u0006a2-\u0019;t\u0017\u0016\u0014h.\u001a7N_:|\u0017\u000e\u001a$pe\u001a+hn\u0019;j_:\u0004TC\u0001\u0013.)\t)c\u0007E\u0002\u001aM!J!aJ\u0007\u0003\r5{gn\\5e!\r\u0019\u0012fK\u0005\u0003UQ\u0011\u0011BR;oGRLwN\u001c\u0019\u0011\u00051jC\u0002\u0001\u0003\u0006]\t\u0011\ra\f\u0002\u0002\u0003F\u0011\u0001g\r\t\u0003'EJ!A\r\u000b\u0003\u000f9{G\u000f[5oOB\u00111\u0003N\u0005\u0003kQ\u00111!\u00118z\u0011\u001d9$!!AA\u0004a\n1\"\u001a<jI\u0016t7-\u001a\u00132oA\u0019\u0011DJ\u0016\u00029\r\fGo]&fe:,G.T8o_&$gi\u001c:Gk:\u001cG/[8ocU\u00191(Q\"\u0015\u0005q*\u0005cA\r'{A!1C\u0010!C\u0013\tyDCA\u0005Gk:\u001cG/[8ocA\u0011A&\u0011\u0003\u0006]\r\u0011\ra\f\t\u0003Y\r#Q\u0001R\u0002C\u0002=\u0012\u0011A\u0011\u0005\b\r\u000e\t\t\u0011q\u0001H\u0003-)g/\u001b3f]\u000e,G%\r\u001d\u0011\u0007e1#)\u0001\fdCR\u001c8*\u001a:oK2luN\\8jI\u001a{'/T1q+\rQ\u0005l\u0017\u000b\u0003\u0017v\u00032!\u0007\u0014M!\u0011iEk\u0016.\u000f\u00059\u0013\u0006CA(\u0015\u001b\u0005\u0001&BA)\u001e\u0003\u0019a$o\\8u}%\u00111\u000bF\u0001\u0007!J,G-\u001a4\n\u0005U3&aA'ba*\u00111\u000b\u0006\t\u0003Ya#Q!\u0017\u0003C\u0002=\u0012\u0011a\u0013\t\u0003Ym#Q\u0001\u0018\u0003C\u0002=\u0012\u0011A\u0016\u0005\b=\u0012\t\t\u0011q\u0001`\u0003-)g/\u001b3f]\u000e,G%M\u001d\u0011\u0007e\u0001',\u0003\u0002b\u001b\tI1+Z7jOJ|W\u000f]\u0001 G\u0006$8oS3s]\u0016d7+Z7jOJ|W\u000f\u001d$peN{'\u000f^3e\u001b\u0006\u0004Xc\u00013pcR\u0011QM\u001d\t\u00043\u00014\u0007\u0003B4m]Bl\u0011\u0001\u001b\u0006\u0003S*\f\u0011\"[7nkR\f'\r\\3\u000b\u0005-$\u0012AC2pY2,7\r^5p]&\u0011Q\u000e\u001b\u0002\n'>\u0014H/\u001a3NCB\u0004\"\u0001L8\u0005\u000be+!\u0019A\u0018\u0011\u00051\nH!\u0002/\u0006\u0005\u0004y\u0003bB:\u0006\u0003\u0003\u0005\u001d\u0001^\u0001\fKZLG-\u001a8dK\u0012\u0012\u0004\u0007E\u0002\u001aAB\fAdY1ug.+'O\\3m\u001b>tw.\u001b3G_J\u001cvN\u001d;fI6\u000b\u0007/F\u0002xwv$B\u0001\u001f@\u0002\bA\u0019\u0011DJ=\u0011\t\u001dd'\u0010 \t\u0003Ym$Q!\u0017\u0004C\u0002=\u0002\"\u0001L?\u0005\u000bq3!\u0019A\u0018\t\u0011}4\u0011\u0011!a\u0002\u0003\u0003\t1\"\u001a<jI\u0016t7-\u001a\u00133cA!\u0011$a\u0001{\u0013\r\t)!\u0004\u0002\u0006\u001fJ$WM\u001d\u0005\n\u0003\u00131\u0011\u0011!a\u0002\u0003\u0017\t1\"\u001a<jI\u0016t7-\u001a\u00133eA\u0019\u0011\u0004\u0019?\u00023\r\fGo]&fe:,G.T8o_&$gi\u001c:FSRDWM]\u000b\u0007\u0003#\tI#!\f\u0015\t\u0005M\u0011q\u0006\t\u00053\u0019\n)\u0002\u0005\u0005\u0002\u0018\u0005\u0005\u0012qEA\u0016\u001d\u0011\tI\"!\b\u000f\u0007=\u000bY\"C\u0001\u0016\u0013\r\ty\u0002F\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t\u0019#!\n\u0003\r\u0015KG\u000f[3s\u0015\r\ty\u0002\u0006\t\u0004Y\u0005%B!\u0002\u0018\b\u0005\u0004y\u0003c\u0001\u0017\u0002.\u0011)Ai\u0002b\u0001_!I\u0011\u0011G\u0004\u0002\u0002\u0003\u000f\u00111G\u0001\fKZLG-\u001a8dK\u0012\u00124\u0007\u0005\u0003\u001aM\u0005-\u0012AF2biN\\UM\u001d8fY6{gn\\5e\r>\u0014HK]=\u0016\t\u0005e\u00121\n\u000b\u0005\u0003w\ti\u0005\u0005\u0003\u001aM\u0005u\u0002CBA \u0003\u000b\nI%\u0004\u0002\u0002B)\u0019\u00111\t\u000b\u0002\tU$\u0018\u000e\\\u0005\u0005\u0003\u000f\n\tEA\u0002Uef\u00042\u0001LA&\t\u0015q\u0003B1\u00010\u0011%\ty\u0005CA\u0001\u0002\b\t\t&A\u0006fm&$WM\\2fII\"\u0004\u0003B\r'\u0003\u0013\n\u0011dY1ug.+'O\\3m\u001b>tw.\u001b3G_J4U\u000f^;sKV!\u0011qKA5)\u0019\tI&a\u001b\u0002rA!\u0011DJA.!\u0019\ti&a\u0019\u0002h5\u0011\u0011q\f\u0006\u0004\u0003C\"\u0012AC2p]\u000e,(O]3oi&!\u0011QMA0\u0005\u00191U\u000f^;sKB\u0019A&!\u001b\u0005\u000b9J!\u0019A\u0018\t\u000f\u00055\u0014\u0002q\u0001\u0002p\u0005\t\u0011\t\u0005\u0003\u001aM\u0005\u001d\u0004bBA:\u0013\u0001\u000f\u0011QO\u0001\u0003K\u000e\u0004B!!\u0018\u0002x%!\u0011\u0011PA0\u0005A)\u00050Z2vi&|gnQ8oi\u0016DH/A\rdCR\u001c8*\u001a:oK2luN\\8jI\u001a{'o\u00149uS>tW\u0003BA@\u0003\u0017#B!!!\u0002\u000eB!\u0011DJAB!\u0015\u0019\u0012QQAE\u0013\r\t9\t\u0006\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u00071\nY\tB\u0003/\u0015\t\u0007q\u0006C\u0005\u0002\u0010*\t\t\u0011q\u0001\u0002\u0012\u0006YQM^5eK:\u001cW\r\n\u001a6!\u0011I\u0002-!#\u0002-\r\fGo]&fe:,G.T8o_&$gi\u001c:TKF,B!a&\u0002$V\u0011\u0011\u0011\u0014\t\u00053\u0019\nY\nE\u0003h\u0003;\u000b\t+C\u0002\u0002 \"\u00141aU3r!\ra\u00131\u0015\u0003\u0006]-\u0011\ra\f"
)
public interface MonoidInstances extends BandInstances {
   // $FF: synthetic method
   static Monoid catsKernelMonoidForFunction0$(final MonoidInstances $this, final Monoid evidence$17) {
      return $this.catsKernelMonoidForFunction0(evidence$17);
   }

   default Monoid catsKernelMonoidForFunction0(final Monoid evidence$17) {
      return package$.MODULE$.catsKernelMonoidForFunction0(evidence$17);
   }

   // $FF: synthetic method
   static Monoid catsKernelMonoidForFunction1$(final MonoidInstances $this, final Monoid evidence$18) {
      return $this.catsKernelMonoidForFunction1(evidence$18);
   }

   default Monoid catsKernelMonoidForFunction1(final Monoid evidence$18) {
      return package$.MODULE$.catsKernelMonoidForFunction1(evidence$18);
   }

   // $FF: synthetic method
   static Monoid catsKernelMonoidForMap$(final MonoidInstances $this, final Semigroup evidence$19) {
      return $this.catsKernelMonoidForMap(evidence$19);
   }

   default Monoid catsKernelMonoidForMap(final Semigroup evidence$19) {
      return cats.kernel.instances.map.package$.MODULE$.catsKernelStdMonoidForMap(evidence$19);
   }

   // $FF: synthetic method
   static Semigroup catsKernelSemigroupForSortedMap$(final MonoidInstances $this, final Semigroup evidence$20) {
      return $this.catsKernelSemigroupForSortedMap(evidence$20);
   }

   default Semigroup catsKernelSemigroupForSortedMap(final Semigroup evidence$20) {
      return cats.kernel.instances.sortedMap.package$.MODULE$.catsKernelStdSemigroupForSortedMap(evidence$20);
   }

   // $FF: synthetic method
   static Monoid catsKernelMonoidForSortedMap$(final MonoidInstances $this, final Order evidence$21, final Semigroup evidence$22) {
      return $this.catsKernelMonoidForSortedMap(evidence$21, evidence$22);
   }

   default Monoid catsKernelMonoidForSortedMap(final Order evidence$21, final Semigroup evidence$22) {
      return cats.kernel.instances.sortedMap.package$.MODULE$.catsKernelStdMonoidForSortedMap(evidence$21, evidence$22);
   }

   // $FF: synthetic method
   static Monoid catsKernelMonoidForEither$(final MonoidInstances $this, final Monoid evidence$23) {
      return $this.catsKernelMonoidForEither(evidence$23);
   }

   default Monoid catsKernelMonoidForEither(final Monoid evidence$23) {
      return cats.kernel.instances.either.package$.MODULE$.catsDataMonoidForEither(evidence$23);
   }

   // $FF: synthetic method
   static Monoid catsKernelMonoidForTry$(final MonoidInstances $this, final Monoid evidence$24) {
      return $this.catsKernelMonoidForTry(evidence$24);
   }

   default Monoid catsKernelMonoidForTry(final Monoid evidence$24) {
      return new TryMonoid(Monoid$.MODULE$.apply(evidence$24));
   }

   // $FF: synthetic method
   static Monoid catsKernelMonoidForFuture$(final MonoidInstances $this, final Monoid A, final ExecutionContext ec) {
      return $this.catsKernelMonoidForFuture(A, ec);
   }

   default Monoid catsKernelMonoidForFuture(final Monoid A, final ExecutionContext ec) {
      return new FutureMonoid(A, ec);
   }

   // $FF: synthetic method
   static Monoid catsKernelMonoidForOption$(final MonoidInstances $this, final Semigroup evidence$25) {
      return $this.catsKernelMonoidForOption(evidence$25);
   }

   default Monoid catsKernelMonoidForOption(final Semigroup evidence$25) {
      return cats.kernel.instances.option.package$.MODULE$.catsKernelStdMonoidForOption(evidence$25);
   }

   // $FF: synthetic method
   static Monoid catsKernelMonoidForSeq$(final MonoidInstances $this) {
      return $this.catsKernelMonoidForSeq();
   }

   default Monoid catsKernelMonoidForSeq() {
      return cats.kernel.instances.seq.package$.MODULE$.catsKernelStdMonoidForSeq();
   }

   static void $init$(final MonoidInstances $this) {
   }
}
