package org.apache.spark.sql;

import java.util.Locale;
import java.util.Map;
import org.apache.spark.annotation.Stable;
import org.apache.spark.util.ArrayImplicits.;
import scala.Option;
import scala.Some;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\u0005]g!B\u0011#\u0003\u0003Y\u0003\"\u0002\u001a\u0001\t\u0003\u0019\u0004\"\u0002\u001c\u0001\t\u00039\u0004\"\u0002\u001c\u0001\t\u0003y\u0004\"\u0002\u001c\u0001\t\u0003i\u0005\"\u0002\u001c\u0001\t\u0003\u0019\u0006\"\u0002\u001c\u0001\t\u0003i\u0006\"\u0002\u001c\u0001\t\u0003\u0001\u0007\"\u0002\u001c\u0001\t\u0003\u0019\u0007\"\u0002\u001c\u0001\t\u0003I\u0007\"\u0002\u001c\u0001\t\u0003a\u0007\"B8\u0001\t\u0013\u0001\b\"\u0002\u001c\u0001\r#)\b\"\u0002\u001c\u0001\r#9\b\"\u0002>\u0001\r\u0003Y\bB\u0002>\u0001\r\u0003\t\u0019\u0001\u0003\u0004{\u0001\u0019\u0005\u0011Q\u0002\u0005\u0007u\u0002!\t!!\u0005\t\ri\u0004A\u0011AA\f\u0011\u0019Q\bA\"\u0001\u0002\u001e!1!\u0010\u0001D\u0001\u0003GAaA\u001f\u0001\u0005\u0002\u0005%\u0002B\u0002>\u0001\r\u0003\ty\u0003\u0003\u0004{\u0001\u0019\u0005\u0011Q\u0007\u0005\u0007u\u00021\t!a\u0010\t\ri\u0004A\u0011AA#\u0011\u0019Q\b\u0001\"\u0001\u0002L!1!\u0010\u0001C\u0001\u0003OBq!a\u001c\u0001\r#\t\t\bC\u0004\u0002\u0000\u0001!\t!!!\t\u000f\u0005}\u0004\u0001\"\u0001\u0002 \"9\u0011q\u0010\u0001\u0007\u0002\u00055\u0006bBA@\u0001\u0019\u0005\u00111\u0018\u0002\u0015\t\u0006$\u0018M\u0012:b[\u0016t\u0015MR;oGRLwN\\:\u000b\u0005\r\"\u0013aA:rY*\u0011QEJ\u0001\u0006gB\f'o\u001b\u0006\u0003O!\na!\u00199bG\",'\"A\u0015\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001a\u0003CA\u00171\u001b\u0005q#\"A\u0018\u0002\u000bM\u001c\u0017\r\\1\n\u0005Er#AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002iA\u0011Q\u0007A\u0007\u0002E\u0005!AM]8q)\u0005A\u0004CA\u001d=\u001d\t)$(\u0003\u0002<E\u00059\u0001/Y2lC\u001e,\u0017BA\u001f?\u0005%!\u0015\r^1Ge\u0006lWM\u0003\u0002<EQ\u0011\u0001\b\u0011\u0005\u0006\u0003\u000e\u0001\rAQ\u0001\u0004Q><\bCA\"K\u001d\t!\u0005\n\u0005\u0002F]5\taI\u0003\u0002HU\u00051AH]8pizJ!!\u0013\u0018\u0002\rA\u0013X\rZ3g\u0013\tYEJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0013:\"\"\u0001\u000f(\t\u000b=#\u0001\u0019\u0001)\u0002\t\r|Gn\u001d\t\u0004[E\u0013\u0015B\u0001*/\u0005\u0015\t%O]1z)\tAD\u000bC\u0003P\u000b\u0001\u0007Q\u000bE\u0002W5\ns!aV-\u000f\u0005\u0015C\u0016\"A\u0018\n\u0005mr\u0013BA.]\u0005\r\u0019V-\u001d\u0006\u0003w9\"2\u0001\u000f0`\u0011\u0015\te\u00011\u0001C\u0011\u0015ye\u00011\u0001Q)\rA\u0014M\u0019\u0005\u0006\u0003\u001e\u0001\rA\u0011\u0005\u0006\u001f\u001e\u0001\r!\u0016\u000b\u0003q\u0011DQ!\u001a\u0005A\u0002\u0019\f1\"\\5o\u001d>tg*\u001e7mgB\u0011QfZ\u0005\u0003Q:\u00121!\u00138u)\rA$n\u001b\u0005\u0006K&\u0001\rA\u001a\u0005\u0006\u001f&\u0001\r\u0001\u0015\u000b\u0004q5t\u0007\"B3\u000b\u0001\u00041\u0007\"B(\u000b\u0001\u0004)\u0016!\u0004;p\u001b&tgj\u001c8Ok2d7\u000f\u0006\u0002riB\u0019QF\u001d4\n\u0005Mt#AB(qi&|g\u000eC\u0003B\u0017\u0001\u0007!\t\u0006\u00029m\")Q\r\u0004a\u0001cR\u0019\u0001\b_=\t\u000b\u0015l\u0001\u0019A9\t\u000b=k\u0001\u0019A+\u0002\t\u0019LG\u000e\u001c\u000b\u0003qqDQ! \bA\u0002y\fQA^1mk\u0016\u0004\"!L@\n\u0007\u0005\u0005aF\u0001\u0003M_:<Gc\u0001\u001d\u0002\u0006!1Qp\u0004a\u0001\u0003\u000f\u00012!LA\u0005\u0013\r\tYA\f\u0002\u0007\t>,(\r\\3\u0015\u0007a\ny\u0001C\u0003~!\u0001\u0007!\tF\u00039\u0003'\t)\u0002C\u0003~#\u0001\u0007a\u0010C\u0003P#\u0001\u0007\u0001\u000bF\u00039\u00033\tY\u0002\u0003\u0004~%\u0001\u0007\u0011q\u0001\u0005\u0006\u001fJ\u0001\r\u0001\u0015\u000b\u0006q\u0005}\u0011\u0011\u0005\u0005\u0006{N\u0001\rA \u0005\u0006\u001fN\u0001\r!\u0016\u000b\u0006q\u0005\u0015\u0012q\u0005\u0005\u0007{R\u0001\r!a\u0002\t\u000b=#\u0002\u0019A+\u0015\u000ba\nY#!\f\t\u000bu,\u0002\u0019\u0001\"\t\u000b=+\u0002\u0019\u0001)\u0015\u000ba\n\t$a\r\t\u000bu4\u0002\u0019\u0001\"\t\u000b=3\u0002\u0019A+\u0015\u0007a\n9\u0004\u0003\u0004~/\u0001\u0007\u0011\u0011\b\t\u0004[\u0005m\u0012bAA\u001f]\t9!i\\8mK\u0006tG#\u0002\u001d\u0002B\u0005\r\u0003BB?\u0019\u0001\u0004\tI\u0004C\u0003P1\u0001\u0007Q\u000bF\u00039\u0003\u000f\nI\u0005\u0003\u0004~3\u0001\u0007\u0011\u0011\b\u0005\u0006\u001ff\u0001\r\u0001\u0015\u000b\u0004q\u00055\u0003bBA(5\u0001\u0007\u0011\u0011K\u0001\tm\u0006dW/Z'baB9\u00111KA/\u0005\u0006\u0005TBAA+\u0015\u0011\t9&!\u0017\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u00037\nAA[1wC&!\u0011qLA+\u0005\ri\u0015\r\u001d\t\u0004[\u0005\r\u0014bAA3]\t\u0019\u0011I\\=\u0015\u0007a\nI\u0007C\u0004\u0002Pm\u0001\r!a\u001b\u0011\r\r\u000biGQA1\u0013\r\ty\u0006T\u0001\bM&dG.T1q)\rA\u00141\u000f\u0005\b\u0003kb\u0002\u0019AA<\u0003\u00191\u0018\r\\;fgB!aKWA=!\u0019i\u00131\u0010\"\u0002b%\u0019\u0011Q\u0010\u0018\u0003\rQ+\b\u000f\\33\u0003\u001d\u0011X\r\u001d7bG\u0016,B!a!\u0002\u0014R)\u0001(!\"\u0002\n\"1\u0011qQ\u000fA\u0002\t\u000b1aY8m\u0011\u001d\tY)\ba\u0001\u0003\u001b\u000b1B]3qY\u0006\u001cW-\\3oiBA\u00111KA/\u0003\u001f\u000by\t\u0005\u0003\u0002\u0012\u0006ME\u0002\u0001\u0003\b\u0003+k\"\u0019AAL\u0005\u0005!\u0016\u0003BAM\u0003C\u00022!LAN\u0013\r\tiJ\f\u0002\b\u001d>$\b.\u001b8h+\u0011\t\t+a+\u0015\u000ba\n\u0019+!*\t\u000b=s\u0002\u0019\u0001)\t\u000f\u0005-e\u00041\u0001\u0002(BA\u00111KA/\u0003S\u000bI\u000b\u0005\u0003\u0002\u0012\u0006-FaBAK=\t\u0007\u0011qS\u000b\u0005\u0003_\u000bI\fF\u00039\u0003c\u000b\u0019\f\u0003\u0004\u0002\b~\u0001\rA\u0011\u0005\b\u0003\u0017{\u0002\u0019AA[!\u001d\u0019\u0015QNA\\\u0003o\u0003B!!%\u0002:\u00129\u0011QS\u0010C\u0002\u0005]U\u0003BA_\u0003\u000f$R\u0001OA`\u0003\u0003DQa\u0014\u0011A\u0002UCq!a#!\u0001\u0004\t\u0019\rE\u0004D\u0003[\n)-!2\u0011\t\u0005E\u0015q\u0019\u0003\b\u0003+\u0003#\u0019AALQ\r\u0001\u00111\u001a\t\u0005\u0003\u001b\f\u0019.\u0004\u0002\u0002P*\u0019\u0011\u0011\u001b\u0013\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002V\u0006='AB*uC\ndW\r"
)
public abstract class DataFrameNaFunctions {
   public Dataset drop() {
      return this.drop("any");
   }

