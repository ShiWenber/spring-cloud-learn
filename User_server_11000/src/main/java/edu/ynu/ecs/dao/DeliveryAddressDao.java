package edu.ynu.ecs.dao;

import edu.ynu.ecs.entities.DeliveryAddress;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAddressDao extends AbstractDao<DeliveryAddress, String> {
}
