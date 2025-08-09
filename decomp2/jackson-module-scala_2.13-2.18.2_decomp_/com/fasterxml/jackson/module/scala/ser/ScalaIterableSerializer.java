package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Predef.;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tUf\u0001\u0002\u00180\trB\u0001\u0002\u0019\u0001\u0003\u0016\u0004%\t!\u0019\u0005\tM\u0002\u0011\t\u0012)A\u0005E\"Aq\r\u0001BK\u0002\u0013\u0005\u0001\u000e\u0003\u0005m\u0001\tE\t\u0015!\u0003j\u0011!i\u0007A!f\u0001\n\u0003q\u0007\u0002C;\u0001\u0005#\u0005\u000b\u0011B8\t\u0011Y\u0004!Q3A\u0005\u0002]D\u0001b\u001f\u0001\u0003\u0012\u0003\u0006I\u0001\u001f\u0005\ty\u0002\u0011)\u001a!C\u0001{\"I\u00111\u0003\u0001\u0003\u0012\u0003\u0006IA \u0005\u000b\u0003+\u0001!Q3A\u0005\u0002\u0005]\u0001BCA\u000f\u0001\tE\t\u0015!\u0003\u0002\u001a!9\u0011q\u0004\u0001\u0005\u0002\u0005\u0005\u0002bBA\u0010\u0001\u0011\u0005\u00111\u0007\u0005\b\u0003?\u0001A\u0011AA\u001f\u0011\u001d\ty\u0006\u0001C!\u0003CBq!!\u001e\u0001\t\u0003\n9\bC\u0004\u0002|\u0001!\t%! \t\u000f\u0005m\u0005\u0001\"\u0011\u0002\u001e\"9\u0011Q\u0015\u0001\u0005B\u0005\u001d\u0006bBA`\u0001\u0011\u0005\u0013\u0011\u0019\u0005\b\u0003+\u0004A\u0011BAl\u0011%\tI\u000fAA\u0001\n\u0003\tY\u000fC\u0005\u0002z\u0002\t\n\u0011\"\u0001\u0002|\"I!\u0011\u0003\u0001\u0012\u0002\u0013\u0005!1\u0003\u0005\n\u0005/\u0001\u0011\u0013!C\u0001\u00053A\u0011B!\b\u0001#\u0003%\tAa\b\t\u0013\t\r\u0002!%A\u0005\u0002\t\u0015\u0002\"\u0003B\u0015\u0001E\u0005I\u0011\u0001B\u0016\u0011%\u0011y\u0003AA\u0001\n\u0003\u0012\t\u0004C\u0005\u0003:\u0001\t\t\u0011\"\u0001\u0003<!I!1\t\u0001\u0002\u0002\u0013\u0005!Q\t\u0005\n\u0005\u0017\u0002\u0011\u0011!C!\u0005\u001bB\u0011B!\u0016\u0001\u0003\u0003%\tAa\u0016\t\u0013\tm\u0003!!A\u0005B\tu\u0003\"\u0003B1\u0001\u0005\u0005I\u0011\tB2\u0011%\u0011)\u0007AA\u0001\n\u0003\u00129\u0007C\u0005\u0003j\u0001\t\t\u0011\"\u0011\u0003l\u001dI!qN\u0018\u0002\u0002#%!\u0011\u000f\u0004\t]=\n\t\u0011#\u0003\u0003t!9\u0011q\u0004\u0015\u0005\u0002\t-\u0005\"\u0003B3Q\u0005\u0005IQ\tB4\u0011%\u0011i\tKA\u0001\n\u0003\u0013y\tC\u0005\u0003\u001e\"\n\t\u0011\"!\u0003 \"I!\u0011\u0017\u0015\u0002\u0002\u0013%!1\u0017\u0002\u0018'\u000e\fG.Y%uKJ\f'\r\\3TKJL\u0017\r\\5{KJT!\u0001M\u0019\u0002\u0007M,'O\u0003\u00023g\u0005)1oY1mC*\u0011A'N\u0001\u0007[>$W\u000f\\3\u000b\u0005Y:\u0014a\u00026bG.\u001cxN\u001c\u0006\u0003qe\n\u0011BZ1ti\u0016\u0014\b0\u001c7\u000b\u0003i\n1aY8n\u0007\u0001\u0019B\u0001A\u001fR)B\u0019a\b\u0012$\u000e\u0003}R!\u0001Q!\u0002\u0007M$HM\u0003\u00021\u0005*\u00111)N\u0001\tI\u0006$\u0018MY5oI&\u0011Qi\u0010\u0002\u0016\u0003N\f%O]1z'\u0016\u0014\u0018.\u00197ju\u0016\u0014()Y:f!\r95*T\u0007\u0002\u0011*\u0011\u0011JS\u0001\u000bG>dG.Z2uS>t'\"\u0001\u001a\n\u00051C%\u0001C%uKJ\f'\r\\3\u0011\u00059{U\"\u0001&\n\u0005AS%aA!osB\u0011aJU\u0005\u0003'*\u0013q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002V;:\u0011ak\u0017\b\u0003/jk\u0011\u0001\u0017\u0006\u00033n\na\u0001\u0010:p_Rt\u0014\"\u0001\u001a\n\u0005qS\u0015a\u00029bG.\fw-Z\u0005\u0003=~\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\u0018&\u0002\u0011\u0015dW-\u001c+za\u0016,\u0012A\u0019\t\u0003G\u0012l\u0011AQ\u0005\u0003K\n\u0013\u0001BS1wCRK\b/Z\u0001\nK2,W\u000eV=qK\u0002\nAb\u001d;bi&\u001cG+\u001f9j]\u001e,\u0012!\u001b\t\u0003\u001d*L!a\u001b&\u0003\u000f\t{w\u000e\\3b]\u0006i1\u000f^1uS\u000e$\u0016\u0010]5oO\u0002\n1A\u001e;t+\u0005y\u0007C\u00019t\u001b\u0005\t(B\u0001:C\u0003!Q7o\u001c8usB,\u0017B\u0001;r\u00059!\u0016\u0010]3TKJL\u0017\r\\5{KJ\fAA\u001e;tA\u0005A\u0001O]8qKJ$\u00180F\u0001y!\t\u0019\u00170\u0003\u0002{\u0005\na!)Z1o!J|\u0007/\u001a:us\u0006I\u0001O]8qKJ$\u0018\u0010I\u0001\u0010m\u0006dW/Z*fe&\fG.\u001b>feV\ta\u0010\u0005\u0003d\u007f\u0006\r\u0011bAA\u0001\u0005\nq!j]8o'\u0016\u0014\u0018.\u00197ju\u0016\u0014\b\u0003BA\u0003\u0003\u001fi!!a\u0002\u000b\t\u0005%\u00111B\u0001\u0005Y\u0006twM\u0003\u0002\u0002\u000e\u0005!!.\u0019<b\u0013\u0011\t\t\"a\u0002\u0003\r=\u0013'.Z2u\u0003A1\u0018\r\\;f'\u0016\u0014\u0018.\u00197ju\u0016\u0014\b%\u0001\u0007v]^\u0014\u0018\r]*j]\u001edW-\u0006\u0002\u0002\u001aA!\u0011QAA\u000e\u0013\rY\u0017qA\u0001\u000ek:<(/\u00199TS:<G.\u001a\u0011\u0002\rqJg.\u001b;?)9\t\u0019#a\n\u0002*\u0005-\u0012QFA\u0018\u0003c\u00012!!\n\u0001\u001b\u0005y\u0003\"\u00021\u000e\u0001\u0004\u0011\u0007\"B4\u000e\u0001\u0004I\u0007\"B7\u000e\u0001\u0004y\u0007\"\u0002<\u000e\u0001\u0004A\b\"\u0002?\u000e\u0001\u0004q\bbBA\u000b\u001b\u0001\u0007\u0011\u0011\u0004\u000b\u000b\u0003G\t)$a\u000e\u0002:\u0005m\u0002\"\u00021\u000f\u0001\u0004\u0011\u0007\"B4\u000f\u0001\u0004I\u0007\"B7\u000f\u0001\u0004y\u0007\"\u0002?\u000f\u0001\u0004qH\u0003DA\u0012\u0003\u007f\t\u0019%!\u0012\u0002H\u0005u\u0003bBA!\u001f\u0001\u0007\u00111E\u0001\u0004gJ\u001c\u0007\"\u0002<\u0010\u0001\u0004A\b\"B7\u0010\u0001\u0004y\u0007B\u0002?\u0010\u0001\u0004\tI\u0005\r\u0003\u0002L\u0005E\u0003\u0003B2\u0000\u0003\u001b\u0002B!a\u0014\u0002R1\u0001A\u0001DA*\u0003\u000f\n\t\u0011!A\u0003\u0002\u0005U#aA0%cE\u0019\u0011qK'\u0011\u00079\u000bI&C\u0002\u0002\\)\u0013qAT8uQ&tw\rC\u0004\u0002\u0016=\u0001\r!!\u0007\u0002\u000f%\u001cX)\u001c9usR)\u0011.a\u0019\u0002n!9\u0011Q\r\tA\u0002\u0005\u001d\u0014\u0001\u00029s_Z\u00042aYA5\u0013\r\tYG\u0011\u0002\u0013'\u0016\u0014\u0018.\u00197ju\u0016\u0014\bK]8wS\u0012,'\u000fC\u0004\u0002pA\u0001\r!!\u001d\u0002\u000bY\fG.^3\u0011\tU\u000b\u0019(T\u0005\u0003\u0019~\u000b\u0001\u0003[1t'&tw\r\\3FY\u0016lWM\u001c;\u0015\u0007%\fI\bC\u0004\u0002pE\u0001\r!!\u001d\u0002\u0013M,'/[1mSj,G\u0003CA@\u0003\u000b\u000b9)a&\u0011\u00079\u000b\t)C\u0002\u0002\u0004*\u0013A!\u00168ji\"9\u0011q\u000e\nA\u0002\u0005E\u0004bBAE%\u0001\u0007\u00111R\u0001\u0002OB!\u0011QRAJ\u001b\t\tyIC\u0002\u0002\u0012V\nAaY8sK&!\u0011QSAH\u00055Q5o\u001c8HK:,'/\u0019;pe\"9\u0011\u0011\u0014\nA\u0002\u0005\u001d\u0014\u0001\u00039s_ZLG-\u001a:\u0002#M,'/[1mSj,7i\u001c8uK:$8\u000f\u0006\u0005\u0002\u0000\u0005}\u0015\u0011UAR\u0011\u001d\tyg\u0005a\u0001\u0003cBq!!#\u0014\u0001\u0004\tY\tC\u0004\u0002\u001aN\u0001\r!a\u001a\u0002\u0019]LG\u000f\u001b*fg>dg/\u001a3\u0015\u0015\u0005%\u00161VAW\u0003_\u000bi\f\u0005\u0003?\t\u0006E\u0004\"\u0002<\u0015\u0001\u0004A\b\"B7\u0015\u0001\u0004y\u0007bBAY)\u0001\u0007\u00111W\u0001\u0012K2,W.\u001a8u'\u0016\u0014\u0018.\u00197ju\u0016\u0014\b\u0007BA[\u0003s\u0003BaY@\u00028B!\u0011qJA]\t1\tY,a,\u0002\u0002\u0003\u0005)\u0011AA+\u0005\ryFE\r\u0005\b\u0003+!\u0002\u0019AA\r\u0003ayv/\u001b;i-\u0006dW/\u001a+za\u0016\u001cVM]5bY&TXM\u001d\u000b\u0005\u0003\u0007\f\u0019\u000e\r\u0003\u0002F\u0006=\u0007CBAd\u0003\u0013\fi-D\u0001B\u0013\r\tY-\u0011\u0002\u0014\u0007>tG/Y5oKJ\u001cVM]5bY&TXM\u001d\t\u0005\u0003\u001f\ny\rB\u0006\u0002RV\t\t\u0011!A\u0003\u0002\u0005U#aA0%g!)Q.\u0006a\u0001_\u000612/\u001a:jC2L'0Z\"p]R,g\u000e^:Vg&tw\r\u0006\u0006\u0002\u0000\u0005e\u00171\\Ao\u0003?Dq!a\u001c\u0017\u0001\u0004\t\t\bC\u0004\u0002\nZ\u0001\r!a#\t\u000f\u0005ee\u00031\u0001\u0002h!1\u0001G\u0006a\u0001\u0003C\u0004BaY@\u0002dB\u0019a*!:\n\u0007\u0005\u001d(J\u0001\u0004B]f\u0014VMZ\u0001\u0005G>\u0004\u0018\u0010\u0006\b\u0002$\u00055\u0018q^Ay\u0003g\f)0a>\t\u000f\u0001<\u0002\u0013!a\u0001E\"9qm\u0006I\u0001\u0002\u0004I\u0007bB7\u0018!\u0003\u0005\ra\u001c\u0005\bm^\u0001\n\u00111\u0001y\u0011\u001dax\u0003%AA\u0002yD\u0011\"!\u0006\u0018!\u0003\u0005\r!!\u0007\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011Q \u0016\u0004E\u0006}8F\u0001B\u0001!\u0011\u0011\u0019A!\u0004\u000e\u0005\t\u0015!\u0002\u0002B\u0004\u0005\u0013\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\t-!*\u0001\u0006b]:|G/\u0019;j_:LAAa\u0004\u0003\u0006\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011!Q\u0003\u0016\u0004S\u0006}\u0018AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u00057Q3a\\A\u0000\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ*\"A!\t+\u0007a\fy0\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001b\u0016\u0005\t\u001d\"f\u0001@\u0002\u0000\u0006q1m\u001c9zI\u0011,g-Y;mi\u00122TC\u0001B\u0017U\u0011\tI\"a@\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\u0011\u0019\u0004\u0005\u0003\u0002\u0006\tU\u0012\u0002\u0002B\u001c\u0003\u000f\u0011aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLXC\u0001B\u001f!\rq%qH\u0005\u0004\u0005\u0003R%aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HcA'\u0003H!I!\u0011\n\u0011\u0002\u0002\u0003\u0007!QH\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\t=\u0003\u0003B$\u0003R5K1Aa\u0015I\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0007%\u0014I\u0006\u0003\u0005\u0003J\t\n\t\u00111\u0001N\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\tM\"q\f\u0005\n\u0005\u0013\u001a\u0013\u0011!a\u0001\u0005{\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0005{\t\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0005g\ta!Z9vC2\u001cHcA5\u0003n!A!\u0011\n\u0014\u0002\u0002\u0003\u0007Q*A\fTG\u0006d\u0017-\u0013;fe\u0006\u0014G.Z*fe&\fG.\u001b>feB\u0019\u0011Q\u0005\u0015\u0014\u000b!\u0012)H!!\u0011\u001b\t]$Q\u00102j_bt\u0018\u0011DA\u0012\u001b\t\u0011IHC\u0002\u0003|)\u000bqA];oi&lW-\u0003\u0003\u0003\u0000\te$!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8omA!!1\u0011BE\u001b\t\u0011)I\u0003\u0003\u0003\b\u0006-\u0011AA5p\u0013\rq&Q\u0011\u000b\u0003\u0005c\nQ!\u00199qYf$b\"a\t\u0003\u0012\nM%Q\u0013BL\u00053\u0013Y\nC\u0003aW\u0001\u0007!\rC\u0003hW\u0001\u0007\u0011\u000eC\u0003nW\u0001\u0007q\u000eC\u0003wW\u0001\u0007\u0001\u0010C\u0003}W\u0001\u0007a\u0010C\u0004\u0002\u0016-\u0002\r!!\u0007\u0002\u000fUt\u0017\r\u001d9msR!!\u0011\u0015BW!\u0015q%1\u0015BT\u0013\r\u0011)K\u0013\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u00159\u0013IKY5pqz\fI\"C\u0002\u0003,*\u0013a\u0001V;qY\u00164\u0004\"\u0003BXY\u0005\u0005\t\u0019AA\u0012\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u0007\u0001"
)
public class ScalaIterableSerializer extends AsArraySerializerBase implements Product {
   private final JavaType elemType;
   private final boolean staticTyping;
   private final TypeSerializer vts;
   private final BeanProperty property;
   private final JsonSerializer valueSerializer;
   private final Boolean unwrapSingle;