   public Dataset drop(final String how) {
      return this.drop(this.toMinNonNulls(how));
   }

   public Dataset drop(final String[] cols) {
      return this.drop((Seq).MODULE$.SparkArrayOps(cols).toImmutableArraySeq());
   }

   public Dataset drop(final Seq cols) {
      return this.drop(cols.size(), cols);
   }

   public Dataset drop(final String how, final String[] cols) {
      return this.drop((String)how, (Seq).MODULE$.SparkArrayOps(cols).toImmutableArraySeq());
   }

   public Dataset drop(final String how, final Seq cols) {
      return this.drop(this.toMinNonNulls(how), cols);
   }

   public Dataset drop(final int minNonNulls) {
      return this.drop(scala.Option..MODULE$.apply(BoxesRunTime.boxToInteger(minNonNulls)));
   }

   public Dataset drop(final int minNonNulls, final String[] cols) {
      return this.drop(minNonNulls, (Seq).MODULE$.SparkArrayOps(cols).toImmutableArraySeq());
   }

   public Dataset drop(final int minNonNulls, final Seq cols) {
      return this.drop(scala.Option..MODULE$.apply(BoxesRunTime.boxToInteger(minNonNulls)), cols);
   }

   private Option toMinNonNulls(final String how) {
      String var3 = how.toLowerCase(Locale.ROOT);
      switch (var3 == null ? 0 : var3.hashCode()) {
         case 96673:
            if ("all".equals(var3)) {
               return new Some(BoxesRunTime.boxToInteger(1));
            }
            break;
         case 96748:
            if ("any".equals(var3)) {
               return scala.None..MODULE$;
            }
      }

      throw new IllegalArgumentException("how (" + how + ") must be 'any' or 'all'");
   }

