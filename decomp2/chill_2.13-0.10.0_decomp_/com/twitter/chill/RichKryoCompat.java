package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rc!\u0003\u0005\n!\u0003\r\t\u0001EA\u001f\u0011\u00159\u0002\u0001\"\u0001\u0019\u0011\u0015a\u0002\u0001\"\u0001\u001e\u0011\u001da\u0006!%A\u0005\u0002uCQA\u001c\u0001\u0005\u0002=D\u0011\"!\u0001\u0001#\u0003%\t!a\u0001\t\u000f\u0005=\u0001\u0001\"\u0001\u0002\u0012!I\u0011q\u0006\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u0007\u0002\u000f%&\u001c\u0007n\u0013:z_\u000e{W\u000e]1u\u0015\tQ1\"A\u0003dQ&dGN\u0003\u0002\r\u001b\u00059Ao^5ui\u0016\u0014(\"\u0001\b\u0002\u0007\r|Wn\u0001\u0001\u0014\u0005\u0001\t\u0002C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u00023A\u0011!CG\u0005\u00037M\u0011A!\u00168ji\u00061bm\u001c:Ue\u00064XM]:bE2,7+\u001e2dY\u0006\u001c8/F\u0002\u001f\tJ\"2a\b*X)\r\u0001\u0003F\u0013\t\u0003C\u0015r!AI\u0012\u000e\u0003%I!\u0001J\u0005\u0002\u000fA\f7m[1hK&\u0011ae\n\u0002\u0005\u0017JLxN\u0003\u0002%\u0013!)\u0011F\u0001a\u0002U\u0005\u0011QN\u001a\t\u0004W9\u0002T\"\u0001\u0017\u000b\u00055\u001a\u0012a\u0002:fM2,7\r^\u0005\u0003_1\u0012\u0001b\u00117bgN$\u0016m\u001a\t\u0003cIb\u0001\u0001B\u00034\u0005\t\u0007AGA\u0001D#\t)\u0004\b\u0005\u0002\u0013m%\u0011qg\u0005\u0002\b\u001d>$\b.\u001b8h!\rI\u0004i\u0011\b\u0003u}r!a\u000f \u000e\u0003qR!!P\b\u0002\rq\u0012xn\u001c;?\u0013\u0005!\u0012B\u0001\u0013\u0014\u0013\t\t%IA\u0006Ue\u00064XM]:bE2,'B\u0001\u0013\u0014!\t\tD\tB\u0003F\u0005\t\u0007aIA\u0001U#\t)t\t\u0005\u0002\u0013\u0011&\u0011\u0011j\u0005\u0002\u0004\u0003:L\b\"B&\u0003\u0001\ba\u0015!\u00014\u0011\t5\u00036\tM\u0007\u0002\u001d*\u0011qjE\u0001\u000bG>dG.Z2uS>t\u0017BA)O\u0005\u001d1\u0015m\u0019;pefDQa\u0015\u0002A\u0002Q\u000b\u0011a\u0019\n\u0004+BBd\u0001\u0002,\u0001\u0001Q\u0013A\u0002\u0010:fM&tW-\\3oizBq\u0001\u0017\u0002\u0011\u0002\u0003\u0007\u0011,A\u0006jg&kW.\u001e;bE2,\u0007C\u0001\n[\u0013\tY6CA\u0004C_>dW-\u00198\u0002A\u0019|'\u000f\u0016:bm\u0016\u00148/\u00192mKN+(m\u00197bgN$C-\u001a4bk2$HEM\u000b\u0004=&TW#A0+\u0005e\u00037&A1\u0011\u0005\t<W\"A2\u000b\u0005\u0011,\u0017!C;oG\",7m[3e\u0015\t17#\u0001\u0006b]:|G/\u0019;j_:L!\u0001[2\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u0003F\u0007\t\u0007a\tB\u00034\u0007\t\u00071.\u0005\u00026YB\u0019\u0011\bQ7\u0011\u0005EJ\u0017a\u00054peR\u0013\u0018M^3sg\u0006\u0014G.Z\"mCN\u001cXc\u00019zkR\u0019\u0011\u000f`@\u0015\u0007\u0001\u0012(\u0010C\u0003*\t\u0001\u000f1\u000fE\u0002,]Q\u0004\"!M;\u0005\u000bM\"!\u0019\u0001<\u0012\u0005U:\bcA\u001dAqB\u0011\u0011'\u001f\u0003\u0006\u000b\u0012\u0011\rA\u0012\u0005\u0006\u0017\u0012\u0001\u001da\u001f\t\u0005\u001bBCH\u000fC\u0003T\t\u0001\u0007QPE\u0002\u007fi^4AA\u0016\u0001\u0001{\"9\u0001\f\u0002I\u0001\u0002\u0004I\u0016!\b4peR\u0013\u0018M^3sg\u0006\u0014G.Z\"mCN\u001cH\u0005Z3gCVdG\u000f\n\u001a\u0016\u000by\u000b)!a\u0002\u0005\u000b\u0015+!\u0019\u0001$\u0005\rM*!\u0019AA\u0005#\r)\u00141\u0002\t\u0005s\u0001\u000bi\u0001E\u00022\u0003\u000b\t1DZ8s\u0007>t7M]3uKR\u0013\u0018M^3sg\u0006\u0014G.Z\"mCN\u001cXCBA\n\u0003;\t\t\u0003\u0006\u0004\u0002\u0016\u0005\u001d\u0012Q\u0006\u000b\u0004A\u0005]\u0001BB&\u0007\u0001\b\tI\u0002\u0005\u0004N!\u0006m\u0011q\u0004\t\u0004c\u0005uA!B#\u0007\u0005\u00041\u0005cA\u0019\u0002\"\u001111G\u0002b\u0001\u0003G\t2!NA\u0013!\u0011I\u0004)a\u0007\t\rM3\u0001\u0019AA\u0015%\u0019\tY#a\b\u0002&\u0019)a\u000b\u0001\u0001\u0002*!9\u0001L\u0002I\u0001\u0002\u0004I\u0016!\n4pe\u000e{gn\u0019:fi\u0016$&/\u0019<feN\f'\r\\3DY\u0006\u001c8\u000f\n3fM\u0006,H\u000e\u001e\u00133+\u0015q\u00161GA\u001b\t\u0015)uA1\u0001G\t\u0019\u0019tA1\u0001\u00028E\u0019Q'!\u000f\u0011\te\u0002\u00151\b\t\u0004c\u0005M\u0002c\u0001\u0012\u0002@%\u0019\u0011\u0011I\u0005\u0003\u0011IK7\r[&ss>\u0004"
)
public interface RichKryoCompat {
   // $FF: synthetic method
   static Kryo forTraversableSubclass$(final RichKryoCompat $this, final Iterable c, final boolean isImmutable, final ClassTag mf, final Factory f) {
      return $this.forTraversableSubclass(c, isImmutable, mf, f);
   }

