package org.json4s;

import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00154q\u0001D\u0007\u0011\u0002\u0007\u0005!\u0003C\u0003\u001b\u0001\u0011\u00051\u0004C\u0003 \u0001\u0019\u0005\u0001\u0005C\u0003:\u0001\u0011\u0005!hB\u0003G\u001b!\u0005qIB\u0003\r\u001b!\u0005\u0001\nC\u0003J\u000b\u0011\u0005!\nC\u0003L\u000b\u0011\u0005A\nC\u0003T\u000b\u0011\u0005A\u000bC\u0003\\\u000b\u0011\u0005A\fC\u0004b\u000b\t\u0007I1\u00012\t\r\u0011,\u0001\u0015!\u0003d\u00055Q5o\u001c8LKf<&/\u001b;fe*\u0011abD\u0001\u0007UN|g\u000eN:\u000b\u0003A\t1a\u001c:h\u0007\u0001)\"a\u0005\u0019\u0014\u0005\u0001!\u0002CA\u000b\u0019\u001b\u00051\"\"A\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005e1\"AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u00029A\u0011Q#H\u0005\u0003=Y\u0011A!\u00168ji\u0006)qO]5uKR\u0011\u0011\u0005\f\t\u0003E%r!aI\u0014\u0011\u0005\u00112R\"A\u0013\u000b\u0005\u0019\n\u0012A\u0002\u001fs_>$h(\u0003\u0002)-\u00051\u0001K]3eK\u001aL!AK\u0016\u0003\rM#(/\u001b8h\u0015\tAc\u0003C\u0003.\u0005\u0001\u0007a&A\u0002lKf\u0004\"a\f\u0019\r\u0001\u0011)\u0011\u0007\u0001b\u0001e\t\t\u0011)\u0005\u00024mA\u0011Q\u0003N\u0005\u0003kY\u0011qAT8uQ&tw\r\u0005\u0002\u0016o%\u0011\u0001H\u0006\u0002\u0004\u0003:L\u0018!C2p]R\u0014\u0018-\\1q+\tYt\b\u0006\u0002=\u0003B\u0019Q\b\u0001 \u000e\u00035\u0001\"aL \u0005\u000b\u0001\u001b!\u0019\u0001\u001a\u0003\u0003\tCQAQ\u0002A\u0002\r\u000b\u0011A\u001a\t\u0005+\u0011sd&\u0003\u0002F-\tIa)\u001e8di&|g.M\u0001\u000e\u0015N|gnS3z/JLG/\u001a:\u0011\u0005u*1CA\u0003\u0015\u0003\u0019a\u0014N\\5u}Q\tq)A\u0003baBd\u00170\u0006\u0002N!R\u0011a*\u0015\t\u0004{\u0001y\u0005CA\u0018Q\t\u0015\ttA1\u00013\u0011\u0015\u0011v\u0001q\u0001O\u0003\u0005\t\u0017AA8g+\t)\u0006\f\u0006\u0002W3B\u0019Q\bA,\u0011\u0005=BF!B\u0019\t\u0005\u0004\u0011\u0004\"\u0002\"\t\u0001\u0004Q\u0006\u0003B\u000bE/\u0006\nAB\u001a:p[R{7\u000b\u001e:j]\u001e,\"!\u00181\u0016\u0003y\u00032!\u0010\u0001`!\ty\u0003\rB\u00032\u0013\t\u0007!'\u0001\u0004tiJLgnZ\u000b\u0002GB\u0019Q\bA\u0011\u0002\u000fM$(/\u001b8hA\u0001"
)
public interface JsonKeyWriter {
   static JsonKeyWriter string() {
      return JsonKeyWriter$.MODULE$.string();
   }

   static JsonKeyWriter fromToString() {
      return JsonKeyWriter$.MODULE$.fromToString();
   }

   static JsonKeyWriter of(final Function1 f) {
      return JsonKeyWriter$.MODULE$.of(f);
   }

   static JsonKeyWriter apply(final JsonKeyWriter a) {
      return JsonKeyWriter$.MODULE$.apply(a);
   }

   String write(final Object key);

   default JsonKeyWriter contramap(final Function1 f) {
      return new JsonKeyWriter(f) {
         // $FF: synthetic field
         private final JsonKeyWriter $outer;
         private final Function1 f$1;

         public JsonKeyWriter contramap(final Function1 f) {
            return JsonKeyWriter.super.contramap(f);
         }

         public String write(final Object key) {
            return this.$outer.write(this.f$1.apply(key));
         }

         public {
            if (JsonKeyWriter.this == null) {
               throw null;
            } else {
               this.$outer = JsonKeyWriter.this;
               this.f$1 = f$1;
               JsonKeyWriter.$init$(this);
            }
         }
      };
   }

   static void $init$(final JsonKeyWriter $this) {
   }
}
