package com.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.Model.Person;

public class Dao extends HibernateDaoSupport {

    // Insert
    public void insertData(Person p) {
        this.getHibernateTemplate().save(p);
    }

    // View (Get all Persons)
    @SuppressWarnings("unchecked")
    public List<Person> viewData() {
        return (List<Person>) this.getHibernateTemplate().find("from Person");
    }

    // Delete by ID
    public void deleteData(int id) {
        HibernateTemplate template = this.getHibernateTemplate();
        Person p = template.get(Person.class, id);
        if (p != null) {
            template.delete(p);
        }
    }

    // Update Person
    public void updateData(Person p) {
        this.getHibernateTemplate().update(p);
    }

    // Get Person by ID
    public Person getPersonById(int id) {
        return this.getHibernateTemplate().get(Person.class, id);
    }

    // ✅ Get Person by Username and Password
    @SuppressWarnings("unchecked")
    public Person getPersonByUsernameAndPassword(String username, String password) {
        List<Person> list = (List<Person>) this.getHibernateTemplate()
                .find("from Person where name = ? and password = ?", username, password);

        if (list != null && !list.isEmpty()) {
            return list.get(0);  // return first match
        }
        return null;  // no match
    }
}
