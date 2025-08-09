package org.apache.spark.internal.config;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala..less.colon.less.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]4Qa\u0004\t\u0001)iA\u0011B\r\u0001\u0003\u0002\u0003\u0006Ia\r \t\u0013}\u0002!\u0011!Q\u0001\n\u0001\u000b\u0005\"\u0003\"\u0001\u0005\u0003\u0005\u000b\u0011B\u001aD\u0011%!\u0005A!A!\u0002\u0013)e\n\u0003\u0005P\u0001\t\u0015\r\u0011\"\u0001Q\u0011!!\u0006A!A!\u0002\u0013\t\u0006\u0002C+\u0001\u0005\u000b\u0007I\u0011\u0001,\t\u0011a\u0003!\u0011!Q\u0001\n]C\u0011\"\u0017\u0001\u0003\u0002\u0003\u0006Ia\r.\t\u0013m\u0003!\u0011!Q\u0001\nq{\u0006\"\u00031\u0001\u0005\u0003\u0005\u000b\u0011B\u001ab\u0011\u0015\u0011\u0007\u0001\"\u0001d\u0011\u0015q\u0007\u0001\"\u0011p\u0011\u0015\u0001\b\u0001\"\u0011r\u0005My\u0005\u000f^5p]\u0006d7i\u001c8gS\u001e,e\u000e\u001e:z\u0015\t\t\"#\u0001\u0004d_:4\u0017n\u001a\u0006\u0003'Q\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003+Y\tQa\u001d9be.T!a\u0006\r\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005I\u0012aA8sOV\u00111\u0004K\n\u0003\u0001q\u00012!\b\u0010!\u001b\u0005\u0001\u0012BA\u0010\u0011\u0005-\u0019uN\u001c4jO\u0016sGO]=\u0011\u0007\u0005\"c%D\u0001#\u0015\u0005\u0019\u0013!B:dC2\f\u0017BA\u0013#\u0005\u0019y\u0005\u000f^5p]B\u0011q\u0005\u000b\u0007\u0001\t\u0015I\u0003A1\u0001,\u0005\u0005!6\u0001A\t\u0003Y=\u0002\"!I\u0017\n\u00059\u0012#a\u0002(pi\"Lgn\u001a\t\u0003CAJ!!\r\u0012\u0003\u0007\u0005s\u00170A\u0002lKf\u0004\"\u0001N\u001e\u000f\u0005UJ\u0004C\u0001\u001c#\u001b\u00059$B\u0001\u001d+\u0003\u0019a$o\\8u}%\u0011!HI\u0001\u0007!J,G-\u001a4\n\u0005qj$AB*ue&twM\u0003\u0002;E%\u0011!GH\u0001\raJ,\u0007/\u001a8eK\u0012\\U-\u001f\t\u0004C\u0011\u001a\u0014BA \u001f\u0003A\u0001(/\u001a9f]\u0012\u001cV\r]1sCR|'/\u0003\u0002C=\u0005a\u0011\r\u001c;fe:\fG/\u001b<fgB\u0019aiS\u001a\u000f\u0005\u001dKeB\u0001\u001cI\u0013\u0005\u0019\u0013B\u0001&#\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001T'\u0003\t1K7\u000f\u001e\u0006\u0003\u0015\nJ!\u0001\u0012\u0010\u0002#I\fwOV1mk\u0016\u001cuN\u001c<feR,'/F\u0001R!\u0011\t#k\r\u0014\n\u0005M\u0013#!\u0003$v]\u000e$\u0018n\u001c82\u0003I\u0011\u0018m\u001e,bYV,7i\u001c8wKJ$XM\u001d\u0011\u0002%I\fwo\u0015;sS:<7i\u001c8wKJ$XM]\u000b\u0002/B!\u0011E\u0015\u00144\u0003M\u0011\u0018m^*ue&twmQ8om\u0016\u0014H/\u001a:!\u0003\r!wnY\u0005\u00033z\t\u0001\"[:Qk\nd\u0017n\u0019\t\u0003CuK!A\u0018\u0012\u0003\u000f\t{w\u000e\\3b]&\u00111LH\u0001\bm\u0016\u00148/[8o\u0013\t\u0001g$\u0001\u0004=S:LGO\u0010\u000b\u000bI\u00164w\r[5kW2l\u0007cA\u000f\u0001M!)!\u0007\u0004a\u0001g!)q\b\u0004a\u0001\u0001\")!\t\u0004a\u0001g!)A\t\u0004a\u0001\u000b\")q\n\u0004a\u0001#\")Q\u000b\u0004a\u0001/\")\u0011\f\u0004a\u0001g!)1\f\u0004a\u00019\")\u0001\r\u0004a\u0001g\u0005\u0011B-\u001a4bk2$h+\u00197vKN#(/\u001b8h+\u0005\u0019\u0014\u0001\u0003:fC\u00124%o\\7\u0015\u0005\u0001\u0012\b\"B:\u000f\u0001\u0004!\u0018A\u0002:fC\u0012,'\u000f\u0005\u0002\u001ek&\u0011a\u000f\u0005\u0002\r\u0007>tg-[4SK\u0006$WM\u001d"
)
public class OptionalConfigEntry extends ConfigEntry {
   private final Function1 rawValueConverter;
   private final Function1 rawStringConverter;

   public Function1 rawValueConverter() {
      return this.rawValueConverter;
   }

   public Function1 rawStringConverter() {
      return this.rawStringConverter;
   }

   public String defaultValueString() {
      return ConfigEntry$.MODULE$.UNDEFINED();
   }

   public Option readFrom(final ConfigReader reader) {
      return this.readString(reader).map(this.rawValueConverter());
   }

   public OptionalConfigEntry(final String key, final Option prependedKey, final String prependSeparator, final List alternatives, final Function1 rawValueConverter, final Function1 rawStringConverter, final String doc, final boolean isPublic, final String version) {
      super(key, prependedKey, prependSeparator, alternatives, new Serializable(rawValueConverter) {
         private static final long serialVersionUID = 0L;
         private final Function1 rawValueConverter$1;

         public final Some apply(final String s) {
            return new Some(this.rawValueConverter$1.apply(s));
         }

         public {
            this.rawValueConverter$1 = rawValueConverter$1;
         }
      }, new Serializable(rawStringConverter) {
         private static final long serialVersionUID = 0L;
         private final Function1 rawStringConverter$1;

         public final String apply(final Option v) {
            return (String)v.map(this.rawStringConverter$1).orNull(.MODULE$.refl());
         }

         public {
            this.rawStringConverter$1 = rawStringConverter$1;
         }
      }, doc, isPublic, version);
      this.rawValueConverter = rawValueConverter;
      this.rawStringConverter = rawStringConverter;
   }
}
