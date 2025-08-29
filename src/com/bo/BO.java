package com.bo;

import java.util.List;

import com.Model.Person;
import com.dao.Dao;

public class BO {
    Dao dao;

    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    // Insert
    public void inserData(Person p) {
        dao.insertData(p);
    }

    // View
    public List<Person> viewdata() {
        return dao.viewData();
    }

    // Delete
    public void deletedata(int id) {
        dao.deleteData(id);
    }

    // Update
    public void updatedata(Person p) {
        dao.updateData(p);
    }

    // Get by ID
    public Person getPersonById(int id) {
        return dao.getPersonById(id);
    }

  
    public Person getPersonByUsernameAndPassword(String username, String password) {
        return dao.getPersonByUsernameAndPassword(username, password);
    }
}
