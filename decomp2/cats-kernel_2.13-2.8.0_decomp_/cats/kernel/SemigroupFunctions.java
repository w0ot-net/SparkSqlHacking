package cats.kernel;

import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}d!B\u0005\u000b\u0003\u0003y\u0001\"B\f\u0001\t\u0003A\u0002\"\u0002\u0018\u0001\t\u0003y\u0003\"B-\u0001\t\u0003Q\u0006\"B-\u0001\t\u0003\u0001\bbBA\u0005\u0001\u0011\u0005\u00111\u0002\u0005\b\u0003;\u0001A\u0011AA\u0010\u0011\u001d\tY\u0003\u0001C\u0001\u0003[Aq!a\u0017\u0001\t\u0003\tiF\u0001\nTK6LwM]8va\u001a+hn\u0019;j_:\u001c(BA\u0006\r\u0003\u0019YWM\u001d8fY*\tQ\"\u0001\u0003dCR\u001c8\u0001A\u000b\u0003!u\u0019\"\u0001A\t\u0011\u0005I)R\"A\n\u000b\u0003Q\tQa]2bY\u0006L!AF\n\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\t\u0011\u0004E\u0002\u001b\u0001mi\u0011A\u0003\t\u00039ua\u0001\u0001B\u0003\u001f\u0001\t\u0007qDA\u0001T+\t\u0001\u0003&\u0005\u0002\"IA\u0011!CI\u0005\u0003GM\u0011qAT8uQ&tw\rE\u0002\u001bK\u001dJ!A\n\u0006\u0003\u0013M+W.[4s_V\u0004\bC\u0001\u000f)\t\u0015ISD1\u0001+\u0005\u0005!\u0016CA\u0011,!\t\u0011B&\u0003\u0002.'\t\u0019\u0011I\\=\u0002\u000f\r|WNY5oKV\u0011\u0001g\r\u000b\u0004cU;FC\u0001\u001aS!\ta2\u0007B\u00055\u0005\u0001\u0006\t\u0011!b\u0001U\t\t\u0011\t\u000b\u00044me\u001a\u0005*\u0014\t\u0003%]J!\u0001O\n\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006GiZT\b\u0010\b\u0003%mJ!\u0001P\n\u0002\u0007%sG/\r\u0003%}\t#bBA C\u001b\u0005\u0001%BA!\u000f\u0003\u0019a$o\\8u}%\tA#M\u0003$\t\u0016;eI\u0004\u0002\u0013\u000b&\u0011aiE\u0001\u0005\u0019>tw-\r\u0003%}\t#\u0012'B\u0012J\u00152[eB\u0001\nK\u0013\tY5#A\u0003GY>\fG/\r\u0003%}\t#\u0012'B\u0012O\u001fF\u0003fB\u0001\nP\u0013\t\u00016#\u0001\u0004E_V\u0014G.Z\u0019\u0005Iy\u0012E\u0003C\u0003T\u0005\u0001\u000fA+\u0001\u0002fmB\u0019A$\b\u001a\t\u000bY\u0013\u0001\u0019\u0001\u001a\u0002\u0003aDQ\u0001\u0017\u0002A\u0002I\n\u0011!_\u0001\r[\u0006L(-Z\"p[\nLg.Z\u000b\u00037z#2\u0001\u00186p)\ti\u0006\u000e\u0005\u0002\u001d=\u0012IAg\u0001Q\u0001\u0002\u0003\u0015\rA\u000b\u0015\u0007=Z\u0002'\r\u001a42\u000b\rR4(\u0019\u001f2\t\u0011r$\tF\u0019\u0006G\u0011+5MR\u0019\u0005Iy\u0012E#M\u0003$\u0013*+7*\r\u0003%}\t#\u0012'B\u0012O\u001f\u001e\u0004\u0016\u0007\u0002\u0013?\u0005RAQaU\u0002A\u0004%\u00042\u0001H\u000f^\u0011\u0015Y7\u00011\u0001m\u0003\ty\u0007\u0010E\u0002\u0013[vK!A\\\n\u0003\r=\u0003H/[8o\u0011\u0015A6\u00011\u0001^+\t\tH\u000fF\u0003s\u0003\u0003\t\u0019\u0001\u0006\u0002t}B\u0011A\u0004\u001e\u0003\ni\u0011\u0001\u000b\u0011!AC\u0002)Bc\u0001\u001e\u001cwqjd\u0018'B\u0012;w]d\u0014\u0007\u0002\u0013?\u0005R\tTa\t#Fs\u001a\u000bD\u0001\n C)E*1%\u0013&|\u0017F\"AE\u0010\"\u0015c\u0015\u0019cjT?Qc\u0011!cH\u0011\u000b\t\u000bM#\u00019A@\u0011\u0007qi2\u000fC\u0003W\t\u0001\u00071\u000fC\u0004\u0002\u0006\u0011\u0001\r!a\u0002\u0002\u0005=L\bc\u0001\nng\u0006i\u0011n]\"p[6,H/\u0019;jm\u0016,B!!\u0004\u0002\u001cQ!\u0011qBA\u000b!\r\u0011\u0012\u0011C\u0005\u0004\u0003'\u0019\"a\u0002\"p_2,\u0017M\u001c\u0005\u0007'\u0016\u0001\u001d!a\u0006\u0011\tqi\u0012\u0011\u0004\t\u00049\u0005mA!\u0002\u001b\u0006\u0005\u0004Q\u0013\u0001D5t\u0013\u0012,W\u000e]8uK:$X\u0003BA\u0011\u0003S!B!a\u0004\u0002$!11K\u0002a\u0002\u0003K\u0001B\u0001H\u000f\u0002(A\u0019A$!\u000b\u0005\u000bQ2!\u0019\u0001\u0016\u0002\u0011\r|WNY5oK:+B!a\f\u00026Q1\u0011\u0011GA'\u0003#\"B!a\r\u0002JA\u0019A$!\u000e\u0005\u0013Q:\u0001\u0015!A\u0001\u0006\u0004Q\u0003fCA\u001bm\u0005e\u0012QHA!\u0003\u000b\nda\t\u001e<\u0003wa\u0014\u0007\u0002\u0013?\u0005R\tda\t#F\u0003\u007f1\u0015\u0007\u0002\u0013?\u0005R\tdaI%K\u0003\u0007Z\u0015\u0007\u0002\u0013?\u0005R\tda\t(P\u0003\u000f\u0002\u0016\u0007\u0002\u0013?\u0005RAaaU\u0004A\u0004\u0005-\u0003\u0003\u0002\u000f\u001e\u0003gAq!a\u0014\b\u0001\u0004\t\u0019$A\u0001b\u0011\u001d\t\u0019f\u0002a\u0001\u0003+\n\u0011A\u001c\t\u0004%\u0005]\u0013bAA-'\t\u0019\u0011J\u001c;\u0002!\r|WNY5oK\u0006cGn\u00149uS>tW\u0003BA0\u0003O\"B!!\u0019\u0002nQ!\u00111MA5!\u0011\u0011R.!\u001a\u0011\u0007q\t9\u0007B\u00035\u0011\t\u0007!\u0006\u0003\u0004T\u0011\u0001\u000f\u00111\u000e\t\u00059u\t)\u0007C\u0004\u0002p!\u0001\r!!\u001d\u0002\u0005\u0005\u001c\bCBA:\u0003s\n)GD\u0002?\u0003kJ1!a\u001e\u0014\u0003\u001d\u0001\u0018mY6bO\u0016LA!a\u001f\u0002~\ta\u0011\n^3sC\ndWm\u00148dK*\u0019\u0011qO\n"
)
public abstract class SemigroupFunctions {
   public Object combine(final Object x, final Object y, final Semigroup ev) {
      return ev.combine(x, y);
   }

