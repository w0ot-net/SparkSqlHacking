package scala.reflect.internal.util;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014AAF\f\u0003A!AQ\u0005\u0001BC\u0002\u0013\u0005a\u0005\u0003\u0005+\u0001\t\u0005\t\u0015!\u0003(\u00111Y\u0003\u0001\"A\u0001\u0002\u0003\u0005\t\u0011\"\u0003-\u0011\u0015\u0001\u0004\u0001\"\u00012\u0011\u0015)\u0004\u0001\"\u00012\u0011\u001d1\u0004!!A\u0005B]Bq\u0001\u000f\u0001\u0002\u0002\u0013\u0005\u0013hB\u0003@/!\u0005\u0001IB\u0003\u0017/!\u0005\u0011\tC\u0003,\u0013\u0011\u0005Q\tC\u0003G\u0013\u0011\rq\tC\u0004K\u0013\t\u0007I\u0011A&\t\r1K\u0001\u0015!\u0003.\u0011\u001di\u0015B1A\u0005\u0002-CaAT\u0005!\u0002\u0013i\u0003bB(\n\u0005\u0004%\ta\u0013\u0005\u0007!&\u0001\u000b\u0011B\u0017\t\u000bEKAQ\u0001*\t\u000bUKAQ\u0001,\t\u000faK\u0011\u0011!C\u00033\"91,CA\u0001\n\u000ba&\u0001\u0003+sSN#\u0018\r^3\u000b\u0005aI\u0012\u0001B;uS2T!AG\u000e\u0002\u0011%tG/\u001a:oC2T!\u0001H\u000f\u0002\u000fI,g\r\\3di*\ta$A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001\t\u0003C\u0001\u0012$\u001b\u0005i\u0012B\u0001\u0013\u001e\u0005\u0019\te.\u001f,bY\u0006)a/\u00197vKV\tq\u0005\u0005\u0002#Q%\u0011\u0011&\b\u0002\u0004\u0013:$\u0018A\u0002<bYV,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003[=\u0002\"A\f\u0001\u000e\u0003]AQ!J\u0002A\u0002\u001d\nq![:L]><h.F\u00013!\t\u00113'\u0003\u00025;\t9!i\\8mK\u0006t\u0017\u0001\u00042p_2,\u0017M\u001c,bYV,\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003\u001d\na!Z9vC2\u001cHC\u0001\u001a;\u0011\u001dYt!!AA\u0002q\n1\u0001\u001f\u00132!\t\u0011S(\u0003\u0002?;\t\u0019\u0011I\\=\u0002\u0011Q\u0013\u0018n\u0015;bi\u0016\u0004\"AL\u0005\u0014\u0005%\u0011\u0005C\u0001\u0012D\u0013\t!UD\u0001\u0004B]f\u0014VM\u001a\u000b\u0002\u0001\u0006\t\"m\\8mK\u0006tGk\u001c+sSN#\u0018\r^3\u0015\u00055B\u0005\"B%\f\u0001\u0004\u0011\u0014!\u00012\u0002\u000fUs7N\\8x]V\tQ&\u0001\u0005V].twn\u001e8!\u0003\u00151\u0015\r\\:f\u0003\u00191\u0015\r\\:fA\u0005!AK];f\u0003\u0015!&/^3!\u0003EI7o\u00138po:$S\r\u001f;f]NLwN\u001c\u000b\u0003eMCQ\u0001\u0016\nA\u00025\nQ\u0001\n;iSN\faCY8pY\u0016\fgNV1mk\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0003e]CQ\u0001V\nA\u00025\n!\u0003[1tQ\u000e{G-\u001a\u0013fqR,gn]5p]R\u0011qG\u0017\u0005\u0006)R\u0001\r!L\u0001\u0011KF,\u0018\r\\:%Kb$XM\\:j_:$\"!X0\u0015\u0005Ir\u0006bB\u001e\u0016\u0003\u0003\u0005\r\u0001\u0010\u0005\u0006)V\u0001\r!\f"
)
public final class TriState {
   private final int value;

   public static boolean equals$extension(final int $this, final Object x$1) {
      return TriState$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final int $this) {
      TriState$ var10000 = TriState$.MODULE$;
      return Integer.hashCode($this);
   }

   public static boolean booleanValue$extension(final int $this) {
      return TriState$.MODULE$.booleanValue$extension($this);
   }

   public static boolean isKnown$extension(final int $this) {
      return TriState$.MODULE$.isKnown$extension($this);
   }

   public static int True() {
      return TriState$.MODULE$.True();
   }

   public static int False() {
      return TriState$.MODULE$.False();
   }

   public static int Unknown() {
      return TriState$.MODULE$.Unknown();
   }

   public static int booleanToTriState(final boolean b) {
      return TriState$.MODULE$.booleanToTriState(b);
   }

   public int value() {
      return this.value;
   }

   public boolean isKnown() {
      return TriState$.MODULE$.isKnown$extension(this.value());
   }

   public boolean booleanValue() {
      return TriState$.MODULE$.booleanValue$extension(this.value());
   }

   public int hashCode() {
      TriState$ var10000 = TriState$.MODULE$;
      return Integer.hashCode(this.value());
   }

   public boolean equals(final Object x$1) {
      return TriState$.MODULE$.equals$extension(this.value(), x$1);
   }

   public TriState(final int value) {
      this.value = value;
   }
}