   public static Option unapply(final ScalaIterableSerializer x$0) {
      return ScalaIterableSerializer$.MODULE$.unapply(x$0);
   }

   public static ScalaIterableSerializer apply(final JavaType elemType, final boolean staticTyping, final TypeSerializer vts, final BeanProperty property, final JsonSerializer valueSerializer, final Boolean unwrapSingle) {
      return ScalaIterableSerializer$.MODULE$.apply(elemType, staticTyping, vts, property, valueSerializer, unwrapSingle);
   }

   public static Function1 tupled() {
      return ScalaIterableSerializer$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ScalaIterableSerializer$.MODULE$.curried();
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

   public boolean isEmpty(final SerializerProvider prov, final Iterable value) {
      return value.isEmpty();
   }

   public boolean hasSingleElement(final Iterable value) {
      return value.size() == 1;
   }

   public void serialize(final Iterable value, final JsonGenerator g, final SerializerProvider provider) {
      if ((this._unwrapSingle == null && provider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) || .MODULE$.Boolean2boolean(this._unwrapSingle)) && this.hasSingleElement(value)) {
         this.serializeContents(value, g, provider);
      } else {
         g.writeStartArray(value);
         this.serializeContents(value, g, provider);
         g.writeEndArray();
      }
   }

   public void serializeContents(final Iterable value, final JsonGenerator g, final SerializerProvider provider) {
      g.setCurrentValue(value);
      if (this._elementSerializer != null) {
         this.serializeContentsUsing(value, g, provider, this._elementSerializer);
      } else {
         Iterator it = value.iterator();
         if (it.hasNext()) {
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
            } catch (Throwable var15) {
               if (var15 == null || !scala.util.control.NonFatal..MODULE$.apply(var15)) {
                  throw var15;
               }

               this.wrapAndThrow(provider, var15, value, i);
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

         }
      }
   }