   public Object maybeCombine(final Option ox, final Object y, final Semigroup ev) {
      Object var4;
      if (ox instanceof Some) {
         Some var6 = (Some)ox;
         Object x = var6.value();
         var4 = ev.combine(x, y);
      } else {
         if (!.MODULE$.equals(ox)) {
            throw new MatchError(ox);
         }

         var4 = y;
      }

      return var4;
   }

   public Object maybeCombine(final Object x, final Option oy, final Semigroup ev) {
      Object var4;
      if (oy instanceof Some) {
         Some var6 = (Some)oy;
         Object y = var6.value();
         var4 = ev.combine(x, y);
      } else {
         if (!.MODULE$.equals(oy)) {
            throw new MatchError(oy);
         }

         var4 = x;
      }

      return var4;
   }

   public boolean isCommutative(final Semigroup ev) {
      return ev instanceof CommutativeSemigroup;
   }

   public boolean isIdempotent(final Semigroup ev) {
      return ev instanceof Band;
   }

   public Object combineN(final Object a, final int n, final Semigroup ev) {
      return ev.combineN(a, n);
   }

   public Option combineAllOption(final IterableOnce as, final Semigroup ev) {
      return ev.combineAllOption(as);
   }

   public double combine$mDc$sp(final double x, final double y, final Semigroup ev) {
      return ev.combine$mcD$sp(x, y);
   }

