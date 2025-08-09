package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.util.control.NonFatal.;

@ScalaSignature(
   bytes = "\u0006\u0005\tmf\u0001B\u00181\tvB\u0001\"\u0019\u0001\u0003\u0016\u0004%\tA\u0019\u0005\tO\u0002\u0011\t\u0012)A\u0005G\"A\u0001\u000e\u0001BK\u0002\u0013\u0005\u0011\u000e\u0003\u0005n\u0001\tE\t\u0015!\u0003k\u0011!q\u0007A!f\u0001\n\u0003y\u0007\u0002\u0003<\u0001\u0005#\u0005\u000b\u0011\u00029\t\u0011]\u0004!Q3A\u0005\u0002aD\u0001\u0002 \u0001\u0003\u0012\u0003\u0006I!\u001f\u0005\t{\u0002\u0011)\u001a!C\u0001}\"I\u0011Q\u0003\u0001\u0003\u0012\u0003\u0006Ia \u0005\u000b\u0003/\u0001!Q3A\u0005\u0002\u0005e\u0001BCA\u0010\u0001\tE\t\u0015!\u0003\u0002\u001c!9\u0011\u0011\u0005\u0001\u0005\u0002\u0005\r\u0002bBA\u0011\u0001\u0011\u0005\u0011Q\u0007\u0005\b\u0003C\u0001A\u0011AA\u001f\u0011\u001d\t\t\u0003\u0001C\u0001\u0003\u000fBq!!\u001b\u0001\t\u0003\nY\u0007C\u0004\u0002\u0000\u0001!\t%!!\t\u000f\u0005\u0015\u0005\u0001\"\u0011\u0002\b\"9\u0011Q\u0015\u0001\u0005B\u0005\u001d\u0006bBAY\u0001\u0011\u0005\u00131\u0017\u0005\b\u0003\u0017\u0004A\u0011IAg\u0011\u001d\t\t\u000f\u0001C\u0005\u0003GD\u0011\"!>\u0001\u0003\u0003%\t!a>\t\u0013\t\u0015\u0001!%A\u0005\u0002\t\u001d\u0001\"\u0003B\u000f\u0001E\u0005I\u0011\u0001B\u0010\u0011%\u0011\u0019\u0003AI\u0001\n\u0003\u0011)\u0003C\u0005\u0003*\u0001\t\n\u0011\"\u0001\u0003,!I!q\u0006\u0001\u0012\u0002\u0013\u0005!\u0011\u0007\u0005\n\u0005k\u0001\u0011\u0013!C\u0001\u0005oA\u0011Ba\u000f\u0001\u0003\u0003%\tE!\u0010\t\u0013\t\u0015\u0003!!A\u0005\u0002\t\u001d\u0003\"\u0003B(\u0001\u0005\u0005I\u0011\u0001B)\u0011%\u00119\u0006AA\u0001\n\u0003\u0012I\u0006C\u0005\u0003\\\u0001\t\t\u0011\"\u0001\u0003^!I!\u0011\r\u0001\u0002\u0002\u0013\u0005#1\r\u0005\n\u0005O\u0002\u0011\u0011!C!\u0005SB\u0011Ba\u001b\u0001\u0003\u0003%\tE!\u001c\t\u0013\t=\u0004!!A\u0005B\tEt!\u0003B;a\u0005\u0005\t\u0012\u0002B<\r!y\u0003'!A\t\n\te\u0004bBA\u0011S\u0011\u0005!\u0011\u0013\u0005\n\u0005WJ\u0013\u0011!C#\u0005[B\u0011Ba%*\u0003\u0003%\tI!&\t\u0013\t\r\u0016&!A\u0005\u0002\n\u0015\u0006\"\u0003B\\S\u0005\u0005I\u0011\u0002B]\u0005]\u00196-\u00197b\u0013R,'/\u0019;peN+'/[1mSj,'O\u0003\u00022e\u0005\u00191/\u001a:\u000b\u0005M\"\u0014!B:dC2\f'BA\u001b7\u0003\u0019iw\u000eZ;mK*\u0011q\u0007O\u0001\bU\u0006\u001c7n]8o\u0015\tI$(A\u0005gCN$XM\u001d=nY*\t1(A\u0002d_6\u001c\u0001a\u0005\u0003\u0001}I+\u0006cA F\u000f6\t\u0001I\u0003\u0002B\u0005\u0006\u00191\u000f\u001e3\u000b\u0005E\u001a%B\u0001#7\u0003!!\u0017\r^1cS:$\u0017B\u0001$A\u0005U\t5/\u0011:sCf\u001cVM]5bY&TXM\u001d\"bg\u0016\u00042\u0001\u0013'O\u001b\u0005I%B\u0001&L\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002g%\u0011Q*\u0013\u0002\t\u0013R,'/\u0019;peB\u0011q\nU\u0007\u0002\u0017&\u0011\u0011k\u0013\u0002\u0004\u0003:L\bCA(T\u0013\t!6JA\u0004Qe>$Wo\u0019;\u0011\u0005YsfBA,]\u001d\tA6,D\u0001Z\u0015\tQF(\u0001\u0004=e>|GOP\u0005\u0002g%\u0011QlS\u0001\ba\u0006\u001c7.Y4f\u0013\ty\u0006M\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002^\u0017\u0006AQ\r\\3n)f\u0004X-F\u0001d!\t!W-D\u0001D\u0013\t17I\u0001\u0005KCZ\fG+\u001f9f\u0003%)G.Z7UsB,\u0007%\u0001\u0007ti\u0006$\u0018n\u0019+za&tw-F\u0001k!\ty5.\u0003\u0002m\u0017\n9!i\\8mK\u0006t\u0017!D:uCRL7\rV=qS:<\u0007%A\u0002wiN,\u0012\u0001\u001d\t\u0003cRl\u0011A\u001d\u0006\u0003g\u000e\u000b\u0001B[:p]RL\b/Z\u0005\u0003kJ\u0014a\u0002V=qKN+'/[1mSj,'/\u0001\u0003wiN\u0004\u0013\u0001\u00039s_B,'\u000f^=\u0016\u0003e\u0004\"\u0001\u001a>\n\u0005m\u001c%\u0001\u0004\"fC:\u0004&o\u001c9feRL\u0018!\u00039s_B,'\u000f^=!\u0003=1\u0018\r\\;f'\u0016\u0014\u0018.\u00197ju\u0016\u0014X#A@\u0011\u000b\u0011\f\t!!\u0002\n\u0007\u0005\r1I\u0001\bKg>t7+\u001a:jC2L'0\u001a:\u0011\t\u0005\u001d\u0011\u0011C\u0007\u0003\u0003\u0013QA!a\u0003\u0002\u000e\u0005!A.\u00198h\u0015\t\ty!\u0001\u0003kCZ\f\u0017\u0002BA\n\u0003\u0013\u0011aa\u00142kK\u000e$\u0018\u0001\u0005<bYV,7+\u001a:jC2L'0\u001a:!\u00031)hn\u001e:baNKgn\u001a7f+\t\tY\u0002\u0005\u0003\u0002\b\u0005u\u0011b\u00017\u0002\n\u0005iQO\\<sCB\u001c\u0016N\\4mK\u0002\na\u0001P5oSRtDCDA\u0013\u0003S\tY#!\f\u00020\u0005E\u00121\u0007\t\u0004\u0003O\u0001Q\"\u0001\u0019\t\u000b\u0005l\u0001\u0019A2\t\u000b!l\u0001\u0019\u00016\t\u000b9l\u0001\u0019\u00019\t\u000b]l\u0001\u0019A=\t\u000bul\u0001\u0019A@\t\u000f\u0005]Q\u00021\u0001\u0002\u001cQA\u0011QEA\u001c\u0003s\tY\u0004C\u0003b\u001d\u0001\u00071\rC\u0003i\u001d\u0001\u0007!\u000eC\u0003o\u001d\u0001\u0007\u0001\u000f\u0006\u0006\u0002&\u0005}\u0012\u0011IA\"\u0003\u000bBQ!Y\bA\u0002\rDQ\u0001[\bA\u0002)DQA\\\bA\u0002ADQ!`\bA\u0002}$B\"!\n\u0002J\u00055\u0013qJA)\u0003OBq!a\u0013\u0011\u0001\u0004\t)#A\u0002te\u000eDQa\u001e\tA\u0002eDQA\u001c\tA\u0002ADa! \tA\u0002\u0005M\u0003\u0007BA+\u00037\u0002R\u0001ZA\u0001\u0003/\u0002B!!\u0017\u0002\\1\u0001A\u0001DA/\u0003#\n\t\u0011!A\u0003\u0002\u0005}#aA0%cE\u0019\u0011\u0011\r(\u0011\u0007=\u000b\u0019'C\u0002\u0002f-\u0013qAT8uQ&tw\rC\u0004\u0002\u0018A\u0001\r!a\u0007\u0002\u000f%\u001cX)\u001c9usR)!.!\u001c\u0002x!9\u0011qN\tA\u0002\u0005E\u0014\u0001\u00029s_Z\u00042\u0001ZA:\u0013\r\t)h\u0011\u0002\u0013'\u0016\u0014\u0018.\u00197ju\u0016\u0014\bK]8wS\u0012,'\u000fC\u0004\u0002zE\u0001\r!a\u001f\u0002\u000bY\fG.^3\u0011\tY\u000biHT\u0005\u0003\u001b\u0002\f\u0001\u0003[1t'&tw\r\\3FY\u0016lWM\u001c;\u0015\u0007)\f\u0019\tC\u0004\u0002zI\u0001\r!a\u001f\u0002\u0013M,'/[1mSj,G\u0003CAE\u0003\u001f\u000b\t*!)\u0011\u0007=\u000bY)C\u0002\u0002\u000e.\u0013A!\u00168ji\"9\u0011\u0011P\nA\u0002\u0005m\u0004bBAJ'\u0001\u0007\u0011QS\u0001\u0002OB!\u0011qSAO\u001b\t\tIJC\u0002\u0002\u001cZ\nAaY8sK&!\u0011qTAM\u00055Q5o\u001c8HK:,'/\u0019;pe\"9\u00111U\nA\u0002\u0005E\u0014\u0001\u00039s_ZLG-\u001a:\u0002#M,'/[1mSj,7i\u001c8uK:$8\u000f\u0006\u0005\u0002\n\u0006%\u0016QVAX\u0011\u001d\tY\u000b\u0006a\u0001\u0003w\n!!\u001b;\t\u000f\u0005ME\u00031\u0001\u0002\u0016\"9\u00111\u0015\u000bA\u0002\u0005E\u0014\u0001D<ji\"\u0014Vm]8mm\u0016$GCCA[\u0003o\u000bI,a/\u0002JB!q(RA>\u0011\u00159X\u00031\u0001z\u0011\u0015qW\u00031\u0001q\u0011\u001d\ti,\u0006a\u0001\u0003\u007f\u000b\u0011#\u001a7f[\u0016tGoU3sS\u0006d\u0017N_3sa\u0011\t\t-!2\u0011\u000b\u0011\f\t!a1\u0011\t\u0005e\u0013Q\u0019\u0003\r\u0003\u000f\fY,!A\u0001\u0002\u000b\u0005\u0011q\f\u0002\u0004?\u0012\u0012\u0004bBA\f+\u0001\u0007\u00111D\u0001\u0019?^LG\u000f\u001b,bYV,G+\u001f9f'\u0016\u0014\u0018.\u00197ju\u0016\u0014H\u0003BAh\u0003?\u0004D!!5\u0002\\B1\u00111[Ak\u00033l\u0011AQ\u0005\u0004\u0003/\u0014%aE\"p]R\f\u0017N\\3s'\u0016\u0014\u0018.\u00197ju\u0016\u0014\b\u0003BA-\u00037$1\"!8\u0017\u0003\u0003\u0005\tQ!\u0001\u0002`\t\u0019q\fJ\u001a\t\u000b94\u0002\u0019\u00019\u0002-M,'/[1mSj,7i\u001c8uK:$8/V:j]\u001e$\"\"!#\u0002f\u0006\u001d\u0018\u0011^Av\u0011\u001d\tYk\u0006a\u0001\u0003wBq!a%\u0018\u0001\u0004\t)\nC\u0004\u0002$^\u0001\r!!\u001d\t\rE:\u0002\u0019AAw!\u0015!\u0017\u0011AAx!\ry\u0015\u0011_\u0005\u0004\u0003g\\%AB!osJ+g-\u0001\u0003d_BLHCDA\u0013\u0003s\fY0!@\u0002\u0000\n\u0005!1\u0001\u0005\bCb\u0001\n\u00111\u0001d\u0011\u001dA\u0007\u0004%AA\u0002)DqA\u001c\r\u0011\u0002\u0003\u0007\u0001\u000fC\u0004x1A\u0005\t\u0019A=\t\u000fuD\u0002\u0013!a\u0001\u007f\"I\u0011q\u0003\r\u0011\u0002\u0003\u0007\u00111D\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\u0011IAK\u0002d\u0005\u0017Y#A!\u0004\u0011\t\t=!\u0011D\u0007\u0003\u0005#QAAa\u0005\u0003\u0016\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0005/Y\u0015AC1o]>$\u0018\r^5p]&!!1\u0004B\t\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\u0011\tCK\u0002k\u0005\u0017\tabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0003()\u001a\u0001Oa\u0003\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%iU\u0011!Q\u0006\u0016\u0004s\n-\u0011AD2paf$C-\u001a4bk2$H%N\u000b\u0003\u0005gQ3a B\u0006\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIY*\"A!\u000f+\t\u0005m!1B\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\t}\u0002\u0003BA\u0004\u0005\u0003JAAa\u0011\u0002\n\t11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"A!\u0013\u0011\u0007=\u0013Y%C\u0002\u0003N-\u00131!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$2A\u0014B*\u0011%\u0011)&IA\u0001\u0002\u0004\u0011I%A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002\u000f\u0006A1-\u00198FcV\fG\u000eF\u0002k\u0005?B\u0001B!\u0016$\u0003\u0003\u0005\rAT\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0003@\t\u0015\u0004\"\u0003B+I\u0005\u0005\t\u0019\u0001B%\u0003!A\u0017m\u001d5D_\u0012,GC\u0001B%\u0003!!xn\u0015;sS:<GC\u0001B \u0003\u0019)\u0017/^1mgR\u0019!Na\u001d\t\u0011\tUs%!AA\u00029\u000bqcU2bY\u0006LE/\u001a:bi>\u00148+\u001a:jC2L'0\u001a:\u0011\u0007\u0005\u001d\u0012fE\u0003*\u0005w\u00129\tE\u0007\u0003~\t\r5M\u001b9z\u007f\u0006m\u0011QE\u0007\u0003\u0005\u007fR1A!!L\u0003\u001d\u0011XO\u001c;j[\u0016LAA!\"\u0003\u0000\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001c\u0011\t\t%%qR\u0007\u0003\u0005\u0017SAA!$\u0002\u000e\u0005\u0011\u0011n\\\u0005\u0004?\n-EC\u0001B<\u0003\u0015\t\u0007\u000f\u001d7z)9\t)Ca&\u0003\u001a\nm%Q\u0014BP\u0005CCQ!\u0019\u0017A\u0002\rDQ\u0001\u001b\u0017A\u0002)DQA\u001c\u0017A\u0002ADQa\u001e\u0017A\u0002eDQ! \u0017A\u0002}Dq!a\u0006-\u0001\u0004\tY\"A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\t\u001d&1\u0017\t\u0006\u001f\n%&QV\u0005\u0004\u0005W[%AB(qi&|g\u000e\u0005\u0006P\u0005_\u001b'\u000e]=\u0000\u00037I1A!-L\u0005\u0019!V\u000f\u001d7fm!I!QW\u0017\u0002\u0002\u0003\u0007\u0011QE\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA\u0003\u0001"
)
public class ScalaIteratorSerializer extends AsArraySerializerBase implements Product {
   private final JavaType elemType;
   private final boolean staticTyping;
   private final TypeSerializer vts;
   private final BeanProperty property;
   private final JsonSerializer valueSerializer;
   private final Boolean unwrapSingle;

