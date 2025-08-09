package org.apache.derby.iapi.types;

import org.apache.derby.shared.common.error.StandardException;

interface CollationElementsInterface {
   boolean hasSingleCollationElement() throws StandardException;
}
