package edu.ynu.ecs.service;

import edu.ynu.ecs.dao.DeliveryAddressDao;
import edu.ynu.ecs.entities.DeliveryAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class DeliveryAddressService extends AbstractTypedService<DeliveryAddress, String> {
    @Resource
    DeliveryAddressDao deliveryAddressDao;

    public DeliveryAddressService(DeliveryAddressDao dao) {
        this.dataContext = dao;
        this.deliveryAddressDao = dao;
    }

    public List<DeliveryAddress> queryAll() {
        List<DeliveryAddress> lst = null;
        try {
            lst = ((DeliveryAddressDao) this.dataContext).findAll();
            log.info("find deliveryAddress: " + lst.size());
        } catch (Exception e) {
            log.warn(e.toString());
        }
        return lst;
    }

    public Page<DeliveryAddress> queryLineItemByPage(Pageable pageable) {
        return deliveryAddressDao.queryPage(pageable, null);
    }
}
