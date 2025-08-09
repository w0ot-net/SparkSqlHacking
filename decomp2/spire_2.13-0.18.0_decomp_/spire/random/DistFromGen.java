package spire.random;

import algebra.ring.Field;
import algebra.ring.Rig;
import scala.Function1;
import scala.Function2;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.immutable.LazyList;
import scala.collection.immutable.Map;
import scala.collection.immutable.Stream;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005Q2A\u0001B\u0003\u0001\u0015!AQ\u0005\u0001B\u0001B\u0003%a\u0005C\u0003-\u0001\u0011\u0005Q\u0006C\u00031\u0001\u0011\u0005\u0011GA\u0006ESN$hI]8n\u000f\u0016t'B\u0001\u0004\b\u0003\u0019\u0011\u0018M\u001c3p[*\t\u0001\"A\u0003ta&\u0014Xm\u0001\u0001\u0016\u0005-A2c\u0001\u0001\r%A\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\u00042a\u0005\u000b\u0017\u001b\u0005)\u0011BA\u000b\u0006\u0005\u0011!\u0015n\u001d;\u0011\u0005]AB\u0002\u0001\u0003\n3\u0001\u0001\u000b\u0011!AC\u0002i\u0011\u0011!Q\t\u00037y\u0001\"!\u0004\u000f\n\u0005uq!a\u0002(pi\"Lgn\u001a\t\u0003\u001b}I!\u0001\t\b\u0003\u0007\u0005s\u0017\u0010\u000b\u0002\u0019EA\u0011QbI\u0005\u0003I9\u00111b\u001d9fG&\fG.\u001b>fI\u0006\ta\r\u0005\u0003\u000eO%2\u0012B\u0001\u0015\u000f\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002\u0014U%\u00111&\u0002\u0002\n\u000f\u0016tWM]1u_J\fa\u0001P5oSRtDC\u0001\u00180!\r\u0019\u0002A\u0006\u0005\u0006K\t\u0001\rAJ\u0001\u0006CB\u0004H.\u001f\u000b\u0003-IBQaM\u0002A\u0002%\n1aZ3o\u0001"
)
public class DistFromGen implements Dist {
   public final Function1 f;

   public void fill(final Generator gen, final Object arr) {
      Dist.fill$(this, gen, arr);
   }

   public void fill$mcZ$sp(final Generator gen, final boolean[] arr) {
      Dist.fill$mcZ$sp$(this, gen, arr);
   }

   public void fill$mcB$sp(final Generator gen, final byte[] arr) {
      Dist.fill$mcB$sp$(this, gen, arr);
   }

   public void fill$mcC$sp(final Generator gen, final char[] arr) {
      Dist.fill$mcC$sp$(this, gen, arr);
   }

   public void fill$mcD$sp(final Generator gen, final double[] arr) {
      Dist.fill$mcD$sp$(this, gen, arr);
   }

   public void fill$mcF$sp(final Generator gen, final float[] arr) {
      Dist.fill$mcF$sp$(this, gen, arr);
   }

   public void fill$mcI$sp(final Generator gen, final int[] arr) {
      Dist.fill$mcI$sp$(this, gen, arr);
   }

   public void fill$mcJ$sp(final Generator gen, final long[] arr) {
      Dist.fill$mcJ$sp$(this, gen, arr);
   }

   public void fill$mcS$sp(final Generator gen, final short[] arr) {
      Dist.fill$mcS$sp$(this, gen, arr);
   }

   public void fill$mcV$sp(final Generator gen, final BoxedUnit[] arr) {
      Dist.fill$mcV$sp$(this, gen, arr);
   }

   public Dist map(final Function1 f) {
      return Dist.map$(this, f);
   }

   public Dist map$mcZ$sp(final Function1 f) {
      return Dist.map$mcZ$sp$(this, f);
   }

   public Dist map$mcB$sp(final Function1 f) {
      return Dist.map$mcB$sp$(this, f);
   }

