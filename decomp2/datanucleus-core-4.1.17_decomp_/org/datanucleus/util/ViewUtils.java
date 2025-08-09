package org.datanucleus.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.datanucleus.exceptions.NucleusUserException;

public class ViewUtils {
   public static void checkForCircularViewReferences(Map viewReferences, String referencer_name, String referencee_name, List referenceChain) {
      Set class_names = (Set)viewReferences.get(referencee_name);
      if (class_names != null) {
         if (referenceChain == null) {
            referenceChain = new ArrayList();
            referenceChain.add(referencer_name);
         }

         referenceChain.add(referencee_name);

         for(String current_name : class_names) {
            if (current_name.equals(referencer_name)) {
               StringBuilder error = new StringBuilder(Localiser.msg("031003"));
               Iterator chainIter = referenceChain.iterator();

               while(chainIter.hasNext()) {
                  error.append(chainIter.next());
                  if (chainIter.hasNext()) {
                     error.append(" -> ");
                  }
               }

               throw (new NucleusUserException(error.toString())).setFatal();
            }

            checkForCircularViewReferences(viewReferences, referencer_name, current_name, referenceChain);
         }
      }

   }
}
