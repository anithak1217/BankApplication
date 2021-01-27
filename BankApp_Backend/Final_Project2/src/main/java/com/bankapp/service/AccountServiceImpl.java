package com.bankapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankapp.entities.Account;
import com.bankapp.entities.TransactionLog;
import com.bankapp.exceptions.ResourseNotFondException;
import com.bankapp.repo.AccountRepo;
import com.bankapp.repo.TransactionLogRepo;
@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	@Autowired
	private com.bankapp.repo.AccountRepo accountRepo;
	@Autowired
	private TransactionLogRepo transactionLogRepo;
	
	
	public AccountServiceImpl(AccountRepo accountRepo, TransactionLogRepo transactionLogRepo) {
		super();
		this.accountRepo = accountRepo;
		this.transactionLogRepo = transactionLogRepo;
	}

	@Override
	public List<Account> getAll() {
		
		return accountRepo.findAll();
	}
	@Override
	public Account getAccountId(int id) {
		return accountRepo.findById(id).orElseThrow(()->new ResourseNotFondException("account with id :"+id+"is not found"));			
		
	}
	@Override
	public Account Save(Account account) {
		
		accountRepo.save(account);
		return null;
	}

	@Override
	public Account delete(int id) {
		Account accountToDelAccount=getAccountId(id);
		accountRepo.delete(accountToDelAccount);
		return accountToDelAccount;
	}

	

	@Override
	public void transfer(int fromId, int toId, double amount) {
		Account fromAcc=getAccountId(fromId);
		Account toAcc=getAccountId(toId);
		fromAcc.setBalance(fromAcc.getBalance()-amount);
		toAcc.setBalance(toAcc.getBalance()+amount);
		
		TransactionLog fromAccLog=new TransactionLog("withdraw",fromId,toId,"withdraw");
		fromAcc.getTransactionLogs().add(fromAccLog);
		
		TransactionLog toAccLog=new TransactionLog("deposite",toId,fromId,"diposite");
		toAcc.getTransactionLogs().add(toAccLog);
		
		
		accountRepo.save(fromAcc);
		accountRepo.save(toAcc);
		
		
	}

	@Override
	public Account updateDetails(int fromId, Account request) {
		Account accountToUpdate=getAccountId(fromId);
		accountToUpdate.setAddress(request.getAddress());
		accountToUpdate.setCity(request.getCity());
		accountToUpdate.setPhone(request.getPhone());
		accountToUpdate.setEmail(request.getEmail());
		return accountRepo.save(accountToUpdate);
	}

	@Override
	public void deposit(int id, double amount) {
		Account toDeposite=getAccountId(id);
		toDeposite.setBalance(toDeposite.getBalance()+amount);
		
		TransactionLog accLog=new TransactionLog("deposite",0,id,"deposite");
		toDeposite.getTransactionLogs().add(accLog);
		
		accountRepo.save(toDeposite);
		
		
		
	}

	@Override
	public void withdraw(int id, double amount) {
		Account toWithdraw=getAccountId(id);
		toWithdraw.setBalance(toWithdraw.getBalance()-amount);
		
		TransactionLog accLog=new TransactionLog("withdraw",0,id,"ithdraw");
		toWithdraw.getTransactionLogs().add(accLog);
		
		accountRepo.save(toWithdraw);
		
	}

	

}