   public Dist map$mcC$sp(final Function1 f) {
      return Dist.map$mcC$sp$(this, f);
   }

   public Dist map$mcD$sp(final Function1 f) {
      return Dist.map$mcD$sp$(this, f);
   }

   public Dist map$mcF$sp(final Function1 f) {
      return Dist.map$mcF$sp$(this, f);
   }

   public Dist map$mcI$sp(final Function1 f) {
      return Dist.map$mcI$sp$(this, f);
   }

   public Dist map$mcJ$sp(final Function1 f) {
      return Dist.map$mcJ$sp$(this, f);
   }

   public Dist map$mcS$sp(final Function1 f) {
      return Dist.map$mcS$sp$(this, f);
   }

   public Dist map$mcV$sp(final Function1 f) {
      return Dist.map$mcV$sp$(this, f);
   }

   public Dist flatMap(final Function1 f) {
      return Dist.flatMap$(this, f);
   }

   public Dist flatMap$mcZ$sp(final Function1 f) {
      return Dist.flatMap$mcZ$sp$(this, f);
   }

   public Dist flatMap$mcB$sp(final Function1 f) {
      return Dist.flatMap$mcB$sp$(this, f);
   }

   public Dist flatMap$mcC$sp(final Function1 f) {
      return Dist.flatMap$mcC$sp$(this, f);
   }

   public Dist flatMap$mcD$sp(final Function1 f) {
      return Dist.flatMap$mcD$sp$(this, f);
   }

   public Dist flatMap$mcF$sp(final Function1 f) {
      return Dist.flatMap$mcF$sp$(this, f);
   }

   public Dist flatMap$mcI$sp(final Function1 f) {
      return Dist.flatMap$mcI$sp$(this, f);
   }

   public Dist flatMap$mcJ$sp(final Function1 f) {
      return Dist.flatMap$mcJ$sp$(this, f);
   }

   public Dist flatMap$mcS$sp(final Function1 f) {
      return Dist.flatMap$mcS$sp$(this, f);
   }

   public Dist flatMap$mcV$sp(final Function1 f) {
      return Dist.flatMap$mcV$sp$(this, f);
   }

   public Dist filter(final Function1 pred) {
      return Dist.filter$(this, pred);
   }

   public Dist filter$mcZ$sp(final Function1 pred) {
      return Dist.filter$mcZ$sp$(this, pred);
   }

   public Dist filter$mcB$sp(final Function1 pred) {
      return Dist.filter$mcB$sp$(this, pred);
   }

   public Dist filter$mcC$sp(final Function1 pred) {
      return Dist.filter$mcC$sp$(this, pred);
   }

   public Dist filter$mcD$sp(final Function1 pred) {
      return Dist.filter$mcD$sp$(this, pred);
   }

   public Dist filter$mcF$sp(final Function1 pred) {
      return Dist.filter$mcF$sp$(this, pred);
   }

   public Dist filter$mcI$sp(final Function1 pred) {
      return Dist.filter$mcI$sp$(this, pred);
   }

   public Dist filter$mcJ$sp(final Function1 pred) {
      return Dist.filter$mcJ$sp$(this, pred);
   }

   public Dist filter$mcS$sp(final Function1 pred) {
      return Dist.filter$mcS$sp$(this, pred);
   }

   public Dist filter$mcV$sp(final Function1 pred) {
      return Dist.filter$mcV$sp$(this, pred);
   }

   public Dist given(final Function1 pred) {
      return Dist.given$(this, pred);
   }

   public Dist given$mcZ$sp(final Function1 pred) {
      return Dist.given$mcZ$sp$(this, pred);
   }

   public Dist given$mcB$sp(final Function1 pred) {
      return Dist.given$mcB$sp$(this, pred);
   }

   public Dist given$mcC$sp(final Function1 pred) {
      return Dist.given$mcC$sp$(this, pred);
   }

   public Dist given$mcD$sp(final Function1 pred) {
      return Dist.given$mcD$sp$(this, pred);
   }