   default Kryo forTraversableSubclass(final Iterable c, final boolean isImmutable, final ClassTag mf, final Factory f) {
      ((RichKryo)this).k().addDefaultSerializer(mf.runtimeClass(), new TraversableSerializer(isImmutable, f));
      return ((RichKryo)this).k();
   }

   // $FF: synthetic method
   static boolean forTraversableSubclass$default$2$(final RichKryoCompat $this) {
      return $this.forTraversableSubclass$default$2();
   }

   default boolean forTraversableSubclass$default$2() {
      return true;
   }

   // $FF: synthetic method
   static Kryo forTraversableClass$(final RichKryoCompat $this, final Iterable c, final boolean isImmutable, final ClassTag mf, final Factory f) {
      return $this.forTraversableClass(c, isImmutable, mf, f);
   }

   default Kryo forTraversableClass(final Iterable c, final boolean isImmutable, final ClassTag mf, final Factory f) {
      return ((RichKryo)this).forClass(new TraversableSerializer(isImmutable, f), mf);
   }

   // $FF: synthetic method
   static boolean forTraversableClass$default$2$(final RichKryoCompat $this) {
      return $this.forTraversableClass$default$2();
   }

   default boolean forTraversableClass$default$2() {
      return true;
   }

   // $FF: synthetic method
   static Kryo forConcreteTraversableClass$(final RichKryoCompat $this, final Iterable c, final boolean isImmutable, final Factory f) {
      return $this.forConcreteTraversableClass(c, isImmutable, f);
   }

   default Kryo forConcreteTraversableClass(final Iterable c, final boolean isImmutable, final Factory f) {
      ((RichKryo)this).k().register(c.getClass(), new TraversableSerializer(isImmutable, f));
      return ((RichKryo)this).k();
   }

   // $FF: synthetic method
   static boolean forConcreteTraversableClass$default$2$(final RichKryoCompat $this) {
      return $this.forConcreteTraversableClass$default$2();
   }

   default boolean forConcreteTraversableClass$default$2() {
      return true;
   }

   static void $init$(final RichKryoCompat $this) {
   }
}
