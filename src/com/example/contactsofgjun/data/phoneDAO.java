package com.example.contactsofgjun.data;


public interface phoneDAO {

	public void add(Phone p);
	public Phone[] getAll();
	public Phone getPhone(int ID);
	public void removeAll();
	public void delete(int ID);
	public Phone[] search(String keyword);
	
}