   public Dist given$mcF$sp(final Function1 pred) {
      return Dist.given$mcF$sp$(this, pred);
   }

   public Dist given$mcI$sp(final Function1 pred) {
      return Dist.given$mcI$sp$(this, pred);
   }

   public Dist given$mcJ$sp(final Function1 pred) {
      return Dist.given$mcJ$sp$(this, pred);
   }

   public Dist given$mcS$sp(final Function1 pred) {
      return Dist.given$mcS$sp$(this, pred);
   }

   public Dist given$mcV$sp(final Function1 pred) {
      return Dist.given$mcV$sp$(this, pred);
   }

   public Dist until(final Function1 pred) {
      return Dist.until$(this, pred);
   }

   public Dist until$mcZ$sp(final Function1 pred) {
      return Dist.until$mcZ$sp$(this, pred);
   }

   public Dist until$mcB$sp(final Function1 pred) {
      return Dist.until$mcB$sp$(this, pred);
   }

   public Dist until$mcC$sp(final Function1 pred) {
      return Dist.until$mcC$sp$(this, pred);
   }

   public Dist until$mcD$sp(final Function1 pred) {
      return Dist.until$mcD$sp$(this, pred);
   }

   public Dist until$mcF$sp(final Function1 pred) {
      return Dist.until$mcF$sp$(this, pred);
   }

   public Dist until$mcI$sp(final Function1 pred) {
      return Dist.until$mcI$sp$(this, pred);
   }

   public Dist until$mcJ$sp(final Function1 pred) {
      return Dist.until$mcJ$sp$(this, pred);
   }

   public Dist until$mcS$sp(final Function1 pred) {
      return Dist.until$mcS$sp$(this, pred);
   }

   public Dist until$mcV$sp(final Function1 pred) {
      return Dist.until$mcV$sp$(this, pred);
   }

   public Dist foldn(final Object init, final int n, final Function2 f) {
      return Dist.foldn$(this, init, n, f);
   }

   public Dist foldn$mcZ$sp(final Object init, final int n, final Function2 f) {
      return Dist.foldn$mcZ$sp$(this, init, n, f);
   }

   public Dist foldn$mcB$sp(final Object init, final int n, final Function2 f) {
      return Dist.foldn$mcB$sp$(this, init, n, f);
   }

   public Dist foldn$mcC$sp(final Object init, final int n, final Function2 f) {
      return Dist.foldn$mcC$sp$(this, init, n, f);
   }

   public Dist foldn$mcD$sp(final Object init, final int n, final Function2 f) {
      return Dist.foldn$mcD$sp$(this, init, n, f);
   }

   public Dist foldn$mcF$sp(final Object init, final int n, final Function2 f) {
      return Dist.foldn$mcF$sp$(this, init, n, f);
   }

   public Dist foldn$mcI$sp(final Object init, final int n, final Function2 f) {
      return Dist.foldn$mcI$sp$(this, init, n, f);
   }

   public Dist foldn$mcJ$sp(final Object init, final int n, final Function2 f) {
      return Dist.foldn$mcJ$sp$(this, init, n, f);
   }

   public Dist foldn$mcS$sp(final Object init, final int n, final Function2 f) {
      return Dist.foldn$mcS$sp$(this, init, n, f);
   }

   public Dist foldn$mcV$sp(final Object init, final int n, final Function2 f) {
      return Dist.foldn$mcV$sp$(this, init, n, f);
   }

   public Dist unfold(final Object init, final Function2 f, final Function1 pred) {
      return Dist.unfold$(this, init, f, pred);
   }

   public Dist unfold$mcZ$sp(final Object init, final Function2 f, final Function1 pred) {
      return Dist.unfold$mcZ$sp$(this, init, f, pred);
   }

   public Dist unfold$mcB$sp(final Object init, final Function2 f, final Function1 pred) {
      return Dist.unfold$mcB$sp$(this, init, f, pred);
   }

