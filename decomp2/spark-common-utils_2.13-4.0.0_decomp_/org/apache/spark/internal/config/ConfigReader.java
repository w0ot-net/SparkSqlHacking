package org.apache.spark.internal.config;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import scala.Option;
import scala.Predef.;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dq!B\n\u0015\u0011\u0013yb!B\u0011\u0015\u0011\u0013\u0011\u0003\"B\u0015\u0002\t\u0003Q\u0003bB\u0016\u0002\u0005\u0004%I\u0001\f\u0005\u0007k\u0005\u0001\u000b\u0011B\u0017\u0007\u000b\u0005\"\u0002\u0001\u0007\u001c\t\u0011]*!\u0011!Q\u0001\naBQ!K\u0003\u0005\u0002mBQ!K\u0003\u0005\u0002yBqAU\u0003C\u0002\u0013%1\u000b\u0003\u0004]\u000b\u0001\u0006I\u0001\u0016\u0005\u0006;\u0016!\tA\u0018\u0005\u0006;\u0016!\ta\u0019\u0005\u0006O\u0016!\t\u0001\u001b\u0005\u0006U\u0016!\ta\u001b\u0005\u0006[\u0016!\tA\u001c\u0005\u0006i\u0016!\t!\u001e\u0005\u0006i\u0016!I\u0001\u001f\u0005\u0007\u007f\u0016!I!!\u0001\u0002\u0019\r{gNZ5h%\u0016\fG-\u001a:\u000b\u0005U1\u0012AB2p]\u001aLwM\u0003\u0002\u00181\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\u001a5\u0005)1\u000f]1sW*\u00111\u0004H\u0001\u0007CB\f7\r[3\u000b\u0003u\t1a\u001c:h\u0007\u0001\u0001\"\u0001I\u0001\u000e\u0003Q\u0011AbQ8oM&<'+Z1eKJ\u001c\"!A\u0012\u0011\u0005\u0011:S\"A\u0013\u000b\u0003\u0019\nQa]2bY\u0006L!\u0001K\u0013\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\tq$\u0001\u0004S\u000b\u001a{&+R\u000b\u0002[A\u0011afM\u0007\u0002_)\u0011\u0001'M\u0001\t[\u0006$8\r[5oO*\u0011!'J\u0001\u0005kRLG.\u0003\u00025_\t)!+Z4fq\u00069!+\u0012$`%\u0016\u00033CA\u0003$\u0003\u0011\u0019wN\u001c4\u0011\u0005\u0001J\u0014B\u0001\u001e\u0015\u00059\u0019uN\u001c4jOB\u0013xN^5eKJ$\"\u0001P\u001f\u0011\u0005\u0001*\u0001\"B\u001c\b\u0001\u0004ADC\u0001\u001f@\u0011\u00159\u0004\u00021\u0001A!\u0011\tUiR$\u000e\u0003\tS!AM\"\u000b\u0003\u0011\u000bAA[1wC&\u0011aI\u0011\u0002\u0004\u001b\u0006\u0004\bC\u0001%P\u001d\tIU\n\u0005\u0002KK5\t1J\u0003\u0002M=\u00051AH]8pizJ!AT\u0013\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0016K\u0001\u0004TiJLgn\u001a\u0006\u0003\u001d\u0016\n\u0001BY5oI&twm]\u000b\u0002)B!QKW$9\u001b\u00051&BA,Y\u0003\u001diW\u000f^1cY\u0016T!!W\u0013\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002\\-\n9\u0001*Y:i\u001b\u0006\u0004\u0018!\u00032j]\u0012LgnZ:!\u0003\u0011\u0011\u0017N\u001c3\u0015\u0007qz\u0016\rC\u0003a\u0017\u0001\u0007q)\u0001\u0004qe\u00164\u0017\u000e\u001f\u0005\u0006E.\u0001\r\u0001O\u0001\taJ|g/\u001b3feR\u0019A\bZ3\t\u000b\u0001d\u0001\u0019A$\t\u000b\u0019d\u0001\u0019\u0001!\u0002\rY\fG.^3t\u0003\u001d\u0011\u0017N\u001c3F]Z$\"\u0001P5\t\u000b\tl\u0001\u0019\u0001\u001d\u0002\u0015\tLg\u000eZ*zgR,W\u000e\u0006\u0002=Y\")!M\u0004a\u0001q\u0005\u0019q-\u001a;\u0015\u0005=\u0014\bc\u0001\u0013q\u000f&\u0011\u0011/\n\u0002\u0007\u001fB$\u0018n\u001c8\t\u000bM|\u0001\u0019A$\u0002\u0007-,\u00170\u0001\u0006tk\n\u001cH/\u001b;vi\u0016$\"a\u0012<\t\u000b]\u0004\u0002\u0019A$\u0002\u000b%t\u0007/\u001e;\u0015\u0007\u001dK(\u0010C\u0003x#\u0001\u0007q\tC\u0003|#\u0001\u0007A0\u0001\u0005vg\u0016$'+\u001a4t!\rAUpR\u0005\u0003}F\u00131aU3u\u000319W\r^(s\t\u00164\u0017-\u001e7u)\u0015y\u00171AA\u0003\u0011\u00159$\u00031\u00019\u0011\u0015\u0019(\u00031\u0001H\u0001"
)
public class ConfigReader {
   private final ConfigProvider conf;
   private final HashMap bindings;