   public float combine$mFc$sp(final float x, final float y, final Semigroup ev) {
      return ev.combine$mcF$sp(x, y);
   }

   public int combine$mIc$sp(final int x, final int y, final Semigroup ev) {
      return ev.combine$mcI$sp(x, y);
   }

   public long combine$mJc$sp(final long x, final long y, final Semigroup ev) {
      return ev.combine$mcJ$sp(x, y);
   }

   public double maybeCombine$mDc$sp(final Option ox, final double y, final Semigroup ev) {
      double var5;
      if (ox instanceof Some) {
         Some var8 = (Some)ox;
         double x = BoxesRunTime.unboxToDouble(var8.value());
         var5 = ev.combine$mcD$sp(x, y);
      } else {
         if (!.MODULE$.equals(ox)) {
            throw new MatchError(ox);
         }

         var5 = y;
      }

      return var5;
   }

   public float maybeCombine$mFc$sp(final Option ox, final float y, final Semigroup ev) {
      float var4;
      if (ox instanceof Some) {
         Some var6 = (Some)ox;
         float x = BoxesRunTime.unboxToFloat(var6.value());
         var4 = ev.combine$mcF$sp(x, y);
      } else {
         if (!.MODULE$.equals(ox)) {
            throw new MatchError(ox);
         }

         var4 = y;
      }

      return var4;
   }

   public int maybeCombine$mIc$sp(final Option ox, final int y, final Semigroup ev) {
      int var4;
      if (ox instanceof Some) {
         Some var6 = (Some)ox;
         int x = BoxesRunTime.unboxToInt(var6.value());
         var4 = ev.combine$mcI$sp(x, y);
      } else {
         if (!.MODULE$.equals(ox)) {
            throw new MatchError(ox);
         }

         var4 = y;
      }

      return var4;
   }

   public long maybeCombine$mJc$sp(final Option ox, final long y, final Semigroup ev) {
      long var5;
      if (ox instanceof Some) {
         Some var8 = (Some)ox;
         long x = BoxesRunTime.unboxToLong(var8.value());
         var5 = ev.combine$mcJ$sp(x, y);
      } else {
         if (!.MODULE$.equals(ox)) {
            throw new MatchError(ox);
         }

         var5 = y;
      }

      return var5;
   }

   public double maybeCombine$mDc$sp(final double x, final Option oy, final Semigroup ev) {
      double var5;
      if (oy instanceof Some) {
         Some var8 = (Some)oy;
         double y = BoxesRunTime.unboxToDouble(var8.value());
         var5 = ev.combine$mcD$sp(x, y);
      } else {
         if (!.MODULE$.equals(oy)) {
            throw new MatchError(oy);
         }

         var5 = x;
      }

      return var5;
   }

   public float maybeCombine$mFc$sp(final float x, final Option oy, final Semigroup ev) {
      float var4;
      if (oy instanceof Some) {
         Some var6 = (Some)oy;
         float y = BoxesRunTime.unboxToFloat(var6.value());
         var4 = ev.combine$mcF$sp(x, y);
      } else {
         if (!.MODULE$.equals(oy)) {
            throw new MatchError(oy);
         }

         var4 = x;
      }

      return var4;
   }

   public int maybeCombine$mIc$sp(final int x, final Option oy, final Semigroup ev) {
      int var4;
      if (oy instanceof Some) {
         Some var6 = (Some)oy;
         int y = BoxesRunTime.unboxToInt(var6.value());
         var4 = ev.combine$mcI$sp(x, y);
      } else {
         if (!.MODULE$.equals(oy)) {
            throw new MatchError(oy);
         }

         var4 = x;
      }

      return var4;
   }

   public long maybeCombine$mJc$sp(final long x, final Option oy, final Semigroup ev) {
      long var5;
      if (oy instanceof Some) {
         Some var8 = (Some)oy;
         long y = BoxesRunTime.unboxToLong(var8.value());
         var5 = ev.combine$mcJ$sp(x, y);
      } else {
         if (!.MODULE$.equals(oy)) {
            throw new MatchError(oy);
         }

         var5 = x;
      }

      return var5;
   }

   public double combineN$mDc$sp(final double a, final int n, final Semigroup ev) {
      return ev.combineN$mcD$sp(a, n);
   }

   public float combineN$mFc$sp(final float a, final int n, final Semigroup ev) {
      return ev.combineN$mcF$sp(a, n);
   }

   public int combineN$mIc$sp(final int a, final int n, final Semigroup ev) {
      return ev.combineN$mcI$sp(a, n);
   }

   public long combineN$mJc$sp(final long a, final int n, final Semigroup ev) {
      return ev.combineN$mcJ$sp(a, n);
   }
}
