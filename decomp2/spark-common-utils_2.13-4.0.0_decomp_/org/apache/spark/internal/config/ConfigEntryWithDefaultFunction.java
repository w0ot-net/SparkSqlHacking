package org.apache.spark.internal.config;

import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q4Aa\u0004\t\u00057!Iq\u0006\u0001B\u0001B\u0003%\u0001g\u000f\u0005\ny\u0001\u0011\t\u0011)A\u0005{\u0001C\u0011\"\u0011\u0001\u0003\u0002\u0003\u0006I\u0001\r\"\t\u0013\r\u0003!\u0011!Q\u0001\n\u0011k\u0005\u0002\u0003(\u0001\u0005\u0003\u0005\u000b\u0011B(\t\u0013I\u0003!\u0011!Q\u0001\nM3\u0006\"C,\u0001\u0005\u0003\u0005\u000b\u0011\u0002-Z\u0011%Q\u0006A!A!\u0002\u0013\u00014\fC\u0005]\u0001\t\u0005\t\u0015!\u0003^A\"I\u0011\r\u0001B\u0001B\u0003%\u0001G\u0019\u0005\u0006G\u0002!\t\u0001\u001a\u0005\u0006a\u0002!\t%\u001d\u0005\u0006g\u0002!\t\u0005\u001e\u0005\u0006k\u0002!\tA\u001e\u0002\u001f\u0007>tg-[4F]R\u0014\u0018pV5uQ\u0012+g-Y;mi\u001a+hn\u0019;j_:T!!\u0005\n\u0002\r\r|gNZ5h\u0015\t\u0019B#\u0001\u0005j]R,'O\\1m\u0015\t)b#A\u0003ta\u0006\u00148N\u0003\u0002\u00181\u00051\u0011\r]1dQ\u0016T\u0011!G\u0001\u0004_J<7\u0001A\u000b\u00039\r\u001a\"\u0001A\u000f\u0011\u0007yy\u0012%D\u0001\u0011\u0013\t\u0001\u0003CA\u0006D_:4\u0017nZ#oiJL\bC\u0001\u0012$\u0019\u0001!Q\u0001\n\u0001C\u0002\u0015\u0012\u0011\u0001V\t\u0003M1\u0002\"a\n\u0016\u000e\u0003!R\u0011!K\u0001\u0006g\u000e\fG.Y\u0005\u0003W!\u0012qAT8uQ&tw\r\u0005\u0002([%\u0011a\u0006\u000b\u0002\u0004\u0003:L\u0018aA6fsB\u0011\u0011\u0007\u000f\b\u0003eY\u0002\"a\r\u0015\u000e\u0003QR!!\u000e\u000e\u0002\rq\u0012xn\u001c;?\u0013\t9\u0004&\u0001\u0004Qe\u0016$WMZ\u0005\u0003si\u0012aa\u0015;sS:<'BA\u001c)\u0013\tys$\u0001\u0007qe\u0016\u0004XM\u001c3fI.+\u0017\u0010E\u0002(}AJ!a\u0010\u0015\u0003\r=\u0003H/[8o\u0013\tat$\u0001\tqe\u0016\u0004XM\u001c3TKB\f'/\u0019;pe&\u0011\u0011iH\u0001\rC2$XM\u001d8bi&4Xm\u001d\t\u0004\u000b*\u0003dB\u0001$I\u001d\t\u0019t)C\u0001*\u0013\tI\u0005&A\u0004qC\u000e\\\u0017mZ3\n\u0005-c%\u0001\u0002'jgRT!!\u0013\u0015\n\u0005\r{\u0012\u0001E0eK\u001a\fW\u000f\u001c;Gk:\u001cG/[8o!\r9\u0003+I\u0005\u0003#\"\u0012\u0011BR;oGRLwN\u001c\u0019\u0002\u001dY\fG.^3D_:4XM\u001d;feB!q\u0005\u0016\u0019\"\u0013\t)\u0006FA\u0005Gk:\u001cG/[8oc%\u0011!kH\u0001\u0010gR\u0014\u0018N\\4D_:4XM\u001d;feB!q\u0005V\u00111\u0013\t9v$A\u0002e_\u000eL!AW\u0010\u0002\u0011%\u001c\b+\u001e2mS\u000e\u0004\"a\n0\n\u0005}C#a\u0002\"p_2,\u0017M\\\u0005\u00039~\tqA^3sg&|g.\u0003\u0002b?\u00051A(\u001b8jiz\"2\"\u001a4hQ&T7\u000e\\7o_B\u0019a\u0004A\u0011\t\u000b=Z\u0001\u0019\u0001\u0019\t\u000bqZ\u0001\u0019A\u001f\t\u000b\u0005[\u0001\u0019\u0001\u0019\t\u000b\r[\u0001\u0019\u0001#\t\u000b9[\u0001\u0019A(\t\u000bI[\u0001\u0019A*\t\u000b][\u0001\u0019\u0001-\t\u000bi[\u0001\u0019\u0001\u0019\t\u000bq[\u0001\u0019A/\t\u000b\u0005\\\u0001\u0019\u0001\u0019\u0002\u0019\u0011,g-Y;miZ\u000bG.^3\u0016\u0003I\u00042a\n \"\u0003I!WMZ1vYR4\u0016\r\\;f'R\u0014\u0018N\\4\u0016\u0003A\n\u0001B]3bI\u001a\u0013x.\u001c\u000b\u0003C]DQ\u0001\u001f\bA\u0002e\faA]3bI\u0016\u0014\bC\u0001\u0010{\u0013\tY\bC\u0001\u0007D_:4\u0017n\u001a*fC\u0012,'\u000f"
)
public class ConfigEntryWithDefaultFunction extends ConfigEntry {
   private final Function0 _defaultFunction;

   public Option defaultValue() {
      return new Some(this._defaultFunction.apply());
   }

   public String defaultValueString() {
      return (String)super.stringConverter().apply(this._defaultFunction.apply());
   }

   public Object readFrom(final ConfigReader reader) {
      return this.readString(reader).map(super.valueConverter()).getOrElse(this._defaultFunction);
   }

   public ConfigEntryWithDefaultFunction(final String key, final Option prependedKey, final String prependSeparator, final List alternatives, final Function0 _defaultFunction, final Function1 valueConverter, final Function1 stringConverter, final String doc, final boolean isPublic, final String version) {
      super(key, prependedKey, prependSeparator, alternatives, valueConverter, stringConverter, doc, isPublic, version);
      this._defaultFunction = _defaultFunction;
   }
}