   private HashMap bindings() {
      return this.bindings;
   }

   public ConfigReader bind(final String prefix, final ConfigProvider provider) {
      this.bindings().update(prefix, provider);
      return this;
   }

   public ConfigReader bind(final String prefix, final Map values) {
      return this.bind(prefix, (ConfigProvider)(new MapProvider(values)));
   }

   public ConfigReader bindEnv(final ConfigProvider provider) {
      return this.bind("env", provider);
   }

   public ConfigReader bindSystem(final ConfigProvider provider) {
      return this.bind("system", provider);
   }

   public Option get(final String key) {
      return this.conf.get(key).map((input) -> this.substitute(input));
   }

   public String substitute(final String input) {
      return this.substitute(input, (Set).MODULE$.Set().apply(scala.collection.immutable.Nil..MODULE$));
   }

   private String substitute(final String input, final Set usedRefs) {
      return input != null && input.contains("${") ? ConfigReader$.MODULE$.org$apache$spark$internal$config$ConfigReader$$REF_RE().replaceAllIn(input, (m) -> {
         String prefix = m.group(1);
         String name = m.group(2);
         String ref = prefix == null ? name : prefix + ":" + name;
         .MODULE$.require(!usedRefs.contains(ref), () -> "Circular reference in " + input + ": " + ref);
         String replacement = (String)this.bindings().get(prefix).flatMap((x$1) -> this.getOrDefault(x$1, name)).map((v) -> this.substitute(v, (Set)usedRefs.$plus(ref))).getOrElse(() -> m.matched());
         return scala.util.matching.Regex..MODULE$.quoteReplacement(replacement);
      }) : input;
   }

   private Option getOrDefault(final ConfigProvider conf, final String key) {
      return conf.get(key).orElse(() -> {
         ConfigEntry var4 = ConfigEntry$.MODULE$.findEntry(key);
         if (var4 instanceof ConfigEntryWithDefault var5) {
            return scala.Option..MODULE$.apply(var5.defaultValueString());
         } else if (var4 instanceof ConfigEntryWithDefaultString var6) {
            return scala.Option..MODULE$.apply(var6.defaultValueString());
         } else if (var4 instanceof ConfigEntryWithDefaultFunction var7) {
            return scala.Option..MODULE$.apply(var7.defaultValueString());
         } else if (var4 instanceof FallbackConfigEntry var8) {
            return this.getOrDefault(conf, var8.fallback().key());
         } else {
            return scala.None..MODULE$;
         }
      });
   }

   public ConfigReader(final ConfigProvider conf) {
      this.conf = conf;
      this.bindings = new HashMap();
      this.bind((String)null, (ConfigProvider)conf);
      this.bindEnv(new EnvProvider());
      this.bindSystem(new SystemProvider());
   }

   public ConfigReader(final Map conf) {
      this((ConfigProvider)(new MapProvider(conf)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