   public Dist unfold$mcC$sp(final Object init, final Function2 f, final Function1 pred) {
      return Dist.unfold$mcC$sp$(this, init, f, pred);
   }

   public Dist unfold$mcD$sp(final Object init, final Function2 f, final Function1 pred) {
      return Dist.unfold$mcD$sp$(this, init, f, pred);
   }

   public Dist unfold$mcF$sp(final Object init, final Function2 f, final Function1 pred) {
      return Dist.unfold$mcF$sp$(this, init, f, pred);
   }

   public Dist unfold$mcI$sp(final Object init, final Function2 f, final Function1 pred) {
      return Dist.unfold$mcI$sp$(this, init, f, pred);
   }

   public Dist unfold$mcJ$sp(final Object init, final Function2 f, final Function1 pred) {
      return Dist.unfold$mcJ$sp$(this, init, f, pred);
   }

   public Dist unfold$mcS$sp(final Object init, final Function2 f, final Function1 pred) {
      return Dist.unfold$mcS$sp$(this, init, f, pred);
   }

   public Dist unfold$mcV$sp(final Object init, final Function2 f, final Function1 pred) {
      return Dist.unfold$mcV$sp$(this, init, f, pred);
   }

   public Dist pack(final int n, final ClassTag ct) {
      return Dist.pack$(this, n, ct);
   }

   public Dist pack$mcZ$sp(final int n, final ClassTag ct) {
      return Dist.pack$mcZ$sp$(this, n, ct);
   }

   public Dist pack$mcB$sp(final int n, final ClassTag ct) {
      return Dist.pack$mcB$sp$(this, n, ct);
   }

   public Dist pack$mcC$sp(final int n, final ClassTag ct) {
      return Dist.pack$mcC$sp$(this, n, ct);
   }

   public Dist pack$mcD$sp(final int n, final ClassTag ct) {
      return Dist.pack$mcD$sp$(this, n, ct);
   }

   public Dist pack$mcF$sp(final int n, final ClassTag ct) {
      return Dist.pack$mcF$sp$(this, n, ct);
   }

   public Dist pack$mcI$sp(final int n, final ClassTag ct) {
      return Dist.pack$mcI$sp$(this, n, ct);
   }

   public Dist pack$mcJ$sp(final int n, final ClassTag ct) {
      return Dist.pack$mcJ$sp$(this, n, ct);
   }

   public Dist pack$mcS$sp(final int n, final ClassTag ct) {
      return Dist.pack$mcS$sp$(this, n, ct);
   }

   public Dist pack$mcV$sp(final int n, final ClassTag ct) {
      return Dist.pack$mcV$sp$(this, n, ct);
   }

   public Dist repeat(final int n, final Factory cbf) {
      return Dist.repeat$(this, n, cbf);
   }

   public Dist iterate(final int n, final Function1 f) {
      return Dist.iterate$(this, n, f);
   }

   public Dist iterate$mcZ$sp(final int n, final Function1 f) {
      return Dist.iterate$mcZ$sp$(this, n, f);
   }

   public Dist iterate$mcB$sp(final int n, final Function1 f) {
      return Dist.iterate$mcB$sp$(this, n, f);
   }

   public Dist iterate$mcC$sp(final int n, final Function1 f) {
      return Dist.iterate$mcC$sp$(this, n, f);
   }

   public Dist iterate$mcD$sp(final int n, final Function1 f) {
      return Dist.iterate$mcD$sp$(this, n, f);
   }

   public Dist iterate$mcF$sp(final int n, final Function1 f) {
      return Dist.iterate$mcF$sp$(this, n, f);
   }

   public Dist iterate$mcI$sp(final int n, final Function1 f) {
      return Dist.iterate$mcI$sp$(this, n, f);
   }

   public Dist iterate$mcJ$sp(final int n, final Function1 f) {
      return Dist.iterate$mcJ$sp$(this, n, f);
   }

   public Dist iterate$mcS$sp(final int n, final Function1 f) {
      return Dist.iterate$mcS$sp$(this, n, f);
   }

