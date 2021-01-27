package com.bankapp.service;

import java.util.List;

import com.bankapp.dto.AddressChangeRequest;
import com.bankapp.entities.Account;

public interface AccountService {
	public List<Account> getAll();
	public Account getAccountId(int id);
	public Account Save(Account account);
	public Account delete(int id);
	public void transfer (int fromId, int toId, double balance);
	public Account updateDetails(int fromId, Account account );
	public void deposit(int id, double amount);
	public void withdraw(int id, double amount);

}
