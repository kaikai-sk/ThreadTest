package com.sk.thread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *         public boolean hasNext() {
            return cursor != size;
        }
        
        next()
        {
        	   final void checkForComodification() 
        	   {
            		if (modCount != expectedModCount)
                			throw new ConcurrentModificationException();
        	   }
        }
 * @author root
 *
 */
public class CollectionModifyExceptionTest
{
	public static void main(String[] args)
	{
		Collection<User> users=new CopyOnWriteArrayList<User>();//new ArrayList<User>();
		users.add(new User("����", 28));
		users.add(new User("����", 25));
		users.add(new User("����", 31));
		
		Iterator<User> itUsers=users.iterator();
		while(itUsers.hasNext())
		{
			System.out.println("aaa");
			User user=(User)itUsers.next();
			if("����".equals(user.getName()))
			{
				users.remove(user);
			}
			else
			{
				System.out.println(user);
			}
		}
		
	}
}
