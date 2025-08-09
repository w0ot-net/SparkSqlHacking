package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.ByteBufferInputStream;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import scala.Option;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceExtensionMethods.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%d\u0001\u0002\b\u0010\u0001YA\u0001\"\t\u0001\u0003\u0006\u0004%\tA\t\u0005\tU\u0001\u0011\t\u0011)A\u0005G!)1\u0006\u0001C\u0001Y!)q\u0006\u0001C\u0001a!)q\u0006\u0001C\u0001\u001b\")!\f\u0001C\u00017\")q\r\u0001C\u0001Q\")\u0011\u000f\u0001C\u0001e\"9\u00111\u0001\u0001\u0005\u0002\u0005\u0015\u0001bBA\t\u0001\u0011\u0005\u00111\u0003\u0005\b\u0003g\u0001A\u0011AA\u001b\u0011\u001d\t\t\u0005\u0001C\u0001\u0003\u0007Bq!!\u0016\u0001\t\u0003\t9F\u0001\u0005SS\u000eD7J]=p\u0015\t\u0001\u0012#A\u0003dQ&dGN\u0003\u0002\u0013'\u00059Ao^5ui\u0016\u0014(\"\u0001\u000b\u0002\u0007\r|Wn\u0001\u0001\u0014\u0007\u00019R\u0004\u0005\u0002\u001975\t\u0011DC\u0001\u001b\u0003\u0015\u00198-\u00197b\u0013\ta\u0012D\u0001\u0004B]f\u0014VM\u001a\t\u0003=}i\u0011aD\u0005\u0003A=\u0011aBU5dQ.\u0013\u0018p\\\"p[B\fG/A\u0001l+\u0005\u0019\u0003C\u0001\u0013(\u001d\tqR%\u0003\u0002'\u001f\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u0015*\u0005\u0011Y%/_8\u000b\u0005\u0019z\u0011AA6!\u0003\u0019a\u0014N\\5u}Q\u0011QF\f\t\u0003=\u0001AQ!I\u0002A\u0002\r\n\u0011#\u00197sK\u0006$\u0017PU3hSN$XM]3e)\t\tD\u0007\u0005\u0002\u0019e%\u00111'\u0007\u0002\b\u0005>|G.Z1o\u0011\u0015)D\u00011\u00017\u0003\u0015YG.Y:ta\t9D\tE\u00029\u007f\ts!!O\u001f\u0011\u0005iJR\"A\u001e\u000b\u0005q*\u0012A\u0002\u001fs_>$h(\u0003\u0002?3\u00051\u0001K]3eK\u001aL!\u0001Q!\u0003\u000b\rc\u0017m]:\u000b\u0005yJ\u0002CA\"E\u0019\u0001!\u0011\"\u0012\u001b\u0002\u0002\u0003\u0005)\u0011\u0001$\u0003\u0007}#\u0013'\u0005\u0002H\u0015B\u0011\u0001\u0004S\u0005\u0003\u0013f\u0011qAT8uQ&tw\r\u0005\u0002\u0019\u0017&\u0011A*\u0007\u0002\u0004\u0003:LXC\u0001(Y)\t\tt\nC\u0003Q\u000b\u0001\u000f\u0011+A\u0002d[\u001a\u00042AU+X\u001b\u0005\u0019&B\u0001+\u001a\u0003\u001d\u0011XM\u001a7fGRL!AV*\u0003\u0011\rc\u0017m]:UC\u001e\u0004\"a\u0011-\u0005\u000be+!\u0019\u0001$\u0003\u0003Q\u000b1BZ8s'V\u00147\r\\1tgV\u0011A,\u0019\u000b\u0003;\n$\"a\t0\t\u000bA3\u00019A0\u0011\u0007I+\u0006\r\u0005\u0002DC\u0012)\u0011L\u0002b\u0001\r\")1M\u0002a\u0001I\u0006!1n]3s!\r!S\rY\u0005\u0003M&\u00121bS*fe&\fG.\u001b>fe\u0006Aam\u001c:DY\u0006\u001c8/\u0006\u0002j]R\u0011!n\u001c\u000b\u0003G-DQ\u0001U\u0004A\u00041\u00042AU+n!\t\u0019e\u000eB\u0003Z\u000f\t\u0007a\tC\u0003d\u000f\u0001\u0007\u0001\u000fE\u0002%K6\fAB[1wC\u001a{'o\u00117bgN,\"a]<\u0015\u0005\r\"\b\"\u0002)\t\u0001\b)\bc\u0001*VmB\u00111i\u001e\u0003\u00063\"\u0011\r\u0001_\t\u0003\u000ff\u0004\"A_@\u000e\u0003mT!\u0001`?\u0002\u0005%|'\"\u0001@\u0002\t)\fg/Y\u0005\u0004\u0003\u0003Y(\u0001D*fe&\fG.\u001b>bE2,\u0017a\u00046bm\u00064uN]*vE\u000ed\u0017m]:\u0016\t\u0005\u001d\u0011q\u0002\u000b\u0004G\u0005%\u0001B\u0002)\n\u0001\b\tY\u0001\u0005\u0003S+\u00065\u0001cA\"\u0002\u0010\u0011)\u0011,\u0003b\u0001q\u0006y!/Z4jgR,'o\u00117bgN,7\u000fF\u0002$\u0003+Aq!a\u0006\u000b\u0001\u0004\tI\"A\u0004lY\u0006\u001c8/Z:\u0011\r\u0005m\u00111EA\u0015\u001d\u0011\ti\"!\t\u000f\u0007i\ny\"C\u0001\u001b\u0013\t1\u0013$\u0003\u0003\u0002&\u0005\u001d\"a\u0004+sCZ,'o]1cY\u0016|enY3\u000b\u0005\u0019J\u0002\u0007BA\u0016\u0003_\u0001B\u0001O \u0002.A\u00191)a\f\u0005\u0017\u0005E\u0012QCA\u0001\u0002\u0003\u0015\tA\u0012\u0002\u0004?\u0012\u0012\u0014\u0001\u00049paVd\u0017\r^3Ge>lGcA\u0012\u00028!9\u0011\u0011H\u0006A\u0002\u0005m\u0012a\u0001:fOB\u0019a$!\u0010\n\u0007\u0005}rB\u0001\bJ\u0017JLxNU3hSN$(/\u0019:\u0002\u001f\u0019\u0014x.\\%oaV$8\u000b\u001e:fC6$B!!\u0012\u0002LA!\u0001$a\u0012\u0018\u0013\r\tI%\u0007\u0002\u0007\u001fB$\u0018n\u001c8\t\u000f\u00055C\u00021\u0001\u0002P\u0005\t1\u000fE\u0002{\u0003#J1!a\u0015|\u0005-Ie\u000e];u'R\u0014X-Y7\u0002\u001d\u0019\u0014x.\u001c\"zi\u0016\u0014UO\u001a4feR!\u0011QIA-\u0011\u001d\tY&\u0004a\u0001\u0003;\n\u0011A\u0019\t\u0005\u0003?\n)'\u0004\u0002\u0002b)\u0019\u00111M?\u0002\u00079Lw.\u0003\u0003\u0002h\u0005\u0005$A\u0003\"zi\u0016\u0014UO\u001a4fe\u0002"
)
public class RichKryo implements RichKryoCompat {
   private final Kryo k;

