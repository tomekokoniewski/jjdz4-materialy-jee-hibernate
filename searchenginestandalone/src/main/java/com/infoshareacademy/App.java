package com.infoshareacademy;

import com.infoshareacademy.searchengine.dao.UsersRepositoryDaoRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        properties.put("jboss.naming.client.ejb.context", "true");
        properties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        properties.put(Context.SECURITY_PRINCIPAL, "<user>");
        properties.put(Context.SECURITY_CREDENTIALS, "<password>");
        Context context = null;
        try {
            context = new InitialContext(properties);
        } catch (NamingException e) {
            e.printStackTrace();
        }


        if(context != null) {
            try {
                UsersRepositoryDaoRemote usersRepository =
                        (UsersRepositoryDaoRemote)
                                context.lookup("search-engine/UsersRepositoryDaoBean!com.infoshareacademy.searchengine.dao.UsersRepositoryDaoRemote");

                for (String name : usersRepository.getUsersNames()) {
                    System.out.println("User: " + name);
                }
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }
}
