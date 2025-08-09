package org.apache.spark.internal.config;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e4Aa\u0004\t\u00057!Iq\u0006\u0001B\u0001B\u0003%\u0001g\u000f\u0005\ny\u0001\u0011\t\u0011)A\u0005{\u0001C\u0011\"\u0011\u0001\u0003\u0002\u0003\u0006I\u0001\r\"\t\u0013\r\u0003!\u0011!Q\u0001\n\u0011k\u0005\u0002\u0003(\u0001\u0005\u0003\u0005\u000b\u0011B\u0011\t\u0013=\u0003!\u0011!Q\u0001\nA\u001b\u0006\"\u0003+\u0001\u0005\u0003\u0005\u000b\u0011B+W\u0011%9\u0006A!A!\u0002\u0013\u0001\u0004\fC\u0005Z\u0001\t\u0005\t\u0015!\u0003[;\"Ia\f\u0001B\u0001B\u0003%\u0001g\u0018\u0005\u0006A\u0002!\t!\u0019\u0005\u0006[\u0002!\tE\u001c\u0005\u0006a\u0002!\t%\u001d\u0005\u0006e\u0002!\ta\u001d\u0002\u0017\u0007>tg-[4F]R\u0014\u0018pV5uQ\u0012+g-Y;mi*\u0011\u0011CE\u0001\u0007G>tg-[4\u000b\u0005M!\u0012\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005U1\u0012!B:qCJ\\'BA\f\u0019\u0003\u0019\t\u0007/Y2iK*\t\u0011$A\u0002pe\u001e\u001c\u0001!\u0006\u0002\u001dGM\u0011\u0001!\b\t\u0004=}\tS\"\u0001\t\n\u0005\u0001\u0002\"aC\"p]\u001aLw-\u00128uef\u0004\"AI\u0012\r\u0001\u0011)A\u0005\u0001b\u0001K\t\tA+\u0005\u0002'YA\u0011qEK\u0007\u0002Q)\t\u0011&A\u0003tG\u0006d\u0017-\u0003\u0002,Q\t9aj\u001c;iS:<\u0007CA\u0014.\u0013\tq\u0003FA\u0002B]f\f1a[3z!\t\t\u0004H\u0004\u00023mA\u00111\u0007K\u0007\u0002i)\u0011QGG\u0001\u0007yI|w\u000e\u001e \n\u0005]B\u0013A\u0002)sK\u0012,g-\u0003\u0002:u\t11\u000b\u001e:j]\u001eT!a\u000e\u0015\n\u0005=z\u0012\u0001\u00049sKB,g\u000eZ3e\u0017\u0016L\bcA\u0014?a%\u0011q\b\u000b\u0002\u0007\u001fB$\u0018n\u001c8\n\u0005qz\u0012\u0001\u00059sKB,g\u000eZ*fa\u0006\u0014\u0018\r^8s\u0013\t\tu$\u0001\u0007bYR,'O\\1uSZ,7\u000fE\u0002F\u0015Br!A\u0012%\u000f\u0005M:\u0015\"A\u0015\n\u0005%C\u0013a\u00029bG.\fw-Z\u0005\u0003\u00172\u0013A\u0001T5ti*\u0011\u0011\nK\u0005\u0003\u0007~\tQb\u00183fM\u0006,H\u000e\u001e,bYV,\u0017A\u0004<bYV,7i\u001c8wKJ$XM\u001d\t\u0005OE\u0003\u0014%\u0003\u0002SQ\tIa)\u001e8di&|g.M\u0005\u0003\u001f~\tqb\u001d;sS:<7i\u001c8wKJ$XM\u001d\t\u0005OE\u000b\u0003'\u0003\u0002U?\u0005\u0019Am\\2\n\u0005]{\u0012\u0001C5t!V\u0014G.[2\u0011\u0005\u001dZ\u0016B\u0001/)\u0005\u001d\u0011un\u001c7fC:L!!W\u0010\u0002\u000fY,'o]5p]&\u0011alH\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0017\t\u001cG-\u001a4hQ&T7\u000e\u001c\t\u0004=\u0001\t\u0003\"B\u0018\f\u0001\u0004\u0001\u0004\"\u0002\u001f\f\u0001\u0004i\u0004\"B!\f\u0001\u0004\u0001\u0004\"B\"\f\u0001\u0004!\u0005\"\u0002(\f\u0001\u0004\t\u0003\"B(\f\u0001\u0004\u0001\u0006\"\u0002+\f\u0001\u0004)\u0006\"B,\f\u0001\u0004\u0001\u0004\"B-\f\u0001\u0004Q\u0006\"\u00020\f\u0001\u0004\u0001\u0014\u0001\u00043fM\u0006,H\u000e\u001e,bYV,W#A8\u0011\u0007\u001dr\u0014%\u0001\neK\u001a\fW\u000f\u001c;WC2,Xm\u0015;sS:<W#\u0001\u0019\u0002\u0011I,\u0017\r\u001a$s_6$\"!\t;\t\u000bUt\u0001\u0019\u0001<\u0002\rI,\u0017\rZ3s!\tqr/\u0003\u0002y!\ta1i\u001c8gS\u001e\u0014V-\u00193fe\u0002"
)
public class ConfigEntryWithDefault extends ConfigEntry {
   private final Object _defaultValue;

   public Option defaultValue() {
      return new Some(this._defaultValue);
   }

   public String defaultValueString() {
      return (String)super.stringConverter().apply(this._defaultValue);
   }

   public Object readFrom(final ConfigReader reader) {
      return this.readString(reader).map(super.valueConverter()).getOrElse(() -> this._defaultValue);
   }

   public ConfigEntryWithDefault(final String key, final Option prependedKey, final String prependSeparator, final List alternatives, final Object _defaultValue, final Function1 valueConverter, final Function1 stringConverter, final String doc, final boolean isPublic, final String version) {
      super(key, prependedKey, prependSeparator, alternatives, valueConverter, stringConverter, doc, isPublic, version);
      this._defaultValue = _defaultValue;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
