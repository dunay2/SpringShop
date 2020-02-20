package com.shop.rest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;



public abstract class BaseRest implements IRestController {

	
	private JpaRepository repository;
	
	void setRepository(JpaRepository repository)
			
	{
		this.repository = repository;
	}
	
	
	@Override
	public ResponseEntity<List<Object>> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity getOne(Long id) {
		return null;
	
		
	//	public void foo(Class c){
	  //      try {
	    //
	    //Object ob = c.newInstance();
		//    } catch (InstantiationException ex) {
		//      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		//  } catch (IllegalAccessException ex) {
		//      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		//  }
		//}//
		
	}

	@Override
	public ResponseEntity createRecord(Object record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity updateRecord(Long id, Object record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity deleteRecord(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
