package jakarta.xml.bind.annotation;

import jakarta.xml.bind.ValidationEventHandler;
import javax.xml.transform.Result;
import javax.xml.transform.Source;

public interface DomHandler {
   Result createUnmarshaller(ValidationEventHandler var1);

   Object getElement(Result var1);

   Source marshal(Object var1, ValidationEventHandler var2);
}
