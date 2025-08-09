package org.apache.spark.deploy.rest;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.SparkApplication;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a3Q!\u0002\u0004\u0001\u0015AAQa\u0007\u0001\u0005\u0002uAQ\u0001\t\u0001\u0005\u0002\u0005Bq\u0001\u0012\u0001\u0012\u0002\u0013\u0005Q\tC\u0003Q\u0001\u0011\u0005\u0013KA\fSKN$8+\u001e2nSN\u001c\u0018n\u001c8DY&,g\u000e^!qa*\u0011q\u0001C\u0001\u0005e\u0016\u001cHO\u0003\u0002\n\u0015\u00051A-\u001a9m_fT!a\u0003\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00055q\u0011AB1qC\u000eDWMC\u0001\u0010\u0003\ry'oZ\n\u0004\u0001E9\u0002C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"AB!osJ+g\r\u0005\u0002\u001935\t\u0001\"\u0003\u0002\u001b\u0011\t\u00012\u000b]1sW\u0006\u0003\b\u000f\\5dCRLwN\\\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\ta\u0004\u0005\u0002 \u00015\ta!A\u0002sk:$bAI\u00133iez\u0004CA\u0010$\u0013\t!cA\u0001\u000eTk\nl\u0017\u000e\u001e*fgR\u0004&o\u001c;pG>d'+Z:q_:\u001cX\rC\u0003'\u0005\u0001\u0007q%A\u0006baB\u0014Vm]8ve\u000e,\u0007C\u0001\u00150\u001d\tIS\u0006\u0005\u0002+'5\t1F\u0003\u0002-9\u00051AH]8pizJ!AL\n\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0014G\u0001\u0004TiJLgn\u001a\u0006\u0003]MAQa\r\u0002A\u0002\u001d\n\u0011\"\\1j]\u000ec\u0017m]:\t\u000bU\u0012\u0001\u0019\u0001\u001c\u0002\u000f\u0005\u0004\b/\u0011:hgB\u0019!cN\u0014\n\u0005a\u001a\"!B!se\u0006L\b\"\u0002\u001e\u0003\u0001\u0004Y\u0014\u0001B2p]\u001a\u0004\"\u0001P\u001f\u000e\u0003)I!A\u0010\u0006\u0003\u0013M\u0003\u0018M]6D_:4\u0007b\u0002!\u0003!\u0003\u0005\r!Q\u0001\u0004K:4\b\u0003\u0002\u0015CO\u001dJ!aQ\u0019\u0003\u00075\u000b\u0007/A\u0007sk:$C-\u001a4bk2$H%N\u000b\u0002\r*\u0012\u0011iR\u0016\u0002\u0011B\u0011\u0011JT\u0007\u0002\u0015*\u00111\nT\u0001\nk:\u001c\u0007.Z2lK\u0012T!!T\n\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002P\u0015\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u000bM$\u0018M\u001d;\u0015\u0007I+v\u000b\u0005\u0002\u0013'&\u0011Ak\u0005\u0002\u0005+:LG\u000fC\u0003W\t\u0001\u0007a'\u0001\u0003be\u001e\u001c\b\"\u0002\u001e\u0005\u0001\u0004Y\u0004"
)
public class RestSubmissionClientApp implements SparkApplication {
   public SubmitRestProtocolResponse run(final String appResource, final String mainClass, final String[] appArgs, final SparkConf conf, final Map env) {
      String master = (String)conf.getOption("spark.master").getOrElse(() -> {
         throw new IllegalArgumentException("'spark.master' must be set.");
      });
      Map sparkProperties = .MODULE$.wrapRefArray((Object[])conf.getAll()).toMap(scala..less.colon.less..MODULE$.refl());
      RestSubmissionClient client = new RestSubmissionClient(master);
      CreateSubmissionRequest submitRequest = client.constructSubmitRequest(appResource, mainClass, appArgs, sparkProperties, env);
      return client.createSubmission(submitRequest);
   }

   public Map run$default$5() {
      return (Map).MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
   }

   public void start(final String[] args, final SparkConf conf) {
      if (args.length < 2) {
         throw scala.sys.package..MODULE$.error("Usage: RestSubmissionClient [app resource] [main class] [app args*]");
      } else {
         String appResource = args[0];
         String mainClass = args[1];
         String[] appArgs = (String[])scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.refArrayOps((Object[])args), 2, args.length);
         Map env = RestSubmissionClient$.MODULE$.filterSystemEnvironment(scala.sys.package..MODULE$.env());
         this.run(appResource, mainClass, appArgs, conf, env);
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
