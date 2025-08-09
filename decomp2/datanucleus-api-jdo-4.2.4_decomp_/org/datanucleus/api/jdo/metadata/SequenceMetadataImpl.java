package org.datanucleus.api.jdo.metadata;

import javax.jdo.annotations.SequenceStrategy;
import javax.jdo.metadata.SequenceMetadata;
import org.datanucleus.metadata.SequenceMetaData;

public class SequenceMetadataImpl extends AbstractMetadataImpl implements SequenceMetadata {
   public SequenceMetadataImpl(SequenceMetaData internal) {
      super(internal);
   }

   public SequenceMetaData getInternal() {
      return (SequenceMetaData)this.internalMD;
   }

   public Integer getAllocationSize() {
      return this.getInternal().getAllocationSize();
   }

   public Integer getInitialValue() {
      return this.getInternal().getInitialValue();
   }

   public String getDatastoreSequence() {
      return this.getInternal().getDatastoreSequence();
   }

   public String getFactoryClass() {
      return this.getInternal().getFactoryClass();
   }

   public String getName() {
      return this.getInternal().getName();
   }

   public SequenceStrategy getSequenceStrategy() {
      org.datanucleus.metadata.SequenceStrategy strategy = this.getInternal().getStrategy();
      if (strategy == org.datanucleus.metadata.SequenceStrategy.CONTIGUOUS) {
         return SequenceStrategy.CONTIGUOUS;
      } else if (strategy == org.datanucleus.metadata.SequenceStrategy.NONCONTIGUOUS) {
         return SequenceStrategy.NONCONTIGUOUS;
      } else {
         return strategy == org.datanucleus.metadata.SequenceStrategy.NONTRANSACTIONAL ? SequenceStrategy.NONTRANSACTIONAL : null;
      }
   }

   public SequenceMetadata setAllocationSize(int size) {
      this.getInternal().setAllocationSize(size);
      return this;
   }

   public SequenceMetadata setDatastoreSequence(String seq) {
      this.getInternal().setDatastoreSequence(seq);
      return this;
   }

   public SequenceMetadata setFactoryClass(String cls) {
      this.getInternal().setFactoryClass(cls);
      return this;
   }

   public SequenceMetadata setInitialValue(int value) {
      this.getInternal().setInitialValue(value);
      return this;
   }
}
