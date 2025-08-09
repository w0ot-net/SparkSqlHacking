package org.apache.spark.sql.types;

import org.apache.spark.annotation.Stable;
import scala..less.colon.less.;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005c\u0001\u0002\u000b\u0016\u0001\u0001BQa\n\u0001\u0005\u0002!Bqa\u000b\u0001C\u0002\u0013%A\u0006\u0003\u0004D\u0001\u0001\u0006I!\f\u0005\u0006\t\u0002!\t\"\u0012\u0005\u0006\u0017\u0002!\t\u0001\u0014\u0005\u0006'\u0002!\t\u0001\u0016\u0005\u0006/\u0002!\t\u0001\u0017\u0005\u0006?\u0002!\t\u0001\u0019\u0005\u0006M\u0002!\ta\u001a\u0005\u0006[\u0002!\tA\u001c\u0005\u0006c\u0002!\tA\u001d\u0005\u0006k\u0002!\tA\u001e\u0005\u0006y\u0002!\t! \u0005\b\u0003\u0007\u0001A\u0011AA\u0003\u0011\u001d\ti\u0001\u0001C\u0001\u0003\u001fAq!a\u0006\u0001\t\u0003\tI\u0002C\u0004\u0002\"\u0001!\t!a\t\t\u000f\u0005\u0015\u0002\u0001\"\u0003\u0002(!9\u0011Q\u0006\u0001\u0005\u0002\u0005=\"aD'fi\u0006$\u0017\r^1Ck&dG-\u001a:\u000b\u0005Y9\u0012!\u0002;za\u0016\u001c(B\u0001\r\u001a\u0003\r\u0019\u0018\u000f\u001c\u0006\u00035m\tQa\u001d9be.T!\u0001H\u000f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0012aA8sO\u000e\u00011C\u0001\u0001\"!\t\u0011S%D\u0001$\u0015\u0005!\u0013!B:dC2\f\u0017B\u0001\u0014$\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012!\u000b\t\u0003U\u0001i\u0011!F\u0001\u0004[\u0006\u0004X#A\u0017\u0011\t9\u001aT\u0007Q\u0007\u0002_)\u0011\u0001'M\u0001\b[V$\u0018M\u00197f\u0015\t\u00114%\u0001\u0006d_2dWm\u0019;j_:L!\u0001N\u0018\u0003\u00075\u000b\u0007\u000f\u0005\u00027{9\u0011qg\u000f\t\u0003q\rj\u0011!\u000f\u0006\u0003u}\ta\u0001\u0010:p_Rt\u0014B\u0001\u001f$\u0003\u0019\u0001&/\u001a3fM&\u0011ah\u0010\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005q\u001a\u0003C\u0001\u0012B\u0013\t\u00115EA\u0002B]f\fA!\\1qA\u00051q-\u001a;NCB,\u0012A\u0012\t\u0005\u000f*+\u0004)D\u0001I\u0015\tI\u0015'A\u0005j[6,H/\u00192mK&\u0011A\u0007S\u0001\ro&$\b.T3uC\u0012\fG/\u0019\u000b\u0003\u001b:k\u0011\u0001\u0001\u0005\u0006\u001f\u0016\u0001\r\u0001U\u0001\t[\u0016$\u0018\rZ1uCB\u0011!&U\u0005\u0003%V\u0011\u0001\"T3uC\u0012\fG/Y\u0001\baV$h*\u001e7m)\tiU\u000bC\u0003W\r\u0001\u0007Q'A\u0002lKf\fq\u0001];u\u0019>tw\rF\u0002N3jCQAV\u0004A\u0002UBQaW\u0004A\u0002q\u000bQA^1mk\u0016\u0004\"AI/\n\u0005y\u001b#\u0001\u0002'p]\u001e\f\u0011\u0002];u\t>,(\r\\3\u0015\u00075\u000b'\rC\u0003W\u0011\u0001\u0007Q\u0007C\u0003\\\u0011\u0001\u00071\r\u0005\u0002#I&\u0011Qm\t\u0002\u0007\t>,(\r\\3\u0002\u0015A,HOQ8pY\u0016\fg\u000eF\u0002NQ&DQAV\u0005A\u0002UBQaW\u0005A\u0002)\u0004\"AI6\n\u00051\u001c#a\u0002\"p_2,\u0017M\\\u0001\naV$8\u000b\u001e:j]\u001e$2!T8q\u0011\u00151&\u00021\u00016\u0011\u0015Y&\u00021\u00016\u0003-\u0001X\u000f^'fi\u0006$\u0017\r^1\u0015\u00075\u001bH\u000fC\u0003W\u0017\u0001\u0007Q\u0007C\u0003\\\u0017\u0001\u0007\u0001+\u0001\u0007qkRduN\\4BeJ\f\u0017\u0010F\u0002NobDQA\u0016\u0007A\u0002UBQa\u0017\u0007A\u0002e\u00042A\t>]\u0013\tY8EA\u0003BeJ\f\u00170\u0001\bqkR$u.\u001e2mK\u0006\u0013(/Y=\u0015\u00075sx\u0010C\u0003W\u001b\u0001\u0007Q\u0007\u0003\u0004\\\u001b\u0001\u0007\u0011\u0011\u0001\t\u0004Ei\u001c\u0017a\u00049vi\n{w\u000e\\3b]\u0006\u0013(/Y=\u0015\u000b5\u000b9!!\u0003\t\u000bYs\u0001\u0019A\u001b\t\rms\u0001\u0019AA\u0006!\r\u0011#P[\u0001\u000faV$8\u000b\u001e:j]\u001e\f%O]1z)\u0015i\u0015\u0011CA\n\u0011\u00151v\u00021\u00016\u0011\u0019Yv\u00021\u0001\u0002\u0016A\u0019!E_\u001b\u0002!A,H/T3uC\u0012\fG/Y!se\u0006LH#B'\u0002\u001c\u0005u\u0001\"\u0002,\u0011\u0001\u0004)\u0004BB.\u0011\u0001\u0004\ty\u0002E\u0002#uB\u000bQAY;jY\u0012$\u0012\u0001U\u0001\u0004aV$H#B'\u0002*\u0005-\u0002\"\u0002,\u0013\u0001\u0004)\u0004\"B.\u0013\u0001\u0004\u0001\u0015A\u0002:f[>4X\rF\u0002N\u0003cAQAV\nA\u0002UB3\u0001AA\u001b!\u0011\t9$!\u0010\u000e\u0005\u0005e\"bAA\u001e3\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005}\u0012\u0011\b\u0002\u0007'R\f'\r\\3"
)
public class MetadataBuilder {
   private final Map map;

