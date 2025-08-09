package org.glassfish.jersey.inject.hk2;

import jakarta.ws.rs.RuntimeType;
import java.util.ArrayList;
import java.util.List;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.innate.inject.spi.ExternalRegistrables;

public class Hk2Registrables implements ExternalRegistrables {
   public List registrableContracts() {
      List<ExternalRegistrables.ClassRuntimeTypePair> list = new ArrayList();
      list.add(new ExternalRegistrables.ClassRuntimeTypePair(AbstractBinder.class, (RuntimeType)null));
      return list;
   }
}
