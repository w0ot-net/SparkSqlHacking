package org.apache.spark.security;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.Credentials;
import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;
import scala.Option;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005=3q\u0001B\u0003\u0011\u0002G\u0005a\u0002C\u0003\u0016\u0001\u0019\u0005a\u0003C\u0003#\u0001\u0019\u00051\u0005C\u00038\u0001\u0019\u0005\u0001HA\u000fIC\u0012|w\u000e\u001d#fY\u0016<\u0017\r^5p]R{7.\u001a8Qe>4\u0018\u000eZ3s\u0015\t1q!\u0001\u0005tK\u000e,(/\u001b;z\u0015\tA\u0011\"A\u0003ta\u0006\u00148N\u0003\u0002\u000b\u0017\u00051\u0011\r]1dQ\u0016T\u0011\u0001D\u0001\u0004_J<7\u0001A\n\u0003\u0001=\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0017aC:feZL7-\u001a(b[\u0016,\u0012a\u0006\t\u00031}q!!G\u000f\u0011\u0005i\tR\"A\u000e\u000b\u0005qi\u0011A\u0002\u001fs_>$h(\u0003\u0002\u001f#\u00051\u0001K]3eK\u001aL!\u0001I\u0011\u0003\rM#(/\u001b8h\u0015\tq\u0012#\u0001\reK2,w-\u0019;j_:$vn[3ogJ+\u0017/^5sK\u0012$2\u0001J\u0014.!\t\u0001R%\u0003\u0002'#\t9!i\\8mK\u0006t\u0007\"\u0002\u0015\u0003\u0001\u0004I\u0013!C:qCJ\\7i\u001c8g!\tQ3&D\u0001\b\u0013\tasAA\u0005Ta\u0006\u00148nQ8oM\")aF\u0001a\u0001_\u0005Q\u0001.\u00193p_B\u001cuN\u001c4\u0011\u0005A*T\"A\u0019\u000b\u0005I\u001a\u0014\u0001B2p]\u001aT!\u0001N\u0005\u0002\r!\fGm\\8q\u0013\t1\u0014GA\u0007D_:4\u0017nZ;sCRLwN\\\u0001\u0017_\n$\u0018-\u001b8EK2,w-\u0019;j_:$vn[3ogR!\u0011h\u0010!B!\r\u0001\"\bP\u0005\u0003wE\u0011aa\u00149uS>t\u0007C\u0001\t>\u0013\tq\u0014C\u0001\u0003M_:<\u0007\"\u0002\u0018\u0004\u0001\u0004y\u0003\"\u0002\u0015\u0004\u0001\u0004I\u0003\"\u0002\"\u0004\u0001\u0004\u0019\u0015!B2sK\u0012\u001c\bC\u0001#G\u001b\u0005)%B\u0001\u00044\u0013\t9UIA\u0006De\u0016$WM\u001c;jC2\u001c\bF\u0001\u0001J!\tQU*D\u0001L\u0015\tau!\u0001\u0006b]:|G/\u0019;j_:L!AT&\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5"
)
public interface HadoopDelegationTokenProvider {
   String serviceName();

   boolean delegationTokensRequired(final SparkConf sparkConf, final Configuration hadoopConf);

   Option obtainDelegationTokens(final Configuration hadoopConf, final SparkConf sparkConf, final Credentials creds);
}