   public Dist iterate$mcV$sp(final int n, final Function1 f) {
      return Dist.iterate$mcV$sp$(this, n, f);
   }

   public Dist iterateUntil(final Function1 pred, final Function1 f) {
      return Dist.iterateUntil$(this, pred, f);
   }

   public Dist iterateUntil$mcZ$sp(final Function1 pred, final Function1 f) {
      return Dist.iterateUntil$mcZ$sp$(this, pred, f);
   }

   public Dist iterateUntil$mcB$sp(final Function1 pred, final Function1 f) {
      return Dist.iterateUntil$mcB$sp$(this, pred, f);
   }

   public Dist iterateUntil$mcC$sp(final Function1 pred, final Function1 f) {
      return Dist.iterateUntil$mcC$sp$(this, pred, f);
   }

   public Dist iterateUntil$mcD$sp(final Function1 pred, final Function1 f) {
      return Dist.iterateUntil$mcD$sp$(this, pred, f);
   }

   public Dist iterateUntil$mcF$sp(final Function1 pred, final Function1 f) {
      return Dist.iterateUntil$mcF$sp$(this, pred, f);
   }

   public Dist iterateUntil$mcI$sp(final Function1 pred, final Function1 f) {
      return Dist.iterateUntil$mcI$sp$(this, pred, f);
   }

   public Dist iterateUntil$mcJ$sp(final Function1 pred, final Function1 f) {
      return Dist.iterateUntil$mcJ$sp$(this, pred, f);
   }

   public Dist iterateUntil$mcS$sp(final Function1 pred, final Function1 f) {
      return Dist.iterateUntil$mcS$sp$(this, pred, f);
   }

   public Dist iterateUntil$mcV$sp(final Function1 pred, final Function1 f) {
      return Dist.iterateUntil$mcV$sp$(this, pred, f);
   }

   public Dist zip(final Dist that) {
      return Dist.zip$(this, that);
   }

   public Dist zip$mcZ$sp(final Dist that) {
      return Dist.zip$mcZ$sp$(this, that);
   }

   public Dist zip$mcB$sp(final Dist that) {
      return Dist.zip$mcB$sp$(this, that);
   }

   public Dist zip$mcC$sp(final Dist that) {
      return Dist.zip$mcC$sp$(this, that);
   }

   public Dist zip$mcD$sp(final Dist that) {
      return Dist.zip$mcD$sp$(this, that);
   }

   public Dist zip$mcF$sp(final Dist that) {
      return Dist.zip$mcF$sp$(this, that);
   }

   public Dist zip$mcI$sp(final Dist that) {
      return Dist.zip$mcI$sp$(this, that);
   }

   public Dist zip$mcJ$sp(final Dist that) {
      return Dist.zip$mcJ$sp$(this, that);
   }

   public Dist zip$mcS$sp(final Dist that) {
      return Dist.zip$mcS$sp$(this, that);
   }

   public Dist zip$mcV$sp(final Dist that) {
      return Dist.zip$mcV$sp$(this, that);
   }

   public Dist zipWith(final Dist that, final Function2 f) {
      return Dist.zipWith$(this, that, f);
   }

   public Dist zipWith$mcZ$sp(final Dist that, final Function2 f) {
      return Dist.zipWith$mcZ$sp$(this, that, f);
   }

   public Dist zipWith$mcB$sp(final Dist that, final Function2 f) {
      return Dist.zipWith$mcB$sp$(this, that, f);
   }

   public Dist zipWith$mcC$sp(final Dist that, final Function2 f) {
      return Dist.zipWith$mcC$sp$(this, that, f);
   }

   public Dist zipWith$mcD$sp(final Dist that, final Function2 f) {
      return Dist.zipWith$mcD$sp$(this, that, f);
   }

   public Dist zipWith$mcF$sp(final Dist that, final Function2 f) {
      return Dist.zipWith$mcF$sp$(this, that, f);
   }

