package org.apache.spark.storage;

import org.apache.spark.annotation.DeveloperApi;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashSet;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u001d3qAA\u0002\u0011\u0002G\u0005A\u0002C\u0003\u0014\u0001\u0019\u0005AC\u0001\fCY>\u001c7NU3qY&\u001c\u0017\r^5p]B{G.[2z\u0015\t!Q!A\u0004ti>\u0014\u0018mZ3\u000b\u0005\u00199\u0011!B:qCJ\\'B\u0001\u0005\n\u0003\u0019\t\u0007/Y2iK*\t!\"A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001\u001bA\u0011a\"E\u0007\u0002\u001f)\t\u0001#A\u0003tG\u0006d\u0017-\u0003\u0002\u0013\u001f\t1\u0011I\\=SK\u001a\f!\u0002\u001d:j_JLG/\u001b>f)\u0019)Re\n\u00177wA\u0019aCH\u0011\u000f\u0005]abB\u0001\r\u001c\u001b\u0005I\"B\u0001\u000e\f\u0003\u0019a$o\\8u}%\t\u0001#\u0003\u0002\u001e\u001f\u00059\u0001/Y2lC\u001e,\u0017BA\u0010!\u0005\u0011a\u0015n\u001d;\u000b\u0005uy\u0001C\u0001\u0012$\u001b\u0005\u0019\u0011B\u0001\u0013\u0004\u00059\u0011En\\2l\u001b\u0006t\u0017mZ3s\u0013\u0012DQAJ\u0001A\u0002\u0005\naB\u00197pG.l\u0015M\\1hKJLE\rC\u0003)\u0003\u0001\u0007\u0011&A\u0003qK\u0016\u00148\u000fE\u0002\u0017U\u0005J!a\u000b\u0011\u0003\u0007M+\u0017\u000fC\u0003.\u0003\u0001\u0007a&A\tqK\u0016\u00148OU3qY&\u001c\u0017\r^3e)>\u00042a\f\u001b\"\u001b\u0005\u0001$BA\u00193\u0003\u001diW\u000f^1cY\u0016T!aM\b\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u00026a\t9\u0001*Y:i'\u0016$\b\"B\u001c\u0002\u0001\u0004A\u0014a\u00022m_\u000e\\\u0017\n\u001a\t\u0003EeJ!AO\u0002\u0003\u000f\tcwnY6JI\")A(\u0001a\u0001{\u0005Ya.^7SKBd\u0017nY1t!\tqa(\u0003\u0002@\u001f\t\u0019\u0011J\u001c;)\u0005\u0001\t\u0005C\u0001\"F\u001b\u0005\u0019%B\u0001#\u0006\u0003)\tgN\\8uCRLwN\\\u0005\u0003\r\u000e\u0013A\u0002R3wK2|\u0007/\u001a:Ba&\u0004"
)
public interface BlockReplicationPolicy {
   List prioritize(final BlockManagerId blockManagerId, final Seq peers, final HashSet peersReplicatedTo, final BlockId blockId, final int numReplicas);
}
