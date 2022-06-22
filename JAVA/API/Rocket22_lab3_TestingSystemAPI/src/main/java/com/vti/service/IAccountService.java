package com.vti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vti.entity.Account;
import com.vti.form.AccountFormForCreating;
import com.vti.form.AccountFormForRegister;
import com.vti.form.AccountFormForUpdate;

@Service
public interface IAccountService extends UserDetailsService {

//	 Page<Account> getAllAccount(Pageable pageable);
	
	public void createNewAccount(AccountFormForCreating accountFormForCreating);
	
	public void upDateAccount(short id, AccountFormForUpdate accountFormForUpdate);
	
	public void deleteAccount(short id);

	public Page<Account> getAllAccount(Pageable pageable, String search);

	public Account findByUsername(String username) ;
//	public Account getAccountByName(String fullNameInput);

	public void registerAccount(AccountFormForRegister accountRegister);


}
