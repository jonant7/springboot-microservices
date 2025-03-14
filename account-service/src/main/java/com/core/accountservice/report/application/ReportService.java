package com.core.accountservice.report.application;

import com.core.accountservice.account.application.AccountService;
import com.core.accountservice.account.domain.Account;
import com.core.accountservice.movement.application.MovementService;
import com.core.accountservice.movement.domain.Movement;
import com.core.accountservice.report.domain.AccountReportDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    private final AccountService accountService;
    private final MovementService movementService;

    public ReportService(AccountService accountService, MovementService movementService) {
        this.accountService = accountService;
        this.movementService = movementService;
    }

    public List<AccountReportDTO> generate(LocalDate startDate, LocalDate endDate, Long customerId) {
        List<Account> accounts = accountService.list(customerId);

        List<AccountReportDTO> reports = new ArrayList<>();

        for (Account account : accounts) {
            List<Movement> movements = movementService.get(account, startDate, endDate);

            AccountReportDTO accountReportDTO = new AccountReportDTO(account, movements);
            reports.add(accountReportDTO);
        }

        return reports;
    }

}
