package com.vti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.form.AccountFormForCreating;
import com.vti.form.AccountFormForRegister;
import com.vti.form.AccountFormForUpdate;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;
import com.vti.repository.IPositionRepository;
import com.vti.specification.AccountSpecification;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private IAccountRepository accountRepository;

	@Autowired
	private IDepartmentRepository departmentRepository;

	@Autowired
	private IPositionRepository positionRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Page<Account> getAllAccount(Pageable pageable, String search) {
		Specification<Account> whereAccount = null;
		if (!StringUtils.isEmpty(search)) {
			AccountSpecification fullnameSpecification = new AccountSpecification("fullName", "LIKE", search);
			AccountSpecification emailSpecification = new AccountSpecification("email", "LIKE", search);
			AccountSpecification departmentSpecification = new AccountSpecification("department", "LIKE", search);
			whereAccount = Specification.where(fullnameSpecification).or(emailSpecification)
					.or(departmentSpecification);

		}
		return accountRepository.findAll(whereAccount, pageable);

	}

	@Override
	public void createNewAccount(AccountFormForCreating accountFormForCreating) {
		Account account = new Account();
		Department department = departmentRepository.getById(accountFormForCreating.getDepartmentId());
		Position position = positionRepository.getById(accountFormForCreating.getPositionId());

		account.setEmail(accountFormForCreating.getEmail());
		account.setUsername(accountFormForCreating.getUsername());
		account.setFullName(accountFormForCreating.getFullname());
		account.setDepartment(department);
		account.setPosition(position);

		accountRepository.save(account);

	}

	@Override
	public void upDateAccount(short id, AccountFormForUpdate accountFormForUpdate) {
		Account account = accountRepository.getById(id);
		Department department = departmentRepository.getById(accountFormForUpdate.getDepartmentId());
		Position position = positionRepository.getById(accountFormForUpdate.getPositionId());
		account.setFullName(accountFormForUpdate.getFullname());
		account.setDepartment(department);
		account.setPosition(position);
		account.setDepartment(department);
		account.setUsername(accountFormForUpdate.getUsername());
		accountRepository.save(account);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUsername(username);
		if (account == null) {
			throw new UsernameNotFoundException(username);
		}

		return new User(account.getUsername(), account.getPassword(), AuthorityUtils.createAuthorityList("user"));

	}

	@Override
	public void deleteAccount(short id) {
		accountRepository.deleteById(id);
	}

	@Override
	public Account findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

	@Override
	public void registerAccount(AccountFormForRegister accountRegister) {
		Account account = new Account();
		Department department = departmentRepository.getById(accountRegister.getDepartmentId());
		Position position = positionRepository.getById(accountRegister.getPositionId());

		account.setEmail(accountRegister.getEmail());
		account.setUsername(accountRegister.getUsername());
		account.setFullName(accountRegister.getFullname());
		account.setDepartment(department);
		account.setPosition(position);
//		Trước khi lưu thông tin password cần phải mã hóa password
		String passEncode = passwordEncoder.encode(accountRegister.getPassword());

//Lưu thông tin password
		account.setPassword(passEncode);
		accountRepository.save(account);

	}

}