   public Kryo forTraversableSubclass(final Iterable c, final boolean isImmutable, final ClassTag mf, final Factory f) {
      return RichKryoCompat.forTraversableSubclass$(this, c, isImmutable, mf, f);
   }

   public boolean forTraversableSubclass$default$2() {
      return RichKryoCompat.forTraversableSubclass$default$2$(this);
   }

   public Kryo forTraversableClass(final Iterable c, final boolean isImmutable, final ClassTag mf, final Factory f) {
      return RichKryoCompat.forTraversableClass$(this, c, isImmutable, mf, f);
   }

   public boolean forTraversableClass$default$2() {
      return RichKryoCompat.forTraversableClass$default$2$(this);
   }

   public Kryo forConcreteTraversableClass(final Iterable c, final boolean isImmutable, final Factory f) {
      return RichKryoCompat.forConcreteTraversableClass$(this, c, isImmutable, f);
   }

   public boolean forConcreteTraversableClass$default$2() {
      return RichKryoCompat.forConcreteTraversableClass$default$2$(this);
   }

   public Kryo k() {
      return this.k;
   }

   public boolean alreadyRegistered(final Class klass) {
      return this.k().getClassResolver().getRegistration(klass) != null;
   }

   public boolean alreadyRegistered(final ClassTag cmf) {
      return this.alreadyRegistered(cmf.runtimeClass());
   }

   public Kryo forSubclass(final Serializer kser, final ClassTag cmf) {
      this.k().addDefaultSerializer(cmf.runtimeClass(), kser);
      return this.k();
   }

   public Kryo forClass(final Serializer kser, final ClassTag cmf) {
      this.k().register(cmf.runtimeClass(), kser);
      return this.k();
   }

   public Kryo javaForClass(final ClassTag cmf) {
      this.k().register(cmf.runtimeClass(), new JavaSerializer());
      return this.k();
   }

   public Kryo javaForSubclass(final ClassTag cmf) {
      this.k().addDefaultSerializer(cmf.runtimeClass(), new JavaSerializer());
      return this.k();
   }

   public Kryo registerClasses(final IterableOnce klasses) {
      .MODULE$.foreach$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(klasses), (klass) -> !this.alreadyRegistered(scala.reflect.ClassTag..MODULE$.apply(klass)) ? this.k().register(klass) : BoxedUnit.UNIT);
      return this.k();
   }

   public Kryo populateFrom(final IKryoRegistrar reg) {
      reg.apply(this.k());
      return this.k();
   }

   public Option fromInputStream(final InputStream s) {
      Input streamInput = new Input(s);
      return scala.util.control.Exception..MODULE$.allCatch().opt(() -> this.k().readClassAndObject(streamInput));
   }

   public Option fromByteBuffer(final ByteBuffer b) {
      return this.fromInputStream(new ByteBufferInputStream(b));
   }

   public RichKryo(final Kryo k) {
      this.k = k;
      RichKryoCompat.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
