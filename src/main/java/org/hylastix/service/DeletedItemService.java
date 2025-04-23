package org.hylastix.service;
import org.hylastix.dto.DeletedItemDTO;
import org.hylastix.model.DeletedItem;

import java.util.List;

public interface DeletedItemService {
    List<DeletedItemDTO> getAllDeletedItems();
}
