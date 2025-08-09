package org.apache.spark.internal.config;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055eA\u0002\u0012$\u0003\u00039S\u0006\u0003\u00056\u0001\t\u0015\r\u0011\"\u00018\u0011!\u0019\u0005A!A!\u0002\u0013A\u0004\u0002\u0003#\u0001\u0005\u000b\u0007I\u0011A#\t\u0011%\u0003!\u0011!Q\u0001\n\u0019C\u0001B\u0013\u0001\u0003\u0006\u0004%\ta\u000e\u0005\t\u0017\u0002\u0011\t\u0011)A\u0005q!AA\n\u0001BC\u0002\u0013\u0005Q\n\u0003\u0005X\u0001\t\u0005\t\u0015!\u0003O\u0011!A\u0006A!b\u0001\n\u0003I\u0006\u0002\u00035\u0001\u0005\u0003\u0005\u000b\u0011\u0002.\t\u0011%\u0004!Q1A\u0005\u0002)D\u0001\u0002\u001c\u0001\u0003\u0002\u0003\u0006Ia\u001b\u0005\t[\u0002\u0011)\u0019!C\u0001o!Aa\u000e\u0001B\u0001B\u0003%\u0001\b\u0003\u0005p\u0001\t\u0015\r\u0011\"\u0001q\u0011!!\bA!A!\u0002\u0013\t\b\u0002C;\u0001\u0005\u000b\u0007I\u0011A\u001c\t\u0011Y\u0004!\u0011!Q\u0001\naBQa\u001e\u0001\u0005\u0002aDa!!\u0003\u0001\r\u00039\u0004bBA\u0006\u0001\u0011E\u0011Q\u0002\u0005\b\u00033\u0001a\u0011AA\u000e\u0011\u001d\ty\u0002\u0001C\u0001\u0003CAq!!\n\u0001\t\u0003\n9c\u0002\u0005\u0002*\rB\taJA\u0016\r\u001d\u00113\u0005#\u0001(\u0003[Aaa\u001e\u000e\u0005\u0002\u0005=\u0002\"CA\u00195\t\u0007I\u0011AA\u001a\u0011!\t\u0019E\u0007Q\u0001\n\u0005U\u0002BCA#5\t\u0007I\u0011A\u0014\u0002H!A\u00111\r\u000e!\u0002\u0013\tI\u0005C\u0004\u0002fi!\t!a\u001a\t\u000f\u0005u$\u0004\"\u0001\u0002\u0000\tY1i\u001c8gS\u001e,e\u000e\u001e:z\u0015\t!S%\u0001\u0004d_:4\u0017n\u001a\u0006\u0003M\u001d\n\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003Q%\nQa\u001d9be.T!AK\u0016\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0013aA8sOV\u0011afX\n\u0003\u0001=\u0002\"\u0001M\u001a\u000e\u0003ER\u0011AM\u0001\u0006g\u000e\fG.Y\u0005\u0003iE\u0012a!\u00118z%\u00164\u0017aA6fs\u000e\u0001Q#\u0001\u001d\u0011\u0005e\u0002eB\u0001\u001e?!\tY\u0014'D\u0001=\u0015\tid'\u0001\u0004=e>|GOP\u0005\u0003\u007fE\na\u0001\u0015:fI\u00164\u0017BA!C\u0005\u0019\u0019FO]5oO*\u0011q(M\u0001\u0005W\u0016L\b%\u0001\u0007qe\u0016\u0004XM\u001c3fI.+\u00170F\u0001G!\r\u0001t\tO\u0005\u0003\u0011F\u0012aa\u00149uS>t\u0017!\u00049sKB,g\u000eZ3e\u0017\u0016L\b%\u0001\tqe\u0016\u0004XM\u001c3TKB\f'/\u0019;pe\u0006\t\u0002O]3qK:$7+\u001a9be\u0006$xN\u001d\u0011\u0002\u0019\u0005dG/\u001a:oCRLg/Z:\u0016\u00039\u00032a\u0014+9\u001d\t\u0001&K\u0004\u0002<#&\t!'\u0003\u0002Tc\u00059\u0001/Y2lC\u001e,\u0017BA+W\u0005\u0011a\u0015n\u001d;\u000b\u0005M\u000b\u0014!D1mi\u0016\u0014h.\u0019;jm\u0016\u001c\b%\u0001\bwC2,XmQ8om\u0016\u0014H/\u001a:\u0016\u0003i\u0003B\u0001M.9;&\u0011A,\r\u0002\n\rVt7\r^5p]F\u0002\"AX0\r\u0001\u0011)\u0001\r\u0001b\u0001C\n\tA+\u0005\u0002cKB\u0011\u0001gY\u0005\u0003IF\u0012qAT8uQ&tw\r\u0005\u00021M&\u0011q-\r\u0002\u0004\u0003:L\u0018a\u0004<bYV,7i\u001c8wKJ$XM\u001d\u0011\u0002\u001fM$(/\u001b8h\u0007>tg/\u001a:uKJ,\u0012a\u001b\t\u0005amk\u0006(\u0001\ttiJLgnZ\"p]Z,'\u000f^3sA\u0005\u0019Am\\2\u0002\t\u0011|7\rI\u0001\tSN\u0004VO\u00197jGV\t\u0011\u000f\u0005\u00021e&\u00111/\r\u0002\b\u0005>|G.Z1o\u0003%I7\u000fU;cY&\u001c\u0007%A\u0004wKJ\u001c\u0018n\u001c8\u0002\u0011Y,'o]5p]\u0002\na\u0001P5oSRtDCD=|yvtx0!\u0001\u0002\u0004\u0005\u0015\u0011q\u0001\t\u0004u\u0002iV\"A\u0012\t\u000bU\u001a\u0002\u0019\u0001\u001d\t\u000b\u0011\u001b\u0002\u0019\u0001$\t\u000b)\u001b\u0002\u0019\u0001\u001d\t\u000b1\u001b\u0002\u0019\u0001(\t\u000ba\u001b\u0002\u0019\u0001.\t\u000b%\u001c\u0002\u0019A6\t\u000b5\u001c\u0002\u0019\u0001\u001d\t\u000b=\u001c\u0002\u0019A9\t\u000bU\u001c\u0002\u0019\u0001\u001d\u0002%\u0011,g-Y;miZ\u000bG.^3TiJLgnZ\u0001\u000be\u0016\fGm\u0015;sS:<Gc\u0001$\u0002\u0010!9\u0011\u0011C\u000bA\u0002\u0005M\u0011A\u0002:fC\u0012,'\u000fE\u0002{\u0003+I1!a\u0006$\u00051\u0019uN\u001c4jOJ+\u0017\rZ3s\u0003!\u0011X-\u00193Ge>lGcA/\u0002\u001e!9\u0011\u0011\u0003\fA\u0002\u0005M\u0011\u0001\u00043fM\u0006,H\u000e\u001e,bYV,WCAA\u0012!\r\u0001t)X\u0001\ti>\u001cFO]5oOR\t\u0001(A\u0006D_:4\u0017nZ#oiJL\bC\u0001>\u001b'\tQr\u0006\u0006\u0002\u0002,\u0005IQK\u0014#F\r&sU\tR\u000b\u0003\u0003k\u0001B!a\u000e\u0002B5\u0011\u0011\u0011\b\u0006\u0005\u0003w\ti$\u0001\u0003mC:<'BAA \u0003\u0011Q\u0017M^1\n\u0007\u0005\u000bI$\u0001\u0006V\u001d\u0012+e)\u0013(F\t\u0002\nAb\u001b8po:\u001cuN\u001c4jON,\"!!\u0013\u0011\u000f\u0005-\u0013Q\u000b\u001d\u0002Z5\u0011\u0011Q\n\u0006\u0005\u0003\u001f\n\t&\u0001\u0006d_:\u001cWO\u001d:f]RTA!a\u0015\u0002>\u0005!Q\u000f^5m\u0013\u0011\t9&!\u0014\u0003#\r{gnY;se\u0016tG\u000fS1tQ6\u000b\u0007\u000f\r\u0003\u0002\\\u0005}\u0003\u0003\u0002>\u0001\u0003;\u00022AXA0\t)\t\tgHA\u0001\u0002\u0003\u0015\t!\u0019\u0002\u0004?\u0012\n\u0014!D6o_^t7i\u001c8gS\u001e\u001c\b%A\u0007sK\u001eL7\u000f^3s\u000b:$(/\u001f\u000b\u0005\u0003S\ny\u0007E\u00021\u0003WJ1!!\u001c2\u0005\u0011)f.\u001b;\t\u000f\u0005E\u0004\u00051\u0001\u0002t\u0005)QM\u001c;ssB\"\u0011QOA=!\u0011Q\b!a\u001e\u0011\u0007y\u000bI\bB\u0006\u0002|\u0005=\u0014\u0011!A\u0001\u0006\u0003\t'aA0%e\u0005Ia-\u001b8e\u000b:$(/\u001f\u000b\u0005\u0003\u0003\u000bY\t\r\u0003\u0002\u0004\u0006\u001d\u0005\u0003\u0002>\u0001\u0003\u000b\u00032AXAD\t)\tI)IA\u0001\u0002\u0003\u0015\t!\u0019\u0002\u0004?\u0012\u001a\u0004\"B\u001b\"\u0001\u0004A\u0004"
)
public abstract class ConfigEntry {
   private final String key;
   private final Option prependedKey;
   private final String prependSeparator;
   private final List alternatives;
   private final Function1 valueConverter;
   private final Function1 stringConverter;
   private final String doc;
   private final boolean isPublic;
   private final String version;

