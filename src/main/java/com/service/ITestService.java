package com.service;

import com.model.Emp;
import com.model.Purchase;

import java.util.List;

public interface ITestService {
    public Emp getEmp(String name) throws Exception;
    public void insertWithNoTrans(Emp emp, Purchase purchase) throws Exception;
}
