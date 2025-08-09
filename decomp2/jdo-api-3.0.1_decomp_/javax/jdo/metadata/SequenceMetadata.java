package javax.jdo.metadata;

import javax.jdo.annotations.SequenceStrategy;

public interface SequenceMetadata extends Metadata {
   String getName();

   SequenceStrategy getSequenceStrategy();

   SequenceMetadata setDatastoreSequence(String var1);

   String getDatastoreSequence();

   SequenceMetadata setFactoryClass(String var1);

   String getFactoryClass();
}
