package com.service.impl;

import com.dao.EmpDao;
import com.dao.PurchaseDao;
import com.dao.TestDao;
import com.model.Emp;
import com.model.Purchase;
import com.service.ITestService;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TestServiceImpl implements ITestService {

    @Resource
    private TestDao testDao;

    @Resource
    private EmpDao empDao;

    @Resource
    private PurchaseDao purchaseDao;

    @Resource
    private DataSourceTransactionManager  txManager;

    @Override
    public List<Emp> test() throws Exception {
        return testDao.listEmp();
    }

    @Override
    public Emp getEmp(String name) throws Exception {
        Map<String,Object> param=new HashMap<>();
        param.put("name",name);
        return empDao.getEmp(param);
    }

   @Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
    public void insertWithNoTrans(Emp emp, Purchase purchase) throws Exception {
        int k=0;
        empDao.insertEmp(emp);
        int j=5/k;
        purchaseDao.insertPurchase(purchase);
    }

     /* public void insertWithNoTrans(Emp emp, Purchase purchase) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = txManager.getTransaction(def);

        try {
            int k=0;
            empDao.insertEmp(emp);
            int j=5/k;
            purchaseDao.insertPurchase(purchase);
        }
        catch (Exception ex) {
            txManager.rollback(status);
            throw ex;
        }
        txManager.commit(status);

    }*/


}