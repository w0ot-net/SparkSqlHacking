package org.stringtemplate.v4;

import java.util.ArrayList;
import java.util.List;

public class InstanceScope {
   public InstanceScope parent;
   public ST st;
   public int ret_ip;
   public List events = new ArrayList();
   public List childEvalTemplateEvents = new ArrayList();

   public InstanceScope(InstanceScope parent, ST st) {
      this.parent = parent;
      this.st = st;
   }
}