   private Map map() {
      return this.map;
   }

   public scala.collection.immutable.Map getMap() {
      return this.map().toMap(.MODULE$.refl());
   }

   public MetadataBuilder withMetadata(final Metadata metadata) {
      this.map().$plus$plus$eq(metadata.map());
      return this;
   }

   public MetadataBuilder putNull(final String key) {
      return this.put(key, (Object)null);
   }

   public MetadataBuilder putLong(final String key, final long value) {
      return this.put(key, BoxesRunTime.boxToLong(value));
   }

   public MetadataBuilder putDouble(final String key, final double value) {
      return this.put(key, BoxesRunTime.boxToDouble(value));
   }

   public MetadataBuilder putBoolean(final String key, final boolean value) {
      return this.put(key, BoxesRunTime.boxToBoolean(value));
   }

   public MetadataBuilder putString(final String key, final String value) {
      return this.put(key, value);
   }

   public MetadataBuilder putMetadata(final String key, final Metadata value) {
      return this.put(key, value);
   }

   public MetadataBuilder putLongArray(final String key, final long[] value) {
      return this.put(key, value);
   }

   public MetadataBuilder putDoubleArray(final String key, final double[] value) {
      return this.put(key, value);
   }

   public MetadataBuilder putBooleanArray(final String key, final boolean[] value) {
      return this.put(key, value);
   }

   public MetadataBuilder putStringArray(final String key, final String[] value) {
      return this.put(key, value);
   }

   public MetadataBuilder putMetadataArray(final String key, final Metadata[] value) {
      return this.put(key, value);
   }

   public Metadata build() {
      return new Metadata(this.map().toMap(.MODULE$.refl()));
   }

   private MetadataBuilder put(final String key, final Object value) {
      this.map().put(key, value);
      return this;
   }

   public MetadataBuilder remove(final String key) {
      this.map().remove(key);
      return this;
   }

   public MetadataBuilder() {
      this.map = (Map)scala.collection.mutable.Map..MODULE$.empty();
   }
}
