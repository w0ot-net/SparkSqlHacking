package org.apache.spark.sql;

import java.util.NoSuchElementException;
import org.apache.spark.annotation.Stable;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.OptionalConfigEntry;
import scala.Option;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\u0005=c!\u0002\t\u0012\u0003\u0003Q\u0002\"B\u0011\u0001\t\u0003\u0011\u0003\"B\u0013\u0001\r\u00031\u0003\"B\u0013\u0001\t\u0003I\u0004\"B\u0013\u0001\t\u0003y\u0004BB\u0013\u0001\r\u0003\tR\tC\u0003^\u0001\u0019\u0005a\fC\u0003^\u0001\u0019\u0005q\u000e\u0003\u0004^\u0001\u0019\u0005\u0011c\u001d\u0005\u0007;\u00021\t!\u0005?\t\u000fu\u0003a\u0011A\t\u0002\u0010!A\u0011Q\u0004\u0001\u0007\u0002E\ty\u0002C\u0004\u0002$\u00011\t!!\n\t\u000f\u00055\u0002A\"\u0001\u00020!9\u0011Q\u0007\u0001\u0007\u0002\u0005]\u0002bBA\u001e\u0001\u0019\u0005\u0011Q\b\u0002\u000e%VtG/[7f\u0007>tg-[4\u000b\u0005I\u0019\u0012aA:rY*\u0011A#F\u0001\u0006gB\f'o\u001b\u0006\u0003-]\ta!\u00199bG\",'\"\u0001\r\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001Y\u0002C\u0001\u000f \u001b\u0005i\"\"\u0001\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0001j\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002GA\u0011A\u0005A\u0007\u0002#\u0005\u00191/\u001a;\u0015\u0007\u001dRs\u0007\u0005\u0002\u001dQ%\u0011\u0011&\b\u0002\u0005+:LG\u000fC\u0003,\u0005\u0001\u0007A&A\u0002lKf\u0004\"!\f\u001b\u000f\u00059\u0012\u0004CA\u0018\u001e\u001b\u0005\u0001$BA\u0019\u001a\u0003\u0019a$o\\8u}%\u00111'H\u0001\u0007!J,G-\u001a4\n\u0005U2$AB*ue&twM\u0003\u00024;!)\u0001H\u0001a\u0001Y\u0005)a/\u00197vKR\u0019qEO\u001e\t\u000b-\u001a\u0001\u0019\u0001\u0017\t\u000ba\u001a\u0001\u0019\u0001\u001f\u0011\u0005qi\u0014B\u0001 \u001e\u0005\u001d\u0011un\u001c7fC:$2a\n!B\u0011\u0015YC\u00011\u0001-\u0011\u0015AD\u00011\u0001C!\ta2)\u0003\u0002E;\t!Aj\u001c8h+\t15\u000bF\u0002(\u000frCQ\u0001S\u0003A\u0002%\u000bQ!\u001a8uef\u00042AS(R\u001b\u0005Y%B\u0001'N\u0003\u0019\u0019wN\u001c4jO*\u0011ajE\u0001\tS:$XM\u001d8bY&\u0011\u0001k\u0013\u0002\f\u0007>tg-[4F]R\u0014\u0018\u0010\u0005\u0002S'2\u0001A!\u0002+\u0006\u0005\u0004)&!\u0001+\u0012\u0005YK\u0006C\u0001\u000fX\u0013\tAVDA\u0004O_RD\u0017N\\4\u0011\u0005qQ\u0016BA.\u001e\u0005\r\te.\u001f\u0005\u0006q\u0015\u0001\r!U\u0001\u0004O\u0016$HC\u0001\u0017`\u0011\u0015Yc\u00011\u0001-Q\r1\u0011-\u001c\t\u00049\t$\u0017BA2\u001e\u0005\u0019!\bN]8xgB\u0011QM\u001b\b\u0003M\"t!aL4\n\u0003yI!![\u000f\u0002\u000fA\f7m[1hK&\u00111\u000e\u001c\u0002\u0017\u001d>\u001cVo\u00195FY\u0016lWM\u001c;Fq\u000e,\u0007\u000f^5p]*\u0011\u0011.H\u0011\u0002]\u0006\u0019\u0014N\u001a\u0011uQ\u0016\u00043.Z=!SN\u0004cn\u001c;!g\u0016$\b%\u00198eAQDWM]3!SN\u0004cn\u001c\u0011eK\u001a\fW\u000f\u001c;!m\u0006dW/\u001a\u000b\u0004YA\f\b\"B\u0016\b\u0001\u0004a\u0003\"\u0002:\b\u0001\u0004a\u0013a\u00023fM\u0006,H\u000e^\u000b\u0003iZ$\"!^<\u0011\u0005I3H!\u0002+\t\u0005\u0004)\u0006\"\u0002%\t\u0001\u0004A\bc\u0001&Pk\"\u001a\u0001\"\u0019>\"\u0003m\fQ#\u001b4!i\",\u0007e[3zA%\u001c\bE\\8uAM,G/F\u0002~\u0003\u000b!2A`A\u0004!\u0011ar0a\u0001\n\u0007\u0005\u0005QD\u0001\u0004PaRLwN\u001c\t\u0004%\u0006\u0015A!\u0002+\n\u0005\u0004)\u0006B\u0002%\n\u0001\u0004\tI\u0001E\u0003K\u0003\u0017\t\u0019!C\u0002\u0002\u000e-\u00131c\u00149uS>t\u0017\r\\\"p]\u001aLw-\u00128uef,B!!\u0005\u0002\u0016Q1\u00111CA\f\u00037\u00012AUA\u000b\t\u0015!&B1\u0001V\u0011\u0019A%\u00021\u0001\u0002\u001aA!!jTA\n\u0011\u0019\u0011(\u00021\u0001\u0002\u0014\u0005A1m\u001c8uC&t7\u000fF\u0002=\u0003CAQaK\u0006A\u00021\naaZ3u\u00032dWCAA\u0014!\u0015i\u0013\u0011\u0006\u0017-\u0013\r\tYC\u000e\u0002\u0004\u001b\u0006\u0004\u0018!C4fi>\u0003H/[8o)\u0011\t\t$a\r\u0011\u0007qyH\u0006C\u0003,\u001b\u0001\u0007A&A\u0003v]N,G\u000fF\u0002(\u0003sAQa\u000b\bA\u00021\nA\"[:N_\u0012Lg-[1cY\u0016$2\u0001PA \u0011\u0015Ys\u00021\u0001-Q\r\u0001\u00111\t\t\u0005\u0003\u000b\nY%\u0004\u0002\u0002H)\u0019\u0011\u0011J\n\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002N\u0005\u001d#AB*uC\ndW\r"
)
public abstract class RuntimeConfig {
   public abstract void set(final String key, final String value);

   public void set(final String key, final boolean value) {
      this.set(key, Boolean.toString(value));
   }

   public void set(final String key, final long value) {
      this.set(key, Long.toString(value));
   }

   public abstract void set(final ConfigEntry entry, final Object value);

   public abstract String get(final String key) throws NoSuchElementException;

   public abstract String get(final String key, final String default);

   public abstract Object get(final ConfigEntry entry) throws NoSuchElementException;

   public abstract Option get(final OptionalConfigEntry entry);

   public abstract Object get(final ConfigEntry entry, final Object default);

   public abstract boolean contains(final String key);

   public abstract Map getAll();

   public abstract Option getOption(final String key);

   public abstract void unset(final String key);

   public abstract boolean isModifiable(final String key);
}