   public static ConfigEntry findEntry(final String key) {
      return ConfigEntry$.MODULE$.findEntry(key);
   }

   public static void registerEntry(final ConfigEntry entry) {
      ConfigEntry$.MODULE$.registerEntry(entry);
   }

   public static String UNDEFINED() {
      return ConfigEntry$.MODULE$.UNDEFINED();
   }

   public String key() {
      return this.key;
   }

   public Option prependedKey() {
      return this.prependedKey;
   }

   public String prependSeparator() {
      return this.prependSeparator;
   }

   public List alternatives() {
      return this.alternatives;
   }

   public Function1 valueConverter() {
      return this.valueConverter;
   }

   public Function1 stringConverter() {
      return this.stringConverter;
   }

   public String doc() {
      return this.doc;
   }

   public boolean isPublic() {
      return this.isPublic;
   }

   public String version() {
      return this.version;
   }

   public abstract String defaultValueString();

   public Option readString(final ConfigReader reader) {
      Option maybePrependedValue = this.prependedKey().flatMap((key) -> reader.get(key));
      Option maybeValue = (Option)this.alternatives().foldLeft(reader.get(this.key()), (res, nextKey) -> res.orElse(() -> reader.get(nextKey)));
      Tuple2 var5 = new Tuple2(maybePrependedValue, maybeValue);
      if (var5 != null) {
         Option var6 = (Option)var5._1();
         Option var7 = (Option)var5._2();
         if (var6 instanceof Some) {
            Some var8 = (Some)var6;
            String prependedValue = (String)var8.value();
            if (var7 instanceof Some) {
               Some var10 = (Some)var7;
               String value = (String)var10.value();
               return new Some(prependedValue + this.prependSeparator() + value);
            }
         }
      }

      return maybeValue.orElse(() -> maybePrependedValue);
   }

   public abstract Object readFrom(final ConfigReader reader);

   public Option defaultValue() {
      return .MODULE$;
   }

   public String toString() {
      String var10000 = this.key();
      return "ConfigEntry(key=" + var10000 + ", defaultValue=" + this.defaultValueString() + ", doc=" + this.doc() + ", public=" + this.isPublic() + ", version=" + this.version() + ")";
   }

   public ConfigEntry(final String key, final Option prependedKey, final String prependSeparator, final List alternatives, final Function1 valueConverter, final Function1 stringConverter, final String doc, final boolean isPublic, final String version) {
      this.key = key;
      this.prependedKey = prependedKey;
      this.prependSeparator = prependSeparator;
      this.alternatives = alternatives;
      this.valueConverter = valueConverter;
      this.stringConverter = stringConverter;
      this.doc = doc;
      this.isPublic = isPublic;
      this.version = version;
      ConfigEntry$.MODULE$.registerEntry(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
