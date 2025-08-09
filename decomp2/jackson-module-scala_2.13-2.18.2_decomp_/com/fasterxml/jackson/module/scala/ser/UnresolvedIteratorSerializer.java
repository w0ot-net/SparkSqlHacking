package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M4AAC\u0006\u00051!A\u0011\u0007\u0001B\u0001B\u0003%!\u0007\u0003\u0005G\u0001\t\u0005\t\u0015!\u0003H\u0011!Y\u0005A!A!\u0002\u0013a\u0005\u0002C(\u0001\u0005\u0003\u0005\u000b\u0011\u0002)\t\u0011Y\u0003!\u0011!Q\u0001\n]CQ!\u0018\u0001\u0005\u0002yCq!\u001b\u0001C\u0002\u0013\u0005#\u000e\u0003\u0004o\u0001\u0001\u0006Ia\u001b\u0005\u0006_\u0002!\t\u0005\u001d\u0002\u001d+:\u0014Xm]8mm\u0016$\u0017\n^3sCR|'oU3sS\u0006d\u0017N_3s\u0015\taQ\"A\u0002tKJT!AD\b\u0002\u000bM\u001c\u0017\r\\1\u000b\u0005A\t\u0012AB7pIVdWM\u0003\u0002\u0013'\u00059!.Y2lg>t'B\u0001\u000b\u0016\u0003%1\u0017m\u001d;feblGNC\u0001\u0017\u0003\r\u0019w.\\\u0002\u0001'\r\u0001\u0011$\f\t\u00045\u0001\u0012S\"A\u000e\u000b\u0005qi\u0012aA:uI*\u0011AB\b\u0006\u0003?E\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003Cm\u0011Q#Q:BeJ\f\u0017pU3sS\u0006d\u0017N_3s\u0005\u0006\u001cX\rE\u0002$O%j\u0011\u0001\n\u0006\u0003K\u0019\n!bY8mY\u0016\u001cG/[8o\u0015\u0005q\u0011B\u0001\u0015%\u0005!IE/\u001a:bi>\u0014\bC\u0001\u0016,\u001b\u00051\u0013B\u0001\u0017'\u0005\r\te.\u001f\t\u0003]=j\u0011aC\u0005\u0003a-\u0011!#\u0013;fe\u0006$xN]*fe&\fG.\u001b>fe\u0006\u00191\r\\:1\u0005M\u0002\u0005c\u0001\u001b<}9\u0011Q'\u000f\t\u0003m\u0019j\u0011a\u000e\u0006\u0003q]\ta\u0001\u0010:p_Rt\u0014B\u0001\u001e'\u0003\u0019\u0001&/\u001a3fM&\u0011A(\u0010\u0002\u0006\u00072\f7o\u001d\u0006\u0003u\u0019\u0002\"a\u0010!\r\u0001\u0011I\u0011)AA\u0001\u0002\u0003\u0015\tA\u0011\u0002\u0004?\u0012\u001a\u0014CA\"*!\tQC)\u0003\u0002FM\t9aj\u001c;iS:<\u0017AA3u!\tA\u0015*D\u0001\u001f\u0013\tQeD\u0001\u0005KCZ\fG+\u001f9f\u00031\u0019H/\u0019;jGRK\b/\u001b8h!\tQS*\u0003\u0002OM\t9!i\\8mK\u0006t\u0017a\u0001<ugB\u0011\u0011\u000bV\u0007\u0002%*\u00111KH\u0001\tUN|g\u000e^=qK&\u0011QK\u0015\u0002\u000f)f\u0004XmU3sS\u0006d\u0017N_3s\u0003E)G.Z7f]R\u001cVM]5bY&TXM\u001d\t\u0004\u0011bS\u0016BA-\u001f\u00059Q5o\u001c8TKJL\u0017\r\\5{KJ\u0004\"AK.\n\u0005q3#AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0007?\u0002,gm\u001a5\u0011\u00059\u0002\u0001\"B\u0019\u0007\u0001\u0004\t\u0007G\u00012e!\r!4h\u0019\t\u0003\u007f\u0011$\u0011\"\u00111\u0002\u0002\u0003\u0005)\u0011\u0001\"\t\u000b\u00193\u0001\u0019A$\t\u000b-3\u0001\u0019\u0001'\t\u000b=3\u0001\u0019\u0001)\t\u000bY3\u0001\u0019A,\u0002%%$XM]1u_J\u001cVM]5bY&TXM]\u000b\u0002WB\u0011a\u0006\\\u0005\u0003[.\u0011qcU2bY\u0006LE/\u001a:bi>\u00148+\u001a:jC2L'0\u001a:\u0002'%$XM]1u_J\u001cVM]5bY&TXM\u001d\u0011\u00021};\u0018\u000e\u001e5WC2,X\rV=qKN+'/[1mSj,'\u000f\u0006\u0002`c\")!/\u0003a\u0001!\u00061a.Z<WiN\u0004"
)
public class UnresolvedIteratorSerializer extends AsArraySerializerBase implements IteratorSerializer {
   private final Class cls;
   private final JavaType et;
   private final boolean staticTyping;
   private final JsonSerializer elementSerializer;
   private final ScalaIteratorSerializer iteratorSerializer;

   public boolean hasSingleElement(final Iterator p1) {
      return IteratorSerializer.hasSingleElement$(this, p1);
   }

   public void serializeContents(final Iterator value, final JsonGenerator jgen, final SerializerProvider provider) {
      IteratorSerializer.serializeContents$(this, value, jgen, provider);
   }

   public ResolvedIteratorSerializer withResolved(final BeanProperty property, final TypeSerializer vts, final JsonSerializer elementSerializer, final Boolean unwrapSingle) {
      return IteratorSerializer.withResolved$(this, property, vts, elementSerializer, unwrapSingle);
   }

   public boolean isEmpty(final SerializerProvider serializerProvider, final Iterator value) {
      return IteratorSerializer.isEmpty$(this, serializerProvider, value);
   }

   public ScalaIteratorSerializer iteratorSerializer() {
      return this.iteratorSerializer;
   }

   public UnresolvedIteratorSerializer _withValueTypeSerializer(final TypeSerializer newVts) {
      return new UnresolvedIteratorSerializer(this.cls, this.et, this.staticTyping, newVts, this.elementSerializer);
   }

   public UnresolvedIteratorSerializer(final Class cls, final JavaType et, final boolean staticTyping, final TypeSerializer vts, final JsonSerializer elementSerializer) {
      super(cls, et, staticTyping, vts, elementSerializer);
      this.cls = cls;
      this.et = et;
      this.staticTyping = staticTyping;
      this.elementSerializer = elementSerializer;
      IteratorSerializer.$init$(this);
      this.iteratorSerializer = new ScalaIteratorSerializer(et, staticTyping, vts);
   }
}
