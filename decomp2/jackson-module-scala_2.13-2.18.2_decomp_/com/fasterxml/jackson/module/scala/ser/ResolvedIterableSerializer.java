package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;
import scala.collection.Iterable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I4AAC\u0006\u00051!A\u0011\u0007\u0001B\u0001B\u0003%Q\u0006\u0003\u00053\u0001\t\u0005\t\u0015!\u00034\u0011!9\u0004A!A!\u0002\u0013A\u0004\u0002\u0003 \u0001\u0005\u0003\u0005\u000b\u0011B \t\u0011-\u0003!\u0011!Q\u0001\n1CQ\u0001\u0016\u0001\u0005\u0002UCq\u0001\u0019\u0001C\u0002\u0013\u0005\u0011\r\u0003\u0004f\u0001\u0001\u0006IA\u0019\u0005\u0006M\u0002!\te\u001a\u0002\u001b%\u0016\u001cx\u000e\u001c<fI&#XM]1cY\u0016\u001cVM]5bY&TXM\u001d\u0006\u0003\u00195\t1a]3s\u0015\tqq\"A\u0003tG\u0006d\u0017M\u0003\u0002\u0011#\u00051Qn\u001c3vY\u0016T!AE\n\u0002\u000f)\f7m[:p]*\u0011A#F\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011AF\u0001\u0004G>l7\u0001A\n\u0004\u0001ei\u0003c\u0001\u000e!E5\t1D\u0003\u0002\u001d;\u0005\u00191\u000f\u001e3\u000b\u00051q\"BA\u0010\u0012\u0003!!\u0017\r^1cS:$\u0017BA\u0011\u001c\u0005U\t5/\u0011:sCf\u001cVM]5bY&TXM\u001d\"bg\u0016\u00042aI\u0014*\u001b\u0005!#BA\u0013'\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u001d%\u0011\u0001\u0006\n\u0002\t\u0013R,'/\u00192mKB\u0011!fK\u0007\u0002M%\u0011AF\n\u0002\u0004\u0003:L\bC\u0001\u00180\u001b\u0005Y\u0011B\u0001\u0019\f\u0005IIE/\u001a:bE2,7+\u001a:jC2L'0\u001a:\u0002\u0007M\u00148-\u0001\u0005qe>\u0004XM\u001d;z!\t!T'D\u0001\u001f\u0013\t1dD\u0001\u0007CK\u0006t\u0007K]8qKJ$\u00180A\u0002wiN\u0004\"!\u000f\u001f\u000e\u0003iR!a\u000f\u0010\u0002\u0011)\u001cxN\u001c;za\u0016L!!\u0010\u001e\u0003\u001dQK\b/Z*fe&\fG.\u001b>fe\u0006\tR\r\\3nK:$8+\u001a:jC2L'0\u001a:1\u0005\u0001+\u0005c\u0001\u001bB\u0007&\u0011!I\b\u0002\u000f\u0015N|gnU3sS\u0006d\u0017N_3s!\t!U\t\u0004\u0001\u0005\u0013\u0019#\u0011\u0011!A\u0001\u0006\u00039%aA0%eE\u0011\u0001*\u000b\t\u0003U%K!A\u0013\u0014\u0003\u000f9{G\u000f[5oO\u0006aQO\\<sCB\u001c\u0016N\\4mKB\u0011QJU\u0007\u0002\u001d*\u0011q\nU\u0001\u0005Y\u0006twMC\u0001R\u0003\u0011Q\u0017M^1\n\u0005Ms%a\u0002\"p_2,\u0017M\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\rY;\u0006,\u0017.`!\tq\u0003\u0001C\u00032\r\u0001\u0007Q\u0006C\u00033\r\u0001\u00071\u0007C\u00038\r\u0001\u0007\u0001\bC\u0003?\r\u0001\u00071\f\r\u0002]=B\u0019A'Q/\u0011\u0005\u0011sF!\u0003$[\u0003\u0003\u0005\tQ!\u0001H\u0011\u0015Ye\u00011\u0001M\u0003Q\u0019w\u000e\u001c7fGRLwN\\*fe&\fG.\u001b>feV\t!\r\u0005\u0002/G&\u0011Am\u0003\u0002\u0018'\u000e\fG.Y%uKJ\f'\r\\3TKJL\u0017\r\\5{KJ\fQcY8mY\u0016\u001cG/[8o'\u0016\u0014\u0018.\u00197ju\u0016\u0014\b%\u0001\r`o&$\bNV1mk\u0016$\u0016\u0010]3TKJL\u0017\r\\5{KJ$\"\u0001\u001b91\u0005%t\u0007c\u00016l[6\tQ$\u0003\u0002m;\t\u00192i\u001c8uC&tWM]*fe&\fG.\u001b>feB\u0011AI\u001c\u0003\n_&\t\t\u0011!A\u0003\u0002\u001d\u00131a\u0018\u00134\u0011\u0015\t\u0018\u00021\u00019\u0003\u0019qWm\u001e,ug\u0002"
)
public class ResolvedIterableSerializer extends AsArraySerializerBase implements IterableSerializer {
   private final BeanProperty property;
   private final JsonSerializer elementSerializer;
   private final Boolean unwrapSingle;
   private final ScalaIterableSerializer collectionSerializer;

   public boolean hasSingleElement(final Iterable value) {
      return IterableSerializer.hasSingleElement$(this, value);
   }

   public void serializeContents(final Iterable value, final JsonGenerator gen, final SerializerProvider provider) {
      IterableSerializer.serializeContents$(this, value, gen, provider);
   }

   public ResolvedIterableSerializer withResolved(final BeanProperty property, final TypeSerializer vts, final JsonSerializer elementSerializer, final Boolean unwrapSingle) {
      return IterableSerializer.withResolved$(this, property, vts, elementSerializer, unwrapSingle);
   }

   public boolean isEmpty(final SerializerProvider prov, final Iterable value) {
      return IterableSerializer.isEmpty$(this, prov, value);
   }

   public ScalaIterableSerializer collectionSerializer() {
      return this.collectionSerializer;
   }

   public ContainerSerializer _withValueTypeSerializer(final TypeSerializer newVts) {
      return new ResolvedIterableSerializer(this, this.property, newVts, this.elementSerializer, this.unwrapSingle);
   }

   public ResolvedIterableSerializer(final IterableSerializer src, final BeanProperty property, final TypeSerializer vts, final JsonSerializer elementSerializer, final Boolean unwrapSingle) {
      super((AsArraySerializerBase)src, property, vts, elementSerializer, unwrapSingle);
      this.property = property;
      this.elementSerializer = elementSerializer;
      this.unwrapSingle = unwrapSingle;
      IterableSerializer.$init$(this);
      this.collectionSerializer = new ScalaIterableSerializer(src.collectionSerializer(), property, vts, elementSerializer, unwrapSingle);
   }
}