   public static Option unapply(final ScalaIteratorSerializer x$0) {
      return ScalaIteratorSerializer$.MODULE$.unapply(x$0);
   }

   public static ScalaIteratorSerializer apply(final JavaType elemType, final boolean staticTyping, final TypeSerializer vts, final BeanProperty property, final JsonSerializer valueSerializer, final Boolean unwrapSingle) {
      return ScalaIteratorSerializer$.MODULE$.apply(elemType, staticTyping, vts, property, valueSerializer, unwrapSingle);
   }

   public static Function1 tupled() {
      return ScalaIteratorSerializer$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ScalaIteratorSerializer$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public JavaType elemType() {
      return this.elemType;
   }

   public boolean staticTyping() {
      return this.staticTyping;
   }

   public TypeSerializer vts() {
      return this.vts;
   }

   public BeanProperty property() {
      return this.property;
   }

   public JsonSerializer valueSerializer() {
      return this.valueSerializer;
   }

   public Boolean unwrapSingle() {
      return this.unwrapSingle;
   }

   public boolean isEmpty(final SerializerProvider prov, final Iterator value) {
      return value.isEmpty();
   }

   public boolean hasSingleElement(final Iterator value) {
      return value.size() == 1;
   }

   public void serialize(final Iterator value, final JsonGenerator g, final SerializerProvider provider) {
      g.writeStartArray(value);
      this.serializeContents(value, g, provider);
      g.writeEndArray();
   }

   public void serializeContents(final Iterator it, final JsonGenerator g, final SerializerProvider provider) {
      g.setCurrentValue(it);
      if (this._elementSerializer != null) {
         this.serializeContentsUsing(it, g, provider, this._elementSerializer);
      } else if (it.hasNext()) {
         TypeSerializer typeSer = this._valueTypeSerializer;
         PropertySerializerMap serializers = this._dynamicSerializers;
         int i = 0;

         try {
            for(; it.hasNext(); ++i) {
               Object elem = it.next();
               if (elem == null) {
                  provider.defaultSerializeNull(g);
               } else {
                  Class cc = elem.getClass();
                  JsonSerializer serializer = serializers.serializerFor(cc);
                  if (serializer == null) {
                     if (this._elementType.hasGenericTypes()) {
                        serializer = this._findAndAddDynamic(serializers, provider.constructSpecializedType(this._elementType, cc), provider);
                     } else {
                        serializer = this._findAndAddDynamic(serializers, cc, provider);
                     }

                     serializers = this._dynamicSerializers;
                  }

                  if (typeSer == null) {
                     serializer.serialize(elem, g, provider);
                  } else {
                     serializer.serializeWithType(elem, g, provider, typeSer);
                  }
               }
            }
         } catch (Throwable var14) {
            if (var14 == null || !.MODULE$.apply(var14)) {
               throw var14;
            }

            this.wrapAndThrow(provider, var14, it, i);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

      }
   }

   public AsArraySerializerBase withResolved(final BeanProperty property, final TypeSerializer vts, final JsonSerializer elementSerializer, final Boolean unwrapSingle) {
      return new ScalaIteratorSerializer(this, property, vts, elementSerializer, unwrapSingle);
   }

   public ContainerSerializer _withValueTypeSerializer(final TypeSerializer vts) {
      return new ScalaIteratorSerializer(this, this._property, vts, this._elementSerializer, this._unwrapSingle);
   }

   private void serializeContentsUsing(final Iterator it, final JsonGenerator g, final SerializerProvider provider, final JsonSerializer ser) {
      if (it.hasNext()) {
         TypeSerializer typeSer = this._valueTypeSerializer;
         int i = 0;

         while(it.hasNext()) {
            Object elem = it.next();

            try {
               if (elem == null) {
                  provider.defaultSerializeNull(g);
               } else if (typeSer == null) {
                  ser.serialize(elem, g, provider);
               } else {
                  ser.serializeWithType(elem, g, provider, typeSer);
               }

               ++i;
            } catch (Throwable var12) {
               if (var12 == null || !.MODULE$.apply(var12)) {
                  throw var12;
               }

               this.wrapAndThrow(provider, var12, it, i);
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         }

      }
   }

   public ScalaIteratorSerializer copy(final JavaType elemType, final boolean staticTyping, final TypeSerializer vts, final BeanProperty property, final JsonSerializer valueSerializer, final Boolean unwrapSingle) {
      return new ScalaIteratorSerializer(elemType, staticTyping, vts, property, valueSerializer, unwrapSingle);
   }

   public JavaType copy$default$1() {
      return this.elemType();
   }

   public boolean copy$default$2() {
      return this.staticTyping();
   }

   public TypeSerializer copy$default$3() {
      return this.vts();
   }

   public BeanProperty copy$default$4() {
      return this.property();
   }

   public JsonSerializer copy$default$5() {
      return this.valueSerializer();
   }

   public Boolean copy$default$6() {
      return this.unwrapSingle();
   }

   public String productPrefix() {
      return "ScalaIteratorSerializer";
   }

   public int productArity() {
      return 6;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.elemType();
         case 1:
            return BoxesRunTime.boxToBoolean(this.staticTyping());
         case 2:
            return this.vts();
         case 3:
            return this.property();
         case 4:
            return this.valueSerializer();
         case 5:
            return this.unwrapSingle();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ScalaIteratorSerializer;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "elemType";
         case 1:
            return "staticTyping";
         case 2:
            return "vts";
         case 3:
            return "property";
         case 4:
            return "valueSerializer";
         case 5:
            return "unwrapSingle";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.elemType()));
      var1 = Statics.mix(var1, this.staticTyping() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.anyHash(this.vts()));
      var1 = Statics.mix(var1, Statics.anyHash(this.property()));
      var1 = Statics.mix(var1, Statics.anyHash(this.valueSerializer()));
      var1 = Statics.mix(var1, Statics.anyHash(this.unwrapSingle()));
      return Statics.finalizeHash(var1, 6);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var14;
      if (this != x$1) {
         label83: {
            if (x$1 instanceof ScalaIteratorSerializer) {
               ScalaIteratorSerializer var4 = (ScalaIteratorSerializer)x$1;
               if (this.staticTyping() == var4.staticTyping()) {
                  label76: {
                     JavaType var10000 = this.elemType();
                     JavaType var5 = var4.elemType();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label76;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label76;
                     }

                     TypeSerializer var10 = this.vts();
                     TypeSerializer var6 = var4.vts();
                     if (var10 == null) {
                        if (var6 != null) {
                           break label76;
                        }
                     } else if (!var10.equals(var6)) {
                        break label76;
                     }

                     BeanProperty var11 = this.property();
                     BeanProperty var7 = var4.property();
                     if (var11 == null) {
                        if (var7 != null) {
                           break label76;
                        }
                     } else if (!var11.equals(var7)) {
                        break label76;
                     }

                     JsonSerializer var12 = this.valueSerializer();
                     JsonSerializer var8 = var4.valueSerializer();
                     if (var12 == null) {
                        if (var8 != null) {
                           break label76;
                        }
                     } else if (!var12.equals(var8)) {
                        break label76;
                     }

                     Boolean var13 = this.unwrapSingle();
                     Boolean var9 = var4.unwrapSingle();
                     if (var13 == null) {
                        if (var9 != null) {
                           break label76;
                        }
                     } else if (!var13.equals(var9)) {
                        break label76;
                     }

                     if (var4.canEqual(this)) {
                        break label83;
                     }
                  }
               }
            }

            var14 = false;
            return var14;
         }
      }

      var14 = true;
      return var14;
   }

