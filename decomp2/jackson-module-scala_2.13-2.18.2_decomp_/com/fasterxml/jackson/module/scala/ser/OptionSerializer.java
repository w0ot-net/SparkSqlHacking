package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.ReferenceTypeSerializer;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.util.NameTransformer;
import scala.Option;
import scala..less.colon.less.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015w!B\n\u0015\u0011\u0003\tc!B\u0012\u0015\u0011\u0003!\u0003\"\u0002\u001a\u0002\t\u0003\u0019\u0004\"\u0002\u001b\u0002\t\u0003)\u0004\"B(\u0002\t\u0003\u0001\u0006\"B(\u0002\t\u0003\u0001\b\"\u0002;\u0002\t\u0003)\bb\u0002=\u0002\u0003\u0003%I!\u001f\u0004\u0006GQ\u0001\u0011\u0011\u0001\u0005\u000b\u00037A!\u0011!Q\u0001\n\u0005u\u0001\"CA\u0015\u0011\t\u0005\t\u0015!\u00037\u0011)\tY\u0003\u0003B\u0001B\u0003%\u0011Q\u0006\u0005\n\u0003sA!\u0011!Q\u0001\nECaA\r\u0005\u0005\u0002\u0005m\u0002bBA$\u0011\u0011\u0005\u0013\u0011\n\u0005\b\u0003wBA\u0011IA?\u0011\u001d\t\u0019\n\u0003C)\u0003+Cq!!*\t\t#\n9\u000bC\u0004\u00026\"!\t&a.\u0002!=\u0003H/[8o'\u0016\u0014\u0018.\u00197ju\u0016\u0014(BA\u000b\u0017\u0003\r\u0019XM\u001d\u0006\u0003/a\tQa]2bY\u0006T!!\u0007\u000e\u0002\r5|G-\u001e7f\u0015\tYB$A\u0004kC\u000e\\7o\u001c8\u000b\u0005uq\u0012!\u00034bgR,'\u000f_7m\u0015\u0005y\u0012aA2p[\u000e\u0001\u0001C\u0001\u0012\u0002\u001b\u0005!\"\u0001E(qi&|gnU3sS\u0006d\u0017N_3s'\r\tQE\u000b\t\u0003M!j\u0011a\n\u0006\u0002/%\u0011\u0011f\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005-\u0002T\"\u0001\u0017\u000b\u00055r\u0013AA5p\u0015\u0005y\u0013\u0001\u00026bm\u0006L!!\r\u0017\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?)\u0005\t\u0013!C;tKN#\u0018\r^5d)\u00111\u0014(Q%\u0011\u0005\u0019:\u0014B\u0001\u001d(\u0005\u001d\u0011un\u001c7fC:DQAO\u0002A\u0002m\n\u0001\u0002\u001d:pm&$WM\u001d\t\u0003y}j\u0011!\u0010\u0006\u0003}i\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003\u0001v\u0012!cU3sS\u0006d\u0017N_3s!J|g/\u001b3fe\")!i\u0001a\u0001\u0007\u0006A\u0001O]8qKJ$\u0018\u0010E\u0002'\t\u001aK!!R\u0014\u0003\r=\u0003H/[8o!\tat)\u0003\u0002I{\ta!)Z1o!J|\u0007/\u001a:us\")!j\u0001a\u0001\u0017\u0006a!/\u001a4feJ,G\rV=qKB\u0019a\u0005\u0012'\u0011\u0005qj\u0015B\u0001(>\u0005!Q\u0015M^1UsB,\u0017A\u00044j]\u0012\u001cVM]5bY&TXM\u001d\u000b\u0005#R+f\u000eE\u0002=%\u0016J!aU\u001f\u0003\u001d)\u001bxN\\*fe&\fG.\u001b>fe\")!\b\u0002a\u0001w!)a\u000b\u0002a\u0001/\u0006\u0019A/\u001f91\u0005a+\u0007cA-aG:\u0011!L\u0018\t\u00037\u001ej\u0011\u0001\u0018\u0006\u0003;\u0002\na\u0001\u0010:p_Rt\u0014BA0(\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011M\u0019\u0002\u0006\u00072\f7o\u001d\u0006\u0003?\u001e\u0002\"\u0001Z3\r\u0001\u0011Ia-VA\u0001\u0002\u0003\u0015\ta\u001a\u0002\u0004?\u0012\n\u0014C\u00015l!\t1\u0013.\u0003\u0002kO\t9aj\u001c;iS:<\u0007C\u0001\u0014m\u0013\tiwEA\u0002B]fDQa\u001c\u0003A\u0002\r\u000bA\u0001\u001d:paR!\u0011+\u001d:t\u0011\u0015QT\u00011\u0001<\u0011\u00151V\u00011\u0001M\u0011\u0015yW\u00011\u0001D\u0003aA\u0017m]\"p]R,g\u000e\u001e+za\u0016\feN\\8uCRLwN\u001c\u000b\u0004mY<\b\"\u0002\u001e\u0007\u0001\u0004Y\u0004\"\u0002\"\u0007\u0001\u00041\u0015\u0001D<sSR,'+\u001a9mC\u000e,G#\u0001>\u0011\u0005mtX\"\u0001?\u000b\u0005ut\u0013\u0001\u00027b]\u001eL!a ?\u0003\r=\u0013'.Z2u'\rA\u00111\u0001\t\u0007\u0003\u000b\ti!!\u0005\u000e\u0005\u0005\u001d!\u0002BA\u0005\u0003\u0017\t1a\u001d;e\u0015\t)R(\u0003\u0003\u0002\u0010\u0005\u001d!a\u0006*fM\u0016\u0014XM\\2f)f\u0004XmU3sS\u0006d\u0017N_3sa\u0011\t\u0019\"a\u0006\u0011\t\u0019\"\u0015Q\u0003\t\u0004I\u0006]AACA\r\u0011\u0005\u0005\t\u0011!B\u0001O\n\u0019q\f\n\u001a\u0002\u000fI,g\rV=qKB!\u0011qDA\u0013\u001b\t\t\tCC\u0002\u0002$u\nA\u0001^=qK&!\u0011qEA\u0011\u00055\u0011VMZ3sK:\u001cW\rV=qK\u0006a1\u000f^1uS\u000e$\u0016\u0010]5oO\u0006)2m\u001c8uK:$H+\u001f9f'\u0016\u0014\u0018.\u00197ju\u0016\u0014\b\u0003BA\u0018\u0003ki!!!\r\u000b\u0007\u0005MR(\u0001\u0005kg>tG/\u001f9f\u0013\u0011\t9$!\r\u0003\u001dQK\b/Z*fe&\fG.\u001b>fe\u000612m\u001c8uK:$h+\u00197vKN+'/[1mSj,'\u000f\u0006\u0006\u0002>\u0005}\u0012\u0011IA\"\u0003\u000b\u0002\"A\t\u0005\t\u000f\u0005mQ\u00021\u0001\u0002\u001e!1\u0011\u0011F\u0007A\u0002YBq!a\u000b\u000e\u0001\u0004\ti\u0003\u0003\u0004\u0002:5\u0001\r!U\u0001\ro&$\bNU3t_24X\r\u001a\u000b\u000b\u0003\u0017\n9&!\u0017\u0002^\u0005-\u0004CBA\u0003\u0003\u001b\ti\u0005\r\u0003\u0002P\u0005M\u0003\u0003\u0002\u0014E\u0003#\u00022\u0001ZA*\t)\t)FDA\u0001\u0002\u0003\u0015\ta\u001a\u0002\u0004?\u0012\"\u0004\"B8\u000f\u0001\u00041\u0005bBA.\u001d\u0001\u0007\u0011QF\u0001\u0004mR\u001c\bbBA0\u001d\u0001\u0007\u0011\u0011M\u0001\tm\u0006dW/Z*feB\"\u00111MA4!\u0011a$+!\u001a\u0011\u0007\u0011\f9\u0007B\u0006\u0002j\u0005u\u0013\u0011!A\u0001\u0006\u00039'aA0%g!9\u0011Q\u000e\bA\u0002\u0005=\u0014!C;ooJ\f\u0007\u000f]3s!\u0011\t\t(a\u001e\u000e\u0005\u0005M$bAA;{\u0005!Q\u000f^5m\u0013\u0011\tI(a\u001d\u0003\u001f9\u000bW.\u001a+sC:\u001chm\u001c:nKJ\fAc^5uQ\u000e{g\u000e^3oi&s7\r\\;tS>tGCBA@\u0003\u0017\u000by\t\u0005\u0004\u0002\u0006\u00055\u0011\u0011\u0011\u0019\u0005\u0003\u0007\u000b9\t\u0005\u0003'\t\u0006\u0015\u0005c\u00013\u0002\b\u0012Q\u0011\u0011R\b\u0002\u0002\u0003\u0005)\u0011A4\u0003\u0007}#S\u0007\u0003\u0004\u0002\u000e>\u0001\r!J\u0001\u0012gV\u0004\bO]3tg\u0006\u0014G.\u001a,bYV,\u0007BBAI\u001f\u0001\u0007a'A\u0007tkB\u0004(/Z:t\u001dVdGn]\u0001\u0010?&\u001ch+\u00197vKB\u0013Xm]3oiR\u0019a'a&\t\u000f\u0005e\u0005\u00031\u0001\u0002\u001c\u0006)a/\u00197vKB\"\u0011QTAQ!\u00111C)a(\u0011\u0007\u0011\f\t\u000bB\u0006\u0002$\u0006]\u0015\u0011!A\u0001\u0006\u00039'aA0%m\u0005qqlZ3u%\u00164WM]3oG\u0016$GcA\u0013\u0002*\"9\u0011\u0011T\tA\u0002\u0005-\u0006\u0007BAW\u0003c\u0003BA\n#\u00020B\u0019A-!-\u0005\u0017\u0005M\u0016\u0011VA\u0001\u0002\u0003\u0015\ta\u001a\u0002\u0004?\u0012:\u0014aF0hKR\u0014VMZ3sK:\u001cW\rZ%g!J,7/\u001a8u)\r)\u0013\u0011\u0018\u0005\b\u00033\u0013\u0002\u0019AA^a\u0011\ti,!1\u0011\t\u0019\"\u0015q\u0018\t\u0004I\u0006\u0005GaCAb\u0003s\u000b\t\u0011!A\u0003\u0002\u001d\u00141a\u0018\u00139\u0001"
)
public class OptionSerializer extends ReferenceTypeSerializer {
   public static boolean hasContentTypeAnnotation(final SerializerProvider provider, final BeanProperty property) {
      return OptionSerializer$.MODULE$.hasContentTypeAnnotation(provider, property);
   }

