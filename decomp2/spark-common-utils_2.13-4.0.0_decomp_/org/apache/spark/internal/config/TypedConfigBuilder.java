package org.apache.spark.internal.config;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkIllegalArgumentException;
import scala.Function0;
import scala.Function1;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnce;
import scala.collection.MapOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eb!\u0002\n\u0014\u0001]i\u0002\u0002C\u0013\u0001\u0005\u000b\u0007I\u0011A\u0014\t\u00111\u0002!\u0011!Q\u0001\n!B\u0001\"\f\u0001\u0003\u0006\u0004%\tA\f\u0005\t\u0011\u0002\u0011\t\u0011)A\u0005_!A\u0011\n\u0001BC\u0002\u0013\u0005!\n\u0003\u0005M\u0001\t\u0005\t\u0015!\u0003L\u0011\u0015i\u0005\u0001\"\u0001O\u0011\u0015i\u0005\u0001\"\u0001T\u0011\u00151\u0006\u0001\"\u0001X\u0011\u0015Y\u0006\u0001\"\u0001]\u0011\u0015Y\u0006\u0001\"\u0001f\u0011\u0015y\u0007\u0001\"\u0001q\u0011\u00151\b\u0001\"\u0001x\u0011\u001d\t)\u0001\u0001C\u0001\u0003\u000fAq!a\u0004\u0001\t\u0003\t\t\u0002C\u0004\u0002\u001e\u0001!\t!a\b\t\u000f\u0005-\u0002\u0001\"\u0001\u0002.\t\u0011B+\u001f9fI\u000e{gNZ5h\u0005VLG\u000eZ3s\u0015\t!R#\u0001\u0004d_:4\u0017n\u001a\u0006\u0003-]\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u00031e\tQa\u001d9be.T!AG\u000e\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0012aA8sOV\u0011adP\n\u0003\u0001}\u0001\"\u0001I\u0012\u000e\u0003\u0005R\u0011AI\u0001\u0006g\u000e\fG.Y\u0005\u0003I\u0005\u0012a!\u00118z%\u00164\u0017A\u00029be\u0016tGo\u0001\u0001\u0016\u0003!\u0002\"!\u000b\u0016\u000e\u0003MI!aK\n\u0003\u001b\r{gNZ5h\u0005VLG\u000eZ3s\u0003\u001d\u0001\u0018M]3oi\u0002\n\u0011bY8om\u0016\u0014H/\u001a:\u0016\u0003=\u0002B\u0001\t\u00193{%\u0011\u0011'\t\u0002\n\rVt7\r^5p]F\u0002\"a\r\u001e\u000f\u0005QB\u0004CA\u001b\"\u001b\u00051$BA\u001c'\u0003\u0019a$o\\8u}%\u0011\u0011(I\u0001\u0007!J,G-\u001a4\n\u0005mb$AB*ue&twM\u0003\u0002:CA\u0011ah\u0010\u0007\u0001\t\u0015\u0001\u0005A1\u0001B\u0005\u0005!\u0016C\u0001\"F!\t\u00013)\u0003\u0002EC\t9aj\u001c;iS:<\u0007C\u0001\u0011G\u0013\t9\u0015EA\u0002B]f\f!bY8om\u0016\u0014H/\u001a:!\u0003=\u0019HO]5oO\u000e{gN^3si\u0016\u0014X#A&\u0011\t\u0001\u0002THM\u0001\u0011gR\u0014\u0018N\\4D_:4XM\u001d;fe\u0002\na\u0001P5oSRtD\u0003B(Q#J\u00032!\u000b\u0001>\u0011\u0015)s\u00011\u0001)\u0011\u0015is\u00011\u00010\u0011\u0015Iu\u00011\u0001L)\ryE+\u0016\u0005\u0006K!\u0001\r\u0001\u000b\u0005\u0006[!\u0001\raL\u0001\niJ\fgn\u001d4pe6$\"a\u0014-\t\u000beK\u0001\u0019\u0001.\u0002\u0005\u0019t\u0007\u0003\u0002\u00111{u\n!b\u00195fG.4\u0016\r\\;f)\ryUl\u0019\u0005\u0006=*\u0001\raX\u0001\nm\u0006d\u0017\u000eZ1u_J\u0004B\u0001\t\u0019>AB\u0011\u0001%Y\u0005\u0003E\u0006\u0012qAQ8pY\u0016\fg\u000eC\u0003e\u0015\u0001\u0007!'\u0001\u0005feJ|'/T:h)\u0011yemZ5\t\u000by[\u0001\u0019A0\t\u000b!\\\u0001\u0019\u0001\u001a\u0002\u0015\u0015\u0014(o\u001c:DY\u0006\u001c8\u000fC\u0003k\u0017\u0001\u00071.\u0001\u0006qCJ\fW.\u001a;feN\u0004B\u0001\t\u0019>YB!1'\u001c\u001a3\u0013\tqGHA\u0002NCB\f1b\u00195fG.4\u0016\r\\;fgR\u0011q*\u001d\u0005\u0006e2\u0001\ra]\u0001\fm\u0006d\u0017\u000e\u001a,bYV,7\u000fE\u00024ivJ!!\u001e\u001f\u0003\u0007M+G/\u0001\u0006u_N+\u0017/^3oG\u0016,\u0012\u0001\u001f\t\u0004S\u0001I\bc\u0001>\u0000{9\u001110 \b\u0003kqL\u0011AI\u0005\u0003}\u0006\nq\u0001]1dW\u0006<W-\u0003\u0003\u0002\u0002\u0005\r!aA*fc*\u0011a0I\u0001\u000fGJ,\u0017\r^3PaRLwN\\1m+\t\tI\u0001\u0005\u0003*\u0003\u0017i\u0014bAA\u0007'\t\u0019r\n\u001d;j_:\fGnQ8oM&<WI\u001c;ss\u0006\t2M]3bi\u0016<\u0016\u000e\u001e5EK\u001a\fW\u000f\u001c;\u0015\t\u0005M\u0011\u0011\u0004\t\u0005S\u0005UQ(C\u0002\u0002\u0018M\u00111bQ8oM&<WI\u001c;ss\"1\u00111D\bA\u0002u\nq\u0001Z3gCVdG/A\rde\u0016\fG/Z,ji\"$UMZ1vYR4UO\\2uS>tG\u0003BA\n\u0003CAq!a\t\u0011\u0001\u0004\t)#A\u0006eK\u001a\fW\u000f\u001c;Gk:\u001c\u0007\u0003\u0002\u0011\u0002(uJ1!!\u000b\"\u0005%1UO\\2uS>t\u0007'A\fde\u0016\fG/Z,ji\"$UMZ1vYR\u001cFO]5oOR!\u00111CA\u0018\u0011\u0019\tY\"\u0005a\u0001e\u0001"
)
public class TypedConfigBuilder {
   private final ConfigBuilder parent;
   private final Function1 converter;
   private final Function1 stringConverter;

