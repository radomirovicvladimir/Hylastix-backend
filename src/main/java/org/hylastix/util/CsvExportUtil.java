package org.hylastix.util;

import org.hylastix.model.DeletedItem;
import org.hylastix.model.User;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@Component
public class CsvExportUtil {

    public void exportToCsv(List<DeletedItem> items, User user) {
        String fileName = "deleted-items-" + LocalDate.now().getYear() + "-" + LocalDate.now().getMonth() + "-" + user.getUsername() + ".csv";

        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            writer.println("ItemName,Quantity,Stored,BestBefore,DeletedAt,UserId");
            for (DeletedItem item : items) {
                writer.printf("%s,%d,%s,%s,%s,%d\n",
                        item.getItemName(), item.getQuantity(),
                        item.getTimeStored(), item.getBestBefore(),
                        item.getDeletedAt(), item.getUser().getId()
                );
            }
        } catch (IOException e) {
            throw new RuntimeException("CSV export failed", e);
        }
    }
}

