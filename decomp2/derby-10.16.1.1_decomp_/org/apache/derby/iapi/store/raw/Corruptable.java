package org.apache.derby.iapi.store.raw;

import org.apache.derby.shared.common.error.StandardException;

public interface Corruptable {
   StandardException markCorrupt(StandardException var1);
}
