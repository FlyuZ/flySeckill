package com.nwafu.seckill.mapper;

import com.nwafu.seckill.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AddressMapperTest {
    @Autowired
    private AddressMapper addressMapper;
    @Test
    public void findByUserId() {

    }

    @Test
    public void findDefaultAddress() {

    }

    @Test
    public void insert() {
        addressMapper.insert(1,"泰安市");
    }

    @Test
    public void delete() {

    }

    @Test
    public void setDefaultAddress() {

    }
}