   public ScalaIteratorSerializer(final JavaType elemType, final boolean staticTyping, final TypeSerializer vts, final BeanProperty property, final JsonSerializer valueSerializer, final Boolean unwrapSingle) {
      super(scala.collection.Iterator..MODULE$.getClass(), elemType, staticTyping, vts, property, valueSerializer, unwrapSingle);
      this.elemType = elemType;
      this.staticTyping = staticTyping;
      this.vts = vts;
      this.property = property;
      this.valueSerializer = valueSerializer;
      this.unwrapSingle = unwrapSingle;
      Product.$init$(this);
   }

   public ScalaIteratorSerializer(final JavaType elemType, final boolean staticTyping, final TypeSerializer vts) {
      this(elemType, staticTyping, vts, (BeanProperty)scala.None..MODULE$.orNull(scala..less.colon.less..MODULE$.refl()), (JsonSerializer)scala.None..MODULE$.orNull(scala..less.colon.less..MODULE$.refl()), (Boolean)scala.None..MODULE$.orNull(scala..less.colon.less..MODULE$.refl()));
   }

   public ScalaIteratorSerializer(final JavaType elemType, final boolean staticTyping, final TypeSerializer vts, final JsonSerializer valueSerializer) {
      this(elemType, staticTyping, vts, (BeanProperty)scala.None..MODULE$.orNull(scala..less.colon.less..MODULE$.refl()), valueSerializer, (Boolean)scala.None..MODULE$.orNull(scala..less.colon.less..MODULE$.refl()));
   }

   public ScalaIteratorSerializer(final ScalaIteratorSerializer src, final BeanProperty property, final TypeSerializer vts, final JsonSerializer valueSerializer, final Boolean unwrapSingle) {
      this(src.elemType(), src.staticTyping(), vts, property, valueSerializer, unwrapSingle);
   }
}
