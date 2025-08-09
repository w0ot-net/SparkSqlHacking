package org.apache.spark.internal.config;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e4Aa\u0004\t\u00057!Iq\u0006\u0001B\u0001B\u0003%\u0001g\u000f\u0005\ny\u0001\u0011\t\u0011)A\u0005{\u0001C\u0011\"\u0011\u0001\u0003\u0002\u0003\u0006I\u0001\r\"\t\u0013\r\u0003!\u0011!Q\u0001\n\u0011k\u0005\u0002\u0003(\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0019\t\u0013=\u0003!\u0011!Q\u0001\nA\u001b\u0006\"\u0003+\u0001\u0005\u0003\u0005\u000b\u0011B+W\u0011%9\u0006A!A!\u0002\u0013\u0001\u0004\fC\u0005Z\u0001\t\u0005\t\u0015!\u0003[;\"Ia\f\u0001B\u0001B\u0003%\u0001g\u0018\u0005\u0006A\u0002!\t!\u0019\u0005\u0006[\u0002!\tE\u001c\u0005\u0006a\u0002!\t%\u001d\u0005\u0006e\u0002!\ta\u001d\u0002\u001d\u0007>tg-[4F]R\u0014\u0018pV5uQ\u0012+g-Y;miN#(/\u001b8h\u0015\t\t\"#\u0001\u0004d_:4\u0017n\u001a\u0006\u0003'Q\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003+Y\tQa\u001d9be.T!a\u0006\r\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005I\u0012aA8sO\u000e\u0001QC\u0001\u000f$'\t\u0001Q\u0004E\u0002\u001f?\u0005j\u0011\u0001E\u0005\u0003AA\u00111bQ8oM&<WI\u001c;ssB\u0011!e\t\u0007\u0001\t\u0015!\u0003A1\u0001&\u0005\u0005!\u0016C\u0001\u0014-!\t9#&D\u0001)\u0015\u0005I\u0013!B:dC2\f\u0017BA\u0016)\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aJ\u0017\n\u00059B#aA!os\u0006\u00191.Z=\u0011\u0005EBdB\u0001\u001a7!\t\u0019\u0004&D\u00015\u0015\t)$$\u0001\u0004=e>|GOP\u0005\u0003o!\na\u0001\u0015:fI\u00164\u0017BA\u001d;\u0005\u0019\u0019FO]5oO*\u0011q\u0007K\u0005\u0003_}\tA\u0002\u001d:fa\u0016tG-\u001a3LKf\u00042a\n 1\u0013\ty\u0004F\u0001\u0004PaRLwN\\\u0005\u0003y}\t\u0001\u0003\u001d:fa\u0016tGmU3qCJ\fGo\u001c:\n\u0005\u0005{\u0012\u0001D1mi\u0016\u0014h.\u0019;jm\u0016\u001c\bcA#Ka9\u0011a\t\u0013\b\u0003g\u001dK\u0011!K\u0005\u0003\u0013\"\nq\u0001]1dW\u0006<W-\u0003\u0002L\u0019\n!A*[:u\u0015\tI\u0005&\u0003\u0002D?\u0005iq\fZ3gCVdGOV1mk\u0016\faB^1mk\u0016\u001cuN\u001c<feR,'\u000f\u0005\u0003(#B\n\u0013B\u0001*)\u0005%1UO\\2uS>t\u0017'\u0003\u0002P?\u0005y1\u000f\u001e:j]\u001e\u001cuN\u001c<feR,'\u000f\u0005\u0003(#\u0006\u0002\u0014B\u0001+ \u0003\r!wnY\u0005\u0003/~\t\u0001\"[:Qk\nd\u0017n\u0019\t\u0003OmK!\u0001\u0018\u0015\u0003\u000f\t{w\u000e\\3b]&\u0011\u0011lH\u0001\bm\u0016\u00148/[8o\u0013\tqv$\u0001\u0004=S:LGO\u0010\u000b\fE\u000e$WMZ4iS*\\G\u000eE\u0002\u001f\u0001\u0005BQaL\u0006A\u0002ABQ\u0001P\u0006A\u0002uBQ!Q\u0006A\u0002ABQaQ\u0006A\u0002\u0011CQAT\u0006A\u0002ABQaT\u0006A\u0002ACQ\u0001V\u0006A\u0002UCQaV\u0006A\u0002ABQ!W\u0006A\u0002iCQAX\u0006A\u0002A\nA\u0002Z3gCVdGOV1mk\u0016,\u0012a\u001c\t\u0004Oy\n\u0013A\u00053fM\u0006,H\u000e\u001e,bYV,7\u000b\u001e:j]\u001e,\u0012\u0001M\u0001\te\u0016\fGM\u0012:p[R\u0011\u0011\u0005\u001e\u0005\u0006k:\u0001\rA^\u0001\u0007e\u0016\fG-\u001a:\u0011\u0005y9\u0018B\u0001=\u0011\u00051\u0019uN\u001c4jOJ+\u0017\rZ3s\u0001"
)
public class ConfigEntryWithDefaultString extends ConfigEntry {
   private final String _defaultValue;

   public Option defaultValue() {
      return new Some(super.valueConverter().apply(this._defaultValue));
   }

   public String defaultValueString() {
      return this._defaultValue;
   }

   public Object readFrom(final ConfigReader reader) {
      String value = (String)this.readString(reader).getOrElse(() -> reader.substitute(this._defaultValue));
      return super.valueConverter().apply(value);
   }

   public ConfigEntryWithDefaultString(final String key, final Option prependedKey, final String prependSeparator, final List alternatives, final String _defaultValue, final Function1 valueConverter, final Function1 stringConverter, final String doc, final boolean isPublic, final String version) {
      super(key, prependedKey, prependSeparator, alternatives, valueConverter, stringConverter, doc, isPublic, version);
      this._defaultValue = _defaultValue;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
