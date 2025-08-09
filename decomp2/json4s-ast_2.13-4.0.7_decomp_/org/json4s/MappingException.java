package org.json4s;

import java.io.Serializable;
import scala..less.colon.less.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r4A!\u0005\n\u0001/!Aa\u0005\u0001BC\u0002\u0013\u0005q\u0005\u0003\u00051\u0001\t\u0005\t\u0015!\u0003)\u0011!\t\u0004A!b\u0001\n\u0003\u0011\u0004\u0002C\u001a\u0001\u0005\u0003\u0005\u000b\u0011\u0002\r\t\u000bQ\u0002A\u0011A\u001b\t\u000bQ\u0002A\u0011\u0001\u001e\b\u000bq\u0012\u0002\u0012A\u001f\u0007\u000bE\u0011\u0002\u0012\u0001 \t\u000bQBA\u0011A&\u0007\t1C\u0001!\u0014\u0005\t\u001d*\u0011)\u0019!C\u0001\u001f\"A1K\u0003B\u0001B\u0003%\u0001\u000bC\u00052\u0015\t\u0005\t\u0015!\u0003\u0019\u0007!)AG\u0003C\u0001)\")AG\u0003C\u00013\"91\fCA\u0001\n\u0013a&\u0001E'baBLgnZ#yG\u0016\u0004H/[8o\u0015\t\u0019B#\u0001\u0004kg>tGg\u001d\u0006\u0002+\u0005\u0019qN]4\u0004\u0001M\u0011\u0001\u0001\u0007\t\u00033\rr!A\u0007\u0011\u000f\u0005mqR\"\u0001\u000f\u000b\u0005u1\u0012A\u0002\u001fs_>$h(C\u0001 \u0003\u0015\u00198-\u00197b\u0013\t\t#%A\u0004qC\u000e\\\u0017mZ3\u000b\u0003}I!\u0001J\u0013\u0003\u0013\u0015C8-\u001a9uS>t'BA\u0011#\u0003\ri7oZ\u000b\u0002QA\u0011\u0011&\f\b\u0003U-\u0002\"a\u0007\u0012\n\u00051\u0012\u0013A\u0002)sK\u0012,g-\u0003\u0002/_\t11\u000b\u001e:j]\u001eT!\u0001\f\u0012\u0002\t5\u001cx\rI\u0001\u0006G\u0006,8/Z\u000b\u00021\u000511-Y;tK\u0002\na\u0001P5oSRtDc\u0001\u001c9sA\u0011q\u0007A\u0007\u0002%!)a%\u0002a\u0001Q!)\u0011'\u0002a\u00011Q\u0011ag\u000f\u0005\u0006M\u0019\u0001\r\u0001K\u0001\u0011\u001b\u0006\u0004\b/\u001b8h\u000bb\u001cW\r\u001d;j_:\u0004\"a\u000e\u0005\u0014\u0007!y4\t\u0005\u0002A\u00036\t!%\u0003\u0002CE\t1\u0011I\\=SK\u001a\u0004\"\u0001R%\u000e\u0003\u0015S!AR$\u0002\u0005%|'\"\u0001%\u0002\t)\fg/Y\u0005\u0003\u0015\u0016\u0013AbU3sS\u0006d\u0017N_1cY\u0016$\u0012!\u0010\u0002\u0006\u001bVdG/[\n\u0003\u0015Y\na!\u001a:s_J\u001cX#\u0001)\u0011\u0007e\tf'\u0003\u0002SK\t\u00191+Z9\u0002\u000f\u0015\u0014(o\u001c:tAQ\u0019Qk\u0016-\u0011\u0005YSQ\"\u0001\u0005\t\u000b9s\u0001\u0019\u0001)\t\u000bEr\u0001\u0019\u0001\r\u0015\u0005US\u0006\"\u0002(\u0010\u0001\u0004\u0001\u0016\u0001D<sSR,'+\u001a9mC\u000e,G#A/\u0011\u0005y\u000bW\"A0\u000b\u0005\u0001<\u0015\u0001\u00027b]\u001eL!AY0\u0003\r=\u0013'.Z2u\u0001"
)
public class MappingException extends Exception {
   private final String msg;
   private final Exception cause;

   public String msg() {
      return this.msg;
   }

   public Exception cause() {
      return this.cause;
   }

   public MappingException(final String msg, final Exception cause) {
      super(msg, cause);
      this.msg = msg;
      this.cause = cause;
   }

   public MappingException(final String msg) {
      this(msg, (Exception)null);
   }

   public static class Multi extends MappingException {
      private final Seq errors;

      public Seq errors() {
         return this.errors;
      }

      public Multi(final Seq errors, final Exception cause) {
         super(((IterableOnceOps)errors.map(new Serializable() {
            private static final long serialVersionUID = 0L;

            public final String apply(final MappingException x$1) {
               return x$1.msg();
            }
         })).mkString(", "), cause);
         this.errors = errors;
      }

      public Multi(final Seq errors) {
         this(errors, (Exception)errors.headOption().orNull(.MODULE$.refl()));
      }
   }
}
