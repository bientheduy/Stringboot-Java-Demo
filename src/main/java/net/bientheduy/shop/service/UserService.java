package net.bientheduy.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.HashSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import net.bientheduy.shop.shopmodel.Account;
import net.bientheduy.shop.AccountRepository; 
    @Service
    public class UserService implements UserDetailsService{
        @Autowired
        AccountRepository accountRepository;
        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            Account account = accountRepository.findByEmail(email);
            if (account !=null){
                Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
                String role = "ROLE_USER";
                if (account.getAdmin()) {
                    role = "ROLE_ADMIN";
                }
                authorities.add(new SimpleGrantedAuthority(role));
                return new User(email, account.getPassword(), authorities);
            }
            return null;
        }
    }
