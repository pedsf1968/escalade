package com.dsf.escalade.service;

public interface SecurityService {
   String findLoggedInUsername();

   void autoLogin(String nom, String motDePasse);

}