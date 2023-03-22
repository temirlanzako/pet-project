package pet_project.pet.scheduler;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pet_project.pet.dto.JournalDto;
import pet_project.pet.model.Journal;
import pet_project.pet.repository.JournalRepository;
import pet_project.pet.service.EmailSenderService;
import pet_project.pet.service.JournalService;

import java.util.List;

@Component
public class Schedule {
    @Autowired
    private JournalService journalService;
    @Autowired
    private EmailSenderService emailSenderService;

    @Scheduled(fixedDelay = 100000)
    public void informUserAboutDepositStatus() {

        List<Journal> journalList = journalService.getRecords();
        System.out.println(journalList);
        for (Journal record : journalList) {
            if (record.getDepositStatus() == false) {
                double deposit = record.getDeposit();
                deposit = deposit - 100.00;
                record.setDeposit(deposit);
                journalService.saveJournal(record);
                emailSenderService.sendEmailRegardingDeposit(record);
                /*if (record.getDeposit() <= 0) {
                    emailSenderService.sendWarningAboutDebt(record);
                }*/
            }
        }
    }
}