   public static JsonSerializer findSerializer(final SerializerProvider provider, final JavaType typ, final Option prop) {
      return OptionSerializer$.MODULE$.findSerializer(provider, typ, prop);
   }

   public static JsonSerializer findSerializer(final SerializerProvider provider, final Class typ, final Option prop) {
      return OptionSerializer$.MODULE$.findSerializer(provider, typ, prop);
   }

   public static boolean useStatic(final SerializerProvider provider, final Option property, final Option referredType) {
      return OptionSerializer$.MODULE$.useStatic(provider, property, referredType);
   }

   public ReferenceTypeSerializer withResolved(final BeanProperty prop, final TypeSerializer vts, final JsonSerializer valueSer, final NameTransformer unwrapper) {
      return new ResolvedOptionSerializer(this, prop, vts, valueSer, unwrapper, this._suppressableValue, this._suppressNulls);
   }

   public ReferenceTypeSerializer withContentInclusion(final Object suppressableValue, final boolean suppressNulls) {
      return new ResolvedOptionSerializer(this, this._property, this._valueTypeSerializer, this._valueSerializer, this._unwrapper, suppressableValue, suppressNulls);
   }

   public boolean _isValuePresent(final Option value) {
      return value.isDefined();
   }

   public Object _getReferenced(final Option value) {
      return value.get();
   }

   public Object _getReferencedIfPresent(final Option value) {
      return value.orNull(.MODULE$.refl());
   }

   public OptionSerializer(final ReferenceType refType, final boolean staticTyping, final TypeSerializer contentTypeSerializer, final JsonSerializer contentValueSerializer) {
      super(refType, staticTyping, contentTypeSerializer, contentValueSerializer);
   }
}
