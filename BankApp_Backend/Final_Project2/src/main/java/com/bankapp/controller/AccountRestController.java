package com.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.config.AuthResponse;
import com.bankapp.dto.AddressChangeRequest;
import com.bankapp.entities.Account;
import com.bankapp.service.AccountService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AccountRestController {
	@Autowired
	private AccountService accountService;
	
	@GetMapping(path="account", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Account> allAccounts(){
		return accountService.getAll();
	}
	
	@GetMapping(path="account/{id}",produces=MediaType.APPLICATION_JSON_VALUE )
	public Account getAccountById(@PathVariable(name="id")int accountId){
		return accountService.getAccountId(accountId);
	}
	
	@PostMapping(path="account",produces=MediaType.APPLICATION_JSON_VALUE, 
			consumes=MediaType.APPLICATION_JSON_VALUE )
	public Account addAccount(@RequestBody Account account){
		return accountService.Save(account);
	}
	
	@PutMapping(path="account/{id}",produces=MediaType.APPLICATION_JSON_VALUE, 
			consumes=MediaType.APPLICATION_JSON_VALUE )
	public Account updateAccount(@PathVariable(name="id") int id,   @RequestBody Account account){
		return  accountService.updateDetails(id,account);
		
	}
	@DeleteMapping(path="account/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Account deleteAccount(@PathVariable(name="id") int accountId){
		return accountService.delete(accountId);
	}
	

@GetMapping(produces = "application/json")
@RequestMapping({ "/validateLogin" })
public AuthResponse validateLogin() {
		return new AuthResponse("User successfully authenticated");
}

}
