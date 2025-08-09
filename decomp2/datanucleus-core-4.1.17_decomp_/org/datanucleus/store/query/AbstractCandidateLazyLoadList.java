package org.datanucleus.store.query;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.MetaDataUtils;

public abstract class AbstractCandidateLazyLoadList extends AbstractLazyLoadList {
   protected ExecutionContext ec;
   protected List cmds = new ArrayList();

   public AbstractCandidateLazyLoadList(Class cls, boolean subclasses, ExecutionContext ec, String cacheType) {
      super(cacheType);
      this.ec = ec;
      this.cmds = MetaDataUtils.getMetaDataForCandidates(cls, subclasses, ec);
   }
}
