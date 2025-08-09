package breeze.linalg;

import breeze.generic.UFunc;
import breeze.storage.Zero;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uq!B\u0004\t\u0011\u0003ia!B\b\t\u0011\u0003\u0001\u0002\"B\u000f\u0002\t\u0003q\u0002\"B\u0010\u0002\t\u0007\u0001\u0003\"\u0002*\u0002\t\u0007\u0019\u0006\"B5\u0002\t\u0007Q\u0007\"B=\u0002\t\u0007Q\u0018\u0001\u0002;jY\u0016T!!\u0003\u0006\u0002\r1Lg.\u00197h\u0015\u0005Y\u0011A\u00022sK\u0016TXm\u0001\u0001\u0011\u00059\tQ\"\u0001\u0005\u0003\tQLG.Z\n\u0004\u0003E9\u0002C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"AB!osJ+g\r\u0005\u0002\u001975\t\u0011D\u0003\u0002\u001b\u0015\u00059q-\u001a8fe&\u001c\u0017B\u0001\u000f\u001a\u0005\u0015)f)\u001e8d\u0003\u0019a\u0014N\\5u}Q\tQ\"A\u0007uS2,w\f\u0012,`\u00136\u0004HNM\u000b\u0003C-\"BAI\u001c@\u000fB)1\u0005\n\u00145M5\t\u0011!\u0003\u0002&7\t)\u0011*\u001c9meA\u0019abJ\u0015\n\u0005!B!a\u0003#f]N,g+Z2u_J\u0004\"AK\u0016\r\u0001\u0011)Af\u0001b\u0001[\t\tA+\u0005\u0002/cA\u0011!cL\u0005\u0003aM\u0011qAT8uQ&tw\r\u0005\u0002\u0013e%\u00111g\u0005\u0002\u0004\u0003:L\bC\u0001\n6\u0013\t14CA\u0002J]RDQ\u0001O\u0002A\u0004e\n!a\u0019;\u0011\u0007ij\u0014&D\u0001<\u0015\ta4#A\u0004sK\u001adWm\u0019;\n\u0005yZ$\u0001C\"mCN\u001cH+Y4\t\u000b\u0001\u001b\u00019A!\u0002\u0003i\u00042AQ#*\u001b\u0005\u0019%B\u0001#\u000b\u0003\u001d\u0019Ho\u001c:bO\u0016L!AR\"\u0003\ti+'o\u001c\u0005\u0006\u0011\u000e\u0001\u001d!S\u0001\u0004g\u0016$\b\u0003\u0002&QM\u0019r!a\u0013(\u000e\u00031S!!\u0014\u0005\u0002\u0013=\u0004XM]1u_J\u001c\u0018BA(M\u0003\u0015y\u0005oU3u\u0013\t\t6D\u0001\u0007J]Bc\u0017mY3J[Bd''A\u0007uS2,w\f\u0012,`\u00136\u0004HnM\u000b\u0003)j#R!\u00160bI\u001e\u0004ba\t,YiQZ\u0016BA,\u001c\u0005\u0015IU\u000e\u001d74!\rqq%\u0017\t\u0003Ui#Q\u0001\f\u0003C\u00025\u00022A\u0004/Z\u0013\ti\u0006BA\u0006EK:\u001cX-T1ue&D\bbB0\u0005\u0003\u0003\u0005\u001d\u0001Y\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004c\u0001\u001e>3\"9!\rBA\u0001\u0002\b\u0019\u0017AC3wS\u0012,gnY3%eA\u0019!)R-\t\u000b\u0015$\u00019\u00014\u0002\u000b%l\u0007\u000f\u001c\u001a\u0011\u000b\r\"\u0003\f\u000e-\t\u000b!#\u00019\u00015\u0011\t)\u0003\u0006\fW\u0001\u000ei&dWm\u0018#N?&k\u0007\u000f\u001c\u001a\u0016\u0005-|G\u0003\u00027qgZ\u0004Ra\t\u0013ni5\u00042A\u0004/o!\tQs\u000eB\u0003-\u000b\t\u0007Q\u0006C\u0004r\u000b\u0005\u0005\t9\u0001:\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007E\u0002;{9Dq\u0001^\u0003\u0002\u0002\u0003\u000fQ/\u0001\u0006fm&$WM\\2fIQ\u00022AQ#o\u0011\u0015AU\u0001q\u0001x!\u0011Q\u0005\u000b\u001f=\u0011\u000799c.A\u0007uS2,w\fR'`\u00136\u0004HnM\u000b\u0003w~$\u0012\u0002`A\u0001\u0003\u000f\ti!!\u0005\u0011\r\r2V\u0010\u000e\u001b~!\rqAL \t\u0003U}$Q\u0001\f\u0004C\u00025B\u0011\"a\u0001\u0007\u0003\u0003\u0005\u001d!!\u0002\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007E\u0002;{yD\u0011\"!\u0003\u0007\u0003\u0003\u0005\u001d!a\u0003\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$c\u0007E\u0002C\u000bzDa!\u001a\u0004A\u0004\u0005=\u0001#B\u0012%{Rj\bB\u0002%\u0007\u0001\b\t\u0019\u0002\u0005\u0003K!vl\b"
)
public final class tile {
   public static UFunc.UImpl3 tile_DM_Impl3(final ClassTag evidence$5, final Zero evidence$6, final UFunc.UImpl2 impl2, final UFunc.InPlaceImpl2 set) {
      return tile$.MODULE$.tile_DM_Impl3(evidence$5, evidence$6, impl2, set);
   }

   public static UFunc.UImpl2 tile_DM_Impl2(final ClassTag evidence$3, final Zero evidence$4, final UFunc.InPlaceImpl2 set) {
      return tile$.MODULE$.tile_DM_Impl2(evidence$3, evidence$4, set);
   }

   public static UFunc.UImpl3 tile_DV_Impl3(final ClassTag evidence$1, final Zero evidence$2, final UFunc.UImpl2 impl2, final UFunc.InPlaceImpl2 set) {
      return tile$.MODULE$.tile_DV_Impl3(evidence$1, evidence$2, impl2, set);
   }

   public static UFunc.UImpl2 tile_DV_Impl2(final ClassTag ct, final Zero z, final UFunc.InPlaceImpl2 set) {
      return tile$.MODULE$.tile_DV_Impl2(ct, z, set);
   }

   public static Object withSink(final Object s) {
      return tile$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return tile$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return tile$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return tile$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return tile$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return tile$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return tile$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return tile$.MODULE$.apply(v, impl);
   }
}