   public abstract Dataset drop(final Option minNonNulls);

   public abstract Dataset drop(final Option minNonNulls, final Seq cols);

   public abstract Dataset fill(final long value);

   public abstract Dataset fill(final double value);

   public abstract Dataset fill(final String value);

   public Dataset fill(final long value, final String[] cols) {
      return this.fill(value, (Seq).MODULE$.SparkArrayOps(cols).toImmutableArraySeq());
   }

   public Dataset fill(final double value, final String[] cols) {
      return this.fill(value, (Seq).MODULE$.SparkArrayOps(cols).toImmutableArraySeq());
   }

   public abstract Dataset fill(final long value, final Seq cols);

   public abstract Dataset fill(final double value, final Seq cols);

   public Dataset fill(final String value, final String[] cols) {
      return this.fill(value, (Seq).MODULE$.SparkArrayOps(cols).toImmutableArraySeq());
   }

   public abstract Dataset fill(final String value, final Seq cols);

   public abstract Dataset fill(final boolean value);

   public abstract Dataset fill(final boolean value, final Seq cols);

   public Dataset fill(final boolean value, final String[] cols) {
      return this.fill(value, (Seq).MODULE$.SparkArrayOps(cols).toImmutableArraySeq());
   }

   public Dataset fill(final Map valueMap) {
      return this.fillMap(scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(valueMap).asScala().toSeq());
   }

   public Dataset fill(final scala.collection.immutable.Map valueMap) {
      return this.fillMap(valueMap.toSeq());
   }

   public abstract Dataset fillMap(final Seq values);

   public Dataset replace(final String col, final Map replacement) {
      return this.replace(col, scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(replacement).asScala().toMap(scala..less.colon.less..MODULE$.refl()));
   }

   public Dataset replace(final String[] cols, final Map replacement) {
      return this.replace((Seq).MODULE$.SparkArrayOps(cols).toImmutableArraySeq(), (scala.collection.immutable.Map)scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(replacement).asScala().toMap(scala..less.colon.less..MODULE$.refl()));
   }

   public abstract Dataset replace(final String col, final scala.collection.immutable.Map replacement);

   public abstract Dataset replace(final Seq cols, final scala.collection.immutable.Map replacement);
}
