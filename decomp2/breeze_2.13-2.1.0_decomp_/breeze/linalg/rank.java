package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanTraverseValues;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]q!B\u0003\u0007\u0011\u0003Ya!B\u0007\u0007\u0011\u0003q\u0001\"B\u000e\u0002\t\u0003a\u0002\"B\u000f\u0002\t\u0007q\u0002\"B6\u0002\t\u0007a\u0017\u0001\u0002:b].T!a\u0002\u0005\u0002\r1Lg.\u00197h\u0015\u0005I\u0011A\u00022sK\u0016TXm\u0001\u0001\u0011\u00051\tQ\"\u0001\u0004\u0003\tI\fgn[\n\u0004\u0003=)\u0002C\u0001\t\u0014\u001b\u0005\t\"\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\t\"AB!osJ+g\r\u0005\u0002\u001735\tqC\u0003\u0002\u0019\u0011\u00059q-\u001a8fe&\u001c\u0017B\u0001\u000e\u0018\u0005\u0015)f)\u001e8d\u0003\u0019a\u0014N\\5u}Q\t1\"A\bj[Bd'+\u00198l\rJ|Wn\u0015,E+\u0011ybE\u0014-\u0015\u000b\u0001\u0012\u0014K\u00172\u0011\t\u0005\u0012CeL\u0007\u0002\u0003%\u00111%\u0007\u0002\u0005\u00136\u0004H\u000e\u0005\u0002&M1\u0001A!B\u0014\u0004\u0005\u0004A#!A'\u0012\u0005%b\u0003C\u0001\t+\u0013\tY\u0013CA\u0004O_RD\u0017N\\4\u0011\u0005Ai\u0013B\u0001\u0018\u0012\u0005\r\te.\u001f\t\u0003!AJ!!M\t\u0003\u0007%sG\u000fC\u00034\u0007\u0001\u000fA'\u0001\u0004dC:\u001cf\u000b\u0012\t\u0005k\t\"\u0003H\u0004\u0002\rm%\u0011qGB\u0001\u0004gZ$\u0007GA\u001dG!\u0011Q$)\u0012)\u000f\u0005m2dB\u0001\u001fB\u001d\ti\u0004)D\u0001?\u0015\ty$\"\u0001\u0004=e>|GOP\u0005\u0002\u0013%\u0011q\u0001C\u0005\u0003\u0007\u0012\u00131a\u0015,E\u0015\t9d\u0001\u0005\u0002&\r\u0012Iq\tSA\u0001\u0002\u0003\u0015\t\u0001\u000b\u0002\u0004?\u0012\n\u0004\"B\u001a\u0004\u0001\bI\u0005\u0003B\u001b#\u0015.\u0003\"!\n\u00141\u000513\u0005\u0003\u0002\u001eC\u000b6\u0003\"!\n(\u0005\u000b=\u001b!\u0019\u0001\u0015\u0003\u0003M\u0003\"!\n(\t\u000bI\u001b\u00019A*\u0002\t5\f\u0007p\u0015\t\u0005)\n\u0002vK\u0004\u0002\r+&\u0011aKB\u0001\u0004[\u0006D\bCA\u0013Y\t\u0015I6A1\u0001)\u0005\u00051\u0005\"B.\u0004\u0001\ba\u0016!\u0002;sCZ\u001c\u0006\u0003B/a!^k\u0011A\u0018\u0006\u0003?\u001a\tqa];qa>\u0014H/\u0003\u0002b=\n\t2)\u00198Ue\u00064XM]:f-\u0006dW/Z:\t\u000b\r\u001c\u00019\u00013\u0002\u000594\u0005\u0003B3#/\"t!\u0001\u00044\n\u0005\u001d4\u0011\u0001\u00028pe6\u0004\"\u0001E5\n\u0005)\f\"A\u0002#pk\ndW-A\u0006j[Bd'+\u00198l)>dW\u0003B7s\u0003\u000b!bA\\:\u0002\u0010\u0005M\u0001#B\u0011pc\"|\u0013B\u00019\u001a\u0005\u0015IU\u000e\u001d73!\t)#\u000fB\u0003(\t\t\u0007\u0001\u0006C\u00034\t\u0001\u000fA\u000f\u0005\u00036EE,\b\u0007\u0002<{\u0003\u0013\u0001r\u0001E<z\u0003\u001b\t9!\u0003\u0002y#\t1A+\u001e9mKN\u0002\"!\n>\u0005\u0013md\u0018\u0011!A\u0001\u0006\u0003A#aA0%e!)1\u0007\u0002a\u0002{B!QG\t@\u0000!\t)#\u000fM\u0003\u0002\u0002i\fI\u0001E\u0004\u0011of\f\u0019!a\u0002\u0011\u0007\u0015\n)\u0001B\u0003P\t\t\u0007\u0001\u0006E\u0002&\u0003\u0013!!\"a\u0003}\u0003\u0003\u0005\tQ!\u0001)\u0005\ryFe\r\t\u0004K\u0005\u0015\u0001B\u0002*\u0005\u0001\b\t\t\u0002E\u0003UE\u00055\u0001\u000e\u0003\u0004\\\t\u0001\u000f\u0011Q\u0003\t\u0006;\u0002\fi\u0001\u001b"
)
public final class rank {
   public static UFunc.UImpl2 implRankTol(final UFunc.UImpl canSVD, final UFunc.UImpl maxS, final CanTraverseValues travS) {
      return rank$.MODULE$.implRankTol(canSVD, maxS, travS);
   }

   public static UFunc.UImpl implRankFromSVD(final UFunc.UImpl canSVD, final UFunc.UImpl maxS, final CanTraverseValues travS, final UFunc.UImpl nF) {
      return rank$.MODULE$.implRankFromSVD(canSVD, maxS, travS, nF);
   }

   public static Object withSink(final Object s) {
      return rank$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return rank$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return rank$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return rank$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return rank$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return rank$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return rank$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return rank$.MODULE$.apply(v, impl);
   }
}