   public ConfigBuilder parent() {
      return this.parent;
   }

   public Function1 converter() {
      return this.converter;
   }

   public Function1 stringConverter() {
      return this.stringConverter;
   }

   public TypedConfigBuilder transform(final Function1 fn) {
      return new TypedConfigBuilder(this.parent(), (s) -> fn.apply(this.converter().apply(s)), this.stringConverter());
   }

   public TypedConfigBuilder checkValue(final Function1 validator, final String errorMsg) {
      return this.transform((v) -> {
         if (!BoxesRunTime.unboxToBoolean(validator.apply(v))) {
            throw new IllegalArgumentException("'" + v + "' in " + this.parent().key() + " is invalid. " + errorMsg);
         } else {
            return v;
         }
      });
   }

   public TypedConfigBuilder checkValue(final Function1 validator, final String errorClass, final Function1 parameters) {
      return this.transform((v) -> {
         if (!BoxesRunTime.unboxToBoolean(validator.apply(v))) {
            throw new SparkIllegalArgumentException("INVALID_CONF_VALUE." + errorClass, (Map)((MapOps)parameters.apply(v)).$plus$plus((IterableOnce).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("confValue"), v.toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("confName"), this.parent().key())})))));
         } else {
            return v;
         }
      });
   }

   public TypedConfigBuilder checkValues(final Set validValues) {
      return this.transform((v) -> {
         if (!validValues.contains(v)) {
            String var10002 = this.parent().key();
            throw new IllegalArgumentException("The value of " + var10002 + " should be one of " + validValues.mkString(", ") + ", but was " + v);
         } else {
            return v;
         }
      });
   }

   public TypedConfigBuilder toSequence() {
      return new TypedConfigBuilder(this.parent(), (x$2) -> ConfigHelpers$.MODULE$.stringToSeq(x$2, this.converter()), (x$3) -> ConfigHelpers$.MODULE$.seqToString(x$3, this.stringConverter()));
   }

   public OptionalConfigEntry createOptional() {
      OptionalConfigEntry entry = new OptionalConfigEntry(this.parent().key(), this.parent()._prependedKey(), this.parent()._prependSeparator(), this.parent()._alternatives(), this.converter(), this.stringConverter(), this.parent()._doc(), this.parent()._public(), this.parent()._version());
      this.parent()._onCreate().foreach((x$4) -> {
         $anonfun$createOptional$1(entry, x$4);
         return BoxedUnit.UNIT;
      });
      return entry;
   }

   public ConfigEntry createWithDefault(final Object default) {
      .MODULE$.assert(default != null, () -> "Use createOptional.");
      if (default instanceof String var4) {
         return this.createWithDefaultString(var4);
      } else {
         Object transformedDefault = this.converter().apply(this.stringConverter().apply(default));
         ConfigEntryWithDefault entry = new ConfigEntryWithDefault(this.parent().key(), this.parent()._prependedKey(), this.parent()._prependSeparator(), this.parent()._alternatives(), transformedDefault, this.converter(), this.stringConverter(), this.parent()._doc(), this.parent()._public(), this.parent()._version());
         this.parent()._onCreate().foreach((x$5) -> {
            $anonfun$createWithDefault$2(entry, x$5);
            return BoxedUnit.UNIT;
         });
         return entry;
      }
   }

   public ConfigEntry createWithDefaultFunction(final Function0 defaultFunc) {
      ConfigEntryWithDefaultFunction entry = new ConfigEntryWithDefaultFunction(this.parent().key(), this.parent()._prependedKey(), this.parent()._prependSeparator(), this.parent()._alternatives(), defaultFunc, this.converter(), this.stringConverter(), this.parent()._doc(), this.parent()._public(), this.parent()._version());
      this.parent()._onCreate().foreach((x$6) -> {
         $anonfun$createWithDefaultFunction$1(entry, x$6);
         return BoxedUnit.UNIT;
      });
      return entry;
   }

   public ConfigEntry createWithDefaultString(final String default) {
      ConfigEntryWithDefaultString entry = new ConfigEntryWithDefaultString(this.parent().key(), this.parent()._prependedKey(), this.parent()._prependSeparator(), this.parent()._alternatives(), default, this.converter(), this.stringConverter(), this.parent()._doc(), this.parent()._public(), this.parent()._version());
      this.parent()._onCreate().foreach((x$7) -> {
         $anonfun$createWithDefaultString$1(entry, x$7);
         return BoxedUnit.UNIT;
      });
      return entry;
   }

   // $FF: synthetic method
   public static final void $anonfun$createOptional$1(final OptionalConfigEntry entry$1, final Function1 x$4) {
      x$4.apply(entry$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$createWithDefault$2(final ConfigEntryWithDefault entry$2, final Function1 x$5) {
      x$5.apply(entry$2);
   }

   // $FF: synthetic method
   public static final void $anonfun$createWithDefaultFunction$1(final ConfigEntryWithDefaultFunction entry$3, final Function1 x$6) {
      x$6.apply(entry$3);
   }

   // $FF: synthetic method
   public static final void $anonfun$createWithDefaultString$1(final ConfigEntryWithDefaultString entry$4, final Function1 x$7) {
      x$7.apply(entry$4);
   }

   public TypedConfigBuilder(final ConfigBuilder parent, final Function1 converter, final Function1 stringConverter) {
      this.parent = parent;
      this.converter = converter;
      this.stringConverter = stringConverter;
   }

   public TypedConfigBuilder(final ConfigBuilder parent, final Function1 converter) {
      this(parent, converter, new Serializable() {
         private static final long serialVersionUID = 0L;

         public final String apply(final Object v) {
            return v.toString();
         }
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
