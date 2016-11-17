package com.edu.nchu.distriuted.service.repo;

import com.edu.nchu.distributed.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Alen on 2016/10/30.
 */
public interface OrderRepo extends JpaRepository<Order, String> {
}
