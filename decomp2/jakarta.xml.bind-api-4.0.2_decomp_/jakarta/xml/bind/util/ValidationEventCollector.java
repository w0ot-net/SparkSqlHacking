package jakarta.xml.bind.util;

import jakarta.xml.bind.ValidationEvent;
import jakarta.xml.bind.ValidationEventHandler;
import java.util.ArrayList;
import java.util.List;

public class ValidationEventCollector implements ValidationEventHandler {
   private final List events = new ArrayList();

   public ValidationEvent[] getEvents() {
      return (ValidationEvent[])this.events.toArray(new ValidationEvent[0]);
   }

   public void reset() {
      this.events.clear();
   }

   public boolean hasEvents() {
      return !this.events.isEmpty();
   }

   public boolean handleEvent(ValidationEvent event) {
      this.events.add(event);
      boolean retVal = true;
      switch (event.getSeverity()) {
         case 0:
         case 1:
            retVal = true;
            break;
         case 2:
            retVal = false;
            break;
         default:
            _assert(false, Messages.format("ValidationEventCollector.UnrecognizedSeverity", (Object)event.getSeverity()));
      }

      return retVal;
   }

   private static void _assert(boolean b, String msg) {
      if (!b) {
         throw new InternalError(msg);
      }
   }
}
