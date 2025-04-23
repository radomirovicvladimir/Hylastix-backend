package org.hylastix.job;

import lombok.RequiredArgsConstructor;
import org.hylastix.repository.DeletedItemRepository;
import org.hylastix.service.impl.UserDetailsServiceImpl;
import org.hylastix.util.CsvExportUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.hylastix.model.DeletedItem;
import org.hylastix.model.User;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ExportAndDeleteDeletedItemsJob implements Job {

    private final DeletedItemRepository deletedItemRepository;
    private final CsvExportUtil csvExportUtil;
    private static final Logger log = LoggerFactory.getLogger(ExportAndDeleteDeletedItemsJob.class);
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    public void execute(JobExecutionContext context) {
        log.info("Running scheduled job to export and delete deleted items at the beginning of the month");

        List<User> users = userDetailsServiceImpl.getAllUsers();

        for (User user : users) {
            List<DeletedItem> deletedItemsForUser = deletedItemRepository.findByUser(user);

            if (!deletedItemsForUser.isEmpty()) {

                csvExportUtil.exportToCsv(deletedItemsForUser, user);
                deletedItemRepository.deleteAll(deletedItemsForUser);
                log.info("Deleted items for user {}", user.getUsername());

            }
        }
    }
}