   public Dist zipWith$mcI$sp(final Dist that, final Function2 f) {
      return Dist.zipWith$mcI$sp$(this, that, f);
   }

   public Dist zipWith$mcJ$sp(final Dist that, final Function2 f) {
      return Dist.zipWith$mcJ$sp$(this, that, f);
   }

   public Dist zipWith$mcS$sp(final Dist that, final Function2 f) {
      return Dist.zipWith$mcS$sp$(this, that, f);
   }

   public Dist zipWith$mcV$sp(final Dist that, final Function2 f) {
      return Dist.zipWith$mcV$sp$(this, that, f);
   }

   public final Iterator toIterator(final Generator gen) {
      return Dist.toIterator$(this, gen);
   }

   public final LazyList toLazyList(final Generator gen) {
      return Dist.toLazyList$(this, gen);
   }

   /** @deprecated */
   public final Stream toStream(final Generator gen) {
      return Dist.toStream$(this, gen);
   }

   public Iterable sample(final int n, final Generator gen, final Factory cbf) {
      return Dist.sample$(this, n, gen, cbf);
   }

   public int count(final Function1 pred, final int n, final Generator gen) {
      return Dist.count$(this, pred, n, gen);
   }

   public int count$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist.count$mcZ$sp$(this, pred, n, gen);
   }

   public int count$mcB$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist.count$mcB$sp$(this, pred, n, gen);
   }

   public int count$mcC$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist.count$mcC$sp$(this, pred, n, gen);
   }

   public int count$mcD$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist.count$mcD$sp$(this, pred, n, gen);
   }

   public int count$mcF$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist.count$mcF$sp$(this, pred, n, gen);
   }

   public int count$mcI$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist.count$mcI$sp$(this, pred, n, gen);
   }

   public int count$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist.count$mcJ$sp$(this, pred, n, gen);
   }

   public int count$mcS$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist.count$mcS$sp$(this, pred, n, gen);
   }

   public int count$mcV$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist.count$mcV$sp$(this, pred, n, gen);
   }

   public double pr(final Function1 pred, final int n, final Generator gen) {
      return Dist.pr$(this, pred, n, gen);
   }

   public double pr$mcZ$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist.pr$mcZ$sp$(this, pred, n, gen);
   }

   public double pr$mcB$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist.pr$mcB$sp$(this, pred, n, gen);
   }

   public double pr$mcC$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist.pr$mcC$sp$(this, pred, n, gen);
   }

   public double pr$mcD$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist.pr$mcD$sp$(this, pred, n, gen);
   }

   public double pr$mcF$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist.pr$mcF$sp$(this, pred, n, gen);
   }

   public double pr$mcI$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist.pr$mcI$sp$(this, pred, n, gen);
   }

   public double pr$mcJ$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist.pr$mcJ$sp$(this, pred, n, gen);
   }

   public double pr$mcS$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist.pr$mcS$sp$(this, pred, n, gen);
   }

   public double pr$mcV$sp(final Function1 pred, final int n, final Generator gen) {
      return Dist.pr$mcV$sp$(this, pred, n, gen);
   }

   public Object sum(final int n, final Generator gen, final Rig alg) {
      return Dist.sum$(this, n, gen, alg);
   }

   public boolean sum$mcZ$sp(final int n, final Generator gen, final Rig alg) {
      return Dist.sum$mcZ$sp$(this, n, gen, alg);
   }

   public byte sum$mcB$sp(final int n, final Generator gen, final Rig alg) {
      return Dist.sum$mcB$sp$(this, n, gen, alg);
   }

   public char sum$mcC$sp(final int n, final Generator gen, final Rig alg) {
      return Dist.sum$mcC$sp$(this, n, gen, alg);
   }

   public double sum$mcD$sp(final int n, final Generator gen, final Rig alg) {
      return Dist.sum$mcD$sp$(this, n, gen, alg);
   }

   public float sum$mcF$sp(final int n, final Generator gen, final Rig alg) {
      return Dist.sum$mcF$sp$(this, n, gen, alg);
   }

   public int sum$mcI$sp(final int n, final Generator gen, final Rig alg) {
      return Dist.sum$mcI$sp$(this, n, gen, alg);
   }

   public long sum$mcJ$sp(final int n, final Generator gen, final Rig alg) {
      return Dist.sum$mcJ$sp$(this, n, gen, alg);
   }

   public short sum$mcS$sp(final int n, final Generator gen, final Rig alg) {
      return Dist.sum$mcS$sp$(this, n, gen, alg);
   }

   public void sum$mcV$sp(final int n, final Generator gen, final Rig alg) {
      Dist.sum$mcV$sp$(this, n, gen, alg);
   }

   public Object ev(final int n, final Generator gen, final Field alg) {
      return Dist.ev$(this, n, gen, alg);
   }

   public boolean ev$mcZ$sp(final int n, final Generator gen, final Field alg) {
      return Dist.ev$mcZ$sp$(this, n, gen, alg);
   }

   public byte ev$mcB$sp(final int n, final Generator gen, final Field alg) {
      return Dist.ev$mcB$sp$(this, n, gen, alg);
   }

   public char ev$mcC$sp(final int n, final Generator gen, final Field alg) {
      return Dist.ev$mcC$sp$(this, n, gen, alg);
   }

   public double ev$mcD$sp(final int n, final Generator gen, final Field alg) {
      return Dist.ev$mcD$sp$(this, n, gen, alg);
   }

   public float ev$mcF$sp(final int n, final Generator gen, final Field alg) {
      return Dist.ev$mcF$sp$(this, n, gen, alg);
   }

   public int ev$mcI$sp(final int n, final Generator gen, final Field alg) {
      return Dist.ev$mcI$sp$(this, n, gen, alg);
   }

   public long ev$mcJ$sp(final int n, final Generator gen, final Field alg) {
      return Dist.ev$mcJ$sp$(this, n, gen, alg);
   }

   public short ev$mcS$sp(final int n, final Generator gen, final Field alg) {
      return Dist.ev$mcS$sp$(this, n, gen, alg);
   }

   public void ev$mcV$sp(final int n, final Generator gen, final Field alg) {
      Dist.ev$mcV$sp$(this, n, gen, alg);
   }

   public Map histogram(final int n, final Generator gen) {
      return Dist.histogram$(this, n, gen);
   }

   public Map rawHistogram(final int n, final Generator gen) {
      return Dist.rawHistogram$(this, n, gen);
   }

   public Object apply(final Generator gen) {
      return this.f.apply(gen);
   }

   public boolean apply$mcZ$sp(final Generator gen) {
      return BoxesRunTime.unboxToBoolean(this.apply(gen));
   }

   public byte apply$mcB$sp(final Generator gen) {
      return BoxesRunTime.unboxToByte(this.apply(gen));
   }

   public char apply$mcC$sp(final Generator gen) {
      return BoxesRunTime.unboxToChar(this.apply(gen));
   }

   public double apply$mcD$sp(final Generator gen) {
      return BoxesRunTime.unboxToDouble(this.apply(gen));
   }

   public float apply$mcF$sp(final Generator gen) {
      return BoxesRunTime.unboxToFloat(this.apply(gen));
   }

   public int apply$mcI$sp(final Generator gen) {
      return BoxesRunTime.unboxToInt(this.apply(gen));
   }

   public long apply$mcJ$sp(final Generator gen) {
      return BoxesRunTime.unboxToLong(this.apply(gen));
   }

   public short apply$mcS$sp(final Generator gen) {
      return BoxesRunTime.unboxToShort(this.apply(gen));
   }

   public void apply$mcV$sp(final Generator gen) {
      this.apply(gen);
   }

   public DistFromGen(final Function1 f) {
      this.f = f;
      Dist.$init$(this);
   }
}
