package scala.collection.generic;

import java.io.Serializable;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.mutable.Builder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParMap;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055caB\u0007\u000f!\u0003\r\t!\u0006\u0005\u00067\u0001!\t\u0001\b\u0005\u0006A\u00011\t!\t\u0005\u0006\r\u0002!\u0019aR\u0004\u0006+:A\tA\u0016\u0004\u0006\u001b9A\t\u0001\u0017\u0005\u00063\u0016!\tA\u0017\u0005\u0006\r\u0016!\u0019a\u0017\u0004\u0005e\u0016!1\u000fC\u0005p\u0011\t\u0005\t\u0015!\u0003\u0002\u0018!1\u0011\f\u0003C\u0001\u00037Aq!a\t\t\t\u0003\t)\u0003C\u0004\u00022!!\t!a\r\u0003-\u001d+g.\u001a:jGB\u000b'/T1q\u0007>l\u0007/\u00198j_:T!a\u0004\t\u0002\u000f\u001d,g.\u001a:jG*\u0011\u0011CE\u0001\u000bG>dG.Z2uS>t'\"A\n\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0011acO\n\u0003\u0001]\u0001\"\u0001G\r\u000e\u0003II!A\u0007\n\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\tQ\u0004\u0005\u0002\u0019=%\u0011qD\u0005\u0002\u0005+:LG/A\u0006oK^\u001cu.\u001c2j]\u0016\u0014Xc\u0001\u0012/qU\t1\u0005\u0005\u0003%O%RT\"A\u0013\u000b\u0005\u0019\u0002\u0012\u0001\u00039be\u0006dG.\u001a7\n\u0005!*#\u0001C\"p[\nLg.\u001a:\u0011\taQCfN\u0005\u0003WI\u0011a\u0001V;qY\u0016\u0014\u0004CA\u0017/\u0019\u0001!Qa\f\u0002C\u0002A\u0012\u0011\u0001U\t\u0003cQ\u0002\"\u0001\u0007\u001a\n\u0005M\u0012\"a\u0002(pi\"Lgn\u001a\t\u00031UJ!A\u000e\n\u0003\u0007\u0005s\u0017\u0010\u0005\u0002.q\u0011)\u0011H\u0001b\u0001a\t\t\u0011\u000b\u0005\u0003.w1:DA\u0002\u001f\u0001\t\u000b\u0007QH\u0001\u0002D\u0007V\u0019ahQ#\u0012\u0005Ez\u0004\u0003\u0002\u0013A\u0005\u0012K!!Q\u0013\u0003\rA\u000b'/T1q!\ti3\tB\u00030w\t\u0007\u0001\u0007\u0005\u0002.\u000b\u0012)\u0011h\u000fb\u0001a\u0005IAo\u001c$bGR|'/_\u000b\u0004\u0011>\u0013V#A%\u0011\t)[U\nV\u0007\u0002!%\u0011A\n\u0005\u0002\b\r\u0006\u001cGo\u001c:z!\u0011A\"FT)\u0011\u00055zE!\u0002)\u0004\u0005\u0004\u0001$!A&\u0011\u00055\u0012F!B*\u0004\u0005\u0004\u0001$!\u0001,\u0011\t5Zd*U\u0001\u0017\u000f\u0016tWM]5d!\u0006\u0014X*\u00199D_6\u0004\u0018M\\5p]B\u0011q+B\u0007\u0002\u001dM\u0011QaF\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003Y+B\u0001\u00181cIR\u0011QL\u001c\t\u0005\u0015.s6\r\u0005\u0003\u0019U}\u000b\u0007CA\u0017a\t\u0015\u0001vA1\u00011!\ti#\rB\u0003T\u000f\t\u0007\u0001\u0007\u0005\u0003.I~\u000bG!\u0002\u001f\b\u0005\u0004)Wc\u00014jYF\u0011\u0011g\u001a\t\u0005I\u0001C7\u000e\u0005\u0002.S\u0012)!\u000e\u001ab\u0001a\t\t\u0001\f\u0005\u0002.Y\u0012)Q\u000e\u001ab\u0001a\t\t\u0011\fC\u0003p\u000f\u0001\u0007\u0001/\u0001\u0006qCJ4\u0015m\u0019;pef\u00042a\u0016\u0001r!\tiCMA\u0005U_\u001a\u000b7\r^8ssV!A\u000f\u001f>}'\u0015Aq#^A\u0005!\u0011Q5J^>\u0011\taQs/\u001f\t\u0003[a$Q\u0001\u0015\u0005C\u0002A\u0002\"!\f>\u0005\u000bMC!\u0019\u0001\u0019\u0011\t5bx/\u001f\u0003\u0006y!\u0011\r!`\u000b\u0006}\u0006\r\u0011qA\t\u0003c}\u0004b\u0001\n!\u0002\u0002\u0005\u0015\u0001cA\u0017\u0002\u0004\u0011)!\u000e b\u0001aA\u0019Q&a\u0002\u0005\u000b5d(\u0019\u0001\u0019\u0011\t\u0005-\u0011\u0011\u0003\b\u00041\u00055\u0011bAA\b%\u00059\u0001/Y2lC\u001e,\u0017\u0002BA\n\u0003+\u0011AbU3sS\u0006d\u0017N_1cY\u0016T1!a\u0004\u0013!\u00119\u0006!!\u0007\u0011\u00055bH\u0003BA\u000f\u0003C\u0001r!a\b\tof\fI\"D\u0001\u0006\u0011\u0019y'\u00021\u0001\u0002\u0018\u0005aaM]8n'B,7-\u001b4jGR\u001910a\n\t\u000f\u0005%2\u00021\u0001\u0002,\u0005\u0011\u0011\u000e\u001e\t\u0005\u0015\u00065b/C\u0002\u00020A\u0011A\"\u0013;fe\u0006\u0014G.Z(oG\u0016\f!B\\3x\u0005VLG\u000eZ3s+\t\t)\u0004\u0005\u0004\u00028\u0005ubo_\u0007\u0003\u0003sQ1!a\u000f\u0011\u0003\u001diW\u000f^1cY\u0016LA!a\u0010\u0002:\t9!)^5mI\u0016\u0014\bf\u0002\u0005\u0002D\u0005%\u00131\n\t\u00041\u0005\u0015\u0013bAA$%\t\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\u0002\u0007\u0001"
)
public interface GenericParMapCompanion {
   Combiner newCombiner();

   // $FF: synthetic method
   static Factory toFactory$(final GenericParMapCompanion $this) {
      return $this.toFactory();
   }

   default Factory toFactory() {
      return GenericParMapCompanion$.MODULE$.toFactory(this);
   }

   static void $init$(final GenericParMapCompanion $this) {
   }

   private static class ToFactory implements Factory, Serializable {
      private static final long serialVersionUID = 3L;
      private final GenericParMapCompanion parFactory;

      public ParMap fromSpecific(final IterableOnce it) {
         return (ParMap)((Builder)this.parFactory.newCombiner().$plus$plus$eq(it)).result();
      }

      public Builder newBuilder() {
         return this.parFactory.newCombiner();
      }

      public ToFactory(final GenericParMapCompanion parFactory) {
         this.parFactory = parFactory;
      }
   }
}
