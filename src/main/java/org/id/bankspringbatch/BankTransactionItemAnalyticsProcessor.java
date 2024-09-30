package org.id.bankspringbatch;

import lombok.Getter;
import org.id.bankspringbatch.dao.BankTransaction;
import org.springframework.batch.item.ItemProcessor;

@Getter
//@Component
public class BankTransactionItemAnalyticsProcessor implements ItemProcessor<BankTransaction, BankTransaction> {
    private double totalDebit;
    private double totalCredit;

    @Override
    public BankTransaction process(BankTransaction bankTransaction) throws Exception {
        if (bankTransaction.getTransactionType().equals("D")) totalDebit += bankTransaction.getAmount();
        else if (bankTransaction.getTransactionType().equals("C")) totalCredit += bankTransaction.getAmount();
        return bankTransaction;
    }
}
