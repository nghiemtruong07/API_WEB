package com.vti.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.DTO.AccountDTO;
import com.vti.entity.Account;
import com.vti.form.AccountFormForCreating;
import com.vti.form.AccountFormForRegister;
import com.vti.form.AccountFormForUpdate;
import com.vti.service.IAccountService;

@RestController
@ComponentScan("com")
@RequestMapping(value = "api/v1/accounts")
@CrossOrigin("*")

public class AccountController {

	@Autowired
	private IAccountService accountService;

	@GetMapping
	public ResponseEntity<?> getAllAccount(Pageable pageable,@RequestParam(required = false) String search) {
	
		Page<Account> accountPage_DB = accountService.getAllAccount(pageable,search);
		
		Page<AccountDTO> accountPage_dtos = accountPage_DB.map(new Function<Account, AccountDTO>() {
			@Override
			public AccountDTO apply(Account account) {
				AccountDTO accountDTO = new AccountDTO();
				accountDTO.setId(account.getId());
				accountDTO.setEmail(account.getEmail());
				accountDTO.setUsername(account.getUsername());
				accountDTO.setFullname(account.getFullName());
				accountDTO.setDepartment(account.getDepartment().getName());
				accountDTO.setPosition(account.getPosition().getName().toString());
				accountDTO.setCreateDate(account.getCreateDate());
				return accountDTO;
			}
			
		});
	
		return new ResponseEntity<>(accountPage_dtos, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<?> createNewAccount(@RequestBody AccountFormForCreating accountFormForCreating) {
		accountService.createNewAccount(accountFormForCreating);
		return new ResponseEntity<>("Create Success", HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> upDateAccount(@PathVariable(name = "id") short id,
			@RequestBody AccountFormForUpdate accountFormForUpdate) {
		accountService.upDateAccount(id, accountFormForUpdate);

		return new ResponseEntity<>("Update completed!!", HttpStatus.OK);

	}
//	xóa account by id ->
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable(name = "id") short id)
	{
		accountService.deleteAccount(id);
		return new ResponseEntity<String>("Delete successfuly !!",HttpStatus.OK) ;
	}
//	hàm đăng kí thêm mới tài khoản
	@PostMapping(value = "/register")
	public ResponseEntity<?> registerAccount(@RequestBody AccountFormForRegister accountRegister) {
		accountService.registerAccount(accountRegister);
		
		return new ResponseEntity<>("Create Success", HttpStatus.CREATED);
	}

}
