package com.vti.controller;

import java.security.Principal;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.DTO.AccountDTO;
import com.vti.entity.Account;
import com.vti.service.AccountService;
import com.vti.service.IAccountService;

@RestController
@RequestMapping(value = "api/v1/login")
@CrossOrigin("*")

public class LoginController {
	@Autowired
	private IAccountService accountService;
	@GetMapping
	public ResponseEntity<?> getAllAccount(Principal principal) {
//
		String username = principal.getName();
//		tìm account login dựa vào username đẩy về
		Account accountLoginInfor = accountService.findByUsername(username);
		
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId(accountLoginInfor.getId());
		accountDTO.setEmail(accountLoginInfor.getEmail());
		accountDTO.setUsername(accountLoginInfor.getUsername());
		accountDTO.setFullname(accountLoginInfor.getFullName());
		accountDTO.setDepartment(accountLoginInfor.getDepartment().getName());
		accountDTO.setPosition(accountLoginInfor.getPosition().getName().toString());
		accountDTO.setCreateDate(accountLoginInfor.getCreateDate());
		
		
		return new ResponseEntity<>(accountDTO, HttpStatus.OK);

	}

}