   public AsArraySerializerBase withResolved(final BeanProperty property, final TypeSerializer vts, final JsonSerializer elementSerializer, final Boolean unwrapSingle) {
      return new ScalaIterableSerializer(this, property, vts, elementSerializer, unwrapSingle);
   }

   public ContainerSerializer _withValueTypeSerializer(final TypeSerializer vts) {
      return new ScalaIterableSerializer(this, this._property, vts, this._elementSerializer, this._unwrapSingle);
   }

   private void serializeContentsUsing(final Iterable value, final JsonGenerator g, final SerializerProvider provider, final JsonSerializer ser) {
      Iterator it = value.iterator();
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
            } catch (Throwable var13) {
               if (var13 == null || !scala.util.control.NonFatal..MODULE$.apply(var13)) {
                  throw var13;
               }

               this.wrapAndThrow(provider, var13, value, i);
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         }

      }
   }

   public ScalaIterableSerializer copy(final JavaType elemType, final boolean staticTyping, final TypeSerializer vts, final BeanProperty property, final JsonSerializer valueSerializer, final Boolean unwrapSingle) {
      return new ScalaIterableSerializer(elemType, staticTyping, vts, property, valueSerializer, unwrapSingle);
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
      return "ScalaIterableSerializer";
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
      return x$1 instanceof ScalaIterableSerializer;
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
            if (x$1 instanceof ScalaIterableSerializer) {
               ScalaIterableSerializer var4 = (ScalaIterableSerializer)x$1;
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

   public ScalaIterableSerializer(final JavaType elemType, final boolean staticTyping, final TypeSerializer vts, final BeanProperty property, final JsonSerializer valueSerializer, final Boolean unwrapSingle) {
      super(scala.collection.Iterable..MODULE$.getClass(), elemType, staticTyping, vts, property, valueSerializer, unwrapSingle);
      this.elemType = elemType;
      this.staticTyping = staticTyping;
      this.vts = vts;
      this.property = property;
      this.valueSerializer = valueSerializer;
      this.unwrapSingle = unwrapSingle;
      Product.$init$(this);
   }

   public ScalaIterableSerializer(final JavaType elemType, final boolean staticTyping, final TypeSerializer vts, final JsonSerializer valueSerializer) {
      this(elemType, staticTyping, vts, (BeanProperty)scala.None..MODULE$.orNull(scala..less.colon.less..MODULE$.refl()), valueSerializer, (Boolean)scala.None..MODULE$.orNull(scala..less.colon.less..MODULE$.refl()));
   }

   public ScalaIterableSerializer(final ScalaIterableSerializer src, final BeanProperty property, final TypeSerializer vts, final JsonSerializer valueSerializer, final Boolean unwrapSingle) {
      this(src.elemType(), src.staticTyping(), vts, property, valueSerializer, unwrapSingle);
   }
}
